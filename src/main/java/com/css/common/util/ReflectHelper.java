package com.css.common.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Transient;

import com.css.business.web.sysManage.bean.Blog;
import com.css.common.annotation.TableNameAnnotiation;

/**
 * 
 * TODO:反射工具类
 * 
 * @author huanghao 2012-3-25 下午7:23:30
 * 
 */
public class ReflectHelper {
	/**
	 * 获取obj对象fieldName的Field
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static synchronized Field getFieldByFieldName(Object obj,
			String fieldName) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	public static synchronized String getFieldType(Object obj, String fieldName) {
		Field field = getFieldByFieldName(obj, fieldName);
		return field.getType().getName();
	}

	/**
	 * 获取obj对象fieldName的属性值
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static synchronized Object getValueByFieldName(Object obj,
			String fieldName) throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = getFieldByFieldName(obj, fieldName);
		Object value = null;
		if (field != null) {
			if (field.isAccessible()) {
				value = field.get(obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
	}

	/**
	 * 高级查询，根据自定义注解获取查询条件
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @author leitao
	 * @date 2016-12-1 下午5:07:47
	 */
	public static synchronized HashMap<String, Object> getAnnotationValueByFieldName(
			Object obj, String fieldName) throws SecurityException,
			NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {

		Field field = getFieldByFieldName(obj, fieldName);
		HashMap<String, Object> values = new HashMap<String, Object>();
		if (field != null) {
			// TableNameAnnotiation自定义注解类
			TableNameAnnotiation tableNameAnnotiation = field
					.getAnnotation(TableNameAnnotiation.class);
			if (tableNameAnnotiation == null) {
				return null;
			}
			values.put("fieldName", tableNameAnnotiation.fieldName());
			values.put("fieldType", tableNameAnnotiation.fieldType());
			values.put("isTree", tableNameAnnotiation.isTree());
			values.put("treeActionName", tableNameAnnotiation.treeActionName());
			values.put("treeMethod", tableNameAnnotiation.treeMethod());
			values.put("queryName", tableNameAnnotiation.queryName());
			values.put("queryValue", tableNameAnnotiation.queryValue());

		}
		return values;
	}

	/**
	 * 设置obj对象fieldName的属性值
	 * 
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static synchronized void setValueByFieldName(Object obj,
			String fieldName, Object value) throws SecurityException,
			NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}

	/**
	 * 取对象中有值的属性，
	 * 
	 * @param t
	 * @param isRemoveNullAttar
	 *            true:不返回空值属性，false:返回空值属性
	 * @return map key:属性，value:属性值
	 * @throws Exception
	 */
	public static synchronized Map<String, Object> getAllFileds(Object t,
			boolean isRemoveNullAttar) throws Exception {
		Map<String, Object> fileds = new LinkedHashMap<String, Object>();
		Field[] fields = t.getClass().getDeclaredFields();
		for (Field f : fields) {
			if (f.getAnnotation(Transient.class) != null) {
				continue;
			}
			String name = f.getName();
			Object value = getValueByFieldName(t, name);
			if (isRemoveNullAttar) {
				if (value != null && !"".equals(value)) {
					fileds.put(name, value);
				}
			} else {
				fileds.put(name, value);
			}
		}
		return fileds;
	}

	/**
	 * 取对象中有值的属性，包括Transient
	 * 
	 * @param t
	 * @param isRemoveNullAttar
	 *            true:不返回空值属性，false:返回空值属性
	 * @return map key:属性，value:属性值
	 * @throws Exception
	 */
	public static synchronized Map<String, Object> getAllFiledsIncludeTransient(
			Object t, boolean isRemoveNullAttar) throws Exception {
		Map<String, Object> fileds = new LinkedHashMap<String, Object>();
		Field[] fields = t.getClass().getDeclaredFields();
		for (Field f : fields) {
			// if(f.getAnnotation(Transient.class) != null){
			// continue;
			// }
			String name = f.getName();
			Object value = getValueByFieldName(t, name);
			if (isRemoveNullAttar) {
				if (value != null && !"".equals(value)) {
					fileds.put(name, value);
				}
			} else {
				fileds.put(name, value);
			}
		}
		return fileds;
	}

	/**
	 * 设置对象对空的属性
	 * 
	 * @param t
	 * @param isRemoveNullAttar
	 * @return
	 * @throws Exception
	 */
	public static synchronized Object checkValue(Object t,
			boolean isRemoveNullAttar, Object va) throws Exception {
		Field[] fields = t.getClass().getDeclaredFields();
		for (Field f : fields) {
			if (f.getAnnotation(Transient.class) != null) {
				continue;
			}
			String name = f.getName();
			Object value = getValueByFieldName(t, name);
			if (isRemoveNullAttar) {
				if (f.getGenericType().toString()
						.equals("class java.lang.String")) {
					if (value == null || "".equals(value)) {
						setValueByFieldName(t, name, va.toString());
					}
				} else if (f.getGenericType().toString()
						.equals("class java.math.BigDecimal")) {
					if (value == null || "".equals(value)) {
						BigDecimal big = new BigDecimal(0);
						setValueByFieldName(t, name, big);
					}
				}
			}
		}
		return t;
	}

	/**
	 * 对象是否有此属性
	 * 
	 * @param obj
	 * @param filed
	 * @return
	 * @throws Exception
	 */
	public static synchronized boolean isContainAttr(Object obj, String filed)
			throws Exception {
		return getAllFileds(obj, false).containsKey(filed);
	}

	/**
	 * 对象是否有此属性,包括Transient属性
	 * 
	 * @param obj
	 * @param filed
	 * @return
	 * @throws Exception
	 */
	public static synchronized boolean isContainAttrIncludeTransient(
			Object obj, String filed) throws Exception {
		return getAllFiledsIncludeTransient(obj, false).containsKey(filed);
	}

	public static void main(String args[]) {
		Blog b = new Blog();
		b.setOwn("ff");
		b.setBlogContent("333");
		try {
			Map<String, Object> map = getAllFileds(b, false);
			Iterator<Entry<String, Object>> ites = map.entrySet().iterator();
			while (ites.hasNext()) {
				Entry<String, Object> ent = ites.next();
				String key = ent.getKey();
				Object val = ent.getValue();
				System.out.println(key + " : " + val);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
