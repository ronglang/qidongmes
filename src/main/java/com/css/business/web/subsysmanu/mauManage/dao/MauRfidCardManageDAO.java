package com.css.business.web.subsysmanu.mauManage.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysmanu.bean.MauRfid;
import com.css.business.web.subsysmanu.mauManage.bean.OptionVo;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;



@Repository("mauRfidCardManageDAO")
public class MauRfidCardManageDAO extends BaseEntityDaoImpl<MauRfid>  {
	//查询所有的卡号
	public Page queryAllCard(Page p, StringBuffer sql) {
		Page page = this.pageQuery(sql.toString(), p.getPageNo(), p.getPagesize());
		return page;
	}
	
	
	
	//根据id删除卡号
	public void deleteRfidById(StringBuilder sql, String id) {
		Query createQuery = this.createQuery(sql.toString());
		createQuery.executeUpdate();
		
	}
	//修改rfid卡的状态
	public void updateRfidById(String id) {
		String hql="update from MauRfid r set r.status='未用' where r.id='"+id+"' ";
		Query query = this.createQuery(hql);
		query.executeUpdate();
		
	}
	public void saveRfidCardNo(Integer rf,String name,String type) {
		//前台输入次数控制 数据库生成多少条卡号
		if(null!=rf&&rf!=0&&null!=name&&!"".equals(name)){
			for (int i = 0; i <rf; i++) {
				 String hql="INSERT into mau_rfid(id,rfid_no,status,create_date,create_by,app_id,material_type,amount) "
				 		+ " values (nextval('seq_mau_rfid'),fun_get_wf_psdm(?),'未用',now(),'"+name+"',?,?,?)";	
				 Query query = this.createSQLQuery(hql, type,null,type,0);
				/* Query query = this.createSQLQuery(hql);*/
				 query.executeUpdate();
				 
				}
		}
		
		 
		     }
	
	//网页端获取一个未使用的RFID号
	public String getNewRfidCode( String status) {
		String rfidCode="";
		StringBuilder sql = new StringBuilder("select o from MauRfid o where o.status=? and o.materialType=?  order by o.id  ");
		//List<MauRfid> list = this.findBy(sql,null);
		List<MauRfid> list = this.createQuery(sql.toString(),"未用",status).list();
		if(list.size()>0){
			MauRfid mauRfid =list.get(0);
			rfidCode=mauRfid.getRfidNO();
		}else{
			rfidCode="RFID号已经不够请联系管理员生成新的RFID号";
		}
		return rfidCode;
	}
	//根据RFID编号修改其状态
	public String updateStatusByRfidCode(String status, String rfidCode) {
		String msg="";
		try {
			String sql ="update MauRfid set status=? where rfidNO=? ";
			Query query = this.createQuery(sql,status,rfidCode);
			query.executeUpdate();
			msg="修改成功";
		} catch (Exception e) {
			// TODO: handle exception
			msg="修改失败"+e.toString();
		}
	
		return msg;
		
	}
	//获取RFID的状态返回到前台形成下拉框
	public List<String> getSelectOption(String hql) {
		List<String> list =new ArrayList<>();
		try {
			list=this.createQuery(hql,"RFID卡类型").list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	/**
	 * 
	 *@data:2017年7月11日
	@param materialType
	@return
	@autor:wl
	 */
	public String getMessageReturnRfidByApp(String materialType) {
		String rfidCode="";
		StringBuilder sql = new StringBuilder("select rfidNO from  MauRfid s  where s.status=? and s.materialType=? order by id");
		List list = this.createQuery(sql.toString(), "未用",materialType).list();
		if(null!=list&&list.size()>0){
			rfidCode=(String)list.get(0);
		}
		return rfidCode;
	}
	/**
	 * 修改RFID状态
	 *@data:2017年7月12日
	@param rfidCode
	@param materialType
	@param amount
	@return
	@autor:wl
	 */
	public String updateStatus(String rfidCode,String materialType, Integer amount,String sixRfid) {
		String msg="";
		try {
			String sql ="update MauRfid set materialType=?,amount=?,sixRfidNo=? where rfidNO=? ";
			Query query = this.createQuery(sql,materialType,amount,sixRfid,rfidCode);
			query.executeUpdate();
			msg="修改成功";
		} catch (Exception e) {
			// TODO: handle exception
			msg="修改失败"+e.toString();
		}
		return msg;
	}
	/**
	 * 
	 *@data:2017年7月12日
	@param rfidCode
	@return
	@autor:wl
	 */
	public List<MauRfid> getObjectByRfidCode(String rfidCode) {
		StringBuilder hql = new StringBuilder("select o from  MauRfid o where o.rfidNO=?");
		 @SuppressWarnings("unchecked")
		List<MauRfid> list = this.createQuery(hql.toString(),rfidCode).list();
		 
		return list;
	}
	/**
	 * 保存手持机的机台RFID号
	 *@data:2017年7月19日
	@param rfidCode
	@param appId
	@autor:wl
	 */
	public void saveRfid(String rfidCode, String appId) {
		try {
			String sql="INSERT into mau_rfid(id,rfid_no,status,create_date,create_by,app_id,material_type,amount) "
				 		+ " values (nextval('seq_mau_rfid'),?,?,now(),'admin',?,?,?)";
			Query query = this.createSQLQuery(sql,rfidCode,"在用",appId,"机台卡",1);
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public List<OptionVo> getOptionSelectOption(String sql) {
		 List<OptionVo> list=null;
		 try {
			 list=this.createQuery(sql, "RFID卡类型").list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return list;
	}
		
	

}
