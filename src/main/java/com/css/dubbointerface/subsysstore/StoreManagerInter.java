package com.css.dubbointerface.subsysstore;

import java.util.List;

import com.css.business.web.subsysplan.bean.PlnPlan;
import com.css.business.web.subsysstore.bean.StoreMrecord;

public interface StoreManagerInter {


	/**
	 * @TODO: 原材料入库保存（原料材新购买入库、退料入库）
	 * @author: zhaichunlei
	 & @DATE : 2017年4月21日
	 * @param name
	 */
	public void saveStore(StoreMrecord ent);
}
