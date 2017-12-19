package com.css.business.web.subsyscraft.craManage.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.bean.CraBomTheory;
import com.css.business.web.subsyscraft.bean.CraProSeqRelation;
import com.css.business.web.subsyscraft.bean.CraSeq;
import com.css.business.web.subsyscraft.craManage.dao.CraBomTheoryManageDAO;
import com.css.business.web.subsyscraft.vo.CraBomTheoryVo;
import com.css.business.web.sysManage.bean.SysDictionary;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("craBomTheoryManageService")
public class CraBomTheoryManageService extends BaseEntityManageImpl<CraBomTheory, CraBomTheoryManageDAO> {
	@Resource(name="craBomTheoryManageDAO")
	//@Autowired
	private CraBomTheoryManageDAO dao;
	
	
	@Override
	public CraBomTheoryManageDAO getEntityDaoInf() {
		return dao;
	}


	/**   
	 * @Description: 通过参数code去查理论值   
	 * @param paramCode
	 * @return         
	 */ 
	@SuppressWarnings("unchecked")
	public CraBomTheory queryValueByCode(String paramCode) {
		String hql = "from CraBomTheory where thirdProp='"+paramCode+"'";
		// TODO Auto-generated method stub
		List<CraBomTheory> list = dao.createQuery(hql).list();
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	public List<CraBomTheory> getCraBomTheory(String thirdProp){
		String hql = " from CraBomTheory where thirdProp = ? and remark = '单位换算' ";
		@SuppressWarnings("unchecked")
		List<CraBomTheory> list = dao.createQuery(hql, thirdProp).list();
		return list;
	}
	
	
	public Page getCraBomTheoryGrid(Page p){
		String hql = " from CraBomTheory ";
		Page page = dao.pageQuery(hql, p.getPageNo(), p.getPagesize());
		return page;
	}
	
	@SuppressWarnings({ "unchecked" })
	@Deprecated
	public String saveCraBomTheory(){
		String hq = " from CraBomTheory ";
		List<CraBomTheory> ls= dao.createQuery(hq).list();
		if(ls==null || ls.size()<1){
			String hql = " from SysDictionary where type = 'BOM参数' ";
			List<SysDictionary> list = dao.createQuery(hql).list();
			for (SysDictionary sysdic : list) {
				CraBomTheory craTheory = new CraBomTheory();
				/*String hql2 = " from CraBomTheory where paramName = ? AND  ";*/
				craTheory.setParamName(sysdic.getValue());
				craTheory.setRemark(sysdic.getRemark());
				craTheory.setCreateDate(new Date());
				craTheory.setThirdProp(sysdic.getPcode());
				craTheory.setParamType(sysdic.getType());
				dao.save(craTheory);
			}
			return "已保存成功";
		}else{
			return "数据已存在";
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void initializeTheroy(){
		String hql = " from CraProSeqRelation ";
		List<CraProSeqRelation> list = dao.createQuery(hql).list();
		for (CraProSeqRelation craProSeqRelation : list) {  //遍历关系表得到工序边和和关系表CODE
			String hh = " from CraBomTheory where pcscRelaCode = ? ";
			// 判断CraBomTheory是否已存在该数据
			List<CraBomTheory> li = dao.createQuery(hh,craProSeqRelation.getPcscRelaCode()).list();
			if(li==null || li.size()<1){
				String hq = " from SysDictionary where type = 'BOM参数' and pcode = ? ";
				List<SysDictionary> ls = dao.createQuery(hq, craProSeqRelation.getSeqCode()).list();
				for (SysDictionary sysdic : ls) { //遍历基础数剧，写入BOM参数表，初始化数据
					CraBomTheory craTheory = new CraBomTheory();
					craTheory.setParamName(sysdic.getValue());
					craTheory.setRemark(sysdic.getType());
					craTheory.setCreateDate(new Date());
					craTheory.setThirdProp(sysdic.getPcode()+":BOM");
					craTheory.setParamType(sysdic.getRemark());
					craTheory.setPcscRelaCode(craProSeqRelation.getPcscRelaCode());
					dao.save(craTheory);
				}
			}
			
		}
		
	}
	
	/**
	 * 展示BOM参数规格及条件查询
	 * @param p
	 * @param paramType
	 * @param thirdProp
	 * @param proGgxh
	 * @param proColor
	 * @return
	 * @throws Exception
	 * @author JS
	 */
	public Page getTheoryGrid(Page p,String paramType,String thirdProp,String proGgxh,String proColor) throws Exception{
		String sql = " SELECT c.id,c.param_name paramname,c.param_value paramvalue,"
				+ "c.third_prop thirdprop,c.param_type paramtype,r.pro_ggxh proggxh,"
				+ "r.pro_color procolor FROM cra_bom_theory c "
				+ "LEFT JOIN cra_pro_seq_relation r ON"
				+ "  c.pcsc_rela_code = r.pcsc_rela_code where c.remark = 'BOM参数' ";
		if(paramType!=null && !"".equals(paramType)){
			sql +=" and c.param_type = '"+paramType+"' ";
			p.setPageNo(1);
		}
		if(thirdProp!=null && !"".equals(thirdProp)){
			String hql = " from CraSeq where seqName = ? ";
			CraSeq craSeq=(CraSeq) dao.createQuery(hql, thirdProp).list().get(0);
			String prop = craSeq.getSeqCode()+":BOM";
			sql +=" and c.third_prop = '"+prop+"' ";
			p.setPageNo(1);
		}
		if(proGgxh!=null && !"".equals(proGgxh)){
			sql +=" and r.pro_ggxh='"+proGgxh+"' ";
			p.setPageNo(1);
		}
		if(proColor!=null && !"".equals(proColor)){
			sql +=" and r.pro_color='"+proColor+"' ";
			p.setPageNo(1);
		}
		sql += " order by c.id ";
		Page page = dao.pageSQLQueryVONoneDesc(sql, p.getPageNo(), p.getPagesize(),new CraBomTheoryVo());
		
		return page;
	}
	
	
	public void updateCraBomTheory(CraBomTheory craBomTheory) throws Exception{
		dao.updateByCon(craBomTheory, false);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map[] getParamType(){
		String hql = " SELECT DISTINCT paramType from CraBomTheory where remark = 'BOM参数'  ";
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
	public Map[] getSEQName(){
		String hql = " SELECT DISTINCT seqName from CraSeq ";
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
	public Map[] getProGgxh(){
		
		String hql = " SELECT DISTINCT proGgxh from CraProSeqRelation ";
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
	public Map[] getProColor(String proGgxh){
		String hql = " SELECT DISTINCT proColor from CraProSeqRelation where proGgxh = ? ";
		List<Object> list = dao.createQuery(hql,proGgxh).list();
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
	
	
	public String getSeqName(String thirdprop){
		String[] split = thirdprop.split(":");
		String hql = " from CraSeq where seqCode = ? ";
		CraSeq seq = (CraSeq) dao.createQuery(hql, split[0]).list().get(0);
		return seq.getSeqName();
	}
	
	
}
