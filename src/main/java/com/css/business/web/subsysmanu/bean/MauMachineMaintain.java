package com.css.business.web.subsysmanu.bean;

import java.sql.Timestamp;
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
@Table(name = "MAU_MACHINE_MAINTAIN")
public class MauMachineMaintain implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 6595414412047682652L;
	
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_mau_machine_maintain", sequenceName = "seq_mau_machine_maintain")  
	@GeneratedValue(generator = "seq_mau_machine_maintain", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name="start_maintain_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp startMaintainDate; 
	@Column(name = "mac_code")
	private String macCode;
	@Column(name = "maintain_by")
	private String maintainBy;
	@Column(name="end_maintain_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp endMaintainDate; 
	@Column(name="remark")
	private  String remark;
	@Column(name="fact_maintain_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp factMaintainDate; 
	/**
	 * 机台维护状态
	 */
	@Column(name="status")
	private String status;
	@Column(name="mac_type")
	private String macType;
	
	

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

	

	public Timestamp getStartMaintainDate() {
		return startMaintainDate;
	}

	public void setStartMaintainDate(Timestamp startMaintainDate) {
		this.startMaintainDate = startMaintainDate;
	}

	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	public String getMaintainBy() {
		return maintainBy;
	}

	public void setMaintainBy(String maintainBy) {
		this.maintainBy = maintainBy;
	}

	public Timestamp getEndMaintainDate() {
		return endMaintainDate;
	}

	public void setEndMaintainDate(Timestamp endMaintainDate) {
		this.endMaintainDate = endMaintainDate;
	}

	public String getStatus() {
		if("0".equals(status)){
			status="未开始";
		}else if("1".equals(status)){
			status="进行中";
		}else if("2".equals(status)){
			status="已完成";
		}
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMacType() {
		return macType;
	}

	public void setMacType(String macType) {
		this.macType = macType;
	}

	public Timestamp getFactMaintainDate() {
		return factMaintainDate;
	}

	public void setFactMaintainDate(Timestamp factMaintainDate) {
		this.factMaintainDate = factMaintainDate;
	}


	
	

	
	
	
	

}
