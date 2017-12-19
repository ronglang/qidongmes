package com.css.business.web.subsysstore.bean;

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
@Table(name = "store_dg_ck")
@SequenceGenerator(name = "seq_store_dg_ck", sequenceName = "seq_store_dg_ck")
public class StoreDgCk implements BaseEntity {

	/**
	 * 物品出入库记录表，物品包括原料、成品、半成品
	 */
	@Transient
	private static final long serialVersionUID = -8553506536906670208L;
	@Id
	@Column(name = "id")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(generator = "seq_store_dg_ck", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	/** 供应商 */
	@Column(name = "pick_list_code")
	private String pickListCode;//领料单
	@Column(name="obj_ggxh")
	private String objGgxh;//规格型号
	@Column(name="status")
	private String status;//出库状态  （已出库，未出库）
	@Column(name="outbound_order_code")
	private String outboundOrderCode;//工单编号
	@Column(name="operator")
	private String operator;//单位
	@Column(name="picktor")
	private String picktor;//单位
	
	@Column(name="operat_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp operatTime;

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
	public String getPickListCode() {
		return pickListCode;
	}
	public void setPickListCode(String pickListCode) {
		this.pickListCode = pickListCode;
	}
	public String getObjGgxh() {
		return objGgxh;
	}
	public void setObjGgxh(String objGgxh) {
		this.objGgxh = objGgxh;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOutboundOrderCode() {
		return outboundOrderCode;
	}
	public void setOutboundOrderCode(String outboundOrderCode) {
		this.outboundOrderCode = outboundOrderCode;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getPicktor() {
		return picktor;
	}
	public void setPicktor(String picktor) {
		this.picktor = picktor;
	}
	public Timestamp getOperatTime() {
		return operatTime;
	}
	public void setOperatTime(Timestamp operatTime) {
		this.operatTime = operatTime;
	}
	

	

	
	

}
