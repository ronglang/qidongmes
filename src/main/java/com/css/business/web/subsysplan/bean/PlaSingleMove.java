package com.css.business.web.subsysplan.bean;

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
@Table(name = "PLA_SINGLE_MOVE")
@SequenceGenerator(name = "SEQ_PLA_SINGLE_MOVE", sequenceName = "SEQ_PLA_SINGLE_MOVE")
public class PlaSingleMove implements BaseEntity {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 4873342529016363199L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "SEQ_PLA_SINGLE_MOVE", sequenceName = "SEQ_PLA_SINGLE_MOVE")
	@GeneratedValue(generator = "SEQ_PLA_SINGLE_MOVE", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "be_moved_ws_code")
	private String beMovedWsCode;
	@Column(name = "moved_ws_code")
	private String movedWsCode;
	@Column(name = "be_moved_batch_code")
	private String beMovedBatchCode;
	@Column(name = "moved_batch_code")
	private String movedBatchCode;
	@Column(name = "pro_ggxh")
	private String proGgxh;
	@Column(name = "pro_color")
	private String proColor;
	@Column(name = "move_account")
	private Integer moveAccount;
	@Column(name = "partLen")
	private String partLen;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "move_code")
	private String moveCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getBeMovedWsCode() {
		return beMovedWsCode;
	}

	public void setBeMovedWsCode(String beMovedWsCode) {
		this.beMovedWsCode = beMovedWsCode;
	}

	public String getMovedWsCode() {
		return movedWsCode;
	}

	public void setMovedWsCode(String movedWsCode) {
		this.movedWsCode = movedWsCode;
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

	public Integer getMoveAccount() {
		return moveAccount;
	}

	public void setMoveAccount(Integer moveAccount) {
		this.moveAccount = moveAccount;
	}

	public String getPartLen() {
		return partLen;
	}

	public void setPartLen(String partLen) {
		this.partLen = partLen;
	}

	public String getBeMovedBatchCode() {
		return beMovedBatchCode;
	}

	public void setBeMovedBatchCode(String beMovedBatchCode) {
		this.beMovedBatchCode = beMovedBatchCode;
	}

	public String getMovedBatchCode() {
		return movedBatchCode;
	}

	public void setMovedBatchCode(String movedBatchCode) {
		this.movedBatchCode = movedBatchCode;
	}

	public String getMoveCode() {
		return moveCode;
	}

	public void setMoveCode(String moveCode) {
		this.moveCode = moveCode;
	}

}
