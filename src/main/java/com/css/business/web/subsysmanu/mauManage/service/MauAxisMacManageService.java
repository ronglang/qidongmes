package com.css.business.web.subsysmanu.mauManage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauAxisMac;
import com.css.business.web.subsysmanu.mauManage.dao.MauAxisMacManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauAxisMacManageService")
public class MauAxisMacManageService extends BaseEntityManageImpl<MauAxisMac, MauAxisMacManageDAO> {
	@Resource(name="mauAxisMacManageDAO")
	//@Autowired
	private MauAxisMacManageDAO dao;
	
	
	@Override
	public MauAxisMacManageDAO getEntityDaoInf() {
		return dao;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map[] getAxisName(){
		String hql = " SELECT DISTINCT axisName from MauAxis ";
		List<Object> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					map.put("text", list.get(i).toString());
				} else {
					map.put("id", i + "");
				}
			}
			str[i] = map;
		}
		return str;
	}

	public Map[] getMacCode() {
		String hql = " SELECT DISTINCT macCode from MauAxisMac ";
		List<Object> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					map.put("text", list.get(i).toString());
				} else {
					map.put("id", i + "");
				}
			}
			str[i] = map;
		}
		return str;
	}
	
	
	public Page getListPage(Page page, String macCode, String axisName) {
		String hql = "from MauAxisMac where 1 = 1";
		if (macCode != null && !"".equals(macCode)) {
			hql += "  and macCode ='"+macCode+"'";
		}
		if (axisName != null && !"".equals(axisName)) {
			hql += "  and axisName like '%"+macCode+"%'";
		}
		Page pageQuery = dao.pageQuery(hql, page.getPageNo(), page.getPagesize());
		return pageQuery;
	}


	
}
