package com.css.business.web.subsyscraft.craManage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.bean.CraColorBom;
import com.css.business.web.subsyscraft.craManage.dao.CraColorBomManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("craColorBomManageService")
public class CraColorBomManageService extends BaseEntityManageImpl<CraColorBom, CraColorBomManageDAO>  {
	@Resource(name="craColorBomManageDAO")
	//@Autowired
	private CraColorBomManageDAO dao;
	
	
	@Override
	public CraColorBomManageDAO getEntityDaoInf() {
		return dao;
	}


	public Page getListPage(Page page, String proGgxh, String seqCode,String seqStep) {
		String hql = "from CraColorBom where  proGgxh='"+proGgxh
				+"' and seqCode = '"+seqCode+"' and seqStep = '"+seqStep+"' ";
		Page pageQuery = dao.pageQuery(hql, page.getPageNo(), page.getPagesize());
		return pageQuery;
	}


	@SuppressWarnings("unchecked")
	public List<CraColorBom> getCraColorBomList(String proGgxh,String seqCode){
		String hql = " from CraColorBom where proGgxh = ? and seqCode = ? ";
		List<CraColorBom> list = dao.createQuery(hql, proGgxh,seqCode).list();
		return list;
	}
	
	
}
