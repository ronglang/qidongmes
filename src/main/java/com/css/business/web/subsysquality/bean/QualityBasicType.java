package com.css.business.web.subsysquality.bean;

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
 * 
* @Title: QualityBasicType.java   
* @Package com.css.business.web.subsysquality.bean   
* @Description: 质检参数类型名称表 中心表   都会关联这张表或者取在这表表的数据
* @author   rb
* @date 2017年8月30日 下午11:37:26   
* @company  SMTC
 */
@Entity
@Table(name = "Quality_Basic_Type")
@SequenceGenerator(name = "SEQ_Quality_Basic_Type", sequenceName = "SEQ_Quality_Basic_Type")
public class QualityBasicType implements BaseEntity {
	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	*/ 
	@Transient
	private static final long serialVersionUID = 1835362853337607108L;
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
	/** 质检参数类型名称 */
	@Column(name = "type_name")
	private String typeName;
	/** 类型 */
	@Column(name = "type")
	private String type;
	/** 备注 */
	@Column(name = "remark")
	private String remark;
	/** 质检依据 */
	@Column(name = "test_according")
	private String testAccording;

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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTestAccording() {
		return testAccording;
	}

	public void setTestAccording(String testAccording) {
		this.testAccording = testAccording;
	}

}
