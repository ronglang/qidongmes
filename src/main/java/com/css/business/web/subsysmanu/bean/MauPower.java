package com.css.business.web.subsysmanu.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.css.common.web.syscommon.bean.BaseEntity;

@Entity
@Table(name = "mau_power")
public class MauPower implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -4128451034815296663L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "create_date")
	private Timestamp createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "mp_code")
	private String mpCode;
	@Column(name = "mac_code")
	private String macCode;
	@Column(name = "start_elec")
	private Float startElec;
	@Column(name = "end_elec")
	private Float endElec;
	@Column(name = "elec")
	private Float elec;
	@Column(name = "ws_code")
	private String wsCode;
	@Column(name = "employee")
	private String employee;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Float getStartElec() {
		return startElec;
	}

	public void setStartElec(Float startElec) {
		this.startElec = startElec;
	}

	public Float getEndElec() {
		return endElec;
	}

	public void setEndElec(Float endElec) {
		this.endElec = endElec;
	}

	public Float getElec() {
		return elec;
	}

	public void setElec(Float elec) {
		this.elec = elec;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getMpCode() {
		return mpCode;
	}

	public void setMpCode(String mpCode) {
		this.mpCode = mpCode;
	}

	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	public String getWsCode() {
		return wsCode;
	}

	public void setWsCode(String wsCode) {
		this.wsCode = wsCode;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

}
