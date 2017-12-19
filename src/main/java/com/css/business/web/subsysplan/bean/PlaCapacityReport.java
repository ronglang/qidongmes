package com.css.business.web.subsysplan.bean;

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
@Table(name = "PLA_CAPACITY_REPORT")
@SequenceGenerator(name = "SEQ_PLA_CAPACITY_REPORT", sequenceName = "SEQ_PLA_CAPACITY_REPORT")
public class PlaCapacityReport implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -9133088227225356642L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name="createBy")
	private String createBy;
	@Column(name= "pm_id") //生产部人员id
	private Integer pmId;
	@Column(name="test_time")
	private Double testTime; //调试时间
	@Column(name="change_disk_time")
	private Double changeDiskTime; //换盘时间
	@Column(name="product_speed")
	private Double productSpeed;//生产速度
	@Column(name="unit")
	private String unit; //单位
	

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

	public Integer getPmId() {
		return pmId;
	}

	public void setPmId(Integer pmId) {
		this.pmId = pmId;
	}

	public Double getTestTime() {
		return testTime;
	}

	public void setTestTime(Double testTime) {
		this.testTime = testTime;
	}

	public Double getChangeDiskTime() {
		return changeDiskTime;
	}

	public void setChangeDiskTime(Double changeDiskTime) {
		this.changeDiskTime = changeDiskTime;
	}

	public Double getProductSpeed() {
		return productSpeed;
	}

	public void setProductSpeed(Double productSpeed) {
		this.productSpeed = productSpeed;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
}
