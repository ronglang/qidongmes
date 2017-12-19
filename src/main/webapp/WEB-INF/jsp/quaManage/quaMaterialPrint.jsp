<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>原材料入库检验单信息打印</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
	
	<style type="text/css">
			
			body,div,table{
				margin: 0px;
				padding: 0px;
				font-size: 14px;
			}
			
			#top{
				width: 100%;
				height: 50px;
				border-bottom: 1px solid gray;
				border-width: 80%;
				margin: 20px auto;
				/*display: none;*/
			}
			#top .empty{
				float: left;
				height: 50px;
				line-height:50px;
				text-decoration:underline;
				color:blue;
				width: 30%;
				text-align: right;
				cursor: pointer;
			}
			#top .empty:hover{
				color:red;
			}
			#top .top_title{
				float: left;
				height: 50px;
				line-height: 50px;
				font-size: 18px;
				text-align: center;
				width: 40%;
				font-weight: bolder;
			}
			#top .top_export{
				float: left;
				height: 50px;
				width: 30%;
				text-align: center;
			}
			#top .top_export button{
				cursor: pointer;
				margin: 10px;
				background-color: #088603;
				border: none;
				border-radius: 5px;
				width: 70px;
				height: 30px;
				color: white;
			}
			#top .top_export button:hover{
				background-color: #4DC44B;
			}
			
				#b_add{
					width: 100%;
					margin: 50px auto;
					border: 1px solid gray;
					border-collapse: collapse;
					
				}
				#b_add tr{
					height: 1cm;
				}
				#b_add tr th{
					text-align: right;
					border: 1px solid gray;
				}
				#b_add tr td{
					border: 1px solid gray;
				}
			@page {
			    margin: 14mm 18mm 10mm; 
			    size: A4;
			    page-break-inside: avoid;
			    page-break-before: always;
			}
			
		</style>
	
  </head>
  
  <body>
    	
    	<div id="top">
			<div class="empty">返回</div>
			<div class="top_title">原材料入库检验单信息打印</div>
			<div class="top_export"><button onclick="prints()">打  印</button></div>
		</div>
		<div id="print_con">
			
			<table border="0" cellspacing="1" cellpadding="0" id="b_add">
				<tr>
					<td colspan="2" style="border-right: none;">&nbsp;</td>
					<td colspan="2" style="text-align: center;border-right: none;border-left: none;">原材料入库检验单</td>
					<td colspan="2" class="time" style="border-left: none;text-align: right;">2017-08-09 12:12:12</td>
				</tr>
		 		<tr class="l-table-edit-tr">
		 			<th>物料类型:</th>
		 			<td >${report.objSort }</td>
		 			<th>物料规格型号:</th>
		 			<td >${report.objGgxh}</td>
		 			<th>颜色:</th>
		 			<td >${report.objColor}</td>
		 		</tr>
		 		<tr>
		 			<th>供货商:</th>
		 			<td>${report.supplyAgent}</td>
		 			<th>批次号:</th>
		 			<td >${report.batchCode}</td>
		 			<th>出厂报告:</th>
		 			<td >${report.isSupplySurey}</td>
		 		</tr>
		 		<tr>
		 			<th>数量:</th>
		 			<td>${report.acount}</td>
		 			<th>单位:</th>
		 			<td>${report.unit}</td>
		 			<th>FRID卡:</th>
		 			<td>${report.rfidCode}</td>
		 		</tr>
		 		<tr>
		 			<th>检测期:</th>
		 			<td>${report.surveyDate}</td>
		 			<th>保质期:</th>
		 			<td>${report.releaseDate}</td>
		 			<th>有效期:</th>
		 			<td>${report.expiryDate}</td>
		 		</tr>
		 		<tr>
		 			<th>检验状态:</th>
		 			<td>${report.reportState }</td>
		 			<th>检验报告单号:</th>
		 			<td>${report.surveyReportCode}</td>
		 			<th>备注:</th>
		 			<td>${report.remark}/td>
		 		</tr>
		 		<tr>
		 			<td colspan="6" style="text-align: center;">
		 				报告单
		 			</td>
		 			
		 		</tr>
		 		<tr style="height: 10cm;">
		 			<td colspan="6">
		 				<img src="<%=basePath %>${report.reportUrl}" title=""/>
		 			</td>
		 		</tr>
		 	</table>
		 	<div id="foot">
		 		<h3></h3>
		 		<div class="up_img">
	 				
	 			</div>
		 	</div>
		</div>
    
    	<script type="text/javascript">
 				
 				$(document).ready(function(){
 					
 					$("#top .empty").click(function(){
 						window.location.href="<%=basePath %>/rest/qualitySurveyReportManageAction/toMaterialPageList";
 					});
 					
 					//自动在打印之前执行 
					window.onbeforeprint = function(){ 
						//$("#top").hide();
					};
					
					//自动在打印之后执行 
					window.onafterprint = function(){ 
						$("#top").hide();
					};

 					more_hide();
 					
 					$("#b_add .time").html(showTime);
 					setInterval(function(){
 						$("#b_add .time").html(showTime);
 					},1000);
 					
 				});
 				
 				function prints(){
//					$("#b_add").css({
//								   'height' : 'auto', //高度自动
//								   'overflow' : 'visible' //在打印之前把这个div的overflow改成全部显示
//								}).jqprint({ operaSupport: true,printContainer: true});
					
					window.print();
				}
 				
 				//设置多余的字影藏
 				function more_hide(){
 					var td = $("#b_add tr td");
 					for(var i = 0;i<td.length;i++){
 						var td_text = td.eq(i);
 						var td_text_len = td_text.html().length;
 						if(td_text_len > 20){
 							td_text.attr("title",td_text.html());
	 						td_text.html(td_text.html().substring(0,20)+"...");
	 					}
 					}
 				}
 				
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
