package com.css.business.web.subsysmanu.mauManage.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysmanu.bean.SgRecord;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository
public class SgRecordManageDAO extends BaseEntityDaoImpl<SgRecord>{

	public List<SgRecord> getSgRecordDAO() {
		 StringBuffer sql=new StringBuffer("select ");
		   sql.append("s.id,s.sg_types sgtypes,s.rfid_num rfidnum,s.sg_state sgstate,s.name ");
		   sql.append("from sg_record s where 1=1 ");
		   sql.append("order by s.id asc ");
		   List<SgRecord> listRfid=new ArrayList<SgRecord>(); 
		   List<?> list=this.createSQLQuery(sql.toString()).list();
		   Iterator<?> it=list.iterator();
		   while(it.hasNext()){
			 Object[] obj=(Object[]) it.next();
			 SgRecord ssr=new SgRecord();
			 ssr.setId((Integer)obj[0]);
			 ssr.setSgtypes((String)obj[1]);
			 ssr.setRfidnum((String)obj[2]);
			 ssr.setSgstate((String)obj[3]);
			 ssr.setName((String)obj[4]);
			 listRfid.add(ssr);
		   }
		   return listRfid;
		
	}

	public void sgMachineDataDAO(String strRfid) {
		// TODO Auto-generated method stub
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String sql="update sg_record set sg_state='已施工' where rfid_num="+"'"+strRfid+"'";
//		this.createSQLQuery(sql);
		Query query=session.createSQLQuery(sql);
		query.executeUpdate();
		tx.commit();
		session.close();
	}
  
}
