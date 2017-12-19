package com.css.business.web.subsysplan.plaManage.service;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.bean.CraProSeqRelation;
import com.css.business.web.subsyscraft.craManage.dao.CraRouteManageDAO;
import com.css.business.web.subsyscraft.craManage.dao.CraRouteSeqManageDAO;
import com.css.business.web.subsysmanu.bean.MauMachine;
import com.css.business.web.subsysmanu.bean.MauMachineSpeed;
import com.css.business.web.subsysmanu.mauManage.dao.MauMachineManageDAO;
import com.css.business.web.subsysplan.bean.PlaMachinePlan;
import com.css.business.web.subsysplan.bean.PlaMachinePlanMater;
import com.css.business.web.subsysplan.bean.PlaMachinePlanSchedule;
import com.css.business.web.subsysplan.bean.PlaProductOrder;
import com.css.business.web.subsysplan.bean.PlaProductOrderSeqHourse;
import com.css.business.web.subsysplan.bean.PlaUnitConMater;
import com.css.business.web.subsysplan.bean.PlaWeekPlan;
import com.css.business.web.subsysplan.bean.PlaWeekSeqHours;
import com.css.business.web.subsysplan.plaManage.dao.PlaMachinePlanManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaMachinePlanScheduleManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaProductOrderManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaWeekPlanManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaWeekSeqHoursManageDAO;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
@Service("schedulingManageService")
public class SchedulingManageService extends BaseEntityManageImpl<PlaWeekSeqHours, PlaWeekSeqHoursManageDAO>  {

	@Override
	public PlaWeekSeqHoursManageDAO getEntityDaoInf() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*public static final String PRIXF = " and createBy = 'york' ";
	
	public static final Integer DAY_HOUR = 20;//机台每天工作20个小时
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private PlaWeekSeqHoursManageDAO plaWeekSeqHoursManageDAO;
	@Autowired
	private CraRouteManageDAO craRouteManageDAO;//工艺路线
	@Autowired
	private CraRouteSeqManageDAO craRouteSeqManageDAO;//工艺路线明细-工序
	@Autowired
	private MauMachineManageDAO mauMachineManageDAO;//机台编号
	@Autowired
	private PlaWeekPlanManageDAO plaWeekPlanManageDAO;//计划编号
	@Resource(name = "plaMachinePlanManageService")
	private PlaMachinePlanManageService service; //机台计划service
	@Resource(name="plaMachinePlanManageDAO")
	private PlaMachinePlanManageDAO plaMachinePlanManageDAO;
	
	@Resource(name="plaMachinePlanScheduleManageDAO")
	private PlaMachinePlanScheduleManageDAO plaMachinePlanScheduleManageDAO;
	
	@Autowired
	private PlaProductOrderManageDAO plaProductOrderManageDAO;//生产令dao
	
	@Autowired
	private SellContractDetailManageDAO sellContractDetailManageDAO;
	@Autowired
	private SellContractManageDAO sellContractManageDAO;
	
	@Resource(name="plaProductOrderSeqHourseManageDAO")
	private PlaProductOrderSeqHourseManageDAO plaProductOrderSeqHourseManageDAO;
	
	@Resource(name="craCraftProductManageDAO")
	private CraCraftProductManageDAO craCraftProductManageDAO;
	
	@Resource(name="craProSeqRelationManageDao")
	private CraProSeqRelationManageDao craProSeqRelationManageDao;
	
	@Resource(name="plaCourseManageDAO")
	private PlaCourseManageDAO plaCourseManageDAO;
	
	@Resource(name="mauMachineSpeedManageDAO")
	private MauMachineSpeedManageDAO mauMachineSpeedManageDAO;
	
	@Resource(name = "plaUnitConMaterManageService")
	private PlaUnitConMaterManageService puservice;
	
	@Resource(name = "plaMachinePlanMaterManageService")
	private PlaMachinePlanMaterManageService ppservice;
	
	@Resource(name="plaProductOrderAxisManageDAO")
	private PlaProductOrderAxisManageDAO plaProductOrderAxisManageDAO;
	
	@Resource(name="mauMaterialRecordManageDAO")
	private MauMaterialRecordManageDAO mauMaterialRecordManageDAO;//各种工单  这里领料单

