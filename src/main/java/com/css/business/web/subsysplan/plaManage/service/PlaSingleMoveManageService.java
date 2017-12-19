package com.css.business.web.subsysplan.plaManage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaCourse;
import com.css.business.web.subsysplan.bean.PlaSingleMove;
import com.css.business.web.subsysplan.plaManage.bean.plaSingleMoveVo;
import com.css.business.web.subsysplan.plaManage.dao.PlaSingleMoveManageDAO;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaSingleMoveManageService")
public class PlaSingleMoveManageService extends
		BaseEntityManageImpl<PlaSingleMove, PlaSingleMoveManageDAO> {
	@Resource(name = "plaSingleMoveManageDAO")
	// @Autowired
	private PlaSingleMoveManageDAO dao;

	@Override
	public PlaSingleMoveManageDAO getEntityDaoInf() {
		return dao;
	}

	/*@SuppressWarnings("unchecked")
	public Page getplaSingleMove(Page p, String wsCode) throws Exception {
		String sql = "SELECT P.id id, P .ws_code wscode,P .head_ggxh proggxh,P .head_zzdc partlen,P.move_code movecode,"
				+ " P .color color,P .bat_code batcode,P.manu_notice_id manuid,COUNT(*)||'' as acount FROM	pla_course P "
				+ " LEFT JOIN pla_product_order_axis A ON A .product_order_code = P .product_order_code "
				+ " WHERE	A .is_finsh = '是' ";
		if (wsCode != null && !"".equals(wsCode)) {
			sql += " AND P .ws_code = '" + wsCode + "' ";
		}
		sql += " GROUP BY	P.id, P .ws_code,	P .head_ggxh,P .color,P .bat_code,P .head_zzdc, P.move_code";
		Page page = dao.pageSQLQueryVONoneDesc(sql, p.getPageNo(),
				p.getPagesize(), new plaSingleMoveVo());
		List<plaSingleMoveVo> data = page.getData();
		List<Object> list = new ArrayList<Object>();
		for (plaSingleMoveVo vo : data) {
			if (vo.getMovecode() != null && !"".equals(vo.getMovecode())) {
				PlaSingleMove moveData = this.getPlaSingleMoveData(vo
						.getMovecode());
				if (moveData != null) {
					vo.setAcount(Integer.parseInt(vo.getAcount())
							- moveData.getMoveAccount() + "");
				}
			}
			list.add(vo);
		}
		page.setData(list);
		return page;
	}

	@SuppressWarnings("unchecked")
	public PlaSingleMove getPlaSingleMoveData(String moveCode) {
		String hql = " from PlaSingleMove where moveCode = ? ";
		List<PlaSingleMove> list = dao.createQuery(hql, moveCode).list();
		if (list == null || list.size() < 1) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map[] getWsCode() {
		String hql = " from PlaCourse ";
		List<PlaCourse> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					map.put("text", list.get(i).getWsCode());
				} else {
					map.put("id", i + "");
				}
			}
			str[i] = map;
		}
		return str;
	}

	@SuppressWarnings({ "unchecked" })
	public SellContractPlanBatch getSellContractPlanBatchById(Integer id) {
		String hql = " from SellContractPlanBatch where id = ? ";
		List<SellContractPlanBatch> list = dao.createQuery(hql, id).list();
		if (list == null || list.size() < 1) {
			return null;
		} else {
			SellContractPlanBatch sellContractPlanBatch = list.get(0);
			return sellContractPlanBatch;
		}
	}

	*//**
	 * 进行挪单与补单(补单在条件满足是补单，不满足时则不进行补单)
	 * 
	 * @param voLi
	 *            目标挪单数据及被挪单数据
	 * @param id
	 *            SellContractPlanBatch的id
	 * @return
	 * @throws Exception
	 *//*
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> singleMove(SysUser user,
			List<plaSingleMoveVo> voLi, Integer id, List<plaSingleMoveVo> voLi2)
			throws Exception {
		// 1.挪单操作
		SellContractPlanBatch sellContractPlanBatchById = this
				.getSellContractPlanBatchById(id);
		for (plaSingleMoveVo vo : voLi) {
			String hql = " from PlaSingleMove where beMovedWsCode = ? ";
			List<PlaSingleMove> list = dao.createQuery(hql, vo.getWscode())
					.list();
			PlaSingleMove move = new PlaSingleMove();
			move.setBeMovedBatchCode(vo.getBatcode());
			move.setMovedBatchCode(sellContractPlanBatchById.getBatCode());
			move.setBeMovedWsCode(vo.getWscode());
			move.setPartLen(vo.getPartlen());
			move.setProColor(vo.getColor());
			move.setProGgxh(vo.getProggxh());
			move.setCreateBy(user.getAccount());
			move.setCreateDate(new Date());
			if (list.size() < 1 || list == null) {
				move.setMoveAccount(vo.getAmount());
				move.setMoveCode(dao.exeFunction("fun_get_single_move_code")
						.toString());
				dao.save(move);
				PlaCourse course = new PlaCourse();
				course.setId(vo.getId());
				course.setMoveCode(move.getMoveCode());
				dao.updateByCon(course, false);
			} else {
				move.setId(list.get(0).getId());
				move.setMoveAccount(list.get(0).getMoveAccount()
						+ vo.getAmount());
				dao.updateByCon(move, false);
			}

		}
		if (voLi2 == null) {
			return JsonWrapper.successWrapper(null, "成功挪单(没有补单)!");
		} else {
			// TODO 进行补单操作
			for (plaSingleMoveVo plaVo : voLi2) {
				PlaCourse plaCourse = this.getPlaCourseById(plaVo.getId());
				PlaCourse course = new PlaCourse();
				course.setHeadZzds(plaVo.getAmount().toString());
				course.setWsType(PlaCourse.wsType_BEMOVE);
				course.setWsCode(dao.exeFunction("fun_get_course_code")
						.toString());
				course.setDemandDate(plaVo.getDamandDate());
				course.setBatCode(plaVo.getBatcode());
				course.setHeadGgxh(plaVo.getProggxh());
				course.setColor(plaVo.getColor());
				course.setHeadZzdc(plaVo.getPartlen());
				course.setTotalAmount(plaCourse.getTotalAmount());// 计算总长度
				course.setManuNoticeId(plaCourse.getManuNoticeId());
				course.setUseFlag(plaCourse.getUseFlag());
				course.setRouteCode(plaCourse.getRouteCode());
				course.setProCraftCode(plaCourse.getProCraftCode());
				course.setProductOrderCode(plaCourse.getProductOrderCode());
				course.setCreateDate(new Date());
				course.setCreateBy(user.getAccount());
				dao.save(course);
			}
			return JsonWrapper.successWrapper(null, "成功挪单(补单成功)!");
		}

	}

	*//**
	 * 通过ID 查询工单
	 * 
	 * @param id
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public PlaCourse getPlaCourseById(Integer id) {
		String hql = " from PlaCourse where id  =? ";
		List<PlaCourse> list = dao.createQuery(hql, id).list();
		if (list == null || list.size() < 1) {
			return null;
		} else {
			return list.get(0);
		}

	}*/

}
