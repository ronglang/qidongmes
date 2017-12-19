package com.css.business.web.subsyssell.sellManage.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaProductOrder;
import com.css.business.web.subsysplan.bean.PlaProductOrderAxis;
import com.css.business.web.subsysplan.plaManage.dao.PlaProductOrderAxisManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaProductOrderManageDAO;
import com.css.business.web.subsysplan.plaManage.service.SchedulingManageService;
import com.css.business.web.subsyssell.bean.SellContractDetail;
import com.css.business.web.subsyssell.bean.SellContractPlanBatch;
import com.css.business.web.subsyssell.sellManage.dao.SellContractDetailManageDAO;
import com.css.business.web.subsyssell.sellManage.dao.SellContractPlanBatchManageDAO;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
@Service("sellContractDetailManageService")
public class SellContractDetailManageService extends BaseEntityManageImpl<SellContractDetail, SellContractDetailManageDAO> {
	@Resource(name="sellContractDetailManageDAO")
	//@Autowired
	private SellContractDetailManageDAO dao;
	
	@Resource(name="plaProductOrderAxisManageDAO")
	private PlaProductOrderAxisManageDAO plaProductOrderAxisManageDAO;
	
	@Resource(name="plaProductOrderManageDAO")
	private PlaProductOrderManageDAO plaProductOrderManageDAO;
	
	@Resource(name="sellContractPlanBatchManageDAO")
	private SellContractPlanBatchManageDAO sellContractPlanBatchManageDAO;
	
	@Resource(name="schedulingManageService")
	private SchedulingManageService schedulingManageService;
	
	@Override
	public SellContractDetailManageDAO getEntityDaoInf() {
		return dao;
	}


	/**
	 * @TODO: 查询
	 * @author: zhaichunlei
	 & @DATE : 2017年8月25日
	 * @param pageStr
	 * @param pagesizeStr
	 * @param scId 合同表的ID
	 * @return
	 */
	public HashMap<String, Object> getSellContractDetailManageService(String pageStr, String pagesizeStr,Integer scId) {
		String hql = "from SellContractDetail where planBatchId=? order by id";
		HashMap<String,Object> map = getPagingQueryToolService(hql,pageStr,pagesizeStr,scId);
		return map;
		
	}
	 public void deleteSCDManageService(int [] ids){
		 String hql = "delete SellContractDetail where id=?";
		 dao.deleteByIds(ids, hql);
	 }
	 public void updateSellContractDetailManageService(SellContractDetail sellContractDetail){
			dao.updateSellContractDetailManageDao(sellContractDetail);
	 }
	 public void addSellContractDetailManageService(SellContractDetail sellContractDetail) {
		 dao.addSellContractDetailManageDao(sellContractDetail);
	 }

