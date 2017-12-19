package com.css.business.web.subsysplan.plaManage.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.css.business.web.subsyscraft.bean.CraBomTheory;
import com.css.business.web.subsyscraft.craManage.service.CraBomTheoryManageService;
import com.css.business.web.subsysplan.bean.PlaMachinePlanMater;
import com.css.business.web.subsysplan.plaManage.bean.CalculaMaterVo;
import com.css.business.web.subsysplan.plaManage.service.PlaMachinePlanMaterManageService;
import com.css.common.web.syscommon.service.SpringContextHolder;

/**
 * @Description 铜线的长度与重量的计算
 * @author JS 
 *
 */
public class  CalculaMaterUtil {
	
	private CraBomTheoryManageService service = SpringContextHolder.getBean("craBomTheoryManageService");
	private PlaMachinePlanMaterManageService materService = SpringContextHolder.getBean("plaMachinePlanMaterManageService");
	/**
	 * 做计算
	 * @return
	 */
 	public static Double doCalcula(){
		// TODO 计算实现的方法
		
		
		return null;
	}
 	/**
 	 * 
 	 * @Description:  铜料的计算方法  , 
 	 * @param paramCode 原料编码
 	 * @param actualAmount 实际需求数量
 	 * @return
 	 */
 	//数据库中存铜,应该是,每根的长度
 	public CalculaMaterVo getResultCu(String paramCode,Double actualAmount){
 		CraBomTheory craBomTheory = service.queryValueByCode(paramCode);
 		
 		if (craBomTheory !=null) {
			
		}
		return null;
 		
 	}
 	
 	/**
 	 * 
 	 * @Description: 根据工单编号,查出该工单编号下所有机台使用材料的信息  (老版,工单mater铜料还是以重量进行存储的)  
 	 * @param courses  工单编号
 	 * @return
 	 */
 	public List<CalculaMaterVo>getResult(String ...courses){
 		if (courses.length<1)return null; 
 		//返回的list
 		List<CalculaMaterVo> resultList = new ArrayList<>();
 		for (String code : courses) {
 			try {
 				List<PlaMachinePlanMater>materList =  materService.queryByCourseCode(code); 				
 				CalculaMaterVo vo = new CalculaMaterVo();
 				Integer macCount; //机台数量
 				//String glue_type; //胶料类型
 				String cu_type; //铜料类型
 				Double weight_glue = 0.0;//胶料重量
 				Double weight_cu = 0.0; //铜料重量
 				Double cu_lengths =0.0; //统计铜的长度
 				List<Integer>glue_machineIds = new ArrayList<>(); //使用胶料的机台id
 				List<Double>glueList = new ArrayList<>(); //对应的数量
 				List<Integer>cu_machineIds = new ArrayList<>(); //使用铜料的机台id
 				List<Double>cuList = new ArrayList<>(); //铜料对应的数量
 				for (PlaMachinePlanMater mater : materList) {
 					if ("胶料".equals(mater.getMaterName())) {
						if (mater.getAmount()!=null) {
							//胶料总质量
							weight_glue += mater.getAmount();
							//机台id 
							glue_machineIds.add(mater.getMachineId());
							glueList.add( mater.getAmount());
							/*glue_type = mater.getGgxh();
							//胶料单位
							CraBomTheory theory = service.queryValueByCode(glue_type);
							if (theory != null) {
								String paramValue = theory.getParamValue();
							}*/
						}
					} else if ("铜料".equals(mater.getMaterName())) {
						if (mater.getAmount()!=null) {
							weight_cu += mater.getAmount();
							cu_type = mater.getGgxh();
							//
							CraBomTheory theory = service.queryValueByCode(cu_type);
							if (theory != null) {
								Double value = Double.parseDouble(theory.getParamValue());//铜料是单位 ;m/kg  //JS 修改标记： 返回值类型改变，这里进行了类型转换 
								//Double resultDouble =  weight_cu*Double.parseDouble(value);
								//机台id
								cu_machineIds.add(mater.getMachineId());
								//保留两位小数
								Double cu_length = new BigDecimal(weight_cu*value).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
								//机台数值
								cuList.add(cu_length);
								//铜料总长度
								cu_lengths +=cu_length;
							}
						}
					}
				}
 				vo.setJlweight(weight_glue);
 				vo.setCulength(cu_lengths);
 				vo.setCuweight(weight_cu);
 				vo.setCuMachineIds(cu_machineIds);
 				vo.setCuVlaues(cuList);
 				vo.setGlueMachineIds(glue_machineIds);
 				vo.setGlueVlaues(glueList);
 				resultList.add(vo);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				continue;
			}
		}
 		if (resultList != null&& resultList.size()>0) {
 			return resultList;
		}
 		return null;
 	}
 	
 	/**
 	 * 
 	 * @Description: 铜料的质量换算成长度,(可用于 入库的时候,直接将重量换算成长度入库)   
 	 * @param cuTypeCode 材料在理论参数中的,param_code参数code
 	 * @param cuWeight 材料质量
 	 * @return
 	 */
 	public Double getCuLength(String cuTypeCode,Double cuWeight){
 		CraBomTheory craBomTheory = service.queryValueByCode(cuTypeCode);
 		Double cuLength = 0.0;
 		if (craBomTheory != null ) {
 			Double value = Double.parseDouble(craBomTheory.getParamValue()); //JS 修改标记： 返回值类型改变，这里进行了类型转换 
 			cuLength = new BigDecimal(cuWeight*value).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
 			return cuLength;
		}else {
			try {
				throw new Exception("找不到 类型是"+cuTypeCode+"的材料");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
 		return -1.0;
 	}
 	
 	
 	
 	/**
 	 * @Description: 将日期格式yyyyMMddy与yyyy-MM-dd格式互转
 	 * @param date ：需要转换的时间
 	 * @param fmt ：值只能为   yyyyMMdd 或者  yyyy-MM-dd
 	 * @return
 	 * @throws ParseException
 	 * @author JS
 	 */
 	
 	public static String strToDateFormat(String date,String fmt) throws  ParseException {
 		if("yyyyMMdd".equals(fmt)){
 			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
 			formatter.setLenient(false);
 			Date newDate= formatter.parse(date);
 			formatter = new SimpleDateFormat("yyyyMMdd");
 			return formatter.format(newDate);
 		}else if("yyyy-MM-dd".equals(fmt)){
 			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
 			formatter.setLenient(false);
 			Date newDate= formatter.parse(date);
 			formatter = new SimpleDateFormat("yyyy-MM-dd");
 			return formatter.format(newDate);
 		}
		return date;
    }
 	

 	
}
