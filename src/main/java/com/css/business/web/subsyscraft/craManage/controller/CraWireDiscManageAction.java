package com.css.business.web.subsyscraft.craManage.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.css.business.web.subsyscraft.bean.CraWireDisc;
import com.css.business.web.subsyscraft.craManage.service.CraWireDiscManageService;
import com.css.business.web.subsysplan.plaManage.utils.JsonUtil;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
@Controller
@RequestMapping("/craWireDiscManageAction")
public class CraWireDiscManageAction extends BaseSpringSupportAction<CraWireDisc, CraWireDiscManageService> {
	
	//@Autowired
	private CraWireDiscManageService service;
	
	@Override
	public CraWireDiscManageService getEntityManager() {
		return service;
	}

	public CraWireDiscManageService getService() {
		return service;
	}

	@Resource(name="craWireDiscManageService")
	public void setService(CraWireDiscManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		if(null!=id){
			//修改操作  需要回显数据
			CraWireDisc craWireDisc=service.getCraWireDiscById(id);
			if(null!=craWireDisc){
				Gson gson = new Gson();
				String json = gson.toJson(craWireDisc);
				request.setAttribute("dataMessage", json);
			}
		}else{
			request.setAttribute("dataMessage", false);
		}
		//添加数据 不需要回显数据
		return "craManage/wiredisc/craWireDiscEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/wiredisc/craWireDiscForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/wiredisc/craWireDiscList";
	}
	
	
	@RequestMapping({"toDataGridPage"})
	@ResponseBody
	public Page toDataGridPage(HttpServletRequest requst,Page param,
			String rfidNumber,String wireDiscPgxh,String status,String useStatus){
		
		Page page = new Page();

		try {
			page = service.getCraWireDiscPageList(param, rfidNumber, wireDiscPgxh,
					status, useStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return page;
	}
	
	
/**
	
	*获取下拉框材料方法
	*/
	@RequestMapping({ "getSelectOption" })
	@ResponseBody
	public HashMap<String,List<String>> getSelectOption(HttpServletRequest request) {
		HashMap<String,List<String>> map = new HashMap<>();
		List<String> list =null;
		try {
			String[] str={"线盘规格型号","线盘质地","线盘状态","线盘使用状态"};
			for (String obj : str) {
				list = service.getSelectOption(obj);
				map.put(obj, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
	@RequestMapping({ "toDelete" })
	@ResponseBody
	public HashMap<String,Object> toDelete(HttpServletRequest request,String id) {
		HashMap<String,Object> map = new HashMap<>();
		
		try {
			if(id!=null&&!"".equals(id)){
				String[] split = id.split(",");
				service.deleteBusiness(split);
				return JsonWrapper.successWrapper("删除成功");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
		return map;
	}
	
	
	@RequestMapping({"saveProductData"})
	@ResponseBody
	public Map<String,Object> saveProductData(String li) throws ParseException{
		JSONArray ja = JSONArray.fromObject(li);
		List<Object> list = JsonUtil.jsonToList(ja);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for (Object obj : list) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) obj;
			CraWireDisc mpm = new CraWireDisc();
			if (map.get("id") != null) {
				
				try {
					// TODO 执行update方法
					service.updateProductManageService(obj);
				} catch (Exception e) {
					e.printStackTrace();
					return JsonWrapper.failureWrapperMsg(mpm, "false");
				}
				
			} else {
				mpm.setRfidNumber(map.get("rfidNumber").toString());
				mpm.setWireDiscPgxh(map.get("wireDiscPgxh").toString());
				mpm.setStatus(map.get("status").toString());
				mpm.setUseStatus(map.get("useStatus").toString());
				mpm.setExternalDiameter(Double.parseDouble(map.get("externalDiameter").toString()));
				mpm.setBoreDiameter(Double.parseDouble(map.get("boreDiameter").toString()));
				mpm.setCapacity(0D);
				mpm.setCreateDate(new Date());
				mpm.setCreateBy("WL");
				// TODO 执行save方法
				if(!service.saveProductManageService(mpm)){
					return JsonWrapper.failureWrapperMsg(mpm, "false");
				}
				
			}

		}
		return JsonWrapper.successWrapper(null, "成功");
		
	}
	
/**
	
	*保存和修改
	*/
	@RequestMapping({ "toSaveData" })
	@ResponseBody
	public HashMap<String,Object> toSaveData(HttpServletRequest request,String rfidNumber,String wireDiscPgxh,
			String status,String useStatus,Double externalDiameter,Double boreDiameter,String materialTexture,Integer id,Double capacity) {
		HashMap<String,Object>  map = new HashMap<>();
		try {
			if(null!=wireDiscPgxh&&!"".equals(wireDiscPgxh)){
				String[] split = wireDiscPgxh.split(",");
				wireDiscPgxh=split[0];
			}
			if(null!=status&&!"".equals(status)){
				String[] split = status.split(",");
				status=split[0];
			}
			if(null!=useStatus&&!"".equals(useStatus)){
				String[] split = useStatus.split(",");
				useStatus=split[0];
			}
			if(null!=materialTexture&&!"".equals(materialTexture)){
				String[] split = materialTexture.split(",");
				materialTexture=split[0];
			}
			//获取当前登录人
			 SysUser user = SessionUtils.getUser(request);
			if(null!=id&&!"".equals(id)){
				//修改操作
				service.updateById(rfidNumber,wireDiscPgxh,
						 status, useStatus, externalDiameter, boreDiameter,materialTexture,capacity, id);
				
			}else{
				//增加操作
				CraWireDisc cwd= new CraWireDisc();
				cwd.setBoreDiameter(boreDiameter);
				cwd.setCreateDate(new Date());
				cwd.setRfidNumber(rfidNumber);
				cwd.setExternalDiameter(externalDiameter);
				cwd.setStatus(status);
				cwd.setUseStatus(useStatus);
				cwd.setWireDiscPgxh(wireDiscPgxh);
				cwd.setCreateBy(user.getName());
				cwd.setMaterialTexture(materialTexture);
				//容量计算
				cwd.setCapacity(capacity);
				//材质先不管
				service.save(cwd);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
	
}
