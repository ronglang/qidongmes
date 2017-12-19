package com.css.business.web.sysManage.bean;

public class ShouYeViltpVO {
	 public String viltp_vil_task;//预脱贫村个数
     public String viltp_plan;//村项目规划总投资 亿元
     public String viltp_result;//已完成投资 亿元
     public String viltp_rate;//已完成投资占比
     public String viltp_state;//时序进度：正常、滞后、严重滞后【绿、黄、红】
     public String viltp_basic;//基础设施投资 亿元
     public String viltp_cyfz;//产业发展投资 亿元
     public String viltp_ggfw;//公共服务建设投资 亿元
     public String viltp_sthj;//生态环境建设投资 亿元
	public ShouYeViltpVO(String viltp_vil_task, String viltp_plan, String viltp_result, String viltp_rate,
			String viltp_state, String viltp_basic, String viltp_cyfz, String viltp_ggfw, String viltp_sthj) {
		super();
		this.viltp_vil_task = viltp_vil_task;
		this.viltp_plan = viltp_plan;
		this.viltp_result = viltp_result;
		this.viltp_rate = viltp_rate;
		this.viltp_state = viltp_state;
		this.viltp_basic = viltp_basic;
		this.viltp_cyfz = viltp_cyfz;
		this.viltp_ggfw = viltp_ggfw;
		this.viltp_sthj = viltp_sthj;
	}
	public ShouYeViltpVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getViltp_vil_task() {
		return viltp_vil_task;
	}
	public void setViltp_vil_task(String viltp_vil_task) {
		this.viltp_vil_task = viltp_vil_task;
	}
	public String getViltp_plan() {
		return viltp_plan;
	}
	public void setViltp_plan(String viltp_plan) {
		this.viltp_plan = viltp_plan;
	}
	public String getViltp_result() {
		return viltp_result;
	}
	public void setViltp_result(String viltp_result) {
		this.viltp_result = viltp_result;
	}
	public String getViltp_rate() {
		return viltp_rate;
	}
	public void setViltp_rate(String viltp_rate) {
		this.viltp_rate = viltp_rate;
	}
	public String getViltp_state() {
		return viltp_state;
	}
	public void setViltp_state(String viltp_state) {
		this.viltp_state = viltp_state;
	}
	public String getViltp_basic() {
		return viltp_basic;
	}
	public void setViltp_basic(String viltp_basic) {
		this.viltp_basic = viltp_basic;
	}
	public String getViltp_cyfz() {
		return viltp_cyfz;
	}
	public void setViltp_cyfz(String viltp_cyfz) {
		this.viltp_cyfz = viltp_cyfz;
	}
	public String getViltp_ggfw() {
		return viltp_ggfw;
	}
	public void setViltp_ggfw(String viltp_ggfw) {
		this.viltp_ggfw = viltp_ggfw;
	}
	public String getViltp_sthj() {
		return viltp_sthj;
	}
	public void setViltp_sthj(String viltp_sthj) {
		this.viltp_sthj = viltp_sthj;
	}
	   
}
