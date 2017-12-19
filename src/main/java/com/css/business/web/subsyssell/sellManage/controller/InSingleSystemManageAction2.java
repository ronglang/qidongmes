/**
 * 在单系统控制器
 */
package com.css.business.web.subsyssell.sellManage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsyssell.bean.SellContractDetail;
import com.css.business.web.subsyssell.sellManage.service.InSingleSystemManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;


/**
 * 在单系统
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/inSingleSystemManageAction2")
public class InSingleSystemManageAction2 extends BaseSpringSupportAction<SellContractDetail, InSingleSystemManageService>{

	@Override
	public InSingleSystemManageService getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 在单系统主页面
	 * @return
	 */
	@RequestMapping({"inSingleSystemPage"})
	public String inSingleSystem(){
		
		return "sellManage/inSingleSystem/inSingleSystemList2";
	}
	
	
}
