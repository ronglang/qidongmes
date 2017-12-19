package com.css.business.web.sysManage.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "SYS_EMPLOYEE")
public class SysEmployee implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -8398933246240625753L;
	private Integer id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "ORG_ID")
	private String orgId;
	@Column(name = "AREA_CODE")
	private String areaCode;
	@Column(name = "GENDER")
	private String gender;
	@Column(name = "EDUCATION")
	private String education;
	@Column(name = "DEGREE")
	private String degree;
	@Column(name = "ENGLISH_LEVEL")
	private String englishLevel;
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
	@Column(name = "HOME_PLACE")
	private String homePlace;
	@Column(name = "CURRENT_PLACE")
	private String currentPlace;
	@Column(name = "ID_NO")
	private String idNo;
	@Column(name = "MARITAL_STATUS")
	private String maritalStatus;
	@Column(name = "BIRTH_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date birthDate;
	@Column(name = "TEL")
	private String tel;
	@Column(name = "MOBILE_TEL")
	private String mobileTel;
	@Column(name = "HOME_TEL")
	private String homeTel;
	@Column(name = "OFFICE_TEL")
	private String officeTel;
	@Column(name = "PICTURE")
	private String picture;
	@Column(name = "WEIGHT")
	private BigDecimal weight;
	@Column(name = "HEIGHT")
	private Integer height;
	@Column(name = "HEALTH")
	private String health;
	@Column(name = "FAITH")
	private String faith;
	@Column(name = "JOIN_COMSOMOL_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date joinComsomolTime;
	@Column(name = "JOIN_COMMY_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date joinCommyTime;
	@Column(name = "OTHER_NAME")
	private String otherName;
	@Column(name = "WORK_TYPE")
	private String workType;
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
	@Column(name = "department")
	private String department;
	@Column(name = "user_type")
	private String userType;
	@Column(name = "account")
	private String account;
	@Column(name="emp_rfid")
	private String empRfid;

	@Id
	@Column(name = "ID")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "SEQ_SYS_EMPLOYEE", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_SYS_EMPLOYEE")  
	@GeneratedValue(generator = "SEQ_SYS_EMPLOYEE", strategy = GenerationType.SEQUENCE)
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

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
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

	public String getEnglishLevel() {
		return englishLevel;
	}

	public void setEnglishLevel(String englishLevel) {
		this.englishLevel = englishLevel;
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

	public String getHomePlace() {
		return homePlace;
	}

	public void setHomePlace(String homePlace) {
		this.homePlace = homePlace;
	}

	public String getCurrentPlace() {
		return currentPlace;
	}

	public void setCurrentPlace(String currentPlace) {
		this.currentPlace = currentPlace;
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getFaith() {
		return faith;
	}

	public void setFaith(String faith) {
		this.faith = faith;
	}

	public Date getJoinComsomolTime() {
		return joinComsomolTime;
	}

	public void setJoinComsomolTime(Date joinComsomolTime) {
		this.joinComsomolTime = joinComsomolTime;
	}

	public Date getJoinCommyTime() {
		return joinCommyTime;
	}

	public void setJoinCommyTime(Date joinCommyTime) {
		this.joinCommyTime = joinCommyTime;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmpRfid() {
		return empRfid;
	}

	public void setEmpRfid(String empRfid) {
		this.empRfid = empRfid;
	}

}
