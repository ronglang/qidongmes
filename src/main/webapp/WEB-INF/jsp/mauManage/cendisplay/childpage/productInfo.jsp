<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>订单生产详情页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath%>core/js/echarts.min.js" type="text/javascript" charset="utf-8"></script>
		
	<script type="text/javascript">
	var basePath = "<%=basePath%>";
	var urlPirfex = basePath.substring(7, basePath.length);
		$(function(){
			initAjaxData();
			myWebsockt();
		});
		function initAjaxData(){
			var url ="<%=basePath%>rest/sellContractManageAction/getSellContractSurvey";
			$.post(url,{},function(data){
				myEcharts(data);
			},"json");
			
		}
		
		function myEcharts(data){
			var list = {};
			list.legend = eval("("+data.legend+")");
			list.dataList =  eval("("+data.dataList+")");
			
			var myChart = echarts.init(document.getElementById('productInfo'));
			
			option = {
				color: ['#FB6A93','#1CE7D3','#03FC43','#FFD66A'],
				title : {
			        text: '生产通知单生产情况',
			        textStyle: {
			        	color: '#FFF300',
			      	},
			      	left: 'center'
			    },
			    tooltip: {
			        trigger: 'item',
			        formatter: "{a} <br/>{b}: {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        x: 'left',
			       // data:['未下发订单','以生产订单','生产中订单','未生产订单'],
			      	 data:list.legend,
			        textStyle: {
			        	color: ['#FB6A93','#1CE7D3','#03FC43','#FFD66A'],
			      	},
			    },
			    series: [
			        {
			            name:'访问来源',
			            type:'pie',
			            radius: ['50%', '70%'],
			            avoidLabelOverlap: false,
			            label: {
	                normal: {
	                    formatter: '{d}%',
	                    borderWidth: 1,
	                    borderRadius: 4,
	                    rich: {
	                        d: {
	                            fontSize: 20,
	                            lineHeight: 33
	                        },
	                    }
	                }
	            },
	            /*		data: [
			                {value:335, name:'未下发订单'},
			                {value:310, name:'以生产订单'},
			                {value:234, name:'生产中订单'},
			                {value:135, name:'未生产订单'},
			            ], */
			            
			            data:list.dataList,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};
			
			
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
		}
		
		function myWebsockt(){
			
			var url = "ws://"+urlPirfex+"sellContractSurveyWebSocket";
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
				var map = eval('('+event.data+')');
				myEcharts(map);
				
			}
			//传的参数
			function onOpen(event) { 
				webSocket.send('机台状态数');
			}
		
			function onError(event) {
				
			}
		}
		
	</script>
	<style type="text/css">
		#info{
			padding: 0;
			width: 93%;
			height: 450px;
			margin: 0 auto;
			background-color: #224B81;
			border-radius:8px;
		}
		#productInfo{
			width: 100%;
			height: 450px;
		}
		
	</style>
	

  </head>
  
  <body>
    
    <div id="info">
		<div id="productInfo"></div>
	</div>
    
    
  </body>
</html>
