package com.css.business.web.sysManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.business.web.sysManage.bean.SysConfig;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("sysConfigManageDAO")
public class SysConfigManageDAO extends BaseEntityDaoImpl<SysConfig>  {
	
	/**
	 * @TODO: 根据item取它的值
	 * @author: zhaichunlei
	 & @DATE : 2017年8月3日
	 * @param item
	 * @return
	 */
	public String getValueByItem(String item){
		List<SysConfig> lst = this.findBy("item", item);
		if(lst == null || lst.size() == 0)
			return null;
		else
			return lst.get(0).getValue();
	}
}
