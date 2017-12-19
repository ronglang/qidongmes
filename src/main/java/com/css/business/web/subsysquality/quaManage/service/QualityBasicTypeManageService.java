package com.css.business.web.subsysquality.quaManage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysquality.bean.QualityBasicType;
import com.css.business.web.subsysquality.quaManage.dao.QualityBasicTypeManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;


@Service("qualityBasicTypeManageService")
public class QualityBasicTypeManageService extends
		BaseEntityManageImpl<QualityBasicType, QualityBasicTypeManageDAO> {
	@Resource(name = "qualityBasicTypeManageDAO")
	// @Autowired
	private QualityBasicTypeManageDAO dao;

	@Override
	public QualityBasicTypeManageDAO getEntityDaoInf() {
		return dao;
	}

	/**   
	 * @Description: 条件分页查询    模糊查询
	 * @param map
	 * @param page
	 * @return         
	 */ 
	public Page getPageList(Map<String, String> map, Page page) {
		// TODO Auto-generated method stub
		String hql =" from QualityBasicType where 1 = 1 ";
		StringBuilder sb = new StringBuilder(hql);
		if (map != null && map.size() > 0 ) {
			if (map.get("type") != null && map.get("type") != "") {
				sb.append(" and type = '"+map.get("type")+"' ");
			}
			if (map.get("typeName") != null && map.get("typeName") != "") {
				sb.append("and  typeName like '%"+map.get("typeName")+"%' ");
			}
			if (map.get("testAccording") != null && map.get("testAccording") != "") {
				sb.append(" and testAccording like '%"+map.get("testAccording")+"%' ");
			}
		}
		Page pageQuery = page;
		try {
			pageQuery = dao.pageQuery(sb.toString(), page.getPageNo(), page.getPagesize());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return pageQuery;
	}

	

	/**   
	 * @Description: 查重  
	 * @param type
	 * @param typeName
	 * @return         
	 */ 
	public boolean checkType(String type, String typeName) {
		// TODO Auto-generated method stub
		String hql ="from QualityBasicType where type='"+type+"'  and typeName='"+typeName+"'";
		List list = dao.createQuery(hql).list();
		if (list != null && list.size() > 0) {
			return false;
		}
		return true;
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param type
	 * @return         
	 */ 
	public Map[] getTypeNames(String type) {
		// TODO Auto-generated method stub
		String hql = "select typeName from QualityBasicType where type = '"+type+"'";
		List list = dao.createQuery(hql).list();
		Map[] arrMap  = null;
		if (list.size() > 0 && list != null) {
			 arrMap = new Map[list.size()];
			for (int i = 0;i < list.size();i++) {
				Map<String, String>map = new HashMap<String, String>();
				map.put("name", (String)list.get(i));
				map.put("text", (String)list.get(i));
				arrMap[i] = map;
			}
			return arrMap;
		}
		return null;
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param typeName
	 * @return         
	 */ 
	public Map[] getAccording(String typeName) {
		// TODO Auto-generated method stub
		String hql = "select testAccording from QualityBasicType where typeName ='"+typeName+"' ";
		List list = dao.createQuery(hql).list();
		Map[] arrMap  = null;
		if (list != null && list.size() > 0 ) {
			 arrMap = new Map[list.size()];
				for (int i = 0;i < list.size();i++) {
					Map<String, String>map = new HashMap<String, String>();
					map.put("testAccording", (String)list.get(i));
					map.put("id", (String)list.get(i));
					arrMap[i] = map;
				}
				return arrMap;
		}
		return null;
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param id         
	 */ 
	public void clearBean(String id) {
		// TODO Auto-generated method stub
//		String hql ="delect from  QualityBasicType where id="+id+"";
		dao.removeById(Integer.parseInt(id));
	}

	
}
