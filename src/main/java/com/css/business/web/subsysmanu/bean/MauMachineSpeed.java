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
@Table(name = "MAU_MACHINE_SPEED")
@SequenceGenerator(name = "SEQ_MAU_MACHINE_SPEED", sequenceName = "SEQ_MAU_MACHINE_SPEED")
public class MauMachineSpeed implements BaseEntity {

	
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 436100514002394946L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "machine_id")
	private Integer machineId;
	@Column(name = "pro_ggxh")
	private String proGgxh;
	@Column(name = "speed")
	private Double speed;
	@Column(name = "unit")
	private String unit;
	@Column(name = "ready_time")
	private Double readyTime;
	@Column(name = "up_time")
	private Double upTime;
	@Column(name = "down_time")
	private Double downTime;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMachineId() {
		return machineId;
	}

	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getReadyTime() {
		return readyTime;
	}

	public void setReadyTime(Double readyTime) {
		this.readyTime = readyTime;
	}

	public Double getUpTime() {
		return upTime;
	}

	public void setUpTime(Double upTime) {
		this.upTime = upTime;
	}

	public Double getDownTime() {
		return downTime;
	}

	public void setDownTime(Double downTime) {
		this.downTime = downTime;
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
