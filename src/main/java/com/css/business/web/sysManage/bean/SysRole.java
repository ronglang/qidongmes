package com.css.business.web.sysManage.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.css.common.web.syscommon.bean.BaseEntity;

@Entity
@Table(name = "sys_role")
public class SysRole implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 3109376046919205541L;
	@Id
	@Column(name = "id")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "seq_sys_role", sequenceName = "seq_sys_role")  
	@GeneratedValue(generator = "seq_sys_role", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "remark")
	private String remark;

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
