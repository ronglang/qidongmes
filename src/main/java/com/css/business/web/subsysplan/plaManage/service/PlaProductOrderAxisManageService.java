package com.css.business.web.subsysplan.plaManage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaProductOrderAxis;
import com.css.business.web.subsysplan.plaManage.dao.PlaProductOrderAxisManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaProductOrderAxisManageService")
public class PlaProductOrderAxisManageService extends BaseEntityManageImpl<PlaProductOrderAxis, PlaProductOrderAxisManageDAO> {
	@Resource(name="plaProductOrderAxisManageDAO")
	//@Autowired
	private PlaProductOrderAxisManageDAO dao;
	
	
	@Override
	public PlaProductOrderAxisManageDAO getEntityDaoInf() {
		return dao;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map[] gettAxisCode(){
		String hql = " from PlaProductOrderAxis ";
		List<PlaProductOrderAxis> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map<String,String> map = new HashMap<String, String>();
			for(int j = 0; j < 2 ; j++){
				if(j==0){
					map.put("text", list.get(i).getAxisName());
				}else{
					map.put("id", i+"");
				}
			}
			str[i] = map; 
		}
		return str;
	}
	
}
