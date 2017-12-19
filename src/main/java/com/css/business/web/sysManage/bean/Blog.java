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
/**
 * 
 *TODO 博客实体类
 * @author huangaho
 *2015-4-24上午11:33:35
 */
@Entity
@Table(name = "blog")
//@SequenceGenerator(name="SEQ_BLOG",sequenceName="SEQ_BLOG")  //ID序列生成专用
public class Blog implements BaseEntity{

	@Transient
	private static final long serialVersionUID = 6930289109229414890L;

	private Integer id;
	
	@Column(name="title")
	private String title;
	@Column(name="add_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd")//HH:mm:ss  
	@JsonSerialize(using=JsonDateSerializer.class) 
	private Date addDate;
	@Column(name="own")
	private String own;
	@Column(name="blog_content")//,columnDefinition="ntext")//sqlserver nvarchar,text字段类型
	private String blogContent;
	@Column(name="ck")
	private String ck;
	@Column(name="ra")
	private String ra;
	@Column(name="hi")
	private String hi;
	@Column(name="city")
	private String city;
	@Column(name="upload")
	private String upload;
	
	@Column(name="upload_orig_name",columnDefinition="ntext")
	private String uploadOrigName;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)//sqlserver id 自增涨
	//@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_BLOG")  // id 增涨序列
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getOwn() {
		return own;
	}

	public void setOwn(String own) {
		this.own = own;
	}

	public String getBlogContent() {
		return blogContent;
	}

	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}

	public String getCk() {
		return ck;
	}

	public void setCk(String ck) {
		this.ck = ck;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getHi() {
		return hi;
	}

	public void setHi(String hi) {
		this.hi = hi;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public String getUploadOrigName() {
		return uploadOrigName;
	}

	public void setUploadOrigName(String uploadOrigName) {
		this.uploadOrigName = uploadOrigName;
	}
	
}
