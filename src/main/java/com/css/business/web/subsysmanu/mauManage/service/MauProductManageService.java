package com.css.business.web.subsysmanu.mauManage.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.subsyscraft.bean.CraSeq;
import com.css.business.web.subsysmanu.bean.MauProduct;
import com.css.business.web.subsysmanu.mauManage.dao.MauProductManageDao;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.common.web.syscommon.bean.TreeVO;
import com.css.common.web.syscommon.controller.support.QueryCondition;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauProductManageService")
public class MauProductManageService extends BaseEntityManageImpl<MauProduct, MauProductManageDao> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Resource(name = "mauProductManageDao")
	private MauProductManageDao dao;
	
	@Override
	public MauProductManageDao getEntityDaoInf() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	public Page getProductListDao(Page p,String pro_name,String pro_type){
		Page page = dao.getProductListDao(p, pro_name,pro_type);
		return page;
	}
	
	public boolean saveProductManageService(MauProduct product){
		return dao.saveProductManageDao(product);
	}
	
	public boolean updateProductManageService(Object obj){
		return dao.updateProductManageDao(obj);
	}
	
	public void deleteProductManageService(Integer id){
		String sql = " delete from mau_product where id = ? ";
		dao.deleteBySql(sql, id);
	}

	/**
	 * @TODO: 产品树
	 * 产品编码作value, 
	 * @author: zhaichunlei
	 & @DATE : 2017年7月31日
	 * @param user
	 * @param code
	 * @param dvalue
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<TreeVO> nodeTree(SysUser user, String code, Object dvalue) throws Exception {
		QueryCondition qc = new QueryCondition(MauProduct.class);
		if (!(code.isEmpty())) {
			qc.addCondition(code, "like", "%"+dvalue + "%");
		}
		List<MauProduct> list = this.query(qc);
		List<TreeVO> tree = new ArrayList<TreeVO>();
		for (MauProduct s : list) {
			TreeVO vo = new TreeVO(s.getPro_code(), s.getPro_name().toString()/*+"-"+s.getPro_color()*/,
					s.getPro_type() == null ? "-1" : s.getPro_type().toString());
			tree.add(vo);
		}
		return tree;
	}

	/**   
	 * @Description: 保存初始的产品类型   
	 * @param typeName         
	 */ 
	public String saveProType(String p_type,String typeName,String account) {
		// TODO Auto-generated method stub
		/*String sql = "select max(id) from Mau_Product where pro_code is null";
		@SuppressWarnings("unchecked")
		List<Object> list = dao.createSQLQuery(sql).list();
		Integer id = null;
		if (list.get(0) != null && list.size() > 0) {
			id = (Integer) list.get(0) + 1;
		}else {
			id = 2;
		}
		mauProduct.setId(id);*/
		String sql = "select  DISTINCT pro_type from MAU_PRODUCT where pro_code is null";
		@SuppressWarnings("unchecked")
		List<String> list = dao.createSQLQuery(sql).list();
		if (list.contains(typeName)) {
			return "该种类已存在";
		}
		try{
			MauProduct mauProduct = new MauProduct();
			mauProduct.setPro_type(typeName);
			mauProduct.setP_code("0");
			mauProduct.setCreateBy(account);
			mauProduct.setCreateDate(new Date());
			dao.save(mauProduct);
			return "添加成功";
		}catch(Exception e){
			e.printStackTrace();
			return "添加失败";
		}
		
		
		
	}

	/**   
	 * @Description: 查询所有产品类型1
	 * @return         
	 */ 
	public Map[] queryProTypes() {
		// TODO Auto-generated method stub
		String sql = "select  DISTINCT pro_type from MAU_PRODUCT ";
		@SuppressWarnings("unchecked")
		List<Object> list = dao.createSQLQuery(sql).list();
		List<String> list2 = new ArrayList<String>();
	    list2.add(null);
	    list.removeAll(list2);
		Map[] resultMap = new Map[list.size()];
		for (int i=0;i< list.size();i++) {
			Map<String,String>map = new HashMap<String, String>();
			//map.put("id", list.get(i)[0].toString());
			map.put("text", list.get(i).toString());
			resultMap[i] = map;
		}
		if (resultMap != null && resultMap.length > 0) {
			return resultMap;
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map[] getProGgxh(){
		String hql = " SELECT DISTINCT pro_ggxh from MauProduct where pro_type = '成品' ";
		List<Object> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map<String,String> map = new HashMap<String,String>(); 
			for(int j = 0;j<2;j++){
				if(j==0){
					map.put("text", list.get(i).toString());
				}else{
					map.put("id", i+"");
				}
			}
			str[i] = map;
		}
		return str;
	}
	
	
	/**   
	 * @Description: 查询所有产品类型2
	 * @return      TG
	 */ 
	public Page queryAllProTypes(Page p){
		String hql = "from MauProduct where pro_code is null";
		Page page = dao.pageQuery(hql, p.getPageNo(), p.getPagesize());
		return page;
	}

	/**   
	 * @Description: 获得种类父id  
	 * @param proType
	 * @return         
	 */ 
	public String queryIdByPcode(String proType) {
		// TODO Auto-generated method stub
		String sql = "select id from MAU_PRODUCT where pro_type='"+ proType+"' and pro_code is null";
		List list = dao.createSQLQuery(sql).list();
		if (list != null && list.size() > 0  ) {
			return list.get(0).toString();
		}
		return null;
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @return         
	 */ 
	public Integer queryMaxId() {
		// TODO Auto-generated method stub
		String sql =  "select MAX(id) from MAU_PRODUCT";
		@SuppressWarnings("unchecked")
		List<Object[]> list = dao.createSQLQuery(sql).list();
		Integer maxId = (Integer) list.get(0)[0];
		return maxId;
	}
	
	/**
	 * 修改产品类型,beforValue修改前的产品类型
	 * @param map
	 * @return
	 */
	public Map<String,String> modifyProTypeService(List<MauProduct> list,String beforValue){
//		String sql = "select * from mau_product where id =:id and pro_code is not null";
//		@SuppressWarnings("unchecked")
//		List<String> list = dao.createSQLQuery(sql).setParameter("id", map.get("id")).list();
		Map<String,String> maps = new HashMap<>();
		
		String sql = "select id from mau_product where pro_type='"+ beforValue+"' and pro_code is not null";
		@SuppressWarnings("unchecked")
		List<MauProduct> listPro = dao.createSQLQuery(sql).list();
		if(listPro != null && listPro.toString()!="[]"){
			maps.put("error", "此类型正在被使用，禁止修改！");
		}else{
			
			MauProduct mauProduct = (MauProduct)dao.get(MauProduct.class, list.get(0).getId());
			mauProduct.setPro_type(list.get(0).getPro_type());
			
			Session session = null;
			try {
				session = sessionFactory.openSession();
				session.beginTransaction();
				session.update(mauProduct);
				session.getTransaction().commit();
				maps.put("success", "修改成功");
			} catch (Exception e) {
				session.getTransaction().rollback();
				maps.put("error", "修改失败");
				e.printStackTrace();
			}
			
		}
		
		return maps;
	}
	
	/**
	 * 删除产品类型
	 * @param id
	 * @return
	 */
	public Map<String,String> clearProTypeService(List<MauProduct> list){
		Map<String,String> maps = new HashMap<>();
		String sql = "select id from mau_product where pro_type='"+ list.get(0).getPro_type()+"' and pro_code is not null";
		@SuppressWarnings("unchecked")
		List<MauProduct> listPro = dao.createSQLQuery(sql).list();
		
		if(listPro != null && listPro.toString()!="[]"){
			maps.put("error", "此类型正在被使用，禁止删除！");
		}else{
			Session session = null;
			session = sessionFactory.openSession();
			MauProduct mauProduct = (MauProduct)session.get(MauProduct.class, list.get(0).getId());
			
			try {
				
				session.beginTransaction();
				session.delete(mauProduct);
				session.getTransaction().commit();
				maps.put("success", "删除成功");
			} catch (Exception e) {
				session.getTransaction().rollback();
				maps.put("error", "删除失败");
				e.printStackTrace();
			}
		}
		
		return maps;
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param procode
	 * @return         
	 */ 
	public Map<String, String> checkProCode(String procode) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from Mau_Product where pro_code = '"+procode+"' ";
		BigInteger count = (BigInteger) dao.createSQLQuery(sql).uniqueResult();
		int compareTo = count.compareTo(new BigInteger("0"));
		Map<String, String>map = new HashMap<>();
		if (compareTo > 0 ) {
			map.put("msg", "已有该编码,请查证后重新填写");
			map.put("flag", "false");
		} else if (compareTo == 0) {
			map.put("msg", "无该编码,请放心使用");
			map.put("flag", "true");
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public Page gerProGgxh(Page p){
		String hql  = " from MauProduct ";
		List<MauProduct> list = dao.createQuery(hql).list();
		return null;
	}
	
	public MauProduct getByProductGgxh(String proGgxh){
		return dao.getByProductGgxh(proGgxh);
	}

	public List<String> getAllProGgxh() {
		String hql = "select pro_ggxh from MauProduct where pro_ggxh != null group by pro_ggxh";
		List<String> list = dao.createQuery(hql).list();
		return list;
	}
	
	public Page getSelectProGgxh(Page p){
		String hql = " from MauProduct where pro_type = '成品' ";
		Page page = dao.pageQuery(hql, p.getPageNo(), p.getPagesize());
		return page;
	}
	
}
