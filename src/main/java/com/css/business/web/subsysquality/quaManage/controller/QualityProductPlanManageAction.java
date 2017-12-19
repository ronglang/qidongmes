package com.css.business.web.subsysquality.quaManage.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.css.business.web.subsysquality.bean.QualityProductPlan;
import com.css.business.web.subsysquality.quaManage.service.QualityProductPlanManageService;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.util.PropertyFileUtil;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @Title: QualityProductPlanManageAction.java
 * @Package com.css.business.web.subsysquality.quaManage.controller
 * @Description: 成品,半成品检测
 * @author rb
 * @date 2017年8月11日 上午10:00:04
 * @company SMTC
 */
@Controller
@RequestMapping("/qualityProductPlanManageAction")
public class QualityProductPlanManageAction
		extends
		BaseSpringSupportAction<QualityProductPlan, QualityProductPlanManageService> {

	// @Autowired
	private QualityProductPlanManageService service;

	@Override
	public QualityProductPlanManageService getEntityManager() {
		return service;
	}

	public QualityProductPlanManageService getService() {
		return service;
	}

	@Resource(name = "qualityProductPlanManageService")
	public void setService(QualityProductPlanManageService service) {
		this.service = service;
	}

	private Gson gson = new Gson();
	/**
	 * 
	 * @Description: 电子看板   
	 * @return
	 */
	@RequestMapping("toPageListBoard")
	public String toPageListBoard(){
		return "quaManage/proPlan/quaProTableBorad";
	}
	
	@RequestMapping("toQualProTablePrint")
	public String toQualProTablePrint(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "quaManage/proPlan/qualProTablePrint";
	}
	
	/**
	 * 
	 * @Description: 后台管理的分页
	 * @return
	 */
	@RequestMapping("toPageList")
	public String toPageList(){
		return "quaManage/proPlan/quaProTable";
	}
	
	@RequestMapping({"toFormPage"})
	public String toFormPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "quaManage/proPlan/quaProForm";
	}
	
	@RequestMapping({"toEditPage"})
	public String toEditPage(HttpServletRequest request, Integer id){
		QualityProductPlan plan = service.get(id);
		if (plan  == null ) {
			request.setAttribute("msg", "未查到相关信息");
			return "quaManage/proPlan/quaProTable";
		}
		request.setAttribute("info", "修改");
		request.setAttribute("plan", plan);
		return "quaManage/proPlan/quaSaveForm";
	}
	

	
	@RequestMapping("toSave")
	public String toSave(HttpServletRequest request){
		request.setAttribute("info", "增加");
		return "quaManage/proPlan/quaSaveForm";
	}
	
	/**
	 * 
	 * @Description: 去打印页面   
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("toPrint")
	public String toPrint(HttpServletRequest request,Integer id){
		QualityProductPlan plan = service.get(id);
		if (plan == null) {
			request.setAttribute("msg", "未查到相关信息");
		} else {
			request.setAttribute("plan", plan);
		}
		
		return "";
	}
	/**
	 * 
	 * @Description: 后台管理主页面   
	 * @param request
	 * @param param
	 * @param page
	 * @return
	 */
	@RequestMapping("getPageList")
	@ResponseBody
	public Page getPageList(HttpServletRequest request,String param,Page page){
		Map<String,String>map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Page queryPage = page;
		queryPage = service.queryPageList(page,map);
		return queryPage;
		
	}
	
	@RequestMapping("toExportExcel")
	public void toExportExcel(HttpServletRequest request,HttpServletResponse response,String param,Page page){
		Map<String,String>map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		try {
			service.exportExcelService(response,page,map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Description: 后台修改检测报告,允许修改状态结果,备注,上传图片   
	 * @param request
	 * @param plan
	 * @param myfiles
	 * @return
	 */
	@RequestMapping("updatePlan")
	public String updatePlan(HttpServletRequest request,QualityProductPlan plan,MultipartFile myfiles){
		String code = plan.getReportCode() ;
		SysUser user = SessionUtils.getUser(request);
		try {
			//上传检测报告图片
			if (myfiles.getSize() >1) {
				String originalFilename = myfiles.getOriginalFilename();
				String type = originalFilename.substring(originalFilename
						.lastIndexOf("."));
				String uri = "";
				Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH) + 1;
				int day = c.get(Calendar.DAY_OF_MONTH);
				uri += File.separator + year + File.separator + month
						+ File.separator + day;

				String fileName = code + type;
				PropertyFileUtil util = new PropertyFileUtil();
				String filePath = util.getProp("reportUpload") + File.separator
						+ util.getProp("report") + File.separator
						+ util.getProp("profolder") + uri;
				// 需要保存进数据库的uri
				uri = util.getProp("report") + File.separator
						+ util.getProp("profolder") + uri +File.separator+ fileName;
				plan.setReportUrl(uri);
				File targetFile = new File(filePath, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				myfiles.transferTo(targetFile);
			}
			//原报告
			/*QualityProductPlan rePlan = service.get(plan.getId());
			Date reDate = rePlan.getFactTestDate();*/
			
			if (plan.getId()==null ) {
				//新建
				plan.setCreateBy(user.getAccount());
				plan.setCreateDate(new Date());
				//系统自动生产的检测单号
				String fun = (String) service.getFun("fun_get_mau_code");
				plan.setSurvyCode(fun);
				service.save(plan);
			}else {
				//更新
				//plan.setFactTestDate(new Date());
				service.updateByCon(plan, false);
			}
			
			//数据回显
			request.setAttribute("msg", "操作成功");
			request.setAttribute("plan", plan);
			
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", "操作失败");
			e.printStackTrace();
			return "quaManage/proPlan/quaSaveForm";
		}
		//返回修改页面
		return "quaManage/proPlan/quaProTable";
	}
	/**
	 * 
	 * @Description: app通过轴号 获得计划   
	 * @param request
	 * @param token
	 * @param axisName
	 * @return
	 */
	/*@RequestMapping("appGetPlan")
	@ResponseBody
	public QualityProductPlan appGetPlan(HttpServletRequest request,
			String token, String axisName) {
		QualityProductPlan plan = service.queryByAxisName(axisName);
		return plan;
	}*/

	/**
	 * 
	 * @Description: app给出结果,更新原计划   
	 * @param request
	 * @param id
	 * @param result
	 * @return
	 */
	@RequestMapping("appUpdatePlan")
	@ResponseBody
	public HashMap<String, Object> appUpdatePlan(HttpServletRequest request,
			Integer id, String result) {
		try {
			QualityProductPlan plan = service.get(id);
			plan.setFactTestDate(new Date());
			plan.setTestResult(result);
			plan.setTestState(QualityProductPlan.TEST_STATE_TESTED);
			service.updateByCon(plan, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonWrapper.failureWrapper("检测失败");
		}
		return JsonWrapper.successWrapper("检测成功");
	}
	
	/**
	 * 
	 * @Description: app通过扫工序半制品轴号(rfid)查找轴名称 返回数据   
	 * @param request
	 * @param token
	 * @param rfid
	 * @return
	 */
	@RequestMapping("appGetPlan")
	@ResponseBody
	public HashMap<String, Object>appGetPlan(HttpServletRequest request,String token,String rfid){
		QualityProductPlan plan = service.appGetPlan(rfid);
		if (plan != null) {
			return JsonWrapper.successWrapper(plan, "查找成功");
		}else {
			return JsonWrapper.failureWrapperMsg("未找到相关数据");
		}
		
	}
	

}
