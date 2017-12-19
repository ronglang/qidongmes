package com.css.business.web.subsysquality.quaManage.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.plaManage.utils.CalculaMaterUtil;
import com.css.business.web.subsysplan.vo.PlaDisPatchVo;
import com.css.business.web.subsysquality.bean.QualityUndoDetail;
import com.css.business.web.subsysquality.quaManage.dao.QualityUndoDetailDAO;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

/**
 * 质量回溯详细信息服务层
 * @author Administrator
 *
 */

@Service("qualityUndoDetailService")
public class QualityUndoDetailService extends
BaseEntityManageImpl<QualityUndoDetail, QualityUndoDetailDAO> {

	@Resource(name = "qualityUndoDetailDAO")
	private QualityUndoDetailDAO dao;
	
	@Override
	public QualityUndoDetailDAO getEntityDaoInf() {	
		return dao;
	}

 
	public Page queryPage(Page p,
			String mac_code,String workcode,String seqstep) {
		try{
		StringBuffer sql= new StringBuffer(" from QualityUndoDetail r  where 1=1  ");

		if(null!=mac_code&&!"".equals(mac_code)){
			sql.append("  and r.maccode='"+mac_code+"'");
		}
		if(null!=workcode&&!"".equals(workcode)){
			sql.append("  and r.workcode='"+workcode+"'");
		}
		if(null!=seqstep&&!"".equals(seqstep)){
			sql.append("  and r.step="+seqstep);
		}
		Page page = dao.pageQuery(sql.toString(), p.getPageNo(), p.getPagesize());
		return page;
		}catch(Exception e){
			e.getMessage();
			return null;
		}
	} 
	 

	


	
	public  HashMap<String, Object> getMac(String wscode){

		List<Map<String, Object>> maps = new  ArrayList<Map<String,Object>>();
	    int lsCount = 0;
	    int jjsetp = 0;
	    int secondls = 0;
		ArrayList<String> macs = new ArrayList<String>();
		ArrayList<String> seqs = new ArrayList<String>();
		String sql = "select maccode||'('||step||')' as maccode,seqcode from pla_mac_task where workcode='"+wscode+"' order by step";
		List<Object> list = dao.createSQLQuery(sql).list(); // 
		for(int i = 0; i<list.size();i++){
			Object[] obj = (Object[])list.get(i);
			macs.add((String)obj[0]);
			seqs.add((String)obj[1]);
			if(((String)obj[1]).equalsIgnoreCase("ls")){
				lsCount = lsCount+1;
				if(lsCount == 2){
					secondls = i;
				}
			}
			if(((String)obj[1]).equalsIgnoreCase("jj")){
				jjsetp = i;
			}
		}
		int j = 0;
		if(lsCount==1){
			for(int i = 0 ;i<macs.size(); i++){
				HashMap<String,Object> map = new HashMap<String,Object>();
		        map.put("name", macs.get(i));
				map.put("x", String.valueOf((i+1)*100));
				map.put("y",String.valueOf(300));
				maps.add(map);
			}   	
		}
		else{
			
			for(int i = 0 ;i<macs.size(); i++){
				HashMap<String,Object> map = new HashMap<String,Object>();
				if(i==0){
					map.put("name", macs.get(i));
					map.put("x", String.valueOf((i+1)*100));
					map.put("y",String.valueOf(200));
					maps.add(map);
				}
				else{
					if(i>0&&i<jjsetp){
						if(i<secondls){
							map.put("name", macs.get(i));
							map.put("x", String.valueOf((i+1)*100));
							map.put("y",String.valueOf(200));
							maps.add(map);	
						}
						else{
							map.put("name", macs.get(i));
							map.put("x", String.valueOf((j+1)*100));
							map.put("y",String.valueOf(400));
							maps.add(map);
							j=j+1;
						}
					}
					if(i>=jjsetp){
						map.put("name", macs.get(i));
						map.put("x", String.valueOf((j+1)*100));
						map.put("y",String.valueOf(300));
						maps.add(map);
						j=j+1;
					 }
					}
					
				}
		       
			}   	
	
		return JsonWrapper.successWrapper(maps);
	    
	   
	}
	
	public  HashMap<String, Object> getLinks(String wscode){
		
		List<Map<String, Object>> maps = new  ArrayList<Map<String,Object>>();
		ArrayList<String> macs = new ArrayList<String>();
		ArrayList<String> seqs = new ArrayList<String>();
		String sql = "select maccode||'('||step||')' as maccode,seqcode from pla_mac_task where workcode='"+wscode+"' order by step";
		List<Object> list = dao.createSQLQuery(sql).list(); 
		int lsCount = 0;
		int jjsetp = 0;
		  int secondls = 0;
		for(int i = 0 ;i<list.size(); i++){
			Object[] obj = (Object[])list.get(i);
			macs.add((String)obj[0]);
			seqs.add((String)obj[1]);
			if(((String)obj[1]).equalsIgnoreCase("jj")){
				jjsetp = i;
			}
			if(((String)obj[1]).equalsIgnoreCase("ls")){
				lsCount = lsCount+1;
				if(lsCount == 2){
					secondls = i;
				}
			}
		}
		if(lsCount==1){
			for(int i = 0 ;i<macs.size()-1; i++){
				HashMap<String,Object> map = new HashMap<String,Object>();
				map.put("source", macs.get(i));
				map.put("target", macs.get(i+1));
				maps.add(map);	
				}
		}
		else{
			for(int i = 0 ;i<macs.size()-1; i++){
				HashMap<String,Object> map = new HashMap<String,Object>();
				if(seqs.get(i).equalsIgnoreCase("jy")){
					map.put("source", macs.get(i));
					map.put("target", macs.get(jjsetp));
					maps.add(map);	
				}
				else{
					map.put("source", macs.get(i));
					map.put("target", macs.get(i+1));
					maps.add(map);	
				}
			}
			}
		return JsonWrapper.successWrapper(maps);
	}
	
}
