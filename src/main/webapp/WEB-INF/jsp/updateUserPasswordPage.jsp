<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户密码修改</title>


<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Silvery/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/js/datepicker/css/datepicker.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />




<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
<script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js" type="text/javascript"></script><script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>

<script type="text/javascript" src="<%=basePath%>core/js/datepicker/js/datepicker.js"></script>

<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script type="text/javascript">
 		var basePathUrl = "<%=basePath %>";
		$(function() {
			$("#updateUserPasswordForm").ligerForm();
		});
		
		
		function checkpassword(){
			if($("#password").val()==""){
				$("#password").focus();
				$('#msg2').text('请输入原密码');
				return false;
			}else{
				$('#msg2').text('');
				return true;
			}
		}
		function checknewpassword(){
			if($("#newpassword1").val()==""){
				$("#newpassword1").focus();
				$('#msg1').text('新密码不能为空');
				return false;
			}else{
				$('#msg1').text('');
				return true;
			}
		}
		function checkrepeatpassword(){
			if($("#newpassword1").val()!=$("#password").val()){//修改密码不能与原密码相同
				if($("#newpassword2").val()!=$("#newpassword1").val()){
					/* $("#newpassword2").focus(); */
					$('#msg').text('两次密码不一致');
					return false;
				}else{
					$('#msg').text('');
					return true;
				}
			}else{
				$('#msg').text('修改的密码不能与原密码相同');
					return false;
			}
			
		}
function updateUserPassword(){
	if(checkpassword()){
		if(checknewpassword()){
			if(checkrepeatpassword()){
				var datas = $("#updateUserPasswordForm").serialize();
				var url = basePathUrl+"rest/sysUserManageAction/updateUserPassword";
				$.ajax({
			        url: url,
			        type:"post",
			        data:datas,
			        dataType:'json',
			        success: function(json){
			        	subSuccess(json);
			        },
			        error: function(json) {
			        	 ajaxError(json);
			        }
			    });
			}
		}
	}
}

function ajaxError(json){
	if(json.msg){
		top.$.ligerDialog.error(json.msg);
	}else{
		if(json.responseText){
			top.$.ligerDialog.error(json.responseText);
		}else{
			//top.$.ligerDialog.error("系统错误，请联系管理员！");
			var m = json.msg;
			var r= json.responseText;
			alert(json+','+typeof(json)+m+',,,,,,'+r);
		}
	}
}		
		
/**
 * 重写提交数据成功后的回调函数
 */
function subSuccess(json) {
	if (json.success) {
		//top.$.ligerDialog.success(json.msg);
		//alert(json.msg);
		ajaxError(json);
		window.parent.location.href = basePathUrl+'login.jsp';
		/* var flag =confirm(json.msg);
		if(flag==true){
			window.parent.location.href = basePathUrl+'login.jsp';
		} */
	}else {
		//top.$.ligerDialog.error(json.data);
		ajaxError(json);
		$("#password").focus();
	}
}
</script>
   <style type="text/css">
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-reset{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    </style>
</head>
<body>
	<form id="updateUserPasswordForm">
		<table  cellspacing="0" cellpadding="0">
			 <tr style="height: 50px;">
                <td align="right" class="l-table-edit-td">原密码:</td>
                <td align="left" class="l-table-edit-td">
                <input name="password" type="password" id="password" ltype="text" onblur="checkpassword();" />
                <span id="msg2" style="color: red;"> </span>
                </td>
                <td align="left"></td>
            </tr>
			
			 <tr style="height: 50px;">
                <td align="right" class="l-table-edit-td">新密码:</td>
                <td align="left" class="l-table-edit-td">
                <input name="newpassword" type="password" id="newpassword1" ltype="text" onblur="checknewpassword();" />
               	<span id="msg1" style="color: red;"> </span>
                </td>
                <td align="left"></td>
            </tr>
			 <tr style="height: 50px;">
                <td align="right" class="l-table-edit-td">确认新密码:</td>
                <td align="left" class="l-table-edit-td">
                <input name="newpasswordrepeat" type="password" id="newpassword2" ltype="text" onblur="checkrepeatpassword();"/>
                <span id="msg" style="color: red;"> </span>
                </td>
                <td align="left"></td>
            </tr>
		</table>
		 <div >
		 	<div style="float:left;">
	            <input style="margin-left: 70px;" class="l-button l-button-submit" type="button" value="提交" onclick="updateUserPassword();"/> 
		 	</div>
		 	<div style="float:left;">
	            <input style="float: right;" class="l-button l-button-submit" type="reset" value="重置" />
		 	</div>
        </div>
	</form>
</body>
</html>