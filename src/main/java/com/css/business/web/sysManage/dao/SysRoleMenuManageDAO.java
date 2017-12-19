package com.css.business.web.sysManage.dao;

import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.sysManage.bean.SysMenuVO;
import com.css.business.web.sysManage.bean.SysRoleMenu;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("sysRoleMenuManageDAO")
public class SysRoleMenuManageDAO extends BaseEntityDaoImpl<SysRoleMenu>  {

	//根据角色ID查询角色权限表的数据
	public List<SysRoleMenu> QueryLists(Integer id){
	String hql = "FROM SysRoleMenu where 1=1 ";
	
	if (id != null) {
		hql += " AND roleId=?";
	}
	@SuppressWarnings("unchecked")
	List<SysRoleMenu> tempList = this.getHibernateTemplate().find(hql,id);
	return tempList;
	}

	//根据roleId删除数据
	public void deletRoleResourceByRoleId(int roleId) {
		String hql = "delete FROM SysRoleMenu where roleId=?";
		this.getSessionFactory().getCurrentSession().createQuery(hql)
		.setParameter(0, roleId)
		.executeUpdate();
	}

	//保存对象
	public void saveSysRoleMenu(SysRoleMenu SysRoleMenu) {
		this.getHibernateTemplate().save(SysRoleMenu);
	}
	
	/**
	 * @TODO: 菜单管理，查询菜单
	 * @author: zhaichunlei
	 & @DATE : 2016年12月16日
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly=true)
	public List<SysMenuVO> queryRoleMenu(SysMenuVO e)throws Exception{
		String pcode = e.getPcode();
		if(pcode == null || pcode.length() == 0){
			pcode = "-1";
			e.setPcode("-1");
		}
		
		List<SysMenuVO> lst = queryRoleMenu_sub(pcode,e.getRoleid());
		return lst;
	}
	
	/**
	 * @TODO: 菜单管理，查询菜单
	 * @author: zhaichunlei
	 & @DATE : 2016年12月16日
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly=true)
	@SuppressWarnings("unchecked")
	public List<SysMenuVO> queryRoleMenu_sub(String pcode,Integer roleId)throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("select m.id,m.create_by createby,m.create_date createdate,m.image,m.is_leaf isleaf,");
		sb.append("m.is_single issingle,m.level,m.menu_type menutype,m.name,m.pcode,m.sort,m.url,m.code, ");
		sb.append("(select name from sys_menu m2 where m2.code=m.pcode) pname, ");
		if(roleId == null){
			sb.append("null roleid ");
		}
		else{
			sb.append("r.role_id roleid ");
		}
		sb.append("from sys_menu m ");
		if(roleId != null){
			sb.append(" left join sys_role_menu r on m.id=r.menu_id and r.role_id="+roleId+" ");
		}
		else{
			
		}
		sb.append("where 1=1 ");
		
		if(pcode != null && pcode.length() > 0){
			sb.append(" and m.pcode='"+pcode+"' ");
		}
		sb.append(" order by m.sort asc");//m.code asc,
		List<SysMenuVO> lst = this.createSQLQuery(sb.toString()).setResultTransformer(Transformers.aliasToBean(SysMenuVO.class)).list();
		for(SysMenuVO vo : lst){
			String code = vo.getCode();
			List<SysMenuVO> list = queryRoleMenu_sub(code,roleId);
			vo.setChildren(list);
		}
		return lst;
	}
}
