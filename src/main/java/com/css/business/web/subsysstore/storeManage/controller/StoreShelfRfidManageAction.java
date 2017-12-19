package com.css.business.web.subsysstore.storeManage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysstore.bean.StoreShelfRfid;
import com.css.business.web.subsysstore.storeManage.service.StoreShelfRfidManageService;
import com.css.common.web.syscommon.dao.support.Page;
/**
 * ClassName: StoreShelfRfidManageAction 
 * @Description: 用于货架的rfid施工
 * @author mjq
 * @date 2017年7月17日
 */
@Controller
@RequestMapping("/storeShelfRfidManageAction")
public class StoreShelfRfidManageAction {
    @Autowired
	private  StoreShelfRfidManageService service;
    /**
     * @Description: 用于根据条件查询车间的货架地标rfid的信息，也可查询已施工和未施工数据
     * @param 
     * @return Page  
     * @throws
     * @author mjq
     * @date 2017年7月17日
     */
    @RequestMapping({"getShelfRfid"})
    @ResponseBody
    private List<StoreShelfRfid> getShelfRfid(){
    	return service.getShelfRfidService();
    }
    
    /**
     * @Description: 用于保存货架施工的rfid信息
     * @param @param storeShelfRfid   
     * @return void  
     * @throws
     * @author mjq  
     * @date 2017年7月17日
     */
    @RequestMapping({"saveShelfRfidData"})
    @ResponseBody
    private String saveShelfRfidData(List<StoreShelfRfid> list){
    	try{
    		service.saveShelfRfidData(list);
    		return "保存成功";
    	}
    	catch(Exception e){
    		return "保存失败";
    	}
    }
   
}
