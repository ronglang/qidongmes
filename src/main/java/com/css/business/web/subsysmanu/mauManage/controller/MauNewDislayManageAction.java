package com.css.business.web.subsysmanu.mauManage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsyscraft.bean.CraSeq;
import com.css.business.web.subsysmanu.bean.MauMachine;
import com.css.business.web.subsysmanu.mauManage.service.MauMachineManageService;
import com.css.business.web.subsysmanu.mauManage.service.MauNewDislayManageService;
import com.css.business.web.subsysplan.plaManage.bean.PlaMachineDisplayVo;
import com.css.business.web.subsysplan.plaManage.service.PlaMachinePlanManageService;
import com.css.business.web.subsysplan.plaManage.service.PlaMachinePlanScheduleManageService;
import com.css.business.web.subsysquality.bean.QualityMaterReport;
import com.css.business.web.subsysquality.bean.QualityMauReport;
import com.css.business.web.subsysquality.quaManage.service.QualityMaterReportManageService;
import com.css.business.web.subsysquality.quaManage.service.QualityMauReportManageService;
import com.css.business.web.sysManage.bean.SysMacDictionary;
import com.css.business.web.sysManage.service.SysMacDictionaryManageService;
import com.css.business.web.syswebsoket.bean.EchartsVo;
import com.css.commcon.util.YorkUtil;
import com.css.common.util.DateUtil;
import com.css.common.util.EhCacheFactory;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

/**
 * 新工序看板，根据机台参数来选择个别机台参数来展示在电子看板上面 显示所有工序的机台参数action
 * 
 * @author TG
 * 
 */

