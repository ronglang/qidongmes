package com.css.business.web.subsyscraft.bean;

import java.math.BigDecimal;
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

/**
 * @Title: CraBomProductMater.java
 * @Package com.css.business.web.subsyscraft.bean
 * @Description: 理论BOM原料明细
 * @author rb
 * @date 2017年7月26日 下午5:24:39
 * @company SMTC
 */
@Entity
@Table(name = "cra_bom_product_mater")
@SequenceGenerator(name = "seq_cra_bom_product_mater", sequenceName = "seq_cra_bom_product_mater")
public class CraBomProductMater implements BaseEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	@Transient
	private static final long serialVersionUID = 8241737372882075709L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 单位消耗原料量(m/kg) */
	@Column(name = "mcpu")
	private BigDecimal mcpu;
	/** 材料参数BOM编码 */
	@Column(name = "bm_code")
	private String bmCode;
	/** 原材料ID */
	@Column(name = "mater_id")
	private Integer materId;
	/** 单位 */
	@Column(name = "unit")
	private String unit;
	/** 工序编码 */
	@Column(name = "seq_code")
	private String seqCode;
	/** 产品工艺编码 */
	@Column(name = "pro_craft_code")
	private String proCraftCode;
	/** 工艺编码 */
	@Column(name = "c_code")
	private String cCode;
	/** 规格型号*/
	@Column(name = "pro_ggxh")
	private String proGgxh;
	@Column(name = "pro_color")
	private String proColor;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            要设置的 id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return mcpu
	 */
	public BigDecimal getMcpu() {
		return mcpu;
	}

	/**
	 * @param mcpu
	 *            要设置的 mcpu
	 * 
	 */
	public void setMcpu(BigDecimal mcpu) {
		this.mcpu = mcpu;
	}

	/**
	 * @return bmCode
	 */
	public String getBmCode() {
		return bmCode;
	}

	/**
	 * @param bmCode
	 *            要设置的 bmCode
	 * 
	 */
	public void setBmCode(String bmCode) {
		this.bmCode = bmCode;
	}

	/**
	 * @return materId
	 */
	public Integer getMaterId() {
		return materId;
	}

	/**
	 * @param materId
	 *            要设置的 materId
	 * 
	 */
	public void setMaterId(Integer materId) {
		this.materId = materId;
	}

	/**
	 * @return unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *            要设置的 unit
	 * 
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            要设置的 createDate
	 * 
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return createBy
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy
	 *            要设置的 createBy
	 * 
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * @return seqCode
	 */
	public String getSeqCode() {
		return seqCode;
	}

	/**
	 * @param seqCode
	 *            要设置的 seqCode
	 * 
	 */
	public void setSeqCode(String seqCode) {
		this.seqCode = seqCode;
	}

	/**
	 * @return proCraftCode
	 */
	public String getProCraftCode() {
		return proCraftCode;
	}

	/**
	 * @param proCraftCode
	 *            要设置的 proCraftCode
	 * 
	 */
	public void setProCraftCode(String proCraftCode) {
		this.proCraftCode = proCraftCode;
	}

	/**
	 * @return cCode
	 */
	public String getcCode() {
		return cCode;
	}

	/**
	 * @param cCode
	 *            要设置的 cCode
	 * 
	 */
	public void setcCode(String cCode) {
		this.cCode = cCode;
	}

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	public String getProColor() {
		return proColor;
	}

	public void setProColor(String proColor) {
		this.proColor = proColor;
	}

}
