package com.css.business.web.subsysmanu.mauManage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauWorkStation;
import com.css.business.web.subsysmanu.mauManage.service.MauWorkStationManageService;
import com.css.business.web.subsysplan.plaManage.bean.PlaMachineDisplayVo;
import com.css.business.web.subsysplan.plaManage.service.PlaMachinePlanManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;

@Controller
@RequestMapping("/mauWorkStationManageAction")
public class MauWorkStationManageAction
		extends
		BaseSpringSupportAction<MauWorkStation,MauWorkStationManageService> {

	// @Autowired
	private MauWorkStationManageService service;
	@Autowired
	private PlaMachinePlanManageService plaMachinePlanManageService;
	
	@Override
	public MauWorkStationManageService getEntityManager() {
		return service;
	}

	public MauWorkStationManageService getService() {
		return service;
	}

	@Resource(name = "mauWorkStationManageService")
	public void setService(MauWorkStationManageService service) {
		this.service = service;
	}

	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "mauManage/mauWorkStationEdit";
	}

	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "mauManage/mauWorkStationForm";
	}
/**
 * 跳转主页面
 *@data:2017年7月18日
@param request
@param id
@return
@autor:wl
 */
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "mauManage/workstationdisplay/workstationList";
	}
	/**
	 * 跳转到第一个展示页面
	 *@data:2017年7月18日
	@param request
	@param id
	@return
	@autor:wl
	 */
	@RequestMapping({ "toMauWorkStationOneShow" })
	public String toMauWorkStationOneShow(HttpServletRequest request,
			Integer id) {
		request.setAttribute("id", id);
		
		return "mauManage/workstationdisplay/workstationone";
	}
	/**
	 * 跳转到第二个展示页面
	 *@data:2017年7月18日
	@param request
	@param id
	@return
	@autor:wl
	 */
	@RequestMapping({ "toMauWorkStationTwoShow" })
	public String toMauWorkStationTwoShow(HttpServletRequest request,
			Integer id) {
		request.setAttribute("id", id);
		return "mauManage/workstationdisplay/workstationtwo";
	}

//	@RequestMapping({ "getChartDate" })
//	@ResponseBody
//	public PlaMachineDisplayVo getChartDate(String objSort) {
//		PlaMachineDisplayVo queryChart = plaMachinePlanManageService.queryChart();
//		/*Map<String, List<String>> map = service.getChart(objSort);*/
//		return queryChart;
//	}

	@RequestMapping({ "getPineChartDate" })
	@ResponseBody
	public Map<String, List<String>> getPineChartDate() {
		Map<String, List<String>> map = service.getPineChartDate();
		return map;

	}
	/**
	 * 获取第一个页面工位看板的数据
	 *@data:2017年7月19日
	@return
	@autor:wl
	 */
	@RequestMapping({ "getMessageOne" })
	@ResponseBody
	public Map<String, Object> getMessageOne() {
		Map<String, Object> map = service.getMessageOne();
		return map;

	}
	/**
	 * 获取工位第二个页面看板数据
	 *@data:2017年7月19日
	@return
	@autor:wl
	 */
	@RequestMapping({ "getMessageTwo" })
	@ResponseBody
	public Map<String, Object> getMessageTwo() {
		Map<String, Object> map = service.getMessageTwo();
		return map;

	}
	
	@RequestMapping({ "getMauWorkStationDate" })
	@ResponseBody
	public Page getMauWorkStationDate(HttpServletRequest request,
			Integer id, Page p, String time1, String time2,
			String productOrderCode, String materType, String contractId) {

		try {
			Page page = service.queryMauWorkStation(time1, time2,
					productOrderCode, materType, contractId, p);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping({ "toMauWorkStationTable" })
	public String toMauWorkStationTable(HttpServletRequest request,
			Integer id, String time1, String time2, String productOrderCode,
			String materType, String contractId) {
		request.setAttribute("time1", time1);
		request.setAttribute("time2", time2);
		request.setAttribute("productOrderCode", productOrderCode);
		request.setAttribute("materType", materType);
		request.setAttribute("contractId", contractId);
		return "mauManage/mauworkstationTable";
	}

	
	/**
	 * 
	 * @Description: app传上来的各类参数
	 * @param request
	 * @param id
	 * @param token
	 * @param rfidCode
	 * @param qualityReportCode
	 *            质检报告单号
	 * @param objSort
	 *            物料类型
	 * @param objGgxh
	 *            规格
	 * @param batchCode
	 *            批次
	 * @param acount
	 *            总重量
	 * @return
	 */


	@RequestMapping({ "toStorePickingMaterial" })
	public String toStorePickingMaterial(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "mauManage/storePickingMaterial";
	}


	


}
