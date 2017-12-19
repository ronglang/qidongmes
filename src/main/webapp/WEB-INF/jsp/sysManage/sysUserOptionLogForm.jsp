<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String sysUserOptionLog_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
	<head>
		

		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>sysUserOptionLogForm</title>
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="<%=basePath%>core/css/public.css" />
		<script type="text/javascript" src="<%=basePath%>core/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="<%=basePath%>core/js/cssBase.js"></script>
		<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

		<script>
			var basePath  = '<%=basePath%>';
    	</script>
	</head>

	<body>
		<form id="SysUserOptionLog">
		<table>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">用户ID：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="userId" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">登录IP：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="loginIp" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">创建人：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="createBy" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">创建时间：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="createDate" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
			</tr>
			  <tr class="l-table-edit-tr">
                <td align="right" class="l-table-edit-td ">备注：</td> 
                <td align="left" class="l-table-edit-td-input" colspan="7">
                <textarea style="margin-bottom:10px;height: 120px; width: 650px;" id="remark" name="remark" class="l-textarea" readonly="readonly"></textarea>
                </td>
                <td align="left" ></td>
            </tr>
		</table>
	</form>
	</body>

	<script type="text/javascript">
	var routeName = "sysUserOptionLogManage"; 
	var row_id = "";
	row_id = <%=sysUserOptionLog_id%>;
	
	$(document).ready(function() {
		if (row_id != null) {
			$("#SysUserOptionLog").ligerForm();
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
		}
	});
  	</script>
</html>

