package com.css.business.web.subsysmanu.vo;

import java.math.BigDecimal;

import javax.persistence.Column;

/**
 * 
 * @Title:MonthOEEVo.java
 * @Description:
 * @author RB
 * @company SMTC
 * @date 2017年10月20日下午2:51:40
 */
public class MonthOEEVo {
	/** 机台code */
	private String maccode;
	/** 工序名称 */
	private String seqname;
	/** 开机时间 */
	private Integer startmin;
	/** 停机时间 */
	private Integer endmin;
	/** 不良品 */
	private Integer rejects;
	/** 零碎品 */
	private Integer bits_pieces;
	/** 过量耗用品 */
	private Integer overdoes;
	/** 实际产出 */
	private BigDecimal factoutput;
	/** 满载输出 */
	private BigDecimal fullyoutput;
	/** 满速度满载率输出 PR */
	private Double ratespeed;
	/** 开机率 OR */
	private Double ratestart;
	/** 正品率 QR */
	private Double ratequality;
	/** OEE */
	@Column(name = "oee")
	private Double oee;
	/** 预设OEE */
	private Double preoee;
	/** 月份 */
	private Integer inmonth;
	/** 预设 */
	private Double set_month_pr;
	/** 预设 */
	private Double set_month_qr;
	/** 预设 */
	private Double set_month_or;

	public String getMaccode() {
		return maccode;
	}

	public void setMaccode(String maccode) {
		this.maccode = maccode;
	}

	public String getSeqname() {
		return seqname;
	}

	public void setSeqname(String seqname) {
		this.seqname = seqname;
	}

	public Integer getStartmin() {
		return startmin;
	}

	public void setStartmin(Integer startmin) {
		this.startmin = startmin;
	}

	public Integer getEndmin() {
		return endmin;
	}

	public void setEndmin(Integer endmin) {
		this.endmin = endmin;
	}

	public Integer getRejects() {
		return rejects;
	}

	public void setRejects(Integer rejects) {
		this.rejects = rejects;
	}

	public Integer getBits_pieces() {
		return bits_pieces;
	}

	public void setBits_pieces(Integer bits_pieces) {
		this.bits_pieces = bits_pieces;
	}

	public Integer getOverdoes() {
		return overdoes;
	}

	public void setOverdoes(Integer overdoes) {
		this.overdoes = overdoes;
	}

	public BigDecimal getFactoutput() {
		return factoutput;
	}

	public void setFactoutput(BigDecimal factoutput) {
		this.factoutput = factoutput;
	}

	public BigDecimal getFullyoutput() {
		return fullyoutput;
	}

	public void setFullyoutput(BigDecimal fullyoutput) {
		this.fullyoutput = fullyoutput;
	}

	public Double getRatespeed() {
		return ratespeed;
	}

	public void setRatespeed(Double ratespeed) {
		this.ratespeed = ratespeed;
	}

	public Double getRatestart() {
		return ratestart;
	}

	public void setRatestart(Double ratestart) {
		this.ratestart = ratestart;
	}

	public Double getRatequality() {
		return ratequality;
	}

	public void setRatequality(Double ratequality) {
		this.ratequality = ratequality;
	}

	public Double getOee() {
		return oee;
	}

	public void setOee(Double oee) {
		this.oee = oee;
	}

	public Double getPreoee() {
		return preoee;
	}

	public void setPreoee(Double preoee) {
		this.preoee = preoee;
	}

	public Integer getInmonth() {
		return inmonth;
	}

	public void setInmonth(Integer inmonth) {
		this.inmonth = inmonth;
	}

	public Double getSet_month_pr() {
		return set_month_pr;
	}

	public void setSet_month_pr(Double set_month_pr) {
		this.set_month_pr = set_month_pr;
	}

	public Double getSet_month_qr() {
		return set_month_qr;
	}

	public void setSet_month_qr(Double set_month_qr) {
		this.set_month_qr = set_month_qr;
	}

	public Double getSet_month_or() {
		return set_month_or;
	}

	public void setSet_month_or(Double set_month_or) {
		this.set_month_or = set_month_or;
	}

}
