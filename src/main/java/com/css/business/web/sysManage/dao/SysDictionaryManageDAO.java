package com.css.business.web.sysManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.business.web.sysManage.bean.SysDictionary;
import com.css.business.web.sysManage.bean.SysMetaData;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;
/**
 * 
 *TODO 博客DAO数据库操作类
 * @author huangaho
 *2015-4-24上午11:35:47
 */
@Repository("sysDictionaryManageDAO")
public class SysDictionaryManageDAO extends BaseEntityDaoImpl<SysDictionary>  {

	//**********查询数据字典的父类***********//
	public Page findAllParent(Page paramPage) {
		String sql = "Select value from SYS_DICTIONARY where isleaf=0";
		paramPage = pageSQLQuery(sql, paramPage.getPageNo(),paramPage.getPagesize(),SysDictionary.class);
		return paramPage;
	}

	//**********查询数据字典父类对应的子类***********//
	public Page findAllChildren(Page paramPage, String PID) {
		String sql = "Select value from SYS_DICTIONARY where pid="+PID;
		paramPage = pageSQLQuery(sql, paramPage.getPageNo(),paramPage.getPagesize(),SysDictionary.class);
		return paramPage;
	}

	/**
	 * 返回表的列名，类型，描述，ID
	 * 仅支持SQL SERVER
	 * @param tabname
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SysMetaData> metaDataByTabname(String tabname){
		String sql = "SELECT a.colorder id,a.name,g.[value] AS remark,b.name as type "+
		" FROM syscolumns a left join systypes b on a.xtype=b.xusertype "+
		" inner join sysobjects d on a.id=d.id and d.xtype='U' and d.name <> 'dtproperties'"+
		" left join sys.extended_properties g on a.id=g.major_id AND a.colid = g.minor_id"+
		" WHERE d.[name]  = ? and g.name = 'MS_Description'"+
		" order by a.id,a.colorder";
		return this.createSQLQuery(sql, SysMetaData.class, tabname).list();
	}

	public SysDictionary findValueByCode(String code) {
		String sql = "SELECT t.* FROM SYS_DICTIONARY t WHERE t.CODE='"+code+"'";
		@SuppressWarnings("unchecked")
		List<SysDictionary> list  = this.createSQLQuery(sql, SysDictionary.class).list();
		return (list.size() > 0 ? list.get(0):null);
	}
	
	@SuppressWarnings("unchecked")
	public List<SysDictionary> findValueByPcode(String pcode) {
		String sql = "SELECT t.* FROM SYS_DICTIONARY t WHERE t.pid= ?";
		List<SysDictionary> list  = this.listSQLQuery(sql, com.css.business.web.sysManage.bean.SysDictionary.class, pcode);
		return list;
	}
	
	
	
}
