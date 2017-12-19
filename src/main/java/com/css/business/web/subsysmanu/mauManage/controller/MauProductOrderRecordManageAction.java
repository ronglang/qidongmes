package com.css.business.web.subsysmanu.mauManage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauCallForkliftRecord;
import com.css.business.web.subsysmanu.bean.MauProductOrderRecord;
import com.css.business.web.subsysmanu.mauManage.service.MauCallForkliftRecordManageService;
import com.css.business.web.subsysmanu.mauManage.service.MauProductOrderRecordManageService;
import com.css.business.web.subsysplan.plaManage.bean.PlaMachineDisplayVo;
import com.css.business.web.subsysplan.plaManage.service.PlaMachinePlanManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;

@Controller
@RequestMapping("/mauProductOrderRecordManageAction")
public class MauProductOrderRecordManageAction
		extends
		BaseSpringSupportAction<MauProductOrderRecord, MauProductOrderRecordManageService> {
	@Resource(name = "mauProductOrderRecordManageService")
	// @Autowired
	private MauProductOrderRecordManageService service;
	//@Autowired
	@Resource(name="plaMachinePlanManageService")
	private PlaMachinePlanManageService plaMachinePlanManageService;
	
	
	
	@Override
	public MauProductOrderRecordManageService getEntityManager() {
		return service;
	}

	public MauProductOrderRecordManageService getService() {
		return service;
	}

	/*@Resource(name = "mauProductOrderRecordManageService")
	public void setService(MauProductOrderRecordManageService service) {
		this.service = service;
	}*/

	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "mauManage/mauProductOrderRecordEdit";
	}

	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "mauManage/mauProductOrderRecordForm";
	}

//	@RequestMapping({ "toListPage" })
//	public String toListPage(HttpServletRequest request, Integer id) {
//		request.setAttribute("id", id);
//		return "mauManage/mauProductOrderRecordList";
//	}

	@RequestMapping({ "toMauProductOrderRecordPineChart" })
	public String toMauProductOrderRecordPineChart(HttpServletRequest request,
			Integer id) {
		request.setAttribute("id", id);
		return "mauManage/mauProductOrderRecordCuChart";
	}

	@RequestMapping({ "toMauProductOrderRecordGlueChart" })
	public String toMauProductOrderRecordGlueChart(HttpServletRequest request,
			Integer id) {
		request.setAttribute("id", id);
		return "mauManage/mauProductOrderRecordGlueChart";
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

	@RequestMapping({ "getMauProductOrderRecordDate" })
	@ResponseBody
	public Page getMauProductOrderRecordDate(HttpServletRequest request,
			Integer id, Page p, String time1, String time2,
			String productOrderCode, String materType, String contractId) {

		try {
			Page page = service.queryMauProductOrderRecord(time1, time2,
					productOrderCode, materType, contractId, p);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping({ "toMauProductOrderRecordTable" })
	public String toMauProductOrderRecordTable(HttpServletRequest request,
			Integer id, String time1, String time2, String productOrderCode,
			String materType, String contractId) {
		request.setAttribute("time1", time1);
		request.setAttribute("time2", time2);
		request.setAttribute("productOrderCode", productOrderCode);
		request.setAttribute("materType", materType);
		request.setAttribute("contractId", contractId);
		return "mauManage/mauProductOrderRecordTable";
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

	
	
	
	/***********************TG************************/
	@Resource(name="mauCallForkliftRecordManageService")
	private MauCallForkliftRecordManageService services;
	
	/**
	 * 去生产部电子看板页面
	 * @return
	 */
	@RequestMapping({ "toProductListPage" })
	public String toProductListPage(HttpServletRequest req) {
//		List<MauCallForkliftRecord>list = new ArrayList<>();
//		try {
//			list = services.queryCallCarList();
//			req.setAttribute("list", list);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return "mauManage/cendisplay/mauProductKanBanPage";
	}
	
	/**
	 * 机台状态数页面
	 * @return
	 */
	@RequestMapping({ "productMachineNum" })
	public String productMachineNum(){
		return "mauManage/cendisplay/childpage/productMachineNum";
	}
	
	/**
	 * 订单生产详情页面图
	 * @return
	 */
	@RequestMapping({"productInfo"})
	public String productInfo(){
		return "mauManage/cendisplay/childpage/productInfo";
	}
	
	/**
	 * 订单生产进度页面图
	 * @return
	 */
	@RequestMapping({"productSchedule"})
	public String productSchedule(){
		return "mauManage/cendisplay/childpage/productSchedule";
	}
	
	/**
	 * OEE随时间波动情况图
	 * @return
	 */
	@RequestMapping({"productOeeTimeLeft"})
	public String productOeeTimeLeft(){
		return "mauManage/cendisplay/childpage/productOeeTimeLeft";
	}
	
	/**
	 * OEE随时间波动情况图
	 * @return
	 */
	@RequestMapping({"productOeeTimeRight"})
	public String productOeeTimeRight(){
		return "mauManage/cendisplay/childpage/productOeeTimeRight";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
