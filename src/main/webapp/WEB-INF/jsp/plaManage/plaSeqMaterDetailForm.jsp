<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String plaSeqMaterDetail_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">

		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>plaSeqMaterDetailForm</title>
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
		<form id="PlaSeqMaterDetail">
			<table>
				<tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">生产令：</td><td align="left" class="l-table-edit-td-input"><input id="productOrderId" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">周计划id：</td><td align="left" class="l-table-edit-td-input"><input id="weekPlanId" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">工作日：</td><td align="left" class="l-table-edit-td-input"><input id="workDate" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr><tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">工序编码：</td><td align="left" class="l-table-edit-td-input"><input id="seqCode" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">工序工艺编码：</td><td align="left" class="l-table-edit-td-input"><input id="cCode" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">来料轴号：</td><td align="left" class="l-table-edit-td-input"><input id="incomingAxis" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr><tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">来料段长：</td><td align="left" class="l-table-edit-td-input"><input id="incomingLong" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">来料规格型号：</td><td align="left" class="l-table-edit-td-input"><input id="incomingType" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">配送到位时间：</td><td align="left" class="l-table-edit-td-input"><input id="sendDate" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr><tr class="l-table-edit-tr"><td align="right" class="l-table-edit-td">上工序编码：</td><td align="left" class="l-table-edit-td-input"><input id="upSeqCode" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td><td align="right" class="l-table-edit-td">上工序机台编码：</td><td align="left" class="l-table-edit-td-input"><input id="upSeqMachineCode" type="text" ltype="text" readonly="readonly"></input></td><td align="left"></td></tr>
			</table>
		</form>
	</body>

	<script type="text/javascript">
	var routeName = "plaSeqMaterDetailManage"; 
	var row_id = "";
	row_id = <%=plaSeqMaterDetail_id%>;
	
	$(document).ready(function() {
		if (row_id != null) {
			$("#PlaSeqMaterDetail").ligerForm();
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
		}
	});
  	</script>
</html>

