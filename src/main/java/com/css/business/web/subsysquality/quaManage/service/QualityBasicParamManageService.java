package com.css.business.web.subsysquality.quaManage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysquality.bean.QualityBasicParam;
import com.css.business.web.subsysquality.bean.QualityBasicType;
import com.css.business.web.subsysquality.quaManage.dao.QualityBasicParamManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;


@Service("qualityBasicParamManageService")
public class QualityBasicParamManageService extends
		BaseEntityManageImpl<QualityBasicParam, QualityBasicParamManageDAO> {
	@Resource(name = "qualityBasicParamManageDAO")
	// @Autowired
	private QualityBasicParamManageDAO dao;

	@Override
	public QualityBasicParamManageDAO getEntityDaoInf() {
		return dao;
	}

	/**   
	 * @Description: 保存方法   
	 * @param beansList         
	 * @param basicTypeName 
	 * @param basicTypeId 
	 */ 
	public Map<String ,Integer> saveBeans(List<QualityBasicParam> beansList, int basicTypeId, String basicTypeName) {
		// TODO Auto-generated method stub
		Map<String ,Integer>map = new HashMap<String, Integer>();
		Integer successCount = 0;
		Integer falseCount = 0;
		for (QualityBasicParam qualityBasicParam : beansList) {
			try {
				qualityBasicParam.setBasicTypeId(basicTypeId);
				qualityBasicParam.setBasicTypeName(basicTypeName);
				this.save(qualityBasicParam);
				successCount++;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				falseCount++;
				e.printStackTrace();
				continue;
			}
		}
		map.put("successCount", successCount);
		map.put("falseCount", falseCount);
		return map;
	}

	/**   
	 * @Description: 条件分页查询   
	 * @param map
	 * @param page
	 * @return         
	 */ 
	public Page getPageList(Map<String, String> map, Page page) {
		// TODO Auto-generated method stub
		String hql = "from QualityBasicParam where 1 = 1 ";
		StringBuilder sb = new StringBuilder(hql);
		if (map != null && map.size() > 0) {
			if (map.get("basicTypeId") != null && map.get("basicTypeId") != "") {
				sb.append(" and  basicTypeId = '"+map.get("basicTypeId")+"'");
			} 
			if (map.get("basicTypeName") != null && map.get("basicTypeName") != "") {
				sb.append(" and basicTypeName like '%"+map.get("basicTypeName")+"%'");
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
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param beansList
	 * @return         
	 */ 
	public Map<String, Integer> updeteBeans(List<QualityBasicParam> beansList) {
		// TODO Auto-generated method stub
		Map<String ,Integer>map = new HashMap<String, Integer>();
		Integer successCount = 0;
		Integer falseCount = 0;
		for (QualityBasicParam qualityBasicParam : beansList) {
			try {
				this.updateByCon(qualityBasicParam, false);
				successCount++;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				falseCount++;
				e.printStackTrace();
				continue;
			}
		}
		map.put("successCount", successCount);
		map.put("falseCount", falseCount);
		return map;
	}

	/**   
	 * @Description: 查重 现在  默认不查 
	 * @param basicTypeId
	 * @param paramName
	 * @param flag 是否是更新         true 更新  false 新建
	 */ 
	public boolean checkParam(Integer basicTypeId, String paramName, boolean flag) {
		// TODO Auto-generated method stub
		/*String hql ="from QualityBasicParam where basicTypeId = '"+basicTypeId+"' and paramName='"+paramName+"'";
		List list = dao.createQuery(hql).list();
		if (flag) {
			//更新
			if (list.size() > 1) {
				return false;
			}
			return true;
		} else {
			if (flag) {
				
			}
		}*/
		
		return true;
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param typeName
	 * @return         
	 */ 
	public List<QualityBasicParam> getParamsByTypeName(String typeName) {
		// TODO Auto-generated method stub
		String hql = "from  QualityBasicParam where basicTypeName = '"+typeName+"'";
		List<QualityBasicParam> list = dao.createQuery(hql).list();
		return list;
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param basicType         
	 */ 
	public void updateName(QualityBasicType basicType) {
		// TODO Auto-generated method stub
		String hql = "UPDATE QualityBasicParam SET basicTypeName = '"+basicType.getTypeName()+"' WHERE basicTypeId = '"+basicType.getId()+"'  ";
		dao.createQuery(hql).executeUpdate();
	}

	/**   
	 * @Description: 查重   
	 * @param basicTypeId
	 * @param paramName
	 * @return         
	 */ 
	public Long checkName(String basicTypeId, String paramName) {
		// TODO Auto-generated method stub
		String hql ="select count(*) from QualityBasicParam where paramName ='"+paramName+"' and basicTypeId ='"+basicTypeId+"'";
		Long count = (Long) dao.createQuery(hql).uniqueResult();
		return count;
	}

	
	
}
