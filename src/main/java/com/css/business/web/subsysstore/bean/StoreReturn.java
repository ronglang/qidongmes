package com.css.business.web.subsysstore.bean;
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

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
* @Title: StoreReturn.java   
* @Package com.css.business.web.subsysstore.bean   
* @Description: 退料表的尸体bean 
* @author   rb
* @date 2017年6月27日 上午11:49:21   
* @company  SMTC
 */
@Entity
@Table(name = "STORE_RETURN")
@SequenceGenerator(name = "SEQ_STORE_RETURN", sequenceName = "SEQ_STORE_RETURN")
public class StoreReturn implements BaseEntity {
	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	*/ 
	private static final long serialVersionUID = 3483212756716346472L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 批次 */
	@Column(name="batchcode")
	private String batchcode;
	/** 材料 */
	@Column(name="material")
	private String material;
	/** RFID */
	@Column(name="rfid")
	private String rfidCode;
	/** 型号 */
	@Column(name="model")
	private String model;
	/** 颜色 */
	@Column(name="colour")
	private String colour;
	/** 数量 */
	@Column(name="count")
	private String count;
	/** 位置 */
	@Column(name="poistion")
	private String poistion;
	/** 叉车编号 */
	@Column(name="forklift_code")
	private String forklift_code;
	/** 机台 */
	@Column(name="board")
	private String board;
	/** 备注 */
	@Column(name = "remark")
	private String remark;
	/** 操作人 */
	@Column(name = "create_by")
	private String createBy;
	/** 单位 */
	@Column(name = "unit")
	private String unit;
	/** 入库时间 */
	@Column(name = "store_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date storeDate;
	/** 退料时间 */
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;

	public Date getStoreDate() {
		return storeDate;
	}

	public void setStoreDate(Date storeDate) {
		this.storeDate = storeDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

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
	 * @return  batchcode
	 */
	public String getBatchcode() {
		return batchcode;
	}

	/**
	 *  @param batchcode 要设置的 batchcode 
	 *    
	 */
	public void setBatchcode(String batchcode) {
		this.batchcode = batchcode;
	}

	/**
	 * @return  material
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 *  @param material 要设置的 material 
	 *    
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

	

	/**
	 * @return  rfidCode
	 */
	public String getRfidCode() {
		return rfidCode;
	}

	/**
	 *  @param rfidCode 要设置的 rfidCode 
	 *    
	 */
	public void setRfidCode(String rfidCode) {
		this.rfidCode = rfidCode;
	}

	/**
	 * @return  model
	 */
	public String getModel() {
		return model;
	}

	/**
	 *  @param model 要设置的 model 
	 *    
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return  colour
	 */
	public String getColour() {
		return colour;
	}

	/**
	 *  @param colour 要设置的 colour 
	 *    
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * @return  count
	 */
	public String getCount() {
		return count;
	}

	/**
	 *  @param count 要设置的 count 
	 *    
	 */
	public void setCount(String count) {
		this.count = count;
	}

	/**
	 * @return  poistion
	 */
	public String getPoistion() {
		return poistion;
	}

	/**
	 *  @param poistion 要设置的 poistion 
	 *    
	 */
	public void setPoistion(String poistion) {
		this.poistion = poistion;
	}

	/**
	 * @return  forklift_code
	 */
	public String getForklift_code() {
		return forklift_code;
	}

	/**
	 *  @param forklift_code 要设置的 forklift_code 
	 *    
	 */
	public void setForklift_code(String forklift_code) {
		this.forklift_code = forklift_code;
	}

	/**
	 * @return  board
	 */
	public String getBoard() {
		return board;
	}

	/**
	 *  @param board 要设置的 board 
	 *    
	 */
	public void setBoard(String board) {
		this.board = board;
	}

	/**
	 * @return  remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 *  @param remark 要设置的 remark 
	 *    
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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

	/**
	 * @return  unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 *  @param unit 要设置的 unit 
	 *    
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	
}