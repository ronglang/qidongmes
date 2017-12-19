package com.css.business.web.sysManage.bean;

public class ShouYeTzVO {
	public String tz_plan;//有未脱贫贫困户 户
    public String tz_result;//已建立台账 户
    public String tz_rate;//已建立台账户数占比
    public String tz_upbase;//人均纯收入高于扶贫标准 户
    public String tz_upbase_rate;//人均纯收入高于扶贫标准户数占比
    public String tz_rjxjsr;//贫困户人均现金收入 元
    public String tz_rjxjzc;//人均现金支出 元
    public String tz_incapt;//实现人均纯收入 元
	public String getTz_plan() {
		return tz_plan;
	}
	public void setTz_plan(String tz_plan) {
		this.tz_plan = tz_plan;
	}
	public String getTz_result() {
		return tz_result;
	}
	public void setTz_result(String tz_result) {
		this.tz_result = tz_result;
	}
	public String getTz_rate() {
		return tz_rate;
	}
	public void setTz_rate(String tz_rate) {
		this.tz_rate = tz_rate;
	}
	public String getTz_upbase() {
		return tz_upbase;
	}
	public void setTz_upbase(String tz_upbase) {
		this.tz_upbase = tz_upbase;
	}
	public String getTz_upbase_rate() {
		return tz_upbase_rate;
	}
	public void setTz_upbase_rate(String tz_upbase_rate) {
		this.tz_upbase_rate = tz_upbase_rate;
	}
	public String getTz_rjxjsr() {
		return tz_rjxjsr;
	}
	public void setTz_rjxjsr(String tz_rjxjsr) {
		this.tz_rjxjsr = tz_rjxjsr;
	}
	public String getTz_rjxjzc() {
		return tz_rjxjzc;
	}
	public void setTz_rjxjzc(String tz_rjxjzc) {
		this.tz_rjxjzc = tz_rjxjzc;
	}
	public String getTz_incapt() {
		return tz_incapt;
	}
	public void setTz_incapt(String tz_incapt) {
		this.tz_incapt = tz_incapt;
	}
	public ShouYeTzVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShouYeTzVO(String tz_plan, String tz_result, String tz_rate, String tz_upbase, String tz_upbase_rate,
			String tz_rjxjsr, String tz_rjxjzc, String tz_incapt) {
		super();
		this.tz_plan = tz_plan;
		this.tz_result = tz_result;
		this.tz_rate = tz_rate;
		this.tz_upbase = tz_upbase;
		this.tz_upbase_rate = tz_upbase_rate;
		this.tz_rjxjsr = tz_rjxjsr;
		this.tz_rjxjzc = tz_rjxjzc;
		this.tz_incapt = tz_incapt;
	}
}
