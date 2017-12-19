<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String quaPro_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>plaCourseForm</title>
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
	<form id="quaPro">
		<table>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">测试类型：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="type" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">轴名称：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="axisName" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">工序名称：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="seqName" type="text" ltype="text" readonly="readonly"></input></td>
				<!-- <td align="left" class="l-table-edit-td-input"><input
					id="planTestDate" type="text" ltype="text" readonly="readonly"></input></td> -->
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">计划测试时间：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="planTestDate" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">实际检测时间：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id=""factTestDate"" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">检测状态：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="testState" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">检测结果：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="testResult" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">质检报告单号：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="reportCode" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">机台编码：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="macCode" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
			</tr>
			<!-- <tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">检测人：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="testBy" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">检测结果：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="testResult" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">检测状态：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="testState" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
			</tr> -->
			<!-- <tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">工序名称：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="seqName" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">质检报告编号：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="reportCode" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">生产令ID：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="plaOrderId" type="text" ltype="text" readonly="readonly"></input></td>
				
				<td align="left"></td>
			</tr> -->
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">检测人：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="testBy" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">处理意见：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="advice" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">质检结果图片：</td>
				<td align="left" class="l-table-edit-td-input" ><a href="<%-- <%=basePath%> --%>reportUrl">查看图片</</a></td>
				<td align="left"></td>
			</tr>
		</table>
	</form>
</body>

<script type="text/javascript">
	var routeName = "qualityProductPlanManage";
	var row_id = "";
	row_id =<%=quaPro_id%>;

	$(document).ready(function() {
		if (row_id != null) {
			$("#quaPro").ligerForm();
			var parm = "id=" + row_id;
			pub_initForm(routeName, parm);
		}
	});
</script>
</html>

