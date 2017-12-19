package com.css.business.web.subsysstore.storeManage.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.css.business.web.subsysstore.bean.StoreInventory;
import com.css.business.web.subsysstore.bean.StoreInventoryDetailed;
import com.css.business.web.subsysstore.bean.StoreObj;
import com.css.business.web.subsysstore.bean.StoreReturn;
import com.css.business.web.subsysstore.bean.StoreScrapRecord;
import com.css.business.web.subsysstore.storeManage.dao.StoreInventoryDetailedDAO;
import com.css.business.web.subsysstore.storeManage.dao.StoreInventoryManageDAO;
import com.css.business.web.subsysstore.storeManage.vo.StoreInventoryDetailedVo;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
@Service("storeInventoryManageService")
public class StoreInventoryManageService extends BaseEntityManageImpl<StoreInventory, StoreInventoryManageDAO>{
	
	public static final String PRIFX = " createBy = 'york' ";
	
	@Autowired
	private StoreInventoryManageDAO storeInventoryManageDAO;
	@Autowired
	private StoreInventoryDetailedDAO storeInventoryDetailedDAO;
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public StoreInventoryManageDAO getEntityDaoInf() {
		return storeInventoryManageDAO;
	}

	public void findByQueryPage(HttpServletRequest request, Page page) {
		String inv_time = request.getParameter("inv_time");
		String inv_materiel = request.getParameter("inv_materiel");
		String hql = " from StoreInventory where 1=1 ";
		List<Object> list = new ArrayList<Object>();
		if(inv_materiel != null && inv_materiel.length()!=0){
			hql += " and inv_materiel = ? ";
			list.add(inv_materiel);
		}
		if(inv_time!=null&&inv_time.length()!=0){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date d = null;
			try {
				d = df.parse(inv_time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar ca = Calendar.getInstance();
			ca.setTime(d);
			int year = ca.get(Calendar.YEAR);
			int month = ca.get(Calendar.MONTH);
			int day = ca.get(Calendar.DAY_OF_MONTH);
			ca.set(year, month, day, 0, 0, 0);
			Timestamp start = new Timestamp(ca.getTimeInMillis());
			ca.set(year, month, day, 23, 59, 59);
			Timestamp end = new Timestamp(ca.getTimeInMillis());
			hql += " and  inv_time >= ? and inv_time <= ?";
			list.add(start);
			list.add(end);
		}
		storeInventoryManageDAO.findByQueryPage(hql,page,list);
		
	}
	/**
	 * 盘点明细数据列表
	 * @param request
	 * @param page
	 */
	public void findByQueryList(HttpServletRequest request, Page page) throws Exception {
		/**
		 * 目前数据库伪数据比较混乱，所有暂时查询创建人为york的记录
		 * create_by = york
		 * 最终需要将StoreInventoryDetailedVo的一个集合放到page里边，因为里边只能放List<Object>,所有有一个结果集，
		 * 
		 */
		List<Object> resultList = new ArrayList<Object>();
		
		String inv_materiel = request.getParameter("inv_materiel");//盘点材料
		String inv_time = request.getParameter("inv_materiel");//盘点时间
		Date inv_times = null;
		if(inv_time!=null&&inv_time.length()!=0){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				inv_times = df.parse(inv_time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String materie = request.getParameter("materie");//材料
	    String ggxh = request.getParameter("ggxh");//规格型号
	    String color = request.getParameter("color");//颜色
	    //TODO ,暂时不支持这个盘点结果属性的查询，因为需要输入实际的盘点值，是否缺料，是需要实际盘点值之后，才能计算得出
//	    String inventory = request.getParameter("inventory");//盘点结果类型
	    
	    get(page, resultList, inv_materiel, inv_times, materie, ggxh, color);
	}
	/**
	 * 方便饼状图调用数据
	 * @param page
	 * @param resultList
	 * @param inv_materiel
	 * @param inv_times
	 * @param materie
	 * @param ggxh
	 * @param color
	 */
	public void get(Page page, List<Object> resultList, String inv_materiel,
			Date inv_times, String materie, String ggxh, String color) {
		// 根据材料、规格型号、颜色查询盘点日期之前的所有入库记录
	    List<StoreObj> inList = getInInvData(inv_materiel, inv_times, materie, ggxh, color);
		// 根据材料、规格型号、颜色查询盘点日期之前的所有出库记录
	    List<StoreObj> outList = getOutInvData(inv_materiel, inv_times,
				materie, ggxh, color);
		// 根据材料、规格型号、颜色查询盘点日期之前的所有退料记录
		List<StoreReturn> backList = getBackMaterialData(inv_materiel,
				inv_times, materie, ggxh, color);
		// 根据材料、规格型号、颜色查询盘点日期之前的废料信息
		List<StoreScrapRecord> storeScrapRecordList = getScrapData(
				inv_materiel, inv_times, materie, ggxh, color);
		//TODO 根据材料、规格型号、颜色查询盘点日期的所有真是盘点数据，查询表store_inventory_detailed;storeInventoryDetailedDAO
		List<StoreInventoryDetailed> storeInventoryDetailedList = getInvResult(
				inv_materiel, inv_times, materie, ggxh, color);
		// 这里是一个数据整合的过程，最终转换之后，放入resultList;
		getResultData(page, resultList, inv_times, inList, outList, backList, storeScrapRecordList, storeInventoryDetailedList);
	}

	@SuppressWarnings("unchecked")
	private List<StoreInventoryDetailed> getInvResult(String inv_materiel,
			Date inv_times, String materie, String ggxh, String color) {
		List<StoreInventoryDetailed> storeInventoryDetailedList = null;
		StringBuffer hql = new StringBuffer(" from StoreInventoryDetailed where 1=1 ");
	    List<Object> list = new ArrayList<Object>();
	    if(inv_materiel != null && !inv_materiel.isEmpty()){//盘点材料
	    	hql.append(" and inv_materiel = ? ");
	    	list.add(inv_materiel);
	    }
	    if(inv_times!=null ){//盘点时间
	    	hql.append(" and  inv_time < ? ");
	    	list.add(inv_times);
	    }
	    if(materie!=null && !materie.isEmpty()){//查询原材料
	    	hql.append(" and inv_materiel = ? ");
	    	list.add(materie);
	    }
	    if(ggxh!=null && !ggxh.isEmpty()){//规格型号
	    	hql.append(" and inv_model = ? ");
	    	list.add(ggxh);
	    }
	    if(color!=null && !color.isEmpty()){//颜色
	    	hql.append(" and inv_color = ? ");
	    	list.add(color);
	    }
	    //TODO 这句代码正式上线要去掉，是为了不和他人数据混乱
	    hql.append(" and "+PRIFX);
	    if(list.isEmpty()){
	    	storeInventoryDetailedList = storeInventoryDetailedDAO.getHibernateTemplate().find(hql.toString());
	    }else{
	    	Object[] objs = list.toArray();
	    	storeInventoryDetailedList = storeInventoryDetailedDAO.getHibernateTemplate().find(hql.toString(),objs);
	    }
		return storeInventoryDetailedList;
	}
	/**
	 * 封装返回最终数据结果集
	 * @param page
	 * @param resultList
	 * @param inv_times
	 * @param inList
	 * @param outList
	 * @param backList
	 * @param storeScrapRecordList
	 */
	private void getResultData(Page page, List<Object> resultList,
			Date inv_times, List<StoreObj> inList, List<StoreObj> outList,
			List<StoreReturn> backList,
			List<StoreScrapRecord> storeScrapRecordList,List<StoreInventoryDetailed> storeInventoryDetailedList) {
		//入库记录,出库记录、退料记录、报废记录
		Map<String,Double[]> map = new HashMap<String,Double[]>();
		Map<String,StoreInventoryDetailedVo> voMap = new HashMap<String,StoreInventoryDetailedVo>();
		//装入入库记录
		for(StoreObj obj : inList){
			
			String key = obj.getObjSort();//物品名称，如：铜杆，胶料
			key = key + obj.getObjGgxh();//规格型号
			key = key + obj.getColor();//颜色
			
			if(map.get(key)==null){
				map.put(key, new Double[4]);
			}
			if(voMap.get(key)==null){
				voMap.put(key, new StoreInventoryDetailedVo());
				voMap.get(key).setMateriel(obj.getObjSort());
				voMap.get(key).setModel(obj.getObjGgxh());
				voMap.get(key).setColor(obj.getColor());
//				voMap.get(key).setInv_time(new Timestamp(inv_times.getTime()));
			}
			map.get(key)[0] = (map.get(key)[0]==null?0:map.get(key)[0])+(obj.getAcount()==null?0:obj.getAcount());
		}
		//装入出库记录
		for(StoreObj obj : outList){
			String key = obj.getObjSort();//物品名称，如：铜杆，胶料
			key = key + obj.getObjGgxh();//规格型号
			key = key + obj.getColor();//颜色
			if(map.get(key)==null){
				map.put(key, new Double[4]);
			}
			if(voMap.get(key)==null){
				voMap.put(key, new StoreInventoryDetailedVo());
			}
			map.get(key)[1] = (map.get(key)[1]==null?0:map.get(key)[1])+(obj.getAcount()==null?0:obj.getAcount());
		}
		//装入退料记录
		for(StoreReturn obj : backList){
			String key = obj.getMaterial();//材料
			key = key + obj.getModel();//型号
			key = key + obj.getColour();//颜色
			if(map.get(key)==null){
				map.put(key, new Double[4]);
			}
			map.get(key)[2] = (map.get(key)[2]==null?0:map.get(key)[2])+(obj.getCount()==null?0:Double.parseDouble(obj.getCount()));
		}
		//装入废料信息
		for(StoreScrapRecord obj : storeScrapRecordList){
			String key = obj.getMaterialName();//材料
			key = key + obj.getModel();//规格型号
			key = key + obj.getColor();//颜色
			if(map.get(key)==null){
				map.put(key, new Double[4]);
			}
			map.get(key)[3] = (map.get(key)[3]==null?0:map.get(key)[3])+(obj.getAmount()==null?0D:Double.parseDouble(obj.getAmount()+"")); 
		}
		//装入实际库存量，盘点人等信息
		for(StoreInventoryDetailed bean : storeInventoryDetailedList){
			String key = bean.getInv_materiel();//盘点材料
			key += bean.getInv_model();//规格型号
			key += bean.getInv_color();//规格型号
			
			if(voMap.get(key) == null){
				voMap.put(key, new StoreInventoryDetailedVo());
			}
			voMap.get(key).setInventory_by(bean.getCreateBy());//盘点人
			voMap.get(key).setInv_time(bean.getInv_time());//盘点时间
			voMap.get(key).setConclusion(bean.getInv_result());//盘点结果
			voMap.get(key).setLackNumber(bean.getInv_lack_number()==null?0:bean.getInv_lack_number());//缺料量
			voMap.get(key).setInv_count(bean.getInv_count()==null?0:bean.getInv_count());//盘点数量
			voMap.get(key).setUnit(bean.getUnit());
			
			
		}
		
		for(String key : map.keySet()){
			if(voMap.get(key)==null){
				throw new RuntimeException("键："+key+";没有对应的入库记录，却有了对应的出库或者退料或者废料记录");
			}
			voMap.get(key).setStorageNumber(map.get(key)[0]==null?0:map.get(key)[0]);//封装入库
			voMap.get(key).setTheLibrary(map.get(key)[1]==null?0:map.get(key)[1]);//出库数量
			voMap.get(key).setReturnOfMaterial(map.get(key)[2]==null?0:map.get(key)[2]);//退料
			voMap.get(key).setWaste(map.get(key)[3]==null?0:map.get(key)[3]);//废料
			//计算库存量,入库-出库+退料-报废
			StoreInventoryDetailedVo vo = voMap.get(key);
			Double inventory = (vo.getStorageNumber()==null?0D:vo.getStorageNumber())-
					(vo.getTheLibrary()==null?0D:vo.getTheLibrary())+
					(vo.getReturnOfMaterial()==null?0D:vo.getReturnOfMaterial())-
					(vo.getWaste()==null?0D:vo.getWaste());
			vo.setInventory(inventory);
			if(inv_times!=null){
				vo.setInv_time(new Timestamp(inv_times.getTime()));
			}
		}
		Collection<StoreInventoryDetailedVo> collection = voMap.values();
		for(StoreInventoryDetailedVo vo : collection){
			resultList.add(vo);
		}
		// 这个最终的一个步骤，要讲结果集放入page
		page.setData(resultList);
		page.setTotalCount(resultList.size());
	}
	/**
	 * 报废记录
	 * @param inv_materiel
	 * @param inv_times
	 * @param materie
	 * @param ggxh
	 * @param color
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<StoreScrapRecord> getScrapData(String inv_materiel,
			Date inv_times, String materie, String ggxh, String color) {
		List<StoreScrapRecord> storeScrapRecordList = null;
		StringBuffer hql = new StringBuffer(" from StoreScrapRecord where 1=1 ");
	    List<Object> list = new ArrayList<Object>();
	    if(inv_materiel != null && !inv_materiel.isEmpty()){//盘点材料
	    	hql.append(" and materialName = ? ");
	    	list.add(inv_materiel);
	    }
	    if(inv_times!=null ){//盘点时间
	    	hql.append(" and  handleDate < ? ");
	    	list.add(inv_times);
	    }
	    if(materie!=null && !materie.isEmpty()){//查询原材料
	    	hql.append(" and materialName = ? ");
	    	list.add(materie);
	    }
	    if(ggxh!=null && !ggxh.isEmpty()){//规格型号
	    	hql.append(" and model = ? ");
	    	list.add(ggxh);
	    }
	    if(color!=null && !color.isEmpty()){//颜色
	    	hql.append(" and color = ? ");
	    	list.add(color);
	    }
	    //TODO 这句代码正式上线要去掉，是为了不和他人数据混乱
	    hql.append(" and "+PRIFX);
	    if(list.isEmpty()){
	    	storeScrapRecordList = storeInventoryManageDAO.getHibernateTemplate().find(hql.toString());
	    }else{
	    	Object[] objs = list.toArray();
	    	storeScrapRecordList = storeInventoryManageDAO.getHibernateTemplate().find(hql.toString(), objs);
	    }
		return storeScrapRecordList;
	}
	/**
	 * 退料记录查询
	 * @param inv_materiel
	 * @param inv_times
	 * @param materie
	 * @param ggxh
	 * @param color
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<StoreReturn> getBackMaterialData(String inv_materiel,
			Date inv_times, String materie, String ggxh, String color) {
		List<StoreReturn> backList = null;
		StringBuffer hql = new StringBuffer(" from StoreReturn where 1=1 ");
	    List<Object> list = new ArrayList<Object>();
	    if(inv_materiel != null && !inv_materiel.isEmpty()){//盘点材料
	    	hql.append(" and material = ? ");
	    	list.add(inv_materiel);
	    }
	    if(inv_times!=null ){//盘点时间
	    	hql.append(" and  storeDate < ? ");
	    	list.add(inv_times);
	    }
	    if(materie!=null && !materie.isEmpty()){//查询原材料
	    	hql.append(" and material = ? ");
	    	list.add(materie);
	    }
	    if(ggxh!=null && !ggxh.isEmpty()){//规格型号
	    	hql.append(" and model = ? ");
	    	list.add(ggxh);
	    }
	    if(color!=null && !color.isEmpty()){//颜色
	    	hql.append(" and colour = ? ");
	    	list.add(color);
	    }
	    hql.append(" and "+PRIFX);
	    if(list.isEmpty()){
	    	backList = storeInventoryManageDAO.getHibernateTemplate().find(hql.toString());
	    }else{
	    	Object[] obj = list.toArray();
	    	backList = storeInventoryManageDAO.getHibernateTemplate().find(hql.toString(), obj);
	    }
	    
		return backList;
	}
	/**
	 * 入库记录
	 * @param inv_materiel
	 * @param inv_times
	 * @param materie
	 * @param ggxh
	 * @param color
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<StoreObj> getInInvData(String inv_materiel, Date inv_times,
			String materie, String ggxh, String color) {
		
	    StringBuffer hql = new StringBuffer(" from StoreObj where 1=1 ");
	    List<Object> list = new ArrayList<Object>();
	    if(inv_materiel != null && !inv_materiel.isEmpty()){//盘点材料
	    	hql.append(" and objSort = ? ");
	    	list.add(inv_materiel);
	    }
	    if(inv_times!=null ){//盘点时间
	    	hql.append(" and  inDate < ? ");
	    	list.add(inv_times);
	    }
	    if(materie!=null && !materie.isEmpty()){//查询原材料
	    	hql.append(" and objSort = ? ");
	    	list.add(materie);
	    }
	    if(ggxh!=null && !ggxh.isEmpty()){//规格型号
	    	hql.append(" and objGgxh = ? ");
	    	list.add(ggxh);
	    }
	    if(color!=null && !color.isEmpty()){
	    	hql.append(" and objGgxh = ? ");
	    	list.add(color);
	    }
	    hql.append(" and inoutType = '入库' and materialType = '原材料' ");
	    hql.append(" and "+PRIFX);
	    List<StoreObj> resultList = null;
	    if(list.isEmpty()){
	    	resultList = storeInventoryManageDAO.getHibernateTemplate().find(hql.toString());
	    }else{
	    	resultList = storeInventoryManageDAO.getHibernateTemplate().find(hql.toString(),list.toArray());
	    }
		return resultList;
	}
	/**
	 * 出库记录
	 * @param inv_materiel
	 * @param inv_times
	 * @param materie
	 * @param ggxh
	 * @param color
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<StoreObj> getOutInvData(String inv_materiel, Date inv_times,
			String materie, String ggxh, String color) {
		
	    StringBuffer hql = new StringBuffer(" from StoreObj where 1=1 ");
	    List<Object> list = new ArrayList<Object>();
	    if(inv_materiel != null && !inv_materiel.isEmpty()){//盘点材料
	    	hql.append(" and objSort = ? ");
	    	list.add(inv_materiel);
	    }
	    if(inv_times!=null ){//盘点时间
	    	hql.append(" and  inDate < ? ");
	    	list.add(inv_times);
	    }
	    if(materie!=null && !materie.isEmpty()){//查询原材料
	    	hql.append(" and objSort = ? ");
	    	list.add(materie);
	    }
	    if(ggxh!=null && !ggxh.isEmpty()){//规格型号
	    	hql.append(" and objGgxh = ? ");
	    	list.add(ggxh);
	    }
	    if(color!=null && !color.isEmpty()){
	    	hql.append(" and color = ? ");
	    	list.add(color);
	    }
	    hql.append(" and inoutType = '出库' and materialType = '原材料' ");
	    
	    hql.append(" and "+PRIFX);
		List<StoreObj> resultList = null;
	    if(list.isEmpty()){
	    	resultList = storeInventoryManageDAO.getHibernateTemplate().find(hql.toString());
	    }else{
	    	Object[] objs = list.toArray();
	    	resultList = storeInventoryManageDAO.getHibernateTemplate().find(hql.toString(), objs);
	    }
		return resultList;
	}

}
