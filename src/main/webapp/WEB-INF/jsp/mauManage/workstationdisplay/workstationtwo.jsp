<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>机台参数展示</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="呼叫叉车,新鲜事时间">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	.nav{
				height: 80px;
				background: aqua;
				line-height: 50px;
			}
			.img{
				width:20%;
				height: 198px;
				float: right;
				background: red;
			}
			.top-a{
				height: 127px;
				line-height: 80px;
				background: blue;
			}
			.main{
				width: 100%;
				float: left;
				line-height: 80px;
				background: aqua;
				height: 450px;
			}
			.nav-a{
				width: 33%;
				height: 60px;
				text-align: center;
				float: left;
				
			}
			.span-a{
				font-size:20px;
				font-family: "微软雅黑";
				font-weight: 300;
			}
			.top-a-a{
				width:230px;
				float: left;
				height: 60px;
				text-align: center;
			}
			.main-a{
				float: left;
				width: 11%;
				height: 450px;
				
			}
			.main-b{
				float: left;
				width: 88%;
				height: 450px;
				
			}
			.main-b-a{
				float: left;
				
				width: 19%;
				margin-left:5px ;
				height: 100px;
			}
			.top-a-2{
				float: left;
				width: 68%;
				height: 120px;
			}
			.nav-a-2{
				float: left;
				width: 68%;
				height: 80px;
			}
	</style>
	<script src="<%=basePath%>core/js/jquery-1.11.1.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/dateUtils.js" type="text/javascript"></script>
	
		<script type="text/javascript">
	$(function(){
		$.ajax({
			type:"post",
			url:basePath+"rest/mauWorkStationManageAction/getMessageTwo",
			dataType:"json",
			async: false,  
			 error: function(request) {  
			        alert("Connection error");  
			    },  
			    success: function(data) {  
			        //接收后台返回的结果  
			    }  
			
		});
	});
	
	
	</script>
  </head>
  
  <body>
		<div class="main-a"><span class="span-a" >历史参数设定：</span></div>
			<div class="main-b">
				<div class="main-b-a"><span class="span-a" >放线张力：<span id="tensionNum"></span></span></div>
				<div class="main-b-a"><span class="span-a" >水槽温度：<span id="sinkTem"></span></span></div>
				<div class="main-b-a"><span class="span-a" >一段温度：<span id="oneSectionTem"></span></span></div>
				<div class="main-b-a"><span class="span-a" >二段温度：<span id="twoSectionTem"></span></span></div>
				<div class="main-b-a"><span class="span-a" >三段温度：<span id="threeSectionTem"></span></span></div>
				<div class="main-b-a"><span class="span-a" >四段温度：<span id="fourSectionTem"></span></span></div>
				<div class="main-b-a"><span class="span-a" >五段温度：<span id="fiveSectionTem"></span></span></div>
				<div class="main-b-a"><span class="span-a" >六段温度：<span id="sixSectionTem"></span></span></div>
				<div class="main-b-a"><span class="span-a" >七段温度：<span id="sevenSectionTem"></span></span></div>
				<div class="main-b-a"><span class="span-a" >八段温度：<span id="eightSectionTem"></span></span></div>
				<div class="main-b-a"><span class="span-a" >线径：<span id="wireDiameter"></span></span></div>
				<div class="main-b-a"><span class="span-a" >储线张力：<span id="storageLineNum"></span></span></div>
				<div class="main-b-a"><span class="span-a" >OEE值：<span id="oeeValue"></span></span></div>
				<div class="main-b-a"><span class="span-a" >挤出压力:<span id="pressureNum"></span></span></div>
				<div style="width: 30%;height: 120px;float: left;"><span class="span-a" >请求轴号：<span id="requestAxis"></span>p1   p2  p3  p4  p5</span></div>
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
/* 	Date.prototype.pattern=function(fmt) {         
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
	} */
	
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
			queryFork();
		}
		//传的参数
		function onOpen(event) { 
			//TODO 发送房间号到后台去,获取房间号
			//webSocket.send('${id}');
			webSocket.send();
		}
	
		function onError(event) {
	// 		alert(event.data);
		}
	}
	
	//
	function queryFork(){
		$.post('<%=basePath%>rest/mauCallForkliftRecordManageAction/getConditionCall',{/* condition:'呼叫' */},function(data){
			load(data);
		},"json");
	}
	
	//加载获得值到页面
	function load(data){
		var html="";
		for(var i = 0;i<data.length;i++){
			html += "叉车"+data[i].fCode+"呼叫时间"+data[i].callTime;
		}
		$("#callfork").html(html);
	}
  </script>
  <style>
		.call{
			font-size: 20px;
			color: #C1CDCD;
		}
	</style>
</html>
