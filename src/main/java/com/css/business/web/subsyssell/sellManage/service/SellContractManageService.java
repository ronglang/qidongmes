package com.css.business.web.subsyssell.sellManage.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.subsyscraft.bean.CraCraftProduct;
import com.css.business.web.subsyscraft.craManage.dao.CraCraftProductManageDAO;
import com.css.business.web.subsyscraft.craManage.dao.CraRouteSeqManageDAO;
import com.css.business.web.subsysmanu.bean.MauProduct;
import com.css.business.web.subsysmanu.mauManage.dao.MauProductManageDao;
import com.css.business.web.subsysplan.bean.PlaCourse;
import com.css.business.web.subsysplan.plaManage.dao.PlaCourseManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaMachinePlanScheduleManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaProductOrderAxisManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaProductOrderManageDAO;
import com.css.business.web.subsysplan.plaManage.service.PlaCourseManageService;
import com.css.business.web.subsyssell.bean.SellContract;
import com.css.business.web.subsyssell.bean.SellContractDetail;
import com.css.business.web.subsyssell.bean.SellContractPlanBatch;
import com.css.business.web.subsyssell.bean.SellCustomer;
import com.css.business.web.subsyssell.sellManage.dao.SellContractDetailManageDAO;
import com.css.business.web.subsyssell.sellManage.dao.SellContractManageDAO;
import com.css.business.web.subsyssell.sellManage.dao.SellContractPlanBatchManageDAO;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.google.gson.Gson;

