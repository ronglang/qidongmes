<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>质检部电子看板</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="<%=basePath%>core/img/logos.png">
	
	<link href="<%=basePath %>core/css/board/qiBoard.css" rel="stylesheet" type="text/css" />
	
	<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    <script type="text/javascript" src="<%=basePath %>core/js/kxbdSuperMarquee.js"></script>
    <script type="text/javascript">
    var basePath = "<%=basePath%>";
	var urlPirfex = basePath.substring(7, basePath.length);
    	$(function(){
    		
    		$("#times").html(showTime());
			setInterval(function(){
				$("#times").html(showTime());
			},1000);
    		
    		ajaxInitData();
			myWebsockt();
    		
    		
    	});
    	
    	function ajaxInitData(){
			
			$.ajax({
				url: basePath + "rest/mauCallForkliftRecordManageAction/getCallCarInfo",
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
		
		function showTime(){ 
		    var show_day=new Array('星期日','星期一','星期二','星期三','星期四','星期五','星期六'); 
		    var time=new Date(); 
		    var year=time.getFullYear();
		    var month=time.getMonth();
		    var date=time.getDate(); 
		    var day=time.getDay(); 
		    var hour=time.getHours(); 
		    var minutes=time.getMinutes(); 
		    var second=time.getSeconds(); 
		    month=parseInt(month)+1;
		    month<10?month='0'+month:month;
		    hour<10?hour='0'+hour:hour; 
		    minutes<10?minutes='0'+minutes:minutes; 
		    second<10?second='0'+second:second; 
		    var now_time=year+'/'+month+'/'+date+'　'+hour+':'+minutes+':'+second+'　'+show_day[day]; 
		    
		    return now_time;
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
    
    <div id="all">
			<div id="top">
				<ul class="bt">
					<li class="ck" style="font-size: 25px;letter-spacing: 5px;">质检看板</li>
					<li class="sj" id="times">2017/09/12 16:58 星期二</li>
				</ul>
			</div>
			<!-- 
			<div class="gd">
				<small class="gdz">机台编号ASJ-001呼叫叉车pl-023 &nbsp;叉车位置：机台放线出  &nbsp; 呼叫时间：20147/6/22/ 15:18 &nbsp;&nbsp;   ASJ-001呼叫叉车pl-023 &nbsp; 叉车位置：机台放线出 &nbsp;  呼叫时间：20147/6/22/ 15:18</small>
			</div>
 			-->
 			<!-- 呼叫叉车
 			<div id="head"> </div>
 			 -->
			<div class="kanr">
				
				<iframe src="<%=basePath%>rest/qualityAskManageAction/toDisplayInfo?requestType=mapuser" width="100%" height="100%" frameborder="0" scrolling="no" class=""></iframe>

			</div>

		</div>
    
  </body>
</html>
