package com.css.common.web.apachemq.bean;

import java.io.Serializable;

/**
 * @TODO  :  用于机台异常提交
 * @author: 翟春磊
 * @DATE  : 2017年7月21日
 */
public class MauExceptionVO implements Serializable {
	private static final long serialVersionUID = 7111873032165184598L;
	
	private String code; //异常编码  (后续异常编码做成参数，两边一致)
	private String macCode; //机台编码
	private String meInfo; //异常信息 200长度
	//private Date meTime;
	private Long meTime; //异常发生时间
    private String seqCode;  //工序编码
	private String axisName; //出异常的轴名称
	private String exceptionParam; //出现异常的参数编码（编码后续统一。） 有值时填
	private String exceptionValue; //异常值 有值时填
	private String agentBy;  //操作人
	private String courseCode;  //当前工单
	private String proCraftCode;  // 产品工艺编码
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMacCode() {
		return macCode;
	}
	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}
	public String getMeInfo() {
		return meInfo;
	}
	public void setMeInfo(String meInfo) {
		this.meInfo = meInfo;
	}
	public Long getMeTime() {
		return meTime;
	}
	public void setMeTime(Long meTime) {
		this.meTime = meTime;
	}
	public String getSeqCode() {
		return seqCode;
	}
	public void setSeqCode(String seqCode) {
		this.seqCode = seqCode;
	}
	public String getAxisName() {
		return axisName;
	}
	public void setAxisName(String axisName) {
		this.axisName = axisName;
	}
	public String getExceptionParam() {
		return exceptionParam;
	}
	public void setExceptionParam(String exceptionParam) {
		this.exceptionParam = exceptionParam;
	}
	public String getExceptionValue() {
		return exceptionValue;
	}
	public void setExceptionValue(String exceptionValue) {
		this.exceptionValue = exceptionValue;
	}
	public String getAgentBy() {
		return agentBy;
	}
	public void setAgentBy(String agentBy) {
		this.agentBy = agentBy;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getProCraftCode() {
		return proCraftCode;
	}
	public void setProCraftCode(String proCraftCode) {
		this.proCraftCode = proCraftCode;
	}

}
