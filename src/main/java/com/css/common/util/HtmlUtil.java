package com.css.common.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 *TODO 数据输出工具类
 * @author huangaho
 *2015-4-17下午1:09:04
 */
public class HtmlUtil {
	/**
	 * 输出json格式
	 * 
	 * @param response
	 * @param jsonStr
	 */
	public static void writerJson(HttpServletResponse response, String jsonStr) {
		writer(response, jsonStr);
	}

	public static void writerJson(HttpServletResponse response, Object object) {
		response.setContentType("text/html");
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			writer(response, objectMapper.writeValueAsString(object));
		} catch (JsonGenerationException e) {

			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>输出HTML代码<br>
	 * <b>作者：</b>liuxiaoli<br>
	 * 
	 * @param response
	 * @param htmlStr
	 * @throws Exception
	 */
	public static void writerHtml(HttpServletResponse response, String htmlStr) {
	    response.setContentType("text/html;charset=UTF-8");
		writer(response, htmlStr);
	}

	private static void writer(HttpServletResponse response, String str) {
		try {
			// 设置页面不缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = null;
			out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
