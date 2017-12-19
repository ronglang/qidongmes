package com.css.business.web.subsysstatistics.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysplan.bean.PlaProductOrder;
import com.css.business.web.subsysstatistics.service.StatStoreObjVoManageService;
import com.css.business.web.subsysstore.bean.StoreMrecord;
import com.css.common.util.ExcelExportUtil;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @Title: StatStoreObjVoManageAction.java
 * @Package com.css.business.web.subsysstatistics.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @author rb
 * @date 2017年8月2日 上午10:48:17
 * @company SMTC
 */
@Controller
@RequestMapping("/statStoreObjVoManageAction")
public class StatStoreObjVoManageAction extends
		BaseSpringSupportAction<PlaProductOrder, StatStoreObjVoManageService> {

	@Resource(name = "statStoreObjVoManageServide")
	private StatStoreObjVoManageService service;

	@Override
	public StatStoreObjVoManageService getEntityManager() {
		// TODO Auto-generated method stub
		return service;
	}

	Gson gson = new Gson();

	/* 成品库存查询页面 */
	@RequestMapping({ "overProduct" })
	public String toOverProductList() {
		return "totalQuery/warehouse/overProductList";
	}

	/* 原材料库存查询列表页面 */
	@RequestMapping({ "rarMaterial" })
	public String toRarMaterialList() {
		return "totalQuery/warehouse/rawMaterialList";
	}

	/* 半成品库存查询列表页面 */
	@RequestMapping({ "semiOverProduct" })
	public String toSemiOverProductList() {
		return "totalQuery/warehouse/semiOverProductList";
	}

	/* 成品出入库查询页面 */
	@RequestMapping({ "overProductIO" })
	public String toOverProductIOList() {
		return "totalQuery/warehouse/overProductIOList";
	}

	/* 原材料出入库查询列表页面 */
	@RequestMapping({ "rarMaterialIO" })
	public String toRarMaterialIOList() {
		return "totalQuery/warehouse/rawMaterialIOList";
	}

	/* 半成品出入库查询列表页面 */
	@RequestMapping({ "semiOverProductIO" })
	public String toSemiOverProductIOList() {
		return "totalQuery/warehouse/semiOverProductIOList";
	}

	@RequestMapping({ "toViewPage" })
	public String toViewPage() {
		return "totalQuery/warehouse/roomAddressView";
	}

	@RequestMapping({ "toPlanePage" })
	public String toPlanePage() {
		return "totalQuery/warehouse/storePlaneView";
	}

	@RequestMapping({ "toTablePage" })
	public String toTablePage(HttpServletRequest requst, String materGgxh,
			String areaType) {
		requst.setAttribute("materGgxh", materGgxh);
		requst.setAttribute("areaType", areaType);
		return "totalQuery/warehouse/storePlaneTable";
	}

	@RequestMapping({ "toFormPage" })
	public String toFormPage(HttpServletRequest requst, String address) {
		requst.setAttribute("address", address);
		return "totalQuery/warehouse/roomAddressForm";
	}

	@RequestMapping({ "getStoreMrecord" })
	@ResponseBody
	public StoreMrecord getStoreMrecord(String address) {
		StoreMrecord mrecord = service.getStoreMrecord(address);
		return mrecord;
	}

	/**
	 * 去详情页面
	 * 
	 * @param requst
	 * @param param
	 * @return
	 */
	@RequestMapping({ "toStatDetail" })
	public String toStatDetail(HttpServletRequest requst, String param) {
		if (param != null && !"".equals(param)) {
			requst.setAttribute("data", param);
		} else {
			requst.setAttribute("data", "false");
		}
		return "totalQuery/warehouse/DetailList";
	}

	/**
	 * 
	 * @param materName
	 * @return
	 */
	@RequestMapping({ "getAddress" })
	@ResponseBody
	public List<String> getAddress(String materName) {
		List<String> str = service.getAddress(materName);
		return str;
	}

	/**
	 * 
	 * @Description: 获得所有的库存情况 ,一段时间的出入库量
	 * @param request
	 * @param param
	 *            条件 ggxh color sort;看出入库情况,有时间start end ,只看库存没有
	 * @return
	 */
	@RequestMapping("getAllStore")
	@ResponseBody
	public Page getAllStore(HttpServletRequest request, String param, Page page) {
		try {
			Map<String, String> map = gson.fromJson(param,
					new TypeToken<Map<String, String>>() {
					}.getType());
			Page queryPage = page;
			if ("库存".equals(map.get("focus"))) {
				queryPage = service.getActStore(map, page);
			} else if ("出入库".equals(map.get("focus"))) {
				queryPage = service.getAllStore(map, page);
			}
			return queryPage;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * 成品导出
	 * 
	 * @data:2017年8月11日
	 * @param request
	 * @param response
	 * @param param
	 * @param page
	 * @param ggxh
	 * @param color
	 * @autor:wl
	 */
	@RequestMapping({ "exportExecl" })
	public void exportExecl(HttpServletRequest request,
			HttpServletResponse response, String param, Page page, String ggxh,
			String color, String start, String end, String matName) {
		Map<String, String> map = gson.fromJson(param,
				new TypeToken<Map<String, String>>() {
				}.getType());
		try {
			if ("库存".equals(map.get("focus"))) {
				service.exportExeclActStore(map, page, response, ggxh, color);
			} else if ("出入库".equals(map.get("focus"))) {
				service.exportExeclAllStore(map, page, response, ggxh, color,
						start, end, matName);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 
	 * @Description: 查询一段时间某个材料,半成品,成品的的出入库详情
	 * @param request
	 * @param param
	 *            包括 ggxh color start end
	 * @param page
	 * @return
	 */
	@RequestMapping("getInOrOutDetail")
	@ResponseBody
	public Page getInOrOutDetail(HttpServletRequest request, String param,
			String signner, Page page) {
		try {
			Map<String, String> map = gson.fromJson(param,
					new TypeToken<Map<String, String>>() {
					}.getType());
			Page queryPage = service.queryInOrOutDetail(page, map, signner);
			return queryPage;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return page;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("exportE")
	public void exportE(HttpServletRequest request,
			HttpServletResponse response, String param) {
		Map<String, String> map = gson.fromJson(param,
				new TypeToken<Map<String, String>>() {
				}.getType());
		List list = service.exportExcel(map);
		Map<String, String> head = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		String filePath = "D:\\soft\\aa.xls";
		String filename = "aa.xls";
		head.put("规格型号", "规格型号");
		head.put("名称", "名称");
		head.put("颜色", "颜色");
		head.put("单位", "单位");
		head.put("库存", "库存");
		for (int i = 0; i < list.size(); i++) {
			try {
				Map<String, Object> dataMap = new HashMap<>();
				Object[] object = (Object[]) list.get(i);
				dataMap.put("规格型号", object[0]);
				dataMap.put("名称", object[1]);
				dataMap.put("颜色", object[2]);
				dataMap.put("单位", object[3]);
				dataMap.put("库存", object[4]);
				dataList.add(dataMap);
			} catch (Exception e) {
				// TODO: handle exception
				continue;
			}
		}

		try {

			ExcelExportUtil
					.export(head, dataList, filePath, filename, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("getAreaType")
	@ResponseBody
	public List<String> getAreaType(String materGgxh) {
		List<String> areaType = service.getAreaType(materGgxh);
		return areaType;
	}

	@RequestMapping("getStoreAddress")
	@ResponseBody
	public Page getStoreAddress(String materGgxh, String areaType,String materColor, Page p) {
		Page page = service.getStoreMrecord(materGgxh, areaType,materColor, p);
		return page;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("getMaterGgxh")
	@ResponseBody
	public Map[] getMaterGgxh(){
		Map[] ggxh = service.getMaterGgxh();
		return ggxh;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("getMaterColor")
	@ResponseBody
	public Map[] getMaterColor(){
		Map[] color = service.getMaterColor();
		return color;
	}

}
