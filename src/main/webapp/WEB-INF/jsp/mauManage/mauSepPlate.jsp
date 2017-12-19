<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>分盘</title><meta http-equiv="X-UA-Compatible" content="IE=9" />
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
	<script src="<%=basePath%>app/js/mauManage/mauSepPlate.js" type="text/javascript"></script>
	<script>
		var basePath  = '<%=basePath%>';  
    </script>
	<style>
		body{
			background-color:#0066FF;
		}
		#paramstable{
			width: 100%;
			text-align: center;
			height: 240px;
		}
		.tr001{
		    font-size: 30px;
   			color: #FFFF00;
   			text-align: left;
		}
		.tr0011{
		    font-size: 30px;
   			color: #FFFF00;
   			text-align: center;
		}
		.tr002{
		    font-size: 24px;
   			color: #66FF00;
		    text-align: left;
		}
		.tr003{
			 font-size: 28px;
    		 color: #FFFFFF;
   			 text-align: left;
		}
		.tr0031{
			 font-size: 20px;
   			 color: #FFFF00;
   			 text-align: left;
		}
		
	</style>
  </head>
   <body>
  		<table id="paramstable">
			<tr>
				<td class="tr001"></td>
				<td class="tr0011">机台：</td>
				<td class="tr001" id="macCode">YUJ001 </td>
				<td class="tr0011">规格：</td>
				<td class="tr001" id="ggxh">SECF-1203</td>
				<td class="tr0011">机台完成情况：</td>
				<td class="tr001" id="completeState">80%</td>
				<td></td>
			</tr>
			<tr>
				<td class="tr002">工单编号：</td>
				<td class="tr002" id="courseCode">GD17042501</td>
				<td></td>
				<td class="tr002">操作人员：</td>
				<td class="tr002" id="oprName">刘云</td>
				<td></td>
				<td class="tr002">日期时间：</td>
				<td class="tr002" id="timeShow"></td>
			</tr>
			<tr>
				<td class="tr003">每盘米数：</td>
				<td class="tr0031" id="patLength">1000M</td>
				<td class="tr003"></td>
				<td class="tr003">分轴数量：</td>
				<td class="tr0031" id="axisNums">10轴</td>
				<td class="tr003"></td>
				<td class="tr003">完成轴数：</td>
				<td class="tr0031" id="completeAxisNums">5轴</td>
				
			</tr>
			<tr>
				<td class="tr003">正在分轴轴名称：</td>
				<td class="tr0031" id="axisName">GD17042501-001</td>
				<td></td>
				<td class="tr003">收线长度：</td>
				<td class="tr0031" id="semiProductLen">600米</td>
				<td></td>
				<td class="tr003">生产现状：</td>
				<td class="tr0031" id="productState">生产中</td>
			</tr>
		</table>
  </body>
</html>

