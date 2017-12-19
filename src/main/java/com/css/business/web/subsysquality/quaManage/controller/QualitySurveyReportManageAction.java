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

import com.css.business.web.subsysquality.bean.QualitySurveyReport;
import com.css.business.web.subsysquality.quaManage.service.QualitySurveyReportManageService;
import com.css.commcon.util.SessionUtils;
import com.css.common.util.PropertyFileUtil;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @Title: QualitySurveyReportManageAction.java
 * @Package com.css.business.web.subsysquality.quaManage.controller
 * @Description: 现在作为原材料的
 * @author rb
 * @date 2017年8月5日 下午3:30:11
 * @company SMTC
 */
@Controller
@RequestMapping("/qualitySurveyReportManageAction")
public class QualitySurveyReportManageAction
		extends
		BaseSpringSupportAction<QualitySurveyReport, QualitySurveyReportManageService> {

	// @Autowired
	private QualitySurveyReportManageService service;

	@Override
	public QualitySurveyReportManageService getEntityManager() {
		return service;
	}

	public QualitySurveyReportManageService getService() {
		return service;
	}

	@Resource(name = "qualitySurveyReportManageService")
	public void setService(QualitySurveyReportManageService service) {
		this.service = service;
	}
	
	

	private Gson gson = new Gson();

	@RequestMapping("toUpload")
	public String toUpload(HttpServletRequest request) {
		return "quaManage/uploadTest";
	}

	/**
	 * 
	 * @Description: 去原材料page页面
	 * @param request
	 * @return
	 */
	@RequestMapping("toMaterialPageList")
	public String toMaterialPageList(HttpServletRequest request) {
		return "quaManage/quaMaterialList";
	}

	/**
	 * 
	 * @Description: 去原材料增加修改的form页面,如果有id就查询,返回,没id 就是添加
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("toMaterialForm")
	public String toMaterialForm(HttpServletRequest request, Integer id) {
		if (id != null) {
			QualitySurveyReport report = service.get(id);
			if (report == null) {
				request.setAttribute("msg", "没有此条记录");
			}
			request.setAttribute("report", report);
			request.setAttribute("info", "修改");
		}else{
			request.setAttribute("info", "添加");
		}
		return "quaManage/quaMaterialForm";
	}
	/**
	 * 
	 * @Description: 去打印页面   
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("toPrintForm")
	public String toPrintForm(HttpServletRequest request, Integer id) {
		if (id != null) {
			QualitySurveyReport report = service.get(id);
			if (report == null) {
				request.setAttribute("msg", "没有此条记录");
			}
			request.setAttribute("report", report);
		}
		return "quaManage/quaMaterialPrint";
	}

	/**
	 * 
	 * @Description: 保存检测报告
	 * @param request
	 * @return
	 */
	/*@RequestMapping("saveQualitySurveyReport")
	@ResponseBody
	public HashMap<String, Object> saveQualitySurveyReport(
			HttpServletRequest request, String token, String createBy,
			String objSort, String acount, String surveyReportCode,
			String unit, String supplyAgent, String batchCode,
			String isSupplySurey, String objGgxh, String objColor,
			String rfidCode, String reportResult) {
		if ("合格".equals("reportResult") && (rfidCode == null || rfidCode == "")) {
			return JsonWrapper.failureWrapperMsg(reportResult,
					"保存失败,如果合格,RFID卡不能为空");
		}
		QualitySurveyReport report = new QualitySurveyReport();
		// report.setAcount(acount);
		report.setBatchCode(batchCode);
		report.setCreateDate(new Date());
		report.setIsSupplySurey(isSupplySurey);
		report.setObjColor(objColor);
		report.setObjGgxh(objGgxh);
		report.setSupplyAgent(supplyAgent);
		// report.setSurveyDate(new Date());
		report.setSurveyReportCode(surveyReportCode);
		report.setUnit(unit);
		try {
			service.save(report);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(report, "保存失败");
		}
		return JsonWrapper.successWrapper(report, "保存成功");
	}*/

	@RequestMapping("queryPage")
	@ResponseBody
	public Page queryPage(HttpServletRequest request, String param, Page page) {
		Map<String, String> map = gson.fromJson(param,new TypeToken<Map<String, String>>() {}.getType());
		Page queryPage = page;
		try {
			queryPage = service.queryPage(map, page);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return queryPage;
	}
	
	/**
	 * 导出原材料质检结果
	 * @param request
	 * @param param
	 * @param page
	 */
	@RequestMapping("toExport")
	@ResponseBody
	public void exportMaterial(HttpServletRequest request,HttpServletResponse response, String param, Page page){
		Map<String, String> map = gson.fromJson(param,new TypeToken<Map<String, String>>() {}.getType());
//		Map<String, String> mapInfo = new HashMap<>();
		try {
			service.exportMaterialService(map,page,response);
//			mapInfo.put("success", "导出已执行成功，请查看结果！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 * @Description: form 表单提交 保存或者更新
	 * @param request
	 * @param qualitySurveyReport
	 *            form表单对应的bean 字段相对应
	 * @param myfiles
	 *            检验单
	 * @return
	 */
	@RequestMapping("saveSurveyReport")
	//@ResponseBody
	public String/*HashMap<String, String>*/ saveSurveyReport(HttpServletRequest request,
			QualitySurveyReport qualitySurveyReport, MultipartFile myfiles) {
		String account = SessionUtils.getUser(request).getAccount();
		qualitySurveyReport.setCreateBy(account);
		qualitySurveyReport.setCreateDate(new Date());
			HashMap<String, String> resutMap = new HashMap<>();
		try {
			// 获得检测报告的的code
			String code = qualitySurveyReport.getSurveyReportCode();
			//如果上传了图片
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
						+ util.getProp("folder") + uri;
				// 需要保存进数据库的uri
				uri = util.getProp("report") + File.separator
						+ util.getProp("folder") + uri +File.separator+ fileName;
				qualitySurveyReport.setReportUrl(uri);
				File targetFile = new File(filePath, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}

				myfiles.transferTo(targetFile);
			}

			// 判断是新建还是更新
			if (qualitySurveyReport.getId() == null) {
				qualitySurveyReport.setIsCome("未入库");
				//系统自动生成的code
				String suCode  = (String)service.getFunction("fun_get_material_code");
				qualitySurveyReport.setSurveyCode(suCode);
				service.save(qualitySurveyReport);
			} else {
				QualitySurveyReport report = service.get(qualitySurveyReport.getId());
				//三个时间为空,图片URL为空,不管
				if (qualitySurveyReport.getExpiryDate()==null){
					qualitySurveyReport.setExpiryDate(report.getExpiryDate());
				}
				if (qualitySurveyReport.getReleaseDate()==null){
					qualitySurveyReport.setReleaseDate(report.getReleaseDate());
				}
				if (qualitySurveyReport.getSurveyDate()==null){
					qualitySurveyReport.setSurveyDate(report.getSurveyDate());
				}
				if (qualitySurveyReport.getReportUrl()==null){
					qualitySurveyReport.setReportUrl(report.getReportUrl());
				}
				service.updateByCon(qualitySurveyReport, false);
			}
			resutMap.put("msg", "保存成功");
			request.setAttribute("msg", "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			resutMap.put("msg", "保存失败");
			request.setAttribute("msg", "保存失败");
		}
		return "quaManage/quaMaterialList";
	}
	
	/**
	 * 
	 * @Description: 删除
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("clearReport")
	@ResponseBody
	public Map<String,String>clearReport(HttpServletRequest request,Integer id){
		Map<String,String> map = new HashMap<>();
		try {
			service.clearReport(id);
			map.put("msg", "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("msg", "删除失败");
		}
		return map;
	}
	
	/**
	 * 
	 * @Description: 查看详情   
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("reportDetail")
	public String reportDetail(HttpServletRequest request,Integer id){
		try {
			QualitySurveyReport report = service.get(id);
			request.setAttribute("report", report);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//失败返回
			request.setAttribute("msg", "查看详情失败");
			return "quaManage/quaMaterialList";
		}
		//成功返回
		return "quaManage/quaMaterialForm";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
