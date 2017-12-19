package com.css.business.web.subsysstore.bean;

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
@Table(name = "store_obj")
@SequenceGenerator(name = "seq_store_obj", sequenceName = "seq_store_obj")
public class StoreObj implements BaseEntity {

	/**
	 * 物品出入库记录表，物品包括原料、成品、半成品
	 */
	@Transient
	private static final long serialVersionUID = -8553506536906670208L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	/** 供应商 */
	@Column(name = "supply_agent")
	private String supplyAgent;
	/** 供货单号 */
	@Column(name = "supply_code")
	private String supplyCode;
	/** 入库日期 */
	@Column(name = "in_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date inDate;
	/** 制单人 */
	@Column(name = "single_man")
	private String singleMan;
	/** 质检报告编号 */
	@Column(name = "quality_report_code")
	private String qualityReportCode;
	/** 仓库签收人 */
	@Column(name = "depot_sign")
	private String depotSign;
	/** 物品分类 */
	@Column(name = "obj_sort")
	private String objSort;
	/** 物料规格 */
	@Column(name = "obj_ggxh")
	private String objGgxh;
	@Column(name = "obj_name")
	private String objName;
	/** 位置 */
	@Column(name = "address")
	private String address;
	/** RFID */
	@Column(name = "rfid_code")
	private String rfidCode;
	/** 批次号 */
	@Column(name = "batch_code")
	private String batchCode;
	/** 数量 */
	@Column(name = "acount")
	private Double acount;
	/** 单位*/
	private String unit;
	/** 出入库类型 */
	@Column(name = "inout_type")
	private String inoutType;
  	/** 出库日期 */
	@Column(name = "out_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date outDate;
	/** 出入库物品类型包括：原材料，成品 */
	@Column(name = "material_type")
	private String materialType;
	/** 叉车编号 */
	@Column(name = "forklift_truck_number")
	private String forkliftTruckNumber;
	/** 到达机台 */
	@Column(name = "arrive_machine")
	private String arriveMachine;
	/**班次*/
	@Column(name = "shift")
	private String shift;
	/**调用班次*/
	@Column(name = "call_flight")
	private String callFlight;
	@Column(name = "color")
	private String color;
	@Column(name = "weight")
	private Double weight;
	
	
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

	public String getSupplyAgent() {
		return supplyAgent;
	}

	public void setSupplyAgent(String supplyAgent) {
		this.supplyAgent = supplyAgent;
	}

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public String getSingleMan() {
		return singleMan;
	}

	public void setSingleMan(String singleMan) {
		this.singleMan = singleMan;
	}

	public String getQualityReportCode() {
		return qualityReportCode;
	}

	public void setQualityReportCode(String qualityReportCode) {
		this.qualityReportCode = qualityReportCode;
	}

	public String getDepotSign() {
		return depotSign;
	}

	public void setDepotSign(String depotSign) {
		this.depotSign = depotSign;
	}

	public String getObjSort() {
		return objSort;
	}

	public void setObjSort(String objSort) {
		this.objSort = objSort;
	}

	public String getObjGgxh() {
		return objGgxh;
	}

	public void setObjGgxh(String objGgxh) {
		this.objGgxh = objGgxh;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRfidCode() {
		return rfidCode;
	}

	public void setRfidCode(String rfidCode) {
		this.rfidCode = rfidCode;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public Double getAcount() {
		return acount;
	}

	public void setAcount(Double acount) {
		this.acount = acount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getInoutType() {
		return inoutType;
	}

	public void setInoutType(String inoutType) {
		this.inoutType = inoutType;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getForkliftTruckNumber() {
		return forkliftTruckNumber;
	}

	public void setForkliftTruckNumber(String forkliftTruckNumber) {
		this.forkliftTruckNumber = forkliftTruckNumber;
	}

	public String getArriveMachine() {
		return arriveMachine;
	}

	public void setArriveMachine(String arriveMachine) {
		this.arriveMachine = arriveMachine;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public String getCallFlight() {
		return callFlight;
	}

	public void setCallFlight(String callFlight) {
		this.callFlight = callFlight;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}
	
}
