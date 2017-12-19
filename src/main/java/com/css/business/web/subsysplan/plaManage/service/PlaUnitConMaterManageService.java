package com.css.business.web.subsysplan.plaManage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaUnitConMater;
import com.css.business.web.subsysplan.plaManage.dao.PlaUnitConMaterManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaUnitConMaterManageService")
public class PlaUnitConMaterManageService extends BaseEntityManageImpl<PlaUnitConMater, PlaUnitConMaterManageDAO> {
	@Resource(name="plaUnitConMaterManageDAO")
	//@Autowired
	private PlaUnitConMaterManageDAO dao;
	
	
	@Override
	public PlaUnitConMaterManageDAO getEntityDaoInf() {
		return dao;
	}
	
	public List<PlaUnitConMater> getPlaUnitConMater(String proGgxh){
		String hql = " from PlaUnitConMater p where p.proGgxh = ? ";
		@SuppressWarnings("unchecked")
		List<PlaUnitConMater> list = dao.createQuery(hql, proGgxh).list();
		return list;
	}
	
	
}
