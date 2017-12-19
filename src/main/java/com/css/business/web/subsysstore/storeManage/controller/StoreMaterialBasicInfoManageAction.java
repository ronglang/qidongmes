package com.css.business.web.subsysstore.storeManage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysstore.bean.StoreMaterialBasicInfo;
import com.css.business.web.subsysstore.storeManage.service.StoreMaterialBasicInfoManageService;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/storeMaterialBasicInfoManageAction")
public class StoreMaterialBasicInfoManageAction
		extends
		BaseSpringSupportAction<StoreMaterialBasicInfo, StoreMaterialBasicInfoManageService> {
	private StoreMaterialBasicInfoManageService service;

	@Override
	public StoreMaterialBasicInfoManageService getEntityManager() {
		// TODO Auto-generated method stub
		return service;
	}

	public StoreMaterialBasicInfoManageService getService() {
		return service;
	}

	@Resource(name = "storeMaterialBasicInfoManageService")
	public void setService(StoreMaterialBasicInfoManageService service) {
		this.service = service;
	}

	private Gson gson = new Gson();

	/**
	 * 
	 * @Description: 列表页面
	 * @return
	 */
	@RequestMapping({ "getBasicInfo" })
	public String getBasicInfo() {
		return "storeManage/storeBasicInfo/storeMaterialBasicInfoForm";
	}
	
	/**
	 * 去材料类型页面
	 * @return
	 */
	@RequestMapping({ "toStoreType" })
	public String toStoreType(){
		
		return "storeManage/storeBasicInfo/storeTypeList";
	}
	
	
	/**
	 * 
	 * @Description: 修改 页面
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("toInputMaterial")
	public String toInputMaterial(HttpServletRequest request, Integer id) {
		if (id != null) {
			Gson gson = new Gson();
			StoreMaterialBasicInfo info = service.get(id);
			request.setAttribute("info", info);
		}
		return "storeManage/storeBasicInfo/addMaterialForm";
	}

	/**
	 * 
	 * @Description: 添加页面
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("toAddMaterial")
	public String toAddMaterial(HttpServletRequest request) {
		return "storeManage/storeBasicInfo/addMaterialForm";
	}

	/* CRUD */
	@RequestMapping({ "/queryForm" })
	@ResponseBody
	public Page queryForm(Page page, StoreMaterialBasicInfo entity,
			String endTime) {
		try {
			return this.service.getlist(page, entity, endTime);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return new Page();
		}
	}

	@RequestMapping({ "getSupplier" })
	@ResponseBody
	public Map[] getSupplier() {
		List<String> list = null;
		Map[] maps = null;
		try {
			maps = service.getSupplier();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return maps;
	}

	@RequestMapping({ "/deleteRecord" })
	@ResponseBody
	public String deleteRecord(StoreMaterialBasicInfo entity, String params) {
		StringBuilder response = new StringBuilder();
		response.append("-1");
		JSONArray jsonarr = JSONArray.fromObject(params);
		Iterator<?> iterator = jsonarr.iterator();
		while (iterator.hasNext()) {
			JSONObject obj = (JSONObject) iterator.next();
			response.append("," + obj.get("id"));
		}
		// do something
		try {
			// 传递rfid参数
			List<String> rfid = (List<String>) service.getRfid(response
					.toString());
			service.deleteRecord(response.toString(), rfid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "Success";
	}

	@RequestMapping({ "/saveOrUpdateRecord" })
	@ResponseBody
	public String saveOrUpdateRecord(StoreMaterialBasicInfo entity,
			String endTime, Integer id) throws Exception {
		try {
			service.saveOrUpdate(entity, id);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "Success";
	}

	// **--全部重写---------------------

	/**
	 * 
	 * @Description: 分页条件查询
	 * @param request
	 * @param param
	 * @param page
	 * @return
	 */
	@RequestMapping("getPageList")
	@ResponseBody
	public Page getPageList(HttpServletRequest request, String param, Page page) {
		Page queryPage = page;
		if (param != null && param != "") {
			Map<String, String> map = gson.fromJson(param,
					new TypeToken<Map<String, String>>() {
					}.getType());
			queryPage = service.getPageList(page, map);
		} else {
			queryPage = service.getPageList(page, new HashedMap());
		}
		

		return queryPage;

	}

	/**
	 * 
	 * @Description: 更新或者保存操作   
	 * @param request
	 * @param info
	 * @return
	 */
	@RequestMapping("saveOrUpdate")
	public String saveOrUpdate(HttpServletRequest request,StoreMaterialBasicInfo info) {
		@SuppressWarnings("unchecked")
		Map<String,String>resultMap = new HashedMap();
		try {
			
			if (info.getId() != null) {
				// 更新
				service.updateByCon(info, false);
				request.setAttribute("msg", "修改成功！");
			} else {
				boolean flag = service.addDataTypeService(request, info);
				if(flag){
					request.setAttribute("msg", "保存成功！");
				}else{
					request.setAttribute("msg", "此规格型号已存在！");
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "操作失败！");
		}
		
		return "storeManage/storeBasicInfo/addMaterialForm";
	}
	
	/**
	 * 
	 * @Description: 获得所有现有的类型
	 * @param request
	 * @return
	 */
	@RequestMapping("getAllType")
	@ResponseBody
	public List<String>getAllType(HttpServletRequest request){
		List<String> typeList =  service.getAllType();
		return typeList;
	}
	
	/**
	 * 
	 * @Description: 删除   
	 * @param request
	 * @param id 
	 * @return
	 */
	@RequestMapping("clearBean")
	@ResponseBody
	public Map<String ,String>clearBean(HttpServletRequest request,Integer id){
		Map<String,String>map = new HashedMap();
		StoreMaterialBasicInfo info = service.get(id);
		if (info == null  ) {
			map.put("msg", "删除失败,未找到相关数据");
			return map;
		}
		try {
			service.removeByCon(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("msg","删除成功");
		return map;
	} 
	
	/**
	 * 
	 * @Description: 模糊查询所有供应商   
	 * @param request
	 * @param info 模糊查询条件
	 * @return
	 */
	@RequestMapping("getAllDelivery")
	@ResponseBody
	public List<String>getAllDelivery(HttpServletRequest request,String info){
		List<String>list = service.getAllDelivery(info);
		return list;
	}
	
	/**
	 * 显示所有的大分类
	 * @return
	 */
	@RequestMapping("getAllDataBigType")
	@ResponseBody
	public List<StoreMaterialBasicInfo> getAllDataType(){
		return service.getAllBigDataTypeService();
	}
	
	/**
	 * 增加,编辑原料类型
	 * @param param
	 * @return
	 */
	@RequestMapping("saveAllTypes")
	@ResponseBody
	public Map<String,String> saveAllTypes(String param,HttpServletRequest request){
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Map<String,String> maps = new HashMap();
		Gson gson = new Gson();
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		try{
			if(map.get("id") != null && map.get("id") != ""){
				service.saveModifyTypesService(map);
				maps.put("success", "编辑类型成功！");
			}else{
				service.saveAllTypesService(map,request);
				maps.put("success", "添加类型成功！");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			maps.put("error", "操作失败！");
		}
		return maps;
	}
	
	/**
	 * 获取原料类型列表
	 * @param page
	 * @return
	 */
	@RequestMapping("getPageTypeList")
	@ResponseBody
	public Page getPageTypeList(Page page){
		return service.getPageTypeList(page);
	}
	
	/**
	 * 删除原料类型
	 * @param id
	 * @return
	 */
	@RequestMapping("clearMaterType")
	@ResponseBody
	public Map<String,String> clearMaterType(String id){
		Map<String,String> map = new HashMap<>();
		StoreMaterialBasicInfo sb = new StoreMaterialBasicInfo();
		sb.setId(Integer.valueOf(id));
		try {
			service.removeByCon(sb);
			map.put("success", "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", "删除失败！");
		}
		
		return map;
	}
	
	

}
