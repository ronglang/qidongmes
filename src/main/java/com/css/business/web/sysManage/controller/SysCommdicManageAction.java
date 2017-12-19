package com.css.business.web.sysManage.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.sysManage.bean.SysCommdic;
import com.css.business.web.sysManage.service.SysCommdicManageService;
import com.css.common.web.syscommon.bean.TreeVO;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
@Controller
@RequestMapping("/sysCommdicManageAction")
public class SysCommdicManageAction extends BaseSpringSupportAction<SysCommdic, SysCommdicManageService> {
	
	//@Autowired
	private SysCommdicManageService service;
	
	@Override
	public SysCommdicManageService getEntityManager() {
		return service;
	}

	public SysCommdicManageService getService() {
		return service;
	}

	@Resource(name="sysCommdicManageService")
	public void setService(SysCommdicManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id,String clas){
		request.setAttribute("id", id);
		
		if(id!=null){
			request.setAttribute("clas", this.getEntityManager().get(id).getClas());
			request.setAttribute("value", this.getEntityManager().get(id).getValue());
		}
		
		else if(clas!=null){
			request.setAttribute("clas", clas);
		}
		return "sysManage/sysCommdicEdit";	
		
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysCommdicForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysCommdicList";
		
	}
	
	/**
	 * 生成树
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "nodeTree" })
	@ResponseBody
	public HashMap<String, Object> nodeTree(HttpServletRequest request,String qcolName,String qcolValue)  {
		List<TreeVO> list;
		try {
			list = this.getEntityManager().getTree(request,qcolName,qcolValue);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询树数据错误",e);
			return JsonWrapper.successWrapper("查询树数据错误");
		}
	}
	
	/**
	 * 生成树
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "nodeTreeClas" })
	@ResponseBody
	public HashMap<String, Object> nodeTreeClas(HttpServletRequest request,String qcolClas,String qcolValue)  {
		List<TreeVO> list;
		try {
			list = this.getEntityManager().getTreeClas(request,qcolClas,qcolValue);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询树数据错误",e);
			return JsonWrapper.successWrapper("查询树数据错误");
		}
	}
	
	/**
	 * 生成树
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "nodeTreeName" })
	@ResponseBody
	public HashMap<String, Object> nodeTreeName(String qcolName,String qcolValue)  {
		List<TreeVO> list;
		try {
			list = this.getEntityManager().getTreeName(qcolName,qcolValue);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询树数据错误",e);
			return JsonWrapper.successWrapper("查询树数据错误");
		}
	}
	
	
	@RequestMapping({ "saveCommdic" })
	@ResponseBody
	public HashMap<String, Object> saveCommdic(HttpServletRequest request, 
			SysCommdic entity)  throws Exception {
		String msg = "添加成功";
		try {
			if( entity.getId()!=null){
				msg ="编辑成功";
			}
			service.saveCommdic(entity);
			return JsonWrapper.successWrapper(entity,msg);
		} catch (Exception e) {
			String error = null;
			if(entity.getId()!=null){
				this.log.error("编辑失败",e);
				error ="编辑失败";
			}else{
				this.log.error("添加失败",e);
				error ="添加失败";
			}
			return JsonWrapper.failureWrapperMsg(entity,error);
		}
	}
	
	
	/**
	 * 审核状态下拉框只显示通过和未通过
	 * @param request
	 * @param code
	 * @return
	 */
	@RequestMapping({ "nodeTreeForAudit" })
	@ResponseBody
	public HashMap<String, Object> nodeTreeForAudit(HttpServletRequest request){
		@SuppressWarnings("rawtypes")
		List list;
		try {
			list = this.getEntityManager().nodeTreeForAudit(request);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("获取审核状态树数据错误！");
		}
		
	}

}
