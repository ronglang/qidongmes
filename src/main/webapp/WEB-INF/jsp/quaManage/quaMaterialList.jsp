<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>原材料检测单列表</title>
    
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
  </head>
  	<style type="text/css">
			*{
				margin: 0px;
				padding: 0px;
			}
			
			table{
				width:80%;
				margin: 0 auto;
				
			}
			table tr{
				height: 40px;
			}
			table tr td{
			}
			table tr td span{
				display: inline-block;
				width: 80px;
			}
			table tr td select{
				width: 120px;
			}
			
		</style>
  <script type="text/javascript">
  var basePath = "<%=basePath %>";
  $(function(){
	  //$.ligerDialog.close();
		queryTable();
		$("#start").ligerDateEditor({  labelWidth: 80, labelAlign: 'left' });
		$("#end").ligerDateEditor({  labelWidth: 80, labelAlign: 'left' });
		$("#msg").show();
		setTimeout(function(){
			$("#msg").empty();
		}, 5000);
		
		
		
	});
  
  function initSartch(){
	  
		var start = $("#start").val();
		var end = $("#end").val();
		var supplyAgent = $("#supplyAgent").val();
		var batchCode = $("#batchCode").val();
		var objSort = $("#objSort option:selected").text();
		var reportResult = $("#reportResult option:selected").text();
		var isCome = $("#isCome option:selected").text();
		var objName = $("#objName").val();
		var params = {};
		if(start != null && start !=""){
			params.start = start;
		}
		if(end != null && end !=""){
			params.end = end;
		}
		if(supplyAgent != null && supplyAgent !=""){
			params.supplyAgent = supplyAgent;
		}
		if(batchCode != null && batchCode !=""){
			params.batchCode = batchCode;
		}
		if(objSort != null && objSort !=""){
			params.objSort = objSort;
		}
		if(reportResult != null && reportResult !=""){
			params.reportResult = reportResult;
		}
		if(isCome != null && isCome !=""){
			params.isCome = isCome;
		}
		if(objName != null && objName !=""){
			params.objName = objName;
		}
		
		var param = JSON.stringify(params);
		return param;
  }
  
  //表格初始化
  function queryTable(){
			
		{
			var param = initSartch();
			
			window['g'] = $("#maingrid").ligerGrid({
				  height: '93%',
				url:'<%=basePath %>/rest/qualitySurveyReportManageAction/queryPage?param='+param,
				checkbox: true,
				columns:[
				{display: '物料类型', name: 'objSort'},
				{display: '名称', name: 'objName'},
				{display: '物料规格型号', name: 'objGgxh'},
				/* {display: '物料颜色', name: 'objColor'}, */
				{display: '供货商', name: 'supplyAgent' },
				{display: '批次号', name: 'batchCode'},						
				{display: '数量', name: 'acount'},
				{display: '单位', name: 'unit'},
				{display: '出厂检验报告', name: 'isSupplySurey'},
				{display: '检验报告单号', name: 'surveyReportCode'},					
				{display: '检验结果', name: 'reportResult'},
				{display: '质检日期', name: 'surveyDate',width:150},
				{display: '是否入库', name: 'isCome'},
				{display: '操作', name: '',render: function (rowData)
                {
					  var h =  '<a href="#" onclick="javascript:toDetail('+ rowData.id+ ');return false;">详情</a>&nbsp;&nbsp;';
	                    h +=  '<a href="#" onclick="javascript:toPrint('+ rowData.id+ ');return false;">打印</a>';
	                    return h;
                  /*   var h = "";
                        h += "<a href=#' onclick='javascript:toDetail(" + rowData.id + ")'>详情</a> ||";
                        h += "<a  href='#' onclick='javascript:toPrint("+rowData.id+")>打印</a> "; 
                    return h; */
                }},
				{hide: 'id', name: 'id',width:'1px'}
				],
				toolbar: {
                  items: [
                  { text: '增加', click: addRow, icon: 'add' },
                  { line: true },
                  { text: '修改', click: updateRow, icon: 'modify' },
                  { line: true },
                  { text: '删除', click: deleteRow, icon:'delete' }
                  ]
              	},
     			 rownumbers:true,
				
			});
			 $("#pageloading").hide();
		}
	}
  
  function toDetail(id){
	  window.location.href="<%=basePath %>/rest/qualitySurveyReportManageAction/reportDetail?id="+id;
  }
  function toPrint(id){
	  window.location.href="<%=basePath %>/rest/qualitySurveyReportManageAction/toPrintForm?id="+id;
  }
  //添加
  function addRow(){
		<%-- $.ligerDialog.open({
	        height: 450,
	        width: 950,
	        title: '添加原材料检测信息',
	        url: "<%=basePath %>/rest/qualitySurveyReportManageAction/toMaterialForm",
	        showMax: false,
	        showMin: false,
	        isResize: true,
	        slide: false,
	        name:'repairAgain',
	        buttons: [
	            { text: '取消', onclick: function (item, dialog) { dialog.close(); } }]

	    }); --%>
	   //window.open("<%=basePath %>/rest/qualitySurveyReportManageAction/toMaterialForm");
	    window.location.href="<%=basePath %>/rest/qualitySurveyReportManageAction/toMaterialForm";
	}

  	function updateRow(){
  		var manager = $("#maingrid").ligerGetGridManager();
    	var li = manager.getSelectedRows();
    	if (li.length != 1) {
			confirm("请选择一行数据");
			return;
		}
    	var id = li[0].id;
    	 window.location.href="<%=basePath %>/rest/qualitySurveyReportManageAction/toMaterialForm?id="+id;
  }
	function deleteRow(){
		var manager = $("#maingrid").ligerGetGridManager();
    	var li = manager.getSelectedRows();
    	if (li.length != 1) {
			confirm("请选择一行数据");
			return;
		}
    	$.ligerDialog.confirm('是否删除?',function(data){
    		if (data) {
    		var id = li[0].id;
    		$.post('<%=basePath %>/rest/qualitySurveyReportManageAction/clearReport',{id:id},function(data){
    			alert(data.msg);
    			queryTable();
    		},"json");
			} else {
				return;
			}
    	});
  }
	
	function doSearch(){
		queryTable();
	}
	
	function exportMaterial(){
		var param = initSartch();
		/*
		debugger;
		$.ajax({
			url: basePath+'rest/qualitySurveyReportManageAction/toExport?param='+param,
			type: "post",
			dataType: "json",
			success: function(){
				$.ligerDialog.success(map.success);
			},
			error: function(){
				$.ligerDialog.error("导出失败，发生异常！");
			}
			
		});
		*/
		window.location.href = basePath+'rest/qualitySurveyReportManageAction/toExport?param='+param;
		
	}
	
	
  </script>
 <body style="overflow-x:hidden; padding:2px;">
 	<div id="msg" style="color: red ;display: none;font-size: 20px" align="center">${msg}</div>
 	<div > 
 		<table border="0" cellspacing="50px" cellpadding="50px" width="80%">
 		<tr>
 			<td>
 				<span>检测时间</span><input id="start" type="text" />
 			</td>
 			<td>
 				<span>到</span><input id="end" type="text" />
 			</td>
 			<td><span>供应商</span><input id="supplyAgent" type="text" /></td>
 			<td><span>批次号</span><input id="batchCode" type="text" /></td>
 		</tr>
 		<tr>
 			<td><span>物料类型</span>
 				<select name="objSort" id="objSort">
 								<option></option>	
		 						<option>胶料</option>	
		 						<option>铜杆</option>
		 						<option>铝杆</option>
		 						<option>其他</option>	
		 				</select></td>
 			<td><span>是否合格</span>
 				<select name="reportResult" id="reportResult">
		 						<option></option>
		 						<option>合格</option>	
		 						<option>不合格</option>
		 		</select>
		 	</td>
 			<td><span>是否入库</span>
 			<select name="reportResult" id="isCome">
		 						<option></option>
		 						<option>已入库</option>	
		 						<option>未入库</option>
		 		</select></td>
		 		<td>
		 			<span>物料名称</span><input id="objName" type="text" />
		 		</td>
 			<td>
 				<button class="btn-search" onclick="doSearch()"></button>
 				<button class="btn-export" style="width:65px;height:26px;margin-left:15px;" onclick="exportMaterial()"></button>
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
