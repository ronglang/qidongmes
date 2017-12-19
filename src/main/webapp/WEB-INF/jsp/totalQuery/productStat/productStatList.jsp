<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>生产统计报表</title>
    
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
    <link href="<%=basePath %>app/css/totalQuery/query.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    
	<script type="text/javascript">
		
		var basePath = "<%=basePath%>";
		
		$(function(){
			$("input[name='start']").ligerDateEditor(
					{format:'yyyy-MM-dd hh:mm:ss',showTime:true,width:180,height:25}
					);
			$("input[name='end']").ligerDateEditor({format:'yyyy-MM-dd hh:mm:ss',showTime:true,width:180,height:25});
			
			
			var url =  basePath+'rest/productStatReportVoManageAction/getProductStat';
			grid(url);
			
			$("#sorts .span1").click(function(){
				var arr_data = {};
				var course_code = $("input[name='course_code']").val();
				var mac_name = $("input[name='mac_name']").val();
				var head_ggxh = $("input[name='head_ggxh']").val();
				var product_state = $("input[name='product_state']").val();
				
				var end = $("input[name='end']").val();
				var start = $("input[name='start']").val();
				if(course_code != "" && course_code != null){
					arr_data.course_code = course_code;
				}
				if(mac_name != "" && mac_name != null){
					arr_data.mac_name = mac_name;
				}
				if(head_ggxh != "" && head_ggxh != null){
					arr_data.head_ggxh = head_ggxh;
				}
				if(product_state != "" && product_state != null){
					arr_data.product_state = product_state;
				}
				if(start != "" && start != null){
					arr_data.start = start;
				}
				if(end != "" && end != null){
					arr_data.end = end;
				}
				
				if(course_code == "" && mac_name == "" && head_ggxh == "" && product_state == ""  && start == "" && end == ""){
					return;
				}
				var param = JSON.stringify(arr_data);
				var url =  basePath+'rest/productStatReportVoManageAction/getProductStat?param='+param;
				grid(url);
			});
			
			//显示所有数据
			$("#sorts span.s2").click(function(){
				var arr_data = {};
				param = JSON.stringify(arr_data);
				var url =  basePath+'rest/productStatReportVoManageAction/getProductStat?param='+param;
				grid(url);
			});
			
		});
		
		function grid(url){
			//debugger;
			
			window['g'] =
		    $("#maingrid").ligerGrid({
		        height: '99%',
		        title:'生产统计报表',
		        url: url,
		       	checkbox: true,
		        columns: [
	    			{ display: '工单编号', name: 'course_code',width:150},
	    	        { display: '机台名称', name: 'mac_name',width:80},
	    	        { display: '工序名称', name: 'seq_name',width:80},
	    	        { display: '轴名称', name: 'axis_name',width:130},
	    	        { display: '产品规格', name: 'head_ggxh',width:80},
	    	        { display: '生产状态', name: 'product_state',width:50},
	    	        { display: '完成率(%)', name: 'comp_rate',width:50},
	    	        { display: '原料类型', name: 'mater_type',width:50},
	    	        { display: '原料规格型号', name: 'mater_ggxh',width:100},
	    	        { display: '计划长度', name: 'part_len',width:80},
	    	        { display: '半成品长度', name: 'semi_product_len',width:80},
	    	        { display: '计划开始时间', name: 'plan_start_time',width:130},
	    	        { display: '实际开始时间', name: 'fact_start_time',width:130},
	    	        { display: '计划结束时间', name: 'plan_end_time',width:150},
	    	        { display: '实际结束时间', name: 'fact_end_time',width:150},
	    	        { display: '计划来料时间', name: 'plan_incoming_time',width:150},
	    	        { display: '实际来料时间', name: 'fact_incoming_time',width:150},
	    	        { display: '工作时间', name: 'work_date',width:80},
	    	        { display: '颜色', name: 'color',width:50}
		        ],
		        toolbar : {
	    			items : [
	    			         { text : '导出', click : exportClick, icon : 'print', },
	    			         ]
	    		},
		        
		        rownumbers: true,
		        enabledEdit: true,
		        pageSize:20,
		        pageSizeOptions:[ 20, 30, 40, 50]
		    });
		    $("#pageloading").hide();
		}
		
		function exportClick(){
			
			var arr_data = {};
			var course_code = $("input[name='course_code']").val();
			var mac_name = $("input[name='mac_name']").val();
			var head_ggxh = $("input[name='head_ggxh']").val();
			var product_state = $("input[name='product_state']").val();
			
			var end = $("input[name='end']").val();
			var start = $("input[name='start']").val();
			if(course_code != "" && course_code != null){
				arr_data.course_code = course_code;
			}
			if(mac_name != "" && mac_name != null){
				arr_data.mac_name = mac_name;
			}
			if(head_ggxh != "" && head_ggxh != null){
				arr_data.head_ggxh = head_ggxh;
			}
			if(product_state != "" && product_state != null){
				arr_data.product_state = product_state;
			}
			if(start != "" && start != null){
				arr_data.start = start;
			}
			if(end != "" && end != null){
				arr_data.end = end;
			}
			param = JSON.stringify(arr_data);
			var url =  basePath+'rest/productStatReportVoManageAction/exportProductStat?param='+param;
			window.open(url,"_self");
			
		}
		
		
	</script>
	<style type="text/css">
		#con_select{
			width:94%;
			height:100px;
			margin:10px auto;
		}
		#con_select span{
			float: left;
		}
		#con_select .s_input{
			float: left;
			margin-left:30px;
			margin-top:10px;
			font-size:18px;
			height:40px;
		}
		#con_select .s_input input{
			width:170px;
			height:25px;
			font-size:14px;
		}
		
		
		#sorts{
			float: left;
			width:300px;
			height:40px;
			line-height:40px;
			text-align:center;
			
		}
		#sorts span{
			display: inline-block;
			text-align:center;
			width:80px;
			height:25px;
			line-height:25px;
			background-color: #2475CC;
			border-radius:5px;
			margin-top:8px;
			color:white;
			cursor:pointer;
			margin-left:50px;
		}
		#sorts span:hover{
			background-color: #31C4F5;
		}
		#sorts span.s2{
			display: inline-block;
			text-align:center;
			height:25px;
			line-height:25px;
			background-color: #EEA71D;
			
			border-radius:5px;
			margin-top:8px;
			color:white;
			cursor:pointer;
			margin-left:50px;
		}
		#sorts span.s2:hover{
			background-color: #F0A77B;
		}
	
	</style>
	
  </head>
  
  <body>
    
    <div class="l-loading" style="display:block" id="pageloading"></div>
	<div class="l-clear"></div>
    
    <div id="con_select" >
		<div class="s_input">
			<span>工单编号：</span>
			<input type="text" name="course_code"/>
		</div>
		<div class="s_input">
			<span>机台名称：</span>
			<input type="text" name="mac_name" />
		</div>
		<div class="s_input">
			<span>产品规格：</span>
			<input type="text" name="head_ggxh" />
		</div>
		<div class="s_input">
			<span>生产状态：</span>
			<input type="text" name="product_state" />
		</div>
		
		<div class="s_input">
			<span>实际开始时间：</span> 
			<input type="text" name="start" />
		</div>
		<div style="float: left;margin-top:10px;font-size:18px;margin-left:5px;">—</div>
		<div class="s_input" style="margin-left:5px;">
			<input type="text" name="end" style="float:left;"/>
		</div>
		<div id="sorts">
			<span class="span1">搜索</span>
			<span class="s2">显示所有</span>
		</div>
	</div>
    
    <div id="liger">
    	<div id="maingrid"></div>
    </div>
    
    
	<div style="display:none;"></div>
    
  </body>
</html>
