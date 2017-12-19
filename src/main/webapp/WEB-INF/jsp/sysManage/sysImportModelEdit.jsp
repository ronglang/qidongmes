<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String sysImportModel_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>


<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>sysImportModelEdit</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<!-- <link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Silvery/css/style.css" rel="stylesheet" type="text/css" /> -->
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "sysImportModelManage"; 
	var row_id = "";
	row_id =<%=sysImportModel_id%>;
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
<script src="<%=basePath%>app/js/sysManage/sysImportModelEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->

</head>

<body style="padding:0px; margin:0px;">
	<form id="sysImportModelManage" method="post">
		<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin:20px auto;">
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td" style="width:100px;">模板名称：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input" style="width:180px;"><input
					id="modelname" name="modelname" type="text" ltype="text" required="required"/>
				</td>
				<td align="left" style="width:30px;"></td>
				<td align="right" class="l-table-edit-td" style="width:100px;">标题行数：</td>
				<td align="left" class="l-table-edit-td-input" style="width:180px;"><input
					id="ignorecols" name="ignorecols" type="text" ltype="text" />
				</td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">跳过列数：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="ignorerows" name="ignorerows" type="text" ltype="text" />
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">表名：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input"><input
					id="tablename" name="tablename" type="text" ltype="text" required="required"/>
				</td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">对应列名称：</td>
				<td align="left" class="l-table-edit-td-input" colspan="4"><textarea style="height: 120px;width:490px; margin-top: 10px;margin-bottom: 10px;" rows="4" id="colnames" name="colnames" class="l-textarea" ></textarea>
				</td>
				<td align="left"></td>
			</tr>
		</table>
		<div style="margin:0px auto;width:250px; clear:both; overflow:hidden; margin-top:90px;">
		    <input style="float: left; display:inline-block;" class="l-button l-button-submit" type="submit" value="提交" /> 
		    <input style="float: right; display:inline-block;" class="l-button l-button-test" type="reset" value="重置" />
		</div>
	</form>
</body>
</html>