	 /**
	  *  旧方式。业务已不适合   新方法见genernateGd
	  * @param req
	  * @param ids
	  * @throws Exception
	  */
	 @Deprecated
	 public void downSCDManageService(HttpServletRequest req, int [] ids) throws Exception{
		 SysUser user = SessionUtils.getUser(req);
		 String hql0 = "update SellContractDetail Set pbatDetailState =? where id=?";
		 //String hql1 = "update sell_contract_plan_batch Set sell_contract_plan_batch.sc_planbat_state=? where sell_contract_detail.id=? and sell_contract_plan_batch.pbat_detail_code =sell_contract_detail.pbat_detail_code";
		 String hql2 = "from SellContractDetail where id=?";
		 //String hql3 = " from SellContractDetail where id = ? ";
		 String hql4 = " from SellContractPlanBatch where pbatDetailCode = ? ";
		 for (int i = 0; i < ids.length; i++) {
			 //dao.createQuery(hql0, "已生产",ids[i]);
			/* SellContractDetail scd = new SellContractDetail();
			 scd.setId(ids[i]);
			 scd.setPbatDetailState("已生成");
			 dao.updateByCon(scd, false);*/
			 
			SellContractDetail scd = dao.get(ids[i]);
			scd.setPbatDetailState("已生成");
			scd.setProPeriodLength(scd.getReqPeriodLength()); //把客户要求段长，先写入生产段长。 以后有模块时再处理
			dao.updateByCon(scd, false);
			
			/*@SuppressWarnings("unchecked")
			List<SellContractDetail> list = dao.createQuery(hql2,ids[i]).list();
			 for (SellContractDetail sd : list) {
				 @SuppressWarnings("unchecked")
				List<SellContractPlanBatch> li = dao.createQuery(hql4, sd.getPbatDetailCode()).list();
				for (SellContractPlanBatch spb : li) {
					//SellContractPlanBatch sp = new SellContractPlanBatch();
					//sp.setId(spb.getId());
					spb.setScPlanbatState("已下发");
					dao.updateByCon(spb, false);
				}
				// dao.createSQLQuery(hql1,"已下发",ids[i]);
			}*/
			/*@SuppressWarnings("unchecked")
			List<SellContractPlanBatch> li = dao.createQuery(hql4, scd.getPbatDetailCode()).list();
			for (SellContractPlanBatch spb : li) {
				//SellContractPlanBatch sp = new SellContractPlanBatch();
				//sp.setId(spb.getId());
				spb.setScPlanbatState("已下发");
				//dao.updateByCon(spb, false);
				sellContractPlanBatchManageDAO.updateByCon(spb, false);
			}*/
			Integer batId = scd.getPlanBatchId();
			SellContractPlanBatch spb = sellContractPlanBatchManageDAO.get(batId);
			spb.setScPlanbatState("已下发");
			sellContractPlanBatchManageDAO.updateByCon(spb, false);
			
			String proCode = spb.getProCode();
			if(proCode == null || proCode.length() == 0){
				throw new Exception("合同批次表的产品为空。数据错误");
			}
			
			 //生产生产令
			 //SellContractDetail sellContractDetail = (SellContractDetail) dao.createQuery(hql2, ids[i]).list().get(0);
			 PlaProductOrder plaProductOrder = new PlaProductOrder();
			 plaProductOrder.setProductOrderCode(dao.exeFunction("fun_get_pla_product_order").toString());
			 Timestamp timestamp = Timestamp.valueOf(scd.getDeliveDate().toString());
			 plaProductOrder.setDemandDate(timestamp);
			 plaProductOrder.setAmount(scd.getReqAmount());
			 
			 //String reqPeriodLengthStr = sellContractDetail.getReqPeriodLength().split("x")[1];
			 //plaProductOrder.setDemandPartLen(Double.parseDouble(reqPeriodLengthStr)*1000);
			 plaProductOrder.setDemandPartLen(scd.getReqPeriodLength());
			 plaProductOrder.setProductPartLen(scd.getReqPeriodLength()+"0.2");
			 //目前生产段长未实际变更， 这里先用客户要求段长。 以后应该有模块处理合并要求段长为生产段长--------------------
			 plaProductOrder.setProductPartLen(scd.getReqPeriodLength());
			 plaProductOrder.setProGgxh(scd.getProGgxh());
			 plaProductOrder.setIs_flag("0"); //否已加入计划 (0:未生产；1:已生产; 2:预生产, 3:生产中)
			 Timestamp timestamp2 = Timestamp.valueOf(scd.getCreateDate().toString());
			 plaProductOrder.setBillDate(timestamp2);
			 plaProductOrder.setPbatDetailCode(scd.getPbatDetailCode());
			 plaProductOrder.setContractCode(scd.getScCode());
			 //plaProductOrder.setProductOrderState(productOrderState);  未知状态。 不填
			 //dao.save(plaProductOrder);
			 plaProductOrder.setProColor(scd.getProColor());
			 plaProductOrder.setProCode(proCode);
			 plaProductOrder.setProCraftCode(spb.getProCraftCode());//产品工艺
			 plaProductOrder.setBatCode(scd.getBatCode());
			 plaProductOrder.setCompleteAmount(0);
			 
			 plaProductOrder.setCreateBy(user.getAccount());
			 plaProductOrder.setCreateDate(new Date(System.currentTimeMillis()));
			 plaProductOrderManageDAO.save(plaProductOrder);
			 
			 //生成生产令对应轴号
			 //Integer amount = plaProductOrder.getAmount();
			 //String pl = scd.getProPeriodLength();
			 //先用客户要求段长。
			 String pl = scd.getProPeriodLength();

			 /*if(amount != null && pl != null){
				 SortedMap<Integer,Integer> map = new TreeMap<Integer,Integer>();
				 pl = pl.replace(" ", "");//英文空格
				 pl = pl.replace(" ","");//中文空格
				 pl = pl.replace("	",""); //tab空格
				 
				 //1.2*3 + 0.33*3 + 2.3*1
				// String[] reg = pl.split("+");
				 String[] reg = pl.split("\\+");
				 for(int j = 0; j < reg.length; j ++){
					 String ss[] = reg[j].split("\\*");
					 map.put(Integer.parseInt(ss[0]),Integer.parseInt(ss[1]));
				 }
				 
				 Iterator<Integer> it = map.keySet().iterator();
				 int m = 0;
				 String poc = plaProductOrder.getProductOrderCode();
				 while(it.hasNext()){
					 Integer dc = it.next(); //段长
					 Integer num = map.get(dc); //轴数
					 for(int j = 0; j < num; j ++){
						 m++;
						 PlaProductOrderAxis pa = new PlaProductOrderAxis();
						 pa.setProductOrderCode(poc);
						 pa.setAxisName(poc+"_"+ StringUtil.lpad(m+"",4,'0'));
						 pa.setPartLen(dc);
						 pa.setCreateBy(user == null ? null : user.getName());
						 pa.setCreateDate(new Date(System.currentTimeMillis()));
						 
						 //axis_code/mater_type/start_date/end_date,此时未经机台计划计算，无法写入
						 plaProductOrderAxisManageDAO.save(pa);
					 }
				 }
			 } */
		}
	 }
	 
