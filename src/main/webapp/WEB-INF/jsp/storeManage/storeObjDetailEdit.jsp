<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String storeObjDetail_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>storeObjDetailEdit</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "storeObjDetailManage"; 
	var row_id = "";
	row_id =<%=storeObjDetail_id%>;
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
<script src="<%=basePath%>app/js/storeManage/storeObjDetailEdit.js"
	type="text/javascript"></script>
<!-- 默认引用end -->

</head>

<body>
	<form id="storeObjDetailManage" method="post">
		<div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit" >
			<tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="id" name="id" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="createBy" name="createBy" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="createDate" name="createDate" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">物品名称：</td><td align="left" class="l-table-edit-td-input"><input id="objName" name="objName" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">物品规格型号：</td><td align="left" class="l-table-edit-td-input"><input id="objGgxh" name="objGgxh" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">物品数量：</td><td align="left" class="l-table-edit-td-input"><input id="objCount" name="objCount" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">物品颜色：</td><td align="left" class="l-table-edit-td-input"><input id="objColor" name="objColor" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">计量单位：</td><td align="left" class="l-table-edit-td-input"><input id="unit" name="unit" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">订单号：</td><td align="left" class="l-table-edit-td-input"><input id="orderCode" name="orderCode" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">领料单号：</td><td align="left" class="l-table-edit-td-input"><input id="pickCode" name="pickCode" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">包装类型：</td><td align="left" class="l-table-edit-td-input"><input id="packingType" name="packingType" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">包装数量：</td><td align="left" class="l-table-edit-td-input"><input id="packingAmount" name="packingAmount" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">批次号：</td><td align="left" class="l-table-edit-td-input"><input id="batchNum" name="batchNum" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">安全库存：</td><td align="left" class="l-table-edit-td-input"><input id="safeStock" name="safeStock" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">最低库存：</td><td align="left" class="l-table-edit-td-input"><input id="minStock" name="minStock" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">实际库存：</td><td align="left" class="l-table-edit-td-input"><input id="actStock" name="actStock" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">采购在途：</td><td align="left" class="l-table-edit-td-input"><input id="inWayStock" name="inWayStock" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">需求交期：</td><td align="left" class="l-table-edit-td-input"><input id="reqRecDate" name="reqRecDate" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">实际交期：</td><td align="left" class="l-table-edit-td-input"><input id="deliveDate" name="deliveDate" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="remark" name="remark" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">需订购数量：</td><td align="left" class="l-table-edit-td-input"><input id="reqPurchAmount" name="reqPurchAmount" type="text" ltype="text"/></td><td align="left"></td></tr>
		</table>
		<input class="l-button l-button-submit" type="submit" value="提交"/>
		<input class="l-button l-button-test" type="reset" value="重置"/>
	</form>
</body>
</html>

