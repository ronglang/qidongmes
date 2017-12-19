package com.css.business.web.subsysplan.bean;

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
@Table(name = "PLA_SINGLE_MOVE_DETAIL")
@SequenceGenerator(name = "SEQ_PLA_SINGLE_MOVE_DETAIL", sequenceName = "SEQ_PLA_SINGLE_MOVE_DETAIL")
public class PlaSingleMoveDetail implements BaseEntity {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -1316068461441100105L;
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "single_move_id")
	private Integer singleMoveId;
	@Column(name = "product_order_id")
	private Integer productOrderId;
	@Column(name = "week_plan_id")
	private Integer weekPlanId;
	@Column(name = "machine_plan_id")
	private Integer machinePlanId;
	@Column(name = "machine_plan_schedule_id")
	private Integer machinePlanScheduleId;
	@Column(name = "product_type")
	private String productType;
	@Column(name = "axis_code")
	private String axisCode;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSingleMoveId() {
		return singleMoveId;
	}

	public void setSingleMoveId(Integer singleMoveId) {
		this.singleMoveId = singleMoveId;
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

	public Integer getMachinePlanId() {
		return machinePlanId;
	}

	public void setMachinePlanId(Integer machinePlanId) {
		this.machinePlanId = machinePlanId;
	}

	public Integer getMachinePlanScheduleId() {
		return machinePlanScheduleId;
	}

	public void setMachinePlanScheduleId(Integer machinePlanScheduleId) {
		this.machinePlanScheduleId = machinePlanScheduleId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getAxisCode() {
		return axisCode;
	}

	public void setAxisCode(String axisCode) {
		this.axisCode = axisCode;
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