@Controller
@RequestMapping("/mauNewDislayManageAction")
public class MauNewDislayManageAction extends
		BaseSpringSupportAction<CraSeq, MauNewDislayManageService> {

	@Resource(name = "mauNewDislayManageService")
	private MauNewDislayManageService service;

	@Resource(name = "mauMachineManageService")
	private MauMachineManageService macService;

	@Resource(name = "sysMacDictionaryManageService")
	private SysMacDictionaryManageService paramService;
	/** 原材料质检 */
	@Resource(name = "qualityMaterReportManageService")
	private QualityMaterReportManageService qmtService;
	/** 成品质检 */
	@Resource(name = "qualityMauReportManageService")
	private QualityMauReportManageService qmServcie;
	/** 机台计划进度 */
	@Resource(name = "plaMachinePlanScheduleManageService")
	private PlaMachinePlanScheduleManageService pmsService;
	/** 机台计划 */
	@Resource(name = "plaMachinePlanManageService")
	private PlaMachinePlanManageService pmService;

	@Override
	public MauNewDislayManageService getEntityManager() {
		return service;
	}

	private Gson gson = new Gson();

	private EhCacheFactory factory = EhCacheFactory.getInstance();

	/**
	 * 
	 * @Description: 去工序列表
	 * @return
	 */
	@RequestMapping("newProcessList")
	public String toProcessList() {
		return "mauManage/cendisplay/newProcessList";
	}

	/**
	 * 去车间电子看板
	 * 
	 * @return
	 */
	@RequestMapping("newWorkShopList")
	public String toWorkShopList() {
		return "mauManage/cendisplay/workShop";
	}

	@RequestMapping("workShopList")
	public String workShopList() {
		return "mauManage/cendisplay/newWorkShopList";
	}

	/**
	 * 去生产部电子看板
	 * 
	 * @return
	 */
	@RequestMapping("newProDepartList")
	public String toProDepartList() {
		return "mauManage/cendisplay/newProDepartList";
	}

	/**
	 * 显示机台状态数
	 * 
	 * @return
	 */
	@RequestMapping({ "toDisplayMachineNum" })
	public String toDisplayMachineNum() {
		return "mauManage/cendisplay/childpage/displayMachineNum";
	}

	/**
	 * 显示机台生产情况:生产部电子看板
	 * 
	 * @return
	 */
	@RequestMapping({ "macProductInfo" })
	public String toMacProductInfoList() {
		return "mauManage/cendisplay/childpage/macProductInfo";
	}

	/**
	 * 去对应工序编辑页面
	 * 
	 * @return
	 */
	/*
	@RequestMapping("newProcessListEdit")
	public String toProcessListEdit(String seqCode, String seqName,
			HttpServletRequest request) {
		request.setAttribute("seqCode", seqCode);
		request.setAttribute("seqName", seqName);
		return "mauManage/cendisplay/newProcessListEdit";
	}*/
	@RequestMapping("newProcessListEdit")
	public String toProcessListEdit(String macCode,String seqName,String macName,HttpServletRequest request) {
		request.setAttribute("macCode", macCode);
		request.setAttribute("seqName", seqName);
		request.setAttribute("macName", macName);
		return "mauManage/cendisplay/newProcessListEdit";
	}

	@RequestMapping("toNewKanBan")
	public String toNewKanBan(String map, HttpServletRequest request) {
		@SuppressWarnings("serial")
		Map<String, Object> maps = gson.fromJson(map,
				new TypeToken<Map<String, Object>>() {
				}.getType());
		request.setAttribute("map", maps);
		return "mauManage/cendisplay/newKanBan";
	}

	/**
	 * 获取所有工序列表
	 * 
	 * @return
	 */
	@RequestMapping("getAllCraSeq")
	@ResponseBody
	public Map<String,Object> getAllCraSeq() {
		Map<String,Object> map = service.getAllCraSeqService();
		return map;
	}

	/**
	 * 根据工序获取对应机台信息
	 * 
	 * @param seqCode
	 * @return
	 */
	@RequestMapping("getSeqMacInfo")
	@ResponseBody
	public List<MauMachine> getSeqMacInfo(String seqCode) {
		List<MauMachine> list = macService.getMacBySeq(seqCode);
		return list;
	}

	/**
	 * 根据机台编码查询对应机台参数
	 * 
	 * @param macCode
	 * @return
	 */
	@RequestMapping("getMacParam")
	@ResponseBody
	public List<SysMacDictionary> getMacParam(String macCode) {
		List<SysMacDictionary> list = paramService
				.getMacParamByMacCode(macCode);
		return list;
	}

	/**
	 * 
	 * @Description: 仓库电子看板 入原材料入库情况
	 * @param
	 * @return HashMap<String,Object>
	 * @throws
	 */
	@RequestMapping("getMaterQuality")
	@ResponseBody
	public HashMap<String, Object> getMaterQuality() {
		try {
			// TODO jia
			List<QualityMaterReport> depotShow = qmtService.getDepotShow();
			return JsonWrapper.successWrapper(depotShow);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("未找到相关数据");
		}
	}

	/**
	 * 
	 * @Description: 仓库电子看板，成品入库情况
	 * @param @return
	 * @return HashMap<String,Object>
	 * @throws
	 */
	@RequestMapping("getMauQuality")
	@ResponseBody
	public HashMap<String, Object> getMauQuality() {
		try {
			List<QualityMauReport> depotShow = qmServcie.getDepotShow();
			return JsonWrapper.successWrapper(depotShow);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("未找到相关数据");
		}
	}

	/**
	 * 
	 * @Description: 仓库电子看板，原材料生产领料情况
	 * @param @return
	 * @return HashMap<String,Object>
	 * @throws
	 */
	@RequestMapping("getPickingMaterial")
	@ResponseBody
	public HashMap<String, Object> getPickingMaterial() {
		try {

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("未找到相关数据");
		}
		return null;
	}

	/**
	 * 
	 * @Description: 仓库电子看板，成品出口情况
	 * @param @return
	 * @return HashMap<String,Object>
	 * @throws
	 */
	@RequestMapping("getMauGetOut")
	@ResponseBody
	public HashMap<String, Object> getMauGetOut() {
		try {

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("未找到相关数据");
		}
		return null;
	}

	/**
	 * 
	 * @Description: 生产车间电子看板
	 * @param @return
	 * @return HashMap<String,Object>
	 * @throws
	 */
	@RequestMapping("getWorkShop")
	@ResponseBody
	public HashMap<String, Object> getWorkShop() {
		try {

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("未找到相关数据");
		}
		return null;
	}

	/**
	 * 
	 * @Description:生产部电子看板，机台生产情况
	 * @param seqCode
	 *            工序code
	 * @return
	 */
	@RequestMapping("getProDepMac")
	@ResponseBody
	public HashMap<String, Object> getProDepMac(/* String seqCode */) {
		try {
			Integer workDay = Integer.parseInt(DateUtil.format(new Date(),
					"yyyyMMdd"));
			PlaMachineDisplayVo vo = pmsService.getTodayProductionBySeq(null,
					workDay);
			;
			return JsonWrapper.successWrapper(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("未找到相关数据");
		}
	}

	/**
	 * 
	 * @Description:工序电子看板，速度-时间表
	 * @param macCode
	 * @param
	 * @return HashMap<String,Object>
	 * @throws
	 */
	@RequestMapping("getSeqMacSpeed")
	@ResponseBody
	public HashMap<String, Object> getSeqMacSpeed(String macCode) {
		String cmdCache = null;
		// Map<String,List<String>>map = new HashMap<>();
		try {

			// List<String> timeList = new ArrayList<>();
			// timeList.add("1.2");
			// timeList.add("1.3");
			// List<String> dataList = new ArrayList<>();
			// dataList.add("0.3");
			// dataList.add("0.5");
			// map.put("X", timeList);
			// map.put("Y", dataList);
			// cmdCache =
			// factory.getWebsocketCmdCache(YorkUtil.Memcache_机台_实时_速度+"_"+macCode);
			cmdCache = factory
					.getWebsocketCmdCache(YorkUtil.Memcache_机台_实时_Echart + "_"
							+ macCode);
			if (cmdCache == null || "".equals(cmdCache)) {
				return JsonWrapper.failureWrapperMsg("false");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("false");
		}
		// Map<String,List<String>> map = gson.fromJson(cmdCache, new
		// TypeToken<Map<String,List<String>>>(){}.getType());
		EchartsVo vo = gson.fromJson(cmdCache, EchartsVo.class);
		return JsonWrapper.successWrapper(vo);
	}

	/**
	 * 
	 * @Description:工序电子看板，机台参数信息
	 * @param macCode
	 * @param
	 * @return HashMap<String,Object>
	 * @throws
	 */
	@RequestMapping("getSeqMacParam")
	@ResponseBody
	public HashMap<String, Object> getSeqMacParam(String macCode) {
		String cmdCache = null;
		// Map<String,Object>map = new HashMap<>();
		// map.put("米表", "123");
		try {
			// map
			cmdCache = factory.getWebsocketCmdCache(YorkUtil.Memcache_机台_实时_参数
					+ "_" + macCode);
			if (cmdCache == null || "".equals(cmdCache)) {
				return JsonWrapper.failureWrapperMsg("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("false");
		}
		return JsonWrapper.successWrapper(cmdCache);
	}
	
	
	/**
	 * 
	 * @Description:工序列表页面查询有参数的机台
	 * @return HashMap<String,Object>
	 * @throws
	 */
	@RequestMapping("getMacCodeCache")
	@ResponseBody
	public HashMap<String, Object> getMacCodeCache() {
		String cmdCache = null;
		//Map<String,Object> map = new HashMap<>();
		//map.put("SC", "123");
		try {
			 //map
			cmdCache = factory.getWebsocketCmdCache(YorkUtil.Memcache_机台code);
			if (cmdCache == null || "".equals(cmdCache)) {
				return JsonWrapper.failureWrapperMsg("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("false");
		}
		return JsonWrapper.successWrapper(cmdCache);
	}
	
	
	
	
	
	

}
