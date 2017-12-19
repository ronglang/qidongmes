<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>机台状态数</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript" charset="utf-8"></script>
	
	
	<style type="text/css">
	/*
	.ml_one,.ml_three,.ml_two,.ml_four{
		width: 250px;
		height: 130px;
		border-radius:5px;
		background-color: #427DC3;
		margin: 20px auto;
		display:inline-block;
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
	*/
	
	
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
			
			//myWebsockt();
			getMacStatus();
			
			setInterval(function(){
				getMacStatus();
			}, 60000);
			
		});
		
		function getMacStatus(){
			$.ajax({
				url: basePath + "rest/mauMachineManageAction/getMacAllStateNum",
				type: "POST",
				dataType: "JSON",
				success:function(data){
					var map = eval("("+data+")");
					$("#macNum .statusNum").html("");
					var html = "";
					for(var key in map){
						html += "<td>";
						html += "<div class='tbtd'>";
						html += "<label class='mlo_one'><b id='openCount'>"+map[key]+"</b><span>台</span></label>";
						html += "<label class='mlo_two'>机台"+key+"数</label>";
						html += "</div>";
						html += "</td>";
					}
					$("#macNum .statusNum").append(html);
				}
				
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
  
  <table border="0" cellspacing="0" cellpadding="0" id="macNum">
	<tr class="statusNum">
		<!-- 
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
		 -->
	</tr>
</table>
  
  <!-- 
    <div class="ml_one">
		<label class="mlo_one"><b id="openCount">30</b><span>台</span></label>
		<label class="mlo_two">机台开机数</label>
	</div>
	<div class="ml_two">
		<label class="mlo_one"><b id="stopCount">8</b><span>台</span></label>
		<label class="mlo_two">机台停机数</label>
	</div>
	<div class="ml_three">
		<label class="mlo_one"><b id="errorCount">10</b><span>台</span></label>
		<label class="mlo_two">机台故障数</label>
	</div>
	<div class="ml_four">
		<label class="mlo_one"><b id="maintainCount">10</b><span>台</span></label>
		<label class="mlo_two">机台保养数</label>
	</div>
     -->
     
  </body>
</html>
