package com.css.common.web.syscommon.service;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;

import com.css.common.web.syscommon.bean.AdvancedQueryTreeVO;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.css.common.web.syscommon.bean.EntityField;
import com.css.common.web.syscommon.bean.Sys_attachment;
import com.css.common.web.syscommon.controller.support.QueryCondition;
import com.css.common.web.syscommon.dao.IBaseEntityDaoInf;
import com.css.common.web.syscommon.dao.support.Page;

public abstract interface IBaseEntityManageInf<T extends BaseEntity, D extends IBaseEntityDaoInf<T>>
  extends InitializingBean
{
  public abstract T get(Serializable paramSerializable)
    throws Exception;
  
  public abstract List<T> queryAll()
    throws Exception;
  
  public abstract List<T> query(QueryCondition paramQueryCondition)
    throws Exception;
  
  public abstract Page pageQuery(Page paramPage, QueryCondition paramQueryCondition)
    throws Exception;
  
  public abstract void save(T paramT)
    throws Exception;
  
  public abstract void deleteBusiness(Serializable paramSerializable)
    throws Exception;
  
  public abstract void deleteBusiness(String[] paramArrayOfString)
    throws Exception;
  
  public abstract Page pageQuery(HttpServletRequest request,Page paramPage, T entity)throws Exception;
  
  public abstract List<Sys_attachment> queryAttachment(Serializable id);
  public abstract void deleteAttachment(Serializable id);
  public abstract List<T> listQuery(HttpServletRequest request,T entity,String token) throws Exception ;
  
  List<EntityField> getMetaField(String tableName) throws Exception;
  
  Page advQuery(Page paramPage, String tableName, String condition, Class<T> entityClass)throws Exception;
  
  @SuppressWarnings("rawtypes")
List getExportData(String tableName) throws Exception;
  public abstract void removeByCon(BaseEntity object) throws Exception;

	public abstract void updateByCon(BaseEntity object, boolean nullIsUpdate)
			throws Exception;
	
	// 高级查询加载树
	public List<AdvancedQueryTreeVO> advancedQueryNodeTree(T entity) throws Exception;
}






