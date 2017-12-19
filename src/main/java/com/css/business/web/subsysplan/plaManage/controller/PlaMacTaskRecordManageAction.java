/**
 * @todo: 
 * @author : zhaichunlei
 * @date: 2017年12月13日
 */
package com.css.business.web.subsysplan.plaManage.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysplan.bean.PlaMacTaskRecord;
import com.css.business.web.subsysplan.plaManage.bean.PlaMacTaskVO;
import com.css.business.web.subsysplan.plaManage.service.PlaMacTaskRecordManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/plaMacTaskRecordManageAction")
public class PlaMacTaskRecordManageAction extends BaseSpringSupportAction<PlaMacTaskRecord, PlaMacTaskRecordManageService> {
	private PlaMacTaskRecordManageService service;
	
	@Override
	public PlaMacTaskRecordManageService getEntityManager() {
		return service;
	}
	
	public PlaMacTaskRecordManageService getService() {
		return service;
	}

	@Resource(name="plaMacTaskRecordManageService")
	public void setService(PlaMacTaskRecordManageService service) {
		this.service = service;
	}
	
	@RequestMapping("/queryMachineTaskSummer")
	@ResponseBody
	public List<PlaMacTaskVO> queryMachineTaskSummer(String maccode){
		return service.queryMachineTaskSummer(maccode);
	}
}
