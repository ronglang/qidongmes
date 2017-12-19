package com.css.business.web.sysManage.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.sysManage.bean.SysEmployee;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.business.web.sysManage.dao.SysAttachmentManageDAO;
import com.css.business.web.sysManage.dao.SysEmployeeManageDAO;
import com.css.business.web.sysManage.dao.SysUserManageDAO;
import com.css.common.util.Md5Util;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.bean.Sys_attachment;
import com.css.common.web.syscommon.bean.TreeVO;
import com.css.common.web.syscommon.controller.FileUploadAction;
import com.css.common.web.syscommon.controller.support.QueryCondition;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("sysEmployeeManageService")
public class SysEmployeeManageService extends BaseEntityManageImpl<SysEmployee, SysEmployeeManageDAO> {
	@Resource(name = "sysAttachmentManageDAO")
	private SysAttachmentManageDAO adao;

	@Resource(name="sysEmployeeManageDAO")
	private SysEmployeeManageDAO dao;
	
	@Resource(name="sysUserManageDAO")
	//@Autowired
	private SysUserManageDAO userdao; 
	
	@Override
	public SysEmployeeManageDAO getEntityDaoInf() {
		return dao;
	}
	

	
public List<TreeVO> getTree(String code,Object dvalue)throws Exception{
		QueryCondition qc = new QueryCondition(SysEmployee.class);
		if(!StringUtil.isEmpty(code)){
			qc.addCondition(code, "=",dvalue);
		}
		List <SysEmployee> list = this.query(qc);
		List<TreeVO> tree = new ArrayList<TreeVO>();
		for(SysEmployee s : list){
			TreeVO vo = new TreeVO(s.getId().toString(),s.getName().toString());
			tree.add(vo);
		}
		return tree;
	}

public void save(SysEmployee SysEmployee){
	String account = SysEmployee.getTel();

	if(SysEmployee.getId() == null){
		
		SysUser sysuser = userdao.getUserByAccount(account);
		if(sysuser != null){
			throw new RuntimeException("保存错误，原因：手机号"+account+"已创建了帐号，一个手机只能关联一个帐号！");
		}
		
		dao.save(SysEmployee);
//		SysUser user = new SysUser();
//		user.setAccount(account);
//		user.setPassword(Md5Util.MD5("666666"));
//		user.setDept(SysEmployee.getWorkUnit());
//		user.setTel(SysEmployee.getTel());
//		user.setStatus(SysEmployee.getHealth());
//		user.setName(SysEmployee.getName());
//		user.setPerson_id(SysEmployee.getId());
//		user.setCreateBy(SysEmployee.getCreateBy());
//		user.setCreateDate(new Date());
//		userdao.save(user);
	}else{
		/*SysUser sysuser = userdao.getUserByAccount(sysEmployee.getId(), account);
		if(sysuser != null){
			throw new RuntimeException("更新错误，原因：手机号"+account+"已创建了帐号，一个手机只能关联一个帐号！");
		}
		*/
		
		dao.save(SysEmployee);
		/*SysUser u = new SysUser();
		u.setEmployeeId(sysEmployee.getId());
		List<SysUser> list = userdao.listQuery(u);
		if(list.size() > 1){
			throw new RuntimeException("更新错误，原因：人员"+sysEmployee.getName()+"创建了两个帐号，只能一人一个帐号！");
		}
		for(SysUser us : list){
			us.setAccount(sysEmployee.getMobileTel());
			us.setPassword(Md5Util.MD5("666666"));
			us.setOrgCode(sysEmployee.getOrgCode());
			us.setTel(sysEmployee.getMobileTel());
			us.setStatus("C10020001");
			us.setName(sysEmployee.getName());
			us.setEmployeeId(sysEmployee.getId());
			us.setCreateBy(sysEmployee.getCreateBy());
			us.setCreateDate(new Date());
			userdao.save(us);
		}*/
	}	
}

@Transactional(readOnly = false)
public void saveFormAndAttach(HttpServletRequest request,SysUser user,
					SysEmployee SysEmployee,String uploadurl,String uploadurlorigname) throws Exception{
	this.save(SysEmployee);
	createSysUser(user,SysEmployee);
	
	String[] uploadurlarr = null;
	String[] uploadurlorignamearr=null;
	if(!StringUtil.isEmpty(user.getToken())){
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
		if(uploadurlList.size()>0){
			uploadurlarr= uploadurlList.toArray(new String[1]);//返回一个包含所有对象的指定类型的数组
			
			/*//手机端上传照片（不是图片），需要更新到贫困户头像中
			String hzphoto = uploadurlarr[0];
			if(hzphoto != null && hzphoto.length() > 0){
				bPoor.setHzphoto(hzphoto);
				this.updateByCon(bPoor,false);
			}*/
		}
		if(uploadurlorignameList.size()>0)uploadurlorignamearr=uploadurlorignameList.toArray(new String[1]);
	}else{
		if(!StringUtil.isEmpty(uploadurl)  && !StringUtil.isEmpty(uploadurlorigname)){
			uploadurlarr = uploadurl.split(",");
			uploadurlorignamearr=uploadurlorigname.split(",");
		}
	}
	
	
	if(uploadurlarr!=null){
		this.adao.deleteByBid(SysEmployee.getId(), this.getClassName());
		
		for (int i = 0; i < uploadurlarr.length; i++) {
			Sys_attachment att = new Sys_attachment(
					uploadurlorignamearr[i], uploadurlarr[i]);
			att.setBid(SysEmployee.getId());
			att.setCreate_by(user.getAccount());
			att.setCreate_date(new Date());
			this.saveAttachment(att);
		}
	}
}

private void createSysUser(SysUser su,SysEmployee emp) throws Exception{
	Integer id = emp.getId();
	String userType = emp.getUserType();
	if("登陆用户".equals(userType)){
		//判断账号是否存在
		String account = emp.getAccount();
		if(account == null || account.length() == 0)
			throw new Exception("该人员为系统登陆用户， 请录入用户帐户。");
		
		SysUser user = userdao.getUserByPersonId(id);
		if(user == null)
			user = new SysUser();
		
		user.setPerson_id(emp.getId());
		user.setAccount(account);
		String pass = Md5Util.MD5("666666");
		user.setPassword(pass);
		user.setTel(emp.getTel());
		user.setCreateDate(new Date());
		user.setCreateBy(su.getName());
		user.setName(emp.getName());
		user.setDept(emp.getDepartment());
		user.setStatus("C10020001");//启用
		
		if(user.getId() == null)
			userdao.save(user);
		else
			userdao.updateByCon(user,false);
	}
	
}

@Transactional(readOnly = true)
public List<SysEmployee> queryAttachmentBy(Serializable id){
	String hql = " from SysEmployee where id =?";
	return this.dao.listQuery(hql, id);
}

public void updatePictureUrl(Integer id){
	String sql="UPDATE H_PERSON  SET PICTURE='' WHERE ID=?";
	this.dao.getSessionFactory().getCurrentSession().createSQLQuery(sql)
	.setParameter(0, id)
	.executeUpdate();
}
public List<TreeVO> getTreeAddUser(String code, Object dvalue) throws Exception {
	QueryCondition qc = new QueryCondition(SysEmployee.class);
	if (!StringUtil.isEmpty(code)) {
		qc.addCondition(code, "=", dvalue);
	}
	List<SysEmployee> list = dao.queryUserNoAccount(qc);
	List<TreeVO> tree = new ArrayList<TreeVO>();
	for (SysEmployee s : list) {
		TreeVO vo = new TreeVO(s.getId().toString(), s.getName()
				.toString());
		tree.add(vo);
	}
	return tree;
}

public List<TreeVO> getTreeNoExit(String code, Object dvalue,Integer id)
		throws Exception {
	QueryCondition qc = new QueryCondition(SysEmployee.class);
	if (!StringUtil.isEmpty(code)) {
		qc.addCondition(code, "=", dvalue);
	}
	List<SysEmployee> list = dao.queryUserNoAccounts(qc,id);
	List<TreeVO> tree = new ArrayList<TreeVO>();
	for (SysEmployee s : list) {
		TreeVO vo = new TreeVO(s.getId().toString(), s.getName().toString());
		tree.add(vo);
	}
	return tree;
}
	
}