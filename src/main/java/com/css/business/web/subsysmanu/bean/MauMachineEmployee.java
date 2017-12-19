package com.css.business.web.subsysmanu.bean;

/**
 * 设备故障信息记录表
 */

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
@Table(name = "mau_machine_employee")
public class MauMachineEmployee implements BaseEntity {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -2938969540279631804L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_mau_machine_employee", sequenceName = "seq_mau_machine_employee")
	@GeneratedValue(generator = "seq_mau_machine_employee", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "mac_code")
	private String macCode;
	@Column(name = "emp_rfid")
	private String empRfid; // 人员RFID
	@Column(name = "test_time")
	private Float testTime; // 测试用时
	@Column(name="test_len")
	private Float testLen;
	@Column(name = "ave_speed")
	private Float aveSpeed; // 平均速度
	@Column(name = "pro_ggxh")
	private String proGgxh;
	@Column(name = "diameter")
	private Float diameter;

	public MauMachineEmployee() {
		aveSpeed = 0.0f;
	}

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

	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	public String getEmpRfid() {
		return empRfid;
	}

	public void setEmpRfid(String empRfid) {
		this.empRfid = empRfid;
	}

	public Float getTestTime() {
		return testTime;
	}

	public void setTestTime(Float testTime) {
		this.testTime = testTime;
	}

	public Float getAveSpeed() {
		return aveSpeed;
	}

	public void setAveSpeed(Float aveSpeed) {
		this.aveSpeed = aveSpeed;
	}

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	public Float getDiameter() {
		return diameter;
	}

	public void setDiameter(Float diameter) {
		this.diameter = diameter;
	}

	public Float getTestLen() {
		return testLen;
	}

	public void setTestLen(Float testLen) {
		this.testLen = testLen;
	}

}
