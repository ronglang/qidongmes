package com.css.business.web.subsysquality.quaManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysquality.bean.QualityBasicParam;
import com.css.business.web.subsysquality.quaManage.service.QualityBasicParamManageService;
import com.css.business.web.subsysquality.quaManage.service.QualityBasicValueManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
* @Title: QualityBasicParamManageAction.java   
* @Package com.css.business.web.subsysquality.quaManage.controller   
* @Description: 基础数据的action  
* @author   rb
* @date 2017年8月31日 上午9:26:58   
* @company  SMTC
 */
@Controller
@RequestMapping("/qualityBasicParamManageAction")
public class QualityBasicParamManageAction
		extends
		BaseSpringSupportAction<QualityBasicParam, QualityBasicParamManageService> {

	// @Autowired
	private QualityBasicParamManageService service;
	
	@Resource(name="qualityBasicValueManageService")
	private QualityBasicValueManageService valueService;

	@Override
	public QualityBasicParamManageService getEntityManager() {
		return service;
	}

	public QualityBasicParamManageService getService() {
		return service;
	}

	@Resource(name = "qualityBasicParamManageService")
	public void setService(QualityBasicParamManageService service) {
		this.service = service;
	}

	private Gson gson = new Gson();
	
	/**
	 * 
	 * @Description: 去后天管理页面   
	 * @return
	 */
	@RequestMapping("toPageList")
	public String toPageList(){
		return "";
	}
	
	
	/**
	 * 
	 * @Description: 条件查询pageList   
	 * @param request
	 * @param param
	 * @return
	 */
	@RequestMapping("getPageList")
	@ResponseBody
	public Page getPageList(HttpServletRequest request,String param,Page page){
		Map<String ,String>map = gson.fromJson(param, new TypeToken<Map<String, String>>() {}.getType());
		Page queryPage = page; 
		try {
			queryPage = service.getPageList(map,page);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return queryPage;
	}
	
	/**
	 * 
	 * @Description: 保存bean  是一个list
	 * @param request
	 * @param bean
	 * @return
	 */
	@RequestMapping("saveBeans")
	@ResponseBody
	public HashMap<String, Object> saveBeans(HttpServletRequest request,String bean,String basicTypeId,String basicTypeName){
		List<QualityBasicParam> beansList = gson.fromJson(bean, new TypeToken<List<QualityBasicParam>>(){}.getType());
		Map<String ,Integer> map= service.saveBeans(beansList,Integer.parseInt(basicTypeId),basicTypeName);
		String msg = "本次操作,成功"+map.get("successCount")+"个,失败"+map.get("falseCount")+"个";
		return JsonWrapper.successWrapper(null, msg);
	}
	
	/**
	 * 
	 * @Description: 修改bean   
	 * @param request
	 * @param bean 就是bean 里面包含 id 和需要修改项,其他项可以不要
	 * @return
	 */
	@RequestMapping("updateBean")
	@ResponseBody
	public HashMap<String, Object> updateBean(HttpServletRequest request,String bean){
		List<QualityBasicParam> beansList = gson.fromJson(bean, new TypeToken<List<QualityBasicParam>>(){}.getType());
		Map<String ,Integer> map = service.updeteBeans(beansList);
		String msg = "本次操作,成功"+map.get("successCount")+"个,失败"+map.get("falseCount")+"个";
		return JsonWrapper.successWrapper(null, msg);
	}
	
	/**
	 * 
	 * @Description: 删除基础参数   
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("clearBean")
	@ResponseBody
	public HashMap<String,Object> clearBean(HttpServletRequest request,String id){
		try {
			service.deleteBusiness(Integer.parseInt(id));
			return JsonWrapper.successWrapper(null, "删除成功");
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("删除失败");
		}
	}
	
	/**
	 * 
	 * @Description: 单个添加修改方法   
	 * @param request
	 * @param bean
	 * @return
	 */
	@RequestMapping("saveBean")
	@ResponseBody
	public HashMap<String, Object>saveBean(HttpServletRequest request,String bean){
		QualityBasicParam param = gson.fromJson(bean, QualityBasicParam.class);
		try {
			if (param.getId() != null) {
				//更新
				service.updateByCon(param, false);
				//查重
				//service.checkParam(param.getBasicTypeId(),param.getParamName(),true);
			} else {
				service.save(param);
			}
			return JsonWrapper.successWrapper(null, "操作成功");
		} catch (Exception e) {
			// TODO: handle exception
			return JsonWrapper.failureWrapperMsg("操作失败");
		}
	}
	
	/**
	 * 
	 * @Description: 通过typeName查询所有的参数   :先查询 basic_value 如果有值,就返回去,如果没有值 就查询basic_name 当新建
	 * @param request
	 * @param typeName
	 * @return
	 */
	@RequestMapping("getParamsByTypeName")
	@ResponseBody
	public HashMap<String, Object> getParamsByTypeName(HttpServletRequest request,String typeName){
		List resultList = null;
		try {
				//如果没查到,查询basic_param 当新建
			resultList  = service.getParamsByTypeName(typeName);
			resultList  = service.getParamsByTypeName(typeName);
			QualityBasicParam e = new QualityBasicParam();
			e.setParamName("11");
			e.setReferValue(">2");
			resultList.add(e );
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("参数查询出错");
		}
		return JsonWrapper.successWrapper(resultList);
	}
	
	/**
	 * 
	 * @Description: 查重   
	 * @param request
	 * @param basicTypeId
	 * @param paramName
	 * @return
	 */
	@RequestMapping("checkName")
	@ResponseBody
	public HashMap<String, Object>checkName(HttpServletRequest request,String basicTypeId,String paramName){
		Long count = service.checkName(basicTypeId,paramName);
		if (count != null && count > 0) {
			return JsonWrapper.failureWrapperMsg("已有该名称");
		}
		return JsonWrapper.successWrapper(null,"放心使用");
	}
	
}
