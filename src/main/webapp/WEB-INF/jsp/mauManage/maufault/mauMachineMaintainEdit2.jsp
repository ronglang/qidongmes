<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String mauMachineFault_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>mauMachineFaultEdit</title>

<!-- 默认引用1 -->
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/plugin/swfupload/css/default.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	
	var routeName = "mauMachineFaultManage";
	var row_id = "";
	row_id =<%=mauMachineFault_id%>
	;
</script>
<script src="<%=basePath%>core/js/jquery-1.4.4.min.js"
	type="text/javascript"></script>

<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<!-- 默认引用1end -->

<!-- 默认引用 -->
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script src="<%=basePath%>app/js/mauManage/mauMachineFaultEdit.js"
	type="text/javascript"></script>
<!-- 默认引用end -->
<style type="text/css">

	table{
	 margin-left:60px;
	 margin-top: 60px;
	
	}
	#mydiv{
	 width:100%;
	 height: 30px;
	}
</style>
<script type="text/javascript">

function submitMauMachineMaintain() {
	var parm = "";
	
		//	加入查询参数，进行update参数
	var id=$("#hideId").val();
	var remark=$("#remark").val();
	var status=$("#status option:selected").val();
	parm = "id=" + id+"&remark="+remark+"&status="+status;
	var url="rest/mauMachineFaultManageAction/updateMachineMaintain?"+parm;
	$.post(url,{},function(json){
		debugger;
		var url=basePath + "rest/" + routeName + "Action/toMachineMaintainTable";
		var data=json.data;
		$.ligerDialog.success(data);
		parent.$.ligerDialog.close(); //关闭弹出窗
		parent.window.location.href=url;
		//parent.window.getDataGrid(url);//调父窗口某个方法
		//window.location.reload(true);
		parent.$(".l-dialog,.l-window-mask").remove(); //关闭弹出层
		//parent.window.getDataGrid(url);//调父窗口某个方法
	},"json");
}


</script>
</head>

<body>
	<form id="mauMachineFaultManage" method="post">
	<input type="hidden" name="id" id="hideId"  value="${id}"/>
		<div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit">
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">维护描述:</td>
				<td align="left" class="l-table-edit-td-input"><input id="remark"  name="remark" type="text" ltype="text" /></td>
				
			</tr>
			<tr class="l-table-edit-tr">
			<td align="right" class="l-table-edit-td">进行状态:</td>
				<td align="left" class="l-table-edit-td-input">
				<select  id="status">
					<option >--请选择--</option>
					<option value="1">进行中</option>
					<option value="2">已完成</option>
				</select>
				</td>	
			</tr>
		</table>
		<input class="l-button l-button-submit"  onclick="submitMauMachineMaintain()" type="button" value="提交" style="margin-right:90px;margin-top: 40px;" /> <input
			class="l-button l-button-test" type="reset" value="重置"  style="margin-top: 40px;"/>
	</form>
</body>
</html>

