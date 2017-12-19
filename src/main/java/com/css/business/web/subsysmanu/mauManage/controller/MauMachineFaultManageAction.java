package com.css.business.web.subsysmanu.mauManage.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
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

import com.css.business.web.subsysmanu.bean.MauMachineFault;
import com.css.business.web.subsysmanu.bean.MauMachineMaintain;
import com.css.business.web.subsysmanu.mauManage.service.MauMachineFaultManageService;
import com.css.business.web.subsysmanu.mauManage.service.MauMachineMaintainManageService;
import com.css.business.web.subsysplan.plaManage.utils.JsonUtil;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
/**
 * 
* @Title: MauMachineFaultManageAction.java   
* @Package com.css.business.web.subsysmanu.mauManage.controller   
* @Description: 机台故障信息记录表，记录每次机台故障，以便分析故障原因
* @author   TG
* @date 2017年9月08日 更改版
* @company  SMTC
 */
@Controller
@RequestMapping("/mauMachineFaultManageAction")
public class MauMachineFaultManageAction extends BaseSpringSupportAction<MauMachineFault, MauMachineFaultManageService> {
	
	
	private MauMachineFaultManageService service;
	@Autowired
	private MauMachineMaintainManageService mauMachineMaintainManageService;
	
	@Override
	public MauMachineFaultManageService getEntityManager() {
		return service;
	}

	public MauMachineFaultManageService getService() {
		return service;
	}

	@Resource(name="mauMachineFaultManageService")
	public void setService(MauMachineFaultManageService service) {
		this.service = service;
	}
	
	
	/**
	 * 跳转到故障维修管理页面
	 *@data:2017年9月2日
	@param request
	@return
	@autor:wl
	 */
	@RequestMapping({"toMachineFaultTable"})
	public String toMachineFaultTable(HttpServletRequest request){
		return "mauManage/maufault/maufaultTable";
	}
	
