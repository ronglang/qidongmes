package com.css.business.web.subsyscraft.craManage.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.subsyscraft.bean.CraCraftProduct;
import com.css.business.web.subsyscraft.bean.CraGatheringMode;
import com.css.business.web.subsyscraft.bean.CraProSeqRelation;
import com.css.business.web.subsyscraft.bean.CraRouteSeq;
import com.css.business.web.subsyscraft.craManage.dao.CraCraftProductManageDAO;
import com.css.business.web.subsyscraft.craManage.dao.CraGatheringModeManageDao;
import com.css.business.web.subsyscraft.craManage.dao.CraProSeqRelationManageDao;
import com.css.business.web.subsyscraft.craManage.dao.CraRouteSeqManageDAO;
import com.css.business.web.subsyscraft.craManage.dao.CraSeqMiddleManageDAO;
import com.css.business.web.subsysmanu.bean.MauProduct;
import com.css.business.web.subsyssell.vo.OrderProcessFlowVo;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("craCraftProductManageService")
public class CraCraftProductManageService extends BaseEntityManageImpl<CraCraftProduct, CraCraftProductManageDAO> {
	@Resource(name="craCraftProductManageDAO")
	//@Autowired
	private CraCraftProductManageDAO dao;
	
	@Autowired
	private CraSeqMiddleManageDAO craSeqMiddleManageDAO;
	
	
	//测试用
	//private String createBy = "JS";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private CraProSeqRelationManageDao craProSeqRelationManageDao;
	
	@Autowired
	private CraRouteSeqManageDAO craRouteSeqManageDAO;
	
	@Autowired
	private CraGatheringModeManageDao craGatheringModeManageDao;//集绞方式表
	
	@Override
	public CraCraftProductManageDAO getEntityDaoInf() {
		return dao;
	}
	
	public List<CraCraftProduct> getCraCraftProduct(String proGgxh,String color){
		String hql = " from CraCraftProduct where proGgxh = ? AND proColor = ? ";
		@SuppressWarnings("unchecked")
		List<CraCraftProduct> list = dao.createQuery(hql, proGgxh,color).list();
		return list;
	}
	
	public List<CraCraftProduct> getCraCraftProductCode(){
		String hql = " from CraCraftProduct ";
		@SuppressWarnings("unchecked")
		List<CraCraftProduct> list = dao.createQuery(hql).list();
		return list;
	}
	
