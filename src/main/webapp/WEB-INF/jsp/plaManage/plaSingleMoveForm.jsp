<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String plaSingleMove_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">

		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>plaSingleMoveForm</title>
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
		<form id="PlaSingleMove">
			<table>
				<tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">所属合同：</td><td align="left" class="l-table-edit-td-input"><input id="contractId" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">所属生产令：</td><td align="left" class="l-table-edit-td-input"><input id="productOrderId" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">所属周计划：</td><td align="left" class="l-table-edit-td-input"><input id="weekPlanId" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr><tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">所属机台计划(可能为空)：</td><td align="left" class="l-table-edit-td-input"><input id="machinePlanId" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">挪用者轴号：</td><td align="left" class="l-table-edit-td-input"><input id="moveAxisCode" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">挪用者轴名称：</td><td align="left" class="l-table-edit-td-input"><input id="moveAxisName" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr>
			</table>
		</form>
	</body>

	<script type="text/javascript">
	var routeName = "plaSingleMoveManage"; 
	var row_id = "";
	row_id = <%=plaSingleMove_id%>;
	
	$(document).ready(function() {
		if (row_id != null) {
			$("#PlaSingleMove").ligerForm();
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
		}
	});
  	</script>
</html>

