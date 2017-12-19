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

@Entity
@Table(name = "PLA_MACHINE_DISPATCH")
@SequenceGenerator(name = "SEQ_PLA_MACHINE_DISPATCH", sequenceName = "SEQ_PLA_MACHINE_DISPATCH")
public class PlaMachineDispatch implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -2756096844383393656L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "code")
	private String code;
	@Column(name = "old_wsc")
	private String oldWsc;
	@Column(name = "new_wsc")
	private String newWsc;
	@Column(name = "old_wsc_details")
	private String oldWscDetails;
	@Column(name = "new_wsc_details")
	private String newWscDetails;
	@Column(name = "dispatch_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date dispatchTime;
	@Column(name = "old_wsc_code")
	private String oldWscCode;
	@Column(name = "new_wsc_code")
	private String newWscCode;
	@Column(name = "remark")
	private String remark;
	@Column(name = "old_seq_code")
	private String oldSeqCode;
	@Column(name = "new_seq_code")
	private String newSeqCode;
	@Column(name = "old_mac_code")
	private String oldMacCode;
	@Column(name = "new_mac_code")
	private String newMacCode;

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

	public Date getDispatchTime() {
		return dispatchTime;
	}

	public void setDispatchTime(Date dispatchTime) {
		this.dispatchTime = dispatchTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOldWsc() {
		return oldWsc;
	}

	public void setOldWsc(String oldWsc) {
		this.oldWsc = oldWsc;
	}

	public String getNewWsc() {
		return newWsc;
	}

	public void setNewWsc(String newWsc) {
		this.newWsc = newWsc;
	}

	public String getOldWscDetails() {
		return oldWscDetails;
	}

	public void setOldWscDetails(String oldWscDetails) {
		this.oldWscDetails = oldWscDetails;
	}

	public String getNewWscDetails() {
		return newWscDetails;
	}

	public void setNewWscDetails(String newWscDetails) {
		this.newWscDetails = newWscDetails;
	}

	public String getOldWscCode() {
		return oldWscCode;
	}

	public void setOldWscCode(String oldWscCode) {
		this.oldWscCode = oldWscCode;
	}

	public String getNewWscCode() {
		return newWscCode;
	}

	public void setNewWscCode(String newWscCode) {
		this.newWscCode = newWscCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOldSeqCode() {
		return oldSeqCode;
	}

	public void setOldSeqCode(String oldSeqCode) {
		this.oldSeqCode = oldSeqCode;
	}

	public String getNewSeqCode() {
		return newSeqCode;
	}

	public void setNewSeqCode(String newSeqCode) {
		this.newSeqCode = newSeqCode;
	}

	public String getOldMacCode() {
		return oldMacCode;
	}

	public void setOldMacCode(String oldMacCode) {
		this.oldMacCode = oldMacCode;
	}

	public String getNewMacCode() {
		return newMacCode;
	}

	public void setNewMacCode(String newMacCode) {
		this.newMacCode = newMacCode;
	}
	

}
