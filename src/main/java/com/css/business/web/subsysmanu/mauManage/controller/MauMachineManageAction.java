package com.css.business.web.subsysmanu.mauManage.controller;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauMachine;
import com.css.business.web.subsysmanu.bean.MauSpeedQuotiety;
import com.css.business.web.subsysmanu.mauManage.service.MauMachineManageService;
import com.css.business.web.subsysmanu.mauManage.service.MauSpeedQuotietyManageService;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/mauMachineManageAction")
public class MauMachineManageAction extends
		BaseSpringSupportAction<MauMachine, MauMachineManageService> {

	// @Autowired
	private MauMachineManageService service;
	
	@Resource(name="mauSpeedQuotietyManageService")
	private MauSpeedQuotietyManageService qservice;

	@Override
	public MauMachineManageService getEntityManager() {
		return service;
	}

	public MauMachineManageService getService() {
		return service;
	}

	@Resource(name = "mauMachineManageService")
	public void setService(MauMachineManageService service) {
		this.service = service;
	}

	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "mauManage/mauMachineEdit";
	}

	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "mauManage/mauMachineForm";
	}

	@RequestMapping({ "toMauMachineTable" })
	public String toMauMachineTable(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "mauManage/mauMachineTable";
	}

	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "mauManage/mauMachineList";
	}

	@RequestMapping(value = { "/loadMauMachineManage" })
	@ResponseBody
	public Page loadMauMachineManage(HttpServletRequest request, Page page,
			String macCode) {
		Page param = null;
		try {
			param = service.getMauMachineManageService(page, macCode);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return param;

	}

	@RequestMapping(value = { "/deleteMauMachineManageByIds" })
	@ResponseBody
	public String deleteMauMachineManageByIds(int[] ids) {
		int[] a = ids;
		service.deleteMauMachineService(a);
		return "删除成功";
	}

	@RequestMapping(value = { "/updateMauMachineManage" })
	@ResponseBody
	public String updateMauMachineManage(MauMachine mauMachine) {
		service.updateMauMachineService(mauMachine);
		return "更新成功";
	}

	@RequestMapping(value = { "/addMauMachineManage" })
	@ResponseBody
	public String addMauMachineManage(MauMachine mauMachine) {
		service.addMauMachineService(mauMachine);
		return "新增成功";
	}

	/*
	 * @RequestMapping(value={"/serchByMacCode"})
	 * 
	 * @ResponseBody public HashMap<String,Object> serchByMacCode(String
	 * page,String pagesize,String macCode){ return
	 * service.getSerchByMacCodeService(page,pagesize,macCode); }
	 */
	@RequestMapping(value = { "/serchMacType" })
	@ResponseBody
	public List<?> serchMacType() {
		return service.getSerchMacTypeService();
	}

	@RequestMapping(value = { "/serchMacPrio" })
	@ResponseBody
	public List<?> serchMacPrio() {
		return service.getSerchMacPrioService();
	}

	@RequestMapping({ "getMachineGrid" })
	@ResponseBody
	public Page getMachineGrid(Page p, String macCode) {
		try {
			String name = "";
			if( macCode!=null && !"".equals(macCode) ){
				name = URLDecoder.decode(macCode, "UTF-8");
			}
			Page page = service.getMauMachineGrid(p, name);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping({ "saveMachine" })
	@ResponseBody
	public HashMap<String, Object> saveMachine(HttpServletRequest req,String data,String quotiety) {
		String msg = "";
		SysUser user = SessionUtils.getUser(req);
		Gson gson = new Gson();
		try {
			String decode = URLDecoder.decode(data, "UTF-8");
			MauMachine machine = gson.fromJson(decode, MauMachine.class);
			List<MauSpeedQuotiety> mauSpeeds = gson.fromJson(quotiety,new TypeToken<List<MauSpeedQuotiety>>() {}.getType());
			for (MauSpeedQuotiety mauSpeed : mauSpeeds) {
				if(mauSpeed.getId()!=null && !"".equals(mauSpeed.getId())){
					qservice.updateMauSpeedQuotiety(mauSpeed);
				}else{
					qservice.saveMauSpeedQuotiety(mauSpeed);
				}
			}
			machine.setCreateBy(user.getAccount());
			machine.setCreateDate(new Date());
			if(machine.getId()!=null && !"".equals(machine.getId())){
				service.updateMachineState(machine);
			}else{
				service.addMachine(machine);
			}
			msg = "保存成功";
			return JsonWrapper.successWrapper(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "发生未知错误";
			return JsonWrapper.failureWrapperMsg(null, msg);
		}
	}
	
	@RequestMapping({"delMahcine"})
	@ResponseBody
	public HashMap<String, Object> delMahcine(String ids){
		String msg = "";
		Gson gson = new Gson();
		try {
			List<MauMachine> machines = gson.fromJson(ids,new TypeToken<List<MauMachine>>() {}.getType());
			for (MauMachine machine : machines) {
				List<MauSpeedQuotiety> quotiety = service.getQuotietyBymacCode(machine.getMacCode());
				for (MauSpeedQuotiety mauSpeedQuotiety : quotiety) {
					service.removeByCon(mauSpeedQuotiety);
				}
				service.delMahcine(machine);
			}
			msg = "";
			return JsonWrapper.successWrapper(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "发生未知错误";
			return JsonWrapper.failureWrapperMsg(null, msg);
		}
	}
	
	@RequestMapping({"getMachine"})
	@ResponseBody
	public MauMachine getMachine(Integer id){
		MauMachine machine = service.getMachine(id);
		return machine;
	}
	

	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getMachineState" })
	@ResponseBody
	public Map[] getMachineState() {
		Map[] state = service.getMachineState();
		return state;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getMachineCode" })
	@ResponseBody
	public Map[] getMachineCode() {
		Map[] str = service.getMacCode();
		return str;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getMachineName" })
	@ResponseBody
	public Map[] getMachineName() {
		Map[] str = service.getMacName();
		return str;

	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getSeqName" })
	@ResponseBody
	public Map[] getSeqName(){
		Map[] str = service.getSeqName();
		return str;
	}
	

	/**
	 * 系统上线时，存在机台与产品部分参数不能及时录入导致系统运行报错。 这里暂使用旧数据初始化数据： 机台生产速度
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping({ "initMachineSpeed" })
	@ResponseBody
	public HashMap<String, Object> initMachineSpeed(HttpServletRequest req) {
		try {
			SysUser user = SessionUtils.getUser(req);
			service.initMachineSpeed(user);
			return JsonWrapper.successWrapper("",
					"初始化成功,请在产品BOM参数管理中设置机台合理的生产速度、准备时间、等生产参数");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String m = e.getMessage();
			return JsonWrapper.failureWrapperMsg("aa" + m);
		}
	}
	
	/**
	 * 查询机台状态个数
	 * @param req
	 * @return
	 */
	@RequestMapping("getMacCount")
	@ResponseBody
	public Map<String, String> getMacCount(HttpServletRequest req) {
		Map<String, String> map = new HashMap<String, String>();
		Long errorCount = service.getCount("故障");
		Long allCount = service.getCount("");
		Long stopCount = service.getCount("关闭");
		Long serviceCount = service.getCount("运行");
		Long careCount = service.getCount("保养");
		map.put("errorCount", errorCount + "");
		map.put("allCount", allCount + "");
		map.put("stopCount", stopCount + "");
		map.put("serviceCount", serviceCount + "");
		map.put("careCount", careCount + "");
		return map;
	}
	
	/**
	 * 
	 * @Description: 通过工序查找机台   
	 * @param seqCode
	 * @return
	 */
	@RequestMapping("getMacBySeq")
	@ResponseBody
	public HashMap<String, Object> getMacBySeq(String seqCode){
		List<MauMachine> list = service.getMacBySeq(seqCode);
		if (list != null) {
			return JsonWrapper.successWrapper(list);
		}
		return JsonWrapper.failureWrapperMsg(null, "未找到相关机台");
	}
	
	@RequestMapping("getMacAllStateNum")
	@ResponseBody
	public Map<Object,Object> getMacAllStateNum(){
		return service.getMacAllStateNum();
	}
	
	@RequestMapping("testCallFork")
	@ResponseBody
	public HashMap<String, Object> testCallFork(){
		return service.testCallFork();
	}
	
	
	
}
