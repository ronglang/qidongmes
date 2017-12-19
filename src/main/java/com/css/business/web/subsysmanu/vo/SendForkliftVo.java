package com.css.business.web.subsysmanu.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class SendForkliftVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String taskId;
	
	private String startPlace;//出发地
	
	private String materialGgxh;//原材料规格型号
	
	private BigDecimal count;//物料数量
	
	private String unit;//单位
	
	private String destination;//目的地，没有就填机台
	private String destType;//目的地类型：机台, 半成品区、入库待检区等

	private String isReceive;//表示该领料任务是否已经被领取
	private String macCode ;//机台编号
	private String courseCode ;//工单编号
	
	public String getIsReceive() {
		return isReceive;
	}

	public void setIsReceive(String isReceive) {
		this.isReceive = isReceive;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getStartPlace() {
		return startPlace;
	}

	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}

	public String getMaterialGgxh() {
		return materialGgxh;
	}

	public void setMaterialGgxh(String materialGgxh) {
		this.materialGgxh = materialGgxh;
	}

	public BigDecimal getCount() {
		return count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	public String getDestType() {
		return destType;
	}

	public void setDestType(String destType) {
		this.destType = destType;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	
	
	

}
