<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加原料form</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	--> 
	<script type="text/javascript">
   		var basePath = '<%=basePath%>';
   		var routeName = "storeMaterialBasicInfoManage";
   		var row_id = null;
   </script>
<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/json2.js" type="text/javascript"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    	<script type="text/javascript" src="<%=basePath%>core/js/jquery-validation/jquery.validate.min.js"></script>
  	<%-- <script type="text/javascript" src="<%=basePath%>app/js/storeManage/storeBasicInfo/storeInfoInput.js"></script> --%>
  	<script type="text/javascript">
  	var groupicon = "<%=basePath %>/core/img/site_icon/communication.gif";
  	
  //----------------全部重写---------------------------------------	
  	
  	$(function(){
  		//获取所有的类型
  		getAllType();
  		
  		getTypes();
  		
  		var msg = '${msg}';
  		if(msg != null && msg != ""){
  			$.ligerDialog.warn(msg);
  			debugger;
  			//parent.$.ligerDialog.close(); //关闭弹出窗
  			//parent.$(".l-dialog,.l-window-mask").remove(); //关闭弹出层
  			parent.$.ligerDialog.reload();
  		}
  		
  	});
  //获取所有的类型
  	function getAllType(){
  		var url="<%=basePath%>rest/storeMaterialBasicInfoManageAction/getAllType";
  		$.post(url,{},function(data){
  			
  		},"json");
  	}
 	//当供应商窗口值发生变化,模糊查询	
  	function getAllDelivery(){
  		//供应商输入框的值
  		var info = $("#delivery_info").val();
  		var url="<%=basePath%>rest/storeMaterialBasicInfoManageAction/getAllDelivery";
  		$.post(url,{info:info},function(data){
  			
  		},"json");
  	}
 	
 	//上传form
 	function submitForm(){
 		
 		var mater_name = $("#inputform input[name='mater_name']").val();
 		var mater_ggxh = $("#inputform input[name='mater_ggxh']").val();
 		if(mater_name == "" || mater_ggxh == ""){
 			alert("原料名称和原料规格型号不能为空！");
 			
 		}else{
 			$("#inputform").attr('action','<%=basePath %>rest/storeMaterialBasicInfoManageAction/saveOrUpdate');
 	 		$("#inputform").submit();
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
	  						if($("#selectDefault").html() == data[i]){
	  							
	  							h += "<option value='"+data[i]+"' selected = 'selected'>"+data[i]+"</option>";
	  						}else{
	  							h += "<option value='"+data[i]+"'>"+data[i]+"</option>";
	  						}
	  						
	  					}
	  					$("select[name='material_type']").append(h);
					}
					
					
				},
				error: function(){
					$.ligerDialog.error("发生未知错误！");
				}
				
				
			});
			
			
		}
  	
  	</script>
  	<style type="text/css">
  		#inputform table{
  			width:100%;
  			margin:20px auto;
  		}
  		#inputform table tr{
  			height:40px;
  		}
  		#inputform table tr td span{
  			display: inline-block;
  			width:130px;
  			text-align: right;
  		}
  		#inputform .foot{
  			width:100%;
  			height:50px;
  			text-align: center;
  		}
  		#inputform .foot .btn{
  			border:none;
    		margin-left:20px;
    		background-color: #0099CC;
    		color:white;
    		width:80px;
    		height:25px;
    		border-radius:5px;
    		cursor: pointer;
    		font-size: 14px;
  		}
  		#inputform .foot .btn:hover{
    		background-color: #48B9E3;
    	}
    	#inputform .foot .res{
    		border:none;
    		margin-left:20px;
    		background-color: #FFAB95;
    		color:white;
    		width:80px;
    		height:25px;
    		border-radius:5px;
    		cursor: pointer;
    		font-size: 14px;
    	}
    	
    	#inputform .foot .res:hover{
    		background-color: #F5BFB2;
    	}
  	</style>
  </head>
  
  <body>
    <form id="inputform">
    	<table>
			<tr>
				<td>
				<input id="id" name="id" type="text" style="display: none" value="${info.id}"> 
				<input id="id" name="id" type="text" style="display: none">
					<span>原料类型:</span>
					<span>
						<span id="selectDefault" style="display:none">${info.material_type}</span>
						<select name='material_type'>
							
						</select>
					</span>
				</td>
				<td>
					<span>原料名称:</span>
					<span><input id="mater_name" name="mater_name" type="text" value="${info.mater_name }"></span>
				</td>
			</tr>
			<tr>
				<td>
					<span>原料规格型号:</span>
					<span><input id="mater_ggxh"  name="mater_ggxh" type="text" value="${ info.mater_ggxh}"></span>
				</td>
				<td>
					<span>供应商:</span>
					<span><input id="delivery_info" name="delivery_info" type="text" value="${info.delivery_info}"></span>
				</td>
			</tr>
			<tr>
				<td>
					<span>单位:</span>
					<span><input id="unit" name="unit" type="text" value="${info.unit }"></span>
				</td>
				<td>
					<span>备注:</span>
					<span><input id="remark" name="remark" type="text" value="${info.remark }"></span>
				</td>
			</tr>
		</table>
		<div class="foot">
			<input type="submit" class="btn" onclick="submitForm()" value="提交"/>
			<input type="reset" class="res" value="重置"/>
		</div>
		 
    </form>
   
	
	
  </body>
</html>
