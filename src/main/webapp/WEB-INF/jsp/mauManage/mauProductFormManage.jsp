<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>产品管理</title>
    
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
		var form;
		var datas;
		$(document).ready(function(){
			//form();
			getLigerUi();
			
			debugger;
			
		});
		
		function form(){
	    	form = $("#form2").ligerForm({
	            inputWidth: 150, labelWidth: 180, space:20,
	            fields: [
	            	{ display: "产品类型", name: "pro_type", newline:true,type: "select",comboboxName:'pro_type',editor: {valueField:'text',data:pro_type},align:'left' ,validate: { required: true, minlength: 1 }},
	            	{ display: "产品规格型号", name: "pro_ggxh", newline:false,comboboxName:'pro_ggxh', type: "text",align:'left',validate: { required: true, minlength: 1 }},
	            	{ display: "产品名称", name: "pro_name", newline:true,comboboxName:'pro_name',type: "text" ,align:'right',validate: { required: true, minlength: 1 }},
	            	{ display: "产品颜色", name: "pro_color", newline:false,comboboxName:'pro_name',type: "text" ,align:'right',validate: { required: true, minlength: 1 }},
	            	{ display: "芯线铜丝根数数",  name: "inCore", newline:true,comboboxName:'route_code',type: "int",align:'right' ,validate: { required: true, minlength: 1 }},
	            	{ display: "大芯线颜色(多种颜色用;隔开)",  name: "colorCore", newline:false,comboboxName:'route_code',type: "text",align:'right' ,validate: { required: true, minlength: 1 }},
	            	{ display: "芯线数", name: "core", newline:true,type: "int",align:'left' ,validate: { required: true, minlength: 1 }},
	            	{ display: "二类芯线铜丝根数数(选填)",  name: "inCore2", newline:false,comboboxName:'route_code',type: "int"},
	            	{ display: "产品截面积(有多种用;隔开)", name: "area", newline:true,type: "text",align:'left' ,validate: { required: true, minlength: 1 }},
	            	{ display: "备注", name: "remark",inputWidth: 200, newline:false,type: "text",align:'left' },
	            	
	            ],
	            validate: true
	        });
	    }
		
		function valids(){
	    	var va = form.valid();
	    	return va;
	    }
	    
	    function getDatas(){
	    	var datas = form.getData();
	    	var re = /^[1-9]+[0-9]*]*$/;
	    	var areas = datas.area.split(";");
	    	debugger;
	    	for(var i = 0;i < areas.length;i++){
	    		if(!re.test(areas[i])){
	    			$.ligerDialog.warn("请输入真确的表达式:95;25或者95");
	    			return;
	    		}
	    	}
	    	return datas;
	    }
		
		
		function getLigerUi(){
			 
			$.ajax({
   	    		url: basePath + 'rest/mauProductManageAction/getAllProType',
   	    		type:'POST',
   	    		dataType:'json',
   	    		success:function(str){
   	    			debugger;
   	    			pro_type = str;
   	    			form(pro_type);
   	    			datas = ${data};
   	    			if(datas!=null && datas!="false"){
   	    				f_setData(datas);//修改
   	    			}
   	    		}
   	    	});
				
			
		}
		
		function f_setData(datas){
			debugger;
			form.setData(datas);
			
		}
		
	</script>
  </head>
  
  <body>

	<div class="l-clear"></div>

   <form id="form2" style="margin:20px"></form>
   
  <div style="display:none;"></div>

    
  </body>
</html>
