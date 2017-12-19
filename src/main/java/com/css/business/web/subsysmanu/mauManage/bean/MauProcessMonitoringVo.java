package com.css.business.web.subsysmanu.mauManage.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class MauProcessMonitoringVo implements Serializable {

	/**
	 * { display: '产品编号', name: 'productId', align: 'left', width: 100,
	 * minWidth: 10 }, { display: '产品合格', name: 'isQualified', minWidth: 30 }, {
	 * display: '采样日期', name: 'samplDate', minWidth: 30 }, { display: '当前轴号',
	 * name: 'ownAxisNumber' }, { display: '来源轴号', name: 'sourceAxisNumber' }, {
	 * display: '工作单号',name:'wsCode',minWidth:30}, { display:
	 * '工序名称',name:'seqName',minWidth:30}
	 */
	private static final long serialVersionUID = -7264106398322348437L;

	private Integer id;
	private Integer productid;
	private String isqualified;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date sampldate;
	private String ownaxisnumber;
	private String sourceaxisnumber;
	private String wscode;
	private String seqname;
	private String maccode;
	private String macname;
	private String mactype;
	private String paramname;
	private String vaule; // 工艺参数参考值
	private String actvaule; // 工艺参数实测值
	private String mcpu;
	private String matername;
	private String materggxh;
	private String matertype;
	private String matercolor;
	private String productname;
	private String producttype;
	private Double productlength;
	private String unit;
	private String color;
	private String packingtype;
	private String remark;
	private String productbatch;
	private String endaxisnumber;
	private static final String materdetail = "材料信息";
	private static final String qualityrecord = "质检报告";
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createdate;
	private String createby;
	private String seqcode;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date startdate;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date enddate;
	private String materid;
	private Integer manuamount;
	private String ccode;
	private String mpscode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public String getIsqualified() {
		return isqualified;
	}

	public void setIsqualified(String isqualified) {
		this.isqualified = isqualified;
	}

	public Date getSampldate() {
		return sampldate;
	}

	public void setSampldate(Date sampldate) {
		this.sampldate = sampldate;
	}

	public String getOwnaxisnumber() {
		return ownaxisnumber;
	}

	public void setOwnaxisnumber(String ownaxisnumber) {
		this.ownaxisnumber = ownaxisnumber;
	}

	public String getSourceaxisnumber() {
		return sourceaxisnumber;
	}

	public void setSourceaxisnumber(String sourceaxisnumber) {
		this.sourceaxisnumber = sourceaxisnumber;
	}

	public String getWscode() {
		return wscode;
	}

	public void setWscode(String wscode) {
		this.wscode = wscode;
	}

	public String getSeqname() {
		return seqname;
	}

	public void setSeqname(String seqname) {
		this.seqname = seqname;
	}

	public String getMaccode() {
		return maccode;
	}

	public void setMaccode(String maccode) {
		this.maccode = maccode;
	}

	public String getMacname() {
		return macname;
	}

	public void setMacname(String macname) {
		this.macname = macname;
	}

	public String getMactype() {
		return mactype;
	}

	public void setMactype(String mactype) {
		this.mactype = mactype;
	}

	public String getParamname() {
		return paramname;
	}

	public void setParamname(String paramname) {
		this.paramname = paramname;
	}

	public String getVaule() {
		return vaule;
	}

	public void setVaule(String vaule) {
		this.vaule = vaule;
	}

	public String getActvaule() {
		return actvaule;
	}

	public void setActvaule(String actvaule) {
		this.actvaule = actvaule;
	}

	public String getMcpu() {
		return mcpu;
	}

	public void setMcpu(String mcpu) {
		this.mcpu = mcpu;
	}

	public String getMatername() {
		return matername;
	}

	public void setMatername(String matername) {
		this.matername = matername;
	}

	public String getMaterggxh() {
		return materggxh;
	}

	public void setMaterggxh(String materggxh) {
		this.materggxh = materggxh;
	}

	public String getMatertype() {
		return matertype;
	}

	public void setMatertype(String matertype) {
		this.matertype = matertype;
	}

	public String getMatercolor() {
		return matercolor;
	}

	public void setMatercolor(String matercolor) {
		this.matercolor = matercolor;
	}

	public static String getMaterdetail() {
		return materdetail;
	}

	public static String getQualityrecord() {
		return qualityrecord;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public Double getProductlength() {
		return productlength;
	}

	public void setProductlength(Double productlength) {
		this.productlength = productlength;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPackingtype() {
		return packingtype;
	}

	public void setPackingtype(String packingtype) {
		this.packingtype = packingtype;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProductbatch() {
		return productbatch;
	}

	public void setProductbatch(String productbatch) {
		this.productbatch = productbatch;
	}

	public String getEndaxisnumber() {
		return endaxisnumber;
	}

	public void setEndaxisnumber(String endaxisnumber) {
		this.endaxisnumber = endaxisnumber;
	}

	public String getProducttype() {
		return producttype;
	}

	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}

	public String getSeqcode() {
		return seqcode;
	}

	public void setSeqcode(String seqcode) {
		this.seqcode = seqcode;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getMaterid() {
		return materid;
	}

	public void setMaterid(String materid) {
		this.materid = materid;
	}

	public Integer getManuamount() {
		return manuamount;
	}

	public void setManuamount(Integer manuamount) {
		this.manuamount = manuamount;
	}

	public String getCcode() {
		return ccode;
	}

	public void setCcode(String ccode) {
		this.ccode = ccode;
	}

	public String getMpscode() {
		return mpscode;
	}

	public void setMpscode(String mpscode) {
		this.mpscode = mpscode;
	}

}
