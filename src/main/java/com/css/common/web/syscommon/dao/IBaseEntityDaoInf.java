package com.css.common.web.syscommon.dao;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.css.common.web.syscommon.bean.BaseEntity;
import com.css.common.web.syscommon.bean.EntityField;
import com.css.common.web.syscommon.dao.support.Page;

public abstract interface IBaseEntityDaoInf<T extends BaseEntity>
{
  public abstract T get(Serializable paramSerializable);
  
  @SuppressWarnings("rawtypes")
public abstract Object get(Class paramClass, Serializable paramSerializable);
  
  public abstract List<T> getAll();
  
  public abstract List<T> getAll(String paramString, boolean paramBoolean);
  
  //public abstract Criteria createCriteria(Criterion... paramVarArgs);
  
  //public abstract Criteria createCriteria(String paramString, boolean paramBoolean, Criterion... paramVarArgs);
  
  public abstract List<T> findBy(String paramString, Object paramObject);
  
  public abstract List<T> findBy(String paramString1, Object paramObject, String paramString2, boolean paramBoolean);
  
  public abstract T findUniqueBy(String paramString, Object paramObject);
  
  public abstract void save(BaseEntity paramBaseEntity);
  
  public abstract void remove(BaseEntity paramBaseEntity);
  
  public abstract void removeById(Serializable paramSerializable);
  
  @SuppressWarnings("rawtypes")
public abstract void removeById(Class paramClass, Serializable paramSerializable);
  
  @SuppressWarnings("rawtypes")
public abstract String getIdName(Class paramClass);
  
  public abstract Page pageQuery(String paramString, int paramInt1, int paramInt2, Object... paramVarArgs);
  
  public abstract Page pageSQLQuery(String paramString, int paramInt1, int paramInt2, Object... paramVarArgs);
  
  @SuppressWarnings("rawtypes")
public abstract Page pageSQLQuery(String sql, int pageNo, int pageSize,Class clazz,Object... values);
  
  public abstract List<T> listQuery(String paramString, Object... paramVarArgs);
  public abstract Page pageQuery(HttpServletRequest request,Page paramPage, T entity)throws Exception;
 
  public abstract List<T> listQuery(HttpServletRequest request,T entity,String token)throws Exception;
  
  List<EntityField> getMetaField(String tableName) throws Exception;
  
  public void removeByCon(BaseEntity object) throws Exception;
  public void updateByCon(BaseEntity object,boolean nullIsUpdate) throws Exception;

}






