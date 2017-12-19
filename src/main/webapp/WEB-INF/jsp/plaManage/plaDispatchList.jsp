<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html style="height: 100%">

	<head>
		<meta charset="utf-8">
		<script type="text/javascript" src="<%=basePath%>core/js/echarts-all-3.js"></script>
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
		<style>
			.l-box-dateeditor-monthselector { display:block; }
			
		</style>
		
	</head>
	
	<body style="height: 100%; margin: 0;text-align: center;position: relative;">
			<div style="text-align: center;position: absolute;left: 150px;z-index: 999;top: 9px;">
				<form id="form2" action="">
				</form>
				<div class="liger-button" onclick="search()" style="float: right;position: absolute;left: 300px;top: 1px; ">搜索</div>
			</div>
			<div id="container" style="height: 95%;width:95%;padding-bottom: 0px; position: absolute;left: 40px; "></div>
		<script type="text/javascript">
			var dom = document.getElementById("container");
			var myChart = echarts.init(dom);
			var app = {};
			var list,series,legends,fdate;
			option = null;
			var cellSize = [150, 90];
			var pieRadius = 30;
			var i = 0;	
			
			$(function(){
				$.ajax({
					url :'<%=basePath %>rest/plaDispatchManageAction/getPlanDisPatchCanlenderPie',
					type:'POST',
					dataType:'JSON',
					success:function(vo){
						series = vo.series; //获取图数据
						legends = vo.legend; //获取图例
						//alert(legends);
						forms();
						getChart();
					}
					
				});
				
			});
			
			
			
			function search(){
				var month = $("[name=mounth]").val();
				getChart(month);
			}
			
			
			function fmtDate(obj,fmt){
			    var date =  new Date(obj);
			    var y = 1900+date.getYear();
			    var m = "0"+(date.getMonth()+1);
			    var d = "0"+date.getDate();
			    if(fmt == "yyyy-MM-dd"){
				    return y+"-"+m.substring(m.length-2,m.length)+"-"+d.substring(d.length-2,d.length);
			    }else if(fmt == "yyyy-MM"){
			    	return y+"-"+m.substring(m.length-2,m.length);
			    }
			    
			}

			function forms() {
				//创建表单结构 
				form = $("#form2").ligerForm({
					inputWidth: 200,
					labelWidth: 65,
					space: 100,
					fields: [
						{ display: "查看月份", name: "mounth", newline: false, type: "date",editor:{format:"yyyy-MM"}}
					],
					validate: true
				});

			}

			function getVirtulData() {
				
				var date = +echarts.number.parseDate(fdate+"-01");
				var ds = fdate.split("-");
				var mth = parseInt(ds[1])+1;
				var fd ="";
				if(mth<10){
					fd = ds[0]+"-0"+mth+"-01";
				}else{
					fd = ds[0]+"-"+mth+"-01";
				}
				var end = +echarts.number.parseDate(fd);
				//debugger;
				var dayTime = 3600 * 24 * 1000;
				var data = [];
				for(var time = date; time < end; time += dayTime) {
					data.push([
						echarts.format.formatTime('yyyy-MM-dd', time),
						Math.floor(Math.random() * 10000)
					]);
				}
				return data;
			}

			function getPieSeries(scatterData, chart) {
					debugger;
				return echarts.util.map(scatterData, function(item, index) {
					var center = chart.convertToPixel('calendar', item);
					var date = scatterData[i][0];
					debugger;
					i++;
					if(series[date]){
						return {
							id: index + 'pie',
							type: 'pie',
							center: center,
							radius: '100%',
							label: {
								normal: {
									formatter: '{c}',
									position: 'inside'
								}
							},
							radius: pieRadius,
							data:series[date],
						};
					}else{
						return {
							id: index + 'pie',
							type: 'pie',
							center: center,
							radius: '100%',
							label: {
								normal: {
									formatter: '{c}',
									position: 'inside'
								}
							},
							radius: pieRadius,
							data:[
							      { name: '', value: '' }
							      ]
						};
					}
				});
			}

			function getPieSeriesUpdate(scatterData, chart) {
				debugger;
				return echarts.util.map(scatterData, function(item, index) {
					var center = chart.convertToPixel('calendar', item);
					return {
						id: index + 'pie',
						radius: '100%',
						center: center
					};
				});
			}

			//var scatterData = getVirtulData();

			
			function getPie(date){
				option = {
				title: {
					text: '计划调度',
					x:'center'
				},
				tooltip: {},
				legend: {
					data: legends,
					bottom: 20
				},
				//color:['#141414','#B03060','#BF3EFF','#6176e2','#8b7dc9','#50b495','#f1ad58','#EE30A7','#5cc8c8','#ff5353'],
				calendar: {
					top: 'middle',
					left: 'center',
					orient: 'vertical',
					cellSize: cellSize,
					yearLabel: {
						show: false,
						textStyle: {
							fontSize: 30
						}
					},
					toolbox: {
				        // y: 'bottom',
				        feature: {
				            magicType: {
				                type: ['stack', 'tiled']
				            },
				            dataView: {},
				            saveAsImage: {
				                pixelRatio: 2
				            }
				        }
				    },
					dayLabel: {
						margin: 20,
						firstDay: 1,
						nameMap: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
					},
					monthLabel: {
						show: true
					},
					range: date
				},
				series: [{
					id: 'label',
					type: 'scatter',
					coordinateSystem: 'calendar',
					symbolSize: 1,
					label: {
						normal: {
							show: true,
							formatter: function(params) {
								return echarts.format.formatTime('dd', params.value[0]);
							},
							offset: [-cellSize[0] / 2 + 10, -cellSize[1] / 2 + 10],
							textStyle: {
								color: '#000',
								fontSize: 14
							}
						}
					},
					data: getVirtulData()
				}]
			};
			
			return option;
			
			}
			


		function getChart(month){
			i = 0;
			if(!month){
				//debugger;
				var date = new Date();
				var fmt = "yyyy-MM";
				fdate = fmtDate(date,fmt);
				
				if(!app.inNode) {
					var pieInitialized = "";
					setTimeout(function() {
						pieInitialized = true;
						myChart.setOption({
							series: getPieSeries(getVirtulData(), myChart)
						});
					}, 10);

					app.onresize = function() {
						if(pieInitialized) {
							myChart.setOption({
								series: getPieSeriesUpdate(getVirtulData(), myChart)
							});
						}
					};
				};
				if(getPie(fdate) && typeof getPie(fdate) === "object") {
					myChart.setOption(getPie(fdate), true);
				}
				
			}else{
				//debugger;
				fdate = month;
				if(!app.inNode) {
					var pieInitialized = "";
					setTimeout(function() {
						pieInitialized = true;
						myChart.setOption({
							series: getPieSeries(getVirtulData(), myChart)
						});
					}, 10);

					app.onresize = function() {
						if(pieInitialized) {
							myChart.setOption({
								series: getPieSeriesUpdate(getVirtulData(), myChart)
							});
						}
					};
				};
				if(getPie(month) && typeof getPie(month) === "object") {
					myChart.setOption(getPie(month), true);
				}
				
				
				
			}
			
			
		}
		
		myChart.on('click', function (param) {
            //alert(param.data.date+"+"+param.data.name+"+"+param.data.value);
            // TODO 已经获取了饼图的基础数据，接下来跳转页面和传值
            var width = document.body.clientWidth;
			var heigth = document.body.clientHeight;
            window.open('<%=basePath %>rest/plaDispatchManageAction/toChartPage?date='+param.data.date+'&seqName='+param.data.name, "机台负荷折线图",'height='+heigth+', width='+width);
 		});
			
		</script>
	</body>

</html>
