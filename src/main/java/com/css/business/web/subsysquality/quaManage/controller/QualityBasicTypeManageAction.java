package com.css.business.web.subsysquality.quaManage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysquality.bean.QualityAsk;
import com.css.business.web.subsysquality.bean.QualityBasicType;
import com.css.business.web.subsysquality.quaManage.service.QualityBasicParamManageService;
import com.css.business.web.subsysquality.quaManage.service.QualityBasicTypeManageService;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@Controller
@RequestMapping("/qualityBasicTypeManageAction")
public class QualityBasicTypeManageAction
		extends
		BaseSpringSupportAction<QualityBasicType, QualityBasicTypeManageService> {

	// @Autowired
	private QualityBasicTypeManageService service;
	
	@Resource(name="qualityBasicParamManageService")
	private QualityBasicParamManageService basicParamService;

	@Override
	public QualityBasicTypeManageService getEntityManager() {
		return service;
	}

	public QualityBasicTypeManageService getService() {
		return service;
	}

	@Resource(name = "qualityBasicTypeManageService")
	public void setService(QualityBasicTypeManageService service) {
		this.service = service;
	}

	private Gson gson = new Gson();
	/**
	 * 
	 * @Description: 去修改页面   
	 * @return
	 */
	@RequestMapping("toUpdate")
	public String toUpdate(HttpServletRequest request,Integer id){
		if (id !=null) {
			QualityBasicType type = service.get(id);
			request.setAttribute("type", type);
		}
		return "quaManage/basicType/basicTypeUpdate";
	}
	
	/**
	 * 
	 * @Description: 列表页面   
	 * @return
	 */
	@RequestMapping("toPageList")
	public String toPageList(){
		return "quaManage/basicType/basicTypeList";
	}
	
	/**
	 * 
	 * @Description: 去质检类行和关联列表   
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("toTypeParam")
	public String toTypeParam(HttpServletRequest request,Integer id){
		if (id !=null) {
			QualityBasicType type = service.get(id);
			request.setAttribute("id", id);
			request.setAttribute("typeName", type.getTypeName());
		}
		return "quaManage/basicType/basicTypeParamAdd";
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
		queryPage = service.getPageList(map,page);
		return queryPage;
	}
	
	/**
	 * 
	 * @Description:   新建修该都用这一个方法
	 * @param request
	 * @param bean
	 * @param paramBean
	 * @return
	 */
	@RequestMapping("saveBean")
	@ResponseBody
	public HashMap<String, Object> saveBean(HttpServletRequest request,String bean){
		SysUser user = SessionUtils.getUser(request);
		QualityBasicType basicType = gson.fromJson(bean, QualityBasicType.class);
		try {
			if (basicType.getId() != null ) {
				//修改
				service.updateByCon(basicType, false);
				//把基础参数里面的名称也一起修改了
				basicParamService.updateName(basicType);
				return JsonWrapper.successWrapper(basicType, "修改成功");
			} else {
				//新建
				basicType.setCreateBy(user.getAccount());
				basicType.setCreateDate(new Date());
				service.save(basicType);
				return JsonWrapper.successWrapper(basicType, "保存成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(basicType, "操作失败");
		}
		
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
		QualityBasicType basicType = gson.fromJson(bean, QualityBasicType.class);
		if (basicType.getId() != null) {
			
		}
		return null;
	}
	
	/**
	 * 
	 * @Description: 查重    
	 * @param request
	 * @param param type typeName ,testAccording
	 * @return
	 */
	@RequestMapping("checkTypeByName")
	@ResponseBody
	public HashMap<String, Object>checkTypeByName(HttpServletRequest request,String type ,String typeName){
		boolean checkType = service.checkType(type,typeName);
		if (checkType) {
			return JsonWrapper.successWrapper(null, "可以使用");
		} else {
			return JsonWrapper.failureWrapperMsg("该名称已有,请重新录入");
		}
	}
	
	@RequestMapping("getById")
	@ResponseBody
	public HashMap<String, Object> getById(HttpServletRequest request,Integer id) {
		QualityBasicType type = service.get(id);
		return JsonWrapper.successWrapper(type);
	}
	
	/**
	 * 
	 * @Description: 通过检测类型,获得所属的检测类型名称   
	 * @param request
	 * @param type
	 * @return
	 */
	@RequestMapping("getTypeNames")
	@ResponseBody
	public Map[] getTypeNames(HttpServletRequest request,String type){
		Map[] arrMap = service.getTypeNames(type);
		return arrMap;
	}
	
	@RequestMapping("getAccording")
	@ResponseBody
	public Map[] getAccording(HttpServletRequest request,String typeName){
		Map[] arrMap = service.getAccording(typeName);
		return arrMap;
	}

	/**
	 * 
	 * @Description: 通过id删除   
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("clearBean")
	@ResponseBody
	public HashMap<String, Object>clearBean(HttpServletRequest request,String id){
		try {
			service.clearBean(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return	JsonWrapper.failureWrapperMsg("删除失败");
		}
		return JsonWrapper.successWrapper(null,"删除成功");
	}
}
