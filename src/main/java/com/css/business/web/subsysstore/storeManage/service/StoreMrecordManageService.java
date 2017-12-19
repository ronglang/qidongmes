package com.css.business.web.subsysstore.storeManage.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaMacTask;
import com.css.business.web.subsysplan.bean.PlaMacTaskMateril;
import com.css.business.web.subsysstore.bean.StoreCoilSa;
import com.css.business.web.subsysstore.bean.StoreMrecord;
import com.css.business.web.subsysstore.bean.StoreObj;
import com.css.business.web.subsysstore.storeManage.dao.StoreMrecordManageDAO;
import com.css.business.web.subsysstore.storeManage.dao.StoreScrapRecordManageDAO;
import com.css.business.web.subsysstore.storeManage.vo.JudgeMaterVo;
import com.css.common.util.PropertyFileUtil;
import com.css.common.web.apachemq.handle.ApacheMqSender;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.google.gson.Gson;

@Service("storeMrecordManageService")
public class StoreMrecordManageService extends
		BaseEntityManageImpl<StoreMrecord, StoreMrecordManageDAO> {
	@Resource(name = "storeMrecordManageDAO")
	// @Autowired
	private StoreMrecordManageDAO dao;

	@Resource(name = "storeScrapRecordManageDAO")
	private StoreScrapRecordManageDAO srDao;

	@Override
	public StoreMrecordManageDAO getEntityDaoInf() {
		return dao;
	}

	public Double getActStock(String objGgxh) {
		String hql = " from StoreMrecord s where s.materGgxh = ? ";
		@SuppressWarnings("unchecked")
		List<StoreMrecord> list = dao.createQuery(hql, objGgxh).list();
		return list.get(0).getActStock();
	}

	public void updateStock(StoreMrecord sm) throws Exception {
		dao.updateByCon(sm, false);
	}

	public void deleteStcok(Integer id) {
		String sql = " DELETE FROM store_mrecord WHERE id = ? ";
		dao.deleteBySql(sql, id);
	}

	public void saveStock(StoreMrecord sm) {
		dao.save(sm);
	}

	@SuppressWarnings("unchecked")
	public List<StoreMrecord> getStock(String rfid) {
		String hql = " from StoreMrecord where rfid = '" + rfid + "' ";
		List<StoreMrecord> list = dao.createQuery(hql).list();
		return list;
	}

	public List<StoreMrecord> getAllStock() {
		String hql = " from StoreMrecord ";
		@SuppressWarnings("unchecked")
		List<StoreMrecord> list = dao.createQuery(hql).list();
		return list;
	}

	/**
	 * 根据ID查询相应的库存信息
	 * 
	 * @param id
	 * @return
	 */
	public List<StoreMrecord> getStoreMrecord(Integer id) {
		String hql = "  from StoreMrecord where id = ? ";
		@SuppressWarnings("unchecked")
		List<StoreMrecord> list = dao.createQuery(hql, id).list();
		return list;
	}

	/**
	 * 根据物料名称去查询id
	 * 
	 * @param objName
	 * @return
	 */
	public StoreMrecord getMaterialIdByMatName(String objName, String objGgxh) {
		// TODO Auto-generated method stub
		StoreMrecord storeMrecord = null;
		try {
			String hql = " from StoreMrecord where materName=? and materGgxh=?";
			List<StoreMrecord> list = dao.getMaterialIdByMatName(hql, objName,
					objGgxh);
			if (null != list && list.size() > 0) {
				storeMrecord = list.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return storeMrecord;
	}

	/**
	 * @Description: 分类查询 总量
	 * @param materType
	 *            物料类型
	 * @return
	 */
	public List<Map<String, String>> getClassify(String materType) {
		// TODO Auto-generated method stub
		String hql = "select materName,sum(actStock) from StoreMrecord where materType='"
				+ materType + "' group by materName";
		@SuppressWarnings("unchecked")
		List<Object[]> list = dao.createQuery(hql).list();
		List<Map<String, String>> resultList = new ArrayList<>();
		for (Object[] objs : list) {
			Map<String, String> map = new HashMap<String, String>();
			// map.put(objs[0].toString(),
			// objs[1]==null?"0":objs[2].toString());
			map.put("type", "原材料");
			map.put("key", objs[0].toString());
			map.put("value", objs[1] == null ? "0" : objs[1].toString());
			resultList.add(map);
		}
		return resultList;
	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 */
	public List<Map<String, String>> getObjAndScap() {
		// TODO Auto-generated method stub
		List<Map<String, String>> stList = this.getClassify("原材料");
		// 报废料
		List<Map<String, String>> srList = srDao.getClassify("报废");
		if (srList != null && srList.size() > 0) {
			stList.addAll(srList);
		}
		return stList;
	}

	/**
	 * 判断原材料
	 * 
	 * @param rfidCode
	 *            以","隔开,机台code :线盘P 地标G 原材料R 人员E 机台M
	 */
	public JudgeMaterVo judgeMater(String rfidCodes) {
		String msg = "";
		boolean type = false; 
		List<String>msgList = new ArrayList<>();
		try {
			String[] split = rfidCodes.split(",");
			String macCode = "";
			String materRfid="";
			//扫描出的原材料规格型号
			Set<String>materSet = new HashSet<>();
			// 机台任务用料规格型号
			Set<String>taskMaterSet = new HashSet<>();
			//扫描材料信息
			Map<String,Double>materMap = new HashMap<>(16);
			//机台任务用料信息
			Map<String,Double>taskMaterMap = new HashMap<>(16);
			
			// 1.获得机台Code
			for (String string : split) {
				if (string.startsWith("M")) {
					String sql = "select area_name from store_coil_sa where area_rfid='"+string+"'";
					List<Object> list = dao.createSQLQuery(sql).list(); 
					if(list.size()>0){
						macCode = (String)list.get(0);
					}
					
				} else {
					materRfid = string;
					// 2. 获取原材料
					String sql = "select obj_ggxh ,weight from Store_Obj where rfid_Code = '" + materRfid
							+ "'";
					List<Object> list = dao.createSQLQuery(sql).list(); 
					if (list != null && list.size() > 0) {
						Object[] objects = (Object[])list.get(0);
						if (materMap.get(objects[0].toString()) == null) {
							materMap.put(objects[0].toString(), Double.parseDouble(objects[1].toString()));
						}else {
							Double double1 = materMap.get(objects[0].toString());
							double1 += Double.parseDouble(objects[1].toString());
							materMap.put(objects[0].toString(), double1);
						}
						materSet.add(objects[0].toString());
					}else {
						msgList.add("有rfid未找到物料信息");
					}
				}
			}
			// 查询该机台的当前任务用料情况
			String taskHql = "from PlaMacTask where productstate ='已排产' and maccode ='"+macCode+"' ";
			List<PlaMacTask> list = dao.createQuery(taskHql).list();
			if (list == null || list.size() == 0) {
				msgList.add("未找到机台"+macCode+"当前任务");
			}else {
				PlaMacTask task = (PlaMacTask) dao.createQuery(taskHql).list().get(0);
				String taskMaHql = "select  materil,matecount  from Pla_Mac_Task_Materil where maccode = '"+macCode+"' and workcode = '"+task.getWorkcode()+"' and ptime = '"+task.getPstime()+"' ";
				//查询材料规格
				List<Object[]> taskMaterList = dao.createSQLQuery(taskMaHql).list();
				if (taskMaterList == null || taskMaterList.size() == 0) {
					msgList.add("未找到机台任务用料信息");
				}else{
					for (Object[] objects : taskMaterList) {
						taskMaterMap.put(objects[0].toString(), Double.parseDouble(objects[1].toString()));
						taskMaterSet.add(objects[0].toString());
					}
				}
				
			}
			//比较两个查询方式查到的规格型号
			int re = materSet.size() - taskMaterSet.size() ;
			if (taskMaterSet.size() == 0) {
				msg = "未找到机台任务关联材料信息";
			}else if (materSet.size() == 0) {
				msg = "未查到RFID关联材料信息";
			}else if (re == 0) {
				int size = materSet.size();
				materSet.addAll(taskMaterSet);				
				int size2 = materSet.size();
				if (size != size2) {
					msg = "材料种类不符合,请仔细查找";
				}else {
					msg = "材料种类符合";
					type = true;
				}
			}else if(re > 0 ){
				msg = "扫描的材料种类比任务需要的多";
			}else{
				msg = "扫描的材料种类比任务需要的少";
			}
			
			JudgeMaterVo vo = new JudgeMaterVo();
			vo.setMaterMap(materMap);
			vo.setTaskMaterMap(taskMaterMap);
			vo.setMsgList(msgList);
			vo.setType(type);
			vo.setMsg(msg);
			
			// 判断 是否查到材料 和 maccode 是否存在
			// 3.发送MQ
			PropertyFileUtil pf = new PropertyFileUtil();
			String queue  = pf.getProp("JUDGE_MATER");
			ApacheMqSender mq = ApacheMqSender.getInstance();
			mq.sendMsg_point_to_point(queue+macCode, gson.toJson(vo));
			return vo;
		} catch (Exception e) {
			e.printStackTrace();
			JudgeMaterVo vo = new JudgeMaterVo();
			vo.setType(type);
			vo.setMsg("出现未知错误,请联系管理员");
			return vo;
		}


}
private Gson gson = new Gson();


public boolean checkStore(String materName,
		String materGgxh, String materType, String unit, String rfids,
		String batchCode, Double checkStock) throws Exception{
	String[] rfid = rfids.split(",");
	boolean flag = false;
	String address  = "";
	List<String> gls = new ArrayList<String>();
	List<String> rls = new ArrayList<String>();
	for (String str : rfid) {
		String a = str.subSequence(0, 1).toString();
		if("G".equals(a)){
			gls.add(str);
		}else if("R".equals(a)){
			rls.add(str);
		}
		
	}
	for (int i = 0; i < rls.size(); i++) {
		if(i==0){
			address = this.queryStoreCoilSaByRfid(gls.get(0));
		}
		List<StoreMrecord> stock = this.getStock(rls.get(i));
		StoreMrecord sm = new StoreMrecord();
		StoreObj so = new StoreObj();
		if(stock.size()<1 || stock==null){
			sm.setMaterName(materName);
			sm.setMaterGgxh(materGgxh);
			sm.setMaterType(materType);
			sm.setUnit(unit);
			sm.setBatchCode(batchCode);
			sm.setCheckStock(checkStock);
			sm.setAddress(address);
			sm.setRfid(rls.get(i));
			sm.setCreateDate(new Date());
			so.setWeight(checkStock);
			so.setObjGgxh(materGgxh);
			so.setObjName(materName);
			so.setMaterialType(materType);
			so.setUnit(unit);
			so.setBatchCode(batchCode);
			so.setAddress(address);
			so.setRfidCode(rls.get(i));
			so.setCreateDate(new Date());
			so.setInoutType("入库");
			so.setInDate(new Date());
			dao.save(so);
			dao.save(sm);
			flag =true;
		}else{
			sm = stock.get(0);
			sm.setCheckStock(checkStock);
			sm.setAddress(address);
			sm.setCreateDate(new Date());
			dao.updateByCon(sm, false);
			flag =true;
		}
		
	}
	return flag;
}


/**
 * 根据RFID查询位置名称
 * @param rfid
 * @return
 */
public String queryStoreCoilSaByRfid(String rfid){
	String hql = " from StoreCoilSa where areaRfid = ? ";
	@SuppressWarnings("unchecked")
	List<StoreCoilSa> list = dao.createQuery(hql, rfid).list();
	if(list.size()<1||list==null){
		return "";
	}
	return list.get(0).getArea_name();
}
	
public Page getStoreMrecordByPage(Page p ,String batchCode,String materGgxh){
	String hql = " from StoreMrecord where checkStock IS NOT NULL ";
	if(!"".equals(batchCode) && batchCode!=null ){
		hql +=" and batchCode = '"+batchCode+"' ";
	}
	if(!"".equals(materGgxh) && materGgxh!=null ){
		hql +=" and materGgxh = '"+materGgxh+"' ";
	}
	Page page = dao.pageQuery(hql, p.getPageNo(), p.getPagesize());
	return page;
}

@SuppressWarnings("unchecked")
public void sureCheck(Integer id) throws Exception{
	String hql = " from StoreMrecord where checkStock IS NOT NULL and id = ? ";
	List<StoreMrecord> list = dao.createQuery(hql,id).list();
	for (StoreMrecord storeMrecord : list) {
		storeMrecord.setActStock(storeMrecord.getCheckStock());
		storeMrecord.setCheckStock(null);
		dao.updateByCon(storeMrecord, false);
	}
}


@SuppressWarnings("rawtypes")
public Map[] getSmBatchCode(){
	String hql = " SELECT DISTINCT batchCode FROM StoreMrecord WHERE batchCode IS NOT NULL ";
	@SuppressWarnings("unchecked")
	List<Object> list = dao.createQuery(hql).list();
	Map[] str = new Map[list.size()];
	for (int i = 0; i < list.size(); i++) { // 根据ligerForm下拉框数据格式，封装数据
		Map<String, String> map = new HashMap<String, String>();
		for (int j = 0; j < 2; j++) {
			if (j == 0) {
				map.put("text", list.get(i).toString());
			} else {
				map.put("key", i + "");
			}
		}
		str[i] = map;
	}
	return str;
}

public Map[] getSmMaterGgxh(){
	String hql = " SELECT DISTINCT materGgxh FROM StoreMrecord WHERE materGgxh IS NOT NULL ";
	@SuppressWarnings("unchecked")
	List<Object> list = dao.createQuery(hql).list();
	Map[] str = new Map[list.size()];
	for (int i = 0; i < list.size(); i++) { // 根据ligerForm下拉框数据格式，封装数据
		Map<String, String> map = new HashMap<String, String>();
		for (int j = 0; j < 2; j++) {
			if (j == 0) {
				map.put("text", list.get(i).toString());
			} else {
				map.put("key", i + "");
			}
		}
		str[i] = map;
	}
	return str;
}

public List<PlaMacTaskMateril> getPlaMacTaskMaterilList(){
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	String format = sf.format(new Date());
	String hql = "select * from Pla_Mac_Task_Materil where state = '未领取' and CAST(ptime AS DATE)=DATE '"+format+"' ";
	//List<PlaMacTaskMateril> list = dao.createQuery(hql).list();
	@SuppressWarnings("unchecked")
	List<PlaMacTaskMateril> list = dao.createSQLQuery(hql, PlaMacTaskMateril.class).list();
	return list;
}


}
