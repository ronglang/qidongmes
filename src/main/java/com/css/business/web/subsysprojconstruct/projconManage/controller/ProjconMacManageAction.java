package com.css.business.web.subsysprojconstruct.projconManage.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysprojconstruct.bean.ProjconMac;
import com.css.business.web.subsysprojconstruct.projconManage.service.ProjconMacManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/projconMacManageAction")
public class ProjconMacManageAction extends BaseSpringSupportAction<ProjconMac, ProjconMacManageService> {
	
	//@Autowired
	private ProjconMacManageService service;
	
	@Override
	public ProjconMacManageService getEntityManager() {
		return service;
	}

	public ProjconMacManageService getService() {
		return service;
	}

	@Resource(name="projconMacManageService")
	public void setService(ProjconMacManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "projconManage/projconMacEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "projconManage/projconMacForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "projconManage/projconMacList";
	}
	
	@RequestMapping({ "toProcessDiagram" })
	public String toProcessDiagram(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "projconManage/ProcessDiagram";
	}
	@RequestMapping({ "toMachineDiagram" })
	public String toMachineDiagram(HttpServletRequest request,String processName){
		request.setAttribute("processName", processName);
		return "projconManage/MachineDiagram";
	}
	
	@RequestMapping(value={"/loadProjconMacManage"})
	@ResponseBody
	public List<ProjconMac> loadProjconMacManage(HttpServletRequest request,String processName){
		return service.getProjconMacService(processName);
	}
	
	@RequestMapping(value={"/serchChangeProjconMacManage"})
	@ResponseBody
	public List<ProjconMac> serchChangeProjconMacManage(HttpServletRequest request,String processName,String searchSate){
		return service.getserchChangeProjconMacService(processName,searchSate);
	}
	@RequestMapping(value={"/saveProjconMacManage"})
	@ResponseBody
	public String saveProjconMacManage(HttpServletRequest request,String macCode,String macState,String processName){
		return service.getsaveProjconMacManageService(macCode,macState,processName);
	}
	
	@RequestMapping(value={"/updateProjconMacManage"})
	@ResponseBody
	public String updateProjconMacManage(ProjconMac projconMac){
		return service.getUpdateProjconMacManageService(projconMac);
	}
	
	@RequestMapping(value={"/serchProjconMacManageForSelecState"})
	@ResponseBody
	public String serchProjconMacManageForSelecState(HttpServletRequest request,String macCode){
		return service.getserchProjconMacManageForSelecStateService(macCode);
	}
	
	@RequestMapping(value={"/updateProjconMacManageForSelecState"})
	@ResponseBody
	public String updateProjconMacManageForSelecState(HttpServletRequest request,String selecState,String macCode){
		return service.getupdateProjconMacSelecStateManageService(selecState,macCode);
	}
	@RequestMapping(value={"/deleteProjconMacManageBySelecState"})
	@ResponseBody
	public String deleteProjconMacManageBySelecState(){
		return service.deleteProjconMacManageBySelecStateManageService();
	}
	@RequestMapping(value={"/initUpdateSelecState"})
	@ResponseBody
	public String initUpdateSelecState(){
		return service.initUpdateSelecStateManageService();
	}
	
	@RequestMapping(value={"/serchSelecStateNum"})
	@ResponseBody
	public List<ProjconMac> serchSelecStateNum(){
		return service.getserchSelecStateNumService();
	}
	
}
