package com.css.business.web.sysManage.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.css.common.web.syscommon.bean.BaseEntity;

@Entity
@Table(name = "sys_config")

public class SysConfig implements BaseEntity{

	@Transient
	private static final long serialVersionUID = 4703623969071251237L;
	private Integer id;
	@Column(name = "ITEM")
	private String item;
	@Column(name = "VALUE")
	private String value;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
