/**
 * 在单系统控制器
 */
package com.css.business.web.subsyssell.sellManage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsyssell.bean.SellSalesOrderDetails;
import com.css.business.web.subsyssell.sellManage.service.SellSalesOrderDetailsManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
@Controller
@RequestMapping("/sellSalesOrderDetailsManageAction")
public class SellSalesOrderDetailsManageAction extends BaseSpringSupportAction<SellSalesOrderDetails, SellSalesOrderDetailsManageService>{

	@Autowired
	private SellSalesOrderDetailsManageService sellSalesOrderDetailsManageService;
	
	@Override
	public SellSalesOrderDetailsManageService getEntityManager() {
		// TODO Auto-generated method stub
		return sellSalesOrderDetailsManageService;
	}
	
	@RequestMapping({"getListPageData"})
	@ResponseBody
	public Page getListPageData(HttpServletRequest request,Page page,SellSalesOrderDetails bean ){
		try {
//			page = super.dataGridPage(request, page, bean);
			
			//TODO 根据订单编号，查询明细记录
			page = sellSalesOrderDetailsManageService.findByOrderCode(request, page, bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
}
