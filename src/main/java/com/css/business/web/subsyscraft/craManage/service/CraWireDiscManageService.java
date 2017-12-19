package com.css.business.web.subsyscraft.craManage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.bean.CraWireDisc;
import com.css.business.web.subsyscraft.bean.ParamWireVo;
import com.css.business.web.subsyscraft.bean.SysParameter;
import com.css.business.web.subsyscraft.craManage.dao.CraWireDiscManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("craWireDiscManageService")
public class CraWireDiscManageService extends BaseEntityManageImpl<CraWireDisc, CraWireDiscManageDAO> {
	@Resource(name="craWireDiscManageDAO")
	//@Autowired
	private CraWireDiscManageDAO dao;
	
	
	@Override
	public CraWireDiscManageDAO getEntityDaoInf() {
		return dao;
	}
	@Autowired
	private SysParameterManageService paramService;
	
	/**
	 * 提供给机台或者仓库修改线盘使用状态使用为空闲
	 *@data:2017年7月25日
	@param rfidNumber
	@autor:wl
	 */
	public boolean updateWireDiscUsedStatusByRfid(String rfidNumber){
		boolean flag=false;
		try {
			if(null!=rfidNumber&&!"".equals(rfidNumber)){
				String[] rfidNumer = rfidNumber.split(",");
				String msg="空闲";
				for (String rfid : rfidNumer) {
					dao.updateUsedStatusByRfid(rfid,msg);
				}
				flag=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}
	

	/**
	 * 根据RFID修改线盘的使用状态为在用
	 *@data:2017年7月25日
	@param rfidNumber
	@autor:wl
	 */
	public boolean updateWireDiscUsedStatus(String rfidNumber){
		boolean flag=false;
		try {
			if(null!=rfidNumber&&!"".equals(rfidNumber)){
				String[] rfidNumer = rfidNumber.split(",");
				String msg="使用中";
				for (String rfid : rfidNumer) {
					dao.updateUsedStatusByRfid(rfid,msg);
				}
				flag=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}



	public Page getCraWireDiscPageList(Page param, String rfidNumber,
			String wireDiscPgxh, String status, String useStatus) {

		StringBuilder sql = new StringBuilder(
				"select o from CraWireDisc o where 1=1");
		if (null != rfidNumber && !"".equals(rfidNumber)) {
			sql.append("  and  o.rfidNumber like '%" + rfidNumber + "%'");
		}
		if (null != wireDiscPgxh && !"".equals(wireDiscPgxh)) {
			sql.append("  and  o.wireDiscPgxh = '" + wireDiscPgxh + "'");
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
			page = dao.getRecordList(sql,param);
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
 * 根据ID修改线盘
 *@data:2017年7月25日
@param rfidNumber
@param wireDiscPgxh
@param status
@param useStatus
@param externalDiameter
@param boreDiameter
@param id
@autor:wl
 */

	public void updateById(String rfidNumber, String wireDiscPgxh,
			String status, String useStatus, Double externalDiameter,
			Double boreDiameter,String materialTexture,Double capacity, Integer id) {
		try {
			String hql="update CraWireDisc set rfidNumber=?,wireDiscPgxh=?,status=?,useStatus=?,externalDiameter=?,boreDiameter=?,materialTexture=?,capacity=?  where id=?";
			dao.updateById(hql,rfidNumber,  wireDiscPgxh,
					 status,  useStatus,  externalDiameter,
					 boreDiameter,materialTexture,capacity,id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 根据线规格、需要生产线长（或者指定线盘）获取指定规格线盘空闲的线盘
	 *@data:2017年7月27日
	@param wirePgxh
	@param wirelength
	@param wirediscPgxh
	@return
	@autor:wl
	 */
	public List<CraWireDisc>  getCraWireDiscList(String wirePgxh,Double wirelength,String wirediscPgxh){
		List<CraWireDisc> list = new ArrayList<>();
		int amount=0;
		try {
			if(null!=wirediscPgxh&&!"".equals(wirediscPgxh)){
				if(null!=wirePgxh&&!"".equals(wirePgxh)){
					//根据线盘规格和线规格去查询对应的容量值
					SysParameter param=paramService.getParamValue(wirediscPgxh,wirePgxh);
					if(null!=wirelength&&wirelength!=0D){
						String  val = param.getParaValue();
						if(null!=val){
							Double value =Double.parseDouble(val);
							Double number=	wirelength/value;
							amount =(int) Math.ceil(number);
						}
						list=this.findCraWireDiscList(wirediscPgxh,amount); 
					}
				}
				
			}else{
				if(null!=wirePgxh&&!"".equals(wirePgxh)){
					List<SysParameter> listParam = paramService.getParamValueList(wirePgxh);
					//ParamWireVo  封装线盘的类型   该线盘空闲的数量  每个线盘容纳的value  能不能继承加个比较器Comparator 
					List<ParamWireVo> voList = new ArrayList<>();
					Map<Boolean,Integer> map =new HashMap<Boolean, Integer>();
					for (SysParameter param : listParam) {
						ParamWireVo vo=new ParamWireVo();
						//根据param获取的线盘型号去查找数据库  找出线盘处于空闲最多的数量  如果就只有一个就返回最多那个
						Integer number= this.getWireAmountBy(param);
						map=this.checkWireDiscIsEnough(number,param,wirelength);
						for (java.util.Map.Entry<Boolean, Integer> mapvo: map.entrySet()) {
							Boolean key = mapvo.getKey();
							Integer value = mapvo.getValue();
							
							if(key){
								vo.setMessage("足够");
								vo.setValue(value);
							}else{
								vo.setMessage("该种规格线盘不足，数量为:"+number);
							}
						}		
								vo.setSysParameter(param);
								vo.setAmount(number);
								voList.add(vo);
								ParamWireVo paramWireVo= this.getParamWireVo(voList);
								if(paramWireVo.getLeftAmount()>0){
									//获取空余数最多的线盘集合
									list=this.findCraWireDiscList(paramWireVo.getSysParameter().getParaType(),paramWireVo.getValue()); 
								}
								
					}
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;		
		
	}
	/**
	 * 求出线盘数量空余个数最多的那种线盘规格 
	 *@data:2017年7月27日
	@param voList
	@return
	@autor:wl
	 */
private ParamWireVo getParamWireVo(List<ParamWireVo> voList) {
	int maxClick=0;
	for (ParamWireVo paramWireVo : voList) {
		if(null!=paramWireVo){
			if(paramWireVo.getMessage().equals("足够")){
				if(paramWireVo.getLeftAmount()>maxClick){
					 maxClick=paramWireVo.getLeftAmount();
				}
			}else{
				continue;
			}
		}
	}
	int amount=maxClick;
	for (ParamWireVo paramWireVo : voList) {
		if(paramWireVo.getLeftAmount()==amount){
			return paramWireVo;
		}
	}
	
		return null;
	}


	
			/**
			 * 判断当前规格型号的线盘是否足够	
			 *@data:2017年7月27日
			@param number
			@param param
			@return
			@autor:wl
			 */
		private Map<Boolean,Integer>  checkWireDiscIsEnough(Integer number, SysParameter param,Double wirelength) {
			boolean flag=false;
			Map<Boolean,Integer> map =new HashMap<Boolean, Integer>();
			try {
				if(null!=param){
					String value = param.getParaValue();
					Double val = Double.parseDouble(value);
					double aoumt=wirelength/val;
					int ceil =(int)Math.ceil(aoumt);
					if(number>=ceil){
						flag=true;
						map.put(flag, ceil);
					}else{
						map.put(flag, ceil);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		// TODO Auto-generated method stub
		return map;
	}

		/**
		 * 根据线盘参数获取当前规格型号空闲线盘的个数  当前
		 *@data:2017年7月26日
		@param param
		@return
		@autor:wl
		 */
	private Integer getWireAmountBy(SysParameter param) {
		// TODO Auto-generated method stub
		Integer amount=0;
		try {
			if(null!=param){
				 amount=dao.getWireAmountBy(param.getParaType());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return amount;
	}

	private List<CraWireDisc> findCraWireDiscList(String wirediscPgxh,
			int amount) {
		// (使用方法list1 = list.subList(0, amount);取出前面几条数据)
		List<CraWireDisc> list =null;
		List<CraWireDisc> list1 =new ArrayList<>();
		try {
			list = dao.findCraWireDiscList(wirediscPgxh);
			if(null!=list&&list.size()>0){
				if(list.size()<amount){
					throw new RuntimeException("该种规格线盘不足，请更换线盘规格");
				}else{
					list1=list.subList(0, amount);
				}
			}else{
				throw new RuntimeException("该种规格线盘全在使用中");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return list1;
	}

	public boolean saveProductManageService(CraWireDisc mpm) {
		return dao.saveProductManageDao(mpm);
	}

	public boolean updateProductManageService(Object obj) {
		return dao.updateProductManageDao(obj);
		
	}
	/**
	 * 根据id去获取线盘数据 回显便捷的数据
	 *@data:2017年8月1日
	@param id
	@return
	@autor:wl
	 */
	public CraWireDisc getCraWireDiscById(Integer id) {
		// TODO Auto-generated method stub
		CraWireDisc craWireDisc=null;
		try {
			String hql ="from CraWireDisc where id=?";
			craWireDisc=dao.getCraWireDiscById(hql ,id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return craWireDisc;
	}
 }
