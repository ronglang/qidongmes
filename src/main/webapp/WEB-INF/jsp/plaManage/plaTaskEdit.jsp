<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String plaTask_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>plaTaskEdit</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "plaTaskManage"; 
	var row_id = "";
	row_id =<%=plaTask_id%>;
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
<script src="<%=basePath%>app/js/plaManage/plaTaskEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->

</head>

<body>
	<form id="plaTaskManage" method="post">
		<div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit" >
			<tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="id" name="id" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="createDate" name="createDate" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="createBy" name="createBy" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">人员编号：</td><td align="left" class="l-table-edit-td-input"><input id="personId" name="personId" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">工作单编号：</td><td align="left" class="l-table-edit-td-input"><input id="wsCode" name="wsCode" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">开始时间：</td><td align="left" class="l-table-edit-td-input"><input id="startTime" name="startTime" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">结束时间：</td><td align="left" class="l-table-edit-td-input"><input id="endTime" name="endTime" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">机台编号：</td><td align="left" class="l-table-edit-td-input"><input id="macId" name="macId" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="planDetailId" name="planDetailId" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">计划数量：</td><td align="left" class="l-table-edit-td-input"><input id="planCount" name="planCount" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">完成数量：</td><td align="left" class="l-table-edit-td-input"><input id="finishCount" name="finishCount" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">当前工序，冗余字段：</td><td align="left" class="l-table-edit-td-input"><input id="seqCurrent" name="seqCurrent" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">当前工艺：</td><td align="left" class="l-table-edit-td-input"><input id="craftCurrent" name="craftCurrent" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">当前日期：</td><td align="left" class="l-table-edit-td-input"><input id="dateCurrent" name="dateCurrent" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">是否开始执行，计划删除标志：</td><td align="left" class="l-table-edit-td-input"><input id="isInuse" name="isInuse" type="text" ltype="text"/></td><td align="left"></td></tr>
		</table>
		<input class="l-button l-button-submit" type="submit" value="提交"/>
		<input class="l-button l-button-test" type="reset" value="重置"/>
	</form>
</body>
</html>

