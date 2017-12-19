package com.css.business.web.sysManage.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.css.business.web.sysManage.bean.SysMenu;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.business.web.sysManage.bean.SysUserRole;
import com.css.commcon.util.SessionUtils;
import com.css.common.util.Md5Util;
import com.css.common.util.ReflectHelper;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;

@Repository("sysUserManageDAO")
@SuppressWarnings("all")
public class SysUserManageDAO extends BaseEntityDaoImpl<SysUser> {

	public void saveUserInfo(SysUser sysUser) {
		this.save(sysUser);
	}

	public void saveUserRole(SysUserRole sysUserRole) {
		this.save(sysUserRole);

	}
	
	public void upDateUser(SysUser sysUser) {
		this.getHibernateTemplate().update(sysUser);
	}

	public void resetPasswordById(Integer userId) {
		String basePwd = Md5Util.MD5("666666");// 默认密码

		SysUser user = this.get(userId);
		user.setPassword(basePwd);
		// 更新对象信息
		this.getHibernateTemplate().update(user);
	}
	
	public SysUser getUserByAccount(String account){
		String hql = "from SysUser u where u.account = ?";
		List<SysUser> list = getHibernateTemplate().find(hql, account);
		return list.size() > 0 ? list.get(0) : null;
		
	}
	
	
	public SysUser getUserByAccount(Integer id, String account){
		String hql = "from SysUser u where u.account = ? and u.person_id <> ?";
		List<SysUser> list = getHibernateTemplate().find(hql, account, id);
		return list.size() > 0 ? list.get(0) : null;
		
	}
	
	public SysUser getUserByPersonId(Integer pid){
		String hql = "from SysUser u where  u.person_id = ?";
		List<SysUser> list = getHibernateTemplate().find(hql, pid);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public Page pageQuery(HttpServletRequest request, Page paramPage,
			SysUser entity) throws Exception {
		Map<String, Object> map = ReflectHelper.getAllFileds(entity, true);
		Iterator<Entry<String, Object>> ites = map.entrySet().iterator();
		String classname = this.entityClass.getName();
		StringBuilder hql = new StringBuilder("from " + classname
				+ " where 1=1 ");
		List<Object> vals = new ArrayList<Object>();
		while (ites.hasNext()) {
			Entry<String, Object> ent = ites.next();
			String key = ent.getKey();
			Object val = ent.getValue();
			if (val == null || StringUtil.isEmpty(val.toString())) {
				continue;
			}
			hql.append(" and ").append(key);
			if (val instanceof String) {
				hql.append(" like ?");
				vals.add("%"+val + "%");
			} else {
				hql.append(" = ?");
				vals.add(val);
			}
		}
		SysUser user = SessionUtils.getUser(request);
		if(!user.isAdmin()){
			switch(user.getUserType()){
			case "C1060002":
			case "C1060003":
			case "C1060004":
				hql.append(" and areaCode like ?");
				vals.add(user.getAreaCode());
				break;
			default:
				return paramPage;
			}
		}
		paramPage = this.pageQuery(hql.toString(), paramPage.getPageNo(),
				paramPage.getPagesize(), vals.toArray());
		return paramPage;
	}

	@Override
	public List<SysUser> listQuery(HttpServletRequest request, SysUser entity,String token)
			throws Exception {
		return super.listQuery(request, entity,token);
	}
	
	public List<SysMenu> findMenuByPcode(String pcode){
		String sql = "select * from sys_menu where pcode = '" + pcode + "'";
		List<SysMenu>  men =  this.createSQLQuery(sql, SysMenu.class).list();
		return men;
	}
	/*
	 * 更新账号状态
	 */
	public void updateUserStatusByID(Integer userId,String status) {
		SysUser user = this.get(userId);
		user.setStatus(status);
		//更新对象信息
		this.getHibernateTemplate().update(user);
	}
}
