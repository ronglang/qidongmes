<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>合同到期情况</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/json2.js" type="text/javascript"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    
    <script type="text/javascript">
    	var basePath = "<%=basePath%>";
		var urlPath = basePath.substring(7, basePath.length);
    </script>
    
    <script type="text/javascript">
    
    $(document).ready(function(){

    	grid();
    	
    });

    function grid(){
    	
        $("#maingrid").ligerGrid({
            height: '80%',
            url: basePath+'rest/sellSalesOrderManageAction/toListPageData?param={}',
            columns: [
    		 { display: 'id', name: 'id',width:1,  hide:true},
 		            { display: '订单编号', name: 'orderCode'},
 		          	{ display: '订单类型', name: 'orderType'},
 		            { display: '优先级', name: 'priLevel'},
 		            { display: '合同编号', name: 'contractCode'},
 		            { display: '交货日期', name: 'deliveryDate'},
 		            { display: '销售部经办人', name: 'salesManager'},
 		            { display: '订单录入人员', name: 'orderEntryClerk'},
 		            { display: '是否分解', name: 'genFlag'},
            { hide:'id',name:'id',width:1}
            ],
            rownumbers: true,
            enabledEdit: false,
            pageSize: 15
        });
        $("#pageloading").hide();
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
