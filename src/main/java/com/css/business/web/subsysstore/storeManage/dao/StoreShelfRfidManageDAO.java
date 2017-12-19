package com.css.business.web.subsysstore.storeManage.dao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.business.web.subsysstore.bean.StoreShelfRfid;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
@Repository
public class StoreShelfRfidManageDAO extends BaseEntityDaoImpl<StoreShelfRfid> {
	public List<StoreShelfRfid> getShelfRfidDAO() {
		 StringBuffer sql=new StringBuffer("select ");
		   sql.append("s.id,s.shelf_num shelfnum,s.whether_done whetherDone,s.relativex relativex,s.relativey relativey,s.workroomx workroomx ,"
		   		+ "s.workroomy workroomy,s.shelf_rfid shelfrfid ");
		   sql.append("from store_shelf_rfid s where 1=1 ");
		   sql.append("order by s.id asc ");
		   List<StoreShelfRfid> listRfid=new ArrayList<StoreShelfRfid>(); 
		   List<?> list=this.createSQLQuery(sql.toString()).list();
		   Iterator<?> it=list.iterator();
		   while(it.hasNext()){
			 Object[] obj=(Object[]) it.next();
			 StoreShelfRfid ssr=new StoreShelfRfid();
			 ssr.setId((Integer)obj[0]);
			 ssr.setShelfNum((String)obj[1]);
			 ssr.setWhetherDone((String)obj[2]);
			 ssr.setRelativex((Integer)obj[3]);
			 ssr.setRelativey((Integer)obj[4]);
			 ssr.setWorkroomx((Integer)obj[5]);
			 ssr.setWorkroomy((Integer)obj[6]);
			 ssr.setShelfRfid((String)obj[7]);
			 listRfid.add(ssr);
		   }
		   return listRfid;
	}
	public void saveShelfRfidDataDAO(List<StoreShelfRfid> list) {
	     for(StoreShelfRfid ssf:list){
	    	 getHibernateTemplate().saveOrUpdate(ssf);
	}
}
}
