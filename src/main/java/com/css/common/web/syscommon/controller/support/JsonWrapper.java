package com.css.common.web.syscommon.controller.support;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonWrapper {
	private static Logger loger = Logger.getLogger(JsonWrapper.class);

	public static HashMap<String, Object> successWrapper(Object pojo) {
		HashMap<String, Object> wrapper = new HashMap<String,Object>();
		wrapper.put("success", Boolean.valueOf(true));
		wrapper.put("data", pojo);
		return wrapper;
	}

	public static HashMap<String, Object> successWrapper(Object pojo,String msg) {
		HashMap<String, Object> wrapper = new HashMap<String,Object>();
		wrapper.put("success", Boolean.valueOf(true));
		wrapper.put("data", pojo);
		wrapper.put("msg", msg);
		return wrapper;
	}
	
	public static HashMap<String, Object> failureWrapper(Object pojo) {
		HashMap<String, Object> wrapper = new HashMap<String,Object>();
		wrapper.put("success", Boolean.valueOf(false));
		wrapper.put("data", pojo);
		return wrapper;
	}

	public static HashMap<String, Object> failureWrapperMsg(String msg) {
		HashMap<String, Object> wrapper = new HashMap<String,Object>();
		wrapper.put("success", Boolean.valueOf(false));
		wrapper.put("msg", msg);
		return wrapper;
	}
	
	public static HashMap<String, Object> failureWrapperMsg(Object pojo,String msg) {
		HashMap<String, Object> wrapper = new HashMap<String,Object>();
		wrapper.put("success", Boolean.valueOf(false));
		wrapper.put("msg", msg);
		wrapper.put("data", pojo);
		return wrapper;
	}

	public static HashMap<String, Object> failureWrapper(Object... pojo) {
		return wrapper(false, pojo);
	}

	public static HashMap<String, Object> successWrapper(Object... pojo) {
		return wrapper(true, pojo);
	}

	public static HashMap<String, Object> wrapper(boolean success,
			Object... pojo) {
		HashMap<String, Object> wrapper = new HashMap<String,Object>();
		wrapper.put("success", Boolean.valueOf(success));
		int length = pojo.length;
		if (pojo.length % 2 == 1) {
			length--;
		}
		length /= 2;
		for (int i = 0; i < length; i++) {
			wrapper.put(String.valueOf(pojo[(i * 2)]), pojo[(i * 2 + 1)]);
		}
		return wrapper;
	}

	public static HashMap<String, Object> wrapperMap(boolean success,
			Map<String, Object> map) {
		HashMap<String, Object> wrapper = new HashMap<String,Object>();
		wrapper.put("success", Boolean.valueOf(success));
		wrapper.put("data", map);
		return wrapper;
	}

	public static Map<String, Object> wrapperDataRows(List<Object> dataRows) {
		HashMap<String, Object> wrapper = new HashMap<String,Object>();
		wrapper.put("success", Boolean.valueOf(true));
		wrapper.put("Rows", dataRows);
		return wrapper;
	}

	public static String toJsonString(Object jsonObj) throws Exception {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			return mapper.writeValueAsString(jsonObj).toString();
		} catch (Exception e) {
			loger.error("将对象序列化为JSON String时发送错误:" + e.getMessage());
			throw new Exception("将对象序列化为JSON String时发送错误,无法转换.");
		}
	}
}
