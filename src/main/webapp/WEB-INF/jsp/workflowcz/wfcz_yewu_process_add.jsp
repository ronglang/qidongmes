<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <title>添加</title>
    <jsp:include page="../inc_right_new.jsp"></jsp:include>
<script type="text/javascript">
var s = new Object();   
s.type="";   //设为空不刷新父页面
window.returnValue=s; 

/*取值
var l_array=new Array();   
l_array=window.dialogArguments;   
var aa=l_array[0];   
var bb=l_array[1];   
var cc=l_array[2];   
*/

//关闭并刷新父窗口
function closeAndRefreshWindow(){
	var s=new Object();      
	s.type="ok";//设置返回值为ok就可以。//这里返回刷新父页面。
	window.returnValue=s;   
	window.close();    
} 
  
//关闭并刷新父窗口
function closeWindow(){      
	window.close();      
} 


//保存提交表单
$(function(){
	//检验表单
	$("#myform").validationEngine({
		validationEventTriggers:"keyup blur", //will validate on keyup and blur
		promptPosition:"centerRight",//OPENNING BOX POSITION, IMPLEMENTED: topLeft, topRight, bottomLeft,  centerRight, bottomRight
		prettySelect:true//,是否使用美化过的select
	 });
	 
	 
 $('#saveBtn').click(function(){
	var status=$("#myform").validationEngine("validate");
	if(status){//表单验证通过
			//开始保存 
			var formData=$("#myform").serialize();
			$('#saveBtn').hide();
			$.ajax({
				url:"wfcz_saveYewuProcess.action",
				type:'post',
				async:true,
				cache:false,
				timeout: 100000,
				data:formData,
				error:function(){
					alert("服务器繁忙，请稍后再试！");
				},
				success: function(result){
			        if(result=="1"){
			            alert("保存成功！");
			            closeAndRefreshWindow();
			         }else if(result=="2"){
				        alert("业务名称拼音字母缩写已经存在，请换用其他业务名称拼音字母缩写！");
			         }else{
		             	alert("保存失败！");
			         }
			     }
			});	
			$('#saveBtn').show();
	}//表单验证通过over
 });
});
</SCRIPT>
  </head>
  
  <body>
  <h3 style="text-align:center">添加业务</h3>
<div class="content_wrap" >
	<form id="myform" method="post">
	<table width="100%" class="table" valign="top" align="center" border="1" >
    <tr>
         <td class="title">业务名称</td>
         <td align="left">
         <input type="text" name="yewuMc" maxlength="100" size="40" class="validate[required]"/>
         </td>
    </tr>
    <tr>
         <td class="title" width="150px">业务名称拼音字母缩写</td>
         <td align="left">
         <input type="text" name="yewuPyzm" maxlength="200"  class="validate[required]"/>
         </td>
    </tr>
    
 </table>
         <p style="text-align:center">
          <a href="#" id="saveBtn" class="easyui-linkbutton" data="">保存数据</a>
          &nbsp;&nbsp;
          <a href="#" onClick="closeWindow()" class="easyui-linkbutton">关闭返回</a>
         </p>
		</form>
</div>
  </body>
</html>