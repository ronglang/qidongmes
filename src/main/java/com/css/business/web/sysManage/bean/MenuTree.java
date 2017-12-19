package com.css.business.web.sysManage.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 前台需要的菜单对象
 * 
 * @author aliang
 * @version 1.0
 * @created 2015年4月17日 上午9:34:32
 */
public class MenuTree implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -39571149596219L;
	
	private String id;
	private String pid;
	private String text;

	private boolean isexpand;

	private List<MenuTree> children ;

	private String url;
	
	private String mapurl;

	private Integer type;

	private Integer sort;

	private String image;
	
	private String name;
	private String menuType;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isIsexpand() {
		return isexpand;
	}

	public void setIsexpand(boolean isexpand) {
		this.isexpand = isexpand;
	}

	public List<MenuTree> getChildren() {
		return children;
	}

	public void setChildren(List<MenuTree> children) {
		this.children = children;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	public String getMapurl() {
		return mapurl;
	}

	public void setMapurl(String mapurl) {
		this.mapurl = mapurl;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
}
