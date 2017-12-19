package com.css.business.web.subsysplan.plaManage.bean;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlaProductOrderSeqHourseVo {

	private Integer id;
	private Integer plaorderid;
	private String macname;
	private String maccode;
	private String axisname;
	private String seqname;
	private String proggxh;
	private String procraftcode;
	private Integer partlen;
	private String lenunit;
	private String employeeidmain;
	private String employeeidvice;
	private Date startdate;
	private Date enddate;
	private BigDecimal seqhours;
	private Integer workday;
	private String createby;
	private Date createdate;
	private BigDecimal hours; // 机台负荷

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPlaorderid() {
		return plaorderid;
	}

	public void setPlaorderid(Integer plaorderid) {
		this.plaorderid = plaorderid;
	}

	public String getAxisname() {
		return axisname;
	}

	public void setAxisname(String axisname) {
		this.axisname = axisname;
	}

	public String getProggxh() {
		return proggxh;
	}

	public void setProggxh(String proggxh) {
		this.proggxh = proggxh;
	}

	public String getProcraftcode() {
		return procraftcode;
	}

	public void setProcraftcode(String procraftcode) {
		this.procraftcode = procraftcode;
	}

	public Integer getPartlen() {
		return partlen;
	}

	public void setPartlen(Integer partlen) {
		this.partlen = partlen;
	}

	public String getLenunit() {
		return lenunit;
	}

	public void setLenunit(String lenunit) {
		this.lenunit = lenunit;
	}

	public String getEmployeeidmain() {
		return employeeidmain;
	}

	public void setEmployeeidmain(String employeeidmain) {
		this.employeeidmain = employeeidmain;
	}

	public String getEmployeeidvice() {
		return employeeidvice;
	}

	public void setEmployeeidvice(String employeeidvice) {
		this.employeeidvice = employeeidvice;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public BigDecimal getSeqhours() {
		return seqhours;
	}

	public void setSeqhours(BigDecimal seqhours) {
		this.seqhours = seqhours;
	}

	public Integer getWorkday() {
		return workday;
	}

	public void setWorkday(Integer workday) {
		this.workday = workday;
	}

	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	/**
	 * 前台显示用的workDay
	 * 
	 * @return
	 */
	public Date getWorkDay(int a) {
		/* Date wd = workDay; */
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int mounth = 0, day = 0;
		String years = "", mounths = "", days = "";
		day = workday % 100;
		years += workday % 10000;
		mounth = workday / 100 % 100;
		if (day < 10) {
			days = "-0" + day;
		} else {
			days = "-" + day;
		}
		if (mounth < 10) {
			mounths = "-0" + mounth;
		} else {
			mounths = "-" + mounth;
		}
		String str = years + mounths + days;
		try {
			Date date = df.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getSeqname() {
		return seqname;
	}

	public void setSeqname(String seqname) {
		this.seqname = seqname;
	}

	public String getMacname() {
		return macname;
	}

	public void setMacname(String macname) {
		this.macname = macname;
	}

	public BigDecimal getHours() {
		return hours;
	}

	public void setHours(BigDecimal hours) {
		this.hours = hours;
	}

	public String getMaccode() {
		return maccode;
	}

	public void setMaccode(String maccode) {
		this.maccode = maccode;
	}

}
