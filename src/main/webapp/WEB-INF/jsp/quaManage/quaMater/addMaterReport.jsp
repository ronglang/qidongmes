<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加来料质检计划页面</title>
    
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
    <style type="text/css">
    	<style type="text/css">
			*{
				margin: 0px;
				padding: 0px;
			}
			body,div,table{
				margin: 0px;
				padding: 0px;
				font-size: 14px;
			}
			h3{
				text-align: center;
				margin: 0 auto;
			}
			form{
			}
			#b_add{
				width:80%;
				margin: 0 auto;
				border-top: 1px solid gray;
			}
			#b_add tr{
				height: 50px;
			}
			#b_add tr td{
				width: 33%;
				font-size: 13px;
				position: relative;
			}
			#b_add tr td span{
				display: inline-block;
				width: 100px;
				text-align: right;
			}
			#b_add tr td select{
				width: 80px;
			}
			#b_add tr td input{
				
			}
			#end{
				width: 200px;
				height: 50px;
				margin: 10px auto;
				
			}
			#end input.btn1{
				background-color: #0A90F0;
				border-radius:5px;
				display: inline-block;
				width: 80px;
				height: 25px;
				line-height: 25px;
				color: white;
				cursor: pointer;
				border: none;
				margin: 12px;
			}
			#end input.btn1:hover{
				background-color: #62B4ED;
			}
			#end input.btn2{
				background-color: #AACE1F;
				border-radius:5px;
				display: inline-block;
				width: 80px;
				height: 25px;
				line-height: 25px;
				color: white;
				cursor: pointer;
				border: none;
			}
			#end input.btn2:hover{
				background-color: #CEE574;
			}
			#b_add .error{
				position: absolute;
				top: 10px;
				left: 250px;
				width: 170px;
				height: 30px;
				text-indent: 15px;
				line-height: 30px;
				background: url(<%=basePath %>app/images/errror_bg.png);
				display: none;
			}
			#top{
				width:100%;
				height:30px;
				line-height: 30px;
			}
			
			#top .t_ret{
				text-align: center;
			}
			#top .t_ret a{
				color: #038BDE;
			}
			#top .t_ret a:hover{
				color: red;
			}
			#top .t_text{
				text-align: center;
			}
			#btn{
	    		width: 100%;
	    		height:50px;
	    		line-height: 50px;
	    		margin: 0 auto;
	    		text-align: center;
	    		padding-top:20px;
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
    <script type="text/javascript">
    //有id就是id,新建id=-1
    var id =${id};
    var reportForm ;

	var stateData = [
	                 { id: '未检测', text: '未检测' },
	                 { id: '检测中', text: '检测中' },
	                 { id: '已检测', text: '已检测' }
	                 ];
	var resultData = [
	                 { id: '1', text: '1' },
	                 { id: '合格', text: '合格' },
	                 { id: '不合格', text: '不合格' }
	                 ];
    	//初始化form
    	$(function(){
    		getForm();
    		//是否是新建
    		if(id != "-1"){
    			//新建
    			return ;
    		} else{
    			//修改保存
    			setForm(id);
    		}
    	});
    	
    	function getForm(){
    		reportForm = $("#form").ligerForm({
    			//url:'<%=basePath %>rest/qualityMaterReportManageAction/getById?id='+reportId,
                 inputWidth: 120, labelWidth: 120, 
                fields: [
				//{display: '系统质检编号', name: 'reportCode', newline:true,comboboxName:'reportCode',type: "text"},
				{display: '来料类型', name: 'materType', newline:true,comboboxName:'materType',type: "text"},
				{display: '来料名称', name: 'materName', newline:false,comboboxName:'materName',type: "text"},
				{display: '来料规格', name: 'materGgxh', newline:false,comboboxName:'materGgxh',type: "text"},
				{display: '供货商', name: 'materFirm', newline:true,comboboxName:'materFirm',type: "text"},
				{display: '批次号', name: 'materBatch', newline:false,comboboxName:'materBatch',type: "text"},
				{display: '数量', name: 'materAmount', newline:false,comboboxName:'materAmount',type: "text"},
				{display: '单位', name: 'unit', newline:true,comboboxName:'unit',type: "text"},
			//	{display: '检测人', name: 'testBy', newline:false,comboboxName:'testBy',type: "text"},
			//	{display: '检测时间', name: 'testDate', newline:true,comboboxName:'testDate',type: "date",editors:{format:'yyyy-MM-dd HH:mm:ss'}},
			//	{display: '纸质报告code', name: 'pageCode', newline:false,comboboxName:'pageCode',type: "text"},
				{display: '来料时间', name: 'comeDate', newline:false,comboboxName:'comeDate',type: "date"},
				{display: '有效期', name: 'expiryDate', newline:false,comboboxName:'expiryDate',type: "date"},
				{display: '保质期', name: 'releaseDate', newline:true,comboboxName:'releaseDate',type: "date"},
				{display: '备注', name: 'remark', newline:false,comboboxName:'remark',type: "text"},
				//{display: '检测状态', name: 'testState', newline:true,type: "select", comboboxName: "testState", options: { data: stateData}},
				//{display: '检测结果', name: 'testResult', newline:false,type: "select", comboboxName: "testResult", options: { data: resultData}},
				/*{display: '检测类型名称', name: 'typeName', newline:false,type: "select", comboboxName: "typeName", editor: {url:'<%=basePath %>rest/qualityBasicTypeManageAction/getTypeNames?type=来料质检' ,valueField:'text', 
					onSelected: function (value)
	                 {
					 typeName = $("[name=typeName]").val();
					//该质检类型名称下的参数set一个grid
					setParamGrid(typeName);
					
					if(typeName !=""){
	                    $.post('<%=basePath %>rest/qualityBasicTypeManageAction/getAccording',{typeName:typeName},function(data){
	                            reportForm.setData(data[0]);
	                     },"json");
					}
            	 }}},
            	 {display: '检测依据', name: 'testAccording', newline:true,comboboxName:'testAccording',type: "text"},*/
				/* {display: '检测类型', name: 'testResult', newline:true,type: "select", comboboxName: "Type", options: { data: resultData}}, */
				
				
			 /* { display: "质检类型名称", name: "basicTypeName", newline:false,comboboxName:'basicTypeName',type: "text"  }, */
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
    		var url = "<%=basePath %>rest/qualityMaterReportManageAction/updateBean";
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
    		var url ="<%=basePath %>rest/qualityMaterReportManageAction/getById";
    		$.post(url,{id:id},function(data){
    			reportForm.setData(data);
    		},"json");
    	}
    </script>
    
    
  </head>
  
  <body style="padding:10px" >
   <!--  <div id="top" style="width:100%;height:50px;line-height: 20px;">
	<h3 style="text-align: center;" class="t_text">来料质检添加</h3>
  	</div> -->
		 <form id="form"></form> 
	<div id="btn">
		 <button  id="xz" onclick="save()" value="新增保存">保存</button>
	</div>
  </body>
</html>
