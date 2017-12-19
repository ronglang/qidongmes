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

/**
 * 
 * rfid卡号基础数据管理实体类
 * */
@Entity
@Table(name = "mau_rfid")
@SequenceGenerator(name = "seq_mau_rfid", sequenceName = "seq_mau_rfid")
public class MauRfid implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 325442212297213998L;
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "seq_mau_rfid", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "rfid_no")
	private String rfidNO;
	@Column(name = "status")
	private String status;
	@Column(name = "app_id")
	private String appId;
	@Column(name = "material_type")
	private String materialType;
	@Column(name="amount")
	private Integer amount;
	@Column(name="six_rfid_no")
	private String sixRfidNo;
	
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
	public String getRfidNO() {
		return rfidNO;
	}
	public void setRfidNO(String rfidNO) {
		this.rfidNO = rfidNO;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getMaterialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getSixRfidNo() {
		return sixRfidNo;
	}
	public void setSixRfidNo(String sixRfidNo) {
		this.sixRfidNo = sixRfidNo;
	}
	


	

}
