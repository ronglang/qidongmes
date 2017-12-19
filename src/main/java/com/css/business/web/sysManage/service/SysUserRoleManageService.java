package com.css.business.web.sysManage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.sysManage.bean.SysUserRole;
import com.css.business.web.sysManage.dao.SysUserRoleManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("sysUserRoleManageService")
public class SysUserRoleManageService extends
		BaseEntityManageImpl<SysUserRole, SysUserRoleManageDAO> {
	@Resource(name = "sysUserRoleManageDAO")
	// @Autowired
	private SysUserRoleManageDAO dao;

	@Override
	public SysUserRoleManageDAO getEntityDaoInf() {
		return dao;
	}

	public List<SysUserRole> QueryLists(Integer params) {
		return dao.QueryLists(params);

	}

	@Transactional(readOnly = false)
	public void deletUserRoleById(Integer id) {
		dao.deletUserRoleById(id);

	}

	@Transactional(readOnly = true)
	public List<SysUserRole> findEntityByUserId(Integer userId) {
		return dao.findEntityByUserId(userId);
	}

	@Transactional(readOnly = true)
	public List<String> queryById(String id) {
		List<SysUserRole> list = dao.QueryLists(Integer.parseInt(id));
		List<String> idlist = new ArrayList<String>();
		if (list.size() > 0) {
			for (SysUserRole sysUserRole : list) {
				idlist.add(sysUserRole.getRoleId().toString());
			}
			return idlist;
		}

		return null;
	}

}
