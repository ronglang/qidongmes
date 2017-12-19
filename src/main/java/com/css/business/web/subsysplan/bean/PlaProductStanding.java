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
@Table(name = "PLA_PRODUCT_STANDING")
@SequenceGenerator(name = "SEQ_PLA_PRODUCT_STANDING", sequenceName = "SEQ_PLA_PRODUCT_STANDING")
public class PlaProductStanding implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -8267383583402836063L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "name")
	private String name;
	@Column(name = "sc_code")
	private String scCode;
	@Column(name = "delive_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date deliveDate;
	@Column(name = "delive_quantity")
	private Integer deliveQuantity;
	@Column(name = "unit")
	private String unit;
	@Column(name = "product_code")
	private String productCode;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScCode() {
		return scCode;
	}

	public void setScCode(String scCode) {
		this.scCode = scCode;
	}

	public Date getDeliveDate() {
		return deliveDate;
	}

	public void setDeliveDate(Date deliveDate) {
		this.deliveDate = deliveDate;
	}

	public Integer getDeliveQuantity() {
		return deliveQuantity;
	}

	public void setDeliveQuantity(Integer deliveQuantity) {
		this.deliveQuantity = deliveQuantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	

}
