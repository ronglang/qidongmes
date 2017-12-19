<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String plaCourseTask_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">

		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>plaCourseTaskForm</title>
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
		<form id="PlaCourseTask">
			<table>
				<tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="createDate" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="createBy" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">人员编号：</td><td align="left" class="l-table-edit-td-input"><input id="personId" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr><tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">工作单编号：</td><td align="left" class="l-table-edit-td-input"><input id="wsCode" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">开始时间：</td><td align="left" class="l-table-edit-td-input"><input id="startTime" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">结束时间：</td><td align="left" class="l-table-edit-td-input"><input id="endTime" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr><tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">机台编号：</td><td align="left" class="l-table-edit-td-input"><input id="macId" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="planDetailId" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">计划数量：</td><td align="left" class="l-table-edit-td-input"><input id="planCount" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr><tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">完成数量：</td><td align="left" class="l-table-edit-td-input"><input id="finishCount" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">当前工序，冗余字段：</td><td align="left" class="l-table-edit-td-input"><input id="seqCurrent" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">当前工艺：</td><td align="left" class="l-table-edit-td-input"><input id="craftCurrent" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr><tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">当前日期：</td><td align="left" class="l-table-edit-td-input"><input id="dateCurrent" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">是否开始执行，计划删除标志：</td><td align="left" class="l-table-edit-td-input"><input id="isInuse" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr>
			</table>
		</form>
	</body>

	<script type="text/javascript">
	var routeName = "plaCourseTaskManage"; 
	var row_id = "";
	row_id = <%=plaCourseTask_id%>;
	
	$(document).ready(function() {
		if (row_id != null) {
			$("#PlaCourseTask").ligerForm();
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
		}
	});
  	</script>
</html>

