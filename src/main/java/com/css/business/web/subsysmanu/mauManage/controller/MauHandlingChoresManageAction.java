package com.css.business.web.subsysmanu.mauManage.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauHandlingChores;
import com.css.business.web.subsysmanu.mauManage.service.MauHandlingChoresManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;


@Controller
@RequestMapping("/mauHandlingChoresManageAction")
public class MauHandlingChoresManageAction extends BaseSpringSupportAction<MauHandlingChores, MauHandlingChoresManageService> {
	
	//@Autowired
	private MauHandlingChoresManageService service;
	
	@Override
	public MauHandlingChoresManageService getEntityManager() {
		return service;
	}

	public MauHandlingChoresManageService getService() {
		return service;
	}

	@Resource(name="mauHandlingChoresManageService")
	public void setService(MauHandlingChoresManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauHandlingChoresEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauHandlingChoresForm";
	}
	@RequestMapping({"toDelete"})
	@ResponseBody
	public String toDelete(HttpServletRequest request,String id){
		
		service.toDelete(id);
		return "success";
	}
	/*
	 * 详情页面跳转
	 * 
	 */
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauHandlingChoresList";
	}
	
	/*
	 * @分页条件查询
	 * 
	 */
	@RequestMapping({"toDataGridPage"})
	@ResponseBody
	/*public Page getPage(HttpServletRequest request, MauHandlingChores ent){
		Page paramPage=new Page();
		try {
			paramPage=dataGridPage(request,paramPage,ent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paramPage;
	}*/
	public Page getPage(HttpServletRequest request,Page p,MauHandlingChores ent) throws Exception{
			return service.queryChoresDataService(p, ent);
	}
   /*
    * @保存编辑
    * 
    */
	@RequestMapping({"toSaveData"})
	@ResponseBody
	public String toSaveData(HttpServletRequest request,@RequestParam(value="id",required=false) Integer id,MauHandlingChores ent){
		service.choresEdit(ent);
		return "success";
	}
	
	/**
	 * 获取杂物名称下拉框数据
	 * @return
	 * @author JS
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping({"getChoresname"})
	@ResponseBody
	public Map[] getChoresname(){
		Map[] choresname = service.getChoresname();
		return choresname;
	}
	
	/**
	 * 获取物料编号下拉框数据
	 * @return
	 * @author JS
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping({"getMaterCode"})
	@ResponseBody
	public Map[] getMaterCode(){
		Map[] materCode = service.getMaterCode();
		return materCode;
	}
	
}

