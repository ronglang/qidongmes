package com.css.business.web.subsysmanu.mauManage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauAxis;
import com.css.business.web.subsysmanu.bean.MauRfid;
import com.css.business.web.subsysmanu.mauManage.dao.MauAxisManageDAO;
import com.css.business.web.subsysmanu.mauManage.dao.MauRfidCardManageDAO;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.TransformStrToSixteen;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauRfidCardManageService")
public class MauRfidCardManageService extends BaseEntityManageImpl<MauRfid,MauRfidCardManageDAO > {
	@Resource(name="mauRfidCardManageDAO")
	//@Autowired
	private MauRfidCardManageDAO dao;
	private static String stringHex;
	@Resource(name="mauAxisManageDAO")
	private MauAxisManageDAO maDao;

	@Override
	public MauRfidCardManageDAO getEntityDaoInf() {
		return dao;
	}

	public Page queryRfidCardService(Page p, MauRfid rfid,
			String startTime, String endTime,String rfidNO,String materialType) {
		StringBuffer sql= new StringBuffer(" from MauRfid r  where 1=1  ");
		if(null!=rfidNO&&!"".equals(rfidNO)){
			sql.append("  and r.rfidNO  like '%"+rfidNO+"%'");
		}
		if(null!=startTime&&!"".equals(startTime)){
			sql.append("  and r.createDate>='"+startTime+"'");
		}
		if(null!=endTime&&!"".equals(endTime)){
			sql.append("  and r.createDate<='"+endTime+"'");
		}
		if(null!=materialType&&!"".equals(materialType)){
			sql.append("  and r.materialType='"+materialType+"'");
		}
		
		sql.append("    order by  r.createDate desc");
		Page page = dao.queryAllCard(p, sql);
		
		return page;
	}
 
