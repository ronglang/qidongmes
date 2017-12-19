package com.css.business.web.subsysmanu.mauManage.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.css.business.web.subsysmanu.bean.MauReplacementLibrary;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;

/**
 * 备件管理Dao
 * @author TG
 *
 */
@Repository("replacementLibraryManageDao")
public class MauReplacementLibraryManageDao extends BaseEntityDaoImpl<MauReplacementLibrary>{
	
	/**
	 * 条件查询分页获取备件表中的所有数据
	 * @param page
	 * @return
	 */
	public Page getAllDataListDao(Page page,Map<String,String> map){
		String hql = "from MauReplacementLibrary where 1=1 and status = '正常' ";
		StringBuffer sb = new StringBuffer(hql);
		if(map!= null){
			if(map.get("code") != null && !"".equals(map.get("code"))){
				sb.append(" and code like '%"+map.get("code")+"%' ");
			}
//			if(map.get("name") != null && !"".equals(map.get("name"))){
//				sb.append(" and name like '%"+map.get("name")+"%' ");
//			}
			if(map.get("ggxh") != null && !"".equals(map.get("ggxh"))){
				sb.append(" and ggxh like '%"+map.get("ggxh")+"%' ");
			}
			if(map.get("type") != null && !"".equals(map.get("type"))){
				sb.append("and type like '%"+map.get("type")+"%'");
			}
		}
		
		Page pageHql = this.pageQuery(sb.toString(), page.getPageNo(), page.getPagesize());
		return pageHql;
	}
	
	/**
	 * 条件查询分页获取备件表中的所有报废数据
	 * @param page
	 * @return
	 */
	public Page getRejectDataListDao(Page page,Map<String,String> map){
		String hql = "from MauReplacementLibrary where 1=1 and status = '报废' ";
		StringBuffer sb = new StringBuffer(hql);
		if(map!= null){
			if(map.get("code") != null && !"".equals(map.get("code"))){
				sb.append(" and code like '%"+map.get("code")+"%' ");
			}
		}
		
		Page pageHql = this.pageQuery(sb.toString(), page.getPageNo(), page.getPagesize());
		return pageHql;
	}
	
	/**
	 * 条件查询分页获取备件表中的所有报废数据
	 * @param page
	 * @return
	 */
	public Page getMaterialDataListDao(Page page,Map<String,String> map){
		String hql = "from MauReplacementLibrary where 1=1 and status = '领料' ";
		StringBuffer sb = new StringBuffer(hql);
		if(map!= null){
			if(map.get("code") != null && !"".equals(map.get("code"))){
				sb.append(" and code like '%"+map.get("code")+"%' ");
			}
		}
		
		Page pageHql = this.pageQuery(sb.toString(), page.getPageNo(), page.getPagesize());
		return pageHql;
	}
	
