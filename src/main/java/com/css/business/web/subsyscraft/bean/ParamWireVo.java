package com.css.business.web.subsyscraft.bean;

import org.apache.poi.ss.formula.functions.T;

/**
 * 线盘参数VO
 * @author DELL
 *
 */
public class ParamWireVo {
	
	
	private SysParameter sysParameter;
	private Integer amount;//这个线盘规格的空闲的个数
	
	private Integer value;//当前生产该种规格线需要的线盘数量
	
	private Integer leftAmount;//当前规格线盘个数-当前生产需要的线盘数量
	private String message;

	
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public SysParameter getSysParameter() {
		return sysParameter;
	}

	public void setSysParameter(SysParameter sysParameter) {
		this.sysParameter = sysParameter;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getLeftAmount() {
		return leftAmount;
	}

	public void setLeftAmount(Integer leftAmount) {
		leftAmount=this.amount-this.value;
	}

	
	
	

}
