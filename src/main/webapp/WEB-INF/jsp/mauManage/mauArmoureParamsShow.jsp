<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>机台参数展示</title><meta http-equiv="X-UA-Compatible" content="IE=9" />
	<meta http-equiv="X-UA-Compatible" content="IE=9" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
	<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<script src="<%=basePath%>core/js/echarts.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/dateUtils.js" type="text/javascript"></script>
	<script src="<%=basePath%>app/js/mauManage/mauArmoureParamsShow.js" type="text/javascript"></script>
	<style>
		#paramstable{
			width: 100%;
			text-align: center;
			height: 240px;
		}
		.tr001{
			font-size: 24px;
		    color: #FFFF00;
		    text-align: left;
		}
		.tr002{
			font-size: 24px;
		    color: #66FF00;
		    text-align: left;
		}
		.tr003{
			 font-size: 20px;
   			 color: #FFFF00;
   			 text-align: left;
		}
		.tr004{
			 font-size: 20px;
   			 color: #FFFFFF;
   			 text-align: left;
		}
		#callRepair{
			background-color:#FF0000
		}
		#rtext{
			    color:#FFFFFF;
		}
	</style>
  </head>
   <body>
  		<table id="paramstable">
			<tr>
				<td class="tr001"></td>
				<td class="tr001">机台：</td>
				<td class="tr001">SA001 </td>
				<td class="tr001">规格型号：</td>
				<td class="tr001">SECF-1203</td>
				<td class="tr001">机台完成情况：</td>
				<td class="tr001">50%</td>
				<td></td>
			</tr>
			<tr>
				<td class="tr002">工单编号：</td>
				<td class="tr002">GD17042501</td>
				<td></td>
				<td class="tr002">操作人员：</td>
				<td class="tr002">张云溪</td>
				<td></td>
				<td class="tr002">日期时间：</td>
				<td class="tr002" id="timeShow"></td>
			</tr>
			<tr>
				<td class="tr004">材料：</td>
				<td class="tr003">铜线</td>
				<td class="tr004">型号：</td>
				<td class="tr003">HUKD/VP-009</td>
				<td class="tr004">数量：</td>
				<td class="tr003">10000m</td>
				<td class="tr004">剩余米数：</td>
				<td class="tr003">2000m</td>
			</tr>
			<tr>
				<td class="tr004">生产米数：</td>
				<td class="tr003">10000m</td>
				<td class="tr004">已生产米数：</td>
				<td class="tr003">5000m</td>
				<td class="tr004">轴名称：</td>
				<td class="tr003">GD170-001</td>
				<td class="tr004">生产段长：</td>
				<td class="tr003">0.6*6+0.5</td>
			</tr>
			<tr>
				<td class="tr004">目标米数：</td>
				<td class="tr003">550m</td>
				<td class="tr004">产品型号：</td>
				<td class="tr003">4*3mm2</td>
				<td class="tr004">拉丝速度：</td>
				<td class="tr003">20m/s：</td>
				<td class="tr004">收线张力：</td>
				<td class="tr003">50N</td>
			</tr>
			<tr>
				<td class="tr004">放线张力：</td>
				<td class="tr003">50N</td>
				<td class="tr004">主机速度：</td>
				<td class="tr003">20m/s</td>
				<td class="tr004">退火温度：</td>
				<td class="tr003">20℃</td>
			</tr>
		</table>
  </body>
</html>

