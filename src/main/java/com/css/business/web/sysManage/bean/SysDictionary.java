package com.css.business.web.sysManage.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "sys_dictionary")

public class SysDictionary implements BaseEntity{
	@Transient
	private static final long serialVersionUID = -6093394488232648605L;
	private Integer id;
	@Column(name = "code")
	private String code;
	@Column(name = "value")
	private String value;
	@Column(name = "remark")
	private String remark;
	@Column(name = "pcode")
	private String pcode;
	@Column(name = "CREATE_BY")
	private String createBy;
	@Column(name = "CREATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "type")
	private String type;
	@Column(name = "is_parent")
	private String isParent;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return  type
	 */
	public String getType() {
		return type;
	}

	/**
	 *  @param type 要设置的 type 
	 *    
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return  isParent
	 */
	public String getIsParent() {
		return isParent;
	}

	/**
	 *  @param isParent 要设置的 isParent 
	 *    
	 */
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	
	

}
