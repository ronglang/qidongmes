<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>绞线工序电子看板</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>core/css/board/strepsinema2.css"/>
	<script type="text/javascript">
		var basePath = "<%=basePath%>";
		var urlPath = basePath.substring(7, basePath.length);
	</script>
  </head>
  
  <body>
			<!-- 
		<div id="head">
		<div class="h1" id="showtime">&nbsp;</div>
			<div class="h2">
				<table border="0" cellpadding="0" cellspacing="0" align="center">
					<tr style="height: 80px;color:white;font-size: 22px">
					 
						<td class="tbh">机台：</td>
						<td class="tbb">【PIJ001】</td>
						<td class="tbh">规格型号：</td>
						<td class="tbb">【630-1250】</td>
						<td class="tbh">机台任务完成情况：</td>
						<td class="tbb">【70%】</td>
					
					 <td>【<span id="machineName" style="color:yellow"></span>】工单任务完成情况</td> 
					</tr>
				</table>
			  </div>
			
			<div class="h3">
				&nbsp;
			</div>
		
		</div>
		-->
		<div id="contentimg" style="width:100%;">
			<div class="lefts" style="width:80%;margin: 0 auto;background-color: #224B81;border-radius:15px;">
				<!--
				<div id="topimg" style="width: 550px; height: 290px; margin:0 auto;margin-top: 9px;">
					
				</div>
				-->
				<div class="divbottom" id="bottomimg" style="width:80%; height: 350px; margin:0 auto;margin-top: 9px;">
					
				</div>
			</div>
			<!--  
			<div id="right" style="width: 750px;height:450px;margin:0 auto;margin-top: 7px;">
				
			</div>
			-->
		</div>
		
		<script type="text/javascript" src="<%=basePath%>core/js/jquery-2.1.4.min.js" ></script>
		<script type="text/javascript" src="<%=basePath%>core/js/echarts.min.js" ></script>
		<script type="text/javascript" src="<%=basePath%>core/js/board/strepsinema2.js" ></script>
		
	</body>
</html>
