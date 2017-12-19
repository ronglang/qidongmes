<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String storeCoilSaDetail_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>storeCoilSaDetailEdit</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "storeCoilSaDetailManage"; 
	var row_id = "";
	row_id =<%=storeCoilSaDetail_id%>;
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
<script src="<%=basePath%>app/js/storeManage/storeCoilSaDetailEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->

</head>

<body>
	<form id="storeCoilSaDetailManage" method="post">
		<div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit" >
			<tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="id" name="id" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">线盘存放区域表ID（store_coil_storage_area
）：</td><td align="left" class="l-table-edit-td-input"><input id="scsaId" name="scsaId" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">线盘RFID：</td><td align="left" class="l-table-edit-td-input"><input id="coilRfid" name="coilRfid" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">制品类型（原料、半制品、成品、异常品、数据字典）：</td><td align="left" class="l-table-edit-td-input"><input id="productType" name="productType" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">线盘规格型号（盘的大小）：</td><td align="left" class="l-table-edit-td-input"><input id="coilSpecifications" name="coilSpecifications" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">线规格型号（原料、半成品、成品的规格型号）：</td><td align="left" class="l-table-edit-td-input"><input id="cable
Specifications" name="cable
Specifications" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">线盘类型（铜盘、木盘、铁盘）：</td><td align="left" class="l-table-edit-td-input"><input id="coilStyle" name="coilStyle" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">创建人：</td><td align="left" class="l-table-edit-td-input"><input id="createBy" name="createBy" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">创建时间：</td><td align="left" class="l-table-edit-td-input"><input id="createDate" name="createDate" type="text" ltype="text"/></td><td align="left"></td></tr>
		</table>
		<input class="l-button l-button-submit" type="submit" value="提交"/>
		<input class="l-button l-button-test" type="reset" value="重置"/>
	</form>
</body>
</html>

