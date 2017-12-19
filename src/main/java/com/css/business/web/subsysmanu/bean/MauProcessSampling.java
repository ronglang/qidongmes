package com.css.business.web.subsysmanu.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "mau_process_samping")
// @SequenceGenerator(name="SEQ_MAU_PROCESS_SAMPLING",sequenceName="SEQ_MAU_PROCESS_SAMPLING")
public class MauProcessSampling implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 4688620164783866797L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "mps_code")
	private String mpsCode;
	@Column(name = "seq_code")
	private String seqCode;
	@Column(name = "ws_code")
	private String wsCode;
	@Column(name = "axis_number")
	private String axisNumber;
	@Column(name = "is_qualified")
	private String isQualified;
	@Column(name = "mac_code")
	private Date macCode;
	@Column(name = "sampl_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date samplDate;


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

	public Date getSamplDate() {
		return samplDate;
	}

	public void setSamplDate(Date samplDate) {
		this.samplDate = samplDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getMpsCode() {
		return mpsCode;
	}

	public void setMpsCode(String mpsCode) {
		this.mpsCode = mpsCode;
	}

	public String getSeqCode() {
		return seqCode;
	}

	public void setSeqCode(String seqCode) {
		this.seqCode = seqCode;
	}

	public String getWsCode() {
		return wsCode;
	}

	public void setWsCode(String wsCode) {
		this.wsCode = wsCode;
	}

	public String getAxisNumber() {
		return axisNumber;
	}

	public void setAxisNumber(String axisNumber) {
		this.axisNumber = axisNumber;
	}

	public String getIsQualified() {
		return isQualified;
	}

	public void setIsQualified(String isQualified) {
		this.isQualified = isQualified;
	}

	public Date getMacCode() {
		return macCode;
	}

	public void setMacCode(Date macCode) {
		this.macCode = macCode;
	}
	
	

}
