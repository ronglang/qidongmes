package com.css.business.web.subsysplan.vo;

import java.sql.Timestamp;

/**
 * 在单系统显示视图
 * @author Administrator
 *
 */
public class InSingleSystemVo {

	
	private String orderCode;//生产令编号
	
	private Timestamp planStartTime;//计划开始日期
	
	private Timestamp planEndTime;//计划完成日期
	
	private Double hours;//所需工时
	
	private String timeUnit;//计时单位
	
	private String materielCode;//物料编码
	
	private String materielGgxh;//物料规格型号
	
	private String proGgxh;//产品规格型号
	
	private String color;//产品颜色
	
	private Double count;//产品数量
	
	private String proUnit;//产品计量单位
	
	private Timestamp deliveryTime;//产品交货日期
	
	private String contractCode ;//合同编号
	
	private Double productPartLen;//生产段长

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Timestamp getPlanStartTime() {
		return planStartTime;
	}

	public void setPlanStartTime(Timestamp planStartTime) {
		this.planStartTime = planStartTime;
	}

	public Timestamp getPlanEndTime() {
		return planEndTime;
	}

	public void setPlanEndTime(Timestamp planEndTime) {
		this.planEndTime = planEndTime;
	}

	public Double getHours() {
		return hours;
	}

	public void setHours(Double hours) {
		this.hours = hours;
	}

	public String getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(String timeUnit) {
		this.timeUnit = timeUnit;
	}

	public String getMaterielCode() {
		return materielCode;
	}

	public void setMaterielCode(String materielCode) {
		this.materielCode = materielCode;
	}

	public String getMaterielGgxh() {
		return materielGgxh;
	}

	public void setMaterielGgxh(String materielGgxh) {
		this.materielGgxh = materielGgxh;
	}

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public String getProUnit() {
		return proUnit;
	}

	public Double getProductPartLen() {
		return productPartLen;
	}

	public void setProductPartLen(Double productPartLen) {
		this.productPartLen = productPartLen;
	}

	public void setProUnit(String proUnit) {
		this.proUnit = proUnit;
	}

	public Timestamp getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	
}
