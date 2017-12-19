package com.css.business.web.sysManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.sysManage.bean.SysOrgRole;
import com.css.business.web.sysManage.dao.SysOrgRoleManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("sysOrgRoleManageService")
public class SysOrgRoleManageService extends BaseEntityManageImpl<SysOrgRole, SysOrgRoleManageDAO> {
	@Resource(name="sysOrgRoleManageDAO")
	//@Autowired
	private SysOrgRoleManageDAO dao;
	
	
	@Override
	public SysOrgRoleManageDAO getEntityDaoInf() {
		return dao;
	}

/**
 * 
 * 获取主页展示数据
 * */
	public Page getOrmRoleDataPage(Page param) {
		StringBuilder sql = new StringBuilder("select o from SysOrgRole o");
		Page p=dao.getOrmRoleDataPage(param,sql);
		return p;
	}
/**
 * 
 * 根据id修改
 * */
public void updateOrgRoleById(Integer id, String oRG_ID, String rOLE_ID) {
	StringBuilder sql =new StringBuilder();
	if(null!=oRG_ID&&null!=rOLE_ID){
	Integer	orgId=Integer.parseInt(oRG_ID);
	Integer roleId =Integer.parseInt(rOLE_ID);
		sql.append("update SysOrgRole o set o.orgId='"+orgId+"' , o.roleId='"+roleId+"' where o.id='"+id+"' ");
	}
	
	dao.updateOrgRoleById(sql);
}
	
	
}
