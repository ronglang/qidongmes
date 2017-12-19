package com.css.common.util;
//配置文件读取工具
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertyFileUtil {
	private Properties appPropertie = null;
    

	@SuppressWarnings("unused")
	public PropertyFileUtil(){
    	FileInputStream configFile;
		try {
			InputStream is = this.getClass().getResourceAsStream("/conf/config.properties");
//			configFile = new FileInputStream("/conf/config.properties");
			appPropertie = new Properties();
			appPropertie.load(new InputStreamReader(is, "utf-8"));
//			configFile.close();
			is.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
    }
    
    public String getProp(String key){
    	String value="";
    	if(appPropertie!=null){
    		if(appPropertie.containsKey(key)){
    			value = appPropertie.getProperty(key);
    			return value;
    		}else{
    			System.out.println("配置读取错误，没有这个key:"+key);
    			return null;
    		}
    	}
    	return null;
    }
}
