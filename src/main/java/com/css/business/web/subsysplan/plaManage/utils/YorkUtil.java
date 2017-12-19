package com.css.business.web.subsysplan.plaManage.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YorkUtil {

	public static void main(String[] args) {
//		List<Object> list = new ArrayList<Object>();
//		for (int i = 0; i < 100; i++) {
//			Object obj = new String[] { i + "", "name" + i, i + "" };
//			list.add(obj);
//		}
//		String sql = " select id as id ,name as name ,count as count from ";
//		System.out.println(parse(list, Vo.class, sql));
	}
	
	public static  <T> List<T>  parse(List<?> list,Class<T> cla,String sql){
		Map<String,Integer> map = new HashMap<String,Integer>();
		List<T> resultList = new ArrayList<T>();
		String value = sql.split(" from ")[0];
		String value1 = sql.split(" FROM ")[0];
		String arr[] = null;
		if(value.length()<value1.length()){
			arr = value.split(",");
		}else{
			arr = value1.split(",");
		}
		String key = null;
		for (int i = 0; i < arr.length; i++) {
			if(i==7){
				System.out.println(arr[i]);
			}
			try{
				
				if(arr[i].split(" as ").length<2){
					key = arr[i].split(" AS ")[1].trim();
				}else{
					key = arr[i].split(" as ")[1].trim();
				}
				map.put(key, i);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		try {
			parse(list, map, resultList, cla);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public static <T> void parse(List<?> list, Map<String, Integer> map,
			List<T> result, Class<T> cla) throws Exception {
		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		for (Object obj : list) {
			Object[] objArr = (Object[]) obj;
			T t = cla.newInstance();
			for (String key : map.keySet()) {
				Field nameField = cla.getDeclaredField(key);
				String name = nameField.getName();
				name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1)  
	                    .toUpperCase());  
				String fldtype = nameField.getType().getSimpleName();  
				nameField.setAccessible(true);
				String value = objArr[map.get(key)].toString();
				Method m = null;
				if("String".equals(fldtype)){
					m = cla.getMethod("set" + name,String.class);
					m.invoke(t, value);
				}else if("Integer".equals(fldtype)){
					m = cla.getMethod("set" + name,Integer.class);
					m.invoke(t, Integer.valueOf(objArr[map.get(key)].toString()));
				}else if("Date".equals(fldtype)){
					m = cla.getMethod("set" + name,Date.class);
					m.invoke(t, sdf.parse(value));
				}else if("Long".equals(fldtype)){
					m = cla.getMethod("set" + name,Long.class);
					m.invoke(t, Long.valueOf(value));
				}else if("Boolean".endsWith(fldtype)){
					m = cla.getMethod("set" + name,Boolean.class);
					m.invoke(t, Boolean.valueOf(value));
				}
			}
			result.add(t);
		}
	}
}
