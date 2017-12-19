<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String mauHandlingChores_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>mauHandlingChoresEdit</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "mauMachineSpeedManage"; 
	var row_id = "";
	row_id =<%=mauHandlingChores_id%>;
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
<script src="<%=basePath%>app/js/mauManage/mauMachineSpeedAddForm.js" type="text/javascript"></script>
<!-- 默认引用end -->
<style type="text/css">
	table{
		
	}
	tr{
		height:50px;
	}
	table tr td span{
		font-size: 18px;
		color:black;
		font-weight: bolder;
	}
	table tr td input{
		height:25px;
		width:160px;
	}

</style>
</head>

<body>
	<form id="mauHandlingChoresManage" method="post">
		<div></div>
		<table border="0" cellpadding="0" cellspacing="0" class="l-table-edit"
			style="width:90%;margin:0 auto;">
			<tr class="l-table-edit-tr" style="height:30px;width:100%">
			</tr>
			<tr style="text-align: center;">
				<td colspan="3">
					<span>机台ID：</span>
					<input id="" name="machineId" type="text" ltype="text" />
				</td>
			</tr>
			<tr>
				<td>
					<span>规格型号：</span><input type="text" name="proGgxh">
				</td>
				<td>
					<span>标准生产速度：</span><input type="text" name="speed">
				</td>
				<td>
					<span>单位：</span><input type="text" name="unit">
				</td>
			</tr>
			<tr>
				<td>
					<span>准备时间：</span><input type="text" name="readyTime">
				</td>
				<td>
					<span>放线上盘时间：</span><input type="text" name="upTime">
				</td>
				<td>
					<span>收线上盘时间：</span><input type="text" name="downTime">
				</td>
			</tr>
		</table>
		<input class="l-button l-button-submit" type="button" value="提交"  onclick="saveFormData()" style="margin-right:55px"/>
		<input class="l-button l-button-test" type="reset" value="重置" />
	</form> 
</body>
</html>

