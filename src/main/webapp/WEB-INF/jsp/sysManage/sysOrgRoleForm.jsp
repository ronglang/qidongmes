<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String sysOrgRole_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
	<head>
		

		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>sysOrgRoleForm</title>
		<link rel="stylesheet" href="<%=basePath%>core/css/public.css" />
		<script type="text/javascript" src="<%=basePath%>core/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="<%=basePath%>core/js/cssBase.js"></script>

		<script>
			var basePath  = '<%=basePath%>';
    	</script>
	</head>

	<body>
		<form id="SysOrgRole">
			<div></div>
			<a href="javascript:history.go(-1)" class="return">返&nbsp;回</a>
			<table>
				<tr><td>：</td><td><label id="ID" ></label></td></tr><tr><td>：</td><td><label id="ORG_ID" ></label></td></tr><tr><td>：</td><td><label id="ROLE_ID" ></label></td></tr>
			</table>
		</form>
	</body>

	<script type="text/javascript">
	var routeName = "sysOrgRoleManage"; 
	var row_id = "";
	row_id = <%=sysOrgRole_id%>;
	
	$(document).ready(function() {
		if (row_id != null) {
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
		}
	});
  	</script>
</html>

