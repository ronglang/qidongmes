<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工位电子看板</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="<%=basePath%>core/js/dateUtils.js" type="text/javascript"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style >
	<style type="text/css">
				.nav{
				height: 80px;
				background: aqua;
				line-height: 50px;
			}
			.img{
				width:20%;
				height: 198px;
				float: right;
				background: red;
				border-radius:0 30px 0 0;
			}
			.top-a{
				height: 127px;
				line-height: 80px;
				background: blue;
			}
			.main{
				width: 100%;
				float: left;
				line-height: 80px;
				background: aqua;
				height: 450px;
			}
			.nav-a{
				width: 33%;
				height: 60px;
				text-align: center;
				float: left;
				
			}
			.span-a{
				font-size:20px;
				font-family: "微软雅黑";
				font-weight: 300;
			}
			.top-a-a{
				width:230px;
				float: left;
				height: 60px;
				text-align: center;
			}
			.main-a{
				float: left;
				width: 11%;
				height: 450px;
				
			}
			.main-b{
				float: left;
				width: 88%;
				height: 450px;
				
			}
			.main-b-a{
				float: left;
				
				width: 19%;
				margin-left:5px ;
				height: 100px;
			}
			.top-a-2{
				float: left;
				width: 68%;
				height: 120px;
			}
			.nav-a-2{
				float: left;
				width: 68%;
				height: 80px;
			}
	
	
	
			.ta{
				width: 280px;
				height: 80px;
				text-align: center;
				float: left;
				line-height: 70px;
			}
			.tb{
				width: 30%;
				height: 80px;
				float: right;
				text-align: right;
				line-height: 70px;
			}
			 
		</style>
		<script type="text/javascript">
			setInterval(function(){
				setTime();
			},1000);
			
			function setTime(){
				var date = new Date();
				var dates=date.pattern("yyyy-MM-dd EEE hh:mm:ss");
				$("#nowTime").html(dates);
			}
			
			
		</script>
	</style>
	<script type="text/javascript">
		setInterval(function(){
				setTime();
			},1000);
			
			function setTime(){
				var date = new Date();      
			 	var dateS =  date.pattern("yyyy-MM-dd EEE hh:mm:ss");
			 	 $("#nowTime").html(dateS);
			}
			
		</script>
  </head>
  <body>
  		<div  style="width: 100%;height: 80px;background: blue;float:left;">
			<div class="ta"><span class="span-a" >机台编号：SA-001</span></div>
			<div class="ta"><span class="span-a" >工序：绞线</span></div>
			<div class="ta"><span class="span-a" >机台规格型号：7+2框绞机</span></div>
			<div class="tb"><span class="span-a" >时间：<span id="nowTime"></span></span></div>
		</div>
			<div class="img"><img src="#" alt="没有头像"></div>
			<div style="width:80%;float: left;height: 200px;padding: 0px;margin: 0px;">
			<iframe  style="width: 100%;height: 199px;border: 0;padding: 0px;"  scrolling="no" src="<%=basePath%>rest/mauWorkStationManageAction/toMauWorkStationOneShow"></iframe>
		</div>
		<div class="main">
			<iframe style="width: 100%;height: 450px;float: left;border: 0;"  scrolling="no" src="<%=basePath%>rest/mauWorkStationManageAction/toMauWorkStationTwoShow"></iframe>
		</div>
		
  </body>
</html>
