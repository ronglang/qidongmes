package com.css.business.web.subsysmanu.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "mau_process_monitoring")
// @SequenceGenerator(name="SEQ_MAU_PROCESS_MONITORING",sequenceName="SEQ_MAU_PROCESS_MONITORING")
public class MauProcessMonitoring implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 4263679184000647555L;
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
	@Column(name = "mpm_code")
	private String mpmCode;
	@Column(name = "ws_code")
	private String wsCode;
	@Column(name = "seq_code")
	private String seqCode;
	@Column(name = "start_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date startDate;
	@Column(name = "end_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date endDate;
	@Column(name = "mater_id")
	private String materId;
	@Column(name = "manu_amount")
	private Integer manuAmount;
	@Column(name = "mac_code")
	private String macCode;
	@Column(name = "own_axis_number")
	private String ownAxisNumber;
	@Column(name = "source_axis_number")
	private String sourceAxisNumber;
	@Column(name = "c_code")
	private String cCode;
	@Column(name = "unit")
	private String unit;
	@Column(name = "product_id")
	private Integer productId;
	@Column(name = "mps_code")
	private String mpsCode;


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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getManuAmount() {
		return manuAmount;
	}

	public void setManuAmount(Integer manuAmount) {
		this.manuAmount = manuAmount;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getMpmCode() {
		return mpmCode;
	}

	public void setMpmCode(String mpmCode) {
		this.mpmCode = mpmCode;
	}

	public String getWsCode() {
		return wsCode;
	}

	public void setWsCode(String wsCode) {
		this.wsCode = wsCode;
	}

	public String getSeqCode() {
		return seqCode;
	}

	public void setSeqCode(String seqCode) {
		this.seqCode = seqCode;
	}

	public String getMaterCode() {
		return materId;
	}

	public void setMaterCode(String materId) {
		this.materId = materId;
	}

	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	public String getOwnAxisNumber() {
		return ownAxisNumber;
	}

	public void setOwnAxisNumber(String ownAxisNumber) {
		this.ownAxisNumber = ownAxisNumber;
	}

	public String getSourceAxisNumber() {
		return sourceAxisNumber;
	}

	public void setSourceAxisNumber(String sourceAxisNumber) {
		this.sourceAxisNumber = sourceAxisNumber;
	}

	public String getcCode() {
		return cCode;
	}

	public void setcCode(String cCode) {
		this.cCode = cCode;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getMpsCode() {
		return mpsCode;
	}

	public void setMpsCode(String mpsCode) {
		this.mpsCode = mpsCode;
	}
	
}
