package com.css.business.web.subsyssell.sellManage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsyssell.bean.SellContractDetail;
import com.css.business.web.subsyssell.bean.SellContractPlanBatch;
import com.css.business.web.subsyssell.sellManage.dao.SellContractDetailManageDAO;
import com.css.business.web.subsyssell.sellManage.dao.SellContractPlanBatchManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("sellContractPlanBatchManageService")
public class SellContractPlanBatchManageService extends BaseEntityManageImpl<SellContractPlanBatch, SellContractPlanBatchManageDAO> {
	@Resource(name="sellContractPlanBatchManageDAO")
	//@Autowired
	private SellContractPlanBatchManageDAO dao;
	
	@Resource(name = "sellContractDetailManageDAO")
	private SellContractDetailManageDAO sellContractDetailManageDAO;
	
	@Override
	public SellContractPlanBatchManageDAO getEntityDaoInf() {
		return dao;
	}
	
	/**
	 * @todo:  删除批次
	 * @author : zhaichunlei
	 * @date: 2017年9月18日
	 */
	public void deleteBat(String ids) throws Exception{
		String idss[] = ids.split(",");
		if(idss != null && idss.length > 0){
			for(String id : idss){
				if(id != null && (id = id.trim()).length() > 0 ){
					//SellContractPlanBatch pb = dao.get(Integer.parseInt(id));
					List<SellContractDetail> scdLst = sellContractDetailManageDAO.findBy("planBatchId", Integer.parseInt(id));
					if(scdLst != null && scdLst.size() > 0){
						for(SellContractDetail  scd : scdLst){
							String state = scd.getPbatDetailState();
							//已生成的则不能删除生产通知单
							if(!"未生成".equals(state)){
								throw new Exception("批次号（"+scd.getBatCode()+"）存在工作单，不能删除");
							}
						}
					}
					
					//删除detail
					String sql = " DELETE FROM sell_contract_detail  WHERE plan_batch_id = ? ";
					dao.deleteBySql(sql, Integer.parseInt(id));
				}
			}
		}
	}
}
