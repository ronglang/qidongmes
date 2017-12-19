package com.css.business.web.subsysstore.storeManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysplan.bean.PlaMacTaskMateril;
import com.css.business.web.subsysstore.bean.StoreMrecord;
import com.css.business.web.subsysstore.storeManage.service.StoreMrecordManageService;
import com.css.business.web.subsysstore.storeManage.service.StoreObjManageService;
import com.css.business.web.subsysstore.storeManage.vo.JudgeMaterVo;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/storeMrecordManageAction")
public class StoreMrecordManageAction extends
		BaseSpringSupportAction<StoreMrecord, StoreMrecordManageService> {

	// @Autowired
	private StoreMrecordManageService service;

	@Resource(name = "storeObjManageService")
	private StoreObjManageService somsSer;

	@Override
	public StoreMrecordManageService getEntityManager() {
		return service;
	}

	public StoreMrecordManageService getService() {
		return service;
	}

	@Resource(name = "storeMrecordManageService")
	public void setService(StoreMrecordManageService service) {
		this.service = service;
	}

	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "storeManage/storeMrecordEdit";
	}

	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "storeManage/storeMrecordForm";
	}

	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "storeManage/storeMrecordList";
	}

	@RequestMapping({ "getStock" })
	@ResponseBody
	public Page getStock(Page p) {
		Page page = somsSer.getInStock(p);
		return page;
	}

	@RequestMapping({ "getAllStock" })
	@ResponseBody
	public List<StoreMrecord> getAllStock() {
		List<StoreMrecord> list = service.getAllStock();
		return list;
	}

	/**
	 * 
	 * @Description: 首页库存统计
	 * @param request
	 * @param materType
	 * @return
	 */
	@RequestMapping("getClassify")
	@ResponseBody
	public List<Map<String, String>> getClassify(HttpServletRequest request,
			String materType) {
		// 分类查询
		List<Map<String, String>> list = service.getClassify(materType);
		return list;
	}

	/**
	 * 
	 * @Description: 仓库电子看板初始化数据
	 * @param request
	 * @return
	 */
	@RequestMapping("getAllClassify")
	@ResponseBody
	public List<Map<String, String>> getObjAndScap(HttpServletRequest request) {
		// 分类查询
		List<Map<String, String>> list = service.getObjAndScap();
		return list;
	}

	/**
	 * 
	 * @param materName
	 *            物料名称
	 * @param materGgxh
	 *            物料规格型号
	 * @param materType
	 *            物料类型
	 * @param unit
	 *            单位
	 * @param rfids
	 *            RFID卡号(包括物料与地标卡，地标卡只有一个)
	 * @param batchCode
	 *            批次号
	 * @param checkStock
	 *            盘点数量
	 * @return
	 * @author JS
	 */
	@RequestMapping("checkStore")
	@ResponseBody
	public HashMap<String, Object> checkStore(String materName,
			String materGgxh, String materType, String unit, String rfids,
			String batchCode, Double checkStock) {
		boolean flag = false;
		try {
			flag = service.checkStore(materName, materGgxh, materType, unit,
					rfids, batchCode, checkStock);
			if(flag){
				return JsonWrapper.successWrapper(null, "盘点记录已生成");
			}else{
				return JsonWrapper.failureWrapper(null, "盘点失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 检验 原材料是否匹配
	 * @param request
	 * @param rfidCode
	 * @return
	 */
	@RequestMapping("jungeMater")
	@ResponseBody
	public HashMap<String, Object> jungeMater(HttpServletRequest request,String jungeMater){
		JudgeMaterVo vo = service.judgeMater(jungeMater);
		boolean type = vo.isType();
		if (type) {
			return JsonWrapper.successWrapper(null,"上传成功");
		}else {
			return JsonWrapper.failureWrapperMsg(vo.getMsg());
		}
	}
	
	@RequestMapping({"getStoreMrecordByPage"})
	@ResponseBody
	public Page getStoreMrecordByPage(Page p,String batchCode,String materGgxh){
		Page page = service.getStoreMrecordByPage(p, batchCode, materGgxh);
		return page;
	}
	
	
	@RequestMapping({"sureCheck"})
	@ResponseBody
	public HashMap<String, Object> sureCheck(String ids){
		Gson gson = new Gson();
		List<StoreMrecord> list = gson.fromJson(ids, new TypeToken<List<StoreMrecord>>() {}.getType());
		for (StoreMrecord storeMrecord : list) {
			try {
				service.sureCheck(storeMrecord.getId());
			} catch (Exception e) {
				e.printStackTrace();
				return JsonWrapper.failureWrapperMsg(null, "盘点失败");
			}
		}
		return JsonWrapper.successWrapper(null, "盘点成功");
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping({"getSmBatchCode"})
	@ResponseBody
	public Map[] getSmBatchCode(){
		Map[] code = service.getSmBatchCode();
		return code;
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping({"getSmMaterGgxh"})
	@ResponseBody
	public Map[] getSmMaterGgxh(){
		Map[] ggxh = service.getSmMaterGgxh();
		return ggxh;
	}
	
	
	@RequestMapping({"getPlaMacTaskMaterilList"})
	@ResponseBody
	public HashMap<String, Object> getPlaMacTaskMaterilList(){
		List<PlaMacTaskMateril> list = service.getPlaMacTaskMaterilList();
		HashMap<String, Object> map = JsonWrapper.successWrapper(list);
		return map;
	}
	
	
}
