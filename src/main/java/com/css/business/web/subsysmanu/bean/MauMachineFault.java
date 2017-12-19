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
@Table(name = "MAU_MACHINE_FAULT")
public class MauMachineFault implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 6595414412047682652L;
	
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_mau_machine_fault", sequenceName = "seq_mau_machine_fault")  
	@GeneratedValue(generator = "seq_mau_machine_fault", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "mmf_code")
	private String mmfCode;
	@Column(name = "fault_type")
	private String faultType;
	@Column(name="fault_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date faultDate; 
	@Column(name = "mac_code")
	private String macCode;
	@Column(name = "remark")
	private String remark;
	@Column(name = "repair_by")
	private String repairBy;
	@Column(name="repair_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date repairDate; 
	/**
	 * 设备维修状态(0:未开始，1：进行中，2:已完成)
	 */
	@Column(name="status")
	private String status;
	
	//设备类型（机台或叉车）
	@Column(name="mac_type")
	private String macType;
	

	public String getMacType() {
		return macType;
	}

	public void setMacType(String macType) {
		this.macType = macType;
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

	public String getMmfCode() {
		return mmfCode;
	}

	public void setMmfCode(String mmfCode) {
		this.mmfCode = mmfCode;
	}

	public String getFaultType() {
		return faultType;
	}

	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}

	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRepairBy() {
		return repairBy;
	}

	public void setRepairBy(String repairBy) {
		this.repairBy = repairBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status=status;
	}

	public Date getFaultDate() {
		return faultDate;
	}

	public void setFaultDate(Date faultDate) {
		this.faultDate = faultDate;
	}

	public Date getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(Date repairDate) {
		this.repairDate = repairDate;
	}

	
	
	
	
	

}
