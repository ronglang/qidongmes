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

@Entity
@Table(name = "Quality_Ask")
@SequenceGenerator(name = "SEQ_Quality_Ask", sequenceName = "SEQ_Quality_Ask")
public class QualityAsk implements BaseEntity {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	@Transient
	private static final long serialVersionUID = 8382937629662152232L;
	public static String STATE_NOT_HANDLE="未处理"; 
	public static String STATE_IS_HANDLE="已处理"; 
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "finish_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date finishDate;
	@Column(name = "create_by")
	private String createBy;
	/** 呼叫状态 */
	@Column(name = "ask_state")
	private String askState;
	/** 呼叫位置 */
	@Column(name = "ask_location")
	private String askLocation;
	/** 完成人 */
	@Column(name = "finish_by")
	private String finishBy;
	/** 呼叫类型 */
	@Column(name = "ask_type")
	private String askType;
	/** 轴号 */
	@Column(name = "semi_axis")
	private String semiAxis;

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

	public String getAskState() {
		return askState;
	}

	public void setAskState(String askState) {
		this.askState = askState;
	}

	public String getAskLocation() {
		return askLocation;
	}

	public void setAskLocation(String askLocation) {
		this.askLocation = askLocation;
	}

	/**
	 * @return finishDate
	 */
	public Date getFinishDate() {
		return finishDate;
	}

	/**
	 * @param finishDate
	 *            要设置的 finishDate
	 * 
	 */
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	/**
	 * @return finishBy
	 */
	public String getFinishBy() {
		return finishBy;
	}

	/**
	 * @param finishBy
	 *            要设置的 finishBy
	 * 
	 */
	public void setFinishBy(String finishBy) {
		this.finishBy = finishBy;
	}

	/**
	 * @return askType
	 */
	public String getAskType() {
		return askType;
	}

	/**
	 * @param askType
	 *            要设置的 askType
	 * 
	 */
	public void setAskType(String askType) {
		this.askType = askType;
	}

	/**
	 * @return semiAxis
	 */
	public String getSemiAxis() {
		return semiAxis;
	}

	/**
	 * @param semiAxis
	 *            要设置的 semiAxis
	 * 
	 */
	public void setSemiAxis(String semiAxis) {
		this.semiAxis = semiAxis;
	}

}
