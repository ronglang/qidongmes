<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>出库</title>
    
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
			
			initData();
			myWebsockt();
			
		});
		
		function initData(){
			$.ajax({
				url: basePath + "rest/storeObjManageAction/getTodayObj?inoutType=出库",
				type: 'post',
				dataType : 'json',
				success: function(list){
					outStore(list);
				}
			});
			
			
		}
		
		function outStore(list){
			debugger;
			$("#mlc_list_o").html("");
			var html = "";
			html+="<div class='mlcl_ul'>";
			html+="<ul>";
			for(var i=0;i<list.length;i++){
				if(i%2 == 0){
					html+= "<li><span class='s1'>"+list[i].objSort+"</span><span class='s2'>"+list[i].objGgxh+"</span><span class='s1'>"+list[i].acount+"</span><span class='s1'>"+list[i].unit+"</span><span class='s2'>"+list[i].outDate+"</span><span class='s1 s3'>"+list[i].materialType+"</span></li>";
				}else{
					html+= "<li class='ls'><span class='s1'>"+list[i].objSort+"</span><span class='s2'>"+list[i].objGgxh+"</span><span class='s1'>"+list[i].acount+"</span><span class='s1'>"+list[i].unit+"</span><span class='s2'>"+list[i].outDate+"</span><span class='s1 s3'>"+list[i].materialType+"</span></li>";
				}
			}
			html+="</ul>";
			html+="</div>";
			$("#mlc_list_o").append(html);
			//通知滚动
			$("#mlc_list_o .mlcl_ul").kxbdSuperMarquee({
				isMarquee:true,
				isEqual:false,
				scrollDelay:150,
				direction:'top'
			});
			
		}
		
		function myWebsockt(){
			
			var url = "ws://"+urlPirfex+"storeCrewOutWebSocket";
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
				outStore(list);
				
			}
			//传的参数
			function onOpen(event) { 
				webSocket.send('电子看板消息呼叫叉车通知');
			}
		}
	
	</script>
	<style type="text/css">
		ul{
				list-style-type: none;
			}
		.ml_con{
			height: 500px;
			width: 95%;
			border-radius: 5px;
			background-color: #224B81;
			margin: 0 auto;
		}
		
		#mlc_list_o .mlcl_ul,#mlc_list_t .mlcl_ul{
				width:90%;
				height:350px;
				overflow:hidden;
				margin:0 auto;
	    	}
	    	#mlc_list_o .mlcl_ul ul,#mlc_list_t .mlcl_ul ul{
	    		height:350px;
				width: 100%;
				margin: 0 auto;
				border-bottom: 1px solid white;
				padding: 0;
	    	}
	    	#mlc_list_o .mlcl_ul ul li,#mlc_list_t .mlcl_ul ul li{
	    		height: 50px;
	    	}
	    	#mlc_list_o .mlcl_ul ul li.ls span,#mlc_list_t .mlcl_ul ul li.ls span{
	    		background-color: #0367CE;
	    	}
	    	#mlc_list_o .mlcl_ul ul li span,#mlc_list_t .mlcl_ul ul li span{
	    		display: inline-block;
	    		height: 50px;
	    		line-height: 50px;
	    		border-top: 1px solid white;
	    		border-left: 1px solid white;
	    		text-align: center;
	    		color: white;
	    	}
	    	#mlc_list_o .mlcl_ul ul li span.s1,#mlc_list_t .mlcl_ul ul li span.s1{
	    		width: 80px;
	    	}
	    	#mlc_list_o .mlcl_ul ul li span.s2,#mlc_list_t .mlcl_ul ul li span.s2{
	    		width: 160px;
	    	}
	    	#mlc_list_o .mlcl_ul ul li span.s3,#mlc_list_t .mlcl_ul ul li span.s3{
	    		border-right: 1px solid white;
	    	}
	    	.title{
	    		width: 90%;
	    		margin: 0 auto;
	    	}
	    	.title span{
	    		display: inline-block;
	    		height: 50px;
	    		line-height: 50px;
	    		border-top: 1px solid white;
	    		border-left: 1px solid white;
	    		text-align: center;
	    		color: yellow;
	    		margin: 0;
	    		padding: 0;
	    		margin-right: -8px;
	    	}
	    	
	    	#storeDiv{
	    		margin: 10px auto;
	    		height: 100px;
	    		width: 100%;
	    	}
	    	.d_con{
	    		margin: 10px auto;
	    		height: 100px;
	    		width: 90%;
	    		overflow: hidden;
	    		border-left: 1px solid white;
	    		border-right: 1px solid white;
	    	}
	    	.d_con ul{
	    		margin: 0 auto;
	    		height: 100px;
	    		width: 100%;
				padding: 0;
	    	}
	    	.d_con ul li.de{
	    		height: 100px;
	    		width: 140px;
	    		background-color: #224B81;
	    		float: left;
	    		border-right: 1px solid chartreuse;
	    	}
	    	.d_con ul li.de span{
	    		display: inline-block;
	    		height: 50px;
	    		width: 100%;
	    		text-align: center;
	    	}
	    	.d_con ul li.de span.sa{
	    		line-height: 60px;
	    		color: white;
	    		font-size: 18px;
	    		letter-spacing: 5px;
	    	}
	    	.d_con ul li.de span.sp{
	    		line-height: 20px;
	    		color: yellow;
	    		font-size: 20px;
	    	}
	    	.mlcl_ul ul li span{
	    		overflow: hidden;
	    	}
			h3{
				height: 40px;
				line-height: 40px;
				border-top-left-radius:5px;
				border-top-right-radius:5px;
				background-color: #4C85D4;
				text-align: center;
				color: yellow;
				font-size: 16px;
				letter-spacing: 5px;
			}
			.title{
	    		width: 90%;
	    		margin: 0 auto;
	    	}
	    	.title span{
	    		display: inline-block;
	    		height: 50px;
	    		line-height: 50px;
	    		border-top: 1px solid white;
	    		border-left: 1px solid white;
	    		border-bottom:1px solid white;
	    		text-align: center;
	    		color: yellow;
	    		margin: 0;
	    		padding: 0;
	    		margin-right: -8px;
	    		overflow: hidden;
	    	}
	</style>
  </head>
  
  <body>
    
    <div class="ml_con">
		<h3>出库情况</h3>
		<div class="title" style="margin-top: 10px;">
			<span calss="t1" style="width: 80px;">名称</span>
			<span calss="t1" style="width: 160px;">规格型号</span>
			<span calss="t1" style="width: 80px;">出库数量</span>
			<span calss="t2" style="width: 80px;">单位</span>
			<span calss="t2" style="width: 160px;">出库时间</span>
			<span calss="t1 t3" style="width: 80px;border-right: 1px solid white;">类型</span>
		</div>
		<div id="mlc_list_o">
			
		</div>
		
	</div>
    
  </body>
</html>
