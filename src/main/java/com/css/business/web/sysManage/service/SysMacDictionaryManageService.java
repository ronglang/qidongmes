package com.css.business.web.sysManage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.sysManage.bean.SysMacDictionary;
import com.css.business.web.sysManage.dao.SysMacDictionaryManageDao;
import com.css.common.util.EhCacheFactory;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.google.gson.Gson;
@Service("sysMacDictionaryManageService")
public class SysMacDictionaryManageService extends BaseEntityManageImpl<SysMacDictionary, SysMacDictionaryManageDao>{
	@Resource(name = "sysMacDictionaryManageDao")
	private SysMacDictionaryManageDao dao;

	@Override
	public SysMacDictionaryManageDao getEntityDaoInf() {
		return dao;
		}
	
	private Gson gson = new Gson();
	private EhCacheFactory factory = EhCacheFactory.getInstance();

	/**
	 * 系统启动将mac参数存入ehcache;
	 */
	public void getMacDicToEh() {
		// TODO Auto-generated method stub
		String hql = " select DISTINCT macCode from  SysMacDictionary ";
		List<String> list = dao.createQuery(hql).list();
		for (String code : list) {
				String macHql = "from SysMacDictionary where macCode = '"+code+"' ";
				//查询到该机台所有的参数信息
				List<SysMacDictionary> dicList = dao.createQuery(macHql).list();
				//所有参数信息的List
				List<Map<String,SysMacDictionary>>detailList = new ArrayList<>(); 
				//页面展示的参数  code :htmlId
				Map<String,String> showMap = new HashMap<>();
				for (SysMacDictionary dic : dicList) {
					Map<String, SysMacDictionary>map = new HashMap<String, SysMacDictionary>();
					map.put(dic.getCode(), dic);
					detailList.add(map);
					//需要展示
					if (dic.getIsShow() == SysMacDictionary.IS_SHOW ) {
						showMap.put(dic.getCode(), dic.getHtmlId());
					}
				factory.addMacDicCaChe(code, gson.toJson(detailList));
				//添加页面展示的缓存
				String str =  gson.toJson(showMap);
				factory.addMacShowCache(code,str);
			}
			
			
		}
		
		
		
	}

	/**   
	 * @Description: TODO(根据机台编码查询电子看板机台参数)   
	 * @param macCode
	 * @return         
	 */ 
	public List<SysMacDictionary> getMacParamByMacCode(String macCode) {
		String hql = "from SysMacDictionary where 1 = 0 " ;
		if(macCode != null && macCode != ""){
			hql += " or macCode = '"+macCode+"' order by isShow DESC";
		}
		List<SysMacDictionary> listQuery = dao.listQuery(hql);
		return listQuery;
	}
	
	
	
	
	
}
