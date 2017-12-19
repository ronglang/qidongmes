package com.css.business.web.sysManage.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.sysManage.bean.AreaParentVO;
import com.css.business.web.sysManage.bean.SysArea;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.business.web.sysManage.dao.SysAreaManageDAO;
import com.css.common.util.Constant;
import com.css.common.util.HttpUtils;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.bean.TreeVO;
import com.css.common.web.syscommon.controller.support.QueryCondition;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("sysAreaManageService")
public class SysAreaManageService extends BaseEntityManageImpl<SysArea, SysAreaManageDAO> {
	@Resource(name = "sysAreaManageDAO")
	private SysAreaManageDAO dao;

	@Override
	public SysAreaManageDAO getEntityDaoInf() {
		return dao;
	}

	@Transactional(readOnly = true)
	public List<TreeVO> getTree(SysUser user, String code, Object dvalue) throws Exception {
		List<TreeVO> tree = new ArrayList<TreeVO>();
	/*	QueryCondition qc = new QueryCondition(SysArea.class);
		List<SysArea> list = null;
		if (!user.isAdmin()) {
			//qc.addCondition("areaCode", "like", user.getAreaCode() + "%");
			List<VSysuserAccountAll> lst = user.getAreaLst();
			String areaCode = user.getAreaCode();
			if(lst != null && lst.size() > 0){
				StringBuffer sb = new StringBuffer("select s FROM SysArea s where 1=1 and (");
				int i = 0;
				Map<String,String> am = new HashMap<String,String>();
				for(VSysuserAccountAll a : lst){
					if(a.getAreaCode() != null && a.getAreaCode().indexOf(areaCode) == -1)
						am.put(a.getAreaCode(), a.getAreaCode());
				}
				am.put(areaCode, areaCode);
				
				for(String a : am.keySet()){
					if(i == 0){
						sb.append(" areaCode like '"+a+"%' ");
					}
					else{
						sb.append(" or areaCode like '"+a+"%' ");
					}
					i ++;
				}
				sb.append(") ");
				list = dao.createQuery(sb.toString()).list();
			}
			else{
				qc.addCondition("areaCode", "like", user.getAreaCode() + "%");
				list =  this.query(qc);
			}
		} else {
			if (code != null && dvalue != null && code.length() > 0 && dvalue.toString().length() > 0)
				qc.addCondition(code, "=", dvalue);
			
			list =  this.query(qc);
		}
		//List<SysArea> list = this.query(qc);
		
		for (SysArea s : list) {
			TreeVO vo = new TreeVO(s.getAreaCode(), s.getAname().toString(),
					s.getPcode() == null ? "-1" : s.getPcode().toString());
			tree.add(vo);
		}*/
		return tree;
	}

	@Transactional(readOnly = true)
	public List<TreeVO> nodeTreeName(SysUser user, String code, Object dvalue) throws Exception {
		QueryCondition qc = new QueryCondition(SysArea.class);
		if (!(code.isEmpty())) {
			qc.addCondition(code, "like", dvalue + "%");
		}
		List<SysArea> list = this.query(qc);
		List<TreeVO> tree = new ArrayList<TreeVO>();
		for (SysArea s : list) {
			TreeVO vo = new TreeVO(s.getAname(), s.getAname().toString(),
					s.getPcode() == null ? "-1" : s.getPcode().toString());
			tree.add(vo);
		}
		return tree;
	}

	@Transactional(readOnly = true)
	public List<TreeVO> nodeTreeByCon(SysUser user, String code, Object dvalue) throws Exception {
		QueryCondition qc = new QueryCondition(SysArea.class);
		if (!(code.isEmpty())) {
			qc.addCondition(code, "like", dvalue + "%");
		}
		List<SysArea> list = this.query(qc);
		List<TreeVO> tree = new ArrayList<TreeVO>();
		for (SysArea s : list) {
			TreeVO vo = new TreeVO(s.getAreaCode(), s.getAname().toString(),
					s.getPcode() == null ? "-1" : s.getPcode().toString());
			tree.add(vo);
		}
		return tree;
	}