	public Boolean toDeleteRfidById(String[] split) {
		Boolean flag =false;
		for (String id : split) {
			if(null!=id&&!"".equals(id)){
				
				StringBuilder  sql = new StringBuilder("DELETE  MauRfid r where r.id='"+id+"'");
				//在删除之前进行校验
				String sqlquery="select r from MauRfid r where r.id='"+id+"' and r.status='在用' ";
				 List list = dao.getHibernateTemplate().find(sqlquery);
				 if(list.size()==0){
					 dao.deleteRfidById(sql, id);
					 flag=true;
				 }else{
					 System.out.println("该卡正在使用，不能删除");
					 return flag;
				 }
			}
			
		}
		
		
		return flag;
	}
	//修改功能
	public boolean toUpdateRfidById(String id) {
		boolean flag=false;
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			try {
				
				dao.updateRfidById(ids[i]);
				flag=true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	
	//修改功能
		public String getNewRfidCode(String materialType) {
			String msg="";
		
			try {
				
				msg=dao.getNewRfidCode(materialType);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return msg;
		}
		
		//根据传入的RFID号修改状态
		public String updateStatusByRfidCode(String status,String rfidCode){
			String msg="";
			try {
				
				msg=dao.updateStatusByRfidCode(status,rfidCode);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return msg;
			
		}
	public void saveRfidCardNo(String rfidCardNumber,SysUser user,String materialType) {
		
		if(null!=rfidCardNumber&&!"".equals(rfidCardNumber)&&null!=user&&null!=materialType&&!"".equals(materialType)){
			String name = user.getName();
			Integer rf=Integer.parseInt(rfidCardNumber);
			dao.saveRfidCardNo(rf,name,materialType);
		}else{
			throw new RuntimeException("录入rfid卡号不能为空");
		}
		
	}
	
	public List<String> getSelectOption() {
		List<String> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder("select  o.value from  SysCommdic o  where clas=?");
		list=dao.getSelectOption(sql.toString());
		return list;
	}
/**
 * 
 *@data:2017年7月11日
@param materialType
@return
@autor:wl
 */
	public String getMessageReturnRfidByApp(String materialType) {
		String rfidCode="";
		String hexString="";
		try {
			rfidCode=dao.getMessageReturnRfidByApp(materialType);
			if(null!=rfidCode&&!"".equals(rfidCode)){
				hexString = TransformStrToSixteen.toHexString(rfidCode);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return hexString;
	}
/**
 * 
 *@data:2017年7月11日
@param materialType
@param amount
@autor:wl
 */
public String updateStatus(String rfidCode,String materialType, Integer amount) {
	String msg="";
	try {
		if(null!=rfidCode&&!"".equals(rfidCode)){
			String stringHex = TransformStrToSixteen.toStringHex(rfidCode);
			msg=dao.updateStatus(stringHex,materialType, amount,rfidCode);
		}
	} catch (Exception e) {
		// TODO: handle exception
		msg=e.getMessage();
	}
	
	return msg;
	
}
/**
 * 
 *@data:2017年7月12日
@param rfidCode
@param amount
@return
@autor:wl
 */
public HashMap<String, Object> getObjectByRfidCode(String rfidCode, String appId) {
	String message="";
	try {
		String stringHex = TransformStrToSixteen.toStringHex(rfidCode);
		List<MauRfid> list=dao.getObjectByRfidCode(stringHex);
		if(null!=list&&list.size()>0){
			MauRfid mauRfid = list.get(0);
			Integer amount = mauRfid.getAmount();
			if(null!=appId&&!"".equals(appId)){
				String[] split = appId.split(",");
				if(split.length==amount){
					message="成功";
					mauRfid.setAppId(appId);
					mauRfid.setStatus("在用");
					dao.updateByCon(mauRfid, false);//将手持机的TID存入到数据库
					return JsonWrapper.successWrapper(message);
				}else{
					message="传入的ID个数与数量不匹配";
					return JsonWrapper.failureWrapperMsg(message);
				}
			}else{
				message="未传入ID";
				return JsonWrapper.failureWrapperMsg(message);
			}
		}else{
			message="传入的RFID有误";
			return JsonWrapper.failureWrapperMsg(message);
		}
	} catch (Exception e) {
		message=e.getMessage();
		return JsonWrapper.failureWrapperMsg(message);
	}
	
}

/**
 * 初始化RFID卡，线盘卡直接绑定
 * @param appIds
 * @param pType
 * @return
 * @throws Exception
 */
@SuppressWarnings("unchecked")
public boolean saveRfid(String appIds,String pType) throws Exception{
	String[] appId = appIds.split(",");
	String[] p = pType.split(",");
	boolean flag = false;
	String rf2 = "";
	int a = 0;
	for (int i=0;i<appId.length;i++) {
		String app = appId[i];
		String hql = " from MauRfid where appId = ? ";
		List<MauRfid> list = dao.createQuery(hql, app.trim()).list();
		if(list.size()>0 && list!=null){
			continue;
		}
		flag = false;
		MauRfid mauRfid = new MauRfid();
		mauRfid.setAppId(app.trim());
		char at = app.trim().charAt(0);
		//线盘P 地标G 原材料R 人员E 机台M
		if(at=='P'){
			mauRfid.setMaterialType("线盘卡");
			String rf = app.trim().substring(0, app.trim().length()-1);
			
			if(!rf.equals(rf2)){
				rf2 = rf;
				MauAxis ma = maDao.getMauAxis(p[a]);
				if(ma!=null){
					ma.setRfid(rf2);
					dao.updateByCon(ma, false);
					a+=1;
				}
			}
		}else if(at=='G'){
			mauRfid.setMaterialType("地标卡");
		}else if(at=='R'){
			mauRfid.setMaterialType("物料卡");
		}else if(at=='E'){
			mauRfid.setMaterialType("人员卡");
		}else if(at=='M'){
			mauRfid.setMaterialType("机台卡");
		}
		mauRfid.setCreateDate(new Date());
		mauRfid.setCreateBy("APP");
		mauRfid.setStatus("在用");
		mauRfid.setAmount(1);
		dao.save(mauRfid);
		flag = true;
	}
	return flag;
}




/**
 * 来自手机端保存机台的RFID号
 *@data:2017年7月19日
@param rfidCode
@param appId
@return
@autor:wl
 */
public HashMap<String,Object> saveRfidByMachine(String rfidCode, String appId) {
	String message="";
	try {
		List<MauRfid> code = dao.getObjectByRfidCode(rfidCode);
		if(null!=code&&code.size()>0){
				message="RFID号已经被某机台使用了";
				return JsonWrapper.failureWrapperMsg(message);
				
		}else{
			dao.saveRfid(rfidCode,appId);
			message="保存成功";
			return JsonWrapper.successWrapper(message);	
		}
		
	} catch (Exception e) {
		e.printStackTrace();
		message=e.getMessage();
		return JsonWrapper.failureWrapperMsg(message);
	}
	
}

public List<MauRfid> repairRfid(String rfids){
	String [] rfid = rfids.split(",");
	String hql = "from MauRfid where appId like '%"+rfid[0].substring(0,rfid[0].length()-1)+"%'  ";
	@SuppressWarnings("unchecked")
	List<MauRfid> list = dao.createQuery(hql).list();
	return list;
}


	
	
	
}
