<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'basicParamUpdate.jsp' starting page</title>
    
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
	<script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/core/base.js"></script>
	<style type="text/css">
		<style type="text/css">
			*{
				margin: 0px;
				padding: 0px;
			}
			body,div,table{
				margin: 0px;
				padding: 0px;
				font-size: 14px;
			}
			h3{
				text-align: center;
				margin: 0 auto;
			}
			form{
			}
			ul{
				list-style-type: none;
			}
			#b_add{
				width:90%;
				margin: 0 auto;
				border-top: 1px solid gray;
			}
			#b_add tr{
				height: 50px;
			}
			#b_add tr td{
				width: 33%;
				font-size: 13px;
				position: relative;
			}
			#b_add tr td span{
				display: inline-block;
				width: 100px;
				text-align: right;
			}
			#b_add tr td select{
				width: 80px;
			}
			#b_add tr td input{
				
			}
			#end{
				width: 200px;
				height: 50px;
				margin: 10px auto;
				
			}
			#end input.btn1{
				background-color: #0A90F0;
				border-radius:5px;
				display: inline-block;
				width: 80px;
				height: 25px;
				line-height: 25px;
				color: white;
				cursor: pointer;
				border: none;
				margin: 12px;
			}
			#end input.btn1:hover{
				background-color: #62B4ED;
			}
			#end input.btn2{
				background-color: #AACE1F;
				border-radius:5px;
				display: inline-block;
				width: 80px;
				height: 25px;
				line-height: 25px;
				color: white;
				cursor: pointer;
				border: none;
			}
			#end input.btn2:hover{
				background-color: #CEE574;
			}
			#b_add .error{
				position: absolute;
				top: 10px;
				left: 250px;
				width: 170px;
				height: 30px;
				text-indent: 15px;
				line-height: 30px;
				background: url(<%=basePath %>app/images/errror_bg.png);
				display: none;
			}
			#top{
				width:100%;height:30px;line-height: 30px;
			}
			#top .t_ret{
				width:30%;float: left;text-align: center;text-decoration: underline;
			}
			#top .t_ret a{
				color: #038BDE;
			}
			#top .t_ret a:hover{
				color: red;
			}
			#top .t_text{
				width:40%;text-align:center;display: inline-block;float: left;
			}
			/*智能搜索样式*/
			#searchresult{
		    	width:150px; 
		    	position:absolute;
		    	z-index:1; 
		    	overflow:hidden;
		    	border:1px solid gray;
		    	border-top:none;
		    	background-color: #DBDBDB;
		    }
		    .line{
		    	font-size:12px;
		    	width:150px;
		    	height:20px;
		    	line-height:20px;
		    	text-indent: 2px;
		    }
		    .line:hover{
		    	background-color:gray;
		    	color:white;
		    }
		    .hover{
		    	background-color:gray; 
		    	width:150px;
		    	
		    	color:#767676;
		    }
		    .std{ 
		    	display:inline-block;
		    	text-align:left;
		    	width:100%;
		    	height:20px;
		    	line-height:20px;
		    }
		</style>
		<script type="text/javascript">
			function formSubmit() {
				alert(11);
			}
			
			//去重操作
			function doCheck(){
				var type =$("#type option:selected").text(); 
				var typeName = $("#typeName");
				var url = "<%=basePath%>rest/qualityBasicTypeManageAction/checkTypeByName";
				$.post(url,{type:type,typeName:typeName},function(data){
					alert(data.msg);
					if (data.success) {
						//$("#check").html(data.msg);
					} else{
						//$("#check").html(data.msg);
					}
				},"json");
				
			}
			
			function formSubmit(){
				var params ={};
				params.id = $("#id");
				params.type = $("#type");
				params.typeName = $("#typeName");
				params.testAccording = $("#testAccording");
				params.remark = $("#remark");
				var param = JSON.stringify(params);
				var url = "<%=basePath%>rest/qualityBasicTypeManageAction/saveBean";
				$.post(url,{bean:param},function(data){
					if(data.success){
						$.ligerDialog.success(data.msg);
					}else{
						$.ligerDialog.error(data.msg);
					}
				},"json");
			}
		</script>
		
  </head>
  
  <body style="padding:10px">
  	<div id="top">
  		 	<h3 class="t_text" >质检类型名称名称</h3>
  		 	<div style="width:30%;float: left;">&nbsp;</div>
  		 </div>
  	<div>
	    <form id="form" action="" method="post">
	  	  <div style="display: none"><input name="id" id="id" value="${type.id }"></div>
	    	  <table border="0" cellspacing="" cellpadding="" width="80%" id="b_add">
	          	<tr class="l-table-edit-tr">
	          		<td>
		 				<span>*检测类型:</span>
		 				<select name="type" id="type">
		 						<option <c:if test="${type.type=='来料质检' }">selected</c:if>>来料质检</option>	
		 						<option <c:if test="${type.type=='制程质检' }">selected</c:if>>制程质检</option>
		 						<option <c:if test="${type.type=='电缆质检' }">selected</c:if>>电缆质检</option>
		 						<%-- <option <c:if test="${type.type=='其他质检' }">selected</c:if>>其他质检</option>	 --%>
		 				</select>
		 			</td>
		 			<td >
		 				<span>*质检类型名称:</span>
		 				<input id="typeName" type="text" name="typeName"  value="${type.typeName}" />
		 				<div class="error" id="check"></div>
		 			</td>
		 			
	          	</tr>  
	          	<tr class="l-table-edit-tr">
	          		<td >
		 				<span>*质检依据:</span>
		 				<input id="testAccording" type="text" name="testAccording" value="${type.testAccording}" />
		 				<div class="error"></div>
		 			</td>
	          		<td >
		 				<span>*备注:</span>
		 				<input id="remark" type="text" name="remark" value="${type.remark}" />
		 				<div class="error"></div>
		 			</td>
	          	</tr>	
	          </table>
	         <!-- <div id="end">
 				<input type="button" name="" id="sub_btn" value="提交" class="btn1" onclick="formSubmit()"/>
 				<input type="reset" name="" id="sub_res" value="重置" class="btn2"/>
 			</div> --> 
	    </form>
   </div> 
  </body>
</html>
