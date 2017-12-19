<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'sysNoticeList.jsp.jsp' starting page</title>
    
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
    var basePath  = '<%=basePath%>';
	var routeName = "sysNoticeManage";
	var tbdArray = new Array();
	var grid;
	$(function ()
	    {
			$("#add input[name='startTime']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",labelWidth: 180,labelHeight:30});
			$("#add input[name='endTime']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",labelWidth: 180,labelHeight:30});
			
			myLigerGrid();
		
			
	    }
	);
	
	function myLigerGrid(){
		//pub_initList(routeName);
		grid=$("#maingrid").ligerGrid({
			url : basePath + "rest/"+ routeName + "Action/dataGridPage",
	        columns: [
	            { display: 'id', name: 'id',  width: 1,hide:true },
	            { display: 'key', name: 'key',  width: 1,hide:true},
	            { display: '内容', name: 'value',  width: 450 },
	            { display: '是否显示', name: 'isShow',  width: 120 },
	            { display: '类型', name: 'type',  width: 1,hide:true},
	            { display: '开始时间', name: 'startTime',  width: 180 },
	            { display: '结束时间', name: 'endTime',  width: 180 },
	            { display: '创建时间', name: 'createDate',  width: 180 },
		        {
                    display: '操作', isAllowHide: false,  width:120,
                    render: function (row,rowIndex)
                    {
                    	var html = '<a href="javascript:void(0)" onclick="editData(' + rowIndex+')">编辑</a>&nbsp;&nbsp;&nbsp;';
                    	html += '<a href="javascript:void(0)" onclick="deleteData(' + rowIndex + ')">删除</a>';
                        return html;
                    }
                }
	        ], 
	        toolbar: {
                items: [
                //{ text: '增加', click: addRows, icon: 'add'},
                { text: '增加', click: addClick, icon: 'add'},
                { line: true },
                
                ]
            },
            rownumbers: true,
	        pageSize:10,
	        width: '100%',
	        height:'70%',
	        onSuccess:function (json, grid){
	        	$("#pageloading").hide(); 
	        },
			onError:function (json, grid){
	        	$("#pageloading").hide(); 
	        }
	    });
		
	}
	
	//添加维修计划
	function addClick(){
		
		$.ligerDialog.open({
			title : "添加通知消息(显示在电子看板)",
			target:$("#add"),
			height : 250,
			width : 400,
			modal : true,
			buttons: [  { text: '添加', onclick: function (i, d) {saveaddData(d);}},
	                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
	                 ] 
		});
		
	}
	
	function saveaddData(d){
	   var value = $("[name='value']").val();
	   var isShow = $("[name='isShow']").val();
	   var start = $("[name='startTime']").val();
	   var end = $("[name='endTime']").val();
	   var data ={};
	   data.value= value;
	   data.isShow = isShow;
	   data.startTime = start;
	   data.endTime = end;
	   data.type="欢迎致辞";
	   var bean = JSON.stringify(data);
	   var url= basePath+"rest/sysNoticeManageAction/saveOrUpdate";
	   $.post(url,{bean:bean},function(data){
		   debugger;
		   if(data.success){
			   $.ligerDialog.success(data.msg);
			   myLigerGrid();
			   d.hide();
		   }else{
			   $.ligerDialog.error("添加失败！");
		   }
	   },"json");
		
	}
	
	function editData(index){
		var row = grid.rows[index];
		$("[name='value']").val(row.value);
		$("[name='isShow']").val(row.isShow);
		$("[name='startTime']").val(row.startTime);
		$("[name='endTime']").val(row.endTime);
		debugger;
		$.ligerDialog.open({
			title : "编辑通知消息(显示在电子看板)",
			target:$("#add"),
			height : 250,
			width : 400,
			modal : true,
			buttons: [  { text: '编辑', onclick: function (i, d) {saveEditData(d,row.id);}},
	                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
	                 ] 
		});
		
	}
	
	function saveEditData(d,id){
		
		var value = $("[name='value']").val();
		   var isShow = $("[name='isShow']").val();
		   var start = $("[name='startTime']").val();
		   var end = $("[name='endTime']").val();
		   var data ={};
		   data.id = id;
		   data.value= value;
		   data.isShow = isShow;
		   data.startTime = start;
		   data.endTime = end;
		   data.type="欢迎致辞";
		   var bean = JSON.stringify(data);
		   var url= basePath+"rest/sysNoticeManageAction/saveOrUpdate";
		   $.post(url,{bean:bean},function(data){
			   debugger;
			   if(data.success){
				   $.ligerDialog.success(data.msg);
				   myLigerGrid();
				   d.hide();
			   }else{
				   $.ligerDialog.error("编辑失败！");
			   }
		   },"json");
		
	}
	function deleteData(index){
		$.ligerDialog.confirm( "您确认要删掉这条数据吗？",
				function(result) {
					if (result == true) {
						var row = grid.rows[index];
						var id = row.id;
						var url= basePath+"rest/sysNoticeManageAction/clearRowData";
						   $.post(url,{id:id},function(data){
							   debugger;
							   if(data.success){
								   $.ligerDialog.success(data.msg);
								   myLigerGrid();
							   }else{
								   $.ligerDialog.error("删除失败！");
							   }
						   },"json");
						
					}else{
						return;
					}
				});
		
		
	}
	
	/*
	function addRows(){
		
		$.ligerDialog.open({ 
			url: basePath+"rest/sysNoticeManageAction/toAddEditPage", 
			height: 400,
			width: 600,
			showMax: false,
			showMin: false,
			isResize: true,
			slide: false,
			title:"添加欢迎致辞",
			name:'repairAgain',
			buttons: [
						   { text: '确认添加', onclick: function (item, dialog) {
							   var id = dialog.frame.$("[name=id]").val();
							   var value = dialog.frame.$("[name=value]").val();
							   var isShow = dialog.frame.$("[name=isShow]").val();
							   var start = dialog.frame.$("[name=startTime]").val();
							   var end = dialog.frame.$("[name=endTime]").val();
							   
							   var data ={};
							   if(id !=null  && id != ""){
							  	 data.id= id;
							   }
							   data.value= value;
							   data.isShow = isShow;
							   data.startTime = start;
							   data.endTime = end;
							   data.type="欢迎致辞";
							   var bean = JSON.stringify(data);
							   var url= basePath+"rest/sysNoticeManageAction/saveOrUpdate";
							   $.post(url,{bean:bean},function(data){
								   if(data.success){
									   $.ligerDialog.success(data.msg);
									   dialog.close();
								   }
							   },"json");
						   }}, 
						  { text: '取消', onclick: function (item, dialog) { dialog.close(); } }
						          ]	
			});
		
	}

	function edit(id,rowIndex){
		
		
		$.ligerDialog.open({ 
			url: basePath+"rest/sysNoticeManageAction/toAddEditPage?id="+row.id, 
			height: 300,
			width: 900,
			showMax: false,
			showMin: false,
			isResize: true,
			slide: false,
			title:"修改",
			name:'repairAgain',
			buttons: [
						  { text: '确认添加', onclick: function (item, dialog) { 
							  var row = grid.rows[rowIndex];
								dialog.frame.$("[name=value]").val(row.value);
								dialog.frame.$("[name=isShow]").val(row.grid);
								dialog.frame.$("[name=startTime]").val(row.start);
								dialog.frame.$("[name=endTime]").val(row.end);
							  
							  var id = dialog.frame.$("[name=id]").val();
							   var value = dialog.frame.$("[name=value]").val();
							   var isShow = dialog.frame.$("[name=isShow]").val();
							   var start = dialog.frame.$("[name=startTime]").val();
							   var end = dialog.frame.$("[name=endTime]").val();
							   
							   var data ={};
							   if(id !=null  && id != ""){
							  	 data.id= id;
							   }
							   data.value= value;
							   data.isShow = isShow;
							   data.startTime = start;
							   data.endTime = end;
							   data.type="欢迎致辞";
							   var bean = JSON.stringify(data);
							   var url= basePath+"rest/sysNoticeManageAction/saveOrUpdate";
							   $.post(url,{bean:bean},function(data){
								   if(data.success){
									   $.ligerDialog.success(data.msg);
									   dialog.close();
								   }
							   },"json");
						  } },
						  { text: '取消', onclick: function (item, dialog) { dialog.close();queryTable(); } }
						          ]	
			});
	}

	function reloadData(){
		var pa = $("#"+routeName+"ListForm").serialize();
		grid.loadServerData(decodeURIComponent(pa,true));
	}

	function show(id){
		$.ligerDialog.open({ 
			url: basePath+"rest/sysNoticeManageAction/toDetailPage?id="+id, 
			height: 600,
			width: 1080,
			modal:true, 
			title:"详细信息"
			});
	} 
	*/
	
	
	
    </script>
    
    <style type="text/css">
    	#add span{
    		display: inline-block;
    		width:90px;
    		text-align: right;
    	}
    	#add tr{
    		height:40px;
    	}
    	#add tr td input{
    		width:180px;
    		height:28px;
    	}
    	#add tr td select{
    		width:90px;
    	}
    	#add tr td textarea{
    		width:180px;
    		height:50px;
    	}
    </style>
    
  </head>
  
  <body style="overflow-x:hidden; padding:2px;">
    <div class="l-loading" style="display:block" id="pageloading"></div>
	<div class="l-clear"></div>
	<div id="maingrid"></div>
	<div style="display:none;"></div>
	
	<div id="add" style="display: none;">
   		<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<span>是否显示：</span>
					<select name="isShow">
						<option value="是">是</option>
						<option value="否">否</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<span>显示开始时间：</span>
					<input type="text" name="startTime"/>	
				</td>
			</tr>
			<tr>
				<td>
					<span>显示结束时间：</span>
					<input type="text" name="endTime" />	
				</td>
			</tr>
			<tr>
				<td>
					<span>内容：</span>
					<textarea name="value"></textarea>	
				</td>
			</tr>
		</table>
   	
   	</div>
	
  </body>
</html>
