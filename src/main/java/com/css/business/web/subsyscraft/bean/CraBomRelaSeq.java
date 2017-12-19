
package com.css.business.web.subsyscraft.bean;

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
 * @Title: CraBomRelaSeq.java   
 * @Package com.css.business.web.subsyscraft.bean   
 * @Description: BOM工序关联表   
 * @author   rb
 * @date 2017年7月26日 下午5:29:22   
 * @company  SMTC   
 */

@Entity
@Table(name = "cra_bom_rela_seq")
@SequenceGenerator(name = "seq_cra_bom_rela_seq", sequenceName = "seq_cra_bom_rela_seq")
public class CraBomRelaSeq implements BaseEntity{

	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	*/ 
	@Transient
	private static final long serialVersionUID = -3346486453190919055L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 产品BOM编码 */
	@Column(name = "pro_bom_code")
	private String proBomCode;
	/** 关联表编码 */
	@Column(name = "bom_rela_code")
	private String bomRelaCode;
	/** BOM性能表编码 */
	@Column(name = "bom_prop_code")
	private String bomPropCode;
	/** 产品规格型号 */
	@Column(name = "pro_ggxh")
	private String proGgxh;
	/** 工序编码 */
	@Column(name = "seq_code")
	private String seqCode;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	/**
	 * @return  id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 *  @param id 要设置的 id 
	 *    
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return  proBomCode
	 */
	public String getProBomCode() {
		return proBomCode;
	}
	/**
	 *  @param proBomCode 要设置的 proBomCode 
	 *    
	 */
	public void setProBomCode(String proBomCode) {
		this.proBomCode = proBomCode;
	}
	/**
	 * @return  bomRelaCode
	 */
	public String getBomRelaCode() {
		return bomRelaCode;
	}
	/**
	 *  @param bomRelaCode 要设置的 bomRelaCode 
	 *    
	 */
	public void setBomRelaCode(String bomRelaCode) {
		this.bomRelaCode = bomRelaCode;
	}
	/**
	 * @return  bomPropCode
	 */
	public String getBomPropCode() {
		return bomPropCode;
	}
	/**
	 *  @param bomPropCode 要设置的 bomPropCode 
	 *    
	 */
	public void setBomPropCode(String bomPropCode) {
		this.bomPropCode = bomPropCode;
	}
	/**
	 * @return  proGgxh
	 */
	public String getProGgxh() {
		return proGgxh;
	}
	/**
	 *  @param proGgxh 要设置的 proGgxh 
	 *    
	 */
	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}
	/**
	 * @return  seqCode
	 */
	public String getSeqCode() {
		return seqCode;
	}
	/**
	 *  @param seqCode 要设置的 seqCode 
	 *    
	 */
	public void setSeqCode(String seqCode) {
		this.seqCode = seqCode;
	}
	/**
	 * @return  createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 *  @param createDate 要设置的 createDate 
	 *    
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return  createBy
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 *  @param createBy 要设置的 createBy 
	 *    
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	
}
