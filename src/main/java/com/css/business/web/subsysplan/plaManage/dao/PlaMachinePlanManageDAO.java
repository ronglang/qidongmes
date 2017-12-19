package com.css.business.web.subsysplan.plaManage.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.subsysplan.bean.PlaMacTask;
import com.css.common.util.DateUtil;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("plaMachinePlanManageDAO")
public class PlaMachinePlanManageDAO extends BaseEntityDaoImpl<PlaMacTask>  {

	Logger log = Logger.getLogger(PlaMachinePlanManageDAO.class);
	

	
	
	/**
	 * @TODO:查询当天的机台计划.及今天之前延迟的
	 * @author: zhaichunlei
	 & @DATE : 2017年7月10日
	 * @return 
	 */
	public List<PlaMacTask> queryMachinePlanDaily(){
		String sql = "select p from PlaMacTask p where p.pstime<=current_date and productState in('已排产','生产中','结束') ";
		List<PlaMacTask> lst = createQuery(sql).list();
		return lst;
	} 
	
	/**
	 * @TODO:查询当天的工单对应的机台计划.
	 & @DATE : 2017年7月10日
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PlaMacTask> queryMachinePlanDailyByCourse(String wsCode){
		//List<PlaMachinePlan> lst = findBy("workDate", "current_date");
		//return lst;
		//String str = "select p from PlaMachinePlan p where p.workDate<=current_date and p.productState in('未开始','生产中') and p.courseCode=? ";
		//使用workDay作为条件
		String str = "select p from PlaMacTask p where p.pstime<=CAST(to_char(now(),'yyyyMMdd') as int4) and p.productState in('已排产','生产中','结束') and p.workcode=? ";
		List<PlaMacTask> lst = createQuery(str,wsCode).list();
		return lst;
	}
	
	
	/**
	 * @TODO:查询工单对应的机台计划.
	 & @DATE : 2017年7月10日
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PlaMacTask> queryMachinePlanByWorkCode(String workCode){
		//使用workDay作为条件
		String str = "select p from PlaMacTask p where  p.workcode=?  order by step";
		List<PlaMacTask> lst = createQuery(str,workCode).list();
		return lst;
	}
	
	

	/**
	 * @TODO:取明天以内到计划.
	 & @DATE : 2017年7月10日
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PlaMacTask> getSendMacTaskList(String macCode){
		//List<PlaMachinePlan> lst = findBy("workDate", "current_date");
		//return lst;
		//String str = "select p from PlaMachinePlan p where p.workDate<=current_date and p.productState in('未开始','生产中') and p.courseCode=? ";
		//使用workDay作为条件
		String str = "select p from PlaMacTask p where  p.maccode=? and p.productstate='计划' "
				+" and p.pstime<to_timestamp(to_char((CURRENT_DATE+1),'YYYY-MM-DD')||' 23:59', 'YYYY-MM-DD hh24:mi')";
		List<PlaMacTask> lst = createQuery(str,macCode).list();
		return lst;
	}
	
	
	/**
	 * 删除对应的计划数据
	 */
	@Transactional(readOnly = false)
	public void deleteByworkCode(String workCode) throws Exception{
		boolean bool = true;
		try{
			String sql = "delete from pla_mac_task where workcode='"+workCode+"'";
			this.deleteBySql(sql);
			sql = "delete from pla_mac_task_color where workcode='"+workCode+"'";
			this.deleteBySql(sql);
			sql = "delete from pla_mac_task_materil where workcode='"+workCode+"'";
			this.deleteBySql(sql);
		}catch(Exception e){
			throw new Exception("清除工作单"+workCode+"对应的计划失败");
		}
		
	}
	

