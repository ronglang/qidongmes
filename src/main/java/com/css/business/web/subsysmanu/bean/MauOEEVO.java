package com.css.business.web.subsysmanu.bean;

import java.util.List;

/**
 * @Title: MauOEEVO.java
 * @Package com.css.business.web.subsysmanu.bean
 * @Description: TODO(用一句话描述该文件做什么)
 * @author rb
 * @date 2017年9月29日 下午5:22:06
 * @company SMTC
 */

public class MauOEEVO {

	/**
	 * legend: { data:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎'] },
	 */
	private List<String> legend;
	/**
	 * [ { name:'邮件营销', legend[i] type:'line', stack: '总量', data:[120, 132, 101,
	 * 134, 90, 230, 210] ..lineList[i] },
	 */
	// 折线图数据
	private List<List<Double>> lineList;
	/**
	 * X轴坐标
	 */
	private List<String> Xlist;

	/**
	 * 机台号
	 */
	private String macCode;

	/**
	 * 表名
	 */
	private String titleName;

	/**
	 * @return legend
	 */
	public List<String> getLegend() {
		return legend;
	}

	/**
	 * @param legend
	 *            要设置的 legend
	 * 
	 */
	public void setLegend(List<String> legend) {
		this.legend = legend;
	}

	/**
	 * @return lineList
	 */
	public List<List<Double>> getLineList() {
		return lineList;
	}

	/**
	 * @param lineList
	 *            要设置的 lineList
	 * 
	 */
	public void setLineList(List<List<Double>> lineList) {
		this.lineList = lineList;
	}

	/**
	 * @return xlist
	 */
	public List<String> getXlist() {
		return Xlist;
	}

	/**
	 * @param xlist
	 *            要设置的 xlist
	 * 
	 */
	public void setXlist(List<String> xlist) {
		Xlist = xlist;
	}

	/**
	 * @return macCode
	 */
	public String getMacCode() {
		return macCode;
	}

	/**
	 * @param macCode
	 *            要设置的 macCode
	 * 
	 */
	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	/**
	 * @return titleName
	 */
	public String getTitleName() {
		return titleName;
	}

	/**
	 * @param titleName
	 *            要设置的 titleName
	 * 
	 */
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

}
