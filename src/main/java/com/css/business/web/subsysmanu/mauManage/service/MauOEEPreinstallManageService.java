package com.css.business.web.subsysmanu.mauManage.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauOEEPreinstall;
import com.css.business.web.subsysmanu.mauManage.dao.MauOEEPreinstallManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauOEEPreinstallManageService")
public class MauOEEPreinstallManageService extends BaseEntityManageImpl<MauOEEPreinstall, MauOEEPreinstallManageDAO> {
	@Resource(name="mauOEEPreinstallManageDAO")
	//@Autowired
	private MauOEEPreinstallManageDAO dao;
	
	
	@Override
	public MauOEEPreinstallManageDAO getEntityDaoInf() {
		return dao;
	}

	
	/**
	 * 
	 * @Description:分页查询
	 * @param page
	 * @param map
	 * @return Page
	 */
	public Page getListPage(Page page, Map<String, String> map) {
		String hql = " from MauOEEPreinstall where 1 = 1";
		StringBuilder sb = new StringBuilder(hql);
		if (map != null && map.size() > 0) {
			if (map.get("macCode")!= null && !"".equals(map.get("macCode"))) {
				sb.append(" and macCode like '%"+map.get("macCode")+"%'") ;
			}
			if (map.get("seqName")!= null && !"".equals(map.get("seqName"))) {
				sb.append(" and seqName like '%"+map.get("seqName")+"%'") ;
			}
			if (map.get("setMonth")!= null && !"".equals(map.get("setMonth"))) {
				sb.append(" and setMonth = '"+map.get("setMonth")+"'") ;
			}
		}
		sb.append(" order by setMonth");
		Page pageQuery = page;
		try {
			pageQuery = dao.pageQuery(sb.toString(), page.getPageNo(), page.getPagesize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageQuery;
	}

	/**
	 * 
	 * @Description:修改月份设定，同时修改月份统计的设定值
	 * @param mauBean
	 * @return boolean
	 */
	public boolean updateBeanAndMonth(MauOEEPreinstall mauBean) {
		boolean flag = false;
		try {
			//修改月设定
			this.updateByCon(mauBean, false);
			//修改月统计
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return flag;
		return true;
		
	}
	/**
	 * 获取表中所有工序名称
	 * @return
	 */
	public List<String> getAllSeqNameService(){
		String sql = "select seq_name from mau_oee_preinstall group by seq_name";
		@SuppressWarnings("unchecked")
		List<String> list = dao.createSQLQuery(sql).list();
		return list;
	}
	
	/**
	 * 获取表中所有工序名称
	 * @return
	 */
	public List<String> getAllMacCodeService(){
		String sql = "select mac_code from mau_oee_preinstall group by mac_code";
		@SuppressWarnings("unchecked")
		List<String> list = dao.createSQLQuery(sql).list();
		return list;
	}
	
	
	
	
	
}
