<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String plaMachinePlan_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>plaMachinePlanEdit</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "plaMachinePlanManage"; 
	var row_id = "";
	row_id =<%=plaMachinePlan_id%>;
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
<script src="<%=basePath%>app/js/plaManage/plaMachinePlanEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->

</head>

<body>
	<form id="plaMachinePlanManage" method="post">
		<div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit" >
			<tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="id" name="id" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">周计划id：</td><td align="left" class="l-table-edit-td-input"><input id="weekPlanId" name="weekPlanId" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">工作日：</td><td align="left" class="l-table-edit-td-input"><input id="workDate" name="workDate" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">机台id：</td><td align="left" class="l-table-edit-td-input"><input id="machineId" name="machineId" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">人员id：</td><td align="left" class="l-table-edit-td-input"><input id="employeeId" name="employeeId" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">轴名称：</td><td align="left" class="l-table-edit-td-input"><input id="axisName" name="axisName" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">计划开始时间：</td><td align="left" class="l-table-edit-td-input"><input id="planStartTime" name="planStartTime" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">计划完成时间：</td><td align="left" class="l-table-edit-td-input"><input id="planEndTime" name="planEndTime" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">实际开始时间：</td><td align="left" class="l-table-edit-td-input"><input id="factStartTime" name="factStartTime" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">实际完成时间：</td><td align="left" class="l-table-edit-td-input"><input id="factEndTime" name="factEndTime" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">成品轴号(在最后一道工序落轴是生产)：</td><td align="left" class="l-table-edit-td-input"><input id="endAxisCode" name="endAxisCode" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">工艺路线编码：</td><td align="left" class="l-table-edit-td-input"><input id="routeCode" name="routeCode" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">段长：</td><td align="left" class="l-table-edit-td-input"><input id="partLen" name="partLen" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">生产状态(未开始、生产中、已结束)：</td><td align="left" class="l-table-edit-td-input"><input id="productState" name="productState" type="text" ltype="text"/></td><td align="left"></td></tr>
		</table>
		<input class="l-button l-button-submit" type="submit" value="提交"/>
		<input class="l-button l-button-test" type="reset" value="重置"/>
	</form>
</body>
</html>

