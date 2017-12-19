package com.css.business.web.subsyscraft.vo;

import java.util.ArrayList;
import java.util.List;

public class VOBean {

	
	private String id; 
	
	
	private String level;//级别
	
	private String text;//显示值
	
	private List<VOBean> children; 
	
	private String parentId;
	

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<VOBean> getChildren() {
		if(children==null){
			children = new ArrayList<VOBean>();
		}
		return children;
	}

	public void setChildren(List<VOBean> children) {
		this.children = children;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	
}