@Service("sellContractManageService")
public class SellContractManageService extends
		BaseEntityManageImpl<SellContract, SellContractManageDAO> {
	@Resource(name = "sellContractManageDAO")
	// @Autowired
	private SellContractManageDAO dao;

	@Resource(name = "sellContractPlanBatchManageDAO")
	private SellContractPlanBatchManageDAO sellContractPlanBatchManageDAO;

	@Resource(name = "sellContractDetailManageDAO")
	private SellContractDetailManageDAO sellContractDetailManageDAO;
	@Resource(name = "mauProductManageDao")
	private MauProductManageDao mauProductManageDao;

	@Resource(name = "craCraftProductManageDAO")
	private CraCraftProductManageDAO craCraftProductManageDAO;

	@Resource(name = "plaProductOrderManageDAO")
	private PlaProductOrderManageDAO plaProductOrderManageDAO;

	@Resource(name = "plaProductOrderAxisManageDAO")
	private PlaProductOrderAxisManageDAO plaProductOrderAxisManageDAO;

	@Resource(name = "plaCourseManageDAO")
	private PlaCourseManageDAO plaCourseManageDAO;
	@Resource(name="craRouteSeqManageDAO")
	private CraRouteSeqManageDAO craRouteSeqManageDAO;
	@Resource(name="plaMachinePlanScheduleManageDAO")
	private PlaMachinePlanScheduleManageDAO plaMachinePlanScheduleManageDAO;//进度
	
	@Resource(name = "plaCourseManageDAO")
	private PlaCourseManageDAO pcDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Gson gson = new Gson();
	@Override
	public SellContractManageDAO getEntityDaoInf() {
		return dao;
	}

	public void delContractService(String scCode) throws ParseException {
		String sql = " DELETE from sell_contract where sc_code = '" + scCode
				+ "'";
		dao.delContractService(sql);
	}

	public void InsertContractService() {

	}

	public List<SellCustomer> queryCustomerService() {
		String sql = " select * from  ";
		return null;
	}

	/*
	 * public HashMap<String, Object> getloadSellContractManageService( String
	 * pageStr, String pagesizeStr) { String hql =
	 * "from SellContract order by id "; HashMap<String, Object> map =
	 * getPagingQueryToolService(hql, pageStr, pagesizeStr); return map; }
	 */

	/**
	 * @todo: 查询生产通知单
	 * @author : zhaichunlei
	 * @throws Exception
	 * @date: 2017年9月19日
	 */
	@Transactional(readOnly = true)
	public Page loadSellContractManage(Page page, SellContract ent)
			throws Exception {
		/*
		 * String hql = "from SellContract order by id "; HashMap<String,
		 * Object> map = getPagingQueryToolService(hql, pageStr, pagesizeStr);
		 */
		return dao.loadSellContractManage(page, ent);
	}

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

	public Page getComSerchSellContractService(Page p, String orderCode,
			String serchState, String serchscCode, String serchcusCode,
			String serchAgentBy) {
		String hql = "from SellContract where 1=1";
		if (!"".equals(serchState) && serchState != null) {
			hql += " and scState = '" + serchState + "' ";
		}
		if (!"".equals(serchscCode) && serchscCode != null) {
			hql += " and scCode = '" + serchscCode + "' ";
		}
		if (!"".equals(orderCode) && orderCode != null)
			if (!"".equals(serchcusCode) && serchcusCode != null) {
				hql += " and cusCode = '" + serchcusCode + "' ";
			}
		if (!"".equals(serchAgentBy) && serchAgentBy != null) {
			hql += " and agentBy = '" + serchAgentBy + "' ";
		}
		hql += " order by id";
		Page page = dao.pageQuery(hql, p.getPageNo(), p.getPagesize());
		return page;
	}

	/**
	 * 新增合同
	 * 
	 * @param sellContract
	 * @throws Exception
	 */
	public void addSellContractManageService(SysUser user,
			SellContract sellContract) throws Exception {
		// dao.addSellContractManageDao(sellContract);
		List<SellContractPlanBatch> lst = sellContract
				.getSellContractPlanBatchLst();
		String wsCode = sellContract.getScCode();

		if (lst == null || lst.size() == 0)
			throw new Exception("保存合同需要有批次信息");
		if (wsCode == null || wsCode.trim().length() == 0)
			throw new Exception("合同编码不能为空");

		dao.save(sellContract);

		for (SellContractPlanBatch p : lst) {
			p.setScCode(wsCode); // 合同编码

			// 得到规格型号,颜色
			String proGgxh = p.getProGgxh();
			String proColor = p.getProColor();
			// 得到产品code 从产品工艺表中获取 proid
			List<CraCraftProduct> craList = craCraftProductManageDAO
					.getCraCraftProduct(proGgxh, proColor);
			if (craList != null && craList.size() > 0) {
				Integer proId = craList.get(0).getProId();
				// 从产品表里获得procode
				MauProduct mauProduct = mauProductManageDao.get(proId);
				if (mauProduct != null) {
					String proCode = mauProduct.getPro_code();
					if (proCode == null || proCode.length() == 0)
						throw new Exception("产品表数据错误。产品编码为空");
					p.setProCode(mauProduct.getPro_code());
				} else {
					throw new Exception("产品工艺表中的产品不存在于产品表中。参数错误");
				}
			} else {
				throw new Exception("参数配置错误，根据产品规格（ " + proGgxh + "）,产品颜色（"
						+ proColor + "）,不存在对应产品工艺存在");
			}
			p.setCreateBy(user.getAccount());
			p.setCreateDate(new Date(System.currentTimeMillis()));
			p.setScPlanbatState("未下发");

			p.setMainId(sellContract.getId());
			sellContractPlanBatchManageDAO.save(p);
		}
	}

	public void addSellContractPlanBatchService(List<SellContractPlanBatch> list)
			throws Exception {
		for (int i = 0; i < list.size(); i++) {
			SellContractPlanBatch b = list.get(i);
			String proCode = b.getProCode();
			String proCraftCode = b.getProCraftCode();
			if (proCode == null || proCode.length() == 0) {
				throw new Exception("产品不能为空");
			}
			if (proCraftCode == null || proCraftCode.length() == 0) {
				throw new Exception("产品工艺不能为空");
			}
			dao.addSellContractPlanBatchDao(list.get(i));
		}
	}

	/**
	 * 删除合同
	 * 
	 * @param ids
	 */

	public void deleteSellContractManageByIdsServices(int[] ids) {

		String hql0 = "delete from sell_contract_plan_batch USING sell_contract where sell_contract.id =? and sell_contract_plan_batch.sc_code = sell_contract.sc_code";
		String hql1 = "delete from SellContract where id=?";
		for (int i = 0; i < ids.length; i++) {
			dao.createSQLQuery(hql0, ids[i]).executeUpdate();
			dao.createQuery(hql1, ids[i]).executeUpdate();
		}
	}

	/**
	 * 修改合同
	 * 
	 * @param pageStr
	 * @param pagesizeStr
	 * @param scCode
	 * @return
	 */
	public HashMap<String, Object> getSellContractPlanBatchService(
			String pageStr, String pagesizeStr, String scCode) {
		String hql = "from SellContractPlanBatch where scCode =? order by id ";
		HashMap<String, Object> map = getPagingQueryToolService(hql, pageStr,
				pagesizeStr, scCode);
		return map;
	}

	/**
	 * 更新SellContract
	 * 
	 * @param sellContract
	 */

	public void updateSellContractManageService(SellContract sellContract) {
		dao.updateSellContractManageDao(sellContract);
	}

	/**
	 * 更新SellContractPlanBatch
	 * 
	 * @param list
	 */
	public void updateSellContractPlanBatchService(
			List<SellContractPlanBatch> list) {
		for (int i = 0; i < list.size(); i++) {
			dao.updateSellContractPlanBatchDao(list.get(i));
		}
	}

	public void decSellContractDetailService(SysUser user, int[] ids)
			throws Exception {
		// sell_contract_detail:bat_code,sc_code,create_by,create_date,delive_date
		// req_unit,pbat_detail_code,pbat_detail_state,req_period_length,req_amount,pro_ggxh

		// sell_contract:id,create_date,create_by,sc_code

		// sell_contract_plan_batch:req_amount,req_unit,bat_code,delive_date,pbat_detail_code,req_period_length,pro_ggxh
		for (int i = 0; i < ids.length; i++) {
			String hql0 = "update SellContract s set s.scState=? where s.id=?";
			dao.createQuery(hql0, "已分解", ids[i]).executeUpdate();
			// String hql1 = "from SellContract s where s.id=?";
			SellContract s = dao.get(ids[i]);

			List<SellContractPlanBatch> lst = sellContractPlanBatchManageDAO
					.findBy("scCode", s.getScCode());
			if (lst == null || lst.size() == 0) {
				throw new Exception("本次分解不存在合同批次内容。无法分解");
			}
			if (lst != null && lst.size() > 0) {
				for (SellContractPlanBatch scb : lst) {
					SellContractDetail scdetail = new SellContractDetail();
					scdetail.setBatCode(scb.getBatCode());
					scdetail.setScCode(s.getScCode());
					scdetail.setCreateBy(user.getAccount());
					scdetail.setCreateDate(new Date(System.currentTimeMillis()));
					scdetail.setDeliveDate(scb.getDeliveDate());
					scdetail.setReqUnit(scb.getReqUnit());
					scdetail.setPbatDetailCode(scb.getPbatDetailCode());
					scdetail.setPbatDetailState("未生成");
					scdetail.setReqPeriodLength(scb.getReqPeriodLength());
					scdetail.setReqAmount(scb.getReqAmount());
					scdetail.setProGgxh(scb.getProGgxh());
					scdetail.setProColor(scb.getProColor());

					MauProduct mp = mauProductManageDao.getByProductCode(scb
							.getProCode());
					if (mp != null)
						scdetail.setMauProductId(mp.getId());

					scdetail.setPlanBatchId(scb.getId());

					// added by zhaichunlei
					ScriptEngineManager manager = new ScriptEngineManager();
					ScriptEngine se = manager.getEngineByName("js");
					BigDecimal bd = new BigDecimal(se.eval(scb.getProPeriodLength()).toString());
					Integer length = bd.intValue(); // 单位是米，要求总长度是int

					scdetail.setProPeriodLength(scb.getProPeriodLength());
					scdetail.setTotalLen(length);// 明细中，生产段长计算后的总长度
					scdetail.setCompleteLen(0); // 下发到工单的长度
					scdetail.setMainId(scb.getMainId()); // 批次表中的
					sellContractDetailManageDAO.save(scdetail);

					// 批次与与合同使用同一个状态。批次在合同分解时，它分解到detail中，然后批次的状态就变成已分解。
					// detail中的状态是下发为工单的状态
					scb.setScPlanbatState("已分解");
					sellContractPlanBatchManageDAO.updateByCon(scb, false);
				}
			}
			// SellContract sellContract = (SellContract) dao.createQuery(hql1,
			// ids[i]).list().get(0);
			/*
			 * String hql2 =
			 * "SELECT r.* FROM sell_contract_plan_batch r,sell_contract s WHERE s.id = ? AND s.sc_code = r.sc_code"
			 * ;
			 * 
			 * @SuppressWarnings("unchecked") List<Object[]> SCPBatchlist =
			 * dao.createSQLQuery(hql2, ids[i]).list(); for (int j = 0; j <
			 * SCPBatchlist.size(); j++) { SellContractDetail scdetail = new
			 * SellContractDetail();
			 * scdetail.setBatCode(SCPBatchlist.get(j)[7]!=
			 * null?SCPBatchlist.get(j)[7].toString():null);
			 * scdetail.setScCode(sellContract.getScCode());
			 * scdetail.setCreateBy(sellContract.getCreateBy());
			 * scdetail.setCreateDate(sellContract.getCreateDate());
			 * scdetail.setDeliveDate
			 * (SCPBatchlist.get(j)[8]!=null?(java.sql.Date
			 * )SCPBatchlist.get(j)[8]:null);
			 * scdetail.setReqUnit(SCPBatchlist.get
			 * (j)[6]!=null?SCPBatchlist.get(j)[6].toString():null);
			 * scdetail.setPbatDetailCode
			 * (SCPBatchlist.get(j)[9]!=null?SCPBatchlist
			 * .get(j)[9].toString():null); scdetail.setPbatDetailState("未生成");
			 * scdetail
			 * .setReqPeriodLength(SCPBatchlist.get(j)[10]!=null?SCPBatchlist
			 * .get(j)[10].toString():null);
			 * scdetail.setReqAmount(SCPBatchlist.get
			 * (j)[5]!=null?(Integer)SCPBatchlist.get(j)[5]:null);
			 * scdetail.setProGgxh
			 * (SCPBatchlist.get(j)[11]!=null?SCPBatchlist.get
			 * (j)[11].toString():null); //obj[14]是颜色
			 * scdetail.setProColor(SCPBatchlist
			 * .get(j)[14]!=null?SCPBatchlist.get(j)[14].toString():null);
			 * 
			 * scdetail.setPlanBatchId(SCPBatchlist.get(j)[0] == null ? null:
			 * Integer.parseInt(SCPBatchlist.get(j)[0].toString()));
			 * dao.save(scdetail); }
			 */
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map[] getcusCode() {
		String hql = " from SellContract ";
		List<SellContract> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					map.put("text", list.get(i).getCusCode());
				} else {
					map.put("id", i + "");
				}
			}
			str[i] = map;
		}
		return str;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map[] getscCode() {
		String hql = " from SellContract ";
		List<SellContract> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					map.put("text", list.get(i).getScCode());
				} else {
					map.put("id", i + "");
				}
			}
			str[i] = map;
		}
		return str;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map[] getagentBy() {
		String hql = " SELECT DISTINCT agentBy from SellContract ";
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map[] getProType() {
		//String sql = " SELECT DISTINCT pro_name from mau_product where pro_type = '成品' and pro_name is NOT NULL  ";
		String sql = " SELECT DISTINCT pro_name from mau_product where pro_type = '产品类型' and pro_name is NOT NULL  ";
		// List<Object> list = dao.createQuery(hql).list();
		List<Object> list = dao.createSQLQuery(sql).list();
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map[] getGgxh(String proType) {
		//String sql = " SELECT DISTINCT pro_code from mau_product where  pro_name = ? and pro_type = '成品' and pro_code is NOT NULL ";
		String sql = " SELECT DISTINCT pro_code from mau_product where  p_code = ? and pro_type = '成品' and pro_code is NOT NULL ";
		// List<Object> list = dao.createQuery(hql,proName).list();
		List<Object> list = dao.createSQLQuery(sql, proType).list();
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map[] getColor(String proGgxh) {
		String sql = " SELECT DISTINCT pro_color from mau_product where  pro_ggxh = ? and pro_type = '成品' and pro_color  is NOT NULL ";
		// List<Object> list = dao.createQuery(hql,proGgxh).list();
		List<Object> list = dao.createSQLQuery(sql, proGgxh).list();
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map[] getProCraftCode() {
		String sql = " SELECT DISTINCT pro_craft_code from cra_craft_product where  pro_craft_code  is NOT NULL ";
		// List<Object> list = dao.createQuery(hql,proGgxh).list();
		List<Object> list = dao.createSQLQuery(sql).list();
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map[] getProCode(String proType, String proGgxh, String proColor) {
		String sql = " SELECT DISTINCT pro_code from mau_product where  pro_name = ? and pro_ggxh =? and pro_color =? ";
		// List<Object> list = dao.createQuery(hql,proGgxh).list();
		List<Object> list = dao.createSQLQuery(sql, proType, proGgxh, proColor)
				.list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					map.put("proCode", list.get(i).toString());
				} else {
					map.put("id", i + "");
				}
			}
			str[i] = map;
		}
		return str;
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> getgetGgxh1() {

		final String sql = " SELECT DISTINCT pro_code from mau_product where  pro_type = ? and pro_type = '成品' and pro_code is not NULL ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,
				new Object[] { "" });

		return list;
	}

	public void saveSellContract(SysUser user, SellContract sc,
			List<SellContractPlanBatch> lsc) throws Exception {
		sc.setCreateBy(user.getAccount());
		sc.setCreateDate(new Date());
		sc.setScState("未分解");
		dao.save(sc);

		for (SellContractPlanBatch sp : lsc) {
			String pc = sp.getProCraftCode();
			if(pc == null || pc.length() == 0)
				throw new Exception("参数提交错误，产品工艺不能为空");
			
			String hql = " from CraCraftProduct where proCraftCode = ? ";
			CraCraftProduct cp = (CraCraftProduct) dao
					.createQuery(hql, sp.getProCraftCode()).list().get(0);
			Integer pid = cp.getProId();
			MauProduct mp = mauProductManageDao.get(pid);
			if(mp == null){
				throw new Exception("数据错误。产品工艺（"+sp.getProCraftCode()+"）对应的产品不存在");
			}
			
			sp.setProCode(mp.getPro_code());
			sp.setProCraftName(cp.getProCraftName());
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine se = manager.getEngineByName("js");
			String str = se.eval(sp.getProPeriodLength()).toString();
			BigDecimal bd = new BigDecimal(str);
			//int length = (int) Double.parseDouble(str);
			int length = bd.intValue();
			sp.setTotalPartLen(length);
			sp.setScCode(sc.getScCode());
			sp.setCreateBy(user.getAccount());
			sp.setReqUnit("轴");
			sp.setCreateDate(new Date());
			sp.setScPlanbatState("未下发");
			sp.setMainId(sc.getId());
			dao.save(sp);
		}

	}

	@Deprecated
	public void saveSellContractPlanBatch(SysUser user, SellContractPlanBatch sb) {
		sb.setCreateBy(user.getAccount());
		sb.setCreateDate(new Date());
		sb.setScPlanbatState("未下发");
		dao.save(sb);
	}

	public void updateSellContract(SysUser user, SellContract sc,
			List<SellContractPlanBatch> lsc) throws Exception {
		sc.setCreateBy(user.getAccount());
		sc.setCreateDate(new Date());
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine se = manager.getEngineByName("js");
		for (SellContractPlanBatch planBatch : lsc) {
			planBatch.setCreateBy(user.getAccount());
			//planBatch.setTotalPartLen((int)Double.parseDouble(se.eval(planBatch.getProPeriodLength()).toString()));
			BigDecimal bd = new BigDecimal(se.eval(planBatch.getProPeriodLength()).toString());
			planBatch.setTotalPartLen(bd.intValue());
			planBatch.setCreateDate(new Date());
			dao.updateByCon(planBatch, false);
		}
		dao.updateByCon(sc, false);
	}

	@Deprecated
	public void updateSellContractPlanBatch(SysUser user,
			SellContractPlanBatch sb) throws Exception {
		sb.setCreateBy(user.getAccount());
		sb.setCreateDate(new Date());
		dao.updateByCon(sb, false);

	}

	@Transactional(readOnly = false)
	public void delContract(Integer id) throws Exception {
		SellContract sc = dao.get(id);
		String code = sc.getContractCode();
		List<SellContractDetail> scdLst = sellContractDetailManageDAO.findBy(
				"mainId", id);
		if (scdLst != null && scdLst.size() > 0) {
			for (SellContractDetail scd : scdLst) {
				String state = scd.getPbatDetailState();
				// 已生成的则不能删除生产通知单
				if (!"未生成".equals(state)) {
					throw new Exception("生产通知单（" + code + "）存在工作单，不能删除");
				}
			}
		}

		// 删除通知单
		String sql = " DELETE FROM sell_contract WHERE id = ? ";
		dao.deleteBySql(sql, id);

		// 删除bat
		sql = " DELETE FROM sell_contract_plan_batch  WHERE main_id = ? ";
		dao.deleteBySql(sql, id);

		// 删除detail
		sql = " DELETE FROM sell_contract_detail  WHERE main_id = ? ";
		dao.deleteBySql(sql, id);
	}

	// 需要在合适的环境中用。 未校验
	@Deprecated
	public void delPlaContract(String ScCode) {
		String sql = " DELETE FROM sell_contract_plan_batch  WHERE sc_code = ? ";
		dao.deleteBySql(sql, ScCode);
	}

	public String getScCode(Integer id) {
		String sql = " SELECT c.sc_code FROM sell_contract c WHERE id = ? ";
		String sc = (String) dao.createSQLQuery(sql, id).list().get(0);
		return sc;

	}

	public SellContract getContracts(Integer id) {
		String hql = " from SellContract where id = ? ";
		SellContract sc = (SellContract) dao.createQuery(hql, id).list().get(0);
		return sc;
	}

	public Page getContractsGrid(Page p, String scCode) {
		String hql = "from  SellContractPlanBatch where sc_code = ? ";
		Page page = dao.pageQuery(hql, p.getPageNo(), p.getPagesize(), scCode);
		return page;
	}

	/**   
	 * @Description: 获得当前所有通知单情况   
	 * @return         
	 */ 
	public Map<String,String> getSellContractSurvey() {
		// TODO Auto-generated method stub
		Integer all = 0;
		Long isGive = 0L ;//未分解;
		Integer notProduct = 0; //未生产
		Integer producting = 0; //生产中
		Integer producted = 0; //已生产
		Map<String,String>resultMap = new HashMap<>();
		String hql ="select count(*) from SellContract where scState ='未分解'";
		isGive = (Long) dao.createQuery(hql).uniqueResult();
		//在已分解中查询,生产通知单关联生产令
		String hql_2 =" select  scCode from  SellContract where scState ='已分解'";
		List<String> list = dao.createQuery(hql_2).list();
		if (list != null && list.size() > 0) {
			for (String scCode : list) {
				//根据编号去查询他当前的生产情况
				String state = survey(scCode);
				if ("未生产".equals(state)) {
					notProduct +=1;
				} else if ("生产中".equals(state)) {
					producting += 1;
				}else if ("已生产".equals(state)) {
					producted += 1;
				}
			}
		}
		List<String>legend = new ArrayList<>();
		legend.add("未分解");
		legend.add("未生产");
		legend.add("生产中");
		legend.add("已生产");
		List<Map<String, String>>dataList = new ArrayList<>();
		Map<String,String> map_no = new HashMap<>();
		map_no.put("value", notProduct.toString());
		map_no.put("name", "未生产");
		Map<String,String> map_ing = new HashMap<>();
		map_ing.put("value", producting.toString());
		map_ing.put("name", "生产中");
		Map<String,String> map_ed = new HashMap<>();
		map_ed.put("value", producted.toString());
		map_ed.put("name", "已生产");
		Map<String,String> map_re = new HashMap<>();
		map_re.put("value", isGive.toString());
		map_re.put("name", "未分解");
		dataList.add(map_re);
		dataList.add(map_no);
		dataList.add(map_ing);
		dataList.add(map_ed);

		resultMap.put("legend",gson.toJson(legend) );
		resultMap.put("dataList", gson.toJson(dataList));
		
		return resultMap;
	}
	
	
	private String survey(String sellCode){
		//0 未生产  1 已生产 2.预生产 3生产中 
		String sql ="SELECT st from "
				+ "( SELECT sc_code code, po.is_flag st "
				+ "FROM sell_contract ss "
				+ "LEFT JOIN pla_product_order po ON ss.sc_code = po.contract_code"
				+ " GROUP BY ss.sc_code, po.is_flag) M "
				+ "where code='"+sellCode+"' ";
		List<String> list = dao.createSQLQuery(sql).list();
		if (list.contains("1") || list.contains("2")) {
			//生产中
			return "生产中";
		} else if(list.contains("0") && !list.contains("4")){
			return "未生产";
		} else{
			return "已生产";
		}
	}

	/**   
	 * @Description: 根据订单获得该订单下的生产通知单的完成情况   
	 * @param orderCode         
	 */ 
	public void getCompleteRateByOrder(String orderCode) {
		// TODO Auto-generated method stub
		String hql ="from SellContract where orderCode='"+orderCode+"'";
		List<SellContract> list = dao.createQuery(hql).list();
		if (list != null ) {
			for (SellContract sellContract : list) {
				//获得
				Integer id = sellContract.getId();//通知单编号
				BigDecimal allsemi = new BigDecimal(0);
				List<PlaCourse> courseList= pcDao.getPlaCourseByContractId(id.toString());//工单id
				if (courseList != null) {
					for (PlaCourse plaCourse : courseList) {
						String courseCode = plaCourse.getWsCode();
						String routeCode = plaCourse.getRouteCode();
						String seqCode = craRouteSeqManageDAO.getLastSeqCode(routeCode);
						//3.查询机台任务计划和机台进度,担心一个工单 一个工序 有多个机台所以使用object[]
						List<Object[]>semiList = plaMachinePlanScheduleManageDAO.getPlanAndFactPro(courseCode,seqCode);
						BigDecimal allPro = new BigDecimal(0);
						
						for (Object[] objs : semiList) {
							//String proLen = objs[0]==null?"0":objs[0].toString() ;
							String semiLen =objs[1]==null?"0":objs[1].toString() ;
						//	BigDecimal pro = new BigDecimal(proLen);
							//BigDecimal semi = new BigDecimal(semiLen);
						//	allPro =allPro.add(pro);
							allsemi = allsemi.add(new BigDecimal(semiLen));
						}
					}
				}
			}
		}
	}

}
