package com.css.business.web.subsysstore.storeManage.bean;

import java.math.BigDecimal;

public class StoreHdmiVo {

	private BigDecimal acount; // 出库数量
	private String sort; // 物料名
	private String ggxh; // 规格型号
	private String amount; // 退料数量
	private String unit;

	public BigDecimal getAcount() {
		return acount;
	}

	public void setAcount(BigDecimal acount) {
		this.acount = acount;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getGgxh() {
		return ggxh;
	}

	public void setGgxh(String ggxh) {
		this.ggxh = ggxh;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
