<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String plaCourseDetails_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>plaCourseDetailsForm</title>
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>core/css/public.css" />
<script type="text/javascript"
	src="<%=basePath%>core/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=basePath%>core/js/cssBase.js"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script>
			var basePath  = '<%=basePath%>';
</script>
</head>

<body>
	<form id="PlaCourseDetails">
		<table>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">：</td>
				<td align="left" class="l-table-edit-td-input"><input id="id"
					type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">创建日期：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="createDate" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">创建人：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="createBy" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">工作单号：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="wsCode" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">描述信息：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="remark" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">工艺编号：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="craftCode" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">工序编号：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="seqCode" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">完成数量：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="compAmount" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="planEndDate" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="actEndDate" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="planAmount" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">计量单位：</td>
				<td align="left" class="l-table-edit-td-input"><input id="unit"
					type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
			</tr>
		</table>
	</form>
</body>

<script type="text/javascript">
	var routeName = "plaCourseDetailsManage";
	var row_id = "";
	row_id =
<%=plaCourseDetails_id%>
	;

	$(document).ready(function() {
		if (row_id != null) {
			$("#PlaCourseDetails").ligerForm();
			var parm = "id=" + row_id;
			pub_initForm(routeName, parm);
		}
	});
</script>
</html>

