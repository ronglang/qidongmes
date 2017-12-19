package com.css.business.web.subsysquality.quaManage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysquality.bean.QualityBasicParam;
import com.css.business.web.subsysquality.bean.QualityBasicValue;
import com.css.business.web.subsysquality.quaManage.dao.QualityBasicValueManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;


@Service("qualityBasicValueManageService")
public class QualityBasicValueManageService extends
		BaseEntityManageImpl<QualityBasicValue, QualityBasicValueManageDAO> {
	@Resource(name = "qualityBasicValueManageDAO")
	// @Autowired
	private QualityBasicValueManageDAO dao;

	@Override
	public QualityBasicValueManageDAO getEntityDaoInf() {
		return dao;
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param typeName
	 * @return         
	 */ 
	public List<QualityBasicValue> getValueByTypeName(String typeName,String quaCode) {
		// TODO Auto-generated method stub
		String hql ="";
		if (typeName != null && quaCode != null) {
			 hql ="from QualityBasicValue where basicTypeName = '"+typeName+"' and qua_code = '"+quaCode+"'";
		} if (typeName == null && quaCode != null) {
			 hql ="from QualityBasicValue where qua_code = '"+quaCode+"'";
		} 
		List<QualityBasicValue> queryList = dao.listQuery(hql);
		return queryList;
	}

	/**   
	 * @Description: 通过报告id 删除关联数据  
	 * @param id         
	 */ 
	public void clearBeansByReportId(String quaCode) {
		// TODO Auto-generated method stub
		String hql ="delete from QualityBasicValue where quaCode='"+quaCode+"'";
		int successInt = dao.createQuery(hql).executeUpdate();
		
	}

	
}
