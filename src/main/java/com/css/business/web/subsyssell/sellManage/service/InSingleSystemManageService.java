package com.css.business.web.subsyssell.sellManage.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.vo.InSingleSystemVo;
import com.css.business.web.subsyssell.bean.SellContractDetail;
import com.css.business.web.subsyssell.sellManage.dao.SellContractDetailManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
@Service("inSingleSystemManageService")
public class InSingleSystemManageService extends
		BaseEntityManageImpl<SellContractDetail, SellContractDetailManageDAO> {
	
	/**查询在单系统已有生产令，状态为2，并且周计划状态为2的未完成记录*/
	public static final String SQL = "select (product_part_len*amount) as 生产数量 , sc_code as 合同编号, orderid as 生产令id, product_order_code as 生产令编号,work_start_date as 计划开始日期 ,work_start_date as 计划完成日期,drawbench_time as 拉丝总工时,strand_time as 绞线总工时,insulation_time as 绝缘总工时,armored_time as 铠装总工时,cable_time as 成缆总工时,sheath_time as 护套总工时,delive_date as 交货日期  from  (select o.is_flag as orderFlag ,  o.id as orderID,o.*,s.* from pla_product_order o,sell_contract s where o.contract_id = s.id) temp1,pla_week_plan plan where temp1.orderID = plan.product_order_id  and orderFlag = ? and plan.is_flag = ?  ";  
	
	public static final String ORDER_FLAG = "2";//生产令预排产
	
	public static final String PLAN = "2";//计划预排产
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SellContractDetailManageDAO sellContractDetailManageDAO;

	@Override
	public SellContractDetailManageDAO getEntityDaoInf() {
		return sellContractDetailManageDAO;
	}

	/**
	 * 封装数据，在单系统未生产数据
	 * 
	 * @param request
	 * @param page
	 * @throws Exception 
	 */
	public void setData(HttpServletRequest request, Page page) throws Exception {
		List<Object> list = new ArrayList<Object>();
		//proState=2&startTime=2017-07-20&endTime=2017-07-20
//		String pa = request.getParameter("param");
		String proState = request.getParameter("proState");// 生产状态
		String start = request.getParameter("startDate");// 开工日期
		String end = request.getParameter("endDate");// 结束日期
		if(proState == null){
			proState = "2";
		}
//		proState = "1";
//		start = "2017-07-19";
//		end = "2017-10-19";
//		List<InSingleSystemVo> voList = new ArrayList<InSingleSystemVo>();
		Map<String, List<InSingleSystemVo>> voMap = getVoMap(proState, start,
				end,proState);
		/**
		 * 封装数据到Page里边
		 */
		getResult(list, voMap);
		//TODO 将list数据进行过滤
		if(start==null||end==null){
			
		}else{//都不为null才能过滤数据
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = df.parse(start);
			Date endDate = df.parse(end);
			
			for(int i = 0;i < list.size(); i++){
				Object obj = list.get(i);
				InSingleSystemVo vo = (InSingleSystemVo)obj;
				if(vo.getPlanStartTime().getTime()<startDate.getTime() || vo.getPlanEndTime().getTime() > endDate.getTime()){//表示不再查询范围
					list.remove(i);
					i--;
				}
			}
		}
		
		page.setData(list);
		page.setTotalCount(list.size());
		
	}

	/**
	 * 返回集结果集到Page里边
	 * @param list
	 * @param voMap
	 */
	private void getResult(List<Object> list,
			Map<String, List<InSingleSystemVo>> voMap) {
		for(String key : voMap.keySet()){
			InSingleSystemVo vo = new InSingleSystemVo();
			
			List<InSingleSystemVo> ll = voMap.get(key);
			vo.setOrderCode(key);//生产令编号
			
			//封装需要计算的数据，如：开工日期、完工日期、所需工时
			Timestamp tempStart = null;
			Timestamp tempEnd  = null;
			Double hour = null;
			for(InSingleSystemVo bean : ll){
				if(tempStart == null){//第一次进来
					tempStart = bean.getPlanStartTime();
				}else{//比较谁最小
//					if(tempStart.getTime() >= bean.getPlanStartTime().getTime()){
//						tempStart = bean.getPlanStartTime();
//					}
					tempStart = (tempStart.getTime() >= bean.getPlanStartTime().getTime())?bean.getPlanStartTime():tempStart;
				}
				if(tempEnd == null){
					tempEnd = bean.getPlanEndTime();
				}else{
					tempEnd = (tempEnd.getTime()<=bean.getPlanEndTime().getTime())?bean.getPlanEndTime():tempEnd;
				}
				hour = (hour == null?0:hour)+(bean.getHours()==null?0:bean.getHours());
			}
			vo.setColor(ll.get(0).getColor());//颜色
			vo.setPlanStartTime(tempStart);//计划开始日期
			vo.setPlanEndTime(tempEnd);//计划完成日期
			vo.setHours(hour);//总工时
			vo.setTimeUnit("h");//时间计量单位
			vo.setProGgxh(ll.get(0).getProGgxh());//规格型号
			vo.setCount(ll.get(0).getCount());//产品数量
			vo.setProUnit("m");//产品计量单位
			vo.setDeliveryTime(ll.get(0).getDeliveryTime());//交货日期
			vo.setContractCode(ll.get(0).getContractCode());//合同编号
			list.add(vo);
		}
	}

	/**
	 * 返回显示的voMap
	 * @param proState
	 * @param start
	 * @param end
	 * @return
	 * @throws ParseException
	 */
	private Map<String, List<InSingleSystemVo>> getVoMap(String proState,
			String start, String end,String is_flag) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Map<String,List<InSingleSystemVo>> voMap = new HashMap<String,List<InSingleSystemVo>>();
//		if ((proState != null && proState.length() != 0)
//				&& (start != null && start.length() != 0)
//				&& (end != null && end.length() != 0)) {
			//TODO 先只管合同中，已经生成生产令的在单系统统计，由于随着系统使用，合同，生产令等会无限增加，所以强制要求这三个查询条件必须要有
			
			/**
			 * 未生产{合同编号=HT001, 生产令id=5, 生产令编号=L001, 计划开始日期=2017-07-17 11:49:25.748, 
			 * 计划完成日期=2017-07-17 11:49:25.748, 拉丝总工时=18.43, 绞线总工时=18.41, 绝缘总工时=18.41, 
			 * 铠装总工时=null, 成缆总工时=null, 护套总工时=null, 交货日期=null}
			 */
			List<Map<String, Object>> resutlRow = jdbcTemplate.queryForList(SQL, new Object[]{is_flag,is_flag});
			//封装到voList
			for(Map<String,Object> map : resutlRow){
				
				Double laSi = map.get("拉丝总工时")==null?0:Double.parseDouble(map.get("拉丝总工时").toString()) ;
				Double jiaoXian = map.get("绞线总工时")==null?0:Double.parseDouble(map.get("绞线总工时").toString());
				Double jueYuan = map.get("绝缘总工时")==null?0:Double.parseDouble(map.get("绝缘总工时").toString());
				Double kaiZhuang = map.get("铠装总工时")==null?0:Double.parseDouble(map.get("铠装总工时").toString());
				Double chengLan = map.get("成缆总工时")==null?0:Double.parseDouble(map.get("成缆总工时").toString());
				Double huTao = map.get("护套总工时")==null?0:Double.parseDouble(map.get("护套总工时").toString());
				
				InSingleSystemVo vo = new InSingleSystemVo();
				vo.setColor(null);//颜色
				vo.setOrderCode(map.get("生产令编号").toString());//生产令编号
				vo.setContractCode(map.get("合同编号").toString());//合同编号
				if(map.get("计划开始日期") == null){
					vo.setPlanStartTime(null);
				}else{
					Date planStart = df.parse(map.get("计划开始日期").toString()); 
					vo.setPlanStartTime(new Timestamp(planStart.getTime()));//计划开始日期
				}
				if(map.get("计划完成日期") == null){
					vo.setPlanEndTime(null);
				}else{
					Date planEnd = df.parse(map.get("计划完成日期").toString());
					vo.setPlanEndTime(new Timestamp(planEnd.getTime()));//计划完工时间
				}
				if(map.get("交货日期") == null){
					vo.setDeliveryTime(null);
				}else{
					Date deliveryTime = df.parse(map.get("交货日期").toString());
					vo.setDeliveryTime(new Timestamp(deliveryTime.getTime()));//交货日期
				}
				vo.setHours(laSi+jiaoXian+jueYuan+kaiZhuang+chengLan+huTao);//总工时
				vo.setTimeUnit("h");//时间计量单位
				vo.setCount(Double.parseDouble(map.get("生产数量").toString()));//总数量(一个生产令生产的数量=生产段长*轴数);数据库已经计算
				vo.setProUnit("m");//产品计量单位
				
//				voList.add(vo);
				
				String orderId = map.get("生产令编号").toString();
				if(voMap.get(orderId)==null){
					voMap.put(orderId, new ArrayList<InSingleSystemVo>());
				}
				voMap.get(orderId).add(vo);
			}
//		}else{
//			throw new RuntimeException("查询参数不能有为空的项");
//		}
		return voMap;
	}
}
