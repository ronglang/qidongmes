<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>异常统计报表</title>
    
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
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    
	<script type="text/javascript">
		var basePath = "<%=basePath%>";
		
		$(document).ready(function(){
			$("input[name='start']").ligerDateEditor({format:'yyyy-MM-dd hh:mm:ss',showTime:true,width:180,height:25});
			$("input[name='end']").ligerDateEditor({format:'yyyy-MM-dd hh:mm:ss',showTime:true,width:180,height:25});
			
			var url = basePath+'rest/exceptionStatVoManageAction/getExceptionVoPage';
			ligerUi(url);
			
			sortClick();
			
			
			//显示所有数据
			$("#sort span.s2").click(function(){
				var url = basePath+'rest/exceptionStatVoManageAction/getExceptionVoPage';
				ligerUi(url);
			});
			
			
		});
		
		function sortClick(){
			$("#sort span").click(function(){
				var arr_data = {};
				var mac_name = $("input[name='mac_name']").val();
				var codename = $("input[name='codename']").val();
				var seq_name = $("input[name='seq_name']").val();
				var axis_name = $("input[name='axis_name']").val();
				var start = $("input[name='start']").val();
				var end = $("input[name='end']").val();
				var state = $("input[name='state']").val();
				if(mac_name != "" && mac_name != null){
					arr_data.mac_name = mac_name;
				}
				if(codename != "" && codename != null){
					arr_data.codename = codename;
				}
				if(seq_name != "" && seq_name != null){
					arr_data.seq_name = seq_name;
				}
				if(axis_name != "" && axis_name != null){
					arr_data.axis_name = axis_name;
				}
				if(start != "" && start != null){
					arr_data.start = start;
				}
				if(end != "" && end != null){
					arr_data.end = end;
				}
				if(state != "" && state != null){
					arr_data.state = state;
				}
				if(mac_name == "" && codename == "" && seq_name == "" && axis_name == "" && state == "" && start == "" && end == ""){
					return;
				}
				var param = JSON.stringify(arr_data);
				var url = basePath+'rest/exceptionStatVoManageAction/getExceptionVoPage?param='+param;
				ligerUi(url);
			});
			
		}
		
		function ligerUi(url){
			window['g'] =
			    $("#maingrid").ligerGrid({
			    	width: '96%',
			        height: '90%',
			        title:'异常统计报表',
			        url: url,
			       	checkbox: true,
			        columns: [
		    			{ display: '机器名称', name: 'mac_name'},
		    	        { display: '异常名称', name: 'codename'},
		    	        { display: '异常信息', name: 'me_info'},
		    	        { display: '异常时间', name: 'me_time' ,width: 130},
		    	        { display: '工序名称', name: 'seq_name'},
		    	        { display: '工单编码', name: 'course_code'},
		    	        { display: '异常轴名称', name: 'axis_name'},
		    	        { display: '异常参数', name: 'exception_param'},
		    	        { display: '异常值', name: 'exception_value'},
		    	        { display: '操作人', name: 'agent_by'},
		    	        { display: '状态', name: 'state'},
		    	        { display: '备注', name: 'remark'},
		    	        { hide:'id',name:'id',width:1}
			        ],
			        toolbar : {
		    			items : [
		    			         { text : '导出', click : exportClick, icon : 'print', },
		    			         {line:true},
		    			         ]
		    		},
			        rownumbers: true,
			        enabledEdit: true
			    });
			    $("#pageloading").hide();
		}
		
		function exportClick(){
			var arr_data = {};
			var mac_name = $("input[name='mac_name']").val();
			var codename = $("input[name='codename']").val();
			var seq_name = $("input[name='seq_name']").val();
			var axis_name = $("input[name='axis_name']").val();
			var start = $("input[name='start']").val();
			var end = $("input[name='end']").val();
			var state = $("input[name='state']").val();
			if(mac_name != "" && mac_name != null){
				arr_data.mac_name = mac_name;
			}
			if(codename != "" && codename != null){
				arr_data.codename = codename;
			}
			if(seq_name != "" && seq_name != null){
				arr_data.seq_name = seq_name;
			}
			if(axis_name != "" && axis_name != null){
				arr_data.axis_name = axis_name;
			}
			if(start != "" && start != null){
				arr_data.start = start;
			}
			if(end != "" && end != null){
				arr_data.end = end;
			}
			if(state != "" && state != null){
				arr_data.state = state;
			}
			
			var param = JSON.stringify(arr_data);
			var url = basePath+'rest/exceptionStatVoManageAction/exportExceptionList?param='+param;
			
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
		
		#export{
			border:none;
			background-color: #8E8989;
			margin:5px 0 0 20px;
			width:90px;
			height:20px;
			cursor: pointer;
			border-radius:5px;
			color:white;
		}
		#sort{
			float: left;
			width:300px;
			height:40px;
			line-height:40px;
			text-align:center;
		}
		#sort span{
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
		#sort span:hover{
			background-color: #31C4F5;
		}
		#sort span.s2{
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
		#sort span.s2:hover{
			background-color: #F0A77B;
		}
	</style>
  </head>
  
  <body>
    <div class="l-loading" style="display:block" id="pageloading"></div>
	<div class="l-clear"></div>
	
	<div id="con_select" >
		<div class="s_input">
			<span>机器名称：</span>
			<input type="text" name="mac_name"/>
		</div>
		<div class="s_input">
			<span>异常名称：</span>
			<input type="text" name="codename" />
		</div>
		<div class="s_input">
			<span>工序名称：</span>
			<input type="text" name="seq_name" />
		</div>
		<div class="s_input">
			<span>异常轴名称：</span>
			<input type="text" name="axis_name" />
		</div>
		<div class="s_input" style="clear: both;">
			<span><span style="visibility: hidden;">隐藏</span>状态：</span>
			<input type="text" name="state" />
		</div>
		<div class="s_input">
			<span><span style="visibility: hidden;">隐</span>时间段：</span> 
			<input type="text" name="start" />
		</div>
		<div style="float: left;margin-top:10px;font-size:18px;margin-left:5px;">—</div>
		<div class="s_input" style="margin-left:5px;">
			<input type="text" name="end" style="float:left;"/>
		</div>
		<div id="sort">
			<span>查  询</span>
			<span class="s2">显示所有</span>
		</div>
	</div>
		
    <div id="liger">
    	<div id="maingrid" style="margin: 0 auto;"></div>
    </div>
    
	<div style="display:none;"></div>
    
    
  </body>
</html>
