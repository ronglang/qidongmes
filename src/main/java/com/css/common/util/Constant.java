package com.css.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.css.business.web.sysManage.bean.SysArea;
import com.css.business.web.sysManage.bean.SysCommdic;
import com.css.business.web.sysManage.bean.SysConfig;
import com.css.business.web.sysManage.bean.SysDictionary;
import com.css.business.web.sysManage.bean.SysOrg;
import com.css.business.web.sysManage.bean.SysUser;


/**
 * 
 *TODO 常量类
 * @author huangaho
 *2015-4-22下午3:22:46
 */
public class Constant {
	public static final String IMG = "img";
	public static final String DOC = "doc";
	public static final String VIDEO = "video";
	
	/**
	 * 资源对应的菜单类型
	 */
	public static Integer   RESOURCE_TYPE_MENU=1;
	/**
	 * 数据字典 ，KEY：字典编码，VALUE:名称
	 */
	public static ConcurrentHashMap<String,String> DICS = new ConcurrentHashMap<String,String>();
	public static List<SysDictionary> DIC_LIST = new ArrayList<SysDictionary>();
	/**
	 * KEY :机构编码 ，VALUE:名称
	 */
	public static ConcurrentHashMap<String,SysOrg> ORG = new ConcurrentHashMap<String,SysOrg>();
	
	public static List<SysOrg> ORG_LIST = new ArrayList<SysOrg>();
	
	public static List<SysArea> AREA_LIST = new ArrayList<SysArea>();
	
	/**
	 * KEY :区划编码 ，VALUE:名称
	 */
	public static ConcurrentHashMap<String,SysArea> AREA = new ConcurrentHashMap<String,SysArea>();
	/**
	 * KEY :帐号 ，VALUE:SysUser
	 */
	public static ConcurrentHashMap<String, SysUser> USER=new ConcurrentHashMap<String,SysUser>();
	public static List<SysUser> USERLIST;
	
	public static ConcurrentHashMap<String, SysConfig> CONFIG=new ConcurrentHashMap<String,SysConfig>();
	
	public static ConcurrentHashMap<String, SysCommdic> commdic=new ConcurrentHashMap<String,SysCommdic>();
	
	//public static ConcurrentHashMap<String, ProjectCode> PROJECTNAME=new ConcurrentHashMap<String,ProjectCode>();
	
	
	/**
	 * 
	 * SysOrg
	 * 2016年3月7日 下午5:53:22
	 * @param orgName
	 * @return
	 */
	public synchronized static SysOrg existOrgByName(String orgName){
		if(StringUtil.isEmpty(orgName))return null;
		for(SysOrg org:ORG_LIST){
			if(orgName.equals(org.getOrgName())){
				return org;
			}
		}
		return null;
	}
	
	public synchronized static SysDictionary existDictionaryByName(String dicName){
		if(StringUtil.isEmpty(dicName))return null;
		for(SysDictionary dic:DIC_LIST){
			if(dicName.equals(dic.getValue())){
				return dic;
			}
		}
		return null;
	}
}
