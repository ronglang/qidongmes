package com.css.business.web.subsysplan.vo;

import java.util.List;
import java.util.Map;

public class PlaDisPatchVo {
	
	private String seqname;
	private Integer count;
	private String workdate;
	@SuppressWarnings("rawtypes")
	private Map<String,Map[]> series; //数据
	private List<String> workdates;
	private List<String> legend; //图例
	private List<String> BarY; //柱状图Y轴数据
	private List<String> BarX; //柱状图X轴数据
	
	
	
	public String getSeqname() {
		return seqname;
	}
	public void setSeqname(String seqname) {
		this.seqname = seqname;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getWorkdate() {
		return workdate;
	}
	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}
	public List<String> getWorkdates() {
		return workdates;
	}
	public void setWorkdates(List<String> workdates) {
		this.workdates = workdates;
	}
	public List<String> getLegend() {
		return legend;
	}
	public void setLegend(List<String> legend) {
		this.legend = legend;
	}
	@SuppressWarnings("rawtypes")
	public Map<String, Map[]> getSeries() {
		return series;
	}
	@SuppressWarnings("rawtypes")
	public void setSeries(Map<String, Map[]> series) {
		this.series = series;
	}
	public List<String> getBarY() {
		return BarY;
	}
	public void setBarY(List<String> barY) {
		BarY = barY;
	}
	public List<String> getBarX() {
		return BarX;
	}
	public void setBarX(List<String> barX) {
		BarX = barX;
	}
}
