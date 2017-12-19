<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>生产部电子看板</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
		<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>core/js/echarts.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript" charset="utf-8"></script>
		
		<script type="text/javascript">
		var basePath = "<%=basePath%>";
		var urlPirfex = basePath.substring(7, basePath.length);
			$(function(){
				
				$("#times").showDate();
				
				/*
				$("#oee").css("display" ,"none");
				$("#main").css("display","block");
				
				setInterval(function(){
					if($("#oee").css("display") == "none"){
						$("#main").css("display" ,"none");
						$("#oee").css("display","block");
					}else{
						$("#main").css("display" ,"block");
						$("#oee").css("display","none");
					}
				}, 10000);
				*/
				
				
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
			
			
			iframe{
				border: none;
			}
			
			ul {list-style:none;}
			
			
			#main{
			}
			#main .machineNum{
				width: 100%;
				height: 170px;
				margin: 0 auto;
				
			}
			#main .mt_left{
				width: 32%;
				height: 470px;
				display: inline-block;
				float: left;
			}
			#main .mt_right{
				width: 68%;
				height: 470px;
				display: inline-block;
				float: left;
			}
			
			#oee{
				margin: 20px auto;
				display: none;
			}
			#oee .oe_left{
				
				width: 50%;
				height: 520px;
				display: inline-block;
				float: left;
			}
			#oee .oe_right{
				width: 50%;
				height: 520px;
				display: inline-block;
				float: left;
			}
		</style>
	

  </head>
  
  <body>
  		
  	<div id="top">
			<div class="t_center">
				生产部电子看板
			</div>
			<div class="t_time">
				<span id="times"></span>
			</div>
		</div>
		<div id="callCar">
			<iframe src="<%=basePath%>rest/mauCallForkliftRecordManageAction/toCallCar?requestType=mapuser" width="100%" height="60px" frameborder="0" scrolling="no" class=""></iframe>
		</div>
		<div id="main">
			<div class="machineNum">
				<iframe src="<%=basePath%>rest/mauProductOrderRecordManageAction/productMachineNum?requestType=mapuser" width="100%" height="100%" frameborder="0" scrolling="no" class=""></iframe>
			</div>
			
			<div class="taskImg">
				<div class="mt_left">
					<iframe src="<%=basePath%>rest/mauProductOrderRecordManageAction/productInfo?requestType=mapuser" width="100%" height="100%" frameborder="0" scrolling="no" class=""></iframe>
				</div>
				<div class="mt_right">
					<iframe src="<%=basePath%>rest/mauProductOrderRecordManageAction/productSchedule?requestType=mapuser" width="100%" height="100%" frameborder="0" scrolling="no" class=""></iframe>
				</div>
			</div>
		</div>
		<div id="oee">
			<div class="oe_left">
				<iframe src="<%=basePath%>rest/mauProductOrderRecordManageAction/productOeeTimeLeft?requestType=mapuser" width="100%" height="100%" frameborder="0" scrolling="no" class=""></iframe>
			</div>
			<div class="oe_right">
				<iframe src="<%=basePath%>rest/mauProductOrderRecordManageAction/productOeeTimeRight?requestType=mapuser" width="100%" height="100%" frameborder="0" scrolling="no" class=""></iframe>
			</div>
		</div>
  		
  		
  		
  		
  </body>
</html>
