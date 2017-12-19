package com.css.business.web.sysManage.bean;

public class ShouYeCouVO {
	   public String cou_task;//计划摘帽县数（任务数）
	   public String cou_towns;//计划摘帽县下乡镇个数
	   public String cou_result;// 已达标摘帽县数
	   public String cou_rate;//已达标摘帽县占比
	   public String cou_state;//计划摘帽时序进度：正常、滞后、严重滞后【绿、黄、红】
	   public String cou_pov;//贫困发生率低于3%摘帽县数
	   public String cou_pov_rate;//贫困发生率低于3%摘帽县占比
	   public String cou_school;//有标准中心校乡镇数
	   public String cou_school_rate;//有标准中心校乡镇占比
	   public String cou_hosp;//有达标卫生院乡镇数
	   public String cou_hosp_rate;//有达标卫生院乡镇占比
	   public String cou_ser ;//有便民服务中心乡镇数
	   public String cou_ser_rate;//有便民服务中心乡镇占比
	   
	public ShouYeCouVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShouYeCouVO(String cou_task, String cou_towns, String cou_result, String cou_rate, String cou_state,
			String cou_pov, String cou_pov_rate, String cou_school, String cou_school_rate, String cou_hosp,
			String cou_hosp_rate, String cou_ser, String cou_ser_rate) {
		super();
		this.cou_task = cou_task;
		this.cou_towns = cou_towns;
		this.cou_result = cou_result;
		this.cou_rate = cou_rate;
		this.cou_state = cou_state;
		this.cou_pov = cou_pov;
		this.cou_pov_rate = cou_pov_rate;
		this.cou_school = cou_school;
		this.cou_school_rate = cou_school_rate;
		this.cou_hosp = cou_hosp;
		this.cou_hosp_rate = cou_hosp_rate;
		this.cou_ser = cou_ser;
		this.cou_ser_rate = cou_ser_rate;
	}
	public String getCou_task() {
		return cou_task;
	}
	public void setCou_task(String cou_task) {
		this.cou_task = cou_task;
	}
	public String getCou_towns() {
		return cou_towns;
	}
	public void setCou_towns(String cou_towns) {
		this.cou_towns = cou_towns;
	}
	public String getCou_result() {
		return cou_result;
	}
	public void setCou_result(String cou_result) {
		this.cou_result = cou_result;
	}
	public String getCou_rate() {
		return cou_rate;
	}
	public void setCou_rate(String cou_rate) {
		this.cou_rate = cou_rate;
	}
	public String getCou_state() {
		return cou_state;
	}
	public void setCou_state(String cou_state) {
		this.cou_state = cou_state;
	}
	public String getCou_pov() {
		return cou_pov;
	}
	public void setCou_pov(String cou_pov) {
		this.cou_pov = cou_pov;
	}
	public String getCou_pov_rate() {
		return cou_pov_rate;
	}
	public void setCou_pov_rate(String cou_pov_rate) {
		this.cou_pov_rate = cou_pov_rate;
	}
	public String getCou_school() {
		return cou_school;
	}
	public void setCou_school(String cou_school) {
		this.cou_school = cou_school;
	}
	public String getCou_school_rate() {
		return cou_school_rate;
	}
	public void setCou_school_rate(String cou_school_rate) {
		this.cou_school_rate = cou_school_rate;
	}
	public String getCou_hosp() {
		return cou_hosp;
	}
	public void setCou_hosp(String cou_hosp) {
		this.cou_hosp = cou_hosp;
	}
	public String getCou_hosp_rate() {
		return cou_hosp_rate;
	}
	public void setCou_hosp_rate(String cou_hosp_rate) {
		this.cou_hosp_rate = cou_hosp_rate;
	}
	public String getCou_ser() {
		return cou_ser;
	}
	public void setCou_ser(String cou_ser) {
		this.cou_ser = cou_ser;
	}
	public String getCou_ser_rate() {
		return cou_ser_rate;
	}
	public void setCou_ser_rate(String cou_ser_rate) {
		this.cou_ser_rate = cou_ser_rate;
	}
	   
}
