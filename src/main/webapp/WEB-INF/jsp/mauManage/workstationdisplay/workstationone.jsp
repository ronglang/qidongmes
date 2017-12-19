<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>机台情况展示</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="呼叫叉车,新鲜事时间">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	
	-->
	<style type="text/css">
	.nav{
				height: 80px;
				background: aqua;
				line-height: 50px;
				border-radius:30px 0 0 0;
			}
			.img{
				width:20%;
				height: 198px;
				float: right;
				background: red;
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
	</style>
	<script src="<%=basePath%>core/js/jquery-1.11.1.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/dateUtils.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(function(){
		$.ajax({
			type:"post",
			url:basePath+"rest/mauWorkStationManageAction/getMessageOne",
			dataType:"json",
			async: false,  
			 error: function(request) {  
			        alert("Connection error");  
			    },  
			    success: function(data) {  
			        //接收后台返回的结果  
			        
			        alert(data);
			        $("#maupart").val(data.maupart);
			        $("#completeRate").val(data.completeRate);
			        $("#remainTime").val(data.remainTime);
			        $("#ppgx").val(data.ppgx);
			        $("#color").val(data.color);
			        $("#remainTime").val(data.amount);
			        $("#ppgx").val(data.courseCode);
			        $("#color").val(data.endTime);
			    }  
			
		});
	});
	
	
	</script>
  </head>
  
 <body>
 		<div class="nav">
				<div class="nav-a-1" style="float: left;width: 13%;height: 80px;">
				<span class="span-a" >机台生产情况：</span>
				</div>
				<div class="nav-a-2">
				<div class="nav-a"><span class="span-a" >生产米数：<span id="maupart"></span></span></div>
				<div  class="nav-a"><span class="span-a" >机台完成情况：<span id="completeRate"></span></span></div>
				<div  class="nav-a"><span class="span-a" >剩余完成时间：<span id="remainTime"></span></span></div>
				</div>
			</div>
		<div class="top-a" >
		<div class="top-a-1" style="float: left;width: 14%;height: 120px;">
				<span style="font-size: 17px">正在生产的工单：</span></div>
			<div class="top-a-2" >
			<div class="top-a-a"><span class="span-a"  >规格：<span id="ppgx"></span></span></div>
			<div class="top-a-a"><span class="span-a" >颜色：<span id="color"></span></span></div>
			<div class="top-a-a"><span class="span-a" >数量：<span id="amount"></span></span></div>
			<div class="top-a-a"><span class="span-a" >工单编号：<span id="courseCode"></span></span></div>
			<div class="top-a-a"><span class="span-a" >交货日期：<span id="endTime"></span></span></div>
			</div>
	</div>
	
	</body>
</html>
