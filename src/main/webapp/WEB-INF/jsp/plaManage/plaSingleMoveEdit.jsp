<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String plaSingleMove_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>plaSingleMoveEdit</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "plaSingleMoveManage"; 
	var row_id = "";
	row_id =<%=plaSingleMove_id%>;
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
<script src="<%=basePath%>app/js/plaManage/plaSingleMoveEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->

</head>

<body>
	<form id="plaSingleMoveManage" method="post">
		<div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit" >
			<tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="id" name="id" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">所属合同：</td><td align="left" class="l-table-edit-td-input"><input id="contractId" name="contractId" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">所属生产令：</td><td align="left" class="l-table-edit-td-input"><input id="productOrderId" name="productOrderId" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">所属周计划：</td><td align="left" class="l-table-edit-td-input"><input id="weekPlanId" name="weekPlanId" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">所属机台计划(可能为空)：</td><td align="left" class="l-table-edit-td-input"><input id="machinePlanId" name="machinePlanId" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">挪用者轴号：</td><td align="left" class="l-table-edit-td-input"><input id="moveAxisCode" name="moveAxisCode" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">挪用者轴名称：</td><td align="left" class="l-table-edit-td-input"><input id="moveAxisName" name="moveAxisName" type="text" ltype="text"/></td><td align="left"></td></tr>
		</table>
		<input class="l-button l-button-submit" type="submit" value="提交"/>
		<input class="l-button l-button-test" type="reset" value="重置"/>
	</form>
</body>
</html>

