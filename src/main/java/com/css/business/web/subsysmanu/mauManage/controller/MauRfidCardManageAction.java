package com.css.business.web.subsysmanu.mauManage.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauRfid;
import com.css.business.web.subsysmanu.mauManage.bean.OptionVo;
import com.css.business.web.subsysmanu.mauManage.service.MauRfidCardManageService;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.business.web.sysManage.service.SysUserManageService;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
@Controller
@RequestMapping("/mauRfidCardManageAction")
public class MauRfidCardManageAction extends BaseSpringSupportAction<MauRfid, MauRfidCardManageService> {
	@Autowired
	private SysUserManageService sysUserService;
	//@Autowired
	private MauRfidCardManageService service;
	public MauRfidCardManageService getService() {
		return service;
	}
	@Resource(name="mauRfidCardManageService")
	public void setService(MauRfidCardManageService service) {
		this.service = service;
	}
	/**
	 * 
	 * 修改功能
	 * */
	@RequestMapping({ "toUpdateRfid" })
	public String toUpdateRfid(HttpServletRequest request, String id){
		String msg="";
		try {
			
			boolean flag=service.toUpdateRfidById(id);
			if(flag){
				msg= "修改成功";
				
			}else{
				msg= "修改失败";
				
			}
		} catch (Exception e) {
		}
		//return msg;
		return "redirect:/rest/mauRfidCardManageAction/toShowPage";
	}
	/**
	 * 
	 * 跳转到新增编辑页面
	 * */
	@RequestMapping({ "toAddRfidCardEdit" })
	public String toAddRfidCardEidt(HttpServletRequest request){
		List<String> list = service.getSelectOption();
		List<OptionVo> listVo = new ArrayList<>();
		for (String obj : list) {
			OptionVo vo =new OptionVo();
			vo.setValue(obj);
			listVo.add(vo);
		}
		request.setAttribute("list", listVo);
		 return "mauManage/mauRfidCardEdit";
	}
	
	
	
	/**
	 * 
	 * 添加  数据库自动生成卡号 生成后自动跳转到主页
	 * */
	@RequestMapping({ "toAddRfid" })
	@ResponseBody
	public String toAddRfid(HttpServletRequest request,String rfidCardNumber,String materialType){
		
		 try {
			 SysUser user = SessionUtils.getUser(request);
			 if(null!=rfidCardNumber&&!"".equals(rfidCardNumber)&&null!=user&&null!=materialType&&!"".equals(materialType)){
				 
				 service.saveRfidCardNo(rfidCardNumber,user,materialType);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	//boolean flag=service.toUpdateRfidById();
		 return "redirect:/rest/mauRfidCardManageAction/toShowPage";
	}
	/**
	 * 
	 * 删除
	 * */
	@RequestMapping({"toDelete"})
	@ResponseBody
	public String toDelete(String id){
		String[] split = id.split(",");
		String msg="";
		try {
		Boolean flag =	service.toDeleteRfidById(split);
		if(flag){
			msg= "删除成功";
		}else{
			msg= "删除失败";
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
		//return  "redirect:/rest/mauRfidCardManageAction/toShowPage";
	}
	
	/**
	
	*获取下拉框材料方法
	*/
	@RequestMapping({ "getSelectOption" })
	@ResponseBody
	public HashMap<String,List<String>> getSelectOption(HttpServletRequest request) {
		HashMap<String,List<String>> map = new HashMap<>();
		List<String> list =null;
		try {
			 list = service.getSelectOption();
			 map.put("select", list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
	/**
	 * 获取扫码请求分配一个RFID编号
	 *@data:2017年7月11日
	@param materialType
	@return
	@autor:wl
	 */
	@RequestMapping({"getMessageReturnRfidByApp"})
	@ResponseBody
	public HashMap<String, Object> getMessageReturnRfidByApp(String materialType,Integer amount){
		
		try {
			if(null!=materialType&&!"".equals(materialType)&&null!=amount&&amount!=0){
				String rfidCode =service.getMessageReturnRfidByApp(materialType);//返回到APP一个RFID号
				if(null!=rfidCode&&!"".equals(rfidCode)){
					String msg=	service.updateStatus(rfidCode,materialType, amount);//同时执行修改操作
					return JsonWrapper.successWrapper(rfidCode,"成功");
				}else{
					return JsonWrapper.failureWrapperMsg("失败");
				}
			}else{
				return JsonWrapper.failureWrapperMsg("请选择原料类型");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
		
	}
	
	/**
	 * APP 初始化RFID卡
	 * @param appIds
	 * @param pType
	 * @return
	 * @author JS
	 */
	@RequestMapping({"saveAppRfid"})
	@ResponseBody
	public HashMap<String, Object> saveAppRfid(String appIds,String pType){
		try {
			if(pType==null){
				pType=",";
			}
			service.saveRfid(appIds,pType);
			return JsonWrapper.successWrapper(null, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(null, "失败");
		}
	}
	
	/**
	 * 
	 * @param rfidCode
	 * @param appId
	 * @return
	 */
	@RequestMapping({"saveAppIdByRfidCode"})
	@ResponseBody
	public HashMap<String, Object> saveAppIdByRfidCode(String rfidCode,String appId){
		String message="";
		HashMap<String, Object> map=null;
		try {
			if(null!=rfidCode&&!"".equals(rfidCode)){
				if(null!=appId&&!"".equals(appId)){
					String strJT = rfidCode.substring(4);
					if(strJT.equals("00000000000000000000")){
						map=service.saveRfidByMachine(rfidCode,appId);
					}else{
						map=service.getObjectByRfidCode(rfidCode,appId);
					}
					return map;
				}else{
					message="传入的ID号为空";
					return JsonWrapper.failureWrapperMsg(message);
				}
			}else{
				message="返回的RFID号为空";
				return JsonWrapper.failureWrapperMsg(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
		
	}
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauRfidCardForm";
	}
	
	/**
	 * 
	 * 跳转到主页
	 * */
	@RequestMapping({"toShowPage"})
	public String toShowPage(){
		return "mauManage/mauRfidCardList";
	}
	
	/**
	 * 
	 * 主页表格展示 包括高级查询分页
	 * 
	 * */
	@RequestMapping({ "toListPage" })
	@ResponseBody
	public Page toListPage(HttpServletRequest request, Integer id,Page pageParam, MauRfid rfid){
	    String startTime=request.getParameter("starttime");
	    String endTime= request.getParameter("endtime");
	    String rfidNO = request.getParameter("rfidNO");
	    String materialType = request.getParameter("materialType");
	    Page page= service.queryRfidCardService(pageParam, rfid,startTime,endTime,rfidNO,materialType);
	    return page;
	} 
	
	/**
	 * 
	 * @param rfids
	 * @return
	 * @author JS
	 */
	@RequestMapping({"repairRfid"})
	@ResponseBody
	public HashMap<String, Object> repairRfid(String rfids){
		List<MauRfid> list = service.repairRfid(rfids);
		if(list==null || list.size()<0){
			return JsonWrapper.failureWrapperMsg(list, "数据获取失败");
		}
		String ids = "";
		for (int i = 0; i < list.size(); i++) {
			ids += list.get(i).getAppId();
			if(i<list.size()-1){
				ids += ",";
			}
		}
		return JsonWrapper.successWrapper(ids,"补卡数据以发送");
	}
	
	
	
	@Override
	public MauRfidCardManageService getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}
}
