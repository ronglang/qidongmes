package com.css.business.web.sysManage.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.css.business.web.sysManage.bean.SysMenu;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;


/**
 * 
 * 菜单DAO   
 *  
 * @author aliang    
 * @version 1.0  
 * @created 2015年4月16日 下午4:40:27
 */
@Repository("resourceManageDAO")
@SuppressWarnings("all")
public class ResourceManageDAO extends BaseEntityDaoImpl<SysMenu> {
	

	/**
	 * 查询后台管理系统菜单
	 * List<SysMenu>
	 * 2015年9月1日 上午10:59:36
	 * @param userid
	 * @return
	 */
	public List<SysMenu> querySysMenuBySysUserId(Integer userid){
		String sql = "SELECT * FROM sys_menu r WHERE r.id IN (SELECT rr.menu_id FROM sys_role_menu rr WHERE rr.role_id IN (SELECT t.role_id FROM sys_user_role t WHERE t.user_id = ?)) AND r.menu_type = '1' ";
		Query query = this.createSQLQuery(sql, SysMenu.class, userid);
		List<SysMenu> lst = query.list();
		
		/*//菜单对应的按钮权限。用时再查 
		String code = null;
		for(SysMenu m : lst){
			code = m.getCode();
			String s = "SELECT * FROM SYS_MENU r WHERE r.id IN (SELECT rr.menu_id FROM sys_role_menu rr WHERE rr.ROLE_ID IN (SELECT t.ROLE_ID FROM SYS_USER_ROLE t WHERE t.USER_ID = ?)) AND r.menu_type = 2 and r.pcode=?";
			query =  this.createSQLQuery(sql, SysMenu.class, userid);
			m.setBtnLst(query.list());
		}*/
		return lst;
	}
	
	/**
	 * 查询后台管理系统菜单 按钮
	 * List<SysMenu>
	 * 2015年9月1日 上午10:59:36
	 * @param userid
	 * @return
	 */
	public List<SysMenu> querySysMenuBtnBySysUser(Integer userid){
		//增加按钮权限 zhaichunlei
		String s = "SELECT * FROM sys_menu r WHERE r.id IN (SELECT rr.menu_id FROM sys_role_menu rr WHERE rr.role_id IN (SELECT t.role_id FROM sys_user_role t WHERE t.user_id = ?)) AND r.menu_type = '2' ";
		Query query =  this.createSQLQuery(s, SysMenu.class, userid);
		List<SysMenu> lst = query.list();
		return lst;
	}
	
	/**
	 * 查询后台管理系统菜单 按钮,没有权限的按钮
	 * List<SysMenu>
	 * 2015年9月1日 上午10:59:36
	 * @param userid
	 * @return
	 */
	public List<SysMenu> querySysMenuBtnBySysUser_not(Integer userid){
		//增加按钮权限 zhaichunlei
		String s = "select * from sys_menu m left join sys_role_menu r on m.id=r.menu_id ";
		s +=   " and exists(select 1 from sys_user u,sys_user_role ur where u.id=ur.user_id and ur.role_id=r.role_id and u.id=?)";
		s += " where r.id is null and m.menu_type='2' ";
		Query query =  this.createSQLQuery(s, SysMenu.class, userid);
		List<SysMenu> lst = query.list();
		return lst;
	}

}
