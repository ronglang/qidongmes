<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String mauElectricityPrice_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>mauElectricityPriceEdit</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "mauElectricityPriceManage"; 
	var row_id = "";
	row_id =<%=mauElectricityPrice_id%>;
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
<script src="<%=basePath%>app/js/mauManage/mauElectricityPriceEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->

</head>

<body>
	<form id="mauElectricityPriceManage" method="post">
		<div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit" >
			<tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="id" name="id" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="mepCode" name="mepCode" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">当前季度的每一天的电价开始时间(em:9:00-12:00)这里的start_date=9:00：</td><td align="left" class="l-table-edit-td-input"><input id="startDate" name="startDate" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">当前季度的每一天的电价开始时间(em:9:00-12:00)这里的start_date=12:00：</td><td align="left" class="l-table-edit-td-input"><input id="endDate" name="endDate" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="price" name="price" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="createBy" name="createBy" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="createDate" name="createDate" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">可能会出现电价在每个季度不一样，需要有类型区分：</td><td align="left" class="l-table-edit-td-input"><input id="type" name="type" type="text" ltype="text"/></td><td align="left"></td></tr>
		</table>
		<input class="l-button l-button-submit" type="submit" value="提交"/>
		<input class="l-button l-button-test" type="reset" value="重置"/>
	</form>
</body>
</html>

