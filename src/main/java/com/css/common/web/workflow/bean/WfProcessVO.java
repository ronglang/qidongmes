package com.css.common.web.workflow.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class WfProcessVO implements Serializable{
	private static final long serialVersionUID = -8384840587589493415L;
	private Integer id;
	private String psdm;
	private String psmc;
	private String psxml;
	private Integer pstjrid;
	private String pstjrxm;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date pstjsj;
	private String pssfzf;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPsdm() {
		return psdm;
	}
	public void setPsdm(String psdm) {
		this.psdm = psdm;
	}
	public String getPsmc() {
		return psmc;
	}
	public void setPsmc(String psmc) {
		this.psmc = psmc;
	}
	public String getPsxml() {
		return psxml;
	}
	public void setPsxml(String psxml) {
		this.psxml = psxml;
	}
	public Integer getPstjrid() {
		return pstjrid;
	}
	public void setPstjrid(Integer pstjrid) {
		this.pstjrid = pstjrid;
	}
	public String getPstjrxm() {
		return pstjrxm;
	}
	public void setPstjrxm(String pstjrxm) {
		this.pstjrxm = pstjrxm;
	}
	public Date getPstjsj() {
		return pstjsj;
	}
	public void setPstjsj(Date pstjsj) {
		this.pstjsj = pstjsj;
	}
	public String getPssfzf() {
		return pssfzf;
	}
	public void setPssfzf(String pssfzf) {
		this.pssfzf = pssfzf;
	}



}
