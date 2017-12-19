
package com.css.business.web.subsysmanu.mauManage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauCallForkliftRecord;
import com.css.business.web.subsysmanu.mauManage.service.MauCallForkliftRecordManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;

/**     
 * @Title: MauCallForkliftRecordManageAction.java   
 * @Package com.css.business.web.subsysmanu.mauManage.controller   
 * @Description: 呼叫叉车记录   
 * @author   rb
 * @date 2017年7月11日 上午11:53:41   
 * @company  SMTC   
 */
@Controller
@RequestMapping("mauCallForkliftRecordManageAction")
public class MauCallForkliftRecordManageAction  extends BaseSpringSupportAction<MauCallForkliftRecord, MauCallForkliftRecordManageService>{
	//@Autowired
	@Resource(name="mauCallForkliftRecordManageService")
	private MauCallForkliftRecordManageService service;

	/**
	 * @return  service
	 */
	public MauCallForkliftRecordManageService getService() {
		return service;
	}
	@Override
	public MauCallForkliftRecordManageService getEntityManager() {
		// TODO Auto-generated method stub
		return service;
	}
	
	/**
	 * 
	 * @Description: 去生产电子看板的呼叫叉车记录
	 * @return
	 */
	@RequestMapping("toCenDisplay")
	public String toCenDisplay(){
		return "mauManage/cendisplay/callforklift";
	}
	
	//去呼叫叉车动态页面
	@RequestMapping("toCallCar")
	public String toCallCar(){
		return "mauManage/cendisplay/callCar";
	}
	
	
	/**
	 * 
	 * @Description: 查询不同地方 未做应答 的叉车呼叫   
	 * @param req
	 * @param condition
	 * @return
	 */
	@RequestMapping("getConditionCall")
	@ResponseBody
	public List<MauCallForkliftRecord>getConditionCall(HttpServletRequest req,String condition){
		List<MauCallForkliftRecord>list = new ArrayList<>();
		try {
			if (condition!=null && condition!="" ) {
				list = service.queryCallList(condition);
			}else {
				list = service.queryCallList("");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	/**
	 * 叉车接单接口
	 * 叉车接单，app调用接口，向服务器传任务id,和叉车名（任务领取成功后，
	 * 返回success,同时向消息队列发消息，告知该任务已被领取）

	 *@data:2017年7月22日
	@param forkliftCode
	@param id
	@return
	@autor:wl
	 */
	@RequestMapping({"callForkliftRecieveBill"})
	@ResponseBody
	public HashMap<String, Object> callForkliftRecieveBill(String forkliftName,Integer id){
		HashMap<String, Object> map=new HashMap<>();
		try {
			map=service.callForkliftRecieveBill(forkliftName,id);
			
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
		return map;
	}
	
	/**
	 * 叉车领完物料操作与app进行通信
	 * 
	 * 装载物料成功后，app调用接口，向服务器传任务id，物料rfid,
	 * 和当前毫秒数（如果服务器验证rfid失败，向消息队列发警报消息）
		（校验原料的规格和制定计划的规格型号是否一致）
	 *@data:2017年7月31日
	@param forkliftName
	@param id
	@return
	@autor:wl
	 */
	@RequestMapping({"forkliftEndPickMateriel"})
	@ResponseBody
	public HashMap<String, Object> forkliftEndPickMateriel(String forkliftName,String rfidNumber,Integer id,long date){
		HashMap<String, Object> map=new HashMap<>();
		try {
			if(null!=rfidNumber&&!"".equals(rfidNumber)){
				map=service.endPickMateriel(rfidNumber,forkliftName,id,date);
				//发送异常信息到消息队列
				boolean flag=service.sendExceptionToMes(map);
				if(flag){
					String  message = (String)map.get("data");
					return JsonWrapper.successWrapper(message,"发送异常消息成功");
				}
			}else{
				return JsonWrapper.failureWrapperMsg("传入的原料RFID编号为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
		return map;
	}
	
	
	/**
	 * 叉车卸载货物后  
	 * 卸载物料成功后，app调用接口，向服务器传任务id，物料rfid,
	 * 和当前毫秒数（如果服务器验证rfid失败，向消息队列发警报消息）
	 * （校验原料的规格和制定计划的规格型号是否一致）
	 *@data:2017年7月31日
	@param forkliftName
	@param id
	@return
	@autor:wl
	 */
	
	@RequestMapping({"forkliftUninstallMateriel"})
	@ResponseBody
	public HashMap<String, Object> forkliftUninstallMateriel(String forkliftName,String machine_id,Integer id,String rfidNumber,long date ){
		HashMap<String, Object> map=new HashMap<>();
		try {
			map=service.uninstallMateriel(machine_id, rfidNumber,forkliftName,id,date);
			//发送异常信息到消息队列
			boolean flag=service.sendExceptionToMes(map);
			if(flag){
				String  message = (String)map.get("data");
				return JsonWrapper.successWrapper(message,"发送异常消息成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
		return map;
	}
	
	/**
	 * 叉车完成任务接口
	 * @param pro_rfid    插取货物rfid编码
	 * @param addr_rfid   地表rfid
	 * @param deviceId    叉车编码
	 * @return
	 */
	@RequestMapping("finishTask")
	@ResponseBody
	public HashMap<String,Object> finishTask(String pro_rfid, String deviceId,String addr_rfid){
		return service.finishTaskService(pro_rfid, deviceId, addr_rfid);
	}
	
	
	
	
	
	/******************************TG*******************************/
	/**
	 * 
	 * @Description: 查询不同地方 未做应答 的叉车呼叫   
	 * @param req
	 * @param condition
	 * @return
	 */
	@RequestMapping("getCallCarInfo")
	@ResponseBody
	public List<MauCallForkliftRecord>getCallCarInfo(){
		List<MauCallForkliftRecord>list = new ArrayList<>();
		try {
			list = service.queryCallCarList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
//	@RequestMapping("getCallCarInfo")
//	@ResponseBody
//	public String getCallCarInfo(HttpServletRequest req){
//		List<MauCallForkliftRecord>list = new ArrayList<>();
//		try {
//			list = service.queryCallCarList();
//			req.setAttribute("list", list);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return "mauManage/cendisplay/toMauDisplayListNew";
//	}
	
	
}
