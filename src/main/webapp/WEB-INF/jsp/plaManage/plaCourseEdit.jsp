<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String plaCourse_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>plaCourseEdit</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "plaCourseManage"; 
	var row_id = "";
	row_id =<%=plaCourse_id%>;
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
<script src="<%=basePath%>app/js/plaManage/plaCourseEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->
<script type="text/javascript">
	var id = "${id}";
</script>
</head>

<body>
	<form id="plaCourseManage" method="post">
<!-- 		<div></div> -->
<!-- 		<table cellpadding="0" cellspacing="0" class="l-table-edit" > -->
<!-- 			<tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="id" name="id" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="createDate" name="createDate" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="createBy" name="createBy" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">描述信息：</td><td align="left" class="l-table-edit-td-input"><input id="remark" name="remark" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">工作单类型：</td><td align="left" class="l-table-edit-td-input"><input id="wsType" name="wsType" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="wsCode" name="wsCode" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">合同编号：</td><td align="left" class="l-table-edit-td-input"><input id="scCode" name="scCode" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">批次号：</td><td align="left" class="l-table-edit-td-input"><input id="batCode" name="batCode" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">型号规格：</td><td align="left" class="l-table-edit-td-input"><input id="headGgxh" name="headGgxh" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">颜色：</td><td align="left" class="l-table-edit-td-input"><input id="color" name="color" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">扎装段长：</td><td align="left" class="l-table-edit-td-input"><input id="headZzdc" name="headZzdc" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">扎装段数：</td><td align="left" class="l-table-edit-td-input"><input id="headZzds" name="headZzds" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">总数量：</td><td align="left" class="l-table-edit-td-input"><input id="totalAmount" name="totalAmount" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">生产通知单ID：</td><td align="left" class="l-table-edit-td-input"><input id="manuNoticeId" name="manuNoticeId" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">客户id：</td><td align="left" class="l-table-edit-td-input"><input id="cusId" name="cusId" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="billDate" name="billDate" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">是否完成：</td><td align="left" class="l-table-edit-td-input"><input id="isFinish" name="isFinish" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">工艺id：</td><td align="left" class="l-table-edit-td-input"><input id="craftId" name="craftId" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">审核标志：</td><td align="left" class="l-table-edit-td-input"><input id="auditFlag" name="auditFlag" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">审核时间：</td><td align="left" class="l-table-edit-td-input"><input id="auditTime" name="auditTime" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">过程名称：</td><td align="left" class="l-table-edit-td-input"><input id="courseName" name="courseName" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">启用标志，是否启用：</td><td align="left" class="l-table-edit-td-input"><input id="useFlag" name="useFlag" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">生产原则：</td><td align="left" class="l-table-edit-td-input"><input id="manuTenet" name="manuTenet" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">过期标志，是否已过期：</td><td align="left" class="l-table-edit-td-input"><input id="pastFlag" name="pastFlag" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">计划启用日期：</td><td align="left" class="l-table-edit-td-input"><input id="planEnableDate" name="planEnableDate" type="text" ltype="text"/></td><td align="left"></td></tr> -->
<!-- 		</table> -->
<!-- 		<input class="l-button l-button-submit" type="submit" value="提交"/> -->
<!-- 		<input class="l-button l-button-test" type="reset" value="重置"/> -->
	</form>
</body>
</html>

