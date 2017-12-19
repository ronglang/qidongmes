package com.css.business.web.subsysmanu.mauManage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauOEEPreinstall;
import com.css.business.web.subsysmanu.mauManage.service.MauOEEPreinstallManageService;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
* @Title: MauOEEPreinstallManageAction.java   
* @Package com.css.business.web.subsysmanu.mauManage.controller   
* @Description: OEE月份期望时间   
* @author   rb
* @date 2017年9月29日 下午4:22:41   
* @company  SMTC
 */
@Controller
@RequestMapping("/mauOEEPreinstallManageAction")
public class MauOEEPreinstallManageAction extends BaseSpringSupportAction<MauOEEPreinstall, MauOEEPreinstallManageService> {
	
	//@Autowired
	private MauOEEPreinstallManageService service;
	
	@Override
	public MauOEEPreinstallManageService getEntityManager() {
		return service;
	}

	public MauOEEPreinstallManageService getService() {
		return service;
	}

	@Resource(name="mauOEEPreinstallManageService")
	public void setService(MauOEEPreinstallManageService service) {
		this.service = service;
	}
	
	private Gson gson = new Gson();
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauOEEPreinstall/MauOEEPreinstallList";
	}
	
	/**
	 * 
	 * @Description:分页查询
	 * @param request
	 * @param page
	 * @param param
	 * @return
	 */
	@RequestMapping("getListPage")
	@ResponseBody
	public Page getListPage(HttpServletRequest request,Page page ,String param){
		Map<String,String>map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Page queryPage = page;
		try {
			queryPage = service.getListPage(page,map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryPage;
	}
	
	/**
	 * 
	 * @Description:保存 或者新修改
	 * @param request
	 * @param bean
	 * @return
	 */
	@RequestMapping("saveBean")
	@ResponseBody
	public HashMap<String, Object>saveOrUpdateBean(HttpServletRequest request,String bean){
		MauOEEPreinstall mauBean = gson.fromJson(bean, MauOEEPreinstall.class);
		try {
			if (mauBean.getId() == null ) {
				//新建
				String account = SessionUtils.getUser(request).getAccount();
				mauBean.setCreateBy(account);
				mauBean.setCreateDate(new Date());
				service.save(mauBean);
			}else {
				//修改
				//1.超过时间 不能更新
				//DateUtil.format(new Date(), "yyyy-MM");
				//String setMonth = mauBean.getSetMonth();
				//2.更新的时候，把月份统计表中的也修改
				//自定义方法去完成
				boolean flag = service.updateBeanAndMonth(mauBean);
				if (!flag) {
					return JsonWrapper.failureWrapperMsg("操作失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("操作失败");
		}
		return JsonWrapper.successWrapper(null, "操作成功");
	}
	
	
	/**
	 * 
	 * @Description:删除
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("clearBean")
	@ResponseBody
	public HashMap<String, Object>clearBean(HttpServletRequest request,Integer id){
		try {
			service.deleteBusiness(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("删除失败");
		}
		return JsonWrapper.successWrapper(null,"删除成功");
	}
	
	@RequestMapping("getAllSeqName")
	@ResponseBody
	public List<String> getAllSeqName(){
		return service.getAllSeqNameService();
	}
	
	@RequestMapping("getAllMacCode")
	@ResponseBody
	public List<String> getAllMacCode(){
		return service.getAllMacCodeService();
	}

}
