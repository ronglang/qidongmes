package com.css.business.web.subsysplan.plaManage.service;

import java.util.HashMap;

import com.css.business.web.subsysplan.plaManage.bean.PlaContractVo;

/**
 * 排产功能，各个子模块接口
 * @author Administrator
 *
 */
public interface SchedulingInterface {
	
	public static final String prixf = " and  createBy = 'york' ";

	/**
	 * 计算机台可用量，可用值
	 * 根据合同中的工艺路线，查找对应的工序、返回工艺路线、对应工序、对应机台，可以使用的日期，
	 * PlaContractVo,合同对象，里边包含工艺路线id,可以此查出所需工序，以及需要的机台
	 * success ：true，表示机台部分能支持生产。false表示不能支持生产该合同
	 * 
	 * @return 可用机台
	 */
	public HashMap<String,Object> getMachineRoute(PlaContractVo bean);
	
	/**
	 * 计算材料消耗量（不包括损耗）
	 * 根据合同对象信息，计算出理论上需要消耗的原材料的总量（这是理论值，不包含客户调试所消耗的数量值和内容）
	 * @param bean
	 * @return
	 */
	public HashMap<String,Object> getConsumeMaterial(PlaContractVo bean);
	
	/**
	 * 计算gognshi
	 * 根据合同和可用机台，计算所需要的工时，如果工时计算之后，超出交货期限，返回提示值，
	 * @param bean
	 * @param map
	 * @return
	 */
	public HashMap<String,Object> getConsumeWorkHours(PlaContractVo bean,HashMap<String,Object> map);
	
	
	
	
}
