package com.css.common.web.workflow.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import com.css.business.web.sysManage.bean.SysUser;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.css.common.web.workflow.bean.BsSpcySq;
import com.css.common.web.workflow.bean.WfProcess;
import com.css.common.web.workflow.bean.WfYewuProcess;
import com.css.common.web.workflow.dao.WfYewuProcessManageDAO;

@Service("wfYewuProcessManageService")
public class WfYewuProcessManageService extends BaseEntityManageImpl<WfYewuProcess, WfYewuProcessManageDAO> {
	@Resource(name="wfYewuProcessManageDAO")
	//@Autowired
	private WfYewuProcessManageDAO dao;
	
	
	@Override
	public WfYewuProcessManageDAO getEntityDaoInf() {
		return dao;
	}

	 /**
	  * 检查yewu_pyzm是否存在
	  * @param ps_dm
	  * @return
	  */
	public boolean checkYewuProcessPyzmIsExist(String yewu_pyzm){
		boolean existFlag=false;
		String ls_sql="select count(yp_id) as totalCount from wf_yewu_process where yewu_pyzm='"+yewu_pyzm+"'";
//		System.out.println(ls_sql);
		Object o = dao.createSQLQuery(ls_sql).uniqueResult();
		if(o != null && Integer.parseInt(o.toString()) > 0)
			existFlag = true;
		/*BaseDao dao=new BaseDao();
		if(dao.getRecordCountBySql(ls_sql)>0){
			existFlag=true;
		}*/
		return existFlag;
	}
	

    /**
     * @TODO:通过表名,字段名,申请编号 查询字段状态标志(上报,审核,受理)
     * @author: zhaichunlei
     & @DATE : 2017年4月18日
     * @param user
     * @param tablename
     * @param caozuo
     * @param sqbh
     * @param hdm
     * @return
     * @throws Exception
     */
	public String getbz(SysUser user,String tablename,String caozuo,String sqbh,String hdm ) throws  Exception{
		String sbbz = "";
		String shjg = "";
		String slbz = "";
		String shrid = "";
		String str = "0";
		String bbh = "";
		String sql = "";
		String v_sqColName="sq_sqbh";
		if ("BS_SPSC_BZ".equals(tablename)){
			v_sqColName="bz_sqbh";
		}
		
		sql = "select sq_sbbz,log_shjg,sq_slbz,sq_hdm,log_shrid  from "+tablename+" where "+v_sqColName+"='"+sqbh+"'";
		@SuppressWarnings("unchecked")
		List<BsSpcySq> lst = dao.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(BsSpcySq.class)).list();
		BsSpcySq bean = null;
		if(lst != null && lst.size() > 0){
			bean = lst.get(0);
		}
		 //BsSpcySq bean = (BsSpcySq) dao.findById(sql, BsSpcySq.class); 
		 if(bean != null){
			 sbbz = bean.getSqSbbz();
			 shjg = bean.getLogShjg();
			 slbz = bean.getSqSlbz();
			 shrid = Long.toString(bean.getLogShrid());
			 bbh = null == bean.getSqHdm() ? "" :bean.getSqHdm();
		 }
		 System.out.println("hdm:"+hdm);
		 System.out.println("bbh:"+bbh);
		if (!hdm.equals("")&&hdm!=null&&!bbh.equals("")&&!hdm.equals(bbh)){
			str = "核对码变了，请刷新后重试!";
			return str;
		} 
		if(caozuo.equals("edit")){
			if(sbbz.equals("1")){
			  str = "已上报不能修改!";	
			}else if(sbbz.equals(""))
			  str = "已删除不能修改!";
		}else
		if(caozuo.equals("shanchu")){
			if(sbbz.equals("1")){
			  str = "已上报不能删除!";
			}else if(sbbz.equals("2")){
			  str = "审核未通过不能删除!";
			}else if(sbbz.equals(""))
			 str = "已删除不能删除!";
		}else
		if(caozuo.equals("shangbao")){
			if(sbbz.equals("1")){
			  str = "已上报不能上报!";
			}else if(sbbz.equals(""))
			  str = "已删除不能上报!";
		}else
		if(caozuo.equals("chehui")){
			if(shjg.equals("1")){
			  str = "已审核不能撤回!";	
			}else if(sbbz.equals("0") || sbbz.equals("2")){
			  str = "未上报不能撤回!";	
			}else if(sbbz.equals(""))
			  str = "已删除不能撤回!";
		}else
		if(caozuo.equals("shenhe")){
			if(sbbz.equals("0")  || sbbz.equals("2")){
			  str = "未上报不能审核!";	
			}else if(shjg.equals("1")){
			  str = "已审核不能审核!";
			}else if(shjg.equals("2") && (!shrid.equals(user.getId()))){
				  str = "已由他人审核,不得更换审核人!";
			}else if(sbbz.equals(""))
			  str = "已删除不能审核!";
		}else
		if(caozuo.equals("shouli")){
			if(slbz.equals("1")){
			 str = "已受理不能受理!";
			}
		}
		return str;
	}
	

	
	/**
	 * 获取所有已经定义的工作流，方便前台对其节点进行授权
	 * @return
	 */
	 @SuppressWarnings("unchecked")
	public List<WfProcess> getAllProcessTreeData(){
    	//List dataList=null;
		//String ls_sql="select ps_id,ps_mc,ps_dm from wf_process where ps_sfzf='N' order by ps_id";
		List<WfProcess> lst = dao.createQuery("select * from WfProcess where psSfzf='N' order by id").list();
		/*dataList=new BaseDao().executeQuery(ls_sql, WfProcess.class);
		return dataList;*/
		return lst;
	 }	
}
