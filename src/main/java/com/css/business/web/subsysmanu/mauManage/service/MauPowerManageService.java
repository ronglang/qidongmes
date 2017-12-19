package com.css.business.web.subsysmanu.mauManage.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauPower;
import com.css.business.web.subsysmanu.mauManage.dao.MauPowerManageDAO;
import com.css.common.web.apachemq.bean.MauPowerVo;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauPowerManageService")
public class MauPowerManageService extends
		BaseEntityManageImpl<MauPower, MauPowerManageDAO> {
	@Resource(name = "mauPowerManageDAO")
	// @Autowired
	private MauPowerManageDAO dao;

	@Override
	public MauPowerManageDAO getEntityDaoInf() {
		return dao;
	}

	public void saveMauPower(MauPowerVo vo) {
		MauPower power = new MauPower();
		power.setStartElec(vo.getStartPower());
		power.setEndElec(vo.getEndPower());
		power.setElec(vo.getEndPower() - vo.getStartPower());
		power.setEmployee(vo.getEmployees());
		power.setWsCode(vo.getWorkOrder());
		power.setMacCode(vo.getMachineCode());
		Timestamp d = new Timestamp(System.currentTimeMillis()); // 获取当前时间
		power.setCreateDate(d);
		dao.save(power);
	}

	@SuppressWarnings("unchecked")
	public MauPowerVo getEchartsDate(String macCode, String employee,
			String wsCode) {
		String hql = " from MauPower where 1=1 ";
		int i = 0;
		if (macCode != null && !"".equals(macCode)) {
			hql += " AND macCode = '" + macCode + "' ";
			i++;
		}
		if (employee != null && !"".equals(employee)) {
			hql += " AND employee LIKE '%" + employee + "%' ";
			i++;
		}
		if (wsCode != null && !"".equals(wsCode)) {
			hql += " AND wsCode = '" + wsCode + "'  ";
			i++;
		}
		if (i == 0) {
			hql += " AND macCode = 'ALL' ";
		}
		hql += " order by createDate ";

		List<MauPower> list = dao.createQuery(hql).list();
		MauPowerVo vo = new MauPowerVo();
		List<String> series = new ArrayList<String>();
		List<String> lengds = new ArrayList<String>();
		for (MauPower power : list) {
			DateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
			String date = format.format(power.getCreateDate());
			series.add(power.getEndElec().toString());
			lengds.add(date);
		}
		vo.setSeries(series);
		vo.setLengds(lengds);
		return vo;
	}

}
