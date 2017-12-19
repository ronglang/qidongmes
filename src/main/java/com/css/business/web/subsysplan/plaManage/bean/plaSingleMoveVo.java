package com.css.business.web.subsysplan.plaManage.bean;

import java.util.Date;

public class plaSingleMoveVo {

	private Integer id;
	private String wscode;
	private String proggxh;
	private String partlen;
	private String color;
	private String batcode;
	private String acount; // 当前轴数
	private Integer amount; // 挪动轴数
	private String movecode;
	private Date damandDate;
	private Integer manuid; // 生产通知单表id
	private String routecode;
	private String procraftcode;
	private String productordercode;
	

	public String getWscode() {
		return wscode;
	}

	public void setWscode(String wscode) {
		this.wscode = wscode;
	}

	public String getProggxh() {
		return proggxh;
	}

	public void setProggxh(String proggxh) {
		this.proggxh = proggxh;
	}

	public String getPartlen() {
		return partlen;
	}

	public void setPartlen(String partlen) {
		this.partlen = partlen;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBatcode() {
		return batcode;
	}

	public void setBatcode(String batcode) {
		this.batcode = batcode;
	}

	public String getAcount() {
		return acount;
	}

	public void setAcount(String acount) {
		this.acount = acount;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMovecode() {
		return movecode;
	}

	public void setMovecode(String movecode) {
		this.movecode = movecode;
	}

	public Date getDamandDate() {
		return damandDate;
	}

	public void setDamandDate(Date damandDate) {
		this.damandDate = damandDate;
	}

	public Integer getManuid() {
		return manuid;
	}

	public void setManuid(Integer manuid) {
		this.manuid = manuid;
	}

	public String getRoutecode() {
		return routecode;
	}

	public void setRoutecode(String routecode) {
		this.routecode = routecode;
	}

	public String getProcraftcode() {
		return procraftcode;
	}

	public void setProcraftcode(String procraftcode) {
		this.procraftcode = procraftcode;
	}

	public String getProductordercode() {
		return productordercode;
	}

	public void setProductordercode(String productordercode) {
		this.productordercode = productordercode;
	}

}
