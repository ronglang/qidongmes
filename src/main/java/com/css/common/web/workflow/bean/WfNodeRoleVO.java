package com.css.common.web.workflow.bean;


public class WfNodeRoleVO {
	private Integer id;
	private String psDm;
	private Integer nodeId;
	private Integer roleId;
	
	private String roleName;
	private long depId;
	private String depName;
	private String nodeSx;
	private String nodeUrl;	
	
	private String wdYear;


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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public long getDepId() {
		return depId;
	}

	public void setDepId(long depId) {
		this.depId = depId;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getNodeSx() {
		return nodeSx;
	}

	public void setNodeSx(String nodeSx) {
		this.nodeSx = nodeSx;
	}

	public String getNodeUrl() {
		return nodeUrl;
	}

	public void setNodeUrl(String nodeUrl) {
		this.nodeUrl = nodeUrl;
	}

	public String getWdYear() {
		return wdYear;
	}

	public void setWdYear(String wdYear) {
		this.wdYear = wdYear;
	}

}
