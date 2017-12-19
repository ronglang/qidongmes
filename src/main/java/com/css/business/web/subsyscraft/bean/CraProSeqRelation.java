package com.css.business.web.subsyscraft.bean;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Table(name = "cra_pro_seq_relation")
public class CraProSeqRelation implements BaseEntity {

	/**
	 * 产品工艺，工序，工艺关系表
	 */
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_cra_pro_seq_relation", sequenceName = "seq_cra_pro_seq_relation")  
	@GeneratedValue(generator = "seq_cra_pro_seq_relation", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "pro_craft_code")
	private String proCraftCode;
	@Column(name = "route_code")
	private String routeCode;
	@Column(name = "seq_code")
	private String seqCode;
	@Column(name = "c_code")
	private String cCode;
	@Column(name = "pro_ggxh")
	private String proGgxh;
	@Column(name = "pro_color")
	private String proColor;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name ="pcsc_rela_code")
	private String pcscRelaCode;
	@Column(name = "core")
	private Integer core;
	@Column(name = "seq_half_pro_num")
	private Integer seqHalfProNum;
	@Column(name = "seq_sort")
	private Integer seqSort;
	
	@Transient
	private String seqName;
	
	@Transient
	List<CraSeqParam> craSeqParamList = new ArrayList<CraSeqParam>();
	
	@Transient
	public String getSeqName() {
		return seqName;
	}
	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProCraftCode() {
		return proCraftCode;
	}
	public void setProCraftCode(String proCraftCode) {
		this.proCraftCode = proCraftCode;
	}
	public String getRouteCode() {
		return routeCode;
	}
	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}
	public String getSeqCode() {
		return seqCode;
	}
	public void setSeqCode(String seqCode) {
		this.seqCode = seqCode;
	}
	public String getcCode() {
		return cCode;
	}
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
	
	public Integer getCore() {
		return core;
	}
	public void setCore(Integer core) {
		this.core = core;
	}
	public String getPcscRelaCode() {
		return pcscRelaCode;
	}
	public void setPcscRelaCode(String pcscRelaCode) {
		this.pcscRelaCode = pcscRelaCode;
	}
	public Integer getSeqHalfProNum() {
		return seqHalfProNum;
	}
	public void setSeqHalfProNum(Integer seqHalfProNum) {
		this.seqHalfProNum = seqHalfProNum;
	}
	public Integer getSeqSort() {
		return seqSort;
	}
	public void setSeqSort(Integer seqSort) {
		this.seqSort = seqSort;
	}
	@Transient
	public List<CraSeqParam> getCraSeqParamList() {
		return craSeqParamList;
	}
	public void setCraSeqParamList(List<CraSeqParam> craSeqParamList) {
		this.craSeqParamList = craSeqParamList;
	}
}
