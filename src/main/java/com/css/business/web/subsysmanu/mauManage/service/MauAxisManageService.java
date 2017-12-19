package com.css.business.web.subsysmanu.mauManage.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauAxis;
import com.css.business.web.subsysmanu.mauManage.dao.MauAxisManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauAxisManageService")
public class MauAxisManageService extends BaseEntityManageImpl<MauAxis, MauAxisManageDAO> {
	@Resource(name = "mauAxisManageDAO")
	private MauAxisManageDAO dao;

	//private Gson gson = new Gson();

	@Override
	public MauAxisManageDAO getEntityDaoInf() {
		return dao;
	}

	public Page getListPage(Page page, Map<String, String> map) {
		String hql = "from MauAxis where 1 = 1 ";
		StringBuilder sb = new StringBuilder(hql);
		if (map != null && map.size() > 0 ) {
			if (map.get("macCode")!=null && !"".equals(map.get("macCode"))) {
				sb.append(" and macCode='"+map.get("macCode")+"' ");
			}
			if (map.get("axisName")!=null && !"".equals(map.get("axisName"))) {
				sb.append(" and axisName like '%"+map.get("axisName").trim()+"%' ");
			}
		}
		
		Page pageQuery = dao.pageQuery(sb.toString(), page.getPageNo(), page.getPagesize());
		return pageQuery;
	}

	
}
