package com.css.business.web.subsysplan.plaManage.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysplan.bean.PlaProductOrder;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("plaProductOrderManageDAO")
public class PlaProductOrderManageDAO extends BaseEntityDaoImpl<PlaProductOrder>  {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * @TODO: 根据工作编号查询所属生产令未完成的工单数量。大于0则说明生产令未完成
	 * @author: zhaichunlei
	 & @DATE : 2017年7月20日
	 * @param courseCode
	 * @return
	 */
	public int getMachinePlanByCoureCode_not_complete(String courseCode){
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from pla_course c where c.is_finish='否' and ");
		sb.append("c.product_order_code in (select product_order_code from pla_course p where p.ws_code=? )");

	
		int num = Integer.parseInt(this.createSQLQuery(sb.toString(),courseCode).uniqueResult().toString());
		return num;
	}
	public void saveBatch(final List<PlaProductOrder> list){
		getHibernateTemplate().execute(new HibernateCallback<String>() {
			@Override
			public String doInHibernate(Session session)
					throws HibernateException, SQLException {
				int count = 0;
				for(PlaProductOrder bean : list){
					session.save(bean);
					count++;
					if(count%50==0){
						session.flush();
					}
				}
				return null;
			}
		});
	}
	/**
	 * @TODO: 根据工单编号，更新所属生产令已完成
	 * @author: zhaichunlei
	 & @DATE : 2017年7月20日
	 */
	public void updateProductOrderFlag_toFinish_By_courseCode(String courseCode){
		String sql = "update pla_course set is_flag='已生产' where product_order_code in (select product_order_code from pla_course p where p.ws_code=? )";
		this.deleteBySql(sql, courseCode);
	}
	/**
	 * 返回生产令编号
	 * @return
	 */
	public String plaProductOrderCode(){
		String sql = "  select fun_get_pla_product_order() ";
		Map<String,Object> map = jdbcTemplate.queryForMap(sql);
		System.out.println("生产令编号"+map.get("fun_get_pla_product_order").toString());
		return map.get("fun_get_pla_product_order").toString();
	}
	
	/**
	 * @TODO: 根据编码查实体
	 * @author: zhaichunlei
	 & @DATE : 2017年8月2日
	 * @param productOrderCode
	 * @return
	 */
	public PlaProductOrder getPlaProductOrderByCode(String productOrderCode){
		List<PlaProductOrder> lst  = this.findBy("productOrderCode",productOrderCode);
		if(lst == null || lst.size() == 0 )
			return null;
		return lst.get(0);
	}
}
