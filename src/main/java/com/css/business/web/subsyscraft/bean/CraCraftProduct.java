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
@Table(name = "cra_craft_product")
//@SequenceGenerator(name = "seq_cra_craft_product", sequenceName = "seq_cra_craft_product")
public class CraCraftProduct implements BaseEntity {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -3447600649577859317L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_cra_craft_product", sequenceName = "seq_cra_craft_product")  
	@GeneratedValue(generator = "seq_cra_craft_product", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "pro_craft_code")
	private String proCraftCode;
	@Column(name = "pro_id")
	private Integer proId;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "route_code")
	private String routeCode;
	@Column(name = "pro_ggxh")
	private String proGgxh;
	@Column(name = "pro_craft_name")
	private String proCraftName;
	
	/**
	 * 产品工艺 + 工艺路线->工序列表->工序的工艺参数   使用包含的层次关系。
	 */
	//工艺术路线对象
	@Transient
	private CraRoute craRoute;
	
	//工艺路线的工序列表
	@Transient
	private List<CraRouteSeq> seqLst = new ArrayList<CraRouteSeq>();

	@Transient
	private String setGround;//是否是集绞
	
	@Transient
	private String gatheringMode;//集绞方式
	
	@Transient
	private String materielGgxhs;//原材料规格型号集合，
	
	
	
	
	public String getMaterielGgxhs() {
		return materielGgxhs;
	}

	public void setMaterielGgxhs(String materielGgxhs) {
		this.materielGgxhs = materielGgxhs;
	}

	public String getSetGround() {
		return setGround;
	}

	public void setSetGround(String setGround) {
		this.setGround = setGround;
	}

	public String getGatheringMode() {
		return gatheringMode;
	}

	public void setGatheringMode(String gatheringMode) {
		this.gatheringMode = gatheringMode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getProCraftCode() {
		return proCraftCode;
	}

	public void setProCraftCode(String proCraftCode) {
		this.proCraftCode = proCraftCode;
	}

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	public CraRoute getCraRoute() {
		return craRoute;
	}

	public void setCraRoute(CraRoute craRoute) {
		this.craRoute = craRoute;
	}

	public List<CraRouteSeq> getSeqLst() {
		return seqLst;
	}

	public void setSeqLst(List<CraRouteSeq> seqLst) {
		this.seqLst = seqLst;
	}

	public String getProCraftName() {
		return proCraftName;
	}

	public void setProCraftName(String proCraftName) {
		this.proCraftName = proCraftName;
	}
	
}
