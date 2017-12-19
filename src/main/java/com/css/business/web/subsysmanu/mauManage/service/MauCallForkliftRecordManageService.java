package com.css.business.web.subsysmanu.mauManage.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauCallForkliftRecord;
import com.css.business.web.subsysmanu.bean.MauForklift;
import com.css.business.web.subsysmanu.mauManage.dao.MauCallForkliftRecordManageDAO;
import com.css.business.web.subsysmanu.mauManage.dao.MauMachineManageDAO;
import com.css.commcon.util.YorkUtil;
import com.css.common.util.EhCacheFactory;
import com.css.common.util.PropertyFileUtil;
import com.css.common.web.apachemq.handle.ApacheMqSender_MQTT;
import com.css.common.web.apachemq.handle.test.MqttBroker;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.google.gson.Gson;

@Service("mauCallForkliftRecordManageService")
public class MauCallForkliftRecordManageService
		extends
		BaseEntityManageImpl<MauCallForkliftRecord, MauCallForkliftRecordManageDAO> {
	@Resource(name = "mauCallForkliftRecordManageDAO")
	private MauCallForkliftRecordManageDAO dao;

	@Resource(name = "mauMachineManageDAO")
	private MauMachineManageDAO mauMachineManageDAO;

	private PropertyFileUtil pfu = new PropertyFileUtil();

	private EhCacheFactory factory = EhCacheFactory.getInstance();
	private Gson gson = new Gson();

	@Override
	public MauCallForkliftRecordManageDAO getEntityDaoInf() {
		return dao;
	}

	/**
	 * @Description: 查询不同地方的叉车呼叫
	 * @param condtion
	 *            查询地点
	 * @return
	 */
	public List<MauCallForkliftRecord> queryCallList(String condition) {
		// TODO Auto-generated method stub
		String hql = null;
		try {
			if (condition != null && condition != "") {
				hql = "from MauCallForkliftRecord where mcfr_code ='"
						+ condition
						+ "' and reply_by != null order by create_date DESC";
			} else {
				hql = "from MauCallForkliftRecord reply_by where reply_by IS NULL order by create_date DESC ";
			}
			Query createQuery = dao.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<MauCallForkliftRecord> list = createQuery.list();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 新增一条记录并获取id
	 * 
	 * @data:2017年7月21日
	 * @return
	 * @autor:wl
	 */
	public Integer saveCallForkliftRecord() {
		Integer id = 0;
		try {
			String sql = "INSERT INTO mau_call_forklift_record(id,call_time)  VALUES (nextval('seq_mau_call_forklift_record'),now()) ";

			id = dao.saveCallForkliftRecord(sql);
			// 查询未领取的叉车呼叫
			List<MauCallForkliftRecord> callCarList = this.queryCallCarList();
			factory.addWebsocketCmdCache(YorkUtil.Memcache_看板_叉车呼叫,
					gson.toJson(callCarList));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return id;
	}

	
	/**
	 * 叉车接单
	 * 
	 * @data:2017年7月31日
	 * @param forkliftName
	 * @param id
	 * @return
	 * @autor:wl
	 */
	public HashMap<String, Object> callForkliftRecieveBill(String forkliftName,
			Integer id) {
		String msg = "";
		// TODO Auto-generated method stub
		String hql = "update MauCallForkliftRecord set fType=?  where  id=?";
		try {
			dao.callForkliftRecieveBill(hql, forkliftName, id);
			msg = "修改成功";
			// 叉车接单成功后发消息队列该任务已经被接收了
			//this.sendMessageTo(forkliftName, id);
			// 查询未领取的叉车呼叫
			List<MauCallForkliftRecord> callCarList = this.queryCallCarList();
			factory.addWebsocketCmdCache(YorkUtil.Memcache_看板_叉车呼叫,
					gson.toJson(callCarList));
			return JsonWrapper.successWrapper(msg, "成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg = e.getMessage();
			return JsonWrapper.failureWrapperMsg(msg, "失败");
		}
	}

	/**
	 * 通知消息队列该任务已经完成接收
	 * @throws JMSException 
	 * 
	 * @data:2017年7月31日
	 * @autor:wl
	 */
	public  void sendMessageTo(List<MauCallForkliftRecord> call) throws JMSException {
		ApacheMqSender_MQTT sender = ApacheMqSender_MQTT.getInstance();
		String prop = pfu.getProp("QEU_TOPIC_PERSIST_CHACHE");
		Gson gson = new Gson();
		String message=gson.toJson(call);
		sender.sendMsg_topic_persist(prop, message);
	} 

	public HashMap<String, Object> endPickMateriel(String rfidNumber,
			String forkliftName, Integer id, long date) {
		String msg = "";
		try {

			String sql = "SELECT  b.ggxh from mau_call_forklift_record a,pla_machine_plan_mater b  "
					+ "where a.course_code=b.course_code  and a.id=?  GROUP BY b.ggxh";
			String sql1 = "select objGgxh from StoreObjDetail where rfidCode=?";
			List<String> list = dao.getRfidNumberById(sql, id);
			String[] rfidArray = rfidNumber.split(",");
			if (null != list && list.size() > 0) {
				if (rfidArray.length != list.size()) {
					msg = "获取的原料与任务制定的规格型号不一致";
					return JsonWrapper.failureWrapperMsg(msg, "失败");
				} else {

					for (String rfid : rfidArray) {
						// 根据RFID去库存里面查询规格型号
						String obj = dao.getobjGgxhByRfid(sql1, rfid);
						if (list.contains(obj)) {
							continue;
						} else {
							msg = "获取的原料与任务制定的规格型号不一致";
							return JsonWrapper.failureWrapperMsg(msg, "失败");
						}
					}
					msg = "成功";
					return JsonWrapper.successWrapper(msg, "成功");
				}
			} else {
				msg = "查询到当前任务信息为空";
				return JsonWrapper.failureWrapperMsg(msg, "失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg = e.getMessage();
			return JsonWrapper.failureWrapperMsg(msg, "失败");
		}
	}

	/**
	 * 发送异常信息到消息队列
	 * 
	 * @data:2017年8月1日
	 * @param map
	 * @return
	 * @autor:wl
	 */
	public boolean sendExceptionToMes(HashMap<String, Object> map) {
		boolean flag = false;
		try {
			boolean object = (boolean) map.get("success");
			if (object) {
				flag = false;
			} else {
				MqttBroker sender;
				sender = MqttBroker.getInstance();
				String prop = pfu.getProp("QEU_TOPIC_PERSIST_CHACHE");
				String message = (String) map.get("data");
				sender.sendMessage(prop, message);
				flag = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			flag = false;
		}

		return flag;
	}

	/**
	 * 叉车卸货时候
	 * 
	 * @data:2017年8月1日
	 * @param machine_id
	 * @param rfidNumber
	 * @param forkliftName
	 * @param id
	 * @param date
	 * @return
	 * @autor:wl
	 */
	public HashMap<String, Object> uninstallMateriel(String machine_id,
			String rfidNumber, String forkliftName, Integer id, long date) {
		String msg = "";
		String sql = "SELECT  b.ggxh from mau_call_forklift_record a,pla_machine_plan_mater b  "
				+ "where a.course_code=b.course_code  and a.id=?  GROUP BY b.ggxh";
		String sql1 = "select objGgxh from StoreObjDetail where rfidCode=?";
		String sql2 = "select a.machine_id,c.id from pla_machine_plan_mater a,mau_call_forklift_record b,mau_machine c "
				+ " where  a.course_code=b.course_code and a.machine_id=c.id   and b.id=?  and mac_mark=?";

		try {
			List<String> list = dao.getRfidNumberById(sql, id);
			String[] rfidArray = rfidNumber.split(",");
			if (null != list && list.size() > 0) {
				if (rfidArray.length != list.size()) {
					msg = "获取的原料与任务制定的规格型号不一致";
					return JsonWrapper.failureWrapperMsg(msg, "失败");
				} else {

					for (String rfid : rfidArray) {
						// 根据RFID去库存里面查询规格型号
						String obj = dao.getobjGgxhByRfid(sql1, rfid);
						if (list.contains(obj)) {
							continue;
						} else {
							msg = "获取的原料与任务制定的规格型号不一致";
							return JsonWrapper.failureWrapperMsg(msg, "失败");
						}
					}

					if (null != machine_id && !"".equals(machine_id)) {
						String macMark = machine_id.substring(1, 4);
						List<Object[]> listId = dao.getMauMachineById(sql2, id,
								macMark);
						if (null != listId && listId.size() > 0) {
							Object[] ObjectArray = listId.get(0);
							Integer id1 = (Integer) ObjectArray[0];
							Integer id2 = (Integer) ObjectArray[1];
							if (id1 == id2) {
								msg = "成功";
								return JsonWrapper.successWrapper(msg, "成功");
							} else {
								msg = "所送机台与规格机台不匹配";
								return JsonWrapper.failureWrapperMsg(msg, "失败");
							}
						} else {
							msg = "未获取到该机台的信息";
							return JsonWrapper.failureWrapperMsg(msg, "失败");
						}

					}

					else {
						msg = "传入的机台RFID为空";
						return JsonWrapper.failureWrapperMsg(msg, "失败");
					}
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg = e.getMessage();
			return JsonWrapper.failureWrapperMsg(msg, "失败");
		}
		return null;

}

/**
 * 叉车完成任务接口
 * @param pro_rfid    插取货物rfid编码
 * @param addr_rfid   地表rfid
 * @param deviceId    叉车编码
 * @return
 */
@Transactional
public HashMap<String,Object> finishTaskService(String pro_rfid, String deviceId,String addr_rfid){
	//更改叉车状态为空闲
	boolean f1 = dao.changeForkState(deviceId);
	if(f1){
		//更改叉车呼叫记录消息的状态为已完成
		boolean f2 = dao.changeForkRecordState(pro_rfid);
		if(f2){
			if(!"".equals(addr_rfid) && addr_rfid != null){
				String[] rfid = pro_rfid.split(",");
				for (int i = 0; i < rfid.length; i++) {
					boolean f3 =  dao.changeStoreAddr(rfid[i], addr_rfid);
					if(!f3){
						return JsonWrapper.failureWrapperMsg(null,"失败");
					}
				}
			}else{
				return JsonWrapper.failureWrapperMsg(null,"失败");
			}
			return JsonWrapper.successWrapper(null,"成功");
		}
	}
	return JsonWrapper.failureWrapperMsg(null,"失败");
}

	/******************************** TG ***************************************/

	/**
	 * @Description: 查询不同地方的叉车呼叫
	 * @param condtion
	 *            查询地点
	 * @return
	 */
	public List<MauCallForkliftRecord> queryCallCarList() {
		String hql = null;
		try {
			hql = "from MauCallForkliftRecord where status = 'N' order by replyTime ASC ";
			Query createQuery = dao.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<MauCallForkliftRecord> list = createQuery.list();
			return list;
		} catch (Exception e) {
			System.out.println("获取呼叫叉车信息异常！");
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<MauForklift> QuerryCanUseForklift(Double weight) {
		String hql = " from MauForklift where weight >= " + weight
				+ " AND status = '空闲' order by weight ";
		List<MauForklift> list = dao.createQuery(hql).list();
		return list;
	}

}
