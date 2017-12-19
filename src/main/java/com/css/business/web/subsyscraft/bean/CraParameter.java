package com.css.business.web.subsyscraft.bean;

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
@Table(name = "cra_parameter")
@SequenceGenerator(name = "seq_cra_parameter", sequenceName = "seq_cra_parameter")
public class CraParameter implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -561673182872311317L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="cp_code")
	private String cp_code;
	@Column(name="c_code")
	private String c_code;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "param_vaule")
	private String paramVaule;
	@Column(name = "param_name")
	private String paramName;
	@Column(name = "param_min")
	private String paramMin;
	@Column(name = "param_max")
	private String paramMax;
	@Column(name  ="unit")
	private String unit;

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

	public String getCp_code() {
		return cp_code;
	}

	public void setCp_code(String cp_code) {
		this.cp_code = cp_code;
	}

	public String getC_code() {
		return c_code;
	}

	public void setC_code(String c_code) {
		this.c_code = c_code;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getParamVaule() {
		return paramVaule;
	}

	public void setParamVaule(String paramVaule) {
		this.paramVaule = paramVaule;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamMin() {
		return paramMin;
	}

	public void setParamMin(String paramMin) {
		this.paramMin = paramMin;
	}

	public String getParamMax() {
		return paramMax;
	}

	public void setParamMax(String paramMax) {
		this.paramMax = paramMax;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
