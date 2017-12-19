package com.css.business.web.subsysquality.quaManage.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.css.business.web.subsysquality.bean.QualityUndoBasic;
import com.css.business.web.subsysquality.quaManage.dao.QualityUndoBasicDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;


@Service("qualityUndoService")
public class QualityUndoService extends
BaseEntityManageImpl<QualityUndoBasic, QualityUndoBasicDAO> {
	
		
	@Resource(name = "qualityUndoBasicDAO")
	private QualityUndoBasicDAO dao; 

	
	@Override
	public QualityUndoBasicDAO getEntityDaoInf() {
		return dao;
	}

	
	public Page getQualityUndoBasic(Page p) throws Exception{
		String sql = "from QualityUndoBasic ";
		Page pageQuery = dao.pageQuery(sql.toString(), p.getPageNo(), p.getPagesize());
		return pageQuery;

	}
	
	public Page queryPage(Page p,
			String startTime, String endTime) {
		StringBuffer sql= new StringBuffer(" from QualityUndoBasic r  where 1=1  ");

		if(null!=startTime&&!"".equals(startTime)){
			sql.append("  and r.end_date>='"+startTime+"'");
		}
		if(null!=endTime&&!"".equals(endTime)){
			sql.append("  and r.end_date<='"+endTime+"'");
		}
		sql.append("    order by  r.end_date desc");
		Page page = dao.pageQuery(sql.toString(), p.getPageNo(), p.getPagesize());
		return page;
	}
 
	
}
