package com.css.business.web.subsyscraft.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * BOM材料详情Vo
 * 
 * @author JS
 * 
 */
public class CraBomProductMaterVo {

	private Integer id;
	private BigDecimal mcpu;
	private String bmCode;
	private String materggxh;
	private String unit;
	private String seqname;
	private String procraftcode;
	private String ccode;
	private String proggxh;
	private String procolor;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createdate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getMcpu() {
		return mcpu;
	}

	public void setMcpu(BigDecimal mcpu) {
		this.mcpu = mcpu;
	}

	public String getBmCode() {
		return bmCode;
	}

	public void setBmCode(String bmCode) {
		this.bmCode = bmCode;
	}

	public String getMaterggxh() {
		return materggxh;
	}

	public void setMaterggxh(String materggxh) {
		this.materggxh = materggxh;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSeqname() {
		return seqname;
	}

	public void setSeqname(String seqname) {
		this.seqname = seqname;
	}

	public String getProcraftcode() {
		return procraftcode;
	}

	public void setProcraftcode(String procraftcode) {
		this.procraftcode = procraftcode;
	}

	public String getCcode() {
		return ccode;
	}

	public void setCcode(String ccode) {
		this.ccode = ccode;
	}

	public String getProggxh() {
		return proggxh;
	}

	public void setProggxh(String proggxh) {
		this.proggxh = proggxh;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreateDate(Date createdate) {
		this.createdate = createdate;
	}

	public String getProcolor() {
		return procolor;
	}

	public void setProcolor(String procolor) {
		this.procolor = procolor;
	}

}
