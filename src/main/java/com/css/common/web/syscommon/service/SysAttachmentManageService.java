package com.css.common.web.syscommon.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.sysManage.dao.SysAttachmentManageDAO;
import com.css.common.web.syscommon.bean.Sys_attachment;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

/**
 * 
 *TODO 附件业务处理类
 * @author huangaho
 *2015-4-24下午6:07:14
 */
@Service("sysAttachmentManageService")
public class SysAttachmentManageService extends BaseEntityManageImpl<Sys_attachment, SysAttachmentManageDAO> {
	@Resource(name="sysAttachmentManageDAO")
	//@Autowired
	private SysAttachmentManageDAO dao;
	
	@Override
	public SysAttachmentManageDAO getEntityDaoInf() {
		return dao;
	}
	
}
