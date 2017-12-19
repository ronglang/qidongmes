package com.css.business.web.subsysplan.vo;

import java.util.Date;

public class NeedPartPlaCourseVo {

	private String workcode;
	private String ggxh;
	private Integer count;
	private Date demanddate; // 交货日期

	public String getWorkcode() {
		return workcode;
	}

	public void setWorkcode(String workcode) {
		this.workcode = workcode;
	}

	public String getGgxh() {
		return ggxh;
	}

	public void setGgxh(String ggxh) {
		this.ggxh = ggxh;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getDemanddate() {
		return demanddate;
	}

	public void setDemanddate(Date demanddate) {
		this.demanddate = demanddate;
	}

	

}
