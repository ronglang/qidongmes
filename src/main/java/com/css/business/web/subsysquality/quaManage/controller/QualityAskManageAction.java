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
import com.css.business.web.subsysquality.quaManage.service.QualityAskManageService;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
* @Title: QualityAskManageAction.java   
* @Package com.css.business.web.subsysquality.quaManage.controller   
* @Description: 质检呼叫的controller   
* @author   rb
* @date 2017年8月31日 上午9:18:00   
* @company  SMTC
 */
@Controller
@RequestMapping("/qualityAskManageAction")
public class QualityAskManageAction
		extends
		BaseSpringSupportAction<QualityAsk, QualityAskManageService> {

	// @Autowired
	private QualityAskManageService service;

	@Override
	public QualityAskManageService getEntityManager() {
		return service;
	}

	public QualityAskManageService getService() {
		return service;
	}

	@Resource(name = "qualityAskManageService")
	public void setService(QualityAskManageService service) {
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
		return "quaManage/quaAsk/quaAskList";
	}
	
	/**
	 * 
	 * @Description: 去电子看板页面   
	 * @return
	 */
	/*
	@RequestMapping("toDisPlayPage")
	public String toDispalyPage(){
		return "quaManage/quaAsk/quaAskShowList";
	}
	*/
	@RequestMapping("toDisPlayPage")
	public String toDispalyPage(){
		return "quaManage/quaAsk/quaAskKanbanList";
	}
	@RequestMapping("toDisplayInfo")
	public String toDisplayInfo(){
		return "quaManage/quaAsk/quaAskKanbanInfo";
	}
	
	/**
	 * 
	 * @Description: 条件查询pageList   
	 * @param request
	 * @param param askState askType finishBy
	 * @return
	 */
	@RequestMapping("getPageList")
	@ResponseBody
	public Page getPageList(HttpServletRequest request,String param,Page page){
		Map<String ,String>map = gson.fromJson(param, new TypeToken<Map<String, String>>() {}.getType());
		Page pageList = service.getPageList(map,page);
		return pageList;
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
	public HashMap<String, Object> saveBean(HttpServletRequest request,String bean){
		SysUser user = SessionUtils.getUser(request);
		QualityAsk ask = gson.fromJson(bean, QualityAsk.class);
		try {
			ask.setCreateDate(new Date());
			ask.setCreateBy(user.getAccount());
			service.save(ask);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JsonWrapper.failureWrapperMsg("保存失败");
		}
		return JsonWrapper.successWrapper(ask, "保存成功");
		
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
		SysUser user = SessionUtils.getUser(request);
		QualityAsk ask = gson.fromJson(bean, QualityAsk.class);
		try {
			ask.setFinishDate(new Date());
			ask.setFinishBy(user.getAccount());
			service.updateByCon(ask, false);;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JsonWrapper.failureWrapperMsg("修改失败");
		}
		return JsonWrapper.successWrapper(ask, "修改成功");
	}
	
	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("finishAsk")
	@ResponseBody
	public HashMap<String, Object>finishAsk(HttpServletRequest request,String id){
		QualityAsk ask = service.get(Integer.parseInt(id));
		SysUser user = SessionUtils.getUser(request);
		if (ask != null) {
			ask.setFinishBy(user.getAccount());
			ask.setFinishDate(new Date());
			try {
				service.updateByCon(ask, false);
				return JsonWrapper.successWrapper(null, "处理成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return JsonWrapper.failureWrapperMsg("处理失败");
			}
		} else {
			return JsonWrapper.failureWrapperMsg("处理失败");
		}
	}
	
	/**
	 * 质检部电子看板显示数据信息 轮播图
	 * @return
	 */
	@RequestMapping("getDataKanban")
	@ResponseBody
	public List<QualityAsk> getDataList(){
		return service.getAskList(QualityAsk.STATE_NOT_HANDLE);
	}
	
	/**
	 * 
	 * @Description: 质检呼叫看板的grid   
	 * @return
	 */
	@RequestMapping("getAllTodayList")
	@ResponseBody
	public List<QualityAsk>getAllTodayList(){
		return service. getTodayAskList(QualityAsk.STATE_NOT_HANDLE);
	}
	
}
