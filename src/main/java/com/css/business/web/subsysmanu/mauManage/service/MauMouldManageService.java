package com.css.business.web.subsysmanu.mauManage.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.subsysmanu.bean.MauMould;
import com.css.business.web.subsysmanu.mauManage.dao.MauMouldManageDao;
import com.css.business.web.subsysplan.plaManage.utils.JsonUtil;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

/**
 * @Title: MauMouldManageService.java
 * @Package com.css.business.web.subsysmanu.mauManage.service
 * @Description: 模具管理Service
 * @author TG
 * @date 2017年9月08日 上午11：27
 * @company SMTC
 */
@Service("mauMouldManageService")
public class MauMouldManageService extends BaseEntityManageImpl<MauMould, MauMouldManageDao>  {
	@Autowired
	private MauMouldManageDao dao;
	
	@Override
	public MauMouldManageDao getEntityDaoInf() {
		return dao;
	}
	
	/**
	 * 获取模具基础信息列表，附带查询分页
	 * @param page
	 * @param map
	 * @return
	 */
	public Page getMouldListService(Page page, Map<String, String> map) {
		return dao.getMouldListDao(page, map);
	}
	
	/**
	 * 获取模具基础信息的状态
	 * @return
	 */
	public List<String> getMouldStatusService(){
		return dao.getMouldStatusDao();
	}
	
	/**
	 * 添加维修计划
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public boolean saveMaintainDataService(HttpServletRequest request,Map<String,String> map) throws Exception{
			boolean flag = dao.saveMaintainDataDao(request, map);
			if(flag){
				//添加开始维修成功后改变基础信息的状态
				dao.updateStatusDao(map);
				return true;
			}
			return false;
	}
	
	/**
	 * 添加模具基础信息
	 * @param param
	 * @return
	 */
	public Map<String,String> saveDataService(HttpServletRequest request,Map<String,String> map){
		return dao.saveDataDao(request, map);
	}
	
	/**
	 * 保存修改后的值
	 * @param map
	 * @return
	 */
	public Map<String,String> updateSaveDataService(Map<String,String> map){
		return dao.updateSaveDataDao(map);
	}
	
	/**
	 * 删除模具基础数据
	 * @param id 表id
	 */
	@Transactional
	public void clearDataService(String param){
		JSONArray ja = JSONArray.fromObject(param);
		List<Object> list = JsonUtil.jsonToList(ja);
		if(list != null){
			for (Object obj : list) {
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) obj;
				Integer id = Integer.parseInt(map.get("id").toString());
				dao.clearDataDao(id);
			}
		}
		
	}
	
	/**
	 * 保存报废数据
	 * @param param
	 */
	public void saveRejectDataService(Map<String,String> map){
		dao.saveRejectDataDao(map);
	}
	
	
	
	
	/************************************模具维修记录操作方法*************************************/
	
	/**
	 * 获取模具维修记录的状态
	 * @return
	 */
	public List<String> getMouldMaintainStatusService(){
		return dao.getMouldMaintainStatusDao();
	}
	
	/**
	 * 获取模具维修记录信息列表，附带查询分页
	 * @param page
	 * @param map
	 * @return
	 */
	public Page getMouldMaintainListService(Page page, Map<String, String> map){
		return dao.getMouldMaintainListDao(page, map);
	}
	
	/**
	 * 点击开始维修
	 * @param id
	 */
	public void startMaintainService(Integer id){
		dao.startMaintainDao(id);
	}
	
	/**
	 * 点击完成维修
	 * @param map
	 * @throws Exception
	 */
	@Transactional
	public void overMaintainDataService(Map<String,String> map) throws Exception{
		//保存完成维修数据
		dao.overMaintainDataDao(map);
		//修改模具状态为正常
		dao.updateOverStatusDao(map);
	}
	
	/**
	 * 删除维修记录模具信息
	 * @param param
	 */
	@Transactional
	public void clearMaintainDataService(String param) {
		JSONArray ja = JSONArray.fromObject(param);
		List<Object> list = JsonUtil.jsonToList(ja);
		if(list != null){
			for (Object obj : list) {
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) obj;
				Integer id = Integer.parseInt(map.get("id").toString());
				dao.clearMaintainDataDao(id);
			}
		}
	}
	
	/**
	 * 显示所有模具基础信息
	 * @return
	 */
	public List<MauMould> showAllDataService(){
		return dao.showAllDataDao();
	}
	
	/**
	 * 根据模具code获取所有的模具name
	 * @return
	 */
	public List<MauMould> showAllMauNameDataService(String mouCode){
		return dao.showAllMauNameDataDao(mouCode);
	}
	
	/**
	 * 保存修改后的维修记录信息
	 * @param map
	 * @throws Exception 
	 */
	public void updateSaveMaintainDataService(MauMould mm) throws Exception{
		dao.updateSaveMaintainDataDao(mm);
	}
	
	
	

}
