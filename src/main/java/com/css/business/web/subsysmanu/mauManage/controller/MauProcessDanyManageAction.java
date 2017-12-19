package com.css.business.web.subsysmanu.mauManage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysmanu.bean.MauProcessDany;
import com.css.business.web.subsysmanu.mauManage.service.MauProcessDanyManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;

/**
 * @TODO  :生产过程中，实时参数采集 
 * @author: 翟春磊
 * @DATE  : 2017年7月19日
 */
@Controller
@RequestMapping("/mauProcessDanyManageAction")
public class MauProcessDanyManageAction  extends BaseSpringSupportAction<MauProcessDany, MauProcessDanyManageService> {
		private MauProcessDanyManageService service;
		
		@Override
		public MauProcessDanyManageService getEntityManager() {
			return service;
		}

		public MauProcessDanyManageService getService() {
			return service;
		}

		@Resource(name="mauProcessDanyManageService")
		public void setService(MauProcessDanyManageService service) {
			this.service = service;
		}

}
