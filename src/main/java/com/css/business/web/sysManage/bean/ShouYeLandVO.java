package com.css.business.web.sysManage.bean;

import java.math.BigInteger;
import java.sql.Date;

public class ShouYeLandVO {
	private Date stime;
	private BigInteger sort;

	public BigInteger getSort() {
		return sort;
	}

	public void setSort(BigInteger sort) {
		this.sort = sort;
	}

	public Date getStime() {
		return stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

}
