package com.css.business.web.subsysplan.plaManage.controller;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysplan.bean.PlaDispatch;
import com.css.business.web.subsysplan.plaManage.service.PlaDispatchManageService;
import com.css.business.web.subsysplan.vo.PlaDisPatchVo;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;

@Controller
@RequestMapping("/plaDispatchManageAction")
public class PlaDispatchManageAction extends
		BaseSpringSupportAction<PlaDispatch, PlaDispatchManageService> {

	@Resource(name = "plaDispatchManageService")
	private PlaDispatchManageService service;

	@Override
	public PlaDispatchManageService getEntityManager() {
		return service;
	}

	public PlaDispatchManageService getService() {
		return service;
	}

	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "plaManage/plaDispatchEdit";
	}

	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "plaManage/plaDispatchForm";
	}

	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "plaManage/plaDispatchList";
	}

	@RequestMapping({ "toTablePage" })
	public String toTablePage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "plaManage/plaDispatchTable";
	}

	@RequestMapping({ "toChartPage" })
	public String toChartPage(HttpServletRequest request, Integer id,
			String date, String seqName) {
		request.setAttribute("id", id);
		request.setAttribute("date", date);
		request.setAttribute("seqName", seqName);
		return "plaManage/plaDispatchChart";
	}

	/**
	 * 获得日历饼图的数据
	 */
	@RequestMapping({ "getPlanDisPatchCanlenderPie" })
	@ResponseBody
	public PlaDisPatchVo getPlanDisPatchCanlenderPie() {
		try {
			PlaDisPatchVo vo = service.getPlanDisPatchCanlenderPie();
			return vo;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获得柱状图的数据
	 * 
	 * @param request
	 * @param date
	 * @param seqName
	 * @return
	 */
	@RequestMapping({ "getPlanDisPatchCanBar" })
	@ResponseBody
	public PlaDisPatchVo getPlanDisPatchCanBar(HttpServletRequest request,
			String date, String seqName) {
		try {
			PlaDisPatchVo vo = service.getPlanDisPatchCanBar(date, seqName);
			return vo;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
