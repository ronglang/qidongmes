package com.css.business.web.subsysplan.plaManage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaCourseAxis;
import com.css.business.web.subsysplan.plaManage.dao.PlaCourseAxisManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service("plaCourseAxisManageService")
public class PlaCourseAxisManageService extends
		BaseEntityManageImpl<PlaCourseAxis, PlaCourseAxisManageDAO> {
	@Resource(name = "plaCourseAxisManageDAO")
	// @Autowired
	private PlaCourseAxisManageDAO dao;

	@Override
	public PlaCourseAxisManageDAO getEntityDaoInf() {
		return dao;
	}
	private Gson gson = new Gson();
	
	public Page getListPage(Page page, String param) {
		String hql = "from PlaCourseAxis where 1 = 1";
		StringBuilder sb = new StringBuilder(hql);
		Map<String,String>map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		if (map != null && map.size() > 0 ) {
			if (map.get("courseCode") != null  && !"".equals(map.get("courseCode"))) {
				sb.append(" and courseCode = '"+map.get("courseCode")+"'");
			}
		}
		Page pageQuery = dao.pageQuery(sb.toString(), page.getPageNo(), page.getPagesize());
		return pageQuery;
	}

	public List<Object> getList(String param) {
		String hql = "from PlaCourseAxis where 1 = 1";
		StringBuilder sb = new StringBuilder(hql);
		Map<String,String>map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		if (map != null && map.size() > 0 ) {
			if (map.get("courseCode") != null  && !"".equals(map.get("courseCode"))) {
				sb.append(" and courseCode = '"+map.get("courseCode")+"'");
			}
		}
		List<Object> listQuery = dao.createQuery(sb.toString()).list();
		return listQuery;
	}

	
}
