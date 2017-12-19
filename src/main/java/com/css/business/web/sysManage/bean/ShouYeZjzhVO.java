package com.css.business.web.sysManage.bean;

public class ShouYeZjzhVO {
	public String zjzh_plan;//计划整合资金亿元
    public String zjzh_result;//已到位资金亿元
    public String zjzh_rate;//已到位资金占比
    public String zjzh_state;//时序进度：正常、滞后、严重滞后【绿、黄、红】
    public String zjzh_xm_plan;//整合资金项目计划总投资
    public String zjzh_xm_result;//整合资金项目已执行亿元
    public String zjzh_xm_rate;//整合资金项目已执行占比
    public String zjzh_xm_state;//整合资金项目已执行时序进度：正常、滞后、严重滞后【绿、黄、红】 
	public ShouYeZjzhVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShouYeZjzhVO(String zjzh_plan, String zjzh_result, String zjzh_rate, String zjzh_state, String zjzh_xm_plan,
			String zjzh_xm_result, String zjzh_xm_rate, String zjzh_xm_state) {
		super();
		this.zjzh_plan = zjzh_plan;
		this.zjzh_result = zjzh_result;
		this.zjzh_rate = zjzh_rate;
		this.zjzh_state = zjzh_state;
		this.zjzh_xm_plan = zjzh_xm_plan;
		this.zjzh_xm_result = zjzh_xm_result;
		this.zjzh_xm_rate = zjzh_xm_rate;
		this.zjzh_xm_state = zjzh_xm_state;
	}
	public String getZjzh_plan() {
		return zjzh_plan;
	}
	public void setZjzh_plan(String zjzh_plan) {
		this.zjzh_plan = zjzh_plan;
	}
	public String getZjzh_result() {
		return zjzh_result;
	}
	public void setZjzh_result(String zjzh_result) {
		this.zjzh_result = zjzh_result;
	}
	public String getZjzh_rate() {
		return zjzh_rate;
	}
	public void setZjzh_rate(String zjzh_rate) {
		this.zjzh_rate = zjzh_rate;
	}
	public String getZjzh_state() {
		return zjzh_state;
	}
	public void setZjzh_state(String zjzh_state) {
		this.zjzh_state = zjzh_state;
	}
	public String getZjzh_xm_plan() {
		return zjzh_xm_plan;
	}
	public void setZjzh_xm_plan(String zjzh_xm_plan) {
		this.zjzh_xm_plan = zjzh_xm_plan;
	}
	public String getZjzh_xm_result() {
		return zjzh_xm_result;
	}
	public void setZjzh_xm_result(String zjzh_xm_result) {
		this.zjzh_xm_result = zjzh_xm_result;
	}
	public String getZjzh_xm_rate() {
		return zjzh_xm_rate;
	}
	public void setZjzh_xm_rate(String zjzh_xm_rate) {
		this.zjzh_xm_rate = zjzh_xm_rate;
	}
	public String getZjzh_xm_state() {
		return zjzh_xm_state;
	}
	public void setZjzh_xm_state(String zjzh_xm_state) {
		this.zjzh_xm_state = zjzh_xm_state;
	}
}
