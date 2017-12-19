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
@Table(name = "PLA_MAC_TYPE")
@SequenceGenerator(name = "SEQ_PLA_MAC_TYPE", sequenceName = "SEQ_PLA_MAC_TYPE")
@Deprecated
public class PlaMacType implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 2023754151619539847L;
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
	@Column(name = "mac_name")
	private String macName;
	@Column(name = "mac_state")
	private String macState;
	@Column(name = "perfm_indicators")
	private String perfmIndicators;
	@Column(name = "seq_id")
	private Integer seqId;
	@Column(name = "task_amount")
	private Integer taskAmount;
	@Column(name = "predict_repair_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date predictRepairTime;
	@Column(name = "mac_code")
	private String macCode;

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

	public Integer getSeqId() {
		return seqId;
	}

	public void setSeqId(Integer seqId) {
		this.seqId = seqId;
	}

	
	public Integer getTaskAmount() {
		return taskAmount;
	}

	public void setTaskAmount(Integer taskAmount) {
		this.taskAmount = taskAmount;
	}

	public Date getPredictRepairTime() {
		return predictRepairTime;
	}

	public void setPredictRepairTime(Date predictRepairTime) {
		this.predictRepairTime = predictRepairTime;
	}

	public String getMacName() {
		return macName;
	}

	public void setMacName(String macName) {
		this.macName = macName;
	}

	public String getMacState() {
		return macState;
	}

	public void setMacState(String macState) {
		this.macState = macState;
	}

	public String getPerfmIndicators() {
		return perfmIndicators;
	}

	public void setPerfmIndicators(String perfmIndicators) {
		this.perfmIndicators = perfmIndicators;
	}

	/**
	 * @return macCode
	 */
	public String getMacCode() {
		return macCode;
	}

	/**
	 * @param macCode
	 *            要设置的 macCode
	 * 
	 */
	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

}
