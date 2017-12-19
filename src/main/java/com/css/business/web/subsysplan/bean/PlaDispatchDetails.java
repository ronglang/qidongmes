package com.css.business.web.subsysplan.bean;

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

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "PLA_DISPATCH_DETAILS")
@SequenceGenerator(name = "SEQ_PLA_DISPATCH_DETAILS", sequenceName = "SEQ_PLA_DISPATCH_DETAILS")
public class PlaDispatchDetails implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 2993005533876544178L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "remark")
	private String remark;
	@Column(name = "old_plan_seq")
	private Integer oldPlanSeq;
	@Column(name = "old_plan_mac")
	private Integer oldPlanMac;
	@Column(name = "old_plan_amount")
	private Integer oldPlanAmount;
	@Column(name = "old_complete_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date oldCompleteTime;
	@Column(name = "new_plan_seq")
	private Integer newPlanSeq;
	@Column(name = "new_plan_mac")
	private Integer newPlanMac;
	@Column(name = "new_plan_amount")
	private Integer newPlanAmount;
	@Column(name = "new_end_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date newEndTime;
	@Column(name = "unit")
	private String unit;
	@Column(name = "old_start_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date oldStartTime;
	@Column(name = "new_start_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date newStartTime;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Integer getOldPlanSeq() {
		return oldPlanSeq;
	}

	public void setOldPlanSeq(Integer oldPlanSeq) {
		this.oldPlanSeq = oldPlanSeq;
	}

	public Integer getOldPlanMac() {
		return oldPlanMac;
	}

	public void setOldPlanMac(Integer oldPlanMac) {
		this.oldPlanMac = oldPlanMac;
	}

	public Integer getOldPlanAmount() {
		return oldPlanAmount;
	}

	public void setOldPlanAmount(Integer oldPlanAmount) {
		this.oldPlanAmount = oldPlanAmount;
	}

	public Date getOldCompleteTime() {
		return oldCompleteTime;
	}

	public void setOldCompleteTime(Date oldCompleteTime) {
		this.oldCompleteTime = oldCompleteTime;
	}

	public Integer getNewPlanSeq() {
		return newPlanSeq;
	}

	public void setNewPlanSeq(Integer newPlanSeq) {
		this.newPlanSeq = newPlanSeq;
	}

	public Integer getNewPlanMac() {
		return newPlanMac;
	}

	public void setNewPlanMac(Integer newPlanMac) {
		this.newPlanMac = newPlanMac;
	}

	public Integer getNewPlanAmount() {
		return newPlanAmount;
	}

	public void setNewPlanAmount(Integer newPlanAmount) {
		this.newPlanAmount = newPlanAmount;
	}

	public Date getNewEndTime() {
		return newEndTime;
	}

	public void setNewEndTime(Date newEndTime) {
		this.newEndTime = newEndTime;
	}

	public Date getOldStartTime() {
		return oldStartTime;
	}

	public void setOldStartTime(Date oldStartTime) {
		this.oldStartTime = oldStartTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getNewStartTime() {
		return newStartTime;
	}

	public void setNewStartTime(Date newStartTime) {
		this.newStartTime = newStartTime;
	}
	
	

}
