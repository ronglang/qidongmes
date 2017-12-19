<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>质检呼叫电子看板</title>
    
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
    
    <link href="<%=basePath %>core/css/public.css" rel="stylesheet" type="text/css" />

    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    <script type="text/javascript">
    var grid;
    	$(function(){
    		queryTable();
    	});
    	
    	function queryTable(){
    	 {
    		 var param = initSartch();
    		 grid = $("#maingrid").ligerGrid({
    			 height: '93%',
 				url:'<%=basePath %>rest/qualityAskManageAction/toPageList?param='+param,
 				checkbox: true,
 				columns:[
 				{display: '呼叫类型', name: 'askType'},
				{display: '呼叫位置', name: 'askLocation'},
				{display: '呼叫人', name: 'createBy'},
				{display: '呼叫时间', name: 'createDate' },
				{display: '呼叫状态', name: 'askState'},						
				{display: '完成人', name: 'finishBy'},
				{display: '完成时间', name: 'finishDate'},
 				{display: '操作', name: '',render: function (rowData)
                 {
 					  var h =  '<a href="#" onclick="javascript:toFinish('+ rowData.id+ ');return false;">处理</a>&nbsp;&nbsp;';
 	                    return h;
                   /*   var h = "";
                         h += "<a href=#' onclick='javascript:toDetail(" + rowData.id + ")'>详情</a> ||";
                         h += "<a  href='#' onclick='javascript:toPrint("+rowData.id+")>打印</a> "; 
                     return h; */
                 }},
 				{hide: 'id', name: 'id',width:'1px'}
 				],
 				
      			 rownumbers:true,
     		});	
    		 $("#pageloading").hide();
    	 }
    		
    	}
    	
    	function toFinish(id){
    		var url ="<%=basePath %>rest/qualityAskManageAction/finishAsk?id="+id;
    		$.post(url,{id:id},function(data){
    			if(data.success){
    				queryTable();
    				return;
    			}else {
    				$.ligerDialog.error(data.msg);
    			}
    		},"json");
    	}
    	
    	function initSartch(){
    		var params = {};
    		return JSON.stringify(params);
    	}
    	
    </script>
  </head>
  
  <body>
    <div class="l-loading" style="display:block" id="pageloading"></div>
	<div class="l-clear"></div>
	<div id="maingrid"></div>
	<div style="display:none;"></div>
  </body>
</html>
