package com.css.business.web.subsyscraft.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "cra_seq_param")
//@SequenceGenerator(name = "seq_cra_seq_param", sequenceName = "seq_cra_seq_param")
public class CraSeqParam implements BaseEntity {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 8222705435058008313L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_cra_seq_param", sequenceName = "seq_cra_seq_param")  
	@GeneratedValue(generator = "seq_cra_seq_param", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "craft_code")
	private String craftCode;
	@Column(name = "craft_name")
	private String craftName;
	@Column(name = "seq_code")
	private String seqCode;
	@Column(name = "pro_craft_code")
	private String proCraftCode;
	@Column(name = "pcsc_rela_code")
	private String pcscRelaCode;
	@Column(name = "param_code")
	private String paramCode;
	@Column(name = "param_value")
	private Double paramValue;
	@Column(name = "param_min_value")
	private Double paramMinValue;
	@Column(name = "param_max_value")
	private Double paramMaxValue;
	@Column(name = "param_name")
	private String paramName;
	@Column(name = "uint")
	private String uint;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	//产品规格型号 
	@Column(name = "ggxh")
	private String ggxh;
	@Column(name = "mac_code")
	private String macCode;
	
	
	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	@Transient
	private Integer relationId;
	
	

	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getParamMinValue() {
		return paramMinValue;
	}

	public void setParamMinValue(Double paramMinValue) {
		this.paramMinValue = paramMinValue;
	}

	public Double getParamMaxValue() {
		return paramMaxValue;
	}

	public void setParamMaxValue(Double paramMaxValue) {
		this.paramMaxValue = paramMaxValue;
	}

	public String getUint() {
		return uint;
	}

	public void setUint(String uint) {
		this.uint = uint;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Double getParamValue() {
		return paramValue;
	}

	public void setParamValue(Double paramValue) {
		this.paramValue = paramValue;
	}

	public String getSeqCode() {
		return seqCode;
	}

	public void setSeqCode(String seqCode) {
		this.seqCode = seqCode;
	}

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public String getProCraftCode() {
		return proCraftCode;
	}

	public void setProCraftCode(String proCraftCode) {
		this.proCraftCode = proCraftCode;
	}

	public String getPcscRelaCode() {
		return pcscRelaCode;
	}

	public void setPcscRelaCode(String pcscRelaCode) {
		this.pcscRelaCode = pcscRelaCode;
	}

	public String getCraftCode() {
		return craftCode;
	}

	public void setCraftCode(String craftCode) {
		this.craftCode = craftCode;
	}

	public String getCraftName() {
		return craftName;
	}

	public void setCraftName(String craftName) {
		this.craftName = craftName;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getGgxh() {
		return ggxh;
	}

	public void setGgxh(String ggxh) {
		this.ggxh = ggxh;
	}
	
}
