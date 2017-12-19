<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String storeReturn_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>storeReturnEdit</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "storeReturnManage"; 
	var row_id = "";
	row_id =<%=storeReturn_id%>;
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
<script src="<%=basePath%>app/js/storeManage/storeReturnEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->

</head>

<body>
	<form id="storeReturnManage" method="post">
		<div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit" >
			<tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="id" name="id" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">批次：</td><td align="left" class="l-table-edit-td-input"><input id="batchcode" name="batchcode" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">材料：</td><td align="left" class="l-table-edit-td-input"><input id=" material" name=" material" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="rfid" name="rfid" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">型号：</td><td align="left" class="l-table-edit-td-input"><input id="model" name="model" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">颜色：</td><td align="left" class="l-table-edit-td-input"><input id="colour" name="colour" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">数量：</td><td align="left" class="l-table-edit-td-input"><input id="count" name="count" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">位置：</td><td align="left" class="l-table-edit-td-input"><input id="poistion" name="poistion" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">叉车编号：</td><td align="left" class="l-table-edit-td-input"><input id="forkliftCode" name="forkliftCode" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">机台：</td><td align="left" class="l-table-edit-td-input"><input id="board" name="board" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">入库时间：</td><td align="left" class="l-table-edit-td-input"><input id="storeDate" name="storeDate" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">备注：</td><td align="left" class="l-table-edit-td-input"><input id="remark" name="remark" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="createDate" name="createDate" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="createBy" name="createBy" type="text" ltype="text"/></td><td align="left"></td></tr>
		</table>
		<input class="l-button l-button-submit" type="submit" value="提交"/>
		<input class="l-button l-button-test" type="reset" value="重置"/>
	</form>
</body>
</html>

