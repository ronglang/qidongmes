package com.css.business.web.subsysmanu.mauManage.bean;

import java.math.BigDecimal;
import java.util.Date;

public class MaterialRequsitionFromVo {
	private String materManageBy;
	private String mmrCode;
	private String docType;
	private String wsCode;
	private Date materManageTime;
	private String macCode;
	private String materCode;
	private BigDecimal materAmount;
	private String unit;
	public String getMaterManageBy() {
		return materManageBy;
	}
	public void setMaterManageBy(String materManageBy) {
		this.materManageBy = materManageBy;
	}
	public String getMmrCode() {
		return mmrCode;
	}
	public void setMmrCode(String mmrCode) {
		this.mmrCode = mmrCode;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getWsCode() {
		return wsCode;
	}
	public void setWsCode(String wsCode) {
		this.wsCode = wsCode;
	}
	
	public Date getMaterManageTime() {
		return materManageTime;
	}
	public void setMaterManageTime(Date materManageTime) {
		this.materManageTime = materManageTime;
	}
	public String getMacCode() {
		return macCode;
	}
	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}
	public String getMaterCode() {
		return materCode;
	}
	public void setMaterCode(String materCode) {
		this.materCode = materCode;
	}
	public BigDecimal getMaterAmount() {
		return materAmount;
	}
	public void setMaterAmount(BigDecimal materAmount) {
		this.materAmount = materAmount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	

}
