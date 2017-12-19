package com.css.business.web.subsysplan.bean;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.css.common.web.syscommon.bean.BaseEntity;

/**
 * @TODO  : 机台计划领料计划表 (铜线，胶料)
 * @author: 翟春磊
 * @DATE  : 2017年7月11日
 */
@Entity
@Table(name = "PLA_MACHINE_PLAN_MATER")
@SequenceGenerator(name = "SEQ_PLA_MACHINE_PLAN_MATER", sequenceName = "SEQ_PLA_MACHINE_PLAN_MATER")
public class PlaMachinePlanMater implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -7987553041832352029L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "machine_plan_id")
	private Integer machinePlanId;
	@Column(name = "amount")
	private Double amount;
	@Column(name = "unit")
	private String unit;
	@Column(name = "ggxh")
	private String ggxh;
	@Column(name = "axis_name")
	private String axisName;
	@Column(name = "machine_id")
	private Integer machineId;
	@Column(name = "course_code")
	private String courseCode;
	@Column(name = "plan_send_time")
	private Timestamp planSendTime;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	private Date createDate;
	@Column(name = "flag")
	private String flag;
	@Column(name = "send_state")
	private String sendState;
	@Column(name = "mater_name")
	private String materName;
	
	//原料计划配送时间
	@Transient
	private Long planSendTime_inLong;
	
	@Transient
	private String macName;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMachinePlanId() {
		return machinePlanId;
	}
	public void setMachinePlanId(Integer machinePlanId) {
		this.machinePlanId = machinePlanId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getGgxh() {
		return ggxh;
	}
	public void setGgxh(String ggxh) {
		this.ggxh = ggxh;
	}
	public String getAxisName() {
		return axisName;
	}
	public void setAxisName(String axisName) {
		this.axisName = axisName;
	}
	public Integer getMachineId() {
		return machineId;
	}
	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public Timestamp getPlanSendTime() {
		return planSendTime;
	}
	public void setPlanSendTime(Timestamp planSendTime) {
		this.planSendTime = planSendTime;
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
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getSendState() {
		return sendState;
	}
	public void setSendState(String sendState) {
		this.sendState = sendState;
	}
	public String getMaterName() {
		return materName;
	}
	public void setMaterName(String materName) {
		this.materName = materName;
	}
	public String getMacName() {
		return macName;
	}
	public void setMacName(String macName) {
		this.macName = macName;
	}
	
	public Long getPlanSendTime_inLong() {
		if(planSendTime == null)
			return null;
		else
			return planSendTime.getTime();
	}
	public void setPlanSendTime_inLong(Long planSendTime_inLong) {
		this.planSendTime_inLong = planSendTime_inLong;
	}
}