	public HashMap<String, Object> getSerchSCDManageService(String page, String pagesize, String batCode, String scCode,String pbatDetailCode, String pbatDetailState) {
			String hql="from SellContractDetail where 1=1 ";
			List<String> list = new ArrayList<>();
			if(batCode!=""){
				hql+=" and batCode =?";
				list.add(batCode);
			}
			if(scCode!=""){
				hql+=" and scCode =?";
				list.add(scCode);
			}
			if(pbatDetailCode!=""){
				hql+=" and pbatDetailCode =?";
				list.add(pbatDetailCode);
			}
			if(!pbatDetailState.startsWith("---")){
				hql+=" and pbatDetailState =?";
				list.add(pbatDetailState);
			}
			hql+= " order by id";	
			String[] arrays = new String[list.size()];
			for (int i = 0;i<arrays.length;i++) {
				arrays[i] = list.get(i);
			}
			HashMap<String,Object> map = getPagingQueryToolService(hql,page,pagesize,arrays);
			return map;
	}
	 public HashMap<String,Object> getPagingQueryToolService(String hql,String pageStr,String pagesizeStr,final Object...values){
		 HashMap<String,Object> map=new HashMap<String,Object>();
		 List<?> list;
		 double total;
		 int page=1;
		 int pagesize=10;
		 if(pageStr!=null){
			 page =Integer.parseInt(pageStr);
		  }
		 if(pagesizeStr!=null){
			 pagesize=Integer.parseInt(pagesizeStr);
		 }
		 total = dao.getPagingQueryToolDao(hql, true, page, pagesize, values).size();
		 list = dao.getPagingQueryToolDao(hql, false, page, pagesize, values);
		 map.put("Total", total);
		 map.put("Rows", list);
		 return map;
	 }

	 
	public List<SellContractDetail> findByIds(List<Integer> idList) {
		String hql = " from SellContractDetail where id in (:ids) ";
//		hql += " and createBy = 'york' ";// TODO 正式上线需要去掉，这个过滤的是我自建的模拟数据
		@SuppressWarnings("unchecked")
		List<SellContractDetail> list = dao.getHibernateTemplate().findByNamedParam(hql, "ids", idList);
		return list;
	}


