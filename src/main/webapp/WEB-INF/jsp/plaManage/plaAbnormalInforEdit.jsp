<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String plaAbnormalInfor_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>plaAbnormalInforEdit</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "plaAbnormalInforManage"; 
	var row_id = "";
	row_id =<%=plaAbnormalInfor_id%>;
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
<script src="<%=basePath%>app/js/plaManage/plaAbnormalInforEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->

</head>

<body>
	<form id="plaAbnormalInforManage" method="post">
		<div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit" >
			<tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="id" name="id" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">生产令ID：</td><td align="left" class="l-table-edit-td-input"><input id="prodcutOrderId" name="prodcutOrderId" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">周计划ID：</td><td align="left" class="l-table-edit-td-input"><input id="weekPlanId" name="weekPlanId" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">机台计划ID：</td><td align="left" class="l-table-edit-td-input"><input id="machinePlanId" name="machinePlanId" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">机台计划进度ID：</td><td align="left" class="l-table-edit-td-input"><input id="machinePlanScheduleId" name="machinePlanScheduleId" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">异常大类：</td><td align="left" class="l-table-edit-td-input"><input id="abnormalClass" name="abnormalClass" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">异常小类：</td><td align="left" class="l-table-edit-td-input"><input id="abnormalSmallClass" name="abnormalSmallClass" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">处理方式：</td><td align="left" class="l-table-edit-td-input"><input id="treatMode" name="treatMode" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">处理结果(继续生产、返工等)：</td><td align="left" class="l-table-edit-td-input"><input id="treatResult" name="treatResult" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">异常原因(简单描述)：</td><td align="left" class="l-table-edit-td-input"><input id="abnormalReason" name="abnormalReason" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">发现场所(机台、质检、生产人员)：</td><td align="left" class="l-table-edit-td-input"><input id="scene" name="scene" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">异常参数编码：</td><td align="left" class="l-table-edit-td-input"><input id="abnormalParamCode" name="abnormalParamCode" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">参数允许值：</td><td align="left" class="l-table-edit-td-input"><input id="paramAllowableValue" name="paramAllowableValue" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">当前参数值：</td><td align="left" class="l-table-edit-td-input"><input id="paramCurrentValue" name="paramCurrentValue" type="text" ltype="text"/></td><td align="left"></td></tr>
		</table>
		<input class="l-button l-button-submit" type="submit" value="提交"/>
		<input class="l-button l-button-test" type="reset" value="重置"/>
	</form>
</body>
</html>

