 package com.css.common.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.servlet.http.HttpServlet;

import net.sf.json.JSONObject;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.css.business.web.subsysplan.bean.PlaMacTaskRecord;
import com.css.business.web.subsysplan.plaManage.service.PlaCourseManageService;
import com.css.commcon.util.YorkUtil;
import com.css.common.util.EhCacheFactory;
import com.css.common.util.PropertyFileUtil;
import com.css.common.web.apachemq.bean.CommandVO;
import com.css.common.web.apachemq.handle.ApacheMqSender;
import com.google.gson.Gson;

/**
 * @TODO : 核心Servlet,监听web端消息队列，后台处理后，再与看板、机台相关方通讯。
 * @author: 翟春磊
 * @DATE : 2017年7月18日
 */
public class MesApacheMqReaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1919363511303397854L;
	private Logger log = Logger.getLogger(MesApacheMqReaderServlet.class);
	
	private PropertyFileUtil pfu = new PropertyFileUtil();
	private PlaCourseManageService plaCourseManageService;
	//监听消息队列
	private Connection conn;
	//private Session session;
	
	public MesApacheMqReaderServlet() {

	}

	@Override
	public void init() {
		log.log(Level.INFO, "WEB服务端Servlet启动。开始接收处理机台通讯...");

		final WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		
		plaCourseManageService = (PlaCourseManageService)wac.getBean("plaCourseManageService");
		
		String BROKER_URL = pfu.getProp("APACHE_MQ_URL");
		//创建连接工厂
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(BROKER_URL);
		//获得连接
		try {
			conn = connectionFactory.createConnection();
			//conn.setClientID(YorkUtil.QUEUE_TOPIC_WEB_SYSTEM_ID);
			//start
			// 手持机
			// new MesApacheMqReaderHandler_ShouChiJi(wac);
			
			// 机台生产异常处理类
			//new MesApacheMqReaderHandler_Exception(conn);
			
			//接收来自各方指令。目前是机台呼叫叉车
			//new MesApacheMqReaderHandler_ReceiveCommand(conn);
			
			// 机台生产，实时数据上报 处理类.    
			//new MesApacheMqReaderHandler_RuntimeData(conn);
			
			// 机台生产完毕后，打包保存
			//new MesApacheMqReaderHandler_SaveData(conn);
			
			
			// 向机台发送测试数据
			Test_SendTestQueueInfo_PerMinute t = new Test_SendTestQueueInfo_PerMinute();
			//t.setPlaCourseManageService(plaCourseManageService);
			t.start();
			
			/***********/
			
			// 电子看板接收非实时数据的处理类(中心服务器发送)
			// new MesApacheMqReaderHandler_Display(wac);
			
			// 电子看板接收实时数据的处理类(机台基础数据)
			// new MesApacheMqReaderHandler_DisplayRT(wac);
			conn.start();
			
			//机台参数自动记载到ehcache线程
			//Thread macThread = new MacReadDicThread();
			//macThread.run();
			//t.start();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

/**
 * @TODO : 发测试数据到消息队列中。供测试用. 1分钟一次
 * @author: 翟春磊
 * @DATE : 2017年7月14日
 */
class Test_SendTestQueueInfo_PerMinute extends Thread {
	private Logger log = Logger
			.getLogger(Test_SendTestQueueInfo_PerMinute.class);
	private ApacheMqSender s;
	private PlaCourseManageService plaCourseManageService;
	private EhCacheFactory cacheFactory = EhCacheFactory.getInstance();
	
	private int i = 0;

	protected Test_SendTestQueueInfo_PerMinute() {
		//s = ApacheMqSender.getInstance();
		//this.start();
	}
	
	//测试车间看板机台生产情况
	public void run(){
		while( i < 100){
			try {
				List<PlaMacTaskRecord> lst = new ArrayList<PlaMacTaskRecord>();
				PlaMacTaskRecord r = new PlaMacTaskRecord();
				r.setMaccode("CC");
				lst.add(r);
				r = new PlaMacTaskRecord();
				r.setMaccode("VF");
				lst.add(r);
				r = new PlaMacTaskRecord();
				r.setMaccode("DA");
				lst.add(r);
				r = new PlaMacTaskRecord();
				r.setMaccode("VB");
				lst.add(r);
				
				//JSONObject js = JSONObject.f(lst);
				Gson g = new Gson();
				String msg = g.toJson(lst);
				//cacheFactory.addExceptionCache("1", js.toString());
				//getMacShowCaChe
				cacheFactory.addMacShowCache(YorkUtil.Memcache_车间动态 ,msg);
				
				sleep(1000 * 30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//测试车间看板异常数据
	public void run_b2(){
		while( i < 100){
			try {
				i ++;
				Random r =  new Random();
				Map<String,String> m = new HashMap<String,String>();
				m.put("macCode", "测试机台编码"+ r.nextInt());
				m.put("courseCode", "测试工单号"+r.nextInt());
				m.put("meInfo",  "测试异常内容" + r.nextInt());
				m.put("meTime", "测试异常发生时间"+r.nextInt());
				m.put("agentBy", "测试操作人"+r.nextInt());
				
				JSONObject js = JSONObject.fromObject(m);
				//cacheFactory.addExceptionCache("1", js.toString());
				cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_异常 , js.toString());
				
				sleep(1000 * 30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void run_bak() {
		while (true) {
			try {
				// 机台
				/*s.sendMsg_point_to_point("CL_01", "测试信息");
				s.sendMsg_point_to_point("CL_02", "测试信息");
				s.sendMsg_point_to_point("FP-2", "测试信息");
				s.sendMsg_point_to_point("HT120-1", "测试信息");
				s.sendMsg_point_to_point("HT120-2", "测试信息");
				s.sendMsg_point_to_point("HT150-1", "测试信息");
				s.sendMsg_point_to_point("HT150-2", "测试信息");
				s.sendMsg_point_to_point("HT200-1", "测试信息");
				s.sendMsg_point_to_point("JX01", "测试信息");
				s.sendMsg_point_to_point("JX02", "测试信息");
				s.sendMsg_point_to_point("JY90-1", "测试信息");
				s.sendMsg_point_to_point("JY90-5", "测试信息");
				s.sendMsg_point_to_point("JY90-6", "测试信息");
				s.sendMsg_point_to_point("JY90-8", "测试信息");
				s.sendMsg_point_to_point("JY90-9", "测试信息");
				s.sendMsg_point_to_point("KZDG1", "测试信息");
				s.sendMsg_point_to_point("KZDG2", "测试信息");
				s.sendMsg_point_to_point("KZDG3", "测试信息");
				s.sendMsg_point_to_point("KZDG4", "测试信息");
				s.sendMsg_point_to_point("KZGS36-1", "测试信息");
				s.sendMsg_point_to_point("KZGS60-1", "测试信息");
				s.sendMsg_point_to_point("LS01", "测试信息");
				s.sendMsg_point_to_point("LS02", "测试信息");
				s.sendMsg_point_to_point("SA001", "测试信息");*/
				
				//发送查询工单命令.  模拟机台向web发查询工单命令
				CommandVO vo = new CommandVO();
				vo.setCommType("取工单");
				vo.setFromMachineCode("LS02");
				JSONObject js = JSONObject.fromObject(vo);
				String queue = "QUE_WEB_Command_Receive_p2p";
				s.sendMsg_point_to_point(queue, js.toString());
				System.out.println("向web端接收指令的消息队列发送消息:"+js.toString());
				//取工单
				//List<PlaCourse> plst = plaCourseManageService.fetchGD_and_sendToMachine("LS01");

				// 手持机 目前不发

				// 电子看板.7工序看板差不多， 另车间、仓库、生产三看板单独。
				// 看板命令：hideException,showException,refreshPage(与各看板定义它的名称,通知看板刷新指定子页面)
				// 60秒
				sleep(60 * 1000 * 2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(e);
			}
		}
	}

	public PlaCourseManageService getPlaCourseManageService() {
		return plaCourseManageService;
	}

	public void setPlaCourseManageService(
			PlaCourseManageService plaCourseManageService) {
		this.plaCourseManageService = plaCourseManageService;
	}
}
