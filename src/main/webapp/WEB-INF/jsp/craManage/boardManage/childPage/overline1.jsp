<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>成缆工序电子看板</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=basePath %>core/css/board/strepsinema.css" />
	<script type="text/javascript">
		var basePath = "<%=basePath%>";
		var urlPirfex = basePath.substring(7, basePath.length);
	</script>
  </head>
  
  <body>
		<div id="head">
			<div class="h1" id="showtime">&nbsp;</div>
			<div class="h2">
				<table border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td class="tbh" id="">机台：</td>
						<td class="tbb">【<span id="machineName"></span>】</td>
						<td class="tbh" id="jx_type">规格型号：</td>
						<td class="tbb">【<span  id="machineType"></span>】</td>
						<td class="tbh" id="jx_task">机台任务完成情况：</td>
						<td class="tbb">【<span  id="taskPercent"></span>%】</td>
					</tr>
				</table>
			</div>
			<div class="h3">
				
			</div>
		</div>
		
		<div id="content">
			<div class="con_head">
				<h3>最新动态</h3>
				<div>工单编号：<span id="courseCode"></span></div>
				<div>操作人员：<span id="operPerson"></span></div>
				<!--<div>2017/6/21 14:00 星期三</div>-->
			</div>
			<div class="con_info">
				<h3>原料信息</h3>
				<div>材料：<span id="material"></span></div>
				<div>型号：<span id="material_type"></span></div>
				<div>数量：<span id="material_num"></span>m</div>
				<div>剩余米数：<span id="semiLen"></span>m</div>
			</div>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr class="tbtr">
					<td class="tbhead">计划米数</td>
					<td class="tbbody"><span id="partLen"></span>m</td>
					<td class="tbhead">已生产米数</td>
					<td class="tbbody"><span style="color:red"></span>m</td>
					<td class="tbhead">生产段长</td>
					<td class="tbbody"><span style="color:red"></span>m</td>
					<td class="tbhead">轴名称</td>
					<td class="tbbody"><span id="axis_name"></span></td>
				</tr>
				<tr>
					<td class="tbhead">产品型号</td>
					<td class="tbbody"><span style="color:red">无</span></td>
					<td class="tbhead">放线张力</td>
					<td class="tbbody"><span id="cl_layLine_power"></span>N</td>
					<td class="tbhead">水槽温度</td>
					<td class="tbbody"><span id="cl_flume_temp"></span>&#8451;</td>
					<td class="tbhead">一段温度</td>
					<td class="tbbody"><span id="cl_one_tem"></span>&#8451;</td>
				</tr>
				<tr class="tbtr">
					<td class="tbhead">二段温度</td>
					<td class="tbbody"><span id="cl_two_temp"></span>&#8451;</td>
					<td class="tbhead">三段温度</td>
					<td class="tbbody"><span id="cl_three_temp"></span>&#8451;</td>
					<td class="tbhead">四段温度</td>
					<td class="tbbody"><span id="cl_four_temp"></span>&#8451;</td>
					<td class="tbhead">五段温度</td>
					<td class="tbbody"><span id="cl_five_temp"></span>&#8451;</td>
				</tr>
				<tr>
					<td class="tbhead">六段温度</td>
					<td class="tbbody"><span id="cl_six_temp"></span>&#8451;</td>
					<td class="tbhead">七段温度</td>
					<td class="tbbody"><span id="cl_seven_temp"></span>&#8451;</td>
					<td class="tbhead">八段温度</td>
					<td class="tbbody"><span id="cl_eight_temp"></span>&#8451;</td>
					<td class="tbhead">线　经</td>
					<td class="tbbody"><span id="cl_line_path"></span>mm</td>
				</tr>
				<tr class="tbtr">
					<td class="tbhead">挤出压力</td>
					<td class="tbbody"><span id="cl_line_path"></span>N</td>
					<td class="tbhead">主机速度</td>
					<td class="tbbody"><span id="cl_mainframe_speed"></span>m/s</td>
					<td class="tbhead">储线张力</td>
					<td class="tbbody"><span id="cl_store_power"></span>N</td>
					<td class="tbhead">前段计米</td>
					<td class="tbbody"><span id="cl_precountMeter"></span>m</td>
				</tr>
				<tr>
					<td class="tbhead">后段计米</td>
					<td class="tbbody"><span id="cl_backcountMeter"></span>m</td>
					<td class="tbhead">机台耗能</td>
					<td class="tbbody"><span id=""></span>度</td>
				
				</tr>
				
			</table>
		</div>
		
		
		
		<script type="text/javascript" src="<%=basePath%>core/js/jquery-2.1.4.min.js" ></script>
		<script type="text/javascript" src="<%=basePath%>core/js/board/overline1.js" ></script>
		
	</body>
	
</html>
