package com.css.business.web.subsyscraft.craManage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.subsyscraft.bean.CraSeq;
import com.css.business.web.subsyscraft.craManage.dao.CraSeqManageDAO;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.common.web.syscommon.bean.TreeVO;
import com.css.common.web.syscommon.controller.support.QueryCondition;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("craSeqManageService")
public class CraSeqManageService extends BaseEntityManageImpl<CraSeq, CraSeqManageDAO> {
	@Resource(name="craSeqManageDAO")
	//@Autowired
	private CraSeqManageDAO dao;
	
	
	@Override
	public CraSeqManageDAO getEntityDaoInf() {
		return dao;
	}

	/**
	 * method：查询CraSeq表
	 * @return 返回一个map对象
	 * author:曾斌
	 * date:2017-06-6
	*/
	public HashMap<String, Object> getCraSeqManageService(String pageStr,String pagesizeStr) {
		String hql = "from CraSeq order by id";
		 HashMap<String,Object> map = getPagingQueryToolService(hql,pageStr,pagesizeStr);
		return map;
	}	
	
	
	
	/**
	 * @method：根据id删除CraSeq表中的一条或者多条记录
	 * @param: ids存放id的数组
	 * @return void
	 * author:曾斌
	 * date:2017-06-06
	*/
	public void deleteCraSeqService(int[] ids) {
		 String hql = "delete CraSeq where id=?";
		 dao.deleteByIdsDao(ids, hql);
	}
	 
	 /**
	  *@method:跟新 CraSeq表中的一条记录
	  *@param:Object表所对应的一个实例对象
	  *@date:217-06-06
	  */
	public void updateCraSeqService(CraSeq craSeq) {
		dao.updateCraSeqDao(craSeq);
	}
	/** 
	 * @method: 新增CraSeq表中的一条记录
	 * author:曾斌
	 * time:2017-06-06
	 */
	public void addCraSeqService(CraSeq craSeq) {
		 dao.addCraSeqDao(craSeq);
	}
	/** 
	 * @method: 根据seqCode 查询CraSeq表中的记录
	 * author:曾斌
	 * time:2017-06-06
	 */
	public HashMap<String, Object> getserchBySeqCodeService(String pageStr,String pagesizeStr, String seqCode) {
		String hql = "from CraSeq where seqCode=? order by id ";
		 HashMap<String,Object> map1 = getPagingQueryToolService(hql,pageStr,pagesizeStr,seqCode);
		return map1;
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
		 * @TODO: 产品树
		 * 产品编码作value, 
		 * @author: zhaichunlei
		 & @DATE : 2017年7月31日
		 * @param user
		 * @param code
		 * @param dvalue
		 * @return
		 * @throws Exception
		 */
		@Transactional(readOnly = true)
		public List<TreeVO> nodeTree(SysUser user, String code, Object dvalue) throws Exception {
			QueryCondition qc = new QueryCondition(CraSeq.class);
			if (!(code.isEmpty())) {
				qc.addCondition(code, "like", "%"+dvalue + "%");
				qc.setOrderBy("seqCode");
			}
			List<CraSeq> list = this.query(qc);
			List<TreeVO> tree = new ArrayList<TreeVO>();
			for (CraSeq s : list) {
				TreeVO vo = new TreeVO(s.getSeqCode(), s.getSeqName(),
						//无父级
						"-1");
				tree.add(vo);
			}
			return tree;
		}
		
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Map[] getSeqCode(){
			String hql = " from CraSeq ";
			List<CraSeq> list = dao.createQuery(hql).list();
			Map[] str = new Map[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Map<String,String> map = new HashMap<String,String>(); 
				for(int j = 0;j<2;j++){
					if(j==0){
						map.put("text", list.get(i).getSeqName());
					}else{
						map.put("id", i+"");
					}
				}
				str[i] = map;
			}
			return str;
		}
		
		/**
		 * 查询工序列表
		 * @return
		 */
		public List<CraSeq> getAllCraSeq(){
			String hql = " from CraSeq ";
			@SuppressWarnings("unchecked")
			List<CraSeq> list = dao.createQuery(hql).list();
			return list;
		}
		
		
		
		
		
		
}
