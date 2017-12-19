package com.css.business.web.subsysmanu.mauManage.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysmanu.bean.MauMachineSpeed;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("mauMachineSpeedManageDAO")
public class MauMachineSpeedManageDAO extends BaseEntityDaoImpl<MauMachineSpeed>  {
	
	
	@SuppressWarnings("unchecked")
	public List<MauMachineSpeed> getListDao(String pro){
		String hql = "from MauMachineSpeed mau where mau.proGgxh=:pro";
		Query query = getSession().createQuery(hql).setParameter("pro", pro);
		List<MauMachineSpeed> list = query.list();
		return list;
	}
	
	
	/**
	 * @TODO: 根据机台与规格型号，取体符合规格的机台
	 * @author: zhaichunlei
	 & @DATE : 2017年8月3日
	 * @param seq
	 * @param ggxh
	 * @return
	 */
	public List<MauMachineSpeed> getMachineBySeqAndGgxh(String seqCode,String ggxh){
		String sql = "from MauMachineSpeed m where  proGgxh=? and exists(select 1 from MauMachine s where m.id=s.machineId and s.seqCode=? )";
		@SuppressWarnings("unchecked")
		List<MauMachineSpeed> lst =  this.createQuery(sql, ggxh,seqCode ).list();
		return lst;
	}
	
	/**
	 * @TODO: 根据机台id与产品规格型号，取唯一的记录
	 * @author: zhaichunlei
	 & @DATE : 2017年8月10日
	 * @param macId
	 * @param ggxh
	 * @return
	 */
	public MauMachineSpeed getMachineByMachineAndGgxh(Integer macId,String ggxh){
		String sql = "from MauMachineSpeed m where  proGgxh=? and machineId=? ";
		@SuppressWarnings("unchecked")
		List<MauMachineSpeed> lst =  this.createQuery(sql, ggxh,macId).list();
		if(lst == null || lst.size() == 0)
			return null;
		else
			return lst.get(0);
	}
}
