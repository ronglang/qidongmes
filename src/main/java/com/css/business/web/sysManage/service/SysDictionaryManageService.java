package com.css.business.web.sysManage.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.sysManage.bean.SysDictionary;
import com.css.business.web.sysManage.bean.SysMetaData;
import com.css.business.web.sysManage.bean.TreeVoR;
import com.css.business.web.sysManage.dao.SysDictionaryManageDAO;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.bean.TreeVO;
import com.css.common.web.syscommon.controller.support.QueryCondition;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("sysDictionaryManageService")
public class SysDictionaryManageService extends BaseEntityManageImpl<SysDictionary, SysDictionaryManageDAO> {
	@Resource(name="sysDictionaryManageDAO")
	//@Autowired
	private SysDictionaryManageDAO dao;
	
	
	@Override
	public SysDictionaryManageDAO getEntityDaoInf() {
		return dao;
	}
	
	@Transactional(readOnly = false)
	@Override
	public void deleteBusiness(Serializable id) throws Exception {
		Integer ids = 0;
		if(id instanceof String){
			ids = Integer.parseInt(id.toString());
		}else{
			ids = (Integer)id;
		}
		SysDictionary en = this.get(ids);
		this.dao.remove(en);
			//super.deleteBusiness(id);
	}

	
	/**
	 * 查询树
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<TreeVO> getTree(HttpServletRequest request,String colName,String colValue)throws Exception{
		QueryCondition qc = new QueryCondition(SysDictionary.class);
		if(!StringUtil.isEmpty(colName)){
			qc.addCondition(colName, "like",colValue+"%");
		}
		List <SysDictionary> list = this.query(qc);
		List<TreeVO> tree = new ArrayList<TreeVO>();
		for(SysDictionary s : list){
			String pid = s.getPcode();
			TreeVO vo = new TreeVO(s.getCode(),s.getValue(),pid == null ?null:pid.toString(),s.getCode());
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
		QueryCondition qc = new QueryCondition(SysDictionary.class);
		if(!StringUtil.isEmpty(colName)){
			qc.addCondition(colName, "like",colValue+"%");
		}
		List <SysDictionary> list = this.query(qc);
		List<TreeVO> tree = new ArrayList<TreeVO>();
		for(SysDictionary s : list){
			String pid = s.getPcode();
			TreeVO vo = new TreeVO(s.getValue(),s.getValue(),pid == null ?null:pid.toString(),s.getValue());
			tree.add(vo);
		}
		return tree;
	}


	//**********查询数据字典的父类***********//
	@Transactional(readOnly = true)
	public Page findAllParent(Page paramPage) {
		return dao.findAllParent(paramPage);
	}

	//**********查询数据字典父类对应的子类***********//
	@Transactional(readOnly = true)
	public Page findAllChildren(Page paramPage, String PID) {
		return dao.findAllChildren(paramPage,PID);
	}
	
	@Transactional(readOnly = false)
	public void saveDictionary(SysDictionary entity) {
		
		if(entity.getPcode()!=null){
			//entity.setIsleaf(String.valueOf(1));
		}else{
			//entity.setIsleaf(String.valueOf(0));
		}
		//entity.setIsSystem(2);
		dao.save(entity);
	}

	//根据Code获取对象
	@Transactional(readOnly = true)
	public SysDictionary findValueByCode(String code) {
		return dao.findValueByCode(code);
	}
	@Transactional(readOnly = true)
	public List<SysMetaData> metaDataByTabname(String tabname){
		return dao.metaDataByTabname(tabname);
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @return         
	 */ 
	public List<TreeVoR> getTreeList() {
		// TODO Auto-generated method stub
		String hql = "from SysDictionary";
		List<SysDictionary> list = dao.createQuery(hql).list();
		List<TreeVoR>treeList = new ArrayList<>();
		if (list!=null && list.size()>0) {
			for (SysDictionary sys : list) {
				TreeVoR vo = new TreeVoR();
				vo.setId(sys.getCode()==null?"-1":sys.getCode());
				vo.setName(sys.getValue()==null?"-1":sys.getValue());
				vo.setPid(sys.getPcode()==null?"-1":sys.getPcode());
				vo.setIsParent(sys.getIsParent()==null?"false":sys.getIsParent());
				treeList.add(vo);
			}
		}
		return treeList;
	}
}
