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
@Table(name = "Mau_Axis_Mac")
public class MauAxisMac implements BaseEntity {
	@Transient
	public static String AXIS_TYPE_IN = "放线轴";
	@Transient
	public static String AXIS_TYPE_OUT = "出线轴";
	@Transient
	private static final long serialVersionUID = -4833652462806978305L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_Mau_Axis_Mac", allocationSize = 1, initialValue = 1, sequenceName = "seq_Mau_Axis_Mac")
	@GeneratedValue(generator = "seq_Mau_Axis_Mac", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;

	@Column(name = "mac_code")
	private String macCode;
	@Column(name = "axis_name")
	private String axisName;
	@Column(name = "axis_type")
	private String axisType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getAxisName() {
		return axisName;
	}

	public void setAxisName(String axisName) {
		this.axisName = axisName;
	}

	public String getAxisType() {
		return axisType;
	}

	public void setAxisType(String axisType) {
		this.axisType = axisType;
	}

}
