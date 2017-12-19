package com.css.business.web.subsysstore.storeManage.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.css.business.web.subsysmanu.mauManage.bean.OptionVo;
import com.css.business.web.subsysplan.plaManage.utils.JsonUtil;
import com.css.business.web.subsysstore.bean.StoreDgCk;
import com.css.business.web.subsysstore.bean.StoreObj;
import com.css.business.web.subsysstore.bean.StoreScrapRecord;
import com.css.business.web.subsysstore.storeManage.service.StoreDgCkDetailManageService;
import com.css.business.web.subsysstore.storeManage.service.StoreDgCkManageService;
import com.css.business.web.subsysstore.storeManage.service.StoreDgCkRfidManageService;
import com.css.business.web.subsysstore.storeManage.service.StoreObjManageService;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;

@Controller
@RequestMapping("/storeDgCkManageAction")
public class StoreDgCkManageAction
		extends
		BaseSpringSupportAction<StoreDgCk, StoreDgCkManageService> {

	@Autowired
	private StoreDgCkManageService service;
	
	@Override
	public StoreDgCkManageService getEntityManager() {
		return service;
	}

	public StoreDgCkManageService getService() {
		return service;
	}
	
	@Autowired
	private StoreDgCkDetailManageService storeDgCkDetailManageService;
	
	@Autowired
	private StoreDgCkRfidManageService storeDgCkRfidManageService;
	
	@Autowired
	private StoreObjManageService storeObjManageService;
	@Resource(name = "storeDgCkManageService")
	public void setService(StoreDgCkManageService service) {
		this.service = service;
	}

	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id,String outCode) {
		request.setAttribute("id", id);
		//回显数据到编辑页面
/*	if(null!=id){
			StoreDgCk obj=service.getObjectById(id);
			request.setAttribute("obj", obj);
		}*/
		/* List<String> list=service.getPickListCode();
		 List<OptionVo>	selectList =new ArrayList<>();
		 for (String obj : list) {
			 OptionVo vo = new OptionVo();
			 vo.setValue(obj);
			 selectList.add(vo);
		}
		 request.setAttribute("selectList", selectList);*/
		if(null!=outCode&&!"".equals(outCode)){
			request.setAttribute("outCode", outCode);
		}
		return "storeManage/storeoutrecord/storeDgCkTable";
	}
	
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id) {
		StoreDgCk objCk=null;
		try {
			objCk= service.getOutCodeById(id);
			if(null!=objCk){
			String 	outCode=objCk.getOutboundOrderCode();
				request.setAttribute("outCode", outCode);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "storeManage/storeoutrecord/storeDgCkTable1";
	}

	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);

		return "storeManage/storeoutrecord/storeDgCkList";
	}
	/**
	 * 
	 * 
	 * 高级查询 所有
	 * */
	@RequestMapping({ "getDataGridPage" })
	@ResponseBody
	public Page getDataGridPage(HttpServletRequest request, Integer id,
			Page param,String pickListCode,String objGgxh, String starttime, String endtime,String status,String  outboundOrderCode) {
		request.setAttribute("id", id);
		Page page = new Page();

		try {
			page = service.getStoreRecordPageList(param, pickListCode, objGgxh,
					 starttime, endtime,status,outboundOrderCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return page;
	}
	
	@RequestMapping({ "getDetail" })
	@ResponseBody
	public Page getDetail(HttpServletRequest request, Integer id,
			Page param,String outCode) {
		request.setAttribute("id", id);
		Page page = new Page();

		try {
			page = storeDgCkDetailManageService.getStoreRecordPageList(param, outCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return page;
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
			StoreDgCk mpm = new StoreDgCk();
			if (map.get("id") != null) {
				
				try {
					// TODO 执行update方法
					service.updateProductManageService(obj);
				} catch (Exception e) {
					e.printStackTrace();
					return JsonWrapper.failureWrapperMsg(mpm, "false");
				}
				
			}

		}
		return JsonWrapper.successWrapper(null, "成功");
		
	}
	
	/**
	 * 
	 * 导出
	 * */
	@RequestMapping({ "exportExecl" })
	@ResponseBody
	public HashMap<String,Object> exportExecl(HttpServletRequest request,HttpServletResponse response, Integer id,
			Page param, String pickListCode,String objGgxh, String starttime, String endtime,String status,String outboundOrderCode) {
		request.setAttribute("id", id);

		try {
			 service.exportExecl(request,response,param, objGgxh, pickListCode,
					 starttime, endtime,status, outboundOrderCode);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}

		return null;
	}
	
	@RequestMapping({ "exprotDetailExcel" })
	@ResponseBody
	public HashMap<String,Object> exprotDetailExcel(HttpServletRequest request,HttpServletResponse response, Integer id,
			Page param, String order) {
		request.setAttribute("id", id);

		try {
			 storeDgCkDetailManageService.exprotDetailExcel(response,param,order);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}

		return null;
	}
	
	/**
	 * 点击出库修改出库状态
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping({ "outStore" })
	@ResponseBody
	public HashMap<String,Object> outStore(HttpServletRequest request,String id) {
		Boolean flag =false;
		try {
			if(null!=id&&!"".equals(id)){
				String[] split = id.split(",");
				for (String idName : split) {
					 flag=service.updateStatusById(idName);
					 if(flag){
						 return JsonWrapper.successWrapper("出库成功");
					 }else{
						 return JsonWrapper.failureWrapperMsg("出库失败");
					 }
				}
			}else {
				return JsonWrapper.failureWrapperMsg("出库失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}

		return null;
	}
	
	@RequestMapping({ "getMaterialRfid" })
	@ResponseBody
	public HashMap<String,Object> getMaterialRfid(HttpServletRequest request,String rfidCode,String outBoundCode) {
		HashMap<String,Object>  map =new HashMap<>();
		try {
			if(null!=outBoundCode&&!"".equals(outBoundCode)){
				if(null!=rfidCode&&!"".equals(rfidCode)){
					String[] split = rfidCode.split(",");
					for (String rfid : split) {
						map=storeDgCkRfidManageService.saveRfidObj(rfid,outBoundCode);
						
					}
				}else{
					return JsonWrapper.failureWrapperMsg("未获取到物料RFID");
				}
			}else{
				return JsonWrapper.failureWrapperMsg("未录入出库单编号");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}

		return map;
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
	
	/**
	
	*获取下拉框材料方法
	*/
	@RequestMapping({ "getGgxh" })
	@ResponseBody
	public HashMap<String,List<String>> getGgxh(HttpServletRequest request,String pickListCode) {
		HashMap<String,List<String>> map = new HashMap<>();
		List<String> list =null;
		try {
				list =service.getPgxhByPickListCode(pickListCode);
				map.put("pgxh", list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
	
	/**
	
	*获取下拉框材料方法
	*/
	@RequestMapping({ "getPickListCode" })
	@ResponseBody
	public HashMap<String,List<String>> getPickListCode(HttpServletRequest request,String pickListCode) {
		HashMap<String,List<String>> map = new HashMap<>();
		List<String> list =null;
		try {
			list=service.getPickListCode();
				map.put("pickListCode", list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
	
	
/*	@RequestMapping({ "saveFormAndAttach" })
	@ResponseBody
	public HashMap<String, Object> saveFormAndAttach(HttpServletRequest request,  StoreDgCk entity,
			String uploadurl,String uploadurlorigname,String token) {
		 
		boolean flag = false;
		try {
			
			SysUser user = SessionUtils.getUser(request);
			service.saveFormAndAttach(request,user,entity,uploadurl,uploadurlorigname);
			return JsonWrapper.successWrapper(entity,"成功");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}


	}*/
	

	@RequestMapping({ "getOutStorageFromApp" })
	@ResponseBody
	public HashMap<String, Object> getOutStorageFromApp(HttpServletRequest request,String amount,String objGgxh,String pickListCode,String createBy) {
		//HashMap<String, Object> map = new HashMap<>();
		boolean flag = false;
		try {

			if(null==pickListCode&&"".equals(pickListCode)){
				return JsonWrapper.failureWrapperMsg("领料单号不能为空");
			}
			if(null==amount&&"".equals(amount)){
				return JsonWrapper.failureWrapperMsg("出库数量不能为空");
			}
			if(null==objGgxh&&"".equals(objGgxh)){
				return JsonWrapper.failureWrapperMsg("原料规格不能为空");
			}
			  flag= service.updateAndsaveOut(amount, objGgxh, pickListCode,createBy);
			  if(flag){
				  return JsonWrapper.successWrapper(flag,"成功");
			  }else{
				  return JsonWrapper.failureWrapperMsg(flag,"数量不能多于领料单的总量");
			  }
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
	
		
	
	
	
}
