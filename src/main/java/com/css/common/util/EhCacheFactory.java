package com.css.common.util;

import java.net.URL;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import com.css.business.web.sysManage.bean.SysEmployee;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.YorkUtil;

/**
 * 令牌缓存框架 用户app登录验证
 */
public class EhCacheFactory {

	// volatile保证在多线程开发中，各个线程不复制自己的ehCacheFactory，只是用“主”存储区的ehCacheFactory
	private static  volatile  EhCacheFactory ehCacheFactory;
	URL url;
	//CacheManager类型，它负责管理cache。Cache里面存储着Element对象，Element必须是key-value对。Cache是实际物理实现的，在内存中或者磁盘。
	CacheManager manager;
	/** 每添加一个缓存则在此新增一个Cache */
	Cache tokenCache;
	//人员缓存
	Cache employeeCache;
	//websocket cmd缓存
	Cache websocketCmdCache;
	//机台参数信息
	Cache macCache;
	//页面展示的机台cache
	Cache macShowCache;
	
	
	//机台提交的新异常缓存
	Cache exceptionCache;
	

	//加载配置文件
	private EhCacheFactory() {
		if (url == null) {
			url = EhCacheFactory.class.getResource("/ehcache.xml");
		}
		if (manager == null) {
			manager = CacheManager.create(url);
		}
		if (tokenCache == null) {
			//tokenCache = manager.getCache("verCodeCache");
			tokenCache = manager.getCache("tokenKeeperCache");
		}
		
		if (employeeCache == null) {
			employeeCache = manager.getCache("verCodeCache");
			employeeCache.removeAll();
		}
		if(websocketCmdCache == null){
			websocketCmdCache =  manager.getCache("websocketCmdCache");
			websocketCmdCache.removeAll();
		}
		if(macCache == null){
			macCache =  manager.getCache("macCache");
			macCache.removeAll();
		}
		if(macShowCache == null){
			macShowCache =  manager.getCache("macShowCache");
			macShowCache.removeAll();
		}
		if(exceptionCache == null){
			exceptionCache = manager.getCache("exceptionCache");
			exceptionCache.removeAll();
		}
	}

	//创建ehCacheFactory对象,单例设计模式-懒汉式， 这里为了保证线程安全，使用了双重检查锁定的方式
	public static EhCacheFactory getInstance() {
		if (ehCacheFactory == null) {
			synchronized (EhCacheFactory.class) {
				if(ehCacheFactory==null){
					ehCacheFactory = new EhCacheFactory();
				}
			}
		}
		return ehCacheFactory;
	}

