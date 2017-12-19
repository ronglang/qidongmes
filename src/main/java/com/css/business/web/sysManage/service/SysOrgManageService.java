package com.css.business.web.sysManage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.sysManage.bean.SysOrg;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.business.web.sysManage.dao.SysOrgManageDAO;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.bean.TreeVO;
import com.css.common.web.syscommon.controller.support.QueryCondition;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("sysOrgManageService")
public class SysOrgManageService extends BaseEntityManageImpl<SysOrg, SysOrgManageDAO> {
	@Resource(name="sysOrgManageDAO")
	//@Autowired
	private SysOrgManageDAO dao;
	
	
	@Override
	public SysOrgManageDAO getEntityDaoInf() {
		return dao;
	}
	
	@Transactional(readOnly = true)
	public List<TreeVO> getTree(SysUser user,String code,Object dvalue)throws Exception{
		QueryCondition qc = new QueryCondition(SysOrg.class);
		if(!user.isAdmin()){
			//if(!StringUtil.isEmpty(code)){
//				qc.addCondition("orgCode", "like", user.getOrgCode() + "%");
			//}
			switch(user.getUserType()){
			case "C1060002":
			case "C1060003":
			case "C1060004":
				break;
			default:
				//qc.addCondition("orgCode", "like", user.getOrgCode() + "%");
			}
		}
		List <SysOrg> list = this.query(qc);
		List<TreeVO> tree = new ArrayList<TreeVO>();
		for(SysOrg s : list){
			TreeVO vo = new TreeVO(s.getOrgCode(),s.getOrgName().toString(),s.getPcode()==null?"-1":s.getPcode().toString(),s.getOrgCode());
			tree.add(vo);
		}
		return tree;
	}

	@Transactional(readOnly = true)
	public List<TreeVO> getTrees(SysUser user,String code,Object dvalue)throws Exception{
		QueryCondition qc = new QueryCondition(SysOrg.class);
		if(!user.isAdmin()){
			//if(!StringUtil.isEmpty(code)){
				//qc.addCondition("orgCode", "like", user.getOrgCode() + "%");
			//}
		}
		List <SysOrg> list = dao.querys(qc);
		List<TreeVO> tree = new ArrayList<TreeVO>();
		for(SysOrg s : list){
			TreeVO vo = new TreeVO(s.getOrgCode(),s.getOrgName().toString(),s.getPcode()==null?"-1":s.getPcode().toString(),s.getOrgCode());
			tree.add(vo);
		}
		return tree;
	}
	
	@Transactional(readOnly = true)
	public List<SysOrg> getTreeList(SysUser user,String code,Object dvalue)throws Exception{
		QueryCondition qc = new QueryCondition(SysOrg.class);
		if(!user.isAdmin()){
			if(!StringUtil.isEmpty(code)){
				//qc.addCondition("orgCode", "like", user.getOrgCode() + "%");
			}
		}
		List <SysOrg> list = this.query(qc);
		return list;
	}
	
	//保存、更新机构信息
	@Transactional(readOnly = false)
	public void saveOrgInfo(SysOrg sysOrg) {
			List<SysOrg> list = dao.findOrgListSize(sysOrg.getPcode());//查询机构部门编码
			if(list.size()>0){
				SysOrg s = list.get(0);
				String ss = s.getOrgCode();
				Long sss = Long.parseLong(ss)+1;
				sysOrg.setOrgCode(String.valueOf(sss));
			}else{
				Long old = Long.parseLong(sysOrg.getPcode()+"0001");
				String newOrgCode = String.valueOf(old);
				sysOrg.setOrgCode(newOrgCode);
			}
			
		for(SysOrg o : list){
			if(o.getId().equals(sysOrg.getId())){
				this.dao.getSessionFactory().getCurrentSession().evict(o);
			}
		}	
		dao.saveOrgInfo(sysOrg);
	}
	
	
	//根据部门编码获取部门对象信息
	@Transactional(readOnly = true)
	public SysOrg findByOrgCode(String orgCode){
		return dao.findByOrgCode(orgCode);
	}
	
	@Transactional(readOnly=true)
	public Page getDataListPage(Page param,String orgName) {
		StringBuilder sql = new StringBuilder("select o from SysOrg o where 1=1");
		if(null!=orgName&&!"".equals(orgName)){
			sql.append(" and o.orgName='"+orgName+"' ");
		}
		Page page =dao.getDataListPage(param,sql);
		return page;
	}
}
