<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>当前质检</title>
    
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
  </head>
  <script type="text/javascript">
 	var reportId="${id}";//计划id
 	var reportCode="${reportCode}";//计划id
 	var testType="${testType}";
	var reportForm;
	var typeName;
	//var testType = "'"+${testType}+"'";
	var stateData = [
	                 { id: '未检测', text: '未检测' },
	                 { id: '检测中', text: '检测中' },
	                 { id: '已检测', text: '已检测' }
	                 ];
	var resultData = [
	                 { id: '', text: '' },
	                 { id: '合格', text: '合格' },
	                 { id: '不合格', text: '不合格' }
	                 ];
	var treatmentResults = [
			                 { id: '', text: '' },
			                 { id: '通过', text: '通过' },
			                 { id: '机台自处理', text: '机台自处理' },
			                 { id: '该工序返工', text: '该工序返工' },
			                 { id: '报废', text: '报废' },
			                 ];
	var paramList = [];
	var paramData = {	//grid的值
			Rows: paramList
	};
	var flag = false;
	 $(function(){
		 $("#form").hide();
		$("#maingrid").hide();
		$("#pageloading").hide();
		//初始化form
		//queryForm();
		//给form 设值
		//setDataForm(reportId);
		//生成grid
		//getParamsGrid();
		//
		//websocket();
	}); 
	function changeFlag(){
		$("#pageloading").show();
		flag = true;
	}
	//实时扫描是否有扫面的id
	setInterval(function(){
		if(flag == true ){
			getReport();
		}
	}, 1000);
	
	function getReport(){
		var url ="<%=basePath %>rest/qualityMauReportManageAction/appPageGetReport";
		$.post(url,{},function(data){
			if(data.success){
				flag = false;
				reportId = data.data.id;
				//alert(reportId);
				testType = data.data.testType;
				$("#sm").hide();
				$("#form").show();
				$("#maingrid").show();
				
				//初始化form
				queryForm();
				//给form 设值
				setDataForm(reportId);
				//生成grid
				getParamsGrid();
			}else{
				
			}
		},"json");
	}
	
	
	function queryForm(){
		reportForm = $("#form").ligerForm({
          inputWidth: 120, labelWidth: 100, 
          fields: [
					{display: '测试类型', name: 'testType', newline:true,comboboxName:'testType',type: "text"},
					{display: '系统质检编号', name: 'reportCode', newline:false,comboboxName:'reportCode',type: "text"},
					{display: '轴名称', name: 'axisName', newline:false,comboboxName:'axisName',type: "text"},
					{display: '工序名称', name: 'seqName', newline:true,comboboxName:'seqName',type: "text"},
					{display: '机器编码', name: 'macCode', newline:false,comboboxName:'macCode',type: "text"},
					{display: '工单编号', name: 'courseCode', newline:false,comboboxName:'courseCode',type: "text"},
					{display: '产品规格型号', name: 'proGgxh', newline:true,comboboxName:'proGgxh',type: "text"},
					{display: '检测人', name: 'testBy', newline:false,comboboxName:'testBy',type: "text"},
					{display: '检测时间', name: 'testDate', newline:false,comboboxName:'testDate',type: "date",editor:{showTime:true,format:"yyyy-MM-dd hh:mm:ss"}},
					{display: '纸质报告code', name: 'pageCode', newline:true,comboboxName:'pageCode',type: "text"},
					{display: '检测状态', name: 'testState', newline:false,type: "select", comboboxName: "testState", options: { data: stateData}},
					{display: '检测结果', name: 'testResult', newline:false,type: "select", comboboxName: "testResult", options: { data: resultData}},
					{display: '处理结果', name: 'treatmentResults', newline:true,type: "select", comboboxName: "treatmentResults", options: { data: treatmentResults}},
					{display: '检测类型名称', name: 'typeName', newline:false,type: "select", comboboxName: "typeName", editor: {url:'<%=basePath %>rest/qualityBasicTypeManageAction/getTypeNames?type=${testType}' ,valueField:'text', 
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
					 {display: '检测依据', name: 'testAccording', newline:false,comboboxName:'testAccording',type: "text"},
					 {display: '备注', name: 'remark', newline:true,comboboxName:'remark',type: "text", inputWidth: 800},
				 ],
          validate: true
      }); 
     
		liger.get('testType').setDisabled(); //设置只读
		liger.get('reportCode').setDisabled(); //设置只读
		liger.get('axisName').setDisabled(); //设置只读
		liger.get('seqName').setDisabled(); //设置只读
		liger.get('macCode').setDisabled(); //设置只读
		liger.get('courseCode').setDisabled(); //设置只读
		liger.get('proGgxh').setDisabled(); //设置只读
		liger.get('testAccording').setDisabled(); //设置只读 
		//liger.get('basicTypeName').setDisabled(); //设置只读  		
	}
	
	function setDataForm(reportId){
		var url ="<%=basePath %>rest/qualityMauReportManageAction/getById";
		$.post(url,{id:reportId},function(data){
			reportForm.setData(data);
		},"json");
	}
	
	function getParamsGrid(){
		window['g'] = $("#maingrid").ligerGrid({
			checkbox: false,
			columns:[
						{hide: 'id', name: 'id',width:1},
						{display: '参数名称', name: 'paramName'},
						{display: '参考值', name: 'referValue'},
						//{display: '第一次', name: 'paramValue',type: 'text',editor: { type: 'text'},minWidth: 40},
						//第一次由机台传输过来
						{display: '第一次', name: 'paramValue',type: 'text'},
						{display: '第二次', name: 'secondValue',type: 'text',editor: { type: 'text'}}
			         ],
			 enabledEdit: true,
			 data: paramData,     
			 rownumbers:true,
			 usePager:false,
			
		});
		$("#pageloading").hide();
	}
	
	//set grid
	function setParamGrid(typeName){
		var url = '<%=basePath %>rest/qualityBasicValueManageAction/getParamsByTypeName';
		$.post(url,{typeName:typeName,reportCode:reportCode},function(data){
			//debugger;
			/* var paramData = { Rows: data.data };
			var manager = $("#maingrid").ligerGrid();
			manager.rows = data.data; */
			
	    	 paramData = {	//grid的值
	    			Rows: data.data
	    		};
			getParamsGrid();
			//manager.setData(paramData);
			//getParamsGrid(paramData);
		},"json");
	}
	
	function save(){
		var manager = $("#maingrid").ligerGrid();
		var addData = manager.rows;
		var params = [];
		for(var i = 0 ;i<addData.length;i++){
			var obj = new Object();
			obj.id = addData[i].id;
			obj.paramName = addData[i].paramName;
			obj.referValue = addData[i].referValue;
			obj.paramValue = addData[i].paramValue;
			obj.secondValue = addData[i].secondValue;
			//obj.quaId = reportId;
			obj.basicTypeName = typeName;
			params[i] = obj;
		}
		debugger;
		//所有参数
		var beans = JSON.stringify(params);
		//data.paramName = $("[name=paramName]").val();
		var form = $("#form").ligerForm();
		
		var beanValue = form.getData();
		beanValue.id = reportId;
		var bean = JSON.stringify(beanValue);
		var url ="<%=basePath %>rest/qualityMauReportManageAction/saveBeanAndValue";
		$.post(url,{bean:bean,paramBeans:beans},function(data){
			if(data.success){
  				$.ligerDialog.success(data.msg);
			} else {
				$.ligerDialog.error(data.msg);
			}
		},"json");
	}
	
	
  </script>
  <style type="text/css">
    	#btn{
    		width: 100%;
    		height:30px;
    		line-height: 30px;
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
    	.search{
			width:80px;
			height:25px;
			text-align:center;
			line-height: 25px;
			display: inline-block;
			font-size:13px;
			border-radius:5px;
			border:none;
			cursor: pointer;
			margin-left: 30px;
			color: white;
		}
		.search{
			background-color: #0099CC;
		}
		.search:hover{
			background-color: #6AC4E3;
		}
		
    </style>
  <body style="overflow-x:hidden; padding:2px;">
  <div><button  id="sm" class="search" onclick="changeFlag()" value="新增保存">扫描</button> </div>
  <div class="l-clear"></div>
  <form id="form" ></form> 
   <div id="maingrid" ></div>
   <div class="l-loading" style="display:block" id="pageloading"></div> 
   <div id="btn">
		<button  id="xz" onclick="save()" value="新增保存">保存</button>
   </div>
  </body>
</html>