	//用户登录创建user登录信息
	public void addTokenKeeperCache(SysUser user) {
		try {
			// find
			String token=user.getToken();
			Element ele = tokenCache.get(token);
			if (ele != null) {
				// remove
				tokenCache.remove(token);
			}
			tokenCache.put(new Element(token, user));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//人员信息
	public void addEmployeeCache(SysEmployee emp) {
		try {
			// find
			String token=emp.getMobileTel();
			Element ele = employeeCache.get(token);
			if (ele != null) {
				// remove
				employeeCache.remove(token);
			}
			employeeCache.put(new Element(token, emp));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//通过token获取缓存中的user信息
	public SysUser getTokenKeeperCache(String token) {
		SysUser value = null;
		try {
			Element ele = tokenCache.get(token);
			if (ele != null) {
				value = (SysUser) ele.getObjectValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	
	public SysEmployee getEmployeeCache(String mobiltel) {
		SysEmployee value = null;
		try {
			Element ele = employeeCache.get(mobiltel);
			if (ele != null) {
				value = (SysEmployee) ele.getObjectValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	//当前用户退出，删除内存中的user信息
	public void removeTokenKeeperCache(String key) {
		try {
			tokenCache.remove(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeEmployeeCache(String mobiltel) {
		try {
			employeeCache.remove(mobiltel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 获取所有的缓存对象
	public void printAllTokenKeeper() {
		for (Object key : tokenCache.getKeys()) {
			@SuppressWarnings("unused")
			SysUser value = null;
			Element ele = tokenCache.get(key);
			if (ele != null) {
				value = (SysUser) ele.getObjectValue();
			}
		}
	}

	/**
	 * @TODO:websocket cmd缓存 
	 * @author: zhaichunlei
	 & @DATE : 2017年7月17日
	 * @param kanBanCode
	 *  YorkUtil.Memcache_看板_拉丝
	 *            ---
	 *            ---
	 *            Memcache_看板_质检
	 * @param jsonStr
	 */
	public void addWebsocketCmdCache(String kanBanCode,String jsonStr){
		try {
			//YorkUtil.Memcache_看板_仓库
			// find
			Element ele = websocketCmdCache.get(kanBanCode);
			if (ele != null) {
				// remove
				websocketCmdCache.remove(kanBanCode);
			}
			websocketCmdCache.put(new Element(kanBanCode, jsonStr));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//取的看板code对应的信息
	public String getWebsocketCmdCache(String kanBanCode) {
		String value = null;
		try {
			Element ele = websocketCmdCache.get(kanBanCode);
			if (ele != null) {
				value = ele.getObjectValue().toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	//删除看板code对应的信息
	public void removeWebsocketCmdCache(String kanBanCode) {
		try {
			websocketCmdCache.remove(kanBanCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Description: 机台所有参数 信息的cache   
	 * @param macCode
	 * @param jsonList 
	 */
	public void addMacDicCaChe(String macCode,String jsonList){
		try {
			Element ele = macCache.get(macCode);
			if (ele != null) {
				// remove
				macCache.remove(macCode);
			}
			macCache.put(new Element(macCode, jsonList));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param macCode
	 * @return 是一个json 类型 List<SysMacDictionary> ,建议使用gson转
	 * List<SysMacDictionary> dicList = gson.fromJson(str,new TypeToken<List<SysMacDictionary>>(){}.getType());
	 */
		public String getMacDicCaChe(String macCode) {
			String value = null;
			try {
				Element ele = macCache.get(macCode);
				if (ele != null) {
					value = ele.getObjectValue().toString();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return value;
		}
		
		/**
		 * 
		 * @Description: 页面展示的cache   
		 * @param macCode 机器code
		 * @param jsonMap 参数code : html_id
		 */
		public void addMacShowCache(String macCode,String jsonMap){
			try {
				Element ele = macShowCache.get(macCode);
				if (ele != null) {
					// remove
					macShowCache.remove(macCode);
				}
				macShowCache.put(new Element(macCode, jsonMap));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//删除看板code对应的信息
		public void removeMacShowCache(String str) {
			try {
				macShowCache.remove(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * @Description: 页面展示的cache   
		 * @param macCode
		 * @return 是一个json 类型 List<SysMacDictionary> ,建议使用gson转
		 * Map<String,String> map = gson.fromJson(str,new TypeToken<Map<String,String>>(){}.getType());
		 */
		public String getMacShowCaChe(String macCode) {
				String value = null;
				try {
					Element ele = macShowCache.get(macCode);
					if (ele != null) {
						value = ele.getObjectValue().toString();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return value;
		}

		/**
		 * @todo: 暂时没用上，用的是 websocketCmdCache Memcache_看板_生产_异常
		 * @author : zhaichunlei
		 * @date: 2017年12月12日
		 */
		@Deprecated 
	   public void addExceptionCache(String key, String jsonMap){
		   try {
				Element ele = exceptionCache.get(key);
				if (ele != null) {
					// remove
					exceptionCache.remove(key);
				}
				macShowCache.put(new Element(key, jsonMap));
			} catch (Exception e) {
				e.printStackTrace();
			}
	   }
	   
	   public void removeExceptionCache(String key){
		   if(key != null)
			   exceptionCache.remove(key);
	   }
	   
	   public String getExceptionCache(String key){
		   Element e = exceptionCache.get(key);
		   if(e != null){
			   String value = e.getObjectValue() == null ? null : e.getObjectValue().toString();
			   return value;
		   }
		   return null;
	   }
}
