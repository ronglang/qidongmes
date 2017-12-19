package com.css.business.web.subsysstore.bean;

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

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="store_shelf_rfid")
public class StoreShelfRfid implements BaseEntity {
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_store_rfid", allocationSize = 1, initialValue = 1, sequenceName = "seq_store_rfid")  
	@GeneratedValue(generator = "seq_store_rfid", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "shelf_num")
	private String shelfNum;
	@Column(name = "whether_done")
	private String whetherDone;
	@Column(name = "relativex")
	private Integer relativex;
	@Column(name = "relativey")
	private Integer relativey;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name="create_by")
	private String createBy;
	@Column(name = "workroomx")
	private Integer workroomx;
	@Column(name = "workroomy")
	private Integer workroomy;
	@Column(name = "shelf_rfid")
	private String shelfRfid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getShelfNum() {
		return shelfNum;
	}
	public void setShelfNum(String shelfNum) {
		this.shelfNum = shelfNum;
	}
	public String getWhetherDone() {
		return whetherDone;
	}
	public void setWhetherDone(String whetherDone) {
		this.whetherDone = whetherDone;
	}
	public Integer getRelativex() {
		return relativex;
	}
	public void setRelativex(Integer relativex) {
		this.relativex = relativex;
	}
	public Integer getRelativey() {
		return relativey;
	}
	public void setRelativey(Integer relativey) {
		this.relativey = relativey;
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
	public Integer getWorkroomx() {
		return workroomx;
	}
	public void setWorkroomx(Integer workroomx) {
		this.workroomx = workroomx;
	}
	public Integer getWorkroomy() {
		return workroomy;
	}
	public void setWorkroomy(Integer workroomy) {
		this.workroomy = workroomy;
	}
	public String getShelfRfid() {
		return shelfRfid;
	}
	public void setShelfRfid(String shelfRfid) {
		this.shelfRfid = shelfRfid;
	}
	
}
