package com.css.business.web.subsysplan.plaManage.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysmanu.mauManage.bean.MauMachineVO;
import com.css.business.web.subsysplan.bean.PlaProductOrderSeqHourse;
import com.css.business.web.sysManage.dao.SysConfigManageDAO;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("plaProductOrderSeqHourseManageDAO")
public class PlaProductOrderSeqHourseManageDAO  extends BaseEntityDaoImpl<PlaProductOrderSeqHourse>{
	
	@Resource(name="sysConfigManageDAO")
	private SysConfigManageDAO sysConfigManageDAO;

	/**
	 * @TODO:取本工序第一根线生产结束时间
	 *  取上一工序的第一根线的结束时间
	 *  
	 *  把本工序第一轴线的结束时间，+ 参数配置的流转时间，作为下一工序的开始生产时间点。
	 * @author: zhaichunlei
	 & @DATE : 2017年8月4日
	 * @param seqCode 工序编码
	 * @param productOrderCode  生产令编码
	 * @return
	 */
	public Timestamp calcThisSeqFirstAxisEndTime(String seqCode,Integer productOrderId){
		StringBuffer sb = new StringBuffer();
		sb.append("select end_date from pla_product_order_seq_hourse po where po.pla_order_id=? and seq_code=? ");
		sb.append(" order by start_date limit 1 ");
		Object o = this.createSQLQuery(sb.toString(),productOrderId,seqCode).uniqueResult();
		if(o == null )
			return null;
		else 
			return Timestamp.valueOf(o.toString());
	}
	
	/**
	 * @TODO: 根据机台，取机台当天最晚的结束时间
	 * @author: zhaichunlei
	 & @DATE : 2017年8月8日
	 * @param macCode
	 * @param workDay
	 * @return
	 */
	public Timestamp calcThisSeqLastAxisEndTime(String macCode,Integer workDay){
		StringBuffer sb = new StringBuffer();
		sb.append("select end_date from pla_product_order_seq_hourse po where po.mac_code=? and work_day=? ");
		sb.append(" order by end_date desc limit 1 ");
		Object o = this.createSQLQuery(sb.toString(),macCode,workDay).uniqueResult();
		if(o == null )
			return null;
		else 
			return Timestamp.valueOf(o.toString());
	}
	
	/**
	 * @TODO: 找到当天的空闲机台
	 *   使用MauTaskLoadDAO.fetchSeqIdleMachine 代替
	 * @author: zhaichunlei
	 & @DATE : 2017年8月4日
	 * @param seqCode
	 * @param thisSeqTime_begin
	 * @return
	 * @throws Exception 
	 */
	@Deprecated 
	public List<MauMachineVO>  fetchSeqIdleMachine(String seqCode,Integer workDay) throws Exception{
	    /*Timestamp d = new Timestamp(System.currentTimeMillis());
	    if(d.getTime() > thisSeqTime_begin.getTime()){
	    	thisSeqTime_begin = d;
	    }*/
		
		//怎么定义一个机台是空闲的？ 假如工时是24小时，目前已有20工时，？
		//--》》》》》》》》》》 当24-合计工时> 系统参数设置的值时，就视为空闲  
		String gs = sysConfigManageDAO.getValueByItem("一天工时数");
		if(gs == null || gs.trim().length() == 0)
			throw new Exception("请在SYSCONFIG配置【一天工时数】参数");
		
		String kxgs = sysConfigManageDAO.getValueByItem("排产机台允许空闲工时");
		if(kxgs == null || kxgs.trim().length() == 0)
			throw new Exception("请在SYSCONFIG配置【排产机台允许空闲工时】参数");
		
		Integer yxgs = Integer.parseInt(gs) - Integer.parseInt(kxgs); //有效工时
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date dday = sdf.parse(workDay.toString());
		sdf.applyPattern("yyyy-MM-dd");
		String dstr = sdf.format(dday);
		String dstart = dstr + " 08:00:00";
		
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		//查询当天的开始与结束时间
		Date date_start = sdf.parse(dstart);
		Date date_end   = new Date(date_start.getTime()+24 * 60 * 60 * 1000 );*/
		
		
		//当天，所有工单合计，已安排工时少于指定工时的机台
		StringBuffer sb = new StringBuffer();
		sb.append("select * from mau_machine m  where 1=1 and m.mac_code in (");
		sb.append("select p.mac_code from pla_product_order_seq_hourse p where 1=1 ");
		sb.append("and p.mac_code=m.mac_code ");
		sb.append("  and p.work_day= ? ");
		sb.append(" group by p.mac_code ");
		sb.append("having  sum(seq_hours) > "+yxgs+" "); // --工时合计不到指定工时
		sb.append(" and m.seq_code=? ");
		
		@SuppressWarnings("unchecked")
		List<MauMachineVO> lst = this.createSQLQuery(sb.toString(), workDay,seqCode).setResultTransformer(Transformers.aliasToBean(MauMachineVO.class)).list();
		return lst;
	}
	
	/**
	 * @TODO: 根据生产令id，查询拆分表列表
	 * @author: zhaichunlei
	 & @DATE : 2017年8月9日
	 * @param productOrderId
	 * @return
	 */
	public List<PlaProductOrderSeqHourse> getListByProductOrderId(Integer productOrderId){
		List<PlaProductOrderSeqHourse> lst = this.findBy("plaOrderId", productOrderId);
		return lst;
	}
	
	/**
	 * @TODO: 根据生产令ID，取本生产令的生产周期（各个工作日）
	 * @author: zhaichunlei
	 & @DATE : 2017年8月9日
	 * @param productOrderId
	 * @return
	 */
	public List<Integer> getWorkDay_productOrder(Integer productOrderId){

		//select DISTINCT work_day from pla_product_order_seq_hourse where pla_order_id=1
		String sql = "select DISTINCT work_day from pla_product_order_seq_hourse where pla_order_id=? ";
		@SuppressWarnings("unchecked")
		List<Integer> lst = this.createSQLQuery(sql, productOrderId).list();
		return lst;
	}
	
	/**
	 * @TODO: 根据生产令、工序，合计某天的总工时
	 * @author: zhaichunlei
	 & @DATE : 2017年8月9日
	 * @param proOrderId
	 * @param seqCode
	 * @param workDay
	 * @return
	 */
	public BigDecimal getSumSeqHoursBy_productOrderId_and_seqCode(Integer proOrderId,String seqCode,Integer workDay){
		//postgresql COALESCE,相当于oracle  nvl
		String sql = "select sum(COALESCE(seq_hours,0)) from pla_product_order_seq_hourse where pla_order_id=? and seq_code=? and work_day=?";
		Object o = this.createSQLQuery(sql, proOrderId,seqCode,workDay).uniqueResult();
		if(o == null)
			return null;
		return new BigDecimal(o.toString());
	}
	
	/**
	 * @TODO: 根据生产令，指定工作日的拆分 记录
	 * @author: zhaichunlei
	 & @DATE : 2017年8月9日
	 * @param productOrderId
	 * @param workDay
	 * @return
	 */
	public List<PlaProductOrderSeqHourse> getListByProductOrderIdAndWorkDay(Integer productOrderId,Integer workDay){
		String sql = "from PlaProductOrderSeqHourse where plaOrderId=? and workDay=? order by seqCode";
		@SuppressWarnings("unchecked")
		List<PlaProductOrderSeqHourse> lst = this.createQuery(sql, productOrderId,workDay).list();
		return lst;
	}
	
}
