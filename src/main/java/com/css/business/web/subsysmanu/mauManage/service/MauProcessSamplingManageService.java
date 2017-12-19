package com.css.business.web.subsysmanu.mauManage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauProcessSampling;
import com.css.business.web.subsysmanu.mauManage.bean.MauProcessMonitoringVo;
import com.css.business.web.subsysmanu.mauManage.dao.MauProcessSamplingManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauProcessSamplingManageService")
public class MauProcessSamplingManageService extends
		BaseEntityManageImpl<MauProcessSampling, MauProcessSamplingManageDAO> {
	@Resource(name = "mauProcessSamplingManageDAO")
	// @Autowired
	private MauProcessSamplingManageDAO dao;

	@Override
	public MauProcessSamplingManageDAO getEntityDaoInf() {
		return dao;
	}

	public Page queryBackDateService(Page p, String endaxisnumber)
			throws Exception {
		StringBuffer sql = new StringBuffer(
				"SELECT "
						+ "product_name productname,product_type producttype,product_length productlength,unit unit,"
						+ "packing_type packingtype,color color,remark remark,product_batch productbatch,end_axis_number endaxisnumber "
						+ " FROM mau_product ");
		Page page = dao.queryBackDateDao(p, sql);
		return page;
	}

	public Page queryCraParameter(Page p, Integer productid) throws Exception {
		StringBuffer sql = new StringBuffer(
				"SELECT "
						+ "mm.mac_name macname,mm.mac_type mactype,ms.seq_name seqname,cp.param_name paramname,"
						+ "cp.cp_vaule vaule,mp.product_id productid FROM mau_process_monitoring mp "
						+ "LEFT JOIN mau_machine mm ON mp.mac_code = mm.mac_code "
						+ "LEFT JOIN mau_seq ms ON mp.seq_code = ms.seq_code "
						+ "LEFT JOIN cra_parameter cp ON cp.seq_code = mp.seq_code "
						+ "WHERE " + "param_name NOTNULL ");
		if (productid != null) {
			sql.append("AND product_id= " + productid);
		}
		Page page = dao.queryBackDateDao(p, sql);
		sql.append("ORDER BY seq_name ");
		return page;
	}

	/**
	 * 质量回溯-工序
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param endaxisnumber
	 * @return
	 * @author JiangShuai
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page queryBackAxisDatePageService(int pageNo, int pageSize,
			String endaxisnumber) {
		boolean flag = true;
		long totalCount = 1L;
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		List ll = new ArrayList();
		while (flag) {
			List axisDate = dao.queryAxisDate(endaxisnumber);
			if (axisDate != null && axisDate.size() == 1) {
				StringBuffer sq = new StringBuffer(
						"SELECT mac_code as maccode,ws_code as wscode,s.seq_name as seqname,start_date as startdate,"
								+ "end_date as enddate,mater_id as materid,manu_amount as manuamount,unit as unit,mac_code as maccode,"
								+ "own_axis_number as ownaxisnumber,source_axis_number as sourceaxisnumber,c_code as ccode,product_id as productid,"
								+ "mps_code as mpscode from mau_process_monitoring as m JOIN mau_seq as s ON m.seq_code=s.seq_code WHERE own_axis_number='"
								+ endaxisnumber + "'");
				List li = dao.querySQLPageList(sq.toString(), startIndex,
						totalCount, pageNo, pageSize,
						new MauProcessMonitoringVo());
				if (li != null) {
					ll.add(li.get(0));
				}
				endaxisnumber = axisDate.get(0).toString();
				if (endaxisnumber.equals("*")) {
					flag = false;
				}
			} else if (axisDate.size() > 1) {
				for (int i = 0; i < axisDate.size(); i++) {
					boolean fg = true;
					// endaxisnumber = axisDate.get(i).toString();
					while (fg) {
						List axis = dao.queryAxisDate(endaxisnumber);
						StringBuffer sq = new StringBuffer(
								"SELECT mac_code as maccode,ws_code as wscode,s.seq_name as seqname,start_date as startdate,"
										+ "end_date as enddate,mater_id as materid,manu_amount as manuamount,unit as unit,mac_code as maccode,"
										+ "own_axis_number as ownaxisnumber,source_axis_number as sourceaxisnumber,c_code as ccode,product_id as productid,"
										+ "mps_code as mpscode from mau_process_monitoring as m JOIN mau_seq as s ON m.seq_code=s.seq_code WHERE own_axis_number='"
										+ endaxisnumber + "'");
						List li = dao.querySQLPageList(sq.toString(),
								startIndex, totalCount, pageNo, pageSize,
								new MauProcessMonitoringVo());
						if (li != null) {
							for (int j = 0; j < li.size(); j++) {
								ll.add(li.get(j));
							}
						}
						endaxisnumber = axis.get(0).toString();
						if (endaxisnumber.equals("*")) {
							fg = false;
							if (i < axisDate.size() - 1)
								endaxisnumber = axisDate.get(i + 1).toString();
						}
					}
				}
				flag = false;
			}

		}
		return new Page(pageNo, totalCount, pageSize, ll);
	}
	
	/**
	 * 质量回溯明细--工艺参数
	 * @param seqname
	 * @return
	 * @author JiangShuai
	 */
	public List<MauProcessMonitoringVo> queryBackDateCraParamService(
			String seqname) {
		StringBuffer sql = new StringBuffer(
				" SELECT cp_vaule as vaule,act_vaule as actvaule,"
						+ "param_name as paramname FROM cra_parameter WHERE seq_code = "
						+ " (SELECT seq_code FROM mau_seq WHERE seq_name='"
						+ seqname + "') ");
		List<MauProcessMonitoringVo> list = dao.queryBackDateCraParamDao(sql
				.toString());
		return list;
	}
	
	/**
	 * 质量回溯明细--材料
	 * @param p
	 * @param materid
	 * @param seq_name
	 * @return
	 * @throws Exception
	 * @author JiangShuai
	 */
	public Page queryBackDateMaterService(Page p,String materid,String seqname) throws Exception{
		StringBuffer sql = new StringBuffer("SELECT	s.mater_name matername,s.mater_ggxh materggxh,s.mater_type matertype,"
				+ "M.product_id productid,M.mac_code maccode FROM store_mrecord s "
				+ "LEFT JOIN mau_process_monitoring M ON s.mater_id = M.mater_id WHERE s.mater_id = '"+materid+"' "
				+ "AND seq_code = (SELECT seq_code from mau_seq WHERE seq_name = '"+seqname+"')");
		Page page = dao.queryBackDateDao(p, sql);
		return page;
	}
	

}
