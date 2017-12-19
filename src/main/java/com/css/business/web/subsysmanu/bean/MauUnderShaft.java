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
@Table(name = "mau_under_shaft")
// @SequenceGenerator(name="SEQ_MAU_UNDER_SHAFT",sequenceName="SEQ_MAU_UNDER_SHAFT")
public class MauUnderShaft implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 5116997390429353879L;
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
	@Column(name = "mus_code")
	private String musCode;
	@Column(name = "ws_code")
	private String wsCode;
	@Column(name = "axis_number")
	private String axisNumber;
	@Column(name = "agent_by")
	private String agentBy;
	@Column(name = "c_code")
	private String cCode;
	@Column(name = "is_normal")
	private String isNormal;
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

	public String getMusCode() {
		return musCode;
	}

	public void setMusCode(String musCode) {
		this.musCode = musCode;
	}

	public String getWsCode() {
		return wsCode;
	}

	public void setWsCode(String wsCode) {
		this.wsCode = wsCode;
	}

	public String getAxisNumber() {
		return axisNumber;
	}

	public void setAxisNumber(String axisNumber) {
		this.axisNumber = axisNumber;
	}

	public String getAgentBy() {
		return agentBy;
	}

	public void setAgentBy(String agentBy) {
		this.agentBy = agentBy;
	}

	public String getcCode() {
		return cCode;
	}

	public void setcCode(String cCode) {
		this.cCode = cCode;
	}

	public String getIsNormal() {
		return isNormal;
	}

	public void setIsNormal(String isNormal) {
		this.isNormal = isNormal;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	

}
