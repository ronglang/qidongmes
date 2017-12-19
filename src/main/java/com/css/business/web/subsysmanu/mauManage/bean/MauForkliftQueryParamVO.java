package com.css.business.web.subsysmanu.mauManage.bean;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class MauForkliftQueryParamVO {
	private int id;
	private String fcode;
	private String ftype;
	private String fdriver;
	//@Temporal(TemporalType.TIMESTAMP)
	/*@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)*/
	private Date createdate;
	public int getId() {
		return id;
	}
	public void setId(int id) { 
		this.id = id;
	} 
	public String getFcode() {
		return fcode;
	}
	public void setFcode(String fcode) {
		this.fcode = fcode;
	}
	public String getFtype() {
		return ftype;
	}
	public void setFtype(String ftype) {
		this.ftype = ftype;
	}
	public String getFdriver() {
		return fdriver;
	}

	public void setFdriver(String fdriver) {
		this.fdriver = fdriver;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
}
