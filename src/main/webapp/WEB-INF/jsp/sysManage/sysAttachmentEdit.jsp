<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String sysAttachment_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>


<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>sysAttachmentEdit</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Silvery/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "sysAttachmentManage"; 
	var row_id = "";
	row_id =<%=sysAttachment_id%>;
</script>
<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerRadioList.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
<!-- 默认引用1end -->

<!-- 默认引用 -->
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script src="<%=basePath%>app/js/sysManage/sysAttachmentEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->

</head>

<body>
	<form id="sysAttachmentManage" method="post">
		<div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit" >
			<tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="CLASSNAME" name="CLASSNAME" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="UPLOADURLORIGNAME" name="UPLOADURLORIGNAME" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="UPLOADURL" name="UPLOADURL" type="text" ltype="text"/></td><td align="left"></td><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="TYPE" name="TYPE" type="text" ltype="text"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" style="display:none"><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input"><input id="BID" name="BID" type="hidden" value="null"/></td><td align="left"></td></tr><tr class="l-table-edit-tr" ><td align="right" class="l-table-edit-td">：</td><td align="left" class="l-table-edit-td-input" colspan="4"><textarea cols="100" rows="4" id="REMARK" name="REMARK" class="l-textarea"></textarea></td><td align="left"></td></tr>
		</table>
		<input class="l-button l-button-submit" type="submit" value="提交"/>
		<input class="l-button l-button-test" type="reset" value="重置"/>
	</form>
</body>
</html>

