<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String storeShelfDetail_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">

		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>storeShelfDetailForm</title>
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="<%=basePath%>core/css/public.css" />
		<script type="text/javascript" src="<%=basePath%>core/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="<%=basePath%>core/js/cssBase.js"></script>
		<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

		<script>
			var basePath  = '<%=basePath%>';
    	</script>
	</head>

	<body>
		<form id="StoreShelfDetail">
			<table>
				<tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">货架表id：</td><td align="left" class="l-table-edit-td-input"><input id="shelfId" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">原料颜色：</td><td align="left" class="l-table-edit-td-input"><input id="color" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">原料id：</td><td align="left" class="l-table-edit-td-input"><input id="materialId" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr><tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">原料的包装规格：</td><td align="left" class="l-table-edit-td-input"><input id="packageType" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">原料的rfid：</td><td align="left" class="l-table-edit-td-input"><input id="materialRfid" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">原料的重量：</td><td align="left" class="l-table-edit-td-input"><input id="weight" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr><tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">创建人：</td><td align="left" class="l-table-edit-td-input"><input id="createBy" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">创建时间：</td><td align="left" class="l-table-edit-td-input"><input id="createDate" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">胶料规格型号：</td><td align="left" class="l-table-edit-td-input"><input id="materialsType" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr>
			</table>
		</form>
	</body>

	<script type="text/javascript">
	var routeName = "storeShelfDetailManage"; 
	var row_id = "";
	row_id = <%=storeShelfDetail_id%>;
	
	$(document).ready(function() {
		if (row_id != null) {
			$("#StoreShelfDetail").ligerForm();
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
		}
	});
  	</script>
</html>

