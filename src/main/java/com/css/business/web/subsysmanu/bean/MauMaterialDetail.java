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
@Table(name = "mau_material_detail")
public class MauMaterialDetail implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -4194166596190368311L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_mau_material_detail", allocationSize = 1, initialValue = 1, sequenceName = "seq_mau_material_detail")  
	@GeneratedValue(generator = "seq_mau_material_detail", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "mater_code")
	private String materCode;
	@Column(name = "mater_amount")
	private BigDecimal materAmount;
	@Column(name = "doc_type")
	private String docType;
	@Column(name = "mmr_code")
	private String mmrCode;
	@Column(name="remark")
	private String remark;
	@Column(name="unit")
	private String unit;
	
	
	@Column(name="main_id")
	private Integer mainId;//主表id
	
	
	@Column(name="plan_count")
	private BigDecimal planCount ;//计划原料数量
	
	@Column(name="mater_name")
	private String materName;//物料名称
	
	@Column(name="is_receive")
	private String isReceive;//是否已领取
	
	@Column(name="mac_id")
	private Integer macId;
	
	@Transient
	private String macCode;
	
	
	
	
	
	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	public Integer getMacId() {
		return macId;
	}

	public void setMacId(Integer macId) {
		this.macId = macId;
	}

	public String getIsReceive() {
		return isReceive;
	}

	public void setIsReceive(String isReceive) {
		this.isReceive = isReceive;
	}

	public String getMaterName() {
		return materName;
	}

	public void setMaterName(String materName) {
		this.materName = materName;
	}

	public BigDecimal getPlanCount() {
		return planCount;
	}

	public void setPlanCount(BigDecimal planCount) {
		this.planCount = planCount;
	}

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getMaterAmount() {
		return materAmount;
	}

	public void setMaterAmount(BigDecimal materAmount) {
		this.materAmount = materAmount;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getMaterCode() {
		return materCode;
	}

	public void setMaterCode(String materCode) {
		this.materCode = materCode;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMmrCode() {
		return mmrCode;
	}

	public void setMmrCode(String mmrCode) {
		this.mmrCode = mmrCode;
	}

}
