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
	 <script type="text/javascript">
	 var url;
	 var testState = [
	                 {id:'1',text:'已检测'},
	                 {id:'2',text:'未检测'},
	                 {id:'3',text:'检测中'},
	                 ];
	 var testResult = [
		                 {id:'1',text:'合格'},
		                 {id:'2',text:'不合格'},
		                 {id:'3',text:'异议'},
		                 ];
	 	$(function(){
	 		url = '<%=basePath %>rest/qualityProductPlanManageAction/getPageList';
	 		queryTable(url);
	 		form();
	 	});
	 	
	 	//初始化数据
	 	function queryTable(url){
	 		{
	 			window['g'] = $("#maingrid").ligerGrid({
					  height: '98%',
					url:url,
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
					{display: '操作', name: '',render: function (rowData)
		                {
		                    var h =  '<a href="#" onclick="javascript:toDetail('+ rowData.id+ ');return false;">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;';
		                    h +=  '<a href="#" onclick="javascript:toPrint('+ rowData.id+ ');return false;">打印</a>';
		                    return h;
		             }},
					{hide: '计划id', name: 'id',width:'1px'}
					],
					toolbar: {
	                    items: [
	                    { text: '增加', click: addrows, icon: 'add' },
	                    { line: true },
	                    { text: '修改', click: updaterows, icon: 'modify' },
	                    { line: true },
	                    ]
	                },
	     			 rownumbers:true,
					
				});
				 $("#pageloading").hide();
	 		}
	 	}
	 	
	 	function addrows(){
	 		// window.location.href="<%=basePath %>rest/qualityProductPlanManageAction/toSave";
	 		 window.location.href="<%=basePath %>rest/qualityProductPlanManageAction/toSave";
	 	}
	 	
	 	function updaterows(){
	 		var manager = $("#maingrid").ligerGetGridManager();
        	var li = manager.getSelectedRows();
        	var dataArray = [];
        	//debugger;
        	if(li.length>1 || li.length<1 ){
        		alert("请选择一行修改");
        		return;
        	}
        	debugger;
        	var id = li[0].id;
        	 window.location.href="<%=basePath %>rest/qualityProductPlanManageAction/toEditPage?id="+id;
	 	}
	 	
	 	function toDetail(id){
	 		<%-- top.$.ligerDialog.open({
				url : "<%=basePath%>rest/qualityProductPlanManageAction/toFormPage?id=" + id,
				height : 600,
				width : 1080,
				modal : true,
				title : "详细信息"
			}); --%>
	 		
	 		window.location.href="<%=basePath %>/rest/qualityProductPlanManageAction/toFormPage?id="+id;
	 		
	 		//alert(id);
	 	}
	 	
	 	function toPrint(id){
	 		window.open('<%=basePath%>rest/qualityProductPlanManageAction/toQualProTablePrint?id=' + id);
	 	}
	 	
	 	function form(){
        	
       	 //创建表单结构 
           form = $("#form2").ligerForm({
               inputWidth: 120, labelWidth: 90, space: 10,
               fields: [
				{ display: "检测开始时间", name: "start", newline: false,type: "date",editor:{showTime:true,format:"yyyy-MM-dd hh:mm:ss"}},
				{ display: "检测结束时间", name: "end", newline: false,type: "date",editor:{showTime:true,format:"yyyy-MM-dd hh:mm:ss"}},
             	{ display: "工序名称", name: "seqName", newline: false,type: "text"},
             	{ display: "检验状态", name: "testState", newline: false,type: "select",editor: {valueField:'text',data:testState}},
             	{ display: "检验结果", name: "testResult", newline: false, type: "select",editor: {valueField:'text',data:testResult}}
               ],
               validate  : true
           });
       	
       }
	 	
	 	function initData(){
	 		var start = $("[name=start]").val();
        	var end = $("[name=end]").val();
			var seqName=$("[name=seqName]").val();
			var testState=$("[name=testState]").val();
			var testResult=$("[name=testResult]").val();
			var param = {};
			if(start != "" && start != null){
				param.start = start;
			}
			if(seqName != "" && seqName != null){
				param.seqName = seqName;
			}
			if(testState != "" && testState != null){
				param.testState = testState;
			}
			if(testResult != "" && testResult != null){
				param.testResult = testResult;
			}
			if(end != "" && end != null){
				param.end = end;
			}
			var data = JSON.stringify(param);
			return data;
	 	}
	 	
	 	function search(){
        	//form.valid();
        	var data = initData();
			url = '<%=basePath %>rest/qualityProductPlanManageAction/getPageList?param='+data;
			queryTable(url);
        }
	 	
	 	function exportExcel(){
	 		var data = initData();
	 		window.location.href = '<%=basePath %>rest/qualityProductPlanManageAction/toExportExcel?param='+data;
	 		
	 	}
	 	
	 	
	 </script>
  </head>
  
  <body>
  <div id="msg" style="color: red ;display: none;font-size: 20px" align="center">${msg}</div>
    <div class="l-loading" style="display:block" id="pageloading"></div>
	<div class="l-clear"></div>
	<div style="text-align: center;width: 90%;margin:0 auto;position: relative;"  >
		<form id="form2" action="" >
		</form>
		<div class="liger-button" onclick="exportExcel()" style="float: right;position: absolute;right: 100px;top: 3px; ">导出</div>
		<div class="liger-button" onclick="search()" style="float: right;position: absolute;right: 180px;top: 3px; ">搜索</div>
		
	</div>
	<div id="maingrid"></div>
	<div style="display:none;"></div>
  </body>
</html>
