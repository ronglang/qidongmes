<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String mauForklift_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>mauForkliftEdit</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "storeForkliftManage"; 
	var row_id = "";
	row_id =<%=mauForklift_id%>;
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
<script src="<%=basePath%>app/js/storeManage/mauForkliftEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->

</head>

<body>
	<form id="storeForkliftManage" method="post">
		<div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit"
			style="width:100%">
			<tr class="l-table-edit-tr" style="height:30px;width:100%">
			</tr>
			<tr class="l-table-edit-tr" style="height:15px">
				<td align="right" class="l-table-edit-td" style="padding-left:50px;width:80px">叉车编号：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="fCode" name="fCode" type="text" ltype="text" /></td>
				<td align="right" class="l-table-edit-td" style="width:200px">叉车类型：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="fType" name="fType" type="text" ltype="text" /></td>
			</tr>
			<tr class="l-table-edit-tr" style="height:10px;width:100%">
			</tr>  
			<tr class="l-table-edit-tr" style="height:15px">
				<td align="right" class="l-table-edit-td" style="padding-left:50px;width:80px">叉车驾驶员：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="fDriver" name="fDriver" type="text" ltype="text" /></td>
				<td align="right" class="l-table-edit-td">创建时间：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="createDate" name="createDate" type="date" ltype="text" /></td>
			</tr>
		</table>  
		<input class="l-button l-button-submit" type="button" value="提交"  onclick="saveFormData()" style="margin-right:55px"/>
		<input class="l-button l-button-test" type="reset" value="重置" /> 
	</form>
</body>
</html>
 
