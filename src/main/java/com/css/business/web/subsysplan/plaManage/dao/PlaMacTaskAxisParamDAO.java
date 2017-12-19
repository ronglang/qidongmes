package com.css.business.web.subsysplan.plaManage.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.business.web.subsysplan.bean.PlaMacTask;
import com.css.business.web.subsysplan.bean.PlaMacTaskAxisParam;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("plaMacTaskAxisParamDAO")
public class PlaMacTaskAxisParamDAO extends
		BaseEntityDaoImpl<PlaMacTaskAxisParam> {

	/**
	 * 
	 * @param workCode
	 * @param macCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<PlaMacTaskAxisParam> queryByParam(String workCode,
			String macCode) {
		String str = "select p from PlaMacTaskAxisParam p where  p.workcode=? and p.maccode=? ";
		List<PlaMacTaskAxisParam> lst = createQuery(str, workCode, macCode)
				.list();
		ArrayList<PlaMacTaskAxisParam> arrlist = new ArrayList<PlaMacTaskAxisParam>();
		for (PlaMacTaskAxisParam pmtap : lst) {
			arrlist.add(pmtap);
		}
		return arrlist;
	}

	public PlaMacTaskAxisParam getPlaMacTaskAxisParamToRfid(String workCode,
			String macCode) {
		String hql = " from PlaMacTaskAxisParam where workcode = ? and maccode = ? "
				+ " and  (product IS NULL OR product ='') AND (rfid IS NULL OR rfid ='') "
				+ " order by createDate ";
		@SuppressWarnings("unchecked")
		List<PlaMacTaskAxisParam> list = this.createQuery(hql, workCode,
				macCode).list();
		if (list.size() < 1 || list == null) {
			return null;
		}
		return list.get(0);
	}

	public PlaMacTask getPlaMacTask(String macCode) {
		String hql = " from PlaMacTask where macCode = ? and fstime IS NOT NULL order by fstime DESC ";
		@SuppressWarnings("unchecked")
		List<PlaMacTask> list = this.createQuery(hql, macCode).list();
		if (list.size() < 1 || list == null) {
			return null;
		}
		return list.get(0);
	}

	public List<PlaMacTaskAxisParam> getPlaMacTaskAxisParamByRfid(String rfid){
		String hql = " from PlaMacTaskAxisParam where rfid =? ";
		@SuppressWarnings("unchecked")
		List<PlaMacTaskAxisParam> list = this.createQuery(hql, rfid).list();
		if(list.size()<1||list==null){
			return null;
		}
		return list;
	}
	
}
