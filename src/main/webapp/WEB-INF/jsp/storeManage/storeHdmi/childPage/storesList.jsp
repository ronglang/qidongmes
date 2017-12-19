<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>库存和废料</title>
    
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
				url:basePath + "rest/storeMrecordManageAction/getAllClassify",
				type:'post',
				dataType:'json',
				success:function(list){
					htmlData(list);
				}
				
			});
			
		}
		
		function myWebsockt(){
			
			var url = "ws://"+urlPirfex+"storeNumWebsocket";
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
				webSocket.send('仓库电子看板');
			}
		}
		
		function htmlData(list){
			debugger;
			var html = "";
			html+="<div class='d_con'>";
			html+="<ul>";
			for(var j=0;j<list.length;j++){
				if(list[j].type == "报废"){
						html+= "<li class='se de'>";
						html+="<span class='sa'>"+list[j].key+"(废)</span>";
						html+="<span class='sp'>"+list[j].value+"</span>";
						html+="</li>";
				}else{
						html+= "<li class='de'>";
						html+="<span class='sa'>"+list[j].key+"</span>";
						html+="<span class='sp'>"+list[j].value+"</span>";
						html+="</li>";
				}
			}
			
			
			
			html+="</ul>";	
			html+="</div>";			
						
			$("#storeDiv").append(html);
			//通知滚动
			$("#storeDiv .d_con").kxbdSuperMarquee({
				isMarquee:true,
				isEqual:false,
				scrollDelay:50,
				direction:'left',
			});
				
			
			
		}
		
	
	</script>
	<style type="text/css">
		ul{
			list-style-type: none;
			padding: 0;
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
    	.d_con ul li.se{
    		background-color: #0D5FB4;
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
    		overflow: hidden;
    	}
    	.d_con ul li.de span.sp{
    		line-height: 20px;
    		color: yellow;
    		font-size: 20px;
    	}
	
	
	</style>
  </head>
  
  <body>
    
    <div id="storeDiv"></div>
    
  </body>
</html>