	/**
	 * 添加数据
	 * @param map 数据保存在map中
	 * @return
	 */
	public Map<String,String> addReplacementDataDao(HttpServletRequest request,Map<String,String> map){
		Map<String,String> maps = new HashMap<>();
		String hql = "select * from mau_replacement_library where status = '正常' and code = ?";
		@SuppressWarnings("unchecked")
		List<MauReplacementLibrary> list = this.listSQLQuery(hql,MauReplacementLibrary.class, map.get("code"));
		
		if(list.size() > 0){
			maps.put("error", "该备件已存在，编码唯一！");
			return maps;
		}
		
		MauReplacementLibrary mr = new MauReplacementLibrary();
		SysUser user = SessionUtils.getUser(request);
		mr.setCreateDate(new Date());
		mr.setCreateBy(user.getAccount());
		mr.setCode(map.get("code"));
		mr.setGgxh(map.get("ggxh"));
		mr.setNumber(Integer.valueOf(map.get("number")));
		mr.setType(map.get("type"));
		mr.setReplaceUse(map.get("replaceUse"));
		mr.setReplaceLineUse(map.get("replaceLineUse"));
		mr.setSupply(map.get("supply"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			mr.setInStoreTime(sdf.parse(map.get("inStoreTime")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		mr.setStatus("正常");
		
		try{
			this.save(mr);
			maps.put("success", "添加成功！");
		}catch(Exception e){
			e.printStackTrace();
			maps.put("error", "出现异常，添加失败！");
		}
		return maps;
	}
	
	/**
	 * 添加报废数据
	 * @param map 数据保存在map中
	 * @return
	 */
	public void saveRejectDataDao(HttpServletRequest request,Map<String,String> map){
		MauReplacementLibrary mr = new MauReplacementLibrary();
		SysUser user = SessionUtils.getUser(request);
		mr.setCreateDate(new Date());
		mr.setCreateBy(user.getAccount());
		mr.setCode(map.get("code"));
		mr.setGgxh(map.get("ggxh"));
		mr.setNumber(Integer.valueOf(map.get("number")));
		mr.setType(map.get("type"));
		mr.setReplaceUse(map.get("replaceUse"));
		mr.setReplaceLineUse(map.get("replaceLineUse"));
		mr.setSupply(map.get("supply"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			mr.setInStoreTime(sdf.parse(map.get("inStoreTime")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		mr.setRejectNumber(Integer.parseInt(map.get("rejectNumber")));
		mr.setRejectReason(map.get("rejectReason"));
		try {
			mr.setRejectTime(sdf.parse(map.get("rejectTime")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		mr.setStatus("报废");
		try{
			this.save(mr);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加报废数据
	 * @param map 数据保存在map中
	 * @return
	 */
	public void saveMaterialDataDao(HttpServletRequest request,Map<String,String> map){
		MauReplacementLibrary mr = new MauReplacementLibrary();
		SysUser user = SessionUtils.getUser(request);
		mr.setCreateDate(new Date());
		mr.setCreateBy(user.getAccount());
		mr.setCode(map.get("code"));
		mr.setGgxh(map.get("ggxh"));
		mr.setNumber(Integer.valueOf(map.get("number")));
		mr.setType(map.get("type"));
		mr.setReplaceUse(map.get("replaceUse"));
		mr.setReplaceLineUse(map.get("replaceLineUse"));
		mr.setSupply(map.get("supply"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			mr.setInStoreTime(sdf.parse(map.get("inStoreTime")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		mr.setMaterialNumber(Integer.parseInt(map.get("materialNumber")));
		mr.setMaterialUse(map.get("materialUse"));
		mr.setMaterialBy(map.get("materialBy"));
		try {
			mr.setMaterialTime(sdf.parse(map.get("materialTime")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		mr.setStatus("领料");
		try{
			this.save(mr);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//报废或领料成功后改变库存数量
	public boolean changeStoreNumber(Map<String,String> map){
		MauReplacementLibrary mr = new MauReplacementLibrary();
		mr.setId(Integer.parseInt(map.get("id")));
		mr.setNumber(Integer.parseInt(map.get("changeNumber")));
		try {
			this.updateByCon(mr, false);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * 删除多行数据
	 * @param id
	 * @return
	 */
	public void clearReplacementDataDao(Integer id){
		MauReplacementLibrary mr = new MauReplacementLibrary();
		mr.setId(id);
		this.remove(mr);
		
	}
	
	/**
	 * 修改数据
	 * @param map 数据保存在map中
	 * @return
	 */
	public Map<String,String> updateReplacementDataDao(HttpServletRequest request,Map<String,String> map){
		MauReplacementLibrary mr = new MauReplacementLibrary();
//		SysUser user = SessionUtils.getUser(request);
//		mr.setCreateDate(new Date());
//		mr.setCreateBy(user.getAccount());
		if(map != null){
			mr.setId(Integer.valueOf(map.get("id")));
			if(map.get("code") != null && !"".equals(map.get("code"))){
				mr.setCode(map.get("code"));
			}
			if(map.get("ggxh") != null && !"".equals(map.get("ggxh"))){
				mr.setGgxh(map.get("ggxh"));
			}
			if(map.get("number") != null && !"".equals(map.get("number"))){
				mr.setNumber(Integer.parseInt(map.get("number")));
			}
			if(map.get("type") != null && !"".equals(map.get("type"))){
				mr.setType(map.get("type"));
			}
			if(map.get("replaceUse") != null && !"".equals(map.get("replaceUse"))){
				mr.setReplaceUse(map.get("replaceUse"));
			}
			if(map.get("replaceLineUse") != null && !"".equals(map.get("replaceLineUse"))){
				mr.setReplaceLineUse(map.get("replaceLineUse"));
			}
			if(map.get("supply") != null && !"".equals(map.get("supply"))){
				mr.setSupply(map.get("supply"));
			}
			if(map.get("inStoreTime") != null && !"".equals(map.get("inStoreTime"))){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					mr.setInStoreTime(sdf.parse(map.get("inStoreTime")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		Map<String,String> maps = new HashMap<>();
		try{
			this.updateByCon(mr,false);
			maps.put("success", "修改成功！");
		}catch(Exception e){
			e.printStackTrace();
			maps.put("error", "出现异常，修改失败！");
		}
		return maps;
	}
	
	
	
	
	
	
}
