package com.css.business.web.subsysmanu.mauManage.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.JMSException;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauCallForkliftRecord;
import com.css.business.web.subsysmanu.bean.MauMachine;
import com.css.business.web.subsysmanu.bean.MauMachineSpeed;
import com.css.business.web.subsysmanu.bean.MauSpeedQuotiety;
import com.css.business.web.subsysmanu.mauManage.dao.MauCallForkliftRecordManageDAO;
import com.css.business.web.subsysmanu.mauManage.dao.MauMachineManageDAO;
import com.css.business.web.subsysmanu.mauManage.dao.MauMachineSpeedManageDAO;
import com.css.business.web.subsysmanu.vo.MachineCraProGgxhSpeedVO;
import com.css.business.web.sysManage.bean.SysCommdic;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.business.web.sysManage.dao.SysConfigManageDAO;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauMachineManageService")
public class MauMachineManageService extends
		BaseEntityManageImpl<MauMachine, MauMachineManageDAO> {
	@Resource(name = "mauMachineManageDAO")
	// @Autowired
	private MauMachineManageDAO dao;
	
	@Resource(name="mauMachineSpeedManageDAO")
	private MauMachineSpeedManageDAO mauMachineSpeedManageDAO;
	
	@Resource(name="sysConfigManageDAO")
	private SysConfigManageDAO sysConfigManageDAO;
	
	@Resource(name = "mauCallForkliftRecordManageDAO")
	private MauCallForkliftRecordManageDAO mauCallDao;

	@Override
	public MauMachineManageDAO getEntityDaoInf() {
		return dao;
	}

	/**
	 * method：查询MauMachine表
	 * 
	 * @return 返回一个map对象
	 * @author:曾斌
	 * @date:2017-06-12
	 */
	public Page getMauMachineManageService(Page page,String macCode) {
		Page pageQuery=null;
		StringBuilder hql = new StringBuilder("from MauMachine  where 1=1 ");
		try {
			
			if(null!=macCode&&!"".equals(macCode)){
				macCode =macCode.trim();
				hql.append(" and macCode like '%"+macCode+"%' ");
			}
			hql.append("   order by id  ");
			 pageQuery =dao.pageQuery(hql.toString(), page.getPageNo(), page.getPagesize());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return pageQuery;
		
	}
	/**
	 * 获取所有机台信息
	 * @return
	 */
	public List<MauMachine> getAllMacInfoService(){
		String hql = "from MauMachine";
		@SuppressWarnings("unchecked")
		List<MauMachine> list = dao.createQuery(hql).list();
		return list;
	}

	/**
	 * @method：根据id删除MauMachine表中的一条或者多条记录
	 * @param: ids存放id的数组
	 * @return void
	 * @author:曾斌
	 * @date:2017-06-12
	 */
	public void deleteMauMachineService(int[] ids) {
		String hql = "delete MauMachine where id=?";
		dao.deleteByIdsDao(ids, hql);
	}

	/**
	 * @method:跟新 MauMachine表中的一条记录
	 * @date:217-06-06
	 */
	public void updateMauMachineService(MauMachine mauMachine) {
		dao.updateMauMachineDao(mauMachine);
	}

	/**
	 * @method: 根据macCode 查询MauMachine表中的记录
	 * @author:曾斌
	 * @date:2017-06-12
	 */
	public HashMap<String, Object> getSerchByMacCodeService(String pageStr,
			String pagesizeStr, String macCode) {
		String hql = "from MauMachine where macCode=? order by id ";
		HashMap<String, Object> map1 = getPagingQueryToolService(hql, pageStr,
				pagesizeStr, macCode);
		return map1;
	}

	/**
	 * @method: 新增CraSeq表中的一条记录
	 * @author:曾斌
	 * @date:2017-06-06
	 */
	public void addMauMachineService(MauMachine mauMachine) {
		dao.addMauMachineDao(mauMachine);
	}

	/**
	 * @method: 查询SysCommedic表中的工序列表
	 * @author:曾斌
	 * @date:2017-06-13
	 */
	public List<?> getSerchMacTypeService() {
		String hql = "from SysCommdic where clas=? order by id ";
		List list = dao.getListDao(hql, "工序列表");
		return list;
	}

	/**
	 * @method: 查询SysCommedic表中的工序列表
	 * @author:曾斌
	 * @date:2017-06-13
	 */
	public List<?> getSerchMacPrioService() {
		String hql = "from SysCommdic where clas=? order by id ";
		List list = dao.getListDao(hql, "优先级");
		return list;
	}

	/**
	 * @method:封装分页查询的一个重要方法
	 * @param hql
	 *            需要查询的一个hql语句
	 * @param pageStr
	 *            当前第几页，从liguerui获取得到的
	 * @param pagesizeStr
	 *            每页记录数，从liguerui获取得到的
	 * @param values
	 *            需要的参数，记住hql语句的顺序，参考getPagingQueryToolDao;
	 * @return HashMap<String,Object> author:曾斌 date:2017-6-12
	 */
	public HashMap<String, Object> getPagingQueryToolService(String hql,
			String pageStr, String pagesizeStr, final Object... values) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<?> list;
		double total;
		int page = 1;
		int pagesize = 10;
		if (pageStr != null) {
			page = Integer.parseInt(pageStr);
		}
		if (pagesizeStr != null) {
			pagesize = Integer.parseInt(pagesizeStr);
		}
		total = dao.getPagingQueryToolDao(hql, true, page, pagesize, values)
				.size();
		list = dao.getPagingQueryToolDao(hql, false, page, pagesize, values);
		map.put("Total", total);
		map.put("Rows", list);
		return map;
	}

	public List<MauMachine> getMauMachine(Integer mac_id) {
		String hql = " from MauMachine where id = ? and macState != '故障' ";
		@SuppressWarnings("unchecked")
		List<MauMachine> list = dao.createQuery(hql, mac_id).list();
		return list;
	}

	/**
	 * 得到未排产的机台
	 * 
	 * @return
	 * @author JS
	 */
	public List<MauMachine> getPlaMauMachine(String macType) {
		String hql = " from MauMachine where isFlag = '0' and macType = ? ";
		@SuppressWarnings("unchecked")
		List<MauMachine> list = dao.createQuery(hql, macType).list();
		return list;
	}

	public void updateMauMachine(MauMachine mauMachine) throws Exception {
		mauMachine.setIsFlag("1");
		System.out.println("执行Update.............");
		dao.updateByCon(mauMachine, false);
	}

	public Page getMauMachineGrid(Page p,String macCode) {
		String hql = " from MauMachine  ";
		if(macCode !=null && !"".equals(macCode)){
			hql += " where macCode like'%"+macCode+"%' ";
			p.setPageNo(1);
		}
		hql += " order by id ";
		
		Page page = dao.pageQuery(hql, p.getPageNo(), p.getPagesize());
		return page;
	}

	public void updateMachineState(MauMachine machine) throws Exception {
		dao.updateByCon(machine, false);
	}
	
	public void addMachine(MauMachine machine){
		dao.save(machine);
	}
	
	public void delMahcine(MauMachine machine){
		dao.remove(machine);
	}
	
	public MauMachine getMachine(Integer id){
		String hql = " from MauMachine where id  =? ";
		MauMachine  machine= (MauMachine) dao.createQuery(hql, id).list().get(0);
		return machine;
	}
	
	@SuppressWarnings("unchecked")
	public List<MauSpeedQuotiety> getQuotietyBymacCode(String macCode){
		String hql = " from MauSpeedQuotiety where macCode = ? ";
		List<MauSpeedQuotiety> list = dao.createQuery(hql, macCode).list();
		return list;
	}

	@SuppressWarnings("rawtypes")
	public Map[] getMachineState() {
		String hql = " from SysCommdic where clas = '机台状态' ";
		@SuppressWarnings("unchecked")
		List<SysCommdic> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					map.put("text", list.get(i).getValue());
				} else {
					map.put("key", i + "");
				}
			}
			str[i] = map;
		}
		return str;
	}

	/**   
	 * @Description: 通过机器编码去查机器  
	 * @param macCode
	 * @return         
	 */ 
	public MauMachine queryMachineByCode(String macCode) {
		String hql="from MauMachine where macCode ='"+macCode+"'";
		Query createQuery = dao.createQuery(hql);
		List<MauMachine> list = createQuery.list();
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * @TODO: 计算指定工序、规格，生产指定长度与轴数的工时. 理论值: 未考虑损耗的加长料
	 * @author: zhaichunlei
	 & @DATE : 2017年8月3日
	 * @param len 成品线的总长度
	 * @param amount 成品轴数
	 * @param seqCode 工序编码
	 * @param proGgxh 产品规格型号
	 * @param seqHalfProNum 当前工序每轴成品的半成品数
	 * @return
	 * @throws Exception 
	 */
	public BigDecimal calcGongShi_LL(Integer len,Integer amount,String seqCode,String proGgxh,Integer seqHalfProNum) throws Exception{
		List<MauMachineSpeed> mlst = mauMachineSpeedManageDAO.getMachineBySeqAndGgxh(seqCode,proGgxh);
		if(mlst == null || mlst.isEmpty()){
			throw new Exception("请配置机台的规格型号与生产速度参数。工序编码（"+seqCode+"）,规格型号（"+proGgxh+"）不存在匹配的机台或未配置");
		}
		BigDecimal speed = new BigDecimal(0); //生产速度
		BigDecimal rt = new BigDecimal(0);  //准备时间
		BigDecimal ut = new BigDecimal(0); //上盘时间
		BigDecimal dt = new BigDecimal(0); //落轴时间
		//Integer core  = 0;
		int count = mlst.size();
		
	/*	String gs = sysConfigManageDAO.getValueByItem("一天工时数");
		if(gs == null || gs.trim().length() == 0)
			throw new Exception("请在SYSCONFIG配置【一天工时数】参数");*/
		
		//一天工时上限
		//int limit = Integer.parseInt(gs);
		
		//算法，速度可以合计。但准备时间按机台数平均
		for(MauMachineSpeed mms : mlst){
			speed = mms.getSpeed() == null ? speed : speed.add(new BigDecimal(mms.getSpeed()));
			rt = mms.getReadyTime() == null ? rt : rt.add(new BigDecimal(mms.getReadyTime()));
			ut = mms.getUpTime() == null ? ut : ut.add(new BigDecimal(mms.getUpTime()));
			dt = mms.getDownTime() == null ? dt : dt.add(new BigDecimal(mms.getDownTime()));
		}
		
		
		//工时计算,总的生产能力与准备平均时间。不足够准确
		BigDecimal gsb = new BigDecimal(len).multiply(new BigDecimal(amount)).multiply(new BigDecimal(seqHalfProNum)).divide(speed).add(
								rt.add(ut).add(dt).multiply(new BigDecimal(seqHalfProNum)).divide(new BigDecimal(count*count)));
		
		return gsb;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map[] getMacCode(){
		String hql = " from MauMachine ";
		List<MauMachine> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					map.put("text", list.get(i).getMacCode());
				} else {
					map.put("id", i + "");
				}
			}
			str[i] = map;
		}
		return str;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map[] getMacName(){
		String hql = "  SELECT DISTINCT macName from MauMachine ";
		List<Object> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					map.put("text", list.get(i).toString());
				} else {
					map.put("id", i + "");
				}
			}
			str[i] = map;
		}
		return str;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map[] getSeqName(){
		String hql = "  SELECT DISTINCT seqCode from CraSeq ";
		List<Object> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					map.put("text", list.get(i).toString());
				} else {
					map.put("id", i + "");
				}
			}
			str[i] = map;
		}
		return str;
	}
	
	/**
	 * @TODO: 计算指定的一台机台，加工指定的一轴时，所需工时
	 * @author: zhaichunlei
	 & @DATE : 2017年8月8日
	 * @param axixName
	 * @param machineId
	 * @return
	 * @throws Exception 
	 */
	public BigDecimal calcGongShi_axis_machine(String axisName,Integer machineId,String seqCode) throws Exception{
		String sql = "";
		Object o = dao.exeFunction("calc_gs_by_machine_axis_name", axisName,machineId,seqCode);
		if(o == null)
			return null;
		else 
			return new BigDecimal(o.toString()); 
	}
	
	/**
	 * 系统上线时，存在机台与产品部分参数不能及时录入导致系统运行报错。  这里暂使用旧数据初始化数据：
	 *    机台生产速度 
	 * @param user
	 * @throws Exception 
	 */
	public void initMachineSpeed(SysUser user) throws Exception{
		//查询出所有机台与所有产品，并且在生产速度表与理论BOM中不存在速度的。
		List<MachineCraProGgxhSpeedVO> lst =  dao.queryMachineSpeed_notExists();
		//if(true) throw new Exception("sss");
		if(lst != null && lst.size() > 0){
			for(MachineCraProGgxhSpeedVO v : lst){
				MauMachineSpeed ms = new MauMachineSpeed();
				ms.setMachineId(v.getId());
				ms.setProGgxh(v.getProggxh());
				
				ms.setCreateBy(user.getAccount());
				ms.setCreateDate(new Date(System.currentTimeMillis()));
				ms.setReadyTime(0.3d);
				ms.setDownTime(0.3d);
				ms.setUpTime(0.3d);
				ms.setSpeed(50d);
				mauMachineSpeedManageDAO.save(ms);
			}
		}
	}

	/**   
	 * @Description: 查询机台各种状态个数 
	 * @param string
	 * @return         
	 */ 
	public Long getCount(String state) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from MauMachine ";
		if (state != null && state != "") {
			hql  += " where macState ='"+state+"'";
		}
		Long result = (Long) dao.createQuery(hql).uniqueResult();
		return result;
	}
	
	/**
	 * 获取所有机台状态的数量
	 * @return
	 */
	public List<Object[]> getMacStateNum(){
		String hql = "select macState,COUNT(macState) from MauMachine group by macState";
		@SuppressWarnings("unchecked")
		List<Object[]> obj = dao.createQuery(hql).list();
		return obj;
	}

	/**   
	 * @Description: TODO(根据工序获取对应机台信息)   
	 * @param seqCode
	 * @return         
	 */ 
	public List<MauMachine> getMacBySeq(String seqCode) {
		String hql = "from MauMachine where 1 = 0 ";
		if(seqCode != "" && seqCode != null){
			hql += " or seqCode = '"+seqCode+"'";
		}
		List<MauMachine> listQuery = dao.listQuery(hql);
		return listQuery;
	}
	
	
	/**
	 * 获取所有机台状态和数量
	 * @return
	 */
	public Map<Object,Object> getMacAllStateNum(){
		String hql = "select macState,COUNT(macState) from MauMachine group by macState";
		@SuppressWarnings("unchecked")
		List<Object[]> obj = dao.createQuery(hql).list();
		Map<Object,Object> map = new HashMap<>();
		
		for(int i=0;i<obj.size();i++){
			if (obj.get(i)[0]==null) {
				continue;
			}
			map.put(obj.get(i)[0], obj.get(i)[1]);
		}
		return map;
	}
	
	
	public HashMap<String, Object> testCallFork() {
		List<MauCallForkliftRecord> call = new ArrayList<MauCallForkliftRecord>();
		MauCallForkliftRecord callfork = new MauCallForkliftRecord();
		SimpleDateFormat sf = new SimpleDateFormat("MM-dd HH:mm");
		callfork.setCallTime(sf.format(new Date()));
		callfork.setStatus(MauCallForkliftRecord.NO_status);
		callfork.setCallAddress("车间:RF--工单完成,成品需移至成品待检区!");
		callfork.setId(10000);
		//dao.save(callfork);
		call.add(callfork);
		try {
			mauCallDao.sendMessageTo(call);
			return JsonWrapper.successWrapper(call, "呼叫成功");
		} catch (JMSException e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(call, "呼叫失败");
		}
	}
	
	
	
}
