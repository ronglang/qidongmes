<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String mauRfidCard_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>mauRfidCardEdit</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "mauRfidCardManage"; 
	var row_id = "";
	row_id =<%=mauRfidCard_id%>;
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
<script src="<%=basePath%>app/js/mauManage/mauRfidCardEdit.js" type="text/javascript"></script>
<style type="text/css">

.l-button l-button-submit{
	margin-top:100px;

}
</style>
<!-- 默认引用end -->
</head>

<body>
	<form id="mauRfidCardManage" method="post">
	 <div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit"
			style="width:100%;height:70px">
					<tr class="l-table-edit-tr" style="height:30px;width:100%">
			</tr>
			<tr class="l-table-edit-tr" style="height:15px">
				<td align="right" class="l-table-edit-td" style="padding-left:50px;width:80px">请录入Rfid卡数：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="rfidCardNumber" name="rfidCardNumber" type="text" ltype="text" /></td>
					<tr class="l-table-edit-tr" style="height:30px;width:100%">
			</tr>
			<tr class="l-table-edit-tr" style="height:15px">
						<td align="right" class="l-table-edit-td" style="padding-left:50px;width:80px">请选择卡类型：</td>
				<td align="left" class="l-table-edit-td-input"><!-- <input
					id="materialType" name="materialType" type="text" ltype="text" /> -->
					
					<select id="materialType">
					<c:forEach items="${list}" var="vo">
					 <option value="${vo.value}">${vo.value}</option>
					</c:forEach>
					
					</select>
					</td>
			</tr> 
					<tr class="l-table-edit-tr" style="height:30px;width:100%">
			</tr>
	
			<!-- <tr class="l-table-edit-tr" style="height:10px;width:100%">
			</tr>  
			<tr class="l-table-edit-tr" style="height:15px">
				<td align="right" class="l-table-edit-td" style="padding-left:50px;width:80px">叉车驾驶员：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="fDriver" name="fDriver" type="text" ltype="text" /></td>
				<td align="right" class="l-table-edit-td">创建时间：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="createDate" name="createDate" type="text" ltype="text" /></td>
			</tr>  -->
		</table>  
		<input  class="l-button l-button-submit" type="button" value="提交"  onclick="saveFormData()" style="margin-right:55px"/>
		<input  class="l-button l-button-test" type="reset" value="重置" /> 
	</form>
</body>
</html>
 
