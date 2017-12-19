package com.css.business.web.sysManage.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.Constant;
import com.css.common.util.JsonDateSerializer;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "sys_user")
public class SysUser implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -7049683423456196825L;
	
	private Integer id;
	@Column(name = "account")
	private String account;
	@Column(name = "person_id")
	private Integer person_id;
	@Column(name = "name")
	private String name;
	@Column(name = "password")
	private String password;
	@Column(name = "dept")
	private String dept;
	/*@Column(name = "org_code")
	private String orgCode;*/
	@Column(name = "area_code")
	private String areaCode;
	@Column(name = "tel")
	private String tel;
	@Column(name = "status")
	private String status;
	@Column(name = "user_type")
	private String userType;
	@Column(name = "change_pw_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date changePwDate;
	@Column(name = "remark")
	private String remark;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	
	@Transient
	private java.util.List<MenuTree> userMenuTree = new ArrayList<MenuTree>();
	
	@Transient
	List<SysMenu> userAllResource;
	@Transient
	List<SysMenu> sysAllResource;
	@Transient
	List<String> roles;
	@Transient
	List<String> roleId;
	@Transient
	private String areaCodeName;
	@Transient
	private String roleIdShow;
	
	@Transient
	private String token;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	
	@Transient
	public boolean isAdmin(){
		return "admin".equals(account) ? true : false;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPerson_id() {
		return person_id;
	}

	public void setPerson_id(Integer person_id) {
		this.person_id = person_id;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Date getChangePwDate() {
		return changePwDate;
	}

	public void setChangePwDate(Date changePwDate) {
		this.changePwDate = changePwDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Transient
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Transient
	public List<SysMenu> getUserAllResource() {
		return userAllResource;
	}

	public void setUserAllResource(List<SysMenu> userAllResource) {
		this.userAllResource = userAllResource;
	}

	@Transient
	public java.util.List<MenuTree> getUserMenuTree() {
		return userMenuTree;
	}

	public void setUserMenuTree(java.util.List<MenuTree> userMenuTree) {
		this.userMenuTree = userMenuTree;
	}

	@Transient
	public List<SysMenu> getSysAllResource() {
		return sysAllResource;
	}

	public void setSysAllResource(List<SysMenu> sysAllResource) {
		this.sysAllResource = sysAllResource;
	}

	@Transient
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Transient
	public List<String> getRoleId() {
		return roleId;
	}

	public void setRoleId(List<String> roleId) {
		this.roleId = roleId;
	}

	@Transient
	public String getRoleIdShow() {
		return roleIdShow;
	}
	
	public void setRoleIdShow(String roleIdShow) {
		this.roleIdShow = roleIdShow;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Transient
	public String getAreaCodeName() {
		if(!StringUtil.isEmpty(this.getAreaCode())){
			SysArea area = Constant.AREA.get(this.getAreaCode());
			if(area!=null){
				return area.getAname();
			}
			return areaCodeName;
		}
		return areaCodeName;
	}

	public void setAreaCodeName(String areaCodeName) {
		this.areaCodeName = areaCodeName;
	}
}
