package com.css.business.web.subsyscraft.craManage.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.bean.CraHtBomParam;
import com.css.business.web.subsyscraft.bean.CraRoute;
import com.css.business.web.subsyscraft.craManage.dao.CraHtBomParamManageDAO;
import com.css.business.web.subsyscraft.craManage.dao.CraRouteManageDAO;
import com.css.business.web.subsyscraft.craManage.dao.CraSeqManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("craHtBomParamManageService")
public class CraHtBomParamManageService extends
		BaseEntityManageImpl<CraHtBomParam, CraHtBomParamManageDAO> {
	@Resource(name = "craHtBomParamManageDAO")
	// @Autowired
	private CraHtBomParamManageDAO dao;

	@Override
	public CraHtBomParamManageDAO getEntityDaoInf() {
		return dao;
	}

	/** 工艺路线dao */
	@Resource(name = "craRouteManageDAO")
	private CraRouteManageDAO crDao;
	/** 工序dao */
	@Resource(name = "craSeqManageDAO")
	private CraSeqManageDAO csDao;

	public void saveOrUpdateBean(List<CraHtBomParam> beans, String account) throws Exception {
		for (CraHtBomParam bean : beans) {
			if (bean.getId() == null) {
				// 新建
				CraRoute craRoute = new CraRoute();
				craRoute.setProGgxh(bean.getProGgxh());
				craRoute.setSeqName(csDao.getNameByCode(bean.getSeqCode()));
				craRoute.setSeqStep(bean.getSeqStep());
				craRoute.setCreateDate(new Date());
				craRoute.setCreateBy(account);
				bean.setCreateBy(account);
				bean.setCreateDate(new Date());
				crDao.save(craRoute);
				dao.save(bean);
			} else {
				// 原BOM 参数
				CraHtBomParam aBean = dao.get(bean.getId());
				// 求原工艺路线
				CraRoute craRoute = crDao.getByCodeAndStep(aBean.getProGgxh(),
						csDao.getNameByCode(aBean.getSeqCode()),
						aBean.getSeqStep());
				if (craRoute == null) {
					// 新建
					craRoute = new CraRoute();
					craRoute.setProGgxh(bean.getProGgxh());
					craRoute.setSeqName(csDao.getNameByCode(bean.getSeqCode()));
					craRoute.setSeqStep(bean.getSeqStep());
					craRoute.setCreateDate(new Date());
					craRoute.setCreateBy(account);
					crDao.save(craRoute);
				} else {
					craRoute.setProGgxh(bean.getProGgxh());
					craRoute.setSeqName(csDao.getNameByCode(bean.getSeqCode()));
					craRoute.setSeqStep(bean.getSeqStep());
					crDao.updateByCon(craRoute, false);
				}
				dao.updateByCon(bean, false);
			}
		}

	}

	public CraHtBomParam getByProGgxh(String proGgxh) {
		String hql = "from CraHtBomParam where proGgxh='" + proGgxh+"' ";
		List<CraHtBomParam> listQuery = dao.listQuery(hql);
		if (listQuery != null && listQuery.size() > 0) {
			return listQuery.get(0);
		}
		return null;
	}
	
	public Page getGridByProGgxh(Page p,String proGgxh){
		String hql = " from CraHtBomParam where 1=1  ";
		if(proGgxh != null && !"".equals(proGgxh)){
			hql += " AND proGgxh = '"+proGgxh+"' ";
		}
		hql += " order by seqStep ";
		Page page = dao.pageQuery(hql, p.getPageNo(), p.getPagesize());
		return page;
	}

}
