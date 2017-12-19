package com.css.business.web.subsysplan.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "pla_abnormal_infor")
@SequenceGenerator(name = "seq_pla_abnormal_infor", sequenceName = "seq_pla_abnormal_infor")
public class PlaAbnormalInfor implements BaseEntity {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -8065860634654328207L;
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "prodcut_order_id")
	private Integer prodcutOrderId;
	@Column(name = "week_plan_id")
	private Integer weekPlanId;
	@Column(name = "machine_plan_id")
	private Integer machinePlanId;
	@Column(name = "machine_plan_schedule_id")
	private Integer machinePlanScheduleId;
	@Column(name = "abnormal_class")
	private String abnormalClass;
	@Column(name = "abnormal_small_class")
	private String abnormalSmallClass;
	@Column(name = "treat_mode")
	private String treatMode;
	@Column(name = "treat_result")
	private String treatResult;
	@Column(name = "abnormal_reason")
	private String abnormalReason;
	@Column(name = "scene")
	private String scene;
	@Column(name = "abnormal_param_code")
	private String abnormalParamCode;
	@Column(name = "param_allowable_value")
	private String paramAllowableValue;
	@Column(name = "param_current_value")
	private String paramCurrentValue;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProdcutOrderId() {
		return prodcutOrderId;
	}

	public void setProdcutOrderId(Integer prodcutOrderId) {
		this.prodcutOrderId = prodcutOrderId;
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

	public String getAbnormalClass() {
		return abnormalClass;
	}

	public void setAbnormalClass(String abnormalClass) {
		this.abnormalClass = abnormalClass;
	}

	public String getAbnormalSmallClass() {
		return abnormalSmallClass;
	}

	public void setAbnormalSmallClass(String abnormalSmallClass) {
		this.abnormalSmallClass = abnormalSmallClass;
	}

	public String getTreatMode() {
		return treatMode;
	}

	public void setTreatMode(String treatMode) {
		this.treatMode = treatMode;
	}

	public String getTreatResult() {
		return treatResult;
	}

	public void setTreatResult(String treatResult) {
		this.treatResult = treatResult;
	}

	public String getAbnormalReason() {
		return abnormalReason;
	}

	public void setAbnormalReason(String abnormalReason) {
		this.abnormalReason = abnormalReason;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getAbnormalParamCode() {
		return abnormalParamCode;
	}

	public void setAbnormalParamCode(String abnormalParamCode) {
		this.abnormalParamCode = abnormalParamCode;
	}

	public String getParamAllowableValue() {
		return paramAllowableValue;
	}

	public void setParamAllowableValue(String paramAllowableValue) {
		this.paramAllowableValue = paramAllowableValue;
	}

	public String getParamCurrentValue() {
		return paramCurrentValue;
	}

	public void setParamCurrentValue(String paramCurrentValue) {
		this.paramCurrentValue = paramCurrentValue;
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
