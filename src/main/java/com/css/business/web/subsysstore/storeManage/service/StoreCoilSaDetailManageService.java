package com.css.business.web.subsysstore.storeManage.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysstore.bean.StoreCoilSaDetail;
import com.css.business.web.subsysstore.bean.StoreMrecord;
import com.css.business.web.subsysstore.storeManage.dao.StoreCoilSaDetailManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.css.dubbointerface.subsysstore.StoreManagerInter;

@Service("storeCoilSaDetailManageService")
public class StoreCoilSaDetailManageService extends BaseEntityManageImpl<StoreCoilSaDetail, StoreCoilSaDetailManageDAO> 
		implements StoreManagerInter{
	@Resource(name="storeCoilSaDetailManageDAO")
	//@Autowired
	private StoreCoilSaDetailManageDAO dao;
	
	
	@Override
	public StoreCoilSaDetailManageDAO getEntityDaoInf() {
		return dao;
	}

	//对外提供接口服务
	@Override
	public void saveStore(StoreMrecord ent) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 将数据存入StoreCoilSaDetail
	 * @param scsaId
	 * @param coilRfid
	 * @param productType
	 */
	public void saveStoreColiSaD(Integer scsaId,String coilRfid,String productType){
		StoreCoilSaDetail obj = new StoreCoilSaDetail();
		Date date = new Date();
		obj.setScsaId(scsaId);
		obj.setCoilRfid(coilRfid);
		obj.setProductType(productType);
		obj.setCreateBy("JS");
		obj.setCreateDate(date);
		dao.save(obj);
	}
	
	public void delStoreColiSaD(String coilRfid){
		String sql = " delete from store_coil_sa_detail where coil_rfid = ? ";
		dao.deleteBySql(sql, coilRfid);
	}
	
	
}
