package com.css.business.web.subsysplan.plaManage.dao;

import org.springframework.stereotype.Repository;

import com.css.business.web.subsysplan.bean.PlaRequestPurchaseDetailed;
import com.css.business.web.subsysplan.plaManage.bean.PlaRequestPurchaseDetailedVo;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;

@Repository("plaRequestPurchaseDetailedManageDAO")
public class PlaRequestPurchaseDetailedManageDAO extends BaseEntityDaoImpl<PlaRequestPurchaseDetailed>  {

	public Page queryBackDateDao(Page p,final StringBuffer sql) throws Exception{
		Page pp = this.pageSQLQueryVONoneDesc(sql.toString(), p.getPageNo(), p.getPagesize(), new PlaRequestPurchaseDetailedVo());
		return pp;
	}
	
}
