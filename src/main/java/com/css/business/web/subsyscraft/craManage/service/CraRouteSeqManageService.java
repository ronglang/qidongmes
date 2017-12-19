package com.css.business.web.subsyscraft.craManage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.bean.CraRouteSeq;
import com.css.business.web.subsyscraft.bean.CraSeq;
import com.css.business.web.subsyscraft.craManage.dao.CraRouteSeqManageDAO;
import com.css.business.web.subsyscraft.craManage.dao.CraSeqManageDAO;
import com.css.business.web.subsyscraft.vo.VOBean;
import com.css.business.web.subsysmanu.bean.MauMachine;
import com.css.business.web.subsysmanu.bean.MauMachineSpeed;
import com.css.business.web.subsysmanu.mauManage.dao.MauMachineManageDAO;
import com.css.business.web.subsysmanu.mauManage.dao.MauMachineSpeedManageDAO;
import com.css.business.web.subsysplan.bean.PlaCourse;
import com.css.business.web.subsysplan.bean.PlaProductOrder;
import com.css.business.web.subsysplan.plaManage.dao.PlaCourseManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaProductOrderManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("craRouteSeqManageService")
public class CraRouteSeqManageService extends BaseEntityManageImpl<CraRouteSeq, CraRouteSeqManageDAO> {
	@Resource(name="craRouteSeqManageDAO")
	//@Autowired
	private CraRouteSeqManageDAO dao;
	
	@Resource(name="plaCourseManageDAO")
	private PlaCourseManageDAO plaCourseManageDAO;
	@Resource(name="craRouteSeqManageDAO")
	private CraRouteSeqManageDAO craRouteSeqManageDAO;
	@Resource(name="craSeqManageDAO")
	private CraSeqManageDAO craSeqManageDAO;
	@Resource(name="mauMachineSpeedManageDAO")
	private MauMachineSpeedManageDAO mauMachineSpeedManageDAO;
	@Resource(name="plaProductOrderManageDAO")
	private PlaProductOrderManageDAO plaProductOrderManageDAO;
	@Resource(name="mauMachineManageDAO")
	private MauMachineManageDAO mauMachineManageDAO;
	
	@Override
	public CraRouteSeqManageDAO getEntityDaoInf() {
		return dao;
	}
	
	/**
	 * @TODO 一个工单的工序树
	 * @param courseCode  工单ID
	 * @return
	 * @throws Exception 
	 */
	public List<VOBean> getVoTreeList(String ids) throws Exception{
		
		
		//PlaCourse pc = plaCourseManageDAO.getByCourseCode(courseCode);
		PlaCourse pc = plaCourseManageDAO.get(Integer.parseInt(ids));
		String routeCode = pc.getRouteCode();
		if(routeCode == null || routeCode.length() == 0)
			throw new Exception("数据错误，工艺路线不能为空");
		
		PlaProductOrder ppo = plaProductOrderManageDAO.getPlaProductOrderByCode(pc.getWsCode());
		if( ppo == null) throw new Exception("数据错误，工单("+pc.getWsCode()+")对应的生产令不存在");
		
		List<VOBean> vlst = new ArrayList<VOBean>();
		String hql = "from CraRouteSeq where routeCode=? order by sort ";
		List<CraRouteSeq> lst = craRouteSeqManageDAO.listQuery(hql, routeCode);
		if(lst == null || lst.size() == 0)
			throw new Exception("工单的工艺路线("+routeCode+")设置错误，不存在工序");
		
		for(CraRouteSeq srs : lst){
			CraSeq cs = craSeqManageDAO.getEntityByCode(srs.getSeqCode());
			VOBean bean = new VOBean();
			bean.setId(srs.getSeqCode());
			bean.setLevel("1");
			bean.setParentId("0");
			bean.setText(cs.getSeqName());
			
			vlst.add(bean);
			
			String sql = "from MauMachineSpeed m where ";
			sql += "exists(SELECT 1 from MauMachine a where a.id=m.machineId and m.proGgxh=? and a.seqCode=?)";
			
			//本工序对应的，支持生产本产品的机台列表
			List<MauMachineSpeed> mlst = mauMachineSpeedManageDAO.listQuery(sql, ppo.getProGgxh(),srs.getSeqCode());
			if(mlst == null || mlst.size() == 0)
				throw new Exception("本产品在工序("+srs.getSeqCode()+")不存在支持的机台。请联系管理员设置机台生产参数");
			
			List<VOBean> blst = new ArrayList<VOBean>();
			for(MauMachineSpeed mms : mlst){
				MauMachine mm = mauMachineManageDAO.get(mms.getMachineId());
				VOBean b = new VOBean();
				b.setId(mms.getMachineId().toString());
				b.setLevel("2");
				b.setParentId(bean.getId());
				b.setText(mm.getMacCode());
				blst.add(b);
			}
			bean.setChildren(blst);
		}
		return vlst;
	}
	
}
