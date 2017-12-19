<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<title></title>
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
		<script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
		<script type="text/javascript">
		//var dateRows;	
		function itemclick(item) {
				alert(item.text);
			}
			$(function() {
				grid();
				myLoad();
			});
			function grid() {
				window['g'] =
					$("#maingrid").ligerGrid({
						height: '99%',
						url:'<%=basePath %>rest/storeMrecordManageAction/getStock?requestType=mapuser',
						columns: [
							{ display: '物料名称', name: 'objSort'},
							{ display: '规格型号', name: 'objGgxh' },
							{ display: '入库数量', name: 'acount'},
							{ display: '单位', name: 'unit'},
							{ display: '颜色', name: 'color' }
						],
						rownumbers: true,
						
						// autoFilter: true
					});
				$("#pageloading").hide();
			}
			
			 function myLoad(){
				  	var basePath = "<%=basePath%>";
						var urlPirfex = basePath.substring(7, basePath.length);
						var url = "ws://"+urlPirfex+"storeCrewWebS";
					
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
							if(jsonBean){
								//debugger;
								$("#maingrid").ligerGrid({}).reload();
							}
							
						}
						function onOpen(event) { 
							//webSocket.send();
							webSocket.send("true");
						}
					
						function onError(event) {
						}
				  }
		</script>
	</head>

	<body style="overflow-x:hidden; padding:2px;">
		<!-- <div class="l-loading" style="display:none;" id="pageloading"></div> -->
		<div class="l-clear"></div>

		<div id="maingrid"></div>

		<div style="display:none;">

		</div>

	</body>

</html>