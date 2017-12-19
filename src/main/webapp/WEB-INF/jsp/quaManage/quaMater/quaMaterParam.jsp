<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>来料质检详情表</title>
    
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
  <%--   <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script> --%>
    <script type="text/javascript">
    	var reportId="${id}";//计划id
    	var reportForm;
    	var typeName;
    	var reportCode = "${reportCode}";
    	var rfidCode ="";
    	//var typeName =${typeName};
    	
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
    	var testTypeData = [
    	                 { id: '1', text: '1' },
    	                 { id: '来料检测',text: '来料检测' },
    	                 { id: '制程检测', text: '制程检测' },
    	                 { id: '电缆检测', text: '电缆检测' }
    	                 ];
    	var paramList = [];
    	var paramData = {	//grid的值
    			Rows: paramList
    		};
    	$(function(){
    		//改变状态
    		var url = "<%=basePath %>rest/qualityMaterReportManageAction/changeFlag";
    		$.post(url,{mFlag:true},function(){},"json");
    		//初始化form
    		queryForm(reportId);
    		//给form 设值
    		setDataForm(reportId);
    		//生成grid
    		getParamsGrid();
    		
    	});
    	//初始化
    	function queryForm(id){
    		reportForm = $("#form").ligerForm({
    			//url:'<%=basePath %>rest/qualityMaterReportManageAction/getById?id='+reportId,
                inputWidth: 120, labelWidth: 100, 
                fields: [
				{display: '系统质检编号', name: 'reportCode', newline:true,comboboxName:'reportCode',type: "text"},
				{display: '来料类型', name: 'materType', newline:false,comboboxName:'materType',type: "text"},
				{display: '来料名称', name: 'materName', newline:false,comboboxName:'materName',type: "text"},
				{display: '来料规格', name: 'materGgxh', newline:true,comboboxName:'materGgxh',type: "text"},
				{display: '供货商', name: 'materFirm', newline:false,comboboxName:'materFirm',type: "text"},
				{display: '批次号', name: 'materBatch', newline:false,comboboxName:'materBatch',type: "text"},
				{display: '数量', name: 'materAmount', newline:true,comboboxName:'materAmount',type: "text"},
				{display: '单位', name: 'unit', newline:false,comboboxName:'unit',type: "text"},
				{display: '检测人', name: 'testBy', newline:false,comboboxName:'testBy',type: "text"},
				{display: '检测时间', name: 'testDate', newline:true,comboboxName:'testDate',type: "date",editor:{showTime:true,format:"yyyy-MM-dd hh:mm:ss"}},
				{display: '纸质报告code', name: 'pageCode', newline:false,comboboxName:'pageCode',type: "text"},
				{display: '来料时间', name: 'comeDate', newline:false,comboboxName:'comeDate',type: "text"},
				{display: '有效期', name: 'expiryDate', newline:true,comboboxName:'expiryDate',type: "text"},
				{display: '保质期', name: 'releaseDate', newline:false,comboboxName:'releaseDate',type: "text"},
				{display: '备注', name: 'remark', newline:false,comboboxName:'remark',type: "text"},
				{display: '检测状态', name: 'testState', newline:true,type: "select", comboboxName: "testState", options: { data: stateData}},
				{display: '检测结果', name: 'testResult', newline:false,type: "select", comboboxName: "testResult", options: { data: resultData}},
			    { display: '绑定的RFIDs', name: 'rfidCode', newline:false,comboboxName:'rfidCode',type: "hidden"  },
				{display: '检测类型名称', name: 'typeName', newline:false,type: "select", comboboxName: "typeName", editor: {url:'<%=basePath %>rest/qualityBasicTypeManageAction/getTypeNames?type=来料质检' ,valueField:'text', 
					onSelected: function (value)
	                 {
					 typeName = $("[name=typeName]").val();
					//该质检类型名称下的参数set一个grid
					setParamGrid(typeName);
					//所有rfids
					rfidCode = $("[name=rfidCode]").val();
					if(typeName !=""){
	                    $.post('<%=basePath %>rest/qualityBasicTypeManageAction/getAccording',{typeName:typeName},function(data){
	                            reportForm.setData(data[0]);
	                     },"json");
					}
            	 }}},
            	 {display: '检测依据', name: 'testAccording', newline:false,comboboxName:'testAccording',type: "text",readonly:true}
                ],
                validate: true
            }); 
    		liger.get('reportCode').setDisabled(); //设置只读
    		liger.get('materType').setDisabled(); //设置只读
    		liger.get('materName').setDisabled(); //设置只读
    		liger.get('materGgxh').setDisabled(); //设置只读
    		liger.get('materFirm').setDisabled(); //设置只读
    		liger.get('materBatch').setDisabled(); //设置只读 
    		liger.get('materAmount').setDisabled(); //设置只读 
    		liger.get('unit').setDisabled(); //设置只读 
    		liger.get('expiryDate').setDisabled(); //设置只读 
    		liger.get('releaseDate').setDisabled(); //设置只读 
    		liger.get('testAccording').setDisabled(); //设置只读  		
    		liger.get('comeDate').setDisabled(); //设置只读  		
    	}
    	//给form 设值
    	function setDataForm(reportId){
    		var url ="<%=basePath %>rest/qualityMaterReportManageAction/getById";
    		$.post(url,{id:reportId},function(data){
    			reportForm.setData(data);
    		},"json");
    	}
    	
    	//初始化grid
    	function getParamsGrid(){
    		//var param = initGrid();
    		window['g'] = $("#maingrid").ligerGrid({
				//height: '80%',
				//url:'<%=basePath %>/rest/qualityBasicTypeManageAction/getPageList?param='+param,
				checkbox: false,
				columns:[
							{hide: 'id', name: 'id',width:1},
							{display: '参数名称', name: 'paramName'},
							{display: '参考值', name: 'referValue'},
							{display: '实际值', name: 'paramValue',type: 'text',editor: { type: 'text'},minWidth: 40}
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
    			//////debugger;
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
    	
    	//保存记录
    	function save(){
    		var form = $("#form").ligerForm();
    		//form参数
    		var beanValue = form.getData();
    		var result = beanValue.testResult;
    		debugger;
    		if(result == "合格"){
    			if(rfidCode == null || rfidCode == ""){
        			//请扫描1
        			$.ligerDialog.warn("请扫描");
        			return;
        		} 
    		} else {
    			//不合格后自动将绑定的rfid 抹除
    			rfidCode = "";
    		}
    		
    		var form = $("#form").ligerForm();
    		//form参数
    		//var beanValue = form.getData();
    			beanValue.id = reportId;
    			beanValue.rfidCode = rfidCode;
    		//判断是否有rfids
    		var manager = $("#maingrid").ligerGrid();
    		var addData = manager.rows;
    		var params = [];
    		for(var i = 0 ;i<addData.length;i++){
    			var obj = new Object();
    			obj.id = addData[i].id;
    			obj.paramName = addData[i].paramName;
    			obj.referValue = addData[i].referValue;
    			obj.paramValue = addData[i].paramValue;
    			//obj.quaId = reportId;
    			obj.basicTypeName = typeName;
    			params[i] = obj;
    		}
    		//所有参数
    		var beans = JSON.stringify(params);
    		//data.paramName = $("[name=paramName]").val();
    		
    		var bean = JSON.stringify(beanValue);
    		var url ="<%=basePath %>rest/qualityMaterReportManageAction/saveBeanAndValue";
    		$.post(url,{bean:bean,paramBeans:beans},function(data){
    			if(data.success){
	    			$.ligerDialog.success(data.msg);
    			} else {
    				$.ligerDialog.error(data.msg);
    			}
    		},"json");
    	}
    	
    	
    	
    	
    	var aFalg = false;
    	function scan(){
    		//改变状态 开启后台接收扫描
    		var url = "<%=basePath %>rest/qualityMaterReportManageAction/changeFlag";
    		$.post(url,{mFlag:false},function(){},"json");
    		aFalg = true;
    		$("#pageloading").show();
    	}
    	
    	setInterval(function(){
    		if(aFalg){
    			getRfids();
    		}
		}, 1000);
    	
    	function getRfids(){
    		var url = "<%=basePath %>rest/qualityMaterReportManageAction/getRFIDS";
    		$.post(url,{},function(data){
				if(data.success){
					rfidCode = data.data;	 
					aFalg = false;
					$("#pageloading").hide();
					//扫描就自动保存
					save();
				}
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
    	#sm{
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
  
  <body style="overflow-x:hidden; padding:2px;">
  <div class="l-clear"></div>
  <form id="form"></form> 
   <div id="maingrid" ></div>
   		<!-- <button  id="tj" onclick="update()" value="提交保存">提交保存</button> -->
   	<div class="l-loading" style="display:block" id="pageloading"></div> 
   	<div id="btn">
   		<button  id="sm" onclick="scan()" value="扫描">扫描</button>
		<button  id="xz" onclick="save()" value="新增保存">保存</button>
	</div>
  </body>
</html>
