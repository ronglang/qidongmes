/**
 * @todo: 
 * @author : zhaichunlei
 * @date: 2017年12月13日
 */
package com.css.business.web.subsysplan.plaManage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaMacTaskRecord;
import com.css.business.web.subsysplan.plaManage.bean.PlaMacTaskVO;
import com.css.business.web.subsysplan.plaManage.dao.PlaMacTaskRecordManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

/**
 * @author Administrator
 *
 */
@Service("plaMacTaskRecordManageService")
public class PlaMacTaskRecordManageService  extends BaseEntityManageImpl<PlaMacTaskRecord, PlaMacTaskRecordManageDAO>{
	@Resource(name = "plaMacTaskRecordManageDAO")
	private PlaMacTaskRecordManageDAO dao;

	@Override
	public PlaMacTaskRecordManageDAO getEntityDaoInf() {
		return dao;
	}
	
	public List<PlaMacTaskVO> queryMachineTaskSummer(String maccode){
		return dao.queryMachineTaskSummer(maccode);
	}
}
