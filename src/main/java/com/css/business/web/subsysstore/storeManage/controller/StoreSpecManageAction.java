package com.css.business.web.subsysstore.storeManage.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysstore.bean.StoreSpec;
import com.css.business.web.subsysstore.storeManage.service.StoreSpecManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
@Controller
@RequestMapping("/storeSpecManageAction")
public class StoreSpecManageAction extends BaseSpringSupportAction<StoreSpec,StoreSpecManageService>{
	
	private StoreSpecManageService service;
	@Override
	public StoreSpecManageService getEntityManager() {
		// TODO Auto-generated method stub
		return service;
	}
	public StoreSpecManageService getService() {
		return service;
	}
	@Resource(name="storeSpecManageService")
	public void setService(StoreSpecManageService service) {
		this.service = service;
	}
	//return view 
	@RequestMapping({"/toSpecManage"})
	public String toSpecManage()
	{
		return "storeManage/storeSpec/storeSpecForm";
	}
	//get page
	@RequestMapping({"/queryForm"})
	@ResponseBody
	public Page queryForm(Page page,String params)
	{
		//try-catch
		JSONObject jsonObj = JSONObject.fromObject(params);
		DetachedCriteria criteria =  getRestrictions(jsonObj);
		try {
			List<?> list = service.queryRecords(criteria,0,page.getPagesize());
			Integer totalSize = service.getCount();
			return new Page(page.getPageNo(),totalSize,page.getPagesize(),list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	//return Criterion object--
	private DetachedCriteria getRestrictions(final JSONObject jsonObj){
		//获取Json对象
		DetachedCriteria criteria =  DetachedCriteria.forClass(StoreSpec.class);
		if(jsonObj.isNullObject())
		{
			return criteria;
		}
		Iterator<?> iterator = jsonObj.keys();
		while(iterator.hasNext())
		{
			String key = iterator.next().toString();
			String value =jsonObj.getString(key);
			criteria.add(Restrictions.eq(key, value));
		}
		return criteria;
	}
	
	//remove records
	@RequestMapping({"/removeCollections"})
	@ResponseBody
	public String removeCollections(String params){
		ArrayList<Integer> col = new ArrayList<Integer>();
		for(String s:params.split(","))
		{
			col.add(Integer.parseInt(s));
		}
		try {
			service.removeCollections(col);
			return "Success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
	
	//saveOrUpdate
	@RequestMapping({"/saveOrUpdate"})
	@ResponseBody
	public boolean saveOrUpdate(String data){
		StoreSpec entity;
		try {
			entity = (StoreSpec) jsonToObject(data,StoreSpec.class);
			service.saveOrUpdate(entity);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	public static <T> T jsonToObject(String jsonString, Class<T> pojoCalss) {    
        try{    
            Object pojo = null;    
            JSONObject jsonObject = JSONObject.fromObject(jsonString);    
            pojo = JSONObject.toBean(jsonObject, pojoCalss);    
            return (T)pojo;    
        }catch(Exception ex){    
            ex.printStackTrace();    
            return null;    
        }    
    }  
}
