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

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "PLA_TASK")
@SequenceGenerator(name = "SEQ_PLA_TASK", sequenceName = "SEQ_PLA_TASK")
public class PlaTask implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -3865287040946476507L;
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
	@Column(name = "person_id")
	private Integer personId;
	@Column(name = "ws_code")
	private String wsCode;
	@Column(name = "start_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date startTime;
	@Column(name = "end_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date endTime;
	@Column(name = "mac_id")
	private Integer macId;
	@Column(name = "plan_detail_id")
	private Integer planDetailId;
	@Column(name = "plan_amount")
	private Integer planAmount;
	@Column(name = "finish_amount")
	private Integer finishAmount;
	@Column(name = "seq_current")
	private String seqCurrent;
	@Column(name = "craft_current")
	private String craftCurrent;
	@Column(name = "date_current")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date dateCurrent;
	@Column(name = "is_inuse")
	private String isInuse;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
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

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getMacId() {
		return macId;
	}

	public void setMacId(Integer macId) {
		this.macId = macId;
	}

	public Integer getPlanDetailId() {
		return planDetailId;
	}

	public void setPlanDetailId(Integer planDetailId) {
		this.planDetailId = planDetailId;
	}

	public String getWsCode() {
		return wsCode;
	}

	public void setWsCode(String wsCode) {
		this.wsCode = wsCode;
	}

	public Integer getPlanAmount() {
		return planAmount;
	}

	public void setPlanAmount(Integer planAmount) {
		this.planAmount = planAmount;
	}

	public Integer getFinishAmount() {
		return finishAmount;
	}

	public void setFinishAmount(Integer finishAmount) {
		this.finishAmount = finishAmount;
	}

	public String getSeqCurrent() {
		return seqCurrent;
	}

	public void setSeqCurrent(String seqCurrent) {
		this.seqCurrent = seqCurrent;
	}

	public String getCraftCurrent() {
		return craftCurrent;
	}

	public void setCraftCurrent(String craftCurrent) {
		this.craftCurrent = craftCurrent;
	}

	public String getIsInuse() {
		return isInuse;
	}

	public void setIsInuse(String isInuse) {
		this.isInuse = isInuse;
	}

	public Date getDateCurrent() {
		return dateCurrent;
	}

	public void setDateCurrent(Date dateCurrent) {
		this.dateCurrent = dateCurrent;
	}

}
