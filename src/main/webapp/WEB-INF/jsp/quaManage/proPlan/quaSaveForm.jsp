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
    
    <title>抽检计划添加页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
   <link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
	<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
	<script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
	<script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
	<script src="<%=basePath %>core/js/pictureHandle.js"></script>
	<script src="<%=basePath %>core/js/tools.js"></script>
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
			#b_add{
				width:80%;
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
				width:100%;
				height:30px;
				line-height: 30px;
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
				width:40%;text-align: center;display: inline-block;float: left;
			}
		</style>
		
		<script type="text/javascript">
		
			var checkFlag = false;
			$(function(){
				$("#factTestDate").ligerDateEditor({format:'yyyy-MM-dd hh:mm:ss',/* label: '检测期', */ showTime:true, labelWidth: 105,labelAlign: 'right' });
				$("#planTestDate").ligerDateEditor({format:'yyyy-MM-dd hh:mm:ss',/* label: '检测期', */ showTime:true, labelWidth: 105,labelAlign: 'right' });
				$("#sub_btn").click(fun_empty);
				
				//返回
				$("#top .t_ret a").click(function(){
					window.location.href = "<%=basePath %>/rest/qualityProductPlanManageAction/toPageList";
				});
				
				$(".error").html("该字段不能为空！");
				
			});
			
			function submitForm() {
				
				checkFlag = checkData();
				if(checkFlag == false){
					$.ligerDialog.error("有星号的字段不能为空！");
					return;
				}
				if(confirm("确认提交吗?")){
					
					$("#form1").attr('action','<%=basePath %>rest/qualityProductPlanManageAction/updatePlan');
					$("#form1").submit();
				}else{
					return;
				}
			}
			
			function fun_empty(){
				isEmpty($("#type"));
				isEmpty($("#macCode"));
				isEmpty($("#seqName"));
				isEmpty($("#proGgxh"));
				isEmpty($("#testState"));
				isEmpty($("#testBy"));
				isEmpty($("#planTestDate"));
			}
			
			//执行事件方法
			function fun_div(){
				overDiv($("#obj_ggxh"));
				overDiv($("#obj_color"));
				overDiv($("#supply_agent"));
				overDiv($("#batchCode"));
				overDiv($("#rfidCode"));
				overDivNum($("#acount"));
				dateOverDiv($("#surveyDate"));
				dateOverDiv($("#expiryDate"));
				dateOverDiv($("#releaseDate"));
				overDiv($("#surveyReportCode"));
				overDiv($("#beizhu"));
			}
			
			//判断input中value是否为空
			function isEmpty(div){
				var divVal = div.val();
				debugger;
//				alert(divVal);
				if("" == divVal || divVal == null){
					div.parents("td").children(".error").css("display","inline-block");
					numFlag = false;
				}
				
			}
			//事件判断是否有值存在
			function overDiv(div){
				div.focus(function(){
					div.parents("td").children(".error").css("display","none");
					numFlag = true;
				});
				div.blur(function(){
					if(div.val() == "" || div.val() == null){
						div.parents("td").children(".error").css("display","inline-block");
						numFlag = false;
					}
				});
			}
			
			function checkData(){
				var type = $("#type").val();
				var macCode = $("#macCode").val();
				var seqName = $("#seqName").val();
				var proGgxh = $("#proGgxh").val();
				var testState = $("#testState").val();
				//var testResult = $("#testResult").val();
				//var testBy = $("#testBy").val();
				var reportCode = $("#reportCode").val();
				var planTestDate = $("#planTestDate").val();
				var factTestDate = $("#factTestDate").val();
				if(type == "" || macCode=="" || seqName=="" || proGgxh=="" || testState==""
						 || reportCode=="" || planTestDate==""||factTestDate==""){
					
					checkFlag = false;
				}else{
					checkFlag = true;
				}
					
				return checkFlag;
				
			}
			
		</script>
  </head>
  
  <body style="padding:10px">
  		<div id="top">
  		 	<div class="t_ret"><a href="javascript:">返回</a></div>
			<h3 class="t_text">抽检计划${info}</h3>
			<div style="width:30%;float: left;">&nbsp;</div>
  		 </div>
		
		<div id="msg" style="color: red ;display: none;font-size: 20px" align="center">${msg}</div>
		 <form id="form1" action="" method="post"  enctype="multipart/form-data">
		 	<div style="display: none"><input name="id" value="${plan.id}"></div>
		 	<table border="0" cellspacing="" cellpadding="" width="100%" id="b_add">
		 		<tr class="l-table-edit-tr">
		 			<td>
		 				<span>*抽检类型:</span>
		 				<select name="type" id="type">
		 						<option <c:if test="${paln.type=='' }">selected</c:if>></option>	
		 						<option <c:if test="${paln.type=='成品检测' }">selected</c:if>>成品检测</option>	
		 						<option <c:if test="${plan.type=='半成品检测' }">selected</c:if>>半成品检测</option>
		 				</select>
		 			</td>
		 			<td >
		 				<span >*机器编号:</span>
		 				<input id="macCode" type="text" name="macCode" value="${plan.macCode}" />
		 				
		 			</td>
		 			<td>
		 				<span >*工序名称:</span>
		 				<input id="seqName" type="text" name="seqName" value="${plan.seqName}"/>
		 				
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				<span>*规格型号:</span>
		 				<input id="proGgxh" type="text" name="proGgxh" value="${plan.proGgxh}" />
		 				
		 			</td>
		 			<td>
		 				<span>*检测状态:</span>
		 				<select name="testState" id="testState">
		 					<option value="未检测"<c:if test="${plan.testState=='未检测' }">selected</c:if>>未检测</option>	
		 					<option value="检测中"<c:if test="${plan.testState=='检测中' }">selected</c:if> >检测中</option>
		 					<option value="已检测"<c:if test="${plan.testState=='已检测' }">selected</c:if> >已检测</option>
		 				</select>
		 			</td>
		 			<td>
		 				<span>*检测结果:</span>
		 				<select name="testResult" >
		 						<option <c:if test="${plan.testResult=='' }">selected</c:if>> </option>
		 						<option <c:if test="${plan.testResult=='不合格' }">selected</c:if>>不合格</option>
		 						<option <c:if test="${plan.testResult=='合格' }">selected</c:if>>合格</option>	
		 						<option <c:if test="${plan.testResult=='异议' }">selected</c:if>>异议</option>
		 				</select>
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				<span>检测人:</span>
		 				<input id="testBy" type="text" name="testBy" value="${plan.testBy}"/>
		 				
		 			</td>
		 			
	 				<td>
	 					<span>*检验报告单号:</span>
	 					<input type="text" name="reportCode" id="reportCode" value="${plan.reportCode}" />
	 					
	 				</td>
	 				<td>
		 				<span>报告单上传:</span>
		 				<input type="file" name="myfiles" style="width: 150px;" id="upFile" />
		 				
		 			</td>
		 		</tr>
		 		<tr>
		 			
		 			<td >
		 				<span>*计划检测时间:</span>
		 				<input type="text" name="planTestDate" id="planTestDate" value=""  value="${plan.planTestDate}"/>
		 				<!-- <div class="error" style="left: 400px;"></div> -->
		 			</td>
		 			<td>
		 				<span>*实际检测时间:</span>
		 				<input type="text" name="factTestDate" id="factTestDate"  value="${plan.factTestDate}" />
		 				
		 			</td>
		 			<td>
		 				<span>系统生成检测单号:</span>
		 				<input type="text" name="survyCode" id="survyCode" readonly="readonly" value="${plan.survyCode}" />
		 				
		 			</td>
		 			
		 		</tr>
		 		<tr>
		 			<td colspan="2">
		 				<span>处理意见:</span>
		 				<input type="text" name="advice" id="advice" value=""  value="${plan.advice}"/>
		 				<!-- <div class="error" style="left: 400px;"></div> -->
		 			</td>
		 		</tr>
		 	</table>
 			<div id="end">
 				<input type="button" name="" id="sub_btn" value="提交" class="btn1" onclick="submitForm()"/>
 				<input type="reset" name="" id="sub_res" value="重置" class="btn2"/>
 					<!--<button class="btn1" onclick="formSubmit()">提交</button>
 					<button type="btn2">重置</button>-->
 					
 			</div>
		 				
		 </form>
		<%--  <div id="preview1" class="download_img" style="width: 500px;height: 800px" >
			 <img src="<%=basePath %>${plan.reportUrl}" id="preview"/>
		 	<!--此处为长传的图片-->
		 </div> --%>
		  <c:choose>
		 	<c:when test="${report.reportUrl == null}">  
			 	<div id="preview1"  style="width: 60%;margin: 0 auto;height:600px;display: none;" ><!-- class="download_img" -->
					 <img src="<%=basePath %>${report.reportUrl}" style="width: 100%;height:100%;" id="preview"/>
			 	<!--此处为长传的图片-->
			 	</div>
   			</c:when>
   			<c:otherwise>
   				<div id="preview1" style="width: 60%;margin: 0 auto;height:600px;" ><!-- class="download_img" -->
				 <img src="<%=basePath %>${report.reportUrl}" style="width: 100%;height:100%;" id="preview"/>
		 	<!--此处为长传的图片-->
				 </div>
   			</c:otherwise>
		 </c:choose>
	</body>
</html>
