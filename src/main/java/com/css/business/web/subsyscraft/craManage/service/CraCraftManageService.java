package com.css.business.web.subsyscraft.craManage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.bean.CraCraft;
import com.css.business.web.subsyscraft.craManage.dao.CraCraftManageDAO;
import com.css.business.web.subsysstore.bean.StoreMaterialBasicInfo;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.css.dubbointerface.subsyscraft.CraftManagerInter;

@Service("craCraftManageService")
public class CraCraftManageService extends BaseEntityManageImpl<CraCraft, CraCraftManageDAO>  implements CraftManagerInter{
	@Resource(name="craCraftManageDAO")
	//@Autowired
	private CraCraftManageDAO dao;
	
	
	@Override
	public CraCraftManageDAO getEntityDaoInf() {
		return dao;
	}


	@Override
	public String demoTest() {
		// TODO Auto-generated method stub
		return "return from CraCraftManageService.demoTest";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map[] getMateril(){
		String hql = " from StoreMaterialBasicInfo where pCode = '2' ";
		List<StoreMaterialBasicInfo> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map<String,String> map = new HashMap<String,String>(); 
			for(int j = 0;j<2;j++){
				if(j==0){
					map.put("text", list.get(i).getMater_ggxh());
				}else{
					map.put("id", i+"");
				}
			}
			str[i] = map;
		}
		return str;
	}
	
	public List<StoreMaterialBasicInfo> getSelectMateril(){
		String hql = " from StoreMaterialBasicInfo where pCode = '2' ";
		List<StoreMaterialBasicInfo> list = dao.createQuery(hql).list();
		return list;
	}
	
	public HashMap<String, Object> selectMaterGgxh(){
		List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
		try {//{Rows:[{"proGgxh":"规格型号1"},{"proGgxh":"规格型号2"}],Total:100}
			//List<MauProduct> list = mauProductManageService.queryAll();
			List<StoreMaterialBasicInfo> list= this.getSelectMateril();
			Map<String,String> map = null;
			for(StoreMaterialBasicInfo bi : list){
				map = new HashMap<String,String>();
				map.put("mater", bi.getMater_ggxh());
				resultList.add(map);
			}
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("Rows", resultList);
			resultMap.put("Total", list.size());
			return JsonWrapper.successWrapper(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonWrapper.failureWrapper("获取失败");
	}
	
}
