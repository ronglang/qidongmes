package com.css.business.web.subsysstore.storeManage.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.subsysmanu.bean.MauForklift;
import com.css.business.web.subsysstore.storeManage.bean.MauForkliftQueryParamVO;
import com.css.business.web.subsysstore.storeManage.dao.StoreForkliftManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
@Service("storeForkliftManageService")
public class StoreForkliftManageService extends BaseEntityManageImpl<MauForklift, StoreForkliftManageDAO> {
	@Resource(name="storeForkliftManageDAO")
	//@Autowired
	private StoreForkliftManageDAO dao;
	@Override  
	public StoreForkliftManageDAO getEntityDaoInf() {
		return dao;
	}
	@Transactional(readOnly=true)
	public Page queryForkliftPageService(Page pageParam,MauForklift manuForklift, String startTime, String endTime) throws ParseException{
		MauForkliftQueryParamVO mauForkliftQueryParamVO = new MauForkliftQueryParamVO();
		StringBuilder sb=new StringBuilder("select m.id id,m.f_code fCode,m.f_type fType,m.f_driver fDriver,m.create_date createDate,m.status status ");
		sb.append("from mau_forklift m where 1=1 "); 
		String fcode=manuForklift.getfCode();
		String ftype=manuForklift.getfType();
		if(fcode!=null&&fcode.length()>0){
			sb.append("and f_code='"+fcode+"' ");  
		}
		if(ftype!=null&&ftype.length()>0){ 
			sb.append("and f_type='"+ftype+"' ");
		}
		if(startTime!=null&&startTime.length()>0&&endTime!=null&&endTime.length()>0){
//			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//			Date startTimeDate=sdf.parse(startTime);
//			Date endTimeDate=sdf.parse(endTime);
			sb.append("and create_date >= to_date('"+startTime+"','yyyy-mm-dd') ");
			sb.append("and create_date <= to_date('"+endTime+"','yyyy-mm-dd') ");
//			sb.append("and create_date between '"+sdf.format( startTimeDate)+"' and +'"+sdf.format( endTimeDate)+"' ");
		}
		sb.append("order by id asc ");
		return dao.queryForkliftPage(pageParam, sb.toString(), mauForkliftQueryParamVO);  
	}
	
	public MauForklift getEditData(Integer id) {
		return(dao.getEditData(id));
	}
	public String saveOrUpdate(MauForklift manuForklift) {
		return (dao.saveOrUpdate(manuForklift));
	}
	public void delete(String ids) {
		//dao.delete(str);
		String[] str=ids.split(",");
		  for (int i = 0; i < str.length; i++) {
			int id=Integer.parseInt(str[i]);
			dao.toDelete(id);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Map[] getFtype(){
		String hql = " SELECT DISTINCT fType FROM MauForklift WHERE fType != '' ";
		@SuppressWarnings("unchecked")
		List<Object> list = dao.createQuery(hql).list(); //得到的list为Object
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) { //根据ligerForm下拉框数据格式，封装数据
			Map<String, String> map = new HashMap<String, String>();
				for (int j = 0; j < 2; j++) {
					if(j == 0) {
						map.put("text", list.get(i).toString());
					}else {
						map.put("key", i + "");
					}
			}
			str[i] = map;
			
		}
		return str;
	}
	
}


