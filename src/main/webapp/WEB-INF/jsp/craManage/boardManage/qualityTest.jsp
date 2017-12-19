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
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>core/css/board/qualityTest.css"/>
	<script src="<%=basePath%>core/js/jquery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>

  </head>
  
  <body>
    
    <div id="quality">
			<div id="top">
				<div class="left">&nbsp;</div>
				<div class="mid">质检部电子看板</div>
				<div class="right"></div>
			</div>
			<div id="head">
				 <marquee scrollamount="3">【胶料入库检验】物料名称：铜线，型号：DFFF/001 ,检验员：刘韵，检验时间：2017/7/6  17:58</marquee>
			</div>
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th>检验员</th>
					<th>检验类型</th>
					<th>工序</th>
					<th>异常类型</th>
					<th>产品型号</th>
					<th>颜色</th>
					<th>检验时间</th>
					<th>状态</th>
					<th>完成百分比</th>
					<th>检验结果</th>
				</tr>
				<tr>
					<td>张瑶</td>
					<td>异常检验</td>
					<td>绝缘</td>
					<td>火花异常</td>
					<td>LAS-233</td>
					<td>\</td>
					<td>2017-04-09  13:41</td>
					<td>检验完成</td>
					<td>60%</td>
					<td>合格</td>
				</tr>
				<tr>
					<td>刘阳和</td>
					<td>成品检验</td>
					<td>\</td>
					<td>\</td>
					<td>PVF2.0*2.5MM</td>
					<td>黄色</td>
					<td>2017-04-09  13:41</td>
					<td>检验完成</td>
					<td>100%</td>
					<td>合格</td>
				</tr>
				<tr>
					<td>郑和阳</td>
					<td>半成品检验</td>
					<td>挤护套</td>
					<td>皮厚异常</td>
					<td>BCT-600</td>
					<td>黑色</td>
					<td>2017-04-09  13:41</td>
					<td>进行中</td>
					<td>60%</td>
					<td>\</td>
				</tr>
				<tr>
					<td>刘阳和</td>
					<td>成品检验</td>
					<td>\</td>
					<td>\</td>
					<td>PVF2.0*2.5MM</td>
					<td>黄色</td>
					<td>2017-04-09  13:41</td>
					<td>检验完成</td>
					<td>100%</td>
					<td>合格</td>
				</tr>
				<tr>
					<td>刘阳和</td>
					<td>成品检验</td>
					<td>\</td>
					<td>\</td>
					<td>PVF2.0*2.5MM</td>
					<td>黄色</td>
					<td>2017-04-09  13:41</td>
					<td>检验完成</td>
					<td>100%</td>
					<td>合格</td>
				</tr>
				<tr>
					<td>刘阳和</td>
					<td>成品检验</td>
					<td>\</td>
					<td>\</td>
					<td>PVF2.0*2.5MM</td>
					<td>黄色</td>
					<td>2017-04-09  13:41</td>
					<td>检验完成</td>
					<td>100%</td>
					<td>合格</td>
				</tr>
				<tr>
					<td>刘阳和</td>
					<td>成品检验</td>
					<td>\</td>
					<td>\</td>
					<td>PVF2.0*2.5MM</td>
					<td>黄色</td>
					<td>2017-04-09  13:41</td>
					<td>检验完成</td>
					<td>100%</td>
					<td>合格</td>
				</tr>
				<tr>
					<td>刘阳和</td>
					<td>成品检验</td>
					<td>\</td>
					<td>\</td>
					<td>PVF2.0*2.5MM</td>
					<td>黄色</td>
					<td>2017-04-09  13:41</td>
					<td>检验完成</td>
					<td>100%</td>
					<td>合格</td>
				</tr>
				<tr>
					<td>刘阳和</td>
					<td>成品检验</td>
					<td>\</td>
					<td>\</td>
					<td>PVF2.0*2.5MM</td>
					<td>黄色</td>
					<td>2017-04-09  13:41</td>
					<td>检验完成</td>
					<td>100%</td>
					<td>合格</td>
				</tr>
				<tr>
					<td>刘阳和</td>
					<td>成品检验</td>
					<td>\</td>
					<td>\</td>
					<td>PVF2.0*2.5MM</td>
					<td>黄色</td>
					<td>2017-04-09  13:41</td>
					<td>检验完成</td>
					<td>100%</td>
					<td>合格</td>
				</tr>
			</table>
			
		</div>
		
		
	<script type="text/javascript">
		$(document).ready(function(){
			$("#top .right").html(showTime);
			setInterval(function(){
				$("#top .right").html(showTime);
			},1000);
			
		});
		
		//获取时间
		function showTime(){ 
		    var show_day=new Array('星期一','星期二','星期三','星期四','星期五','星期六','星期日'); 
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
		    var now_time=year+'/'+month+'/'+date+'　'+hour+':'+minutes+':'+second+'　'+show_day[day-1]; 
		    
		    return now_time;
		}

	</script>
    
  </body>
</html>
