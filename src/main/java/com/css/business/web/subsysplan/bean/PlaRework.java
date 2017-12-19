package com.css.business.web.subsysplan.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "PLA_REWORK")
@SequenceGenerator(name = "SEQ_PLA_REWORK", sequenceName = "SEQ_PLA_REWORK")
public class PlaRework implements BaseEntity {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -8196156909156057673L;
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "abnormal_id")
	private Integer abnormalId;
	@Column(name = "machine_plan_schedule_id")
	private Integer machinePlanScheduleId;
	@Column(name = "machine_plan_id")
	private Integer machinePlanId;
	@Column(name = "week_plan_id")
	private Integer weekPlanId;
	@Column(name = "abnormal_axis_code")
	private String abnormalAxisCode;
	@Column(name = "abnormal_seq")
	private String abnormalSeq;
	@Column(name = "new_machine_plan_id")
	private Integer newMachinePlanId;
	@Column(name = "schedule_state")
	private String scheduleState;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAbnormalId() {
		return abnormalId;
	}

	public void setAbnormalId(Integer abnormalId) {
		this.abnormalId = abnormalId;
	}

	public Integer getMachinePlanScheduleId() {
		return machinePlanScheduleId;
	}

	public void setMachinePlanScheduleId(Integer machinePlanScheduleId) {
		this.machinePlanScheduleId = machinePlanScheduleId;
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

	public Integer getNewMachinePlanId() {
		return newMachinePlanId;
	}

	public void setNewMachinePlanId(Integer newMachinePlanId) {
		this.newMachinePlanId = newMachinePlanId;
	}

	public String getAbnormalAxisCode() {
		return abnormalAxisCode;
	}

	public void setAbnormalAxisCode(String abnormalAxisCode) {
		this.abnormalAxisCode = abnormalAxisCode;
	}

	public String getAbnormalSeq() {
		return abnormalSeq;
	}

	public void setAbnormalSeq(String abnormalSeq) {
		this.abnormalSeq = abnormalSeq;
	}

	public String getScheduleState() {
		return scheduleState;
	}

	public void setScheduleState(String scheduleState) {
		this.scheduleState = scheduleState;
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
	
}
