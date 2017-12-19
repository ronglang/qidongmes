package com.css.business.web.subsysplan.plaManage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.bean.CraBomProductMater;
import com.css.business.web.subsyscraft.bean.CraCraftProduct;
import com.css.business.web.subsyscraft.bean.CraProSeqRelation;
import com.css.business.web.subsyscraft.craManage.service.CraBomProductMaterManageService;
import com.css.business.web.subsyscraft.craManage.service.CraCraftProductManageService;
import com.css.business.web.subsyscraft.craManage.service.CraProSeqRelationManageService;
import com.css.business.web.subsysplan.bean.PlaProductOrder;
import com.css.business.web.subsysplan.bean.PlaUnitConMater;
import com.css.business.web.subsysplan.plaManage.dao.PlaProductOrderManageDAO;
import com.css.business.web.subsysplan.plaManage.utils.JsonUtil;
import com.css.business.web.subsysstore.bean.StoreMrecord;
import com.css.business.web.subsysstore.storeManage.service.StoreMrecordManageService;
import com.css.common.util.DateUtil;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaProductOrderManageService")
public class PlaProductOrderManageService extends
		BaseEntityManageImpl<PlaProductOrder, PlaProductOrderManageDAO> {

	@Resource(name = "plaProductOrderManageDAO")
	private PlaProductOrderManageDAO dao;

	@Resource(name = "plaUnitConMaterManageService")
	private PlaUnitConMaterManageService serviceu;

	@Resource(name = "storeMrecordManageService")
	private StoreMrecordManageService serStore;

	@Resource(name = "craProSeqRelationManageService")
	private CraProSeqRelationManageService serCra;

	@Resource(name = "craCraftProductManageService")
	private CraCraftProductManageService cpmservice;
	
	@Resource(name = "craBomProductMaterManageService")
	private CraBomProductMaterManageService sbpservice;
	
	@Override
	public PlaProductOrderManageDAO getEntityDaoInf() {
		return dao;
	}

	public Page getPlaProductOrder(Page p) {
		//?????????????????  
		/*String hql = " from PlaProductOrder s where s.is_flag = '0' and s.createBy = ? ";
		String createBy = "JS";
		Page page = dao.pageQuery(hql, p.getPageNo(), p.getPagesize(),createBy);
		//dao.pageQuery(hql, p.getPageNo(), p.getPagesize(), createBy);
		return page;*/
		
		String hql = " from PlaProductOrder s where s.is_flag = '0' ";
		Page page = dao.pageQuery(hql, p.getPageNo(), p.getPagesize());
		return page;
	}

	/**
	 * 判断材料是否充足
	 * 
	 * @param li
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> getMater(String li) throws Exception {
		JSONArray ja = JSONArray.fromObject(li);
		List<Object> list = JsonUtil.jsonToList(ja);
		List<CraBomProductMater> lis = new ArrayList<CraBomProductMater>();
		List<Integer> ids = new ArrayList<>();
		String msg = null;
		Double Jl = 0.0; // 胶料数量
		Double jlStock = 0.0; // 胶料实际库存
		Double Cu = 0.0; // 铜料数量
		Double cuStock = 0.0; // 铜料实际库存
		String jlobjGgxh = ""; // 暂存胶料规格型号 （判断胶料相同规格型号下的材料数量）
		String cuobjGgxh = ""; // 暂存铜料规格型号 （判断铜料相同规格型号下的材料数量）
		for (Object obj : list) {
			Map<String, Object> map = (Map<String, Object>) obj;
			Integer id = Integer.parseInt(map.get("id").toString());
			ids.add(id);
			
			PlaProductOrder ppo = dao.get(id);
			String proGgxh = map.get("proGgxh").toString();
			//String str = map.get("productPartLen").toString();
			String str = ppo.getProductPartLen();
			String color = map.get("proColor").toString();
			String proCraftCode = map.get("proCraftCode").toString();
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine se = manager.getEngineByName("js");
			//Double length = (Double) se.eval(str);
			Double length = Double.parseDouble( se.eval(str).toString());
			List<CraProSeqRelation> relation = serCra.getCraProSeqRelation(proGgxh);
			if(relation == null || relation.size() == 0 )
				throw new Exception("请配置该产品工艺的参数");
			Integer core = relation.get(0).getCore();
			//List<PlaUnitConMater> ll = serviceu.getPlaUnitConMater(proGgxh); 
			//TODO 这里的单位消耗材料量从BOM材料详情查询
			//CraCraftProduct craftProduct = (CraCraftProduct) cpmservice.getCraCraftProduct(proGgxh,color); //得到产品工艺
			/*for (CraBomProductMater cb : bomProductMater) {
				// TODO 根据BOM表的数据计算 
				cb.getMcpu();
			}*/
			
			//List<CraBomProductMater> ll = sbpservice.getCraBomProductMater(craftProduct.getProCraftCode());
			List<CraBomProductMater> ll = sbpservice.getCraBomProductMater(proCraftCode);
			for (int i = 0; i < ll.size(); i++) {
				try {
					StoreMrecord mrecord = (StoreMrecord) serStore.getStoreMrecord(ll.get(i).getMaterId()).get(0);
					if ("胶料".equals(mrecord.getMaterName())) {
						if (jlobjGgxh.equals(mrecord.getMaterGgxh())) {
							Jl += length * ll.get(i).getMcpu().doubleValue() * core;
						} else {
							Jl = length * ll.get(i).getMcpu().doubleValue() * core;
						}
						//jlStock = serStore.getActStock(ll.get(i).getObjGgxh()); // 得到胶料的实际库存
						jlStock = mrecord.getActStock(); //得到胶料的实际库存
						jlobjGgxh = mrecord.getMaterGgxh();
					} else if ("铜料".equals(mrecord.getMaterName())) {
						if (cuobjGgxh.equals(mrecord.getMaterGgxh())) {
							Cu += length * ll.get(i).getMcpu().doubleValue() * core;
						} else {
							Cu = length * ll.get(i).getMcpu().doubleValue() * core;
						}
						cuStock = mrecord.getActStock(); // 得到铜料的实际库存
						cuobjGgxh = mrecord.getMaterGgxh();
					}
					lis.add(ll.get(i));
				} catch (Exception e) {
					continue;
				}
				
			}
			if (Jl > jlStock) {
				msg = "产品规格为：" + proGgxh + "的胶料库存不足!少" + (Jl - jlStock) + "kg";
				if (Cu >= cuStock) {
					msg = "产品规格为：" + proGgxh + "的胶料铜料库存不足!铜料少" + (Cu - cuStock)
							+ "kg,胶料少" + (Jl - jlStock) + "kg";
					return JsonWrapper.failureWrapperMsg(lis, msg);
				}
				return JsonWrapper.failureWrapperMsg(lis, msg);
			} else if (Cu > cuStock) {
				msg = "产品规格为：" + proGgxh + "铜料库存不足!少" + (Cu - cuStock) + "kg";
				return JsonWrapper.failureWrapperMsg(lis, msg);
			}
		}
		msg = "材料充足，请排产";
		return JsonWrapper.successWrapper(ids, msg);
	}
	/**
	 * 
	 * @param scList
	 * @return
	 */
	public List<PlaProductOrder> findByIds(List<Integer> scList) {
		String hql = " from PlaProductOrder where id in (:id) ";
		@SuppressWarnings("unchecked")
		List<PlaProductOrder> ll = getEntityDaoInf().getHibernateTemplate().findByNamedParam(hql, "id", scList);
		return ll;
	}

	public List<PlaProductOrder> findByOrderState2(String is_flag) {
		String hql = " from PlaProductOrder where is_flag = ? ";
		@SuppressWarnings("unchecked")
		List<PlaProductOrder> list = dao.getHibernateTemplate().find(hql,is_flag);
		return list;
	}
	
	public void test(){
		dao.plaProductOrderCode();
	}
	
	//???
	@Deprecated
	public List<PlaProductOrder> findById(Integer id){
		String hql = " from PlaProductOrder where id = ? ";
		@SuppressWarnings("unchecked")
		List<PlaProductOrder> list = dao.createQuery(hql, id).list();
		return list;
	}

	/**   
	 * @Description: 分页查询   
	 * @param page
	 * @param map
	 * @return         
	 */ 
	public Page getPageList(Page page, Map<String, String> map) {
		// TODO Auto-generated method stub
		String format = DateUtil.format(new Date(), "yyyy-MM-dd");
		//大于当前时间 ,, 还有没完成的
		String hql ="from PlaProductOrder where  (demandDate >= '"+format+"') or (amount > completeAmount ) ";
		StringBuilder sb = new StringBuilder(hql);
		if (map != null) {
			
		}
		String oderSql = " order by demandDate";
		sb.append(oderSql);
		Page pageQuery = page;
		try {
			 pageQuery = dao.pageQuery(sb.toString(), page.getPageNo(), page.getPagesize());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return pageQuery;
	}

}
