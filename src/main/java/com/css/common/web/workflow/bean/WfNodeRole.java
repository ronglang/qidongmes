package com.css.common.web.workflow.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.css.common.web.syscommon.bean.BaseEntity;

@Entity
@Table(name = "wf_node_role")
public class WfNodeRole implements BaseEntity {
	private Integer id;
	@Column(name = "ps_dm")
	private String psDm;
	@Column(name = "node_id")
	private Integer nodeId;
	@Column(name = "role_id")
	private Integer roleId;



	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPsDm() {
		return psDm;
	}

	public void setPsDm(String psDm) {
		this.psDm = psDm;
	}

	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}