	@Transactional(readOnly = true)
	public List<TreeVO> nodeTreeByCon2(SysUser user, String code, Object dvalue) throws Exception {
		QueryCondition qc = new QueryCondition(SysArea.class);
		if (!(code.isEmpty())) {
			qc.addCondition(code, "=", dvalue);
		}
		List<SysArea> list = this.query(qc);
		List<TreeVO> tree = new ArrayList<TreeVO>();
		for (SysArea s : list) {
			TreeVO vo = new TreeVO(s.getAreaCode(), s.getAname().toString(),
					s.getPcode() == null ? "-1" : s.getPcode().toString());
			tree.add(vo);
		}
		return tree;
	}

	@Transactional(readOnly = true)
	public List<TreeVO> nodeSXJTree(SysUser user) throws Exception {
		String sql = "SELECT * FROM sys_area WHERE LEN(area_code)<=6 ";
		if (user != null && !user.isAdmin()) {
			switch (user.getUserType()) {
			case "市扶贫局":
			case "各县区扶贫局":
				// case "乡镇扶贫办":
				sql += " AND area_code like '" + user.getAreaCode() + "%'";
				break;
			}
		}

		@SuppressWarnings("unchecked")
		List<SysArea> list = this.dao.createSQLQuery(sql, SysArea.class).list();

		List<TreeVO> tree = new ArrayList<TreeVO>();
		for (SysArea s : list) {
			TreeVO vo = new TreeVO(s.getAreaCode(), s.getAname().toString(),
					s.getPcode() == null ? "-1" : s.getPcode().toString());
			tree.add(vo);
		}
		return tree;
	}

	@Transactional(readOnly = true)
	public List<TreeVO> nodeTreeByConToEquals(SysUser user, String code, Object dvalue) throws Exception {
		QueryCondition qc = new QueryCondition(SysArea.class);
		if (user.isAdmin()) {// 权限处理
			if (!(code.isEmpty())) {
				qc.addCondition(code, "=", dvalue);
			}
		} else {
			String userAreaCode = user.getAreaCode();
			if (!(code.isEmpty())) {
				qc.addCondition(code, "=", dvalue);
			}
			if (userAreaCode.length() == 6) {
				qc.addCondition("areaCode", "like", userAreaCode + "%");
			} else {
				userAreaCode = userAreaCode.substring(0, 6);
				qc.addCondition("areaCode", "like", userAreaCode + "%");
			}
		}
		List<SysArea> list = this.query(qc);
		List<TreeVO> tree = new ArrayList<TreeVO>();
		for (SysArea s : list) {
			TreeVO vo = new TreeVO(s.getAreaCode(), s.getAname().toString(),
					s.getPcode() == null ? "-1" : s.getPcode().toString());
			tree.add(vo);
		}
		return tree;
	}

