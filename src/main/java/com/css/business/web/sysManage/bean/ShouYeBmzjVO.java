package com.css.business.web.sysManage.bean;

public class ShouYeBmzjVO {
	   public String bmzj_plan;//十七个专项年度计划总投资 亿元
	   public String bmzj_result;//已完成投资 亿元
	   public String bmzj_rate;//已完成投资占比
	   public String bmzj_state;//时序进度：正常、滞后、严重滞后【绿、黄、红】
	   public String bmzj_green;//绿灯项目
	   public String bmzj_green_rate;//绿灯标准值
	   public String bmzj_yellow;//黄灯项目
	   public String bmzj_yellow_rate;//黄灯标准值
	   public String bmzj_red;// 红灯项目
	   public String bmzj_red_rate;//红灯标准值
	public ShouYeBmzjVO(String bmzj_plan, String bmzj_result, String bmzj_rate, String bmzj_state, String bmzj_green,
			String bmzj_green_rate, String bmzj_yellow, String bmzj_yellow_rate, String bmzj_red,
			String bmzj_red_rate) {
		super();
		this.bmzj_plan = bmzj_plan;
		this.bmzj_result = bmzj_result;
		this.bmzj_rate = bmzj_rate;
		this.bmzj_state = bmzj_state;
		this.bmzj_green = bmzj_green;
		this.bmzj_green_rate = bmzj_green_rate;
		this.bmzj_yellow = bmzj_yellow;
		this.bmzj_yellow_rate = bmzj_yellow_rate;
		this.bmzj_red = bmzj_red;
		this.bmzj_red_rate = bmzj_red_rate;
	}
	public ShouYeBmzjVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getBmzj_plan() {
		return bmzj_plan;
	}
	public void setBmzj_plan(String bmzj_plan) {
		this.bmzj_plan = bmzj_plan;
	}
	public String getBmzj_result() {
		return bmzj_result;
	}
	public void setBmzj_result(String bmzj_result) {
		this.bmzj_result = bmzj_result;
	}
	public String getBmzj_rate() {
		return bmzj_rate;
	}
	public void setBmzj_rate(String bmzj_rate) {
		this.bmzj_rate = bmzj_rate;
	}
	public String getBmzj_state() {
		return bmzj_state;
	}
	public void setBmzj_state(String bmzj_state) {
		this.bmzj_state = bmzj_state;
	}
	public String getBmzj_green() {
		return bmzj_green;
	}
	public void setBmzj_green(String bmzj_green) {
		this.bmzj_green = bmzj_green;
	}
	public String getBmzj_green_rate() {
		return bmzj_green_rate;
	}
	public void setBmzj_green_rate(String bmzj_green_rate) {
		this.bmzj_green_rate = bmzj_green_rate;
	}
	public String getBmzj_yellow() {
		return bmzj_yellow;
	}
	public void setBmzj_yellow(String bmzj_yellow) {
		this.bmzj_yellow = bmzj_yellow;
	}
	public String getBmzj_yellow_rate() {
		return bmzj_yellow_rate;
	}
	public void setBmzj_yellow_rate(String bmzj_yellow_rate) {
		this.bmzj_yellow_rate = bmzj_yellow_rate;
	}
	public String getBmzj_red() {
		return bmzj_red;
	}
	public void setBmzj_red(String bmzj_red) {
		this.bmzj_red = bmzj_red;
	}
	public String getBmzj_red_rate() {
		return bmzj_red_rate;
	}
	public void setBmzj_red_rate(String bmzj_red_rate) {
		this.bmzj_red_rate = bmzj_red_rate;
	}
	   
}
