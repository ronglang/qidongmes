<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>OEE图表查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/js/echarts.min.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    <link href="<%=basePath %>app/css/totalQuery/query.css" rel="stylesheet" type="text/css" />
    
    <script type="text/javascript">
    var flag=1;
    var option;
	var myChart;
	var flag;
	var labelOption = {
    normal: {
	        show: true,
	        position: 'top',
	        distance: 15,
	        align: 'left',
	        verticalAlign: 'middle',
	        rotate: 45,
	        formatter: '{c}  {{a}}',
	        fontSize: 16,
	        rich: {
	            name: {
	                textBorderColor: '#fff'
	            }
	        }
	    }
	};
    	$(function(){
    		$("#start").ligerDateEditor(
    				{format:'yyyy-MM-dd',showTime:true,width:180,height:25}
    				);
    		$("#end").ligerDateEditor({format:'yyyy-MM-dd',showTime:true ,width:180,height:25});
    		$("#createDate").ligerDateEditor({format:'yyyy-MM-dd',showTime:true ,width:180,height:25});
    		
    		$("#time").hide();
    		
    	});
    	
    	function getType(){
    		$("#chart").hide();
    		flag = $("#select option:selected").val();
			if(flag==2){
				$("#time").show();
				$("#mac").hide();
			} else if(flag==1){
				$("#time").hide();
				$("#mac").show();
				var url = "<%=basePath %>rest/mauOEEManageAction/getMacCodes";
				$.post(url,{},function(data){
					if(data.success){
						$("#maccode").empty(); 
						var h = "";
						var list = data.data;
						for(var i=0;i<list.length;i++){
							h += "<option>"+list[i]+"</option>";
						}
						$("#maccode").append(h);
					}
				},"json");
			}
    	}
    	
    	function getChart(){
    		
    		$("#chart").show();
			if(flag==1){
				var createDate = $("#createDate").val();
				if(createDate==null && createDate ==""){
					$.ligerDialog.confirm("请选择时间");
					return;
				}
				var bean = {};
				bean.createDate = createDate;
				var param = JSON.stringify(bean);
				var url = "<%=basePath %>rest/mauOEEManageAction/getAllMacChart";
				$.post(url,{param:param},function(data){
				if(data.success){
					setChart(data.data);
				}else{
					alert(data.msg)
				}
			},"json");
			}else if(flag==2){
				var start = $("#start").val();
				var end = $("#end").val();
				var macCode = $("#maccode option:selected").text();
				var bean = {};
				bean.start = start;
				bean.end = end;
				bean.macCode = macCode;
				var param = JSON.stringify(bean);
				var url = "<%=basePath %>rest/mauOEEManageAction/getSingleMacChart";
				$.post( url,{param:param},function(data){
					if (data.success) {
					var vo = data.data;
					setChart(vo);
				}else{
					alert(data.msg);
				}
			},"json");
			}
		}
    	
    	function setChart(vo){
			if(flag==1){
				option = {
			 title: {
			        text: vo.titleName
			    },
			    tooltip: {
			        trigger: 'axis'
			    },
		    color: ['#003366', '#006699', '#4cabce', '#e5323e'],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		        data: vo.legend
		    },
		    toolbox: {
		        show: true,
		        orient: 'vertical',
		        left: 'right',
		        top: 'center',
		        feature: {
		            mark: {show: true},
		            dataView: {show: true, readOnly: false},
		            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
		            restore: {show: true},
		            saveAsImage: {show: true}
		        }
		    },
		    calculable: true,
		    xAxis: [
		        {
		            type: 'category',
		            axisTick: {show: false},
		            data: vo.xlist
		        }
		    ],
		    yAxis: [
		        {
		            type: 'value'
		        }
		    ],
		    series: [
		        {
			            name:vo.legend[0],
			            type:'bar',
			            label: labelOption,
			            data:vo.lineList[0]
			        },
		        {
			            name:vo.legend[1],
			            type:'bar',
			            label: labelOption,
			            data:vo.lineList[1]
			        },
		        {
			            name:vo.legend[2],
			            type:'bar',
			            label: labelOption,
			            data:vo.lineList[2]
			        },
		        {
			            name:vo.legend[3],
			            type:'bar',
			            label: labelOption,
			            data:vo.lineList[3]
			        }
		      
		    ]
		};
			}else if(flag ==2){
				option = {
						 title: {
						        text: vo.titleName
				},
			    tooltip: {
			        trigger: 'axis'
			    },
			    legend: {
			        data:vo.legend
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    toolbox: {
			    	 show: true,
				        orient: 'vertical',
				        left: 'right',
				        top: 'center',
				        feature: {
				            mark: {show: true},
				            dataView: {show: true, readOnly: false},
				            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
				            restore: {show: true},
				            saveAsImage: {show: true}
				        }
			    },
			    xAxis: {
			        type: 'category',
			        boundaryGap: false,
			        data: vo.xlist
			    },
			    yAxis: {
			        type: 'value'
			    },
			    series: [
			        {
			            name:vo.legend[0],
			            type:'line',
			           // stack: '总量',
			            data:vo.lineList[0],
			            itemStyle : { normal: {label : {show: true}}},
			        },
			        {
			            name:vo.legend[1],
			            type:'line',
			            //stack: '总量',
			            data:vo.lineList[1],
			            itemStyle : { normal: {label : {show: true}}},
			        },
			        {
			            name:vo.legend[2],
			            type:'line',
			          //  stack: '总量',
			            data:vo.lineList[2],
			            itemStyle : { normal: {label : {show: true}}},
			        },
			        {
			            name:vo.legend[3],
			            type:'line',
			           // stack: '总量',
			            data:vo.lineList[3],
			            itemStyle : { normal: {label : {show: true}}},
			        }
			    ]
			};
			}
			var dom = document.getElementById("chart");
	 		myChart = echarts.init(dom);
			myChart.setOption(option);
		}
    </script>
    <style type="text/css">
    	#sort:hover{
    		background-color: #50C1E8;
    	}
    	#showall{
    		border:none;
    		margin-left:20px;
    		background-color: #FFAB95;
    		color:white;
    		width:80px;
    		height:25px;
    		border-radius:5px;
    		cursor: pointer;
    	}
    	#showall:hover{
    		background-color: #F7C6B9;
    	}
    	#t_select{
    		width:100%;
    		height:70px;
    		
    	}
    	#selects{
    		width:15%;
    		height:60px;
    		line-height:60px;
    		display:inline-block;
    		float: left;
    		border-right:1px dashed gray;
    		text-align: center;
    	}
    	#selects select{
    		margin-top:20px;
    		height:27px;
    	}
    	#t_select .con{
    		float: left;
    		height:60px;
    		line-height: 60px;
    		width: 75%;
    	}
    	#t_select .con .con_div2{
    		height:60px;
    		line-height: 60px;
    		width:100%;
    	}
    	#time{
    		float: left;
    	}
    	#mac{
    		float: left;
    	}
    </style>
  </head>
  
  <body>
	
	<div id="t_select" style="margin-top:10px;margin-left:15px;border-bottom: 1px solid gray;">
		<div id="selects">
			<select id="select" onclick="getType()" >
				<option value="1">按时间查询所有</option>
				<option value="2">按机台查询时间段</option>
			</select>
		</div>
		
		<div class="con" style="margin-left:30px;">
			<div class="con_div2" id="time">
				<span>机台编号：</span><select id="maccode" style="width:100px;height:25px;"></select>
				<span style="margin-left:30px;display: inline-block;">时间段：</span>
				<input type="text" name="" id="start" value="">
				<span>—</span>
				<input type="text" name="" id="end"/>
				<input type="button" name="" id="sort" value="查  询" onclick="getChart()"/>
				<input type="button" name="" id="showall" value="显示所有" />
			</div>
			<div class="con_div2" id="mac">
				<span>时间段：</span><input type="text" name="" id="createDate" value="">
				<input type="button" name="" id="sort2" value="查  询"  onclick="getChart()"/>
				<input type="button" name="" id="showall" value="显示所有" />
			</div>
		</div>	
	</div>
		
		<div id="chart" style="height: 500px;width: auto;">
			
			
		</div>
  </body>
</html>
