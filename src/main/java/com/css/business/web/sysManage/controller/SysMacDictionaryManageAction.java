package com.css.business.web.sysManage.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysquality.quaManage.service.QualityProductPlanManageService;
import com.css.business.web.sysManage.bean.SysMacDictionary;
import com.css.business.web.sysManage.service.SysMacDictionaryManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;

@Controller
@RequestMapping("sysMacDictionaryManageAction")
public class SysMacDictionaryManageAction extends BaseSpringSupportAction<SysMacDictionary, SysMacDictionaryManageService>{

	
	
	// @Autowired
		private SysMacDictionaryManageService service;

		@Override
		public SysMacDictionaryManageService getEntityManager() {
			return service;
		}
		
		/**
		 * 
		 * @Description: 根据macCode获得参数信息   
		 * @param macCode
		 * @return
		 */
		@RequestMapping("getMacParamByMacCode")
		@ResponseBody
		public HashMap<String, Object> getMacParamByMacCode(String macCode){
			List<SysMacDictionary>list = service.getMacParamByMacCode(macCode);
			if (list != null) {
				return JsonWrapper.successWrapper(list);
			}
			return JsonWrapper.failureWrapperMsg("未找到相关信息");
		}
	
	
	
	
	
}
