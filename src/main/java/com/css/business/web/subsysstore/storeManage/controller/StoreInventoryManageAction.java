package com.css.business.web.subsysstore.storeManage.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysstore.bean.StoreInventory;
import com.css.business.web.subsysstore.storeManage.service.StoreInventoryManageService;
import com.css.business.web.subsysstore.storeManage.vo.InventoryPieChartVo;
import com.css.business.web.subsysstore.storeManage.vo.StoreInventoryDetailedVo;
import com.css.business.web.sysManage.bean.SysCommdic;
import com.css.business.web.sysManage.service.SysCommdicManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
@Controller
@RequestMapping("/storeInventoryManageAction")
public class StoreInventoryManageAction extends BaseSpringSupportAction<StoreInventory, StoreInventoryManageService>{

	@Autowired
	private StoreInventoryManageService service;
	@Autowired
	private SysCommdicManageService commdicService;
	
	public StoreInventoryManageService getEntityManager() {
		return service;
	}
	/**
	 * 盘点列表
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/inventory/storeInventoryList";
	}
	
	/**
	 * 获取grid数据
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping({ "storeInventoryList" })
	@ResponseBody
	public Page storeInventoryList(HttpServletRequest request,Page page){
		//查询参数封装在map里边
//		Map<String ,Object> map = new HashMap<String,Object>();
//		List<Object> list = new ArrayList<Object>();
//		for(int i = 0; i < 5; i++){
//			StoreInventory bean = new StoreInventory();
//			bean.setId(i);
//			bean.setCreate_by("创建人"+i);
//			bean.setCreate_date(new Timestamp(System.currentTimeMillis()));
//			bean.setInv_materiel("物料"+i);
//			bean.setInv_time(new Timestamp(System.currentTimeMillis()));
//			list.add(bean);
//		}
		
		service.findByQueryPage(request,page);
//		page.setData(list);
		return page;
	}
	
	/**
	 * 材料类型列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({ "getMaterielType" })
	@ResponseBody
	public HashMap<String, Object> getMaterielType(HttpServletRequest request,HttpServletResponse response){
		try{
			List<SysCommdic> list = commdicService.findByClas("原材料类型");
			return JsonWrapper.successWrapper(list, null);
		}catch(Exception e){
			e.printStackTrace();
			return JsonWrapper.failureWrapper(e);
		}
	}
	/**
	 * 盘点明细页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({"getStoreInventoryDetailed"})
	public String getStoreInventoryDetailed(HttpServletRequest request,HttpServletResponse response){
		String materielType = request.getParameter("materielType");
		System.out.println("原材料类型"+materielType);
		request.setAttribute("materielType", materielType);
		return "storeManage/inventory/storeInventoryDetailed";
	}
	/**
	 * 盘点明细数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({"getInventoryDetailedData"})
	@ResponseBody
	public Page getInventoryDetailedData(HttpServletRequest request,HttpServletResponse response,Page page){
		try{
			List<Object> list = new ArrayList<Object>();
			StoreInventoryDetailedVo vo = null;
			for(int i = 0;i < 10;i++){
				vo = new StoreInventoryDetailedVo();
				vo.setColor("颜色"+i);
				vo.setConclusion("缺料"+i);
				vo.setInv_time(new Timestamp(System.currentTimeMillis()));
				vo.setInventory(Double.parseDouble(i+""));//库存量
				vo.setInventory_by("盘点人"+i);
				vo.setMateriel("物料"+i);
				vo.setModel("型号"+i);
				vo.setReturnOfMaterial(Double.parseDouble(i+""));//退料量
				vo.setStorageNumber(Double.parseDouble(i+""));//入库数量
				vo.setTheLibrary(Double.parseDouble(i+""));//出库数量
				vo.setUnit("计量单位"+i);
				vo.setWaste(Double.parseDouble(i+""));//废料
				vo.setLackNumber(Double.parseDouble(i+""));//缺料量
				list.add(vo);
			}
			
			
			page.setData(list);
			//TODO 实际上这个位置需要根据传入参数，决定查询出来的数据，并且需要整合。
			service.findByQueryList(request,page);
			return page;
		}catch(Exception e){
			e.printStackTrace();
			return page;
		}
		
	}
	
	/**
	 * 初始化查询条件数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({ "getSearchType" })
	@ResponseBody
	public HashMap<String, Object> getSearchType(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			//返回查询条件选项数据
			List<SysCommdic> materielList = commdicService.findByClas("原材料类型");
			List<SysCommdic> colorList = commdicService.findByClas("颜色");
			List<SysCommdic> inventoryList = commdicService.findByClas("盘点结果");
			map.put("材料", materielList);
			map.put("规格型号", null);
			map.put("颜色", colorList);
			map.put("盘点结果", inventoryList);
			return JsonWrapper.successWrapper(map, null);
		}catch(Exception e){
			e.printStackTrace();
			return JsonWrapper.failureWrapper(e);
		}
	}
	
	/**
	 * 跳转到胶料饼状图盘点页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({ "getEchartRubber" })
	public String getEchartRubber(HttpServletRequest request,HttpServletResponse response){
		String materielType = request.getParameter("materielType");
		System.out.println("原材料类型"+materielType);
		request.setAttribute("materielType", materielType);
		return "storeManage/inventory/inventoryRubberPieChart";
	}
	/**
	 * 请求盘点胶料echart数据
	 * @return
	 */
	@RequestMapping({ "reqEchartRubberData" })
	@ResponseBody
	public HashMap<String, Object> reqEchartRubberData(HttpServletRequest request){
		String materielType = request.getParameter("materielType");
		System.out.println("原材料类型"+materielType);
		InventoryPieChartVo vo = new InventoryPieChartVo();
		
		
		//TODO 封装数据
		Page page = new Page();
		List<Object> resultList = new ArrayList<Object>();
		Date inv_times = null;
		service.get(page, resultList, null, inv_times, materielType, null, null);
		
		List<Map<String, String>> datas = 饼状图头信息(vo,page);
		vo.setDatas(datas);
		return JsonWrapper.successWrapper(vo);
	}
	private List<Map<String, String>> 饼状图头信息(InventoryPieChartVo vo,Page page) {
		
		//TODO 封装图例
		List<String> legends = new ArrayList<String>();
		legends.add("库存");
		legends.add("入库");
		legends.add("退料");
		legends.add("废料");
		legends.add("出库");
		vo.setLegends(legends);
		//TODO 封装数据
		List<Map<String,String>> datas = new ArrayList<Map<String,String>>();
		for(int i = 0;i < 5;i ++){
			Map<String,String> map = new HashMap<String,String>();
			map.put("name", legends.get(i));
			map.put("value", i+"");
			datas.add(map);
		}
		
		datas.clear();
		
		//将查询到的所有物品全部加起来，算出值,Double[]顺序为库存、入库、退料、废料、出库
		Map<String,Double[]> resultMap = new HashMap<String,Double[]>();
		@SuppressWarnings("unchecked")
		List<Object> objList = page.getData();
		String material = null;
		for(Object obj : objList){//实际上这里只会有一种物料
			StoreInventoryDetailedVo v = (StoreInventoryDetailedVo)obj;
			String key = v.getMateriel();
			material = key;
			if(resultMap.get(key)==null){//只会在第一次进来
				resultMap.put(key, new Double[5]);
				
			}
			resultMap.get(key)[0] = v.getInventory();//库存量
			resultMap.get(key)[1] = v.getStorageNumber();//入库数量
			resultMap.get(key)[2] = v.getReturnOfMaterial();//退料
			resultMap.get(key)[3] = v.getWaste();//废料
			resultMap.get(key)[4] = v.getTheLibrary();//出库
		}
		
		
		for (int i = 0; i < legends.size(); i++) {
			Map<String,String> map = new HashMap<String,String>();
			String legend = legends.get(i);
			map.put("name", legend);
			map.put("value",resultMap.get(material)[i] +"");
			datas.add(map);
		}
		vo.setTitle("本次"+material+"当前系统库存数据");
		return datas;
	}
	@RequestMapping({ "toInventoryTaskPage" })
	public String toInventoryTaskPage(HttpServletRequest request,HttpServletResponse response){
		return "storeManage/inventory/storeInventoryEidt";
	}
	
	
}
