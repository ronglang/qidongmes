package com.css.common.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public final class PropertiesHelper {
	public static String getString(String propertyFileName, String propertyName) {
		String data="";
		try {
			Configuration config= new PropertiesConfiguration(propertyFileName);
			data=config.getString((propertyName));
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static Long getLong(String propertyFileName, String propertyName) {
		Long data=null;
		try {
			Configuration config= new PropertiesConfiguration(propertyFileName);
			data=config.getLong(propertyName,0);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static Integer getInteger(String propertyFileName,String propertyName) {
		Integer data=null;
		try {
			Configuration config= new PropertiesConfiguration(propertyFileName);
			data=config.getInt(propertyName,0);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return data;
	}

	
}
