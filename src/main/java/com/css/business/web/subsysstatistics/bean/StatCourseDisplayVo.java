package com.css.business.web.subsysstatistics.bean;

/**
 * @Title: StatCourseDisplayVo.java
 * @Package com.css.business.web.subsysstatistics.bean
 * @Description: 生产令下工单的生产信息
 * @author rb
 * @date 2017年7月25日 下午2:30:09
 * @company SMTC
 */

public class StatCourseDisplayVo {

	/** 工单编号 */
	private String courseCode;
	/** 工序名称 */
	private String seqName; //不要这个
	/** 机台名称 */
	private String machineName;
	/** 开始时间 */
	private String startTime;
	/** 结束时间 */
	private String endTime;
	/** 平均线径 */
	private String avgLine;
	/** 最大线径 */
	private String maxLine;
	/** 最小线径 */
	private String minLine;
	/** 线径超过平均设定值的百分比 超过平均值百分之多少的 */
	private String overLine;
	/** 线径低于平均设定值的百分比 低于平均值百分之多少的 */
	private String lowLine;
	/** 工单状态 */
	private String state;
	/** 异常次数 */
	private String exCount;

	/**
	 * @return courseCode
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * @param courseCode
	 *            要设置的 courseCode
	 * 
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * @return seqName
	 */
	public String getSeqName() {
		return seqName;
	}

	/**
	 * @param seqName
	 *            要设置的 seqName
	 * 
	 */
	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	/**
	 * @return startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            要设置的 startTime
	 * 
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            要设置的 endTime
	 * 
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return avgLine
	 */
	public String getAvgLine() {
		return avgLine;
	}

	/**
	 * @param avgLine
	 *            要设置的 avgLine
	 * 
	 */
	public void setAvgLine(String avgLine) {
		this.avgLine = avgLine;
	}

	/**
	 * @return maxLine
	 */
	public String getMaxLine() {
		return maxLine;
	}

	/**
	 * @param maxLine
	 *            要设置的 maxLine
	 * 
	 */
	public void setMaxLine(String maxLine) {
		this.maxLine = maxLine;
	}

	/**
	 * @return minLine
	 */
	public String getMinLine() {
		return minLine;
	}

	/**
	 * @param minLine
	 *            要设置的 minLine
	 * 
	 */
	public void setMinLine(String minLine) {
		this.minLine = minLine;
	}

	/**
	 * @return overLine
	 */
	public String getOverLine() {
		return overLine;
	}

	/**
	 * @param overLine
	 *            要设置的 overLine
	 * 
	 */
	public void setOverLine(String overLine) {
		this.overLine = overLine;
	}

	/**
	 * @return lowLine
	 */
	public String getLowLine() {
		return lowLine;
	}

	/**
	 * @param lowLine
	 *            要设置的 lowLine
	 * 
	 */
	public void setLowLine(String lowLine) {
		this.lowLine = lowLine;
	}

	/**
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            要设置的 state
	 * 
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return  machineName
	 */
	public String getMachineName() {
		return machineName;
	}

	/**
	 *  @param machineName 要设置的 machineName 
	 *    
	 */
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	/**
	 * @return  exCount
	 */
	public String getExCount() {
		return exCount;
	}

	/**
	 *  @param exCount 要设置的 exCount 
	 *    
	 */
	public void setExCount(String exCount) {
		this.exCount = exCount;
	}

	
	
}
