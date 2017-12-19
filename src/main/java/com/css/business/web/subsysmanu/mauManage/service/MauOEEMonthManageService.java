package com.css.business.web.subsysmanu.mauManage.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauOEEMonth;
import com.css.business.web.subsysmanu.mauManage.dao.MauOEEMonthManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauOEEMonthManageService")
public class MauOEEMonthManageService extends BaseEntityManageImpl<MauOEEMonth, MauOEEMonthManageDAO> {
	@Resource(name="mauOEEMonthManageDAO")
	//@Autowired
	private MauOEEMonthManageDAO dao;
	
	
	@Override
	public MauOEEMonthManageDAO getEntityDaoInf() {
		return dao;
	}


	public Page getListPage(Page page, Map<String, String> map) {
		String sql = "select "
				+ "in_month in_month,"
				+ "mac_code mac_code,"
				+ "seq_name seq_name,"
				+ "start_min start_min,"
				+ "end_min end_min,"
				+ "rejects rejects,"
				+ "bits_pieces bits_pieces,"
				+ "overdoes overdoes,"
				+ "fact_output fact_output,"
				+ "fully_output fully_output,"
				+ "set_month_oee set_month_oee,"
				+ "mp.set_month_pr set_month_pr ,"
				+ "mp.set_month_or set_month_or,"
				+ "mp.set_month_qr set_month_qr"
				+ "from mau_month_count where 1 = 1";
		Page queryPage = page;
		try {
			queryPage = dao.pageSQLQueryVO(sql, page.getPageNo(), page.getPagesize(), new MauOEEMonth());
			List<MauOEEMonth> data = queryPage.getData();
			//TODO 完成月份的oee
			/*for (MauOEEMonth mauOEEMonth : data) {
				mauOEEMonth.get
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryPage;
	}
	
	
	
}
