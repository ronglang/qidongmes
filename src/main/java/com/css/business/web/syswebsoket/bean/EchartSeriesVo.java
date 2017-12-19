package com.css.business.web.syswebsoket.bean;

import java.util.List;

/**
 * 
 * @Title:EchartSeriesVo.java
 * @Description:针对Echarts的数据 对应问题
 * @author RB
 * @company SMTC
 * @date 2017年11月3日下午4:28:49
 */
public class EchartSeriesVo {
	//y轴依据左右轴  left ,rigt
	private String type;

	private List<String> data;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

}