	/**
	 * 轮流根据每个区域名称搜索天地图村坐标并写入数据库
	 * 
	 * @throws UnsupportedEncodingException
	 * @author fudian
	 */
	public void setVillageLngLat() throws UnsupportedEncodingException {
		List<SysArea> areaList = dao.getAll();
		String villageName;
		String countyName;
		String areaName;
		int index;
		String result;
		JSONObject message = null;
		JSONObject features0;
		JSONArray resultInfo;
		for (SysArea area : areaList) {
			features0 = null;
			resultInfo = null;
			villageName = "";
			areaName = "";
			index = -1;
			if (area.getAreaCode().length() > 4) {

				if (area.getAreaCode().length() == 12) {
					villageName = area.getAname();
					countyName = Constant.AREA.get(area.getAreaCode().substring(0, 6)).getAname();
					if (!StringUtil.isEmpty(villageName)) {
						if (villageName.indexOf("居民委员会") != -1) {
							index = villageName.indexOf("居民委员会");
						} else if (villageName.indexOf("民委员会") != -1) {
							index = villageName.indexOf("民委员会");
						} else if (villageName.indexOf("委员会") != -1) {
							index = villageName.indexOf("委员会");
						}
						if (index != -1) {
							villageName = villageName.substring(0, index);
						}
						villageName = URLEncoder.encode(villageName, "utf-8");
						result = HttpUtils.sendGet(
								"http://www.scgis.net.cn/imap/imapserver/defaultrest/services/Newscnamesearch/Search",
								"keyname=" + villageName + "&token=&StartIndex=0&StopIndex=100&DiQuKey=5119");
						JSONObject jsonObj = JSONObject.fromObject(result);
						if (jsonObj != null) {
							message = jsonObj.getJSONObject("message");
							JSONArray features = message.getJSONArray("features");
							if (features.size() > 0) {
								features0 = features.getJSONObject(0);
								resultInfo = features0.getJSONArray("attributes");
								if (countyName.equals(resultInfo.getString(6))) {// 是同一个区县的再写入数据，避免重名后者覆盖前者的坐标
									area.setLongitude(resultInfo.getString(1));
									area.setLatitude(resultInfo.getString(2));
									dao.save(area);
								}
							} else {
								area.setLongitude("");
								area.setLatitude("");
								dao.save(area);
							}
						}
					}
				} else {
					areaName = area.getAname();
					areaName = URLEncoder.encode(areaName, "utf-8");
					result = HttpUtils.sendGet(
							"http://www.scgis.net.cn/imap/imapserver/defaultrest/services/Newscnamesearch/Search",
							"keyname=" + areaName + "&token=&StartIndex=0&StopIndex=100&DiQuKey=5119");
					JSONObject jsonObj = JSONObject.fromObject(result);
					if (jsonObj != null) {
						message = jsonObj.getJSONObject("message");
						JSONArray features = message.getJSONArray("features");
						if (features.size() > 0) {
							features0 = features.getJSONObject(0);
							resultInfo = features0.getJSONArray("attributes");
							area.setLongitude(resultInfo.getString(1));
							area.setLatitude(resultInfo.getString(2));
							dao.save(area);
						} else {
							area.setLongitude("");
							area.setLatitude("");
							dao.save(area);
						}
					}
				}
			}
		}
	}

	@Transactional(readOnly = true)
	public List<SysArea> ListByPidForApp(String pid) {
		return this.dao.ListByPidForApp(pid);
	}

	public List<SysArea> findDataByPid(String pId) {
		return dao.ListByPidForApp(pId);
	}

	// public List<SysArea> findPoorVillage() {
	// String sql = "SELECT
	// ta.id,ta.aname,ta.area_code,ta.create_by,ta.create_date,ta.pcode,ta.remark,ta.latitude,ta.longitude,ta.city,ta.county,ta.country,CASE
	// WHEN tb.TYPES = '贫困村' THEN 'true' ELSE 'false' END IS_POOR_VILLAGE FROM
	// sys_area ta LEFT JOIN B_VILLAGE tb ON ta.area_code = tb.AREA_CODE";
	// return dao.createSQLQuery(sql,SysArea.class).list();
	// }

	/**
	 * @TODO: 根据行政区划代码取名称
	 * @author: zhaichunlei & @DATE : 2016年3月10日
	 * @param areaCode
	 * @return
	 */
	public String getAreaNameByCode(String areaCode) {
		List<SysArea> lst = dao.findBy("areaCode", areaCode);
		if (lst == null || lst.size() == 0)
			return null;
		SysArea sa = lst.get(0);
		return sa.getAname();
	}

