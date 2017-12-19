package com.css.business.web.subsysquality.quaManage.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysquality.bean.QualityAsk;
import com.css.business.web.subsysquality.quaManage.dao.QualityAskManageDAO;
import com.css.common.util.DateUtil;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;


@Service("qualityAskManageService")
public class QualityAskManageService extends
		BaseEntityManageImpl<QualityAsk, QualityAskManageDAO> {
	@Resource(name = "qualityAskManageDAO")
	// @Autowired
	private QualityAskManageDAO dao;

	@Override
	public QualityAskManageDAO getEntityDaoInf() {
		return dao;
	}

	/**   
	 * @Description: 条件查询pageList   
	 * @param map
	 * @param page
	 * @return         
	 */ 
	public Page getPageList(Map<String, String> map, Page page) {
		// TODO Auto-generated method stub
		String  hql = "from  QualityAsk where 1 = 1 ";
		StringBuilder sb = new StringBuilder(hql);
		if (map != null && map.size() > 0) {
			if (map.get("askState") != null && map.get("askState") != "") {
				sb.append(" and  askState = '"+map.get("askState")+"'");
			}
			if (map.get("askType") != null && map.get("askType") != "") {
				sb.append(" and  askType = '"+map.get("askType")+"'");
			}
			if (map.get("finishBy") != null && map.get("finishBy") != "") {
				sb.append(" and  finishBy like '%"+map.get("finishBy")+"%'");
			}
		}
		Page pageQuery= page;
		try {
			pageQuery = dao.pageQuery(sb.toString(), page.getPageNo(), page.getPagesize());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return pageQuery;
	}

	/**   
	 * @Description: 获得未处理的呼叫   
	 * @return         
	 */ 
	public List<QualityAsk> getNoFinish() {
		// TODO Auto-generated method stub
		String hql = "from QualityAsk where askState ='未处理'";
		List<QualityAsk> list = dao.listQuery(hql);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param semiAxis
	 * @return         
	 */ 
	public QualityAsk getAskBySemiAxis(String semiAxis) {
		// TODO Auto-generated method stub
		String hql = " from QualityAsk where semiAxis = '"+semiAxis+"'";
		List<QualityAsk> list = dao.createQuery(hql).list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param sTATE_NOT_HANDLE 处理状态
	 * @return         
	 */ 
	public List<QualityAsk> getAskList(String state) {
		// TODO Auto-generated method stub
		String hql = "from QualityAsk where askState = '"+state+"' ";
		List<QualityAsk> list = dao.createQuery(hql).list();
		return list;
	}

	/**
	 * 质检部电子看板上面显示数据
	 * @return
	 */
	public List<QualityAsk> getDataListService(){
		String  hql = "from  QualityAsk";
		List<QualityAsk> list = dao.listQuery(hql);
		return list;
	}

	/**   
	 * @Description: 获得未处理的呼叫和当天的所有呼叫   
	 * @param sTATE_NOT_HANDLE
	 * @return         
	 */ 
	public List<QualityAsk> getTodayAskList(String state) {
		String date = DateUtil.format(new Date(), "yyyy-MM-dd");
		// TODO Auto-generated method stub
		String start = date+" 00:00:00";
		String end = date+" 23:59:59";
		String hql = "from QualityAsk where askState = '"+state+"' or  (createDate>='"+start+"' and createDate <= '"+end+"')";
		List<QualityAsk> list = dao.createQuery(hql).list();
		return list;
	}
	
	
	
	
	
}
