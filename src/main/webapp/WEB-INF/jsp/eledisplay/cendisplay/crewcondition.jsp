<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>机台情况</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="<%=basePath%>core/js/jquery-1.11.1.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
  	<style>
		ul{
			height: 100px;
		}
		ul .aa {
			position: relative;
			float: left;
			font-size: xx-large;
			overflow: hidden;
			height: 50px;
			width: 24%;
			margin-left: 5px;
			padding-top: 15px;
			font-family: YouYuan;
			font-weight: 900;
			color: black;
			border: 1px solid blueviolet;
		}
		.aa div{
			position: relative;
		}
		.dd{
			position: absolute;
			float: right;
			margin-right: 34%;
			position: absolute;
			width: 25%;
			font-family: "宋体";
			font-size: xx-large;
			
		}
	</style>
	<script type="text/javascript">
		//查询当前状态
		function queryNow(){
			$.post(url,{},function(data){
				load(data);
			},"json");
		}
		//加载参数
		load(data){
			$("#isopen").val();
			$("#isclose").val();
			$("#isall").val();
			$("#iserror").val();
		}
		
		//websocket
		function websockt(){
			var basePath = "<%=basePath%>";
			var urlPirfex = basePath.substring(7, basePath.length);
			var url = "ws://"+urlPirfex+"cenCrewWebS";
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
			//这里获得反馈的值,进行参数的赋值
			function onMessage(event) {
				//在此处将数据封装到页面
				var josnBean = eval('('+event.data+')');
				//PLC参数
				load(josnBean);
			}
			//传的参数
			function onOpen(event) { 
				//TODO 发送房间号到后台去,获取房间号
				//webSocket.send('${id}');
				webSocket.send('201');
//	 			document.getElementById('data').innerHTML = '<br />' + ""+"start";
			}
		
			function onError(event) {
		// 		alert(event.data);
			}
		}
	</script> 
  </head>
  
  <body>
    <div style="width: 100%;">
			<ul>
				<li class="aa"><div>机台总数:<div id="all" class="dd">1</div></div></li>
				<li class="aa"><div>&nbsp;&nbsp;开机数:<div id="isopen" class="dd">2</div></div></li>
				<li class="aa"><div>&nbsp;&nbsp;停机数:<div id="isclose" class="dd">3</div></div></li>
				<li class="aa"><div>&nbsp;&nbsp;故障数:<div id="iserror" class="dd">4</div></div></li>
			</ul>
		</div>
  </body>
</html>
