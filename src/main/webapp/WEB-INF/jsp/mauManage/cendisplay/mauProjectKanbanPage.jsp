<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工程部电子看板</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="<%=basePath%>core/img/logos.png">
	
	<script src="<%=basePath %>core/js/jquery-1.4.4.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>core/js/echarts.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>core/js/cssBase.js" type="text/javascript" charset="utf-8"></script>
		
		<script type="text/javascript">
		var basePath = "<%=basePath%>";
		var urlPirfex = basePath.substring(7, basePath.length);
			$(function(){
				
				$("#times").showDate();
				
			});
			
		</script>
		<style type="text/css">
			*{
				margin: 0;
				padding: 0;
				font-size: 13px;
			}
			body{
				background-color: #3267AB;
			}
			#top{
				height: 60px;
				line-height: 60px;
				background-color: #3F77C1 ;
				border-bottom: 2px solid #1E4785;
			}
			#top .t_center{
				height: 60px;
				width: 56%;
				color: white;
				font-size: 25px;
				text-align: right;
				letter-spacing: 5px;
				float: left;
			}
			#top .t_time{
				width: 44%;
				height: 60px;
				float: left;
				color: white;
				text-align: right;
			}
			#top .t_time span{
				display: inline-block;
				margin-top: 10px;
				font-size: 16px;
				margin-right: 5%;
			}
			
			ul{
				list-style-type: none;
			}
			#main{
				width:100%;
			}
			#main .m_left{
				width: 50%;
				float: left;
			}
			
			#main .m_right{
				width: 50%;
				float: left;
			}
			
		</style>

  </head>
  
  <body>
    
    <div id="top">
			<div class="t_center">
				工程部电子看板
			</div>
			<div class="t_time">
				<span id="times"></span>
			</div>
		</div>
		<div id="callCar">
			<iframe src="<%=basePath%>rest/mauCallForkliftRecordManageAction/toCallCar?requestType=mapuser" width="100%" height="60px" frameborder="0" scrolling="no" class=""></iframe>
		</div>
		<div id="main">
			<div class="m_left">
				<iframe src="<%=basePath%>rest/mauMachineFaultManageAction/toProjectMaintain?requestType=mapuser" width="100%" height="100%" frameborder="0" scrolling="no" class=""></iframe>
			</div>
			<div class="m_right">
				<iframe src="<%=basePath%>rest/mauMachineFaultManageAction/toProjectFault?requestType=mapuser" width="100%" height="100%" frameborder="0" scrolling="no" class=""></iframe>
			</div>
		</div>
    
    
  </body>
</html>
