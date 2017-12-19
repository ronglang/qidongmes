<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>详情页面</title>
    
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
    <link href="<%=basePath %>app/css/totalQuery/inout.css" rel="stylesheet" type="text/css" />
    
	<script type="text/javascript">
		var basePath = "<%=basePath%>";
		var param = "";
		var pa = ${data};
		var signner = "";
		$(document).ready(function(){
			
			grid();
			
			$("#s_div .c_div input.btn").click(function(){
				signner = $("#s_div .c_div input.text").val();
				grid();
			});
			
			$("#showall").click(function(){
				signner = "";
				grid();
			});
			
		});
		
		function grid(){
			
			if(pa != false){
				param = JSON.stringify(pa);
			}
			
			//$("#s_div .c_div .c_ggxh").html(pa.type+"规格：");
			//$("#s_div .c_div .c_name").html(pa.type+"名称：");
			debugger;
			if(pa.inout == "入库"){
				inStore();
			}else if(pa.inout == "出库"){
				outStore();
			}else{
				other();
			}
			
		}
		
		function inStore(){
			window['g'] =
			    $("#maingrid").ligerGrid({
			        height: '93%',
			        title: pa.type+pa.inout+'详情列表',
			        url: basePath+'rest/statStoreObjVoManageAction/getInOrOutDetail?param='+param+'&signner='+signner,
			       	checkbox: true,
			        columns: [
		    			{ display: pa.type+'规格', name: 'ggxh'},
		    	        { display: pa.type+'名称', name: 'name2'},
		    	        { display: pa.inout+'数量', name: 'count'},
		    	        { display: '单位', name: 'unit'},
		    	        { display: '位置', name: 'address'},
		    	        { display: pa.inout+'时间', name: 'in_date'},
		    	        { display: '仓库签收人', name: 'signer'},
		    	        { display: '质检报告编号', name: 'report_code'},
		    	        { hide:'id',name:'id',width:1}
			        ],
			        rownumbers: true,
			        enabledEdit: true
			    });
			    $("#pageloading").hide();
		}
		
		function outStore(){
			window['g'] =
			    $("#maingrid").ligerGrid({
			        height: '93%',
			        title: pa.type+pa.inout+'详情列表',
			        url: basePath+'rest/statStoreObjVoManageAction/getInOrOutDetail?param='+param+'&signner='+signner,
			       	checkbox: true,
			        columns: [
		    			{ display: pa.type+'规格', name: 'ggxh'},
		    	        { display: pa.type+'名称', name: 'name2'},
		    	        { display: pa.inout+'数量', name: 'count'},
		    	        { display: '单位', name: 'unit'},
		    	        { display: '位置', name: 'address'},
		    	        { display: pa.inout+'时间', name: 'out_date'},
		    	        { display: '仓库签收人', name: 'signer'},
		    	        { display: '质检报告编号', name: 'report_code'},
		    	        { hide:'id',name:'id',width:1}
			        ],
			        rownumbers: true,
			        enabledEdit: true
			    });
			    $("#pageloading").hide();
		}
		//测试专用,排除异常，后续讨论优化改正
		function other(){
			window['g'] =
			    $("#maingrid").ligerGrid({
			        height: '93%',
			        title: pa.type+pa.inout+'详情列表',
			        url: basePath+'rest/statStoreObjVoManageAction/getInOrOutDetail?param='+param+'&signner='+signner,
			       	checkbox: true,
			        columns: [
		    			{ display: pa.type+'规格', name: 'ggxh'},
		    	        { display: pa.type+'名称', name: 'name2'},
		    	        { display: pa.inout+'数量', name: 'count'},
		    	        { display: '单位', name: 'unit'},
		    	        { display: '位置', name: 'address'},
		    	        { display: pa.inout+'时间', name: 'out_date'},
		    	        { display: '仓库签收人', name: 'signer'},
		    	        { display: '质检报告编号', name: 'report_code'},
		    	        { hide:'id',name:'id',width:1}
			        ],
			        rownumbers: true,
			        enabledEdit: true
			    });
			    $("#pageloading").hide();
		}
		
	</script>
	
	<style type="text/css">
		#s_div{
			height:50px;
		}
		#s_div .c_div{
			width:60%;
			height:50px;
			line-height:50px;
			margin: 0 auto;
		}
		#s_div .c_div div{
			float: left;
			margin-left:20px;
		}
		#s_div .c_div div input.text{
			width:130px;
			height:25px;
		}
		#s_div .c_div div span{
			font-size: 15px;
		}
		#s_div .c_div div input.btn{
			display: inline-block;
			width:80px;
			height:25px;
			line-height:25px;
			border:none;
			background-color: #0099CC;
			border-radius:5px;
			color: white;
			cursor: pointer;
			margin-top: 13px;
		}
		#s_div .c_div div input.btn:hover{
			background-color: #40AACE;
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
	</style>
	
  </head>
  
  <body>
    	
     <div class="l-loading" style="display:block" id="pageloading"></div>
	<div class="l-clear"></div>
	<div id="s_div">
		<div class="c_div">
			<div><span class="c_ggxh">仓库签收人：</span><input type="text" name="signner" class="text" /></div>
			<div><input type="submit" name="sub" class="btn" value="查  询" /><input type="button" name="" id="showall" value="显示所有"/></div>
		</div>
		
	</div>
	
		<form id="maingrid"></form>
	
    
   
    <div style="display:none;"></div>
    	
    	
  </body>
</html>
