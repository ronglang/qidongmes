package com.css.business.web.sysManage.bean;

public class ShouYeLpfpVO {
	 public String lpfp_pian;//计划实施片数
     public String lpfp_vil;//覆盖村数
     public String lpfp_qd;//已启动建设村数
     public String lpfp_qd_rate;//已启动建设村占比
     public String lpfp_task;//计划总投资 亿元
     public String lpfp_result;//已完成投资 亿元
     public String lpfp_rate;//已完成投资占比
     public String lpfp_state;//已完成投资时序进度：正常、滞后、严重滞后【绿、黄、红】
     public String lpfp_basic;//基础设施投资 亿元
     public String lpfp_cyfz;//产业发展投资 亿元
     public String lpfp_ggfw;//公共服务建设投资 亿元
     public String lpfp_sthj;//生态环境建设投资 亿元 
	public ShouYeLpfpVO(String lpfp_pian, String lpfp_vil, String lpfp_qd, String lpfp_qd_rate, String lpfp_task,
			String lpfp_result, String lpfp_rate, String lpfp_state, String lpfp_basic, String lpfp_cyfz,
			String lpfp_ggfw, String lpfp_sthj) {
		super();
		this.lpfp_pian = lpfp_pian;
		this.lpfp_vil = lpfp_vil;
		this.lpfp_qd = lpfp_qd;
		this.lpfp_qd_rate = lpfp_qd_rate;
		this.lpfp_task = lpfp_task;
		this.lpfp_result = lpfp_result;
		this.lpfp_rate = lpfp_rate;
		this.lpfp_state = lpfp_state;
		this.lpfp_basic = lpfp_basic;
		this.lpfp_cyfz = lpfp_cyfz;
		this.lpfp_ggfw = lpfp_ggfw;
		this.lpfp_sthj = lpfp_sthj;
	}
	public ShouYeLpfpVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getLpfp_pian() {
		return lpfp_pian;
	}
	public void setLpfp_pian(String lpfp_pian) {
		this.lpfp_pian = lpfp_pian;
	}
	public String getLpfp_vil() {
		return lpfp_vil;
	}
	public void setLpfp_vil(String lpfp_vil) {
		this.lpfp_vil = lpfp_vil;
	}
	public String getLpfp_qd() {
		return lpfp_qd;
	}
	public void setLpfp_qd(String lpfp_qd) {
		this.lpfp_qd = lpfp_qd;
	}
	public String getLpfp_qd_rate() {
		return lpfp_qd_rate;
	}
	public void setLpfp_qd_rate(String lpfp_qd_rate) {
		this.lpfp_qd_rate = lpfp_qd_rate;
	}
	public String getLpfp_task() {
		return lpfp_task;
	}
	public void setLpfp_task(String lpfp_task) {
		this.lpfp_task = lpfp_task;
	}
	public String getLpfp_result() {
		return lpfp_result;
	}
	public void setLpfp_result(String lpfp_result) {
		this.lpfp_result = lpfp_result;
	}
	public String getLpfp_rate() {
		return lpfp_rate;
	}
	public void setLpfp_rate(String lpfp_rate) {
		this.lpfp_rate = lpfp_rate;
	}
	public String getLpfp_state() {
		return lpfp_state;
	}
	public void setLpfp_state(String lpfp_state) {
		this.lpfp_state = lpfp_state;
	}
	public String getLpfp_basic() {
		return lpfp_basic;
	}
	public void setLpfp_basic(String lpfp_basic) {
		this.lpfp_basic = lpfp_basic;
	}
	public String getLpfp_cyfz() {
		return lpfp_cyfz;
	}
	public void setLpfp_cyfz(String lpfp_cyfz) {
		this.lpfp_cyfz = lpfp_cyfz;
	}
	public String getLpfp_ggfw() {
		return lpfp_ggfw;
	}
	public void setLpfp_ggfw(String lpfp_ggfw) {
		this.lpfp_ggfw = lpfp_ggfw;
	}
	public String getLpfp_sthj() {
		return lpfp_sthj;
	}
	public void setLpfp_sthj(String lpfp_sthj) {
		this.lpfp_sthj = lpfp_sthj;
	}
	   
}
