package com.css.business.web.sysManage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.sysManage.bean.SysMenu;
import com.css.business.web.sysManage.bean.SysRole;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.business.web.sysManage.service.ResourceManageService;
import com.css.business.web.sysManage.service.SysUserManageService;
import com.css.commcon.util.SessionUtils;
import com.css.common.annotation.Auth;
import com.css.common.annotation.Remark;
import com.css.common.enums.ServiceErrorCode;
import com.css.common.util.EhCacheFactory;
import com.css.common.util.Md5Util;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.bean.TreeVO;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.CheckCodeService;
import com.css.common.web.syscommon.service.RedisService;

@Controller
@RequestMapping("/sysUserManageAction")
public class SysUserManageAction extends
		BaseSpringSupportAction<SysUser, SysUserManageService> {

	// @Autowired
	private SysUserManageService service;

	@Override
	public SysUserManageService getEntityManager() {
		return service;
	}

	public SysUserManageService getService() {
		return service;
	}

	@Resource(name = "sysUserManageService")
	public void setService(SysUserManageService service) {
		this.service = service;
	}

	@Autowired
	private CheckCodeService checkCodeService;

	@Resource(name = "redisServiceImpl")
	private RedisService redisService;

	@Resource(name = "resourceManageService")
	private ResourceManageService resService;
	

	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		if(id ==null){
			return "sysManage/sysUserEdit";
		}
		String pwd = this.getEntityManager().get(id).getPassword();
				
		if(pwd !=null){
			request.setAttribute("pwd",pwd);
		}
		
		return "sysManage/sysUserEdit";
	}

	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		if(id!=null){
			String roleList = service.findUserInfoById(id);
			request.setAttribute("roleList", roleList);
		}
		return "sysManage/sysUserForm";
	}

	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "sysManage/sysUserList";
	}
	
	/**
	 * 跳转到搜索页面
	*@param request
	*@param routeName
	*@return 
	*@author leitao 
	*@date 2016-3-19 下午5:48:57
	 */
	@RequestMapping({ "toSearchPage" })
	public String toSearchPage(HttpServletRequest request, String routeName) {
		request.setAttribute("routeName", routeName);
		return "sysManage/sysSearchPage";
	}

	
	/**
	 * 平台用户登录接口
	 * 
	 * @param request
	 * @param user
	 * @param checkcode
	 * @return
	 */
	@Remark(toDo = "登录系统")
	@RequestMapping({ "login" })
	@ResponseBody
	@Auth(verifyLogin = false, verifyURL = false, verifyToken = false)
	public HashMap<String, Object> login(HttpServletRequest request,
			HttpServletResponse response, SysUser user, String checkcode) {
		SysUser entity = null;
		HttpSession session = request.getSession(false);
		//String code = null;
		if (session != null) {
			//code = (String) session.getAttribute("checkCode");
			session.removeAttribute("checkCode");
		}
		HashMap<String, Object> map = null;
		try {

			// if(checkcode!=null && checkcode.length()>0 &&
			// checkcode.toUpperCase().equals(code)){
			if (StringUtil.isEmpty(user.getAccount())|| StringUtil.isEmpty(user.getPassword())) {
				map = JsonWrapper.failureWrapperMsg(ServiceErrorCode.ACCOUNT_OR_PASSWORD_NULL.getDesc());
				map.put("stateCode",ServiceErrorCode.ACCOUNT_OR_PASSWORD_NULL.getCode());
				map.put("sessionId", "");
				return map;
			}
			entity = this.getEntityManager().findUserByUsername(user.getAccount(), Md5Util.MD5(user.getPassword()));
			// 生成并保存用户token
			String token = Md5Util.MD5(user.getAccount()+ UUID.randomUUID().toString());
			// redisService.add(token,user.getAccount()+"|"+user.getPassword());
			map = JsonWrapper.successWrapper(entity);
			map.put("token", token);
			map.put("userInfo", entity);
			map.put("stateCode", "success");
			if (entity == null) {
				map = JsonWrapper.failureWrapperMsg(ServiceErrorCode.ACCOUNT_NOT_EXIST.getDesc());
				map.put("stateCode",ServiceErrorCode.ACCOUNT_NOT_EXIST.getCode());
				
				map.put("sessionId", "");
				return map;
			}
			if(entity.getChangePwDate()==null){
				//判断是不是第一次登录，第一次登录就强制修改密码
				map.put("firstLogin", true);
			}else{
				map.put("firstLogin", false);
			}
			SessionUtils.setUser(request, entity);
			// }else{
			// map =
			// JsonWrapper.failureWrapperMsg(ServiceErrorCode.CHECK_CODE_ERROR.getDesc());
			// map.put("stateCode",
			// ServiceErrorCode.CHECK_CODE_ERROR.getCode());
			// return map;
			// }
		} catch(CannotCreateTransactionException e){
			this.log.error(e.getMessage(), e);
			map = JsonWrapper.failureWrapperMsg("获取数据库连接错误");
			
			return map;
		} catch (Exception e) {
			this.log.error(e.getMessage(), e);
			//map = JsonWrapper.failureWrapperMsg(ServiceErrorCode.SYSTEM_ERROR.getDesc());
			map = JsonWrapper.failureWrapperMsg(e.getMessage());
			
			return map;
		}
		if (response.isCommitted()) {
			return null;
		}
		service.findMenu(request);
		return map;
	}
	
	
	@RequestMapping({ "loginAppUser" })
	@ResponseBody
	@Auth(verifyLogin = false, verifyURL = false)
	public HashMap<String, Object> loginAppUser(HttpServletRequest request,SysUser user) {
		SysUser entity = null;
		try {
			if (StringUtil.isEmpty(user.getAccount())|| StringUtil.isEmpty(user.getPassword())) {
				return JsonWrapper.failureWrapperMsg("用户登录失败，用户名或密码不能为空！");
			}
			entity = this.getEntityManager().findUserByUsernameMobile(user.getAccount(), user.getPassword());//密码app端加密
			if (entity == null) {
				return JsonWrapper.failureWrapperMsg("用户登录失败，用户名不存在！");
			}
			// 生成并保存用户token   UUID.randomUUID()十六位的随机数
			String token = Md5Util.MD5(user.getAccount()+ UUID.randomUUID().toString());
			entity.setToken(token);
			EhCacheFactory.getInstance().addTokenKeeperCache(entity);//将user信息保存在内存中
			HashMap<String, Object>  menu = service.findMenu(entity);
			ServletContext application = request.getSession().getServletContext();
			application.setAttribute("menu",menu.get("menu"));
			return JsonWrapper.successWrapper(entity);
		} catch (Exception e) {
			String msg = "用户登录失败！";
			if(e instanceof RuntimeException){
				msg = e.getMessage();
			}
			this.log.error(msg, e);
			return JsonWrapper.failureWrapperMsg(msg);
		}
	}

	/**
	 * app用户退出
	 * @param request
	 * @return
	 */
	@RequestMapping({ "logOutAppUser" })
	@ResponseBody
	public HashMap<String, Object> logOutAppUser(HttpServletRequest request,String token) {
//		SessionUtils.removeUser(request);
		EhCacheFactory.getInstance().removeTokenKeeperCache(token);
		return JsonWrapper.wrapper(true);
	}
	
	/**
	 * 获取当前登录用户
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping({ "logOutUser" })
	@ResponseBody
	public HashMap<String, Object> logOutUser(HttpServletRequest request) {
		SessionUtils.removeUser(request);
		request.getSession().invalidate();
		return JsonWrapper.wrapper(true);
	}

	/**
	 * 移动用户登录接口
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
//	@RequestMapping({ "loginMobile" })
//	@ResponseBody
//	@Auth(verifyLogin = false, verifyURL = false)
//	public HashMap<String, Object> loginMobile(HttpServletRequest request,
//			SysUser user) {
//		SysUser entity = null;
//		try {
//			if (StringUtil.isEmpty(user.getAccount())
//					|| StringUtil.isEmpty(user.getPassword())) {
//				return JsonWrapper.failureWrapperMsg("用户登录失败，用户名或密码不能为空！");
//			}
//			entity = this.getEntityManager().findUserByUsername(
//					user.getAccount(), user.getPassword());
//			if (entity == null) {
//				return JsonWrapper.failureWrapperMsg("用户登录失败，用户名不存在！");
//			}
//		} catch (Exception e) {
//			this.log.error(e.getMessage(), e);
//			return JsonWrapper.wrapper(false);
//		}
//		return JsonWrapper.successWrapper(entity);
//	}
	
	/**
	 * 业务操作主页
	 * String
	 * 2015年9月12日 下午1:45:01
	 * @param request
	 * @param response
	 * @return
	 */
	// @Auth(verifyLogin=true,verifyURL=false,verifyToken=false)
	@RequestMapping({ "main" })
	public String main(HttpServletRequest request, HttpServletResponse response) {
		return "main";
	}

	@RequestMapping({ "showMain" })
	public String showMain(HttpServletRequest request, HttpServletResponse response,Model model) {
		//SysUser user = SessionUtils.getUser(request);
		try {
			service.findMenu(request);
			/*boolean is = MenuUtil.checkMenu(request);
			if(is==false){
				service.findMenu(request);
			}*/
			/*HashMap<String, Object> menu = service.findMenuByUserAcconut(user);
			ServletContext application = request.getSession().getServletContext();
			application.setAttribute("menu",menu.get("menu"));*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";   //WG
		//return "showMain";
	}
	
	
	
	@RequestMapping({ "leader" })
	public String leader(HttpServletRequest request, HttpServletResponse response) {

		return "leader";
	}
	
	// @Auth(verifyLogin=true,verifyURL=false,verifyToken=false)
	@RequestMapping({ "home" })
	public String home(HttpServletRequest request, HttpServletResponse response) {

		return "home";
	}

	@RequestMapping({ "toUpdateUserPasswordPage" })
	public String updateUserPasswordPage() {
		return "updateUserPasswordPage";
	}


	/**
	 * 用户密码变更. 支持电脑端与手机端
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@Remark(toDo = "用户密码变更")
	@RequestMapping({ "updateUserPassword" })
	@ResponseBody
	public HashMap<String, Object> updateUserPassword(HttpServletRequest request,String password, String newpassword,String token) {
		try {
			SysUser sysUser=SessionUtils.getUser(request);
			if(!StringUtil.isEmpty(token)){
				//token不为空为手机端上传
				sysUser=EhCacheFactory.getInstance().getTokenKeeperCache(token);
			}
			if(sysUser == null) return JsonWrapper.failureWrapperMsg(null,"非登陆用户不能修改密码");
			
			String getpassword = sysUser.getPassword();//数据库中用户的密码
			String oldpassword = Md5Util.MD5(password);//页面用户输入的原密码
			
			if (oldpassword.equals(getpassword)) {//比较数据库中的密码和用户输入的原密码是否相等
				newpassword =Md5Util.MD5(newpassword); //对用户输入的新密码加密
				service.updateUserPassword(sysUser,  newpassword);
			} else {
				return JsonWrapper.failureWrapperMsg(null,"原密码不正确,请重新输入");
			}
			return JsonWrapper.failureWrapperMsg(null,"密码修改成功,请重新登录！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 生成校验码图片
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Auth(verifyLogin = false, verifyURL = false, verifyToken = false)
	@RequestMapping({ "getImage" })
	public void getImage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 禁止缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// 指定生成的响应是图片
		response.setContentType("image/jpeg");

		// String code = checkCodeService.generateRandomNumberCode();
		String code = checkCodeService.generateRandomMixedCode();
		// 将生成的验证码保存到Session中
		HttpSession session = request.getSession(true);
		session.setAttribute("checkCode", code);
		ImageIO.write(checkCodeService.getImage(code), "JPEG",
				response.getOutputStream());
	}



	/**
	 * test
	 * 
	 * @param request
	 * @param user
	 *            json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "getById" })
	@ResponseBody
	public HashMap<String, Object> detail(HttpServletRequest request,
			@RequestBody SysUser user) throws Exception {
		SysUser entity = this.getEntityManager().get(user.getId());
		return JsonWrapper.successWrapper(entity);
	}

	/**
	 * test
	 * 
	 * @param request
	 * @param user
	 *            json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "getByIdArray" })
	@ResponseBody
	public HashMap<String, Object> addArray(HttpServletRequest request,
			@RequestBody SysUser[] user) throws Exception {
		SysUser entity = this.getEntityManager().get(user[0].getId());
		return JsonWrapper.successWrapper(entity);
	}


	@RequestMapping({ "oneMap" })
	public String oneMap(HttpServletRequest request) {
		return "maphome";
	}

	@Remark(toDo = "新建、更新用户以及用户权限")
	// 新建、更新用户以及用户权限
	@RequestMapping({ "saveUserInfo" })
	@ResponseBody
	public HashMap<String, Object> saveUserInfo(HttpServletRequest request,
			SysUser entity, String roleIdList) throws Exception {
	
		try {
			Integer flag = entity.getId();
			SysUser user = SessionUtils.getUser(request);
	//		String userType = request.getParameter("userType");
			entity.setCreateDate(new Date());
			entity.setCreateBy(SessionUtils.getUser(request).getAccount());
			service.saveUserInfo(entity,user,roleIdList);
			if (flag != null) {
				return JsonWrapper.successWrapper("用户更新成功");
			}
				return JsonWrapper.successWrapper("用户创建成功");
		} catch (Exception e) {
			this.log.error(e.getMessage(), e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}

	}
	
		/**
		 *  保存用户更新，及更新该用户角色
		 * @param request
		 * @param entity
		 * @param roleIdList
		 * @return
		 */
		@RequestMapping({ "save" })
		@ResponseBody
		@Override
		public HashMap<String, Object> save(HttpServletRequest request,SysUser entity) {
			try {
				Integer userId = entity.getId();
				service.saveUserAndRole(entity);
				if(userId!=null){
					this.log.debug("更新成功");
					return JsonWrapper.successWrapper(entity,"用户更新成功");
				}else{
					this.log.debug("保存成功");
					return JsonWrapper.successWrapper(entity,"保存成功");
				}
			} catch (Exception e) {
				this.log.error(e.getMessage(), e);
				return JsonWrapper.failureWrapper(e.getMessage());
			}
		}
		
		/**
		 * 根据ID查询
		 * @param request
		 * @param id
		 * @return
		 * @throws Exception
		 */
		//@Remark(toDo="查询")
		@RequestMapping({ "detail" })
		@ResponseBody
		//@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
		public HashMap<String, Object> detail(HttpServletRequest request,@RequestParam("id") Integer id) throws Exception {
			SysUser entity = this.getEntityManager().get(id);
			//根据用户id查询用户角色详细
			List<SysRole> listRole = getEntityManager().queryRoleByUserId(id);
			List<String> roleId = new ArrayList<String>(); 
			String roleNames="";
			if(listRole!=null&&listRole.size()>0){
				for (int i = 0; i < listRole.size(); i++) {
					SysRole role =listRole.get(i);
					roleId.add(role.getId()+""); 
					roleNames += role.getName()+" ";
				}
			}
			entity.setRoleId(roleId);
			entity.setRoleIdShow(roleNames);
			HashMap<String, Object> map = JsonWrapper.successWrapper(entity);
			return map;
		}
	
	@Remark(toDo="重置密码")
	@RequestMapping({ "resetPassword" })
	@ResponseBody
	public HashMap<String, Object> resetPassword(String ids) throws Exception {
		try {
			if (StringUtil.isEmpty(ids)) {
				this.log.error("用户集合为空");
				return JsonWrapper.failureWrapperMsg("要重置密码的的目标对象ID为空！");
			}
			service.resetPassword(ids);
			return JsonWrapper.successWrapper(ids,"密码重置成功");
		} catch (Exception e) {
			this.log.error(e.getMessage(),e);
			new RuntimeException(e.getMessage(),e);
			return JsonWrapper.failureWrapperMsg("密码重置失败！");
		}
	}

	/**
	 * 生成树
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "nodeTree" })
	@ResponseBody
	public HashMap<String, Object> nodeTree(String qcolName,String qcolValue)  {
		List<TreeVO> list;
		try {
			list = this.getEntityManager().getTree(qcolName,qcolValue);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询树数据错误",e);
			return JsonWrapper.successWrapper("查询树数据错误");
		}
	}
	
	
	@RequestMapping({ "nodeTreeByCons" })
	@ResponseBody
	public HashMap<String, Object> nodeTreeByCons(HttpServletRequest request,String qcolName,String qcolValue)  {
		List<TreeVO> list;
		try {
			list = this.getEntityManager().getTreesByCons(SessionUtils.getUser(request),qcolName,qcolValue);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询树数据错误",e);
			return JsonWrapper.successWrapper("查询树数据错误");
		}
	}

	@RequestMapping("findMenuByPcode")
	@ResponseBody
	public HashMap<String, Object> findMenuByPcode(HttpServletRequest req , HttpServletResponse rep,String pcode){
		List<SysMenu> menu = new ArrayList<SysMenu>();
		try {
			 menu = service.findMenuByPcode(pcode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JsonWrapper.successWrapper(menu,"查询成功");
	}
	
	@RequestMapping("toType1")
	public String toType1(HttpServletRequest req , HttpServletResponse rep){
		return "type1";
	}
	

	@RequestMapping({ "logOutWebUser" })
	@ResponseBody
	public HashMap<String, Object> logOutWebUser(HttpServletRequest request,String token) {
		SessionUtils.removeUser(request);
		return JsonWrapper.successWrapper("退出系统");
	}
	
	
	/**
	 * @TODO: 带附件保存.志愿者注册.上传头像
	 * @author: zhaichunlei
	 & @DATE : 2016年4月21日
	 * @param request
	 * @param entity
	 * @param uploadurl
	 * @param uploadurlorigname
	 * @param aid
	 * @param token
	 * @return
	 */
	@RequestMapping({ "saveZyzFormAndAttachPhoto" })
	@ResponseBody
	public HashMap<String, Object> saveZyzFormAndAttachPhoto(HttpServletRequest request, SysUser entity,//,HZyz zyz,
			String uploadurl,String uploadurlorigname,String token,String hzphoto){
		try {
			SysUser sysUser=SessionUtils.getUser(request);
			if(!StringUtil.isEmpty(token)){
				//token不为空为手机端上传
				sysUser=EhCacheFactory.getInstance().getTokenKeeperCache(token);
			}
			if(sysUser == null) sysUser = new SysUser();
			sysUser.setToken(token);
			
			Integer id = entity.getId();
			//this.getEntityManager().saveZyzFormAndAttachPhoto(request,sysUser,entity,zyz,uploadurl,uploadurlorigname,hzphoto);
			
			if(id == null){
				this.log.debug("志愿者用户信息保存成功");
				return JsonWrapper.successWrapper(entity,"志愿者用户信息保存成功！");
			}
			else{
				this.log.debug("志愿者用户编辑保存成功");
				return JsonWrapper.successWrapper(entity,"志愿者用户编辑保存成功！");
			}
		} catch (Exception e) {
			log.error("系统异常，请联系管理员！",e);
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(entity,e.getMessage());
		}
	}
	
	/**
	 * @TODO: 用户头像及信息修改
	 * @author: zhaichunlei
	 & @DATE : 2016年4月21日
	 * @param request
	 * @param entity
	 * @param uploadurl
	 * @param uploadurlorigname
	 * @param aid
	 * @param token
	 * @return
	 */
	@RequestMapping({ "saveUserAndAttach" })
	@ResponseBody
	public HashMap<String, Object> saveUserAndAttach(HttpServletRequest request, SysUser entity,
			String uploadurl,String uploadurlorigname,String token,String hzphoto){
		try {
			SysUser sysUser=SessionUtils.getUser(request);
			if(!StringUtil.isEmpty(token)){
				//token不为空为手机端上传
				sysUser=EhCacheFactory.getInstance().getTokenKeeperCache(token);
			}
			if(sysUser == null) sysUser = new SysUser(); //throw new RuntimeException("登陆用户才能修改信息。请登陆");
			sysUser.setToken(token);
			
			Integer id = entity.getId();
			this.getEntityManager().saveUserAndAttach(request,sysUser,entity,uploadurl,uploadurlorigname,hzphoto);
			
			if(id == null){
				this.log.debug("用户信息保存成功");
				return JsonWrapper.successWrapper(entity,"用户信息保存成功！");
			}
			else{
				this.log.debug("用户信息保存成功");
				return JsonWrapper.successWrapper(entity,"用户信息保存成功！");
			}
		} catch (Exception e) {
			log.error("系统异常，请联系管理员！",e);
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(entity,e.getMessage());
		}
	}
	/**
	 * 删除
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping({ "delete2" })
	@ResponseBody
	public HashMap<String, Object> delete2(HttpServletRequest request, String ids) throws Exception{
		try {
			SysUser user = SessionUtils.getUser(request);
			service.delete2(user,ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//throw new Exception(e.getMessage());
			//return JsonWrapper.failureWrapper(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		return JsonWrapper.successWrapper("删除成功！");
	}		
  /**
   * 
   * 更新用户状态
   * @param id
   * @param status
   * @return
   */
	@RequestMapping({ "updateUserStatus" })
	@ResponseBody
	public HashMap<String, Object> updateUserStatusByID(Integer id,String status) {
		try {
			if("C10020001".equals(status)){
				status = "C10020002";
			}else{
				status = "C10020001";
			}
			getEntityManager().updateUserStatusByID(id, status);
			return JsonWrapper.successWrapper(id,"更新用户状态成功！");
		} catch (Exception e) {
			this.log.error("更新用户状态失败！", e);
			return JsonWrapper.failureWrapperMsg("更新用户状态失败！");
		}
	}
	
	/**
	 * 决策分析
	 */
	@RequestMapping({ "toListShowPage" })
	public String toStatisticsExecutePage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "listShow";
	}
	
	
	/**
	   * 请求决策分析数据
	   * @param id
	   * @param status
	   */
		@RequestMapping({ "getReportData" })
		@ResponseBody
		public HashMap<String, Object> getReportData(HttpServletRequest request, 
				HttpServletResponse response,String areaCode){	
			String url="http://124.161.245.148:8099/bll/search.ashx?areacode="+areaCode+"&type=report";
			String param="";
			return JsonWrapper.successWrapper(service.sendPost(url,param));			
	}
		/**
		   * 请求登陆统计数据
		   * @param id
		   * @param status
		   */
			@RequestMapping({ "getLandData" })
			@ResponseBody
			public HashMap<String, Object> getLandData(HttpServletRequest request, 
					HttpServletResponse response,String account){	
				return JsonWrapper.successWrapper(service.getLandData(account));			
		}	
			
		@Override
		//@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
		public Page dataGridPage(HttpServletRequest request,Page paramPage,SysUser entity) throws Exception {
			String account = entity.getAccount();
			if(account == null || account.length() == 0 )
				entity.setAccount(null);
			paramPage = super.dataGridPage(request, paramPage, entity);
			return paramPage;
		}		
	
}

