package com.css.business.web.subsyscraft.vo;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class CraBomTheoryVo {
	
	private Integer id;
	private String paramname;
	private String thirdprop;
	/** 单位 */
	private String unit;
	/** 参数值 */
	private String paramvalue;
	/** 描述 */
	private String remark;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createdate;
	private String createby;
	/**最小值 */
	private String parammin;
	/**最大值 */
	private String parammax;
	/**参数类型 */
	private String paramtype;
	/**主表编码*/
	private String pcscrelacode;
	/**产品规格型号 */
	private String proggxh;
	/**产品颜色 */
	private String procolor;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getParamname() {
		return paramname;
	}
	public void setParamname(String paramname) {
		this.paramname = paramname;
	}
	public String getThirdprop() {
		return thirdprop;
	}
	public void setThirdprop(String thirdprop) {
		this.thirdprop = thirdprop;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getParamvalue() {
		return paramvalue;
	}
	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getParammin() {
		return parammin;
	}
	public void setParammin(String parammin) {
		this.parammin = parammin;
	}
	public String getParammax() {
		return parammax;
	}
	public void setParammax(String parammax) {
		this.parammax = parammax;
	}
	public String getParamtype() {
		return paramtype;
	}
	public void setParamtype(String paramtype) {
		this.paramtype = paramtype;
	}
	public String getPcscrelacode() {
		return pcscrelacode;
	}
	public void setPcscrelacode(String pcscrelacode) {
		this.pcscrelacode = pcscrelacode;
	}
	public String getProggxh() {
		return proggxh;
	}
	public void setProggxh(String proggxh) {
		this.proggxh = proggxh;
	}
	public String getProcolor() {
		return procolor;
	}
	public void setProcolor(String procolor) {
		this.procolor = procolor;
	}
	
}
