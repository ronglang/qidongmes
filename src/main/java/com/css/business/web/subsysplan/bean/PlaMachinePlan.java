package com.css.business.web.subsysplan.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Table(name = "PLA_MACHINE_PLAN")
//@SequenceGenerator(name = "SEQ_PLA_MACHINE_PLAN", sequenceName = "SEQ_PLA_MACHINE_PLAN")
public class PlaMachinePlan implements BaseEntity {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 3005641631135962568L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_pla_machine_plan", sequenceName = "seq_pla_machine_plan")  
	@GeneratedValue(generator = "seq_pla_machine_plan", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "week_plan_id")
	private Integer weekPlanId;
	@Column(name = "work_date")
	// @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp workDate;
	@Column(name = "work_day")
	private Integer workDay;
	@Column(name = "machine_id")
	private Integer machineId;
	@Column(name = "employee_id")
	private Integer employeeId;
	@Column(name = "axis_name")
	private String axisName;
	@Column(name = "plan_start_time")
	// @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp planStartTime;
	@Column(name = "plan_end_time")
	// @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp planEndTime;
	@Column(name = "fact_start_time")
	// @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp factStartTime;
	@Column(name = "fact_end_time")
	// @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp factEndTime;
	@Column(name = "end_axis_code")
	private String endAxisCode;
	@Column(name = "route_code")
	private String routeCode;
	@Column(name = "part_len")
	private String partLen;
	@Column(name = "product_state")
	private String productState;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;

	@Column(name = "course_code")
	private String courseCode;
	@Column(name = "virtual_rfid")
	private String virtualRfid;
	@Column(name = "seq_code")
	private String seqCode;
	@Column(name = "sort")
	private Integer sort;
	@Column(name = "mac_code")
	private String macCode;
	@Column(name = "main_by")
	private String mainBy;
	@Column(name = "vice_by")
	private String viceBy;
	
	//工作日期
	@Transient
	private Long workDate_inLong;
	
	//计划开工时间
	@Transient
	private Long planStartTime_inLong;
	//计划结束时间
	@Transient
	private Long planEndTime_inLong;
	//计划材料
	@Transient
	private List<PlaMachinePlanMater> materList = new ArrayList<PlaMachinePlanMater>();

	
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

	public String getPartLen() {
		return partLen;
	}

	public void setPartLen(String partLen) {
		this.partLen = partLen;
	}

	public Timestamp getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Timestamp workDate) {
		this.workDate = workDate;
	}

	public String getAxisName() {
		return axisName;
	}

	public void setAxisName(String axisName) {
		this.axisName = axisName;
	}

	public Timestamp getPlanStartTime() {
		return planStartTime;
	}

	public void setPlanStartTime(Timestamp planStartTime) {
		this.planStartTime = planStartTime;
	}

	public Timestamp getPlanEndTime() {
		return planEndTime;
	}

	public void setPlanEndTime(Timestamp planEndTime) {
		this.planEndTime = planEndTime;
	}

	public Timestamp getFactStartTime() {
		return factStartTime;
	}

	public void setFactStartTime(Timestamp factStartTime) {
		this.factStartTime = factStartTime;
	}

	public Timestamp getFactEndTime() {
		return factEndTime;
	}

	public void setFactEndTime(Timestamp factEndTime) {
		this.factEndTime = factEndTime;
	}

	public String getEndAxisCode() {
		return endAxisCode;
	}

	public void setEndAxisCode(String endAxisCode) {
		this.endAxisCode = endAxisCode;
	}

	public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	public String getProductState() {
		return productState;
	}

	public void setProductState(String productState) {
		this.productState = productState;
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

	public List<PlaMachinePlanMater> getMaterList() {
		return materList;
	}

	public void setMaterList(List<PlaMachinePlanMater> materList) {
		this.materList = materList;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getVirtualRfid() {
		return virtualRfid;
	}

	public void setVirtualRfid(String virtualRfid) {
		this.virtualRfid = virtualRfid;
	}

	public String getSeqCode() {
		return seqCode;
	}

	public void setSeqCode(String seqCode) {
		this.seqCode = seqCode;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	public Long getWorkDate_inLong() {
		if(workDate == null )
			return null;
		else
			return workDate.getTime();
	}

	public void setWorkDate_inLong(Long workDate_inLong) {
		this.workDate_inLong = workDate_inLong;
	}

	public Long getPlanStartTime_inLong() {
		if(planStartTime == null)
			return null;
		else
			return planStartTime.getTime();
	}

	public void setPlanStartTime_inLong(Long planStartTime_inLong) {
		this.planStartTime_inLong = planStartTime_inLong;
	}

	public Long getPlanEndTime_inLong() {
		if(planEndTime == null)
			return null;
		else
			return planEndTime.getTime();
	}

	public void setPlanEndTime_inLong(Long planEndTime_inLong) {
		this.planEndTime_inLong = planEndTime_inLong;
	}

	public String getMainBy() {
		return mainBy;
	}

	public void setMainBy(String mainBy) {
		this.mainBy = mainBy;
	}

	public String getViceBy() {
		return viceBy;
	}

	public void setViceBy(String viceBy) {
		this.viceBy = viceBy;
	}

	public Integer getWorkDay() {
		return workDay;
	}

	public void setWorkDay(Integer workDay) {
		this.workDay = workDay;
	}
	
}
