<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>生产质检列表</title>
    
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
    var basePath = '<%=basePath %>';
    	$(function(){
    		//初始化列表
    		queryTable();
    		$("#start").ligerDateEditor({showTime:true,  labelWidth: 80, labelAlign: 'left' });
			$("#end").ligerDateEditor({ showTime:true, labelWidth: 80, labelAlign: 'left' });
    	});
    	
    	function queryTable(){
    		var param = initParam();
    		window['g'] = $("#maingrid").ligerGrid({
				height: '85%',
				url:'<%=basePath %>/rest/qualityMauReportManageAction/getPageList?param='+param,
				checkbox: false,
				columns:[
						{display: '质检类型', name: 'testType'},
						{display: '系统质检编号', name: 'reportCode'},
						{display: '机器编号', name: 'macCode'},
						{display: '工序名称', name: 'seqName'},
						{display: '轴名称', name: 'axisName'},
						{display: '测试状态', name: 'testState',render:function(row){
							var html="";
							if(row.testState == "未检测"){
								html = "<span >未检测</span>"
							} else if(row.testState == "检测中"){
								html = "<span style='color:#6959CD'>检测中</span>";
							} else if(row.testState == "已检测"){
								html = "<span style='color:green'>已检测</span>";
							}
							return html;
						}},
						{display: '测试结果', name: 'testResult',render:function(row){
							var html ="";
							if (row.testResult=="合格") {
								html = "<span style='color:green'>合格</span>";
							} else if (row.testResult=="不合格"){
								html = "<span style='color:red'>不合格</span>";
							}
							return html;
						}},
						{display: '检测类型名称', name: 'typeName'},
						{display: '规格型号', name: 'proGgxh'},
						{display: '检查时间', name: 'testDate'},
						{display: '是否入库', name: 'isCome'},
						{display: '操作', name: '',render: function (rowData)
			                {
							debugger;
							var url = basePath+"/rest/qualityMauReportManageAction/toPrintList?axisName="+rowData.axisName;
							var h="";
								 //  h +=  "<a href='javascript:' class='edit' onclick='javascript:toDetailReport("+ rowData.id+ ");return false;'>详情</a>&nbsp;&nbsp;";
								   h +=  "<a href='javascript:' class='edit' onclick='javascript:toUpdateReport("+ rowData.id+ ");return false;'>修改</a>&nbsp;&nbsp;";
								   h +=  "<a href='"+url+"' class='edit' target='_blank'>打印</a>&nbsp;&nbsp;";
								   h +=  "<a href='javascript:' class='edit' onclick='javascript:deleteRow("+ rowData.id+ ");return false;'>删除</a>&nbsp;&nbsp;";
				                    /* h +=  '<a href="#" onclick="javascript:toUpdate('+ rowData.id+ ');return false;">修改</a>'; */
				                    return h;
			                }},
						{hide: 'id', name: 'id',width:'1px'}
				         ],
				toolbar: {
                  items: [
                  { text: '增加', click: addRow, icon: 'add' },
                  { text: '扫描', click: appScan, icon: 'add' },
                 /*   { line: true },
                  { text: '修改', click: modifyBean, icon: 'modify' }, */
                  /*{ line: true },
                  { text: '删除', click: deleteRow, icon:'delete' } */
                  ]
              	}, 
     			 rownumbers:true,
				
			});
			$("#pageloading").hide();
    	}
    	
    	function toPrintReport(axisName){
    		
    		
    	}
    	
    	function deleteRow(id){
    		$.ligerDialog.confirm("确定删除?",function(flag){
    			if(flag){
    				var url ="<%=basePath %>rest/qualityMauReportManageAction/clearBean";
    				$.post(url,{id:id},function(data){
    					if (data.success) {
							$.ligerDialog.success(data.msg);
							queryTable();
						} else {
							$.ligerDialog.error(data.msg);
						}
    				},"json");
    			} else{
    				return;
    			}
    		});
    	}
    	
    	function addRow(){
    		$.ligerDialog.open({
				height: 300,
				width: 900,
				title: '生产质检添加',
				url: "<%=basePath %>rest/qualityMauReportManageAction/toAddOrModify",
				showMax: false,
				showMin: false,
				isResize: true,
				slide: false,
				name:'repairAgain',
				buttons: [
				 /*  { text: '确认添加', onclick: function (item, dialog) {}}, */
				  { text: '操作完成', onclick: function (item, dialog) { dialog.close();queryTable(); } }
				          ]
    		});
    	}
    	
    	function modifyBean(){
    		$.ligerDialog.open({
				height: 500,
				width: 900,
				title: '生产质检修改',
				url: "<%=basePath %>rest/qualityMauReportManageAction/toAddOrModify?id="+id,
				showMax: false,
				showMin: false,
				isResize: true,
				slide: false,
				name:'repairAgain',
				buttons: [
				 /*  { text: '确认添加', onclick: function (item, dialog) {}}, */
				  { text: '完成', onclick: function (item, dialog) { dialog.close(); } }
				          ]
    		});
    	}
    	
    	
    	function toUpdateReport(id){
    		$.ligerDialog.open({
				height: 500,
				width: 900,
				title: '生产质检修改',
				url: "<%=basePath %>rest/qualityMauReportManageAction/toUpdateReport?id="+id,
				showMax: false,
				showMin: false,
				isResize: true,
				slide: false,
				name:'repairAgain',
				buttons: [
				 /*  { text: '确认添加', onclick: function (item, dialog) {}}, */
				  { text: '取消', onclick: function (item, dialog) { dialog.close();queryTable(); } }
				          ]
    		});
    	}
    	
    	function initParam(){
    		var param = {};
    		var testType = $("#testType option:selected").val();
    		var testState = $("#testState option:selected").val();
    		var testResult = $("#testResult option:selected").val();
    		var isCome = $("#isCome option:selected").val();
    		var start = $("#start").val();
    		var end = $("#end").val();
    		if (testType != null && testType != "") {
	    		param.testType  = testType;
			}
    		if (testState != null && testState != "") {
	    		param.testState  = testState;
			}
    		if (testResult != null && testResult != "") {
	    		param.testResult  = testResult;
			}
    		if (isCome != null && isCome != "") {
	    		param.isCome  = isCome;
			}
    		if (start != null && start != "") {
	    		param.start  = start;
			}
    		if (end != null && end != "") {
	    		param.end  = end;
			} 
    		var params = JSON.stringify(param);
    		return params;
    	}
    	
    	function appScan(){
    		$.ligerDialog.open({
				height: 500,
				width: 900,
				title: '生产质检app扫描',
				url: "<%=basePath %>rest/qualityMauReportManageAction/toAppScanReportParam",
				showMax: false,
				showMin: false,
				isResize: true,
				slide: false,
				name:'repairAgain',
				buttons: [
				 /*  { text: '确认添加', onclick: function (item, dialog) {}}, */
				  { text: '取消', onclick: function (item, dialog) { dialog.close();queryTable(); } }
				          ]
    		});
    	}
    </script>
    <style type="text/css">
		*{
				margin: 0px;
				padding: 0px;
			}
		.edit:hover{
			color:red;
		}
		#tb table{
				width:100%;
				margin: 0 auto;
				font-size:13px;
			}
		#tb table tr{
				height: 40px;
			}
		#tb table tr td{
			}
		#tb table tr td span{
				display: inline-block;
				width: 90px;
				text-align: right;
			}
		#tb table tr td select{
				width: 120px;
			}
		#tb .search,#tb .export{
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
		#tb .search{
			background-color: #0099CC;
		}
		#tb .search:hover{
			background-color: #6AC4E3;
		}
		#tb .export{
			background-color: #F7A08B;
		}
		#tb .export:hover{
			background-color: #E5B2A5;
		}
	</style>
  </head>
  
 <body style="overflow-x:hidden; padding:2px;">
  <div id="tb"> 
 		<table border="0" cellspacing="50px" cellpadding="50px" width="80%">
 		<tr>
 			<td>
 			<span>检测类型:</span>
 				<select id="testType">
 					<option value=""></option>
 					<option value="制程质检">制程质检</option>
 					<option value="电缆质检">电缆质检</option>
 				</select>
 				<span>检测状态:</span>
 				<select id="testState">
 					<option value=""> </option>
 					<option value="未检测">未检测</option>
 					<option value="检测中">检测中</option>
 					<option value="已检测">已检测</option>
 				</select>
 				<span>检测结果:</span>
 				<select id="testResult">
 					<option value=""></option>
 					<option value="合格">合格</option>
 					<option value="不合格">不合格</option>
 				</select>
 				<span>是否入库:</span>
 				<select id="isCome">
 					<option></option>
 					<option value="已入库">已入库</option>
 					<option value="未入库">未入库</option>
 				</select>
 				<span>检测时间:</span>
 				<input id="start" type="text" />
 				<span style="width:30px;text-align: center;">到</span><input id="end" type="text" />
 			</td>
 		</tr>
 		<tr>
 			<td>
 				<button class="search" onclick="queryTable()">搜  索</button>
 				<button class="export" onclick="exportMaterial()">导  出</button>
 			</td>
 		</tr>
 		</table>
 	</div>
     <div class="l-loading" style="display:block" id="pageloading"></div>
	<div class="l-clear"></div>
	<div id="maingrid"></div>
	<div style="display:none;"></div>
  </body>
</html>
