package com.css.business.web.subsysmanu.mauManage.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauProcessSampling;
import com.css.business.web.subsysmanu.mauManage.bean.MauProcessMonitoringVo;
import com.css.business.web.subsysmanu.mauManage.service.MauProcessSamplingManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;

@Controller
@RequestMapping("/mauProcessSamplingManageAction")
public class MauProcessSamplingManageAction
		extends
		BaseSpringSupportAction<MauProcessSampling, MauProcessSamplingManageService> {

	// @Autowired
	private MauProcessSamplingManageService service;

	@Override
	public MauProcessSamplingManageService getEntityManager() {
		return service;
	}

	public MauProcessSamplingManageService getService() {
		return service;
	}

	@Resource(name = "mauProcessSamplingManageService")
	public void setService(MauProcessSamplingManageService service) {
		this.service = service;
	}

	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "mauManage/mauProcessSamplingEdit";
	}

	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "mauManage/mauProcessSamplingForm";
	}

	/**
	 * 质量回溯入口
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @author JiangShuai
	 */
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "mauManage/mauProcessSamplingList";
	}

	/**
	 * 质量回溯详情
	 * 
	 * @param request
	 * @param id
	 * @param productid
	 * @return
	 * @author JiangShuai
	 */
	@RequestMapping({ "toListDetailPage" })
	public String toListDetailPage(HttpServletRequest request, Integer id,
			Integer productid) {
		request.setAttribute("id", id);
		if (productid != null) {
			request.setAttribute("productid", productid);
		}
		return null;
	}

	/**
	 * 质量回溯展示
	 * 
	 * @param request
	 * @param id
	 * @param productid
	 * @return
	 */
	@RequestMapping({ "toBackDateTable" })
	public String toBackDateTable(HttpServletRequest request, Integer id,
			Integer productid) {
		request.setAttribute("id", id);
		if (productid != null) {
			request.setAttribute("productid", productid);
		}
		return "mauManage/processSamplingTable";
	}

	@RequestMapping({ "getBackDate" })
	@ResponseBody
	public Page getBackDate(HttpServletRequest request, Page p, Integer id,
			String endaxisnumber) {
		request.setAttribute("id", id);
		try {
			Page page = service.queryBackDateService(p, endaxisnumber);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping({ "toCraParameter" })
	public String toCraParameter(HttpServletRequest request, Integer id,
			Integer productid) {
		request.setAttribute("id", id);
		if (productid != null) {
			request.setAttribute("productid", productid);
		}
		return "mauManage/craParameterTable";
	}

	@RequestMapping({ "getCraParameter" })
	@ResponseBody
	public Page getCraParameter(HttpServletRequest request, Page p, Integer id,
			Integer productid) {
		request.setAttribute("id", id);
		try {
			Page page = service.queryCraParameter(p, productid);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping({ "getBackAxisDate" })
	@ResponseBody
	public Page getBackAxisDate(HttpServletRequest request, Page p, Integer id,
			String endaxisnumber) {
		try {
			Page page = service.queryBackAxisDatePageService(p.getPageNo(),
					p.getPagesize(), endaxisnumber);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping({ "toBackDateDetailTable" })
	public String toBackDateDetailTable(HttpServletRequest request, Integer id,
			String endaxisnumber) {
		request.setAttribute("id", id);
		if (endaxisnumber != null) {
			request.setAttribute("endaxisnumber", endaxisnumber);
		}
		return "mauManage/backDateDetailTable";
	}

	@RequestMapping({ "toBackDateDetail" })
	public String toBackDateDetail(HttpServletRequest request, Integer id,
			String maccode, String seqname, String materid, String ccode,
			String mpscode) {
		request.setAttribute("id", id);
		request.setAttribute("seqname", seqname);
		request.setAttribute("materid", materid);
		request.setAttribute("ccode", ccode);
		request.setAttribute("mpscode", mpscode);
		request.setAttribute("maccode", maccode);
		return "mauManage/backDateDetail";
	}

	@RequestMapping({ "toCraParamDetailTable" })
	public String toCraParamDetailTable(HttpServletRequest request, Integer id,
			String seqname, String maccode) {
		request.setAttribute("id", id);
		request.setAttribute("seqname", seqname);
		request.setAttribute("maccode", maccode);
		return "mauManage/craParameterDetailTable";
	}

	@RequestMapping({ "toBackDateCraParam" })
	@ResponseBody
	public List<MauProcessMonitoringVo> toBackDateCraParam(
			HttpServletRequest request, Integer id, String seqname) {
		List<MauProcessMonitoringVo> list = service
				.queryBackDateCraParamService(seqname);
		return list;
	}
	
	@RequestMapping({"getBackDateMater"})
	@ResponseBody
	public Page getBackDateMater(HttpServletRequest request, Page p,String materid,String seqname){
		try {
			Page page = service.queryBackDateMaterService(p, materid, seqname);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping({"toBackDateMater"})
	public String toBackDateMater(HttpServletRequest request, Integer id,String materid,String seqname){
		request.setAttribute("id", id);
		request.setAttribute("materid", materid);
		request.setAttribute("seqname", seqname);
		return "mauManage/materProTable";
	}
	

}
