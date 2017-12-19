package com.css.common.web.apachemq.handle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.css.business.web.subsysmanu.bean.MauParam;
import com.css.business.web.subsysmanu.mauManage.service.MauParamManageService;
import com.css.business.web.syswebsoket.bean.EchartSeriesVo;
import com.css.business.web.syswebsoket.bean.EchartsVo;
import com.css.commcon.util.DisplayPropertyUtil;
import com.css.commcon.util.YorkUtil;
import com.css.common.util.DateUtil;
import com.css.common.util.EhCacheFactory;
import com.css.common.util.PropertyFileUtil;
import com.css.common.web.apachemq.bean.ParamInfo;
import com.css.common.web.apachemq.bean.UploadWebBoard;
import com.css.common.web.syscommon.service.SpringContextHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @Title: MesApacheMqReaderHandler_DisplayRT.java
 * @Package com.css.common.web.apachemq.handle
 * @Description: MQ对电子看板进行操作的处理类,处理实时数据,队列:QUE_TOPIC_P2P_KANBAN_RT
 * @author rb
 * @date 2017年7月19日 上午11:33:40
 * @company SMTC
 */

public class MesApacheMqReaderHandler_DisplayRT implements Serializable,
		MessageListener {

	private static final long serialVersionUID = -1357151588231534234L;
	private String path = null;

	private WebApplicationContext wac;
	/** 押出机编号,macCode : 螺杆转速名称 */
	private static HashMap<String,String> YACHU = new HashMap<String, String>();
	
	static{
		YACHU.put("EA120", "test");  
    	YACHU.put("EB", "20"); 
    	YACHU.put("EC", "20"); 
    	YACHU.put("ED", "80机螺杆转速"); 
    	YACHU.put("EQ", "20"); 
    	YACHU.put("EK", "20"); 
    	YACHU.put("EJ", "20"); 
	}
	/** 机台速度 */
	public static String MAC_SPEED = "机台速度";
	/** 螺杆速度 */
	public static String MAC_GAN_SPEED = "螺杆速度";
	// 工具类
	Gson gson = new Gson();
	private PropertyFileUtil propertyFileUtil = new PropertyFileUtil();
	DisplayPropertyUtil displayPropertyUtil = new DisplayPropertyUtil("/conf/mauparam.properties");
	EhCacheFactory cacheFactory = EhCacheFactory.getInstance();
	private MauParamManageService mpService = SpringContextHolder.getBean("mauParamManageService");
	private Logger log = Logger.getLogger(MesApacheMqReaderHandler_DisplayRT.class);
	//监听消息队列
		//private Connection conn;
		//private Session session;
		
	public MesApacheMqReaderHandler_DisplayRT(){
		path = propertyFileUtil.getProp("reportUpload");
	}	

	@Override
	public void onMessage(Message msg) {
		TextMessage txtMessage = (TextMessage) msg;
		String text = null;
		try {
			text = txtMessage.getText();
			//把mq写到本地观察
			//writeLocal(text);
			// 获得消息发送的vo
			// MqDisplayVo displayVo = gson.fromJson(text, MqDisplayVo.class);
			// String type = displayVo.getType();
			// MQ对应的类型 msg 时效性低 存缓存 exception params 时效性高 直接刷新,或者替换数据
			UploadWebBoard uploadWebBoard = gson.fromJson(text,UploadWebBoard.class);
			//String seqName = uploadWebBoard.getSeqName();
			/*if ("params".equals(type)) {
				// 实时数据类型
				analysis_params(displayVo);
			}*/
			if (uploadWebBoard != null) {
				analysis_params(uploadWebBoard);
				writeLocal(uploadWebBoard);
			}
			msg.acknowledge();
		} catch (Exception e) {
			e.printStackTrace();
			/*try {
				writeLocal(uploadWebBoard);
			} catch (IOException e1) {
				e1.printStackTrace();
			}*/
			log.error(e);
		}
	}

	/**
	 * @Description: 实时数据的展示,将具体的参数传到前台.主要是生产工序时候会有实时数据,叉车也需要时效性
	 * @param displayVo
	 * @throws Exception 
	 */
	private void analysis_params(/*MqDisplayVo displayVo*/ UploadWebBoard uploadWebBoard) throws Exception {
		//工序名称
		//机台code 
		String macCode = uploadWebBoard.getMachineCode();
		//判断多少机台在线,往上面发数据
		jundgeMacInfo(macCode);
		//所有的参数 参数code : html_id
		List<ParamInfo> infoList = uploadWebBoard.getParamInfoList();
		//判断参数list是否为空,如果为空 直接返回
		if (infoList==null || infoList.size()==0) {
			return;
		}
		
		
		//传给websocket的map
		Map<String,String>paMap = new HashMap<>();
		paMap.put("macCode", macCode);
		/**
		 * 注意.******注意*****
		 */
		//重写
		for (ParamInfo paramInfo : infoList) {
			try {
				//String code = paramInfo.getParamCode();
				String name = paramInfo.getParamName();
				String value = paramInfo.getParamValue();
				paMap.put(name, value);
			} catch (Exception e) {
				continue;
			}
		}
		
		//实时参数写到数据库
		setMauParam(paMap);
		
		
		cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_机台_实时_参数+"_"+macCode, gson.toJson(paMap));
		//机台速度
		String macSpeed = uploadWebBoard.getMacSpeed();
		//判断有机台速度
		if (macSpeed != null && !"".equals(macSpeed)) {
			//螺杆速度
			String  ganSpeed = null;
			//如果是押出机
			if (YACHU.keySet().contains(macCode)) {
				//求出螺杆转速
				ganSpeed = paMap.get(YACHU.get(macCode));
			}
			//保存实时速度
			String realSpeed = cacheFactory.getWebsocketCmdCache(YorkUtil.Memcache_机台_实时_速度+"_"+macCode);
			String realEchart = cacheFactory.getWebsocketCmdCache(YorkUtil.Memcache_机台_实时_Echart+"_"+macCode);
			if ( "".equals(realEchart) || realEchart == null ) {
				String time = DateUtil.format(new Date(), "HH:mm");
				//-----------------------------
				EchartsVo vo = new EchartsVo();
				vo.setTitle(macCode+":"+DateUtil.format(new Date(), "yyMMdd"));
				
				//X轴
				List<String> xAxis = new ArrayList<>(); 
				xAxis.add(time);
				//图例
				List<String> legends = new ArrayList<>();
				//y轴具体的值
				Map<String, EchartSeriesVo> seriesData = new HashMap<>();
				//判断是否是特殊机台
				if(YACHU.keySet().contains(macCode)){
					//特殊机台
					//机台速度
					legends.add(MAC_SPEED);
					EchartSeriesVo esVoSpeed = new EchartSeriesVo();
					List<String> esVoSpeedList = new ArrayList<>();
					esVoSpeedList.add(macSpeed);
					esVoSpeed.setData(esVoSpeedList);
					esVoSpeed.setType("left");
					seriesData.put(MAC_SPEED, esVoSpeed);
					//螺杆速度
					legends.add(MAC_GAN_SPEED);
					EchartSeriesVo esVoGanSpeed = new EchartSeriesVo();
					List<String> esVoGanSpeedList = new ArrayList<>();
					esVoGanSpeedList.add(ganSpeed);
					esVoGanSpeed.setData(esVoGanSpeedList);
					esVoGanSpeed.setType("left");
					seriesData.put(MAC_GAN_SPEED, esVoGanSpeed);
				}else{
					//非特殊机台
					//机台速度
					legends.add(MAC_SPEED);
					EchartSeriesVo esVoSpeed = new EchartSeriesVo();
					List<String> esVoSpeedList = new ArrayList<>();
					esVoSpeedList.add(macSpeed);
					esVoSpeed.setData(esVoSpeedList);
					esVoSpeed.setType("left");
					seriesData.put(MAC_SPEED, esVoSpeed);
				}
				
				//
				vo.setLegends(legends);
				vo.setSeriesData(seriesData);
				vo.setxAxis(xAxis);
				cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_机台_实时_Echart+"_"+macCode, gson.toJson(vo));
			} else {
				//--------------------------------------------
				EchartsVo vo = gson.fromJson(realEchart, EchartsVo.class);
				List<String> xAxis = vo.getxAxis();
				xAxis.add(DateUtil.format(new Date(), "HH:mm"));
				Map<String, EchartSeriesVo> seriesData = vo.getSeriesData();
				if(YACHU.keySet().contains(macCode)){
					//特殊机台
					//机台速度
					EchartSeriesVo esVoSpeed = seriesData.get(MAC_SPEED);
					List<String> speedData = esVoSpeed.getData();
					speedData.add(macSpeed);
					esVoSpeed.setData(speedData);
					seriesData.put(MAC_SPEED, esVoSpeed);
					//螺杆速度
					EchartSeriesVo esVoGanSpeed = seriesData.get(MAC_GAN_SPEED);
					List<String> ganSpeedData = esVoGanSpeed.getData();
					ganSpeedData.add(ganSpeed);
					esVoGanSpeed.setData(ganSpeedData);
					seriesData.put(MAC_GAN_SPEED, esVoGanSpeed);
				}else{
					//正常机台
					//机台速度
					EchartSeriesVo esVoSpeed = seriesData.get(MAC_SPEED);
					List<String> speedData = esVoSpeed.getData();
					speedData.add(macSpeed);
					esVoSpeed.setData(speedData);
					seriesData.put(MAC_SPEED, esVoSpeed);
				}
				vo.setSeriesData(seriesData);
				vo.setxAxis(xAxis);
				cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_机台_实时_Echart+"_"+macCode, gson.toJson(vo));
				
			}
		}
		
		//更新车间看板的数据
		//YorkUtil.Memcache_ALL_车间参数 
		String allMacShow = cacheFactory.getWebsocketCmdCache(YorkUtil.Memcache_ALL_车间参数);
		
		if (allMacShow== null  || "".equals(allMacShow)) {
			//新建
			Map<String,Map<String,String>> map = new HashMap<>(2);
			
			
		}else{
			Map<String,Map<String,String>> macMap = gson.fromJson(allMacShow, new TypeToken<Map<String,Map<String,String>>>(){}.getType());
			//获得机台参数
			Map<String, String> map = macMap.get(macCode);
			if (map != null ) {
				//更新本机台缓存
				//通过macCode 去查询下一个工单
				
				//paMap
				macMap.put(macCode, paMap);
				
			}else{
				//新建本名机台的缓存
			}
			
			
		}
		//cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_ALL_车间参数 , jsonStr);
	}
	
	/**
	 * 将基本实时数据写到数据库
	 * @param paMap
	 * @throws Exception 
	 */
	private void setMauParam(Map<String, String> paMap) throws Exception {
		//实时参数 多长时间存一条数据
		int min = Integer.parseInt(propertyFileUtil.getProp("MAC_PARAM_MIN"));
		Long judgeTime = (long) (min*60*1000);
		String macCode = paMap.get("macCode");
		
		MauParam mauparam = new MauParam();
		if(macCode==null || "".equals(macCode)){
			throw new Exception("机台编码为空");
		}
		MauParam mau = mpService.getByMacCode(macCode);
		if (mau != null) {
			mauparam = mau;
		}
		String params = displayPropertyUtil.getProp(macCode);
		if (params ==null || "".equals(params)) {
			return;
		}
		String[] split = params.split(",");
		// 0 速度1线径
		for (int i = 0; i < split.length; i++) {
			String param = split[i];
			if (param == null || "".equals(param)) {
				continue;
			}
			if (i == 0) {
				mauparam.setSpeed((float)Math.round(Float.parseFloat(paMap.get(param))*100)/100);
			}else if (i == 1) {
				mauparam.setDiameter((float)Math.round(Float.parseFloat(paMap.get(param))*100)/100 );
			}
			Long paramTime = 0L;
			if (mauparam.getCreateDate() != null) {
				paramTime = mauparam.getCreateDate().getTime();
			}
			Long exTime = System.currentTimeMillis() - paramTime;
			if (exTime > judgeTime) {
				//新建
				mauparam.setMacCode(macCode);
				mauparam.setCreateDate(new Timestamp(System.currentTimeMillis()));
				try {
					mpService.save(mauparam);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				//更新
				try {
					mpService.updateByCon(mauparam, false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 判断能上传数据的机台
	 * @param macCode
	 */
	private void jundgeMacInfo(String macCode) {
		
		//当天上传了数据的机台
		String todayMacStr = cacheFactory.getWebsocketCmdCache(YorkUtil.Memcache_当日上传机台);
		TreeSet<String>todayMac = new TreeSet<>();
		if (todayMacStr != null && !"".equals(todayMacStr)) {
			todayMac = gson.fromJson(todayMacStr, new TypeToken<TreeSet<String>>(){}.getType());
			todayMac.add(macCode);
		}else {
			todayMac.add(macCode);
		}
		cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_当日上传机台, gson.toJson(todayMac));
		//几分钟机台没上传数据就默认关闭
		Integer min = Integer.parseInt(propertyFileUtil.getProp("JUDGE_MAC_MIN"));
		Long judgeTime = (long) (min * 60 *1000);
		//新建
		Map<String, Long>map = new HashMap<>(64);
		
		long nowTime = System.currentTimeMillis();
		String cache = cacheFactory.getWebsocketCmdCache(YorkUtil.Memcache_机台code);
		if (cache == null || "".equals(cache)) {
			map.put(macCode, nowTime);
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_机台code, gson.toJson(map));
		}else{
			map = gson.fromJson(cache, new TypeToken<Map<String, Long>>(){}.getType());
			map.put(macCode, nowTime);
			//比较其他机台的最后一次上传数据是什么时候
			for (String code : map.keySet()) {
				Long lastTime = map.get(code);
				//差值
				Long exTime = nowTime - lastTime;
				if ((exTime - judgeTime ) > 0 ) {
					//大于判断时间,删除机台
					map.remove(code);
				}else{
					map.put(code, nowTime);
				}
				
			}
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_机台code, gson.toJson(map));
		}
	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param text
	 * @throws IOException 
	 */
	private void writeLocal(UploadWebBoard text) throws IOException {
		// 把信息写到本地,看一下具体的信息是什么
		//File file = new File("j:\\mq.txt");
		//String pp = path + "\\mq.txt";
		File file = new File("D:\\mq机台实时数据\\"+DateUtil.format(new Date(), "yyyyMMdd")+"\\"+text.getMachineCode()+".txt");
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
			     }
			//file.mkdirs();
			file.createNewFile();
		}
		// 追加
		FileWriter fw;
		try {
			fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.newLine();
			bw.write(gson.toJson(text));
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();

		}

	//	System.out.println("Done");
	}

}
