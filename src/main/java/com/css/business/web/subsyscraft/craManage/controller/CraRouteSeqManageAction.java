package com.css.business.web.subsyscraft.craManage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsyscraft.bean.CraRouteSeq;
import com.css.business.web.subsyscraft.craManage.service.CraRouteSeqManageService;
import com.css.business.web.subsyscraft.vo.VOBean;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/craRouteSeqManageAction")
public class CraRouteSeqManageAction extends BaseSpringSupportAction<CraRouteSeq, CraRouteSeqManageService> {
	
	//@Autowired
	private CraRouteSeqManageService service;
	
	@Override
	public CraRouteSeqManageService getEntityManager() {
		return service;
	}

	public CraRouteSeqManageService getService() {
		return service;
	}

	@Resource(name="craRouteSeqManageService")
	public void setService(CraRouteSeqManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craRouteSeqEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craRouteSeqForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craRouteSeqList";
	}
	
	
	@RequestMapping({ "toTree" })
	@ResponseBody
	public List<VOBean> toTree(HttpServletRequest request,String courseCode){
		
		List<VOBean> list;
		try {
			list = new ArrayList<VOBean>();
			list = service.getVoTreeList(courseCode);
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
