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
	<link rel="stylesheet" type="text/css" href="<%=basePath%>core/css/board/sheath2.css"/>
	 <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var urlPath = basePath.substring(7, basePath.length);
	</script>
    </head>
  	
  <body>
		
		<div id="two">
			<div class="top">
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td><span>机台：</span><b></b></td>
						<td><span>规格型号：</span><b></b></td>
						<td><span>生产令：</span><b></b></td>
						<td><span>操作人员：</span><b></b></td>
						<td><span>任务完成情况：</span><b></b>%</td>
						<td><b id="showtime"></b></td>
					</tr>
				</table>
			</div>
			
			<!--<div class="left">
				<div id="leftTop">
					
				</div>
				<div id="leftBottom">
					
				</div>
			</div>
			<div class="right">
				<div id="rightImg">
					
				</div>
			</div>-->
			<div id="rightImg">
					
			</div>
			
		</div>
		
		<script type="text/javascript" src="<%=basePath%>core/js/jquery-2.1.4.min.js" ></script>
		<script src="<%=basePath%>core/js/echarts.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath%>core/js/board/sheath2.js" ></script>
	</body>
</html>
