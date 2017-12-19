<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>基础数据</title>
   <script type="text/javascript">
   		var basePath = '<%=basePath%>';
   		var routeName = "storeMaterialBasicInfoManage";
   		var row_id = null;
   </script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="icon" href="core/img/site_icon/favicon.ico" type="image/x-icon">
	<link rel="shortcut icon" href="core/img/site_icon/favicon.ico"/>
	<link rel="bookmark" href="<%=basePath%>core/img/site_icon/favicon.ico"/>
	<link rel="stylesheet" href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" />
	<link rel="stylesheet" href="<%=basePath%>app/css/storeBasicInfo/storeMaterialBasic.css"/>
	<link rel="stylesheet" href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" type="text/css" />
	<script type="text/javascript" src="<%=basePath%>core/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>core/js/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js"></script>
	<script type="text/javascript" src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.min.js"></script>
  	<%-- <script type="text/javascript" src="<%=basePath%>app/js/storeManage/storeBasicInfo/storeMaterialBasicInfo.js"></script> --%>
  	<script type="text/javascript">
  		var grid;
  		var param = "";
  		$(function(){
  			getTypes();
  			
  			getTable();
  			
  			$("#sort").click(function(){
  				var material_type = $("select[name='material_type']").val();
  				var mater_name = $("input[name='mater_name']").val();
  				var mater_ggxh = $("input[name='mater_ggxh']").val();
  				var data = {};
  				debugger;
  				if(material_type != ""){
  					data.material_type = material_type;
  				}
  				if(mater_name != ""){
  					data.mater_name = mater_name;
  				}
  				if(mater_ggxh != ""){
  					data.mater_ggxh = mater_ggxh;
  				}
  				param = JSON.stringify(data);
  				getTable();
  			});
  			
  			$("#showall").click(function(){
  				param = "";
  				getTable();
  			});
  			
  			
  		});
  	
  		function getTable(){
  			grid = $("#DataList").ligerGrid({
  				width : '98.5%',
  				height : '85%',
  				method:'post',
  				url : basePath + "rest/" + routeName + "Action/getPageList?param="+param,
  				checkbox : true,
  				columns : [{
	  					hide : '序号',
	  					name : 'id',
	  					width : '0',
	  					isSort :true
	  				},
	  				
	  		        { display: '原料名称', name: 'mater_name'},
	  		        { display: '原料规格型号', name: 'mater_ggxh'},
	  		        { display: '原料类型', name: 'material_type'},
	  		      	{ display: '单位', name: 'unit'},
	  		        { display: '供应商', name: 'delivery_info'},
	  		        { display: '备注', name: 'remark'}
	  		       
  				],
  				enabledEdit: true,
  				usePager : true,
  				rownumbers: true,
  				async:true,
  				sortName:'id',
  				pageSize : 15,
  				pageSizeOptions:[10,15,20],//可指定每页页面大小
  				/* isChecked:InitialCheck, */
  				toolbar : {
  					items : [ {
  						text : '新增',
  						/*click: Add,*/
  						click:addInfo,
  						img : basePath + 'core/img/site_icon/add.gif'
  					}, {
  						text : '编辑',
  						click:updateInfo,
  						img : basePath + 'core/img/site_icon/edit.gif',
  					}, {
  						text : '删除',
  						click : clearBean,
  						img : basePath + 'core/img/site_icon/delete.gif',
  					} , 
  					{line:true},
  					/*可用，暂时注释，后续再优化*/
  					{
  						text : '原料类型',
  						click : matersType,
  						img : basePath + 'core/img/site_icon/view.gif',
  					} 
  					
  					]
  				},
  				onSuccess : function(data) {
  					//alert(JSON.stringify(data));
  				},
  				onSelectRow :function(rowdata,rowid,rowobj){
  					row = rowdata;
  					rows = grid.getSelecteds();
  				},
  				onDblClickRow : function(rowdata, rowindex, rowobj) {
  				},
  			});
  			
  		}
  		
  		function clearBean(){
  			
  			var rows = grid.getSelecteds();
  			if(rows.length != 1){
				$.ligerDialog.warn("请选择一行数据删除！"); 
			}else {
				$.ligerDialog.confirm('你确定需要删除此条数据吗？', function (flag) { 
					
					if(flag){
						$.post('<%=basePath%>rest/storeMaterialBasicInfoManageAction/clearBean',{id:rows[0].id},
								function(data){
									$.ligerDialog.confirm(data.msg,"提示");
									getTable();
								},"json");
					}else{
						return;
					}
					
				});
				
		
			}
  			
  		}
  		
  		function addInfo(){
  			$.ligerDialog.open({
  				 title: '添加原料',
  			        url: basePath+"rest/storeMaterialBasicInfoManageAction/toAddMaterial",
  			        height: 400,
  			        width: 800,
  			        showMax: false,
  			        showMin: false,
  			        isResize: true,
  			        slide: false,
  			        name:'repairAgain',
  			        buttons: [{ text: '关闭', onclick: function (item, dialog) { dialog.close(); }}]
  			});
  		}
  		
  		function updateInfo(){
  			var rows = grid.getSelecteds();
  			debugger;
  			if(rows.length>1)
				{
					$.ligerDialog.confirm("只能编辑一行数据，请重新选择！","提示"); 
				} else if(rows.length==0){
					$.ligerDialog.question("请选择编辑行！","警告");
				} else {
					//OpenDialog(row,'退料记录-编辑');
					$.ligerDialog.open({
						 title: '修改',
					        url: basePath+"rest/storeMaterialBasicInfoManageAction/toInputMaterial?id="+rows[0].id,
					        height: 500,
					        width: 800,
					        showMax: false,
					        showMin: false,
					        isResize: true,
					        slide: false,
					        name:'repairAgain',
					        buttons: [{ text: '取消', onclick: function (item, dialog) { dialog.close(); }}]
					});
					
				}
  		}
  		
  		function getTypes(){
  			$.ajax({
  				url:basePath+"rest/storeMaterialBasicInfoManageAction/getAllType",
  				dataType: 'json',
  				type: 'POST',
  				success: function(data){
  					if(data != null){
  						var h = "";
  	  					for(var i=0;i<data.length;i++){
  	  						h += "<option value='"+data[i]+"'>"+data[i]+"</option>";
  	  					}
  	  					$("select[name='material_type']").append(h);
  					}
  					
  					
  				},
  				error: function(){
  					$.ligerDialog.error("发生未知错误！");
  				}
  				
  				
  			});
  			
  			
  		}
  		
  		
  		
  		function matersType(){
  			
  				$.ligerDialog.open({
  	  		        title: '原料类型',
  	  		        width: 680,
  	  		        height: 470,
  	  		        url: basePath+'rest/storeMaterialBasicInfoManageAction/toStoreType',
  	  		        isResize: true,
  	  		        modal: true,
  	  		        buttons: [
  	  		                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
  	  		                 ]                                  
  	  		   });
  			
  		}
  		
  		
  	</script>
  	<style type="text/css">
  		#InputArea{
  			width:100%;
  			height:50px;
  			line-height:50px;
  		}
  		#InputArea label{
  			margin-left:40px;
  		}
  		#InputArea label select{
  			width:130px;
  			height:25px;
  		}
  		#InputArea label input{
  			width:150px;
  			height:25px;
  		}
  		#sort{
  			border:none;
    		margin-left:20px;
    		background-color: #0099CC;
    		color:white;
    		width:80px;
    		height:25px;
    		border-radius:5px;
    		cursor: pointer;
  		}
  		#sort:hover{
    		background-color: #48B9E3;
    	}
    	#showall{
    		border:none;
    		margin-left:20px;
    		background-color: #FFAB95;
    		color:white;
    		width:80px;
    		height:25px;
    		border-radius:5px;
    		cursor: pointer;
    	}
    	#showall:hover{
    		background-color: #F5BFB2;
    	}
    	
  	</style>
  </head>
  
  <body>
    <div class="container">
    	<div id="InputArea">
    		<label>
    			<span>原料类型：</span>
    			<select name="material_type">
    				<option value=""></option>
    			</select>
    		</label>
    		<label>
    			<span>原料名称：</span>
    			<input type="text" name="mater_name" value="" class="" />
    		</label>
    		<label>
    			<span>原料规格型号：</span>
    			<input type="text" name="mater_ggxh" value="" class="" />
    		</label>
    		<input type="button" name="" id="sort" value="查  询" />
			<input type="button" name="" id="showall" value="显示所有"/>
    	</div>
    	<!-- <div id="QueryArea" style="width:96%;border-radius:10px;">
			<form id="queryform"></form>
		</div> -->
		<div id="DataList"></div>
    </div>
    
    
    
    
    
  </body>
</html>
