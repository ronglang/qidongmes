package com.css.business.web.subsysstore.bean;

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
@Table(name = "store_dg_ck_detail")
@SequenceGenerator(name = "seq_store_dg_ck_detail", sequenceName = "seq_store_dg_ck_detail")
public class StoreDgCkDetail implements BaseEntity {

	/**
	 * 物品出入库记录表，物品包括原料、成品、半成品
	 */
	@Transient
	private static final long serialVersionUID = -8553506536906670208L;
	@Id
	@Column(name = "id")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(generator = "seq_store_dg_ck_detail", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	/**
	 * 
	 */
	@Column(name = "outbound_order_code")
	private String outboundOrderCode;//领料单
	@Column(name="material_id")
	private Integer materialId;//规格型号
	@Column(name="obj_ggxh")
	private String objGgxh;
	@Column(name="amount")
	private BigDecimal amount;//领料单
	@Column(name="fact_amount")
	private BigDecimal factAmount;//领料单
	
	@Column(name="pick_dg_id")
	private Integer pickDgId;//工单编号
	@Column(name="unit")
	private String unit;//单位
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getOutboundOrderCode() {
		return outboundOrderCode;
	}
	public void setOutboundOrderCode(String outboundOrderCode) {
		this.outboundOrderCode = outboundOrderCode;
	}
	public Integer getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}
	public String getObjGgxh() {
		return objGgxh;
	}
	public void setObjGgxh(String objGgxh) {
		this.objGgxh = objGgxh;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getFactAmount() {
		return factAmount;
	}
	public void setFactAmount(BigDecimal factAmount) {
		this.factAmount = factAmount;
	}
	public Integer getPickDgId() {
		return pickDgId;
	}
	public void setPickDgId(Integer pickDgId) {
		this.pickDgId = pickDgId;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	

	
	

}
