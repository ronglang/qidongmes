package com.css.business.web.sysManage.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.sysManage.bean.SysRole;
import com.css.business.web.sysManage.service.SysRoleManageService;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.bean.TreeVO;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;

/**
 * @TODO  : 角色管理 
 * @author: 翟春磊
 * @DATE  : 2017年6月23日
 */
@Controller
@RequestMapping("/sysRoleManageAction")
public class SysRoleManageAction extends BaseSpringSupportAction<SysRole, SysRoleManageService> {
	
	//@Autowired
	private SysRoleManageService service;
	
	@Override
	public SysRoleManageService getEntityManager() {
		return service;
	}

	public SysRoleManageService getService() {
		return service;
	}

	@Resource(name="sysRoleManageService")
	public void setService(SysRoleManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysRoleEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);		
		if(id!=null){
			String resourceList = service.findResourceById(id);//根据角色ID获取全部资源列表
			request.setAttribute("resourceList", resourceList);
		}	
		return "sysManage/sysRoleForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysRoleList";
	}
	
	
	//新建、更新角色以及角色权限
		@RequestMapping({ "saveRoleInfo" })
		@ResponseBody
		public HashMap<String, Object> saveRoleInfo(String roleName,String roleRemark,String datas,Integer params){
			try {
				//if(true) throw new RuntimeException("22");
				service.saveRoleInfo(roleName,roleRemark,datas,params);
				if(params==null){//新建角色
					return JsonWrapper.successWrapper("角色创建成功");
				}
				else{//更新角色
					return JsonWrapper.successWrapper("角色更新成功");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return JsonWrapper.failureWrapperMsg(e.getMessage());
			}
		}

		
		/**
		 * 生成树
		 * 
		 * @return
		 * @throws Exception
		 */
		@RequestMapping({ "nodeTree" })
		@ResponseBody
		public HashMap<String, Object> nodeTree(HttpServletRequest request,
				String qcolName, String qcolValue) {
			List<TreeVO> list;
			try {
				list = this.getEntityManager().getTree(qcolName, qcolValue);
				return JsonWrapper.successWrapper(list);
			} catch (Exception e) {
				this.log.error("查询树数据错误", e);
				return JsonWrapper.successWrapper("查询树数据错误");
			}
		}
		
		/**
		 * 生成树
		 * 
		 * @return
		 * @throws Exception
		 */
		@RequestMapping({ "nodeTrees" })
		@ResponseBody
		public HashMap<String, Object> nodeTrees(String qcolName, String qcolValue,String id) {
			List<TreeVO> list;
			try {
				list = this.getEntityManager().getTrees(qcolName, qcolValue,id);
				return JsonWrapper.successWrapper(list);
			} catch (Exception e) {
				this.log.error("查询树数据错误", e);
				return JsonWrapper.successWrapper("查询树数据错误");
			}
		}

		/**
		 * 删除
		 * @param request
		 * @param vo
		 * @return
		 * @throws Exception 
		 */
		@RequestMapping({ "delete2" })
		@ResponseBody
		public HashMap<String, Object> delete2(HttpServletRequest request, String ids) throws Exception{
			try {
				SysRole role = SessionUtils.getRole(request);
				service.delete2(role,ids);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//throw new Exception(e.getMessage());
				//return JsonWrapper.failureWrapper(e.getMessage());
				throw new RuntimeException(e.getMessage());
			}
			return JsonWrapper.successWrapper("删除成功！");
		}		
		
}
