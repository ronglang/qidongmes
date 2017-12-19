package com.css.business.web.subsysplan.plaManage.bean;

import java.util.List;

public class CalculaMaterVo {

	private Integer core; // 铜线根数
	
	private String courseCode;//工单编号

	private Double Cuweight;// 铜线重量

	private Double Culength;// 铜线长度

	private Double Jlweight; // 胶料重量

	private Integer macCount; // 机台数量

	private List<Integer> glueMachineIds;// 胶料机台id

	private List<Double> glueVlaues; // 胶料的值,对应机台id
	
	private List<Integer> cuMachineIds;// 铜料机台id

	private List<Double> cuVlaues; // 铜料的值,对应机台id
	

	public Integer getCore() {
		return core;
	}

	public void setCore(Integer core) {
		this.core = core;
	}

	public Double getCuweight() {
		return Cuweight;
	}

	public void setCuweight(Double cuweight) {
		Cuweight = cuweight;
	}

	public Double getCulength() {
		return Culength;
	}

	public void setCulength(Double culength) {
		Culength = culength;
	}

	public Double getJlweight() {
		return Jlweight;
	}

	public void setJlweight(Double jlweight) {
		Jlweight = jlweight;
	}

	public Integer getMacCount() {
		return macCount;
	}

	public void setMacCount(Integer macCount) {
		this.macCount = macCount;
	}

	

	/**
	 * @return glueVlaues
	 */
	public List<Double> getGlueVlaues() {
		return glueVlaues;
	}

	/**
	 * @param glueVlaues
	 *            要设置的 glueVlaues
	 * 
	 */
	public void setGlueVlaues(List<Double> glueVlaues) {
		this.glueVlaues = glueVlaues;
	}

	
	/**
	 * @return  glueMachineIds
	 */
	public List<Integer> getGlueMachineIds() {
		return glueMachineIds;
	}

	/**
	 *  @param glueMachineIds 要设置的 glueMachineIds 
	 *    
	 */
	public void setGlueMachineIds(List<Integer> glueMachineIds) {
		this.glueMachineIds = glueMachineIds;
	}

	/**
	 * @return  cuMachineIds
	 */
	public List<Integer> getCuMachineIds() {
		return cuMachineIds;
	}

	/**
	 *  @param cuMachineIds 要设置的 cuMachineIds 
	 *    
	 */
	public void setCuMachineIds(List<Integer> cuMachineIds) {
		this.cuMachineIds = cuMachineIds;
	}

	/**
	 * @return  cuVlaues
	 */
	public List<Double> getCuVlaues() {
		return cuVlaues;
	}

	/**
	 *  @param cuVlaues 要设置的 cuVlaues 
	 *    
	 */
	public void setCuVlaues(List<Double> cuVlaues) {
		this.cuVlaues = cuVlaues;
	}

	/**
	 * @return  courseCode
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 *  @param courseCode 要设置的 courseCode 
	 *    
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	

}
