<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>在单工单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath%>app/css/newpage.css" rel="stylesheet" />
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
		<link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" />
		<link href="<%=basePath%>app/css/newpage.css" rel="stylesheet" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/js/echarts.min.js"  type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
	    
	    <script type="text/javascript">
	    var basePath  = '<%=basePath%>';
	    var form;
	    var grid;
	    	$(function(){
	    		setForm();
	    		queryTable();
	    		//setEchart();
	    	});
	    	
	    	function queryTable(){
	    		var param = initParam();
	    		//debugger;
	    		grid = $("#grid").ligerGrid({
	    			title:"订单列表",
	    			
	    			url : basePath + "rest/plaCourseManageAction/getMorePageList?param="+param,
	    	        rownumbers:true,
	    	        async:false,
	    	        columns: [
	    	            { display: 'id', name: 'id',width:1,  hide:true},
	    	            {display : '订单编号',name : 'orderCode',minWidth : 90},
						{display : '工作单编码',name : 'wsCode',minWidth : 120},
						{display : '当前工序',name : 'nowSeq',minWidth : 150},
						{display : '型号规格',name : 'proGgxh',minWidth : 150},
						{display : '交货日期',name : 'demandDate',minWidth : 100},
						{display : '计划开始时间',name : 'ws_pstime',minWidth : 100},
						{display : '实际开始时间',name : 'ws_fstime',minWidth : 100},
						{display : '计划结束时间',name : 'ws_pdtime',minWidth : 100},
						{display : '实际结束时间',name : 'ws_fdtime',minWidth : 100},
						{hide : '是否完成',name : 'isFinish',minWidth : 1},
	    	            
	    	        ],
	    	        width:'99%',
	    	        height:'60%',
	    	        onSelectRow:function (rowdata, rowid, rowobj){
	    	        },
	    	       
	    		});
	    		setEchart(param);
	    	}
	    	
	    	function initParam(){
	    		var bean = {};
	    		if($("[name=orderCode]").val() !=null && $("[name=orderCode]").val() != ""){
	    			bean.orderCode = $("[name=orderCode]").val();
	    		}
	    		if($("[name=proGgxh]").val() !=null && $("[name=proGgxh]").val() != ""){
	    			bean.proGgxh = $("[name=proGgxh]").val();
	    		}
	    		if($("[name=psstart]").val() !=null && $("[name=psstart]").val() != ""){
	    			bean.psstart = $("[name=psstart]").val();
	    		}
	    		if($("[name=psend]").val() !=null && $("[name=psend]").val() != ""){
	    			bean.psend = $("[name=psend]").val();
	    		}
	    		bean.planFlag = "是";
				var result = JSON.stringify(bean);
				
	    		return result;
	    	}
	    	
	    	function setForm(){
	    		form = $("#form2").ligerForm({
	    			inputWidth: 140, labelWidth: 100, space:10,
		            fields: [
						{ display: "订单编号", name: "orderCode", newline:false, type: "text"},
		            	{ display: "规格型号", name: "proGgxh",  newline:false,type: "text" },
		            	{ display: "计划开始时间  从", name: "psstart", newline:false, type: "date"},
		            	{ display: "到", name: "psend", newline:false, type: "date"},
		            ],	
		        });
	    	}
	    	
	    	function setEchart(param){
	    		var url = basePath + "rest/plaCourseManageAction/getInSystemEchartVo";
	    		$.post(url,{param:param},function(data){
	    			if (data.success) {
	    				createChart(data.data);
					}else{
						var vo = {};
						vo.title = "";
						vo.xAxis = [];
						createChart(vo);
					}
	    		},"json");
	    	}
	    	

			var option;
			var mychart;
			var series = [];
	    	function createChart(vo){
	    		createSeria(vo);
				
				option={
						title: {
					        text: vo.title,
					        left: 'center'
					    },
						 color: ['#3398DB'],
						    tooltip : {
						        trigger: 'axis',
						        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
						            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
						        }
						    },
						    grid: {
						        left: '3%',
						        right: '4%',
						        bottom: '3%',
						        containLabel: true
						    },
						    xAxis : [
						        {
						        	name : '工单编号',
						            type : 'category',
						            data : vo.xAxis,
						            axisTick: {
						                alignWithLabel: true
						            }
						        }
						    ],
						    yAxis : [
						        {
						        	//min :100,
						        	name : '进度',
						            type : 'value'
						        }
						    ],
						    
						    series :series
						
				};
				var myChart = echarts.init(document.getElementById('mychart'));
				// 使用刚指定的配置项和数据显示图表。
				myChart.setOption(option);
	    	}
	    	
	    	function createSeria(vo){
				//debugger;
				var objs = vo.seriesData;
				if(objs == null ){
					return;
				}
				var legends = vo.legends;
				for(var i = 0; i<legends.length;i++){
					var obj = {};
					var name = legends[i];
					obj.name=name;
					obj.type='bar';
					var datas = objs[name];
					obj.data = datas.data;
					series[i] = obj;
				}
				
			}
	    </script>
  </head>
  
  <body>
    <div id="fo" style="text-align: center;width: 100%;position: relative;"  >
		<form id="form2" action="" >
		</form>
		<div class="liger-button" id = 'tj' onclick="queryTable()" style="float: right;position: absolute;right: 200px;top: 1px; ">提交</div>
	</div>
    <div id="grid" style="margin:0; padding:0;overflow:auto;height:450px">
    	
    </div>
    
    <div id="mychart" style="width:98%;height:450px"></div>
  </body>
</html>
