package com.css.business.web.subsysquality.quaManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysquality.bean.QualityAsk;
import com.css.business.web.subsysquality.bean.QualityBasicParam;
import com.css.business.web.subsysquality.bean.QualityBasicValue;
import com.css.business.web.subsysquality.quaManage.service.QualityBasicParamManageService;
import com.css.business.web.subsysquality.quaManage.service.QualityBasicValueManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/qualityBasicValueManageAction")
public class QualityBasicValueManageAction
		extends
		BaseSpringSupportAction<QualityBasicValue, QualityBasicValueManageService> {

	// @Autowired
	private QualityBasicValueManageService service;
	@Resource(name="qualityBasicParamManageService")
	private QualityBasicParamManageService paramService;

	@Override
	public QualityBasicValueManageService getEntityManager() {
		return service;
	}

	public QualityBasicValueManageService getService() {
		return service;
	}

	@Resource(name = "qualityBasicValueManageService")
	public void setService(QualityBasicValueManageService service) {
		this.service = service;
	}

	private Gson gson = new Gson();

	/**
	 * 
	 * @Description: 去后天管理页面
	 * @return
	 */
	@RequestMapping("toPageList")
	public String toPageList() {
		return "";
	}

	/**
	 * 
	 * @Description: 去电子看板页面
	 * @return
	 */
	@RequestMapping("toDispalyPage")
	public String toDispalyPage() {
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
	public List<QualityAsk> getPageList(HttpServletRequest request, String param) {
		Map<String, String> map = gson.fromJson(param,new TypeToken<Map<String, String>>() {}.getType());
		return null;
	}

	/**
	 * 
	 * @Description: 保存bean
	 * @param request
	 * @param bean
	 * @return
	 */
	@RequestMapping("saveBean")
	@ResponseBody
	public HashMap<String, Object> saveBean(HttpServletRequest request,
			String bean) {

		// return JsonWrapper.successWrapper(pojo, msg);
		return null;
	}

	/**
	 * 
	 * @Description: 修改bean
	 * @param request
	 * @param bean
	 *            就是bean 里面包含 id 和需要修改项,其他项可以不要
	 * @return
	 */
	@RequestMapping("updateBean")
	@ResponseBody
	public HashMap<String, Object> updateBean(HttpServletRequest request,
			String bean) {

		return null;
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
	public HashMap<String, Object> getParamsByTypeName(HttpServletRequest request,String typeName,String reportCode){
		List list = null;
		try {
			list = service.getValueByTypeName(typeName,reportCode);
			if(list == null  || list.size() < 1){
				//新建,把id去点
				List<QualityBasicParam> relist = paramService.getParamsByTypeName(typeName);
				for (QualityBasicParam param : relist) {
					param.setId(null);
				}
				list.addAll(relist);
			}
			return JsonWrapper.successWrapper(list, "");
		} catch (Exception e) {
			// TODO: handle exception
			return JsonWrapper.failureWrapperMsg("查询失败");
		}
		
	}
}
