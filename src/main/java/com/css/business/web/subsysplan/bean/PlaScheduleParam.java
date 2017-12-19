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

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "PLA_SCHEDULE_PARAM")
@SequenceGenerator(name = "SEQ_PLA_SCHEDULE_PARAM", sequenceName = "SEQ_PLA_SCHEDULE_PARAM")
public class PlaScheduleParam implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5394530107597064805L;
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "param_name")
	private String param_name;
	@Column(name = "param_value")
	private String param_value;
	@Column(name = "create_by")
	private String create_by;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "machine_schedule_id")
	private Integer machineScheduleId;
	@Column(name = "week_plan_id")
	private Integer weekPlanId;

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

	public Integer getMachineScheduleId() {
		return machineScheduleId;
	}

	public void setMachineScheduleId(Integer machineScheduleId) {
		this.machineScheduleId = machineScheduleId;
	}

	public Integer getWeekPlanId() {
		return weekPlanId;
	}

	public void setWeekPlanId(Integer weekPlanId) {
		this.weekPlanId = weekPlanId;
	}

}
