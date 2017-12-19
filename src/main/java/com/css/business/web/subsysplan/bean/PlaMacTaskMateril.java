package com.css.business.web.subsysplan.bean;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;

@Entity
@Table(name = "PLA_MAC_TASK_MATERIL")
public class PlaMacTaskMateril implements BaseEntity {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -4984342510147464202L;
	@Transient
	public static final String IS_STATE = "已领取";
	@Transient
	public static final String IS_NO_STATE = "未领取";

	public String getWorkcode() {
		return workcode;
	}

	public void setWorkcode(String workcode) {
		this.workcode = workcode;
	}

	public String getMaccode() {
		return maccode;
	}

	public void setMaccode(String maccode) {
		this.maccode = maccode;
	}

	public String getSeqcode() {
		return seqcode;
	}

	public void setSeqcode(String seqcode) {
		this.seqcode = seqcode;
	}

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_pla_mac_task_materil", sequenceName = "seq_pla_mac_task_materil")
	@GeneratedValue(generator = "seq_pla_mac_task_materil", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "workcode")
	private String workcode;

	@Column(name = "maccode")
	private String maccode;

	@Column(name = "seqcode")
	private String seqcode;

	@Column(name = "materil")
	private String materil;

	@Column(name = "matecount")
	private Float matecount;

	@Column(name = "ptime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp ptime;

	@Column(name = "state")
	private String state;

	@Column(name = "type")
	private String type;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMateril() {
		return materil;
	}

	public void setMateril(String materil) {
		this.materil = materil;
	}

	public float getMatecount() {
		return matecount;
	}

	public void setMatecount(Float matecount) {
		this.matecount = matecount;
	}

	public Timestamp getPtime() {
		return ptime;
	}

	public void setPtime(Timestamp ptime) {
		this.ptime = ptime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
