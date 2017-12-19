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
@Table(name = "SYS_ORG_ROLE")
public class SysOrgRole implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -6655066737748122333L;
	private Integer id;
	@Column(name = "ORG_ID")
	private Integer orgId;
	@Column(name = "ROLE_ID")
	private Integer roleId;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}
