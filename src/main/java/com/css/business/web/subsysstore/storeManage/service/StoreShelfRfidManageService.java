package com.css.business.web.subsysstore.storeManage.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.business.web.subsysstore.bean.StoreShelfRfid;
import com.css.business.web.subsysstore.storeManage.dao.StoreShelfRfidManageDAO;
import com.css.common.web.syscommon.dao.support.Page;

@Service
public class StoreShelfRfidManageService {
	@Autowired
	private StoreShelfRfidManageDAO dao;
	public List<StoreShelfRfid> getShelfRfidService() {
		// TODO Auto-generated method stub
		return(dao.getShelfRfidDAO());
	} 
	public void saveShelfRfidData(List<StoreShelfRfid> list) {
       dao.saveShelfRfidDataDAO(list);
	}
}
