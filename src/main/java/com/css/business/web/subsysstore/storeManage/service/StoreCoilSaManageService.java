package com.css.business.web.subsysstore.storeManage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauRfid;
import com.css.business.web.subsysplan.bean.PlaMacTaskAxisParam;
import com.css.business.web.subsysplan.plaManage.dao.PlaMacTaskAxisParamDAO;
import com.css.business.web.subsysstore.bean.StoreCoilSa;
import com.css.business.web.subsysstore.bean.StoreMrecord;
import com.css.business.web.subsysstore.bean.StoreObj;
import com.css.business.web.subsysstore.storeManage.dao.StoreCoilSaManageDAO;
import com.css.business.web.subsysstore.storeManage.dao.StoreMrecordManageDAO;
import com.css.business.web.subsysstore.storeManage.dao.StoreObjManageDAO;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("storeCoilSaManageService")
public class StoreCoilSaManageService extends
		BaseEntityManageImpl<StoreCoilSa, StoreCoilSaManageDAO> {
	@Resource(name = "storeCoilSaManageDAO")
	// @Autowired
	private StoreCoilSaManageDAO dao;

	@Resource(name = "storeObjManageDAO")
	private StoreObjManageDAO objDao;

	@Resource(name = "plaMacTaskAxisParamDAO")
	private PlaMacTaskAxisParamDAO paramDao;

	@Resource(name = "storeMrecordManageDAO")
	private StoreMrecordManageDAO smDao;

	@Override
	public StoreCoilSaManageDAO getEntityDaoInf() {
		return dao;
	}

	public void SaveStoreCoilSa(StoreCoilSa entity) throws Exception {
		dao.save(entity);
	}

	@SuppressWarnings({ "unchecked" })
	public List<StoreCoilSa> queryAddress(String objGgxh, String objSort) {
		if (objSort != null && "胶料".equals(objSort)) {
			StringBuffer hql = new StringBuffer(
					" SELECT s.id from StoreCoilSa s where s.areaMaterType = ? ORDER BY s.id DESC ");
			List<Integer> list = dao.createQuery(hql.toString(), objGgxh)
					.list();
			List<StoreCoilSa> ll = new ArrayList<StoreCoilSa>();
			for (Integer te : list) {
				StringBuffer hq = new StringBuffer(
						" SELECT s.coilRfid from StoreCoilSaDetail s where s.scsaId = ? ");
				List<String> li = dao.createQuery(hq.toString(), te).list();
				if (li == null || li.size() == 0 || li.get(0) == null) {
					String h = "  from StoreCoilSa s where s.id = ? ";
					List<StoreCoilSa> ls = dao.createQuery(h, te).list();
					ll.add(ls.get(0));
				}
			}
			return ll;
		} else {
			StringBuffer hql = new StringBuffer(
					" SELECT s.id from StoreCoilSa s where s.areaMaterType = ? ORDER BY s.id DESC ");
			List<Integer> list = dao.createQuery(hql.toString(), objGgxh)
					.list();
			List<StoreCoilSa> ll = new ArrayList<StoreCoilSa>();
			for (Integer te : list) {
				// StringBuffer hq = new
				// StringBuffer(" SELECT s.coilRfid from StoreCoilSaDetail s where s.scsaId = ? ");
				// List<String> li = dao.createQuery(hq.toString(), te).list();
				String h = "  from StoreCoilSa s where s.id = ? ";
				List<StoreCoilSa> ls = dao.createQuery(h, te).list();
				ll.add(ls.get(0));
			}
			return ll;
		}

	}

	/**
	 * 查询所有库存区域
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<StoreCoilSa> getStoreCoilSa() {
		String hql = " from StoreCoilSa ";
		List<StoreCoilSa> list = dao.createQuery(hql).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<StoreCoilSa> getareaName(String areaRfid) {
		String hql = " from StoreCoilSa where areaRfid = ? ";
		List<StoreCoilSa> list = dao.createQuery(hql, areaRfid).list();
		return list;
	}

	public void saveStoreCoilSa(StoreCoilSa scs) {
		dao.save(scs);
	}

	public void saveMauRfid(MauRfid mau) {
		dao.save(mau);
	}

	public void updateMauRfid(MauRfid mau) throws Exception {
		dao.updateByCon(mau, false);
	}

	public void updateStoreCoilSa(StoreCoilSa scs) throws Exception {
		dao.updateByCon(scs, false);
	}

	public void delStoreCoilSa(Integer id) {
		StoreCoilSa scs = new StoreCoilSa();
		scs.setId(id);
		dao.remove(scs);
	}

	public HashMap<String, Object> changeAddress(String rfids) throws Exception {
		String[] rd = rfids.split(",");
		String areaName = "";
		List<StoreObj> lists = new ArrayList<StoreObj>();
		List<String> area = new ArrayList<String>();
		List<String> mater = new ArrayList<String>();
		for (int a = 0; a < rd.length; a++) {
			String[] rfid = rd[a].split("-");
			for (int i = 0; i < rfid.length; i++) {
				char at = rfid[i].trim().charAt(0);
				if (at == 'G'|| at== 'M' ) {
					area.add(rfid[i]);
				} else {
					mater.add(rfid[i]);
				}
			}
			for (int i = 0; i < mater.size(); i++) {
				char at = rfid[i].trim().charAt(0);

				List<StoreCoilSa> list = this.getareaName(area.get(i));
				if (list == null || list.size() < 1) {
					JsonWrapper.failureWrapperMsg(list,"地标不存在");
					continue;
				}
				
				areaName = list.get(0).getArea_name();
				if (at == 'P') {
					String rf = rfid[i].trim().substring(0,
							rfid[i].trim().length() - 1);
					lists = objDao.getStoreObjByRfid(rf);
					if (lists == null) {
						List<PlaMacTaskAxisParam> ls= paramDao
								.getPlaMacTaskAxisParamByRfid(rf);
						if (ls == null) {
							continue;
						}
						StoreObj obj = new StoreObj();
						obj.setRfidCode(rf);
						obj.setAddress(areaName);
						dao.save(obj);
					} else {
						lists.get(0).setAddress(areaName);
						dao.updateByCon(lists.get(0), false);
					}
				} else if (at == 'R') {
					lists = objDao.getStoreObjByRfid(rfid[i]);
					if (lists == null) {
						return JsonWrapper.failureWrapperMsg(lists, "物料不存在");
					}
					lists.get(0).setAddress(areaName);
					List<StoreMrecord> stock = smDao.getStock(rfid[i]);
					if(stock!=null&&stock.size()>0){
						stock.get(0).setAddress(areaName);
						dao.updateByCon(stock.get(0), false);
					}
					dao.updateByCon(lists.get(0), false);
				}

			}
		}
		return JsonWrapper.successWrapper(areaName, "挪盘成功");
	}

}
