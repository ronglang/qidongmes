package com.css.business.web.sysManage.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.sysManage.bean.MenuTree;
import com.css.business.web.sysManage.bean.ShouYeDataVO;
import com.css.business.web.sysManage.bean.ShouYeLandVO;
import com.css.business.web.sysManage.bean.SysArea;
import com.css.business.web.sysManage.bean.SysMenu;
import com.css.business.web.sysManage.bean.SysRole;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.business.web.sysManage.bean.SysUserRole;
import com.css.business.web.sysManage.dao.SysAreaManageDAO;
import com.css.business.web.sysManage.dao.SysAttachmentManageDAO;
import com.css.business.web.sysManage.dao.SysUserManageDAO;
import com.css.business.web.sysManage.dao.SysUserRoleManageDAO;
import com.css.commcon.util.SessionUtils;
import com.css.common.enums.ServiceErrorCode;
import com.css.common.util.Md5Util;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.bean.Sys_attachment;
import com.css.common.web.syscommon.bean.TreeVO;
import com.css.common.web.syscommon.controller.FileUploadAction;
import com.css.common.web.syscommon.controller.support.QueryCondition;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

/**
 * @TODO  : 用户管理 
 * @author: 翟春磊
 * @DATE  : 2017年6月23日
 */
@Service("sysUserManageService")
public class SysUserManageService extends BaseEntityManageImpl<SysUser, SysUserManageDAO> {
	@Resource(name="sysUserManageDAO")
	//@Autowired
	private SysUserManageDAO dao;

	@Resource(name = "sysAttachmentManageDAO")
	private SysAttachmentManageDAO adao;
	
	@Resource(name = "sysUserRoleManageDAO")
	// @Autowired
	private SysUserRoleManageDAO roleDao;
	
	@Resource(name = "sysUserRoleManageService")
	private SysUserRoleManageService sysUserRoleManageService;
	
	@Resource(name = "resourceManageService")
	private ResourceManageService resService;
	
	@Resource(name = "sysRoleManageService")
	private SysRoleManageService roleService;
	
	@Resource(name="sysAreaManageDAO")
	private SysAreaManageDAO sysAreaManageDAO;
	
