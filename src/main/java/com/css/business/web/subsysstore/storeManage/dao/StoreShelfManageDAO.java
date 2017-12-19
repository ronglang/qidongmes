package com.css.business.web.subsysstore.storeManage.dao;

import org.springframework.stereotype.Repository;

import com.css.business.web.subsysstore.bean.StoreShelf;
import com.css.business.web.subsysstore.storeManage.bean.StoreShelfListVO;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;
@Repository("storeShelfManageDAO")
public class StoreShelfManageDAO extends BaseEntityDaoImpl<StoreShelf>  {
	/*public Page toDateGridPageDao(String sql,Page page, StoreShelf storeshelf,StoreShelfListVO storeShelfListVO) throws Exception {
		return(this.pageSQLQueryVONoneDesc(sql, page.getPageNo(), page.getPagesize(), storeShelfListVO));
	}*/
	 public Page toDateGridPageDao(Page p,StoreShelf ent) throws Exception{
		   StringBuffer sql=new StringBuffer("select ");
		   sql.append("s.id,s.shelf_name shelfname,s.floor floor,s.columns,s.capacity_each capacityeach,s.remain ,s.package_type packagetype ");
		   sql.append("from store_shelf s where 1=1 ");
		   String shelfname=ent.getShelfName();
		   String floor=ent.getFloor();
		   if(shelfname!=null&&shelfname.length()>0){
			  String shelfname1=shelfname.trim();
			   sql.append("and s.shelf_name like '%"+shelfname1+"%'");
		   }
		   if(floor!=null&&floor.length()>0){ 
			   sql.append("and s.floor='"+floor+"' ");
		   }  
		   sql.append("order by s.id asc ");
		   Page pp=this.pageSQLQueryVONoneDesc(sql.toString(), p.getPageNo(), p.getPagesize(),new StoreShelfListVO());
		   return pp;
	   }
	public void toSaveDataDao(StoreShelf storeShelf) {
		getHibernateTemplate().saveOrUpdate(storeShelf);
	}
	public void deletedao(Integer id) {
		     getHibernateTemplate().delete(getHibernateTemplate().get(StoreShelf.class,id));
			} 
}
