<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<link rel="shortcut icon" href="<%=basePath%>core/img/logos.png">
	
	<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/echarts.min.js"  type="text/javascript"></script>
	<script src="<%=basePath%>core/js/cssBase.js"  type="text/javascript"></script>
	
	<script type="text/javascript">
			$(function(){
				
				$("#times").showDate();
				
				$("#exception").hide();
				
				var leng = $("#meInfo").html().toString().length;
				var str = $("#meInfo").html();
				if(leng >= 40){
					$("#meInfo").html(str.substring(0,38)+"......");
				}

				//通知滚动
				$("#head .h_con").marquee({
					isMarquee:true,
					isEqual:false,
					scrollDelay:50,
					direction:'left'
				});
				
				var intDiff = parseInt(60);//倒计时总秒数量
				timer(intDiff);
				
				//webSockets();
				
			});
			
			
			//倒计时
			function timer(intDiff){
			    window.setInterval(function(){
			    var day=0,
			        hour=0,
			        minute=0,
			        second=0;//时间默认值        
			    if(intDiff > 0){
			        day = Math.floor(intDiff / (60 * 60 * 24));
			        hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
			        minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
			        second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
			    }
			    if (minute <= 9) minute = '0' + minute;
			    if (second <= 9) second = '0' + second;
			    
			    $('#exception h3 span.t2').html(minute+":"+second);
			    if(minute == '00' && second == '00'){
			    	$("#exception").css("display","none");
			    }
			    intDiff--;
			    }, 1000);
			} 
			function webSockets(){
				
				var websocket = null;  
			    //判断当前浏览器是否支持WebSocket  
			    if ('WebSocket' in window) {
					var url = "ws://"+urlPath+"LSExceptionWebSocket";
			        websocket = new WebSocket(url);
			    }  
			    else {
			        alert('当前浏览器不支持websocket，请更换浏览器！');  
			    }
			    websocket.onopen = function()
			    {
			       // Web Socket 已连接上，使用 send() 方法发送数据
			    	websocket.send("警告框");
			    };
					
			    websocket.onmessage = function (evt) 
			    { 
			       var map = eval('('+evt.data+')');
			       //debugger;
			       if(map.state == "未处理"){
			    	   $("#exception").show();
			    	   for(var key in map){
				    	   var a = "#"+key;
				    	   $(a).html(map[key]);
				       }
			       }else{
			    	   $("#exception").hide();
			       }
			       
			    };
					
			    websocket.onclose = function()
			    { 
			    };
				
			}
			
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
			
			#head{
				width:98%;
				height:50px;
				background-color:#224B81;
				margin:10px auto;
				border-radius:10px;
			}
			#head .h_con{
				width:100%;
				height:50px;
				overflow:hidden;
				margin:0 auto;
			}
			#head .h_con ul{
				width:100%;
				height:50px;
				margin:0 auto;
			}
			#head .h_con ul li{
				height:50px;
				line-height: 50px;
				float: left;
				margin-left:50px;
			}
			#head .h_con ul li span{
				margin-left:20px;
				color:white;
				font-size:14px;
			}
			
			#main{
				
			}
			#main .machineNum{
				width: 100%;
				height: 170px;
				margin: 0 auto;
				
			}
			
			#main .mt_right{
				margin: 0 auto;
				width: 100%;
				height: 570px;
			}
			
			
			#exception{
			  	width: 350px;
			  	height: 200px;
			  	background-color: #FCDDC8;
			  	position: fixed;
			  	z-index: 9999;
			  	bottom: 0;
			  	right: 0;
			  	border: 1px solid red;
			  }
			  #exception h3{
			  	margin: 0;
			  	padding: 0;
			  	height: 35px;
			  	line-height: 35px;
			  	background-color: #E65600;
			  	position: relative;
			  }
			  #exception h3 span.t1{
			    text-align: center;
			  	color: yellow;
			  	font-size: 25px;
			  	font-weight: bolder;
			  	width: 100%;
			  	display: inline-block;
			  }
			  #exception h3 span.t2{
			  	position: absolute;
			  	right:5px;
			  	top:0px;
			  	font-size: 16px;
			  }
			  #exception ul{
			  	list-style-type: none;
			  	margin: 0;
			  	padding: 0;
			  }
			  #exception ul li{
			  	text-align: center;
			  	border: 1px solid black;
			  	height: 35px;
			  	line-height: 35px;
			  	width: 100%;
			  	margin: 0;
			  	padding: 0;
			  }
			  #exception ul li span{
			  	width: 20%;
			  	display: block;
			  	background-color: #F0F0F0;
			  	float: left;
			  	color: blue;
			  	font-weight: bold;
			  	text-align: right;
			  }
			  #exception ul li b{
			  	width: 73%;
			  	float: left;
			  	display: block;
			  	text-align: left;
			  	text-indent: 10px;
			  }
			  #meInfo{
			  	font-size: 12px;
			  	line-height: 23px;
			  	text-align: left;
			  	margin-left: 5px;
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
		<!-- 
		<div id="head">
			
		</div>
		 -->
		<div id="main">
			<div class="machineNum">
				<iframe src="<%=basePath%>rest/mauNewDislayManageAction/toDisplayMachineNum?requestType=mapuser" width="100%" height="100%" frameborder="0" scrolling="no" class=""></iframe>
			</div>
			
			<div class="taskImg">
				<div class="mt_right">
					<iframe src="<%=basePath%>rest/mauNewDislayManageAction/macProductInfo?requestType=mapuser" width="100%" height="100%" frameborder="0" scrolling="no" class=""></iframe>
				</div>
			</div>
		</div>
  		
  		<div id="exception">
 				<h3><span class="t1">异常警告</span><span class="t2"></span></h3>
 				<div class="content">
 					<ul>
 						<li><span>异常时间:</span><b id="meTime"></b></li>
 						<li><span>机台名称:</span><b id="machineName"></b></li>
 						<li><span>轴名称:</span><b id="axisName"></b></li>
 						<li style="height: 52px;line-height: 52px;"><span>异常信息:</span>
 							<b id="meInfo"></b></li>
 					</ul>
 				</div>
 		</div>
  		
  </body>
</html>
