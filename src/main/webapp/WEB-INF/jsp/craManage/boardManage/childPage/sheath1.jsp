<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>挤护套工序电子看板</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>core/css/board/sheath1.css"/>
		
  <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var urlPath = basePath.substring(7, basePath.length);
	</script>
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
					<tr class="con_tr_d">
						<td><span>计划米数：</span><b><span id="partLen"></span>m</b></td>
						<td><span>已生产米数：</span><b><span id="semiLen"></span>m</b></td>
						<td><span>轴名称：</span><b id="axis_name"></b></td>
						<td><span>生产段长：</span><b id="">未知</b></td>
					</tr>
					<tr class="con_tr_s">
						<td><span>主机速度：</span><b><span id="jht_master_speed"></span>m/s</b></td>
						<td><span>挤出压力：</span><b><span id="jht_out_pressure"></span>N</b></td>
						<td><span>火花报警：</span><b><span id="jht_spark_report"></span>次</b></td>
						<td><span>导体OD值：</span><b><span id="jht_od_value"></span>mm</b></td>
					</tr>
					<tr class="con_tr_d">
						<td><span>机头温度：</span><b><span id="jht_nose_temp"></span>&#8451;</b></td>
						<td><span>水槽温度：</span><b><span id="jht_flume_temp"></span>&#8451;</b></td>
						<td><span>劲部温度：</span><b><span id="jht_neck_temp"></span>&#8451;</b></td>
						<td><span>一段温度：</span><b><span id="jht_one_temp"></span>&#8451;</b></td>
					</tr>
					<tr class="con_tr_s">
						<td><span>二段温度：</span><b><span id="jht_two_temp"></span>&#8451;</b></td>
						<td><span>三段温度：</span><b><span id="jht_three_temp"></span>&#8451;</b></td>
						<td><span>四段温度：</span><b><span id="jht_four_temp"></span>&#8451;</b></td>
						<td><span>五段温度：</span><b><span id="jht_five_temp"></span>&#8451;</b></td>
					</tr>
					<tr class="con_tr_d">
						<td><span>六段温度：</span><b><span id="jht_six_temp"></span>&#8451;</b></td>
						<td><span>七段温度：</span><b><span id="jht_seven_temp"></span>&#8451;</b></td>
						<td><span>八段温度：</span><b><span id="jht_eight_temp"></span>&#8451;</b></td>
						<td><span>外径值：</span><b><span id="jht_outd"></span>mm</b></td>
					</tr>
					<tr class="con_tr_s">
						<td><span>收线速度：</span><b><span id="jht_take_line_speed"></span>m/s</b></td>
						<td><span>收线张力：</span><b><span id="jht_takeLine_power"></span>N</b></td>
						<td><span>放线张力：</span><b><span id="jht_layLine_power"></span>N</b></td>
						<td><span>机台耗能：</span><b><span id="">未知</span>度</b></td>
						
					</tr>
				</table>
			</div>
			
			
		</div>
		
		
		<script type="text/javascript" src="<%=basePath%>core/js/jquery-2.1.4.min.js" ></script>
		<script type="text/javascript" src="<%=basePath%>core/js/board/sheath1.js" ></script>
	</body>
</html>
