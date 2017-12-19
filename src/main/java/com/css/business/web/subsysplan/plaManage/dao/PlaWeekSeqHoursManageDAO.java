package com.css.business.web.subsysplan.plaManage.dao;

import org.springframework.stereotype.Repository;

import com.css.business.web.subsysplan.bean.PlaWeekSeqHours;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("plaWeekSeqHoursManageDAO")
public class PlaWeekSeqHoursManageDAO extends BaseEntityDaoImpl<PlaWeekSeqHours>  {

	/**
	 * @TODO: 根据生产令id删除数据
	 * @author: zhaichunlei
	 & @DATE : 2017年8月3日
	 * @param plaProductId
	 */
	public void deleteByPlaProductId(Integer plaProductId){
		this.deleteBySql("delete from PlaWeekSeqHours where plaOrderId", plaProductId);
	}
}
