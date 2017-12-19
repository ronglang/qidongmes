package com.css.business.web.subsysplan.bean;

import java.math.BigDecimal;
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
@Table(name = "pla_week_plan")
// @SequenceGenerator(name = "SEQ_PLA_WEEK_PLAN", sequenceName =
// "SEQ_PLA_WEEK_PLAN")
public class PlaWeekPlan implements BaseEntity {
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -5506725678678362683L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_pla_week_plan", sequenceName = "seq_pla_week_plan")
	@GeneratedValue(generator = "seq_pla_week_plan", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "work_day")
	private Integer workDay;
	@Column(name = "work_start_date")
	// @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp workStartDate;
	@Column(name = "work_end_date")
	// @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp workEndDate;
	@Column(name = "product_order_id")
	private Integer productOrderId;
	@Column(name = "mater_type")
	private String materType;
	@Column(name = "axis_amount")
	private Integer axisAmount;
	@Column(name = "part_len")
	private String partLen;
	@Column(name = "drawbench_time")
	private BigDecimal drawbenchTime;
	@Column(name = "strand_time")
	private BigDecimal strandTime;
	@Column(name = "insulation_time")
	private BigDecimal insulationTime;
	@Column(name = "armored_time")
	private BigDecimal armoredTime;
	@Column(name = "cable_time")
	private BigDecimal cableTime;
	@Column(name = "sheath_time")
	private BigDecimal sheathTime;
	@Column(name = "strand_sinsulation_time")
	private BigDecimal strandSinsulationTime;
	@Column(name = "state")
	private String state;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;

	private String is_flag;

	// @Id
	// @GeneratedValue(generator = "system-uuid")
	// @GenericGenerator(name = "system-uuid", strategy = "uuid")

	public Integer getId() {
		return id;
	}

	public String getIs_flag() {
		return is_flag;
	}

	public void setIs_flag(String is_flag) {
		this.is_flag = is_flag;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getWorkStartDate() {
		return workStartDate;
	}

	public void setWorkStartDate(Timestamp workStartDate) {
		this.workStartDate = workStartDate;
	}

	public Timestamp getWorkEndDate() {
		return workEndDate;
	}

	public void setWorkEndDate(Timestamp workEndDate) {
		this.workEndDate = workEndDate;
	}

	public Integer getProductOrderId() {
		return productOrderId;
	}

	public void setProductOrderId(Integer productOrderId) {
		this.productOrderId = productOrderId;
	}

	public String getMaterType() {
		return materType;
	}

	public void setMaterType(String materType) {
		this.materType = materType;
	}

	public Integer getAxisAmount() {
		return axisAmount;
	}

	public void setAxisAmount(Integer axisAmount) {
		this.axisAmount = axisAmount;
	}

	public String getPartLen() {
		return partLen;
	}

	public void setPartLen(String partLen) {
		this.partLen = partLen;
	}

	public Integer getWorkDay() {
		return workDay;
	}

	public void setWorkDay(Integer workDay) {
		this.workDay = workDay;
	}

	public BigDecimal getDrawbenchTime() {
		return drawbenchTime;
	}

	public void setDrawbenchTime(BigDecimal drawbenchTime) {
		this.drawbenchTime = drawbenchTime;
	}

	public BigDecimal getStrandTime() {
		return strandTime;
	}

	public void setStrandTime(BigDecimal strandTime) {
		this.strandTime = strandTime;
	}

	public BigDecimal getInsulationTime() {
		return insulationTime;
	}

	public void setInsulationTime(BigDecimal insulationTime) {
		this.insulationTime = insulationTime;
	}

	public BigDecimal getArmoredTime() {
		return armoredTime;
	}

	public void setArmoredTime(BigDecimal armoredTime) {
		this.armoredTime = armoredTime;
	}

	public BigDecimal getCableTime() {
		return cableTime;
	}

	public void setCableTime(BigDecimal cableTime) {
		this.cableTime = cableTime;
	}

	public BigDecimal getSheathTime() {
		return sheathTime;
	}

	public void setSheathTime(BigDecimal sheathTime) {
		this.sheathTime = sheathTime;
	}

	public BigDecimal getStrandSinsulationTime() {
		return strandSinsulationTime;
	}

	public void setStrandSinsulationTime(BigDecimal strandSinsulationTime) {
		this.strandSinsulationTime = strandSinsulationTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

}
