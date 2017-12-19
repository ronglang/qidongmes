package com.css.business.web.subsysprojconstruct.projconManage.dao;

import org.springframework.stereotype.Repository;

import com.css.business.web.subsysprojconstruct.bean.ProjconMac;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("projconMacManageDAO")
public class ProjconMacManageDAO extends BaseEntityDaoImpl<ProjconMac>  {
	public String getUpdateProjconMacManageDao(ProjconMac projconMac) {
		this.getSession().update(projconMac);
		return "更新成功";
	}
}
