package com.css.business.web.subsysquality.quaManage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysplan.plaManage.utils.CalculaMaterUtil;
import com.css.business.web.subsysplan.vo.PlaDisPatchVo;
import com.css.business.web.subsysquality.bean.QualityUndoDetail;
import com.css.business.web.subsysquality.quaManage.service.QualityUndoDetailService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;



/**
 * 质量回溯详细信息控制层
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/qualityUndoDetailManageAction")
public class QualityUndoDetailManageAction extends
BaseSpringSupportAction<QualityUndoDetail,QualityUndoDetailService>{

	@Autowired
	private QualityUndoDetailService service;
	
	public void setQualityUndoDetailService(QualityUndoDetailService service){
		this.service = service;
	}
	
	@Override
	public QualityUndoDetailService getEntityManager() {
		return service;
	}

	/**
	 * 按照axisname 查询视图
	 * @param request
	 * @param scCode
	 * @return
	 */
	@RequestMapping({ "axisPage" })
	public String toListPage(HttpServletRequest request, String wscode){
		System.out.println(wscode);
		//通过轴名称得到对应的机台号
		//String mac = service.getMacs(workcode);
		request.setAttribute("wscode", wscode);
		return "quaManage/quaUndo/qualityUndoDetail";
	}
	
	
	/**
	 * 
	 * 按轴名称
	 * 
	 * */
	@RequestMapping({ "queryPage" })
	@ResponseBody
	public Page queryPage(HttpServletRequest request,Page pageParam){
	    String maccode=request.getParameter("mac_code");
	    String workcode = request.getParameter("workcode");
	    String seqstep = request.getParameter("seqstep");
	    Page page= service.queryPage(pageParam, maccode,workcode,seqstep);
	    return page;
	} 
	
	
	/**
	 * 
	 * 按轴名称
	 * 
	 * */
	@RequestMapping({ "getMacs" })
	@ResponseBody
	public HashMap<String,Object> getMacs(HttpServletRequest request, String wscode){
		return service.getMac(wscode);
	}
	
	
	/**
	 * 
	 * 按轴名称
	 * 
	 * */
	@RequestMapping({ "getLinks" })
	@ResponseBody
	public HashMap<String,Object> getLinks(HttpServletRequest request, String wscode){
		return service.getLinks(wscode);
	}


}
