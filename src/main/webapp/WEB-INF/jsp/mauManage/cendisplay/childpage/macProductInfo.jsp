<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>机台生产情况</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/echarts.min.js"  type="text/javascript"></script>
	
	<script type="text/javascript">
	
	var basePath  = '<%=basePath%>';
	var seqName = "";
	var seqCode = "";
	$(function(){
		$(window).resize(function() {  
			window.location.reload();	  
		});
		setEcharts();
	});
	
	
	
	setInterval("setEcharts()", 10000);
	
	function setEcharts(){
		var url = basePath+"rest/mauNewDislayManageAction/getProDepMac";
		$.post(url,{/* seqCode:seqCode */},function(data){
			if(data.success){
				if(data.data != null){
					myEcharts(data.data);
				}else{
					var vo = {};
					vo.title="";
					vo.legends = [];
					myEcharts(vo);
				}
			}else{
				alert("查询失败");
				var vo = {};
				vo.title="";
				vo.legends = [];
				myEcharts(vo);
			}
		},"json");
	}
	
	//工单生产进度
	var series =[];
	function myEcharts(vo){
		var myChart = echarts.init(document.getElementById('schedule'));
		createSeries(vo);
		option = {
			textStyle: {
		        	color: '#F2F2F2',
		      	},
		    title : {
		        text: vo.title,
		        textStyle: {
		        	color: '#FFF300',
		      	},
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		   /*  legend: {
		        data:vo.legends,
		        textStyle:{
		        	color:['#DA70D6','#FF00FF']
		        }
		    }, */
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    toolbox: {
		        show : true,
		    },
		    calculable : true,
		    xAxis : [
		        {
		        	name : '机台',
		            type : 'category',
		        	data : vo.legends,
		        	nameTextStyle:{
		        		color:'#FCFCFC',
		        	}
		        }
		    ],
		    yAxis : [
		        {
		        	name : '完成量(%)',
		            type : 'value'
		        }
		    ],
		   // series :series
		    series :[
		             {
		                 name:'完成量',
		                 type:'bar',
		                 barWidth: '60%',
		                 data:vo.mDataList.完成量
		             }
		             
		             ]
		};
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);

	}
	
	function createSeries(vo){
		if(vo.seriesData == null){
			return ;
		}
		var legend = vo.legends;
		var seriesData = vo.seriesData;
		for(var i = 0 ; i<legend.length;i++){
			var obj = {};
			var name = legend[i];
			obj.name = name;
			obj.type = "bar";
			obj.label = {normal: {
				                show: true,
				                position: 'inside'
				            }
						};
			obj.stack = name;
			var data = seriesData[name];
			if(data.type == "left"){
				obj.data = data.data;
			}else if(data.type == "right"){
				obj.yAxisIndex = 1;
				obj.data = data.data;
			}
			series[i] = obj;
		}
	}
	
</script>

<style type="text/css">
	ul{
		list-style-type: none;
	}
	#info{
		padding: 0;
		width: 97%;
		height: 500px;
		margin: 0 auto;
		background-color: #224B81;
		border-radius:8px;
	}
	.mr_top{
		width: 100%;
		height: 450px;
	}
	
	#seqCode{
		width: 90%;
		margin: 0 auto;
		padding: 0;
	}
	#seqCode li{
		width: 100%;
		text-align: center;
		margin: 0;
		padding: 0;
	}
	#seqCode li span{
		display: inline-block;
		width: 65px;
		height: 35px;
		line-height: 35px;
		background-color: #224B81;
		margin: 0 1px;
		color: white;
		border-radius: 5px;
		cursor: pointer;
		font-size: 14px;
		overflow: hidden;
	}
	#seqCode li span:hover{
		color: red;
	}
	#seqCode li span.selected{
		color: red;
	}
</style>
	

  </head>
  
  <body>
    <ul id="seqCode">
		
	</ul>

	<div id="info">
		<div Style="width: 100%;height: 500px;" id="schedule"></div>
	</div>
	
	
  </body>
</html>
