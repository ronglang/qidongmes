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
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "wf_process")
public class WfProcess implements BaseEntity {
	@Transient
	private static final long serialVersionUID = -8384840587589493415L;
	private Integer id;
	@Column(name = "ps_dm")
	private String psDm;
	@Column(name = "ps_mc")
	private String psMc;
	@Column(name = "px_xml")
	private String psXml;
	@Column(name = "ps_tjrid")
	private Integer psTjrid;
	@Column(name = "ps_tjrxm")
	private String psTjrxm;
	@Column(name = "ps_tjsj")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date psTjsj;
	@Column(name = "ps_sfzf")
	private String psSfzf;


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

	public String getPsMc() {
		return psMc;
	}

	public void setPsMc(String psMc) {
		this.psMc = psMc;
	}

	public Integer getPsTjrid() {
		return psTjrid;
	}

	public void setPsTjrid(Integer psTjrid) {
		this.psTjrid = psTjrid;
	}

	public String getPsTjrxm() {
		return psTjrxm;
	}

	public void setPsTjrxm(String psTjrxm) {
		this.psTjrxm = psTjrxm;
	}

	public Date getPsTjsj() {
		return psTjsj;
	}

	public void setPsTjsj(Date psTjsj) {
		this.psTjsj = psTjsj;
	}

	public String getPsSfzf() {
		return psSfzf;
	}

	public void setPsSfzf(String psSfzf) {
		this.psSfzf = psSfzf;
	}

	public String getPsXml() {
		return psXml;
	}

	public void setPsXml(String psXml) {
		this.psXml = psXml;
	}

}
