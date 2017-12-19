<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>绝缘工序电子看板</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>core/css/board/insulation1.css"/>
		
		<script type="text/javascript">
			var basePath = "<%=basePath%>";
			var urlPath = basePath.substring(7, basePath.length);
		</script> 
		<style type="text/css">
			.content{
				
			}
			.content table{
			
			}
			.content table tr{
			
			}
		</style>
  </head>
  <body>
		
		<div id="first">
			<div class="top">
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td><span>机台：</span><b id="machineName"></b></td>
						<td><span>规格型号：</span><b id="machineType"></b></td>
						<td><span>工单编号：</span><b id="courseCode"></b></td>
						<td><span>操作人员：</span><b id="operPerson"></b></td>
						<td><span>任务完成情况：</span><b><span id="taskPercent"></span>%</b></td>
						<td><b id="showtime"></b></td>
					</tr>
				</table>
			</div>
			
			<div class="head">
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th>原料信息</th>
						<td><span>材料：</span><b id="material"></b></td>
						<td><span>型号：</span><b id="material_type"></b></td>
						<td><span>颜色：</span><b id="material_color"></b></td>
						<td><span>数量：</span><b><span id="material_num"></span>kg(4袋)</b></td>
						<td><span>已使用胶料：</span><b><span id="material_use"></span>kg</b></td>
						<td><span>剩余胶料：</span><b><span id="material_reNum"></span>kg</b></td>
					</tr>
				</table>
				
			</div>
			
			<div class="content">
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<span>出线速度：</span>
							<label id="3000">暂无数据</label>
						</td>
						<td>
							<span>机器最大线速度：</span>
							<label id="3001">暂无数据</label>
						</td>
						<td>
							<span>1#35机螺杆转速：</span>
							<label id="3002">暂无数据</label>
						</td>
						<td>
							<span>80机螺杆转速：</span>
							<label id="3003">暂无数据</label>
						</td>
					</tr>
					<tr>
						<td>
							<span>2#35机螺杆转速：</span>
							<label id="3004">暂无数据</label>
						</td>
						<td>
							<span>米重系数：</span>
							<label id="3008">暂无数据</label>
						</td>
						<td>
							<span>生产米数：</span>
							<label id="3009">暂无数据</label>
						</td>
						<td>
							<span>线速系数：</span>
							<label id="3010">暂无数据</label>
						</td>
					</tr>
					<tr>
						<td>
							<span>80机系数：</span>
							<label id="3011">暂无数据</label>
						</td>
						<td>
							<span>35机1系数：</span>
							<label id="3012">暂无数据</label>
						</td>
						<td>
							<span>35机2系数：</span>
							<label id="3013">暂无数据</label>
						</td>
						<td>
							<span>测距仪到水槽距离：</span>
							<label id="3014">暂无数据</label>
						</td>
					</tr>
					<tr>
						<td>
							<span>成品线径：</span>
							<label id="3018">暂无数据</label>
						</td>
						<td>
							<span>冷外径系数：</span>
							<label id="3019">暂无数据</label>
						</td>
						<td>
							<span>引取系数：</span>
							<label id="3020">暂无数据</label>
						</td>
						<td>
							<span>内启动提前系数K1：</span>
							<label id="3021">暂无数据</label>
						</td>
					</tr>
					<tr>
						<td>
							<span>35一段PV：</span>
							<label id="3023">暂无数据</label>
						</td>
						<td>
							<span>35二段PV：</span>
							<label id="3024">暂无数据</label>
						</td>
						<td>
							<span>35三段PV：</span>
							<label id="3025">暂无数据</label>
						</td>
						<td>
							<span>80一段PV：</span>
							<label id="3026">暂无数据</label>
						</td>
					</tr>
					<tr>
						<td>
							<span>80二段PV：</span>
							<label id="3027">暂无数据</label>
						</td>
						<td>
							<span>80三段PV：</span>
							<label id="3028">暂无数据</label>
						</td>
						<td>
							<span>80四段PV：</span>
							<label id="3029">暂无数据</label>
						</td>
						<td>
							<span>35一段PV：</span>
							<label id="3030">暂无数据</label>
						</td>
					</tr>
					<tr>
						<td>
							<span>35二段PV：</span>
							<label id="3031">暂无数据</label>
						</td>
						<td>
							<span>35三段PV：</span>
							<label id="3032">暂无数据</label>
						</td>
						<td>
							<span>35机颈PV：</span>
							<label id="3043">暂无数据</label>
						</td>
						<td>
							<span>35导料PV：</span>
							<label id="3044">暂无数据</label>
						</td>
					</tr>
					<tr>
						<td>
							<span>35连接PV：</span>
							<label id="3045">暂无数据</label>
						</td>
						<td>
							<span>80五段PV：</span>
							<label id="3046">暂无数据</label>
						</td>
						<td>
							<span>80机颈PV：</span>
							<label id="3047">暂无数据</label>
						</td>
						<td>
							<span>80机头PV：</span>
							<label id="3048">暂无数据</label>
						</td>
					</tr>
					<tr>
						<td>
							<span>80眼模PV：</span>
							<label id="3049">暂无数据</label>
						</td>
						<td>
							<span>35机颈PV：</span>
							<label id="3050">暂无数据</label>
						</td>
						<td>
							<span>35导料PV：</span>
							<label id="3051">暂无数据</label>
						</td>
						<td>
							<span>35连接PV：</span>
							<label id="3052">暂无数据</label>
						</td>
					</tr>
					<tr>
						<td>
							<span>35机螺杆胶重G1：</span>
							<label id="3063">暂无数据</label>
						</td>
						<td>
							<span>机头胶重G3：</span>
							<label id="3064">暂无数据</label>
						</td>
						<td>
							<span>转阀胶重G5：</span>
							<label id="3065">暂无数据</label>
						</td>
						<td>
							<span>外停机提前系数K2：</span>
							<label id="3066">暂无数据</label>
						</td>
					</tr>
					<tr>
						<td>
							<span>换向提前系数K3：</span>
							<label id="3067">暂无数据</label>
						</td>
						<td>
							<span>加速速率：</span>
							<label id="3068">暂无数据</label>
						</td>
						<td>
							<span>降速速率：</span>
							<label id="3069">暂无数据</label>
						</td>
						<td>
							<span>1档速比：</span>
							<label id="3070">暂无数据</label>
						</td>
					</tr>
					<tr>
						<td>
							<span>2档速比：</span>
							<label id="3071">暂无数据</label>
						</td>
						<td>
							<span>3档速比：</span>
							<label id="3072">暂无数据</label>
						</td>
						<td>
							<span>4档速比：</span>
							<label id="3073">暂无数据</label>
						</td>
						<td>
							<span>35机A马达电流：</span>
							<label id="3074">暂无数据</label>
						</td>
					</tr>
					<tr>
						<td>
							<span>35机B马达电流：</span>
							<label id="3075">暂无数据</label>
						</td>
						<td>
							<span>80电流：</span>
							<label id="3076">暂无数据</label>
						</td>
						<td>
							<span>火花机报警次数：</span>
							<label id="3077">暂无数据</label>
						</td>
						<td>
							<span>火花机开机状态：</span>
							<label id="3078">暂无数据</label>
						</td>
					</tr>
					<tr>
						<td>
							<span>A相电压：</span>
							<label id="2001">暂无数据</label>
						</td>
						<td>
							<span>B相电压：</span>
							<label id="2002">暂无数据</label>
						</td>
						<td>
							<span>C相电压：</span>
							<label id="2003">暂无数据</label>
						</td>
						<td>
							<span>A相电流：</span>
							<label id="2004">暂无数据</label>
						</td>
					</tr>
					<tr>
						<td>
							<span>B相电流：</span>
							<label id="2005">暂无数据</label>
						</td>
						<td>
							<span>C相电流：</span>
							<label id="2006">暂无数据</label>
						</td>
						<td>
							<span>总有功率：</span>
							<label id="2007">暂无数据</label>
						</td>
						<td>
							<span>A相有功率：</span>
							<label id="2008">暂无数据</label>
						</td>
					</tr>
					<tr>
						
						<td>
							<span>B相有功率：</span>
							<label id="2009">暂无数据</label>
						</td>
						<td>
							<span>C相有功率：</span>
							<label id="2010">暂无数据</label>
						</td>
						<td>
							<span>总无功率：</span>
							<label id="2011">暂无数据</label>
						</td>
						<td>
							<span>A相无功率：</span>
							<label id="2012">暂无数据</label>
						</td>
					</tr>
					<tr>
						
						<td>
							<span>B相无功率：</span>
							<label id="2013">暂无数据</label>
						</td>
						<td>
							<span>C相无功率：</span>
							<label id="2014">暂无数据</label>
						</td>
						<td>
							<span>总功率因数：</span>
							<label id="2015">暂无数据</label>
						</td>
						<td>
							<span>A相功率因数：</span>
							<label id="2016">暂无数据</label>
						</td>
					</tr>
					<tr>
						
						<td>
							<span>B相功率因数：</span>
							<label id="2017">暂无数据</label>
						</td>
						<td>
							<span>C相功率因数：</span>
							<label id="2018">暂无数据</label>
						</td>
						
					</tr>
					
					<!-- 
					<tr class="con_tr_d">
						<td><span>已生产米数：</span><b><span id="semiLen"></span>m</b></td>
						<td><span>轴名称：</span><b id="axis_name"></b></td>
						<td><span>生产段长：</span><b id="partLen">未知</b></td>
						<td><span>收线速度：</span><b><span id="jy_take_line_speed"></span>m/s</b></td>
					</tr>
					<tr class="con_tr_s">
						<td><span>主机速度：</span><b><span id="jy_master_speed"></span>m/s</b></td>
						<td><span>生产米数：</span><b><span id="productLen"></span>m/s</b></td>
						<td><span>导体OD值：</span><b><span id="jy_od_value"></span>mm</b></td>
						<td><span>外径值：</span><b><span id="jy_outd"></span>mm</b></td>
					</tr>
					<tr class="con_tr_d">
						<td><span>35机头温度：</span><b><span id="jy_nose35_temp"></span>&#8451;</b></td>
						<td><span>35机颈温度：</span><b><span id="jy_neck35_temp"></span>&#8451;</b></td>
						<td><span>80机头温度：</span><b><span id="jy_nose80_temp"></span>&#8451;</b></td>
						<td><span>80机颈温度：</span><b><span id="jy_neck80_temp"></span>&#8451;</b></td>
					</tr>
					<tr class="con_tr_s">
						<td><span>1#一段35机温度：</span><b><span id="jy_1one35_temp"></span>&#8451;</b></td>
						<td><span>1#二段35机温度：</span><b><span id="jy_1two35_temp"></span>&#8451;</b></td>
						<td><span>1#三段35机温度：</span><b><span id="jy_1three35_temp"></span>&#8451;</b></td>
						<td><span>一段80机温度：</span><b><span id="jy_one80_temp"></span>&#8451;</b></td>
						
					</tr>
					<tr class="con_tr_d">
						<td><span>二段80机温度：</span><b><span id="jy_two80_temp"></span>&#8451;</b></td>
						<td><span>三段80机温度：</span><b><span id="jy_three80_temp"></span>&#8451;</b></td>
						<td><span>四段80机温度：</span><b><span id="jy_four80_temp"></span>&#8451;</b></td>
						<td><span>五段80机温度：</span><b><span id="jy_five80_temp"></span>&#8451;</b></td>
						
						
					</tr>
					<tr class="con_tr_s">
						<td><span>2#一段35机温度：</span><b><span id="jy_2one35_temp"></span>&#8451;</b></td>
						<td><span>2#二段35机温度：</span><b><span id="jy_2two35_temp"></span>&#8451;</b></td>
						<td><span>2#三段35机温度：</span><b><span id="jy_2three35_temp"></span>&#8451;</b></td>
						<td><span>火花报警：</span><b><span id="jy_spark_report"></span>次</b></td>
					</tr>
					<tr class="con_tr_d">
						<td><span>挤出压力：</span><b><span id="jy_out_pressure"></span>N</b></td>
						<td><span>收线张力：</span><b><span id="jy_takeLine_power"></span>N</b></td>
						<td><span>放线张力：</span><b><span id="jy_layLine_power"></span>N</b></td>
						<td><span>水槽温度：</span><b><span id="jy_flume_temp"></span>&#8451;</b></td>
					</tr>
					<tr>
						<td><span>机台耗能：</span><b><span  id="consumePower">未知</span>m</b></td>
					</tr>
					
					 -->
				</table>
			</div>
			
			
		</div>
		
		
		<script type="text/javascript" src="<%=basePath%>core/js/jquery-2.1.4.min.js" ></script>
		<script type="text/javascript" src="<%=basePath%>core/js/board/insulation1.js" ></script>
	</body>
</html>
