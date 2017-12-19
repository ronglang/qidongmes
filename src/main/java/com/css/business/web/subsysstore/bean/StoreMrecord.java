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
@Table(name = "STORE_MRECORD")
@SequenceGenerator(name = "SEQ_STORE_MRECORD", sequenceName = "SEQ_STORE_MRECORD")
public class StoreMrecord implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 7965151974580484242L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "mater_id")
	private Integer materId;
	@Column(name = "mater_name")
	private String materName;
	@Column(name = "mater_ggxh")
	private String materGgxh;
	@Column(name = "mater_type")
	private String materType;
	@Column(name = "unit")
	private String unit;
	@Column(name = "safe_stock")
	private String safeStock;
	@Column(name = "min_stock")
	private String minStock;
	@Column(name = "delive_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date deliveDate;
	@Column(name = "act_stock")
	private Double actStock;
	@Column(name = "in_way_stock")
	private String inWayStock;
	@Column(name = "req_purch_amount")
	private Integer reqPurchAmount;
	@Column(name = "req_rec_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date reqRecDate;
	@Column(name = "remark")
	private String remark;
	@Column(name = "mater_color")
	private String materColor;

	@Column(name = "als")
	private Integer als;

	@Column(name = "axis_number")
	private Integer axisNumber;

	@Column(name = "address")
	private String address;
	@Column(name = "batch_code")
	private String batchCode;
	@Column(name = "rfid")
	private String rfid;
	@Column(name="check_stock")
	private Double checkStock;

	public Integer getAls() {
		return als;
	}

	public void setAls(Integer als) {
		this.als = als;
	}

	public Integer getAxisNumber() {
		return axisNumber;
	}

	public void setAxisNumber(Integer axisNumber) {
		this.axisNumber = axisNumber;
	}

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

	public Integer getMaterId() {
		return materId;
	}

	public void setMaterId(Integer materId) {
		this.materId = materId;
	}

	public Date getReqRecDate() {
		return reqRecDate;
	}

	public void setReqRecDate(Date reqRecDate) {
		this.reqRecDate = reqRecDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getMaterName() {
		return materName;
	}

	public void setMaterName(String materName) {
		this.materName = materName;
	}

	public String getMaterGgxh() {
		return materGgxh;
	}

	public void setMaterGgxh(String materGgxh) {
		this.materGgxh = materGgxh;
	}

	public String getMaterType() {
		return materType;
	}

	public void setMaterType(String materType) {
		this.materType = materType;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	public Date getDeliveDate() {
		return deliveDate;
	}

	public void setDeliveDate(Date deliveDate) {
		this.deliveDate = deliveDate;
	}

	public Double getActStock() {
		return actStock;
	}

	public void setActStock(Double actStock) {
		this.actStock = actStock;
	}

	public String getInWayStock() {
		return inWayStock;
	}

	public void setInWayStock(String inWayStock) {
		this.inWayStock = inWayStock;
	}

	public Integer getReqPurchAmount() {
		return reqPurchAmount;
	}

	public void setReqPurchAmount(Integer reqPurchAmount) {
		this.reqPurchAmount = reqPurchAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return materColor
	 */
	public String getMaterColor() {
		return materColor;
	}

	/**
	 * @param materColor
	 *            要设置的 materColor
	 * 
	 */
	public void setMaterColor(String materColor) {
		this.materColor = materColor;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public Double getCheckStock() {
		return checkStock;
	}

	public void setCheckStock(Double checkStock) {
		this.checkStock = checkStock;
	}

}
