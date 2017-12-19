package com.css.commcon.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @Title: DisplayPropertyUtil.java
 * @Package com.css.commcon.util
 * @Description: 电子看板的参数
 * @author rb
 * @date 2017年7月20日 下午3:11:14
 * @company SMTC
 */

public class DisplayPropertyUtil {
	private Properties appPropertie = null;
	private String url = "/conf/mauparam.properties";
	@SuppressWarnings("unused")
	public DisplayPropertyUtil(String url) {
		FileInputStream configFile;
		try {
			InputStream is = this.getClass().getResourceAsStream(
					url);
			appPropertie = new Properties();
			appPropertie.load(new InputStreamReader(is, "utf-8"));
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getProp(String key) {
		String value = "";
		if (appPropertie != null) {
			if (appPropertie.containsKey(key)) {
				value = appPropertie.getProperty(key);
				return value;
			} else {
				System.out.println("配置读取错误，没有这个key:" + key);
				return null;
			}
		}
		return null;
	}
}
