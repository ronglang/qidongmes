package com.css.business.web.sysManage.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "SYS_MENU")
public class SysMenu implements BaseEntity ,Comparable<SysMenu>{
	@Transient
	private static final long serialVersionUID = -6109487092800626052L;
	
	private Integer id;
	@Column(name = "code")
	private String code;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "image")
	private String image;
	@Column(name = "is_leaf")
	private Integer isLeaf;
	@Column(name = "is_single")
	private String isSingle;
	@Column(name = "level")
	private Integer level;
	@Column(name = "menu_type")
	private String menuType;
	@Column(name = "name")
	private String name;
	@Column(name = "pcode")
	private String pcode;
	@Column(name = "sort")
	private Integer sort;
	@Column(name = "url")
	private String url;
	
	@Transient
	//按钮
	private List<SysMenu> btnLst = new ArrayList<SysMenu>();  
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getIsSingle() {
		return isSingle;
	}

	public void setIsSingle(String isSingle) {
		this.isSingle = isSingle;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int compareTo(SysMenu o) {
		// TODO Auto-generated method stub
		if(o == null || o.getSort() == null) return 1;
		Integer s = o.getSort();
		if(sort != null ){
			if(sort.intValue() > s.intValue()) return 1;
			else if(sort.intValue() < s.intValue()) return -1;
			else return 0;
			//return sort.intValue() - s.intValue();
		}
		return -1;
		
		/*Integer s = o.getSort();
		if(sort != null){
			if(s != null){
				if(sort.intValue() > s.intValue()) return 1;
				else if(sort.intValue() < s.intValue()) return -1;
				else return 0;
				//return sort.intValue() - s.intValue();
			}
			else{
				return 1;
			}
		}
		return -1;*/
	}

	@Transient
	public List<SysMenu> getBtnLst() {
		return btnLst;
	}

	public void setBtnLst(List<SysMenu> btnLst) {
		this.btnLst = btnLst;
	}
}
