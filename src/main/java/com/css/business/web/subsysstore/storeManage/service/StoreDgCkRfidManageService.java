package com.css.business.web.subsysstore.storeManage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.business.web.subsysstore.bean.StoreDgCkDetail;
import com.css.business.web.subsysstore.bean.StoreDgCkRfid;
import com.css.business.web.subsysstore.bean.StoreMrecord;
import com.css.business.web.subsysstore.bean.StoreObj;
import com.css.business.web.subsysstore.storeManage.dao.StoreDgCkRfidManageDAO;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("storeDgCkRfidManageService")
public class StoreDgCkRfidManageService extends BaseEntityManageImpl<StoreDgCkRfid, StoreDgCkRfidManageDAO> {
	@Resource(name="storeDgCkRfidManageDAO")
	//@Autowired
	private StoreDgCkRfidManageDAO dao;
	@Autowired
	private StoreObjManageService storeObjManageService;
	@Autowired
	private StoreMrecordManageService storeMrecordManageService;
	@Autowired
	private StoreDgCkDetailManageService storeDgCkDetailManageService;
	@Override
	public StoreDgCkRfidManageDAO getEntityDaoInf() {
		return dao;
	}
	
	public void SaveStoreDgCkRfid(StoreDgCkRfid entity) throws Exception{
		dao.save(entity);
	}
/**
 * 手机端录入原料RFID和出库单编号  保存记录到store_dg_ck_rfid表
 * @param rfid
 * @param outBoundCode
 * @return
 */
	public HashMap<String, Object> saveRfidObj(String rfid,String outBoundCode) {
 		StoreObj obj=null;
		StoreMrecord storeMrecord=null;
		Integer materialId=0;
		StoreDgCkDetail storeDgCkDetail=null;
		try {
			List<StoreObj> queryStoreRfidList = storeObjManageService.queryStoreRfid(rfid);
			if(null!=queryStoreRfidList&&queryStoreRfidList.size()>0){
				StoreDgCkRfid dgCkRfid = new StoreDgCkRfid();
				dgCkRfid.setOutboundOrderCode(outBoundCode);
				dgCkRfid.setRfidCode(rfid);
				obj =queryStoreRfidList.get(0);
				String objGgxh = obj.getObjGgxh();
				String objName = obj.getObjName();
				if(null!=objName&&!"".equals(objName)&&null!=objGgxh&&!"".equals(objGgxh)){
					storeMrecord=storeMrecordManageService.getMaterialIdByMatName(objName,objGgxh);
					if(null!=storeMrecord){
						 materialId = storeMrecord.getId();
						 dgCkRfid.setMaterialId(materialId);
					}
					
				}
				
					dgCkRfid.setObjGgxh(objGgxh);
					storeDgCkDetail=storeDgCkDetailManageService.queryDetailByCode(outBoundCode,objGgxh,materialId);
					if(null!=storeDgCkDetail){
						dgCkRfid.setDetailId(storeDgCkDetail.getId());
					}
					dgCkRfid.setCreateDate(new Date());
					dao.save(dgCkRfid);
			}else{
				StoreDgCkRfid dgCkRfid = new StoreDgCkRfid();
				dgCkRfid.setRfidCode(rfid);
				dao.save(dgCkRfid);
			}
			return JsonWrapper.successWrapper("成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
		// TODO Auto-generated method stub
	}
	
	
	
	
	
}
