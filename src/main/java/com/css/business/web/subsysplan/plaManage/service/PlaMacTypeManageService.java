package com.css.business.web.subsysplan.plaManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaMacType;
import com.css.business.web.subsysplan.plaManage.dao.PlaMacTypeManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaMacTypeManageService")
@Deprecated
public class PlaMacTypeManageService extends BaseEntityManageImpl<PlaMacType, PlaMacTypeManageDAO> {
	@Resource(name="plaMacTypeManageDAO")
	//@Autowired
	private PlaMacTypeManageDAO dao;
	
	
	@Override
	public PlaMacTypeManageDAO getEntityDaoInf() {
		return dao;
	}


	/**
	 * 
	 * @Description: 通过状态去查询机台数量  
	 * @param condition 状态
	 * @param area 区域
	 * @return
	 */
	public Long queryCount(String condition,String area) {
		// TODO Auto-generated method stub
		Long count = dao.queryCount(condition,area);
		return count;
	}


	
}
