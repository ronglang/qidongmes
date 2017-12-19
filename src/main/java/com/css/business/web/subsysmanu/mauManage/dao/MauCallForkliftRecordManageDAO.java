package com.css.business.web.subsysmanu.mauManage.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysmanu.bean.MauCallForkliftRecord;
import com.css.business.web.subsysmanu.bean.MauForklift;
import com.css.business.web.subsysstore.bean.StoreCoilSa;
import com.css.business.web.subsysstore.bean.StoreMrecord;
import com.css.business.web.subsysstore.bean.StoreObj;
import com.css.common.util.PropertyFileUtil;
import com.css.common.web.apachemq.handle.ApacheMqSender_MQTT;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.google.gson.Gson;

@Repository("mauCallForkliftRecordManageDAO")
public class MauCallForkliftRecordManageDAO extends
		BaseEntityDaoImpl<MauCallForkliftRecord> {
	
	private PropertyFileUtil pfu = new PropertyFileUtil();

	public Integer saveCallForkliftRecord(String sql) {
		Integer id = 0;
		try {
			String hql = "from MauCallForkliftRecord order by id desc";
			Query query = this.createSQLQuery(sql);
			query.executeUpdate();
			List<MauCallForkliftRecord> list = this.createQuery(hql).list();
			MauCallForkliftRecord obj = null;
			if (null != list && list.size() > 0) {
				obj = list.get(0);
				id = obj.getId();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return id;
	}

	public void callForkliftRecieveBill(String hql, String forkliftName,
			Integer id) {

		// TODO Auto-generated method stub
		try {
			Query query = this.createQuery(hql, forkliftName, id);
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 根据呼叫叉车记录id号查询原料的RFID号
	 * 
	 * @data:2017年8月1日
	 * @param sql
	 * @param id
	 * @return
	 * @autor:wl
	 */
	public List<String> getRfidNumberById(String sql, Integer id) {
		List<String> list = null;
		try {
			list = this.createSQLQuery(sql, id).list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return list;
	}

	/**
	 * 根据RFID号查询当前原料入库的规格型号
	 * 
	 * @data:2017年8月1日
	 * @param rfid
	 * @return
	 * @autor:wl
	 */
	public String getobjGgxhByRfid(String hql, String rfid) {
		// TODO Auto-generated method stub
		String obj = null;
		try {
			List<String> list = this.createQuery(hql, rfid).list();
			if (null != list && list.size() > 0) {
				obj = list.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		return obj;
	}

	/**
	 * 根据呼叫叉车记录表ID号去获取机台的id号
	 * 
	 * @data:2017年8月1日
	 * @param sql2
	 * @param id
	 * @return
	 * @autor:wl
	 */
	public List<Object[]> getMauMachineById(String sql2, Integer id,
			String macRemark) {
		// TODO Auto-generated method stub
		List<Object[]> list = new ArrayList<>();
		try {
			list = this.createSQLQuery(sql2, id, macRemark).list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 叉车完成任务更改叉车状态为空闲
	 * 
	 * @param f_code
	 */
	public boolean changeForkState(String fType) {

		String hql = "from MauForklift where fType = ?";

		@SuppressWarnings("unchecked")
		List<MauForklift> list = this.createQuery(hql, fType).list();
		if (list.size() > 0) {
			MauForklift mf = list.get(0);
			mf.setStatus("空闲");
			try {
				this.updateByCon(mf, false);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;

	}

	/**
	 * 改变呼叫叉车记录信息的叉车状态
	 * 
	 * @param taskId
	 */
	public boolean changeForkRecordState(String f_code) {

		String hql = "from MauCallForkliftRecord where rfidCode = ? and status = ?";
		String str = MauCallForkliftRecord.NO_status;
		@SuppressWarnings("unchecked")
		List<MauCallForkliftRecord> list = this.createQuery(hql,f_code,str).list();
		if (list.size() > 0) {
			MauCallForkliftRecord mf = list.get(0);
			mf.setStatus("已完成");
			try {
				this.updateByCon(mf, false);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean changeStoreAddr(String pro_rfid, String addr_rfid) {
		String hql = "from StoreObj where rfidCode = ? order by createDate DESC";
		List<StoreObj> list = this.createQuery(hql, pro_rfid).list();
		if (list.size() > 0) {
			StoreObj so = list.get(0);
			StoreCoilSa sa = this.getAddressByRfid(addr_rfid);
			so.setAddress(sa.getArea_name());
			try {
				this.updateByCon(so, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String sql = "from StoreMrecord where materGgxh = ? and batchCode = ? and ( address IS NULL OR address = '') ";
			List<StoreMrecord> smList = this.createQuery(sql, so.getObjGgxh(), so.getBatchCode()).list();
			StoreMrecord sm = new StoreMrecord();
			if(smList.size()>0||smList!=null){
				sm = smList.get(0);
			}
			sm.setAddress(sa.getArea_name());
			try {
				this.updateByCon(sm, false);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return false;
	}
	
	
	@SuppressWarnings("unchecked")
	public StoreCoilSa getAddressByRfid(String rfid){
		String hql = " from StoreCoilSa where areaRfid = ? ";
		List<StoreCoilSa> list = this.createQuery(hql,rfid).list();
		if(list==null || list.size()<1){
			return null;
		}
		return list.get(0);
	}
	
	
	public  void sendMessageTo(List<MauCallForkliftRecord> call) throws JMSException {
		ApacheMqSender_MQTT sender = ApacheMqSender_MQTT.getInstance();
		String prop = pfu.getProp("QEU_TOPIC_PERSIST_CHACHE");
		Gson gson = new Gson();
		String message=gson.toJson(call);
		sender.sendMsg_topic_persist(prop, message);
	} 

}
