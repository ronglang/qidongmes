<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工单生产进度</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath%>core/js/echarts.min.js" type="text/javascript" charset="utf-8"></script>
		
	<script type="text/javascript">
	var basePath = "<%=basePath%>";
	var areasX=[];
	var dataList =[];
	
		$(function(){
			$.ajax({
							
				url: basePath+'rest/plaCourseManageAction/getTodayComplete',
				dataType: 'json',
				type: 'POST',
				success:function(list){
					var data = list;
					if (list == null || list == "") {
						myEcharts(list);
					}else{
						debugger;
						areasX = data.areas ;
						dataList = data.dataList;
						myEcharts(data);
					}
				},
				error:function(){
					alert("服务器出错！");
				}
				
			});
			webSokect();
			
		});
		
		function webSokect(){
			
			var urlPirfex = basePath.substring(7, basePath.length);
			var url = "ws://"+urlPirfex+"plaCourseWebSocket_2";
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
				debugger;
				var josnBean = eval('('+event.data+')');
				if (josnBean == null || josnBean == "") {
					var list = {};
					areasX = [] ;
					dataList = [];
					myEcharts(list);
				}else{
					areasX = josnBean.areas ;
					dataList = josnBean.dataList;
					myEcharts(josnBean);
				}
				
			}
			//传的参数
			function onOpen(event) { 
				webSocket.send("1");
			}

			function onError(event) {
			}
		}
		
		//工单生产进度
		function myEcharts(list){
			
			var myChart = echarts.init(document.getElementById('schedule'));
			option = {
				color: [ '#F56A4B','#FFFC01'],
				textStyle: {
			        	color: '#F2F2F2',
			      	},
			    title : {
			        text: '工单生产进度',
			        textStyle: {
			        	color: '#FFF300',
			      	},
			        //subtext: '纯属虚构'
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        //data:['计划完成情况','实际完成情况'],
			        data:['实际完成情况'],
			        textStyle: {
			        	color: '#F2F2F2',
			      	},
			    },
			    toolbox: {
			        show : true,
			    },
			    calculable : true,
			    xAxis : [
			        {
			        	name : '工单号',
			            type : 'category',
			            data : areasX,
			            //data : ['ghs001', 'ghs002', 'ghs003', 'ghs004', 'ghs005', 'ghs006', 'ghs007', 'ghs008', 'ghs009', 'ghs010', 'ghs011', 'ghs012'],
			        }
			    ],
			    yAxis : [
			        {
			        	name : '百分比(%)',
			            type : 'value'
			        }
			    ],
			    series : [
			        /*
			        {
			            name:'计划完成情况',
			            type:'bar',
			            barGap: 0,
			            data:[61.0, 27.9, 37.0, 23.2, 25.6, 76.7, 55.6, 72.2, 32.6, 20.0, 65.4, 36.3],
			            label: {
				            normal: {
				                show: true,
				                position: 'top'
				            }
				        },
			        },*/
			        {
			            name:'实际完成情况',
			            type:'bar',
			            barWidth: '40%',
			            data : dataList,
			            //data:[53.6, 16.9, 19.0, 22.4, 22.7, 70.7, 45.6, 52.2, 48.7, 18.8, 60.0, 26.3],
			            label: {
				            normal: {
				                show: true,
				                position: 'top'
				            }
				        },
			        }
			        
			    ]
			};
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
	
		}
	</script>
	
	<style type="text/css">
		#info{
			padding: 0;
			width: 98%;
			height: 450px;
			margin: 0 auto;
			background-color: #224B81;
			border-radius:8px;
		}
		.mr_top{
			width: 100%;
			height: 450px;
		}
	</style>
	

  </head>
  
  <body>
    
    <div id="info">
		<div class="mr_top" id="schedule"></div>
	</div>
    
  </body>
</html>
