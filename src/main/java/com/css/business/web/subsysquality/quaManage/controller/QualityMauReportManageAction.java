package com.css.business.web.subsysquality.quaManage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysquality.bean.QualityMauReport;
import com.css.business.web.subsysquality.quaManage.service.QualityMauReportManageService;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.util.EhCacheFactory;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@Controller
@RequestMapping("/qualityMauReportManageAction")
public class QualityMauReportManageAction
		extends
		BaseSpringSupportAction<QualityMauReport, QualityMauReportManageService> {

	// @Autowired
	private QualityMauReportManageService service;

	@Override
	public QualityMauReportManageService getEntityManager() {
		return service;
	}

	public QualityMauReportManageService getService() {
		return service;
	}

	@Resource(name = "qualityMauReportManageService")
	public void setService(QualityMauReportManageService service) {
		this.service = service;
	}

	private Gson gson = new Gson();
	
	private EhCacheFactory eFactory = EhCacheFactory.getInstance();
	/**
	 * 
	 * @Description: 去后天管理页面   
	 * @return
	 */
	@RequestMapping("toPageList")
	public String toPageList(){
		return "quaManage/quaMau/quaMauList";
	}
	
	//去打印页面
	@RequestMapping("toPrintList")
	public String toPrintList(HttpServletRequest req,String axisName){
		req.setAttribute("axisName", axisName);
		return "quaManage/quaMau/quaMauPrint";
	}
	
	/**
	 * 
	 * @Description: 去电子看板页面   
	 * @return
	 */
	@RequestMapping("toDispalyPage")
	public String toDispalyPage(){
		return "";
	}
	
	@RequestMapping("toAddOrModify")
	public String toAddOrModify(HttpServletRequest request,String id){
		if (id != null ) {
			request.setAttribute("id", id);
			request.setAttribute("info", "修改");
		} else {
			request.setAttribute("info", "添加");
			request.setAttribute("id", "-1");
		}
		return "quaManage/quaMau/quaMauForm";
	}
	
	/**
	 * 
	 * @Description: 去参数修改的页面   
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("toUpdateReport")
	public String toUpdateReport(HttpServletRequest request,String id){
		request.setAttribute("id", id);
		if (id != null ) {
			QualityMauReport report = service.get(Integer.parseInt(id));
			request.setAttribute("testType", report.getTestType());
			String typeName = report.getTypeName();
			String reportCode = report.getReportCode();
			if (typeName != null) {
				request.setAttribute("typeName", typeName);
				request.setAttribute("reportCode", reportCode);
			} else {
				request.setAttribute("typeName", "-1");
				request.setAttribute("reportCode", "-1");
			}
		}
		return "quaManage/quaMau/quaMauParam";
	}
	/**
	 * 
	 * @Description: 条件查询pageList   
	 * @param request
	 * @param param
	 * @return
	 */
	@RequestMapping("getPageList")
	@ResponseBody
	public Page  getPageList(HttpServletRequest request,String param,Page page){
		Map<String ,String>map = gson.fromJson(param, new TypeToken<Map<String, String>>() {}.getType());
		Page queryPage = page;
		try {
			queryPage = service.getPageList(map,page);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return queryPage;
	}
	
	/**
	 * 
	 * @Description: 保存bean 和 所有 参数  
	 * @param request
	 * @param bean
	 * @return
	 */
	@RequestMapping("saveBeanAndValue")
	@ResponseBody
	public HashMap<String, Object> saveBeanAndValue(HttpServletRequest request,String bean,String paramBeans){
		try {
			SysUser user = SessionUtils.getUser(request);
			service.saveBeanAndParams(bean,paramBeans,user.getAccount());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败");
		}
		//return JsonWrapper.successWrapper(pojo, msg);
		return JsonWrapper.successWrapper(null, "保存成功");
	}
	
	/**
	 * 
	 * @Description: 修改bean   
	 * @param request
	 * @param bean 就是bean 里面包含 id 和需要修改项,其他项可以不要
	 * @return
	 */
	@RequestMapping("updateBean")
	@ResponseBody
	public HashMap<String, Object> updateBean(HttpServletRequest request,String bean){
		QualityMauReport report = gson.fromJson(bean, QualityMauReport.class);
		if (report.getId() != null) {
			//更新
			try {
				service.updateByCon(report, false);
				return JsonWrapper.successWrapper(null, "更新成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return JsonWrapper.failureWrapperMsg("更新失败");
			}
		} else {
			try {
				//新建
				SysUser user = SessionUtils.getUser(request);
				report.setCreateBy(user.getAccount());
				report.setCreateDate(new Date());
				String reportCode = (String) service.getFun("fun_get_mau_report_code");
				report.setReportCode(reportCode );
				report.setTestState(QualityMauReport.STATE_NO_TEST);
				service.save(report);
				return JsonWrapper.successWrapper(null, "保存成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return JsonWrapper.failureWrapperMsg("保存失败");
			}
		}
	}
	
	
	@RequestMapping("getById")
	@ResponseBody
	public QualityMauReport getById(HttpServletRequest request,String id){
		QualityMauReport report =null;
		try {
			 report = service.get(Integer.parseInt(id));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return report;
		
	}
	
	/**
	 * 
	 * @Description: app扫描rfid 进行生产质检的添加   
	 * @param request
	 * @param token
	 * @param semiAxis 实际是rfid
	 * @param testType 测试类型
	 * @return
	 */
	@RequestMapping("appAddReport")
	@ResponseBody
	public HashMap<String, Object>appAddReport(HttpServletRequest request,String token,String semiAxis,String testType){
		try {
			service.appAddReport(request,semiAxis,testType);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("新建质检计划失败");
		}
		return JsonWrapper.successWrapper(null, "生成质检计划成功");
	}
	
	/**
	 * 
	 * @Description: 现场质检 app操作,给出的接口  
	 * @param request
	 * @param token
	 * @param bean
	 * @return
	 */
	@RequestMapping("appUpdateBean")
	@ResponseBody
	public HashMap<String, Object>appUpdateBean(HttpServletRequest request,String token,String bean){
		QualityMauReport report = gson.fromJson(bean, QualityMauReport.class);
		SysUser user = SessionUtils.getUser(request);
		report.setTestBy(user.getAccount());
		report.setTestDate(new Date());
		try {
			service.updateByCon(report, false);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("操作失败");
		}
		return JsonWrapper.successWrapper(null, "操作成功");
	}
	
	/**
	 * 
	 * @Description: 删除   
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("clearBean")
	@ResponseBody
	public HashMap<String, Object>clearBean(HttpServletRequest request,String id){
		try {
			service.clearBean(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败");
		}
		return JsonWrapper.successWrapper(null, "删除成功");
	}
	
	
	/**
	 * 
	 * @Description: app 扫描轴号 RFID 页面显示该质检计划   
	 * @param request
	 * @param semiAxis
	 * @return
	 */
	@RequestMapping("appScanRFID")
	@ResponseBody
	public HashMap<String, Object>appScanRFID(HttpServletRequest request,String semiAxis){
		QualityMauReport report = service.getReportBySemiAxis(semiAxis);
		if (report != null) {
			//借用机器缓存存入质检报告id
			eFactory.addMacShowCache("reportId", report.getId().toString());
		//	eFactory.addMacShowCache("testType", report.getTestType().toString());
			return JsonWrapper.successWrapper(null, "扫面成功,请在页面完成质检报告");
		} else {
			return JsonWrapper.failureWrapperMsg("此质检报告不存在,请确认后重新扫描");
		}
	}
	
	@RequestMapping("toAppScanReportParam")
	public String toAppScanReportParam(HttpServletRequest request){
		/*String reportId = eFactory.getMacShowCaChe("reportId");
		QualityMauReport report = service.get(Integer.parseInt(reportId));
		request.setAttribute("testType", report.getTestType());
		request.setAttribute("id", reportId);*/
		return "quaManage/quaMau/appQuaMauParam";
	}
	
	@RequestMapping("appPageGetReport")
	@ResponseBody
	public HashMap<String, Object> appPageGetReport(){
		String reportId = eFactory.getMacShowCaChe("reportId");
		if (reportId == null || "".equals(reportId)) {
			return JsonWrapper.failureWrapperMsg("还没有扫描");
		} else {
			QualityMauReport report = service.get(Integer.parseInt(reportId));
			if (report != null) {
				eFactory.removeMacShowCache("reportId");
				return JsonWrapper.successWrapper(report);
				
			}else{
				return JsonWrapper.failureWrapperMsg("查找失败");
			}
		}
	}
	
	/**
	 * 
	 * @Description: 打印质检报告   
	 * @param request
	 * @param axisName
	 * @return
	 */
	@RequestMapping("getPrintMauReport")
	@ResponseBody
	public HashMap<String, Object> getPrintMauReport(HttpServletRequest request,String axisName){
		QualityMauReport mauReport = service.getPrintmauReport(axisName);
		if (mauReport != null) {
			return JsonWrapper.successWrapper(mauReport, "");
		}
		return JsonWrapper.failureWrapperMsg("未找到该轴的质检报告");
	}
}
