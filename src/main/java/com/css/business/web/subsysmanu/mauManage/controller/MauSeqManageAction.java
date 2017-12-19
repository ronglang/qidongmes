package com.css.business.web.subsysmanu.mauManage.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauSeq;
import com.css.business.web.subsysmanu.mauManage.service.MauSeqManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
@Controller
@RequestMapping("/mauSeqManageAction")
public class MauSeqManageAction extends BaseSpringSupportAction<MauSeq, MauSeqManageService> {
	
	//@Autowired
	private MauSeqManageService service;
	
	@Override
	public MauSeqManageService getEntityManager() {
		return service;
	}

	public MauSeqManageService getService() {
		return service;
	}

	@Resource(name="mauSeqManageService")
	public void setService(MauSeqManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauSeqEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauSeqForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauSeqList";
	}
	
	/**
	 * 
	 * @Description: 获得所有的seq   
	 * @return
	 */
	@RequestMapping("getAllSeq")
	@ResponseBody
	public HashMap<String, Object>getAllSeq(){
		List<MauSeq>list = service.getAllSeq();
		if (list != null) {
			return JsonWrapper.successWrapper(list);
		}
		return JsonWrapper.failureWrapperMsg("未找到相关信息");
	}
	
	

}
