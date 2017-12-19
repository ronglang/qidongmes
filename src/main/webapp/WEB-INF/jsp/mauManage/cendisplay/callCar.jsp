<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>呼叫叉车</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath%>core/js/echarts.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath%>core/js/kxbdSuperMarquee.js" type="text/javascript" charset="utf-8"></script>
		
	<script type="text/javascript">
	var basePath = "<%=basePath%>";
	var urlPirfex = basePath.substring(7, basePath.length);
		$(function(){
			ajaxInitData();
			
			myWebsockt();
		});
		
	function ajaxInitData(){
		
		$.ajax({
			url: basePath + "rest/mauCallForkliftRecordManageAction/getCallCarInfo?requestType=mapuser",
			dataType: 'json',
			type:'post',
			success: function(list){
				//在此处将数据封装到页面
				htmlData(list);
			}
			
		});
		
	}
	
	function myWebsockt(){
		
		var url = "ws://"+urlPirfex+"callForkWebSocket";
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
			debugger;
			var list = eval('('+event.data+')');
			htmlData(list);
			
		}
		//传的参数
		function onOpen(event) { 
			webSocket.send('电子看板消息呼叫叉车通知');
		}
	}
	
	function htmlData(list){
		var html = "";
		$("#head").html("");
		html += "<div class='h_con'>";
		html +="<ul>";
		for(var i=0;i<list.length;i++){
			html += "<li>";
			html +=	"<span>呼叫人："+(list[i].callBy==null? "" : list[i].callBy)+"</span>";
			html +=	"<span>呼叫叉车："+(list[i].fCode==null? "" : list[i].fCode)+"</span>";
			html +=	"<span>呼叫位置："+(list[i].destMachineOrPlace==null? "" : list[i].destMachineOrPlace)+"</span>";
			html +=	"<span>呼叫时间："+(list[i].callTime==null? "" : list[i].callTime)+"</span>";
			html += "</li>";
		}
			
		html +="</ul>";
		html += "</div>";
		$("#head").append(html);
		//通知滚动
		$("#head .h_con").kxbdSuperMarquee({
			isMarquee:true,
			isEqual:false,
			scrollDelay:30,
			direction:'left',
		});
	}
	
	</script>
	<style type="text/css">
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
	
	</style>
  </head>
  
  <body>
    
    <div id="head">
		
	</div>
    
  </body>
</html>
