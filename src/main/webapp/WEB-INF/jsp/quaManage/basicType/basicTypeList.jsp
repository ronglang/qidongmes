<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>质检参数类型名称表</title>
    
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
			queryTable();
		});
		
		//查询列表
		function queryTable(){
			{
				var param = paramInit();
				window['g'] = $("#maingrid").ligerGrid({
					height: '93%',
					url:'<%=basePath %>/rest/qualityBasicTypeManageAction/getPageList?param='+param,
					checkbox: true,
					columns:[
							{display: '质检类型', name: 'type'},
							{display: '质检类型名称', name: 'typeName'},
							{display: '质检类型依据', name: 'testAccording'},
							{display: '备注', name: 'remark'},
							{display: '操作', name: '',render: function (rowData)
				                {
									  var h =  '<a href="#" onclick="javascript:toDetailParam('+ rowData.id+ ');return false;">参数详情</a>&nbsp;&nbsp;';
					                    /* h +=  '<a href="#" onclick="javascript:toUpdate('+ rowData.id+ ');return false;">修改</a>'; */
					                    return h;
				                }},
							{hide: '物料类型', name: 'id',width:'1px'}
					         ],
					toolbar: {
	                  items: [
	                  { text: '增加', click: addRow, icon: 'add' },
	                  { line: true },
	                  { text: '修改', click: toUpdate, icon: 'modify' },
	                  { line: true },
	                  { text: '删除', click: deleteRow, icon:'delete' }
	                  ]
	              	},
	     			 rownumbers:true,
					
				});
				$("#pageloading").hide();
			}
		}
		
		//去添加页面
		function addRow(){
			$.ligerDialog.open({
				height: 300,
				width: 750,
				title: '质检类型添加',
				url: basePath+"rest/qualityBasicTypeManageAction/toUpdate",
				showMax: false,
				showMin: false,
				isResize: true,
				slide: false,
				name:'repairAgain',
				buttons: [{ text: '确认添加', onclick: function (item, dialog) {
					var type = dialog.frame.$("[name=type] option:selected").text();
					var typeName = dialog.frame.$("[name=typeName]").val();
					var testAccording = dialog.frame.$("[name=testAccording]").val();
					var remark = dialog.frame.$("[name=remark]").val();
					//alert(type);
					var bean = {};
					bean.type = type;
					bean.typeName = typeName;
					bean.testAccording = testAccording;
					bean.remark = remark;
					
					var beans = JSON.stringify(bean);
					var url = basePath+"rest/qualityBasicTypeManageAction/saveBean";
					$.post(url,{bean:beans},function(data){
						if(data.success){
							$.ligerDialog.success(data.msg);
							//关闭
							dialog.close();
							//刷新付页面
							queryTable();
						}else{
							$.ligerDialog.error(data.msg);
						}
						//$.ligerDialog.alert(data.msg, '提示');
					},"json");
				}},{ text: '完成', onclick: function (item, dialog) { dialog.close();queryTable(); } }]
			});
		}
		
		//去详情页
		function toUpdate(){
			var manager = $("#maingrid").ligerGetGridManager();
        	var li = manager.getSelectedRows();
        	if(li.length>1 || li.length<1 ){
        		$.ligerDialog.warn("请选择你需要修改的一行");
        		return;
        	}
        	var id = li[0].id; 
			$.ligerDialog.open({
				height: 300,
				width: 750,
				title: '质检类型修改',
				url: basePath+"rest/qualityBasicTypeManageAction/toUpdate?id="+id,
				showMax: false,
				showMin: false,
				isResize: true,
				slide: false,
				name:'repairAgain',
				buttons: [{ text: '确认修改', onclick: function (item, dialog) {
					var type = dialog.frame.$("[name=type] option:selected").text();
					var typeName = dialog.frame.$("[name=typeName]").val();
					var testAccording = dialog.frame.$("[name=testAccording]").val();
					var remark = dialog.frame.$("[name=remark]").val();
					//alert(type);
					var bean = {};
					bean.type = type;
					bean.typeName = typeName;
					bean.testAccording = testAccording;
					bean.remark = remark;
					bean.id = id;
					var beans = JSON.stringify(bean);
					var url = basePath+"rest/qualityBasicTypeManageAction/saveBean";
					$.post(url,{bean:beans},function(data){
						//$.ligerDialog.alert(data.msg, '提示');
						if(data.success){
							$.ligerDialog.success(data.msg);
							//关闭
							dialog.close();
							//刷新付页面
							queryTable();
						}else{
							$.ligerDialog.error(data.msg);
						}
					},"json");
					
				}},{ text: '完成', onclick: function (item, dialog) { dialog.close();queryTable(); } }]
			});
		}
		
		function toDetailParam(id){
			//var id  = rowData.id;
			debugger;
			$.ligerDialog.open({
				height: 600,
				width: 850,
				title: '质检类型参数关联',
				url: basePath+"rest/qualityBasicTypeManageAction/toTypeParam?id="+id,
				showMax: false,
				showMin: false,
				isResize: true,
				slide: false,
				name:'repairAgain',
				buttons: [{ text: '确认修改', onclick: function (item, dialog) {}}
				          ,{ text: '取消', onclick: function (item, dialog) { dialog.close(); } }]
			});
		}
		
		//去详情页面
		function toDetail(){
			
		}
		//删除
		function deleteRow(){
			var manager = $("#maingrid").ligerGetGridManager();
			var li = manager.getSelectedRows();
			if (li.length==0) {
				$.ligerDialog.confirm("请选择一行进行删除");
				//alert("请选择一行进行保存");
				return ;
			}else if(li.length>1){
				//alert("请只选择一行进行保存");
				$.ligerDialog.confirm("请选择一行进行删除");
				return ;
			}
			var id = li[0].id; 
			var url =basePath+"rest/qualityBasicTypeManageAction/clearBean"
			$.post(url,{id:id},function(data){
				if(data.success){
					$.ligerDialog.success(data.msg);
					queryTable();
				} else{
					$.ligerDialog.error(data.msg);
				}
			},"json");
		}
		
		//查询参数
		function paramInit(){
			var type = $("#type option:selected").val();
			var typeName = $("#typeName").val();
			var testAccording = $("#testAccording").val();
			var param = {};
			if (type != null && type !="") {
				param.type = type;
			}
			if (testAccording != null && testAccording !="") {
				param.testAccording = testAccording;
			}
			if (typeName != null && typeName !="") {
				param.typeName = typeName;
			}
			var paramstr = JSON.stringify(param);
			return paramstr;
		}
		</script>
  </head>
  
  <body style="overflow-x:hidden; padding:2px;">
  <div > 
 		<table border="0" cellspacing="50px" cellpadding="50px" width="80%">
 			<tr>
 				<td><span>质检类型</span>
 					<select id="type" name="type">
 						<option></option>
 						<option value="来料质检">来料质检</option>
 						<option value="制程质检">制程质检</option>
 						<option value="电缆质检">电缆质检</option>
 					</select>
 				</td>
 				<td><span>质检名称:</span>
 					<input id="typeName" name="typeName" type="text">
 				</td>
 				<td><span>质检依据</span>
 					<input id="testAccording" name="testAccording" type="text">
 				</td>
 				<td></td>
 				<td></td>
 				<td>
 					<button class="btn-search" onclick="queryTable()"></button>
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
