package com.css.business.web.subsysquality.quaManage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.craManage.dao.CraSeqManageDAO;
import com.css.business.web.subsysmanu.mauManage.service.MauMachineManageService;
import com.css.business.web.subsysplan.bean.PlaMachinePlanSchedule;
import com.css.business.web.subsysplan.bean.PlaProductOrder;
import com.css.business.web.subsysplan.bean.PlaProductOrderSeqHourse;
import com.css.business.web.subsysplan.plaManage.service.PlaMachinePlanScheduleManageService;
import com.css.business.web.subsysplan.plaManage.service.PlaProductOrderManageService;
import com.css.business.web.subsysplan.plaManage.service.PlaProductOrderSeqHourseManageService;
import com.css.business.web.subsysquality.bean.MacQuaVo;
import com.css.business.web.subsysquality.bean.QuaParam;
import com.css.business.web.subsysquality.bean.QualityAsk;
import com.css.business.web.subsysquality.bean.QualityBasicValue;
import com.css.business.web.subsysquality.bean.QualityMaterReport;
import com.css.business.web.subsysquality.bean.QualityMauReport;
import com.css.business.web.subsysquality.quaManage.dao.QualityMauReportManageDAO;
import com.css.business.web.sysManage.service.SysNoticeManageService;
import com.css.commcon.util.SessionUtils;
import com.css.commcon.util.YorkUtil;
import com.css.common.util.DateUtil;
import com.css.common.util.EhCacheFactory;
import com.css.common.web.apachemq.handle.ApacheMqSender;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@Service("qualityMauReportManageService")
public class QualityMauReportManageService extends
		BaseEntityManageImpl<QualityMauReport, QualityMauReportManageDAO> {
	@Resource(name = "qualityMauReportManageDAO")
	// @Autowired
	private QualityMauReportManageDAO dao;

	@Override
	public QualityMauReportManageDAO getEntityDaoInf() {
		return dao;
	}

	private Gson gson = new Gson();
	/** 实际参数值service */
	@Resource(name="qualityBasicValueManageService")
	private QualityBasicValueManageService vaService;
	/** 生产进度的service */
	@Resource(name="plaMachinePlanScheduleManageService")
	private PlaMachinePlanScheduleManageService psService;
	/** 工序service */
	@Resource(name="craSeqManageDAO")
	//private CraSeqManageService seqService;
	private CraSeqManageDAO craSeqManageDAO;
	/** 机器service */
	@Resource(name="mauMachineManageService")
	private MauMachineManageService macService;
	/** 拆分表的service */
	@Resource(name="plaProductOrderSeqHourseManageService")
	private PlaProductOrderSeqHourseManageService posService;
	/** 生产令service */
	@Resource(name="plaProductOrderManageService")
	private PlaProductOrderManageService poService;
	/** 模板service */
	@Resource(name="qualityBasicTypeManageService")
	private QualityBasicTypeManageService typeService;
	/** 基本参数service */
	@Resource(name="qualityBasicParamManageService")
	private QualityBasicParamManageService paramService;
	/** 页面滚动条service */
	@Resource(name="sysNoticeManageService")
	private SysNoticeManageService noticeService;
	/** 质检呼叫 */
	@Resource(name="qualityAskManageService")
	private QualityAskManageService askService;
	
	private EhCacheFactory factory = EhCacheFactory.getInstance();
	//向机台发送质检完成后的信息
	private ApacheMqSender sender;
	
	/**   
	 * @Description: 条件分页查询   
	 * @param map
	 * @param page
	 * @return         
	 */ 
	public Page getPageList(Map<String, String> map, Page page) {
		// TODO Auto-generated method stub
		String hql = "from QualityMauReport where 1 = 1  ";
		StringBuilder sb = new StringBuilder(hql);
		if (map !=null && map.size() > 0) {
			if(map.get("testType") != null && map.get("testType") != ""){
				sb.append(" and testType='"+map.get("testType")+"'");
			}
			if(map.get("start") != null && map.get("start") != ""){
				sb.append(" and testDate >='"+map.get("start")+"'");
			}
			if(map.get("end") != null && map.get("end") != ""){
				sb.append(" and testDate <='"+map.get("end")+"'");
			}
			if(map.get("testState") != null && map.get("testState") != ""){
				sb.append(" and testState = '"+map.get("testState")+"'");
			}
			if(map.get("testResult") != null && map.get("testResult") != ""){
				sb.append(" and testResult = '"+map.get("testResult")+"'");
			}
			if(map.get("isCome") != null && map.get("isCome") != ""){
				sb.append(" and isCome = '"+map.get("isCome")+"'");
			}
		}
		sb.append(" order by testState");
		Page queryPage = dao.pageQuery(sb.toString(), page.getPageNo(), page.getPagesize());
		return queryPage;
	}

	/**   
	 * @Description: 调用数据库函数
	 * @param string
	 * @return         
	 * @throws Exception 
	 */ 
	public Object getFun(String funName,Object ...obj) throws Exception {
		// TODO Auto-generated method stub
		Object result = dao.exeFunction(funName, obj);
		return result;
	}

	/**   
	 * @Description: app添加生产质检计划   
	 * @param request
	 * @param semiAxis
	 * @param testType         
	 * @throws Exception 
	 */ 
	public void appAddReport(HttpServletRequest request, String semiAxis,
			String testType) throws Exception {
		// TODO Auto-generated method stub
		QualityMauReport report = new QualityMauReport();
		String account = SessionUtils.getUser(request).getAccount();
		report.setCreateDate(new Date());	//创建时间
		report.setCreateBy(account); //创建人
		report.setSemiAxis(semiAxis);//rfid
		//通过rfid 查询最新的一条数据,也就是现在准备检测的数据
		PlaMachinePlanSchedule schedule  = psService.getLastBySemiAxis(semiAxis);
		report.setCourseCode(schedule.getCourseCode()); 	//工单编号
		report.setAxisName(schedule.getAxisName()); //轴名称
		report.setMacCode(macService.get(schedule.getMachineId()).getMacCode());//机器编码
		//系统生成的质检报告编号
		report.setReportCode((String)this.getFun("fun_get_mau_report_code"));//系统生成质检报告编号
		//report.setSeqName(seqService.getSeqNameByCode( schedule.getSeqCode())); //工序名称
		report.setSeqName(craSeqManageDAO.getNameByCode( schedule.getSeqCode())); //工序名称
		report.setTestState(QualityMauReport.STATE_NO_TEST); 	//状态:未检测
		PlaProductOrderSeqHourse seqHourse = posService.getGGXHByAxisname(schedule.getAxisName(),schedule.getSeqCode());
		if(seqHourse != null){
			report.setProGgxh(seqHourse.getProGgxh());	//proggxh
			PlaProductOrder order = poService.get(seqHourse.getPlaOrderId());
			if(order != null){
				report.setBatchCode(order.getBatCode());
			}
		}
		try {
			this.save(report);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**   
	 * @Description: web端修改报告和所对应的参数   
	 * @param bean
	 * @param paramBeans
	 * @param account         
	 */ 
	public String saveBeanAndParams(String bean, String paramBeans, String account) {
		QualityMauReport report = gson.fromJson(bean, QualityMauReport.class);
		List<QualityBasicValue> list = gson.fromJson(paramBeans, new TypeToken<List<QualityBasicValue>>(){}.getType());
		try {
			
			for (QualityBasicValue value : list) {
				if (value.getId() != null) {
					//修改
					vaService.updateByCon(value, false);
				} else {
					//新建保存
					value.setCreateDate(new Date());
					value.setCreateBy(account);
					value.setQuaCode(report.getReportCode());
					vaService.save(value);
				}
			}
			//向机台发送检测报告:未发送只有 机台请求的才会有,app和手动添加的这个字段为空
			if (QualityMauReport.SEND_IS_NO.equals(report.getIsSend())) {
				//未发送,发送
				report.setIsSend(QualityMauReport.SEND_IS_YES);
				MacQuaVo vo = new MacQuaVo();
				vo.setAxisName(report.getAxisName());
				vo.setMachineCode(report.getMacCode());
				vo.setModelName(report.getTypeName());
				vo.setTestResult(report.getTestResult());
				vo.setTestType(vo.getTestType());
				List<QuaParam> quaParam = new ArrayList<>();
				for (QualityBasicValue value : list) {
					QuaParam pa = new QuaParam();
					pa.setParamName(value.getParamName());
					pa.setFirstValue(value.getParamValue());
					pa.setSecondValue(value.getSecondValue());
					quaParam.add(pa);
				}
				vo.setQuaParam(quaParam );
				//---------------------
				//发送消息 :如果是制程质检才需要回显到机台
				if (QualityMauReport.TYPE_IS_PROCESS.equals(report.getTestType())) {
					sender.sendMsg_point_to_point("", gson.toJson(vo));
				}
			}
			
			//不合格生成返工单
			/*if(QualityMauReport.RESULT_NO_PASS.equals(report.getTestResult())){
				
			}*/
			
			//电缆质检&&合格  -->入库状态,未入库
			/*if (QualityMauReport.RESULT_IS_PASS.equals(report.getTestResult()) && QualityMauReport.TYPE_IS_PRODUCT.equals(report.getTestResult())) {
				report.setIsCome(QualityMauReport.IS_NOT_COME);
				//在页面滚动条展示
				SysNotice notice = new SysNotice();
				notice.setType("入库");
				notice.setIsShow("是");
				notice.setKey(report.getReportCode());
				notice.setValue("入库:有电缆质检合格,质检单号"+report.getReportCode());
				noticeService.save(notice);
				
			}*/
			this.updateByCon(report,false);
			
			//已检测就将质检呼叫去掉
			if (QualityMauReport.STATE_IS_END.equals(report.getTestState())) {
				QualityAsk ask= askService.getAskBySemiAxis(report.getSemiAxis());
				if (ask != null ) {
					ask.setFinishBy(report.getTestBy());
					ask.setFinishDate(report.getTestDate());
					ask.setAskState(QualityAsk.STATE_IS_HANDLE);
					askService.updateByCon(ask, false);
					//查询未处理的呼叫
					List<QualityAsk>askList = askService.getAskList(QualityAsk.STATE_NOT_HANDLE);
					factory.addWebsocketCmdCache(YorkUtil.Memcache_质检呼叫, gson.toJson(askList));
					//获得未处理和当天的质检呼叫
					List<QualityAsk>allAskList = askService.getTodayAskList(QualityAsk.STATE_NOT_HANDLE);
					factory.addWebsocketCmdCache(YorkUtil.Memcache_质检呼叫_grid, gson.toJson(allAskList));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 
	 * @Description: 机台上传质检报告是的接口:制程是可以这样做的,成品检测怎么做呢   
	 * @param vo
	 */
	public void mqSaveBeanAndParams(MacQuaVo vo){
		String axisName = vo.getAxisName();
		
		//1.通过轴名称去查询质检报告基本数据
		PlaMachinePlanSchedule schedule = psService.getByAxisName(axisName);
		if (schedule != null) {
			try {
				QualityMauReport report = new QualityMauReport();
				if (vo.getTestType() != null && vo.getTestType() != "") {
					report.setTestType(vo.getTestType());
					if (QualityMauReport.TYPE_IS_PRODUCT.equals(vo.getTestType())) {
						//电缆检测 : 加一个 是否入库
						report.setIsCome(QualityMauReport.IS_NOT_COME);
					}else {
						//制程质检
						report.setIsCome("--");
						//制程质检 添加一个 "未发送" ,只有制程质检 才需要回显,成品质检 不需要回显
						report.setIsSend(QualityMauReport.SEND_IS_NO);
					}
				}
				if (vo.getModelName() != null && vo.getModelName() != "") {
					report.setTypeName(vo.getModelName());
				}
				
				report.setCreateDate(new Date());	//创建时间
				report.setCreateBy(vo.getMachineCode()); //创建人
				//系统生成的质检报告编号
				String reportCode = (String) this.getFun("fun_get_mau_report_code");
				report.setReportCode(reportCode);//系统生成质检报告编号
				report.setTypeName(vo.getModelName());//设置模版名称
				report.setSemiAxis(schedule.getSeqSemiProductAxis());//rfid
				report.setCourseCode(schedule.getCourseCode()); 	//工单编号
				report.setAxisName(schedule.getAxisName()); //轴名称
				report.setMacCode(macService.get(schedule.getMachineId()).getMacCode());//机器编码
				//report.setSeqName(seqService.getSeqNameByCode( schedule.getSeqCode())); //工序名称
				report.setSeqName(craSeqManageDAO.getNameByCode( schedule.getSeqCode())); //工序名称
				report.setTestState(QualityMauReport.STATE_NO_TEST); 	//状态:未检测
				PlaProductOrderSeqHourse seqHourse = posService.getGGXHByAxisname(schedule.getAxisName(),schedule.getSeqCode());
				if(seqHourse != null){
					report.setProGgxh(seqHourse.getProGgxh());	//proggxh
					PlaProductOrder order = poService.get(seqHourse.getPlaOrderId());
					if(order != null){
						report.setBatchCode(order.getBatCode());
					}
				}
			//制程质检报告生成完毕
				this.save(report);
				
			//2.生成上传的基本参数信息及数据	
				//获得保存的report
				QualityMauReport saveReport = this.getByReportCode(reportCode);
				Integer quaId = saveReport.getId();
				//a.法一:直接存值,放开和模版的关系,
				List<QuaParam> quaParams = vo.getQuaParam();
				for (QuaParam quaParam : quaParams) {
					QualityBasicValue value= new QualityBasicValue();
					value.setBasicTypeName(vo.getModelName());
					value.setParamName(quaParam.getParamName());
					value.setParamValue(quaParam.getFirstValue());
					value.setQuaCode(saveReport.getReportCode());;
					vaService.save(value);
				}
				
				//b.先求模版,根据模版拿值
				
				//保存质检呼叫
				QualityAsk ask = new QualityAsk();
				ask.setAskType(saveReport.getTestType());
				ask.setAskLocation(saveReport.getMacCode());
				ask.setAskState(QualityAsk.STATE_NOT_HANDLE);
				ask.setCreateDate(new Date());
				//以质检报告编号作为这个的识别号
				ask.setSemiAxis(saveReport.getReportCode());
				askService.save(ask);
				//查询未处理的呼叫
				List<QualityAsk>askList = askService.getAskList(QualityAsk.STATE_NOT_HANDLE);
				factory.addWebsocketCmdCache(YorkUtil.Memcache_质检呼叫, gson.toJson(askList));
				//获得未处理和当天的质检呼叫
				List<QualityAsk>allAskList = askService.getTodayAskList(QualityAsk.STATE_NOT_HANDLE);
				factory.addWebsocketCmdCache(YorkUtil.Memcache_质检呼叫_grid, gson.toJson(allAskList));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	/**   
	 * @Description: 通过质检报告去获得质检报告  
	 * @param reportCode
	 * @return         
	 */ 
	public QualityMauReport getByReportCode(String reportCode) {
		// TODO Auto-generated method stub
		String hql = " from  QualityMauReport where reportCode ='"+reportCode+"'";
		List<QualityMauReport> listQuery = dao.listQuery(hql);
		if (listQuery != null && listQuery.size() > 0) {
			return listQuery.get(0);
		}
		return null;
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param sTATE_NO_TEST
	 * @return         
	 */ 
	public List<QualityMauReport> getListByState(String state) {
		// TODO Auto-generated method stub
		String hql = " from QualityMauReport where testState ='"+state+"'";
		List<QualityMauReport> listQuery = dao.listQuery(hql);
		
		return listQuery;
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param id         
	 */ 
	public void clearBean(String id) {
		// TODO Auto-generated method stub
		String sql = "delete from Quality_Mau_Report where id = "+id;
		dao.deleteBySql(sql);
	}

	/**   
	 * @Description: app 扫描rfid 获得质检报告   
	 * @param semiAxis
	 * @return         
	 */ 
	public QualityMauReport getReportBySemiAxis(String semiAxis) {
		// TODO Auto-generated method stub
		QualityMauReport report = null;
		String hql =" from  QualityMauReport where semiAxis='"+semiAxis+"' order by createDate DESC limit 1";
		try {
			List<QualityMauReport> list = dao.createQuery(hql).list();
			report = list.get(0);
			return report;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param axisName
	 * @return         
	 */ 
	public QualityMauReport getPrintmauReport(String axisName) {
		// TODO Auto-generated method stub
		String hql = " from QualityMauReport where axisName='"+axisName+"' order By createDate DESC"; 
		List<QualityMauReport> listQuery = dao.listQuery(hql);
		QualityMauReport report =  null;
		if (listQuery != null && listQuery.size() > 0) {
			report =  listQuery.get(0);
			List<QualityBasicValue> vaList = vaService.getValueByTypeName(null, report.getReportCode());
			report.setVaList(vaList);
		}
		return report;
	}
	
	/**
	 * 
	 * @Description:仓库电子看版，成品入库。查询当天的，查询以前 没有质检的
	 * @return
	 */
	public List<QualityMauReport>getDepotShow(){
		String day = DateUtil.format(new Date(), "yyyy-MM-dd");
		String start = day + " 00:00:00";
		String end = day + " 23:59:59";
		String hql = "from QualityMauReport where testType='"+QualityMauReport.TYPE_IS_PRODUCT+"' and ((createDate >= '"+start+"' and createDate <= '"+end+"') or (testState = '"+QualityMauReport.STATE_NO_TEST+"' ))";
		List<QualityMauReport> list = dao.listQuery(hql);
		return  list;
	}
	
}
