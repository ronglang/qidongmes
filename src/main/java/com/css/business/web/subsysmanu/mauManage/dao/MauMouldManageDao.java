package com.css.business.web.subsysmanu.mauManage.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.css.business.web.subsysmanu.bean.MauMould;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;

/**
 * @Title: MauMouldManageDao.java
 * @Package com.css.business.web.subsysmanu.mauManage.dao
 * @Description: 模具管理dao
 * @author TG
 * @date 2017年9月08日 上午11：25
 * @company SMTC
 */
@Repository("mauMouldManageDao")
public class MauMouldManageDao extends BaseEntityDaoImpl<MauMould>{
	
	
	/**
	 * 获取模具基础信息列表，附带查询分页
	 * @param page
	 * @param map
	 * @return
	 */
	public Page getMouldListDao(Page page, Map<String, String> map) {
		StringBuilder sql =new StringBuilder("from MauMould where 1=1 and isParent = '0' ");
		if(map != null){
			if(null!=map.get("mouGgxh")&&!"".equals(map.get("mouGgxh"))){
				String mouGgxh=map.get("mouGgxh").trim();
				sql.append(" and mouGgxh like '%"+mouGgxh+"%' ");
			}
			if(null!=map.get("mouCode")&&!"".equals(map.get("mouCode"))){
				String macCode=map.get("mouCode").trim();
				sql.append(" and mouCode like '%"+macCode+"%' ");
			}
			if(null!=map.get("status")&&!"".equals(map.get("status"))){
				sql.append(" and status='"+map.get("status")+"' ");
			}
			
		}
		
		sql.append("  ORDER BY createDate DESC,status ASC");
		Page pageQuery = this.pageQuery(sql.toString(), page.getPageNo(), page.getPagesize());
		
		return pageQuery;
	}
	
	/**
	 * 获取模具基础信息的状态
	 * @return
	 */
	public List<String> getMouldStatusDao(){
		String hql = "select DISTINCT status from MauMould where isParent = '0' ";
		@SuppressWarnings("unchecked")
		List<String> list = this.createQuery(hql).list();
		return list;
	}
	
