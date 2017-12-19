package com.css.business.web.sysManage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.sysManage.bean.SysCommdic;
import com.css.business.web.sysManage.dao.SysCommdicManageDAO;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.bean.TreeVO;
import com.css.common.web.syscommon.controller.support.QueryCondition;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("sysCommdicManageService")
public class SysCommdicManageService extends BaseEntityManageImpl<SysCommdic, SysCommdicManageDAO> {
	@Resource(name="sysCommdicManageDAO")
	@Autowired
	private SysCommdicManageDAO dao;
	
	
	@Override
	public SysCommdicManageDAO getEntityDaoInf() {
		return dao;
	}
	

	/**
	 * 查询树
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<TreeVO> getTree(HttpServletRequest request,String colName,String colValue)throws Exception{
		QueryCondition qc = new QueryCondition(SysCommdic.class);
		if(!StringUtil.isEmpty(colName)){
			if("class".equals(colName)){
				colName = "clas";
			}
			qc.addCondition(colName, "like",colValue+"%");
		}
		
		List <SysCommdic> list = this.query(qc);
		List<TreeVO> tree = new ArrayList<TreeVO>();
		for(SysCommdic s : list){
			//String pid = s.getPcode();
			//TreeVO vo = new TreeVO(s.getId().toString(),s.getValue(),null,s.getId().toString());
			//TreeVO vo = new TreeVO(s.getClas(),s.getClas(),null,s.getClas());
			TreeVO vo = new TreeVO(s.getValue(),s.getValue(),null,s.getValue());
			tree.add(vo);
		}
		return tree;
	}
	/**
	 * 查询树
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TreeVO> getTreeClas(HttpServletRequest request,String Clas,String Value)throws Exception{
	/*	QueryCondition qc = new QueryCondition(SysCommdic.class);
		if(!StringUtil.isEmpty(Clas)){
			qc.addCondition(Clas, "like",Value+"%");
		}
		*/
			String hql = "select distinct clas from sys_Commedic where 1=1 "; 
			if (Clas != null && Clas.length() > 0 && Value != null ) {
				hql += "  and clas='"+Clas+"' ";
			}
			if(Value != null && Value.trim().length() > 0){
				hql += " and clas like '%"+Value.trim()+"%' ";
			}
			List<SysCommdic> list = getEntityDaoInf().createSQLQuery(hql).setResultTransformer(Transformers.aliasToBean(SysCommdic.class)).list();
			
/*		if(list != null && list.size() > 0){
			boolean is = false;
			for(SysCommdic c : list){
				String v = c.getValue();
				if("其他".equals(v) ){
					is = true;
				}
			}
			if(!is){
				SysCommdic c = new SysCommdic();
				c.setValue("其他");
				list.add(c);
			}
		}*/
		
	//	List <SysCommdic> list = this.query(qc);
		List<TreeVO> tree = new ArrayList<TreeVO>();
		for(SysCommdic s : list){
			TreeVO vo = new TreeVO(s.getClas(),s.getClas(), "-2");
			tree.add(vo);
		}
		return tree;
	}
	/**
	 * 查询树
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<TreeVO> getTreeName(String colName,String colValue)throws Exception{
		QueryCondition qc = new QueryCondition(SysCommdic.class);
		if(!StringUtil.isEmpty(colName)){
			qc.addCondition(colName, "like",colValue+"%");
		}
		List <SysCommdic> list = this.query(qc);
		List<TreeVO> tree = new ArrayList<TreeVO>();
		for(SysCommdic s : list){
			//String pid = s.getPcode();
			//TreeVO vo = new TreeVO(s.getValue(),s.getValue(),pid == null ?null:pid.toString(),s.getValue());
			//TreeVO vo = new TreeVO(s.getId().toString(),s.getValue(),null,s.getId().toString());
			TreeVO vo = new TreeVO(s.getValue(),s.getValue(),null,s.getValue());
			tree.add(vo);
		}
		return tree;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TreeVO> nodeTreeForAudit(HttpServletRequest request)throws Exception{
		String sql = "SELECT * FROM sys_CommDic WHERE value='通过' OR value='不通过'";
		List <SysCommdic> list = this.dao.listSQLQuery(sql, SysCommdic.class);
		List<TreeVO> tree = new ArrayList<TreeVO>();
		for(SysCommdic s : list){
			TreeVO vo = new TreeVO(s.getValue(),s.getValue(),null,s.getValue());
			tree.add(vo);
		}
		return tree;
	}
	
	//**********查询数据字典父类对应的子类***********//

	
	@Transactional(readOnly = false)
	public void saveCommdic(SysCommdic entity) {
		dao.save(entity);
	}	
	/**
	 * 查询所有的区域
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<SysCommdic> findByClas(String value){
		String hql = " from  SysCommdic where clas = ? order by  value  ";
		
		List<SysCommdic> list = this.dao.findByArea(hql, value );
		return list ;
	}
	
}

