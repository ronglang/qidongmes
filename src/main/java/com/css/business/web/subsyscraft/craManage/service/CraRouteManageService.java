package com.css.business.web.subsyscraft.craManage.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.json.ParseException;
import com.css.business.web.subsyscraft.bean.CraBdBomParam;
import com.css.business.web.subsyscraft.bean.CraColorBom;
import com.css.business.web.subsyscraft.bean.CraDjBomParam;
import com.css.business.web.subsyscraft.bean.CraFrBomParam;
import com.css.business.web.subsyscraft.bean.CraHtBomParam;
import com.css.business.web.subsyscraft.bean.CraJjBomParam;
import com.css.business.web.subsyscraft.bean.CraJtxBomParam;
import com.css.business.web.subsyscraft.bean.CraJxBomParam;
import com.css.business.web.subsyscraft.bean.CraJyBomParam;
import com.css.business.web.subsyscraft.bean.CraLsBomParam;
import com.css.business.web.subsyscraft.bean.CraRoute;
import com.css.business.web.subsyscraft.bean.CraRouteSeq;
import com.css.business.web.subsyscraft.bean.CraYzBomParam;
import com.css.business.web.subsyscraft.craManage.dao.CraRouteManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.google.gson.Gson;

@Service("craRouteManageService")
public class CraRouteManageService extends BaseEntityManageImpl<CraRoute, CraRouteManageDAO> {
	@Resource(name="craRouteManageDAO")
	//@Autowired
	private CraRouteManageDAO dao;
	
	@Resource(name="craColorBomManageService")
	private CraColorBomManageService service;
	
	
	@Override
	public CraRouteManageDAO getEntityDaoInf() {
		return dao;
	}

	private Gson gson = new Gson();
	/**
	 * method：查询CraSeq表
	 * @return 返回一个map对象
	 * @author:曾斌
	 * @date:2017-06-6
	*/
	public HashMap<String, Object> getCraRouteManageService(String pageStr,String pagesizeStr) {
		String hql = "from CraRoute order by id";
		HashMap<String,Object> map = getPagingQueryToolService(hql,pageStr,pagesizeStr);
		return map;
	}
	/** 
	 * @method: 根据routeCode 查询CraRoute表中的记录
	 * @author:曾斌
	 * @date:2017-06-06
	 */
	public HashMap<String, Object> getserchByRouteCodeService(String pageStr,String pagesizeStr, String routeCode) {
		String hql = "from CraRoute where routeCode=? order by id";
		 HashMap<String,Object> map1 = getPagingQueryToolService(hql,pageStr,pagesizeStr,routeCode);
		return map1;
	}
	/**
	 * @method:根据routeCode 查询CraRouteSeq表中的记录
	 * @param page
	 * @param pagesize
	 * @param routeCode 工序路线编码
	 * @date:2017-06-06 
	 */
	public HashMap<String, Object> getCraRouteSeqManageService(String pageStr,String pagesizeStr, String routeCode) {
		String hql = "from CraRouteSeq where routeCode=? order by id";
		HashMap<String,Object> map2 = getPagingQueryToolService(hql,pageStr,pagesizeStr,routeCode);
		return map2;
	}
	/**
	 * @method：根据id删除CraRoute表中的一条或者多条记录
	 * @param: ids存放id的数组
	 * @return void
	 * author:曾斌
	 * date:2017-06-06
	*/
	public void deleteCraRouteService(int[] ids) {
		 String hql = "delete CraRoute where id=?";
		 dao.deleteByIdsDao(ids, hql);
	}
	/**
	 * @method：根据routeCodes删除CraRouteSeq表中的一条或者多条记录
	 * @param: routeCodes存放routeCodes的数组
	 * @return void
	 * author:曾斌
	 * date:2017-06-06
	*/
	public void deleteCraRouteSeqManageByRouteCodesService(String[] routeCodes) {
		 String hql = "delete CraRouteSeq where routeCode=?";
		 dao.deleteByRouteCodesDao(routeCodes, hql);
	}
	/**
	  * @method:封装分页查询的一个重要方法
	  * @param hql 需要查询的一个hql语句
	  * @param pageStr 当前第几页，从liguerui获取得到的
	  * @param pagesizeStr 每页记录数，从liguerui获取得到的
	  * @param values 需要的参数，记住hql语句的顺序，参考getPagingQueryToolDao;
	  * @return HashMap<String,Object>
	  * author:曾斌
	  * date:2017-6-6
	  */
	 public HashMap<String,Object> getPagingQueryToolService(String hql,String pageStr,String pagesizeStr,final Object...values){
		 HashMap<String,Object> map=new HashMap<String,Object>();
		 List<?> list;
		 double total;
		 int page=1;
		 int pagesize=10;
		 if(pageStr!=null){
			 page =Integer.parseInt(pageStr);
		  }
		 if(pagesizeStr!=null){
			 pagesize=Integer.parseInt(pagesizeStr);
		 }
		 total = dao.getPagingQueryToolDao(hql, true, page, pagesize, values).size();
		 list = dao.getPagingQueryToolDao(hql, false, page, pagesize, values);
		 map.put("Total", total);
		 map.put("Rows", list);
		 return map;
	 }
	 /**
	  * @author:曾斌
	  * @method:跟新 CraRoute表中的一条记录
	  * @date:217-06-07
	  */
	public void updateCraRouteService(CraRoute craRoute) {
		dao.updateCraRouteDao(craRoute);
	}
	 /** 
	 * @throws ParseException 
	 * @method:更新CraRouteSe表中的多条记录
	 * @author:曾斌
	 * @date:2017-06-07
	 */
	public void updateCraRouteSeqService(List<CraRouteSeq> craRouteSeq) {
		 for (int i = 0; i < craRouteSeq.size(); i++) {
			 dao.updateCraRouteSeqDao(craRouteSeq.get(i));
		}
	}
	 /**
	  * @author:曾斌
	  * @method:新增CraRoute表中的一条记录
	  * @date:217-06-08
	  */

