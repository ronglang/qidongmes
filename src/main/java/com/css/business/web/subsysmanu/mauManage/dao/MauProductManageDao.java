package com.css.business.web.subsysmanu.mauManage.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysmanu.bean.MauProduct;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;

@Repository("mauProductManageDao")
public class MauProductManageDao extends BaseEntityDaoImpl<MauProduct> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * 产品列表
	 * @param p
	 * @param proName 条件：产品名称，搜索用
	 * @return
	 */
	public Page getProductListDao(Page p,String pro_name,String pro_type){
		String hql = " from MauProduct  where pro_name is not null ";
		if(pro_name != null && !"".equals(pro_name)){
			hql +=" AND pro_ggxh like '%"+pro_name+"%'";
		}
		if(pro_type !=null && !"".equals(pro_type)){
			hql +=" AND pro_type = '"+pro_type+"' ";
		}
		hql +=" order by pro_ggxh ";
		Page page = this.pageQuery(hql, p.getPageNo(), p.getPagesize());
		return page;
	}
	/**
	 * 保存产品
	 * @param product
	 * @return
	 */
	public boolean saveProductManageDao(MauProduct product){
		Session session = null;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(product);
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
	
	public boolean updateProductManageDao(Object obj){
		Session session = null;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) obj;
			MauProduct mpm = (MauProduct)session.get(MauProduct.class, Integer.parseInt(map.get("id").toString()));
			mpm.setPro_code(map.get("pro_code").toString());
			mpm.setPro_name(map.get("pro_name").toString());
			mpm.setPro_type(map.get("pro_type").toString());
			mpm.setPro_color(map.get("pro_color").toString());
			mpm.setPro_length(Integer.parseInt(map.get("pro_length").toString()));
			mpm.setUnit(map.get("unit").toString());
			mpm.setPacking_type(map.get("packing_type").toString());
			mpm.setPacking_mater(map.get("packing_mater").toString());
			mpm.setRoute_code(map.get("route_code").toString());
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
	}
	
	
	public MauProduct getByProductCode(String productCode){
		List<MauProduct> lst = this.findBy("pro_code", productCode);
		if(lst == null || lst.size() == 0) 
			return null;
		else
			return lst.get(0);
	}
	
	public MauProduct getByProductGgxh(String proGgxh){
		List<MauProduct> lst = this.findBy("pro_ggxh", proGgxh);
		if(lst == null || lst.size() == 0) 
			return null;
		else
			return lst.get(0);
	}
	
	
}
