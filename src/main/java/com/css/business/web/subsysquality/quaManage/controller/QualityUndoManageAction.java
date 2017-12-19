package com.css.business.web.subsysquality.quaManage.controller;


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.css.business.web.subsysquality.bean.QualityUndoBasic;
import com.css.business.web.subsysquality.quaManage.service.QualityUndoService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
/**
 * 质量回溯控制层
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/qualityUndoManageAction")
public class QualityUndoManageAction extends
BaseSpringSupportAction<QualityUndoBasic, QualityUndoService>{

    @Autowired
	private QualityUndoService service;
	
	@Override
	public QualityUndoService getEntityManager() {
		
		return service;
	}
	public void setQualityUndoService(QualityUndoService service) {
		this.service = service;
	}
	
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "quaManage/quaUndo/qualityUndo";
	}
	
	@RequestMapping({"getPage"})
	@ResponseBody
	public Page getPage(Page p){
		Page page;
		try {
			page = service.getQualityUndoBasic(p);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 按时间查询
	 * 
	 * */
	@RequestMapping({ "queryPage" })
	@ResponseBody
	public Page queryPage(HttpServletRequest request,Page pageParam){
	    String startTime=request.getParameter("starttime");
	    String endTime= request.getParameter("endtime");
	    Page page= service.queryPage(pageParam, startTime,endTime);
	    return page;
	} 
	
}
