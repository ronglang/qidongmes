package com.css.business.web.sysManage.bean;

import java.util.Date;

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

import com.css.common.annotation.RemarkClass;
import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "SYS_CONTACTS")
@RemarkClass(toDo="通讯录信息")
public class SysContacts implements BaseEntity {
	@Transient
	private static final long serialVersionUID = 2550434264532998063L;
	private Integer id;
	@Column(name = "CONTACT_GROUP_ID",columnDefinition="分组ID")
	private Integer contactGroupId;
	@Column(name = "NAME",columnDefinition="姓名")
	private String name;
	@Column(name = "ORG_ID",columnDefinition="所在机构")
	private String orgId;
	
	@Column(name = "GENDER",columnDefinition="性别")
	private String gender;
	
	@Column(name = "EDUCATION",columnDefinition="学历")
	private String education;
	
	@Column(name = "SCHOOL_NAME",columnDefinition="学校名称")
	private String schoolName;
	
	@Column(name = "EMAIL",columnDefinition="EMAIL")
	private String email;
	
	@Column(name = "TEL",columnDefinition="电话")
	private String tel;
	@Column(name = "MOBILE_TEL",columnDefinition="手机")
	private String mobileTel;
	@Column(name = "HOME_TEL",columnDefinition="家庭电话")
	private String homeTel;
	@Column(name = "OFFICE_TEL",columnDefinition="办公电话")
	private String officeTel;
	
	@Column(name = "WORK_TYPE",columnDefinition="工作性质")
	private String workType;
	@Column(name = "POSITION",columnDefinition="职位")
	private String position;
	@Column(name = "EDUCATIONBACKGROUND",columnDefinition="教育背景")
	private String educationbackground;
	@Column(name = "CREATE_DATE",columnDefinition="创建时间")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "CREATE_BY",columnDefinition="创建人")
	private String createBy;

	@Column(name = "REMARK",columnDefinition="备注")
	private String remark;
	@Column(name = "PINYIN",columnDefinition="拼音简码")
	private String pinyin;
	

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobileTel() {
		return mobileTel;
	}

	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}

	public String getHomeTel() {
		return homeTel;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEducationbackground() {
		return educationbackground;
	}

	public void setEducationbackground(String educationbackground) {
		this.educationbackground = educationbackground;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public Integer getContactGroupId() {
		return contactGroupId;
	}

	public void setContactGroupId(Integer contactGroupId) {
		this.contactGroupId = contactGroupId;
	}

}
