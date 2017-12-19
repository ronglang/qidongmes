package com.css.business.web.subsyssell.sellManage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsyssell.bean.SellContractDetail;
import com.css.business.web.subsyssell.sellManage.service.SellContractDetailManageService;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
@Controller
@RequestMapping("/sellContractDetailManageAction")
public class SellContractDetailManageAction extends BaseSpringSupportAction<SellContractDetail, SellContractDetailManageService> {
	
	@Autowired
	private SellContractDetailManageService service;
	
	@Override
	public SellContractDetailManageService getEntityManager() {
		return service;
	}

	public SellContractDetailManageService getService() {
		return service;
	}

	@Resource(name="sellContractDetailManageService")
	public void setService(SellContractDetailManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sellManage/sellContractDetailEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sellManage/sellContractDetailForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer planBatchId){
		request.setAttribute("planBatchId", planBatchId);
		return "sellManage/sellContractDetailList";
	}
	
	@RequestMapping(value={"/loadSellContractDetailManage"})
	@ResponseBody
	public HashMap<String,Object> loadSellContractDetailManage(String page,String pagesize,Integer scId){
		return service.getSellContractDetailManageService(page,pagesize,scId);
	}
	
	@RequestMapping(value={"/deleteSCDManageByIds"})
	@ResponseBody
	public String deleteSCDManageByIds(int[] ids){
		int [] a=ids;
		service.deleteSCDManageService(a);
		return "删除成功";
	}
	
	@RequestMapping(value={"/updateSellContractDetailManage"})
	@ResponseBody
	public String updateSellContractDetailManage(SellContractDetail row){
		service.updateSellContractDetailManageService(row);
		return "更新成功";
	}
	
	@RequestMapping(value={"/addSellContractDetailManage"})
	@ResponseBody
	public String addSellContractDetailManage(SellContractDetail row){
		service.addSellContractDetailManageService(row);
		return "新增成功";
	}
	
	@RequestMapping(value={"/getComprehensiveSerchsellContractDetail"})
	@ResponseBody
	public HashMap<String,Object> getComprehensiveSerchsellContractDetail(String page,String pagesize,String batCode,String scCode,String pbatDetailCode,String pbatDetailState){
		return service.getSerchSCDManageService(page,pagesize,batCode,scCode,pbatDetailCode,pbatDetailState);
	}
	
	@RequestMapping(value={"/downSCDManageByIds"})
	@ResponseBody
	public String downSCDManageByIds(HttpServletRequest res,int[] ids){
		try {
			service.downSCDManageService(res,ids);
			return "下发成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value={"/genernateGd"})
	@ResponseBody
	public HashMap<String,Object> genernateGd(HttpServletRequest res,Integer detailId,String proPeriodLength, String deliveDateStr){
		try {
			SysUser user = SessionUtils.getUser(res);
			service.genernateGd(user,detailId,proPeriodLength,deliveDateStr);
			//return "下发成功";
			return JsonWrapper.successWrapper("","生成工作单成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
		//return null;
	}
	
	/**
	 * 
	 * @Description: 获得所有的产品规格型号   
	 * @param request
	 * @return
	 */
	@RequestMapping("getAllProGGXH")
	@ResponseBody
	public Map[] getAllProGGXH(HttpServletRequest request){
		Map[] allGGXH = service.queryAllGGXH();
		if (allGGXH!=null && allGGXH.length>0) {
			return allGGXH;
		}
		return null;
	}
	
	/**
	 * 
	 * @Description: 根据产品型号,获得所有的颜色
	 * @param request
	 * @return
	 */
	@RequestMapping("getGGXHColor")
	@ResponseBody
	public Map[] getGGXHColor(HttpServletRequest request,String proGgxh){
		Map[] ggxhColor = service.queryGGXHColor(proGgxh);
		if (ggxhColor!=null && ggxhColor.length>0) {
			return ggxhColor;
		}
		return null;
	}
	
	/**
	 * 
	 * @Description: 获得产品的工艺编码   
	 * @param request
	 * @param proGgxh 产品型号
	 * @param color 产品颜色
	 * @return
	 */
	@RequestMapping("getProCraftName")
	@ResponseBody
	public Map<String, String> getProCraftName(HttpServletRequest request,String proGgxh,String color){
		if (proGgxh==null || proGgxh=="" || color==null ||color=="")return null;
		String[] strings = service.queryProCraftCode(proGgxh,color);
		Map<String, String>map = new HashMap<>(); 
		if (strings != null && strings.length >0) {
			map.put("msg", "success");
			map.put("proCraftName", strings[0]);
			map.put("proCraftCode", strings[1]);
			map.put("proId", strings[2]);
			return map;
		}
		map.put("msg", "false");
		map.put("data", "未知");
		return map;
	}
	
	/**
	 * 加载可以下发的初始值
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("loadMayBeData")
	@ResponseBody
	public Page loadMayBeData(HttpServletRequest request,Page p,Integer planBatchId) throws Exception{
		Page page = service.loadMayBeData(p,planBatchId);
		return page;
	}
}