	@Override
	public PlaWeekSeqHoursManageDAO getEntityDaoInf() {
		return plaWeekSeqHoursManageDAO;
	}
	
	public List<Object> getProOrderVoDataList(List<PlaProductOrder> paramList){
		List<Object> resultList = new ArrayList<Object>();
		String sql = getSql(paramList);
	    List<Map<String,Object>> lists = jdbcTemplate.queryForList(sql);  
	    //{pla_order_id=2, pro_ggxh=ASD0, lasi=45.00, jiaoxian=38.08, jueyuan=49.29, fenpan=94.48}
		for(Map<String,Object> map : lists){
			System.out.println(map);
			ProOrderVo vo = new ProOrderVo();
			vo.setPlaOrderId(map.get("pla_order_id").toString());
			vo.setLaSi(map.get("lasi").toString());
			vo.setJiaoXian(map.get("jiaoxian").toString());
			vo.setJiJueYuan(map.get("jueyuan").toString());
			vo.setFenPan(map.get("fenpan").toString());
			resultList.add(vo);
		}
		
		return resultList;
	}
	
	public String getSql(List<PlaProductOrder> paramList){
		StringBuffer sql = new StringBuffer("select pla_order_id,pro_ggxh,sum(case seq_name when '拉丝' then seq_hours else 0 end)  as laSi,sum(case seq_name when '绞线' then seq_hours else 0 end)  as jiaoXian,sum(case seq_name when '绝缘' then seq_hours else 0 end)  as jueYuan,sum(case seq_name when '分盘' then seq_hours else 0 end)  as fenPan from pla_week_seq_hours where 1=1 ");
		sql.append("and ( ");
//		Iterator<PlaProductOrder> it = paramList.iterator();
		int size = paramList.size();
		for(int i = 0;i < size;i++){
			PlaProductOrder param = paramList.get(i);
			
			if(i == size-1){
				sql.append("  pla_order_id = "+param.getId()+" ");
			}else{
				sql.append("  pla_order_id = "+param.getId()+" or");
			}
		}
		sql.append(")");
		sql.append(" group by pla_order_id,pro_ggxh ");
		return sql.toString();
	}
	
