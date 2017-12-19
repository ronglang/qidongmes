package com.css.business.web.subsyssell.sellManage.service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.craManage.dao.CraCraftProductManageDAO;
import com.css.business.web.subsysplan.bean.PlaCourse;
import com.css.business.web.subsysplan.bean.PlaCourseAxis;
import com.css.business.web.subsysplan.plaManage.dao.PlaCourseAxisManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaCourseManageDAO;
import com.css.business.web.subsyssell.bean.SellSalesOrder;
import com.css.business.web.subsyssell.bean.SellSalesOrderDetails;
import com.css.business.web.subsyssell.sellManage.dao.SellSalesOrderDetailsManageDAO;
import com.css.business.web.subsyssell.sellManage.dao.SellSalesOrderManageDAO;
import com.css.business.web.subsysstore.storeManage.dao.StoreLockControlManageDAO;
import com.css.business.web.subsysstore.storeManage.dao.StoreProFreeStockManageDAO;
import com.css.common.util.DateUtil;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service("sellSalesOrderManageService")
public class SellSalesOrderManageService extends
		BaseEntityManageImpl<SellSalesOrder, SellSalesOrderManageDAO> {

	@Autowired
	private SellSalesOrderManageDAO sellSalesOrderManageDAO;

	@Autowired
	private SellSalesOrderDetailsManageDAO sellSalesOrderDetailsManageDAO;

	@Autowired
	private CraCraftProductManageDAO craCraftProductManageDAO;

	@Autowired
	private StoreLockControlManageDAO storeLockControlManageDAO;

	@Autowired
	private StoreProFreeStockManageDAO storeProFreeStockManageDAO;
	@Autowired
	private PlaCourseManageDAO plaCourseManageDAO;
	@Autowired
	private PlaCourseAxisManageDAO plaCourseAxisManageDAO;

	@Override
	public SellSalesOrderManageDAO getEntityDaoInf() {
		return sellSalesOrderManageDAO;
	}

	/**
	 * 根据订单编号，返回工艺流程图集合
	 * 
	 * @param orderCode
	 * @return
	 *//*
	public HashMap<String, Object> getVOListFromOrderCode(String orderCode) {
		return null;
	}

	*//**
	 * 根据产品规格型号，
	 * 
	 * @param proGgxh
	 * @return
	 *//*
	public HashMap<String, Object> getVOListByProGgxh(String proGgxh) {
		return null;
	}

	l;// 命名有问题(原来的合同对象，现在的生产通知单)
		// SellContractDetail tempSonBean = null;
		SellContractPlanBatch tempSonBean = null;
		*//**
		 * 部分内容来源于合同，等ERP有了之后，该方法需要传入合同对象
		 *//**
	 * 根据订单编号分解到生产通知单
	 * 
	 * @param orderCode
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	public boolean decomposeOrder(String orderCode, boolean booLock) {

		if (orderCode == null || orderCode.isEmpty()) {
			throw new RuntimeException("订单编号不能为空");
		}
		String hsql = " from SellSalesOrder where orderCode = ? ";
		List<SellSalesOrder> mainList = sellSalesOrderManageDAO
				.getHibernateTemplate().find(hsql, orderCode);
		if (mainList == null || mainList.isEmpty()) {
			throw new RuntimeException("该订单不存在");
		}
		if (mainList.size() > 1) {
			throw new RuntimeException("存在" + mainList.size() + "个订单编号为"
					+ orderCode + "相同的订单");
		}
		// 查询对应的订单明细记录
		String hql = " from SellSalesOrderDetails where orderCode = ? ";
		List<SellSalesOrderDetails> sonList = sellSalesOrderDetailsManageDAO
				.getHibernateTemplate().find(hql, orderCode);

		// 锁控产品库存，增加一条锁控记录
		List<SellSalesOrderDetails> resultSonList = new ArrayList<SellSalesOrderDetails>();// 需要排通知单的明细内容
		List<StoreLockControl> lockList = new ArrayList<StoreLockControl>();
		List<StoreProFreeStock> freeStockList = new ArrayList<StoreProFreeStock>();// TODO
																					// 查询视图

		// TODO 测试数据，正式上线需要删除
		StoreProFreeStock dd = new StoreProFreeStock();
		dd.setAls(3000);
		dd.setAxisNumber(70);
		dd.setProColor("红色");
		dd.setProGgxh("11KV YJWS53-150");
		dd.setStoreMrecordId(25);
		freeStockList.add(dd);
		freeStockList.clear();
		hql = " from StoreProFreeStock ";
		freeStockList = storeProFreeStockManageDAO.getHibernateTemplate().find(
				hql);
		// 得到需要往下排产的，得到需要锁控的记录
		funcDecomp(orderCode, booLock, sonList, resultSonList, lockList,
				freeStockList);
		// 保存锁控记录
		storeLockControlManageDAO.saveOrUpdateBatch(lockList);
		return decomposeOrderSon(resultSonList, mainList.get(0));
	}

	*//**
	 * 
	 * @param orderCode
	 *            订单编号
	 * @param booLock
	 *            是否需要锁控
	 * @param sonList
	 *            需要交付的产品内容
	 * @param resultSonList
	 *            需要生产的产品内容
	 * @param lockList
	 *            锁控记录
	 * @param freeStockList
	 *            空闲库存
	 *//*
	private void funcDecomp(String orderCode, boolean booLock,
			List<SellSalesOrderDetails> sonList,
			List<SellSalesOrderDetails> resultSonList,
			List<StoreLockControl> lockList,
			List<StoreProFreeStock> freeStockList) {
		if (booLock) {
			for (SellSalesOrderDetails orderSon : sonList) {
				String proGgxh = orderSon.getProGgxh();
				String proColor = orderSon.getProColor();
				Integer als = orderSon.getAls() == null ? 0 : orderSon.getAls();
				Integer axisNumber = orderSon.getAxisNumber() == null ? 0
						: orderSon.getAxisNumber();
				boolean boo = false;
				for (StoreProFreeStock bean : freeStockList) {
					Integer axisNumber2 = bean.getAxisNumber() == null ? 0
							: bean.getAxisNumber();// 空闲轴数
					String ggxh = bean.getProGgxh();
					String color = bean.getProColor();
					Integer als2 = bean.getAls() == null ? 0 : bean.getAls();

					if (proGgxh.equals(ggxh) && proColor.equals(color)
							&& als.equals(als2)) {// 各项匹配上，可以锁控
						boo = true;
						if (axisNumber2 <= 0) {// 应该匹配上了再判断库存，无法锁控
							resultSonList.add(orderSon);
							break;
						}
						if (axisNumber - axisNumber2 > 0) {// 生产数量
															// >空闲库存数量才需要向下生产
							orderSon.setAxisNumber(axisNumber - axisNumber2);
							resultSonList.add(orderSon);
							// 锁控记录
							setValue(orderCode, lockList, orderSon, proGgxh,
									als, axisNumber2);
							bean.setAxisNumber(0);
						} else {// 生产数量<=小于空闲库存数量，不需要生产，只需要添加锁控记录
							setValue(orderCode, lockList, orderSon, proGgxh,
									als, axisNumber);
							bean.setAxisNumber(axisNumber2 - axisNumber);
						}

					}
				}
				if (!boo) {// 没有对应的库存信息，那么久需要排产
					resultSonList.add(orderSon);
				}

			}
		} else {
			for (SellSalesOrderDetails bean : sonList) {
				resultSonList.add(bean);
			}
		}
	}

	*//**
	 * 封装锁控明细
	 * 
	 * @param orderCode
	 * @param lockList
	 * @param orderSon
	 * @param proGgxh
	 * @param als
	 * @param axisNumber
	 *//*
	private void setValue(String orderCode, List<StoreLockControl> lockList,
			SellSalesOrderDetails orderSon, String proGgxh, Integer als,
			Integer axisNumber) {
		StoreLockControl storeLockControl = new StoreLockControl();
		storeLockControl.setAls(als);
		storeLockControl.setAxisNumber(axisNumber);
		storeLockControl.setCreateBy("创建人");
		storeLockControl.setCreateTime(new Date());
		storeLockControl.setLockControlTime(new Date());// 锁控操作时间
		storeLockControl.setLockEndTime(orderSon.getDeliveryDate());// 就是明细的交货日期
		storeLockControl.setOrderCode(orderCode);
		storeLockControl.setOrderSonId(orderSon.getId());
		storeLockControl.setProducNoticeCode(null);// 暂时不管，可以通过明细id来关联
		storeLockControl.setProGgxh(proGgxh);
		storeLockControl.setProName(orderSon.getProGgxh());
		storeLockControl.setAmount(storeLockControl.getAxisNumber()
				* storeLockControl.getAls());
		storeLockControl.setProColor(orderSon.getProColor());
		lockList.add(storeLockControl);
	}*/

	/**
	 * 根据订单明细集合分解为生产通知单（个人认为该方法并不合理，这个是王老师说一条订单明细生成一份生产通知单，还要做一个备选方案）
	 * 
	 * @param sonList
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	// @Transactional(readOnly=false)
	public boolean decomposeOrderSon(List<SellSalesOrderDetails> sonList,
			SellSalesOrder mainBean) {
		Date sc_date = new Date();// 合同签订日期
		if (sonList == null || sonList.isEmpty()) {// 该订单没有要生产的产品明细
			return false;
		}
		// List<SellContract> resultMainList = new ArrayList<SellContract>();
		List<SellContractPlanBatch> resultSonList = new ArrayList<SellContractPlanBatch>();

		SellContract tempMainBean = nul
		for (SellSalesOrderDetails bean : sonList) {// 当前认为一条订单明细，生成一条生产通知单记录,同样生成为一条明细记录
			tempMainBean = new SellContract();
			tempMainBean.setCreateDate(new Date());
			tempMainBean.setCreateBy("york");
			tempMainBean.setScCode(mainBean.getContractCode());// 需要合同对象建立，该方法需要传入一个合同对象
			tempMainBean.setScContent("合同内容");
			tempMainBean.setRemark("备注信息自动生成");
			tempMainBean.setScDate(sc_date);// 合同签订日期
			tempMainBean.setScMoney(null);// 合同金额，该字段应该被删除，此处不关注合同金额
			tempMainBean.setFirstParty("甲方");// 来源于合同，当前为系统自动生成值
			tempMainBean.setSecondParty("乙方");// 来源于合同，当前为系统自动生成
			tempMainBean.setAgentBy(mainBean.getOrderEntryClerk());// 订单经办人，就是订单录入人员
			tempMainBean.setCusCode("客户编码");// 客户编码
			tempMainBean.setDeliveDate(null);// 该字段在数据库中备注将要删除
			tempMainBean.setScState("合同状态");// 合同状态
			tempMainBean.setCusName("客户名称");// 客户名称
			tempMainBean.setOrderCode(mainBean.getOrderCode());
			tempMainBean.setContractCode("生产通知单编号");
			tempMainBean.setContractType("正常单");
			// resultMainList.add(tempMainBean);

			sellContractManageDAO.save(tempMainBean);
			// 生成明细
			tempSonBean = new SellContractPlanBatch();
			tempSonBean.setCreateDate(new Date());
			tempSonBean.setCreateBy("york");
			tempSonBean.setScCode(mainBean.getContractCode());// 合同编号
			tempSonBean.setEndDate(new Date());
			tempSonBean.setReqAmount(bean.getAxisNumber());// 轴数/扎数
			tempSonBean.setReqUnit(bean.getUnit());
			tempSonBean.setBatCode(null);
			tempSonBean.setDeliveDate(bean.getDeliveryDate());// 交货日期
			tempSonBean.setPbatDetailCode("产品批次规格型号编码");// 产品批次规格型号编码
			tempSonBean.setReqPeriodLength((bean.getAls() + "*" + bean
					.getAxisNumber()));// 客户要求段长；如：1000*10
			tempSonBean.setProGgxh(bean.getProGgxh());// 规格型号
			tempSonBean.setScPlanbatState("未下发");// 未下发
			tempSonBean.setProCode(bean.getProGgxh());// 产品编码
			tempSonBean.setProColor(bean.getProColor());// 产品颜色
			tempSonBean.setReqSinLength(bean.getAls());// 客户要求单轴长度
			// 根据规格型号封装产品工艺编码
			String hql = " from CraCraftProduct where proGgxh = ? ";
			List<CraCraftProduct> craProCraftList = craCraftProductManageDAO
					.getHibernateTemplate().find(hql, bean.getProGgxh());
			Assert.notNull(craProCraftList, bean.getProGgxh() + "没有对应的产品工艺");
			if (craProCraftList.isEmpty())
				throw new RuntimeException(bean.getProGgxh() + "没有对应的产品工艺");
			if (craProCraftList.size() > 1)
				throw new RuntimeException(bean.getProGgxh()
						+ "产品工艺不只一个，不知道怎么选择");

			CraCraftProduct craCraftProduct = craProCraftList.get(0);
			tempSonBean.setProCraftCode(craCraftProduct.getProCraftCode());
			tempSonBean.setProCraftName(craCraftProduct.getProCraftName());
			tempSonBean.setProPeriodLength(bean.getAls() + "");
			;
			tempSonBean.setTotalPartLen(bean.getTotalLength());
			tempSonBean.setProType(null);// 产品类型
			tempSonBean.setMainId(tempMainBean.getId());// 主表主键
			resultSonList.add(tempSonBean);
		}
		sellContractPlanBatchManageDAO.saveOrUpdateBatch(resultSonList);// 保存生产通知单
		return true;
	}
*/
	/**
	 * 订单分解为生产通知单，备选方案
	 */
	/*@Transactional(readOnly = false)
	public boolean decomposeOrderSon2(List<SellSalesOrderDetails> sonList,
			SellSalesOrder mainBean) {

		return false;
	}*/

	/**
	 * 根据订单实体，查看订单完成率
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String completionRate(String orderCode) throws Exception {
		DecimalFormat df = new DecimalFormat("######0.00");
		Double dou = 0.6;// 该字段表示调用翟工接口，根据生产通知单id或者编号，返回该生产通知单完成率
		Double ddCompletionRate = 0.0;// 表示该订单的完成率

		// 根据订单编号，查询所有订单的明细，并计算总长度
	/*	String hql = " from SellSalesOrderDetails where orderCode = ? ";
		List<SellSalesOrderDetails> orderSonList = sellSalesOrderDetailsManageDAO
				.getHibernateTemplate().find(hql, orderCode);

		if (orderSonList == null || orderSonList.isEmpty()) {
			// throw new RuntimeException("当前订单还没有对应的明细记录");
			return "0";
		}
		// 计算总长度
		int totalLength = 0;// 表示该订单总长度

		for (SellSalesOrderDetails bean : orderSonList) {
			totalLength += bean.getTotalLength();
		}
		// 根据订单编号，查询所有的生产通知单
		hql = " from SellContract where orderCode = ? ";
		List<SellContract> sellContractList = sellContractManageDAO
				.getHibernateTemplate().find(hql, orderCode);
		if (sellContractList == null || sellContractList.isEmpty()) {
			return "0";// 没有生产通知单，表示该订单当前的完成率为0；
		}
		// 计算每个生产通知单的完成率
		for (SellContract bean : sellContractList) {
			Integer mainId = bean.getId();
			// 这个位置，表就需要换掉了
			hql = " from SellContractPlanBatch where mainId = ? ";
			List<SellContractPlanBatch> sellContractSonList = sellContractPlanBatchManageDAO
					.getHibernateTemplate().find(hql, mainId);
			int allLength = 0;// 生产通知单总长度
			for (SellContractPlanBatch son : sellContractSonList) {
				int length = Integer.parseInt(son.getReqPeriodLength())
						* son.getReqAmount();
				allLength += length;
			}
			// 判断该生产通知单，占订单的权重
			Double tempDouble = Double.parseDouble(allLength + "")
					/ totalLength;
			ddCompletionRate += tempDouble * dou;// 表示该订单的完成率
	}*/	
		return df.format(ddCompletionRate);
	}



	/**
	 * 
	 * @Description:分解成工作单
	 * @param orderCode
	 * @param lock
	 * @return
	 * @throws Exception 
	 */
	public Integer decomposePlaCourse(String orderCode, boolean lock) throws Exception {
		if (orderCode == null || orderCode.isEmpty()) {
			throw new RuntimeException("订单编号不能为空");
		}
		String hsql = "from SellSalesOrder where orderCode = ? ";
		List<SellSalesOrder> mainList = sellSalesOrderManageDAO
				.getHibernateTemplate().find(hsql, orderCode);
		if (mainList == null || mainList.isEmpty()) {
			throw new RuntimeException("该订单不存在");
		}
		if (mainList.size() > 1) {
			throw new RuntimeException("存在" + mainList.size() + "个订单编号为"
					+ orderCode + "相同的订单");
		}
		// 查询对应的订单明细记录
		Integer success = 0;
		String dHql = "from SellSalesOrderDetails where orderCode = '"+orderCode+"'";
		List<SellSalesOrderDetails> list = sellSalesOrderDetailsManageDAO.createQuery(dHql).list();
		
			String oHql = "select pro_Ggxh from Sell_Sales_Order_Details  where order_Code ='"
				+ orderCode + "' group by pro_Ggxh";
			List<String> proList =  sellSalesOrderDetailsManageDAO.createSQLQuery(oHql).list();
			//set去重
			Set<String>set = new HashSet<>();
			set.addAll(proList);
			
				for (String ggxh : set) {
				// 	map.put(objs[0].toString(), objs[1].toString());
					String courseCode = plaCourseManageDAO.exeFunction(
							"fun_get_course_code").toString();
					// 一个规格一个工单
					SellSalesOrder sellSalesOrder = mainList.get(0);
					PlaCourse plaCourse = new PlaCourse();
					plaCourse.setCreateDate(new Date());
					plaCourse.setWsType(sellSalesOrder.getOrderType());
					plaCourse.setPriLevel(sellSalesOrder.getPriLevel());
					plaCourse.setWsCode(courseCode);
					plaCourse.setScCode(sellSalesOrder.getContractCode());
					plaCourse.setOrderCode(orderCode);
					plaCourse.setUseFlag(PlaCourse.USE_FLAG_NO);
					// 总长度,
					//plaCourse.setTotalAmount();
					// 开单日期
					plaCourse.setBillDate(new Date());
					plaCourse.setIsFinish(PlaCourse.IS_FINISH_NO);
					plaCourse.setAuditFlag(PlaCourse.AUDIT_FLAG_NO);
					plaCourse.setDemandDate(sellSalesOrder.getDeliveryDate());
					plaCourse.setPlanFlag(PlaCourse.PLAN_FLAG_NO);
					plaCourse.setProGgxh(ggxh);
					// 保存工单
					plaCourseManageDAO.save(plaCourse);
					// 生成工单轴数颜色明细
					String hql = " from SellSalesOrderDetails where orderCode = '"
							+ orderCode
							+ "' and proGgxh = '"
							+ ggxh + "' and alreadyAxisNum <= axisNumber";
					List<SellSalesOrderDetails> sonList = sellSalesOrderDetailsManageDAO
							.getHibernateTemplate().find(hql);
					if (sonList != null && sonList.size() > 0) {
						for (SellSalesOrderDetails sod : sonList) {
							//已经分解完成的 不再分解
							if (sod.getAlreadyAxisNum() == sod.getAxisNumber()) {
								continue;
							}
							PlaCourseAxis pca = new PlaCourseAxis();
							pca.setAxisLength(sod.getAls());
							pca.setAxisNum(sod.getAxisNumber()-sod.getAlreadyAxisNum());
							pca.setColor(sod.getProColor());
							pca.setCourseCode(courseCode);
							pca.setCreateDate(new Date());
							pca.setProGgxh(sod.getProGgxh());
							if(pca.getAxisNum()>0){
								plaCourseAxisManageDAO.save(pca);
							}
							sod.setAlreadyAxisNum(sod.getAxisNumber());
							sellSalesOrderDetailsManageDAO.updateByCon(sod, false);
						}
					}
				}
			//分解标志
			SellSalesOrder sellSalesOrder = mainList.get(0);
			sellSalesOrder.setGenFlag(SellSalesOrder.GEN_FLAG_YES);
			this.updateByCon(sellSalesOrder, false);
		
		return success;
	}


	public SellSalesOrder getByOrderCode(String orderCode) {
		String hql = "from SellSalesOrder where orderCode = '"+orderCode+"'";
		List<SellSalesOrder> list = sellSalesOrderManageDAO.createQuery(hql).list();
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public void clearBean(String orderCode) {
		//删除工单
		//删除任务
		plaCourseManageDAO.clearBeanByOrderCodeAndUse(orderCode,"否");
		String hql = "delete from  SellSalesOrder where orderCode = '"+orderCode+"'";
		//删除订单详情
		String  hqlDetail = "delete  from SellSalesOrderDetails where orderCode='"+orderCode+"'";
		sellSalesOrderDetailsManageDAO.createQuery(hqlDetail).executeUpdate();
		//删除订单,
		sellSalesOrderManageDAO.createQuery(hql).executeUpdate();
		//
		
		
		
	}
	
	private Gson gson = new Gson();
	public Page getListPage(Page page, String param) {
		String hql = "from  SellSalesOrder where 1 = 1 ";
		StringBuilder sb = new StringBuilder(hql);
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		if (map != null && map.size() >0 ) {
			if (map.get("genFlag") != null && !"".equals(map.get("genFlag"))) {
				sb.append(" and genFlag = '"+map.get("genFlag")+"'");
			}
			if (map.get("contractCode") != null && !"".equals(map.get("contractCode"))) {
				sb.append(" and contractCode like '%"+map.get("contractCode")+"%'");
			}
			if (map.get("orderCode") != null && !"".equals(map.get("orderCode"))) {
				sb.append(" and orderCode like '%"+map.get("orderCode")+"%'");
			}
			if (map.get("start") != null && !"".equals(map.get("start"))) {
				sb.append(" and deliveryDate > '"+map.get("start")+"'");
			}
			if (map.get("end") != null && !"".equals(map.get("end"))) {
				sb.append(" and deliveryDate < '"+map.get("end")+"'");
			}
			
		}
		sb.append(" order by deliveryDate,genFlag");
		page = sellSalesOrderManageDAO.pageQuery(sb.toString(), page.getPageNo(), page.getPagesize());
		return page;
	}

	/**
	 * 
	 * @Description:单个订单分批次分解
	 * @param orderCode
	 * @param detailBeans
	 * @throws Exception 
	 */
	public void batchDecompose(String orderCode, String detailBeans) throws Exception {
		//订单分解状态 的
		boolean flag = false;
		SellSalesOrder byOrderCode = this.getByOrderCode(orderCode);
		//总轴数
		Integer total = 0;
		//总分解数
		Integer deTotal = 0;
		List<SellSalesOrderDetails> detailList = gson.fromJson(detailBeans, new TypeToken<List<SellSalesOrderDetails>>(){}.getType());
		List<Map<String ,String>>dataMap = gson.fromJson(detailBeans, new TypeToken<List<Map<String ,String>>>(){}.getType());
		//是本次需要分解的规格,然后的产品规格
		Set<String>set = new HashSet();
		boolean ingFlag = false;
		for (SellSalesOrderDetails detail : detailList) {
			total += detail.getAxisNumber();
			Integer de = detail.getAlreadyAxisNum() + detail.getNowAxisNum();
			deTotal += de;
			//如果本次分解轴数是0 跳过
			if(detail.getNowAxisNum() == 0 || detail.getNowAxisNum() == null || detail.getAlreadyAxisNum() == detail.getAxisNumber()){
				continue;
			}
			total += detail.getAxisNumber();
			detail.setAlreadyAxisNum(de);
			detail.setNowAxisNum(detail.getAxisNumber() - de);
			sellSalesOrderDetailsManageDAO.updateByCon(detail, false);
			//添加规格型号
			set.add(detail.getProGgxh());
		}
		
		//如果所有的都已分解完成 放过这个方法
		if(set.size() == 0){
			return;
		}
		boolean finishFlag = false;
		
		if (total.equals(deTotal)) {
			//分解完成
			byOrderCode.setGenFlag(SellSalesOrder.GEN_FLAG_YES);
		}else if(deTotal > 0){
			//分解中
			byOrderCode.setGenFlag(SellSalesOrder.GEN_FLAG_ING);
		}else {
			byOrderCode.setGenFlag(SellSalesOrder.GEN_FLAG_NO);
		}
		
		this.updateByCon(byOrderCode, false);
		
		
		//生成工单
		for (String proggxh: set) {
			
			
			//生成工单
			String courseCode = plaCourseManageDAO.exeFunction(
					"fun_get_course_code").toString();
			// 一个规格一个工单
			PlaCourse plaCourse = new PlaCourse();
			plaCourse.setCreateDate(new Date());
			plaCourse.setWsType(byOrderCode.getOrderType());
			plaCourse.setPriLevel(byOrderCode.getPriLevel());
			plaCourse.setWsCode(courseCode);
			plaCourse.setScCode(byOrderCode.getContractCode());
			plaCourse.setOrderCode(orderCode);
			plaCourse.setUseFlag(PlaCourse.USE_FLAG_NO);
			// 总长度,
			//plaCourse.setTotalAmount();
			// 开单日期
			plaCourse.setBillDate(new Date());
			plaCourse.setIsFinish(PlaCourse.IS_FINISH_NO);
			plaCourse.setAuditFlag(PlaCourse.AUDIT_FLAG_NO);
			plaCourse.setDemandDate(byOrderCode.getDeliveryDate());
			plaCourse.setPlanFlag(PlaCourse.PLAN_FLAG_NO);
			plaCourse.setProGgxh(proggxh);
			// 保存工单
			plaCourseManageDAO.save(plaCourse);
			
			//循环订单详情生成工单详情表
			for (Map<String,String> map : dataMap) {
				if (!proggxh.equals(map.get("proGgxh"))) {
					continue;
				}
				PlaCourseAxis pca = new PlaCourseAxis();
				pca.setAxisLength(Integer.parseInt(map.get("als")));
				pca.setAxisNum( Integer.parseInt(map.get("nowAxisNum")));
				pca.setColor(map.get("proColor"));
				pca.setCourseCode(courseCode);
				pca.setCreateDate(new Date());
				pca.setProGgxh( map.get("proGgxh"));
				if(pca.getAxisNum()>0){
					plaCourseAxisManageDAO.save(pca);
				}
			}
		}
		
	}

	/**
	 * 
	 * @Description:变插单
	 * @param map
	 * @throws Exception 
	 */
	public void changeOrderTypeNtoIn(Map<String, String> map) throws Exception {
		//1.获得该订单下,详情的已排产情况
		List<PlaCourse>ourseList = plaCourseManageDAO.findByOrderCodeAndUse(map.get("orderCode"),"是");
		if (PlaCourse.wsType_INSERT.equals(map.get("orderType"))) {
			//插单
			//有排产的,将排产的信息全部拿出来 // 规格型号,颜色,每轴长度,轴数
			List<Object[]>axisList = plaCourseAxisManageDAO.getNumByOrderCode(map.get("orderCode"));
			if (axisList != null || axisList.size() > 0) {
			String sql = "from SellSalesOrderDetails where orderCode = '"+map.get("orderCode")+"' ";
			//去匹配订单详情,更新详情
				for (Object[] objs : axisList) {
					StringBuilder sb = new StringBuilder(sql);
					sb.append(" and proGgxh ='"+objs[0].toString()+"' and proColor = '"+objs[1].toString()+"' and als="+objs[2]+"");
					SellSalesOrderDetails detail =  (SellSalesOrderDetails) sellSalesOrderDetailsManageDAO.createQuery(sb.toString()).list().get(0);
					//已排产的轴数
					int sum = Integer.parseInt(objs[3].toString());
					detail.setAlreadyAxisNum(detail.getAlreadyAxisNum() - sum);
					detail.setNowAxisNum(detail.getNowAxisNum() + sum);
					sellSalesOrderDetailsManageDAO.updateByCon(detail, false);
				}
			}
			//删除已生成的所有单子
			plaCourseManageDAO.clearBeanByOrderCodeAndUse(map.get("orderCode"),"否");
			//更新 订单
			SellSalesOrder salesOrder = this.getByOrderCode(map.get("orderCode"));
			salesOrder.setOrderType(map.get("orderType"));
			salesOrder.setPriLevel(Integer.parseInt(map.get("priLevel")));
			SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
			salesOrder.setDeliveryDate(sdf.parse(map.get("deliveryDate")));
			//更新订单
			this.updateByCon(salesOrder, false);
			//分解 剩下的所有
			this.decomposePlaCourse(salesOrder.getOrderCode(), true);
		}	
			
	}
}
