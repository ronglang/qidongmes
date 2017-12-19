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
	<script src="<%=basePath%>core/js/dateUtils.js" type="text/javascript"></script>
	 <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
		<div class="call" style="width: 99%;position: absolute; background-color: #DEDEDE">
			<marquee id="affiche" align="left" behavior="scroll"  direction="left" height="50" width="100%" hspace="0" vspace="0 loop=" -1 " scrollamount="10 " scrolldelay="100 " onMouseOut="this.start() " onMouseOver="this.stop() ">
					<span style="font-size: 20px; color: red " id="callfork"></span>
				</marquee>
		</div>	
	</body>
  <script type="text/javascript">
 	 var flag = 0;
	//如果websocket一直不刷新,20分钟判断一次;websocket,onmessage会自动修改状态,具体时间现场确定
	setInterval("timeQuery();", 60000*20);
	function timeQuery(){
		if (flag == 0) {
			queryFork();
		}
	}
  $(function(){
	 queryFork();
	 websockt();
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
			load(josnBean);
			//改变状态
			flag = 1;
			//只执行一次,一个小时还没有刷新,修改状态,使页面刷新
			setTimeout(function(){
				flag = 0;
			}, 60000*40);
		}
		//传的参数
		function onOpen(event) { 
			//TODO 发送房间号到后台去,获取房间号
			webSocket.send("1");
		}
	
		function onError(event) {
	// 		alert(event.data);
		}
	}
	
	//
	function queryFork(){
		$.post('<%=basePath%>rest/mauCallForkliftRecordManageAction/getConditionCall',{requestType :'mapuser'},function(data){
			load(data);
		},"json");
	}
	
	//加载获得值到页面
	function load(data){
		var html="";
		/* for(var i = 0;i<data.length;i++){
			html += "叉车"+data[i].fCode+"呼叫时间"+data[i].callTime;
		} */
		if (data!=null && data !="") {
			html +="叉车"+data.fCode+"呼叫时间"+data.callTime;
		}
		if(html==""){
			$("#callfork").html("无未处理的叉车呼叫");			
		}else{
			$("#callfork").html(html);
		}
	}
  </script>
  <style>
		.call{
			font-size: 20px;
			color: #C1CDCD;
		}
	</style>
</html>
