package com.css.common.web.workflow.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.workflow.bean.WfNode;
import com.css.common.web.workflow.service.WfNodeManageService;
@Controller
@RequestMapping("/wfNodeManageAction")
public class WfNodeManageAction extends BaseSpringSupportAction<WfNode, WfNodeManageService> {
	
	//@Autowired
	private WfNodeManageService service;
	
	@Override
	public WfNodeManageService getEntityManager() {
		return service;
	}

	public WfNodeManageService getService() {
		return service;
	}

	@Resource(name="wfNodeManageService")
	public void setService(WfNodeManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfNodeEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfNodeForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfNodeList";
	}
	

	/**
	 * 根据工作流PS-DM 列出其所有节点
	 */
	public Page all_node_by_ps_dm(HttpServletRequest request, Page page,WfNode pro){
		String psDm = pro.getPsDm();
		Page p = service.all_node_by_ps_dm(page, psDm);
		return p;
		/*String ps_dm=getParameter("ps_dm");
		String ls_sql="";
		if ("".equals(ps_dm)){
			ls_sql="select * from wf_node where 1=2";
		}else{
			ls_sql="select * from wf_node where ps_dm='"+ps_dm+"' order by nd_id desc";
		}
//		System.out.println(ls_sql);
		JSONObject jsonObj = commonPageQuery(ls_sql,WfNode.class);
		printJsonStr(jsonObj.toString());*/
	}

}
