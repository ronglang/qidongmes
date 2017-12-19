package com.css.business.web.subsysplan.plaManage.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaMember;
import com.css.business.web.subsysplan.plaManage.dao.PlaMemberManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaMemberManageService")
public class PlaMemberManageService extends BaseEntityManageImpl<PlaMember, PlaMemberManageDAO> {
	@Resource(name="plaMemberManageDAO")
	//@Autowired
	private PlaMemberManageDAO dao;
	
	
	@Override
	public PlaMemberManageDAO getEntityDaoInf() {
		return dao;
	}
	
	/**
	 * method：查询PlaMember表
	 * @return 返回一个map对象
	 * author:曾斌
	 * date:2017-05-27
	*/
	 public HashMap<String,Object> getPlaMemberService(String pageStr,String pagesizeStr){
		 String hql = "from PlaMember order by id ";
		 HashMap<String,Object> map = getPagingQueryToolService(hql,pageStr,pagesizeStr);
		 return map;
	 }
	 /**
	 * @method：根据id删除PlaMember表中的一条或者多条记录
	 * @param: ids存放id的数组
	 * @return void
	 * author:曾斌
	 * date:2017-05-27
	*/
	 public void deletePlaMemberByIdsService(int [] ids){
		 String hql = "delete PlaMember where id=?";
		 dao.deleteByIdsDAO(ids, hql);
	 }
	 
	 /**
	  *@method:跟新 PlaMember表中的一条记录
	  *@param:Object表所对应的一个实例对象
	  *@return void
	  * author:曾斌
	  * date:2017-05-27
	  */
	 public void updatePlaMemberService(PlaMember plaMember){
			dao.updatePlaMemberDao(plaMember);
	 }
	 
	 /** 
	 * @method: 新增PlaMember表中的一条记录
	 * author:曾斌
	 * time:2017-05-27
	 */
	 public void addPlaMemberService(PlaMember plaMember) {
		 dao.addPlaMemberDao(plaMember);
	 }
	 /** 
	 * @method: 根据name查询人员表中的一条记录
	 * author:曾斌
	 * time:2017-05-31
	 */
	public HashMap<String,Object> serchPlaMemberByNameService(String pageStr,String pagesizeStr,String name) {
		String hql = "from PlaMember where name =? order by id ";
		HashMap<String,Object> map = getPagingQueryToolService(hql,pageStr,pagesizeStr,name);
		return map;
	}
	 
	 /**
	  * @method:封装分页查询的一个重要方法
	  * @param hql 需要查询的一个hql语句
	  * @param pageStr 当前第几页，从liguerui获取得到的
	  * @param pagesizeStr 每页记录数，从liguerui获取得到的
	  * @param values 需要的参数，记住hql语句的顺序，参考getPagingQueryToolDao;
	  * @return HashMap<String,Object>
	  * author:曾斌
	  * date:2017-05-27
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
		 total = dao.getPagingQueryToolDao(hql, true, page, pagesize,values).size();
		 list = dao.getPagingQueryToolDao(hql, false, page, pagesize,values);
		 map.put("Total", total);
		 map.put("Rows", list);
		 return map;
	 }	
	
}
