package com.css.common.web.workflow.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.css.common.web.syscommon.bean.BaseEntity;

@Entity
@Table(name = "WF_DAIMA")
 @SequenceGenerator(name="SEQ_BS_DAIMA",sequenceName="SEQ_BS_DAIMA")
public class WfDaima implements BaseEntity {
	@Transient
	private static final long serialVersionUID = 1931504213069741899L;
	private Integer dmId;
	@Column(name = "DM_DMLB")
	private String dmDmlb;
	@Column(name = "DM_DMZ")
	private String dmDmz;
	@Column(name = "DM_DMMC")
	private String dmDmmc;
	@Column(name = "DM_KSRQ")
	private String dmKsrq;
	@Column(name = "DM_ZZRQ")
	private String dmZzrq;
	@Column(name = "DM_QYFLAG")
	private String dmQyflag;
	@Column(name = "DM_DMLBMC")
	private String dmDmlbmc;
	@Column(name = "DM_DMMCJX")
	private String dmDmmcjx;
	@Column(name = "DM_FJBC")
	private String dmFjbc;
	@Column(name = "DM_PR_DMZ")
	private String dmPrDmz;


	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public Integer getId() {
		return dmId;
	}

	public void setId(Integer id) {
		this.dmId = id;
	}

	public String getDmQyflag() {
		return dmQyflag;
	}

	public void setDmQyflag(String dmQyflag) {
		this.dmQyflag = dmQyflag;
	}

	public String getDmDmlbmc() {
		return dmDmlbmc;
	}

	public void setDmDmlbmc(String dmDmlbmc) {
		this.dmDmlbmc = dmDmlbmc;
	}

	public String getDmDmmcjx() {
		return dmDmmcjx;
	}

	public void setDmDmmcjx(String dmDmmcjx) {
		this.dmDmmcjx = dmDmmcjx;
	}

	public String getDmFjbc() {
		return dmFjbc;
	}

	public void setDmFjbc(String dmFjbc) {
		this.dmFjbc = dmFjbc;
	}

	public String getDmPrDmz() {
		return dmPrDmz;
	}

	public void setDmPrDmz(String dmPrDmz) {
		this.dmPrDmz = dmPrDmz;
	}

	public Integer getDmId() {
		return dmId;
	}

	public void setDmId(Integer dmId) {
		this.dmId = dmId;
	}

	public String getDmDmlb() {
		return dmDmlb;
	}

	public void setDmDmlb(String dmDmlb) {
		this.dmDmlb = dmDmlb;
	}

	public String getDmDmz() {
		return dmDmz;
	}

	public void setDmDmz(String dmDmz) {
		this.dmDmz = dmDmz;
	}

	public String getDmDmmc() {
		return dmDmmc;
	}

	public void setDmDmmc(String dmDmmc) {
		this.dmDmmc = dmDmmc;
	}

	public String getDmKsrq() {
		return dmKsrq;
	}

	public void setDmKsrq(String dmKsrq) {
		this.dmKsrq = dmKsrq;
	}

	public String getDmZzrq() {
		return dmZzrq;
	}

	public void setDmZzrq(String dmZzrq) {
		this.dmZzrq = dmZzrq;
	}

}
