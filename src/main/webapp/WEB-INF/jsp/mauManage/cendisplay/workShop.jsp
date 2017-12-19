<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<link rel="shortcut icon" href="<%=basePath%>core/img/logos.png">
	
	<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript" charset="utf-8"></script>
	
	<script type="text/javascript">
		var basePath = "<%=basePath%>";
		var urlPirfex = basePath.substring(7, basePath.length);
		$(function(){
			
			myWebsockt();
			
		});
		var index = 0;
		function myWebsockt(){
			
			var url = "ws://"+urlPirfex+"sysNoticeToWorkShopWebSocket";
			var webSocket = new WebSocket(url);
			webSocket.onerror = function(event) {
				onError(event);
			};
		
			webSocket.onopen = function(event) {
				onOpen(event);
			};
		
			webSocket.onmessage = function(event) {
				onMessage(event);
			};
			
			webSocket.onclose = function(event) { 
				
			};
			//这里获得反馈的值,进行参数的赋值
			function onMessage(event) {
				//在此处将数据封装到页面
				var datas = eval('('+event.data+')'); 
				
				if(datas == "0"){
					$("#one").css("display","block");
					$("#two").css("display","none");
				}else{
					if($("#one").css("display") == "none"){
						$("#one").css("display","block");
						$("#two").css("display","none");
					}else{
						$("#one").css("display","none");
						$("#two").css("display","block");
						$("#two .wel_con .con").html(datas.value);
					}
				}
				
			}
			//传的参数
			function onOpen(event) { 
				webSocket.send('电子看板消息通知');
			}
		}
	
	</script>
	<style type="text/css">
	body{
		margin: 0;
		padding: 0;
		background-color: #3267AB;
	}
		#one{
			display: block;
		}
		#two{
			display: none;
		}
		#welcome{
				width: 100%;
				margin-top: 100px;
			}
			
			#welcome .wel_con{
				font-size: 40px;
				font-weight: bold;
				background-color: #224B81;
				height: 500px;
				border-radius: 20px;
				margin: 50px auto;
				width: 80%;
				
			}
			#welcome .wel_con h3{
				letter-spacing: 5px;
				color: white;
				text-align: center;
				height: 50px;
				line-height: 50px;
				font-size: 30px;
				border-top-left-radius: 20px;
				border-top-right-radius: 20px;
				background-color: #427DC3;
			}
			#welcome .wel_con .con{
				color: yellow;
				font-size: 50px;
				height: 350px;
				width: 80%;
				margin: 20px auto;
				text-indent: 2em;
			}
	</style>
  </head>
  
  <body>
    
    <div id="one">
    	<iframe src="<%=basePath%>rest/mauNewDislayManageAction/workShopList?requestType=mapuser" width="100%" height="100%" frameborder="0" scrolling="no" class=""></iframe>
    </div>
    
    <div id="two">
    	<div id="welcome">
			<div class="wel_con">
				<h3>重要消息通知</h3>
				<div class="con">
					
				</div>
				
			</div>
		</div>
    </div>
     
    
  </body>
</html>
