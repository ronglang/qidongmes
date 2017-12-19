package com.css.business.web.subsyssell.sellManage.controller;

import java.net.URLDecoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsyscraft.bean.CraCraftProduct;
import com.css.business.web.subsyscraft.craManage.service.CraCraftProductManageService;
import com.css.business.web.subsysmanu.bean.MauProduct;
import com.css.business.web.subsysmanu.mauManage.service.MauProductManageService;
import com.css.business.web.subsysplan.plaManage.utils.JsonUtil;
import com.css.business.web.subsyssell.bean.SellContract;
import com.css.business.web.subsyssell.bean.SellContractPlanBatch;
import com.css.business.web.subsyssell.sellManage.service.SellContractManageService;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/sellContractManageAction")
public class SellContractManageAction extends
		BaseSpringSupportAction<SellContract, SellContractManageService> {

	// @Autowired
	private SellContractManageService service;

	@Override
	public SellContractManageService getEntityManager() {
		return service;
	}

	public SellContractManageService getService() {
		return service;
	}

	@Resource(name = "sellContractManageService")
	public void setService(SellContractManageService service) {
		this.service = service;
	}

	@Resource(name = "craCraftProductManageService")
	private CraCraftProductManageService craProService;
	@Resource(name = "mauProductManageService")
	private MauProductManageService proService;

	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "sellManage/sellContractEdit";
	}

	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "sellManage/sellContractForm";
	}

	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "sellManage/sellContractList";
	}

	@RequestMapping({ "toContracttable" })
	public String toContracttable(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "sellManage/listChildren/contractTable";
	}

	@RequestMapping({ "toSearch" })
	public String toSearch(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "sellManage/listChildren/search";
	}

	/**
	 * TODO 以下为合同管理新页面
	 * 
	 * @author JS
	 */

	@RequestMapping({ "toNewTablePage" })
	public String toNewTablePage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "sellManage/newContract/sellContractTable";
	}

	@RequestMapping({ "toNewListPage" })
	public String toNewListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "sellManage/newContract/sellContractList";
	}

	@RequestMapping({ "toNewFormPage" })
	public String toNewFormPage(HttpServletRequest request, Integer id,
			Integer flag) {
		request.setAttribute("id", id);
		request.setAttribute("flag", flag);
		return "sellManage/newContract/sellContractForm";
	}

	@RequestMapping({ "toNewEditPage" })
	public String toNewEditPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "sellManage/newContract/sellContractEdit";
	}

	/**
	 * @author JiangShuai
	 * @param request
	 * @param parm
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "querySellContract" })
	@ResponseBody
	public Page querySellContract(HttpServletRequest request, Page parm,
			SellContract entity) throws Exception {

		return dataGridPage(request, parm, entity);
	}

	@RequestMapping({ "addSellContract" })
	@ResponseBody
	public Page addSellContract(HttpServletRequest request, Page parm,
			SellContract entity) {
		try {
			// TODO Auto-generated catch block
			return dataGridPage(request, parm, entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping({ "delSellContract" })
	@ResponseBody
	public Page delSellContract(HttpServletRequest request, Page parm,
			SellContract entity, String scCode) {
		try {
			service.delContractService(scCode);
			return dataGridPage(request, parm, entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = { "/loadSellContractManage" })
	@ResponseBody
	public Page loadSellContractManage(HttpServletRequest req, Page page, SellContract ent) throws Exception {
		
		return service.loadSellContractManage(page, ent);
	}

	@RequestMapping(value = { "/getComSerchSellContract" })
	@ResponseBody
	public Page getComSerchSellContract(Page page, String orderCode,
			String serchState, String serchscCode, String serchcusCode,
			String serchAgentBy) {
		return service.getComSerchSellContractService(page, orderCode,
				serchState, serchscCode, serchcusCode, serchAgentBy);
	}

	@RequestMapping(value = { "/addSellContractManage" })
	@ResponseBody
	public String addSellContractManage(HttpServletRequest req, SellContract row) {
		SysUser user = SessionUtils.getUser(req);
		row.setScState("未分解");
		try {
			String str = row.getSellContractPlanBatchLstStr();
			List<SellContractPlanBatch> lst = new ArrayList<SellContractPlanBatch>();
			if (str != null && str.length() > 0) {
				JSONArray json = JSONArray.fromObject(str);
				for (int i = 0; i < json.size(); i++) {
					SellContractPlanBatch f = (SellContractPlanBatch) (JSONObject
							.toBean((JSONObject) json.get(i),
									SellContractPlanBatch.class));
					lst.add(f);
				}

				row.setSellContractPlanBatchLst(lst);
			} else {
				throw new Exception("合同批次不能为空");
			}
			service.addSellContractManageService(user, row);
			return "新增成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@Deprecated
	@RequestMapping(value = "/addSellContractPlanBatch", method = RequestMethod.POST)
	@ResponseBody
	public String addSellContractPlanBatch(HttpServletRequest req,
			@RequestBody SellContractPlanBatch[] data) {
		SysUser user = SessionUtils.getUser(req);
		try {
			List<SellContractPlanBatch> list = new ArrayList<SellContractPlanBatch>();
			for (SellContractPlanBatch p : data) {
				list.add(p);
				// 得到规格型号,颜色
				String proGgxh = p.getProGgxh();
				String proColor = p.getProColor();
				// 得到产品code 从产品工艺表中获取 proid
				List<CraCraftProduct> craList = craProService
						.getCraCraftProduct(proGgxh, proColor);
				if (craList != null && craList.size() > 0) {
					Integer proId = craList.get(0).getProId();
					// 从产品表里获得procode
					MauProduct mauProduct = proService.get(proId);
					if (mauProduct != null) {
						p.setProCode(mauProduct.getPro_code());
					}
				}
				p.setCreateBy(user.getAccount());
				p.setCreateDate(new Date(System.currentTimeMillis()));
				p.setScPlanbatState("未下发");
			}
			service.addSellContractPlanBatchService(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
		return "新增成功111";
	}

	@RequestMapping(value = { "/deleteSellContractManageByIds" })
	@ResponseBody
	public String deleteSellContractManageByIds(int[] ids) {
		service.deleteSellContractManageByIdsServices(ids);
		return "删除成功";
	}

	@RequestMapping(value = { "/loadSellContractPlanBatchManage" })
	@ResponseBody
	public HashMap<String, Object> loadSellContractPlanBatchManage(String page,
			String pagesize, String scCode) {
		return service.getSellContractPlanBatchService(page, pagesize, scCode);
	}

	@RequestMapping(value = { "/updateSellContractManage" })
	@ResponseBody
	public String updateSellContractManage(SellContract row) {
		service.updateSellContractManageService(row);
		return "更新成功";
	}

	@RequestMapping(value = "/updateSellContractPlanBatchManage", method = RequestMethod.POST)
	@ResponseBody
	public String updateSellContractPlanBatchManage(
			@RequestBody SellContractPlanBatch[] data) {
		List<SellContractPlanBatch> list = new ArrayList<SellContractPlanBatch>();
		for (int i = 0; i < data.length; i++) {
			list.add(data[i]);
		}
		service.updateSellContractPlanBatchService(list);
		return "更新成功";
	}

	@RequestMapping(value = { "/decSellContractDetail" })
	@ResponseBody
	public Map<String, Object> decSellContractDetail(HttpServletRequest req,
			int[] ids) {
		try {
			SysUser user = SessionUtils.getUser(req);
			service.decSellContractDetailService(user, ids);
			// return "分解成功";
			return JsonWrapper.successWrapper("", "分解成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// return e.getMessage();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}

	/**
	 * TODO 以下为得到搜索下拉框
	 * 
	 * @return
	 * @author JS
	 */

	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getscCode" })
	@ResponseBody
	public Map[] getscCode() {
		Map[] str = service.getscCode();
		return str;

	}

	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getcusCode" })
	@ResponseBody
	public Map[] getcusCode() {
		Map[] str = service.getcusCode();
		return str;

	}

	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getagentBy" })
	@ResponseBody
	public Map[] getagentBy() {
		Map[] str = service.getagentBy();
		return str;

	}

	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getProType" })
	@ResponseBody
	public Map[] getProType() {
		Map[] str = service.getProType();
		return str;

	}

	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getProGgxh" })
	@ResponseBody
	public Map[] getProGgxh(String proType) {
		Map[] str = service.getGgxh(proType);
		return str;

	}

	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getProColor" })
	@ResponseBody
	public Map[] getProColor(String proGgxh) {
		Map[] str = service.getColor(proGgxh);
		return str;

	}

	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getProCraftCode" })
	@ResponseBody
	public Map[] getProCraftCode() {
		Map[] str = service.getProCraftCode();
		return str;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getProCode" })
	@ResponseBody
	public Map[] getProCode(String proType, String proGgxh, String proColor) {
		Map[] str = service.getProCode(proType, proGgxh, proColor);
		return str;
	}

	/**
	 * 
	 * 以下为获取前台添加数据，并保存
	 * 
	 * @author JS
	 */
	@RequestMapping({ "saveContract" })
	@ResponseBody
	public HashMap<String, Object> saveContract(HttpServletRequest req,
			String data, String bean, String id) {
		SysUser user = SessionUtils.getUser(req);
		Gson gson = new Gson();
		try {
			String decode = URLDecoder.decode(data, "UTF-8");
			if (id == null || "".equals(id)) {
				List<SellContractPlanBatch> lsc = gson.fromJson(decode,
						new TypeToken<List<SellContractPlanBatch>>() {
						}.getType());
				SellContract contract = gson.fromJson(bean, SellContract.class);
				service.saveSellContract(user, contract, lsc);
			} else {
				List<SellContractPlanBatch> lsc = gson.fromJson(decode,
						new TypeToken<List<SellContractPlanBatch>>() {
						}.getType());
				SellContract contract = gson.fromJson(bean, SellContract.class);
				contract.setId(Integer.parseInt(id));
				service.updateSellContract(user, contract, lsc);
			}
			return JsonWrapper.successWrapper(user, "");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(user, "");
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping({ "delContracts" })
	@ResponseBody
	public HashMap<String, Object> delContracts(HttpServletRequest req,
			String data) {
		JSONArray ja = JSONArray.fromObject(data);
		List<Object> list = JsonUtil.jsonToList(ja);
		try {
			for (Object obj : list) {
				Map<String, Object> map = (Map<String, Object>) obj;
				Integer id = Integer.parseInt(map.get("id").toString());
				//String scCode = service.getScCode(id);
				//service.delPlaContract(scCode);
				//在service完成校验  zhaichunlei
				service.delContract(id);
			}
			return JsonWrapper.successWrapper(null, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(null, e.getMessage());
		}

	}

	/**
	 * 根据ID 查询合同，并返回到页面
	 * 
	 * @param req
	 * @param id
	 * @return
	 */
	@RequestMapping({ "getContracts" })
	@ResponseBody
	public SellContract getContracts(HttpServletRequest req, Integer id) {
		SellContract contract = service.getContracts(id);
		return contract;
	}

	/**
	 * 根据生产通知code查询生产批次
	 * 
	 * @param req
	 * @param p
	 * @param scCode
	 * @return
	 */
	@RequestMapping({ "getContractsGrid" })
	@ResponseBody
	public Page getContractsGrid(HttpServletRequest req, Page p, String scCode) {
		Page page = service.getContractsGrid(p, scCode);
		return page;

	}

	/**
	 * 
	 * @Description: 获得当前的通知单的完成情况 (页面环装图使用)   
	 * @return
	 */
	@RequestMapping("getSellContractSurvey")
	@ResponseBody
	public Map<String,String> getSellContractSurvey(){
		Map<String,String> map=service.getSellContractSurvey();
		return map;
	}
	
	/**
	 * 
	 * @Description: 根据订单编号,获得所有生产通知单的完成率   
	 * @param orderCode
	 * @return
	 */
	@RequestMapping("getCompleteRateByOrderCode")
	@ResponseBody
	public HashMap<String, Object>getCompleteRateByOrderCode(String orderCode){
		service.getCompleteRateByOrder(orderCode);
		return null;
	}
	
}
