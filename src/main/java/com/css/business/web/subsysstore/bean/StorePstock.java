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
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "STORE_PSTOCK")
@SequenceGenerator(name = "SEQ_STORE_PSTOCK", sequenceName = "SEQ_STORE_PSTOCK")
public class StorePstock implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 914185726836040288L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "single_by")
	private String singleBy;
	@Column(name = "single_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date singleDate;
	@Column(name = "inspector")
	private String inspector;
	@Column(name = "ws_code")
	private String wsCode;
	@Column(name = "depot_sign")
	private String depotSign;
	@Column(name = "product_id")
	private Integer productId;

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

	public Date getSingleDate() {
		return singleDate;
	}

	public void setSingleDate(Date singleDate) {
		this.singleDate = singleDate;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getSingleBy() {
		return singleBy;
	}

	public void setSingleBy(String singleBy) {
		this.singleBy = singleBy;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getWsCode() {
		return wsCode;
	}

	public void setWsCode(String wsCode) {
		this.wsCode = wsCode;
	}

	public String getDepotSign() {
		return depotSign;
	}

	public void setDepotSign(String depotSign) {
		this.depotSign = depotSign;
	}

	
}
