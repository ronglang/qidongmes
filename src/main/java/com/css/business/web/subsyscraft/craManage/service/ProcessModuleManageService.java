package com.css.business.web.subsyscraft.craManage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.vo.VOBean;
@Service("processModuleManageService")
public class ProcessModuleManageService {
	

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<VOBean> getVoTreeList(){

		List<VOBean> resultList1 = new ArrayList<VOBean>();
		List<VOBean> resultList2 = new ArrayList<VOBean>();
		List<VOBean> resultList3 = new ArrayList<VOBean>();
		List<Map<String,Object>> list = jdbcTemplate.queryForList(" select * from product_process_view ");
		
		Map<String,List<Map<String,Object>>> map1 = new HashMap<String,List<Map<String,Object>>>();//存储一级产品，产品 key = pro_id
		Map<String,List<Map<String,Object>>> map2 = new HashMap<String,List<Map<String,Object>>>();//存储二级产品，产品工艺 key = pro_id+pro_craft_code
		Map<String,List<Map<String,Object>>> map3 = new HashMap<String,List<Map<String,Object>>>();//存储二级产品，产品工艺 key = pro_id+pro_craft_code+cra_seq_id
		for(Map<String,Object> map : list){
			String pro_id = map.get("pro_id")==null?null:map.get("pro_id").toString();
			String pro_craft_code = map.get("pro_craft_code")==null?null:map.get("pro_craft_code").toString();
			String cra_seq_id = map.get("cra_seq_id")==null?null:map.get("cra_seq_id").toString(); //产品工艺关系表ID
			
			String proType = map.get("pro_type") == null ? null : map.get("pro_type").toString();
			
			//存在产品工艺的成品，才显示在树里。
			if("成品".equals(proType) && pro_craft_code != null){
				if(map1.get(pro_id)==null){
					map1.put(pro_id, new ArrayList<Map<String,Object>>());
				}
				map1.get(pro_id).add(map);
			}
			
			if(pro_craft_code != null){
			}
				String key2 = pro_id+"-"+pro_craft_code;
				if(map2.get(key2)==null){
					map2.put(key2, new ArrayList<Map<String,Object>>());
				}else{
					
				}
				map2.get(key2).add(map);
			
			if(pro_craft_code != null && cra_seq_id != null){
			}
				String key3 = pro_id+pro_craft_code+"-"+cra_seq_id;
				if(map3.get(key3)==null){
					map3.put(key3, new ArrayList<Map<String,Object>>());
				}else{
					
				}
				map3.get(key3).add(map);
		}
		for(String key : map1.keySet()){
			List<Map<String,Object>> tempList = map1.get(key);
			VOBean bean = new VOBean();
			bean.setId(key);
			bean.setLevel("1");
			bean.setParentId("0");
			bean.setText(tempList.get(0).get("pro_name")==null?"产品名称为空":tempList.get(0).get("pro_name").toString());
			resultList1.add(bean);
		}
		for(String key:map2.keySet()){// key = pro_id + pro_craft_code
			//map2的每一条记录都是二级记录
			Map<String,Object> tempMap = map2.get(key).get(0);
			String pro_craft_code = tempMap.get("pro_craft_code")==null?null:tempMap.get("pro_craft_code").toString();
			String pro_id = tempMap.get("pro_id")==null?null:tempMap.get("pro_id").toString();
			String pro_craft_name = tempMap.get("pro_craft_name")==null?null:tempMap.get("pro_craft_name").toString();
			String cra_seq_id = tempMap.get("cra_seq_id")==null?null:tempMap.get("cra_seq_id").toString(); //产品工艺关系表ID
			
			VOBean bean = new VOBean();
			//bean.setId(pro_craft_code);
			bean.setId(cra_seq_id);
			bean.setLevel("2");
			bean.setParentId(pro_id);
			bean.setText(pro_craft_name+"-"+pro_craft_code);
			resultList2.add(bean);
		}
		for(String key : map3.keySet()){
			Map<String,Object> tempMap = map3.get(key).get(0);
			String crq_seq_id = tempMap.get("cra_seq_id")==null?null:tempMap.get("cra_seq_id").toString();
//			String cra_code = tempMap.get("cra_code")==null?null:tempMap.get("cra_code").toString();
			String pro_craft_code = tempMap.get("pro_craft_code")==null?null:tempMap.get("pro_craft_code").toString();
			String seq_code = tempMap.get("seq_code")==null?null:tempMap.get("seq_code").toString();
			
			VOBean bean = new VOBean();
			bean.setId(crq_seq_id);
			bean.setLevel("3");
			bean.setParentId(pro_craft_code);
			bean.setText(seq_code);
			resultList3.add(bean);
		}
		for(VOBean vo1 : resultList1){
			String id1 = vo1.getId();
			if(id1==null){
				continue;
			}
			for(VOBean vo2 : resultList2){
				String id2 = vo2.getId();
				if(id2==null){
					continue;
				}
				String parentId1 = vo2.getParentId();
				if(id1.equals(parentId1)){
					vo1.getChildren().add(vo2);
				}
				
			}
		}
		for(VOBean vo2 : resultList2){
			String id2 = vo2.getId();
			if(id2==null){
				continue;
			}
			for(VOBean vo3 : resultList3){
				String parentId2 = vo3.getParentId();
				String id3 = vo3.getId();
				if(id3==null){
					continue;
				}
				if(id2.equals(parentId2)){
					vo2.getChildren().add(vo3);
				}
			}
		}
		
		
//		for(VOBean vo3 : resultList3){
//			String parent2 = vo3.getParentId();
//			if(id2.equals(parent2)){
//				vo2.getChildren().add(vo3);
//			}
//		}
		return resultList1;
	}
}
