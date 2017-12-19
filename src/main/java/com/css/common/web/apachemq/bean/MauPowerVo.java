package com.css.common.web.apachemq.bean;

import java.util.List;

public class MauPowerVo {

	private Float startPower; // 开始电能
	private Float endPower; // 结束电能
	private Float elec; // 总电能
	private String workOrder; // 工作单编码
	private String employees; // 操作手
	private String machineCode; // 机台编码
	private List<String> series; // Y轴数据
	private List<String> lengds; // X轴数据

	

	public Float getStartPower() {
		return startPower;
	}

	public void setStartPower(Float startPower) {
		this.startPower = startPower;
	}

	public Float getEndPower() {
		return endPower;
	}

	public void setEndPower(Float endPower) {
		this.endPower = endPower;
	}

	public Float getElec() {
		return elec;
	}

	public void setElec(Float elec) {
		this.elec = elec;
	}

	public String getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(String workOrder) {
		this.workOrder = workOrder;
	}

	public String getEmployees() {
		return employees;
	}

	public void setEmployees(String employees) {
		this.employees = employees;
	}

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public List<String> getSeries() {
		return series;
	}

	public void setSeries(List<String> series) {
		this.series = series;
	}

	public List<String> getLengds() {
		return lengds;
	}

	public void setLengds(List<String> lengds) {
		this.lengds = lengds;
	}

}
