package com.css.business.web.subsysmanu.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @TODO : 机台、人员负荷表
 * @author: 翟春磊
 * @DATE : 2017年8月8日
 */
@Entity
@Table(name = "mau_task_load")
public class MauTaskLoad implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -324708680797220146L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_mau_task_load", allocationSize = 1, initialValue = 1, sequenceName = "seq_mau_task_load")
	@GeneratedValue(generator = "seq_mau_task_load", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "type")
	private String type;
	@Column(name = "obj_id")
	private Integer objId;
	@Column(name = "work_day")
	private Integer workDay;
	@Column(name = "hours")
	private BigDecimal hours;
	@Column(name = "status")
	private String status;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	// @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "mac_code")
	private String macCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	public Integer getWorkDay() {
		return workDay;
	}

	public void setWorkDay(Integer workDay) {
		this.workDay = workDay;
	}

	public BigDecimal getHours() {
		return hours;
	}

	public void setHours(BigDecimal hours) {
		this.hours = hours;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

}