	public String updatePlaSeqMaterDetail(String str,String macRemark) {
		String msg= "";
		try {
			if(null!=str&&!"".equals(str)){
				String sql ="update   pla_seq_mater_detail set incoming_axis=? where machine_plan_id "
						+ "=(select id from pla_machine_plan where machine_id"
						+ "=(select id from mau_machine where mac_mark=?) )";
				Query query = this.createSQLQuery(sql, str,macRemark);
				query.executeUpdate();
				msg="修改成功";
			}else{
				msg="手持机传入的RFID为空";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg=e.getMessage();
		}
		return msg;
		
	}
	/**
	 * 修改计划表的实生产时间
	 *@data:2017年7月19日
	@param newDate
	@autor:wl
	 */
//更新机台计划的实际开始生产时间
	public void updateFactTimeFromPlan(Timestamp newDate,String workCode,String macCode) {
		try {
			String sql ="update pla_mac_task  set fstime=? where workcode='"+workCode+"' and maccode='"+macCode+"'";
			  Query query = this.createSQLQuery(sql, newDate);
			  query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}
/**
 * 修改计划表的实际完成时间
 *@data:2017年7月19日
@param newDate
@autor:wl
 */
	public void updateFactEndTime(Timestamp newDate,String macRemark) {
		try {
			String sql ="update pla_machine_plan  set fact_start_time=? where machine_id=(select id from mau_machine where mac_mark=?)";
			  Query query = this.createSQLQuery(sql, newDate,macRemark);
			  query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} 
	
	/**
	 * @TODO: 根据机台code、取当工序的step获取唯一的机台计划数据
	 * @param macCode
	 * @return
	 */
	public PlaMacTask queryMacTask(String workCode,String macCode,int step){
		//List<PlaMachinePlan> lst = findBy("workDate", "current_date");
		//return lst;
		
			String str = "select p from PlaMacTask p where p.workcode=? and p.maccode=? and step=?  ";
			@SuppressWarnings("unchecked")
			List<PlaMacTask> lst = createQuery(str,workCode,macCode,step).list();
			if(lst.size()<1||lst==null){
				return null;
			}
			return (PlaMacTask)lst.get(0);
	} 
	
	
	
	/**
	 * @TODO: 根据工单code、当前的取当工序的生产计划
	 * @param macCode
	 * @return
	 */
	public List<PlaMacTask> queryMachinePlanByMacCode(String workcode){
		//List<PlaMachinePlan> lst = findBy("workDate", "current_date");
		//return lst;
		
			String str = "select p from PlaMacTask p where p.workcode=?  ";
			@SuppressWarnings("unchecked")
			List<PlaMacTask> lst = createQuery(str,workcode).list();
		
			return lst;
	} 
	
	/**
	 * @TODO: 根据工单，查询周计划是否完成。 大于0则说明未完
	 * @author: zhaichunlei
	 & @DATE : 2017年7月20日
	 * @param courseCode
	 * @return
	 */
	public int getMachinePlanByCoureCode_not_complete(String courseCode,Integer workDay){
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from pla_machine_plan p where p.week_plan_id in(");
		sb.append("select week_plan_id from pla_machine_plan p2 where p2.product_state in('生产中','未开始') and p2.course_code=? ) ");
        sb.append("and p.work_day=? ");
		
		int num = Integer.parseInt(this.createSQLQuery(sb.toString(),courseCode,workDay).uniqueResult().toString());
		return num;
	}

	/**
	 * @TODO: 根据工单、当前机台，取制品配送的下一机台
	 * @author: zhaichunlei
	 & @DATE : 2017年7月21日
	 * @param courseCode
	 * @param macCode
	 * @return
	 */
	public String getNextMachineNameByCourseCode_MachineCode(String courseCode,String macCode){
		StringBuffer sb = new StringBuffer();
		sb.append("select  (select mac_name from mau_machine m where m.mac_code=s.mac_code) macName  ");
		sb.append(" from pla_machine_plan s,pla_machine_plan s2 ");
		sb.append("where s.course_code=s2.course_code and s2.mac_code=? and s.axis_name=s2.axis_name and s.id<>s2.id ");
		sb.append("and s.sort > s2.sort and s.course_code=? order by s.sort limit 1 ");
		
		Object o =this.createSQLQuery(sb.toString(),macCode,courseCode).uniqueResult();
		
		return o == null ? null : o.toString();
	}

	/**   
	 * @Description:获得某日生产的工单编号  
	 * @param workDate 工作日 2015-09-09
	 * @return         
	 */ 
	public List<Object[]> getCourseCode(String workDate) {
		// TODO Auto-generated method stub
		String hql = "select courseCode, routeCode from PlaMachinePlan where workDate = '"+workDate+"' and routeCode is not null  group by courseCode,routeCode";
		List<Object[]> list = this.createQuery(hql).list();
		return list;
	}

	/**   
	 * @Description: 查询正在生产的工单信息  
	 * @return         
	 */ 
	public List<Object[]> getWorkingCourseCode() {
		// TODO Auto-generated method stub
		String hql ="select courseCode, routeCode from PlaMachinePlan where productState='生产中' group by courseCode,routeCode";
		List<Object[]> list = this.createQuery(hql).list();
		return list;
	}
	

	/**
	 * 根据ggxh和工序，得的对应的直径值
	 * @param gghx
	 * @param seq
	 * @return
	 */
	public float getDiameterVaule(String ggxh,ArrayList<Integer> steps,ArrayList<String> seqs,int index){
		float diameter = 0;
		for(int i = index; i>=0; i--){
			
			String seq = seqs.get(i);
			if(seq.equalsIgnoreCase("fr")||seq.equalsIgnoreCase("yz")){
				continue;
			}
			int step = steps.get(i);
			String sql="";
			if(seq.equalsIgnoreCase("ls")){
				sql = "select target_diameter from cra_ls_bom_param where pro_ggxh='"
						+ggxh +"' and seq_step="+step;
				List<Object> list = this.createSQLQuery(sql).list();
				for(Object obj:list){
					if(obj != null){
						diameter =(float)obj;
					}
				}
					
			}
			if(seq.equalsIgnoreCase("bd")){
				sql = "select overall_diameter from cra_bd_bom_param where pro_ggxh='"
						+ggxh +"' and seq_step="+step;
				List<Object> list = this.createSQLQuery(sql).list();
				for(Object obj:list){
					if(obj!=null){
						diameter =(float)obj;
					}
				}	
			}
			if(seq.equalsIgnoreCase("jx")){
				sql = "select fourth_fdiameter, third_fdiameter, twice_fdiameter, "
						+"once_fdiameter from cra_jx_bom_param where pro_ggxh='"
						+ggxh +"' and seq_step="+step;
				List<Object> list = this.createSQLQuery(sql).list();
				for(Object ob:list){
					Object[] obj =(Object[])ob;
					for(int j  = 0 ; j<obj.length;j++){
						String tmp = (String)obj[j];
						if(tmp!=null&&tmp.length()>1){
							String[] dia = tmp.split("x");
							if(dia.length>1){
								float d1 = Float.valueOf(dia[0]);
								float d2 = Float.valueOf(dia[1]);
								if(d1>d2){
									diameter = d1;
								}
								else{
									diameter = d2;
							    }
							}
							else{
								diameter = Float.valueOf(dia[0]);
							}
							break;
						}
						else{
							continue;
						}
					}
				}
				
			}
			
			if(seq.equalsIgnoreCase("jy")){
				sql = "select normal_diameter from cra_jy_bom_param where pro_ggxh='"
						+ggxh +"' and  seq_step="+step;			
				List<Object> list = this.createSQLQuery(sql).list();
				String tmp = "";
				for(Object ob:list){
					if(ob!=null){
						 tmp =(String)ob;
					}
				}
				String[] dia = tmp.split("x");
				if(dia.length>1){
					float d1 = Float.valueOf(dia[0]);
					float d2 = Float.valueOf(dia[1]);
					if(d1>d2){
						diameter = d1;
					}
					else{
						diameter = d2;
				    }
				}
				else{
					diameter = Float.valueOf(dia[0]);
				
				}
			}
			if(seq.equalsIgnoreCase("jj")
					||seq.equalsIgnoreCase("ht")
					||seq.equalsIgnoreCase("jtx")
					||seq.equalsIgnoreCase("dj")){
				sql = "select normal_diameter from cra_"+seq+"_bom_param where pro_ggxh='"
						+ggxh +"' and  seq_step="+step;	
				List<Object> list = this.createSQLQuery(sql).list();
				for(Object ob:list){
					if(ob!=null){
						diameter =(float)ob;
					}
				}
			}
			if(diameter>0){
				break;
			}
		}
		return diameter;
	}
	
	/**
	 * 根据ggxh和工序，得的对应的直径值
	 * @param gghx
	 * @param seq
	 * @return
	 */
	public float getDiameterVaule2(String ggxh,int step,String seq){
		float diameter = 0;
		
			if(seq.equalsIgnoreCase("fr")||seq.equalsIgnoreCase("yz")){
				diameter = 0;
			}
			String sql="";
			if(seq.equalsIgnoreCase("ls")){
				sql = "select target_diameter from cra_ls_bom_param where pro_ggxh='"
						+ggxh +"' and seq_step="+step;
				List<Object> list = this.createSQLQuery(sql).list();
				for(Object obj:list){
					if(obj != null){
						diameter =(float)obj;
					}
				}
					
			}
			if(seq.equalsIgnoreCase("bd")){
				sql = "select overall_diameter from cra_bd_bom_param where pro_ggxh='"
						+ggxh +"' and seq_step="+step;
				List<Object> list = this.createSQLQuery(sql).list();
				for(Object obj:list){
					if(obj!=null){
						diameter =(float)obj;
					}
				}	
			}
			if(seq.equalsIgnoreCase("jx")){
				sql = "select fourth_fdiameter, third_fdiameter, twice_fdiameter, "
						+"once_fdiameter from cra_jx_bom_param where pro_ggxh='"
						+ggxh +"' and seq_step="+step;
				List<Object> list = this.createSQLQuery(sql).list();
				for(Object ob:list){
					Object[] obj =(Object[])ob;
					for(int j  = 0 ; j<obj.length;j++){
						String tmp = (String)obj[j];
						if(tmp!=null&&tmp.length()>1){
							String[] dia = tmp.split("x");
							if(dia.length>1){
								float d1 = Float.valueOf(dia[0]);
								float d2 = Float.valueOf(dia[1]);
								if(d1>d2){
									diameter = d1;
								}
								else{
									diameter = d2;
							    }
							}
							else{
								diameter = Float.valueOf(dia[0]);
							}
							break;
						}
						else{
							continue;
						}
					}
				}
				
			}
			
			if(seq.equalsIgnoreCase("jy")){
				sql = "select normal_diameter from cra_jy_bom_param where pro_ggxh='"
						+ggxh +"' and  seq_step="+step;			
				List<Object> list = this.createSQLQuery(sql).list();
				String tmp = "";
				for(Object ob:list){
					if(ob!=null){
						 tmp =(String)ob;
					}
				}
				String[] dia = tmp.split("x");
				if(dia.length>1){
					float d1 = Float.valueOf(dia[0]);
					float d2 = Float.valueOf(dia[1]);
					if(d1>d2){
						diameter = d1;
					}
					else{
						diameter = d2;
				    }
				}
				else{
					diameter = Float.valueOf(dia[0]);
				
				}
			}
			if(seq.equalsIgnoreCase("jj")
					||seq.equalsIgnoreCase("ht")
					||seq.equalsIgnoreCase("jtx")
					||seq.equalsIgnoreCase("dj")){
				sql = "select normal_diameter from cra_"+seq+"_bom_param where pro_ggxh='"
						+ggxh +"' and  seq_step="+step;	
				List<Object> list = this.createSQLQuery(sql).list();
				for(Object ob:list){
					if(ob!=null){
						diameter =(float)ob;
					}
				}
			}
		
		return diameter;
	}
	
	/**
	 * 根据ggxh和工序，得的对应的最大绞距
	 * @param gghx
	 * @param seq
	 * @return
	 */
	public float getLayValue(String ggxh,String seq,int step){
		float lay = 0;
		String sql="";

		if(seq.equalsIgnoreCase("jx")){
			sql = "select fourth_lay, third_lay, twice_lay, "
					+"once_lay from cra_jx_bom_param where pro_ggxh='"
					+ggxh +"' and  seq_step="+step;
			List<Object> list = this.createSQLQuery(sql).list();
			for(Object ob:list){
				Object[] obj =(Object[])ob;
				for(int i  = 0 ; i<obj.length;i++){
					if(obj[i]!=null){
						float tmp = (float)obj[i];
						if(tmp>0){
							lay = tmp;
							break;
						}
					}
					
				}
			}
			
		}
		
		if(seq.equalsIgnoreCase("jj")
				||seq.equalsIgnoreCase("jtx")
				||seq.equalsIgnoreCase("dj")){
			sql = "select lay from cra_"+seq+"_bom_param where pro_ggxh='"
					+ggxh +"' and  seq_step="+step;	
			List<Object> list = this.createSQLQuery(sql).list();
			for(Object ob:list){
				if(ob!=null){
					lay =(float)ob;
				}
			}
		}
		return lay;
	}
	
	
	/**
	 * 获取该工序的材料名称
	 * @param ggxh
	 * @param seq
	 * @param step
	 * @return
	 */
	public String getMaterValue(String ggxh,String seq,int step){
		String mater="";
		String sql = "select mater from cra_"+seq+"_bom_param where pro_ggxh='"
				+ggxh +"' and  seq_step="+step;	
		List<Object> list = this.createSQLQuery(sql).list();
		for(Object ob:list){
			if(ob!=null){
				mater = (String)ob;
			}
		}
		return mater;
	}
	/**
	 * 获取调试长度
	 * @param ggxh
	 * @param seq
	 * @param step
	 * @return
	 */
	public float getDebugLenght(String ggxh,String seq,int step,String macCode){
		float debugLength = 0;
		//首先从mau_machine_emplyoee
		float diameter = this.getDiameterVaule2(ggxh, step, seq);
		String sql="";
		if(diameter>0){
			 sql ="select max(test_len) from mau_machine_employee where mac_code='"+macCode
						+"' and diameter="+ diameter;
		}else{
			 sql ="select max(test_len) from mau_machine_employee where mac_code='"+macCode+"'";
		}
		List<Object> list = this.createSQLQuery(sql).list();
		if(list.size()>0&&list.get(0)!=null){
			debugLength = (float)list.get(0);
		}
		else{
			  sql = "select debug_length from cra_"+seq+"_bom_param where"
						+ " pro_ggxh='"+ggxh+"' and seq_step="+step;
			   list = this.createSQLQuery(sql).list();
			   if(list.size()>0&&list.get(0)!=null){
					debugLength = (float)((int)list.get(0));
				}
		}
	   
		return debugLength;
	}
	

	/**
	 * 根据规格型号，得到工序列表以及集绞步骤的标识
	 * 
	 * @param ggxh
	 * @return
	 */
	public void getSeqs(String ggxh, ArrayList<String> seqs,
			ArrayList<Integer> steps) {

		String sql = "select b.seq_code, a.seq_step  from cra_route a ,cra_seq b  "
				+ "where a.pro_ggxh ='"+ggxh+"' and a.seq_name= b.seq_name   order BY seq_step";
		List<Object> tmp = this.createSQLQuery(sql).list();
		for(Object obj:tmp){
			Object[] obs = (Object[])obj;
			seqs.add((String) obs[0]);
			steps.add((int) obs[1]);
		}
	}
	
	/**
	 * 求机台对应的线直径可能的单轴最大生产力
	 * @param axisType
	 * @param macCode
	 * @param diameter
	 * @return list<"轴类型=长度">
	 */
	public ArrayList<String> getPeAxisLength(float diameter,boolean bool){
		ArrayList<String> tyepLen = new ArrayList<String>();
		float totalLenght=0.0f;
		float d = diameter*15;
		String sql="";
		if(bool){
			sql="select  ma.axis_name,ma.external_diameter,ma.internal_diameter,ma.axis_in_width,ma.axis_out_width,ma.center_diameter"
					+ " from Mau_Axis ma where internal_diameter >"+d+" and type='铁轴' "
					+ "group by ma.axis_name,ma.external_diameter,ma.internal_diameter,ma.axis_in_width,ma.axis_out_width,ma.center_diameter";
		}
		else{
			sql="select  ma.axis_name,ma.external_diameter,ma.internal_diameter,ma.axis_in_width,ma.axis_out_width,ma.center_diameter"
					+ " from Mau_Axis ma where internal_diameter >"+d+" and type='胶轴' "
					+ "group by ma.axis_name,ma.external_diameter,ma.internal_diameter,ma.axis_in_width,ma.axis_out_width,ma.center_diameter";
		}
		List<Object[]> axisList2 = this.createSQLQuery(sql).list();
		for (Object[] objects : axisList2) {
			String axisName = objects[0].toString();
			// 外盘径
			Integer externalDiameter = (Integer) objects[1];
			// 内盘径
			Integer internalDiameter = (Integer) objects[2];
			// 轴内宽
			Integer axisInWidth = (Integer) objects[3];
			// 轴外宽
			Integer axisOutWidth = (Integer) objects[4];
			// 中心孔直径
			Integer centerDiameter = (Integer) objects[5];
			// 每层线数 N
			int N = (int) (axisInWidth / (diameter * 1.07));
			// 装线缆层数
			int n = (int) (((externalDiameter - internalDiameter) / 2 - 50) / diameter);
			float interd = (float)internalDiameter.intValue();
			for(int i = 0 ; i<n;i++){
				totalLenght = totalLenght+(float)Math.PI*(interd+diameter)*N/1000;
				interd=interd+2*diameter;
			}
			
			tyepLen.add(axisName+"="+String.valueOf(totalLenght));
		}
		return tyepLen;
	}
	
/**
 *  最大单轴量，根据直径大小，轴内直径>15*线直径
 * @param diameter
 * @param bool  true 铁轴；false 胶轴
 * @return
 */
	public Float getOneMaxPeAxisLength(float diameter,boolean bool){
		float totalLenght=0.0f;
		float d = diameter*15;
		String sql="";
		if(bool){
			sql="select  ma.axis_name,ma.external_diameter,ma.internal_diameter,ma.axis_in_width,ma.axis_out_width,ma.center_diameter"
					+ " from Mau_Axis ma where internal_diameter in(select min(internal_diameter) "
					+ "from Mau_Axis where internal_diameter >"+d+" and type='铁轴') "
					+ "group by ma.axis_name,ma.external_diameter,ma.internal_diameter,ma.axis_in_width,ma.axis_out_width,ma.center_diameter";
		}
		else{
			sql="select  ma.axis_name,ma.external_diameter,ma.internal_diameter,ma.axis_in_width,ma.axis_out_width,ma.center_diameter"
					+ " from Mau_Axis ma where internal_diameter in(select min(internal_diameter) "
					+ "from Mau_Axis where internal_diameter >"+d+" and type='胶轴') "
					+ "group by ma.axis_name,ma.external_diameter,ma.internal_diameter,ma.axis_in_width,ma.axis_out_width,ma.center_diameter";
		}
		
		// List<MauAxis> axisList = dao.createQuery(sql).list();
		List<Object[]> axisList2 = this.createSQLQuery(sql).list();
		for (Object[] objects : axisList2) {
			String axisName = objects[0].toString();
			// 外盘径
			Integer externalDiameter = (Integer) objects[1];
			// 内盘径
			Integer internalDiameter = (Integer) objects[2];
			// 轴内宽
			Integer axisInWidth = (Integer) objects[3];
			// 轴外宽
			Integer axisOutWidth = (Integer) objects[4];
			// 中心孔直径
			Integer centerDiameter = (Integer) objects[5];
			// 每层线数 N
			int N = (int) (axisInWidth / (diameter * 1.07));
			// 装线缆层数
			int n = (int) (((externalDiameter - internalDiameter) / 2 - 50) / diameter);
	
			float interd = (float)internalDiameter.intValue();
			for(int i = 0 ; i<n;i++){
				totalLenght = totalLenght+(float)Math.PI*(interd+diameter)*N/1000;
				interd=interd+2*diameter;
			}
			
		}
		return totalLenght;
	}
	
/**
 * 根据轴的类型，计算该直径线最大装载长度
 * @param diameter
 * @param axisType
 * @return
 */
		public Float getAxisTypeMaxLen(float diameter,String axisType){
			float totalLenght=0.0f;
			float d = diameter*15;
			String sql="";
			
			sql="select  ma.axis_name,ma.external_diameter,ma.internal_diameter,ma.axis_in_width,ma.axis_out_width,ma.center_diameter"
						+ " from Mau_Axis ma where axis_name ='"
					    + axisType +"' "  
						+ "group by ma.axis_name,ma.external_diameter,ma.internal_diameter,ma.axis_in_width,ma.axis_out_width,ma.center_diameter";
		
			
			// List<MauAxis> axisList = dao.createQuery(sql).list();
			List<Object[]> axisList2 = this.createSQLQuery(sql).list();
			for (Object[] objects : axisList2) {
				String axisName = objects[0].toString();
				// 外盘径
				Integer externalDiameter = (Integer) objects[1];
				// 内盘径
				Integer internalDiameter = (Integer) objects[2];
				// 轴内宽
				Integer axisInWidth = (Integer) objects[3];
				// 轴外宽
				Integer axisOutWidth = (Integer) objects[4];
				// 中心孔直径
				Integer centerDiameter = (Integer) objects[5];
				// 每层线数 N
				int N = (int) (axisInWidth / (diameter * 1.07));
				// 装线缆层数
				int n = (int) (((externalDiameter - internalDiameter) / 2 - 50) / diameter);
		
				float interd = (float)internalDiameter.intValue();
				for(int i = 0 ; i<n;i++){
					totalLenght = totalLenght+(float)Math.PI*(interd+diameter)*N/1000;
					interd=interd+2*diameter;
				}
				
			}
			return totalLenght;
		}
}
