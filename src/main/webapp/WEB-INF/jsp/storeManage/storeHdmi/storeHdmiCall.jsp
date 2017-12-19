<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<script src="<%=basePath %>core/js/jquery-2.1.4.min.js" type="text/javascript"></script>
	</head>

	<body>
		<div class="call" style="width: 99%;position: absolute;">
			<marquee id="affiche" align="left" behavior="scroll" bgcolor="#DEDEDE" direction="left" height="50" width="100%" hspace="0" vspace="0 loop=" -1 " scrollamount="10 " scrolldelay="100 " onMouseOut="this.start() " onMouseOver="this.stop() ">
					<span id="sp" style="font-size: 30px;color: red;">暂无信息提示</span>
			</marquee>
		</div>
	</body>
	<script type="text/javascript">
	
		$(function(){
			//$("#sp").text("暂无提示信息" );
			myload();
			
		});
	
		function load(jsonBean){
			debugger;
			$("#sp").text("警告:"+jsonBean+"!");
		}
		 function myload(){
			  	var basePath = "<%=basePath%>";
					var urlPirfex = basePath.substring(7, basePath.length);
					var url = "ws://"+urlPirfex+"storeCallWebSocket";
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
				
					function onMessage(event) {
						var jsonBean = eval('('+event.data+')');
						load(jsonBean);
						
					}
					function onOpen(event) { 
						webSocket.send("true");
					}
				
					function onError(event) {
					}
			  }
	
	</script>
</html>