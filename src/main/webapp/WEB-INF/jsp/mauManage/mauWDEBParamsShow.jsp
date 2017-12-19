<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>拉丝工序电子看板</title><meta http-equiv="X-UA-Compatible" content="IE=9" />
	<meta http-equiv="X-UA-Compatible" content="IE=9" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript">
		var basePath = "<%=basePath%>";
		var urlPath = basePath.substring(7, basePath.length);
	</script>
	<script src="<%=basePath%>core/js/echarts.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	
	<script src="<%=basePath%>app/js/mauManage/mauWDEBParamsShow.js" type="text/javascript"></script>
	<style>
		#tb1{
			width: 90%;
			margin:0 auto;
		}
		#paramstables{
			width:100%;
			margin-top:15px;
		}
		#tb1 tr td{
			width:33%;
		}
		#paramstables tr{
			height:35px;
		}
		#tb1 tr{
			height:30px;
		}
		#paramstables tr td span{
			display: inline-block;
			text-align: right;
			font-size:13px;
		}
		#paramstables  tr td label{
			color:#12F0F0;
			font-size:18px;
		}
		#tb1 tr td span{
			display: inline-block;
			text-align: right;
			height:30px;
			
		}
		#tb1 tr td label{
			width:65%;
			color:#46FF00;
			font-weight: bold;
		}
		#tb1 tr td span{
			font-size:25px;
			color:#FFFF00;
			width:40%;
		}
		#tb1 tr td label{
			color:#2FF500;
		}
		#paramstables  tr td span{
			font-size:22px;
			color:white;
			width:200px;
		}
		/*
		.tr001{
			font-size: 24px;
		    color: #FFFF00;
		    text-align: left;
		}
		.tr002{
			font-size: 24px;
		    color: #66FF00;
		    text-align: left;
		}
		.tr003{
			 font-size: 20px;
   			 color: #FFFF00;
   			 text-align: left;
		}
		.tr004{
			 font-size: 20px;
   			 color: #FFFFFF;
   			 text-align: right;
		}
		#callRepair{
			background-color:#FF0000
		}
		#rtext{
			    color:#FFFFFF;
		}
		span{
			font-size: 22px;
		}
		*/
	</style>
  </head>
   <body>
   		<table border="0" id="tb1">
   			<tr>
   				<td>
   					<span>机台名称：</span>
   					<label id="machineName"></label>
   				</td>
   				<td>
   					<span>规格型号：</span>
   					<label id="material_type"></label>
   				</td>
   				<td>
   					<span>机台完成情况：</span>
   					<label id="ls_anneal"></label>
   				</td>
   			</tr>
   			<tr>
   				<td>
   					<span>机台编码：</span>
   					<label id="macCode"></label>
   				</td>
   				<td>
   					<span>操作人员：</span>
   					<label id="operPerson"></label>
   				</td>
   				<td>
   					<span></span>
   					<label id="timeShow"></label>
   				</td>
   			</tr>
   		</table>
   		
  		<table id="paramstables" border="0">
  			<tr>
  				<td>
					<span>实际拉线径值：</span>
					<label id="4000">暂无数据</label>
				</td>
				<td>
					<span>实际拉线径标准差：</span>
					<label id="4001">暂无数据</label>
				</td>
				<td>
					<span>拉线速度：</span>
					<label id="4002">暂无数据</label>
				</td>
				<td>
					<span>拉线平均速度：</span>
					<label id="4003">暂无数据</label>
				</td>
			<tr>
				<td>
					<span>拉线最大速度：</span>
					<label id="4004">暂无数据</label>
				</td>
				<td>
					<span>退火电压设定(%)：</span>
					<label id="4005">暂无数据</label>
				</td>
				<td>
					<span>退火电压：</span>
					<label id="4006">暂无数据</label>
				</td>
				<td>
					<span>退火电流：</span>
					<label id="4007">暂无数据</label>
				</td>
			</tr>
			<tr>
				<td>
					<span>主马达电流：</span>
					<label id="4008">暂无数据</label>
				</td>
				<td>
					<span>拉丝长度：</span>
					<label id="4009">暂无数据</label>
				</td>
				<td>
					<span>拉丝条数：</span>
					<label id="4010">暂无数据</label>
				</td>
				<td>
					<span>总电能：</span>
					<label id="2000">暂无数据</label>
				</td>
			</tr>
			<tr>
				<td>
					<span>A相电压：</span>
					<label id="2001">暂无数据</label>
				</td>
				<td>
					<span>B相电压：</span>
					<label id="2002">暂无数据</label>
				</td>
				<td>
					<span>C相电压：</span>
					<label id="2003">暂无数据</label>
				</td>
				<td>
					<span>A相电流：</span>
					<label id="2004">暂无数据</label>
				</td>
			</tr>
			
			
			<tr>
				<td>
					<span>总有功率：</span>
					<label id="2007">暂无数据</label>
				</td>
				<td>
					<span>A相有功率：</span>
					<label id="2008">暂无数据</label>
				</td>
				<td>
					<span>B相有功率：</span>
					<label id="2009">暂无数据</label>
				</td>
				<td>
					<span>C相有功率：</span>
					<label id="2010">暂无数据</label>
				</td>
				
			</tr>
			<tr>
				<td>
					<span>总无功率：</span>
					<label id="2011">暂无数据</label>
				</td>
				<td>
					<span>A相无功率：</span>
					<label id="2012">暂无数据</label>
				</td>
				<td>
					<span>B相无功率：</span>
					<label id="2013">暂无数据</label>
				</td>
				<td>
					<span>C相无功率：</span>
					<label id="2014">暂无数据</label>
				</td>
			</tr>
			<tr>
				<td>
					<span>总功率因数：</span>
					<label id="2015">暂无数据</label>
				</td>
				<td>
					<span>A相功率因数：</span>
					<label id="2016">暂无数据</label>
				</td>
				<td>
					<span>B相功率因数：</span>
					<label id="2017">暂无数据</label>
				</td>
				<td>
					<span>C相功率因数：</span>
					<label id="2018">暂无数据</label>
				</td>
				
			</tr>
			<tr>
				<td>
					<span>B相电流：</span>
					<label id="2005">暂无数据</label>
				</td>
				<td>
					<span>C相电流：</span>
					<label id="2006">暂无数据</label>
				</td>
  			</tr>
		</table>
		
		<script type="text/javascript">
			
		$(document).ready(function(){
			$("#timeShow").html(showTime);
			setInterval(function(){
				$("#timeShow").html(showTime);
			},1000);
			
			
			webSocket();
			
		});
		
		/*计算当前时间*/
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
		
		function webSocket(){
			
			var websocket = null;  
		    //判断当前浏览器是否支持WebSocket  
		    if ('WebSocket' in window) {
		    	debugger;
				var url = "ws://"+urlPath+"LSBoardWebSocket";
		        websocket = new WebSocket(url);
		    }  
		    else {
		        alert('当前浏览器不支持websocket，请更换浏览器！');  
		    }
		    websocket.onopen = function()
		    {
		       // Web Socket 已连接上，使用 send() 方法发送数据
		    	websocket.send("基础");
		    };
				
		    websocket.onmessage = function (evt) 
		    { 
		       var list = eval('('+evt.data+')');
		      debugger;
		       for(var key in list){
		    	   var a = "#"+key;
		    	   $(a).html(list[key]);
		       }
		       
		    };
				
		    websocket.onclose = function()
		    { 
		       // 关闭 websocket
		    };
			
		}
		
		</script>


  </body>
</html>
