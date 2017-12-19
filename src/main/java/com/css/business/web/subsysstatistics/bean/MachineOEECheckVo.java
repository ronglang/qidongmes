package com.css.business.web.subsysstatistics.bean;

import java.util.Date;

/**
 * 机台实际的生产能力相对于理论产能的比率OEE检测
 * 
 * @author candy
 * 
 */
public class MachineOEECheckVo {
	// 机台名称
	private String mac_name;
	// 实际全部产出量
	private Double fact_count;
	// 不良品
	private Double bad_count;
	// 开机时间
	private Date action_time;
	// 停机时间
	private Date stop_time;
	// 满载速度
	private Integer theory_speed;
	// 理论产值
	private Double theory_count;
	// 时间稼动率
	private Double time_activation;
	// 性能稼动率
	private Double nature_activation;
	// 良品率
	private Double rty;
	// 生产能力相对于理论产能的比率
	private Double oee;

	/**
	 * @return mac_name
	 */
	public String getMac_name() {
		return mac_name;
	}

	/**
	 * @param mac_name
	 *            要设置的 mac_name
	 * 
	 */
	public void setMac_name(String mac_name) {
		this.mac_name = mac_name;
	}

	/**
	 * @return fact_count
	 */
	public Double getFact_count() {
		return fact_count;
	}

	/**
	 * @param fact_count
	 *            要设置的 fact_count
	 * 
	 */
	public void setFact_count(Double fact_count) {
		this.fact_count = fact_count;
	}

	/**
	 * @return bad_count
	 */
	public Double getBad_count() {
		return bad_count;
	}

	/**
	 * @param bad_count
	 *            要设置的 bad_count
	 * 
	 */
	public void setBad_count(Double bad_count) {
		this.bad_count = bad_count;
	}

	/**
	 * @return action_time
	 */
	public Date getAction_time() {
		return action_time;
	}

	/**
	 * @param action_time
	 *            要设置的 action_time
	 * 
	 */
	public void setAction_time(Date action_time) {
		this.action_time = action_time;
	}

	/**
	 * @return stop_time
	 */
	public Date getStop_time() {
		return stop_time;
	}

	/**
	 * @param stop_time
	 *            要设置的 stop_time
	 * 
	 */
	public void setStop_time(Date stop_time) {
		this.stop_time = stop_time;
	}

	/**
	 * @return theory_speed
	 */
	public Integer getTheory_speed() {
		return theory_speed;
	}

	/**
	 * @param theory_speed
	 *            要设置的 theory_speed
	 * 
	 */
	public void setTheory_speed(Integer theory_speed) {
		this.theory_speed = theory_speed;
	}

	/**
	 * @return theory_count
	 */
	public Double getTheory_count() {
		return theory_count;
	}

	/**
	 * @param theory_count
	 *            要设置的 theory_count
	 * 
	 */
	public void setTheory_count(Double theory_count) {
		this.theory_count = theory_count;
	}

	/**
	 * @return time_activation
	 */
	public Double getTime_activation() {
		return time_activation;
	}

	/**
	 * @param time_activation
	 *            要设置的 time_activation
	 * 
	 */
	public void setTime_activation(Double time_activation) {
		this.time_activation = time_activation;
	}

	/**
	 * @return nature_activation
	 */
	public Double getNature_activation() {
		return nature_activation;
	}

	/**
	 * @param nature_activation
	 *            要设置的 nature_activation
	 * 
	 */
	public void setNature_activation(Double nature_activation) {
		this.nature_activation = nature_activation;
	}

	/**
	 * @return rty
	 */
	public Double getRty() {
		return rty;
	}

	/**
	 * @param rty
	 *            要设置的 rty
	 * 
	 */
	public void setRty(Double rty) {
		this.rty = rty;
	}

	/**
	 * @return oee
	 */
	public Double getOee() {
		return oee;
	}

	/**
	 * @param oee
	 *            要设置的 oee
	 * 
	 */
	public void setOee(Double oee) {
		this.oee = oee;
	}

}
