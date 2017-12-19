<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>材料类型</title>
    
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
	
	<script type="text/javascript">
	var basePath = '<%=basePath%>';
	var grid;
		$(function(){
			
			getLigerGrid();
			
		});
		
		
		
		function getLigerGrid(){
			grid = $("#DataList").ligerGrid({
				width: '98%',
  				url : basePath + "rest/storeMaterialBasicInfoManageAction/getPageTypeList",
  				checkbox : true,
  				columns : [
  				            { hide : '序号', name : 'id', width : '0', isSort :true},
  				            { display: '原料类型', name: 'matername'},
	  						{ display: '原料大类', name: 'materialtype'},
	  						{ display: '原料数量', name: 'numbers'},
  				],
  				rownumbers: true,
  				enabledEdit: true,
  				sortName:'id',
  				pageSize : 10,
  				pageSizeOptions:[10,15,20],//可指定每页页面大小
  				toolbar : {
  					items : [ 
	  					{text : '新增',click:addDatasType,img : basePath + 'core/img/site_icon/add.gif'}, 
	  					{text : '编辑',click:updateType,img : basePath + 'core/img/site_icon/edit.gif',}, 
	  					{text : '删除',click : clearType,img : basePath + 'core/img/site_icon/delete.gif',} , 
	  					{line:true},
  					]
  				},
  				
  				
			});
			$("#pageloading").hide();
			
		}
	
		//添加类型
  		function addDatasType(){
  			$("select[name='bigType']").html("");
  			$.ajax({
  				url:basePath+"rest/storeMaterialBasicInfoManageAction/getAllDataBigType",
  				dataType: 'json',
  				type: 'POST',
  				success: function(data){
  					debugger;
  					if(data != null){
  						var h = "";
  	  					for(var i=0;i<data.length;i++){
  	  						//h += "<option value='"+data[i].mater_name+"' title='"+data[i].id+"'>"+data[i].mater_name+"</option>";
  	  					h += "<option value='"+data[i].mater_name+"' title='1'>"+data[i].mater_name+"</option>";
  	  					}
  	  					$("select[name='bigType']").append(h);
  					}
  					
  					
  				},
  				error: function(){
  					$.ligerDialog.error("服务器错误！");
  				}
  				
  			});
  			
  			$.ligerDialog.open({
  		        target:$("#addType"),
  		        title: '增加类型',
  		        width: 380,
  		        height: 150,
  		        isResize: true,
  		        modal: true,
  		        buttons: [  { text: '添加', onclick: function (i, d) { saveAllTypes(d); }},
  		                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
  		                 ]                                  
  		   });
  			
  			
  		}
  		
  		function saveAllTypes(d){
  			var p_code = $("select[name='bigType'] option:selected").attr("title");
  			var big_type = $("select[name='bigType']").val();
  			var material_type = $("input[name='allType']").val();
  			if(material_type == ""){
  				$.ligerDialog.error("请填写材料类型！");
  				return;
  			}
  			var data = {};
  			data.pCode = p_code;
  			data.big_type = big_type;
  			data.material_type = material_type;
  			var param = JSON.stringify(data);
  			$.ajax({ 
  				url: basePath+'rest/storeMaterialBasicInfoManageAction/saveAllTypes?param='+param,
  		   		type:"post",
  		   		dataType:'json',
  		   		success: function(map){
  		   			if(map.success != "" && map.success != null){
  		   				getLigerGrid();
  		   				$.ligerDialog.success(map.success);
  		   				d.hide();
  		   				
  		   			}else{
  		   				$.ligerDialog.error(map.error);
  		   			}
  		   	    },
  		   	    error : function() {
  		   	    	$.ligerDialog.error("添加失败，服务器错误！");
  				}
  			});
  			
  		}
		
		
		
		function updateType(){
			var row = grid.selected;
			if(row.length != 1){
				$.ligerDialog.warn("请选择一条数据编辑！");
				return ;
			}
			
			var numbers = row[0].numbers;
			if(parseInt(numbers) > 0){
				$.ligerDialog.warn("不得编辑正在使用的类型！");
				return ;
			}
			
			$("select[name='bigType']").html("");
  			$.ajax({
  				url:basePath+"rest/storeMaterialBasicInfoManageAction/getAllDataBigType",
  				dataType: 'json',
  				type: 'POST',
  				success: function(data){
  					debugger;
  					if(data != null){
  						var h = "";
  	  					for(var i=0;i<data.length;i++){
  	  						if(data[i].mater_name == row[0].materialtype){
  	  						h += "<option value='"+data[i].mater_name+"' title='1' selected='selected'>"+data[i].mater_name+"</option>";
  	  						}else{
  	  							h += "<option value='"+data[i].mater_name+"' title='1'>"+data[i].mater_name+"</option>";
  	  						}
  	  						
  	  					}
  	  					$("select[name='bigType']").append(h);
  					}
  					
  					
  				},
  				error: function(){
  					$.ligerDialog.error("服务器错误！");
  				}
  				
  			});
  			
  			$("#addType input[name='allType']").val(row[0].matername);
  			var id = row[0].id;
  			$.ligerDialog.open({
  		        target:$("#addType"),
  		        title: '编辑类型',
  		        width: 380,
  		        height: 150,
  		        isResize: true,
  		        modal: true,
  		        buttons: [  { text: '编辑', onclick: function (i, d) { saveModifyTypes(d,id); }},
  		                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
  		                 ]                                  
  		   });
			
			
		}
		
		function saveModifyTypes(d,id){
			
			var p_code = $("select[name='bigType'] option:selected").attr("title");
  			var big_type = $("select[name='bigType']").val();
  			var material_type = $("input[name='allType']").val();
  			if(material_type == ""){
  				$.ligerDialog.error("请填写材料类型！");
  				return;
  			}
  			var data = {};
  			data.id = id;
  			data.pCode = p_code;
  			data.big_type = big_type;
  			data.material_type = material_type;
  			var param = JSON.stringify(data);
  			$.ajax({ 
  				url: basePath+'rest/storeMaterialBasicInfoManageAction/saveAllTypes?param='+param,
  		   		type:"post",
  		   		dataType:'json',
  		   		success: function(map){
  		   			if(map.success != "" && map.success != null){
  		   				getLigerGrid();
  		   				$.ligerDialog.success(map.success);
  		   				d.hide();
  		   				
  		   			}else{
  		   				$.ligerDialog.error(map.error);
  		   			}
  		   	    },
  		   	    error : function() {
  		   	    	$.ligerDialog.error("添加失败，服务器错误！");
  				}
  			});
			
			
		}
		
		function clearType(){
			var row = grid.selected;
			
			if(row.length != 1){
				$.ligerDialog.warn("请选择一条数据删除！");
				return ;
			}
			
			var numbers = row[0].numbers;
			if(parseInt(numbers) > 0){
				$.ligerDialog.warn("不得删除正在使用的类型！");
				return ;
			}
			var id = row[0].id;
			
			$.ligerDialog.confirm('你确定需要删除此条数据吗？', function (flag) { 
				if(flag){
					$.ajax({
						url: basePath+'rest/storeMaterialBasicInfoManageAction/clearMaterType?id='+id,
						type: 'post',
						dataType: 'JSON',
						success:function(data){
							if(data.success != null && data.success != ""){
								getLigerGrid();
								$.ligerDialog.success(data.success);
							}else{
								$.ligerDialog.error(data.error);
							}
						},
						error:function(){
							$.ligerDialog.error("服务器出错！");
						}
						
					});
				}else{
					return;
				}
				
			});
			
			
		}
		
	</script>
	
	<style type="text/css">
	
		#addType{
    		margin: 0 auto;
    	}
    	#addType table{
    		width:100%;
    	}
    	#addType table tr{
    		height:30px;
    		line-height: 30px;
    	}
	
	</style>
  </head>
  
  <body>
  
  
	    <div class="l-loading" style="display:block" id="pageloading"></div>
	    <div class="l-clear"></div>
	    
	    <div id="DataList"></div>
	    <div style="display:none;"></div>
    
	   <div id="addType" style="display:none;">
    	<table>
    		<tr>
    			<td>
    				<span>材料大类：</span>
    				<label>
    					<select name='bigType'>
						</select>
    				</label>
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<span>原料类型：</span>
    				<label>
    					<input type="text" name="allType" />
    				</label>
    			</td>
    		</tr>
    	</table>
    	
    	
    </div>
	
    
    
  </body>
</html>
