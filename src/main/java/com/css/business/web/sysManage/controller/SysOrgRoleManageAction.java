package com.css.business.web.sysManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.sysManage.bean.SysOrgRole;
import com.css.business.web.sysManage.service.SysOrgRoleManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
@Controller
@RequestMapping("/sysOrgRoleManageAction")
public class SysOrgRoleManageAction extends BaseSpringSupportAction<SysOrgRole, SysOrgRoleManageService> {
	
	//@Autowired
	private SysOrgRoleManageService service;
	
	@Override
	public SysOrgRoleManageService getEntityManager() {
		return service;
	}

	public SysOrgRoleManageService getService() {
		return service;
	}

	@Resource(name="sysOrgRoleManageService")
	public void setService(SysOrgRoleManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysOrgRoleEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysOrgRoleForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysOrgRoleList";
	}
	
	@RequestMapping({ "getDataGridPage" })
	@ResponseBody
	public Page getDataGridPage(HttpServletRequest request, Integer id,Page param){
		request.setAttribute("id", id);
		Page page=null;
		try {
			page = service.getOrmRoleDataPage(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	
	@RequestMapping({"toSave"})
	@ResponseBody
	public String toSave(HttpServletRequest request, Integer id,String ORG_ID,String ROLE_ID ){
		String msg="";
		try {
			//如果id为空执行添加，否则执行修改
			if(null!=id){
				service.updateOrgRoleById(id,ORG_ID,ROLE_ID);
				msg="修改成功";
			}else{
				SysOrgRole sysOrgRole= null;
				if(sysOrgRole==null){
					sysOrgRole=new SysOrgRole();
					if(null!=ORG_ID&&!"".equals(ORG_ID)){
						sysOrgRole.setOrgId(Integer.parseInt(ORG_ID));
					}else{
						msg="结构Id不能为空";
					}
					if(null!=ROLE_ID&&!"".equals(ROLE_ID)){
						sysOrgRole.setRoleId(Integer.parseInt(ROLE_ID));
					}else{
						msg="角色Id不能为空";
					}
				}
				service.save(sysOrgRole);
				msg="保存成功";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

}
