package com.css.business.web.subsysmanu.mauManage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauMachineSpeed;
import com.css.business.web.subsysmanu.mauManage.dao.MauMachineSpeedManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauMachineSpeedManageService")
public class MauMachineSpeedManageService extends BaseEntityManageImpl<MauMachineSpeed, MauMachineSpeedManageDAO> {
	
	@Resource(name="mauMachineSpeedManageDAO")
	private MauMachineSpeedManageDAO dao;
	
	
	@Override
	public MauMachineSpeedManageDAO getEntityDaoInf() {
		return dao;
	}
	
	public List<MauMachineSpeed> getListService(String pro){
		List<MauMachineSpeed> list = dao.getListDao(pro);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<MauMachineSpeed> getMauMachineSpeed(String proGgxh){
		String hql = " from MauMachineSpeed m where m.proGgxh = ? ";
		List<MauMachineSpeed> list = dao.createQuery(hql, proGgxh).list();
		return list;
	}
	
	public Page getMauMachineSpeedGrid(Page p,String proGgxh){
		String hql = " from MauMachineSpeed ";
		if(proGgxh!=null){
			hql +=" where proGgxh = ? ";
			hql +=" order by id ";
			Page page = dao.pageQuery(hql, p.getPageNo(), p.getPagesize(), proGgxh);
			return page;
		}else{
			hql +=" order by id ";
			Page page = dao.pageQuery(hql, p.getPageNo(), p.getPagesize());
			return page;
		}
	}
	
	public void updateMauMachineSpeed(MauMachineSpeed mms) throws Exception{
		dao.updateByCon(mms, false);
	}
	
	public void saveMauMachineSpeed(MauMachineSpeed mms){
		dao.save(mms);
	}
	
	
}