	/**
	 * 铜鼓jdbc的方式进行批量删除，效果会好很多
	 * @param list
	 */
	@Transactional(readOnly=false)
	public void delList(final List<Integer> list){
		String sql = " delete from cra_craft_product where id = ? ";
		jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, list.get(i));
			}
			
			@Override
			public int getBatchSize() {
				return list.size();
			}
			
		});
	}
	/**
	 * 录入产品工艺，保存方法（暂未考虑修改，商量为，如果有错，就删除了重新录入，不提供修改功能）
	 * @param bean
	 * @param map
	 */
	@Transactional(readOnly=false)
	public void saveBean(CraCraftProduct bean ,List<Map<String,String>> gatherModeList){
//		boolean boo = true;
//		if(boo){
//			return;
//		}
		if(bean.getId()!=null){//修改,强烈建议此处不需要修改功能
//			dao.save(bean);
		}else{//新增
			
			dao.save(bean);//保存产品工艺本身
			String proCraftCode = bean.getProCraftCode();//产品工艺编码
			String proGgxh = bean.getProGgxh();//产品规格型号
			//String proColor = bean.getProColor();//产品颜色
			//TODO 新增该产品工艺对应的工艺、工序、关系表，新增该产品的工艺、工序记录表
			List<CraProSeqRelation> craProSeqRelationList = new ArrayList<CraProSeqRelation>();
			//1.工艺路线
			String routeCode = bean.getRouteCode();//工艺路线编码
			//2.通过工艺路线编码，获取工艺路线明细(对应的工艺),
			List<CraRouteSeq> list = craRouteSeqManageDAO.getRouteSeqList(routeCode);
			
			for(CraRouteSeq b : list ){
				CraProSeqRelation relation = new CraProSeqRelation();
				relation.setCreateBy("创建人");
				relation.setCreateDate(new Date());
				relation.setProCraftCode(proCraftCode);//产品工艺编码
				relation.setRouteCode(routeCode);//工艺路线编码
				relation.setSeqCode(b.getSeqCode());//工序编码，在list中已经按照sort排序，保证工序是顺序的
				relation.setcCode(null);//工艺编码，相当于是下级的 TODO ？？？，怎么保存,这个位置暂定为空
				relation.setProGgxh(proGgxh);//产品规格型号
				//relation.setProColor(proColor);//颜色,TODO ,这个是产品颜色，是否还需要一个字段，叫做工序颜色，没有值可以为空？？
				relation.setPcscRelaCode(craProSeqRelationManageDao.getCode());//主表code,相当于这个 表的编码值，code，从数据库查询
				relation.setCreateBy("创建人");//创建人
				relation.setCreateDate(new Date());
				//需要加入sort字段
				relation.setSeqSort(b.getSort());
				craProSeqRelationList.add(relation);
			}
			/**
			 * 疑问：   1.工序、工艺关系表的主表code与工艺编码，是否是重合了？
			 * 		2.工序、工艺关系表里边如果加一个工艺名称字段，工序、工艺表是否可以不需要了？
			 */
//			//TODO 保存工序记录表（工艺、工序、工艺关系表）
			craProSeqRelationManageDao.saveOrUpdateBatch(craProSeqRelationList);
			
			//TODO 保证在一个事务里边，上面的都做完之后，才会执行下面的这个，保存该产品工艺的集绞信息，到cra_gathering_mode表里边
//			saveCraGatheringMode(bean, proColor, craProSeqRelationList);
			if(gatherModeList==null||gatherModeList.isEmpty()){//没有集绞
//				throw new RuntimeException("没有集绞方式");
			}else{
				/**TODO 包含集绞，保存集绞的相关信息,需要保存对应的参数
				 * 1、产品工艺编码
				 * 2、本表编号
				 * 3、来料规格型号
				 * 4、来料颜色
				 * 5、数量
				 * 6、relationId;
				 */
				List<CraGatheringMode> poList = new ArrayList<CraGatheringMode>();
				for(int i = 0; i<gatherModeList.size(); i++){
					CraGatheringMode modeBean = new CraGatheringMode();//保存该对象是集绞记录
					modeBean.setAmount(Integer.valueOf(gatherModeList.get(i).get("count")));//就是3+12中的3或者12
					modeBean.setCreateBy("创建人");
					modeBean.setCreateDate(new Date());
					modeBean.setGatheringMode(bean.getGatheringMode());
					modeBean.setProColor(gatherModeList.get(i).get("color"));
					modeBean.setProGgxh(gatherModeList.get(i).get("ggxh"));//从map中来
					modeBean.setProCraftCode(proCraftCode);//产品工艺编码
					modeBean.setRelationId(bean.getId());
					poList.add(modeBean);
				}
				
				craGatheringModeManageDao.saveOrUpdateBatch(poList);
			}
		}
	}

