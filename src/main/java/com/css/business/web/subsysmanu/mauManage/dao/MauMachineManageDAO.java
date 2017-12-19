package com.css.business.web.subsysmanu.mauManage.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysmanu.bean.MauMachine;
import com.css.business.web.subsysmanu.vo.MachineCraProGgxhSpeedVO;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("mauMachineManageDAO")
public class MauMachineManageDAO extends BaseEntityDaoImpl<MauMachine>  {
	/** 
	 * @method: 获取某一张表中分页所需记录和所有的记录统计,
	 * @param:true返回所有的记录，false返回符合分页查询的记录
	 * @param:page 当前页号码
	 * @param:pagesize 每页显示多少条数据
	 * @values：需要传入到hql语句中的参数
	 * author:曾斌
	 * time:2017-06-12
	 */
	public List<?> getPagingQueryToolDao(final String hql,final boolean b,final int page,final int pagesize, final Object...values) {
		return getHibernateTemplate().execute(new HibernateCallback<List<?>>() {
			@Override
			public List<?> doInHibernate(Session session)throws HibernateException, SQLException {
				Query q = session.createQuery(hql);
				for (int i = 0; i < values.length; i++) {
					q.setParameter(i, values[i]);
				}
				if(true==b){
					return q.list();
				}else{
					q.setMaxResults(pagesize);
					q.setFirstResult(page*pagesize-pagesize);
					List<?> list = q.list();
					return list;
				}
				
			}
			
		});
	}
	/** 
	 * @method: 不需要分页查询某张表中的多条数据
	 * @author:曾斌
	 * @date:2017-06-13
	 */
	public List<?> getListDao(String hql, Object...values){
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			q.setParameter(i, values[i]);
		}
		List<?> list = q.list();
		return list;
	}
	/** 
	 * @method: 针对多表的根据id删除操作
	 * @param:hql执行删除的hql语句
	 * @param: ids 存放id的数组
	 * @author:曾斌
	 * @date:2017-06-12
	 */
	public void deleteByIdsDao(int[] ids, String hql) {
		Session session = this.getSession();
		for (int i = 0; i < ids.length; i++) {
			Query q = session.createQuery(hql);
			q.setParameter(0, ids[i]);
			q.executeUpdate();
		}
	}
	/** 
	 * @method: 跟新一个表中的某一条记录
	 * @author: 曾斌
	 * @date:   2017-06-12
	 */
	public void updateMauMachineDao(MauMachine mauMachine) {
		this.getSession().update(mauMachine);
	}
	/** 
	 * @method: 新增MauMachine表中的一条记录
	 * @author:曾斌
	 * @date:2017-06-12
	 */
	public void addMauMachineDao(MauMachine mauMachine) {
		this.getSession().save(mauMachine);
	}
	
	/**
	 * @TODO: 根据机台编码取记录
	 * @author: zhaichunlei
	 & @DATE : 2017年7月19日
	 * @param macCode
	 * @return
	 */
	public MauMachine getByMacCode(String macCode){
		List<MauMachine> lst = this.findBy("macCode", macCode);
		if(lst != null && lst.size() > 0)
			return lst.get(0);
		else
			return null;
	}
	
	/**
	 * @TODO: 根据机台代号取记录
	 * @author: zhaichunlei
	 & @DATE : 2017年7月19日
	 * @param macCode
	 * @return
	 */
	public MauMachine getByMacMark(String macMark){
		List<MauMachine> lst = this.findBy("macMark", macMark);
		if(lst != null && lst.size() > 0)
			return lst.get(0);
		else
			return null;
	}
	
	/**
	 * @TODO: 根据机台与规格型号，取体符合规格的机台
	 * @author: zhaichunlei
	 & @DATE : 2017年8月3日
	 * @param seq
	 * @param ggxh
	 * @return
	 */
	public List<MauMachine> getMachineBySeqAndGgxh(String seqCode,String ggxh){
		String sql = "from MauMachine m where seqCode=? and exists(select 1 from MauMachineSpeed s where m.id=s.machineId and s.proGgxh=? )";
		@SuppressWarnings("unchecked")
		List<MauMachine> lst =  this.createQuery(sql, seqCode,ggxh).list();
		return lst;
	}
	
	/**
	 * @todo:  系统上线时，存在机台与产品部分参数不能及时录入导致系统运行报错。  这里暂使用旧数据初始化数据：
	 *    机台生产速度 
	 * @author : zhaichunlei
	 * @date: 2017年9月7日
	 */
	public List<MachineCraProGgxhSpeedVO> queryMachineSpeed_notExists(){
		StringBuffer sb = new StringBuffer();
		sb.append("select m.id, p.pro_ggxh proggxh from mau_machine m,mau_product p ");
		sb.append("where p.pro_type='成品'  ");
		sb.append("and not exists(select 1 from mau_machine_speed s where s.machine_id=m.id and s.pro_ggxh=p.pro_ggxh) ");
		
		@SuppressWarnings("unchecked")
		List<MachineCraProGgxhSpeedVO> lst = this.createSQLQuery(sb.toString()).setResultTransformer(Transformers.aliasToBean(MachineCraProGgxhSpeedVO.class)).list();
		return lst;
	}
}
