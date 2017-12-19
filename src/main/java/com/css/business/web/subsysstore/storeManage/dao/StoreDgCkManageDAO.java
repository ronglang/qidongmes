package com.css.business.web.subsysstore.storeManage.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysmanu.bean.MauMaterialRecord;
import com.css.business.web.subsysstore.bean.StoreDgCkDetail;
import com.css.business.web.subsysstore.bean.StoreDgCk;
import com.css.business.web.subsysstore.bean.StoreMrecord;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;

@Repository("storeDgCkManageDAO")
public class StoreDgCkManageDAO extends BaseEntityDaoImpl<StoreDgCk>  {
	
	@Autowired
	private SessionFactory sessionFactory;


	/**
	 * 查询数据的总条数
	 * */
	public long getRecordTotalCount(StringBuilder hql) {
		long count =0L;
		try {
			List find = this.getHibernateTemplate().find(hql.toString());
			if(find.size()>0){
				 count = (long)find.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * 根据条件查询所有数据
	 * */
	@SuppressWarnings("unchecked")
	public Page getRecordList(StringBuilder sql,Page param) {
		Page page=null;
		try {
			
			page = this.pageQuery(sql.toString(), param.getPageNo(), param.getPagesize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	

	public List<String> getSelectOption(String hql) {
		List<String> list =new ArrayList<>();
		try {
			list=this.createQuery(hql,"出库单状态").list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}
	/**
	 * 回显数据
	 *@data:2017年7月23日
	@param hql
	@param id
	@return
	@autor:wl
	 */
	public StoreDgCk getObjectById(String hql, Integer id) {
		StoreDgCk obj=null;
		try {
			List<StoreDgCk> list = this.createQuery(hql, id).list();
			if(null!=list&&list.size()>0){
				obj=list.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return obj;
	}
	public boolean updateProductManageDao(Object obj) {
		
		Session session = null;
		StoreMrecord storeMrecord=null;
		MauMaterialRecord mauMaterialRecord=null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) obj;
			String  mmrcode =(String) map.get("mmrcode");
			String  matername =(String) map.get("matername");
	
			StoreDgCkDetail mpm = (StoreDgCkDetail)session.get(StoreDgCkDetail.class, Integer.parseInt(map.get("id").toString()));
			String hql1=" from StoreMrecord where materName=?";
			List<StoreMrecord> list = this.createQuery(hql1, matername).list();
			if(null!=list&&list.size()>0){
				storeMrecord=list.get(0);
				Integer id = storeMrecord.getId();
				mpm.setMaterialId(id);
			}
			String hql2="from MauMaterialRecord where mmrCode=?";
			List<MauMaterialRecord> list2 = this.createQuery(hql2, mmrcode).list();
			if(null!=list2&&list2.size()>0){
				mauMaterialRecord=list2.get(0);
				Integer id = mauMaterialRecord.getId();
				mpm.setPickDgId(id);
			}
			mpm.setOutboundOrderCode(map.get("ordercode").toString());
			mpm.setObjGgxh(map.get("ggxh").toString());
			mpm.setAmount(new BigDecimal(map.get("amount").toString()));
			mpm.setFactAmount(new BigDecimal(map.get("factamount").toString()));
			mpm.setUnit(map.get("unit").toString());
			mpm.setCreateDate(new Date());
			
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
	
		
	}
	public List<StoreDgCk> getRecordListByobjGgxh(String objGgxh,
			String pickListCode) {
		List<StoreDgCk> list=new ArrayList<>();
		try {
			String hql="from StoreDgCk where pickListCode=? and objGgxh=? and status=?";
			list =this.createQuery(hql,pickListCode, objGgxh,"待出库").list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return list;
	}
	
	
	public List<String> getPgxhByPickListCode(String hql,String pickListCode) {
		// TODO Auto-generated method stub
		List<String> list =null;
		try {
			list= this.createQuery(hql, pickListCode,"待出库").list();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}
	public List<String> getPickListCode(String hql) {
		List<String> list =null;
		try {
			list= this.createQuery(hql,"待出库").list();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}
	public void updateStatusById(String hql, Integer idName) {
		// TODO Auto-generated method stub
		try {
			Query query = this.createQuery(hql,"已出库",idName);
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<StoreDgCk> getOutCodeById(String hql, Integer id) {
		// TODO Auto-generated method stub
		List<StoreDgCk> list=null;
		try {
			list=this.createQuery(hql, id).list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

}
