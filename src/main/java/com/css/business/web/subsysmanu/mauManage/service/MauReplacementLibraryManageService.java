package com.css.business.web.subsysmanu.mauManage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauReplacementLibrary;
import com.css.business.web.subsysmanu.mauManage.dao.MauReplacementLibraryManageDao;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
/**
 * 备件管理Service
 * @author TG
 *
 */
@Service("replacementLibraryManageService")
public class MauReplacementLibraryManageService extends BaseEntityManageImpl<MauReplacementLibrary, MauReplacementLibraryManageDao> {
	
	@Resource(name="replacementLibraryManageDao")
	private MauReplacementLibraryManageDao dao;
	
	@Override
	public MauReplacementLibraryManageDao getEntityDaoInf() {
		return dao;
	}
	
	/**
	 * 条件查询分页获取备件表中的所有基础数据
	 * @param page
	 * @return
	 */
	public Page getAllDataListService(Page page,Map<String,String> map){
		return dao.getAllDataListDao(page,map);
	}
	
	/**
	 * 条件查询分页获取备件表中的所有报废数据
	 * @param page
	 * @return
	 */
	public Page getRejectDataListService(Page page,Map<String,String> map){
		return dao.getRejectDataListDao(page,map);
	}
	
	/**
	 * 条件查询分页获取备件表中的所有领料数据
	 * @param page
	 * @return
	 */
	public Page getMaterialDataListService(Page page,Map<String,String> map){
		return dao.getMaterialDataListDao(page,map);
	}
	
	
	/**
	 * 添加数据
	 * @param map 数据保存在map中
	 * @return
	 */
	public Map<String,String> addReplacementDataService(HttpServletRequest request,Map<String,String> map){
		return dao.addReplacementDataDao(request,map);
	}
	
	/**
	 * 添加报废数据
	 * @param map 数据保存在map中
	 * @return
	 */
	@Transactional
	public Map<String,String> saveRejectDataService(HttpServletRequest request,Map<String,String> map){
		
		Map<String,String> maps = new HashMap<>();
		dao.saveRejectDataDao(request,map);
		boolean flag = dao.changeStoreNumber(map);
		if(flag){
			maps.put("success", "报废成功！");
		}else{
			maps.put("error", "出现异常，报废失败！");
		}
		return maps;
	}
	
	/**
	 * 添加报废数据
	 * @param map 数据保存在map中
	 * @return
	 */
	public Map<String,String> saveMaterialDataService(HttpServletRequest request,Map<String,String> map){
		
		Map<String,String> maps = new HashMap<>();
		dao.saveMaterialDataDao(request,map);
		boolean flag = dao.changeStoreNumber(map);
		if(flag){
			maps.put("success", "领料成功！");
		}else{
			maps.put("error", "出现异常，领料失败！");
		}
		return maps;
	}
	
	/**
	 * 删除多行数据
	 * @param id
	 * @return
	 */
	public void clearReplacementDataService(Integer id){
		dao.clearReplacementDataDao(id);
	}
	
	/**
	 * 修改数据
	 * @param map 数据保存在map中
	 * @return
	 */
	public Map<String,String> updateReplacementDataService(HttpServletRequest request,Map<String,String> map){
		return dao.updateReplacementDataDao(request,map);
	}
	
	/**
	 * 查询所有替换类型
	 * @return 替换类型集合
	 */
	public List<String> getReplacementTypeService(){
		String hql="select type from MauReplacementLibrary  group by type";
		@SuppressWarnings("unchecked")
		List<String> list=dao.createQuery(hql).list();
		
		return list;
	}
	
	
	
	
	
	
}
