package com.css.business.web.subsysmanu.mauManage.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauProduct;
import com.css.business.web.subsysmanu.mauManage.service.MauProductManageService;
import com.css.business.web.subsysplan.plaManage.utils.JsonUtil;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.bean.TreeVO;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/mauProductManageAction")
public class MauProductManageAction extends BaseSpringSupportAction<MauProduct, MauProductManageService> {
	
	@Resource(name = "mauProductManageService")
	private MauProductManageService service;
	
	@Override
	public MauProductManageService getEntityManager() {
		// TODO Auto-generated method stub
		return service;
	}
	
	@RequestMapping({"product"})
	public String toProductManage(){
		
		return "mauManage/mauProductManage";
	}
	
	@RequestMapping({"toTypePage"})
	public String toProductType(){
		return "mauManage/mauProductTypeManage";
	}
	
	@RequestMapping({"productform"})
	public String toProductForm(HttpServletRequest request, Integer id,String data){
		try {
			String decode = "";
			if(data !=null && !"".equals(data)){
				decode = URLDecoder.decode(data,"UTF-8");
				request.setAttribute("data", decode);
			}else{
				request.setAttribute("data", "false");
			}
			request.setAttribute("id", id);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return "mauManage/mauProductFormManage";
	}
	
	/**
	 * 封装MatgerGgxh的下拉框数据
	 * @return
	 * @author JS
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping({"getAllProType"})
	@ResponseBody
	public Map[] getAllProType(){
		Map[] proTypes = service.queryProTypes();
		return proTypes;
	}
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping({"getProGgxh"})
	@ResponseBody
	public Map[] getProGgxh(){
		Map[] ggxh = service.getProGgxh();
		return ggxh;
	}
	
	@RequestMapping({"productType"})
	public String toProTypePage(){
		return "mauManage/mauProductTypeManage";
	}
	
	
	@RequestMapping({"getProductList"})
	@ResponseBody
	public Page getProductList(Page p,String pro_name,String pro_type){
		Page page = service.getProductListDao(p, pro_name,pro_type);
		return page;
	}
	
	/*@RequestMapping({"saveProductData"})
	@ResponseBody
	public Map<String,Object> saveProductData(HttpServletRequest request,String li){
		Gson gson= new Gson();
		SysUser user = SessionUtils.getUser(request);
		List<MauProduct> mauList = gson.fromJson(li, new TypeToken<List<MauProduct>>(){}.getType());
		JSONArray ja = JSONArray.fromObject(li);
		List<Object> list = JsonUtil.jsonToList(ja);
		for (Object obj : list) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) obj;
			MauProduct mpm = new MauProduct();
			
			//MauProduct mpm = gson.fromJson((String) obj, MauProduct.class);
			if (map.get("id") != null) {
				try {
					// TODO 执行update方法
				//	service.updateProductManageService(obj);
					String pid =  service.queryIdByPcode(mauList.get(0).getPro_type());
					String[] split = mauList.get(0).getPro_code().split("-");
					mauList.get(0).setPro_code(split[0]+"-"+pid+"-"+split[2]);
					service.updateByCon(mauList.get(0), false);
					
				} catch (Exception e) {
					e.printStackTrace();
					//return JsonWrapper.failureWrapperMsg(mpm, "false");
				}
				
			} else {
				//mpm.setPro_code(map.get("pro_code").toString());
				//String proType = map.get("pro_type").toString();
				//mpm.setPro_color(map.get("pro_color").toString());
				//mpm.setPro_length(Integer.parseInt(map.get("pro_length").toString()));
				//mpm.setUnit(map.get("unit").toString());
				//mpm.setPacking_type(map.get("packing_type").toString());
				//mpm.setPacking_mater(map.get("packing_mater").toString());
				
				mpm.setRemark(map.get("remark").toString());
				//mpm.setCreateBy("TG");
				
				//通过type去查到父id 作为pcode
				String pid =  service.queryIdByPcode(map.get("pro_type").toString());
				mpm.setP_code(pid.toString());
				//id的生产策略 如果最大值小于100, 就现在的最大值加100,如果大于100 就不管了,这是给100个品类 
				Integer maxId = service.queryMaxId();
				if (maxId < 100) {
					Integer id = 100 + maxId;
					mpm.setId(id);
				}
				
				mpm.setPro_name(map.get("pro_name").toString());
				mpm.setPro_type(map.get("pro_type").toString());
				mpm.setRoute_code(map.get("route_code").toString());
				mpm.setCreateDate(new Date());
				mpm.setCreateBy(user.getAccount());
				mpm.setPro_code(map.get("pro_code").toString());
				//  执行save方法
				boolean flag = service.saveProductManageService(mpm);
				
				//procode 生成策略  p+父id+最少5位
				Integer id = mpm.getId();
				String rid = "";
				if (id < 10) {
					rid = "0000" + id;
				} else if (id >= 10  && id < 100){
					rid = "000" + id;
				} else if (id >= 100  && id < 1000){
					rid = "00" + id;
				} else if (id >= 1000  && id < 10000){
					rid = "0" + id;
				} else {
					rid = "" + id;
				}
				String pro_code  = "p-"+pid+"-"+rid;
				mpm.setPro_code(pro_code);
				try {
					
					service.updateByCon(mpm,false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!flag){
					return JsonWrapper.failureWrapperMsg(mpm, "false");
				}
			}
		}
		return JsonWrapper.successWrapper(null, "success");
		
	}*/
	
	@RequestMapping({"saveProductData"})
	@ResponseBody
	public Map<String,Object> saveProductData(HttpServletRequest request,String mauproduct){
		try {
			Gson gson= new Gson();
			SysUser user = SessionUtils.getUser(request);
			String decode = URLDecoder.decode(mauproduct,"UTF-8");
			MauProduct mauProduct2 = gson.fromJson(decode, MauProduct.class);
			mauProduct2.setPro_code(mauProduct2.getPro_name());
			mauProduct2.setP_code(mauProduct2.getPro_ggxh());
			if (mauProduct2.getId() != null ) {
				service.updateByCon(mauProduct2, false);
			} else {	
				mauProduct2.setCreateDate(new Date());
				mauProduct2.setCreateBy(user.getAccount());
				service.save(mauProduct2);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(null, "false");
		}
		
		return JsonWrapper.successWrapper(null, "success");
	}
	
	@RequestMapping({"clearProductData"})
	@ResponseBody
	public Map<String,Object> clearProductData(String ids){
		JSONArray ja = JSONArray.fromObject(ids);
		List<Object> list = JsonUtil.jsonToList(ja);
		for (Object obj : list) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) obj;
			try {
				// TODO 执行delete方法
				Integer id = Integer.parseInt(map.get("id").toString());
				service.deleteProductManageService(id);
			} catch (Exception e) {
				e.printStackTrace();
				return JsonWrapper.failureWrapperMsg(map, "false");
			}
				
		}
		return JsonWrapper.successWrapper(null, "success");
	}
	
	

