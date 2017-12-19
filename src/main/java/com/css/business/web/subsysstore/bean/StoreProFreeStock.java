package com.css.business.web.subsysstore.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.css.common.web.syscommon.bean.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "store_pro_free_stock")
public class StoreProFreeStock implements BaseEntity{

	@Id
	@Column(name="store_mrecord_id")
	private Integer storeMrecordId;
	
	@Column(name="pro_name")
	private String proName;
	
	@Column(name="pro_ggxh")
	private String proGgxh;
	
	@Column(name="pro_color")
	private String proColor;
	
	@Column(name="als")
	private Integer als;
	
	@Column(name="axis_number")
	private Integer axisNumber;

	public Integer getStoreMrecordId() {
		return storeMrecordId;
	}

	public void setStoreMrecordId(Integer storeMrecordId) {
		this.storeMrecordId = storeMrecordId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	public String getProColor() {
		return proColor;
	}

	public void setProColor(String proColor) {
		this.proColor = proColor;
	}

	public Integer getAls() {
		return als;
	}

	public void setAls(Integer als) {
		this.als = als;
	}

	public Integer getAxisNumber() {
		return axisNumber;
	}

	public void setAxisNumber(Integer axisNumber) {
		this.axisNumber = axisNumber;
	}

	@Override
	public Integer getId() {
		return null;
	}

	@Override
	public void setId(Integer id) {
		
	}
	
}
