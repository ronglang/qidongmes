package com.css.business.web.subsyscraft.bean;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.css.common.web.syscommon.bean.BaseEntity;

/**
 * 集绞方式表
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "cra_gathering_mode")
public class CraGatheringMode implements BaseEntity,
		net.sf.json.processors.JsonBeanProcessor {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_cra_gathering_mode", sequenceName = "seq_cra_gathering_mode")
	@GeneratedValue(generator = "seq_cra_gathering_mode", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "pro_craft_code")
	private String proCraftCode;// 产品工艺编码

	@Column(name = "gathering_mode")
	private String gatheringMode;// 集绞方式

	@Column(name = "pro_ggxh")
	private String proGgxh;// 半成品规格型号

	@Column(name = "pro_color")
	private String proColor;// 颜色

	@Column(name = "amount")
	private Integer amount;// 来料数量

	@Column(name = "create_by")
	private String createBy;// 创建人

	@Column(name = "create_date")
	private Date createDate;// 创建日期

	@Column(name = "relation_id")
	private Integer relationId;

	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}


	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProCraftCode() {
		return proCraftCode;
	}

	public void setProCraftCode(String proCraftCode) {
		this.proCraftCode = proCraftCode;
	}

	public String getGatheringMode() {
		return gatheringMode;
	}

	public void setGatheringMode(String gatheringMode) {
		this.gatheringMode = gatheringMode;
	}

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	public String getProColor() {
		return proColor;
	}

	public void setProColor(String proColor) {
		this.proColor = proColor;
	}

	@Override
	public JSONObject processBean(Object bean, JsonConfig arg1) {
		JSONObject jsonObject = null;
		System.out.println("processor class name:" + bean.getClass().getName());
		if (bean instanceof java.sql.Date) {
			bean = new Date(((java.sql.Date) bean).getTime());
		}
		if (bean instanceof java.sql.Timestamp) {
			System.out.println("bean timestamp");
			bean = new Date(((java.sql.Timestamp) bean).getTime());
		}
		if (bean instanceof Date) {
			jsonObject = new JSONObject();
			jsonObject.element("time", ((Date) bean).getTime());
		} else {
			jsonObject = new JSONObject(true);
		}
		return jsonObject;
	}

}
