package com.css.business.web.subsysmanu.mauManage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauProcessMonitoring;
import com.css.business.web.subsysmanu.mauManage.bean.MauProcessMonitoringVo;
import com.css.business.web.subsysmanu.mauManage.dao.MauProcessMonitoringManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauProcessMonitoringManageService")
public class MauProcessMonitoringManageService
		extends
		BaseEntityManageImpl<MauProcessMonitoring, MauProcessMonitoringManageDAO> {
	@Resource(name = "mauProcessMonitoringManageDAO")
	// @Autowired BaseEntityDaoImpl
	private MauProcessMonitoringManageDAO dao;

	@Override
	public MauProcessMonitoringManageDAO getEntityDaoInf() {
		return dao;
	}

	/**
	 * 
	 * @param p
	 * @param ent
	 * @param productid
	 * @return
	 * @throws Exception
	 * @author JiangShuai
	 */
	public Page queryMauProcessMonitoring(Page p, MauProcessMonitoring ent,
			Integer productid) throws Exception {
		StringBuffer sql = new StringBuffer(
				" SELECT m.product_id productid,p.is_qualified isqualified,p.sampl_date sampldate,"
						+ "m.own_axis_number ownaxisnumber,m.source_axis_number sourceaxisnumber,m.ws_code wscode,"
						+ "s.seq_name seqname,m.mac_code maccode from mau_seq s LEFT JOIN mau_process_monitoring m on s.seq_code=m.seq_code "
						+ "LEFT JOIN mau_process_sampling p ON m.seq_code=p.seq_code WHERE product_id NOTNULL ");
		if (productid != null) {
			sql.append("AND product_id= " + productid);
		}
		Page page = dao.queryMauProcessMonitoring(p, ent, sql);
		return page;
	}

	/**
	 * 
	 * @param p
	 * @param ent
	 * @param productid
	 * @return
	 * @throws Exception
	 * @author JiangShuai
	 */
	public Page quryMauMonitoringDetail(Page p, MauProcessMonitoring ent,
			Integer productid,String seqname) throws Exception {

		StringBuffer sql = new StringBuffer(
				" SELECT mm.mac_name macname,mm.mac_type mactype,ms.seq_name seqname,cp.param_name paramname,"
						+ " cp.cp_vaule vaule,mp.product_id productid from mau_process_monitoring mp "
						+ " LEFT JOIN mau_machine mm on mp.mac_code=mm.mac_code "
						+ " LEFT JOIN mau_seq ms on mp.seq_code=ms.seq_code "
						+ " LEFT JOIN cra_parameter cp on cp.seq_code=mp.seq_code "
						+ " WHERE param_name NOTNULL AND ms.seq_name='"+seqname+"' ");
		if (productid != null) {
			sql.append(" AND product_id= " + productid);
		}
		Page page = dao.doSearchDao(p, ent, sql);
		return page;
	}
	
	public List<MauProcessMonitoringVo> queryListService(Integer productid,String seqname){
		StringBuffer sql = new StringBuffer(
				" SELECT mm.mac_name as macname,mm.mac_type as mactype,ms.seq_name as seqname,cp.param_name as paramname,"
						+ " cp.cp_vaule as vaule,mp.product_id as productid,mp.source_axis_number as sourceaxisnumber from mau_process_monitoring as mp "
						+ " LEFT JOIN mau_machine as mm on mp.mac_code=mm.mac_code "
						+ " LEFT JOIN mau_seq as ms on mp.seq_code=ms.seq_code "
						+ " LEFT JOIN cra_parameter as cp on cp.seq_code=mp.seq_code "
						+ " WHERE param_name NOTNULL AND ms.seq_name='"+seqname+"' ");
		if (productid != null) {
			sql.append(" AND product_id= " + productid);
		}
		List<MauProcessMonitoringVo> list = dao.queryListDao(productid, seqname, sql.toString());
		return list;
	}
	

}
