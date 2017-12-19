package com.css.business.web.subsysstatistics.bean;

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
 * @Title: StatCourse.java
 * @Package com.css.business.web.subsysstatistics.bean
 * @Description: 工单统计信息表
 * @author rb
 * @date 2017年7月25日 下午5:10:29
 * @company SMTC
 */
@Entity
@Table(name = "Stat_Course")
@SequenceGenerator(name = "seq_stat_course", sequenceName = "seq_stat_course")
public class StatCourse implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 6184826314009220644L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_stat_course", sequenceName = "seq_stat_course")
	@GeneratedValue(generator = "seq_stat_course", strategy = GenerationType.SEQUENCE)
	private Integer id;

	/** 工单编号 */
	@Column(name = "course_code")
	private String course_code;
	/** 工序名称 */
	@Column(name = "seq_name")
	private String seqName;
	/** 机器名称 */
	@Column(name = "mac_name")
	private String macName;
	/** 开始时间 */
	@Column(name = "start_time")
	private String startTime;
	/** 结束时间 */
	@Column(name = "end_time")
	private String endTime;
	/** 参数名称 */
	@Column(name = "param_name")
	private String paramName;
	/** 平均值 */
	@Column(name = "avg_value")
	private String avgValue;
	/** 最大值 */
	@Column(name = "max_value")
	private String maxValue;
	/** 最小值 */
	@Column(name = "min_value")
	private String minValue;
	/** 超过百分比 */
	@Column(name = "over_percnet")
	private String overPercnet;
	/** 低于百分比 */
	@Column(name = "low_percent")
	private String lowPercent;
	/** 异常次数 */
	@Column(name = "ex_count")
	private String exCount;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;

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
	 * @return course_code
	 */
	public String getCourse_code() {
		return course_code;
	}

	/**
	 * @param course_code
	 *            要设置的 course_code
	 * 
	 */
	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}

	/**
	 * @return seqName
	 */
	public String getSeqName() {
		return seqName;
	}

	/**
	 * @param seqName
	 *            要设置的 seqName
	 * 
	 */
	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	/**
	 * @return macName
	 */
	public String getMacName() {
		return macName;
	}

	/**
	 * @param macName
	 *            要设置的 macName
	 * 
	 */
	public void setMacName(String macName) {
		this.macName = macName;
	}

	/**
	 * @return startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            要设置的 startTime
	 * 
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            要设置的 endTime
	 * 
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return paramName
	 */
	public String getParamName() {
		return paramName;
	}

	/**
	 * @param paramName
	 *            要设置的 paramName
	 * 
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	/**
	 * @return avgValue
	 */
	public String getAvgValue() {
		return avgValue;
	}

	/**
	 * @param avgValue
	 *            要设置的 avgValue
	 * 
	 */
	public void setAvgValue(String avgValue) {
		this.avgValue = avgValue;
	}

	/**
	 * @return maxValue
	 */
	public String getMaxValue() {
		return maxValue;
	}

	/**
	 * @param maxValue
	 *            要设置的 maxValue
	 * 
	 */
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * @return minValue
	 */
	public String getMinValue() {
		return minValue;
	}

	/**
	 * @param minValue
	 *            要设置的 minValue
	 * 
	 */
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	/**
	 * @return overPercnet
	 */
	public String getOverPercnet() {
		return overPercnet;
	}

	/**
	 * @param overPercnet
	 *            要设置的 overPercnet
	 * 
	 */
	public void setOverPercnet(String overPercnet) {
		this.overPercnet = overPercnet;
	}

	/**
	 * @return lowPercent
	 */
	public String getLowPercent() {
		return lowPercent;
	}

	/**
	 * @param lowPercent
	 *            要设置的 lowPercent
	 * 
	 */
	public void setLowPercent(String lowPercent) {
		this.lowPercent = lowPercent;
	}

	/**
	 * @return exCount
	 */
	public String getExCount() {
		return exCount;
	}

	/**
	 * @param exCount
	 *            要设置的 exCount
	 * 
	 */
	public void setExCount(String exCount) {
		this.exCount = exCount;
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

}
