<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>大拉机</title>
    
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
		<div id="contentimg" style="width:100%;">
			<div class="lefts" style="width:80%;margin: 0 auto;background-color: #224B81;border-radius:15px;">
				<div class="divbottom" id="bottomimg" style="width:80%; height: 350px; margin:0 auto;margin-top: 9px;">
					
				</div>
			</div>
		</div>
		
		<script type="text/javascript" src="<%=basePath%>core/js/jquery-2.1.4.min.js" ></script>
		<script type="text/javascript" src="<%=basePath%>core/js/echarts.min.js" ></script>
		<script type="text/javascript" src="<%=basePath%>core/js/board/strepsinema2.js" ></script>
		
	</body>
</html>
