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
@Table(name = "PLA_SINGLE_MOVE_HANDLE_DETAIL")
@SequenceGenerator(name = "SEQ_PLA_SINGLE_MOVE_HANDLE_DETAIL", sequenceName = "SEQ_PLA_SINGLE_MOVE_HANDLE_DETAIL")
public class PlaSingleMoveHandleDetail implements BaseEntity {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1658640176585136994L;
	@Id
	@Column(name  ="id")
	private Integer id;
	@Column(name = "single_move_id")
	private Integer singleMoveId;
	@Column(name = "new_week_plan_id")
	private Integer newWeekPlanId;
	@Column(name = "new_machine_plan_id")
	private Integer newMachinePlanId;
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

	public Integer getSingleMoveId() {
		return singleMoveId;
	}

	public void setSingleMoveId(Integer singleMoveId) {
		this.singleMoveId = singleMoveId;
	}

	public Integer getNewWeekPlanId() {
		return newWeekPlanId;
	}

	public void setNewWeekPlanId(Integer newWeekPlanId) {
		this.newWeekPlanId = newWeekPlanId;
	}

	public Integer getNewMachinePlanId() {
		return newMachinePlanId;
	}

	public void setNewMachinePlanId(Integer newMachinePlanId) {
		this.newMachinePlanId = newMachinePlanId;
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
