package com.css.business.web.subsyscraft.craManage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.bean.SysParameter;
import com.css.business.web.subsyscraft.craManage.dao.SysParameterManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("sysParameterManageService")
public class SysParameterManageService extends BaseEntityManageImpl<SysParameter, SysParameterManageDAO> {
	@Resource(name="sysParameterManageDAO")
	//@Autowired
	private SysParameterManageDAO dao;
	
	
	@Override
	public SysParameterManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	



	public Page getSysParameterPageList(Page param, String rfidNumber,
			String wireDiscPgxh, String status, String useStatus) {

		StringBuilder sql = new StringBuilder(
				"select o from SysParameter o where 1=1");
		if (null != rfidNumber && !"".equals(rfidNumber)) {
			sql.append("  and  o.rfidNumber='" + rfidNumber + "'");
		}
		if (null != wireDiscPgxh && !"".equals(wireDiscPgxh)) {
			sql.append("  and  o.wireDiscPgxh='" + wireDiscPgxh + "'");
		}
		if (null != status && !"".equals(status)) {
			sql.append("  and  o.status='" + status + "'");
		}
		if (null != useStatus && !"".equals(useStatus)) {
			sql.append("  and  o.useStatus='" + useStatus + "'");
		}
		sql.append("   order by  id ");
		Page page=null;
		try {
			//page = dao.getRecordList(sql,param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}



	public List<String> getSelectOption(String obj) {
		List<String> list=new ArrayList<>();
		try {
			String hql ="from  SysCommdic where clas=? ";
			list=dao.getSelectOption(hql,obj);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return list;
	}





	/**
	 * 根据线盘规格和线规格去获取参数对象
	 *@data:2017年7月26日
	@param wirediscPgxh
	@param wirePgxh
	@return
	@autor:wl
	 */
	public SysParameter getParamValue(String wirediscPgxh,
			String wirePgxh) {
		// TODO Auto-generated method stub
		SysParameter param=null;
		try {
			String sql="from SysParameter where paraType=? and paraName=?";
			List<SysParameter> list=dao.getParamList(sql,wirediscPgxh, wirePgxh);
			if(null!=list&&list.size()>0){
				param=list.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return param;
	}




	/**
	 * 根据线缆规格查询所有的线盘规格
	 *@data:2017年7月26日
	@param wirePgxh
	@return
	@autor:wl
	 */

	public List<SysParameter> getParamValueList(String wirePgxh) {
		// TODO Auto-generated method stub
		List<SysParameter> list =null;
		try {
			list=dao.getParamValueList(wirePgxh);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}


	
 }
