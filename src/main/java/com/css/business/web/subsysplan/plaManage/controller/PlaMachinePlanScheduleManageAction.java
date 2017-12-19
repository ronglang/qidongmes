package com.css.business.web.subsysplan.plaManage.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysplan.bean.PlaMachinePlanSchedule;
import com.css.business.web.subsysplan.plaManage.bean.PlaMachineDisplayVo;
import com.css.business.web.subsysplan.plaManage.bean.PlaRequestPurchaseDetailedVo;
import com.css.business.web.subsysplan.plaManage.service.PlaMachinePlanScheduleManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.SpringContextHolder;
import com.google.gson.Gson;
@Controller
@RequestMapping("/plaMachinePlanScheduleManageAction")
public class PlaMachinePlanScheduleManageAction extends BaseSpringSupportAction<PlaMachinePlanSchedule, PlaMachinePlanScheduleManageService> {
	
	//@Autowired
	private PlaMachinePlanScheduleManageService service;
	private Gson gson = new Gson();
	
	@Override
	public PlaMachinePlanScheduleManageService getEntityManager() {
		return service;
	}

	public PlaMachinePlanScheduleManageService getService() {
		return service;
	}

	@Resource(name="plaMachinePlanScheduleManageService")
	public void setService(PlaMachinePlanScheduleManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMachinePlanScheduleEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMachinePlanScheduleForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/qualityUndo";
	}
	
	@RequestMapping({ "toPlaMachinePlanScheduleTable" })
	public String toPlaMachinePlanScheduleTable(HttpServletRequest request, Integer id,String axis_name){
		request.setAttribute("id", id);
		request.setAttribute("axis_name", axis_name);
		return "plaManage/plaMachinePlanScheduleTable";
	}
	
	@RequestMapping({"getPlaMachinePlanScheduleTable"})
	@ResponseBody
	public Page getPlaMachinePlanScheduleTable(HttpServletRequest request, Integer id, Page p,String axis_name){
		try {
			if("".equals(axis_name) || axis_name == null){
				Page page = service.queryPlaMachinePlanScheduleService(p);
				return page;
			}else{
				Page page = service.queryMachineScheduleService(p, axis_name);
				return page;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping({"getPlaScheduleParam"})
	@ResponseBody
	public List<PlaRequestPurchaseDetailedVo> getPlaScheduleParam(HttpServletRequest request, Integer id,
			Integer week_plan_id,Integer machine_schedule_id){
		List<PlaRequestPurchaseDetailedVo> list = service.queryPlaScheduleParamService(week_plan_id, machine_schedule_id);
		return list;
	}
	
	@RequestMapping({"toPlaScheduleParam"})
	public String toPlaScheduleParam(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		
		return null;
	}
	
	/**
	 * 
	 * @Description: 通过机台id,获得该机台今日完成计划情况   
	 * @param request
	 * @param machineId
	 * @return
	 */
	@RequestMapping("getTaskComplete")
	@ResponseBody
	public PlaMachineDisplayVo getTaskComplete(HttpServletRequest request,String machineId){
		PlaMachineDisplayVo plaMachineDisplayVo = service.queryTaskCompByMachineId(machineId,null);
		return plaMachineDisplayVo;
		
	}
	
	
	/**
	 * 
	 * @Description: 页面初始化,任务完成情况,根据工序编码查询日计划进度    
	 * @param request
	 * @param seqCode
	 * @return
	 */
	@RequestMapping("initDisplayTask")
	@ResponseBody
	public PlaMachineDisplayVo initDisplayTask(HttpServletRequest request,String seqCode){
		PlaMachineDisplayVo vo = service.initDisplayTask(seqCode);
		return vo;
	}

	@RequestMapping("getTaskByMachineId")
	@ResponseBody
	public String getTaskByMachineId(String machineCode){
		PlaMachineDisplayVo vo = service.queryTaskCompByMachineId(null,machineCode);
		if (vo!=null) {
			String json = gson.toJson(vo);
			return json;
		}
		return null;
	}
	
}
