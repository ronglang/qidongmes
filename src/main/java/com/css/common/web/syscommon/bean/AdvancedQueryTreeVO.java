package com.css.common.web.syscommon.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 高级查询树VO
 * @author leitao
 *
 *
 */
public class AdvancedQueryTreeVO {
	
	private String id;//areacode
	
	@JsonProperty(value="pId")
	private String pid;
	
	@JsonProperty("name")
	private String name;//字段中文名
	@JsonProperty("code")
	private String code;//字段类型
	private String value;//字段英文名
	private boolean checked =false;
	private boolean select=false;//是否是树选择
	private String actionName;//加载树的Action名称
	private String treeMethod;//查询方法名
	private String queryName;//查询条件名
	private String queryValue;//查询条件值
	
	public AdvancedQueryTreeVO() {}
	
	public AdvancedQueryTreeVO(String id, String pid, String name,
			String value, String code, boolean checked, boolean select,
			String actionName, String treeMethod, String queryName,
			String queryValue) {
		this.id=id;
		this.pid=pid;
		this.name=name;
		this.code=code;
		this.value=value;
		this.checked=checked;
		this.select=select;
		this.actionName=actionName;
		this.treeMethod=treeMethod;
		this.queryName=queryName;
		this.queryValue=queryValue;
	}

	public AdvancedQueryTreeVO(String name, String value, String code,
			boolean select, String actionName, String treeMethod,
			String queryName, String queryValue) {
		this.name=name;
		this.code=code;
		this.value=value;
		this.select=select;
		this.actionName=actionName;
		this.treeMethod=treeMethod;
		this.queryName=queryName;
		this.queryValue=queryValue;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getTreeMethod() {
		return treeMethod;
	}

	public void setTreeMethod(String treeMethod) {
		this.treeMethod = treeMethod;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getQueryValue() {
		return queryValue;
	}

	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}
}
