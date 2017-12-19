<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工单任务完成情况</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/echarts.min.js" type="text/javascript" charset="utf-8"></script>
	
	<script type="text/javascript">
		var basePath = "<%=basePath%>";
		
		var areasX=[];
		var dataList =[];
		$(function(){
			//myEcharts({});
			
			$.ajax({
				
				url: basePath+'rest/plaCourseManageAction/getTodayComplete',
				dataType: 'json',
				type: 'POST',
				success:function(list){
					var data = list;
					if (list == null || list == "") {
						myEcharts(list);
					}else{
						areasX = data.areas ;
						dataList = data.dataList;
						myEcharts(data);
					}
				},
				error:function(){
					alert("服务器出错！");
				}
				
			});
			
			//webSokect();
			
		});
		
		function webSokect(){
			
			var urlPirfex = basePath.substring(7, basePath.length);
			var url = "ws://"+urlPirfex+"plaCourseWebSocket";
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
				//debugger;
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
		
		//工单任务完成情况
		function myEcharts(list){
			//debugger;
				var myChart = echarts.init(document.getElementById('task'));
				option = {
					color: ['#37B7F4'],
					textStyle: {
				        	color: '#37B7F4',
				      	},
				    title : {
				       text: '今天工单生成进度',
				        textStyle: {
				        	color: '#37B7F4',
				      	},
				    },
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				        //data:['计划完成情况','实际完成情况'],
				        data:['实际完成情况'],
				        textStyle: {
				        	color: '#37B7F4',
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
				            //data : ['dgk001','dgk002','dgk003','dgk004','dgk005','dgk006','dgk007','dgk008','dgk009','dgk010','dgk011','dgk012']
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

  </head>
  
  <body>
    
    <div id="task" style="width:100%;height:330px;"></div>
    
  </body>
</html>
