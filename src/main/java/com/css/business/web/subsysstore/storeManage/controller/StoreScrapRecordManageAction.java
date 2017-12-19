package com.css.business.web.subsysstore.storeManage.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysplan.plaManage.utils.JsonUtil;
import com.css.business.web.subsysstore.bean.StoreScrapRecord;
import com.css.business.web.subsysstore.storeManage.service.StoreScrapRecordManageService;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;

@Controller
@RequestMapping("/storeScrapRecordManageAction")
public class StoreScrapRecordManageAction
		extends
		BaseSpringSupportAction<StoreScrapRecord, StoreScrapRecordManageService> {

	@Autowired
	private StoreScrapRecordManageService service;

	@Override
	public StoreScrapRecordManageService getEntityManager() {
		return service;
	}

	public StoreScrapRecordManageService getService() {
		return service;
	}

	@Resource(name = "storeScrapRecordManageService")
	public void setService(StoreScrapRecordManageService service) {
		this.service = service;
	}

	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id) throws ParseException {
		request.setAttribute("id", id);
		if(null!=id){
			//修改操作  把数据回显到编辑页面
			StoreScrapRecord storeScrapRecord=service.getStoreScrapRecordById(id);
			request.setAttribute("storeScrapRecord", storeScrapRecord);
		}
		return "storeManage/storeScrapRecordEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		String url=service.getReportUrlById(id);
		request.setAttribute("url", url);
		return "storeManage/storeScrapRecordForm";
	}
	/**
	 * 
	 * 显示报废物料的总量
	 * */
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		String msg = "";
		try {
			msg = service.getTotalMsg(request);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != msg && !"".equals(msg)) {
			request.setAttribute("msg", msg);
		}

		return "storeManage/storeScrapRecordList";
	}
	/**
	 * 
	 * 
	 * 高级查询 所有
	 * */
	@RequestMapping({ "getDataGridPage" })
	@ResponseBody
	public Page getDataGridPage(HttpServletRequest request, Integer id,
			Page param, String materialName, String model, String color,
			String starttime, String endtime) {
		request.setAttribute("id", id);
		Page page = new Page();

		try {
			page = service.getStoreRecordPageList(param, materialName, model,
					color, starttime, endtime);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return page;
	}
/*	
	@RequestMapping({"saveProductData"})
	@ResponseBody
	public Map<String,Object> saveProductData(String li) throws ParseException{
		JSONArray ja = JSONArray.fromObject(li);
		List<Object> list = JsonUtil.jsonToList(ja);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for (Object obj : list) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) obj;
			StoreScrapRecord mpm = new StoreScrapRecord();
			if (map.get("id") != null) {
				
				try {
					// TODO 执行update方法
					service.updateProductManageService(obj);
				} catch (Exception e) {
					e.printStackTrace();
					return JsonWrapper.failureWrapperMsg(mpm, "false");
				}
				
			} else {
				mpm.setBatchCode(map.get("batchCode").toString());
				mpm.setRfidCode(map.get("rfidCode").toString());
				mpm.setModel(map.get("model").toString());
				mpm.setColor(map.get("color").toString());
				mpm.setAmount(Double.parseDouble(map.get("amount").toString()));
				mpm.setUnit(map.get("unit").toString());
				mpm.setMaterialRalation(map.get("materialRalation").toString());
				mpm.setHandler(map.get("handler").toString());
				mpm.setHandleDate(sf.parse(map.get("handleDate").toString()));
				mpm.setHandleIdea(map.get("handleIdea").toString());
				mpm.setStatus(map.get("status").toString());
				mpm.setHandleAfterPosition(map.get("handleAfterPosition").toString());
				mpm.setRemark(map.get("remark").toString());
				mpm.setCreateDate(new Date());
				mpm.setCreateBy("WL");
				// TODO 执行save方法
				if(!service.saveProductManageService(mpm)){
					return JsonWrapper.failureWrapperMsg(mpm, "false");
				}
				
			}

		}
		return JsonWrapper.successWrapper(null, "成功");
		
	}*/
	/**
	 * 
	 * 导出
	 * */
	@RequestMapping({ "exportExecl" })
	@ResponseBody
	public HashMap<String,Object> exportExecl(HttpServletRequest request,HttpServletResponse response, Integer id,
			Page param, String materialName, String model, String color,
			String starttime, String endtime) {
		request.setAttribute("id", id);

		try {
			 service.exportExecl(request,response,param, materialName, model,
					color, starttime, endtime);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}

		return null;
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
			 list = service.getSelectOption();
			 map.put("select", list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
	
	@RequestMapping({ "saveFormAndAttach" })
	@ResponseBody
	public HashMap<String, Object> saveFormAndAttach(HttpServletRequest request,  StoreScrapRecord entity,
			String uploadurl,String uploadurlorigname,String token) {
		 
		boolean flag = false;
		try {
			if(null!=entity){
				String materialName = entity.getMaterialName();
				if(null!=materialName&&!"".equals(materialName)){
					String[] split = materialName.split(",");
					entity.setMaterialName(split[0]);
				}
				entity.setHandleIdea("待处理");
				entity.setStatus("待报废");
			} 
			SysUser user = SessionUtils.getUser(request);
			entity.setCreateBy(user.getAccount());
			service.saveFormAndAttach(request,user,entity,uploadurl,uploadurlorigname);
			//保存一条纪录自动通知质检员
			//调用质检接口 ，传入原料信息，通知质检员，调用接口返回通知成功
			/*flag =service.getObjFromCheck(String batchCode,String rfidCode,
					String model,String amount,String unit,String materialRalation,String remark);*/
			//如果质检报废就修改状态为报废  如果不能报废就删除该条记录
			/*if(flag==true){
				entity.setStatus("报废");
				service.updateByCon(entity, true);
			}else{
				service.deleteById(entity.getId());
			}*/
			//然后调用质检接口 获取质检信息 封装成报废记录
			return JsonWrapper.successWrapper(entity,"成功");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}


	}
	@RequestMapping({ "toDelete" })
	@ResponseBody
	public HashMap<String, Object> toDelete(HttpServletRequest request,String ids) {
		//HashMap<String, Object> map = new HashMap<>();
		boolean flag = false;
		String msg="";
		try {
			if(null!=ids){
				flag=service.deleteRecordByids(ids);
				if(flag){
					msg="成功";
					return JsonWrapper.successWrapper(msg,"成功");
				}else{
					msg="失败";
					return JsonWrapper.failureWrapperMsg(msg,"失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
		return null;
	}

	@RequestMapping({ "getObjectFromApp" })
	@ResponseBody
	public HashMap<String, Object> getObjectFromApp(HttpServletRequest request,String rfidAndRemark) {
		//HashMap<String, Object> map = new HashMap<>();
		boolean flag = false;
		try {
			SysUser user = SessionUtils.getUser(request);
			if(null!=rfidAndRemark&&!"".equals(rfidAndRemark)){
				//去数据库插入该RFID原材料的入库信息，然后通知刘欣手机端和杨超的质检端通知质检员，返回的信息由协商定
				List<StoreScrapRecord> saveFromApp = service.saveFromApp(request,user,rfidAndRemark);
				//然后调用质检接口 获取质检信息 封装成报废记录
				if(null!=saveFromApp&&saveFromApp.size()>0){
					return JsonWrapper.successWrapper(saveFromApp,"成功");
				}else{
					return JsonWrapper.failureWrapperMsg("失败");
				}
			}else{
				return JsonWrapper.failureWrapperMsg("还未接收到信息");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
	
	/*	
		@RequestMapping({ "tellCheckSave"})
		@ResponseBody
		public HashMap<String, Object> tellCheckSave(HttpServletRequest request,String batchCode,String rfidCode,
				String model,String amount,String unit,String materialRalation,String remark) {
			//HashMap<String, Object> map = new HashMap<>();
			boolean flag = false;
			try {
				
				SysUser user = SessionUtils.getUser(request);

				//调用质检接口 ，传入原料信息，通知质检员，调用接口返回通知成功
				flag =service.getObjFromCheck(String batchCode,String rfidCode,
						String model,String amount,String unit,String materialRalation,String remark);
				//然后调用质检接口 获取质检信息 封装成报废记录
				if(flag==true){
					return JsonWrapper.successWrapper(flag,"成功");
				}else{
					return JsonWrapper.failureWrapperMsg("失败");
				}
			
					//return JsonWrapper.failureWrapperMsg("还未接收到信息");
				
			} catch (Exception e) {
				e.printStackTrace();
				return JsonWrapper.failureWrapperMsg(e.getMessage());
			}

	}*/
	/**
	 * 获取质检员传过来的信息 封装对象
	 * */
	@RequestMapping({ "getScriptRecordFromZj" })
	@ResponseBody
	public HashMap<String, Object> getScriptRecordFromZj(HttpServletRequest request,String rfidAndRemark) {
		//HashMap<String, Object> map = new HashMap<>();
		boolean flag = false;
		try {
			
			SysUser user = SessionUtils.getUser(request);
			if(null!=rfidAndRemark&&!"".equals(rfidAndRemark)){
				//去数据库插入该RFID原材料的入库信息，然后通知刘欣手机端和杨超的质检端通知质检员，返回的信息由协商定
				List<StoreScrapRecord> saveFromApp = service.saveFromApp(request,user,rfidAndRemark);
				//然后调用质检接口 获取质检信息 封装成报废记录
				//修改该记录的状态
				if(flag==true){
					return JsonWrapper.successWrapper(saveFromApp,"成功");
				}else{
					return JsonWrapper.failureWrapperMsg("失败");
				}
			}else{
				return JsonWrapper.failureWrapperMsg("还未接收到信息");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}


	}
	
	
	/**
	 * 仓库电子看板   报废材料情况
	 * @return
	 * @author JS
	 */
	@RequestMapping({"getScrapStock"})
	@ResponseBody
	public List<StoreScrapRecord> getScrapStock(){
		List<StoreScrapRecord> list = service.getScrapStock();
		return list;
	}
	
	
}