	/**   
	 * @Description: 查询所有产品型号,及产品工艺编码   
	 * @return         
	 */ 
	public Map[]  queryAllGGXH() {
		// TODO Auto-generated method stub
		String sql = "select DISTINCT pro_ggxh from cra_craft_product ";
		List<String> list = dao.createSQLQuery(sql).list();
		Map[] resultMap = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map<String,String> map = new HashMap<>();
			map.put("id", list.get(i));
			map.put("text", list.get(i));
			resultMap[i] = map;
			
		}
		return resultMap;
	}


	/**   
	 * @Description: 根据产品的型号,获得所有颜色
	 * @param proGgxh
	 * @return         
	 */ 
	public Map[]  queryGGXHColor(String proGgxh) {
		// TODO Auto-generated method stub
		String sql = "select  pro_color from cra_craft_product where pro_ggxh= '"+proGgxh+"'";
		List<String> list = dao.createSQLQuery(sql).list();
		Map[] resultMap = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map<String,String> map = new HashMap<>();
			map.put("id", list.get(i));
			map.put("text", list.get(i));
			resultMap[i] = map;
		}
		return resultMap;
	}


	/**   
	 * @Description: 获得产品工艺编码   
	 * @param proGgxh 产品型号
	 * @param color 产品颜色
	 * @return         
	 */ 
	public String[] queryProCraftCode(String proGgxh, String color) {
		// TODO Auto-generated method stub
		String sql = "select pro_craft_name,pro_craft_code,pro_id from cra_craft_product where pro_ggxh= '"+proGgxh+"' and pro_color='"+color+"'";
		List<Object[]> list = dao.createSQLQuery(sql).list();
		try {
			
		
			String[]  result = new String[list.get(0).length];
		if (list != null && list.size() > 0) {
			result[0] = list.get(0)[0].toString();
			result[1] = list.get(0)[1].toString();
			result[2] = list.get(0)[2].toString()+"";
			
			}
		return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询已下发与可以下发的数量
	 * @param planBatchId
	 * @return
	 * @throws Exception 
	 */
	public Page loadMayBeData(Page p,Integer planBatchId) throws Exception{
		//SellContractDetailVO v = new SellContractDetailVO();
		return dao.loadMayBeData(p,planBatchId);
	}
	
	
	public void genernateGd(SysUser user,Integer detailId, String proPeriodLength, String deliveDateStr)
			throws Exception {
		// sell_contract_detail:bat_code,sc_code,create_by,create_date,delive_date
		// req_unit,pbat_detail_code,pbat_detail_state,req_period_length,req_amount,pro_ggxh
		
		// sell_contract:id,create_date,create_by,sc_code
		
		// sell_contract_plan_batch:req_amount,req_unit,bat_code,delive_date,pbat_detail_code,req_period_length,pro_ggxh
		if(detailId == null || proPeriodLength == null || proPeriodLength.length() == 0 ||
				deliveDateStr == null || deliveDateStr.length() == 0){
			throw new Exception("传参错误。无法下单");
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp deliveDate = new Timestamp(sdf.parse(deliveDateStr).getTime());
		
		SellContractDetail detail = dao.get(detailId);
		//Integer bid = detail.getPlanBatchId();
		//SellContractPlanBatch pb = sellContractPlanBatchManageDAO.get(bid);
		
		//校验录入的段长，是否与明细中的段长的长度一致。必须相等，段数允许小于
		String dpl = detail.getProPeriodLength();
		if(dpl == null || dpl.length() == 0 ) throw new Exception("生产通知单明细中，生产段长数据错误。不能为空");
		if(dpl.split("\\*").length < 2 )throw new Exception("生产通知单明细中，生产段长数据格式错误。不存在*号");
		if(proPeriodLength.split("\\*").length < 2 )throw new Exception("录入的段长格式错误，不存在*号");
		
		//第一个参数是段长，第二个是段数
		Map<String,Integer> lm = new HashMap<String,Integer>();
		String ts[] = dpl.split("\\+");
		for(String tt : ts){
			if(tt != null && tt.length() > 0){
				String ttt[] = tt.split("\\*");
				if(lm.containsKey(ttt[0])){
					//覆盖掉旧值
					lm.put(ttt[0], lm.get(ttt[0]) + Integer.parseInt(ttt[1]) );
				}
				//增加
				else{
					lm.put(ttt[0],Integer.parseInt(ttt[1]));
				}
			}
		}
		
		Integer partNum = 0;
		//校验录入的段长数据是否正确
		ts = proPeriodLength.split("\\+");
		for(String tt : ts ){
			if(tt != null && tt.length() > 0){
				String ttt[] = tt.split("\\*");
				//不包含录入的段长，说明录入错误
				if( ! lm.containsKey(ttt[0])){
					throw new Exception("生成工单，录入的段长("+ttt[0]+")不包含在生产通知单所包含的段长内。 无法下单");
				}
				
				partNum += Integer.parseInt(ttt[1]);
			}
		}
		
		
		//校验工单是否超出了明细的值
		
		Integer totalL = 0;
		String pl = detail.getProPeriodLength();
		if(pl == null || pl.length() == 0)
			throw new Exception("生产单明细的生产段长不能为空");
		/*String gs[] = pl.trim().split("+");
		for(int i = 0 ; i < gs.length ; i ++){
			String ss[] = gs[i].trim().split("*");
			if(ss == null || ss.length != 2) 
				continue;
			totalL += Integer.parseInt(ss[0].trim()) * Integer.parseInt(ss[1].trim());
		}*/
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine se = manager.getEngineByName("js");
		//totalL = Integer.parseInt(se.eval(pl).toString());
		BigDecimal bd = new BigDecimal(se.eval(pl).toString());
		totalL = bd.intValue();
		if(totalL == 0)
			throw new Exception("生产单明细的生产段长计算结果长度为0. 无法执行");
		
		Integer coml = detail.getCompleteLen();//已下发长度
		Integer tl = 0;
		/*gs = proPeriodLength.trim().split("+");
		for(int i = 0 ; i < gs.length ; i ++){
			String ss[] = gs[i].trim().split("*");
			if(ss == null || ss.length != 2) 
				continue;
			tl += Integer.parseInt(ss[0].trim()) * Integer.parseInt(ss[1].trim());
		}*/
		
		//tl =  Integer.parseInt(se.eval(proPeriodLength).toString());
		bd = new BigDecimal(se.eval(proPeriodLength).toString());
		tl = bd.intValue();
		if(tl == 0)
			throw new Exception("下发工单的总长度须大于0");
		
		if(tl + coml > totalL)
			throw new Exception("下发工单的长度("+(tl+coml)+")不能大于生产单的长度("+(totalL)+")");
		
		detail.setCompleteLen(tl + coml);
		//明细已全部完成下发，自动更新明细为已下发
		if(tl + coml == totalL){
			detail.setPbatDetailState("已生成");
		}
		//未全部完成
		else if(tl + coml < totalL){
			detail.setPbatDetailState("未全部生成");
		}
		dao.updateByCon(detail, false);
		
		//生产通知单在分解后就是已分解。 在明细中生成工作单后，状态是明细的生成状态。不需要再考虑通知单的状态
		Integer plan_batch_id = detail.getPlanBatchId();
		SellContractPlanBatch cb = sellContractPlanBatchManageDAO.get(plan_batch_id);
		
		String proCode = cb.getProCode();
		if(proCode == null || proCode.length() == 0){
			throw new Exception("产品批次表数据错误，产品编码不能为空");
		}
		/*
		Integer total_part_len = cb.getTotalPartLen();
		
		Integer dwholeLen = sellContractDetailManageDAO.getAllGdPartLenByBatch(plan_batch_id);
		//detail已全部下发。 批次表更新为已全部下发
		if(total_part_len.intValue() == dwholeLen){
			cb.setScPlanbatState("已下发");
			sellContractPlanBatchManageDAO.updateByCon(cb, false);
		}*/
		
		//参数：长度、数量
		Map<Integer,Integer> pmap = new HashMap<Integer,Integer>();
		//根据段长中的表达式，决定是分为多少个工单1
		for(String tt : ts ){
			if(tt != null && tt.length() > 0){
				String ttt[] = tt.split("\\*");
				ttt[0] = ttt[0] == null ? null :  ttt[0].trim();
				ttt[1] = ttt[1] == null ? null :  ttt[1].trim();
				if(ttt[0] != null && ttt[1] != null){
					Integer temp = pmap.get(Integer.parseInt(ttt[0]));
					if(temp == null)
						pmap.put(Integer.parseInt(ttt[0]),Integer.parseInt(ttt[1]));
					else
						pmap.put(Integer.parseInt(ttt[0]), temp + Integer.parseInt(ttt[1]));
				}
			}
		}
		
		for(Iterator<Integer> itt = pmap.keySet().iterator() ; itt.hasNext() ;){
			Integer partlen = itt.next(); //段长
			Integer amount = pmap.get(partlen); //轴数
			
			//生成生产令
			//Integer mainId = detail.getMainId();
			//SellContract sc = dao.get(mainId);
			PlaProductOrder plaProductOrder = new PlaProductOrder();
			plaProductOrder.setProductOrderCode(dao.exeFunction("fun_get_pla_product_order").toString());
			plaProductOrder.setDemandDate(deliveDate);
			//plaProductOrder.setAmount(partNum);
			//plaProductOrder.setAmount(amount);
			
			//String reqPeriodLengthStr = sellContractDetail.getReqPeriodLength().split("x")[1];
			//plaProductOrder.setDemandPartLen(Double.parseDouble(reqPeriodLengthStr)*1000);
			plaProductOrder.setDemandPartLen(proPeriodLength); //要求段长。 下发工单，不存在客户要求。
			//目前生产段长未实际变更， 这里先用客户要求段长。 以后应该有模块处理合并要求段长为生产段长--------------------
			//plaProductOrder.setProductPartLen(proPeriodLength);  //实际录入的
			plaProductOrder.setProductPartLen(partlen.toString());  //录入的如果是表达式，则拆分为多个表单。
			plaProductOrder.setProGgxh(detail.getProGgxh());
			plaProductOrder.setIs_flag("0"); //否已加入计划 (0:未生产；1:已生产; 2:预生产, 3:生产中)
			plaProductOrder.setBillDate(new Timestamp(detail.getCreateDate().getTime()));
			plaProductOrder.setPbatDetailCode(detail.getPbatDetailCode());
			plaProductOrder.setContractCode(detail.getScCode());
			plaProductOrder.setProductOrderState("否");  // 未生成计划
			//dao.save(plaProductOrder);
			plaProductOrder.setProColor(detail.getProColor());
			plaProductOrder.setProCode(cb.getProCode());
			plaProductOrder.setProCraftCode(cb.getProCraftCode());//产品工艺
			plaProductOrder.setBatCode(detail.getBatCode());
			plaProductOrder.setCompleteAmount(0);
			
			plaProductOrder.setCreateBy(user.getAccount());
			plaProductOrder.setCreateDate(new Date(System.currentTimeMillis()));
			plaProductOrder.setContractDetailId(detailId);
			plaProductOrder.setBatCode(detail.getBatCode());
			plaProductOrderManageDAO.save(plaProductOrder);
			
			//保存工单
			//schedulingManageService.saveGD(user,plaProductOrder);
			
			//生成生产令对应轴号
			//Integer amount = plaProductOrder.getAmount();
			//String pl = scd.getProPeriodLength();
			//pl =detail.getProPeriodLength(); 
			//pl = proPeriodLength;
			
			if(amount != null && pl != null){
				//SortedMap<Integer,Integer> map = new TreeMap<Integer,Integer>();
				/*pl = pl.replace(" ", "");//英文空格
				pl = pl.replace(" ","");//中文空格
				pl = pl.replace("	",""); //tab空格
*/				
				//1.2*3 + 0.33*3 + 2.3*1
				// String[] reg = pl.split("+");
				/*String[] reg = pl.split("\\+");
				for(int j = 0; j < reg.length; j ++){
					String ss[] = reg[j].split("\\*");
					map.put(Integer.parseInt(ss[0].toString()),Integer.parseInt(ss[1]));
				}*/
				
				/*Iterator<Integer> it = map.keySet().iterator();
				int m = 0;
				while(it.hasNext()){
					Integer dc = it.next(); //段长
					Integer num = map.get(dc); //轴数
*/					//for(int j = 0; j < num; j ++){
				String poc = plaProductOrder.getProductOrderCode();
				int m = 0;
				for(int j = 0; j < amount; j ++){
						m++;
						PlaProductOrderAxis pa = new PlaProductOrderAxis();
						pa.setProductOrderCode(poc);
						pa.setAxisName(poc+"_"+ StringUtil.lpad(m+"",4,'0'));
						pa.setPartLen(partlen);
						pa.setCreateBy(user == null ? null : user.getName());
						pa.setCreateDate(new Date(System.currentTimeMillis()));
						
						//axis_code/mater_type/start_date/end_date,此时未经机台计划计算，无法写入
						plaProductOrderAxisManageDAO.save(pa);
					}
				//}
			}
		}
	}
}
