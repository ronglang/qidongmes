package com.css.business.web.subsysstore.storeManage.vo;

import java.sql.Timestamp;

public class StoreInventoryDetailedVo {

	private String materiel;//材料
	
	private String model;//型号
	
	private String color;//颜色
	
	private Double inventory;//库存量
	
	private Double storageNumber;//入库数量
	
	private Double theLibrary;//出库数量
	
	private Double returnOfMaterial;//退料
	
	private Double waste;//废料
	
	private String unit;//计量单位
	
	private String inventory_by;//盘点人员
	
	private Timestamp inv_time;//盘点时间
	
	private String conclusion;//盘点结论
	
	private Double lackNumber;//缺料量
	
	private Double inv_count;//盘点数量
	
	
	
	

	public Double getInv_count() {
		return inv_count;
	}

	public void setInv_count(Double inv_count) {
		this.inv_count = inv_count;
	}

	public Double getLackNumber() {
		return lackNumber;
	}

	public void setLackNumber(Double lackNumber) {
		this.lackNumber = lackNumber;
	}

	public String getMateriel() {
		return materiel;
	}

	public void setMateriel(String materiel) {
		this.materiel = materiel;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getInventory() {
		return inventory;
	}

	public void setInventory(Double inventory) {
		this.inventory = inventory;
	}

	public Double getStorageNumber() {
		return storageNumber;
	}

	public void setStorageNumber(Double storageNumber) {
		this.storageNumber = storageNumber;
	}

	public Double getTheLibrary() {
		return theLibrary;
	}

	public void setTheLibrary(Double theLibrary) {
		this.theLibrary = theLibrary;
	}

	public Double getReturnOfMaterial() {
		return returnOfMaterial;
	}

	public void setReturnOfMaterial(Double returnOfMaterial) {
		this.returnOfMaterial = returnOfMaterial;
	}

	public Double getWaste() {
		return waste;
	}

	public void setWaste(Double waste) {
		this.waste = waste;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getInventory_by() {
		return inventory_by;
	}

	public void setInventory_by(String inventory_by) {
		this.inventory_by = inventory_by;
	}

	public Timestamp getInv_time() {
		return inv_time;
	}

	public void setInv_time(Timestamp inv_time) {
		this.inv_time = inv_time;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	
}
