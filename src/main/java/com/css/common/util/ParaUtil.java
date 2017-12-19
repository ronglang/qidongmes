/**
 * 
 */
package com.css.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author think
 *
 */
public class ParaUtil {

	public String decode(String content) {
		
		String deString = "";
		try {
			deString = URLDecoder.decode(content, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deString;
	}
}
