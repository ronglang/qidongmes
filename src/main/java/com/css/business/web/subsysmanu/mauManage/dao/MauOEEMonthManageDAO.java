package com.css.business.web.subsysmanu.mauManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.business.web.subsysmanu.bean.MauOEEMonth;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("mauOEEMonthManageDAO")
public class MauOEEMonthManageDAO extends BaseEntityDaoImpl<MauOEEMonth>  {

	/**   
	 * @Description:  通过机台code和月份拿到 
	 * @param machineCode
	 * @param month yyyt-MM-dd
	 * @return         
	 */ 
	public MauOEEMonth getByMacCodeAndMont(String machineCode, String month) {
		// TODO Auto-generated method stub
		String hql =" from MauOEEMonth where macCode='"+machineCode+"' and month='"+month+"'" ;
		List<MauOEEMonth> listQuery = this.listQuery(hql);
		if (listQuery != null && listQuery.size() > 0) {
			return listQuery.get(0);
		}
		return null;
	}

}
