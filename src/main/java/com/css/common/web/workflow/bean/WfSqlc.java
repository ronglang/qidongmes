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

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "wf_sqlc")
public class WfSqlc implements BaseEntity {
	private Integer id;
	@Column(name = "ps_dm")
	private String psDm;
	@Column(name = "sl_curr_position")
	private String slCurrPostion;
	@Column(name = "com_dm")
	private String comDm;
	@Column(name = "sq_sqbh")
	private String sqSqbh;
	@Column(name = "com_mc")
	private String comMc;
	@Column(name = "os_ver")
	private Integer osVer;
	@Column(name = "sqlc_jsbz")
	private String sqlcJsbz;
	@Column(name = "node_sx_begin")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date nodeSxBegin;
	@Column(name = "node_sx_end")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date nodeSxEnd;
	@Column(name = "sqlc_czyid")
	private Integer sqlcCzyid;
	@Column(name = "sqlc_czyxm")
	private String sqlcCzyxm;
	@Column(name = "sqlc_czsj")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date sqlcCzsj;
	@Column(name = "sqlc_slbh")
	private String sqlcSlbh;
	@Column(name = "sqlc_sqtablename")
	private String sqlcSqtablename;
	@Column(name = "sqlc_zcjs")
	private String sqlcZcjs;


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

	public String getSlCurrPostion() {
		return slCurrPostion;
	}

	public void setSlCurrPostion(String slCurrPostion) {
		this.slCurrPostion = slCurrPostion;
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

	public String getComMc() {
		return comMc;
	}

	public void setComMc(String comMc) {
		this.comMc = comMc;
	}

	public Integer getOsVer() {
		return osVer;
	}

	public void setOsVer(Integer osVer) {
		this.osVer = osVer;
	}

	public String getSqlcJsbz() {
		return sqlcJsbz;
	}

	public void setSqlcJsbz(String sqlcJsbz) {
		this.sqlcJsbz = sqlcJsbz;
	}

	public Date getNodeSxBegin() {
		return nodeSxBegin;
	}

	public void setNodeSxBegin(Date nodeSxBegin) {
		this.nodeSxBegin = nodeSxBegin;
	}

	public Date getNodeSxEnd() {
		return nodeSxEnd;
	}

	public void setNodeSxEnd(Date nodeSxEnd) {
		this.nodeSxEnd = nodeSxEnd;
	}

	public Integer getSqlcCzyid() {
		return sqlcCzyid;
	}

	public void setSqlcCzyid(Integer sqlcCzyid) {
		this.sqlcCzyid = sqlcCzyid;
	}

	public String getSqlcCzyxm() {
		return sqlcCzyxm;
	}

	public void setSqlcCzyxm(String sqlcCzyxm) {
		this.sqlcCzyxm = sqlcCzyxm;
	}

	public Date getSqlcCzsj() {
		return sqlcCzsj;
	}

	public void setSqlcCzsj(Date sqlcCzsj) {
		this.sqlcCzsj = sqlcCzsj;
	}

	public String getSqlcSlbh() {
		return sqlcSlbh;
	}

	public void setSqlcSlbh(String sqlcSlbh) {
		this.sqlcSlbh = sqlcSlbh;
	}

	public String getSqlcSqtablename() {
		return sqlcSqtablename;
	}

	public void setSqlcSqtablename(String sqlcSqtablename) {
		this.sqlcSqtablename = sqlcSqtablename;
	}

	public String getSqlcZcjs() {
		return sqlcZcjs;
	}

	public void setSqlcZcjs(String sqlcZcjs) {
		this.sqlcZcjs = sqlcZcjs;
	}

}
