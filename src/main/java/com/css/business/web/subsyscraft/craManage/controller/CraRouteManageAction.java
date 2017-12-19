package com.css.business.web.subsyscraft.craManage.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsyscraft.bean.CraRoute;
import com.css.business.web.subsyscraft.bean.CraRouteSeq;
import com.css.business.web.subsyscraft.craManage.service.CraRouteManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;

@Controller
@RequestMapping("/craRouteManageAction")
public class CraRouteManageAction extends
		BaseSpringSupportAction<CraRoute, CraRouteManageService> {

	// @Autowired
	private CraRouteManageService service;

	@Override
	public CraRouteManageService getEntityManager() {
		return service;
	}

	public CraRouteManageService getService() {
		return service;
	}

	@Resource(name = "craRouteManageService")
	public void setService(CraRouteManageService service) {
		this.service = service;
	}

	private Gson gson = new Gson();

	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "craManage/craRouteEdit";
	}

	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "craManage/craRouteForm";
	}

	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "craManage/craftRouteList";
	}

	@RequestMapping(value = { "/loadCraRouteManage" })
	@ResponseBody
	public HashMap<String, Object> loadCraRouteManage(String page,
			String pagesize) {
		return service.getCraRouteManageService(page, pagesize);
	}

	@RequestMapping(value = { "/serchByRouteCode" })
	@ResponseBody
	public HashMap<String, Object> serchByRouteCode(String page,
			String pagesize, String routeCode) {
		return service.getserchByRouteCodeService(page, pagesize, routeCode);
	}

	@RequestMapping(value = { "/deleteCraRouteManageByIds" })
	@ResponseBody
	public String deleteCraRouteManageByIds(int[] ids) {
		int[] a = ids;
		service.deleteCraRouteService(a);
		return "删除工艺路线成功";
	}

	@RequestMapping(value = { "/deleteCraRouteSeqByrouteCodes" })
	@ResponseBody
	public String deleteCraRouteSeqByrouteCodes(String[] routeCodes) {
		String[] a = routeCodes;
		service.deleteCraRouteSeqManageByRouteCodesService(a);
		return "删除工艺路线明细成功";
	}

	@RequestMapping(value = { "/loadCraRouteSeqManage" })
	@ResponseBody
	public HashMap<String, Object> loadCraRouteSeqManage(String page,
			String pagesize, String routeCode) {
		return service.getCraRouteSeqManageService(page, pagesize, routeCode);
	}

	@RequestMapping(value = { "/updateCraRouteManage" })
	@ResponseBody
	public String updateCraRouteManage(CraRoute craRoute) {
		service.updateCraRouteService(craRoute);
		return "更新成功";
	}

	@RequestMapping(value = "/updateCraRouteSeqManage", method = RequestMethod.POST)
	@ResponseBody
	public String updateCraRouteSeqManage(@RequestBody CraRouteSeq[] data) {
		List<CraRouteSeq> craRouteSeq = new ArrayList<CraRouteSeq>();
		for (int i = 0; i < data.length; i++) {
			craRouteSeq.add(data[i]);
		}
		service.updateCraRouteSeqService(craRouteSeq);
		return "更新成功";
	}

	@RequestMapping(value = { "/addCraRouteManage" })
	@ResponseBody
	public String addCraRouteManage(CraRoute craRoute) {
		service.addCraRouteService(craRoute);
		return "新增成功";
	}

	@RequestMapping(value = "/addCraRouteSeqManage", method = RequestMethod.POST)
	@ResponseBody
	public String addCraRouteSeqManage(@RequestBody CraRouteSeq[] data) {
		List<CraRouteSeq> craRouteSeq = new ArrayList<CraRouteSeq>();
		for (int i = 0; i < data.length; i++) {
			craRouteSeq.add(data[i]);
		}
		service.addCraRouteSeqService(craRouteSeq);
		return "新增成功";
	}

	/****** 新开的方法 ***************/
	/**
	 * 
	 * @Description:分页查询
	 * @param request
	 * @param proGgxh
	 *            产品编号 ,工艺路线名称
	 * @param page
	 * @return
	 */
	@RequestMapping("getListPage")
	@ResponseBody
	public Page getListPage(HttpServletRequest request, String seqName,
			String proGgxh, Page page) {
		Page queryPage = null;
		try {
			queryPage = service.getPageList(page, seqName, proGgxh);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return queryPage;
	}

	@RequestMapping("clearBean")
	@ResponseBody
	public HashMap<String, Object> clearBean(HttpServletRequest request,
			Integer id) {
		try {
			CraRoute craRoute = service.getCraRouteById(id);
			service.delCraRoute(craRoute.getProGgxh(), craRoute.getSeqName());
			service.deleteBusiness(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("删除失败");
		}
		return JsonWrapper.successWrapper(null, "删除成功");
	}

}
