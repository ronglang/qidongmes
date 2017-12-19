package com.css.business.web.subsysstore.storeManage.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysstore.bean.StoreOutOfStorageRecord;
import com.css.business.web.subsysstore.bean.StoreScrapRecord;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;

@Repository("storeScrapRecordManageDAO")
public class StoreScrapRecordManageDAO extends BaseEntityDaoImpl<StoreScrapRecord>  {
	
	
	@Autowired
	private SessionFactory sessionFactory;



	public String getTotalMsg(StringBuilder sql) {
		StringBuilder msg = new StringBuilder();
		List find = this.getHibernateTemplate().find(sql.toString(),"报废");
		for (Object obj : find) {
		 	Object[] ss= (Object[])obj;
		 	Object a= ss[2];
		 	Object b = ss[0];
		 	if(null!=a&&!"".equals(a)){
		 		msg.append(ss[0]+":"+ss[1]+a+"&emsp;&emsp;&emsp;");
		 	}else{
		 		msg.append(ss[0]+":"+ss[1]+"&emsp;&emsp;&emsp;");
		 	}
		 
		 	
		}
		
		return msg.toString();
	}
	public List<String> getSelectOption(String hql) {
		List<String> list =new ArrayList<>();
		try {
			list=this.createQuery(hql,"来料类型").list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}
	/**
	 * 
	 *@data:2017年7月26日
	@param sql
	@param param
	@return
	@autor:wl
	 */
	public Page getRecordList(StringBuilder sql, Page param) {
		// TODO Auto-generated method stub
		Page page = this.pageQuery(sql.toString(), param.getPageNo(), param.getPagesize());
		return page;
	}
	
	public boolean deleteById(String sql, int id) {
		boolean flag=false;
		try {
			Query query = this.createQuery(sql, id);
			query.executeUpdate();
			flag=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		return flag;
	}
	/*public boolean saveProductManageDao(StoreScrapRecord mpm) {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(mpm);
			session.getTransaction().commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			System.out.println("保存失败");
			
		}finally{
            if (session != null) {
                if (session.isOpen()) {
                    //关闭session
                    session.close();
                }
            }
        }
		return false;
	}
	*/
	
	/*public boolean updateProductManageDao(Object obj) {

		Session session = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) obj;
			StoreScrapRecord mpm = (StoreScrapRecord)session.get(StoreScrapRecord.class, Integer.parseInt(map.get("id").toString()));
			mpm.setBatchCode(map.get("batchCode").toString());
			mpm.setRfidCode(map.get("rfidCode").toString());
			mpm.setModel(map.get("model").toString());
			mpm.setColor(map.get("color").toString());
			mpm.setAmount(Double.parseDouble(map.get("amount").toString()));
			mpm.setUnit(map.get("unit").toString());
			mpm.setMaterialRalation(map.get("materialRalation").toString());
			mpm.setHandler(map.get("handler").toString());
			mpm.setHandleIdea(map.get("handleIdea").toString());
			mpm.setHandleAfterPosition(map.get("handleAfterPosition").toString());
			mpm.setRemark(map.get("remark").toString());
			session.update(mpm);
			session.getTransaction().commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			System.out.println("更改失败");
			
		}finally{
            if (session != null) {
                if (session.isOpen()) {
                    //关闭session
                    session.close();
                }
            }
        }
		return false;
	
		
	}*/
	public List<StoreScrapRecord> getStoreScrapRecordById(String hql, Integer id) {
		 List<StoreScrapRecord> list = this.createQuery(hql, id).list();
		// TODO Auto-generated method stub
		return list;
	}
	
	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @return         
	 */ 
	public List<Map<String, String>> getClassify(String state) {
		// TODO Auto-generated method stub
		String hql = "select materialName,sum(amount) from StoreScrapRecord  where status ='"+state+"' group by materialName";
		@SuppressWarnings("unchecked")
		List<Object[]> list = this.createQuery(hql).list();
		List<Map<String, String>>resultList = new ArrayList<>();
		for (Object[] objs : list) {
			Map<String,String>map = new HashMap<String, String>();
		//	map.put(objs[0].toString(), objs[1]==null?"0":objs[2].toString());
			map.put("type", "报废");
			map.put("key", objs[0].toString());
			map.put("value", objs[1]==null?"0":objs[1].toString());
			resultList.add(map);
		}
		return resultList;
	}

}
