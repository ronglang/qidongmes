package com.css.business.web.subsysquality.quaManage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.activemq.transport.https.HttpsClientTransport;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysquality.bean.QualityAsk;
import com.css.business.web.subsysquality.bean.QualityMaterReport;
import com.css.business.web.subsysquality.bean.QualityMauReport;
import com.css.business.web.subsysquality.quaManage.service.QualityAskManageService;
import com.css.business.web.subsysquality.quaManage.service.QualityMaterReportManageService;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.commcon.util.YorkUtil;
import com.css.common.util.EhCacheFactory;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@Controller
@RequestMapping("/qualityMaterReportManageAction")
public class QualityMaterReportManageAction
		extends
		BaseSpringSupportAction<QualityMaterReport, QualityMaterReportManageService> {

	// @Autowired
	private QualityMaterReportManageService service;

	@Override
	public QualityMaterReportManageService getEntityManager() {
		return service;
	}

	public QualityMaterReportManageService getService() {
		return service;
	}

	@Resource(name = "qualityMaterReportManageService")
	public void setService(QualityMaterReportManageService service) {
		this.service = service;
	}
	/** 质检呼叫service */
	@Resource(name="qualityAskManageService")
	private QualityAskManageService askService;
	
	private Gson gson = new Gson();
	private EhCacheFactory factory = EhCacheFactory.getInstance();
	
	/**  */
	public static boolean flag = false;
	/**
	 * 
	 * @Description: 去后天管理页面   
	 * @return
	 */
	@RequestMapping("toPageList")
	public String toPageList(){
		return "quaManage/quaMater/quaMaterList";
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
	
	/**
	 * 
	 * @Description: 去修改页面   
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("toUpdateReport")
	public String toUpdateReport(HttpServletRequest request,String id){
		request.setAttribute("id", id);
		QualityMaterReport report = service.get(Integer.parseInt(id));
		String typeName = report.getTypeName();
		String reportCode = report.getReportCode();
		if (typeName != null) {
			request.setAttribute("typeName", typeName);
			request.setAttribute("reportCode", reportCode);
		} else {
			request.setAttribute("typeName", "-1");
			request.setAttribute("reportCode", "-1");
		}
		return "quaManage/quaMater/quaMaterParam";
	}
	
	/**
	 * 
	 * @Description: 去添加页面   
	 * @return
	 */
	@RequestMapping("toAddReport")
	public String toAddReport(HttpServletRequest request,String id){
		if (id != null && id != "") {
			request.setAttribute("id", id);
		} else{
			request.setAttribute("id", "-1");
		}
		return "quaManage/quaMater/addMaterReport";
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
	public Page getPageList(HttpServletRequest request,String param,Page page){
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
	 * @Description: 保存bean和参数一起
	 * @param request
	 * @param bean
	 * @return
	 */
	@RequestMapping("saveBeanAndValue")
	@ResponseBody
	public HashMap<String, Object> saveBeanValue(HttpServletRequest request,String bean,String paramBeans){
		try {
			SysUser user = SessionUtils.getUser(request);
			//这里面也会处理质检呼叫
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
	 * @Description: 修改bean 只和bean有关系  
	 * @param request
	 * @param bean 就是bean 里面包含 id 和需要修改项,其他项可以不要
	 * @return
	 */
	@RequestMapping("updateBean")
	@ResponseBody
	public HashMap<String, Object> updateBean(HttpServletRequest request,String bean){
		QualityMaterReport report = gson.fromJson(bean, QualityMaterReport.class);
		if (report.getId()  != null) {
			//修改
			try {
				service.updateByCon(report, false);
				//显示已检测
				if (QualityMaterReport.STATE_IS_END.equals(report.getTestState())) {
					QualityAsk ask = askService.getAskBySemiAxis(report.getReportCode());
					if (ask != null ) {
						ask.setAskState(QualityAsk.STATE_IS_HANDLE);
						ask.setFinishBy(report.getTestBy());
						ask.setFinishDate(report.getTestDate());
						askService.updateByCon(ask, false);
						//查询未处理的呼叫
						//List<QualityAsk>askList = askService.getAskList(QualityAsk.STATE_NOT_HANDLE);
						//factory.addWebsocketCmdCache(YorkUtil.Memcache_质检呼叫, gson.toJson(askList));
					}
				}
				return JsonWrapper.successWrapper(null, "修改成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return JsonWrapper.failureWrapperMsg("修改失败");
			}
		} else {
			//新建
			try {
				//新建质检
				SysUser user = SessionUtils.getUser(request);
				report.setCreateBy(user.getAccount());
				report.setCreateDate(new Date());
				report.setTestState(QualityMaterReport.STATE_NO_START);
				String reportCode = (String) service.getFun("fun_get_mater_report_code");
				report.setReportCode(reportCode );
				report.setIsCome(QualityMaterReport.IS_NOT_COME);
				service.save(report);
				
				//仓库电子电子看板原材料质检
				//新建质检呼叫
				/*QualityAsk entity = new QualityAsk();
				entity.setAskLocation("来料区");
				entity.setAskState(QualityAsk.STATE_NOT_HANDLE);
				entity.setAskType("来料质检");
				entity.setCreateBy(user.getAccount());
				entity.setCreateDate(new Date());
				//来料质检,是用的质检报告作为唯一标识
				entity.setSemiAxis(reportCode);
				askService.save(entity );
				//查询未处理的呼叫
				List<QualityAsk>askList = askService.getAskList(QualityAsk.STATE_NOT_HANDLE);
				factory.addWebsocketCmdCache(YorkUtil.Memcache_质检呼叫, gson.toJson(askList));*/
				return JsonWrapper.successWrapper(null, "保存成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return JsonWrapper.failureWrapperMsg("保存失败");
			}
			
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("getById")
	@ResponseBody
	public QualityMaterReport getById(HttpServletRequest request,String id){
		try {
			QualityMaterReport report = service.get(Integer.parseInt(id));
			return report;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("clearBean")
	@ResponseBody
	public HashMap<String, Object>clearBean(HttpServletRequest request,String id){
		try {
			service.clearBean(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("删除失败");
		}
		return JsonWrapper.successWrapper(null, "删除成功");
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
	public HashMap<String, Object> getPrintMauReport(HttpServletRequest request,String reportCode){
		QualityMaterReport materReport = service.getPrintMaterReport(reportCode);
		if (materReport != null) {
			return JsonWrapper.successWrapper(materReport, "");
		}
		return JsonWrapper.failureWrapperMsg("未找到该轴的质检报告");
	}
	
	/**
	 * 
	 * @Description:app 上传扫描rfid
	 * @param request
	 * @param rfidList
	 * @return
	 */
	@RequestMapping("appScanRFID")
	@ResponseBody
	public HashMap<String, Object>appScanRFID(HttpServletRequest request,String rfidList){
		try {
			//List<String> list = gson.fromJson(rfidList, new TypeToken<List<String>>(){}.getType());
			if(flag){
				String[] split = rfidList.split(",");
				factory.addMacShowCache(YorkUtil.来料质检RFID, gson.toJson(split));
				flag = false;
				return JsonWrapper.successWrapper(null, "上传成功");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("上传失败");
		}
		return JsonWrapper.failureWrapperMsg("上传无效");
		
	}
	
	/**
	 * 
	 * @Description:来料质检保存时查询的方法
	 * @param request
	 * @return
	 */
	@RequestMapping("getRFIDS")
	@ResponseBody
	public HashMap<String, Object>getRFIDS(HttpServletRequest request){
		String showCaChe = factory.getMacShowCaChe(YorkUtil.来料质检RFID);
		if (showCaChe == null || "".equals(showCaChe) ) {
			return JsonWrapper.failureWrapper(null,"未扫描");
		}else {
			return JsonWrapper.successWrapper(showCaChe);
		}
	}
	
	/**
	 * 
	 * @Description:改变是否可以扫描的条件
	 * @param mFlag
	 */
	@RequestMapping("changeFlag")
	@ResponseBody
	public HashMap<String, Object> changeFlag(String mFlag){
		if("true".equals(mFlag)){
			flag = false;
		}else{
			flag = true;
		}
		return JsonWrapper.successWrapper(null, "改变成功");
	}
	

}
