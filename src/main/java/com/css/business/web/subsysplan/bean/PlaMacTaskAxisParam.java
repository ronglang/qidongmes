package com.css.business.web.subsysplan.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.css.common.web.syscommon.bean.BaseEntity;

/**
 * @ClassName: PlaMacTaskAxisParam
 * @Description: 
 * @author RB
 * @date 2017年11月18日 上午9:39:53
 * 
 */
@Entity
@Table(name = "PLA_MAC_TASK_COLOR")
public class PlaMacTaskAxisParam implements BaseEntity {

	public String getWorkcode() {
		return workcode;
	}

	public void setWorkcode(String workcode) {
		this.workcode = workcode;
	}

	public String getMaccode() {
		return maccode;
	}

	public void setMaccode(String maccode) {
		this.maccode = maccode;
	}

	public String getSeqcode() {
		return seqcode;
	}

	public void setSeqcode(String seqcode) {
		this.seqcode = seqcode;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public int getAxiscount() {
		return axiscount;
	}

	public void setAxiscount(int axiscount) {
		this.axiscount = axiscount;
	}

	public String getAxiscode() {
		return axiscode;
	}

	public void setAxiscode(String axiscode) {
		this.axiscode = axiscode;
	}

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -780119802982164756L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_pla_mac_task_color", sequenceName = "seq_pla_mac_task_color")
	@GeneratedValue(generator = "seq_pla_mac_task_color", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "workcode")
	private String workcode;

	@Column(name = "maccode")
	private String maccode;

	@Column(name = "seqcode")
	private String seqcode;

	@Column(name = "color")
	private String color;

	@Column(name = "length")
	private float length;

	@Column(name = "axiscount")
	private int axiscount;

	@Column(name = "axiscode")
	private String axiscode;

	@Column(name = "product")
	private String product;

	@Column(name = "step")
	private Integer step;
	
	@Column(name = "create_date")
	private Timestamp createDate;
	
	@Column(name="rfid")
	private String rfid;

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public PlaMacTaskAxisParam() {
		color = "";
		length = 0;
		axiscount = 1;
		axiscode = "";
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub

	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

}
