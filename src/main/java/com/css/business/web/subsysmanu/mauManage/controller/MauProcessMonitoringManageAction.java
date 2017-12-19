package com.css.business.web.subsysmanu.mauManage.controller;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauProcessMonitoring;
import com.css.business.web.subsysmanu.mauManage.bean.MauProcessMonitoringVo;
import com.css.business.web.subsysmanu.mauManage.service.MauProcessMonitoringManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
@Controller
@RequestMapping("/mauProcessMonitoringManageAction")
public class MauProcessMonitoringManageAction extends BaseSpringSupportAction<MauProcessMonitoring, MauProcessMonitoringManageService> {
	
	//@Autowired
	private MauProcessMonitoringManageService service;
	
	@Override
	public MauProcessMonitoringManageService getEntityManager() {
		return service;
	}

	public MauProcessMonitoringManageService getService() {
		return service;
	}

	@Resource(name="mauProcessMonitoringManageService")
	public void setService(MauProcessMonitoringManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauProcessMonitoringEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauProcessMonitoringForm";
	}
	/**
	 * 制程检测入口
	 * @param request
	 * @param id
	 * @return
	 * @author JiangShuai
	 */
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauProcessMonitoringList";
	}
	
	
	/**
	 * 
	 * @param request
	 * @param id
	 * @param productid
	 * @return
	 * @author JiangShuai
	 */
	@RequestMapping({"toListDetailPage"})
	public String toListDetailPage(HttpServletRequest request, Integer id,Integer productid){
		request.setAttribute("id", id);
		if(productid != null){
			request.setAttribute("productid", productid);
		}
		return "mauManage/mauProcessMonitoringForm";
	}
	
	/**
	 * 
	 * @param request
	 * @param id
	 * @param productid
	 * @return
	 * @author JiangShuai
	 */
	@RequestMapping({"toMonitoringTable"})
	public String toMonitoringTable(HttpServletRequest request, Integer id,Integer productid){
		request.setAttribute("id", id);
		if(productid != null){
			request.setAttribute("productid", productid);
		}
		return "mauManage/monitoringTable";
	}
	
	/**
	 * 
	 * @param request
	 * @param parm
	 * @param entity
	 * @param productid
	 * @return
	 * @author JiangShuai
	 */
	@RequestMapping({"getMauVoData"})
	@ResponseBody
	public Page getMauVoData(HttpServletRequest request,Page parm, MauProcessMonitoring entity,Integer productid){
		try {
			Page page = service.queryMauProcessMonitoring(parm, entity,productid);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param id
	 * @param productid
	 * @return
	 * @author JiangShuai
	 */
	@RequestMapping({"toMonitoringDetailTable"})
	public String toMonitoringDetailTable(HttpServletRequest request, Integer id,Integer productid){
		request.setAttribute("id", id);
		if(productid != null){
			request.setAttribute("productid", productid);
			return "mauManage/monitoringDetailTable";
		}
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param id
	 * @param productid
	 * @return
	 * @author JiangShuai
	 */
	@RequestMapping({"toMonitoringDetailTableOne"})
	public String toMonitoringDetailTableOne(HttpServletRequest request, Integer id,Integer productid){
		request.setAttribute("id", id);
		if(productid != null){
			request.setAttribute("productid", productid);
			return "mauManage/monitoringDetailTableOne";
		}
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param id
	 * @param productid
	 * @return
	 * @author JiangShuai
	 */
	@RequestMapping({"toMonitoringDetailTableTwo"})
	public String toMonitoringDetailTableTwo(HttpServletRequest request, Integer id,Integer productid){
		request.setAttribute("id", id);
		if(productid != null){
			request.setAttribute("productid", productid);
			return "mauManage/monitoringDetailTableTwo";
		}
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param id
	 * @param productid
	 * @return
	 * @author JiangShuai
	 */
	@RequestMapping({"toMonitoringDetailTableThree"})
	public String toMonitoringDetailTableThree(HttpServletRequest request, Integer id,Integer productid){
		request.setAttribute("id", id);
		if(productid != null){
			request.setAttribute("productid", productid);
			return "mauManage/monitoringDetailTableThree";
		}
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param id
	 * @param productid
	 * @return
	 * @author JiangShuai
	 */
	@RequestMapping({"toMonitoringDetailTableFour"})
	public String toMonitoringDetailTableFour(HttpServletRequest request, Integer id,Integer productid){
		request.setAttribute("id", id);
		if(productid != null){
			request.setAttribute("productid", productid);
			return "mauManage/monitoringDetailTableFour";
		}
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param id
	 * @param productid
	 * @return
	 * @author JiangShuai
	 */
	@RequestMapping({"toMonitoringDetailTableFive"})
	public String toMonitoringDetailTableFive(HttpServletRequest request, Integer id,Integer productid){
		request.setAttribute("id", id);
		if(productid != null){
			request.setAttribute("productid", productid);
			return "mauManage/monitoringDetailTableFive";
		}
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param id
	 * @param productid
	 * @return
	 * @author JiangShuai
	 */
	@RequestMapping({"toMonitoringDetailTableSix"})
	public String toMonitoringDetailTableSix(HttpServletRequest request, Integer id,Integer productid){
		request.setAttribute("id", id);
		if(productid != null){
			request.setAttribute("productid", productid);
			return "mauManage/monitoringDetailTableSix";
		}
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param id
	 * @param productid
	 * @return
	 * @author JiangShuai
	 */
	@RequestMapping({"toMonitoringDetailTableSeven"})
	public String toMonitoringDetailTableSeven(HttpServletRequest request, Integer id,Integer productid){
		request.setAttribute("id", id);
		if(productid != null){
			request.setAttribute("productid", productid);
			return "mauManage/monitoringDetailTableSeven";
		}
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param parm
	 * @param entity
	 * @param productid
	 * @param seqname
	 * @return
	 * @author JiangShuai
	 */
	
	@RequestMapping({"getMauVoSeq"})
	@ResponseBody
	public Page getMauVoSeq(HttpServletRequest request,Page parm, MauProcessMonitoring entity,Integer productid,String seqname){
		try {
			Page page = service.quryMauMonitoringDetail(parm, entity, productid,seqname);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping({"getList"})
	@ResponseBody
	public List<MauProcessMonitoringVo> getList(HttpServletRequest request,Integer productid,String seqname){
		List<MauProcessMonitoringVo> list = service.queryListService(productid, seqname);
		return list;
	}
	
	@RequestMapping({"goHomePage"})
	public String goHomePage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/home";
	}


}
