package com.css.business.web.subsysplan.plaManage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysplan.bean.PlaMacTask;
import com.css.business.web.subsysplan.bean.PlaMachinePlan;
import com.css.business.web.subsysplan.plaManage.service.PlaMachinePlanManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
/**
 * 
* @Title: PlaMachinePlanManageAction.java   
* @Package com.css.business.web.subsysplan.plaManage.controller   
* @Description: 机台日计划   
* @author   rb
* @date 2017年7月12日 下午4:05:06   
* @company  SMTC
 */
@SuppressWarnings("unused")
@Controller
@RequestMapping("/plaMachinePlanManageAction")
public class PlaMachinePlanManageAction extends BaseSpringSupportAction<PlaMachinePlan, PlaMachinePlanManageService> {
	
	//@Autowired
	private PlaMachinePlanManageService service;
	
	@Override
	public PlaMachinePlanManageService getEntityManager() {
		return service;
	}

	public PlaMachinePlanManageService getService() {
		return service;
	}

	@Resource(name="plaMachinePlanManageService")
	public void setService(PlaMachinePlanManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMachinePlanEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMachinePlanForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMachinePlanList";
	}
	
	@RequestMapping({"toPlaMachineTable"})
	public String toPlaMachineTable(HttpServletRequest request, Integer id,String ids){
		request.setAttribute("id", id);
		request.setAttribute("ids", ids);
		return "plaManage/plaMachinePlanTable";
	}
	

	

/**
 * 获取全部机台计划数据
 * @param p
 * @return
 */
	@RequestMapping({"getPlaMachinePlanGrid"})
	@ResponseBody
	public Page getPage(Page p,String workcode,String maccode,String seqcode,String pstime,String pdtime){
		Page page;
		try {
			page = service.queryPage(p, workcode, maccode, seqcode, pstime,pdtime);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping({"getChildPage"})
	@ResponseBody
	public Page getChildPage(Page p,String workCode,String macCode){
		Page page = service.queryChilePage(p, workCode, macCode);
		return page;
	}
	
	
	@RequestMapping({"getPlanGrid"})
	@ResponseBody
	public Page getPlanGrid(Page p,String pstime,String pdtime,String productstate){
		Page page = service.getPlanGrid(p, pstime, pdtime,productstate);
		return page;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping({"getMacCode"})
	@ResponseBody
	public Map[] getMacCode(){
		Map[] macCode = service.getMacCode();
		return macCode;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping({"getWorkCode"})
	@ResponseBody
	public Map[] getWorkCode(){
		Map[] workCode = service.getWorkCode();
		return workCode;
	}

	@RequestMapping("getMacReal")
	@ResponseBody
	public HashMap<String, Object>getMacReal(HttpServletRequest request,String macCode){
		PlaMacTask pm= service.getMacReal(macCode);
		if (pm != null) {
			return JsonWrapper.successWrapper(pm);
		}else {
			return JsonWrapper.failureWrapperMsg("未查到相关信息");
		}
	}
	
	
	@RequestMapping({"sendMacTask"})
	@ResponseBody
	public HashMap<String, Object> sendMacTask(String pstime,String pdtime){
		try {
			service.sendMacTask(pstime, pdtime);
			return JsonWrapper.successWrapper(null, "下发成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(null, "下发失败!"+e.getMessage());
		}
	}
	
	@RequestMapping("toGanTe")
	public String toGanTe(HttpServletRequest req,String start,String end,String type,String mac){
		req.setAttribute("start",start);
		req.setAttribute("end",end);
		req.setAttribute("type", type);
		req.setAttribute("mac", mac);
		return "totalQuery/planDetial/PlanMacTask";
	}
	
	@RequestMapping("toGanTeList")
	public String toGanTeList(){
		return "totalQuery/planDetial/PlanMacTaskList";
	}
	
	@RequestMapping("getTaskGanTe")
	@ResponseBody
	public Map<String, Object> getTaskGanTe(HttpServletRequest request,String start,String end,String type,String mac){
		//最终
		Map<String, Object> map = service.getTaskGanTe(start,end,type,mac);
		return map;
	}
	
	/**
	 *  获取当前的任务
	 * @param request
	 * @param macCode
	 * @return
	 */
	@RequestMapping("getNowTask")
	@ResponseBody
	public HashMap<String, Object>getNowTask(HttpServletRequest request,String macCode){
		try {
			PlaMacTask task= service.getNowTask(macCode);
			return JsonWrapper.successWrapper(task);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("未找到相关数据");
		}
		
	}
	
	@RequestMapping({"reSetPlanTime"})
	@ResponseBody
	public HashMap<String, Object> reSetPlanTime(){
		try {
			service.reSetPlanTime();
			return JsonWrapper.successWrapper(null, "重置成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonWrapper.failureWrapperMsg(null, "未知错误!");
	}

	
	/**
	 * 扫描盘的RFID
	 * @param request
	 * @param data
	 * @return
	 */
	@RequestMapping({"saveWorkData"})
	@ResponseBody
	public HashMap<String,Object> saveWorkData(HttpServletRequest request,String data){
		try {
			String msg=service.saveWorkData(request,data);
			if(null!=msg && !"".equals(msg)){
				return JsonWrapper.successWrapper(msg,"接收成功");
			}else{
				return JsonWrapper.failureWrapperMsg("接收失败");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
	
	
}