	/**
	 * 只能支持根据id修改状态为1;
	 * @param list
	 * @param sql
	 * @return
	 
	public int batchUpdate(final List<Integer> list,String sql){
		jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, "1");
				ps.setInt(2,list.get(i));
			}
			
			@Override
			public int getBatchSize() {
				return list.size();
			}
			
		});
		return 0;
	}
	*//**
	 * 根据生产令id集合，查询工时对象集合记录
	 * @param gsList
	 *//*
	public List<PlaWeekSeqHours> findByIdsForHours(List<Integer> gsList) {
		String hql = " from PlaWeekSeqHours where plaOrderId in (:ids) ";
		List<PlaWeekSeqHours> list = plaWeekSeqHoursManageDAO.getHibernateTemplate().findByNamedParam(hql, "ids", gsList);
		return list;
	}
	


	
	*//**
	 * @TODO: 根据生产令，生成工单
	 * @author: zhaichunlei
	 & @DATE : 2017年8月9日
	 * @param ppo
	 * @return
	 * @throws Exception 
	 *//*	public void saveGD(SysUser user,PlaProductOrder ppo) throws Exception{
		String proCraftCode = ppo.getProCraftCode();
		CraCraftProduct ccp = craCraftProductManageDAO.getObjByProCraCode(proCraftCode);
		if(ccp == null)
			throw new Exception("产品工艺术("+proCraftCode+")不存在。数据错误");
		
		PlaCourse pc = new PlaCourse();
		Integer contract_detail_id = ppo.getContractDetailId();
		if(contract_detail_id == null)
			throw new Exception("数据错误，生产通知单明细ID不能为空");
		SellContractDetail scd = sellContractDetailManageDAO.get(contract_detail_id);
		if(scd == null)
			throw new Exception("数据错误，对应的生产通知单明细不存在");
		Integer mainId = scd.getMainId();
		if(mainId == null)
			throw new Exception("数据错误，生产通知单ID不能为空");
		SellContract sc = sellContractManageDAO.get(mainId);
		if(sc == null)
			throw new Exception("数据错误，对应的生产通知单不存在");
		
		//根据数据库的函数，生产一个工作单号
		//Object obj = dao.exeFunction("fun_get_course_code");
		pc.setWsCode(ppo.getProductOrderCode()); //工单号与生产令，共用同一个号
		pc.setProductOrderCode(ppo.getProductOrderCode());
		pc.setHeadGgxh(ppo.getProGgxh());
		pc.setHeadZzdc(ppo.getProductPartLen());
		pc.setHeadZzds(ppo.getAmount().toString());
		pc.setProCraftCode(ppo.getProCraftCode()); //产品工艺编码  核心
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine se = manager.getEngineByName("js");
		//Double partLen = (Double) se.eval(ppo.getProductPartLen());
		BigDecimal partLen = new BigDecimal(se.eval(ppo.getProductPartLen()).toString());
		//pc.setTotalAmount(relation.get(0).getCore() * partLen);
		pc.setTotalAmount(partLen.intValue());
		pc.setIsFinish("否");
		//pc.setUseFlag("未启用");
		pc.setUseFlag("否");
		pc.setPastFlag("否");
		
		pc.setCreateBy(user.getAccount());
		pc.setCreateDate(new Date(System.currentTimeMillis()));
		pc.setBillDate(new Date(System.currentTimeMillis()));
		pc.setWsType(sc.getWsType()); //正常开单/返工单/挪单/被挪开新单
		pc.setScCode(ppo.getContractCode()); //合同编号
		pc.setBatCode(ppo.getBatCode()); //合同批次号
		pc.setColor(ppo.getProColor()); //产品颜色
		pc.setRouteCode(ccp.getRouteCode()); //工艺路线编码
		pc.setProductOrderCode(ppo.getProductOrderCode()); //生产令编码
		pc.setDemandDate(ppo.getDemandDate()); //交货时间
		pc.setPlanFlag("否"); //计划未生成
		
		pc.setProGgxh(ppo.getProGgxh());
		pc.setProColor(ppo.getProColor());
		
		plaCourseManageDAO.save(pc);

	


	*//**
	 * @TODO: 生成机台计划、进度、材料计划
	 * @author: zhaichunlei
	 & @DATE : 2017年8月9日
	 * @param ppos
	 * @throws Exception 
	 *//*
	private void saveMachinePlan_(SysUser user,PlaProductOrder ppo,PlaWeekPlan pla) throws Exception{
		Integer workDay = pla.getWorkDay();
		Integer proOrderId = ppo.getId();
		List<PlaProductOrderSeqHourse> polst = plaProductOrderSeqHourseManageDAO.getListByProductOrderIdAndWorkDay(proOrderId, workDay);
		if(polst == null || polst.size() == 0)
			throw new Exception("生产令（"+ppo.getProductOrderCode()+"）在工作日（"+workDay+"）不存在拆分记录。数据错误");
		
		//取产品工艺对应的工序信息
		String proCraftCode = ppo.getProCraftCode();
		List<CraProSeqRelation> rlst = craProSeqRelationManageDao.getRouteSeqByRoute_code(proCraftCode);
		
		for(PlaProductOrderSeqHourse posh : polst){
			String macCode = posh.getMacCode();
			MauMachine m = mauMachineManageDAO.getByMacCode(macCode);
			Integer sort = getSeqSort(rlst,posh.getSeqCode());
			
			//生成机台计划
			PlaMachinePlan pls = new PlaMachinePlan();
			pls.setCourseCode(ppo.getProductOrderCode());
			pls.setWeekPlanId(pla.getId());
			pls.setWorkDay(pla.getWorkDay());
			pls.setWorkDate(pla.getWorkStartDate());
			pls.setAxisName(posh.getAxisName());
			pls.setMachineId(m.getId());
			pls.setPlanStartTime(new Timestamp(posh.getStartDate().getTime()));
			pls.setPlanEndTime(new Timestamp(posh.getEndDate().getTime()));
			pls.setCreateBy(user.getAccount());
			pls.setPartLen(posh.getPartLen().toString());
			pls.setProductState("未开始");
			pls.setMainBy(null);   //未指定主操手
			pls.setViceBy(null);   //未指定副操手
			pls.setCreateDate(new Date(System.currentTimeMillis()));
			pls.setSeqCode(posh.getSeqCode());
			pls.setSort(sort);
			//pls.setRouteCode(); //需要从工单表取
			pls.setMacCode(posh.getMacCode());
			
			plaMachinePlanManageDAO.save(pls);
			
			//保存机台计划的进度 表
			PlaMachinePlanSchedule pps = new PlaMachinePlanSchedule();
			pps.setAxisName(pls.getAxisName());
			pps.setCreateBy(pls.getCreateBy());
			pps.setCreateDate(new Date(System.currentTimeMillis()));
			pps.setMachineId(pls.getMachineId());
			pps.setPlanStartTime(pls.getPlanStartTime()); //计划开工时间
			pps.setPlanEndTime(pls.getPlanEndTime());    //计划结束时间
			pps.setWeekPlanId(pls.getWeekPlanId());
			pps.setWorkDate(pls.getWorkDate());
			pps.setPlanIncomingTime(pls.getPlanStartTime());//计划来料时间
			
			//本字段意义不大. 下工序什么时候要,由下工序自己决定.这里取下工序的时间太麻烦. 暂定为生产结束时间
			//优化，或删除
			pps.setPlanSendNextSeqDate(pls.getPlanEndTime()); //计划配送到下工序时间.   
			
			pps.setMachinePlanId(pls.getId()); //机台计划id
			pps.setCourseCode(pls.getCourseCode());//工单
			pps.setCreateDate(new Date(System.currentTimeMillis()));
			pps.setSeqCode(pls.getSeqCode()); //工序编码
			pps.setProductCraft(ppo.getProCraftCode()); //产品工艺编码
			
			pps.setEmployeeId(null);  //人员id。 不像是主操手、副操人的用法.
			pps.setSemiProductLen(null); //半制品长度。  各工序的制品长度，估计不好计算。 目前不理会
			
			//下面是一系列运算或查询
			pps.setSort(sort); //在工序中的排序
			String upSeqCode = getUpSeqCode(rlst,posh.getSeqCode());
			pps.setUpSeqCode(upSeqCode);  //上工序编码
			String nextSeqCode = getNextSeqCode(rlst,posh.getSeqCode());
			pps.setNextSeqCode(nextSeqCode); //下工序编码
			String mc = getSpecifySeq_macCode(polst,nextSeqCode,pla.getWorkDay());
			pps.setNextSeqMachineCode(mc);//下一工序的机台
			BigDecimal sp = getMachineSpeed(m.getId(),ppo.getProGgxh());
			pps.setProductSpeed(sp); //取设定生产速度. 
			
			//生产结束后要回写
			pps.setReadyTime(null); //准备时间
			pps.setProductSpeedAvg(null); //平均生产速度
			pps.setVirtualRfid(null);   //收、放线处的rfid
			pps.setVirtualRfidIncome(null);
			//实际开工时间、结束时间、来料时间、配送走时间
			pps.setFactEndTime(null);
			pps.setFactIncomingTime(null);
			pps.setFactSendNextSeqDate(null);
			pps.setFactStartTime(null);
			
			plaMachinePlanScheduleManageDAO.save(pps);
			
			
			//生成来料计划
			List<PlaUnitConMater> mater = puservice.getPlaUnitConMater(ppo.getProGgxh());
			Double weight = 0.0;
			for (PlaUnitConMater mm : mater) {
				PlaMachinePlanMater ppm = new PlaMachinePlanMater();
				if ("铜料".equals(mm.getObjName()) 	|| "铝料".equals(mm.getObjName())) {
					if (ppo.getProductOrderCode() != null) {
						weight += mm.getObjCount()	* Double.valueOf(pls.getPartLen());
						ppm.setAmount(weight);
						ppm.setGgxh(mm.getObjGgxh());
						ppm.setMaterName(mm.getObjName());
						ppm.setCreateDate(new Date(System.currentTimeMillis()));
						ppm.setMachineId(pls.getMachineId());
						ppm.setCourseCode(ppo.getProductOrderCode()); //工单号与生产令编号是同一个
						ppm.setUnit("kg");
						ppm.setSendState("否");
						ppm.setMachinePlanId(pls.getId());
						ppm.setAxisName(pls.getAxisName());
						ppm.setFlag("否");
						ppm.setCreateBy(pls.getCreateBy());
						ppservice.save(ppm);
					}
				}
			}
		}
	}
	
	*//**
	 * @TODO: 取机台的生产速度
	 * @author: zhaichunlei
	 & @DATE : 2017年8月10日
	 * @param macId
	 * @param proGgxh
	 * @return
	 * @throws Exception 
	 *//*
	private BigDecimal getMachineSpeed(Integer macId,String proGgxh) throws Exception{
		MauMachineSpeed mms = mauMachineSpeedManageDAO.getMachineByMachineAndGgxh(macId,proGgxh);
		if(mms == null)
			throw new Exception("机台ID("+macId+")与产品规格("+proGgxh+")对应的机台速度不存在");
		
		return new BigDecimal(mms.getSpeed());
	}
	
	*//**
	 * @TODO: 取指定工序的机台
	 * @author: zhaichunlei
	 & @DATE : 2017年8月10日
	 * @param rlst
	 * @param seqCode
	 * @return
	 *//*
	private String getSpecifySeq_macCode(List<PlaProductOrderSeqHourse> polst,String seqCode,Integer workDay){
		if(seqCode == null) return null;
		
		String sc = null;
		Integer wd = null;
		//这里不判断异常。 工序与工作日不允许为空
		for(PlaProductOrderSeqHourse posh : polst){
			sc  = posh.getSeqCode();
			wd = posh.getWorkDay();
			if(seqCode.equals(sc) && workDay.intValue() == wd){
				return posh.getMacCode();
			}
		}
		return null;
	}
	
	*//**
	 * @TODO:取下一工序编码
	 * @author: zhaichunlei
	 & @DATE : 2017年8月10日
	 * @param rlst
	 * @param seqCode
	 * @return
	 * @throws Exception 
	 *//*
	private String getNextSeqCode(List<CraProSeqRelation> rlst,String seqCode) throws Exception{
		int idx = -1;
		for(int i = 0; i < rlst.size() ; i ++ ){
			CraProSeqRelation c = rlst.get(i);
			String sq = c.getSeqCode();
			if(seqCode.equals(sq)){
				idx = i;
				break;
			}
		}
		
		if(idx == -1)
			throw new Exception("工序编码异常，未匹配产品工艺关联");
		
		if(idx == rlst.size() - 1)
			return null;
		return rlst.get(idx+1).getSeqCode();
	}
	
	*//**
	 * @TODO: 取上一工序编码
	 * @author: zhaichunlei
	 & @DATE : 2017年8月9日
	 * @param rlst
	 * @param seqCode
	 * @return
	 * @throws Exception 
	 *//*
	private String getUpSeqCode(List<CraProSeqRelation> rlst,String seqCode) throws Exception{
		int idx = -1;
		for(int i = 0; i < rlst.size() ; i ++ ){
			CraProSeqRelation c = rlst.get(i);
			String sq = c.getSeqCode();
			if(seqCode.equals(sq)){
				idx = i;
				break;
			}
		}
		
		if(idx == -1)
			throw new Exception("工序编码异常，未匹配产品工艺关联");
		
		if(idx == 0)
			return null;
		if(idx > 0)
			return rlst.get(idx-1).getSeqCode();
		
		return null;
	}
	
	*//**
	 * @TODO: 从一套工序列表中，取指定工序对应的序号
	 * @author: zhaichunlei
	 & @DATE : 2017年8月9日
	 * @param rlst
	 * @param seqCode
	 * @return
	 *//*
	private Integer getSeqSort(List<CraProSeqRelation> rlst,String seqCode){
		for(CraProSeqRelation c : rlst){
			String sq = c.getSeqCode();
			if(seqCode.equals(sq))
				return c.getSeqSort();
		}
		return null;
	} 
	
	private boolean getIsHaveScheduling(List<PlaProductOrder> plaProductOrderList) {
		List<Integer> sclIdList = new ArrayList<Integer>();//生产令id集合
		for(PlaProductOrder o : plaProductOrderList){
			sclIdList.add(o.getId());
		}
		String hql = " from PlaWeekPlan  where productOrderId in (:ids) ";
		@SuppressWarnings("unchecked")
		List<PlaWeekPlan> planLists = plaWeekPlanManageDAO.getHibernateTemplate().findByNamedParam(hql, "ids", sclIdList);
		if(planLists!=null&&planLists.size()>0){
//			throw new RuntimeException("");
			//这里其实不该抛出异常，而应该直接结束。
			return true;
		}else{
			return false;
		}
	}
	
	*//**
	 * 计算周计划集合时间
	 * @param maxCountDay
	 * @param laSi
	 * @return
	 *//*
	private List<Double> getcalc(Integer maxCountDay, Double laSi) {
		List<Double> list = new ArrayList<Double>();
//		int first = 0;//第一天生产工时，多一点
		Double avg = laSi/maxCountDay;
		for(int i = 0 ;i < maxCountDay;i++){
			list.add(avg);
		}
		//重新计算每天的工时集合
		list.clear();
		String avgInt = avg.toString().split("[.]")[0];//平均值的整数部分
		Integer avgIntValue = Integer.parseInt(avgInt);
		Integer all = avgIntValue*(maxCountDay-1);//求得n-1天的工时总和 
		Double firstHours = laSi - all ;//第一天的工时
		Double avgDouble = Double.valueOf(avgIntValue.toString());
		list.add(firstHours);
		for(int i = 0;i < maxCountDay - 1;i ++){
			list.add(avgDouble);
		}
		return list;
	}

	//	//1.取得工艺路线，判断该生产令需要的工序
//	Integer craRouteId = order.getCra_route_id();
//	CraRoute route = craRouteManageDAO.get(craRouteId);
//	String routeCode = route.getRouteCode();
//	
//	List<CraRouteSeq> craRouteSeqList =  craRouteSeqManageDAO.getHibernateTemplate().find(" from CraRouteSeq where routeCode = ? order by sort",routeCode);
//	//得到这个工艺路线集合，是按顺序排的，保证了工序的先后顺序
//	List<String> seqNameList = new ArrayList<String>();
//	for(CraRouteSeq seq : craRouteSeqList){
//		String seq_name = seq.getSeqName();
//		seqNameList.add(seq_name);
//	}
//	//TODO 2.根据工序，找到空闲的机台（目前只考虑数据库中，直接空闲的机台）
//	Map<String,List<MauMachine>> seq_mac = new HashMap<String,List<MauMachine>>();
//	List<MauMachine> mapValues = new ArrayList<MauMachine>();
//	//可用机台(暂时是全部机台，后要过滤成可用机台)
//	List<MauMachine> macList = mauMachineManageDAO.getHibernateTemplate().findByNamedParam(" from MauMachine where macType in (:seqNames) "+PRIXF, "seqNames", seqNameList);
//	for(String seqName : seqNameList){
//		mapValues.clear();
//		for(MauMachine mac:macList){
//			if(mac.getMacName().equals(seqName)){
//				mapValues.add(mac);
//			}
//		}
//		seq_mac.put(seqName, mapValues);//这里的seqName是工序明细的施工名称，不可能重复的
//	}
	*//**
	 * 根据日期天数，返回排产的日期集合
	 * @param maxCountDay
	 * @return
	 *//*
	private List<Timestamp> getPlanDates(Date date ,Integer maxCountDay) {
		List<Timestamp> list = new ArrayList<Timestamp>();
		long mils = 86400000;//每天的毫秒数
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		for(int i = 0;i < maxCountDay;i++){
			c.setTimeInMillis(c.getTimeInMillis()+mils);
			Timestamp d = new Timestamp(c.getTimeInMillis());
			list.add(d);
		}
		return list;
	}
	

	

	public static void main(String[] args) {
		
		SchedulingManageService d = new SchedulingManageService();
		System.out.println(d.getcalc(3,1.1));;
		
		System.out.println(0.3666666666666667*3);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		
//		//TODO汉字转换会有问题
//		String s = "PL1";
//		StringBuffer str = new StringBuffer("");
//		int i16n = 0; 
//		
//		for (int i=0;i<s.length();i++) 
//		{ 
//		int ch = (int)s.charAt(i); 
////		System.out.println("aa:"+ch);
//		String s4 = Integer.toHexString(ch); 
//		str.append(s4);
//		} 
//		i16n = str.toString().length();
//		String i16nStr = str.toString();
//		System.out.println("十六进制："+str.toString());
//		/////////////////////////////////////////////
//		str = new StringBuffer("");
//		for(int i = 0 ;i < s.length();i++){
//			int ch = (int)s.charAt(i); 
//			String ss = Integer.toBinaryString(ch);
//			str.append(ss);
//		}
//		System.out.println("二进制："+str.toString());
//		
//		///////////////////////////
//		
//		str = new StringBuffer("");
//		for(int i = 0 ;i < s.length();i++){
//			int ch = (int)s.charAt(i); 
//			String ss = Integer.toOctalString(ch);
//			str.append(ss);
//		}
//		System.out.println("八进制："+str.toString());
//		
//		
////		System.out.println("16进制最大值为"+Long.toHexString(9223372036854775807L) );;
////		long x = 0x504c3030303030303031l;
////		long x = 0x504c30303030303030303031l;
//		
//		byte[] b = new byte[]{5,0,4,'c',3,0,3,0,3,0,3,0,3,0,3,0,3,0,3,0,3,0,3,1};
//		b = new byte[i16n];
//		for(int i = 0;i < i16n;i++){
//			char at = i16nStr.charAt(i);
//			if(at>=0&&at<=9){
//				b[i] = (byte)Integer.parseInt(at+"");
//			}else{
//				b[i] = (byte)at;
//			}
//		}
//		//将字节数组转换成为字符串
//		StringBuffer sb = new StringBuffer("");
//		for(int i = 0 ;i < b.length;i++){
//			if(b[i]>=0&&b[i]<=9){
//				sb.append(b[i]);
//			}else{
//				sb.append((char)b[i]);
//			}
//			
//		}
//		
//		System.out.println("转换后的数字"+sb.toString());
//		
//		
//		String test = sb.toString();
//		if (test == null || test.equals("")) {  
//        }  
//		test = test.replace(" ", "");  
//        byte[] baKeyword = new byte[test.length() / 2];  
//        for (int i = 0; i < baKeyword.length; i++) {  
//            try {  
//                baKeyword[i] = (byte) (0xff & Integer.parseInt(  
//                		test.substring(i * 2, i * 2 + 2), 16));  
//            } catch (Exception e) {  
//                e.printStackTrace();  
//            }  
//        }  
//        try {  
//        	test = new String(baKeyword, "GBK");  
//            new String();  
//            System.out.println(test);
//        } catch (Exception e1) {  
//            e1.printStackTrace();  
//        }  
	}

	*//**
	 * 根据idList查询明细
	 * @param idList
	 * @return
	 *//*public List<SellContractDetail> findByIdsForContractDetail(
			List<Integer> idList) {
		String hql = " from SellContractDetail where id in (:ids) ";
		List<SellContractDetail> list = sellContractDetailManageDAO.getHibernateTemplate().findByNamedParam("hql", "ids", idList);
		
		return list;
	}
	
	public void updateBatchSellContractDetail(List<SellContractDetail> list ){
		sellContractDetailManageDAO.updateBatch(list);

	*//**
	 * 数据库迁移，序列值变更sql生成方法
	 *//*
	public void test() {
		List<Map<String, Object>> list = jdbcTemplate.queryForList(" select tablename from pg_tables where schemaname=? ","public");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//表名与最大id的键值对
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(Map<String,Object> mm : list){
			String tablename = mm.get("tablename").toString();
			String sql = "select max(id) from "+tablename;
			Map<String,Object> maxIdMap = jdbcTemplate.queryForMap(sql);
			Integer maxId = null;
			if(maxIdMap.get("max")==null){//将序列值设置为1
				System.out.println("表名"+tablename);
				maxId = 0;
			}else{
				maxId = Integer.parseInt(maxIdMap.get("max").toString());
				System.out.println(tablename+":"+maxId+";");
			}
			map.put(tablename, maxId+1);
		}
		//查询我们自定义的序列
		String sql = "select relname from pg_class where relname like 'seq%'";
		list = jdbcTemplate.queryForList(sql);
		for(String key : map.keySet()){
			String seqKey = "seq_"+key;
			for(Map<String,Object> m : list){
				String seqName = m.get("relname").toString();
				if(seqKey.equals(seqName)){//该表有这个序列
					resultMap.put(seqName, map.get(key));
				}
			}
		}
		//alter sequence seq_test restart with 7
		System.out.println(resultMap.size());
		StringBuffer sb = new StringBuffer();
		for(String key : resultMap.keySet()){
			sb.append(" alter sequence "+key+" restart with "+resultMap.get(key)+" ;\n");
		}
		System.out.println(sb.toString());
	}*/
}
