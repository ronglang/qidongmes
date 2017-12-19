package com.css.business.web.subsysmanu.mauManage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauHandlingChores;
import com.css.business.web.subsysmanu.mauManage.dao.MauHandlingChoresManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauHandlingChoresManageService")
public class MauHandlingChoresManageService extends BaseEntityManageImpl<MauHandlingChores, MauHandlingChoresManageDAO> {
	@Resource(name="mauHandlingChoresManageDAO")
	//@Autowired
	private MauHandlingChoresManageDAO dao;
	@Override
	public MauHandlingChoresManageDAO getEntityDaoInf() {
		return dao;
	}
	/*
	 * @杂物处理条件分页查询
	 * @mjq
	 * @2017/4/27
	 */
	public  Page queryChoresDataService(Page p,MauHandlingChores ent) throws Exception{
		return dao.queryChoresDataDao(p, ent);
	}
	public void choresEdit(MauHandlingChores ent){
		/*MauHandlingChores bean =new MauHandlingChores();
		//MauHandlingChores bean = this.get(id);
		bean.setId(id); 
		bean.setAgentBy(ent.getAgentBy());
		bean.setMaterCode(ent.getMaterCode());
		bean.setRemark(ent.getRemark());
		bean.setChoresName(ent.getChoresName());*/
		dao.updateBean(ent);
	}
	public void toDelete(String idStr) {
	  String[] str=idStr.split(",");
	  for (int i = 0; i < str.length; i++) {
		int id=Integer.parseInt(str[i]);
		dao.toDelete(id);
	}
	}
	
	@SuppressWarnings("rawtypes")
	public Map[] getChoresname(){
		String hql = " SELECT DISTINCT choresName FROM MauHandlingChores WHERE choresName != '' ";
		@SuppressWarnings("unchecked")
		List<Object> list = dao.createQuery(hql).list(); //得到的list为Object
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) { //根据ligerForm下拉框数据格式，封装数据
			Map<String, String> map = new HashMap<String, String>();
				for (int j = 0; j < 2; j++) {
					if(j == 0) {
						map.put("text", list.get(i).toString());
					}else {
						map.put("key", i + "");
					}
			}
			str[i] = map;
			
		}
		return str;
	}
	
	@SuppressWarnings("rawtypes")
	public Map[] getMaterCode(){
		String hql = "  SELECT DISTINCT materCode FROM MauHandlingChores WHERE materCode != ''  ";
		@SuppressWarnings("unchecked")
		List<Object> list = dao.createQuery(hql).list(); //得到的list为Object
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) { //根据ligerForm下拉框数据格式，封装数据
			Map<String, String> map = new HashMap<String, String>();
				for (int j = 0; j < 2; j++) {
					if(j == 0) {
						map.put("text", list.get(i).toString());
					}else {
						map.put("key", i + "");
					}
			}
			str[i] = map;
		}
		return str;
	}
	
	
}

