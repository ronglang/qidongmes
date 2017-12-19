package com.css.business.web.sysManage.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysMenuVO {
	private Integer id;
	private String code;
	private String pid;
	private String text;
	private boolean isexpand;
	private String url;
	
	private String mapurl;

	private Integer type;

	private BigDecimal sort;

	private String image;
	
	private String name;
	private String pname;
	
	private Integer roleid;
	
	private String createby;
	
	private Date createdate;
	
	private Integer isleaf;
	private String issingle;
	private Integer level;
	private String menutype;
	private String pcode;
	
	private List<SysMenuVO> children = new ArrayList<SysMenuVO>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMapurl() {
		return mapurl;
	}
	public void setMapurl(String mapurl) {
		this.mapurl = mapurl;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public BigDecimal getSort() {
		return sort;
	}
	public void setSort(BigDecimal sort) {
		this.sort = sort;
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
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public List<SysMenuVO> getChildren() {
		return children;
	}
	public void setChildren(List<SysMenuVO> children) {
		this.children = children;
	}
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public Integer getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(Integer isleaf) {
		this.isleaf = isleaf;
	}
	public String getIssingle() {
		return issingle;
	}
	public void setIssingle(String issingle) {
		this.issingle = issingle;
	}
	public String getMenutype() {
		return menutype;
	}
	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}
}
