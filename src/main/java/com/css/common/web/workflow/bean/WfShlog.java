package com.css.common.web.workflow.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "wf_shlog")
public class WfShlog implements BaseEntity {

	private Integer id;
	@Column(name = "ps_dm")
	private String psDm;
	@Column(name = "menu_id")
	private Integer menuId;
	@Column(name = "com_dm")
	private String comDm;
	@Column(name = "sq_sqbh")
	private String sqSqbh;
	@Column(name = "log_shjg")
	private String logShjg;
	@Column(name = "log_shbz")
	private String logShbz;
	@Column(name = "log_shsj")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date logShsj;
	@Column(name = "log_shrid")
	private Integer logShrid;
	@Column(name = "log_shrxm")
	private String logShrxm;
	@Column(name = "node_id")
	private Integer nodeId;
	@Column(name = "log_tishi")
	private String logTishi;
	@Column(name = "yewu_pyzm")
	private String yewuPyzm;
	@Column(name = "node_pyzm")
	private String nodePyzm;
	@Column(name = "log_tzjd")
	private String logTzjd;
	@Column(name = "log_bczlflag")
	private String logBczlflag;
	@Column(name = "log_bczlczyxm")
	private String logBczlczyxm;
	@Column(name = "log_bczlczsj")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date logBczlczsj;
	@Column(name = "os_ver")
	private Integer osVer;
	@Column(name = "log_dhyy")
	private String logDhyy;
	@Column(name = "log_dhsj")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date logDhsj;
	@Column(name = "log_dhr_user_id")
	private Integer logDhrUserId;
	@Column(name = "log_dh_ps_dm")
	private String logDhPsDm;
	@Column(name = "log_dh_node_id")
	private Integer logDhNodeId;


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

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getComDm() {
		return comDm;
	}

	public void setComDm(String comDm) {
		this.comDm = comDm;
	}

	public String getSqSqbh() {
		return sqSqbh;
	}

	public void setSqSqbh(String sqSqbh) {
		this.sqSqbh = sqSqbh;
	}

	public String getLogShjg() {
		return logShjg;
	}

	public void setLogShjg(String logShjg) {
		this.logShjg = logShjg;
	}

	public String getLogShbz() {
		return logShbz;
	}

	public void setLogShbz(String logShbz) {
		this.logShbz = logShbz;
	}

	public Date getLogShsj() {
		return logShsj;
	}

	public void setLogShsj(Date logShsj) {
		this.logShsj = logShsj;
	}

	public Integer getLogShrid() {
		return logShrid;
	}

	public void setLogShrid(Integer logShrid) {
		this.logShrid = logShrid;
	}

	public String getLogShrxm() {
		return logShrxm;
	}

	public void setLogShrxm(String logShrxm) {
		this.logShrxm = logShrxm;
	}

	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public String getLogTishi() {
		return logTishi;
	}

	public void setLogTishi(String logTishi) {
		this.logTishi = logTishi;
	}

	public String getYewuPyzm() {
		return yewuPyzm;
	}

	public void setYewuPyzm(String yewuPyzm) {
		this.yewuPyzm = yewuPyzm;
	}

	public String getNodePyzm() {
		return nodePyzm;
	}

	public void setNodePyzm(String nodePyzm) {
		this.nodePyzm = nodePyzm;
	}

	public String getLogTzjd() {
		return logTzjd;
	}

	public void setLogTzjd(String logTzjd) {
		this.logTzjd = logTzjd;
	}

	public String getLogBczlflag() {
		return logBczlflag;
	}

	public void setLogBczlflag(String logBczlflag) {
		this.logBczlflag = logBczlflag;
	}

	public String getLogBczlczyxm() {
		return logBczlczyxm;
	}

	public void setLogBczlczyxm(String logBczlczyxm) {
		this.logBczlczyxm = logBczlczyxm;
	}

	public Date getLogBczlczsj() {
		return logBczlczsj;
	}

	public void setLogBczlczsj(Date logBczlczsj) {
		this.logBczlczsj = logBczlczsj;
	}

	public Integer getOsVer() {
		return osVer;
	}

	public void setOsVer(Integer osVer) {
		this.osVer = osVer;
	}

	public String getLogDhyy() {
		return logDhyy;
	}

	public void setLogDhyy(String logDhyy) {
		this.logDhyy = logDhyy;
	}

	public Date getLogDhsj() {
		return logDhsj;
	}

	public void setLogDhsj(Date logDhsj) {
		this.logDhsj = logDhsj;
	}

	public Integer getLogDhrUserId() {
		return logDhrUserId;
	}

	public void setLogDhrUserId(Integer logDhrUserId) {
		this.logDhrUserId = logDhrUserId;
	}

	public String getLogDhPsDm() {
		return logDhPsDm;
	}

	public void setLogDhPsDm(String logDhPsDm) {
		this.logDhPsDm = logDhPsDm;
	}

	public Integer getLogDhNodeId() {
		return logDhNodeId;
	}

	public void setLogDhNodeId(Integer logDhNodeId) {
		this.logDhNodeId = logDhNodeId;
	}

}
