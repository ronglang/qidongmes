package com.css.common.web.syscommon.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.common.web.syscommon.bean.Sys_attachment;
import com.css.common.web.syscommon.service.SysAttachmentManageService;
/**
 * 
 *TODO 博客管理ACTION
 * @author huangaho
 *2015-4-24上午11:34:47
 */
@Controller
@RequestMapping("/sysAttachmentManageAction")
public class SysAttachmentManageAction extends BaseSpringSupportAction<Sys_attachment, SysAttachmentManageService> {
	
	//@Autowired
	private SysAttachmentManageService service;
	
	@Override
	public SysAttachmentManageService getEntityManager() {
		return service;
	}

	public SysAttachmentManageService getService() {
		return service;
	}

	@Resource(name="sysAttachmentManageService")
	public void setService(SysAttachmentManageService service) {
		this.service = service;
	}

}
