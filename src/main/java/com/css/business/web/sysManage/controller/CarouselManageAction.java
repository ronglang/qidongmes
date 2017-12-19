package com.css.business.web.sysManage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/carouselManageAction")
public class CarouselManageAction {

	@RequestMapping("/carousel")
	public String toSysresourceAddEditPage(){
		return "carousel";
	}
	
	
}
