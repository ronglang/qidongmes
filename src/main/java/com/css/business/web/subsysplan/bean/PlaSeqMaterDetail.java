package com.css.business.web.subsysplan.bean;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
@Table(name = "PLA_SEQ_MATER_DETAIL")
@SequenceGenerator(name = "SEQ_PLA_SEQ_MATER_DETAIL", sequenceName = "SEQ_PLA_SEQ_MATER_DETAIL")
public class PlaSeqMaterDetail implements BaseEntity {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -3903067017694091241L;
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "product_order_id")
	private Integer productOrderId;
	@Column(name = "week_plan_id")
	private Integer weekPlanId;
	@Column(name = "work_date")
	//@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp workDate;
	@Column(name = "seq_code")
	private String seqCode;
	@Column(name = "c_code")
	private String cCode;
	@Column(name = "incoming_axis")
	private String incomingAxis;
	@Column(name = "incoming_long")
	private Integer incomingLong;
	@Column(name = "incoming_type")
	private String incomingType;
	@Column(name = "send_date")
	private Timestamp sendDate;
	@Column(name = "up_seq_code")
	private String upSeqCode;
	@Column(name = "up_seq_machine_code")
	private String upSeqMachineCode;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "course_code")
	private String courseCode;
	@Column(name = "plan_schedule_id")
	private Integer planScheduleId;
	@Column(name = "mater_type")
	private String materType; //来料类型
	@Column(name = "machine_plan_id")
	private Integer machinePlanId; //核心字段，机台计划ID
	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductOrderId() {
		return productOrderId;
	}

	public void setProductOrderId(Integer productOrderId) {
		this.productOrderId = productOrderId;
	}

	public Integer getWeekPlanId() {
		return weekPlanId;
	}

	public void setWeekPlanId(Integer weekPlanId) {
		this.weekPlanId = weekPlanId;
	}


	public Integer getIncomingLong() {
		return incomingLong;
	}

	public void setIncomingLong(Integer incomingLong) {
		this.incomingLong = incomingLong;
	}

	public Timestamp getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Timestamp workDate) {
		this.workDate = workDate;
	}

	public String getSeqCode() {
		return seqCode;
	}

	public void setSeqCode(String seqCode) {
		this.seqCode = seqCode;
	}

	public String getcCode() {
		return cCode;
	}

	public void setcCode(String cCode) {
		this.cCode = cCode;
	}

	public String getIncomingAxis() {
		return incomingAxis;
	}

	public void setIncomingAxis(String incomingAxis) {
		this.incomingAxis = incomingAxis;
	}

	public String getIncomingType() {
		return incomingType;
	}

	public void setIncomingType(String incomingType) {
		this.incomingType = incomingType;
	}

	public Timestamp getSendDate() {
		return sendDate;
	}

	public void setSendDate(Timestamp sendDate) {
		this.sendDate = sendDate;
	}

	public String getUpSeqCode() {
		return upSeqCode;
	}

	public void setUpSeqCode(String upSeqCode) {
		this.upSeqCode = upSeqCode;
	}

	public String getUpSeqMachineCode() {
		return upSeqMachineCode;
	}

	public void setUpSeqMachineCode(String upSeqMachineCode) {
		this.upSeqMachineCode = upSeqMachineCode;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public Integer getPlanScheduleId() {
		return planScheduleId;
	}

	public void setPlanScheduleId(Integer planScheduleId) {
		this.planScheduleId = planScheduleId;
	}

	public Integer getMachinePlanId() {
		return machinePlanId;
	}

	public void setMachinePlanId(Integer machinePlanId) {
		this.machinePlanId = machinePlanId;
	}

	public String getMaterType() {
		return materType;
	}

	public void setMaterType(String materType) {
		this.materType = materType;
	}

}
