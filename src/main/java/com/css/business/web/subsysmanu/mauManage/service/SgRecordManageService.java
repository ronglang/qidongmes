package com.css.business.web.subsysmanu.mauManage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.subsysmanu.bean.SgRecord;
import com.css.business.web.subsysmanu.mauManage.dao.SgRecordManageDAO;

@Service
public class SgRecordManageService {
	@Autowired
	private SgRecordManageDAO dao;

	
	public List<SgRecord> getSgRecord() {
		// TODO Auto-generated method stub
		return dao.getSgRecordDAO();
	}

	public void sgMachineDataService(String string) {
		// TODO Auto-generated method stub
		String strRfid=string+"00000000000000000000";
		dao.sgMachineDataDAO(strRfid);
	}
	
}
