package com.css.business.web.subsysmanu.mauManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauReplacementLibrary;
import com.css.business.web.subsysmanu.mauManage.service.MauReplacementLibraryManageService;
import com.css.business.web.subsysplan.plaManage.utils.JsonUtil;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 备件管理action
 * @author TG
 *
 */
@Controller
@RequestMapping("/mauReplacementManageAction")
public class MauReplacementManageAction extends BaseSpringSupportAction<MauReplacementLibrary, MauReplacementLibraryManageService>{
	

	@Resource(name="replacementLibraryManageService")
	private MauReplacementLibraryManageService service;
	
	@Override
	public MauReplacementLibraryManageService getEntityManager() {
		return service;
	}
	
	/**
	 * 去备件列表页面
	 * @return
	 */
	@RequestMapping({"toReplacementLibrary"})
	public String toReplacementLibrary(){
		return "mauManage/replacement_library/replacementList";
	}
	/**
	 * 去添加备件页面
	 * @return
	 */
	@RequestMapping({"toAddDataPage"})
	public String toAddDataPage(){
		return "mauManage/replacement_library/replacementAdd";
	}
	
	/**
	 * 去备件报废记录页面
	 * @return
	 */
	@RequestMapping("toRejectList")
	public String toRejectList(){
		return "mauManage/replacement_library/replacementRejectList";
	}
	
	/**
	 * 去备件领料记录页面
	 * @return
	 */
	@RequestMapping("toMaterialList")
	public String toMaterialList(){
		return "mauManage/replacement_library/replacementMaterialList";
	}
	
	/**
	 * 条件分页获取备件列表
	 * @return
	 */
	@RequestMapping({"getDataListAction"})
	@ResponseBody
	public Page getDataListAction(Page page,String param){
		Gson gson = new Gson();
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Page pageList = service.getAllDataListService(page,map);
		return pageList;
	}
	
	/**
	 * 添加备件
	 * @param param
	 * @return
	 */
	@RequestMapping({"addReplacementData"})
	@ResponseBody
	public Map<String,String> addReplacementData(HttpServletRequest request,String param){
		Gson gson = new Gson();
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Map<String,String> maps = service.addReplacementDataService(request,map); 
		return maps;
	}
	
	/**
	 * 添加备件报废信息
	 * @param param
	 * @return
	 */
	@RequestMapping({"saveRejectData"})
	@ResponseBody
	public Map<String,String> saveRejectData(HttpServletRequest request,String param){
		Gson gson = new Gson();
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Map<String,String> maps = service.saveRejectDataService(request,map); 
		return maps;
	}
	
	/**
	 * 添加备件领料信息
	 * @param param
	 * @return
	 */
	@RequestMapping({"saveMaterialData"})
	@ResponseBody
	public Map<String,String> saveMaterialData(HttpServletRequest request,String param){
		Gson gson = new Gson();
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Map<String,String> maps = service.saveMaterialDataService(request,map); 
		return maps;
	}
	
	/**
	 * 删除多行数据
	 * @return
	 */
	@RequestMapping({"clearReplacementData"})
	@ResponseBody
	public Map<String,String> clearReplacementData(String ids){
		Map<String,String> maps = new HashMap<>();
		JSONArray ja = JSONArray.fromObject(ids);
		List<Object> list = JsonUtil.jsonToList(ja);
		for (Object obj : list) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) obj;
			try {
				// TODO 执行delete方法
				Integer id = Integer.parseInt(map.get("id").toString());
				service.clearReplacementDataService(id);
				if(maps.get("success") == null || maps.get("success") == ""){
					maps.put("success", "删除成功！");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				if(maps.get("error") == null || maps.get("error") == ""){
					maps.put("error", "删除失败！");
				}
				
			}
				
		}
		return maps;
	}
	
	/**
	 * 修改一行备件信息数据
	 * @param request
	 * @param param
	 * @return
	 */
	@RequestMapping({"updateReplacementData"})
	@ResponseBody
	public Map<String,String> updateReplacementData(HttpServletRequest request,String param){
		Gson gson = new Gson();
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Map<String,String> maps = service.updateReplacementDataService(request,map);
		return maps;
	}
	/**
	 * 获取所有类型
	 * @return
	 */
	@RequestMapping("getReplacementType")
	@ResponseBody
	public List<String> getReplacementType(){
		
		return service.getReplacementTypeService();
	}
	
	/**
	 * 条件分页获取备件报废
	 * @return
	 */
	@RequestMapping({"getRejectList"})
	@ResponseBody
	public Page getRejectList(Page page,String param){
		Gson gson = new Gson();
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Page pageList = service.getRejectDataListService(page,map);
		return pageList;
	}
	
	/**
	 * 条件分页获取备件领料记录
	 * @return
	 */
	@RequestMapping({"getMaterialList"})
	@ResponseBody
	public Page getMaterialList(Page page,String param){
		Gson gson = new Gson();
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Page pageList = service.getMaterialDataListService(page,map);
		return pageList;
	}
	
	
	
	
	
	
}
