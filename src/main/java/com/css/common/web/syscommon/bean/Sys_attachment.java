package com.css.common.web.syscommon.bean;

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

import com.css.common.util.Constant;
import com.css.common.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "SYS_ATTACHMENT")
//@SequenceGenerator(name="SEQ_SYS_ATTACHMENT",sequenceName="SEQ_SYS_ATTACHMENT")
public class Sys_attachment implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -8810683548217016093L;
	private Integer id;
	@Column
	private Integer bid;
	@Column
	private String classname;//业务类名
	@Column
	private String uploadurlorigname ;//源文件名
	@Column
	private String uploadurl; //全文件名
	@Column
	private String type ;
	@Column
	private String prop ;
	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date create_date;
	private String create_by;
	
	@Column
	private String remark;
	@Column
	private String videourl; //转换后的视频地址
	@Transient
	private Integer imgHeight;
	@Transient
	private Integer imgWidth;
	
	@Transient
	private String attachType;
	
	public String getVideourl() {
		return videourl;
	}

	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}

	public Sys_attachment() {}
	
	public Sys_attachment(String uploadurlorigname, String uploadurl) {
		this.uploadurlorigname = uploadurlorigname;
		this.uploadurl = uploadurl;
	}
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_SYS_ATTACHMENT")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getCreate_by() {
		return create_by;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getUploadurlorigname() {
		return uploadurlorigname;
	}
	public void setUploadurlorigname(String uploadurlorigname) {
		this.uploadurlorigname = uploadurlorigname;
	}
	public String getUploadurl() {
		return uploadurl;
	}
	public void setUploadurl(String uploadurl) {
		this.uploadurl = uploadurl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Transient
	public Integer getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(Integer imgHeight) {
		this.imgHeight = imgHeight;
	}

	@Transient
	public Integer getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(Integer imgWidth) {
		this.imgWidth = imgWidth;
	}

	@Transient
	public String getAttachType() {
		if("jpg".equals(this.getType()) || "jpeg".equals(this.getType()) || "png".equals(this.getType()) || "bmp".equals(this.getType())
				|| "gif".equals(this.getType())){
			return Constant.IMG;
		}else if("doc".equals(this.getType()) || "docx".equals(this.getType()) || "xls".equals(this.getType()) || "xlsx".equals(this.getType())
				|| "ppt".equals(this.getType()) || "pptx".equals(this.getType())){
			return Constant.DOC;
		}else if("mp4".equals(this.getType()) || "avi".equals(this.getType()) || "3gp".equals(this.getType())){
			return Constant.VIDEO;
		}
		return attachType;
	}

	public void setAttachType(String attachType) {
		this.attachType = attachType;
	}

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}
	
	
}
