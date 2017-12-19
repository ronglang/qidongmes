<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String plaMachinePlanSchedule_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">

		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>plaMachinePlanScheduleForm</title>
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
		<form id="PlaMachinePlanSchedule">
			<table>
				<tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="id" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">周计划id：</td><td align="left" class="l-table-edit-td-input"><input id="weekPlanId" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">工作日：</td><td align="left" class="l-table-edit-td-input"><input id="workDate" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr><tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">机台id：</td><td align="left" class="l-table-edit-td-input"><input id="machineId" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">人员id：</td><td align="left" class="l-table-edit-td-input"><input id="employeeId" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">轴名称：</td><td align="left" class="l-table-edit-td-input"><input id="axisName" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr><tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">产品工艺：</td><td align="left" class="l-table-edit-td-input"><input id="productCraft" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">物料规格：</td><td align="left" class="l-table-edit-td-input"><input id="materType" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">计划开始时间：</td><td align="left" class="l-table-edit-td-input"><input id="planStartTime" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr><tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">实际考试时间：</td><td align="left" class="l-table-edit-td-input"><input id="factStartTime" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">计划结束时间：</td><td align="left" class="l-table-edit-td-input"><input id="planEndTime" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">实际结束时间：</td><td align="left" class="l-table-edit-td-input"><input id="factEndTime" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr><tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">计划来料时间：</td><td align="left" class="l-table-edit-td-input"><input id="plamIncomingTime" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">实际来料时间：</td><td align="left" class="l-table-edit-td-input"><input id="factIncomingTime" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">来料轴号(多个以，隔开)：</td><td align="left" class="l-table-edit-td-input"><input id="incomingAxis" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr><tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">工序编码：</td><td align="left" class="l-table-edit-td-input"><input id="seqCode" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">工序半制品轴号：</td><td align="left" class="l-table-edit-td-input"><input id="seqSemiProductAxis" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">计划配送到下一个工序时间：</td><td align="left" class="l-table-edit-td-input"><input id="planSendNextSeqDate" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr><tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">实际配送下一个工序时间：</td><td align="left" class="l-table-edit-td-input"><input id="factSendNextSeqDate" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">上工序编码：</td><td align="left" class="l-table-edit-td-input"><input id="upSeqCode" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">下工序编码：</td><td align="left" class="l-table-edit-td-input"><input id="nextSeqCode" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr><tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">下工序机台编号：</td><td align="left" class="l-table-edit-td-input"><input id="nextSeqMachineCode" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">半制品长度：</td><td align="left" class="l-table-edit-td-input"><input id="semiProductLen" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">准备时间(分钟)：</td><td align="left" class="l-table-edit-td-input"><input id="readyTime" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr><tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">生产时间(分钟)：</td><td align="left" class="l-table-edit-td-input"><input id="productTime" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">生产速度（m/s）：</td><td align="left" class="l-table-edit-td-input"><input id="productSpeed" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">排序：</td><td align="left" class="l-table-edit-td-input"><input id="sort" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr>
			</table>
		</form>
	</body>

	<script type="text/javascript">
	var routeName = "plaMachinePlanScheduleManage"; 
	var row_id = "";
	row_id = <%=plaMachinePlanSchedule_id%>;
	
	$(document).ready(function() {
		if (row_id != null) {
			$("#PlaMachinePlanSchedule").ligerForm();
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
		}
	});
  	</script>
</html>

