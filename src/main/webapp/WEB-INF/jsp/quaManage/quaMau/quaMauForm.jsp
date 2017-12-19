<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>生产质检添加或者修改页面</title>
    
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
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerCheckBoxList.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerListBox.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    
    <script type="text/javascript">
  //有id就是id,新建id= -1
    var id =${id};
    var reportForm ;
    var testTypeData = [
		                 { id: '', text: '' },
		                 { id: '制程质检', text: '制程质检' },
		                 { id: '电缆质检', text: '电缆质检' }
		                 ];
  //初始化form
	$(function(){
		getForm();
		//是否是新建
		if(id != null ){
			//新建
			return ;
		} else{
			//修改保存
			setForm(id);
		}
	});
  
	function getForm(){
		reportForm = $("#form").ligerForm({
            inputWidth: 120, labelWidth: 100, 
            fields: [
			{display: '测试类型', name: 'testType', newline:true,comboboxName:'testType',type: "select", options: { data: testTypeData}},
			{display: '轴名称', name: 'axisName', newline:false,comboboxName:'axisName',type: "text"},
			{display: '工序名称', name: 'seqName', newline:false,comboboxName:'seqName',type: "text"},
			{display: '机器编码', name: 'macCode', newline:true,comboboxName:'macCode',type: "text"},
			{display: '工单编号', name: 'courseCode', newline:false,comboboxName:'courseCode',type: "text"},
			{display: '产品规格型号', name: 'proGgxh', newline:false,comboboxName:'proGgxh',type: "text"},
			{display: '备注', name: 'remark', newline:true,comboboxName:'remark',type: "text"},
            ],
            validate: true
        }); 
       
	}
	
	function save(){
		var form = $("#form").ligerForm();
		var reportData = form.getData();
		if(id != null && id != "" && id != "-1"){
			reportData.id = id;
		}
		var report  = JSON.stringify(reportData);
		var url = "<%=basePath %>rest/qualityMauReportManageAction/updateBean";
		$.post(url,{bean:report},function(data){
			if(data.success){
				$.ligerDialog.success(data.msg);
				getForm();
			} else{
				$.ligerDialog.error(data.msg);
			}
		},"json");
	}
	
	function setForm(id){
		var url ="<%=basePath %>rest/qualityMauReportManageAction/getById";
		$.post(url,{id:id},function(data){
			reportForm.setData(data);
		},"json");
	}
    </script>
    
    <style type="text/css">
    	#btn{
    		width: 100%;
    		height:50px;
    		line-height: 50px;
    		margin: 0 auto;
    		text-align: center;
    	}
    	#xz{
    		width:90px;
    		height:27px;
    		border-radius:5px;
    		border:none;
    		color:white;
    		background-color: #0099CC;
    		cursor: pointer;
    	}
    	#xz:hover{
    		background-color: #6AB7D1;
    	}
    </style>
  </head>
  
  
  <body style="padding:10px" >
    <div style="height:50px;line-height: 50px">
		<h3 style="text-align: center;">生产质检${ info}</h3>
  	</div>
	<form id="form"></form> 
	<div id="btn">
		<button  id="xz" onclick="save()" value="新增保存">保存</button>
	</div>
	
  </body>
</html>
