<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String plaMachinePlanSchedule_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>plaMachinePlanScheduleEdit</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "plaMachinePlanScheduleManage"; 
	var row_id = "";
	row_id =<%=plaMachinePlanSchedule_id%>;
</script>
<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
 <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
<!-- 默认引用1end -->

<!-- 默认引用 -->
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script src="<%=basePath%>app/js/plaManage/plaMachinePlanScheduleEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->

</head>

<body>
	<form id="plaMachinePlanScheduleManage" method="post">
		<div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit" >
			<tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="id" name="id" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">周计划id：</td><td align="left" class="l-table-edit-td-input"><input id="weekPlanId" name="weekPlanId" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">工作日：</td><td align="left" class="l-table-edit-td-input"><input id="workDate" name="workDate" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">机台id：</td><td align="left" class="l-table-edit-td-input"><input id="machineId" name="machineId" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">人员id：</td><td align="left" class="l-table-edit-td-input"><input id="employeeId" name="employeeId" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">轴名称：</td><td align="left" class="l-table-edit-td-input"><input id="axisName" name="axisName" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">产品工艺：</td><td align="left" class="l-table-edit-td-input"><input id="productCraft" name="productCraft" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">物料规格：</td><td align="left" class="l-table-edit-td-input"><input id="materType" name="materType" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">计划开始时间：</td><td align="left" class="l-table-edit-td-input"><input id="planStartTime" name="planStartTime" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">实际考试时间：</td><td align="left" class="l-table-edit-td-input"><input id="factStartTime" name="factStartTime" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">计划结束时间：</td><td align="left" class="l-table-edit-td-input"><input id="planEndTime" name="planEndTime" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">实际结束时间：</td><td align="left" class="l-table-edit-td-input"><input id="factEndTime" name="factEndTime" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">计划来料时间：</td><td align="left" class="l-table-edit-td-input"><input id="plamIncomingTime" name="plamIncomingTime" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">实际来料时间：</td><td align="left" class="l-table-edit-td-input"><input id="factIncomingTime" name="factIncomingTime" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">来料轴号(多个以，隔开)：</td><td align="left" class="l-table-edit-td-input"><input id="incomingAxis" name="incomingAxis" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">工序编码：</td><td align="left" class="l-table-edit-td-input"><input id="seqCode" name="seqCode" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">工序半制品轴号：</td><td align="left" class="l-table-edit-td-input"><input id="seqSemiProductAxis" name="seqSemiProductAxis" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">计划配送到下一个工序时间：</td><td align="left" class="l-table-edit-td-input"><input id="planSendNextSeqDate" name="planSendNextSeqDate" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">实际配送下一个工序时间：</td><td align="left" class="l-table-edit-td-input"><input id="factSendNextSeqDate" name="factSendNextSeqDate" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">上工序编码：</td><td align="left" class="l-table-edit-td-input"><input id="upSeqCode" name="upSeqCode" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">下工序编码：</td><td align="left" class="l-table-edit-td-input"><input id="nextSeqCode" name="nextSeqCode" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">下工序机台编号：</td><td align="left" class="l-table-edit-td-input"><input id="nextSeqMachineCode" name="nextSeqMachineCode" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">半制品长度：</td><td align="left" class="l-table-edit-td-input"><input id="semiProductLen" name="semiProductLen" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">准备时间(分钟)：</td><td align="left" class="l-table-edit-td-input"><input id="readyTime" name="readyTime" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">生产时间(分钟)：</td><td align="left" class="l-table-edit-td-input"><input id="productTime" name="productTime" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">生产速度（m/s）：</td><td align="left" class="l-table-edit-td-input"><input id="productSpeed" name="productSpeed" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">排序：</td><td align="left" class="l-table-edit-td-input"><input id="sort" name="sort" type="text" ltype="text"/></td><td align="left"></td></tr>
		</table>
		<input class="l-button l-button-submit" type="submit" value="提交"/>
		<input class="l-button l-button-test" type="reset" value="重置"/>
	</form>
</body>
</html>

