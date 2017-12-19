package com.css.business.web.subsysplan.plaManage.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysplan.bean.PlaMachinePlanSchedule;
import com.css.business.web.subsysplan.plaManage.bean.PlaRequestPurchaseDetailedVo;
import com.css.business.web.subsysplan.plaManage.utils.YorkUtil;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;

@Repository("plaMachinePlanScheduleManageDAO")
public class PlaMachinePlanScheduleManageDAO extends BaseEntityDaoImpl<PlaMachinePlanSchedule>  {
	
	public Page queryPlaMachinePlanScheduleDao(Page p,String sql) throws Exception{
		Page page = this.pageSQLQueryVONoneDesc(sql, p.getPageNo(), p.getPagesize(), new PlaRequestPurchaseDetailedVo() );
		return page;
	}
	
	/**
	 * 查询工序List 方便注入page中
	 * @param sql
	 * @param startIndex
	 * @param totalCount
	 * @param pageNo
	 * @param pageSize
	 * @param vo
	 * @param values
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List querySQLPageList(String sql,int startIndex,long totalCount,int pageNo, int pageSize,PlaRequestPurchaseDetailedVo vo,Object... values) {
		Query query = createSQLQuery(sql,values);
		List li = query.setFirstResult(startIndex).setMaxResults(pageSize).setResultTransformer(Transformers.aliasToBean(vo.getClass())).list();
		return li;
	}
	
	/**
	 * 查询轴号
	 * @param axis_name
	 * @return
	 */
	public List<?> queryIncomingAxis(String axis_name){
		final String hql = " SELECT p.incomingAxis FROM PlaMachinePlanSchedule p WHERE p.axisName = '"+axis_name+"' ";
		List<?> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
	public List<PlaRequestPurchaseDetailedVo> queryPlaScheduleParam(final String sql){
		List<PlaRequestPurchaseDetailedVo> list = getHibernateTemplate().execute(new HibernateCallback<List<PlaRequestPurchaseDetailedVo>>() {

			@Override
			public List<PlaRequestPurchaseDetailedVo> doInHibernate(Session session) throws HibernateException,
					SQLException {
				@SuppressWarnings("unchecked")
				List<PlaRequestPurchaseDetailedVo> li = session.createSQLQuery(sql).list();
				li = YorkUtil.parse(li,PlaRequestPurchaseDetailedVo.class, sql);
				return li;
			}
		});
		return list;
	}
	
	/**
	 * @TODO: 根据机台计划ID取得机台计划进度  计划与进度是一一对应的
	 * @author: zhaichunlei
	 & @DATE : 2017年7月20日
	 * @param machinePlanId
	 * @return
	 */
	public PlaMachinePlanSchedule getByMachinePlanId(Integer machinePlanId){
		List<PlaMachinePlanSchedule> lst = this.findBy("machinePlanId", machinePlanId);
		
		if( lst == null || lst.size() == 0) return null;
		
		return lst.get(0);
	}
	
	/**
	 * @TODO: 根据机台计划ID，取来料的虚拟轴号
	 * 本查询只适合，从拉丝到最后一个工序，都是按照生产令计划来的。
	 * 不适合：非完整计划
	 * @author: zhaichunlei
	 & @DATE : 2017年7月20日
	 * @param machinePlanId
	 * @return
	 */
	public String getVirtualRfid_income_by_machinePlanId(Integer machinePlanId){
		StringBuffer sb = new StringBuffer();
		sb.append("select s.virtual_rfid from pla_machine_plan_schedule s where 1=1 ");
		sb.append("and exists(select 1 from pla_machine_plan_schedule p where s.axis_name=p.axis_name ");
		sb.append("and  p.incoming_axis like '%'||s.seq_semi_product_axis ||'%' and p.machine_plan_id=? )");
				
		Object o = this.createSQLQuery(sb.toString(), machinePlanId).uniqueResult();
		return o == null ? null : o.toString();
	}

	/**   
	 * @Description: 查询计划生产和实际进度   
	 * @param courseCode 工单编号
	 * @param seqCode 工序编号
	 * @return         
	 */ 
	public List<Object[]> getPlanAndFactPro(String courseCode, String seqCode) {
		// TODO Auto-generated method stub
		String sql = "SELECT pm.part_len,pms.semi_product_len FROM pla_machine_plan AS pm left join pla_machine_plan_schedule AS pms on pm.id = pms.machine_plan_id where  pm.seq_code = '"+seqCode+"' AND pm.course_code = '"+courseCode+"'";
		List<Object[]> list = this.createSQLQuery(sql).list();
		return list;
	}
}
