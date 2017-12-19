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
@Table(name = "cra_wire_disc")
@SequenceGenerator(name = "seq_cra_wire_disc", sequenceName = "seq_cra_wire_disc")
public class CraWireDisc implements BaseEntity {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "seq_cra_wire_disc", strategy = GenerationType.SEQUENCE)
	private Integer id;
	/**
	 * 关联的RFID卡号
	 */
	@Column(name = "rfid_number")
	private String rfidNumber;
	/**
	 * 线盘规格
	 */
	@Column(name = "wire_disc_pgxh")
	private String wireDiscPgxh;
	/**
	 * 线盘状态（正常、维修、报废）
	 */
	@Column(name = "status")
	private String status;
	/**
	 * 线盘使用状态（在用，空闲）
	 */
	@Column(name = "use_status")
	private String useStatus;
	/**
	 * 材质（木质，铁质等）
	 */
	@Column(name = "material_texture")
	private String materialTexture;

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name="create_by")
	private String createBy; 
	
	@Column(name="bore_diameter")
	private Double boreDiameter;
	@Column(name="external_diameter")
	private Double externalDiameter;
	@Column(name="capacity")
	private Double capacity;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRfidNumber() {
		return rfidNumber;
	}
	public void setRfidNumber(String rfidNumber) {
		this.rfidNumber = rfidNumber;
	}
	public String getWireDiscPgxh() {
		return wireDiscPgxh;
	}
	public void setWireDiscPgxh(String wireDiscPgxh) {
		this.wireDiscPgxh = wireDiscPgxh;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUseStatus() {
		return useStatus;
	}
	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	public String getMaterialTexture() {
		return materialTexture;
	}
	public void setMaterialTexture(String materialTexture) {
		this.materialTexture = materialTexture;
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
	
	public Double getBoreDiameter() {
		return boreDiameter;
	}
	public void setBoreDiameter(Double boreDiameter) {
		this.boreDiameter = boreDiameter;
	}
	public Double getExternalDiameter() {
		return externalDiameter;
	}
	public void setExternalDiameter(Double externalDiameter) {
		this.externalDiameter = externalDiameter;
	}
	public Double getCapacity() {
		return capacity;
	}
	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}
	
	

	
}
