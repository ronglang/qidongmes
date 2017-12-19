package com.css.business.web.sysManage.bean;

public class ShouYeWgypVO {
	public String wgyp_plan;//计划实施“五个一批”人次
    public String wgyp_result;//“五个一批”完成人数
    public String wgyp_rate;//“五个一批”完成占比
    public String wgyp_state;//“五个一批”时序进度：正常、滞后、严重滞后【绿、黄、红】

    //生产就业发展一批
    public String jysc_plan;//计划
    public String jysc_result;//完成
    public String jysc_rate;//占比
    //移民搬迁安置一批
    public String ymbq_plan;//计划
    public String ymbq_result;//完成
    public String ymbq_rate;// 占比
    //低保政策兜底一批
    public String dbdd_plan;//计划
    public String dbdd_result;//完成
    public String dbdd_rate;//占比
    //医疗救助一批
    public String yljz_plan;//计划
    public String yljz_result;//完成
    public String yljz_rate;//占比
    //灾后重建一批
    public String zhcj_plan;//计划
    public String zhcj_result;//完成
    public String zhcj_rate;//占比
	public String getWgyp_plan() {
		return wgyp_plan;
	}
	public void setWgyp_plan(String wgyp_plan) {
		this.wgyp_plan = wgyp_plan;
	}
	public String getWgyp_result() {
		return wgyp_result;
	}
	public void setWgyp_result(String wgyp_result) {
		this.wgyp_result = wgyp_result;
	}
	public String getWgyp_rate() {
		return wgyp_rate;
	}
	public void setWgyp_rate(String wgyp_rate) {
		this.wgyp_rate = wgyp_rate;
	}
	public String getWgyp_state() {
		return wgyp_state;
	}
	public void setWgyp_state(String wgyp_state) {
		this.wgyp_state = wgyp_state;
	}
	public String getJysc_plan() {
		return jysc_plan;
	}
	public void setJysc_plan(String jysc_plan) {
		this.jysc_plan = jysc_plan;
	}
	public String getJysc_result() {
		return jysc_result;
	}
	public void setJysc_result(String jysc_result) {
		this.jysc_result = jysc_result;
	}
	public String getJysc_rate() {
		return jysc_rate;
	}
	public void setJysc_rate(String jysc_rate) {
		this.jysc_rate = jysc_rate;
	}
	public String getYmbq_plan() {
		return ymbq_plan;
	}
	public void setYmbq_plan(String ymbq_plan) {
		this.ymbq_plan = ymbq_plan;
	}
	public String getYmbq_result() {
		return ymbq_result;
	}
	public void setYmbq_result(String ymbq_result) {
		this.ymbq_result = ymbq_result;
	}
	public String getYmbq_rate() {
		return ymbq_rate;
	}
	public void setYmbq_rate(String ymbq_rate) {
		this.ymbq_rate = ymbq_rate;
	}
	public String getDbdd_plan() {
		return dbdd_plan;
	}
	public void setDbdd_plan(String dbdd_plan) {
		this.dbdd_plan = dbdd_plan;
	}
	public String getDbdd_result() {
		return dbdd_result;
	}
	public void setDbdd_result(String dbdd_result) {
		this.dbdd_result = dbdd_result;
	}
	public String getDbdd_rate() {
		return dbdd_rate;
	}
	public void setDbdd_rate(String dbdd_rate) {
		this.dbdd_rate = dbdd_rate;
	}
	public String getYljz_plan() {
		return yljz_plan;
	}
	public void setYljz_plan(String yljz_plan) {
		this.yljz_plan = yljz_plan;
	}
	public String getYljz_result() {
		return yljz_result;
	}
	public void setYljz_result(String yljz_result) {
		this.yljz_result = yljz_result;
	}
	public String getYljz_rate() {
		return yljz_rate;
	}
	public void setYljz_rate(String yljz_rate) {
		this.yljz_rate = yljz_rate;
	}
	public String getZhcj_plan() {
		return zhcj_plan;
	}
	public void setZhcj_plan(String zhcj_plan) {
		this.zhcj_plan = zhcj_plan;
	}
	public String getZhcj_result() {
		return zhcj_result;
	}
	public void setZhcj_result(String zhcj_result) {
		this.zhcj_result = zhcj_result;
	}
	public String getZhcj_rate() {
		return zhcj_rate;
	}
	public void setZhcj_rate(String zhcj_rate) {
		this.zhcj_rate = zhcj_rate;
	}
	public ShouYeWgypVO(String wgyp_plan, String wgyp_result, String wgyp_rate, String wgyp_state, String jysc_plan,
			String jysc_result, String jysc_rate, String ymbq_plan, String ymbq_result, String ymbq_rate,
			String dbdd_plan, String dbdd_result, String dbdd_rate, String yljz_plan, String yljz_result,
			String yljz_rate, String zhcj_plan, String zhcj_result, String zhcj_rate) {
		super();
		this.wgyp_plan = wgyp_plan;
		this.wgyp_result = wgyp_result;
		this.wgyp_rate = wgyp_rate;
		this.wgyp_state = wgyp_state;
		this.jysc_plan = jysc_plan;
		this.jysc_result = jysc_result;
		this.jysc_rate = jysc_rate;
		this.ymbq_plan = ymbq_plan;
		this.ymbq_result = ymbq_result;
		this.ymbq_rate = ymbq_rate;
		this.dbdd_plan = dbdd_plan;
		this.dbdd_result = dbdd_result;
		this.dbdd_rate = dbdd_rate;
		this.yljz_plan = yljz_plan;
		this.yljz_result = yljz_result;
		this.yljz_rate = yljz_rate;
		this.zhcj_plan = zhcj_plan;
		this.zhcj_result = zhcj_result;
		this.zhcj_rate = zhcj_rate;
	}
	public ShouYeWgypVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	   
}
