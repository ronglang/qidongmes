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

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 收线数据信息
 * @author TG
 *
 */
@Entity
@Table(name = "pla_takeup")
public class PlaTakeup implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -4981068967752825593L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_pla_takeup", sequenceName = "seq_pla_takeup")
	@GeneratedValue(generator = "seq_pla_takeup", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "work_code")
	private String workCode;
	@Column(name = "mac_code")
	private String macCode;
	@Column(name = "axis_code")
	private String axisCode;
	@Column(name = "seq_code")
	private String seqCode;
	
	@Column(name = "sx_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date sxTime;
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
	public String getWorkCode() {
		return workCode;
	}
	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}
	public String getMacCode() {
		return macCode;
	}
	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}
	public String getAxisCode() {
		return axisCode;
	}
	public void setAxisCode(String axisCode) {
		this.axisCode = axisCode;
	}
	public String getSeqCode() {
		return seqCode;
	}
	public void setSeqCode(String seqCode) {
		this.seqCode = seqCode;
	}
	public Date getSxTime() {
		return sxTime;
	}
	public void setSxTime(Date sxTime) {
		this.sxTime = sxTime;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
	
}
