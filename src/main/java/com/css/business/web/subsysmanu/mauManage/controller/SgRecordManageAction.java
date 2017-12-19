package com.css.business.web.subsysmanu.mauManage.controller;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.SgRecord;
import com.css.business.web.subsysmanu.mauManage.service.SgRecordManageService;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
@Controller
@RequestMapping("/sgRecordManageAction")
public class SgRecordManageAction {
  @Autowired
  private SgRecordManageService service;
  @RequestMapping({"getMauSgRecord"})
  @ResponseBody
  public HashMap<String, Object> getSgRecord(){
	  try{
		  List<SgRecord> list= service.getSgRecord();
		  return JsonWrapper.successWrapper(list,"成功"); 
	  }catch(Exception e){
		  return JsonWrapper.failureWrapperMsg("失败");
	  }
  }
  @RequestMapping({"saveMauSgData"})
  @ResponseBody
  public HashMap<String,Object> sgMachineData(HttpServletRequest request,String mauSgData ){
//	  String data="1401,2401";
	  String[] strArr=mauSgData.split(",");
	      try{
		  for (int i = 0; i <strArr.length; i++) {
			  service.sgMachineDataService(strArr[i]);
		  }
		  return JsonWrapper.successWrapper(null,"成功");
	      }
		  catch(Exception e){
			  return JsonWrapper.failureWrapperMsg("失败");
		  }
  }
}
