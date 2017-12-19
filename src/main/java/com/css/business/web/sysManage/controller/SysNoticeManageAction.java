package com.css.business.web.sysManage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.sysManage.bean.SysNotice;
import com.css.business.web.sysManage.service.SysNoticeManageService;
import com.css.commcon.util.SessionUtils;
import com.css.commcon.util.YorkUtil;
import com.css.common.util.EhCacheFactory;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.google.gson.Gson;
@Controller
@RequestMapping("/sysNoticeManageAction")
public class SysNoticeManageAction extends BaseSpringSupportAction<SysNotice, SysNoticeManageService> {
	
	//@Autowired
	private SysNoticeManageService service;
	private Gson gson = new Gson();
	private EhCacheFactory ecFactory = EhCacheFactory.getInstance();
	@Override
	public SysNoticeManageService getEntityManager() {
		return service;
	}

	public SysNoticeManageService getService() {
		return service;
	}

	@Resource(name="sysNoticeManageService")
	public void setService(SysNoticeManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysNoticeEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysNoticeForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysNoticeList";
	}
	
	@RequestMapping("saveOrUpdate")
	@ResponseBody
	public HashMap<String, Object>saveOrUpdate(HttpServletRequest request,String bean){
		//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		//Map<String,String>maq =  gson.fromJson(bean, new TypeToken<Map<String,String>>(){}.getType());
		SysNotice sysNotice = gson.fromJson(bean, SysNotice.class);
		String  isShow = sysNotice.getIsShow();
		String type = sysNotice.getType();
		String account = SessionUtils.getUser(request).getAccount();
		try {
			if (sysNotice.getId() != null ) {
				service.updateByCon(sysNotice, false);
			}else {
				sysNotice.setCreateBy(account);
				sysNotice.setCreateDate(new Date());
				service.save(sysNotice);
			}
			
			//车间电子看板显示
			if("是".equals(isShow)){
				if (YorkUtil.Memcache_车间致辞.equals(type) ) {
					List<SysNotice>list = service.getNoticesWebSocket("是",YorkUtil.Memcache_车间致辞);
					ecFactory.addWebsocketCmdCache(YorkUtil.Memcache_车间致辞, gson.toJson(list));
				}
			}
			//是否显示为否时，欢迎致辞不显示
			else{
				if (YorkUtil.Memcache_车间致辞.equals(type) ) {
					ecFactory.removeWebsocketCmdCache(YorkUtil.Memcache_车间致辞);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonWrapper.successWrapper(null, "操作失败");
		}
		return JsonWrapper.successWrapper(null, "操作成功");
	}
	
	@RequestMapping("clearRowData")
	@ResponseBody
	public HashMap<String,Object> clearRowData(String id){
		SysNotice sysNotice = new SysNotice();
		sysNotice.setId(Integer.valueOf(id));
		try {
			service.removeByCon(sysNotice);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return JsonWrapper.successWrapper(null, "操作失败");
		}
		return JsonWrapper.successWrapper(null, "操作成功");
	}
	
	@RequestMapping("getById")
	@ResponseBody
	public SysNotice getById(HttpServletRequest request,String id){
		SysNotice sysNotice = service.get(Integer.parseInt(id));
		return sysNotice;
	}
	
	@RequestMapping("showNoticeData")
	@ResponseBody
	public SysNotice showNoticeData(){
		return service.showNoticeDataService();
	}
	

}
