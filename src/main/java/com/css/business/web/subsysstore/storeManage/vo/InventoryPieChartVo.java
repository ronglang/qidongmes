package com.css.business.web.subsysstore.storeManage.vo;

import java.util.List;
import java.util.Map;

public class InventoryPieChartVo {
	
	private String title;//标题

	private List<String> legends;//图例
	
	private List<Map<String,String>> datas;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getLegends() {
		return legends;
	}

	public void setLegends(List<String> legends) {
		this.legends = legends;
	}

	public List<Map<String, String>> getDatas() {
		return datas;
	}

	public void setDatas(List<Map<String, String>> datas) {
		this.datas = datas;
	} 
	
	
	
	
	
	
}
