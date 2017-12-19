package com.css.common.web.syscommon.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.commcon.util.SessionUtils;
import com.css.common.annotation.Auth;
import com.css.common.annotation.Remark;
import com.css.common.annotation.RemarkClass;
import com.css.common.util.Constant;
import com.css.common.util.ExcelExportUtil;
import com.css.common.util.GenericsUtils;
import com.css.common.util.PropertyFileUtil;
import com.css.common.util.ReflectHelper;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.bean.AdvancedQueryTreeVO;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.css.common.web.syscommon.bean.EntityField;
import com.css.common.web.syscommon.bean.Sys_attachment;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.IBaseEntityManageInf;

/**
 * 
 *TODO 附件管理ACTION
 * @author huangaho
 *2015-4-24下午6:22:19
 * @param <T>
 * @param <M>
 */
@SuppressWarnings("rawtypes")
public abstract class BaseSpringSupportAction<T extends BaseEntity, M extends IBaseEntityManageInf>
		implements InitializingBean {
	public final transient Logger log = LoggerFactory.getLogger(BaseSpringSupportAction.class);
	//private M entityManager;
	private Class<T> entityClass;
	//private  String entityTableName = "";
	public abstract M getEntityManager();

	/**
	 * 添加，更新功能
	 * @param request
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	//@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	@Remark(toDo="添加，更新功能")
	@RequestMapping({ "save" })
	@ResponseBody
	public HashMap<String, Object> save(HttpServletRequest request, T entity)
			throws Exception {
		this.log.debug("开始执行保存方法");
		Integer id = entity.getId();
		String msg = "添加成功";
		if(id != null){
			msg = "编辑成功";
		}
		//leitao
		if(ReflectHelper.isContainAttr(entity, "dataYear")){
			Object obj = ReflectHelper.getValueByFieldName(entity,"dataYear");
			if(obj == null || obj.toString().length() == 0){
				ReflectHelper.setValueByFieldName(entity, "dataYear", Integer.parseInt(Constant.CONFIG.get("DATA_YEAR").getValue()));
			}
		}
		if(ReflectHelper.isContainAttr(entity, "createDate")){
			//当创建日期为空时才添加默认时间 zhaichunlei 
			//ReflectHelper.setValueByFieldName(entity, "createDate", new Date());
			Object obj = ReflectHelper.getValueByFieldName(entity,"createDate");
			if(obj == null || obj.toString().length() == 0){
				ReflectHelper.setValueByFieldName(entity, "createDate", new Date());
			}
		}
		if(ReflectHelper.isContainAttr(entity, "createBy")){
			//ReflectHelper.setValueByFieldName(entity, "createBy", SessionUtils.getUser(request).getAccount());
			//修改为，传递的有值时，就不自动填充.   zhaichunlei
			Object obj = ReflectHelper.getValueByFieldName(entity,"createBy");
			if(obj == null || obj.toString().length() == 0){
				ReflectHelper.setValueByFieldName(entity, "createBy", SessionUtils.getUser(request).getName());
			}
		}
		this.getEntityManager().save(entity);
		this.log.debug("保存成功");
		return JsonWrapper.successWrapper(entity,msg);
	}

	/**
	 * 根据ID查询
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	//@Remark(toDo="查询")
	@RequestMapping({ "detail" })
	@ResponseBody
	//@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	public HashMap<String, Object> detail(HttpServletRequest request,
			@RequestParam("id") Integer id) throws Exception {
		@SuppressWarnings("unchecked")
		T entity = (T) this.getEntityManager().get(id);
		//List list = this.getEntityManager().queryAttachment(id);
		HashMap<String, Object> map = JsonWrapper.successWrapper(entity);
		//map.put("attachment", list);
		return map;
	}

	@Remark(toDo="删除数据")
	@RequestMapping({ "delete" })
	@ResponseBody
	//@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	public HashMap<String, Object> delete(String ids) throws Exception {
			if (StringUtil.isEmpty(ids)) {
				this.log.error("要删除的ID集合为空");
				return JsonWrapper.failureWrapperMsg("要删除的目标对象ID为空！");
			}
			this.getEntityManager().deleteBusiness(ids.split(","));
			HashMap<String, Object> map = JsonWrapper.successWrapper(ids,"删除成功");
			return map;
	}
	
	//@Remark(toDo="查询所有")
	@RequestMapping({ "list" })
	@ResponseBody
	//@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	public HashMap<String, Object> list() throws Exception {
		List list = this.getEntityManager().queryAll();
		return JsonWrapper.successWrapper(list);
	}
	
	/**
	 * 
	*@param request
	*@param entity
	*@param token app手机端验证登录令牌
	*@return
	*@throws Exception 
	*@author leitao 
	*@date 2016-3-1 下午12:55:14
	 */
	@SuppressWarnings({ "unchecked"})
	//@Remark(toDo="条件查询不分页")
	@RequestMapping({ "listByEntity" })
	@ResponseBody
	//@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	public HashMap<String, Object> listByEntity(HttpServletRequest request,T entity,String token) throws Exception {
		List list = this.getEntityManager().listQuery(request,entity,token);
		return JsonWrapper.successWrapper(list);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping({ "page" })
	@ResponseBody
	//@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	public HashMap<String, Object> page(HttpServletRequest request,Page paramPage,T entity) throws Exception {
		paramPage = this.getEntityManager().pageQuery(request,paramPage, entity);
		return JsonWrapper.successWrapper(paramPage);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping({ "dataGridPage" })
	@ResponseBody
	//@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	public Page dataGridPage(HttpServletRequest request,Page paramPage,T entity) throws Exception {
		long start = System.currentTimeMillis();
		paramPage = this.getEntityManager().pageQuery(request,paramPage, entity);
		long end = System.currentTimeMillis();
		String usertime = (end - start)/1000+"."+(end - start)%1000+"秒";
		paramPage.setTime(usertime);
		return paramPage;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping({ "attachment" })
	@ResponseBody
	//@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	public HashMap<String, Object> attachment(HttpServletRequest request,String id,String type) throws Exception {
		List<Sys_attachment> list = this.getEntityManager().queryAttachment(id);
//		String ctxDir = request.getSession().getServletContext().getRealPath(File.separator+"");
		PropertyFileUtil pfu = new PropertyFileUtil();
		String ctxDir = pfu.getProp("image_path");
		List<Sys_attachment> temp = new ArrayList<Sys_attachment>();
		for(Sys_attachment att : list){
			String uploadurl = att.getUploadurl();
				File file = new File(ctxDir+uploadurl);
				if(file.exists()){
					if(Constant.IMG.equals(type) && Constant.IMG.equals(att.getAttachType())){
						 temp.add(att);
						 BufferedImage sourceImg =ImageIO.read(new FileInputStream(file));
						 if(sourceImg == null){
							 continue;
						 }
						 att.setImgHeight(sourceImg.getHeight());
						 att.setImgWidth(sourceImg.getWidth());
					}else if(Constant.DOC.equals(type) && Constant.DOC.equals(att.getAttachType())){
						 temp.add(att);
					}else if(Constant.VIDEO.equals(type) && Constant.VIDEO.equals(att.getAttachType())){
						 temp.add(att);
					}else if(StringUtil.isEmpty(type)){
						temp.add(att);
					}
				}
		}
		return JsonWrapper.successWrapper(temp);
	}
	
	/**
	 * 批量删除业务表附件
	 * @param ids 多个附件用逗号","分隔
	 * @return
	 */
	@Remark(toDo="批量删除业务表附件")
	@RequestMapping({ "deleteAttachment" })
	@ResponseBody
	//@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	public HashMap<String, Object> deleteAttachment(String ids){
		this.getEntityManager().deleteAttachment(ids);
		return JsonWrapper.successWrapper(ids);
	}
	
	@Remark(toDo="根据条件删除数据")
	@RequestMapping({ "removeByCon" })
	@ResponseBody
	public  HashMap<String, Object> removeByCon(T object) throws Exception{
		this.getEntityManager().removeByCon(object);
		return JsonWrapper.successWrapper(object);
	}

	@Remark(toDo="根据条件更新数据")
	@RequestMapping({ "updateByCon" })
	@ResponseBody
	public  HashMap<String, Object> updateByCon(HttpServletRequest request,T object, boolean nullIsUpdate)
			throws Exception{
		this.getEntityManager().updateByCon(object, nullIsUpdate);
		//added by by zhaichunlei. 
		Object o = this.getEntityManager().get(object.getId());
		return JsonWrapper.successWrapper(o,"更新成功");
	}
	
	@SuppressWarnings("unchecked")
	@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	public void afterPropertiesSet() {
		this.entityClass = GenericsUtils.getSuperClassGenricType(getClass(), 0);
		//Table annotation = (Table)entityClass.getAnnotation(Table.class);
		//entityTableName = annotation.name();
		
//		List<Field> fields = BeanUtils.getFieldsByType(this,GenericsUtils.getSuperClassGenricType(getClass(), 1));
//		Assert.isTrue(fields.size() == 1,
//				"subclass's has not only one entity manager property.");
//		try {
//			this.entityManager = ((M) BeanUtils.forceGetProperty(this,
//					((Field) fields.get(0)).getName()));
//			Assert.notNull(this.entityManager,
//					"subclass not inject manager to action sucessful.");
//		} catch (Exception e) {
//			ReflectionUtils.handleReflectionException(e);
//		}
	}

	
	//通过hibernate注解获得元数据
	@RequestMapping("/toAdvQueryPageEx")
	public String toAdvQueryPage(HttpServletRequest request) throws Exception{
		List<EntityField> list = new ArrayList<EntityField>();
		if (this.entityClass != null) {
			list = this.getEntityFieldList();
			
		}
		request.setAttribute("entityField", list);
		return "advQuery";
	}

	//根据表名获得元数据
	@SuppressWarnings("unchecked")
	@RequestMapping("/toAdvQueryPage")
	public String toAdvQueryPage(HttpServletRequest request, String tableName) throws Exception{
		//通过元数据表获得元数据
		if(StringUtils.isBlank(tableName)){
			Table annotation = (Table)entityClass.getAnnotation(Table.class);
			tableName = annotation.name();
		}
		List<EntityField> list = this.getEntityManager().getMetaField(tableName);
		if(list == null || list.size() < 1){
			list = getEntityFieldList();
			//throw new Exception("为找到数据库元数据定义");
		}
		request.setAttribute("entityField", list);
		return "advQuery";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping({ "advQuery" })
	@ResponseBody
	public Page advQuery(Page paramPage, String condition) throws Exception{
		Table annotation = (Table)entityClass.getAnnotation(Table.class);
		String tableName = annotation.name();
		paramPage = this.getEntityManager().advQuery(paramPage, tableName, condition, entityClass);
		return paramPage;
	}
	
	/**
	 * 公用导出excel方法   
	 * 注意：未处理大数据量查询和导入！
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping({ "exportExcel" })
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,String filename) throws Exception{
		Table annotation = (Table)entityClass.getAnnotation(Table.class);
		String tableName = annotation.name();

		Map<String, String> headMap = new HashMap<String, String>();
		//从元数据表获取列名，如果没找到从注解获取列名
		List<EntityField> fieldList = getEntityManager().getMetaField(tableName);
		if(fieldList == null || fieldList.size() < 1){
			fieldList = this.getEntityFieldList();
		}
		for(EntityField e : fieldList){
			headMap.put(e.getCol(), e.getColName());
		}
		
		//文件夹
		File folder = new File(request.getRealPath("/") + "export");
		if(!folder.exists()){
			folder.mkdirs();
		}
		
		if(StringUtil.isEmpty(filename)){
			RemarkClass rc= entityClass.getAnnotation(RemarkClass.class);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date d = new Date();
			if(rc != null){
				filename = rc.toDo();
			}else{
				filename = "导出数据";
			}
			filename +=sdf.format(d);
		}
		
		//文件路径
		String filePath = folder.getPath() + "\\" + filename+ ".xls";
		
		List<Map<String, Object>> list = getEntityManager().getExportData(tableName);
		ExcelExportUtil.export(headMap, list, filePath, new String(filename.getBytes("GBK"), "ISO8859-1"),response);
	}

	//列名从hibernate注解获取
	private List<EntityField> getEntityFieldList(){
		List<EntityField> list = new ArrayList<EntityField>();
		Field[] fields = entityClass.getDeclaredFields();
		for (Field f : fields) {
			// 获取字段中包含fieldMeta的注解
			Column column = f.getAnnotation(Column.class);
			if (column != null) {
				String colCnName = column.columnDefinition();
				EntityField sf = new EntityField(column.name(), f,colCnName);
				sf.setTypeCode(f.getType().getName());
				list.add(sf);
			}
		}
		// 返回对象所表示的类或接口的所有可访问公共方法
		Method[] methods = entityClass.getMethods();
		for (Method m : methods) {
			Column column = m.getAnnotation(Column.class);
			if (column != null) {
				EntityField sf = new EntityField(column.name(), column.columnDefinition(), m.getReturnType());
				sf.setTypeCode(m.getReturnType().getName());
				list.add(sf);
			}
		}
		return list;
	}
	
	
	/**
	 * 高级查询生成树,数据为表中字段的中文名等等
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping({ "advancedQueryNodeTree" })
	@ResponseBody
	public HashMap<String, Object> advancedQueryNodeTree(HttpServletRequest request,T entity)  {
		List<AdvancedQueryTreeVO> list;
		try {
			list = this.getEntityManager().advancedQueryNodeTree(entity);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询树数据错误",e);
			return JsonWrapper.successWrapper("查询树数据错误");
		}
	}
	
	/**
	 * 跳转到高级查询页面(查询页面固定不变)
	*@param request
	*@param id
	*@return 
	*@author leitao 
	*@date 2016-8-8 下午4:56:40
	 */
	@RequestMapping({ "toAdvancedQueryPage" })
	public String toAdvancedQueryPage(HttpServletRequest request,String actionName,String frameNumber){
		request.setAttribute("actionName", actionName);
		//页面中引入查询页面的iframe序号，如果只有一个页面就是1，
		request.setAttribute("frameNumber", frameNumber);
		return "bManage/advancedQueryPage";
	}
	
	public String dowithNull(Object obj){
		return null==obj?"":obj.toString().trim();
	}
}