	/**
	 * 去工程部电子看板
	 * @param request
	 * @param id
	 * @return
	 */
//	@RequestMapping({ "toListPage" })
//	public String toListPage(HttpServletRequest request, Integer id){
//		request.setAttribute("id", id);
//		return "mauManage/mauMachineFaultList";
//	}
	//工程部电子看板
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/cendisplay/mauProjectKanbanPage";
	}
	//设备保养页面
	@RequestMapping({ "toProjectFault" })
	public String toProjectFault(){
		return "mauManage/cendisplay/childpage/projectFault";
	}
	//设备维修页面
	@RequestMapping({ "toProjectMaintain" })
	public String toProjectMaintain(){
		return "mauManage/cendisplay/childpage/projectMaintain";
	}
	/**
	 * 获取故障维修记录
	 * @param map
	 * @return
	 */
	@RequestMapping("getFaultPage")
	@ResponseBody
	public Page getFaultPage(HttpServletRequest req,Page page,String param){
		Gson gson = new Gson();
		@SuppressWarnings("serial")
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Page queryPage = service.queryPage(page,map);
		return queryPage;
	}
	
	
	/**TG
	 * 设备故障发生时，报告发生故障，向设备故障表中增加故障信息
	 * 获取（故障类型【机械类、电器类】、设备编码、设备类型）
	 * 生成故障编码和故障时间
	 * 注：设备故障影响生产进度，生产调度，改变设备状态
	 * @return map故障报告信息，成功或失败
	 */
	@RequestMapping({"saveReportRepair"})
	@ResponseBody
	public HashMap<String, Object> saveReportRepair(HttpServletRequest request, String mac_code,String fault_type,String mac_type){
		SysUser user = SessionUtils.getUser(request);
		
		MauMachineFault mf = new MauMachineFault();
		mf.setCreateBy("admin");
		mf.setCreateDate(new Date());
		
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date da = new Date();
			String daStr = df.format(da);
			Date date = df.parse(daStr);
			mf.setFaultDate(date);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		mf.setMacCode(mac_code);
		mf.setFaultType(fault_type);
		mf.setMacType(mac_type);
		mf.setStatus("未维修");
		String msg = "";
		MauMachineFault mmf = null;
		try{
			//保存故障信息,更改设备状态
			mmf = service.reportRepair(mf);
			msg = "呼叫成功！";
			return JsonWrapper.successWrapper(mmf, msg);
		}catch(Exception e){
			e.printStackTrace();
			msg = "发生异常，呼叫失败！";
			return JsonWrapper.failureWrapperMsg(mmf, msg);
		}
		
	}
	
	/**TG
	 * 确认到达维修地点
	 * 获取设备编码、维修人员名称
	 * @return 返回成功或失败
	 */
	@RequestMapping({"arriveRepairAddr"})
	@ResponseBody
	public HashMap<String, Object> arriveRepairAddr(String mac_code,String repair_by){
		
		String msg = "";
		MauMachineFault mmf = null;
		try{
			mmf = service.arriveRepairAddrService(mac_code, repair_by);
			msg = "确认到达现场成功！";
			return JsonWrapper.successWrapper(mmf, msg);
		}catch(Exception e){
			e.printStackTrace();
			msg = "发生异常，确认到达失败！请确认机台编码填写正确！";
			return JsonWrapper.failureWrapperMsg(mmf, msg);
		}
		
	}
	
	/**TG
	 * 设备维修完成
	 * 获取设备编码、故障详情
	 * @return 返回提交信息
	 */
	@RequestMapping({"repairOver"})
	@ResponseBody
	public HashMap<String, Object> repairOver(String mac_code,String remark){
		
		String msg = "";
		MauMachineFault mmf = null;
		try{
			mmf = service.repairOverService(mac_code, remark);
			msg = "确认维修成功！";
			return JsonWrapper.successWrapper(mmf, msg);
		}catch(Exception e){
			e.printStackTrace();
			msg = "发生异常，确认失败！请确认机台编码填写正确！";
			return JsonWrapper.failureWrapperMsg(mmf, msg);
		}
	}
	
	/**
	 * 查询所有故障中的设备，包括未维修和维修中
	 * @return
	 */
	@RequestMapping({"repairAll"})
	@ResponseBody
	public HashMap<String, Object> repairAll(){
		List<MauMachineFault> list = service.repairAllService();
//		String msg = "";
//		if(list == null || "".equals(list)){
//			msg = "无故障设备";
//			return JsonWrapper.failureWrapperMsg(null, msg);
//		}
		return JsonWrapper.successWrapper("data", list);
	}
	
	@RequestMapping({"exprotFaultExcel"})
	@ResponseBody
	public HashMap<String, Object> exprotFaultExcel(HttpServletRequest req,HttpServletResponse response,Page page,String param){
		Gson gson = new Gson();
		@SuppressWarnings("serial")
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		try {
			service.exportExcel(response, page, map);
			return JsonWrapper.successWrapper("成功"); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage()); 
		}
		
    }
	
	
	
	
	//********************************设备保养部分*************************************//
	
	/**
	 * 跳转到保养管理页面
	 *@data:2017年9月1日
	@param request
	@return
	@autor:TG
	 */
	@RequestMapping({"toMachineMaintainTable"})
	public String toMachineMaintainTable(HttpServletRequest request){
		return "mauManage/maufault/mauMaintainTable";
	}
	
	/**
	 * 根据设备类型获取设备信息
	 * @param macType
	 * @return
	 */
	@RequestMapping({"getMachineInfo"})
	@ResponseBody
	public List<String> getMachineInfo(String macType){
		return mauMachineMaintainManageService.getMachineInfoService(macType);
	}
	
	/**
	 * 保养计划条件查找
	 * @param map
	 * @return
	 */
	@RequestMapping("getMaintainPage")
	@ResponseBody
	public Page getMaintainPage(HttpServletRequest req,Page page,String param){
		Gson gson = new Gson();
		if(param == null) param = "{}";
		@SuppressWarnings("serial")
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Page queryPage = mauMachineMaintainManageService.queryPage(page,map);
		return queryPage;
	}
	
	/**
	 * 添加保养计划
	 * @return
	 */
	@RequestMapping("addMaintainData")
	@ResponseBody
	public Map<String,String> addMaintainData(HttpServletRequest req,String param){
		Gson gson = new Gson();
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		return mauMachineMaintainManageService.addMaintainDataService(req,map);
	}
	
	/**
	 * 点击开始保养保存更新保养计划数据
	 * @param param
	 * @return
	 */
	@RequestMapping({"toSaveStartData"})
	@ResponseBody
	public Map<String,String> toSaveStartData(String param){
		Gson gson = new Gson();
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Map<String,String> maps = new HashMap<>();
		maps = mauMachineMaintainManageService.saveStartDataService(map);
		return maps;
	}
	
	/**
	 * 更新保养计划
	 * @param param
	 * @return
	 */
	@RequestMapping({"updateMachineMaintain"})
	@ResponseBody
	public Map<String, String> updateMachineMaintain(String param){
		Gson gson = new Gson();
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		
		Map<String, String> maps = mauMachineMaintainManageService.updateMachineMaintainRemark(map);
		return maps;
	}
	
	/**
	 * 删除未开始和已完成的保养计划
	 * @param ids
	 * @return
	 */
	@RequestMapping({"toDelete"})
	@ResponseBody
	public Map<String,String> clearMachineMaintain(String param){
		JSONArray ja = JSONArray.fromObject(param);
		List<Object> list = JsonUtil.jsonToList(ja);
		Map<String,String> maps = new HashMap<>();
		try {
			for (Object obj : list) {
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) obj;
				Integer id = Integer.parseInt(map.get("id").toString());
				mauMachineMaintainManageService.clearMachineMaintain(id);
			}
			maps.put("success", "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			maps.put("error", "出现异常，删除失败！");
		}
		return maps;
	}
	
	/**
	 * 点击保养完成保存完成保养数据
	 * @param param
	 * @return
	 */
	@RequestMapping({"toSaveOverData"})
	@ResponseBody
	public Map<String,String> toSaveOverData(String param){
		Gson gson = new Gson();
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Map<String,String> maps = new HashMap<>();
		maps = mauMachineMaintainManageService.saveOverDataService(map);
		return maps;
	}
	
	/**
	 * 维护保养excel导出
	 *@data:2017年8月8日
	@param req
	@param response
	@param page
	@param macCode
	@param maintainBy
	@param status
	@param startMaintainDate
	@param endMaintainDate
	@return
	@autor:wl
	 */
	@RequestMapping({"exprotMaintainExcel"})
	@ResponseBody
	public HashMap<String, Object> exprotMaintainExcel(HttpServletRequest req,HttpServletResponse response,Page page,String param){
		HashMap<String, Object>  maps = new HashMap<>();
		Gson gson = new Gson();
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
	    try {	
		    mauMachineMaintainManageService.exportExcel(response, page, map);
		    return JsonWrapper.successWrapper("导出成功"); 
	    } catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage()); 
		}
		
	}
	
	/*************************************************************************/
	
	/**
	 * 去工程部电子看板机台维修计划
	 * @param request
	 * @return
	 */
	@RequestMapping({"toMachineFaultHdmiTable1"})
	public String toMachineFaultHdmiTable1(HttpServletRequest request){
		return "mauManage/maufault/maufaultHdmiTable1";
	}
	
	
	
	/***************电子看板部分***************/
	
	/**
	 * 获取保养设备信息
	 * @return
	 */
	@RequestMapping({"getMaintainList"})
	@ResponseBody
	public List<MauMachineMaintain> getMaintainList(){
		List<MauMachineMaintain> list = mauMachineMaintainManageService.getMaintainListService();
		return list;
	}
	
	/**
	 * 获取故障设备信息
	 * @return
	 */
	@RequestMapping({"getFaultList"})
	@ResponseBody
	public List<MauMachineFault> getFaultList(){
		List<MauMachineFault> list = service.repairAllService();
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/************************************WENNUE****以下此处无用（别人写的代码保留）***********************************/
	
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauMachineFaultEdit";
	}
	

	@RequestMapping({ "toAddEditPage1" })
	public String toSaveEditPage1(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/maufault/mauMachineFaultEdit1";
	}
	
	
	@RequestMapping({ "toAddEditPage2" })
	public String toSaveEditPage2(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/maufault/mauMachineMaintainEdit";
	}
	

	@RequestMapping({ "toAddEditPage3" })
	public String toSaveEditPage3(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		MauMachineMaintain mauMachineMaintain=null;
		if(null!=id){
			 mauMachineMaintain = mauMachineMaintainManageService.get(id);
		}
		if(null!=mauMachineMaintain){
			request.setAttribute("mauMachineMaintain", mauMachineMaintain);
		}
		return "mauManage/maufault/mauMachineMaintainEdit1";
	}
	@RequestMapping({ "toAddEditPage4" })
	public String toAddEditPage4(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/maufault/mauMachineMaintainEdit2";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauMachineFaultForm";
	}
	

	
	/**
	 * 
	 * @Description:  去区域电子看板的机台故障    
	 * @param req
	 * @param area
	 * @return
	 */
	@RequestMapping("toAreaMachineDisplay")
	public String toAreaMachineDisplay(HttpServletRequest req,String area){
		req.setAttribute("area", area);
		return "mauManage/mauareadisplay/areaboarderror";
	}
	/**
	 * 实事数据展示
	 *@data:2017年8月3日
	@param request
	@return
	@autor:wl
	 */
	@RequestMapping({"toMachineFaultHdmiCall"})
	public String toMachineFaultHdmiCall(HttpServletRequest request){
		
		List<MauMachineFault> list = service.getListFault();
		if(null!=list&&list.size()>0){
			request.setAttribute("list", list);
		}
		return "mauManage/maufault/maufaultHdmiCall";
	}
	
	
	@RequestMapping({"toMachineFaultHdmiTable"})
	public String toMachineFaultHdmiTable(HttpServletRequest request){
		return "mauManage/maufault/maufaultHdmiTable";
	}
	
	
	
	
	
	@RequestMapping({"getListFault"})
	@ResponseBody
	public HashMap<String, Object> getListFault(HttpServletRequest req){
		HashMap<String, Object>  map=new HashMap<>();
		List<MauMachineFault> list =null;
	  try {
		  list = service.getListFault();
		if(null!=list&&list.size()>0){
			map.put("fault", list);
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
		return map;
		
}
	
	
	
	
	
	/**
	 * 修改维修记录
	 *@data:2017年8月5日
	@param req
	@param status
	@param remark
	@param id
	@param repairBy
	@return
	@autor:wl
	 */
	@RequestMapping({"updateMachineFault"})
	@ResponseBody
	public HashMap<String, Object> updateMachineFault(HttpServletRequest req,String status,String remark,Integer id,String repairBy ){
		HashMap<String, Object>  map=new HashMap<>();
		
	  try {
		
		  if(null!=repairBy){
			  //添加维修人
			  map=service.updateMachineFaultRepairBy(repairBy,id);
		  }else{
			  if(null!=remark){
				  map=service.updateMachineFaultRemark(remark,status,id);
			  }
		  }
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
		return map;
		
}
	/**
	 * 获取全部机台的下拉框
	 *@data:2017年8月7日
	@param req
	@param status
	@param remark
	@param id
	@param repairBy
	@return
	@autor:wl
	 */
	@RequestMapping({"getMachineCode"})
	@ResponseBody
	public HashMap<String, Object> getMachineCode(HttpServletRequest req ){
		HashMap<String, Object>  map=new HashMap<>();
		
	  try {
		  
		List<String> list= mauMachineMaintainManageService.getMachineCode();
		map.put("macCode", list);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
		return map;
		
}
	
	/**
	 * 由机台发起故障消息 查询故障表格
	 *@data:2017年8月3日
	@param req
	@param faultType
	@param date
	@param macCode
	@param remark
	@return
	@autor:wl
	 */
	@RequestMapping({"getMessageFromMachine"})
	@ResponseBody
	public HashMap<String, Object> getMessageFromMachine(HttpServletRequest req,String macCode){
		
		try {
				
				if(null==macCode&&"".equals(macCode)){
					return JsonWrapper.failureWrapperMsg("机台编码不能为空");
				}
				
				SysUser user = SessionUtils.getUser(req);
				MauMachineFault entity = new MauMachineFault();
				entity.setCreateBy(user.getAccount());
				entity.setFaultDate(new Timestamp(new Date().getTime()));
				entity.setFaultType("");
				entity.setMacCode(macCode);
				entity.setMmfCode("");
				entity.setStatus("0");
				service.save(entity );
				return JsonWrapper.successWrapper("成功");
				} catch (Exception e) {
					e.printStackTrace();
					return JsonWrapper.failureWrapperMsg(e.getMessage());
				}
		
				}
	
	/**
	 * 维护添加
	 *@data:2017年8月7日
	@param req
	@param macCode
	@return
	@autor:wl
	 */
	@RequestMapping({"saveMaintainMessageFromMachine"})
	@ResponseBody
	public HashMap<String, Object> saveMaintainMessageFromMachine(HttpServletRequest req,String macCode,String maintainBy){
		
		try {
				
				if(null==macCode&&"".equals(macCode)){
					return JsonWrapper.failureWrapperMsg("机台编码不能为空");
				}
				if(null==maintainBy&&"".equals(maintainBy)){
					return JsonWrapper.failureWrapperMsg("请添加维护保养人员");
				}
				SysUser user = SessionUtils.getUser(req);
				MauMachineMaintain entity=new MauMachineMaintain();
				entity.setCreateBy(user.getAccount());
				entity.setStartMaintainDate(new Timestamp(new Date().getTime()));
				entity.setStatus("1");
				entity.setMacCode(macCode);
				entity.setMaintainBy(maintainBy);
				mauMachineMaintainManageService.save(entity );
				return JsonWrapper.successWrapper("成功");
				} catch (Exception e) {
					e.printStackTrace();
					return JsonWrapper.failureWrapperMsg(e.getMessage());
				}
		
				}
	
	/*@RequestMapping({"repairMachine"})
	@ResponseBody
	public HashMap<String, Object> repairMachine(HttpServletRequest req,String repairBy,String macCode){
		HashMap<String, Object> map=null;
		try {
				
				if(null!=macCode&&!"".equals(macCode)){
				map=service.updateMauMachineFaultByMacCode(repairBy,macCode);
				return JsonWrapper.successWrapper("成功");
				}else{
					return JsonWrapper.failureWrapperMsg("故障机台编码不能未空");
				}
				} catch (Exception e) {
					e.printStackTrace();
					return JsonWrapper.failureWrapperMsg(e.getMessage());
				}
		
				}*/
	
	/*
			@RequestMapping({"finishRepairMachine"})
			@ResponseBody
			public HashMap<String, Object> finishRepairMachine(HttpServletRequest req,String macCode,long date){
				HashMap<String, Object> map=null;
				try {
						
						if(null!=macCode&&!"".equals(macCode)){
						map=service.finishRepairMachine(date,macCode);
						return JsonWrapper.successWrapper("成功");
						}else{
							return JsonWrapper.failureWrapperMsg("故障机台编码不能未空");
						}
						} catch (Exception e) {
							e.printStackTrace();
							return JsonWrapper.failureWrapperMsg(e.getMessage());
						}
				
						}
*/
	
	
	
	
	
	
	
	
	
}