	/**
	 * 添加模具基础信息
	 * @param param
	 * @return
	 */
	public Map<String,String> saveDataDao(HttpServletRequest request,Map<String,String> map){
		Map<String,String> maps = new HashMap<>();
		/*****查询是否存在此模具*****/
		//String hql = "select * from mau_mould where is_parent = '0' and mou_name = ? and mou_code = ?";
		
//		List<MauMould> list = this.listSQLQuery(hql,MauMould.class, map.get("mouName"),map.get("mouCode"));
		//根据编码唯一来判断，名称可以重复，编码不可以重复
		
		String hql = "select * from mau_mould where is_parent = '0' and mou_code = ?";
		@SuppressWarnings("unchecked")
		List<MauMould> list = this.listSQLQuery(hql,MauMould.class, map.get("mouCode"));
		
		if(list.size() > 0){
			maps.put("error", "该模具已存在，编码唯一！");
			return maps;
		}
		//保存
		MauMould mm = new MauMould();
		
		mm.setMouGgxh(map.get("mouGgxh"));
		mm.setMouCode(map.get("mouCode"));
		mm.setMouType(map.get("mouType"));
		mm.setMouSize(map.get("mouSize"));
		mm.setMouSupply(map.get("mouSupply"));
		//mm.setNumber(Integer.valueOf(map.get("number")));
		//mm.setUnit(map.get("unit"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			mm.setInStoreTime(sdf.parse(map.get("inStoreTime")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		mm.setMouUse(map.get("mouUse"));
		mm.setMouLineUse(map.get("mouLineUse"));
		
		mm.setStatus("正常");
		mm.setIsParent("0");
		mm.setIsReject("否");
		
		SysUser user = SessionUtils.getUser(request);
		mm.setCreateBy(user.getAccount());
		mm.setCreateDate(new Date());
		try{
			this.save(mm);
			maps.put("success", "添加成功！");
		}catch(Exception e){
			e.printStackTrace();
			maps.put("error", "添加失败！");
		}
		return maps;
	}
	
	
	/**
	 * 添加维修计划
	 * @param param
	 * @return
	 * @throws ParseException 
	 */
	public boolean saveMaintainDataDao(HttpServletRequest request,Map<String,String> map) throws ParseException{
		if(map != null){
			MauMould mm = new MauMould();
			mm.setMouGgxh(map.get("mouGgxh"));
			mm.setMouCode(map.get("mouCode"));
			mm.setMouType(map.get("mouType"));
			mm.setMouSize(map.get("mouSize"));
			mm.setMouSupply(map.get("mouSupply"));
			//mm.setNumber(Integer.valueOf(map.get("number")));
			//mm.setUnit(map.get("unit"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");
			try {
				mm.setInStoreTime(sdf.parse(map.get("inStoreTime")));
				mm.setMouUse(map.get("mouUse"));
				mm.setMouLineUse(map.get("mouLineUse"));
				SysUser user = SessionUtils.getUser(request);
				mm.setCreateBy(user.getAccount());
				mm.setCreateDate(new Date());
				mm.setIsParent("1");
				mm.setStatus("维修中");
				mm.setMaintainTime(sdfs.parse(map.get("maintainTime")));
				this.save(mm);
				return true;
			} catch (ParseException e1) {
				e1.printStackTrace();
				return false;
			}
			
		}else{
			return false;
		}
		
	}
	/**
	 * 添加维修计划，成功后改变模具基础信息状态
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	public void updateStatusDao(Map<String,String> map) throws Exception{
		
		MauMould mm = new MauMould();
		mm.setId(Integer.valueOf(map.get("id")));
		mm.setStatus("维修中");
		this.updateByCon(mm, false);
	}
	
	/**
	 * 保存修改后的数据
	 * @param param
	 * @return
	 */
	public Map<String,String> updateSaveDataDao(Map<String,String> map){
		MauMould mm = new MauMould();
		Map<String,String> maps = new HashMap<>();
		if(map != null && !"".equals(map.toString())){
			mm.setId(Integer.valueOf(map.get("id")));
			
			mm.setMouGgxh(map.get("mouGgxh"));
			mm.setMouCode(map.get("mouCode"));
			mm.setMouType(map.get("mouType"));
			mm.setMouSize(map.get("mouSize"));
			mm.setMouSupply(map.get("mouSupply"));
			//mm.setNumber(Integer.valueOf(map.get("number")));
			//mm.setUnit(map.get("unit"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				mm.setInStoreTime(sdf.parse(map.get("inStoreTime")));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			mm.setMouUse(map.get("mouUse"));
			mm.setMouLineUse(map.get("mouLineUse"));
			
			try {
				this.updateByCon(mm, false);
				maps.put("success", "修改成功！");
			} catch (Exception e) {
				maps.put("error", "修改失败");
				e.printStackTrace();
			}
		}
		return maps;
	}
	
	/**
	 * 删除模具基础数据
	 * @param id 表id
	 */
	public void clearDataDao(Integer id){
		MauMould mm = new MauMould();
		mm.setId(id);
		this.remove(mm);
	}
	
