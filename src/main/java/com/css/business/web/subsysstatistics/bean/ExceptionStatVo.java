package com.css.business.web.subsysstatistics.bean;

import java.util.Date;

/**
 * 异常统计报表VO
 * 
 * @author candy
 * 
 */
public class ExceptionStatVo {
	//机器code
	private String mac_code;
	// 机器名称
	private String mac_name;
	// 异常名称
	private String codename;
	// 异常信息
	private String me_info;
	// 异常时间
	private Date me_time;
	// 工序编码
	private String seq_code;
	// 工序名称
	private String seq_name;
	// 工单编码
	private String course_code;
	// 异常轴名称
	private String axis_name;
	// 异常参数
	private String exception_param;
	// 异常参数值
	private String exception_value;
	// 操作人
	private String agent_by;
	// 状态
	private String state;
	// 备注
	private String remark;

	public ExceptionStatVo() {
		super();
	}

	public ExceptionStatVo(String mac_name, String codename, String me_info,
			Date me_time, String seq_code, String course_code,
			String axis_name, String exception_param, String exception_value,
			String agent_by, String state, String remark) {
		super();
		this.mac_name = mac_name;
		this.codename = codename;
		this.me_info = me_info;
		this.me_time = me_time;
		this.seq_code = seq_code;
		this.course_code = course_code;
		this.axis_name = axis_name;
		this.exception_param = exception_param;
		this.exception_value = exception_value;
		this.agent_by = agent_by;
		this.state = state;
		this.remark = remark;
	}

	public String getMac_name() {
		if (mac_name ==null || mac_name =="") {
			return mac_code;
		}
		return mac_name;
	}

	public void setMac_name(String mac_name) {
		this.mac_name = mac_name;
	}

	public String getCodename() {
		return codename;
	}

	public void setCodename(String codename) {
		this.codename = codename;
	}

	public String getMe_info() {
		return me_info;
	}

	public void setMe_info(String me_info) {
		this.me_info = me_info;
	}

	public Date getMe_time() {
		return me_time;
	}

	public void setMe_time(Date me_time) {
		this.me_time = me_time;
	}

	public String getSeq_code() {
		return seq_code;
	}

	public void setSeq_code(String seq_code) {
		this.seq_code = seq_code;
	}

	public String getCourse_code() {
		return course_code;
	}

	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}

	public String getAxis_name() {
		return axis_name;
	}

	public void setAxis_name(String axis_name) {
		this.axis_name = axis_name;
	}

	public String getException_param() {
		return exception_param;
	}

	public void setException_param(String exception_param) {
		this.exception_param = exception_param;
	}

	public String getException_value() {
		return exception_value;
	}

	public void setException_value(String exception_value) {
		this.exception_value = exception_value;
	}

	public String getAgent_by() {
		return agent_by;
	}

	public void setAgent_by(String agent_by) {
		this.agent_by = agent_by;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return  seq_name
	 */
	public String getSeq_name() {
		return seq_name;
	}

	/**
	 *  @param seq_name 要设置的 seq_name 
	 *    
	 */
	public void setSeq_name(String seq_name) {
		this.seq_name = seq_name;
	}

	/**
	 * @return  mac_code
	 */
	public String getMac_code() {
		return mac_code;
	}

	/**
	 *  @param mac_code 要设置的 mac_code 
	 *    
	 */
	public void setMac_code(String mac_code) {
		this.mac_code = mac_code;
	}
	
	

}
