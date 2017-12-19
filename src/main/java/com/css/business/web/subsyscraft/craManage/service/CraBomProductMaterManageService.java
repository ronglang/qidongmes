package com.css.business.web.subsyscraft.craManage.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.bean.CraBomProductMater;
import com.css.business.web.subsyscraft.bean.CraBomProductMaterVo;
import com.css.business.web.subsyscraft.bean.CraSeq;
import com.css.business.web.subsyscraft.craManage.dao.CraBomProductMaterManageDAO;
import com.css.business.web.subsysstore.bean.StoreMaterialBasicInfo;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("craBomProductMaterManageService")
public class CraBomProductMaterManageService extends
		BaseEntityManageImpl<CraBomProductMater, CraBomProductMaterManageDAO> {
	@Resource(name = "craBomProductMaterManageDAO")
	// @Autowired
	private CraBomProductMaterManageDAO dao;

	@Override
	public CraBomProductMaterManageDAO getEntityDaoInf() {
		return dao;
	}

	public List<CraBomProductMater> getCraBomProductMater(String proCraftCode) {
		String hql = " from CraBomProductMater where proCraftCode = ? ";
		@SuppressWarnings("unchecked")
		List<CraBomProductMater> list = dao.createQuery(hql, proCraftCode)
				.list();
		return list;
	}

	public Page getgetCraBomProductMaterGrid(Page p,String proGgxh) throws Exception {
		String sql = "  SELECT c.id id,  C .mcpu mcpu,C .unit unit,C .pro_craft_code procraftcode,C .pro_ggxh proggxh,"
				+ "C.pro_color procolor, C .create_date createdate,e.seq_name seqname,s.mater_ggxh materggxh "
				+ "FROM cra_bom_product_mater C "
				+ " LEFT JOIN store_material_basic_info s ON C .mater_id = s. ID "
				+ " LEFT JOIN cra_seq e ON e.seq_code = C .seq_code WHERE 1=1 ";
		if(proGgxh!=null && !"".equals(proGgxh) ){
			sql += " AND C.pro_ggxh = '"+proGgxh+"' ";
		}
		sql+= "order by c.id ";
		Page page = dao.pageSQLQueryVONoneDesc(sql, p.getPageNo(),
				p.getPagesize(), new CraBomProductMaterVo());
		return page;
	}

	public void saveCraBomProductMater(CraBomProductMaterVo vo,SysUser user) {
		CraBomProductMater craBomProductMater = new CraBomProductMater();
		craBomProductMater.setMcpu(vo.getMcpu());
		String seqCode = this.getSeqCodeByName(vo.getSeqname());
		craBomProductMater.setSeqCode(seqCode);
		Integer materId = this.getMaterIdByGgxh(vo.getMaterggxh());
		craBomProductMater.setMaterId(materId);
		craBomProductMater.setUnit(vo.getUnit());
		craBomProductMater.setProGgxh(vo.getProggxh());
		craBomProductMater.setProColor(vo.getProcolor());
		craBomProductMater.setCreateBy(user.getAccount());
		craBomProductMater.setCreateDate(new Date());
		dao.save(craBomProductMater);
	}
	
	@SuppressWarnings("unchecked")
	public String getSeqCodeByName(String seqName){
		String hql = " from CraSeq where seqName = ? ";
		List<CraSeq> list = dao.createQuery(hql, seqName).list();
		if(list.size()<1 || list==null ){
			return null;
		}else{
			return list.get(0).getSeqCode();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Integer getMaterIdByGgxh(String materGgxh){
		String hql = " from StoreMaterialBasicInfo where mater_ggxh = ? ";
		List<StoreMaterialBasicInfo> list = dao.createQuery(hql, materGgxh).list();
		if(list.size()<1 || list==null){
			return null;
		}else{
			return list.get(0).getId();
		}
	}
	

	public void updateCraBomProductMater(CraBomProductMaterVo vo,SysUser user)
			throws Exception {
		CraBomProductMater craBomProductMater = new CraBomProductMater();
		craBomProductMater.setId(vo.getId());
		craBomProductMater.setMcpu(vo.getMcpu());
		craBomProductMater.setCreateBy(user.getAccount());
		craBomProductMater.setCreateDate(new Date());
		dao.updateByCon(craBomProductMater, false); 
	}

	public void delCraBomProductMater(Integer id) {
		String sql = " delete from cra_bom_product_mater where id = ? ";
		dao.deleteBySql(sql, id);
	}

	public void getPage(Page page, HttpServletRequest request, String mainCode) {
		String hql = " from CraBomProductMater where  pcscRelaCode = ? ";
		dao.findByQueryPage(page, hql, new Object[] { mainCode });
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map[] getProggxh() {
		String hql = "SELECT DISTINCT proGgxh from CraBomProductMater ";
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
	public Map[] getProColor(String proggxh){
		String hql = "SELECT DISTINCT value from SysCommdic where clas = ? ";
		List<Object> list = dao.createQuery(hql,proggxh).list();
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
	public Map[] getSeqName() {
		String hql = "SELECT DISTINCT seqName from CraSeq";
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
	public Map[] getMaterGgxh() {
		String hql = "SELECT DISTINCT mater_ggxh from StoreMaterialBasicInfo ";
		List<Object> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					if(list.get(i)==null||"".equals(list.get(i))){
						continue;
					}
					map.put("text", list.get(i).toString());
				} else {
					map.put("id", i + "");
				}
			}
			str[i] = map;
		}
		return str;
	}
	
	
}
