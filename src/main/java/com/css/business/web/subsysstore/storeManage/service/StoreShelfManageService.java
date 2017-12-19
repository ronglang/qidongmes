package com.css.business.web.subsysstore.storeManage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.css.business.web.subsysmanu.bean.MauHandlingChores;
import com.css.business.web.subsysstore.bean.StoreShelf;
import com.css.business.web.subsysstore.storeManage.bean.StoreShelfListVO;
import com.css.business.web.subsysstore.storeManage.dao.StoreShelfManageDAO;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
@Service("storeShelfManageService")
public class StoreShelfManageService 
extends BaseEntityManageImpl<StoreShelf, StoreShelfManageDAO> {
	@Resource(name="storeShelfManageDAO")
	//@Autowired
	private StoreShelfManageDAO dao;
	@Override
	public StoreShelfManageDAO getEntityDaoInf() {
		return dao;  
	}
//	@Transactional(readOnly=true)
	/*public Page toDateGridPageService(Page page, StoreShelf storeshelf) throws Exception {
		StoreShelfListVO storeShelfListVO=new StoreShelfListVO();
		StringBuilder sb=new StringBuilder("select s.id,s.shelf_name shelfname,s.floor floor,s.column,"
				+ "s.capacity_each capacityeach,s.remain ,s.package_type packagetype from store_shelf s "
				+ "where 1=1 ");
		if(storeshelf.getShelfName()!=null){
			sb.append("and shelfname like %" +storeshelf.getShelfName()+"% ");
		}
		if(storeshelf.getFloor()!=null){
			sb.append("and floor ="+storeshelf.getFloor()+"");
		}
		return (dao.toDateGridPageDao(sb.toString(),page,storeshelf, storeShelfListVO));
	}
*/
	public  Page toDateGridPageService(Page p,StoreShelf ent) throws Exception{
		return dao.toDateGridPageDao(p, ent);
	}
	
	public void toSaveData(StoreShelf storeShelf) throws Exception {
		dao.toSaveDataDao(storeShelf);
	
	}
//	@Transactional
	public void delete(String ids) throws Exception {
		String[] str=ids.split(",");
		  for (int i = 0; i < str.length; i++) {
			int id=Integer.parseInt(str[i]);
		    dao.deletedao(id);
		  }
	}
	
	@SuppressWarnings("rawtypes")
	public Map[] getFloor(){
		String hql = "SELECT DISTINCT floor FROM StoreShelf WHERE floor != '' ";
		@SuppressWarnings("unchecked")
		List<Object> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) { //根据ligerForm下拉框数据格式，封装数据
			Map<String, String> map = new HashMap<String, String>();
				for (int j = 0; j < 2; j++) {
					if(j == 0) {
						map.put("text", list.get(i).toString());
					}else {
						map.put("key", i + "");
					}
			}
			str[i] = map;
			
		}
		return str;
	}
	
	
}

