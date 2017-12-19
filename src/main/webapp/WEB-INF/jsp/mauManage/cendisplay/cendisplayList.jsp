<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>生产电子看板list</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		setInterval(function(){
				setTime();
			},1000);
			
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
		</script>
  </head>
  <body>
  		<div style="position: relative;">
		 	<div class="call" style="width: 70%;height:80px;position: absolute; background:#1E90FF ;">
					<span style="font-size: 50px;font-family: 'LiSu';color: white;margin-left: 40%;">
					车&nbsp;&nbsp;间&nbsp;&nbsp;电&nbsp;&nbsp;子&nbsp;&nbsp;看&nbsp;&nbsp;板</span>
		 	</div>
		 	 <div style="background-color:#1E90FF;height:80px;width:30%;float: right;"  >
	 			<div style="float: right;margin-top:40px; font-size:25px ;text-align: center;margin-right: 20px;">
					<span class="call" style="font-size: large;color: white;"> 时间 : </span><span class="call" id="nowdate" 
						style="font-size: large;color: white;"></span>
				</div>
			</div>
			<iframe src="<%=basePath%>rest/mauDisplayAction/toMauDisplayCall?requestType=mapuser" style="height: 60px;width:100%;border:1px solid #DEDEDE;background-color: #DEDEDE;"scrolling="no">
			</iframe>
		</div>
		
		<div style="height: 150px;">
			<iframe src="<%=basePath%>rest/mauDisplayAction/toMauDisplayCrew?requestType=mapuser" style="height: 150px;width:100%;border:1px solid white;"scrolling="no"></iframe>
		</div>
		<div style="width: 100%; height: 350px; ">
			<div style="width: 50%;  display:inline;float:left ;">
				<iframe src="<%=basePath%>rest/mauDisplayAction/toCenMauDisplayWork?requestType=mapuser" style="display:inline;height: 450px;width:100%;"scrolling="no">
				</iframe>
			</div>
			<div style="width: 50%; display:inline;float:left ;">
				<iframe src="<%=basePath%>rest/mauDisplayAction/toMauDisplayMauException?requestType=mapuser" style="display:inline;height: 450px;width:100%;"scrolling="no">
<%-- 				<iframe src="<%=basePath%>rest/mauDisplayAction/toMauDisplayMachineError" style="display:inline;height: 450px;width:100%;"scrolling="no"> --%>
				</iframe>
			</div>
		</div>
		
  </body>
</html>
