package com.css.business.web.subsysmanu.bean;

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

@Entity
@Table(name = "MAU_PRODUCT")
// @SequenceGenerator(name="SEQ_MAU_PRODUCT_BOM",sequenceName="SEQ_MAU_PRODUCT_BOM")
public class MauProduct implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -461814214552649118L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_mau_product", sequenceName = "seq_mau_product")
	@GeneratedValue(generator = "seq_mau_product", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "pro_code")
	private String pro_code;// 产品编码
	@Column(name = "pro_name")
	private String pro_name;// 产品名称
	@Column(name = "pro_type")
	private String pro_type;// 成品或半成品类型 // 产品类型（成品|半成品|成品类型|半成品类型）
	@Column(name = "pro_color")
	private String pro_color; // 产品颜色
	@Column(name = "pro_length")
	private Integer pro_length;// 产品长度
	@Column(name = "unit")
	private String unit;// 单位
	@Column(name = "packing_type")
	private String packing_type;// 包装类型
	@Column(name = "packing_mater")
	private String packing_mater;// 包装材料
	@Column(name = "remark")
	private String remark;// 备注
	@Column(name = "route_code")
	private String route_code;// 工艺编码
	@Column(name = "p_code")
	private String p_code;// 上级产品编码
	// @Column(name = "core")
	// private Integer core;

	@Column(name = "pro_ggxh")
	// 产品规格型号
	private String pro_ggxh;

	// 线径
	@Column(name = "seq_xj")
	private BigDecimal seq_xj;
	@Column(name = "core")
	private Integer core;
	@Column(name = "color_core")
	private String colorCore; // (芯线内芯颜色,内芯有几种颜色如：红;黑色;白色)
	@Column(name = "in_core")
	private String inCore; // 芯线内芯数,与芯线内芯颜色一一对应如：20;20;30
	@Column(name = "area")
	private String area;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getPro_code() {
		return pro_code;
	}

	public void setPro_code(String pro_code) {
		this.pro_code = pro_code;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public String getPro_type() {
		return pro_type;
	}

	public void setPro_type(String pro_type) {
		this.pro_type = pro_type;
	}

	public String getPro_color() {
		return pro_color;
	}

	public void setPro_color(String pro_color) {
		this.pro_color = pro_color;
	}

	public Integer getPro_length() {
		return pro_length;
	}

	public void setPro_length(Integer pro_length) {
		this.pro_length = pro_length;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPacking_type() {
		return packing_type;
	}

	public void setPacking_type(String packing_type) {
		this.packing_type = packing_type;
	}

	public String getPacking_mater() {
		return packing_mater;
	}

	public void setPacking_mater(String packing_mater) {
		this.packing_mater = packing_mater;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRoute_code() {
		return route_code;
	}

	public void setRoute_code(String route_code) {
		this.route_code = route_code;
	}

	/**
	 * @return p_code
	 */
	public String getP_code() {
		return p_code;
	}

	/**
	 * @param p_code
	 *            要设置的 p_code
	 * 
	 */
	public void setP_code(String p_code) {
		this.p_code = p_code;
	}

	/**
	 * @return pro_ggxh
	 */
	public String getPro_ggxh() {
		return pro_ggxh;
	}

	/**
	 * @param pro_ggxh
	 *            要设置的 pro_ggxh
	 * 
	 */
	public void setPro_ggxh(String pro_ggxh) {
		this.pro_ggxh = pro_ggxh;
	}

	// public Integer getCore() {
	// return core;
	// }
	//
	// public void setCore(Integer core) {
	// this.core = core;
	// }

	public BigDecimal getSeq_xj() {
		return seq_xj;
	}

	public void setSeq_xj(BigDecimal seq_xj) {
		this.seq_xj = seq_xj;
	}

	public String getColorCore() {
		return colorCore;
	}

	public void setColorCore(String colorCore) {
		this.colorCore = colorCore;
	}

	public String getInCore() {
		return inCore;
	}

	public void setInCore(String inCore) {
		this.inCore = inCore;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getCore() {
		return core;
	}

	public void setCore(Integer core) {
		this.core = core;
	}

}
