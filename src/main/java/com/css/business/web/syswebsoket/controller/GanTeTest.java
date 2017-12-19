package com.css.business.web.syswebsoket.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/ganTeTest")
@Controller
public class GanTeTest {

	@RequestMapping({"toPage"})
	public String toPage(){
		return "totalQuery/planDetial/PlanMacTask";
	}
	
	@RequestMapping("getGanTe")
	@ResponseBody
	public Map<String, Object> getGanTe() {
		//最终
		Map<String, Object> map = new HashMap<>();

		List<String> macCodeList = new ArrayList<>();
		macCodeList.add("DA");
		macCodeList.add("DB");
		macCodeList.add("DC");
		macCodeList.add("DE");
		macCodeList.add("DF");
		List<String> gdList = new ArrayList<>();
		gdList.add("gd001");
		gdList.add("gd002");
		gdList.add("gd003");
		gdList.add("gd004");
		gdList.add("gd005");
		gdList.add("gd006");
		Map<String, Map<String, Object>> paramMap = new HashMap<>();
		Map<String,Object>maa = new HashMap<>();
		List<Map<String, Object>> listMap = new ArrayList<>();
		Integer count = 0;
		for (int i = 0; i < macCodeList.size(); i++) {
			List<Object>sinList = new ArrayList<>();
			for (String gd : gdList) {
				List<Date> dates = getDate((int)(Math.random() * 5));
				Map<String, Object> singMap = new HashMap<>();
				singMap.put("index", i);
				singMap.put("start", dates.get(0).getTime());
				singMap.put("end", dates.get(1).getTime()+10000000L*Math.random());
				singMap.put("startDate", dates.get(0));
				singMap.put("name", gd);
				long l = dates.get(1).getTime() - dates.get(0).getTime();
				long day = l / (24 * 60 * 60 * 1000);
				long hour = (l / (60 * 60 * 1000) - day * 24);
				long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
				long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
				String da = null;
				if (day != 0) {
					da += day + "天";
				}
				da += hour + "小时";
				da += min + "分钟";
				sinList.add(singMap);
			}
			
			maa.put(macCodeList.get(i), sinList);
			//listMap.add(maa);
		}
		map.put("macs", macCodeList);
		map.put("gds", gdList);
		map.put("startDate", new Date());
		map.put("data", maa);
		return map;
	}

	public List<Date> getDate(Integer count) {
		List<Date> list = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_MONTH, count);
		Date time = c.getTime();
		list.add(time);
		int random = (int) Math.random() * 3;
		c.add(Calendar.HOUR_OF_DAY, random);
		Date time2 = c.getTime();
		list.add(time2);
		return list;
	}
}
