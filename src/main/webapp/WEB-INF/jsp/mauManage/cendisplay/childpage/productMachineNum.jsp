<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>机台状态数量</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript" charset="utf-8"></script>
	
	<style type="text/css">
	div,table{
		margin: 0;
		padding: 0;
	}
	table{
		width: 100%;
		margin: 20px auto;
	}
	table tr td{
		width: 20%;
		height: 130px;
		
		
	}
	table .tbtd{
		width: 90%;
		height: 130px;
		margin: 0 auto;
		background-color: #427DC3;
		border-radius:10px;
	}
	label{
		text-align: center;
		display: block;
		height: 50%;
	}
	
	label.mlo_one b{
		line-height: 100px;
		font-size: 60px;
		color: yellow;
		font-weight: normal;
	}
	label.mlo_one span{
		color: yellow;
		font-size: 30px;
	}
	
	label.mlo_two{
		line-height: 50px;
		font-size: 20px;
		color: white;
	}
</style>
	
	<script type="text/javascript">
		var basePath = "<%=basePath%>";
		var urlPirfex = basePath.substring(7, basePath.length);
		$(function(){
			//初始化机台状态个数数据
			initData();
			//获取机台状态个数数据
			myWebsockt();
			
		});
		
		function initData(){
			$.ajax({
				url: '<%=basePath%>rest/mauMachineManageAction/getMacCount',
				type: 'POST',
				dataType: 'json',
				success: function(map){
					for(var key in map){
						$("#"+key).html(map[key]);
					}
				},
				error: function(){
					alert("服务器出错！");
				},
				
			});
		}
		
		function myWebsockt(){
			
			var url = "ws://"+urlPirfex+"CarKanbanMachineNumWebsocket";
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
				debugger;
				var map = eval('('+event.data+')');
				for(var key in map){
					$("#"+key).html(map[key]);
				}
				
			}
			//传的参数
			function onOpen(event) { 
				webSocket.send('机台状态数');
			}
		
			function onError(event) {
				
			}
		}
	
	</script>
	
  </head>
  
  <body>
    
    <table border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<div class="tbtd">
				<label class="mlo_one"><b id="openCount"></b><span>台</span></label>
				<label class="mlo_two">机台开机数</label>
			</div>
		</td>
		<td>
			<div class="tbtd">
				<label class="mlo_one"><b id="stopCount"></b><span>台</span></label>
				<label class="mlo_two">机台停机数</label>
			</div>
		</td>
		<td>
			<div class="tbtd">
				<label class="mlo_one"><b id="errorCount"></b><span>台</span></label>
				<label class="mlo_two">机台故障数</label>
			</div>
		</td>
		<td>
			<div class="tbtd">
				<label class="mlo_one"><b id="maintainCount"></b><span>台</span></label>
				<label class="mlo_two">机台保养数</label>
			</div>
		</td>
	</tr>
</table>
    
  </body>
</html>
