package com.css.business.web.subsyssell.sellManage.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.business.web.subsyssell.bean.SellSalesOrderDetails;
import com.css.business.web.subsyssell.sellManage.dao.SellSalesOrderDetailsManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
@Service("sellSalesOrderDetailsManageService")
public class SellSalesOrderDetailsManageService extends
		BaseEntityManageImpl<SellSalesOrderDetails, SellSalesOrderDetailsManageDAO> {

	@Autowired
	private SellSalesOrderDetailsManageDAO sellSalesOrderDetailsManageDAO;
	
	@Override
	public SellSalesOrderDetailsManageDAO getEntityDaoInf() {
		// TODO Auto-generated method stub
		return sellSalesOrderDetailsManageDAO;
	}
	/**
	 * 根据订单编号，查询订单明细
	 * @param request
	 * @param page
	 * @param bean
	 */
	public Page findByOrderCode(HttpServletRequest request, Page page,
			SellSalesOrderDetails bean) {
		String hql = " from SellSalesOrderDetails where 1=1 ";
		String orderCode = bean.getOrderCode();
		if(orderCode == null || orderCode.isEmpty()){
			throw new RuntimeException("没有订单编号");
		}else{
			hql += " and orderCode = '"+orderCode+"' ";
		}
		Page pageQuery = sellSalesOrderDetailsManageDAO.pageQuery(hql, page.getPageNo(), page.getPagesize());
		//sellSalesOrderDetailsManageDAO.getByOrderCode( page,orderCode,hql);
		return pageQuery;
	}
	
}