	/**
	 * 保存报废数据
	 * @param param
	 */
	public void saveRejectDataDao(Map<String,String> map){
		MauMould mm = new MauMould();
		if(map != null && !"".equals(map.toString())){
			mm.setId(Integer.parseInt(map.get("id")));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				mm.setRejectTime(sdf.parse(map.get("rejectTime")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			mm.setRejectReason(map.get("rejectReason"));
			mm.setIsReject("是");
			mm.setStatus("报废");
			try {
				this.updateByCon(mm, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	/************************************模具维修记录操作方法*************************************/
	
	/**
	 * 获取模具维修记录的状态
	 * @return
	 */
	public List<String> getMouldMaintainStatusDao(){
		String hql = "select DISTINCT status from MauMould where isParent = '1' ";
		@SuppressWarnings("unchecked")
		List<String> list = this.createQuery(hql).list();
		return list;
	}
	
	/**
	 * 获取模具维修记录信息列表，附带查询分页
	 * @param page
	 * @param map
	 * @return
	 */
	public Page getMouldMaintainListDao(Page page, Map<String, String> map) {
		StringBuilder sql =new StringBuilder("from MauMould where isParent = '1' ");
		if(map != null){
//			if(null!=map.get("mouName")&&!"".equals(map.get("mouName"))){
//				String mouName=map.get("mouName").trim();
//				sql.append(" and mouName like '%"+mouName+"%' ");
//			}
			if(null!=map.get("mouCode")&&!"".equals(map.get("mouCode"))){
				String macCode=map.get("mouCode").trim();
				sql.append(" and mouCode like '%"+macCode+"%' ");
			}/*
			if(null!=map.get("status")&&!"".equals(map.get("status"))){
				sql.append(" and status='"+map.get("status")+"' ");
			}
			if(null!=map.get("maintainBy")&&!"".equals(map.get("maintainBy"))){
				sql.append(" and maintainBy='"+map.get("maintainBy")+"' ");
			}
			if(null!=map.get("planStartTime")&&!"".equals(map.get("planStartTime"))){
				sql.append(" and planMaintainTime >= '"+map.get("planStartTime")+"' ");
			}
			if(null!=map.get("planStartTimes")&&!"".equals(map.get("planStartTimes"))){
				sql.append(" and planMaintainTime <= '"+map.get("planStartTimes")+"' ");
			}
			if(null!=map.get("factStartTime")&&!"".equals(map.get("factStartTime"))){
				sql.append(" and factMaintainTime >= '"+map.get("factStartTime")+"' ");
			}
			if(null!=map.get("factStartTimes")&&!"".equals(map.get("factStartTimes"))){
				sql.append(" and factMaintainTime <= '"+map.get("factStartTimes")+"' ");
			}*/
		}
		
		sql.append("  ORDER BY status ASC,createDate DESC");
		Page pageQuery = this.pageQuery(sql.toString(), page.getPageNo(), page.getPagesize());
		
		return pageQuery;
	}
	
	/**
	 * 点击开始维修
	 * @param id
	 */
	public void startMaintainDao(Integer id){
		MauMould mm = new MauMould();
		mm.setId(id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//mm.setFactMaintainTime(d);
		mm.setStatus("维修中");
		try {
			this.updateByCon(mm, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 点击完成维修
	 * @param id
	 * @throws Exception 
	 */
	public void overMaintainDataDao(Map<String,String> map) throws Exception{
//		MauMould mm = new MauMould();
//		mm.setId(Integer.valueOf(map.get("id")));
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date = sdf.parse(map.get("endMaintainTime"));
//		mm.setEndMaintainTime(date);
//		mm.setMaintainBy(map.get("maintainBy"));
//		mm.setRemark(map.get("remark"));
//		mm.setStatus("已完成");
		
	    //this.updateByCon(mm, false);
	    
	    String hql = "from MauMould where mouCode = '"+map.get("mouCode")+"' and status = '维修中' and isParent = '1' ";
		@SuppressWarnings("unchecked")
		List<MauMould> list = this.createQuery(hql).list();
		Integer id = list.get(0).getId();
		MauMould mm = new MauMould();
		mm.setId(id);
		mm.setMaintainVender(map.get("maintainVender"));
		mm.setMaintainResult(map.get("maintainResult"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		mm.setResultTime(sdf.parse(map.get("resultTime")));
		mm.setChangeRecord(map.get("changeRecord"));
		mm.setStatus("正常");
		
		this.updateByCon(mm, false);
	}
	
	/**
	 * 点击完成维修后修改模具状态为正常
	 * @param id
	 * @throws Exception 
	 */
	public void updateOverStatusDao(Map<String,String> map) throws Exception{
		
		MauMould mm = new MauMould();
		mm.setId(Integer.parseInt(map.get("id")));
		mm.setStatus("正常");
		this.updateByCon(mm, false);
		
	}
	
	/**
	 * 删除模具维修记录信息
	 * @param id
	 */
	public void clearMaintainDataDao(Integer id) {
		MauMould mm = new MauMould();
		mm.setId(id);
		this.remove(mm);
	}
	
	/**
	 * 显示所有的模具编码信息
	 * @return
	 */
	public List<MauMould> showAllDataDao(){
		String hql = "from MauMould where isParent = '0' ";
		@SuppressWarnings("unchecked")
		List<MauMould> list = this.createQuery(hql).list();
		return list;
	}
	
	/**
	 * 根据模具code获取所有的模具name
	 * @return
	 */
	public List<MauMould> showAllMauNameDataDao(String mouCode){
		String hql = "from MauMould where isParent = '0' and mouCode = '"+mouCode+"' ";
		@SuppressWarnings("unchecked")
		List<MauMould> list = this.createQuery(hql).list();
		return list;
	}
	
	/**
	 * 保存修改后的维修记录信息
	 * @param map
	 * @throws Exception 
	 */
	public void updateSaveMaintainDataDao(MauMould  mm) throws Exception{
//		MauMould mm = new MauMould();
//		mm.setId(Integer.valueOf(map.get("id")));
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date = sdf.parse(map.get("planMaintainTime"));
//		mm.setPlanMaintainTime(date);
		
		this.updateByCon(mm, false);
	}
	
	
	
	
	
	
}
