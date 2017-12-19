package com.css.business.web.subsysstore.storeManage.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.mauManage.dao.MauRfidCardManageDAO;
import com.css.business.web.subsysstore.bean.StoreMaterialBasicInfo;
import com.css.business.web.subsysstore.storeManage.bean.StoreMaterialVo;
import com.css.business.web.subsysstore.storeManage.dao.StoreMaterialBasicInfoManageDAO;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("storeMaterialBasicInfoManageService")
public class StoreMaterialBasicInfoManageService
		extends
		BaseEntityManageImpl<StoreMaterialBasicInfo, StoreMaterialBasicInfoManageDAO> {

	@Resource(name = "storeMaterialBasicInfoManageDAO")
	private StoreMaterialBasicInfoManageDAO dao;
	@Resource(name = "mauRfidCardManageDAO")
	private MauRfidCardManageDAO maudao;

	public MauRfidCardManageDAO getMaudao() {
		return maudao;
	}

	public void setMaudao(MauRfidCardManageDAO maudao) {
		this.maudao = maudao;
	}

	@Override
	public StoreMaterialBasicInfoManageDAO getEntityDaoInf() {
		// TODO Auto-generated method stub
		return dao;
	}
	

	// 拼接查询条件
	@SuppressWarnings("unused")
	public String getParams(StoreMaterialBasicInfo entity, String endTime)
			throws Exception {
		String params = "";
		// 遍历StoreMaterialBasicInfo属性
		Field[] field = entity.getClass().getDeclaredFields();
		for (Field f : field) {
			String name = f.getName();
			if (name.equals("serialVersionUID") || name.equals("id"))
				continue;
			// 将属性的首字符大写，方便构造
			name = name.substring(0, 1).toUpperCase() + name.substring(1);
			Method method = entity.getClass().getMethod("get" + name);
			String value = "";
			// 日期类型单独处理
			if (f.getType().getName().equals("java.util.Date")) {
				// SimpleDateFormat strdate = new
				// SimpleDateFormat("yyyy-MM-dd hh:mm");
				Date date = (Date) method.invoke(entity);

				if (date != null) {
					value = date.toString();
				}
				if (value != "" && endTime != "") {
					params += "and " + f.getName().toString() + " between '"
							+ value + "' and '" + endTime + "'";
					continue;
				} else if (value != "" && endTime == "") {
					params += "and " + f.getName().toString() + " > '" + value
							+ "'";
					continue;
				} else if (value == "" && endTime != null) {
					params += "and " + f.getName().toString() + " < '"
							+ endTime + "'";
					continue;
				}
			}
			value = (String) method.invoke(entity);
			// 值不为空
			if (value != null && value != "") {
				params += "and " + f.getName().toString() + " = " + "'" + value
						+ "'";
			}
		}
		return params;
	}

	// 返回page
	public Page getlist(Page page, StoreMaterialBasicInfo entity, String endTime)
			throws Exception {
		int pageSize = page.getPagesize();
		int pageNo = page.getPageNo();
		int pageCount = dao.getPageCount();
		try {
			return new Page(pageNo, pageCount, pageSize, dao.getlist(getParams(
					entity, endTime)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 返回Lsit<?>
	public List<?> getRecord(StoreMaterialBasicInfo entity, String endTime)
			throws DataAccessException, Exception {
		return dao.getlist(getParams(entity, endTime));
	}

	public List<String> getRfid(String params) {
		return dao.getRfid(params);
	}

	// delete
	public void deleteRecord(String params, List<String> rfid) throws Exception {
		dao.deleteByCollection(params);
		Iterator<String> it = (Iterator<String>) rfid.iterator();
		while (it.hasNext()) {
			maudao.updateStatusByRfidCode("已废弃", it.next().toString());
		}

	}

	// saveOrUpdate
	public void saveOrUpdate(StoreMaterialBasicInfo entity, Integer id) {
		String rfid = "";
		if (id != null) {
			// 更新实体
			entity.setId(id);
		} else// 新建对象
		{
			// 请求新的RFID
			// rfid = maudao.getNewRfidCode();
			// rfid = maudao.getNewRfidCode();
		}
		try {
			dao.saveOrUpdate(entity);
			maudao.updateStatusByRfidCode("已使用", rfid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 获取供应商下拉框
	 * 
	 * @data:2017年8月14日
	 * @return
	 * @autor:wl
	 */

	public Map[] getSupplier() {
		// TODO Auto-try method stub
		List<String> list = null;
		Map[] map = null;
		try {
			String hql = "select distinct supplyAgent from  StoreObj where  supplyAgent!=null  and supplyAgent!=''";
			list = dao.getSupplier(hql);
			List<Object> list2 = new ArrayList<>();
			list2.add(null);
			list.removeAll(list2);
			map = new Map[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> map2 = new HashMap<>();
				map2.put("text", list.get(i).toString());
				map[i] = map2;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}

	/**
	 * @Description: 分页条件查询
	 * @param page
	 * @param map
	 * @return
	 */
	public Page getPageList(Page page, Map<String, String> map) {
		// TODO Auto-generated method stub
		String hql = "from StoreMaterialBasicInfo where pCode = '2' ";
		StringBuilder sb = new StringBuilder(hql);
		Page queryPage = page;
		try {
			if (map != null && map.size() > 0) {
				if (map.get("material_type") != null && map.get("material_type") != "") {
					sb.append(" and material_type = '" + map.get("material_type") + "'");
				}
				if (map.get("mater_name") != null && map.get("mater_name") != "") {
					sb.append(" and mater_name like '%" + map.get("mater_name")+ "%'");
				}
				if (map.get("mater_ggxh") != null && map.get("mater_ggxh") != "") {
					sb.append(" and mater_ggxh like '%" + map.get("mater_ggxh") + "%'");
				}
			}
			sb.append(" ORDER BY create_by ");
			queryPage = dao.pageQuery(sb.toString(), page.getPageNo(), page.getPagesize());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return queryPage;
	}

	/**   
	 * @Description: 获得所有类型 
	 * @return         
	 */ 
	public List<String> getAllType() {
		// TODO Auto-generated method stub
		List<String>typeList = new ArrayList<>();
		try {
			//String sql = "select DISTINCT material_type from store_material_basic_info where material_type is not null and mater_ggxh is not null";
			String sql = "from StoreMaterialBasicInfo where pCode = '1' ";
			
			List<StoreMaterialBasicInfo> list = dao.listQuery(sql);
			for(StoreMaterialBasicInfo sb:list){
				typeList.add(sb.getMater_name());
			}
			//typeList.addAll(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return typeList;
	}

	/**   
	 * @Description: 获得所有供应商信息   
	 * @param info 模糊查询条件
	 * @return         
	 */ 
	public List<String> getAllDelivery(String info) {
		// TODO Auto-generated method stub
		List<String> delList = new ArrayList<>();
		try {
			String sql = "select DISTINCT delivery_info from store_material_basic_info where delivery_info is not null and delivery_info like '%"+info+"%'";
			@SuppressWarnings("unchecked")
			List<String> list = dao.createSQLQuery(sql).list();
			delList.addAll(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		return delList;
	}
	
	/**
	 * 添加原材料基础信息
	 * @param request
	 * @param info
	 * @return
	 */
	public boolean addDataTypeService(HttpServletRequest request,StoreMaterialBasicInfo info){
		boolean flag = dao.getGgxh(info.getMater_ggxh());
		if(flag){
			//新建 保存
			SysUser user = SessionUtils.getUser(request);
			info.setCreate_date(new Date());
			info.setCreate_by(user.getAccount());
			info.setpCode("2");
//			List<StoreMaterialBasicInfo> list = dao.getAllDataTypeDao();
//			for(StoreMaterialBasicInfo sb : list){
//				if(sb.getMaterial_type().equals(info.getMaterial_type())){
//					info.setpCode(sb.getId().toString());
//					break;
//				}
//			}
			dao.save(info);
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * 查询所有类别
	 * @return
	 */
	public List<StoreMaterialBasicInfo> getAllDataType(){
		String sql = "from StoreMaterialBasicInfo where pCode = '1' ";
		List<StoreMaterialBasicInfo> list = dao.listQuery(sql);
		return list;
	}
	
	/**
	 * 查询所有的大分类
	 * @return
	 */
	public List<StoreMaterialBasicInfo> getAllBigDataTypeService(){
		String sql = "from StoreMaterialBasicInfo where pCode = '0' ";
		List<StoreMaterialBasicInfo> list = dao.listQuery(sql);
		return list;
	}
	
	/**
	 * 保存增加类型
	 * @param map
	 * @return
	 */
	public boolean saveAllTypesService(Map<String,String> map,HttpServletRequest request){
		StoreMaterialBasicInfo sb = new StoreMaterialBasicInfo();
		sb.setCreate_date(new Date());
		SysUser user = SessionUtils.getUser(request);
		sb.setCreate_by(user.getAccount());
		sb.setMaterial_type(map.get("big_type"));
		sb.setMater_name(map.get("material_type"));
		//sb.setpCode(map.get("pCode"));
		sb.setpCode("1");
		try{
			dao.save(sb);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * 保存编辑类型
	 * @param map
	 * @return
	 */
	public boolean saveModifyTypesService(Map<String,String> map){
		StoreMaterialBasicInfo sb = new StoreMaterialBasicInfo();
		sb.setId(Integer.valueOf(map.get("id")));
		sb.setMaterial_type(map.get("big_type"));
		sb.setMater_name(map.get("material_type"));
		sb.setpCode(map.get("pCode"));
		try{
			dao.updateByCon(sb, false);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * 查询所有的材料分类
	 * @param page
	 * @return
	 */
	public Page getPageTypeList(Page page) {
		// TODO Auto-generated method stub
		String sql = "SELECT l.id,l.material_type materialtype,l.mater_name matername,s.num numbers  FROM "+
					 " (SELECT id,material_type,mater_name from store_material_basic_info where p_code = '1' ) l "+
					 " LEFT JOIN "+
					 " (SELECT material_type st,count(material_type)||'' num from store_material_basic_info where p_code = '2' GROUP BY material_type) s "+
					 " ON l.mater_name = s.st  ORDER BY s.num ASC ";
		
//		String hql = "from StoreMaterialBasicInfo where p_code != '0' and p_code is not null and mater_ggxh is null or mater_ggxh = '' ";
//		StringBuilder sb = new StringBuilder(hql);
//		Page queryPage = dao.pageQuery(sb.toString(), page.getPageNo(), page.getPagesize());
		StringBuilder sb = new StringBuilder(sql);
		Page queryPage = null;
		try {
			queryPage = dao.pageSQLQueryVONoneDesc(sb.toString(), page.getPageNo(), page.getPagesize(), new StoreMaterialVo());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return queryPage;
	}

	
	
	
	
}
