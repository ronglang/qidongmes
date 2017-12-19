package com.css.business.web.subsyscraft.craManage.service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.subsyscraft.bean.CraProSeqRelation;
import com.css.business.web.subsyscraft.bean.CraSeqMiddle;
import com.css.business.web.subsyscraft.bean.CraSeqParam;
import com.css.business.web.subsyscraft.craManage.dao.CraProSeqRelationManageDao;
import com.css.business.web.subsyscraft.craManage.dao.CraSeqMiddleManageDAO;
import com.css.business.web.subsyscraft.craManage.dao.CraSeqParamManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("craSeqParamManageService")
public class CraSeqParamManageService extends
		BaseEntityManageImpl<CraSeqParam, CraSeqParamManageDAO> {
	@Resource(name = "craSeqParamManageDAO")
	// @Autowired
	private CraSeqParamManageDAO dao;
	@Autowired
	private CraProSeqRelationManageDao craProSeqRelationManageDao;
	@Autowired
	private CraSeqMiddleManageDAO craSeqMiddleManageDAO;

	@Override
	public CraSeqParamManageDAO getEntityDaoInf() {
		return dao;
	}

	public void findByQueryPage(Page page, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String hql = " from CraSeqParam where 1=1 ";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (map != null && !map.isEmpty()) {
			for (String key : map.keySet()) {
				Object value = map.get(key);
				hql += " and " + key + "= :" + key;
				paramMap.put(key, value);
			}
		}
		hql += " order by id ";
		dao.findByQueryPage(page, hql, paramMap);
	}

	public void delBean(CraSeqParam bean) {
		dao.getHibernateTemplate().delete(bean);
	}

	@Transactional(readOnly = false)
	public void deleteBatch(final Integer[] ids) {
		dao.getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = " delete  from CraSeqParam where id in (:ids) ";
				Query q = session.createQuery(hql);
				q.setParameterList("ids", Arrays.asList(ids));
				int x = q.executeUpdate();
				return x;
			}
		});

	}

	public void saveBean(CraSeqParam bean) {
//		Integer relationId = bean.getRelationId();

//		String cra_code = dao.exeCode("func_get_craft_code");// TODO
																// 正式的要调用数据库函数生成

//		// 1.保存CraProSeqRelation
//		CraProSeqRelation relationBean = craProSeqRelationManageDao
//				.get(relationId);
//		relationBean.setcCode(cra_code);
//		craProSeqRelationManageDao.save(relationBean);
//		;
//		// 2.保存CraSeqMiddle
//		List<CraSeqMiddle> list = craSeqMiddleManageDAO.getHibernateTemplate()
//				.find(" from CraSeqMiddle where relation_id = ? ", relationId);
//		if (list == null || list.size() == 0) {
//			throw new RuntimeException("工艺中间表缺少对应的数据");
//		} else if (list.size() > 1) {
//			throw new RuntimeException("工艺中间表数据异常");
//		} else {
//			CraSeqMiddle b = list.get(0);
//			b.setCra_code(cra_code);
//			b.setCra_name(bean.getCraftName());// 工艺名称
//			craSeqMiddleManageDAO.save(b);
//		}
		// 3.保存CraSeqParam
//		bean.setCraftCode(cra_code);
		dao.save(bean);
	}

	@Transactional(readOnly=false)
	public void updatedata(Integer id, CraProSeqRelation bean,
			String craftCode, String craftName,
			List<CraSeqParam> craSeqParamList) {
		// 批量更新参数
		getEntityDaoInf().saveOrUpdateBatch(craSeqParamList);
	}

}
