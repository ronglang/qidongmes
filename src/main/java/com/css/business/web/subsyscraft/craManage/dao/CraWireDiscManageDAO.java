package com.css.business.web.subsyscraft.craManage.dao;

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

import com.css.business.web.subsyscraft.bean.CraWireDisc;
import com.css.business.web.subsysstore.bean.StoreOutOfStorageRecord;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;

@Repository("craWireDiscManageDAO")
public class CraWireDiscManageDAO extends BaseEntityDaoImpl<CraWireDisc>  {
	@Autowired
	private SessionFactory  sessionFactory;
	/**
	 * 根据RFID号修改线盘的使用状态未空闲
	 *@data:2017年7月25日
	@param rfid
	@autor:wl
	 */
	public void updateUsedStatusByRfid(String rfid,String msg) {
		try {
			String sql="update CraWireDisc set useStatus=? where rfidNumber=? and status=?";
			Query query = this.createQuery(sql,msg,rfid,"正常");
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}

	

	public Page getRecordList(StringBuilder sql,Page param) {
		Page page =null;
		try {
			page =this.pageQuery(sql.toString(), param.getPageNo(), param.getPagesize());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return page;
	}
/**
 * 获取下拉框的值
 *@data:2017年7月25日
@param hql
@param obj
@return
@autor:wl
 */
	public List<String> getSelectOption(String hql, String obj) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		try {
			 list = this.createQuery(hql, obj).list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

public void updateById(String hql, String rfidNumber, String wireDiscPgxh,
		String status, String useStatus, Double externalDiameter,
		Double boreDiameter,String materialTexture,Double capacity, Integer id) {
	
	try {
		Query query = this.createQuery(hql, rfidNumber,wireDiscPgxh,status,useStatus,externalDiameter,boreDiameter,materialTexture,capacity,id);
		query.executeUpdate();
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	// TODO Auto-generated method stub
	
}

/**
 * 查询这种规格中所有处于空闲  状态为正常的线盘
 *@data:2017年7月26日
@param wirediscPgxh
@return
@autor:wl
 */

public List<CraWireDisc> findCraWireDiscList(String wirediscPgxh) {
	// TODO Auto-generated method stub
	List<CraWireDisc> list =new ArrayList<>();
	try {
		String hql ="from CraWireDisc where wireDiscPgxh=?  and status=?  and useStatus=?";
		list=this.createQuery(hql, wirediscPgxh,"正常","空闲").list();
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return list;
}



public Integer getWireAmountBy(String wireDiscPgxh) {
	Integer num=0;
	try {
		String hql ="count(id) from CraWireDisc where wireDiscPgxh=?  and and status=?  and useStatus=?";
		List list = this.createQuery(hql, wireDiscPgxh,"正常","空闲").list();
		
		if(null!=list&&list.size()>0){
			num=(Integer)list.get(0);
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	// TODO Auto-generated method stub
	return num;
}



public boolean saveProductManageDao(CraWireDisc mpm) {
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



public boolean updateProductManageDao(Object obj) {
	Session session = null;
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	try{
		session = sessionFactory.openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) obj;
		CraWireDisc mpm = (CraWireDisc)session.get(CraWireDisc.class, Integer.parseInt(map.get("id").toString()));
		mpm.setRfidNumber(map.get("rfidNumber").toString());
		mpm.setWireDiscPgxh(map.get("wireDiscPgxh").toString());
		mpm.setStatus(map.get("status").toString());
		mpm.setUseStatus(map.get("useStatus").toString());
		mpm.setExternalDiameter(Double.parseDouble((null==map.get("externalDiameter").toString()&&"".equals(map.get("externalDiameter").toString()))?"0":map.get("externalDiameter").toString()));
		mpm.setBoreDiameter(Double.parseDouble((null==map.get("boreDiameter").toString()&&"".equals(map.get("boreDiameter").toString()))?"0":map.get("boreDiameter").toString()));
		mpm.setCapacity(0D);
		mpm.setCreateDate(new Date());
		mpm.setCreateBy("WL");
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



public CraWireDisc getCraWireDiscById(String hql, Integer id) {
	// TODO Auto-generated method stub
	CraWireDisc craWireDisc=null;
	try {
		List<CraWireDisc> list = this.createQuery(hql, id).list();
		if(null!=list&&list.size()>0){
			craWireDisc=list.get(0);
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return craWireDisc;
}

}
