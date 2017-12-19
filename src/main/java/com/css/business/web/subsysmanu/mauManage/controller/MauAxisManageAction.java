
package com.css.business.web.subsysmanu.mauManage.controller;

import java.net.ServerSocket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauAxis;
import com.css.business.web.subsysmanu.mauManage.service.MauAxisManageService;
import com.css.commcon.util.SessionUtils;
import com.css.commcon.util.YorkUtil;
import com.css.common.util.EhCacheFactory;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@Controller
@RequestMapping("mauAxisManageAction")
public class MauAxisManageAction  extends BaseSpringSupportAction<MauAxis, MauAxisManageService>{
	//@Autowired
	@Resource(name="mauAxisManageService")
	private MauAxisManageService service;

	/**
	 * @return  service
	 */
	public MauAxisManageService getService() {
		return service;
	}
	@Override
	public MauAxisManageService getEntityManager() {
		// TODO Auto-generated method stub
		return service;
	}
	
	EhCacheFactory factory = EhCacheFactory.getInstance();
	
	@RequestMapping("toListPage")
	public String toListPage(){
		return "mauManage/mauaxis/mauAxisList";
	}
	@RequestMapping("toAddOrEdit")
	public String toAddOrEdit(HttpServletRequest request,String id){
		request.setAttribute("id", id);
		return "mauManage/mauaxis/addOrEdit";
	}
	
	private Gson gson = new Gson();
	
	@RequestMapping("getPageList")
	@ResponseBody
	public Page getPageList(HttpServletRequest request ,Page page ,String param){
		Map<String,String> map =gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		page = service.getListPage(page,map);
		return page;
	}
	@RequestMapping("saveOrUpdate")
	@ResponseBody
	public HashMap<String, Object>saveOrUpdate(HttpServletRequest request,String bean){
		try {
			Map<String,String> map = gson.fromJson(bean, new TypeToken<Map<String,String>>(){}.getType());
			int parseInt = Integer.parseInt(map.get("sum"));
			map.remove("sum");
			MauAxis mauAxis = new MauAxis();
			mauAxis.setAxisColor(map.get("axisColor"));
			mauAxis.setAxisInWidth(Integer.parseInt(map.get("axisInWidth")));
			mauAxis.setAxisName(map.get("axisName"));
			mauAxis.setAxisOutWidth(Integer.parseInt(map.get("axisOutWidth")));
			mauAxis.setCenterDiameter(Integer.parseInt(map.get("centerDiameter")));
			mauAxis.setExternalDiameter(Integer.parseInt(map.get("externalDiameter")));
			mauAxis.setInternalDiameter(Integer.parseInt(map.get("internalDiameter")));
			mauAxis.setCreateBy(SessionUtils.getUser(request).getAccount());
			mauAxis.setType(map.get("type"));
			mauAxis.setCreateDate(new Date());
			for (int i = 0; i < parseInt; i++) {
				mauAxis.setId(null);
				service.save(mauAxis);
			}
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
			return JsonWrapper.failureWrapperMsg("保存失败");
		}
		return JsonWrapper.successWrapper(null, "保存成功");
	}
	
	
	/**
	 * 
	 * @Description:页面绑定rfid
	 * @return
	 */
	@RequestMapping("getRfidCode")
	@ResponseBody
	public HashMap<String, Object>getRfidCode(){
		String rfid = factory.getMacShowCaChe(YorkUtil.线盘RFID);
		if (rfid != null && !"".equals(rfid) ) {
			factory.removeMacShowCache(YorkUtil.线盘RFID);
			return JsonWrapper.successWrapper(rfid, null);
		}else{
			return JsonWrapper.failureWrapperMsg("没找到");
		}
	}
	
	/**
	 * 
	 * @Description:app 扫描上传一个rfid
	 * @param request
	 * @param rfidCode
	 * @param token
	 * @return
	 */
	@RequestMapping("appScanRFID")
	@ResponseBody
	public HashMap<String, Object>appScanRFID(HttpServletRequest request,String rfidCode,String token){
		if (rfidCode != null && !"".equals(rfidCode)) {
			factory.addMacShowCache(YorkUtil.线盘RFID, rfidCode);
			return JsonWrapper.successWrapper(rfidCode, null);
		}
		return JsonWrapper.failureWrapperMsg("没找到");
	}
	
	/**
	 * 
	 * @Description:绑定rfid
	 * @param request
	 * @param id
	 * @param rfidCode
	 * @return
	 */
	@RequestMapping("bingdRfid")
	@ResponseBody
	public HashMap<String, Object>bingdRfid(HttpServletRequest request,String id ,String rfidCode){
		MauAxis mauAxis = service.get(Integer.parseInt(id));
		mauAxis.setRfid(rfidCode);
		try {
			service.updateByCon(mauAxis, false);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("绑定失败");
		}
		return JsonWrapper.successWrapper(rfidCode, "绑定成功");
	}
}
