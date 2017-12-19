package com.css.business.web.subsysmanu.mauManage.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauMachineEmployee;
import com.css.business.web.subsysmanu.mauManage.dao.MauMachineEmployeeManageDAO;
import com.css.common.web.apachemq.bean.MqJTSaveDataVO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauMachineEmployeeManageService")
public class MauMachineEmployeeManageService extends BaseEntityManageImpl<MauMachineEmployee, MauMachineEmployeeManageDAO> {
	
	@Resource(name="mauMachineEmployeeManageDAO")
	private MauMachineEmployeeManageDAO dao;
	
	
	@Override
	public MauMachineEmployeeManageDAO getEntityDaoInf() {
		return dao;
	}

	public void updateValue(MqJTSaveDataVO vo) throws Exception{
		// 将机台与人员信息写入。
		if (vo.getMqType().equalsIgnoreCase("yes")) {
			MauMachineEmployee employee = new MauMachineEmployee();
			employee.setCreateDate(new Date());
			employee.setEmpRfid(vo.getEmpRfid());
			employee.setMacCode(vo.getMacCode());
			
			String course = vo.getCourseCode();
			String sql = "select pro_ggxh from pla_course where ws_code='"+course+"'";
			List<String> list = dao.createSQLQuery(sql).list();
			if(list.size()!=0){
				employee.setProGgxh((String)list.get(0));
			}
			employee.setTestTime(vo.getTesttime() == null ? null : new Float(vo.getTesttime() ));
			float aveSpeed = 0.0f;
			if (vo.getActualEndTime() > vo.getActualBeginTime()) {
				aveSpeed = (float) (vo.getAxixLen() / ((int) (vo
						.getActualEndTime() - vo.getActualBeginTime()) / 60000));
			}
			employee.setAveSpeed(aveSpeed);
			dao.save(employee);
		}
		else{
			
		}
	}

		
	
}
