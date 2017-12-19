<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>产品质检列表</title>
    
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
	 <script src="<%=basePath %>core/js/dateUtils.js"></script>
	 <script type="text/javascript">
	 	$(function(){
	 		queryTable();
	 	});
	 	
	 	//初始化数据
	 	function queryTable(){
	 		var param ={};
	 		var date = new Date();
	 		var p = date.pattern("yyyy-MM-dd");
	 		var end = p+" 23:59:59";
	 		param.end = end;
	 		var data = JSON.stringify(param);
	 		{
	 			window['g'] = $("#maingrid").ligerGrid({
	 				 height: '98%',
						url:'<%=basePath %>rest/qualityProductPlanManageAction/getPageList?param='+data,
						checkbox: true,
						columns:[
						{display: '检测类型', name: 'type'},
						{display: '轴名称', name: 'axisName'},
						{display: '机器编号', name: 'macCode' },
						{display: '工序名称', name: 'seqName'},
						{display: '规格型号', name: 'proGgxh'},
						{display: '计划检测时间', name: 'planTestDate',width:150},
						{display: '实际检测时间', name: 'factTestDate',width:150},
						{display: '检测状态', name: 'testState'},
						{display: '检测人', name: 'testBy'},						
						{display: '检测结果', name: 'testResult'},
						{display: '检验报告单号', name: 'reportCode'},					
						{display: '处理意见', name: 'advice'},						
					/* {display: '操作', name: '',render: function (rowdata, rowindex, value)
	                {
	                    var h = "";
	                        h += "<a href='javascript:toDetail(" + rowdata.id + ")'>修改</a> ||";
	                        h += "<a href='javascript:toPrint("+rowdata.id+")>打印</a> "; 
	                    return h;
	                }}, */
					{hide: 'id', name: 'id',width:'1px'}
					],
					
	     			 pageSize:15,
	     			 rownumbers:true,
					
				});
				 $("#pageloading").hide();
	 		}
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
