package com.css.business.web.subsysmanu.bean;

import java.math.BigDecimal;
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
@Table(name = "mau_call_forklift_record")
public class MauCallForkliftRecord implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -3618403428598782180L;
	@Transient
	public static final String NO_status = "N";
	@Transient
	public static final String YES_status = "Y";
	// seq_mau_call_forklift_record
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_mau_call_forklift_record", allocationSize = 1, initialValue = 1, sequenceName = "seq_mau_call_forklift_record")
	@GeneratedValue(generator = "seq_mau_call_forklift_record", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	// @Column(name = "mcfr_code")
	// private String mcfrCode;
	private String callTime;
	@Column(name = "reply_by")
	private String replyBy;
	@Column(name = "reply_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date replyTime;
	@Column(name = "dest_address")
	private String destAddress;
	@Column(name = "course_code")
	private String courseCode;
	@Column(name = "status")
	private String status;
	@Column(name = "pro_ggxh")
	private String proGgxh;
	@Column(name = "account")
	private BigDecimal account;
	@Column(name = "unit")
	private String unit;
	@Column(name = "call_address")
	private String callAddress;
	@Column(name = "rfid_code")
	private String rfidCode;
	@Column(name = "call_rfid")
	private String callRfid;
	@Column(name = "dest_rfid")
	private String destRfid;
	@Column(name = "f_type")
	private String fType;

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

	public String getCallTime() {
		return callTime;
	}

	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getReplyBy() {
		return replyBy;
	}

	public void setReplyBy(String replyBy) {
		this.replyBy = replyBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getDestAddress() {
		return destAddress;
	}

	public void setDestAddress(String destAddress) {
		this.destAddress = destAddress;
	}

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCallAddress() {
		return callAddress;
	}

	public void setCallAddress(String callAddress) {
		this.callAddress = callAddress;
	}

	public String getRfidCode() {
		return rfidCode;
	}

	public void setRfidCode(String rfidCode) {
		this.rfidCode = rfidCode;
	}

	public String getCallRfid() {
		return callRfid;
	}

	public void setCallRfid(String callRfid) {
		this.callRfid = callRfid;
	}

	public String getDestRfid() {
		return destRfid;
	}

	public void setDestRfid(String destRfid) {
		this.destRfid = destRfid;
	}

	public String getfType() {
		return fType;
	}

	public void setfType(String fType) {
		this.fType = fType;
	}

}
