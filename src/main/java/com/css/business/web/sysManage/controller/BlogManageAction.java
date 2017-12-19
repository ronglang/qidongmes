package com.css.business.web.sysManage.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.sysManage.bean.Blog;
import com.css.business.web.sysManage.service.BlogManageService;
import com.css.common.annotation.Auth;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.bean.Sys_attachment;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
/**
 * 
 *TODO 博客管理ACTION
 * @author huangaho
 *2015-4-24上午11:34:47
 */
@Controller
@RequestMapping("/blogManageAction")
public class BlogManageAction extends BaseSpringSupportAction<Blog, BlogManageService> {
	
	//@Autowired
	private BlogManageService service;
	
	@Override
	public BlogManageService getEntityManager() {
		return service;
	}

	public BlogManageService getService() {
		return service;
	}

	@Resource(name="blogManageService")
	public void setService(BlogManageService service) {
		this.service = service;
	}

	@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	@RequestMapping({ "saveFormAndAttach" })
	@ResponseBody
	public HashMap<String, Object> saveFormAndAttach(HttpServletRequest request, Blog entity,String uploadurl,String uploadurlorigname,Integer aid)
			 {
		try {
			this.log.debug("开始执行保存方法");
			if(!StringUtil.isEmpty(uploadurl)  && !StringUtil.isEmpty(uploadurlorigname)){
				String [] uploadurlarr = uploadurl.split(",");
				String [] uploadurlorignamearr=uploadurlorigname.split(",");
					for(int i=0;i<uploadurlarr.length;i++){
						Sys_attachment att = new Sys_attachment(uploadurlorignamearr[i],uploadurlarr[i]);
						this.getEntityManager().saveBlogAndAttach(entity,att);
					}
				
			}else{
				return JsonWrapper.failureWrapperMsg("参数错码！");
			}
			this.log.debug("保存成功");
			return JsonWrapper.successWrapper(entity);
		} catch (Exception e) {
			log.error("系统异常，请联系管理员！",e);
			return JsonWrapper.failureWrapperMsg("系统异常，请联系管理员！");
		}
	}
	
	/**
	 * 博客添加编辑页面
	 * @param request
	 * @param id
	 * @return
	 */
	@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	@RequestMapping({ "toAddEditBolgPage" })
	public String toSaveEditBolgPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "userManage/blogEdit";
	}
	
	/**
	 * 博客详情页面
	 * @param request
	 * @param id
	 * @return
	 */
	@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	@RequestMapping({ "toBlogDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "userManage/blogForm";
	}
	
	/**
	 * 博客管理首页
	 * @param request
	 * @param id
	 * @return
	 */
	@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "userManage/blogList";
	}
}
