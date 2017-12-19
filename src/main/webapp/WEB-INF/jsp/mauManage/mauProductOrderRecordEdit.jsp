<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String mauProductOrderRecord_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>mauProductOrderRecordEdit</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "mauProductOrderRecordManage"; 
	var row_id = "";
	row_id =<%=mauProductOrderRecord_id%>;
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
<script src="<%=basePath%>app/js/storeManage/mauProductOrderRecordEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->

</head>

<body>
	<form id="mauProductOrderRecordManage" method="post">
		<div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit" >
			<tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="id" name="id" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">记录操作人：</td><td align="left" class="l-table-edit-td-input"><input id="createBy" name="createBy" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">记录操作日期：</td><td align="left" class="l-table-edit-td-input"><input id="createDate" name="createDate" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">供应商：</td><td align="left" class="l-table-edit-td-input"><input id="supplyAgent" name="supplyAgent" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">供货单号：</td><td align="left" class="l-table-edit-td-input"><input id="supplyCode" name="supplyCode" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">入库日期：</td><td align="left" class="l-table-edit-td-input"><input id="inDate" name="inDate" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">制单人：</td><td align="left" class="l-table-edit-td-input"><input id="singleMan" name="singleMan" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">质检报告编号：</td><td align="left" class="l-table-edit-td-input"><input id="qualityReportCode" name="qualityReportCode" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">仓库签收人：</td><td align="left" class="l-table-edit-td-input"><input id="depotSign" name="depotSign" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">物品名称：</td><td align="left" class="l-table-edit-td-input"><input id="objSort" name="objSort" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">物料规格：</td><td align="left" class="l-table-edit-td-input"><input id="objGgxh" name="objGgxh" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">位置：</td><td align="left" class="l-table-edit-td-input"><input id="address" name="address" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">RFID卡号：</td><td align="left" class="l-table-edit-td-input"><input id="rfidCode" name="rfidCode" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">批次号：</td><td align="left" class="l-table-edit-td-input"><input id="batchCode" name="batchCode" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">数量：</td><td align="left" class="l-table-edit-td-input"><input id="acount" name="acount" type="text" ltype="text"/></td><td align="left"></td></tr>
		</table>
		<input class="l-button l-button-submit" type="submit" value="提交"/>
		<input class="l-button l-button-test" type="reset" value="重置"/>
	</form>
</body>
</html>

