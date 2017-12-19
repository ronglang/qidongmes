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
		margin-left: 50px;
		margin-top: 60px;
	
	}
	select{
	width:180px;
	}
</style>
<script type="text/javascript">


$(function(){
	//获取全部机台编码
	var url=basePath + "rest/" + routeName + "Action/getMachineCode";
	$.post(url,{},function(data){
		var json= data.macCode;
		var  msg="<select> <option>--请选择--</option>";
		for (var int = 0; int < json.length; int++) {
			msg+="<option  value="+json[int]+">"+json[int]+"</option>";
		}
		msg+="</select>";
		$("#macCode").append(msg);
	},"json");
	
	
});

function submitMaintain(){
	var parm = "";
	
	//	加入查询参数，进行update参数
		var id=$("#hideId").val();
		var maintainBy=$("#maintainBy").val();
		var text=$("#macCode option:selected").val();
		if(maintainBy==null||maintainBy==""){
			$.ligerDialog.warn('请安排维护保养人员！');
			return;
		}
		
		if(macCode==null||macCode==""){
			$.ligerDialog.warn('请分配需要保养的机台！');
			return;
		}
		parm = "id=" + id+"&maintainBy="+maintainBy+"&macCode="+text;
		var url="rest/mauMachineFaultManageAction/saveMaintainMessageFromMachine?"+parm;
		$.post(url,{},function(json){
			var url=basePath + "rest/" + routeName + "Action/toMachineMaintainTable";
			//var data=json.data;
			$.ligerDialog.success('保存成功');
			parent.$.ligerDialog.close(); //关闭弹出窗
			//window.location.reload(true);
			//parent.window.getDataGrid(url);//调父窗口某个方法
			parent.window.location.href=url;
			parent.$(".l-dialog,.l-window-mask").remove(); //关闭弹出层 
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
			<td align="right" class="l-table-edit-td" >维护机台编码:</td>
				<td align="left" class="l-table-edit-td-input"><!-- <input id="macCode"
					name="macCode" type="text" ltype="text" /></td> -->
					<diV id="macCode" style="width:180px;"></diV>
					</tr>
					
			<tr class="l-table-edit-tr">
			
				<td align="right" class="l-table-edit-td">维护保养人员:</td>
				<td align="left" class="l-table-edit-td-input"><input id="maintainBy"
					name="maintainBy" type="text" ltype="text" /></td>
			</tr>
			
		</table>
		<input class="l-button l-button-submit" onclick="submitMaintain()" type="button" value="提交" style="margin-right: 30px;" /> <input
			class="l-button l-button-test" type="reset" value="重置" />
	</form>
</body>
</html>

