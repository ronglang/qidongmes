<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />

<title>Insert title here</title>
</head>
<body>
	
	<div id="maingrid"></div> 

<script type="text/javascript" src="<%=basePath %>core/js/jquery-1.7.2.js"></script>
<script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script type="text/javascript">
 	
	var baseUrl='<%=basePath %>';
	var data=false;
	var grid=false;

	$(function () {
		gridLoad();
	 });
	 
	function gridLoad(){
		 grid =  $("#maingrid").ligerGrid({
		        	url: baseUrl+"rest/resourceManager/queryResourceAll",
		            columns: [
		                { display: '资源名称', id:'text', name: 'text', width: 200, align: 'left' },
		                { display: '编码', id:'id', name: 'id', width: 100 },
		           		{ display: 'url', name: 'url', width: 300, align: 'left' },
		           		{ display: '类型', name: 'menuType', width: 100, align: 'left'},
		           		{ display: '排序值', name: 'sort', width: 80, align: 'left' },
		            ], 
		            delayLoad :true,
		            rownumbers:true,
		            width: '99.8%', 
		            pageSizeOptions: [5, 10, 15, 20], 
		            height: '99.99%',
		            alternatingRow: false, 
		            tree: { columnId: 'text' },
		            onAfterShowData:function(currentData){
		            	grid.collapseAll();
						var data=grid.data.Rows;
		            }
		            
		}); 
       grid.loadData(); 
	}
	
	function add(){
		var rows=grid.getSelectedRow();
		if(rows){
			$.ligerDialog.open({
				url: baseUrl+'rest/resourceManager/toSysresourceAddEditPage', 
				height: 300,
				width: 500,
				isResize:true,
				modal:true,
				showMax:true,
				buttons: [ 
				           { text: '确定', onclick: function (item, dialog) { addOk(item, dialog); } }, 
				           { text: '取消', onclick: function (item, dialog) { dialog.close(); } }
				         ],
				onLoaded:function(){
					var data={pid:rows.id,pName:rows.text};
					this.frame.setData(data);
				}
			});
			
		}
		else{
			$.ligerDialog.alert('请选择资源添加的位置！', '提示', 'warn');
		}
	}
	
	
	function addOk(item, dialog){
		dialog.frame.ok();
		console.info(item);
		console.info(dialog);
	}
	
	function del(){
		var rows = [];
		rows = grid.getSelectedRows();
		var ids = "";
		for(var i = 0;i < rows.length;i++){
			var obj = rows[i];
			ids += obj.id;
			if(i < rows.length){
				ids+=",";
			}
		}
		
		$.ajax( {
			type : 'post',
			url : baseUrl+'rest/resourceManager/delete?ids='+ids,
			data:"id=1",
			dataType : 'json',
			error : function(res) {
				top.$.ligerDialog.error(res.msg);
			},
			success : function(res) {
				grid.loadServerData();
				top.$.ligerDialog.success(res.msg);
			}
		});
		
	}
	
	function removeExcessAttribute(){}
	        
</script>	

</body>
</html>