	public void addCraRouteService(CraRoute craRoute) {
		 dao.addCraRouteDao(craRoute);
	}
	 /**
	  * @author:曾斌
	  * @method:新增CraRouteSeq表中的一条记录
	  * @date:217-06-08
	  */
	public void addCraRouteSeqService(List<CraRouteSeq> craRouteSeq) {
		for (int i = 0; i < craRouteSeq.size(); i++) {
			 dao.addCraRouteSeqDao(craRouteSeq.get(i));
		}		
	}

	
	public Page getPageList(Page page,String seqName, String proGgxh) throws UnsupportedEncodingException {
		String hql = "from CraRoute where 1 = 1 "; 
		StringBuilder sb = new StringBuilder(hql);
		if (proGgxh != null  && !"".equals(proGgxh) ) {
			String decode = URLDecoder.decode(proGgxh,"UTF-8");
			sb.append(" and proGgxh like '%"+decode+"%' ");
		}
		if(seqName !=null && !"".equals(seqName)){
			sb.append(" and seqName = '"+seqName+"' ");
		}
		sb.append(" order by proGgxh,seqStep  ");
		Page pageQuery = page;
		try {
			pageQuery = dao.pageQuery(sb.toString(), page.getPageNo(), page.getPagesize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageQuery;
	}

	@SuppressWarnings("unchecked")
	public CraRoute getCraRouteById(Integer id){
		String hql = " from CraRoute where id = ? ";
		List<CraRoute> list = dao.createQuery(hql, id).list();
		if(list.size()==0 || list==null){
			return null;
		}
		return list.get(0);
	}
	
	public void delCraRoute(String proGgxh,String seqName){
		if("拉丝".equals(seqName)){
			CraLsBomParam ls = new CraLsBomParam();
			ls.setSeqCode("ls");
			ls.setProGgxh(proGgxh);
			dao.remove(ls);
		}else if("绞线".equals(seqName)){
			CraJxBomParam jx = new CraJxBomParam();
			jx.setSeqCode("jx");
			jx.setProGgxh(proGgxh);
			dao.remove(jx);
		}else if("绝缘".equals(seqName)){
			CraJyBomParam jy = new CraJyBomParam();
			jy.setSeqCode("jy");
			jy.setProGgxh(proGgxh);
			List<CraColorBom> list = service.getCraColorBomList(proGgxh, "jy");
			for (CraColorBom craColorBom : list) {
				dao.remove(craColorBom);
			}
			dao.remove(jy);
		}else if("包带".equals(seqName)){
			CraBdBomParam bd = new CraBdBomParam();
			bd.setSeqCode("bd");
			bd.setProGgxh(proGgxh);
			dao.remove(bd);
		}else if("护套".equals(seqName)){
			CraHtBomParam ht = new CraHtBomParam();
			ht.setSeqCode("ht");
			ht.setProGgxh(proGgxh);
			dao.remove(ht);
		}else if("复绕".equals(seqName)){
			CraFrBomParam fr = new CraFrBomParam();
			fr.setSeqCode("fr");
			List<CraColorBom> list = service.getCraColorBomList(proGgxh, "fr");
			for (CraColorBom craColorBom : list) {
				dao.remove(craColorBom);
			}
			fr.setProGgxh(proGgxh);
			dao.remove(fr);
		}else if("绞铁线".equals(seqName)){
			CraJtxBomParam jtx = new CraJtxBomParam();
			jtx.setSeqCode("jtx");
			jtx.setProGgxh(proGgxh);
			dao.remove(jtx);
		}else if("集绞".equals(seqName)){
			CraJjBomParam jj = new CraJjBomParam();
			jj.setSeqCode("jj");
			jj.setProGgxh(proGgxh);
			dao.remove(jj);
		}else if("对绞".equals(seqName)){
			CraDjBomParam dj = new CraDjBomParam();
			dj.setSeqCode("dj");
			dj.setProGgxh(proGgxh);
			dao.remove(dj);
		}else if("印字".equals(seqName)){
			CraYzBomParam yz = new CraYzBomParam();
			yz.setSeqCode("yz");
			yz.setProGgxh(proGgxh);
			dao.remove(yz);
		}
		
	}
	
	
}
