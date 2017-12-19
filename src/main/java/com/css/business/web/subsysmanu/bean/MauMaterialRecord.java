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
@Table(name = "mau_material_record")
public class MauMaterialRecord implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -1038277522150849746L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_mau_material_record", allocationSize = 1, initialValue = 1, sequenceName = "seq_mau_material_record")
	@GeneratedValue(generator = "seq_mau_material_record", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "mater_manage_by")
	private String matermanageBy;
	@Column(name = "mmr_code")
	private String mmrCode;

	@Column(name = "mater_manage_time")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date matermanageTime;

	@Column(name = "doc_type")
	private String docType;
	@Column(name = "ws_code")
	private String wsCode;
	@Column(name = "remark")
	private String remark;

	// @Column(name = "mac_code")
	// private String macCode;
	@Column(name = "work_day")
	private Integer workDay;
	@Column(name = "mmr_state")
	private String mmrState;
	@Column(name = "is_over")
	private String isOver;
	@Column(name = "time_start")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp timeStart;
	@Column(name = "time_end")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp timeEnd;
	@Transient
	private String startDateVo;
	@Transient
	private String endDateVo;

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

	public String getMatermanageBy() {
		return matermanageBy;
	}

	public void setMatermanageBy(String matermanageBy) {
		this.matermanageBy = matermanageBy;
	}

	public String getMmrCode() {
		return mmrCode;
	}

	public void setMmrCode(String mmrCode) {
		this.mmrCode = mmrCode;
	}

	public Date getMatermanageTime() {
		return matermanageTime;
	}

	public void setMatermanageTime(Date matermanageTime) {
		this.matermanageTime = matermanageTime;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getWsCode() {
		return wsCode;
	}

	public void setWsCode(String wsCode) {
		this.wsCode = wsCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/*
	 * public String getMacCode() { return macCode; } public void
	 * setMacCode(String macCode) { this.macCode = macCode; }
	 */
	public Integer getWorkDay() {
		return workDay;
	}

	public void setWorkDay(Integer workDay) {
		this.workDay = workDay;
	}

	public String getMmrState() {
		return mmrState;
	}

	public void setMmrState(String mmrState) {
		this.mmrState = mmrState;
	}

	public String getIsOver() {
		return isOver;
	}

	public void setIsOver(String isOver) {
		this.isOver = isOver;
	}

	public Timestamp getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Timestamp timeStart) {
		this.timeStart = timeStart;
	}

	public Timestamp getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Timestamp timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getStartDateVo() {
		return startDateVo;
	}

	public void setStartDateVo(String startDateVo) {
		this.startDateVo = startDateVo;
	}

	public String getEndDateVo() {
		return endDateVo;
	}

	public void setEndDateVo(String endDateVo) {
		this.endDateVo = endDateVo;
	}

}
