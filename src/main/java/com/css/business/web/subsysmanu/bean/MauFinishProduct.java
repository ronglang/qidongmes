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
@Table(name = "mau_finish_product")
public class MauFinishProduct implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 4708712858594985785L;
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
	@Column(name = "mfp_code")
	private String mfpCode;
	@Column(name = "axis_code")
	private String axisCode;
	@Column(name = "is_rework")
	private String isRework;
	@Column(name = "remark")
	private String remark;
	@Column(name = "bat_code")
	private String batCode;


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

	public String getMfpCode() {
		return mfpCode;
	}

	public void setMfpCode(String mfpCode) {
		this.mfpCode = mfpCode;
	}

	public String getAxisCode() {
		return axisCode;
	}

	public void setAxisCode(String axisCode) {
		this.axisCode = axisCode;
	}

	public String getIsRework() {
		return isRework;
	}

	public void setIsRework(String isRework) {
		this.isRework = isRework;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBatCode() {
		return batCode;
	}

	public void setBatCode(String batCode) {
		this.batCode = batCode;
	}
	
	

}
