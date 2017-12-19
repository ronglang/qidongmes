package com.css.business.web.sysManage.bean;

import java.sql.Timestamp;
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

/**
 * 电子看板参数实体类
 * 
 * @author TG
 * 
 */
@Entity
@Table(name = "sys_mac_dictionary")
@SequenceGenerator(name = "seq_sys_mac_dictionary", sequenceName = "seq_sys_mac_dictionary")
public class SysMacDictionary implements BaseEntity {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -7915660741776400561L;
	/** 页面展示 */
	@Transient
	public static Integer IS_SHOW = 1;
	/** 页面不展示 */
	@Transient
	public static Integer IS_NOT_SHOW = 0;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "code")
	private String code;
	@Column(name = "value")
	private String value;
	@Column(name = "remark")
	private String remark;
	@Column(name = "pcode")
	private String pcode;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "type")
	private String type;
	@Column(name = "is_parent")
	private String isParent;
	@Column(name = "mac_code")
	private String macCode;
	@Column(name = "mac_name")
	private String macName;
	@Column(name = "html_id")
	private String htmlId;
	@Column(name = "is_show")
	private Integer isShow;
	@Column(name = "mac_id")
	private Integer macId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	public String getMacName() {
		return macName;
	}

	public void setMacName(String macName) {
		this.macName = macName;
	}

	public String getHtmlId() {
		return htmlId;
	}

	public void setHtmlId(String htmlId) {
		this.htmlId = htmlId;
	}

	/**
	 * @return isShow
	 */
	public Integer getIsShow() {
		return isShow;
	}

	/**
	 * @param isShow
	 *            要设置的 isShow
	 * 
	 */
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	/**
	 * @return macId
	 */
	public Integer getMacId() {
		return macId;
	}

	/**
	 * @param macId
	 *            要设置的 macId
	 * 
	 */
	public void setMacId(Integer macId) {
		this.macId = macId;
	}

}
