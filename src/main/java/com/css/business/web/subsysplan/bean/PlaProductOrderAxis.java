package com.css.business.web.subsysplan.bean;

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
@Table(name = "PLA_PRODUCT_ORDER_AXIS")
//@SequenceGenerator(name = "SEQ_PLA_PRODUCT_ORDER_AXIS", sequenceName = "SEQ_PLA_PRODUCT_ORDER_AXIS")
public class PlaProductOrderAxis implements BaseEntity {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 659737514869725997L;
	@Transient
	private static final String IS_FINSH = "是";
	@Transient
	private static final String IS_NOT_FINSH = "否";
	
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "SEQ_PLA_PRODUCT_ORDER_AXIS", sequenceName = "SEQ_PLA_PRODUCT_ORDER_AXIS")  
	@GeneratedValue(generator = "SEQ_PLA_PRODUCT_ORDER_AXIS", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "product_order_code")
	private String productOrderCode;
	@Column(name = "axis_name")
	private String axisName;
	@Column(name = "axis_code")
	private String axisCode;
	@Column(name = "mater_type")
	private String materType;
	@Column(name = "start_date")
	//@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp startDate;
	@Column(name = "end_date")
	//@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp endDate;
	@Column(name = "part_len")
	private Integer partLen;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name="is_finsh")
	private String isFinsh;

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductOrderCode() {
		return productOrderCode;
	}

	public void setProductOrderCode(String productOrderCode) {
		this.productOrderCode = productOrderCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public String getAxisName() {
		return axisName;
	}

	public void setAxisName(String axisName) {
		this.axisName = axisName;
	}

	public String getAxisCode() {
		return axisCode;
	}

	public void setAxisCode(String axisCode) {
		this.axisCode = axisCode;
	}

	public String getMaterType() {
		return materType;
	}

	public void setMaterType(String materType) {
		this.materType = materType;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Integer getPartLen() {
		return partLen;
	}

	public void setPartLen(Integer partLen) {
		this.partLen = partLen;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
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

	public String getIsFinsh() {
		return isFinsh;
	}

	public void setIsFinsh(String isFinsh) {
		this.isFinsh = isFinsh;
	}
	
}
