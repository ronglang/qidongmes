package com.css.common.util;

/**
 * @TODO  : 配置一些系统级常量
 * @author: 翟春磊
 * @DATE  : 2016年7月14日
 */
public final class BzhpUtil {
	private static BzhpUtil bzhp = null;
	//行政区划根节点
	public final static String ROOT_AREA ;
	
	private  BzhpUtil(){}
	
	static {
		PropertyFileUtil pfu = new PropertyFileUtil();
		ROOT_AREA = pfu.getProp("root_area");
	}
	
	public static BzhpUtil getInstance(){
		if(bzhp == null)
			bzhp = new BzhpUtil();
		
		return bzhp;
	}
}
