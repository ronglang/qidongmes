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
@Table(name = "store_scrap_record")
@SequenceGenerator(name = "seq_store_scrap_record", sequenceName = "seq_store_scrap_record")
public class StoreScrapRecord implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 7965151974580484242L;
	@Id
	@Column(name="id")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(generator = "seq_store_scrap_record", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	
	@Column(name = "batch_code")
	private String batchCode;
	@Column(name = "material_name")
	private String materialName;
	@Column(name = "rfid_code")
	private String rfidCode;
	@Column(name = "model")
	private String model;
	@Column(name = "color")
	private String color;
	@Column(name="amount")
	private Double amount;
	@Column(name = "material_ralation")
	private String materialRalation;
	@Column(name = "handler")
	private String handler;
	
	@Column(name = "status")
	private String status;
	@Column(name = "handle_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date handleDate;
	@Column(name = "apply_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date applyDate;
	@Column(name = "handle_idea")
	private String handleIdea;
	@Column(name = "handle_after_position")
	private String handleAfterPosition;
	
	@Column(name = "examin_report_url")
	private String examinReportUrl;
	@Column(name = "unit")
	private String unit;
	@Column(name="remark")
	private String remark;
	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getRfidCode() {
		return rfidCode;
	}

	public void setRfidCode(String rfidCode) {
		this.rfidCode = rfidCode;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getMaterialRalation() {
		return materialRalation;
	}

	public void setMaterialRalation(String materialRalation) {
		this.materialRalation = materialRalation;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public Date getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}

	public String getHandleIdea() {
		return handleIdea;
	}

	public void setHandleIdea(String handleIdea) {
		this.handleIdea = handleIdea;
	}

	public String getHandleAfterPosition() {
		return handleAfterPosition;
	}

	public void setHandleAfterPosition(String handleAfterPosition) {
		this.handleAfterPosition = handleAfterPosition;
	}

	public String getExaminReportUrl() {
		return examinReportUrl;
	}

	public void setExaminReportUrl(String examinReportUrl) {
		this.examinReportUrl = examinReportUrl;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	
	
	
	

}
