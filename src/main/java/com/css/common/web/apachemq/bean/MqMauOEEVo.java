package com.css.common.web.apachemq.bean;

import javax.persistence.Column;

/**
 * @Title: MqMauOEEVo.java
 * @Package com.css.common.web.apachemq.bean
 * @Description: 机台关闭时,向web端发送机台开关机时间等信息作为OEE的参照
 * @author rb
 * @date 2017年9月6日 下午3:06:53
 * @company SMTC
 */

public class MqMauOEEVo {

	/** 机台id */
	private Integer machineId;
	/** 机台code */
	private String machineCode;
	/** 工单编号 */
	private String courseCode;
	/** 开机时间 */
	private Long start;
	/** 关机时间 */
	private Long end;
	/** 开机分钟数 */
	private Integer startMin;
	/** 停机分钟数 */
	private Integer endMin;
	/** 不良品 */
	private Integer rejects;
	/** 零碎品 */
	private Integer bitsPieces;
	/** 过量耗用品 */
	private Integer overdoes;
	/** 所属步骤 */
	private Integer step;

	/**
	 * @return machineId
	 */
	public Integer getMachineId() {
		return machineId;
	}

	/**
	 * @param machineId
	 *            要设置的 machineId
	 * 
	 */
	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}

	/**
	 * @return start
	 */
	public Long getStart() {
		return start;
	}

	/**
	 * @param start
	 *            要设置的 start
	 * 
	 */
	public void setStart(Long start) {
		this.start = start;
	}

	/**
	 * @return end
	 */
	public Long getEnd() {
		return end;
	}

	/**
	 * @param end
	 *            要设置的 end
	 * 
	 */
	public void setEnd(Long end) {
		this.end = end;
	}

	/**
	 * @return startMin
	 */
	public Integer getStartMin() {
		return startMin;
	}

	/**
	 * @param startMin
	 *            要设置的 startMin
	 * 
	 */
	public void setStartMin(Integer startMin) {
		this.startMin = startMin;
	}

	/**
	 * @return endMin
	 */
	public Integer getEndMin() {
		return endMin;
	}

	/**
	 * @param endMin
	 *            要设置的 endMin
	 * 
	 */
	public void setEndMin(Integer endMin) {
		this.endMin = endMin;
	}

	/**
	 * @return rejects
	 */
	public Integer getRejects() {
		return rejects;
	}

	/**
	 * @param rejects
	 *            要设置的 rejects
	 * 
	 */
	public void setRejects(Integer rejects) {
		this.rejects = rejects;
	}

	/**
	 * @return bitsPieces
	 */
	public Integer getBitsPieces() {
		return bitsPieces;
	}

	/**
	 * @param bitsPieces
	 *            要设置的 bitsPieces
	 * 
	 */
	public void setBitsPieces(Integer bitsPieces) {
		this.bitsPieces = bitsPieces;
	}

	/**
	 * @return overdoes
	 */
	public Integer getOverdoes() {
		return overdoes;
	}

	/**
	 * @param overdoes
	 *            要设置的 overdoes
	 * 
	 */
	public void setOverdoes(Integer overdoes) {
		this.overdoes = overdoes;
	}

	/**
	 * @return machineCode
	 */
	public String getMachineCode() {
		return machineCode;
	}

	/**
	 * @param machineCode
	 *            要设置的 machineCode
	 * 
	 */
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

}
