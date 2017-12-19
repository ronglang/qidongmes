package com.css.business.web.sysManage.bean;

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

import org.apache.commons.net.ntp.TimeStamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @Title: SysNotice.java
 * @Package com.css.business.web.sysManage.bean
 * @Description: 滚动条的参数
 * @author rb
 * @date 2017年9月7日 下午4:07:24
 * @company SMTC
 */
@Entity
@Table(name = "Sys_Notice")
@SequenceGenerator(name = "SEQ_Sys_Notice", sequenceName = "SEQ_Sys_Notice")
public class SysNotice implements BaseEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	@Transient
	private static final long serialVersionUID = -2086003648935348764L;
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;

	/** 用于自己能识别的key */
	@Column(name = "key")
	private String key;
	/** 展示的值 */
	@Column(name = "value")
	private String value;
	/** 是否展示 */
	@Column(name = "is_show")
	private String isShow;
	/** 类型 */
	@Column(name = "type")
	private String type;

	@Column(name = "start_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date startTime;
	@Column(name = "end_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date endTime;

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            要设置的 id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            要设置的 createDate
	 * 
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return createBy
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy
	 *            要设置的 createBy
	 * 
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * @return key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            要设置的 key
	 * 
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            要设置的 value
	 * 
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return isShow
	 */
	public String getIsShow() {
		return isShow;
	}

	/**
	 * @param isShow
	 *            要设置的 isShow
	 * 
	 */
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	/**
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            要设置的 startTime
	 * 
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            要设置的 endTime
	 * 
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @param type
	 *            要设置的 type
	 * 
	 */
	public void setType(String type) {
		this.type = type;
	}

}
