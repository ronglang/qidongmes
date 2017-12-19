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
@Table(name = "Sys_MetaData")
public class SysMetaData implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -2570502059211829978L;
	private Integer id;
	@Column
	private String name;
	@Column
	private String type;
	@Column
	private String remark;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
