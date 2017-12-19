package com.css.common.web.syscommon.service.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.sysManage.bean.SysCommdic;
import com.css.business.web.sysManage.dao.ExportDAO;
import com.css.business.web.sysManage.dao.SysAreaManageDAO;
import com.css.business.web.sysManage.dao.SysAttachmentManageDAO;
import com.css.business.web.sysManage.dao.SysCommdicManageDAO;
import com.css.business.web.sysManage.dao.SysConfigManageDAO;
import com.css.business.web.sysManage.dao.SysDictionaryManageDAO;
import com.css.business.web.sysManage.dao.SysOrgManageDAO;
import com.css.business.web.sysManage.dao.SysUserManageDAO;
import com.css.common.util.Constant;
import com.css.common.util.FileUtil;
import com.css.common.util.GenericsUtils;
import com.css.common.util.PropertyFileUtil;
import com.css.common.util.ReflectHelper;
import com.css.common.web.syscommon.bean.AdvancedQueryTreeVO;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.css.common.web.syscommon.bean.EntityField;
import com.css.common.web.syscommon.bean.Sys_attachment;
import com.css.common.web.syscommon.controller.support.QueryCondition;
import com.css.common.web.syscommon.dao.IBaseEntityDaoInf;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.IBaseEntityManageInf;


