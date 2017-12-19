<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>质检列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="<%=basePath %>core/css/board/qiBoard.css" rel="stylesheet" type="text/css" />
	
	<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    <script type="text/javascript" src="<%=basePath %>core/js/kxbdSuperMarquee.js"></script>
	
	<script type="text/javascript">
	
	$(function(){
		
		getData();
		myWebsockt();
		
	});
		
	function getData(){
		$.ajax({
			url: '<%=basePath %>' + 'rest/qualityAskManageAction/getAllTodayList',
			dataType: 'json',
			type: 'POST',
			success:function(data){
				queryTable(data);
			}
			
		});
	}
	
	function myWebsockt(){
		
		var url = "ws://"+urlPirfex+"quaAskWebSocket";
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
			queryTable(list);
			
		}
		//传的参数
		function onOpen(event) { 
			webSocket.send('电子看板消息呼叫叉车通知');
		}
	}
	
	function queryTable(list){
		$("#contents").html("");
		var html = "";
		html += "<div id='marquee'>";
		html += "<ul>";
		for(var i=0;i<list.length;i++){
			if(list[i].finishBy == null || list[i].finishBy == ""){
				if(i%2 == 0){
					html += "<li style='background-color:#4B85D1'><span>"+(i+1)+"</span><span>"+list[i].askType+"</span><span>"+list[i].askLocation+"</span><span>"+list[i].askState+"</span><span>&nbsp;</span><span class='des'>&nbsp;</span></li>";
				}else{
					html += "<li><span>"+(i+1)+"</span><span>"+list[i].askType+"</span><span>"+list[i].askLocation+"</span><span>"+list[i].askState+"</span><span>&nbsp;</span><span class='des'>&nbsp;</span></li>";
				}
			}else{
				if(i%2 == 0){
					html += "<li style='background-color:#4B85D1'><span>"+(i+1)+"</span><span>"+list[i].askType+"</span><span>"+list[i].askLocation+"</span><span>"+list[i].askState+"</span><span>"+list[i].finishBy+"</span><span class='des'>"+list[i].finishDate+"</span></li>";
				}else{
					html += "<li><span>"+(i+1)+"</span><span>"+list[i].askType+"</span><span>"+list[i].askLocation+"</span><span>"+list[i].askState+"</span><span>"+list[i].finishBy+"</span><span class='des'>"+list[i].finishDate+"</span></li>";
				}
			}
			
			
		}
		html += "</ul>";
		html += "</div>";
		
		$("#contents").append(html);
		
		$('#marquee').kxbdSuperMarquee({
			isMarquee:true,
			isEqual:false,
			scrollDelay:150,
			direction:'top'
		});
   		 
   	 }
	
	</script>
	<style type="text/css">
		* {
			margin:0;padding:0;
		}
		body { 
			font-size:12px;
		}
		a {
			color:#333;
			text-decoration: none;
		}
		ul {
			list-style:none;
		}
		#marquee {
			width:95%;
			height:410px;
			overflow:hidden;
			margin:0 auto;
		}
		#marquee ul li {
			float:left; 
			width:100%; 
		}
		#marquee ul li span{
			display: inline-block;
			width:16.58%;
			text-align: center;
			border-left:1px solid white;
    		border-bottom:1px solid white;
    		height:40px;
    		line-height:40px;
    		font-size:15px;
		}
		#marquee ul li span.des{
			border-right:1px solid white;
		}
    	#tabs{
    	}
    	#tabs .title{
    		width:95%;
    		height:50px;
    		margin:0 auto;
    		border-bottom: 1px solid white;
    		border-right: 1px solid white;
    	}
    	#tabs .title li{
    		float: left;
    		width:16.595%;
    		height:50px;
    		line-height:50px;
    		text-align:center;
    		border-left:1px solid white;
    		border-top:1px solid white;
    		color:yellow;
    		letter-spacing: 5px;
    		font-size: 16px;
    	}
    	ul {list-style:none;}
			li{
			
			}
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
  
  	<div class="kbtop">质检通知信息</div>
	<div class="table" id="tabs">

		<ul class="title">
			<li>序号</li>
			<li>呼叫类型</li>
			<li>呼叫位置</li>
			<li>呼叫状态</li>
			<li>完成人</li>
			<li>完成时间</li>
		</ul>
		<div id="contents">
		</div>
	</div>
    
  </body>
</html>
