package com.css.business.web.subsysmanu.mauManage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauException;
import com.css.business.web.subsysmanu.mauManage.dao.MauExceptionManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauExceptionManageService")
public class MauExceptionManageService extends BaseEntityManageImpl<MauException, MauExceptionManageDAO> {
	@Resource(name="mauExceptionManageDAO")
	//@Autowired
	private MauExceptionManageDAO dao;
	
	
	@Override
	public MauExceptionManageDAO getEntityDaoInf() {
		return dao;
	}


	/**   
	 * @Description: 分页查询   
	 * @param page
	 * @return         
	 */ 
	public Page queryPage(Page page) {
		String hql = "from MauException  where state='未处理' order by createDate DESC";
		// TODO Auto-generated method stub
		Page pageQuery = page;
		try {
			pageQuery = dao.pageQuery(hql , page.getPageNo(), page.getPagesize());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return pageQuery;
	}


	/**   
	 * @Description: 查询出现的异常个数   
	 * @param courseCode 工单编号
	 * @param macCode  机器编号
	 * @return         
	 */ 
	public Integer queryCount(String courseCode, String macCode) {
		// TODO Auto-generated method stub
		String sql = "select Count(*) from MauException where macCode='"+macCode+"' and courseCode='"+courseCode+"'";
		List list = dao.createSQLQuery(sql).list();
		if (list != null && list.size() > 0) {
			return Integer.parseInt(list.get(0).toString());
		}
		return null;
	}
	
	
	
}
