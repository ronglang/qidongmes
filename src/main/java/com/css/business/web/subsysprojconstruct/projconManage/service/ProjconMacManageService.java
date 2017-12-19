package com.css.business.web.subsysprojconstruct.projconManage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysprojconstruct.bean.ProjconMac;
import com.css.business.web.subsysprojconstruct.projconManage.dao.ProjconMacManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("projconMacManageService")
public class ProjconMacManageService extends BaseEntityManageImpl<ProjconMac, ProjconMacManageDAO> {
	@Resource(name="projconMacManageDAO")
	//@Autowired
	private ProjconMacManageDAO dao;
	
	
	@Override
	public ProjconMacManageDAO getEntityDaoInf() {
		return dao;
	}
	@SuppressWarnings("unchecked")
	public List<ProjconMac> getProjconMacService(String processName) {
		String hql = "from ProjconMac where processName=?";
		List<ProjconMac> list = dao.createQuery(hql, processName).list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<ProjconMac> getserchChangeProjconMacService(String processName,String searchSate) {
		String hql = "from ProjconMac where processName=? and macState=?";
		List<ProjconMac> list = dao.createQuery(hql, processName,searchSate).list();
		return list;
	}
	public String getsaveProjconMacManageService(String macCode,String macState, String processName) {
		ProjconMac projconMac = new ProjconMac();
		projconMac.setMacCode(macCode);
		projconMac.setMacState(macState);
		projconMac.setProcessName(processName);
		dao.save(projconMac);
		return "保存成功";
	}
	@SuppressWarnings("unchecked")
	public String getserchProjconMacManageForSelecStateService(String macCode) {
		String hql = "from ProjconMac where macCode=?";
		List<ProjconMac> list = dao.createQuery(hql,macCode).list();
		String selecState = list.get(0).getSelecState()==null?"":list.get(0).getSelecState();
		return selecState;
	}
	public String getupdateProjconMacSelecStateManageService(String selecState,String macCode) {
		String hql = "update ProjconMac set selecState=? where macCode=?";
		dao.createQuery(hql, selecState,macCode).executeUpdate();
		return "更新成功";
	}
	public String deleteProjconMacManageBySelecStateManageService() {
		String hql = "delete ProjconMac where selecState=?";
		dao.createQuery(hql,"true").executeUpdate();
		return "删除成功";
	}
	public String initUpdateSelecStateManageService() {
		String hql = "update ProjconMac set selecState=? where selecState=?";
		dao.createQuery(hql, "","true").executeUpdate();
		return "初始化成功";
	}
	@SuppressWarnings("unchecked")
	public List<ProjconMac> getserchSelecStateNumService() {
		String hql = "from ProjconMac where selecState=?";
		List<ProjconMac> list = dao.createQuery(hql,"true").list();
		return list;
	}
	public String getUpdateProjconMacManageService(ProjconMac projconMac) {
		return dao.getUpdateProjconMacManageDao(projconMac);
	}
}
