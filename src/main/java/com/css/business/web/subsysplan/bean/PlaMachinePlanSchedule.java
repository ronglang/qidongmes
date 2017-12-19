package com.css.business.web.subsysplan.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
@Table(name = "pla_machine_plan_schedule")
public class PlaMachinePlanSchedule implements BaseEntity {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 582976590680417481L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_pla_machine_plan_schedule", sequenceName = "seq_pla_machine_plan_schedule")
	@GeneratedValue(generator = "seq_pla_machine_plan_schedule", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "machine_plan_id")
	private Integer machinePlanId;
	@Column(name = "week_plan_id")
	private Integer weekPlanId;
	@Column(name = "work_date")
	// @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date workDate;
	@Column(name = "machine_id")
	private Integer machineId;
	@Column(name = "employee_id")
	private Integer employeeId;
	@Column(name = "axis_name")
	private String axisName;
	@Column(name = "product_craft")
	private String productCraft;
	@Column(name = "plan_start_time")
	// @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp planStartTime;
	@Column(name = "fact_start_time")
	// @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp factStartTime;
	@Column(name = "plan_end_time")
	// @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp planEndTime;
	@Column(name = "fact_end_time")
	// @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp factEndTime;
	@Column(name = "plan_incoming_time")
	// @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp planIncomingTime;
	@Column(name = "fact_incoming_time")
	// @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp factIncomingTime;
	@Column(name = "incoming_axis")
	private String incomingAxis;
	@Column(name = "seq_code")
	private String seqCode;
	@Column(name = "seq_semi_product_axis")
	private String seqSemiProductAxis;
	@Column(name = "plan_send_next_seq_date")
	// @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp planSendNextSeqDate;
	@Column(name = "fact_send_next_seq_date")
	// @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp factSendNextSeqDate;
	@Column(name = "up_seq_code")
	private String upSeqCode;
	@Column(name = "next_seq_code")
	private String nextSeqCode;
	@Column(name = "next_seq_machine_code")
	private String nextSeqMachineCode;
	@Column(name = "semi_product_len")
	private BigDecimal semiProductLen;
	@Column(name = "ready_time")
	private Integer readyTime;
	@Column(name = "product_time")
	private Integer productTime;
	@Column(name = "product_speed")
	private BigDecimal productSpeed;
	@Column(name = "product_speed_avg")
	private BigDecimal productSpeedAvg;
	@Column(name = "sort")
	private Integer sort;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;

	/** 工单编号 */
	@Column(name = "course_code")
	private String courseCode;
	
	@Column(name = "virtual_rfid")
	private String virtualRfid;
	@Column(name = "virtual_rfid_income")
	private String virtualRfidIncome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWeekPlanId() {
		return weekPlanId;
	}

	public void setWeekPlanId(Integer weekPlanId) {
		this.weekPlanId = weekPlanId;
	}

	public Integer getMachineId() {
		return machineId;
	}

	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public String getAxisName() {
		return axisName;
	}

	public void setAxisName(String axisName) {
		this.axisName = axisName;
	}

	public String getProductCraft() {
		return productCraft;
	}

	public void setProductCraft(String productCraft) {
		this.productCraft = productCraft;
	}

	public Timestamp getPlanStartTime() {
		return planStartTime;
	}

	public void setPlanStartTime(Timestamp planStartTime) {
		this.planStartTime = planStartTime;
	}

	public Timestamp getFactStartTime() {
		return factStartTime;
	}

	public void setFactStartTime(Timestamp factStartTime) {
		this.factStartTime = factStartTime;
	}

	public Timestamp getPlanEndTime() {
		return planEndTime;
	}

	public void setPlanEndTime(Timestamp planEndTime) {
		this.planEndTime = planEndTime;
	}

	public Timestamp getFactEndTime() {
		return factEndTime;
	}

	public void setFactEndTime(Timestamp factEndTime) {
		this.factEndTime = factEndTime;
	}

	public Timestamp getPlanIncomingTime() {
		return planIncomingTime;
	}

	public Timestamp getFactIncomingTime() {
		return factIncomingTime;
	}

	public void setFactIncomingTime(Timestamp factIncomingTime) {
		this.factIncomingTime = factIncomingTime;
	}

	public String getIncomingAxis() {
		return incomingAxis;
	}

	public void setIncomingAxis(String incomingAxis) {
		this.incomingAxis = incomingAxis;
	}

	public String getSeqCode() {
		return seqCode;
	}

	public void setSeqCode(String seqCode) {
		this.seqCode = seqCode;
	}

	public String getSeqSemiProductAxis() {
		return seqSemiProductAxis;
	}

	public void setSeqSemiProductAxis(String seqSemiProductAxis) {
		this.seqSemiProductAxis = seqSemiProductAxis;
	}

	public Timestamp getPlanSendNextSeqDate() {
		return planSendNextSeqDate;
	}

	public void setPlanSendNextSeqDate(Timestamp planSendNextSeqDate) {
		this.planSendNextSeqDate = planSendNextSeqDate;
	}

	public Timestamp getFactSendNextSeqDate() {
		return factSendNextSeqDate;
	}

	public void setFactSendNextSeqDate(Timestamp factSendNextSeqDate) {
		this.factSendNextSeqDate = factSendNextSeqDate;
	}

	public String getUpSeqCode() {
		return upSeqCode;
	}

	public void setUpSeqCode(String upSeqCode) {
		this.upSeqCode = upSeqCode;
	}

	public String getNextSeqCode() {
		return nextSeqCode;
	}

	public void setNextSeqCode(String nextSeqCode) {
		this.nextSeqCode = nextSeqCode;
	}

	public String getNextSeqMachineCode() {
		return nextSeqMachineCode;
	}

	public void setNextSeqMachineCode(String nextSeqMachineCode) {
		this.nextSeqMachineCode = nextSeqMachineCode;
	}

	public BigDecimal getSemiProductLen() {
		return semiProductLen;
	}

	public void setSemiProductLen(BigDecimal semiProductLen) {
		this.semiProductLen = semiProductLen;
	}

	public Integer getReadyTime() {
		return readyTime;
	}

	public void setReadyTime(Integer readyTime) {
		this.readyTime = readyTime;
	}

	public Integer getProductTime() {
		return productTime;
	}

	public void setProductTime(Integer productTime) {
		this.productTime = productTime;
	}

	public BigDecimal getProductSpeed() {
		return productSpeed;
	}

	public void setProductSpeed(BigDecimal productSpeed) {
		this.productSpeed = productSpeed;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	/**
	 * @return  courseCode
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 *  @param courseCode 要设置的 courseCode 
	 *    
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 *  @param planIncomingTime 要设置的 planIncomingTime 
	 *    
	 */
	public void setPlanIncomingTime(Timestamp planIncomingTime) {
		this.planIncomingTime = planIncomingTime;
	}

	public Integer getMachinePlanId() {
		return machinePlanId;
	}

	public void setMachinePlanId(Integer machinePlanId) {
		this.machinePlanId = machinePlanId;
	}

	public String getVirtualRfid() {
		return virtualRfid;
	}

	public void setVirtualRfid(String virtualRfid) {
		this.virtualRfid = virtualRfid;
	}

	public String getVirtualRfidIncome() {
		return virtualRfidIncome;
	}

	public void setVirtualRfidIncome(String virtualRfidIncome) {
		this.virtualRfidIncome = virtualRfidIncome;
	}

	public BigDecimal getProductSpeedAvg() {
		return productSpeedAvg;
	}

	public void setProductSpeedAvg(BigDecimal productSpeedAvg) {
		this.productSpeedAvg = productSpeedAvg;
	}
	
	
}