	/**
	 * 生成树
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "nodeTree" })
	@ResponseBody
	public HashMap<String, Object> nodeTree(HttpServletRequest request,String qcolName,String qcolValue)  {
		List<TreeVO> list;
		try {
			list = this.getEntityManager().nodeTree(SessionUtils.getUser(request) ,qcolName,qcolValue);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询树数据错误",e);
			return JsonWrapper.successWrapper("查询树数据错误");
		}
		
	}
	
	/**
	 * 
	 * @Description: 得到产品类型   
	 * @return
	 */
	@RequestMapping("getProTypes")
	@ResponseBody
	public Page getProTypes(Page p){
		Page page = service.queryAllProTypes(p);
		return page;
	}
	
	/**
	 * 
	 * @Description: 添加保存产品类型   
	 * @param request
	 * @param typeName
	 * @return
	 */
	@RequestMapping("saveProType")
	@ResponseBody
	public Map<String,String> saveProType(HttpServletRequest request,String typeName,String p_type){
		Map<String,String> map = new HashMap<>();
		try {
			SysUser user = SessionUtils.getUser(request);
			if (typeName != null  && typeName != "") {
				String msg = service.saveProType(p_type,typeName,user.getAccount());
				map.put("msg", msg);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("msg", "保存失败");
		}
		return map;
	}
	
	/**
	 * 修改产品类型：          注需要优化，类型使用时禁止修改，后续优化
	 * @param request
	 * @param param
	 * @return
	 */
	@RequestMapping("modifyProType")
	@ResponseBody
	public Map<String,String> modifyProType(HttpServletRequest request,String param,String beforValue){
		Gson gson = new Gson();
		List<MauProduct> list = gson.fromJson(param, new TypeToken<List<MauProduct>>(){}.getType());
		Map<String,String> maps = service.modifyProTypeService(list,beforValue);
		return maps;
	}
	
	/**
	 * 删除产品类型：         注需要优化，类型使用时禁止删除，后续优化
	 * @param id
	 * @return
	 */
	@RequestMapping("clearProType")
	@ResponseBody
	public Map<String,String> clearProType(String param){
		Gson gson = new Gson();
		List<MauProduct> list = gson.fromJson(param, new TypeToken<List<MauProduct>>(){}.getType());
		Map<String,String> map = service.clearProTypeService(list);
		return map;
	}
	
	/**
	 * 
	 * @Description: 新建产品验证procode是否重复   
	 * @param procode 产品编码
	 * @return
	 */
	@RequestMapping("checkProCode")
	@ResponseBody
	public Map<String,String>checkProCode(String procode){
		Map<String,String>map = service.checkProCode(procode);
		return  map;
	}
	
	@RequestMapping({"getSelectProGgxh"})
	@ResponseBody
	public Page getSelectProGgxh(Page p){
		Page page = service.getSelectProGgxh(p);
		return page;
	}
	
	
}











