package com.css.business.web.subsyscraft.bean;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


import com.css.common.web.syscommon.bean.BaseEntity;

@Entity
@Table(name = "cra_seq_middle")
public class CraSeqMiddle implements BaseEntity {

	/**
	 * 产品工艺，工序，工艺关系表
	 */
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_cra_seq_middle", sequenceName = "seq_cra_seq_middle")  
	@GeneratedValue(generator = "seq_cra_seq_middle", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	private String seq_code;
	
	private String cra_code;
	
	private String cra_name;
	
	private String pro_ggxh;
	
	private String create_by;
	
	private Date create_date;
	
	private Integer relation_id;
	
	
	
	
	
	
	public Integer getRelation_id() {
		return relation_id;
	}
	public void setRelation_id(Integer relation_id) {
		this.relation_id = relation_id;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getSeq_code() {
		return seq_code;
	}
	public void setSeq_code(String seq_code) {
		this.seq_code = seq_code;
	}
	public String getCra_code() {
		return cra_code;
	}
	public void setCra_code(String cra_code) {
		this.cra_code = cra_code;
	}
	public String getCra_name() {
		return cra_name;
	}
	public void setCra_name(String cra_name) {
		this.cra_name = cra_name;
	}
	public String getPro_ggxh() {
		return pro_ggxh;
	}
	public void setPro_ggxh(String pro_ggxh) {
		this.pro_ggxh = pro_ggxh;
	}
	public String getCreate_by() {
		return create_by;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	

}
