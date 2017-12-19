package com.css.business.web.subsysplan.plaManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.business.web.subsysplan.bean.PlaProductOrderAxis;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("plaProductOrderAxisManageDAO")
public class PlaProductOrderAxisManageDAO extends BaseEntityDaoImpl<PlaProductOrderAxis>  {

	/**
	 * @TODO: 根据生产令编号，取轴列表
	 * @author: zhaichunlei
	 & @DATE : 2017年8月4日
	 * @param productOrderCode
	 * @return
	 */
	public List<PlaProductOrderAxis>  getListByProOrderCode(String productOrderCode){
		List<PlaProductOrderAxis> lst = this.findBy("productOrderCode", productOrderCode);
		return lst;
	}
}
