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
	<meta http-equiv="description" content="呼叫叉车,新鲜事时间">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="<%=basePath%>core/js/jquery-1.11.1.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
  </head>
  
  <body>
		<div style="position: relative;">
		 	<div class="call" style="width: 70%;position: absolute;">
		 		<marquee id="affiche" align="left" behavior="scroll" bgcolor="#1E90FF" direction="left" height="50" width="100%" hspace="0" vspace="0 loop="-1" scrollamount="10" scrolldelay="100" onMouseOut="this.start()" onMouseOver="this.stop()">
					<span id="callfork" style="font-size: 30px;">这是滚动</span>
				</marquee>
		 	</div>
		 	 <div style="background-color:#1E90FF;height:50px;width:30%;float: right;"  >
	 			<div style="float: right;margin-top:15px">
					<span class="call" > 时间 : </span><span class="call" id="nowdate"></span>
				</div>
			</div>	
		</div> 	
	</body>
  <script type="text/javascript">
  $(function(){
	  queryFork();
  });
  
  //定时调用设置时间方法
	setInterval(function(){
		setTime();
	},1000);
	//设置时间
	function setTime(){
		var date = new Date();      
	 	var dateS =  date.pattern("yyyy-MM-dd EEE hh:mm:ss");
	 	 $("#nowdate").html(dateS);
	}
	//时间格式转换
	Date.prototype.pattern=function(fmt) {         
	  var o = {         
	  "M+" : this.getMonth()+1, //月份         
	  "d+" : this.getDate(), //日         
	  "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时         
	  "H+" : this.getHours(), //小时         
	  "m+" : this.getMinutes(), //分         
	  "s+" : this.getSeconds(), //秒         
	  "q+" : Math.floor((this.getMonth()+3)/3), //季度         
	  "S" : this.getMilliseconds() //毫秒         
	  };         
	  var week = {         
	  "0" : "日",         
	  "1" : "一",         
	  "2" : "二",         
	  "3" : "三",         
	  "4" : "四",         
	  "5" : "五",         
	  "6" : "六"        
	  };         
	  if(/(y+)/.test(fmt)){         
	      fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
	  }         
	  if(/(E+)/.test(fmt)){         
	      fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "星期" : "周") : "")+week[this.getDay()+""]);         
	  }         
	  for(var k in o){         
	      if(new RegExp("("+ k +")").test(fmt)){         
	          fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
	      }         
	  }         
	  return fmt;         
	}
	
	//websocket
	function websockt(){
		var basePath = "<%=basePath%>";
		var urlPirfex = basePath.substring(7, basePath.length);
		var url = "ws://"+urlPirfex+"cenForkliftWebS";
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
			//PLC参数
			load(josnBean);
		}
		//传的参数
		function onOpen(event) { 
			//TODO 发送房间号到后台去,获取房间号
			//webSocket.send('${id}');
			webSocket.send('201');
// 			document.getElementById('data').innerHTML = '<br />' + ""+"start";
		}
	
		function onError(event) {
	// 		alert(event.data);
		}
	}
	
	//
	function queryFork(){
		$.post(url,{},function(data){
			load(data);
		},"json");
	}
	
	//加载获得值到页面
	load(data){
		var html="";
		for(var i = 0;i<data.length;i++){
			html += data[i];
		}
		$("#callfork").html();
	}
  </script>
  <style>
		.call{
			font-size: 20px;
			color: white;
		}
	</style>
</html>
