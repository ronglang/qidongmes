package com.css.business.web.subsyscraft.craManage.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsyscraft.bean.CraJyBomParam;
import com.css.business.web.subsyscraft.craManage.service.CraJyBomParamManageService;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
@Controller
@RequestMapping("/craJyBomParamManageAction")
public class CraJyBomParamManageAction extends BaseSpringSupportAction<CraJyBomParam, CraJyBomParamManageService> {
	
	//@Autowired
	private CraJyBomParamManageService service;
	
	@Override
	public CraJyBomParamManageService getEntityManager() {
		return service;
	}

	public CraJyBomParamManageService getService() {
		return service;
	}

	@Resource(name="craJyBomParamManageService")
	public void setService(CraJyBomParamManageService service) {
		this.service = service;
	}
	private Gson gson = new Gson();
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craCraftEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craCraftForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id,String proGgxh,Integer flag,String seqName){
		request.setAttribute("id", id);
		String decode = null;
		try {
			if(proGgxh !=null && !"".equals(proGgxh)){
				decode = URLDecoder.decode(proGgxh,"UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setAttribute("proGgxh", decode);
		request.setAttribute("flag", flag);
		request.setAttribute("seqName", seqName);
		return "craManage/seqBomParam/jyTable";
	}
	
	@RequestMapping("saveOrUpdateBean")
	@ResponseBody
	public HashMap<String, Object>saveOrUpdateBean(HttpServletRequest request,String bean){
		try {
			String decode = URLDecoder.decode(bean,"UTF-8");
			service.saveOrUpdateBean((List<CraJyBomParam>)gson.fromJson(decode, new TypeToken<List<CraJyBomParam>>(){}.getType()),SessionUtils.getUser(request).getAccount());
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败");
		}
		return JsonWrapper.successWrapper(null, "保存成功");
	}

	@RequestMapping("clearBean")
	@ResponseBody
	public HashMap<String, Object>clearBean(HttpServletRequest request,String id){
		try {
			service.deleteBusiness(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("删除失败");
		} 
		return JsonWrapper.successWrapper(null, "删除成功");
	}
	
	/**
	 * 
	 * @Description:通过规格型号和步骤去查询 可作为去详情和 查重的时候使用
	 * @param request
	 * @param proGgxh
	 * @param seqStep
	 * @return
	 */
	@RequestMapping("getByProGgxh")
	@ResponseBody
	public HashMap<String, Object>getByProGgxh(HttpServletRequest request,String proGgxh,String seqStep){
		CraJyBomParam craJyBomParam = service.getByProGgxh(proGgxh,seqStep);
		if (craJyBomParam != null) {
			return JsonWrapper.successWrapper(craJyBomParam);
		} else {
			return JsonWrapper.failureWrapper(craJyBomParam);
		}
	}
	
	@RequestMapping({"getGridByProGgxh"})
	@ResponseBody
	public Page getGridByProGgxh(Page p,String proGgxh){
		Page page = p;
		try {
			String decode = URLDecoder.decode(proGgxh,"UTF-8");
			page = service.getGridByProGgxh(p, decode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
}
