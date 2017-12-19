package com.css.business.web.sysManage.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.sysManage.bean.SysNotice;
import com.css.business.web.sysManage.dao.SysNoticeManageDAO;
import com.css.commcon.util.YorkUtil;
import com.css.common.util.EhCacheFactory;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
* @Title: SysNoticeManageService.java   
* @Package com.css.business.web.sysManage.service   
* @Description: 主页滚动条   
* @author   rb
* @date 2017年9月7日 下午4:22:04   
* @company  SMTC
 */
@Service("sysNoticeManageService")
public class SysNoticeManageService extends BaseEntityManageImpl<SysNotice, SysNoticeManageDAO> {
	@Resource(name="sysNoticeManageDAO")
	//@Autowired
	private SysNoticeManageDAO dao;
	
	
	@Override
	public SysNoticeManageDAO getEntityDaoInf() {
		return dao;
	}
	
	private EhCacheFactory factory = EhCacheFactory.getInstance();
	
	private Gson gson =  new Gson();

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @return         
	 */ 
	public List<SysNotice> getShowNotices() {
		// TODO Auto-generated method stub
		String hql ="from SysNotice where isShow = '是'";
		List<SysNotice> listQuery = dao.listQuery(hql);
		return listQuery;
	}
	
	/**
	 * 
	 * @Description:    
	 * @param reportCode
	 */
	public void afterCome(String reportCode){
		String  hql = "from SysNotice where key ='"+reportCode+"'";
		List<SysNotice> listQuery = dao.listQuery(hql);
		if (listQuery != null && listQuery.size() > 0) {
			SysNotice sysNotice = listQuery.get(0);
			sysNotice.setIsShow("否");
			try {
				this.updateByCon(sysNotice, false);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param string
	 * @param memcache_车间致辞
	 * @return         
	 */ 
	public List<SysNotice> getNoticesWebSocket(String isShow, String type) {
		// TODO Auto-generated method stub
		String hql = "from SysNotice where isShow='"+isShow+"' and type='"+type+"' order by startTime ";
		@SuppressWarnings("unchecked")
		List<SysNotice>list = dao.createQuery(hql).list();
		return list;
	}

	
	public SysNotice showNoticeDataService(){
		/*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(new Date());
		
		String hql = "from SysNotice where isShow='是' and startTime <='"+dateString+"' and endTime >= '"+dateString+"' order by createBy desc ";
		SysNotice sysNotice = (SysNotice)dao.createQuery(hql).list().get(0);*/
		
		String jsonList = factory.getWebsocketCmdCache(YorkUtil.Memcache_车间致辞);
		List<SysNotice>list = gson.fromJson(jsonList, new TypeToken<List<SysNotice>>(){}.getType());
		if(list != null && list.size() > 0){
			//第一条 的结束时间和当前对比,如果 小于当前时间 ,显示已完成删除
			long start = list.get(0).getStartTime().getTime();
			long end = list.get(0).getEndTime().getTime();
			long now = new Date().getTime();
			if (end >= now && start <= now) {
				//text = gson.toJson(list.get(0));
				return list.get(0);
			}else if(end < now){
				list.remove(0);
				factory.addWebsocketCmdCache(YorkUtil.Memcache_车间致辞, gson.toJson(list));
				return null;
			}
		}
		return null;
		
	}
	

}
