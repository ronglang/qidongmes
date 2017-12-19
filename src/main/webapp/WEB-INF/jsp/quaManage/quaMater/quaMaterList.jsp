<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>原材料质检列表</title>
    
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
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validat ion/messages_cn.js" type="text/javascript"></script>
    <script type="text/javascript">
    	$(function(){
    		//初始化列表
    		queryTable();
    		$("#start").ligerDateEditor({showTime:true,  labelWidth: 80, labelAlign: 'left' });
			$("#end").ligerDateEditor({ showTime:true, labelWidth: 80, labelAlign: 'left' });
    	});	
    	
    	//初始化列表
    	function queryTable(){
    		var param = initParam();
    		window['g'] = $("#maingrid").ligerGrid({
				height: '80%',
				url:'<%=basePath %>/rest/qualityMaterReportManageAction/getPageList?param='+param,
				checkbox: false,
				columns:[
						{display: '系统质检编号', name: 'reportCode'},
						{display: '来料名称', name: 'materName'},
						{display: '来料规格', name: 'materGgxh'},
						{display: '来料时间', name: 'comeDate'},
						{display: '供货商', name: 'materFirm'},
						{display: '批次号', name: 'materBatch'},
						{display: '数量', name: 'materAmount'},
						{display: '检测状态', name: 'testState',render:function(row){
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
						{display: '检测结果', name: 'testResult',render:function(row){
							var html ="";
							if (row.testResult=="合格") {
								html = "<span style='color:green'>合格</span>";
							} else if (row.testResult=="不合格"){
								html = "<span style='color:red'>不合格</span>";
							}
							return html;
						}},
						{display: '是否入库', name: 'isCome'},
						{display: '检测时间', name: 'testDate'},
						{display: '操作', name: '',render: function (rowData)
			                {
							debugger;
							var h="";
								  // h +=  "<a href='javascript:' onclick='javascript:toDetailReport("+ rowData.id+ ");return false;'>详情</a>&nbsp;&nbsp;";
								   h +=  "<a href='javascript:' onclick='javascript:toUpdateReport("+ rowData.id+ ");return false;'>修改</a>&nbsp;&nbsp;";
								   h +=  "<a href='javascript:' onclick='javascript:deleteRow("+ rowData.id+ ");return false;'>删除</a>&nbsp;&nbsp;";
				                    /* h +=  '<a href="#" onclick="javascript:toUpdate('+ rowData.id+ ');return false;">修改</a>'; */
				                    return h;
			                }},
						{hide: 'id', name: 'id',width:'1px'}
				         ],
				toolbar: {
                  items: [
                  { text: '增加', click: addRow, icon: 'add' },
                  /*  { line: true },
                  { text: '修改', click: modifyBean, icon: 'modify' }, */
                  /*{ line: true },
                  { text: '删除', click: deleteRow, icon:'delete' } */
                  ]
              	}, 
     			 rownumbers:true,
     			 
				
			});
			$("#pageloading").hide();
		}
    	
    	function addRow(){
    		$.ligerDialog.open({
    			height: 400,
				width: 900,
				title: '添加来料质检计划',
				url: "<%=basePath %>rest/qualityMaterReportManageAction/toAddReport",
				showMax: false,
				showMin: false,
				isResize: true,
				slide: false,
				name:'repairAgain',
				buttons: [
				 /*  { text: '确认添加', onclick: function (item, dialog) {}}, */
				  { text: '完成', onclick: function (item, dialog) { dialog.close();queryTable(); } }
				         ]
    		});
    	}
    	
    	//删除
    	function deleteRow(id){
    		debugger;
    		$.ligerDialog.confirm('确认删除?',function(flag){
    			if(flag){
    				var url ="<%=basePath %>rest/qualityMaterReportManageAction/clearBean";
    				$.post(url,{id:id},function(data){
    					if (data.success) {
	    					$.ligerDialog.success(data.msg);
	    					queryTable();
						} else {
							$.ligerDialog.error(data.msg);
						}
    				},"json");
    			}
    		});
    	}
    	
	
    	//查询参数
    	function initParam(){
    		var params = {};
    		if ($("#start").val() != null && $("#start").val() != "") {
				params.start = $("#start").val();
			}
			if ($("#end").val() != null && $("#end").val() != "") {
				params.end = $("#end").val();
			}
    		var testState = $("#testState option:selected").val();
    		var testResult = $("#testResult option:selected").val();
    		var isCome = $("#isCome option:selected").val();
			if(testState != null && testState != ""){
				params.testState = testState;
			}    		
			if(testResult != null && testResult != ""){
				params.testResult = testResult;
			}    		
			if(isCome != null && isCome != ""){
				params.isCome = isCome;
			}    		
    		var param = JSON.stringify(params);
    		return param;
    	}
    	
    	function toUpdateReport(id){
    		$.ligerDialog.open({
				height: 600,
				width: 900,
				title: '来料质检修改',
				url: "<%=basePath %>rest/qualityMaterReportManageAction/toUpdateReport?id="+id,
				showMax: false,
				showMin: false,
				isResize: true,
				slide: false,
				name:'repairAgain',
				buttons: [
				 /*  { text: '确认添加', onclick: function (item, dialog) {}}, */
				  	 { text: '完成', onclick: function (item, dialog) { dialog.close();queryTable(); } }
				          ]
    		});
    	}
    
    	/* function toDetailReport(){
    		
    	} */
    	
    	function modifyBean(){
    		var grid =  $("#maingrid").ligerGrid();
    		
    	}
    </script>
    <style type="text/css">
		*{
				margin: 0px;
				padding: 0px;
			}
			
		#tb table{
				width:100%;
				margin: 0 auto;
				
			}
		#tb table tr{
				height: 40px;
			}
		#tb table tr td{
			}
		#tb table tr td span{
				display: inline-block;
				width: 90px;
				font-size:13px;
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
			margin-left: 10px;
			color: white;
		}
		#tb .search{
			background-color: blue;
		}
		#tb .export{
			background-color: green;
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
 				<span>检测状态:</span>
 				<select id="testState">
 					<option> </option>
 					<option>未检测</option>
 					<option>检测中</option>
 					<option>已检测</option>
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
				<span>检测时间:</span><input id="start" type="text" />
				<span style="text-align: center;width:20px;	">到</span>
				<input id="end" type="text" />
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
