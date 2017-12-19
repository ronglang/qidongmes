<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>铠装工序电子看板</title>
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<style>
			body{
				background-color: #0066FF;
			}
			#params{
				height: 240px;
				
			}
			#charts{
				margin-top:30px;
			}
			
			#PShow{
				width: 100%;
				height: 100%;
			}
			#HSTest{
				height: 300px;
				width: 45%;
				margin-left: 2%;
				background-color: #FFFFFF;
			}
			#MP{
				width: 70%;
				height: 300px;
				margin-left: 15%;
				background-color: #FFFFFF;
			}
			.ltext{
				font-size:20px;
				margin-left:30px;
			}
			#tt{
				margin-left:0px;
			}
			#ttt{
				margin-left:0px;
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
  	width: 100%;
  	text-align: center;
  	background-color: #E65600;
  	color: yellow;
  	font-size: 25px;
  	font-weight: bolder;
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
  	width: 25%;
  	display: block;
  	background-color: #F0F0F0;
  	float: left;
  	color: blue;
  	font-weight: bold;
  }
  #exception ul li b{
  	width: 73%;
  	float: left;
  	display: block;
  }
  #meInfo{
  	font-size: 12px;
  	line-height: 23px;
  	text-align: left;
  	margin-left: 5px;
  }
		</style>
		<script>
			var basePath = "<%=basePath%>";
			var urlPath = basePath.substring(7, basePath.length);
    	</script>
  </head> 
  <body>
   		<div id="params">
			<iframe id="PShow" name="PShow" src="<%=basePath%>rest/armourboard/armourone?requestType=mapuser"></iframe>
		</div>
		<div id="charts">
			<!-- 
			<iframe id="HSTest" src="<%=basePath%>rest/testWireDrawingAction/toHostSpeedTest"></iframe>
			 -->
			<iframe id="MP" src="<%=basePath%>rest/armourboard/armourtwo?requestType=mapuser"></iframe>
		</div>
		<div id="exception">
 				<h3>异常警告</h3>
 				<div class="content">
 					<ul>
 						<li><span>异常时间</span><b id="meTime"></b></li>
 						<li><span>机台名称</span><b id="machineName"></b></li>
 						<li><span>轴名称</span><b id="axisName"></b></li>
 						<li style="height: 52px;line-height: 52px;"><span>异常信息</span>
 							<b id="meInfo"></b></li>
 					</ul>
 				</div>
 		</div>
 		<script src="<%=basePath%>core/js/echarts.min.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
 		<script type="text/javascript">
			$(document).ready(function(){
				
				
				$("#exception").hide();
				var leng = $("#meInfo").html().toString().length;
				var str = $("#meInfo").html();
				if(leng >= 40){
					$("#meInfo").html(str.substring(0,38)+"......");
				}
				
				webSockets();
				
			});
			
			function webSockets(){
				
				var websocket = null;  
			    //判断当前浏览器是否支持WebSocket  
			    if ('WebSocket' in window) {
					var url = "ws://"+urlPath+"KZExceptionWebSocket";
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
			       // 关闭 websocket
			    };
				
				
			}
			
		</script>
 		
  </body>
</html>
