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

import com.css.business.web.subsysmanu.bean.MauMachineSpeed;
import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "cra_route_seq")
public class CraRouteSeq implements BaseEntity{
	/**
	 *@author：曾斌 
	 *@Date：2017-06-05
	 *工艺路线明细——对应工序表
	 */
	@Transient
	private static final long serialVersionUID = -561675183872311317L;
	@Id
	@Column(name="id")
	@SequenceGenerator(name = "seq_car_route_seq", allocationSize = 1, initialValue = 1, sequenceName = "seq_car_route_seq")  
	@GeneratedValue(generator = "seq_car_route_seq", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name="route_code")
	private String routeCode;
	@Column(name="seq_code")
	private String seqCode;
//	@Column(name="seq_name")
//	private String seqName;
	@Column(name = "sort")
	private Integer sort;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
//	@Column(name = "route_seq_code")
//	private String routeSeqCode;
	
	//当前工序的工艺参数
	@Transient
	private List<CraSeqParam> cspLst = new ArrayList<CraSeqParam>();
	@Transient
	private List<MauMachineSpeed> mauMachineSpeedLst = new ArrayList<MauMachineSpeed>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
//	public String getSeqName() {
//		return seqName;
//	}
//	public void setSeqName(String seqName) {
//		this.seqName = seqName;
//	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
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
//	public String getRouteSeqCode() {
//		return routeSeqCode;
//	}
//	public void setRouteSeqCode(String routeSeqCode) {
//		this.routeSeqCode = routeSeqCode;
//	}
	public List<CraSeqParam> getCspLst() {
		return cspLst;
	}
	public void setCspLst(List<CraSeqParam> cspLst) {
		this.cspLst = cspLst;
	}
	public List<MauMachineSpeed> getMauMachineSpeedLst() {
		return mauMachineSpeedLst;
	}
	public void setMauMachineSpeedLst(List<MauMachineSpeed> mauMachineSpeedLst) {
		this.mauMachineSpeedLst = mauMachineSpeedLst;
	}


}