//	private void saveCraGatheringMode(CraCraftProduct bean, String proColor,
//			List<CraProSeqRelation> craProSeqRelationList) {
//		String setGround = bean.getSetGround();
//		if("yes".equals(setGround)){//表示包含集绞工序
//			// TODO 新增集绞方式记录
//			List<CraGatheringMode> poList = new ArrayList<CraGatheringMode>();
//			String gatheringMode = bean.getGatheringMode();
//			String arr[] = gatheringMode.split("[+]");
//			for(int i = 0;i < arr.length;i++){
//				//创建对象
//				CraGatheringMode po = new CraGatheringMode();
//				po.setAmount(Integer.parseInt(arr[i].split("[*]")[0]));//数量，表示几根
//				po.setCreateBy("创建人");//创建人
//				po.setCreateDate(new Timestamp(System.currentTimeMillis()));//创建时间
//				po.setGatheringMode(gatheringMode);//集绞方式
//				po.setProColor(proColor);//来料颜色,这个也是需要手动输入的
//				po.setProCraftCode(bean.getProCraftCode());//产品工艺编码
//				po.setProGgxh(null);//半成品规格,这个默认为空，需要用户手动输入
//				po.setRelationId(craProSeqRelationList.get(0).getId());
//				poList.add(po);
//			}
//			craGatheringModeManageDao.saveOrUpdateBatch(poList);
//		}
//	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map[] getProGgxh(){
		String hql = "SELECT DISTINCT proGgxh  from CraCraftProduct ";
		List<Object> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map<String,String> map = new HashMap<String, String>();
			for(int j = 0;j<2;j++){
				if(j==0){
					map.put("text", list.get(i).toString());
				}else{
					map.put("id", i+"");
				}
			}
			str[i] = map;
			
		}
		return str;
	}
	/**
	 * 根据产品规格型号，获取工艺流程
	 * @param proGgxh
	 * @param level表示级别，越大优先级越高
	 * @return
	 */
	public List<OrderProcessFlowVo> getCraftView(String proGgxh,int level,String parentCode){
		List<OrderProcessFlowVo> list = new ArrayList<OrderProcessFlowVo>();
		
		String hql = " from CraCraftProduct where proGgxh = ? ";
		List<CraCraftProduct> craCraftProductList = dao.getHibernateTemplate().find(hql,proGgxh);
		if(craCraftProductList==null||craCraftProductList.isEmpty()){
			throw new RuntimeException("该规格"+proGgxh+"没有对应的产品工艺");
		}
		String routeCode = craCraftProductList.get(0).getRouteCode();//获取工艺路线
		String proCraftCode = craCraftProductList.get(0).getProCraftCode();//获得产品工艺编号
		OrderProcessFlowVo vo = null;
		//封装第一个结果对象
		vo = new OrderProcessFlowVo();
		vo.setRouteCode(routeCode);
		vo.setLevel(level);
		vo.setProGgxh(proGgxh);
		vo.setParentCraftCode(parentCode);
		vo.setCraftCode(proCraftCode);
		list.add(vo);
		//根据产品工艺编号获得来料规格
		hql = " from CraGatheringMode where  proCraftCode = ? ";
		List<CraGatheringMode> gatheringModeList = craGatheringModeManageDao.getHibernateTemplate().find(hql,proCraftCode);
		if(gatheringModeList==null||gatheringModeList.size()==0){
			//表示没有集绞工序了，到这里就结束了
			return list;
		}
		for(CraGatheringMode bean : gatheringModeList ){//每条记录表示一条串行工艺路线
			proGgxh = bean.getProGgxh();//产品规格型号
			list.addAll(getCraftView(proGgxh,level+1,proCraftCode));
		}
		return list;
	}
	
	public static void main(String[] args) {
		String cc = "3*150+2*112";
		String arr [] = cc.split("[+]");
		for (int i = 0; i < arr.length; i++) {
			String temp[] = arr[i].split("[*]");
			for (int j = 0; j < temp.length; j++) {
				System.out.println(temp[j]);
			}
			
		}
	}
	
	public HashMap<String, Object> getProNameFromProType( String proType) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		resultList.clear();
		String sql = "select id, pro_code from  mau_product where p_code='"+proType+"'";
		List<Object> list = dao.createSQLQuery(sql).list(); 
		for(int i =0; i<list.size(); i++){
			Object[] ob = (Object[])list.get(i);
			int id = (Integer)ob[0];
			String name = (String)ob[1];
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", String.valueOf(id));
			map.put("text", name);
			resultList.add(map);
		}
		return JsonWrapper.successWrapper(resultList);
	}	
	
	public HashMap<String, Object> getColorFromProName( String proname) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		resultList.clear();
		String sql = "select  DISTINCT pro_color from  mau_product where p_code='"+proname+"'";
		List<Object> list = dao.createSQLQuery(sql).list(); 
		for(int i =0; i<list.size(); i++){
			String color = (String)list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", String.valueOf(i));
			map.put("text", color);
			resultList.add(map);
		}	
		return JsonWrapper.successWrapper(resultList);
	}
		
	
	public HashMap<String, Object> getGatherGGXH(String p_code) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		resultList.clear();
		String sql = "select pro_ggxh from mau_product where p_code= '"+p_code+"'";
		List<Object> list = dao.createSQLQuery(sql).list(); 
		for(int i =0; i<list.size(); i++){
			String pro_ggxh = (String)list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", String.valueOf(i));
			map.put("text", pro_ggxh);
			resultList.add(map);
		}	
		return JsonWrapper.successWrapper(resultList);
	}
	
	
	
	public HashMap<String, Object> getGatherColor(String pro_code) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		resultList.clear();
		String sql = "select pro_color from mau_product where pro_code='"+pro_code+"'";
		List<Object> list = dao.createSQLQuery(sql).list(); 
		for(int i =0; i<list.size(); i++){
			String color = (String)list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", String.valueOf(i));
			map.put("text", color);
			resultList.add(map);
		}	
		return JsonWrapper.successWrapper(resultList);
		
		
	
	}	
}
