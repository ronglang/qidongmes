package com.css.business.web.subsysstore.storeManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysstore.bean.StoreReturn;
import com.css.business.web.subsysstore.storeManage.service.StoreReturnManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
* @Title: StoreReturnManageAction.java   
* @Package com.css.business.web.subsysstore.storeManage.controller   
* @Description: 退料列表 
* @author   rb
* @date 2017年6月29日 上午9:16:53   
* @company  SMTC
 */
@Controller
@RequestMapping("/storeReturnManageAction")
public class StoreReturnManageAction extends
		BaseSpringSupportAction<StoreReturn, StoreReturnManageService> {

	/** json解析 */
	Gson gson = new Gson();
	// @Autowired
	private StoreReturnManageService service;

	@Override
	public StoreReturnManageService getEntityManager() {
		return service;
	}

	public StoreReturnManageService getService() {
		return service;
	}

	@Resource(name = "storeReturnManageService")
	public void setService(StoreReturnManageService service) {
		this.service = service;
	}

	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "storeManage/storeReturnEdit";
	}

	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "storeManage/storeReturnForm";
	}

	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "storeManage/storeReturnList";
	}

	// 去退料列表页面
	@RequestMapping("toReturnTable")
	public String toReturnTable(HttpServletRequest request, Integer id) {
		return "storeManage/storeReturn/storeReturnTable";
	}

	/**
	 * 
	 * @Description: 加载所有材料
	 * @return
	 */
	@RequestMapping({ "getMaterials" })
	@ResponseBody
	public List<String> getMaterials(HttpServletRequest request, Integer id) {
		return null;
	}

	/**
	 * 
	 * @Description: 实际的退库列表
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping({ "getReturnTable" })
	@ResponseBody
	public Page getReturnTable(HttpServletRequest request, Page page,
			String param) {

		Map<String, String> paramMap = gson.fromJson(param,
				new TypeToken<Map<String, String>>() {
				}.getType());
		Page pageQuery = null;
		pageQuery = service.queryPageTable(page, paramMap);
		return pageQuery;
	}

	// 去页面封装页
	@RequestMapping({ "toChartsList" })
	public String toChartsList(HttpServletRequest request, Integer id) {
		return "storeManage/storeReturn/storeReturnChartsList";
	}

	// 去铜料柱状图
	@RequestMapping({ "toHistogram_cop" })
	public String toHistogram_cop(HttpServletRequest request, Integer id) {
		return "storeManage/storeReturn/storeReturnCharts_cop";
	}

	// 去铝料柱状图
	@RequestMapping({ "toHistogram_al" })
	public String toHistogram_al(HttpServletRequest request, Integer id) {
		return "storeManage/storeReturn/storeReturnCharts_al";
	}

	// 去胶料柱状图
	@RequestMapping({ "toHistogram_glue" })
	public String toHistogram_glue(HttpServletRequest request, Integer id) {
		return "storeManage/storeReturn/storeReturnCharts_glue";
	}

	/**
	 * 
	 * @Description: 通过材料去查询,完成柱状图
	 * @param request
	 * @param material
	 * @return
	 */
	@RequestMapping({ "queryCharts" })
	@ResponseBody
	public Map<String, List<String>> queryCharts(HttpServletRequest request,
			String material) {
		// Map<String, List<String>> chartsMap = new HashMap<>();
		Map<String, List<String>> chartsMap = service.queryByMaterial(material);
		Map<String, String> paramMap = new HashMap<>();
		//List<StoreReturn> queryByCondition = service.queryByCondition(paramMap );
		if (chartsMap != null && chartsMap.size() > 0) {
			return chartsMap;
		}
		return chartsMap;
	}

	/**
	 * 
	 * @Description: 根据条件导出excel
	 * @param request
	 * @param params
	 * @return
	 */
	/*@RequestMapping({ "getExcel" })
	@ResponseBody
	public Map<String, String> getExcel(HttpServletRequest request,
			HttpServletResponse response, String params) {
		Map<String, String> paramMap = gson.fromJson(params,
				new TypeToken<Map<String, String>>() {
				}.getType());

		List<StoreReturn> excelList = null;
		Map<String, String> resultMap = new HashMap<>();
		try {
			excelList = service.queryByCondition(paramMap);
			if (excelList.size() > 0 && excelList != null) {
				Map<String, String> head = new HashMap<>();
			//	head.put("id","id" );
				head.put("batchcode","批次" );
				head.put("material","材料" );
				head.put("rfidCode","RFID编号" );
				head.put("model","类型" );
				head.put("colour","颜色" );
				head.put("count","数量" );
				head.put("poistion","位置" );
				head.put("forklift_code","叉车" );
				head.put("board","机台" );
				head.put("remark","备注" );
				head.put("createBy","操作人" );
				head.put("unit","单位" );
				head.put("createDate","记录时间" );
				head.put("storeDate","入库时间" );
				String json = gson.toJson(excelList);
				List<Map<String, Object>> dataList = gson.fromJson(json, new TypeToken<List<Map<String, String>>>() {
				}.getType());
			//	List<Map<String, Object>> dataList  = new ArrayList<>();
				String format = DateUtil.format(new Date(), "yyyy-MM-dd");
				String filePath = "J:\\"+format+"退料.xls";
				String fileName = "退料表";
				// excel导出具体方法
				ExcelExportUtil.export(head, dataList, filePath);
			//	ExcelExportUtil.export(head, dataList, filePath, filename, response);
			//	FileUtils.downloadFile(response, fileName, filePath);
			}
			resultMap.put("msg", "导出成功");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("msg", "导出失败");
		}
		return resultMap;
	}*/
	
	@RequestMapping("excelExport")
	public void excelExport(HttpServletRequest request,
			HttpServletResponse response, String params){
		Map<String, String> paramMap = gson.fromJson(params,
				new TypeToken<Map<String, String>>() {
				}.getType());
		try {
			service.exportExecl(response, paramMap, "退料");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


}
