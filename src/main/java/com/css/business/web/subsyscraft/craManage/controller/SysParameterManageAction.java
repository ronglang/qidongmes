package com.css.business.web.subsyscraft.craManage.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsyscraft.bean.SysParameter;
import com.css.business.web.subsyscraft.craManage.service.SysParameterManageService;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
@Controller
@RequestMapping("/sysParameterManageAction")
public class SysParameterManageAction extends BaseSpringSupportAction<SysParameter,SysParameterManageService> {
	
	//@Autowired
	private SysParameterManageService service;
	
	@Override
	public SysParameterManageService getEntityManager() {
		return service;
	}

	public SysParameterManageService getService() {
		return service;
	}

	@Resource(name="sysParameterManageService")
	public void setService(SysParameterManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/wiredisc/sysParameterEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/wiredisc/sysParameterForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/wiredisc/sysParameterList";
	}
	
	
	@RequestMapping({"toDataGridPage"})
	@ResponseBody
	public Page toDataGridPage(HttpServletRequest requst,Page param,
			String rfidNumber,String wireDiscPgxh,String status,String useStatus){
		
		Page page = new Page();

		try {
			//page = service.getsysParameterPageList(param, rfidNumber, wireDiscPgxh,
				//	status, useStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return page;
	}
	

/**
	
	*保存和修改
	*/
	@RequestMapping({ "toSaveData" })
	@ResponseBody
	public HashMap<String,Object> toSaveData(HttpServletRequest request,String rfidNumber,String wireDiscPgxh,
			String status,String useStatus,Double externalDiameter,Double boreDiameter,String materialTexture,Integer id) {
		HashMap<String,Object>  map = new HashMap<>();
		try {
			if(null!=wireDiscPgxh&&!"".equals(wireDiscPgxh)){
				String[] split = wireDiscPgxh.split(",");
				wireDiscPgxh=split[0];
			}
			if(null!=status&&!"".equals(status)){
				String[] split = status.split(",");
				status=split[0];
			}
			if(null!=useStatus&&!"".equals(useStatus)){
				String[] split = useStatus.split(",");
				useStatus=split[0];
			}
			if(null!=materialTexture&&!"".equals(materialTexture)){
				String[] split = materialTexture.split(",");
				materialTexture=split[0];
			}
			//获取当前登录人
			 SysUser user = SessionUtils.getUser(request);
			if(null!=id&&!"".equals(id)){
				//修改操作
				//service.updateById(rfidNumber,wireDiscPgxh,
						// status, useStatus, externalDiameter, boreDiameter,materialTexture, id);
				
			}else{
			
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
	
}