@Transactional
public abstract class BaseEntityManageImpl<T extends BaseEntity, D extends IBaseEntityDaoInf<T>>
		implements IBaseEntityManageInf<T, D> {
	public final transient Log log = LogFactory.getLog(getClass());
	private Class<T> entityClass;
	//private D entityDaoInf;
	private String className;
	
	@Resource(name="sysAttachmentManageDAO")
	private SysAttachmentManageDAO adao;
	
	@Resource(name="exportDAO")
	private ExportDAO edao;
	@Resource(name="sysDictionaryManageDAO")
	//@Autowired
	private SysDictionaryManageDAO dicdao;
	
	@Resource(name="sysOrgManageDAO")
	//@Autowired
	private SysOrgManageDAO orgdao;
	
	@Resource(name = "sysAreaManageDAO")
	// @Autowired
	private SysAreaManageDAO areadao;
	
	@Resource(name = "sysUserManageDAO")
	// @Autowired
	private SysUserManageDAO userdao;
	
	@Resource(name = "sysConfigManageDAO")
	// @Autowired
	private SysConfigManageDAO configdao;
	

	@Resource(name = "sysCommdicManageDAO")
	// @Autowired
	private SysCommdicManageDAO commdicdao;
	
	//@Resource(name = "projectCodeManageDAO")
	// @Autowired
	//private ProjectCodeManageDAO projectCodedao;
	
	public abstract D getEntityDaoInf();
	
	@SuppressWarnings("unchecked")
	public BaseEntityManageImpl(){
		this.entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	}
	
	@Transactional(readOnly = true)
	public T get(Serializable id) {
		return this.getEntityDaoInf().get(id);
	}


	@Transactional(readOnly = true)
	public List<T> queryAll() throws Exception {
		return this.getEntityDaoInf().getAll();
	}

	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public List<T> query(QueryCondition qc) throws Exception {
		if (qc == null) {
			this.log.error("查询条件queryCondition为空");
			throw new Exception("查询条件queryCondition为空");
		}
		Object[] ret = qc.getHql();
		return this.getEntityDaoInf().listQuery((String) ret[0],((List) ret[1]).toArray());
	}

	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public Page pageQuery(Page page, QueryCondition qc) throws Exception {
		if (qc == null) {
			this.log.error("查询条件queryCondition为空");
			throw new Exception("查询条件queryCondition为空");
		}
		Object[] ret = qc.getHql();
		return this.getEntityDaoInf().pageQuery((String) ret[0], page.getPageNo(),
				page.getPagesize(), ((List) ret[1]).toArray());
	}

	@Transactional(readOnly = false)
	public void save(T entity) throws Exception {
//		if (StringUtil.isEmpty(entity.getId())) {
//			entity.setId(null);
//		}
		this.getEntityDaoInf().save(entity);  
	}
	@Transactional(readOnly = false)
	@Override
	public void removeByCon(BaseEntity object) throws Exception {
		this.getEntityDaoInf().removeByCon(object);
	}
	@Transactional(readOnly = false)
	@Override
	public void updateByCon(BaseEntity object, boolean nullIsUpdate)
			throws Exception {
		this.getEntityDaoInf().updateByCon(object, nullIsUpdate);
	}
	
	@Transactional(readOnly = false)
	public void deleteBusiness(Serializable id) throws Exception {
		this.getEntityDaoInf().removeById(id);
		//throw new RuntimeException("测试事务");
	}

	@Transactional(readOnly = false)
	public void deleteBusiness(String[] ids) throws Exception {
		for (String id : ids) {
			if(id != null && id.length() > 0){
				deleteAttaFile(Integer.parseInt(id));//如果有附件就删除附件
				deleteBusiness(id);
			}
		}
	}

	@Transactional(readOnly = true)
	@Override
	public Page pageQuery(HttpServletRequest request,Page paramPage, T entity) throws Exception {
		paramPage = this.getEntityDaoInf().pageQuery(request,paramPage,entity);
		return paramPage;   
	}
	
	/**
	 * 删除附件的同时删除附件文件
	*@param bid 
	*@author leitao 
	*@date 2016-3-11 下午12:24:55
	 */
	@SuppressWarnings("unchecked")
	public void deleteAttaFile(Integer bid){
		String cname = this.entityClass.getName();
		cname = cname.substring(cname.lastIndexOf(".")+1,cname.length());
		String sql="SELECT * FROM sys_attachment WHERE bid=? AND classname=?";
		List<Sys_attachment> attaList=this.adao.createSQLQuery(sql, Sys_attachment.class, bid,cname).list();
		if(attaList.size()>0){
			PropertyFileUtil property = new PropertyFileUtil();
			//获取文件存放盘
			String pUrl=property.getProp("image_path");
			for(Sys_attachment atta:attaList){
				String url=atta.getUploadurl();
				FileUtil.deleteFile(pUrl+url);
			}
			this.adao.deleteByBid(bid, cname);
		}
	}
	/**
	 * 保存附件
	 * 其它保存业务类中调用本方法
	 * @param entity
	 */
	@Transactional(readOnly = false)
	protected void saveAttachment(Sys_attachment entity){
		String cname = this.entityClass.getName();
		cname = cname.substring(cname.lastIndexOf(".")+1,cname.length());
		entity.setClassname(cname);
		entity.setCreate_date(new Date());
		String f = entity.getUploadurlorigname();
		f=f.substring(f.lastIndexOf(".")+1,f.length());
		entity.setType(f);
		this.adao.save(entity);
	}
	@Transactional(readOnly = false)
	public void deleteAttachment(Serializable ids){
		if(ids != null){
			String []idarr = ids.toString().split(",");
			for(String i : idarr){
				this.adao.removeById(Integer.parseInt(i));
			}
		}
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Sys_attachment> queryAttachment(Serializable id){
		Object ids = null;
		if(id instanceof String){
			ids = Integer.parseInt(id.toString());
		}
		String cname = this.entityClass.getName();
		cname = cname.substring(cname.lastIndexOf(".")+1,cname.length());
		String hql = " from Sys_attachment where classname = '"+cname+"' and bid = ?";
		return this.adao.listQuery(hql, ids);
	}
	
	/**
	 * 查询该功能下所有附件信息
	 * @return
	 * @author leitao
	 */
	@Transactional(readOnly = true)
	public List<Sys_attachment> queryAttachment(){
		String cname = this.entityClass.getName();
		cname = cname.substring(cname.lastIndexOf(".")+1,cname.length());
		String hql = " from Sys_attachment where classname = '"+cname+"' ";
		return this.adao.listQuery(hql);
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public List<T> listQuery(HttpServletRequest request,T entity,String token) throws Exception {
		return this.getEntityDaoInf().listQuery(request,entity,token);
	}

	@SuppressWarnings("unchecked")
	public void afterPropertiesSet() {
		this.entityClass = GenericsUtils.getSuperClassGenricType(getClass(), 0);
		String cname = this.entityClass.getName();
		className = cname.substring(cname.lastIndexOf(".")+1,cname.length());
//		List<Field> fields = BeanUtils.getFieldsByType(this,GenericsUtils.getSuperClassGenricType(getClass(), 1));
//		Assert.isTrue(fields.size() == 1,
//				"subclass's has not only one entity manager property.");
//		try {
//			this.entityDaoInf = ((D) BeanUtils.forceGetProperty(this,
//					((Field) fields.get(0)).getName()));
//			Assert.notNull(this.entityDaoInf,
//					"subclass not inject manager to action sucessful.");
//		} catch (Exception e) {
//			ReflectionUtils.handleReflectionException(e);
//		}
		try {
			/*if(Constant.DICS.size() ==  0){
				List<SysDictionary> list = dicdao.listQuery(null);
				Constant.DIC_LIST = list;
				for(SysDictionary dic : list){
					//判断code是否为空，防止数据中有null值出现报空指针
					Constant.DICS.put(dic.getCode()==null?"":dic.getCode(), dic.getValue()==null?"":dic.getValue());
				}
				this.log.info("数据字典加载成功！");
			}*/
			
			/*if(Constant.ORG.size() == 0){
				List<SysOrg> orgs = orgdao.listQuery(null);
				Constant.ORG_LIST = orgs;
				for(SysOrg org : orgs){
					//判断code是否为空，防止数据中有null值出现报空指针
					Constant.ORG.put(org.getOrgCode()==null?"":org.getOrgCode(), org);
				}
				this.log.info("机构加载成功！");
			}*/
			
			/*if(Constant.AREA.size() == 0){
				List<SysArea> areas = areadao.listQuery(null);
				Constant.AREA_LIST = areas;
				for(SysArea a : areas){
					//判断code是否为空，防止数据中有null值出现报空指针
					Constant.AREA.put(a.getAreaCode()==null?"":a.getAreaCode(), a);
				}
				this.log.info("行政区划加载成功！");
			}*/
			/*if(Constant.CONFIG.size() == 0){
				List<SysConfig> configs = configdao.listQuery(null);
				for(SysConfig a : configs){
					//判断code是否为空，防止数据中有null值出现报空指针
					Constant.CONFIG.put(a.getItem()==null?"":a.getItem(), a);
				}
				this.log.info("系统配置加载成功！");
			}*/
			/*if(Constant.PROJECTNAME.size() == 0){
				List<ProjectCode> prjectNames = projectCodedao.listQuery(null);
				for(ProjectCode a : prjectNames){
					//判断code是否为空，防止数据中有null值出现报空指针
					Constant.PROJECTNAME.put(a.getCode()==null?"":a.getCode(), a);
				}
				this.log.info("专项名加载成功！");
			}*/
			
		/*	if(Constant.USER.size() == 0){
				List<SysUser> users=userdao.listQuery(null);
				for(SysUser u:users){
					Constant.USER.put(u.getAccount(), u);
				}
				Constant.USERLIST = users;
				this.log.info("用户信息加载成功！");
			}*/
			/*if(Constant.commdic.size() == 0){
				List<SysCommdic> prjectNames = commdicdao.listQuery(null);
				for(SysCommdic a : prjectNames){
					//判断code是否为空，防止数据中有null值出现报空指针
					Constant.commdic.put(a.getClas()==null?"":a.getClas(), a);
				}
				this.log.info("数据字典sys_commedic表加载成功！");
			}*/
		} catch (Exception e) {
			this.log.error("加载数据字典错误！",e);
		}
	}
	@Transactional(readOnly = true)
	public List<EntityField> getMetaField(String tableName) throws Exception{
		return this.getEntityDaoInf().getMetaField(tableName);
	}
	@Transactional(readOnly = true)
	public Page advQuery(Page paramPage, String tableName, String condition,  Class<T> entityClass)throws Exception{
		String sql = "select * from " + tableName + " where " + condition;
		return getEntityDaoInf().pageSQLQuery(sql, paramPage.getPageNo(), paramPage.getPagesize(), entityClass);

	}
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public List getExportData(String tableName) throws Exception{
		return edao.getExportData(tableName);
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public SysAttachmentManageDAO getAdao() {
		return adao;
	}

	public void setAdao(SysAttachmentManageDAO adao) {
		this.adao = adao;
	}
	
	/**
	 * 高级查询加载树
	 * @author leitao
	 */
	@Transactional(readOnly = true)
	public List<AdvancedQueryTreeVO> advancedQueryNodeTree(T entity)
			throws Exception {
		List<AdvancedQueryTreeVO> tree = new ArrayList<AdvancedQueryTreeVO>();
    	Field[] fields =  entity.getClass().getDeclaredFields();
    	for(Field f : fields){
    		if(f.getAnnotation(Transient.class) != null){
//    			continue;
    		}
    		String name = f.getName();
    		HashMap<String,Object> values=ReflectHelper.getAnnotationValueByFieldName(entity, name);
    		if(values!=null){
    			String fieldName=(String) values.get("fieldName");
    			String fieldType=(String) values.get("fieldType");
    			boolean isTree=(Boolean) values.get("isTree");
    			String treeActionName=(String) values.get("treeActionName");
    			String treeMethod=(String) values.get("treeMethod");
    			String queryName=(String) values.get("queryName");
    			String queryValue=(String) values.get("queryValue");
    			AdvancedQueryTreeVO vo = new AdvancedQueryTreeVO(fieldName,name,fieldType,isTree,treeActionName,treeMethod,queryName,queryValue);
    			tree.add(vo);
    		}
    	}
		
		return tree;
	}
	
	/**
	 * @TODO: 公共查询方法，返回唯一值。 要求查询的返回结果只有一个。
	 * @author: zhaichunlei
	 & @DATE : 2017年4月20日
	 * @param sql  完整查询sql语句
	 * @param values  查询参数
	 * @return
	 */
	public Object getUniqueResult(String sql,Object ... values){
		Query query = adao.createSQLQuery(sql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		Object o = query.uniqueResult();
		return o;
	}
	
	public void batchExeSql(String sql[]){
		for(String s : sql){
			adao.deleteBySql(s);
		}
	}
	
	public void exeSql(String sql){
		adao.deleteBySql(sql);
	}
}
