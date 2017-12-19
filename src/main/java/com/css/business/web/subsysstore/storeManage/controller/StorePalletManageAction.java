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

import com.css.business.web.subsysstore.bean.StorePallet;
import com.css.business.web.subsysstore.bean.StoreSpec;
import com.css.business.web.subsysstore.storeManage.service.StorePalletManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;

@Controller
@RequestMapping({"/storePalletManageAction"})
public class StorePalletManageAction extends BaseSpringSupportAction<StorePallet,StorePalletManageService>{

	private StorePalletManageService service;
	@Resource(name="storePalletManageService")
	public void setService(StorePalletManageService service) {
		this.service = service;
	}
	@Override
	public StorePalletManageService getEntityManager() {
		// TODO Auto-generated method stub
		return service;
	}
	//return view
	@RequestMapping({"toPalletForm"})
	public String toPalletForm(){
		return "storeManage/storePallet/storePalletForm";
	}
	
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
		DetachedCriteria criteria =  DetachedCriteria.forClass(StorePallet.class);
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
	
	@RequestMapping({"/removeCollections"})
	@ResponseBody
	public boolean removeCollections(String params){
		ArrayList<Integer> col = new ArrayList<Integer>();
		for(String s:params.split(","))
		{
			col.add(Integer.parseInt(s));
		}
		try {
			service.removeCollections(col);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping({"/saveOrUpdate"})
	@ResponseBody
	public boolean saveOrUpdate(String data){
		StorePallet entity;
		try {
			entity = (StorePallet) jsonToObject(data,StorePallet.class);
			//如果是新插入的数据，需要请求RFID编号
			if(entity.getPalletRfid()!=null)
			{
				entity.setPalletRfid(service.getNewRfid());
			}
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
