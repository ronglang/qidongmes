<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>机台状态数量饼图</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/echarts.min.js" type="text/javascript" charset="utf-8"></script>
	
	<script type="text/javascript">
	var basePath = '<%=basePath%>';
		$(function(){
			
			initData();
			setInterval(function(){
				//initData();
			}, 60000);
			
		});
		
		function initData(){
			$.ajax({
				url: basePath+"rest/mauIndexManageAction/getMacStatusNum",
				type:'POST',
				dataType:'json',
				success:function(data){
					var vo = eval("("+data+")");
					myEcharts(vo);
				},
				error:function(){
					alert("服务器出错！");
				}
			});
		}
		
		function myEcharts(vo){
			
			if(vo.dataList == null || vo.dataList == ""){
				vo.dataList = ['','','',''];
			}
			if(vo.areas == null || vo.areas == ""){
				vo.areas = [0,0,0,0];
			}
			
			var myChart = echarts.init(document.getElementById('productInfo'));
			
			option = {
				color: ['#FB6A93','#F4A446','#37B7F4','#D12710'],
				title : {
			        //text: '订单生产情况',
			        textStyle: {
			        	color: '#d12710',
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
			        data: vo.areas,
			        textStyle: {
			        	color: ['#FB6A93','#F4A446','#37B7F4','#D12710'],
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
	                    formatter: '{c}',
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
			            data:[
			                {value:vo.dataList[0], name:vo.areas[0]},
			                {value:vo.dataList[1], name:vo.areas[1]},
			                {value:vo.dataList[2], name:vo.areas[2]},
			                {value:vo.dataList[3], name:vo.areas[3]},
			            ],
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
		
	</script>

  </head>
  
  <body>
    
    <div id="productInfo" style="height:330px;width:100%;" ></div>
    
  </body>
</html>
