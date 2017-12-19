package com.css.common.web.syscommon.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TreeVO {
	
	private String id;//areacode
	
	@JsonProperty(value="pId")
	private String pid;
	
	@JsonProperty("name")
	private String name;
	@JsonProperty("code")
	private String code;
	private String value;
	private String valueTwo;//用于扩展传值数量
	
	//hproject中使用。 带出本项目节点的单位
	private String proUnit;
	
	private boolean checked =false;
	
	//是否子节点 是否为子节点 1：子节点 0非子节点
	private String isLeaf ;
	
	public TreeVO() {}
	
	public TreeVO(String id,  String name) {
		this.id = id;
		this.name = name;
	}
	
	public TreeVO(String id,String name, String pid) {
		this.id = id;
		this.pid = pid;
		this.name = name;
	}

	public TreeVO(String id,String name, String pid,String code) {
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.code = code;
	}

	public TreeVO(String id,String name, String pid,String code,String value) {
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.code = code;
		this.value=value;
	}
	
	public TreeVO(String id,String name, String pid,String code,String value,String valueTwo) {
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.code = code;
		this.value=value;
		this.valueTwo=valueTwo;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public TreeVO(String id,String name, String pid,boolean checked) {
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.checked = checked;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getProUnit() {
		return proUnit;
	}

	public void setProUnit(String proUnit) {
		this.proUnit = proUnit;
	}

	public String getValueTwo() {
		return valueTwo;
	}

	public void setValueTwo(String valueTwo) {
		this.valueTwo = valueTwo;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
}
