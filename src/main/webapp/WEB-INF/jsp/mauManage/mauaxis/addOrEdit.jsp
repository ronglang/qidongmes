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
    	var mainForm;
    	var type=[{id:1,text:'铁轴'},{id:1,text:'胶轴'}];
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
					display : "轴名称",
					name : "axisName",
					type : "text",
					newline : false,
					 /* editor : {
						onChangeValue : function(value) {
							checkOrderCode(value);
						}
					}  */
					validate: { required: true, minlength: 1,}
				}, {
					display : '轴颜色',
					name : 'axisColor',
					type : "text",
					newline : false,
					validate: { required: true, minlength: 1,}
				}, {
					display : '外盘径',
					name : 'externalDiameter',
					type : "int",
					newline : false,
					validate: { required: true, minlength: 1,}
				}, {
					display : '内盘径',
					name : 'internalDiameter',
					type : "int",
					newline : false,
					validate: { required: true, minlength: 1,}
				}, {
					display : '轴内宽',
					name : 'axisInWidth',
					type : "int",
					newline : false,
					validate: { required: true, minlength: 1,}
				}, {
					display : '轴外宽',
					name : 'axisOutWidth',
					type : "int",
					newline : false,
					validate: { required: true, minlength: 1,}
				}, {
					display : '中心孔直径',
					name : 'centerDiameter',
					type : "int",
					newline : false,
					validate: { required: true, minlength: 1,}
				}, {
					display : 'id',
					name : 'id',
					type : "hidden",
					newline : false,
					validate: { required: true, minlength: 1,}
				},{
					display : '同类共有几个',
					name : 'sum',
					type : "int",
					newline : false,
					validate: { required: true, minlength: 1,}
					
				} ,{
					display : '盘材料类型',
					name : 'type',
					type : "select",
					newline : false,
					validate: { required: true, minlength: 1,},
					editor: {valueField:'text',data:type}
				} ]
			});
    	}
    	
    	function save(){
    		var bean = mainForm.getData();
    		var pa = JSON.stringify(bean);
    		var url = "<%=basePath %>rest/mauAxisManageAction/saveOrUpdate";
    		$.post(url,{bean:pa},function(data){
    			if (data.success) {
					$.ligerDialog.success(data.msg);
				}else {
					$.ligerDialog.error(data.msg);
				}
    		},"json");
    	}
    	function setForm(){
    		
    	}
    </script>
  </head>
  
  <body>
    	<div id="myForm"></div>
  </body>
</html>
