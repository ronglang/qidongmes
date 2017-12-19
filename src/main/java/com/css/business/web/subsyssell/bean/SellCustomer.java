package com.css.business.web.subsyssell.bean;

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
@Table(name = "SELL_CUSTOMER")
@SequenceGenerator(name = "SEQ_SELL_CUSTOMER", sequenceName = "SEQ_SELL_CUSTOMER")
public class SellCustomer implements BaseEntity {

	
	@Transient
	private static final long serialVersionUID = -1810149903864938209L;
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
	@Column(name="cus_code")
	private String cusCode;
	@Column(name="cus_name")
	private String cusName;
	@Column(name="cus_short")
	private String cusShort;
	@Column(name="cus_credit")
	private String cusCredit;
	@Column(name="cus_address")
	private String cusAddress;
	@Column(name="cus_contacts")
	private String cusContacts;
	@Column(name="cus_phone")
	private String cusPhone;
	@Column(name="cus_email")
	private String cusEmail;
	
	
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
	public String getCusCode() {
		return cusCode;
	}
	public void setCusCode(String cusCode) {
		this.cusCode = cusCode;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusShort() {
		return cusShort;
	}
	public void setCusShort(String cusShort) {
		this.cusShort = cusShort;
	}
	public String getCusCredit() {
		return cusCredit;
	}
	public void setCusCredit(String cusCredit) {
		this.cusCredit = cusCredit;
	}
	public String getCusAddress() {
		return cusAddress;
	}
	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}
	public String getCusContacts() {
		return cusContacts;
	}
	public void setCusContacts(String cusContacts) {
		this.cusContacts = cusContacts;
	}
	public String getCusPhone() {
		return cusPhone;
	}
	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}
	public String getCusEmail() {
		return cusEmail;
	}
	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}
	
	
	
	
}
