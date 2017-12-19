<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String date = request.getParameter("date");
	String seqName = request.getParameter("seqName");
%>

<!DOCTYPE html>
<html style="height: 100%">
<head>
<meta charset="utf-8">
	<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript"src="<%=basePath%>core/js/echarts-all-3.js"></script>
	<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
	<style type="text/css">
	#form2 input[name="macName"]{
    	color:red;
    }
    
    #form2 input[name="hours"]{
    	color:red;
    }
    
</style>
</head>
<body style="height: 100%; margin: 0">
	<div class="top"
		style="width: 95%;height:95%;margin:0 auto; margin-top:0;border: 1px solid #EBEBEB;">
		<div id="container"
			style="height: 90%;width: 96%;text-align: center;margin: 0 auto"></div>
		<div style="text-align: center;position: absolute;left: 160px;z-index: 999;buttom: 9px;">
				<form id="form2" action="">
				</form>
		</div>
	</div>
	<script type="text/javascript">
       var cdate = "<%=date%>";
       var cseqName = "<%=seqName%>";
		var dom = document.getElementById("container");
		var myChart = echarts.init(dom);
		var app = {};
		option = null;
		var dataAxis;
		var data,form;
		var yMax = 24;
		var dataShadow = [];
	$(function(){
		$.ajax({
			url :'<%=basePath%>rest/plaDispatchManageAction/getPlanDisPatchCanBar?date='
								+ cdate + "&seqName=" + cseqName,
						type : 'POST',
						dataType : 'JSON',
						success : function(vo) {
							var bean = new Object();
							bean.workDay = cdate;
							forms();
							setDatas(bean);
							item(vo);
						}

					});
		});

		function item(vo){
			dataAxis = vo.barX; //获取X轴数据
			data = vo.barY; //获取Y轴数据
			getChart();
		}
	
		function getBar() {

			option = {
				title : {
					left : 'center',
					text : '机台负荷折线图',
					subtext : 'SMTC'
				},
				tooltip : {
					trigger : 'axis',
				},
				grid : {
					top : '25%',
					containLabel : true
				},
				xAxis : {
					name:'机台名称',
					data : dataAxis,
					type : 'category',
					boundaryGap : true,
				},
				yAxis : {
					name:'工作时间(h)',
					axisLabel : {
						textStyle : {
							color : '#999'
						}
					}
				},
				toolbox : {
					show : true,
					right:'5%',
					feature : {
						saveAsImage : {
							show : true
						}
					}
				},
				dataZoom : [ {
					type : 'inside'
				} ],
				series : [ { // For shadow
					type : 'bar',
					barGap : '-100%',
					barCategoryGap : '40%',
					data : dataShadow,
					animation : false,
				}, {
					type : 'bar',
					itemStyle : {
						normal : {
							label:{show:true},
							color : function(params) {
								var index_color = params.value;
								if (index_color >= 24) {
									return '#EE9A49';
								} else {
									return '#6495ED';
								}

							}
						}
					},
					data : data
				} ]
			};
			return option;
		}

		// Enable data zoom when user click bar.

		function getChart() {
			myChart.on('click', function(params) {
				var bean = new Object();
				bean.macName = params.name;
				bean.hours = params.value;
				setDatas(bean);
			});
			if (getBar() && typeof getBar() === "object") {
				myChart.setOption(getBar(), true);
			}

		}
		
		
		function forms() {
			//创建表单结构 
			form = $("#form2").ligerForm({
				inputWidth: 150,
				labelWidth: 65,
				space: 20,
				fields: [
					{ display: "调度日期", name: "workDay",newline: false,type: "date",editor:{format:"yyyy-MM-dd",
						onchangedate: function (value)
		                 {
							cdate = $("[name=workDay]").val();
							debugger;
		                    $.post("<%=basePath%>rest/plaDispatchManageAction/getPlanDisPatchCanBar",{
		                    	date:cdate,
		                    	seqName:cseqName
		                    },function(vo,textStatus){
		                           item(vo);
		                     },"json");
	             	 }}},
	             	{ display: "机台名称", name: "macName",newline: false,type: "text"},
	             	{ display: "机台负荷", name: "hours",newline: false,type: "text"}
				],
				validate: true
			});
			liger.get('macName').setDisabled();
			liger.get('hours').setDisabled();

		}
		
		function setDatas(bean){
			form.setData(bean);
		}
		
		
	</script>
</body>
</html>