	@Override
	public SysUserManageDAO getEntityDaoInf() {
		return dao;
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public SysUser findUserByUsername(String username,String password) throws Exception{
		try {
			QueryCondition qc  = new QueryCondition(SysUser.class);
			qc.addCondition("account", "=", username);
			//qc.addCondition("password", "=", password);
			List<SysUser> users = new ArrayList<SysUser>();
			try {
				users = this.query(qc);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("查询错误，获取数据库连接失败："+e.getMessage());
			}
			
			if(users.size() > 1){
				throw new RuntimeException(ServiceErrorCode.ACCOUNT_NOT_UNIQUE.getDesc());
			}else if(users != null && users.size() > 0){
				SysUser user = users.get(0);
				if(ServiceErrorCode.ACCOUNT_STATUS_DISABLE.getCode().equals(user.getStatus())){
					throw new RuntimeException("帐号"+user.getAccount()+"状态："+ServiceErrorCode.ACCOUNT_STATUS_DISABLE.getDesc());
				}
				if(password.equals(user.getPassword())){
					// 加载用户菜单资源
					HashMap<String,Object> map = resService.querySysMenuBySysUser(user);
					user.setUserMenuTree((List<MenuTree>)map.get(ServiceErrorCode.USER_MENU.getCode()));
					//user.setUserAllResource((List<SysMenu>)map.get(ServiceErrorCode.USER_RESOURCE.getCode()));
					//user.setSysAllResource((List<SysMenu>)map.get(ServiceErrorCode.SYSTEM_REOURCE_LIST.getCode()));
					//user.setVwuser(edao.queryVwUserEmployee(user.getAccount()));
					List<SysUserRole> list = sysUserRoleManageService.findEntityByUserId(user.getId());
					for(SysUserRole s:list){
						 SysRole roleEntity = roleService.findEntityByRoleId(s.getRoleId());
						 if(user.getRoles() == null){
							 user.setRoles(new ArrayList<String>());
						 }
						 String roleName = roleEntity.getName();
						 if(!StringUtil.isEmpty(roleName)){
							 user.getRoles().add(roleName);
							 /*if(roleName.contains("leader")){
								 user.setLeader(true);
							 }*/
						 }
						 
					}
					
					//取用户的行政区划数据权限
					//Integer dataYear = Integer.parseInt(Constant.CONFIG.get("DATA_YEAR").getValue());
					//List<VSysuserAccountAll> lst = vSysuserAccountAllManageDAO.createQuery("select t from VSysuserAccountAll t where dataYear=? and account=?", dataYear,username).list();
					//user.setAreaLst(lst);s sf
					return user;
				}else{
					throw new RuntimeException(ServiceErrorCode.PASSWORD_ERROR.getDesc());
				}
			}else{
				throw new RuntimeException(ServiceErrorCode.ACCOUNT_NOT_EXIST.getDesc());
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			if(e instanceof RuntimeException){
				throw new RuntimeException(e.getMessage());
			}else{
				throw new RuntimeException(ServiceErrorCode.SYSTEM_ERROR.getDesc(),e);
			}
		}
		
	}
	/*
	 * 新建账号
	 */
	@Transactional(readOnly = false)
	public void saveUserInfo(SysUser entity,SysUser user,String roleIdList) throws Exception {
		try {
			/*if(entity.getEmployeeId() == null ){
				throw new RuntimeException("添加或修改帐号时，请选择人员！");
			}
			
			SysEmployee emp = edao.get(entity.getEmployeeId());
			
			if(emp == null){
				throw new RuntimeException("更新帐户错误，原因：帐号："+entity.getAccount()+"关联的人员已删除！");
			}
			
			entity.setName(emp.getName());
			entity.setOrgCode(emp.getOrgCode());
			entity.setTel(emp.getMobileTel());
			*/
			Integer id = entity.getId();
			
			if(id == null){
				QueryCondition qc = new QueryCondition(SysUser.class);
				qc.addCondition("account", "=", entity.getAccount());
				List<SysUser>  list = this.query(qc);
				if(list.size() > 0 ){
					throw new RuntimeException("帐号："+entity.getAccount()+"已存在！");
				}
			}
			else{
				QueryCondition qc = new QueryCondition(SysUser.class);
				qc.addCondition("account", "=", entity.getAccount());
				qc.addCondition("id","<>",id);
				List<SysUser>  list = this.query(qc);
				if(list.size() > 0 ){
					throw new RuntimeException("帐号："+entity.getAccount()+"已存在！");
				}
			}
//				
//				QueryCondition qc2 = new QueryCondition(HPerson.class);
//				//qc.addCondition("employeeId", "=", entity.getEmployeeId());
//				List<SysUser>  list2 = this.query(qc2);
//				if(list.size() > 0 ){
//					//throw new RuntimeException("人员："+emp.getName()+"已建了帐号：！"+list2.get(0).getAccount());
//					throw new RuntimeException("人员："+entity.getName()+"已建了帐号：！"+list2.get(0).getAccount());
//				}
			entity.setCreateDate(new Date());
			entity.setCreateBy(user.getAccount());
			if(id == null){
				entity.setStatus("未审核");
				entity.setPassword(Md5Util.MD5("666666"));
			}
			if(id == null)
				dao.save(entity);
			else 
				dao.updateByCon(entity, false);
			
			
			if(id != null)
				roleDao.deleteBySql("delete from sys_user_role where user_Id=?", id);
			/*
			 * 关联生成用户-角色信息
			 * 
			 */
			if (!StringUtil.isEmpty(roleIdList)) {
				String[] args = roleIdList.split(",");// 截取字符串，获取角色的列表数据
				for (int x = 0; x < args.length; x++) {
					SysUserRole sysUserRole = new SysUserRole();
					sysUserRole.setRoleId(Integer.parseInt(args[x]));
					sysUserRole.setUserId(entity.getId());
					roleDao.save(sysUserRole);// 保存用户角色到用户-角色表
				}
			}
			
			
		//	deletUserRoleById(entity.getId());// 根据角色ID删除角色权限表的数据
			
		} catch (Exception e) {
			String msg = "保存用户信息失败！";
			if(e instanceof RuntimeException){
				msg = e.getMessage();
			}
			this.log.error(msg, e);
			throw new RuntimeException(msg,e);
		}
		
	}
	/*
	 * 更新账号状态
	 */
	@Transactional(readOnly = false)
	public void updateUserStatusByID(Integer userId,String status) {
		this.dao.updateUserStatusByID(userId, status);
	}
	/**
	 * 保存user 和  该用户的角色
	 * 1，删除之前的用户角色对应关系，
	 * 2，添加新的用户-角色关系对应
	 * 3，保存用户 保存对应关系
	 * @param user
	 */
	public void saveUserAndRole(SysUser user){
		dao.save(user);
		Integer userId = user.getId();//用户id
		//1，删除之前的用户角色对应关系，
		dao.deleteBySql("DELETE FROM sys_user_role WHERE user_id="+userId);
		//保存新的用户角色对应关系
		List<String> list= user.getRoleId();
		if(list != null){
		for (int i = 0; i < list.size(); i++) {
			dao.save(new SysUserRole(userId,Integer.parseInt(list.get(i))));
		}
		  }
	}
	/**
	 * 更加角色表id,查询角色表详细信息
	 * @param roleID
	 * @returncom.css.sysManage.bean.SysUser
	 */
	@SuppressWarnings("unchecked")
	public List<SysRole> queryRoleByUserId(Integer userId){
		String sql = "SELECT role_t.* FROM sys_role role_t " +
				"INNER JOIN sys_user_role userrole_t on role_t.id=userrole_t.role_id " +
				"INNER JOIN sys_user user_t ON user_t.id=userrole_t.user_id where user_t.id="+userId;
		return dao.createSQLQuery(sql, SysRole.class).list();
	}
	
	@Transactional(readOnly = false)
	public void deletUserRoleById(Integer params) {
		List<SysUserRole> list = sysUserRoleManageService.QueryLists(params);
		for(SysUserRole sysUserRole:list){
			sysUserRoleManageService.deletUserRoleById(sysUserRole.getId());
		}
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteBusiness(Serializable id) throws Exception {
		Integer ids = 0;
		if(id instanceof String){
			ids = Integer.parseInt(id.toString());
		}else{
			ids = (Integer)id;
		}
		SysUser user = this.get(ids);
		if(user != null && user.isAdmin()){
			throw new RuntimeException(ServiceErrorCode.ADMIN_ACCOUNT_NO_DELETE.getDesc());
		}
		super.deleteBusiness(id);
	}

	public void updateUserPassword(SysUser user,String newpassword) {
		user.setChangePwDate(new Date());//每个用户登录都要检查是否为第一次登录，否则修改密码
		user.setPassword(newpassword);
		dao.save(user);
	}

	@Transactional(readOnly = false)
	public void resetPassword(String ids) {
		String[] idList =ids.split(",");
		for(String list:idList){
			dao.resetPasswordById(Integer.parseInt(list));
		}
	}

	//获取用户角色列表
	@Transactional(readOnly = true)
	public String findUserInfoById(Integer id) {
		String string = "";
		List<SysUserRole> list = sysUserRoleManageService.findEntityByUserId(id);
		for(SysUserRole s:list){
			 SysRole roleEntity = roleService.findEntityByRoleId(s.getRoleId());
			 string = string+roleEntity.getName()+"\n";
		}
		return string;
	}


	/**
	 * 查询树
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<TreeVO> getTree(String colName,String colValue)throws Exception{
		QueryCondition qc = new QueryCondition(SysUser.class);
		if(!StringUtil.isEmpty(colName)){
			qc.addCondition(colName, "like",colValue+"%");
		}
		//List <SysUser> list = this.query(qc);
		List<TreeVO> tree = new ArrayList<TreeVO>();
		/*List<Vw_UserEmployee> vwlist=this.dao.getUserEmployee();
		for(SysUser s : list){
			String pid = s.getAccount()+"";
			String orgName="";
			for(Vw_UserEmployee ee:vwlist){
				if(s.getAccount().equals(ee.getAccount()))orgName=ee.getOrgName();
			}
			TreeVO vo = new TreeVO(pid,s.getName()+"（"+orgName+"）","-1",s.getEmployeeId()+"");
			tree.add(vo);
		}*/
		return tree;
	}
	
	@Transactional(readOnly = true)
	public List<TreeVO> getTreesByCons(SysUser user,String code,String dvalue)throws Exception{
		String areaCodes[]=dvalue.split(",");
		StringBuilder sql= new StringBuilder("SELECT * FROM sys_user WHERE 1=1 ");
			for(int i=0;i<areaCodes.length;i++){
				if(sql.toString().contains("and")){
					sql.append(" or area_code like '").append(areaCodes[i]).append("%' " );
				}else{
					sql.append(" and  area_code like '").append(areaCodes[i]).append("%' " );
				}
			}
		//@SuppressWarnings("unchecked")
		//List <SysUser> list = dao.createSQLQuery(sql.toString(),SysUser.class).list();
		List<TreeVO> tree = new ArrayList<TreeVO>();
		/*List<Vw_UserEmployee> vwlist=this.dao.getUserEmployee();
		for(SysUser s : list){
			String pid = s.getAccount()+"";
			String orgName="";
			for(Vw_UserEmployee ee:vwlist){
				if(s.getAccount().equals(ee.getAccount()))orgName=ee.getOrgName();
			}
			TreeVO vo = new TreeVO(pid,s.getName()+"（"+orgName+"）","-1",s.getEmployeeId()+"");
			tree.add(vo);
		}*/
		return tree;
	}
	
	@Transactional(readOnly = true)
	public SysUser findUserByUsernameMobile(String username,String password) throws Exception{
		try {
			QueryCondition qc  = new QueryCondition(SysUser.class);
			qc.addCondition("account", "=", username);
			List<SysUser> users = this.query(qc);
			if(users.size() > 1){
				throw new RuntimeException(ServiceErrorCode.ACCOUNT_NOT_UNIQUE.getDesc());//帐户名不唯一，请联系管理员
			}else if(users != null && users.size() > 0){
				SysUser user = users.get(0);
				if(ServiceErrorCode.ACCOUNT_STATUS_DISABLE.getCode().equals(user.getStatus())){
					//禁用=ACCOUNT_STATUS_DISABLE
					throw new RuntimeException("帐号"+user.getAccount()+"状态："+ServiceErrorCode.ACCOUNT_STATUS_DISABLE.getDesc());
				}
				if(password.equals(user.getPassword())){
					//String userType = user.getUserType();
					//String account = user.getAccount();
					//Integer dataYear = Integer.parseInt(( ((SysConfig)(Constant.CONFIG.get("DATA_YEAR"))).getValue()).toString());
					
					return user;
				}else{
					throw new RuntimeException(ServiceErrorCode.PASSWORD_ERROR.getDesc());
				}
			}else{
				throw new RuntimeException(ServiceErrorCode.ACCOUNT_NOT_EXIST.getDesc());
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			if(e instanceof RuntimeException){
				throw new RuntimeException(e.getMessage(),e);
			}else{
				throw new RuntimeException(ServiceErrorCode.SYSTEM_ERROR.getDesc(),e);
			}
		}
	}
	
	public HashMap<String, Object> findMenuByUserAcconut(SysUser user){
			HashMap<String, Object> map = null;
			try {
				map = resService.querySysMenuBySysUser(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return map;
	}
	
	public List<SysMenu> findMenuByPcode(String pcode) throws Exception{
		List<SysMenu> ls = resService.queryMenuByPcode(pcode);
		return ls;
	}
	
	
	public void findMenu(HttpServletRequest request){
		SysUser user = SessionUtils.getUser(request);
		HashMap<String, Object> menu = this.findMenuByUserAcconut(user);
		//ServletContext application = request.getSession().getServletContext();
		//application.setAttribute("menu",menu.get("menu"));
		
		try {
			//查询出所有按钮
			HashMap<String, String> m = resService.querySysMenuBtnBySysUser(user);
			//application.setAttribute("menu_btn",m.get("menu_btn")); //本用户有权限的按钮。
			//application.setAttribute("menu_btn_not",m.get("menu_btn_not")); //没有权限的按钮
			
			request.getSession(true).setAttribute("menu", menu.get("menu"));
			request.getSession(true).setAttribute("menu_btn", m.get("menu_btn"));
			request.getSession(true).setAttribute("menu_btn_not", m.get("menu_btn_not"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public HashMap<String, Object> findMenu(SysUser user){
		HashMap<String, Object> menu = this.findMenuByUserAcconut(user);
		return menu;
	}
	
	
	/**
	 * @TODO: 志愿者注册、编辑  头像
	 * @author: zhaichunlei
	 & @DATE : 2016年6月6日
	 * @param request
	 * @param sysUser
	 * @param entity
	 * @param uploadurl
	 * @param uploadurlorigname
	 * @return
	 * @throws Exception
	 */
	public SysUser saveZyzFormAndAttachPhoto(HttpServletRequest request,SysUser sysUser, SysUser entity,//HZyz hzyz,
			String uploadurl,String uploadurlorigname,String hzphoto) throws Exception{
		String account = entity.getAccount();
		String password = entity.getPassword();
		Integer  id = entity.getId();
		String name = entity.getName();
		String tel = entity.getTel();
		entity.setUserType("志愿者");
		
		if(id == null){
			if(account == null || account.length() == 0 || password == null || password.length() == 0) 
				throw new Exception("帐号、密码不能为空");
			if(name == null || name.length() == 0 || tel == null || tel.length() == 0){
				throw new Exception("姓名、电话不能为空");
			}
			List<SysUser> lst = dao.findBy("account", entity.getAccount());
			if(lst != null && lst.size() > 0 ){
				throw new Exception("帐号("+account+")已存在,不能注册");
			}
			entity.setStatus("未审核");//默认未审核
		}
		entity.setCreateBy(sysUser == null || sysUser.getAccount() == null ? entity.getAccount() : sysUser.getAccount());
		entity.setCreateDate(new Date());
		
		if(id == null){
			this.save(entity);
		}
		else{
			this.updateByCon(entity, false);
		}
		
		/*List<HZyz> hLst = hZyzManageDAO.findBy("account",account);
		HZyz zyz = new HZyz();
		if(hLst != null && hLst.size() > 0){
			zyz = hLst.get(0);
		}
		
		zyz.setAccount(account);
		zyz.setAddress(hzyz.getAddress() == null || hzyz.getAddress().length() == 0 ? zyz.getAddress() : hzyz.getAddress());
		zyz.setIdNo(hzyz.getIdNo() == null || hzyz.getIdNo().length() == 0 ? zyz.getIdNo() : hzyz.getIdNo());
		zyz.setWorkUnit(hzyz.getWorkUnit() == null || hzyz.getWorkUnit().length() == 0 ? zyz.getWorkUnit() : hzyz.getWorkUnit());
		zyz.setPosition(hzyz.getPosition() == null || hzyz.getPosition().length() == 0 ? zyz.getPosition() : hzyz.getPosition());
		zyz.setSex(hzyz.getSex() == null || hzyz.getSex().length() == 0 ? zyz.getSex() : hzyz.getSex());
		
		zyz.setCreateBy( sysUser.getName() );
		zyz.setCreateDate(new Date());
		
		if(zyz.getId() == null){
			hZyzManageDAO.save(zyz);
		}
		else{
			zyz.setHphoto(hzphoto == null || hzphoto.length() == 0 ? zyz.getHphoto() : hzphoto);
			hZyzManageDAO.updateByCon(zyz,false);
		}*/
		
		//上传头像时，不操作其它附近表
		//this.adao.deleteByBidAndCLassName(entity.getId(), this.getClassName());
		
		//保存用户照片
		String[] uploadurlarr = null;
		@SuppressWarnings("unused")
		String[] uploadurlorignamearr=null;
		if(!StringUtil.isEmpty(sysUser.getToken())){
			List<String> uploadurlList=new ArrayList<String>();
			List<String> uploadurlorignameList=new ArrayList<String>();
			FileUploadAction upload=new FileUploadAction();
			HashMap<String, Object> map=upload.uploadForApp(request);//调用上传文件方法，返回上传文件的地址和文件名
			Integer fileNumber=(Integer) map.get("fileNumber");//获取上传文件的个数
			fileNumber = fileNumber == null ? 0 : fileNumber;
			for(int i=0;i<fileNumber;i++){
				int x=i+1;
				boolean success=(boolean) map.get("success_"+x); 
				uploadurlorigname=(String) map.get("filename_"+x);
				uploadurl=(String) map.get("filenameindisk_"+x);
				if(success==true){
					if (!StringUtil.isEmpty(uploadurl)
							&& !StringUtil.isEmpty(uploadurlorigname)) {
						uploadurlList.add(uploadurl);
						//手机上传多图的时候只调用一次上传接口，所以跟web端不一样，不会返回两个带‘，’分割的字符串；
						uploadurlorignameList.add(uploadurlorigname);
					}
				}
			}
			//if(uploadurlList.size()>0)uploadurlarr= uploadurlList.toArray(new String[1]);//返回一个包含所有对象的指定类型的数组 
			if(uploadurlList.size()>0){
				uploadurlarr= uploadurlList.toArray(new String[1]);//返回一个包含所有对象的指定类型的数组
			}
			if(uploadurlorignameList.size()>0)uploadurlorignamearr=uploadurlorignameList.toArray(new String[1]);
		}else{
			if(!StringUtil.isEmpty(uploadurl)  && !StringUtil.isEmpty(uploadurlorigname)){
				uploadurlarr = uploadurl.split(",");
				uploadurlorignamearr=uploadurlorigname.split(",");
			}
		}
		if(uploadurlarr!=null && uploadurlarr.length > 0){
			//手机端上传照片（不是图片），需要更新到志愿者表头像中
			String h = uploadurlarr[0];
			if(h != null && h.length() > 0){
				//zyz.setHphoto(h);
				//this.updateByCon(zyz,false);
				//entity.setHelperPhoto(h);
			}
			//不保存附件表
			/*for (int i = 0; i < uploadurlarr.length; i++) {
				Sys_attachment att = new Sys_attachment(
						uploadurlorignamearr[i], uploadurlarr[i]);
				att.setBid(entity.getId());
				att.setCreate_by(sysUser.getAccount());
				att.setCreate_date(new Date());
				this.saveAttachment(att);
			}*/
		}
		
		return entity;
	}
	
	/**
	 * @TODO: 用户信息、编辑
	 * @author: zhaichunlei
	 & @DATE : 2016年6月6日
	 * @param request
	 * @param sysUser
	 * @param entity
	 * @param uploadurl
	 * @param uploadurlorigname
	 * @return
	 * @throws Exception
	 */
	public SysUser saveUserAndAttach(HttpServletRequest request,SysUser sysUser, SysUser entity,
			String uploadurl,String uploadurlorigname,String hzphoto) throws Exception{
		String account = entity.getAccount();
		String password = entity.getPassword();
		Integer  id = entity.getId();
		String name = entity.getName();
		String tel = entity.getTel();
		if(account == null || account.length() == 0) throw new Exception("参数帐号不能为空");
		if(id == null){
			if(account == null || account.length() == 0 || password == null || password.length() == 0) 
				throw new Exception("帐号、密码不能为空");
			if(name == null || name.length() == 0 || tel == null || tel.length() == 0){
				throw new Exception("姓名、电话不能为空");
			}
			
			List<SysUser> lst = dao.findBy("account", entity.getAccount());
			if(lst != null && lst.size() > 0 ){
				throw new Exception("帐号("+account+")已存在,不能注册");
			}
			
			this.save(entity); 
		}
		//修改
		else{
			if(account != null && account.length() > 0){//不能把本人的账号修改为它人的
				List<SysUser> lst = dao.listQuery("from SysUser where account=? and id<>?",account,id);
				if(lst != null && lst.size() > 0 ){
					throw new Exception("帐号("+account+")已存在,不能保存");
				}
			}
			this.updateByCon(entity,false);
		}
		
		
		//保存用户照片
		String[] uploadurlarr = null;
		String[] uploadurlorignamearr=null;
		if(!StringUtil.isEmpty(sysUser.getToken())){
			List<String> uploadurlList=new ArrayList<String>();
			List<String> uploadurlorignameList=new ArrayList<String>();
			FileUploadAction upload=new FileUploadAction();
			HashMap<String, Object> map=upload.uploadForApp(request);//调用上传文件方法，返回上传文件的地址和文件名
			Integer fileNumber=(Integer) map.get("fileNumber");//获取上传文件的个数
			fileNumber = fileNumber == null ? 0 : fileNumber;
			for(int i=0;i<fileNumber;i++){
				int x=i+1;
				boolean success=(boolean) map.get("success_"+x); 
				uploadurlorigname=(String) map.get("filename_"+x);
				uploadurl=(String) map.get("filenameindisk_"+x);
				if(success==true){
					if (!StringUtil.isEmpty(uploadurl)
							&& !StringUtil.isEmpty(uploadurlorigname)) {
						uploadurlList.add(uploadurl);
						//手机上传多图的时候只调用一次上传接口，所以跟web端不一样，不会返回两个带‘，’分割的字符串；
						uploadurlorignameList.add(uploadurlorigname);
					}
				}
			}
			//if(uploadurlList.size()>0)uploadurlarr= uploadurlList.toArray(new String[1]);//返回一个包含所有对象的指定类型的数组 
			if(uploadurlList.size()>0){
				uploadurlarr= uploadurlList.toArray(new String[1]);//返回一个包含所有对象的指定类型的数组 
				
				//手机端上传照片（不是图片），需要更新到贫困户头像中
				String h = uploadurlarr[0];
				if(h != null && h.length() > 0){
					//entity.setHelperPhoto(h);
					//Integer dataYear = Integer.parseInt(( ((SysConfig)(Constant.CONFIG.get("DATA_YEAR"))).getValue()).toString());
					@SuppressWarnings("unchecked")
					List<SysUser> ulst = dao.createQuery("from SysUser where  account=?", account).list();
					if(ulst != null && ulst.size() > 0){
						SysUser us = ulst.get(0);
						String ut = us.getUserType();
						//贫困户，头像保存在b_poor中
						if(ut != null && ut.indexOf("贫困户") != -1){
							/*@SuppressWarnings("unchecked")
							List<BPoor> blst = bPoorManageDAO.createQuery("from BPoor where dataYear = ? and idNumber=?",dataYear,account).list();
							if(blst != null && blst.size() > 0){
								BPoor bPoor = blst.get(0);
								bPoor.setHzphoto(h);
								bPoorManageDAO.updateByCon(bPoor,false);
							}*/
						}
						//这几类用户，头像保存在h_person中
						else if(ut != null &&  (ut.indexOf("驻村工作队用户") != -1 || ut.indexOf("党员帮户干部") != -1 || ut.indexOf("农技人员")  != -1  ||
								ut.indexOf("挂联领导")  != -1 )){
							
							//String ac2 = account.replace("sj","");
							/*List<HPerson> hlst = hPersonManageDAO.findBy("tel", ac2);//account
							if(hlst != null && hlst.size() > 0){
								HPerson hp = hlst.get(0);
								hp.setPicture(h);
								hPersonManageDAO.updateByCon(hp,false);
							}*/
						}
					}
					
				}
			}
			if(uploadurlorignameList.size()>0)uploadurlorignamearr=uploadurlorignameList.toArray(new String[1]);
		}else{
			if(!StringUtil.isEmpty(uploadurl)  && !StringUtil.isEmpty(uploadurlorigname)){
				uploadurlarr = uploadurl.split(",");
				uploadurlorignamearr=uploadurlorigname.split(",");
			}
		}
		if(uploadurlarr!=null){
			this.adao.deleteByBidAndCLassName(entity.getId(), this.getClassName());
			for (int i = 0; i < uploadurlarr.length; i++) {
				Sys_attachment att = new Sys_attachment(
						uploadurlorignamearr[i], uploadurlarr[i]);
				att.setBid(entity.getId());
				att.setCreate_by(sysUser.getAccount());
				att.setCreate_date(new Date());
				this.saveAttachment(att);
			}
		}
		
		return entity;
	}
	/**
	 * 删除
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	@Transactional(readOnly=false)
	public void delete2(SysUser user, String ids) throws Exception{
		if(ids == null || ids.length() == 0 ) return;
		String is[] = ids.split(",");
		for(String idst : is){
			if(idst == null || idst.length() == 0 ) continue;
			Integer Id = Integer.parseInt(idst);
			SysUser r = dao.get(Id);
			if(r != null){
			//	Integer userId = r.getId();
				Integer id = r.getId();
				//hPersonManageDAO.deleteBySql("delete from sys_user_role where user_id=?",userId);
			//	menuDao.deleteBySql("delete from sys_role_menu where role_id=?",roleId);
				dao.deleteBySql("delete from sys_user where id=? ",id);
				
				dao.removeById(id);
			}
		}
	}
	 /** 
     * 向指定 URL 发送POST方法的请求
     * @param url  发送请求的 URL
     * @param param  请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public Object sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String data = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON);
            /*conn.setRequestProperty("connection", "Keep-Alive");*/
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                data += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        JSONObject json=JSONObject.fromObject(data);
        ShouYeDataVO vo=(ShouYeDataVO)JSONObject.toBean(json,ShouYeDataVO.class);
		return vo;
    }
    
    /**
	   * 请求登陆统计数据
	   * @param id
	   * @param status
	   */
    public List<ShouYeLandVO> getLandData(String account) {
		StringBuilder sql = new StringBuilder("SELECT ROW_NUMBER() OVER(ORDER BY create_date) sort, "
		+"create_date stime FROM sys_user_option_log WHERE content='登录系统!'  "
		+"AND create_by='"+account+"' "//AND datediff(month,create_date,getdate())=0 "
		+ " and (date_part('year', now()) - date_part('year', create_date) ) * 12 + date_part('month', now()) - date_part('month', create_date) = 0 " 
		+"GROUP BY create_date  ORDER BY create_date desc ");
		@SuppressWarnings("unchecked")
		List<ShouYeLandVO> list = this.dao
				.createSQLQuery(sql.toString())
				.setResultTransformer(
						Transformers.aliasToBean(ShouYeLandVO.class)).list();
		return list;
	}
}
