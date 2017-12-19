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
@Table(name = "MAU_PRODUCT_BOM")
// @SequenceGenerator(name="SEQ_MAU_PRODUCT_BOM",sequenceName="SEQ_MAU_PRODUCT_BOM")
public class MauProductBom implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -461814214552649118L;
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
	@Column(name = "mpb_code")
	private String mpbCode;
	@Column(name = "mpb_name")
	private String mpbName;
	@Column(name = "specifications")
	private String specifications;
	@Column(name = "color")
	private String color;
	@Column(name = "product_code")
	private String productCode;
	@Column(name = "product_c_code")
	private String productC_code;


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

	public String getMpbCode() {
		return mpbCode;
	}

	public void setMpbCode(String mpbCode) {
		this.mpbCode = mpbCode;
	}

	public String getMpbName() {
		return mpbName;
	}

	public void setMpbName(String mpbName) {
		this.mpbName = mpbName;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductC_code() {
		return productC_code;
	}

	public void setProductC_code(String productC_code) {
		this.productC_code = productC_code;
	}
	
	

}
