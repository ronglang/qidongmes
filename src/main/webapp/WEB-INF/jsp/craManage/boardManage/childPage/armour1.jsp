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
	
	<script src="<%=basePath%>core/js/echarts.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	
	<style>
		#paramstable{
			width: 100%;
			text-align: center;
			height: 240px;
		}
		.tr001{
			font-size: 24px;
		    color: #FFFF00;
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
   			 text-align: left;
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
	</style>
	<script>
			var basePath = "<%=basePath%>";
			var urlPath = basePath.substring(7, basePath.length);
    	</script>
  </head>
   <body>
  		<table id="paramstable" border="0">
			<tr>
				<td colspan="2" style="text-align: right" class="tr001">机台：</td>
				<td class="tr001" id="machineName"></td>
				<td class="tr001">规格型号：</td>
				<td class="tr001" id="machineType"></td>
				<td class="tr001">机台完成情况：</td>
				<td class="tr001"><span id="taskPercent"></span>%</td>
				<td></td>
			</tr>
			<tr>
				<td class="tr002">工单编号：</td>
				<td class="tr002" id="courseCode"></td>
				<td></td>
				<td class="tr002">操作人员：</td>
				<td class="tr002" id="operPerson"></td>
				<td class="tr002">日期时间：</td>
				<td colspan="2" class="tr002" id="timeShow"></td>
			</tr>
			<tr>
				<td class="tr004">材料：</td>
				<td class="tr003" id="material"></td>
				<td class="tr004">型号：</td>
				<td class="tr003" id="material_type"></td>
				<td class="tr004">数量：</td>
				<td class="tr003"><span id="material_num"></span>m</td>
				<td class="tr004">剩余米数：</td>
				<td class="tr003"><span id="material_reNum"></span>m</td>
			</tr>
			<tr>
				<td class="tr004">计划米数：</td>
				<td class="tr003"><span id="partLen"></span>m</td>
				<td class="tr004">已生产米数：</td>
				<td class="tr003"><span id="semiLen"></span>m</td>
				<td class="tr004">轴名称：</td>
				<td class="tr003" id="axis_name"></td>
				<td class="tr004">生产段长：</td>
				<td class="tr003" id="partLen">未知</td>
			</tr>
			<tr>
				
				<td class="tr004">产品型号：</td>
				<td class="tr003" id="product_type"></td>
				<td class="tr004">放线张力：</td>
				<td class="tr003"><span id=""></span>m</td>
				
				
				<td class="tr004">前段计米：</td>
				<td class="tr003"><span id="kz_speed"></span>m</td>
				<td class="tr004">后段计米：</td>
				<td class="tr003"><span id="kz_takeLine_power"></span>m</td>
			</tr>
			<tr>
				<td class="tr004">储线张力：</td>
				<td class="tr003"><span id="kz_layLine_power"></span>N</td>
				<td class="tr004">主机速度：</td>
				<td class="tr003"><span id="kz_master_speed"></span>m/s</td>
				<td class="tr004">目标米数：</td>
				<td class="tr003"><span id="ls_anneal"></span>m</td>
				<td class="tr004">机台耗能：</td>
				<td class="tr003"><span id="ls_anneal"></span>度</td>
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
					var url = "ws://"+urlPath+"KZBoardWebSocket";
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
			       for(var key in list){
			    	   var a = "#"+key;
			    	   $(a).html(list[key]);
			       }
			       
			       
			       
			       //$("#head .h3").html(msg);
			    };
					
			    websocket.onclose = function()
			    { 
			       // 关闭 websocket
			    };
				
			}
			
		</script>
  </body>
</html>
