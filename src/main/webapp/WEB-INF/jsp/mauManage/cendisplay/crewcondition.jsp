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
	 <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	 <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
	<script src="<%=basePath%>core/js/jquery-1.11.1.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
  	<style>
		#main h2{
			width: 200px;padding: 0;margin: 0;
		}
		#main h2 span{
			background-color: #F40072;
			color: white;
			width: 50px;
			height: 30px;
			display: inline-block;
			text-align: center;
			line-height: 30px;
		}
		#main div{
			width: 25%; height: 80px; float: left;
		}
		#main div span{
			line-height: 80px; font-size: 25px; margin-left: 80px;
			font-weight: bold;
		}
		#main div span.num{
			margin-left: 0px;
			font-size: 35px;
			color: #158FDA;
			font-weight: bolder;
		}
	</style>
	<script type="text/javascript">
		 var flag = 0;
		//如果websocket一直不刷新,20分钟判断一次;websocket,onmessage会自动修改状态,具体时间现场确定
		setInterval("timeQuery();", 60000*20);
		function timeQuery(){
			if (flag == 0) {
				queryNow();
			}
		}
		//查询当前状态
		$(function(){
			queryNow();
		});
		
		//查询当前状态
		function queryNow(){
			$.post('<%=basePath%>rest/mauMachineManageAction/getMacCount',{requestType:'mapuser'},function(data){
				load(data);
			},"json");
		}
		//加载参数
		function load(data){
			debugger;
			$("#isOpen").html(data.serviceCount+"台");
			$("#isStop").html(data.stopCount+"台");
			$("#isAll").html(data.allCount+"台");
			$("#isError").html(data.errorCount+"台");
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
				if ("true" == josnBean) {
					queryNow();
				}
				//改变状态
				flag = 1;
				//只执行一次,一个小时还没有刷新,修改状态,使页面刷新
				setTimeout(function(){
					flag = 0;
				}, 60000*40);
			}
			//传的参数
			function onOpen(event) { 
				//TODO 发送房间号到后台去,获取房间号
				//webSocket.send('${id}');
			//	webSocket.send('201');
//	 			document.getElementById('data').innerHTML = '<br />' + ""+"start";
			}
		
			function onError(event) {
		// 		alert(event.data);
			}
		}
	</script> 
  </head>
  
  <body>
		
		<div id="main" style="width: 100%;">
			<h2><span>1</span>车间机台情况</h2>
			<div>
				<span>机台总数</span>
				<span class="num" id="isAll">34</span>
			</div>
			<div>
				<span>开机数</span>
				<span class="num" id="isOpen"></span>
			</div>
			<div>
				<span>停机数</span>
				<span class="num" id="isStop"></span>
			</div>
			 <div>
				<span>故障数</span>
				<span class="num" id="isError"></span>
			</div> 
		</div>
		
	</body>
</html>
