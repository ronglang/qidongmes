package com.css.business.web.subsysplan.plaManage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaMachinePlanMater;
import com.css.business.web.subsysplan.plaManage.dao.PlaMachinePlanMaterManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaMachinePlanMaterManageService")
public class PlaMachinePlanMaterManageService extends BaseEntityManageImpl<PlaMachinePlanMater, PlaMachinePlanMaterManageDAO> {
	@Resource(name="plaMachinePlanMaterManageDAO")
	//@Autowired
	private PlaMachinePlanMaterManageDAO dao;
	
	
	@Override
	public PlaMachinePlanMaterManageDAO getEntityDaoInf() {
		return dao;
	}

/**
 * 根据工单号去查询领料信息  
 *@data:2017年7月21日
@param workCode
@return
@autor:wl
 */
	public List<PlaMachinePlanMater> getPlaMachinePlanMater(String workCode) {
		// TODO Auto-generated method stub
		
		List<PlaMachinePlanMater> list = new ArrayList<>();
		
		try {
			String sql="select sum(amount) amount,unit,ggxh,course_code,mater_name,machine_id,(select mac_name from mau_machine m "
					+ "where m.id=machine_id) machineName from pla_machine_plan_mater  where course_code=?  group by unit,ggxh,course_code,mater_name,"
					+ "machine_id ";
			list=dao.getPlaMachinePlanMater(sql,workCode);
			if(null==list||list.size()==0){
				throw new RuntimeException("未查询到出库的领料信息");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public void savePlaMachinePlanMater(PlaMachinePlanMater ppm){
		dao.save(ppm);
	}
	
	public PlaMachinePlanMater getPlaMachinePlanMaterByCourseCode(String courseCode, String machineId ){
		String sql = "from PlaMachinePlanMater where courseCode = '"+courseCode +"' and machineId='"+machineId+"'";
		Query query = dao.createQuery(sql);
		List<PlaMachinePlanMater> list = query.list();
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param code
	 * @return         
	 */ 
	public List<PlaMachinePlanMater> queryByCourseCode(String code) {
		// TODO Auto-generated method stub
		if (code!=null && code!="") {
			String hql ="from PlaMachinePlanMater where courseCode = '"+code+"'";
			List<PlaMachinePlanMater> list = dao.createQuery(hql).list();
			if (list !=null && list.size()>0) {
				return list;
			}
		}
		return null;
	}

	
	
	
}
