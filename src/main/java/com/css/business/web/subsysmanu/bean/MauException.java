package com.css.business.web.subsysmanu.bean;

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
@Table(name = "mau_exception")
public class MauException implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 8282947334240019490L;
	
	@Id
	@Column(name = "id")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "seq_mau_exception", allocationSize = 1, initialValue = 1, sequenceName = "seq_mau_exception")  
	@GeneratedValue(generator = "seq_mau_exception", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "code")
	private String code;
	@Column(name = "mac_code")
	private String macCode;
	@Column(name = "me_info")
	private String meInfo;
	@Column(name = "remark")
	private String remark;
	@Column(name = "me_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date meTime;
	@Column(name = "seq_code")
	private String seqCode;
	/*@Column(name = "c_code")
	private String cCode;*/
	@Column(name = "agent_by")
	private String agentBy;
	@Column(name = "course_code")
	private String courseCode;
	@Column(name = "pro_craft_code")
	private String proCraftCode;
	@Column(name = "seq_mater_detail_id")
	private Integer seqMaterDetailId;
	@Column(name = "machine_plan_id")
	private Integer machinePlanId;
	@Column(name = "week_plan_id")
	private Integer weekPlanId;
	@Column(name = "para_type")
	private String paraType;
	@Column(name = "axis_name")
	private String axisName;
	@Column(name = "exception_param")
	private String exceptionParam;
	@Column(name = "exception_value")
	private String exceptionValue;
	@Column(name = "state")
	private String state;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getMeTime() {
		return meTime;
	}

	public void setMeTime(Date meTime) {
		this.meTime = meTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	public String getMeInfo() {
		return meInfo;
	}

	public void setMeInfo(String meInfo) {
		this.meInfo = meInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSeqCode() {
		return seqCode;
	}

	public void setSeqCode(String seqCode) {
		this.seqCode = seqCode;
	}

	public String getAgentBy() {
		return agentBy;
	}

	public void setAgentBy(String agentBy) {
		this.agentBy = agentBy;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getProCraftCode() {
		return proCraftCode;
	}

	public void setProCraftCode(String proCraftCode) {
		this.proCraftCode = proCraftCode;
	}

	public Integer getSeqMaterDetailId() {
		return seqMaterDetailId;
	}

	public void setSeqMaterDetailId(Integer seqMaterDetailId) {
		this.seqMaterDetailId = seqMaterDetailId;
	}

	public Integer getMachinePlanId() {
		return machinePlanId;
	}

	public void setMachinePlanId(Integer machinePlanId) {
		this.machinePlanId = machinePlanId;
	}

	public Integer getWeekPlanId() {
		return weekPlanId;
	}

	public void setWeekPlanId(Integer weekPlanId) {
		this.weekPlanId = weekPlanId;
	}

	public String getParaType() {
		return paraType;
	}

	public void setParaType(String paraType) {
		this.paraType = paraType;
	}

	public String getAxisName() {
		return axisName;
	}

	public void setAxisName(String axisName) {
		this.axisName = axisName;
	}

	public String getExceptionParam() {
		return exceptionParam;
	}

	public void setExceptionParam(String exceptionParam) {
		this.exceptionParam = exceptionParam;
	}

	public String getExceptionValue() {
		return exceptionValue;
	}

	public void setExceptionValue(String exceptionValue) {
		this.exceptionValue = exceptionValue;
	}

	/**
	 * @return  state
	 */
	public String getState() {
		return state;
	}

	/**
	 *  @param state 要设置的 state 
	 *    
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	
}
