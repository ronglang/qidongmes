<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%= basePath%>app/js/sellManage/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />  
    <link rel="stylesheet" type="text/css" id="mylink"/>   
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%= basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>    
    <script src="<%= basePath%>app/js/sellManage/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>  
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script type="text/javascript">
    	var id= '${id}';
    	var type = [{id:1,text:'收线轴'},{id:2,text:'放线轴'}];
    	var mainForm;
    	$(function(){
    		initForm();
    		if (id  != null && id != "") {
	    		setForm();
			}
    		
    	});
    
    	function initForm(){
    		mainForm = $("#myForm").ligerForm({
				inputWidth : 170,
				labelWidth : 90,
				space : 40,
				fields : [ {
					display : "机台编码",
					name : 'macCode',
					type : "select",
					newline : false,
					editor:{url:'<%= basePath%>rest/mauMachineManageAction/getMachineCode',valueField:'text'},
					validate: { required: true, minlength: 1,}
				}, {
					display : '轴名称',
					name : 'axisName',
					type : "select",
					newline : false,
					editor:{url:'<%= basePath%>rest/mauAxisMacManageAction/getAxisName',valueField:'text'},
					validate: { required: true, minlength: 1,}
				}, {
					display : '线轴类型',
					name : 'axisType',
					type : "select",
					editor:{data:type,valueField:'text'},
					newline : false,
					validate: { required: true, minlength: 1,}
				},{
					display:'id',
					name:'id',
					type:'hidden'
				}]
			});
    	}
    	
    	function save(){
    		var bean = mainForm.getData();
    		if(bean.id==""){
    			bean.id= null ;
    		}
    		var pa = JSON.stringify(bean);
    		var url = "<%=basePath %>rest/mauAxisMacManageAction/saveOrUpdate";
    		$.post(url,{bean:pa},function(data){
    			if (data.success) {
					$.ligerDialog.success(data.msg);
				}else {
					$.ligerDialog.error(data.msg);
				}
    		},"json");
    	}
    	function setForm(){
    		var url = "<%=basePath %>rest/mauAxisMacManageAction/getById";
    		$.post(url,{id:id},function(data){
    			if (data.success) {
    				mainForm.setData(data.data);
					//$.ligerDialog.success(data.msg);
				}else {
					$.ligerDialog.error(data.msg);
				}
    		},"json");
    	}
    </script>
  </head>
  
  <body>
    	<div id="myForm"></div>
  </body>
</html>
