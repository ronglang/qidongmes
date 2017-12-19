package com.css.business.web.sysManage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.sysManage.bean.SysEmployee;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.business.web.sysManage.service.SysEmployeeManageService;
import com.css.commcon.util.SessionUtils;
import com.css.common.util.EhCacheFactory;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.bean.TreeVO;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
/**
 * @TODO  : 人员管理 
 * @author: 翟春磊
 * @DATE  : 2017年6月23日
 */
@Controller
@RequestMapping("/sysEmployeeManageAction")
public class SysEmployeeManageAction extends BaseSpringSupportAction<SysEmployee, SysEmployeeManageService> {
	
	//@Autowired
	private SysEmployeeManageService service;
	
	@Override
	public SysEmployeeManageService getEntityManager() {
		return service;
	}

	public SysEmployeeManageService getService() {
		return service;
	}

	@Resource(name="sysEmployeeManageService")
	public void setService(SysEmployeeManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysEmployeeEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysEmployeeForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysEmployeeList";
	}
	
	/**
	 * 相片上传
	 * @param request
	 * @param entity
	 * @param uploadurl
	 * @param uploadurlorigname
	 * @param aid
	 * @return
	 */
	@RequestMapping({ "saveFormAndAttach" })
	@ResponseBody
	public HashMap<String, Object> saveFormAndAttach(HttpServletRequest request, SysEmployee entity,String uploadurl,String uploadurlorigname,Integer aid,
				String token){
		try {
			this.log.debug("开始执行保存方法");
			SysUser user = SessionUtils.getUser(request);
			if(!StringUtil.isEmpty(token)){
				//token不为空为手机端上传
				user = EhCacheFactory.getInstance().getTokenKeeperCache(token);
			}
			
			entity.setCreateBy(user.getAccount());
			entity.setCreateDate(new Date());
			this.getEntityManager().saveFormAndAttach(request,user,entity,uploadurl,uploadurlorigname);
			this.log.debug("保存成功");
			return JsonWrapper.successWrapper(entity,"保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			String msg = e.getMessage();
			log.error(msg,e);
			return JsonWrapper.failureWrapperMsg(entity,msg);
		}
	}
	
	/**
	 * 查询照片url,用于编辑页面显示照片
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "attachmentBy" })
	@ResponseBody
	//@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	public HashMap<String, Object> attachmentBy(Integer id) throws Exception {
		List list = this.getEntityManager().queryAttachmentBy(id);
		return JsonWrapper.successWrapper(list);
	}
	
	/**
	 * 删除照片时，连带删除数据库中的url
	 * @param id
	 * @return
	 */
	@RequestMapping({ "deletePictureUrl" })
	@ResponseBody
	//@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	public HashMap<String, Object> deletePictureUrl(Integer id){
		this.getEntityManager().updatePictureUrl(id);
		return JsonWrapper.successWrapper(id);
	}
	

/**
	 * 生成树
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "nodeTree" })
	@ResponseBody
	public HashMap<String, Object> nodeTree(HttpServletRequest request,String qcolName,String qcolValue)  {
		List<TreeVO> list;
		try {
			list = this.getEntityManager().getTree(qcolName,qcolValue);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询树数据错误",e);
			return JsonWrapper.successWrapper("查询树数据错误");
		}
		
	}
	
	/**
	 * 生成树 本方法仅为新增用户服务，勿删
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "nodeTreeAddUser" })
	@ResponseBody
	public HashMap<String, Object> nodeTreeAddUser(HttpServletRequest request,
			String qcolName, String qcolValue) {
		List<TreeVO> list;
		try {
			list = this.getEntityManager().getTreeAddUser(qcolName, qcolValue);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询树数据错误", e);
			return JsonWrapper.successWrapper("查询树数据错误");
		}
	}
	
	/**
	 * 生成树
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "nodeTreeNoExit" })
	@ResponseBody
	public HashMap<String, Object> nodeTreeNoExit(HttpServletRequest request,
			String qcolName, String qcolValue, Integer id) {
		List<TreeVO> list;
		try {
			list = this.getEntityManager().getTreeNoExit(qcolName, qcolValue,
					id);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询树数据错误", e);
			return JsonWrapper.successWrapper("查询树数据错误");
		}
	}
	
	
}