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
    
    <title>车间电子看板</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
		<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>core/js/echarts.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>core/js/kxbdSuperMarquee.js" type="text/javascript" charset="utf-8"></script>
		
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
			
			
			#main .m_left{
				float: left;
				width: 20%;
			}
			/*
			#main .m_left .ml_one,#main .m_left .ml_three,#main .m_left .ml_two,#main .m_left .ml_four {
				width: 250px;
				height: 130px;
				border-radius:5px;
				background-color: #427DC3;
				margin: 20px auto;
			}
			#main .m_left label{
				text-align: center;
			}
			#main .m_left label{
				display: block;
				height: 50%;
			}
			#main .m_left label.mlo_one{
				
			}
			
			#main .m_left label.mlo_one b,#main .m_left label.mlo_one span{
			}
			#main .m_left label.mlo_one b{
				line-height: 100px;
				font-size: 60px;
				color: yellow;
				font-weight: normal;
			}
			#main .m_left label.mlo_one span{
				color: yellow;
				font-size: 30px;
			}
			#main .m_left label.mlo_one span{
			}
			#main .m_left label.mlo_two{
				line-height: 50px;
				font-size: 20px;
				color: white;
			}
			*/
			
			#main .m_right{
				width: 80%;
				float: left;
			}
			#main .m_right .mr_top{
				width: 100%;
				height: 280px;
				
			}
			#main .m_right .mrm{
				background-color: #224B81;
				width: 95%;
				margin: 20px auto;
				border-radius: 5px;
				height: 280px;
			}
			#main .m_right .mr_bottom{
				background-color: green;
				width: 100%;
				margin: 10px auto;
			}
			#main .m_right .mr_bottom .mrb_left{
				float: left;
				width: 46%;
				border-radius: 5px;
				background-color: #224B81;
				margin-left: 2%;
				height: 290px;
			}
			
			#main .m_right .mr_bottom .mrb_right{
				float: left;
				width: 46%;
				border-radius: 5px;
				background-color: #224B81;
				margin-left: 2%;
				height: 290px;
			}
			#main .m_right .mr_bottom .mrb_right .exception{
				width: 100%;
				height: 280px;
				margin: 0 auto;
				border-radius: 5px;
			}
			iframe{
				border: none;
			}
			
			ul {list-style:none;}
			
			
		</style>
		<script type="text/javascript">
		var basePath = "<%=basePath%>";
		var urlPirfex = basePath.substring(7, basePath.length);
			$(function(){
				
				$("#times").html(showTime());
				setInterval(function(){
					$("#times").html(showTime());
				},1000);
				
				
			});
			
			function showTime(){ 
			    var show_day=new Array('星期日','星期一','星期二','星期三','星期四','星期五','星期六'); 
			    var time=new Date(); 
			    var year=time.getFullYear();
			    var month=time.getMonth();
			    var date=time.getDate(); 
			    var day=time.getDay(); 
			    var hour=time.getHours(); 
			    var minutes=time.getMinutes(); 
			    var second=time.getSeconds(); 
			    month=parseInt(month)+1;
			    month<10?month='0'+month:month;
			    hour<10?hour='0'+hour:hour; 
			    minutes<10?minutes='0'+minutes:minutes; 
			    second<10?second='0'+second:second; 
			    var now_time=year+'/'+month+'/'+date+'　'+hour+':'+minutes+':'+second+'　'+show_day[day]; 
			    
			    return now_time;
			} 
		</script>
  </head>
  
  <body>
   	  
   	  <div id="top">
			<div class="t_center">
				车间电子看板
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
				<iframe src="<%=basePath%>rest/mauDisplayAction/toDisplayMachineNum?requestType=mapuser" width="100%" height="100%" frameborder="0" scrolling="no" class=""></iframe>
			</div>
			<div class="m_right">
				<div class="mrm">
					<iframe src="<%=basePath%>rest/mauDisplayAction/toMauDisplayProductSchedule?requestType=mapuser" width="100%" height="100%" frameborder="0" scrolling="no" class=""></iframe>
				</div>
				
				<div class="mr_bottom">
					<div class="mrb_left">
						<iframe src="<%=basePath%>rest/mauDisplayAction/toMauDisplayError?requestType=mapuser" width="100%" height="100%" frameborder="0" scrolling="no" class=""></iframe>
					</div>
					<div class="mrb_right">
						<iframe src="<%=basePath%>rest/mauDisplayAction/toMauDisplayException?requestType=mapuser" width="100%" height="100%" frameborder="0" scrolling="no" class=""></iframe>
					</div>
				</div>
			</div>
		</div>
   	  
  </body>
</html>
