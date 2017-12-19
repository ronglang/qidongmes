<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'sysNoticeEdit.jsp' starting page</title>
    
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
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerForm.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerComboBox.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script type="text/javascript">
    var form;
    var testTypeData = [
    	                 { id: '', text: '' },
    	                 { id: '是', text: '是' },
    	                 { id: '否', text: '否' }
    	                 ];
    var id="${id}";
    	$(function(){
    		getForm();
    		if(id !== null && id != "" ){
    			setForm();
    		}
    	});
    	
    	function getForm(){
    		form = $("#form").ligerForm({
                inputWidth: 170, labelWidth: 70, 
                fields: [
    			{display: 'id', name: 'id',type: "hidden", comboboxName:'hidden'},
    			{display: '内容', name: 'value', newline:true,comboboxName:'value',type: "text"},
    			{display: '是否显示', name: 'isShow', newline:false,comboboxName:'isShow',type: "select", options: { data: testTypeData}},
    			{display: '开始时间', name: 'startTime', newline:true,comboboxName:'startTime',type: "date",editor:{showTime:true,format:"yyyy-MM-dd hh:mm:ss"}},
    			{display: '结束时间', name: 'endTime', newline:false,comboboxName:'endTime',type: "date",editor:{showTime:true,format:"yyyy-MM-dd hh:mm:ss"}},
                ],
                validate: true
            }); 
    	}
    	
    	function setForm(){
    		debugger;
    		alert(id);
    		var url="<%=basePath%>rest/sysNoticeManageAction/getById";
    		$.post(url,{id :id},function(data){
    			form.setData(data);
    		},"json");
    	}
    	
    	function save(){
    		var value= $("[name=value]").val();
    		var id= $("[name=id]").val();
    		var isShow = $("[name=isShow]").val();
    		var start = $("[name=startTime]").val();
    		var end = $("[name=endTime]").val();
    		var data ={};
			   if(id !=null  && id != ""){
			   data.id= id;
			   }
			   data.value= value;
			   data.isShow = isShow;
			   data.start = start;
			   data.end = end;
			   data.type="欢迎致辞";
			   var bean = JSON.stringify(data);
			   var url= "<%=basePath %>rest/sysNoticeManageAction/saveOrUpdate";
			   $.post(url,{bean:bean},function(data){
				   if(data.success){
					   $.ligerDialog.success(data.msg);
					   dialog.close();
				   }
			   },"json");
    	}
    	
    </script>
  </head>
  
  <body>
     <div class="l-clear"></div>
 	 <form id="form" ></form> 
 	 
 	 <button onclick="save()"> 添加</button>
  </body>
</html>
