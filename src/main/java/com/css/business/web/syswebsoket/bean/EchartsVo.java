package com.css.business.web.syswebsoket.bean;

import java.util.List;
import java.util.Map;

/**
 * 
 * @Title:EchartsVo.java
 * @Description:echarts.js 通用方法
 * @author RB
 * @company SMTC
 * @date 2017年11月2日上午9:16:27
 */
/**
 * @Title:EchartsVo.java
 * @Description:TODO(用一句话描述)
 * @author RB
 * @company SMTC
 * @date 2017年11月4日下午5:24:22
 */
public class EchartsVo {

	/** 标题 */
	private String title;
	/** 图例 */
	private List<String> legends;
	/** 值 */
	private Map<String, EchartSeriesVo> seriesData;
	/** X的值 */
	private List<String> xAxis;

	public List<String> getLegends() {
		return legends;
	}

	public void setLegends(List<String> legends) {
		this.legends = legends;
	}

	public Map<String, EchartSeriesVo> getSeriesData() {
		return seriesData;
	}

	public void setSeriesData(Map<String, EchartSeriesVo> seriesData) {
		this.seriesData = seriesData;
	}

	public List<String> getxAxis() {
		return xAxis;
	}

	public void setxAxis(List<String> xAxis) {
		this.xAxis = xAxis;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
