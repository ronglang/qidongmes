package com.css.business.web.subsysstore.storeManage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauRfid;
import com.css.business.web.subsysstore.bean.StoreCoilSa;
import com.css.business.web.subsysstore.storeManage.service.StoreCoilSaManageService;
import com.css.business.web.subsysstore.storeManage.service.StoreObjManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
@Controller
@RequestMapping("/storeCoilSaManageAction")
public class StoreCoilSaManageAction extends BaseSpringSupportAction<StoreCoilSa, StoreCoilSaManageService> {
	
	//@Autowired
	private StoreCoilSaManageService service;
	@Resource(name="storeObjManageService")
	private StoreObjManageService store;
	
	@Override
	public StoreCoilSaManageService getEntityManager() {
		return service;
	}

	public StoreCoilSaManageService getService() {
		return service;
	}

	@Resource(name="storeCoilSaManageService")
	public void setService(StoreCoilSaManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storeCoilSaEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storeCoilSaForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storeCoilSaList";
	}
	
	
	/**
	 * APP 
	 * 将区域信息返回到APP
	 * @param token
	 * @return
	 */
	@RequestMapping({"getStoreCoilSa"})
	@ResponseBody
	public HashMap<String, Object> getStoreCoilSa(String token){
		
		List<StoreCoilSa> list = service.getStoreCoilSa();
		return JsonWrapper.successWrapper(list, "");
	}
	
	/**
	 * 增加区域
	 * @return
	 */
	@RequestMapping({"saveStoreCoilSa"})
	@ResponseBody
	public HashMap<String, Object> saveStoreCoilSa(String token,String id, String rfid,String areaName,String areaType){
		StoreCoilSa scs = new StoreCoilSa();
		try {
			scs.setArea_name(areaName);
			scs.setAreaRfid(rfid.trim());
			scs.setArea_type(areaType);
			MauRfid mauRfid = store.getMauRfid(scs.getAreaRfid());
			if(mauRfid==null){
				return JsonWrapper.failureWrapperMsg(mauRfid, "该卡未初始化");
			}
			if(id ==null || "".equals(id) ){
				scs.setCreateDate(new Date());
				List<StoreCoilSa> name = service.getareaName(scs.getAreaRfid());
				if(name!=null && name.size()>0){
					return JsonWrapper.failureWrapperMsg(scs, "RFID卡与区域以绑定");
				}
				service.saveStoreCoilSa(scs);
			}else{
				scs.setId(Integer.parseInt(id));
				service.updateStoreCoilSa(scs);
			}
			return JsonWrapper.successWrapper(scs, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(scs, "保存失败");
		}
	}
	
	
	/**
	 * APP 删除区域
	 * @param token
	 * @param id
	 * @return
	 */
	@RequestMapping({"delStoreCoilSa"})
	@ResponseBody
	public HashMap<String , Object> delStoreCoilSa(String token,String id){
		try {
			service.delStoreCoilSa(Integer.parseInt(id));
			return JsonWrapper.successWrapper(id, "");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(id, "");
		}
		
	}
	
	/**
	 * APP挪盘接口
	 * @param rfids (包含线盘卡与地标卡)
	 * @return
	 */
	@RequestMapping({"changeAddress"})
	@ResponseBody
	public HashMap<String, Object> changeAddress(String rfids){
		
		try {
			return service.changeAddress(rfids);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
