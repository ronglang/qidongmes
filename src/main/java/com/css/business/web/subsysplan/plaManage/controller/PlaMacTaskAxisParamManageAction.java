package com.css.business.web.subsysplan.plaManage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




import com.css.business.web.subsysplan.bean.PlaMacTaskAxisParam;
import com.css.business.web.subsysplan.plaManage.service.PlaMacTaskAxisParamManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;


@Controller
@RequestMapping("/plaMacTaskAxisParamManageAction")
public class PlaMacTaskAxisParamManageAction extends
BaseSpringSupportAction<PlaMacTaskAxisParam,PlaMacTaskAxisParamManageService>{

	@Autowired
	private PlaMacTaskAxisParamManageService service;
	
	public void setQualityUndoDetailService(PlaMacTaskAxisParamManageService service){
		this.service = service;
	}
	
	@Override
	public PlaMacTaskAxisParamManageService getEntityManager() {
		return service;
	}

}
