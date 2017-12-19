package com.css.business.web.subsysstatistics.bean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 
 * @Title: PlanDetialVo.java
 * @Package com.css.business.web.subsysstatistics.bean
 * @Description: 生产统计
 * @author rb
 * @date 2017年8月14日 上午11:16:11
 * @company SMTC
 */
public class PlanDetialVo {

	/** 产品规格型号 */
	private String proggxh;
	/** 生产令id */
	private Integer orderid;
	/** 生产令编号 */
	private String ordercode;
	/** 产品名称 */
	private String proname;
	/** 经过工序 */
	private List<String> proSeqs;
	/** 经过人员 */
	private Map<String, Map<String, String>> proPersons;
	/** 工作日期 */
	private Integer workday;
	/** 轴数 */
	private Integer axiscount;
	/** 机器编号 */
	private String maccode;
	/** 主操作 */
	private String mainby;
	/** 副操作 */
	private String viceby;
	/** 工序名称 */
	private String seqname;
	/** 工单编号 */
	private String coursecode;
	/** 生产状态 */
	private String productstate;
	/** 计划长度 */
	private String partlen;
	/** 轴名称 */
	private String axisname;
	/** 生产长度 */
	private BigDecimal semilen;
	/** 机台计划id */
	private Integer planid;

	// 原材料
	/** 原材料数量 */
	private BigDecimal materamount;
	/** 是否领料 */
	private String flag;
	/** 原料规格型号 */
	private String materggxh;
	/** 原料名称 */
	private String matername;
	/** 原料单位 */
	private String unit;

	/**
	 * @return proggxh
	 */
	public String getProggxh() {
		return proggxh;
	}

	/**
	 * @param proggxh
	 *            要设置的 proggxh
	 * 
	 */
	public void setProggxh(String proggxh) {
		this.proggxh = proggxh;
	}

	/**
	 * @return proname
	 */
	public String getProname() {
		return proname;
	}

	/**
	 * @param proname
	 *            要设置的 proname
	 * 
	 */
	public void setProname(String proname) {
		this.proname = proname;
	}

	/**
	 * @return proSeqs
	 */
	public List<String> getProSeqs() {
		return proSeqs;
	}

	/**
	 * @param proSeqs
	 *            要设置的 proSeqs
	 * 
	 */
	public void setProSeqs(List<String> proSeqs) {
		this.proSeqs = proSeqs;
	}

	/**
	 * @return proPersons
	 */
	public Map<String, Map<String, String>> getProPersons() {
		return proPersons;
	}

	/**
	 * @param proPersons
	 *            要设置的 proPersons
	 * 
	 */
	public void setProPersons(Map<String, Map<String, String>> proPersons) {
		this.proPersons = proPersons;
	}

	/**
	 * @return workday
	 */
	public Integer getWorkday() {
		return workday;
	}

	/**
	 * @param workday
	 *            要设置的 workday
	 * 
	 */
	public void setWorkday(Integer workday) {
		this.workday = workday;
	}

	/**
	 * @return axiscount
	 */
	public Integer getAxiscount() {
		return axiscount;
	}

	/**
	 * @param axiscount
	 *            要设置的 axiscount
	 * 
	 */
	public void setAxiscount(Integer axiscount) {
		this.axiscount = axiscount;
	}

	/**
	 * @return maccode
	 */
	public String getMaccode() {
		return maccode;
	}

	/**
	 * @param maccode
	 *            要设置的 maccode
	 * 
	 */
	public void setMaccode(String maccode) {
		this.maccode = maccode;
	}

	/**
	 * @return mainby
	 */
	public String getMainby() {
		return mainby;
	}

	/**
	 * @param mainby
	 *            要设置的 mainby
	 * 
	 */
	public void setMainby(String mainby) {
		this.mainby = mainby;
	}

	/**
	 * @return viceby
	 */
	public String getViceby() {
		return viceby;
	}

	/**
	 * @param viceby
	 *            要设置的 viceby
	 * 
	 */
	public void setViceby(String viceby) {
		this.viceby = viceby;
	}

	/**
	 * @return seqname
	 */
	public String getSeqname() {
		return seqname;
	}

	/**
	 * @param seqname
	 *            要设置的 seqname
	 * 
	 */
	public void setSeqname(String seqname) {
		this.seqname = seqname;
	}

	/**
	 * @return coursecode
	 */
	public String getCoursecode() {
		return coursecode;
	}

	/**
	 * @param coursecode
	 *            要设置的 coursecode
	 * 
	 */
	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}

	/**
	 * @return productstate
	 */
	public String getProductstate() {
		return productstate;
	}

	/**
	 * @param productstate
	 *            要设置的 productstate
	 * 
	 */
	public void setProductstate(String productstate) {
		this.productstate = productstate;
	}

	/**
	 * @return partlen
	 */
	public String getPartlen() {
		return partlen;
	}

	/**
	 * @param partlen
	 *            要设置的 partlen
	 * 
	 */
	public void setPartlen(String partlen) {
		this.partlen = partlen;
	}

	/**
	 * @return axisname
	 */
	public String getAxisname() {
		return axisname;
	}

	/**
	 * @param axisname
	 *            要设置的 axisname
	 * 
	 */
	public void setAxisname(String axisname) {
		this.axisname = axisname;
	}

	/**
	 * @return semilen
	 */
	public BigDecimal getSemilen() {
		return semilen;
	}

	/**
	 * @param semilen
	 *            要设置的 semilen
	 * 
	 */
	public void setSemilen(BigDecimal semilen) {
		this.semilen = semilen;
	}

	/**
	 * @return ordercode
	 */
	public String getOrdercode() {
		return ordercode;
	}

	/**
	 * @param ordercode
	 *            要设置的 ordercode
	 * 
	 */
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}

	/**
	 * @return orderid
	 */
	public Integer getOrderid() {
		return orderid;
	}

	/**
	 * @param orderid
	 *            要设置的 orderid
	 * 
	 */
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	/**
	 * @return planid
	 */
	public Integer getPlanid() {
		return planid;
	}

	/**
	 * @param planid
	 *            要设置的 planid
	 * 
	 */
	public void setPlanid(Integer planid) {
		this.planid = planid;
	}

	/**
	 * @return flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            要设置的 flag
	 * 
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return materggxh
	 */
	public String getMaterggxh() {
		return materggxh;
	}

	/**
	 * @param materggxh
	 *            要设置的 materggxh
	 * 
	 */
	public void setMaterggxh(String materggxh) {
		this.materggxh = materggxh;
	}

	/**
	 * @return matername
	 */
	public String getMatername() {
		return matername;
	}

	/**
	 * @param matername
	 *            要设置的 matername
	 * 
	 */
	public void setMatername(String matername) {
		this.matername = matername;
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

	/**
	 * @return materamount
	 */
	public BigDecimal getMateramount() {
		return materamount;
	}

	/**
	 * @param materamount
	 *            要设置的 materamount
	 * 
	 */
	public void setMateramount(BigDecimal materamount) {
		this.materamount = materamount;
	}

}
