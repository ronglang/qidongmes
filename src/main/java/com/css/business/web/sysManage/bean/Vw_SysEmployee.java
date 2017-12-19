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

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "VW_SYS_EMPLOYEE")

public class Vw_SysEmployee implements BaseEntity{

	@Transient
	private static final long serialVersionUID = 4561392194501892554L;
	private Integer id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "ORG_CODE")
	private String orgCode;
	@Column(name = "ZZMM")
	private String zzmm;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "GENDER")
	private String gender;
	@Column(name = "EDUCATION")
	private String education;
	@Column(name = "DEGREE")
	private String degree;
	@Column(name = "SCHOOL_NAME")
	private String schoolName;
	@Column(name = "GRADUATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date graduateTime;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "NATION")
	private String nation;
	@Column(name = "NATIVE_PLACE")
	private String nativePlace;
	@Column(name = "BIRTH_PLACE")
	private String birthPlace;
	@Column(name = "CURRENT_PLACE")
	private String currentPlace;
	@Column(name = "HIRED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date hiredDate;
	@Column(name = "ID_NO")
	private String idNo;
	@Column(name = "MARITAL_STATUS")
	private String maritalStatus;
	@Column(name = "BIRTH_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date birthDate;
	@Column(name = "MOBILE_TEL")
	private String mobileTel;
	@Column(name = "OFFICE_TEL")
	private String officeTel;
	@Column(name = "PICTURE")
	private String picture;
	@Column(name = "JOIN_COMMY_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date joinCommyTime;
	@Column(name = "LEVEL")
	private String level;
	@Column(name = "POSITION")
	private String position;
	@Column(name = "CREATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "CREATE_BY")
	private String createBy;
	@Column(name = "REMARK")
	private String remark;
	@Column(name = "TRAINING")
	private String training;
	@Column(name = "ONEDUCATION")
	private String oneducation;
	@Column(name = "ONDEGREE")
	private String ondegree;
	@Column(name = "ONSCHOOL_NAME")
	private String onschoolName;
	@Column(name = "SORT")
	private String sort;
	
	         
	@Column(name = "ORG_NAME")
	private String orgName;
	@Column(name = "ZZMMS")
	private String zzmms;
	@Column(name = "STATUS_NAME")
	private String statusName;
	@Column(name = "GENDERS")
	private String genders;
	@Column(name = "EDUCATIONS")
	private String educations;
	@Column(name = "DEGREES")
	private String degrees;
	@Column(name = "NATIONS")
	private String nations;
	@Column(name = "MARITAL_STATUS_NAME")
	private String maritalStatusName;
	@Column(name = "LEVELS")
	private String levels;
	@Column(name = "CREATE_BYS")
	private String createBys;
	@Column(name = "TRAINING_NAME")
	private String trainingName;
	@Column(name = "ONEDUCATIONS")
	private String oneducations;
	@Column(name = "ORG_AREA_CODE")
	private String orgAreaCode;

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

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Date getGraduateTime() {
		return graduateTime;
	}

	public void setGraduateTime(Date graduateTime) {
		this.graduateTime = graduateTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getCurrentPlace() {
		return currentPlace;
	}

	public void setCurrentPlace(String currentPlace) {
		this.currentPlace = currentPlace;
	}

	public Date getHiredDate() {
		return hiredDate;
	}

	public void setHiredDate(Date hiredDate) {
		this.hiredDate = hiredDate;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getMobileTel() {
		return mobileTel;
	}

	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getJoinCommyTime() {
		return joinCommyTime;
	}

	public void setJoinCommyTime(Date joinCommyTime) {
		this.joinCommyTime = joinCommyTime;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getZzmms() {
		return zzmms;
	}

	public void setZzmms(String zzmms) {
		this.zzmms = zzmms;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getGenders() {
		return genders;
	}

	public void setGenders(String genders) {
		this.genders = genders;
	}

	public String getEducations() {
		return educations;
	}

	public void setEducations(String educations) {
		this.educations = educations;
	}

	public String getDegrees() {
		return degrees;
	}

	public void setDegrees(String degrees) {
		this.degrees = degrees;
	}

	public String getNations() {
		return nations;
	}

	public void setNations(String nations) {
		this.nations = nations;
	}

	public String getMaritalStatusName() {
		return maritalStatusName;
	}

	public void setMaritalStatusName(String maritalStatusName) {
		this.maritalStatusName = maritalStatusName;
	}

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	public String getCreateBys() {
		return createBys;
	}

	public void setCreateBys(String createBys) {
		this.createBys = createBys;
	}

	public String getTraining() {
		return training;
	}

	public void setTraining(String training) {
		this.training = training;
	}

	public String getTrainingName() {
		return trainingName;
	}

	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}

	public String getOneducation() {
		return oneducation;
	}

	public void setOneducation(String oneducation) {
		this.oneducation = oneducation;
	}

	public String getOndegree() {
		return ondegree;
	}

	public void setOndegree(String ondegree) {
		this.ondegree = ondegree;
	}

	public String getOnschoolName() {
		return onschoolName;
	}

	public void setOnschoolName(String onschoolName) {
		this.onschoolName = onschoolName;
	}

	public String getOneducations() {
		return oneducations;
	}

	public void setOneducations(String oneducations) {
		this.oneducations = oneducations;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public String getOrgAreaCode() {
		return orgAreaCode;
	}

	public void setOrgAreaCode(String orgAreaCode) {
		this.orgAreaCode = orgAreaCode;
	}
}
