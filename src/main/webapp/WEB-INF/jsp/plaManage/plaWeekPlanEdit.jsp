<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String plaWeekPlan_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>plaWeekPlanEdit</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "plaWeekPlanManage"; 
	var row_id = "";
	row_id =<%=plaWeekPlan_id%>;
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
<script src="<%=basePath%>app/js/plaManage/plaWeekPlanEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->

</head>

<body>
	<form id="plaWeekPlanManage" method="post">
		<div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit" >
			<tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="id" name="id" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">开始工作日：</td><td align="left" class="l-table-edit-td-input"><input id="workStartDate" name="workStartDate" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">结束工作日：</td><td align="left" class="l-table-edit-td-input"><input id="workEndDate" name="workEndDate" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">生产令id：</td><td align="left" class="l-table-edit-td-input"><input id="productOrderId" name="productOrderId" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">物料规格：</td><td align="left" class="l-table-edit-td-input"><input id="materType" name="materType" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">轴数：</td><td align="left" class="l-table-edit-td-input"><input id="axisAmount" name="axisAmount" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">段长：</td><td align="left" class="l-table-edit-td-input"><input id="partLen" name="partLen" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">拉丝总工时：</td><td align="left" class="l-table-edit-td-input"><input id="drawbenchTime" name="drawbenchTime" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">绞线总工时：</td><td align="left" class="l-table-edit-td-input"><input id="strandTime" name="strandTime" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">绝缘总工时：</td><td align="left" class="l-table-edit-td-input"><input id="insulationTime" name="insulationTime" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">铠装总工时：</td><td align="left" class="l-table-edit-td-input"><input id="armoredTime" name="armoredTime" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">成缆总工时：</td><td align="left" class="l-table-edit-td-input"><input id="cableTime" name="cableTime" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">护套总工时：</td><td align="left" class="l-table-edit-td-input"><input id="sheathTime" name="sheathTime" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">状态：</td><td align="left" class="l-table-edit-td-input"><input id="state" name="state" type="text" ltype="text"/></td><td align="left"></td></tr>
		</table>
		<input class="l-button l-button-submit" type="submit" value="提交"/>
		<input class="l-button l-button-test" type="reset" value="重置"/>
	</form>
</body>
</html>

