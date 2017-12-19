package com.css.business.web.subsysstore.storeManage.bean;

import java.math.BigDecimal;

public class StoreDgCkVo {

	
	private Integer id;
	private String ordercode;//出库单号
	private String ggxh;//物料规格型号
	private BigDecimal amount;//数量
	private BigDecimal factamount;//实际出库数量
	
	private String unit;//单位
	private  String matername;//物料名称
	private String mmrcode;//领料单单据号
	
	public String getOrdercode() {
		return ordercode;
	}
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	public String getGgxh() {
		return ggxh;
	}
	public void setGgxh(String ggxh) {
		this.ggxh = ggxh;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getFactamount() {
		return factamount;
	}
	public void setFactamount(BigDecimal factamount) {
		this.factamount = factamount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getMatername() {
		return matername;
	}
	public void setMatername(String matername) {
		this.matername = matername;
	}
	public String getMmrcode() {
		return mmrcode;
	}
	public void setMmrcode(String mmrcode) {
		this.mmrcode = mmrcode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