	/**
	 * 获取当前区域代码的所有父级名称及代码
	 * 
	 * @param areaCode
	 * @return
	 */
	public AreaParentVO getParentsNames(String areaCode) {
		AreaParentVO areaParent = new AreaParentVO();
		String cityCode;
		String countyCode;
		String countryCode;
		if (areaCode.length() == 4) {
			areaParent.setCityCode(areaCode);
			areaParent.setCityCode(Constant.AREA.get(areaCode).getAname());
		} else if (areaCode.length() == 6) {
			cityCode = areaCode.substring(0, 4);
			areaParent.setCityCode(cityCode);
			areaParent.setCityName(Constant.AREA.get(cityCode).getAname());
			areaParent.setCountyCode(areaCode);
			areaParent.setCountyName(Constant.AREA.get(areaCode).getAname());
		} else if (areaCode.length() == 9) {
			cityCode = areaCode.substring(0, 4);
			areaParent.setCityCode(cityCode);
			areaParent.setCityName(Constant.AREA.get(cityCode).getAname());
			countyCode = areaCode.substring(0, 6);
			areaParent.setCountyCode(countyCode);
			areaParent.setCountyName(Constant.AREA.get(countyCode).getAname());
			areaParent.setCountryCode(areaCode);
			areaParent.setCountryName(Constant.AREA.get(areaCode).getAname());
		} else if (areaCode.length() == 12) {
			cityCode = areaCode.substring(0, 4);
			areaParent.setCityCode(cityCode);
			areaParent.setCityName(Constant.AREA.get(cityCode).getAname());
			countyCode = areaCode.substring(0, 6);
			areaParent.setCountyCode(countyCode);
			areaParent.setCountyName(Constant.AREA.get(countyCode).getAname());
			countryCode = areaCode.substring(0, 9);
			areaParent.setCountryCode(countryCode);
			areaParent.setCountryName(Constant.AREA.get(countryCode).getAname());
			areaParent.setVillageCode(areaCode);
			areaParent.setVillageName(Constant.AREA.get(areaCode).getAname());
		} else {
			areaParent = null;
		}
		return areaParent;
	}

	/**
	 * 查询出全部菜单并转换成树形结构
	 * 
	 * @return
	 * @throws Exception
	 * @author leitao
	 */
	public List<SysArea> getHprojectTree(String areaCode, String aname) throws Exception {
		List<SysArea> list1 = new ArrayList<SysArea>();
		QueryCondition qc1 = new QueryCondition(SysArea.class);
		if (!StringUtil.isEmpty(aname)) {
			qc1.addCondition("aname", "like", aname + "%");
			if (!StringUtil.isEmpty(areaCode))
				qc1.addCondition("areaCode", "=", areaCode);
			list1 = this.query(qc1);
		} else {
			if (!StringUtil.isEmpty(areaCode)) {
				qc1.addCondition("areaCode", "=", areaCode);
				list1 = this.query(qc1);
			} else {
				list1 = recursiveHproject("-1");
			}
		}
		return list1;
	}

	/**
	 * 递归算法
	 * 
	 * @param pcode
	 * @return
	 * @throws Exception
	 * @author leitao
	 */
	public List<SysArea> recursiveHproject(String pcode) throws Exception {
		QueryCondition qc1 = new QueryCondition(SysArea.class);
		qc1.addCondition("pcode", "=", pcode);

		List<SysArea> list1 = this.query(qc1);
		if (list1.size() > 0) {
			for (SysArea hp1 : list1) {
				String pcode2 = hp1.getAreaCode();
				List<SysArea> list2 = recursiveHproject(pcode2);
				if (list2.size() > 0)
					hp1.setChildren(list2);
			}
		}
		return list1;
	}

//	public List<SysArea> findPoorVillage() {
//		String sql = "SELECT ta.id,ta.aname,ta.area_code,ta.create_by,ta.create_date,ta.pcode,ta.remark,ta.latitude,ta.longitude,ta.PROVINCE,ta.CITY,ta.TOWN,ta.COUNTY,ta.IS_POOR FROM sys_area ta ";
//		return dao.createSQLQuery(sql, SysArea.class).list();
//	}
}