<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String sysRole_id = request.getParameter("id");
//	String resourceList = request.getAttribute("resourceList");
%>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML>
<html>
<head>


<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>sysRoleForm</title>
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>core/css/public.css" />
<script type="text/javascript" src="<%=basePath%>core/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=basePath%>core/js/cssBase.js"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script>
			var basePath  = '<%=basePath%>';
</script>

    	<style>
    		table{
    			margin:30px auto;
    		}
    		textarea{
    		margin-top:15px ; 
    		margin-bottom:15px;
		resize:none;
    		}
    	</style>
</head>

<body style="padding:0px; margin:0px;">
	<form id="SysRole">
		<div></div>
		<!-- 			<a href="javascript:history.go(-1)" class="return">返&nbsp;回</a> -->
		<table style="margin:30px auto;">
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td" style="width:100px;">角色名称：</td>
				<td align="left" class="l-table-edit-td-input" style="width:180px;">
					<div class="l-text-readonly"><input id="name" type="text" readonly></input>
					</div>
				</td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">已有权限：</td>
				<td align="left" class="l-table-edit-td-input" colspan="20" >
				<textarea style="width:509px; margin-top:10px;height: 120px;" rows="4"  class="l-textarea" readonly="readonly">${resourceList }</textarea></td>
			</tr>
			 <tr class="l-table-edit-tr">
                <td align="right" class="l-table-edit-td">备注：</td>
                <td align="left" class="l-table-edit-td-input" colspan="20"><textarea style="height: 120px;margin-top: 5px; width:100%" rows="4" id="remark" name="remark" class="l-textarea" readonly="readonly"></textarea></td>
                <td align="left"></td>
            </tr>
		</table>
		
	</form>
</body>

<script type="text/javascript">
	
if(document.all){
	 var textArea=$("textarea");
	 for(var i=0;i<textArea.length;i++){
	 textArea[i].style.width="510px";  
	 }
	 $("#title").attr("ligerui",'{width:510}');
	 
}else{
	 var textArea=$("textarea");
	 for(var i=0;i<textArea.length;i++){
	 textArea[i].style.width="510px";  
	 }
	 $("#title").attr("ligerui",'{width:510}');
}


	var routeName = "sysRoleManage";
	var row_id = "";
	row_id =
<%=sysRole_id%>;

	$(document).ready(function() {
		if (row_id != null) {
			var parm = "id=" + row_id;
			pub_initForm(routeName,parm);
		}
	});
</script>
</html>

