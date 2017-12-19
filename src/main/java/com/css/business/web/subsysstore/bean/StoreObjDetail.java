package com.css.business.web.subsysstore.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "STORE_OBJ_DETAIL")
@SequenceGenerator(name="SEQ_STORE_OBJ_DETAIL",sequenceName="SEQ_STORE_OBJ_DETAIL")
public class StoreObjDetail implements BaseEntity{
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private Integer id;
	private Integer create_by;
	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date createDate;
	@Column(name="obj_name")
	private String objName;
	@Column(name="obj_ggxh")
	private String objGgxh;
	@Column(name="obj_count")
	private String objCount;
	@Column(name="obj_color")
	private String objColor;
	@Column(name="unit")
	private String unit;
	@Column(name = "order_code")
	private String orderCode;
	@Column(name = "pick_code")
	private String pickCode;
	@Column(name = "packing_type")
	private String packingType;
	@Column(name = "packing_amount")
	private String packingAmount;
	@Column(name = "batch_num")
	private String batchNum;
	@Column(name = "safe_stock")
	private String safeStock;
	@Column(name = "min_stock")
	private String minStock;
	@Column(name = "act_stock")
	private String actStock;
	@Column(name = "in_way_stock")
	private String inWayStock;
	@Column(name = "req_rec_date")
	private String reqRecDate;
	@Column(name = "delive_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date deliveDate;
	@Column(name = "remark")
	private String remark;
	@Column(name = "req_purch_amount")
	private String reqPurchAmount;
	@Column(name="rfid_code")
	private String rfidCode;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCreate_by() {
		return create_by;
	}
	public void setCreate_by(Integer create_by) {
		this.create_by = create_by;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getObjName() {
		return objName;
	}
	public void setObjName(String objName) {
		this.objName = objName;
	}
	public String getObjGgxh() {
		return objGgxh;
	}
	public void setObjGgxh(String objGgxh) {
		this.objGgxh = objGgxh;
	}
	public String getObjCount() {
		return objCount;
	}
	public void setObjCount(String objCount) {
		this.objCount = objCount;
	}
	public String getObjColor() {
		return objColor;
	}
	public void setObjColor(String objColor) {
		this.objColor = objColor;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getPickCode() {
		return pickCode;
	}
	public void setPickCode(String pickCode) {
		this.pickCode = pickCode;
	}
	public String getPackingType() {
		return packingType;
	}
	public void setPackingType(String packingType) {
		this.packingType = packingType;
	}
	public String getPackingAmount() {
		return packingAmount;
	}
	public void setPackingAmount(String packingAmount) {
		this.packingAmount = packingAmount;
	}
	public String getBatchNum() {
		return batchNum;
	}
	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
	public String getSafeStock() {
		return safeStock;
	}
	public void setSafeStock(String safeStock) {
		this.safeStock = safeStock;
	}
	public String getMinStock() {
		return minStock;
	}
	public void setMinStock(String minStock) {
		this.minStock = minStock;
	}
	public String getActStock() {
		return actStock;
	}
	public void setActStock(String actStock) {
		this.actStock = actStock;
	}
	public String getInWayStock() {
		return inWayStock;
	}
	public void setInWayStock(String inWayStock) {
		this.inWayStock = inWayStock;
	}
	public String getReqRecDate() {
		return reqRecDate;
	}
	public void setReqRecDate(String reqRecDate) {
		this.reqRecDate = reqRecDate;
	}
	public Date getDeliveDate() {
		return deliveDate;
	}
	public void setDeliveDate(Date deliveDate) {
		this.deliveDate = deliveDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getReqPurchAmount() {
		return reqPurchAmount;
	}
	public void setReqPurchAmount(String reqPurchAmount) {
		this.reqPurchAmount = reqPurchAmount;
	}
	public String getRfidCode() {
		return rfidCode;
	}
	public void setRfidCode(String rfidCode) {
		this.rfidCode = rfidCode;
	}

}
