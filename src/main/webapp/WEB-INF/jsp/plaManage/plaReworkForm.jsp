<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String plaRework_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">

		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>plaReworkForm</title>
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
		<form id="PlaRework">
			<table>
				<tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">异常id：</td><td align="left" class="l-table-edit-td-input"><input id="abnormalId" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">异常进度表id：</td><td align="left" class="l-table-edit-td-input"><input id="machinePlanScheduleId" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">异常机台计划id：</td><td align="left" class="l-table-edit-td-input"><input id="machinePlanId" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr><tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">异常周计划Id：</td><td align="left" class="l-table-edit-td-input"><input id="weekPlanId" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">异常品轴号：</td><td align="left" class="l-table-edit-td-input"><input id="abnormalAxisCode" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">异常发生工序：</td><td align="left" class="l-table-edit-td-input"><input id="abnormalSeq" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr><tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">新机台计划id：</td><td align="left" class="l-table-edit-td-input"><input id="newMachinePlanId" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">进度(未开始、生产中、已结束)：</td><td align="left" class="l-table-edit-td-input"><input id="scheduleState" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr>
			</table>
		</form>
	</body>

	<script type="text/javascript">
	var routeName = "plaReworkManage"; 
	var row_id = "";
	row_id = <%=plaRework_id%>;
	
	$(document).ready(function() {
		if (row_id != null) {
			$("#PlaRework").ligerForm();
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
		}
	});
  	</script>
</html>

