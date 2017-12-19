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
@Table(name = "mau_process_temp")
 @SequenceGenerator(name="SEQ_MAU_PROCESS_TEMP",sequenceName="SEQ_MAU_PROCESS_TEMP")
public class MauProcessTemp implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 4263679184000647555L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name="SEQ_MAU_PROCESS_TEMP",sequenceName="SEQ_MAU_PROCESS_TEMP")
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	
	@Column(name = "mac_remark")
	private String mac_remark;
	@Column(name = "rfidnumber")
	private String rfidnumber;
	@Column(name = "type")
	private String type;
	@Column(name = "save_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date savedate;
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
	public String getMac_remark() {
		return mac_remark;
	}
	public void setMac_remark(String mac_remark) {
		this.mac_remark = mac_remark;
	}
	public String getRfidnumber() {
		return rfidnumber;
	}
	public void setRfidnumber(String rfidnumber) {
		this.rfidnumber = rfidnumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getSavedate() {
		return savedate;
	}
	public void setSavedate(Date savedate) {
		this.savedate = savedate;
	}
	
	


	
}
