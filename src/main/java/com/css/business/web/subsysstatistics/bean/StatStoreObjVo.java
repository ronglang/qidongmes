package com.css.business.web.subsysstatistics.bean;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @Title: StatStoreObjVo.java
 * @Package com.css.business.web.subsysstatistics.bean
 * @Description: 总库存的vo
 * @author rb
 * @date 2017年8月2日 上午10:10:18
 * @company SMTC
 */

public class StatStoreObjVo {
	/** 规格型号 */
	private String ggxh;
	/** 名称 */
	private String name2;
	/** 总量 */
	private BigDecimal allcount;
	/** 单位 */
	private String unit;
	/** 某时间段的入库总数量 */
	private BigDecimal incount;
	/** 某时间段的出库总数量 */
	private BigDecimal outcount;
	/** 颜色 */
	private String color;
	/** 出库单 */
	private String pickcode;
	/** 地点 */
	private String address;
	/** 创建时间 */
	private Date createdate;
	/** 数量(详情领料出入库的数量) */
	private BigDecimal count;
	// 出库时间
	private String in_date;
	// 入库时间
	private String out_date;
	// 出入库签收人
	private String signer;
	// 出入库质检报告编码
	private String report_code;
	/** 出库或者入库数量 */
	private Double acount;

	/**
	 * @return ggxh
	 */
	public String getGgxh() {
		return ggxh;
	}

	/**
	 * @param ggxh
	 *            要设置的 ggxh
	 * 
	 */
	public void setGgxh(String ggxh) {
		this.ggxh = ggxh;
	}

	/**
	 * @return name2
	 */
	public String getName2() {
		return name2;
	}

	/**
	 * @param name2
	 *            要设置的 name2
	 * 
	 */
	public void setName2(String name2) {
		this.name2 = name2;
	}

	/**
	 * @return totalcount
	 */
	public BigDecimal getAllcount() {
		return allcount;
	}

	/**
	 * @param totalcount
	 *            要设置的 totalcount
	 * 
	 */
	public void setAllcount(BigDecimal allcount) {
		this.allcount = allcount;
	}

	/**
	 * @return unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *            要设置的 unit
	 * 
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getIncount() {
		return incount;
	}

	public void setIncount(BigDecimal incount) {
		this.incount = incount;
	}

	public BigDecimal getOutcount() {
		return outcount;
	}

	public void setOutcount(BigDecimal outcount) {
		this.outcount = outcount;
	}

	/**
	 * @return color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color
	 *            要设置的 color
	 * 
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return pickcode
	 */
	public String getPickcode() {
		return pickcode;
	}

	/**
	 * @param pickcode
	 *            要设置的 pickcode
	 * 
	 */
	public void setPickcode(String pickcode) {
		this.pickcode = pickcode;
	}

	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            要设置的 address
	 * 
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	/**
	 * @return count
	 */
	public BigDecimal getCount() {
		return count;
	}

	/**
	 * @param count
	 *            要设置的 count
	 * 
	 */
	public void setCount(BigDecimal count) {
		this.count = count;
	}

	public String getIn_date() {
		return in_date;
	}

	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}

	public String getOut_date() {
		return out_date;
	}

	public void setOut_date(String out_date) {
		this.out_date = out_date;
	}

	public String getSigner() {
		return signer;
	}

	public void setSigner(String signer) {
		this.signer = signer;
	}

	public String getReport_code() {
		return report_code;
	}

	public void setReport_code(String report_code) {
		this.report_code = report_code;
	}

	/**
	 * @return acount
	 */
	public Double getAcount() {
		return acount;
	}

	/**
	 * @param acount
	 *            要设置的 acount
	 * 
	 */
	public void setAcount(Double acount) {
		this.acount = acount;
	}

}
