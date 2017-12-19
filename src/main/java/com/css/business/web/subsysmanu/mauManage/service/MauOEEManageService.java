package com.css.business.web.subsysmanu.mauManage.service;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.craManage.dao.CraSeqManageDAO;
import com.css.business.web.subsysmanu.bean.MauOEE;
import com.css.business.web.subsysmanu.bean.MauOEEHistory;
import com.css.business.web.subsysmanu.bean.MauOEEVO;
import com.css.business.web.subsysmanu.mauManage.dao.MauOEEManageDAO;
import com.css.business.web.subsysplan.bean.PlaMacTask;
import com.css.business.web.subsysplan.plaManage.service.PlaMachinePlanManageService;
import com.css.business.web.subsysplan.plaManage.service.PlaMachinePlanScheduleManageService;
import com.css.commcon.util.YorkUtil;
import com.css.common.util.DateUtil;
import com.css.common.util.EhCacheFactory;
import com.css.common.web.apachemq.bean.MqMauOEEVo;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauOEEManageService")
public class MauOEEManageService extends BaseEntityManageImpl<MauOEE, MauOEEManageDAO> {
	@Resource(name="mauOEEManageDAO")
	//@Autowired
	private MauOEEManageDAO dao;
	
	
	@Override
	public MauOEEManageDAO getEntityDaoInf() {
		return dao;
	}
	private Logger log = Logger.getLogger(MauOEEManageService.class);
	/** 机器service */
	@Resource(name="mauMachineManageService")
	private MauMachineManageService macService ;
	/** 机台计划进度表 */
	@Resource(name="plaMachinePlanScheduleManageService")
	private PlaMachinePlanScheduleManageService pmsService;
	/** 机台计划表 */
	@Resource(name="plaMachinePlanManageService")
	private PlaMachinePlanManageService psService;
	/** 工序service */
	@Resource(name="craSeqManageDAO")
	//private CraSeqManageService seqService;
	private CraSeqManageDAO craSeqManageDAO;
	/** 机台月oee的月计划  */
	//@Resource(name="mauOEEMonthManageDAO")
	//private MauOEEMonthManageDAO mauMonthDAO;
	/** mauOEE历史速度的service */
	@Resource(name="mauOEEHistoryManageService")
	private MauOEEHistoryManageService  mhService;
	
	
	private EhCacheFactory factory = EhCacheFactory.getInstance();
	/**   
	 * @Description: 条件分页查询   
	 * @param map
	 * @param page
	 * @return         
	 */ 
	public Page getPageList(Map<String, String> map, Page page) {
		// TODO Auto-generated method stub
		String hql = "from MauOEE where 1 = 1 ";
		StringBuilder sb = new StringBuilder(hql);
		if(map != null && map.size() > 0){
			if (map.get("start") != null && map.get("start") !="") {
				sb.append(" and createDate >= '"+map.get("start").trim()+"'");
			}
			if (map.get("end") != null && map.get("end") !="") {
				sb.append(" and createDate <= '"+map.get("end").trim()+"'");
			}
			if (map.get("seqName") != null && map.get("seqName") !="") {
				sb.append(" and seqName = '%"+map.get("seqName").trim()+"%'");
			}
			if (map.get("macCode") != null && map.get("macCode") !="") {
				sb.append(" and macCode = '%"+map.get("macCode").trim()+"%'");
			}
			if (map.get("oee") != null && map.get("") !="") {
				sb.append(" and oee  "+map.get("oee").trim()+"");
			}
		}
		sb.append(" order by createDate DESC ");
		Page pageQuery = page;
		try {
			 pageQuery = dao.pageQuery(sb.toString(), page.getPageNo(), page.getPagesize());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return pageQuery;
	}


	/**   
	 * @Description: 机台关机时,发送一条数据给上来,总结出OEE
	 * @param vo         
	 * @throws Exception 
	 */ 
	public void saveByMqVo(MqMauOEEVo vo) throws Exception {
		//零碎品
		Integer bitsPieces = vo.getBitsPieces();
		//过量耗用
		Integer overdoes = vo.getOverdoes();
		//不良品
		Integer rejects = vo.getRejects();
		//工单号
		String courseCode = vo.getCourseCode();
		//机台code
		String macCode = vo.getMachineCode();
		//所属步骤
		Integer step = vo.getStep();
		Integer startMin = 0;
		Integer endMin = 0;
		Date startDate = null;
		Date endDate = null;
		try {
			
			Timestamp startTime = vo.getStart() == null ? null
					: new Timestamp(vo.getStart()); // 实际开工时间
			Timestamp endTime = vo.getEnd() == null ? null
					: new Timestamp(vo.getEnd());
			
			
			startDate = new Date(startTime.getTime()) ;//DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", vo.getStart());
			endDate = new Date(endTime.getTime()) ;//DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", vo.getEnd());
			
			startMin = vo.getStartMin();
			endMin = vo.getEndMin();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		//机台任务 求速度
		PlaMacTask task = psService.getPlaMacTask(macCode,courseCode,step);
		if(task==null){
			log.error("oee 机台任务为空");
			return;
		}
		// 机台任务详情,查询总共的长度
		//psService.getTakAxis();
		// 机台速度
		Float speed = task.getSpeed();
		
		//计算oee
		MauOEE oee = new MauOEE();
		oee.setBitsPieces(bitsPieces);
		oee.setCourseCode(courseCode);
		oee.setEndMin(endMin);
		oee.setStartMin(startMin);
		oee.setMainOperator(task.getMain_by());
		oee.setSecondOperator(task.getVice_by());
		oee.setStartTime(startDate);
		oee.setEndTime(endDate);
		oee.setFullySpeed(speed);
		oee.setInMonth(Integer.parseInt(DateUtil.format(new Date(), "yyyyMM")));
		
		Double factOutput = 0.0;
		//获得该计划长度 实际完成长度
		factOutput = psService.getLength(macCode,courseCode,task.getStep()) * task.getSchedule();
		// 满载完成长度
		Double fullyOutput = 0.0;
		fullyOutput = (double) (speed * startMin );
		//计算
		DecimalFormat df=new DecimalFormat("0.00");
		Double rateStart = 0.0;
		//开机率 : 开机运行时间/(总时间)
		rateStart = Double.parseDouble(df.format((double)startMin/(startMin+endMin)));
		//速度满载率 实际/理论
		Double rateSpeed = 0.0;
		rateSpeed = Double.parseDouble(df.format(factOutput/fullyOutput));
		// 正品率 (实际-不良)/(实际+零碎) - 过量/实际
		Double rateQuality = 0.0;
		double parseA = Double.parseDouble(df.format((double)(factOutput - rejects)/(factOutput + bitsPieces)));
		double parseB = Double.parseDouble(df.format((double)overdoes/factOutput));
		rateQuality = parseA - parseB;
		oee.setFactOutput(factOutput);
		oee.setFullyOutput(fullyOutput);
		oee.setRateQuality(rateQuality);
		oee.setRateSpeed(rateSpeed);
		oee.setRateStart(rateStart);
		
		try {
			this.save(oee);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//当这个oee完成的时候,删除缓存中这个机台的速度缓存
		MauOEEHistory history = new MauOEEHistory();
		history.setCourseCode(vo.getCourseCode());
		history.setCreateBy(vo.getMachineCode());
		history.setCreateDate(new Date());
		history.setMacCode(vo.getMachineCode());
		//history.setMainOperator(mauOEE.getMainOperator());
		//history.setProgxh(mauOEE.getProGgxh());
		history.setEchartsVO(factory.getWebsocketCmdCache(YorkUtil.Memcache_机台_实时_速度+"_"+vo.getMachineCode()));
		try {
			mhService.save(history);
			factory.removeWebsocketCmdCache(YorkUtil.Memcache_机台_实时_速度+"_"+vo.getMachineCode());
			//this.save(mauOEE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param response
	 * @param map         
	 * @throws IOException 
	 * @throws WriteException 
	 */ 
	public void exportExcel(HttpServletResponse response,
			Map<String, String> map) throws IOException, WriteException {
		// TODO Auto-generated method stub
		String hql = "from MauOEE where 1 = 1 ";
		StringBuilder sb = new StringBuilder(hql);
		if(map != null && map.size() > 0){
			if (map.get("start") != null && map.get("start") !="") {
				sb.append(" and createDate >= '"+map.get("start").trim()+"'");
			}
			if (map.get("end") != null && map.get("end") !="") {
				sb.append(" and createDate <= '"+map.get("end").trim()+"'");
			}
			if (map.get("seqName") != null && map.get("seqName") !="") {
				sb.append(" and seqName = '%"+map.get("seqName").trim()+"%'");
			}
			if (map.get("macCode") != null && map.get("macCode") !="") {
				sb.append(" and macCode = '%"+map.get("macCode").trim()+"%'");
			}
			if (map.get("oee") != null && map.get("") !="") {
				sb.append(" and oee  "+map.get("oee").trim()+"");
			}
		}
		sb.append("  order by macCode,createDate DESC ");
		List<MauOEE> listQuery = dao.listQuery(sb.toString());
		String[] arrTitle = new String[] { "机器编号", "工序名称", "实际产出", "满载产出","不良品","零碎品","过量耗用品","开机时长","开机时间","停机时长","关机时间","满载速度","主操作手","副操作手","速度满载率(PR)","开机率(OR)","正品率(QR)","OEE"};
		// 获取表格样式
		WritableCellFormat bodyFomat = this.getBodyFomat();
		WritableCellFormat titlFomat = this.getTitalFomat();
		response.reset();
		OutputStream outputStream = response.getOutputStream();

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sf.format(new Date());
		String titleName = format +"excel表";
		titleName = new String(titleName.getBytes(), "ISO8859-1");
		WritableWorkbook wwb = null;
		WritableSheet ws = null;
		String macCode = null;
		Integer sheetIndex = 0; //sheet 的坐标
		Integer paramIndex = 1; // sheet内数据的行数
		try {
			// 创建表
			wwb = Workbook.createWorkbook(outputStream);
			for (MauOEE mauOEE : listQuery) {
				//初始化
				if (macCode == null) {
					macCode = mauOEE.getMacCode();
					ws = wwb.createSheet(macCode, sheetIndex);
					// 写入标题
					for (int i = 0; i < arrTitle.length; i++) {
						ws.addCell(new Label(i, 0, arrTitle[i], titlFomat));
					}
					//写入数据
					ws.addCell(new Label(0, paramIndex, null==mauOEE.getMacCode()?"":mauOEE.getMacCode(), bodyFomat));
					ws.addCell(new Label(1, paramIndex, null==mauOEE.getSeqName()?"":mauOEE.getSeqName(), bodyFomat));
					ws.addCell(new Label(2, paramIndex, null==mauOEE.getFactOutput()?"":mauOEE.getFactOutput().toString(), bodyFomat));
					ws.addCell(new Label(3, paramIndex, null==mauOEE.getFullyOutput()?"":mauOEE.getFullyOutput().toString(), bodyFomat));
					ws.addCell(new Label(4, paramIndex, null==mauOEE.getRejects()?"":mauOEE.getRejects().toString(), bodyFomat));
					ws.addCell(new Label(5, paramIndex, null==mauOEE.getBitsPieces()?"":mauOEE.getBitsPieces().toString(), bodyFomat));
					ws.addCell(new Label(6, paramIndex, null==mauOEE.getOverdoes()?"":mauOEE.getOverdoes().toString(), bodyFomat));
					ws.addCell(new Label(7, paramIndex, null==mauOEE.getStartMin()?"":mauOEE.getStartMin().toString(), bodyFomat));
					ws.addCell(new Label(8, paramIndex, null==mauOEE.getStartTime()?"":mauOEE.getStartTime().toString(), bodyFomat));
					ws.addCell(new Label(9, paramIndex, null==mauOEE.getEndMin()?"":mauOEE.getEndMin().toString(), bodyFomat));
					ws.addCell(new Label(10, paramIndex, null==mauOEE.getEndTime()?"":mauOEE.getEndTime().toString(), bodyFomat));
					ws.addCell(new Label(11, paramIndex, null==mauOEE.getFullySpeed()?"":mauOEE.getFullySpeed().toString(), bodyFomat));
					ws.addCell(new Label(12, paramIndex, null==mauOEE.getMainOperator()?"":mauOEE.getMainOperator().toString(), bodyFomat));
					ws.addCell(new Label(13, paramIndex, null==mauOEE.getSecondOperator()?"":mauOEE.getSecondOperator().toString(), bodyFomat));
					ws.addCell(new Label(14, paramIndex, null==mauOEE.getRateSpeed()?"":mauOEE.getRateSpeed().toString(), bodyFomat));
					ws.addCell(new Label(15, paramIndex, null==mauOEE.getRateStart()?"":mauOEE.getRateStart().toString(), bodyFomat));
					ws.addCell(new Label(16, paramIndex, null==mauOEE.getRateQuality()?"":mauOEE.getRateQuality().toString(), bodyFomat));
					ws.addCell(new Label(16, paramIndex, null==mauOEE.getOee()?"":mauOEE.getOee().toString(), bodyFomat));
					paramIndex++;
				} else{
					if (macCode.equals(mauOEE.getMacCode())) {
						//同一个sheet
						//写入数据
						ws.addCell(new Label(0, paramIndex, null==mauOEE.getMacCode()?"":mauOEE.getMacCode(), bodyFomat));
						ws.addCell(new Label(1, paramIndex, null==mauOEE.getSeqName()?"":mauOEE.getSeqName(), bodyFomat));
						ws.addCell(new Label(2, paramIndex, null==mauOEE.getFactOutput()?"":mauOEE.getFactOutput().toString(), bodyFomat));
						ws.addCell(new Label(3, paramIndex, null==mauOEE.getFullyOutput()?"":mauOEE.getFullyOutput().toString(), bodyFomat));
						ws.addCell(new Label(4, paramIndex, null==mauOEE.getRejects()?"":mauOEE.getRejects().toString(), bodyFomat));
						ws.addCell(new Label(5, paramIndex, null==mauOEE.getBitsPieces()?"":mauOEE.getBitsPieces().toString(), bodyFomat));
						ws.addCell(new Label(6, paramIndex, null==mauOEE.getOverdoes()?"":mauOEE.getOverdoes().toString(), bodyFomat));
						ws.addCell(new Label(7, paramIndex, null==mauOEE.getStartMin()?"":mauOEE.getStartMin().toString(), bodyFomat));
						ws.addCell(new Label(8, paramIndex, null==mauOEE.getStartTime()?"":mauOEE.getStartTime().toString(), bodyFomat));
						ws.addCell(new Label(9, paramIndex, null==mauOEE.getEndMin()?"":mauOEE.getEndMin().toString(), bodyFomat));
						ws.addCell(new Label(10, paramIndex, null==mauOEE.getEndTime()?"":mauOEE.getEndTime().toString(), bodyFomat));
						ws.addCell(new Label(11, paramIndex, null==mauOEE.getFullySpeed()?"":mauOEE.getFullySpeed().toString(), bodyFomat));
						ws.addCell(new Label(12, paramIndex, null==mauOEE.getMainOperator()?"":mauOEE.getMainOperator().toString(), bodyFomat));
						ws.addCell(new Label(13, paramIndex, null==mauOEE.getSecondOperator()?"":mauOEE.getSecondOperator().toString(), bodyFomat));
						ws.addCell(new Label(14, paramIndex, null==mauOEE.getRateSpeed()?"":mauOEE.getRateSpeed().toString(), bodyFomat));
						ws.addCell(new Label(15, paramIndex, null==mauOEE.getRateStart()?"":mauOEE.getRateStart().toString(), bodyFomat));
						ws.addCell(new Label(16, paramIndex, null==mauOEE.getRateQuality()?"":mauOEE.getRateQuality().toString(), bodyFomat));
						ws.addCell(new Label(17, paramIndex, null==mauOEE.getOee()?"":mauOEE.getOee().toString(), bodyFomat));
						paramIndex++;
					} else {
						//新建sheet
						macCode = mauOEE.getMacCode();
						ws = wwb.createSheet(macCode, ++sheetIndex);
						paramIndex = 1;
						// 写入标题
						for (int i = 0; i < arrTitle.length; i++) {
							ws.addCell(new Label(i, 0, arrTitle[i], titlFomat));
						}
						//写入数据
						ws.addCell(new Label(0, paramIndex, null==mauOEE.getMacCode()?"":mauOEE.getMacCode(), bodyFomat));
						ws.addCell(new Label(1, paramIndex, null==mauOEE.getSeqName()?"":mauOEE.getSeqName(), bodyFomat));
						ws.addCell(new Label(2, paramIndex, null==mauOEE.getFactOutput()?"":mauOEE.getFactOutput().toString(), bodyFomat));
						ws.addCell(new Label(3, paramIndex, null==mauOEE.getFullyOutput()?"":mauOEE.getFullyOutput().toString(), bodyFomat));
						ws.addCell(new Label(4, paramIndex, null==mauOEE.getRejects()?"":mauOEE.getRejects().toString(), bodyFomat));
						ws.addCell(new Label(5, paramIndex, null==mauOEE.getBitsPieces()?"":mauOEE.getBitsPieces().toString(), bodyFomat));
						ws.addCell(new Label(6, paramIndex, null==mauOEE.getOverdoes()?"":mauOEE.getOverdoes().toString(), bodyFomat));
						ws.addCell(new Label(7, paramIndex, null==mauOEE.getStartMin()?"":mauOEE.getStartMin().toString(), bodyFomat));
						ws.addCell(new Label(8, paramIndex, null==mauOEE.getStartTime()?"":mauOEE.getStartTime().toString(), bodyFomat));
						ws.addCell(new Label(9, paramIndex, null==mauOEE.getEndMin()?"":mauOEE.getEndMin().toString(), bodyFomat));
						ws.addCell(new Label(10, paramIndex, null==mauOEE.getEndTime()?"":mauOEE.getEndTime().toString(), bodyFomat));
						ws.addCell(new Label(11, paramIndex, null==mauOEE.getFullySpeed()?"":mauOEE.getFullySpeed().toString(), bodyFomat));
						ws.addCell(new Label(12, paramIndex, null==mauOEE.getMainOperator()?"":mauOEE.getMainOperator().toString(), bodyFomat));
						ws.addCell(new Label(13, paramIndex, null==mauOEE.getSecondOperator()?"":mauOEE.getSecondOperator().toString(), bodyFomat));
						ws.addCell(new Label(14, paramIndex, null==mauOEE.getRateSpeed()?"":mauOEE.getRateSpeed().toString(), bodyFomat));
						ws.addCell(new Label(15, paramIndex, null==mauOEE.getRateStart()?"":mauOEE.getRateStart().toString(), bodyFomat));
						ws.addCell(new Label(16, paramIndex, null==mauOEE.getRateQuality()?"":mauOEE.getRateQuality().toString(), bodyFomat));
						ws.addCell(new Label(17, paramIndex, null==mauOEE.getOee()?"":mauOEE.getOee().toString(), bodyFomat));
						paramIndex++;
					}
				}
			}
			
		//	ws = wwb.createSheet(format, 0);
		
			
			
			response.setContentType("application/vnd.ms-excel;charset=GBK");
			response.addHeader("Content-Disposition", "attachment; filename=\""
					+ titleName + ".xls\"");
			wwb.write();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if (null != wwb) {
				wwb.close();
			}
			if (null != outputStream) {

				outputStream.close();
			}
		}

		
	}
	
	private WritableCellFormat getBodyFomat() {
		WritableCellFormat format = null;
		try {
			WritableFont font = new WritableFont(WritableFont.createFont("宋体"),
					10, WritableFont.NO_BOLD);
			format = new WritableCellFormat(font);
			format.setAlignment(jxl.format.Alignment.CENTRE);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(jxl.format.Border.TOP,
					jxl.format.BorderLineStyle.THIN);
			format.setBorder(jxl.format.Border.LEFT,
					jxl.format.BorderLineStyle.THIN);
			format.setBorder(jxl.format.Border.RIGHT,
					jxl.format.BorderLineStyle.THIN);
			format.setBorder(jxl.format.Border.BOTTOM,
					jxl.format.BorderLineStyle.THIN);

			format.setWrap(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return format;
	}

	private WritableCellFormat getTitalFomat() {
		WritableCellFormat format = null;
		try {
			WritableFont font = new WritableFont(WritableFont.createFont("宋体"),
					13, WritableFont.BOLD);
			format = new WritableCellFormat(font);
			format.setAlignment(jxl.format.Alignment.CENTRE);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(jxl.format.Border.TOP,
					jxl.format.BorderLineStyle.THIN);
			format.setBorder(jxl.format.Border.LEFT,
					jxl.format.BorderLineStyle.THIN);
			format.setBorder(jxl.format.Border.RIGHT,
					jxl.format.BorderLineStyle.THIN);
			format.setBorder(jxl.format.Border.BOTTOM,
					jxl.format.BorderLineStyle.THIN);

			format.setWrap(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return format;
	}


	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param map
	 * @return         
	 */ 
	public MauOEEVO getSingleMacChart(Map<String, String> map) {
		// TODO Auto-generated method stub
		String hql = "from MauOEE where 1 = 1 ";
		StringBuilder sb = new StringBuilder(hql);
		if (map != null) {
			if (map.get("macCode") != null && map.get("macCode") != "") {
				sb.append(" and macCode = '"+map.get("macCode")+"' ");
			}
			
			if (map.get("start") != null && map.get("start") != "") {
				sb.append(" and createDate >= '"+map.get("start")+"' ");
			}
			
			if (map.get("end") != null && map.get("end") != "") {
				sb.append(" and createDate <= '"+map.get("end")+"' ");
			}
		}
		sb.append(" order by createDate ");
		List<MauOEE> listQuery = dao.listQuery(sb.toString());
		if (listQuery !=  null && listQuery.size() > 0) {
			MauOEEVO vo = new MauOEEVO();
			vo.setTitleName("机台编号"+map.get("macCode")+"时间:"+map.get("start")+"到"+map.get("end"));
			List<String>legend = new ArrayList<>(); 
			legend.add("开机率(OR)");
			legend.add("正品率(QR)");
			legend.add("速度满载率(PR)");
			legend.add("OEE");
			List<String>X = new ArrayList<>();
			List<Double>ORList = new ArrayList<>();
			List<Double>QRList = new ArrayList<>();
			List<Double>PRList = new ArrayList<>();
			List<Double>OEEList = new ArrayList<>();
			List<List<Double>>lineList = new ArrayList<>();
			for (MauOEE mauOEE : listQuery) {
				X.add(mauOEE.getCreateDate().toString());
				ORList.add(mauOEE.getRateStart());
				QRList.add(mauOEE.getRateQuality());
				PRList.add(mauOEE.getRateSpeed());
				OEEList.add(mauOEE.getOee());
			}
			lineList.add(ORList);
			lineList.add(QRList);
			lineList.add(PRList);
			lineList.add(OEEList);
			vo.setLegend(legend);
			vo.setXlist(X);
			vo.setLineList(lineList);
			return vo;
		
		}
		return null;
	}


	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param map
	 * @return         
	 */ 
	public MauOEEVO getAllMacChart(Map<String, String> map) {
		// TODO Auto-generated method stub
		String hql = "from MauOEE where 1 = 1 ";
		StringBuilder sb = new StringBuilder(hql);
		if (map != null) {
			
			
			if (map.get("createDate") != null && map.get("createDate") != "") {
				sb.append(" and createDate = '"+map.get("createDate")+"' ");
			}
		}
		sb.append(" order by createDate ");
		List<MauOEE> listQuery = dao.listQuery(sb.toString());
		if (listQuery !=  null && listQuery.size() > 0) {
			MauOEEVO vo = new MauOEEVO();
			vo.setTitleName("查询日期"+ map.get("createDate"));
			List<String>legend = new ArrayList<>(); 
			legend.add("开机率(OR)");
			legend.add("正品率(QR)");
			legend.add("速度满载率(PR)");
			legend.add("OEE");
			List<String>X = new ArrayList<>();
			List<Double>ORList = new ArrayList<>();
			List<Double>QRList = new ArrayList<>();
			List<Double>PRList = new ArrayList<>();
			List<Double>OEEList = new ArrayList<>();
			List<List<Double>>lineList = new ArrayList<>();
			for (MauOEE mauOEE : listQuery) {
				X.add(mauOEE.getMacCode());
				ORList.add(mauOEE.getRateStart());
				QRList.add(mauOEE.getRateQuality());
				PRList.add(mauOEE.getRateSpeed());
				OEEList.add(mauOEE.getOee());
			}
			lineList.add(ORList);
			lineList.add(QRList);
			lineList.add(PRList);
			lineList.add(OEEList);
			vo.setLegend(legend);
			vo.setXlist(X);
			vo.setLineList(lineList);
			return vo;
		
		}
		
		return null;
	}


	public List<String> getMaccodes() {
		// TODO Auto-generated method stub
		String hql ="select macCode from  MauOEE group by macCode";
		List<String> list = dao.createQuery(hql).list();
		return list;
	}
	
}
