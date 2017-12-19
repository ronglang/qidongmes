package com.css.business.web.subsysplan.plaManage.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.css.business.web.subsysmanu.bean.MauMachine;

/**
 * @Title: PlaMachineDisplayVo.java
 * @Package com.css.business.web.subsysplan.plaManage.bean
 * @Description: 生产电子看板计划进度VO
 * @author rb
 * @date 2017年7月12日 下午5:01:56
 * @company SMTC
 */

public class PlaMachineDisplayVo {
	/** 机台id */
	private Integer machineid;
	/** 机台名称 */
	private String machinename;
	/** 计划完成时间 */
	private Timestamp planendtime;
	/** 实际完成时间 */
	private Timestamp factendtime;
	/** 工单编号 */
	private String coursecode;
	/** 周计划id */
	private Integer weekplanid;
	/** 生产令编号 */
	private String ordercode;
	/** 计划生产长度 */
	private String partlen;
	/** 实际生产长度 */
	private BigDecimal semiproductlen;
	/** 完成百分比 */
	private String comptempl;
	/** echart 柱状图图例 */
	private List<String> legends;
	/** echart 柱状图数据 */
	private List<String> dataList;
	/** echart柱状图，堆叠的的数据 */
	private Map<String, List<String>> mDataList;
	/** echart 柱状图X轴 */
	private List<String> areas;
	/* 当前机台信息 */
	private MauMachine mauMachine;

	/**
	 * @return machineid
	 */
	public Integer getMachineid() {
		return machineid;
	}

	/**
	 * @param machineid
	 *            要设置的 machineid
	 * 
	 */
	public void setMachineid(Integer machineid) {
		this.machineid = machineid;
	}

	/**
	 * @return machinename
	 */
	public String getMachinename() {
		return machinename;
	}

	/**
	 * @param machinename
	 *            要设置的 machinename
	 * 
	 */
	public void setMachinename(String machinename) {
		this.machinename = machinename;
	}

	/**
	 * @return planendtime
	 */
	public Timestamp getPlanendtime() {
		return planendtime;
	}

	/**
	 * @param planendtime
	 *            要设置的 planendtime
	 * 
	 */
	public void setPlanendtime(Timestamp planendtime) {
		this.planendtime = planendtime;
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
	 * @return weekplanid
	 */
	public Integer getWeekplanid() {
		return weekplanid;
	}

	/**
	 * @param weekplanid
	 *            要设置的 weekplanid
	 * 
	 */
	public void setWeekplanid(Integer weekplanid) {
		this.weekplanid = weekplanid;
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
	 * @return semiproductlen
	 */
	public BigDecimal getSemiproductlen() {
		return semiproductlen;
	}

	/**
	 * @param semiproductlen
	 *            要设置的 semiproductlen
	 * 
	 */
	public void setSemiproductlen(BigDecimal semiproductlen) {
		this.semiproductlen = semiproductlen;
	}

	/**
	 * @return comptempl
	 */
	public String getComptempl() {
		if (semiproductlen == null) {
			return "0";
		} else if (semiproductlen != null && partlen != null) {
			BigDecimal divide = semiproductlen.divide(new BigDecimal(partlen),
					3, RoundingMode.HALF_UP);
			BigDecimal result = new BigDecimal(Double.toString(100))
					.multiply(divide);
			String subResult = result.toString().substring(0,
					result.toString().indexOf(".") + 2);
			return subResult;
		} else {
			return null;
		}
		// return comptempl;
	}

	/**
	 * @param comptempl
	 *            要设置的 comptempl
	 * 
	 */
	public void setComptempl(String comptempl) {
		this.comptempl = comptempl;
	}

	/**
	 * @return legends
	 */
	public List<String> getLegends() {
		return legends;
	}

	/**
	 * @param legends
	 *            要设置的 legends
	 * 
	 */
	public void setLegends(List<String> legends) {
		this.legends = legends;
	}

	/**
	 * @return dataList
	 */
	public List<String> getDataList() {
		return dataList;
	}

	/**
	 * @param dataList
	 *            要设置的 dataList
	 * 
	 */
	public void setDataList(List<String> dataList) {
		this.dataList = dataList;
	}

	/**
	 * @return areas
	 */
	public List<String> getAreas() {
		return areas;
	}

	/**
	 * @param areas
	 *            要设置的 areas
	 * 
	 */
	public void setAreas(List<String> areas) {
		this.areas = areas;
	}

	/**
	 * @return factendtime
	 */
	public Timestamp getFactendtime() {
		return factendtime;
	}

	/**
	 * @param factendtime
	 *            要设置的 factendtime
	 * 
	 */
	public void setFactendtime(Timestamp factendtime) {
		this.factendtime = factendtime;
	}

	/**
	 * @return mauMachine
	 */
	public MauMachine getMauMachine() {
		return mauMachine;
	}

	/**
	 * @param mauMachine
	 *            要设置的 mauMachine
	 * 
	 */
	public void setMauMachine(MauMachine mauMachine) {
		this.mauMachine = mauMachine;
	}

	public Map<String, List<String>> getmDataList() {
		return mDataList;
	}

	public void setmDataList(Map<String, List<String>> mDataList) {
		this.mDataList = mDataList;
	}

}
