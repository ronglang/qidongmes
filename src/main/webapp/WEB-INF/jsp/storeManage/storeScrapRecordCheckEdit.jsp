<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String storeScrapRecord_id = request.getParameter("id");
	//storeScrapRecord_id = storeScrapRecord_id == null ? "":storeScrapRecord_id;
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>storeScrapRecordEdit</title>

<!-- 默认引用1 -->
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/plugin/swfupload/css/default.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "storeScrapRecordManage";
	var row_id = <%=storeScrapRecord_id%>;
</script>
<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
 
<!-- 默认引用1end -->
<script	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"	type="text/javascript"></script>
<script	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js"	type="text/javascript"></script>
<script	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js"	type="text/javascript"></script>

<script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js"	type="text/javascript"></script>
<script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js"	type="text/javascript"></script>
<!-- 默认引用 -->
<!-- 默认引用end -->
<script type="text/javascript">
	$(function(){
		
	});
	function checkTell(){

		$.ajax({
			url : basePath+ "rest/storeScrapRecordManageAction/tellCheckSave",
			type : "post",
			//dataType : "json",
			data:$("#storeScrapRecordManage").serialize(),
			success : function(data) {
				alert(data);
			},
			error : function(data) {
				alert(data);
			}
		});
		
	}
	
	function reset(){
		 $("#batchCode").val("");
		 $("#rfidCode").val("");
		 $("#model").val("");
		 $("#amount").val("");
		 $("#unit").val("");
		 $("#materialRalation").val("");
		 $("#remark").val("");
	}
</script>
</head>

<body>
	<form id="storeScrapRecordManage" method="post"  >
		<div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit">
		
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">批次号：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="batchCode" name="batchCode" type="text" ltype="text" validate="{required:true,maxlength:32}" /></td>
				<td align="right" class="l-table-edit-td">RFID编号：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="rfidCode" name="rfidCode" type="text" ltype="text" validate="{required:true,maxlength:32}" /></td>
					<td align="right" class="l-table-edit-td">型号：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="model" name="model" type="text" ltype="text"  validate="{required:true,maxlength:32}"/></td>
			</tr>
			<tr class="l-table-edit-tr">
				
		
				<td align="right" class="l-table-edit-td">数量：</td>
				<td align="left" class="l-table-edit-td-input"><input id="amount"
					name="amount" type="text" ltype="text" validate="{required:true,maxlength:32,digits:true}" /></td>
				<td align="right" class="l-table-edit-td">单位：</td>
				<td align="left" class="l-table-edit-td-input"><input id="unit"
					name="unit" type="text" ltype="text" validate="{required:true,maxlength:32}" /></td>
					<td align="right" class="l-table-edit-td">物料位置：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="materialRalation" name="materialRalation" type="text" ltype="text"  validate="{required:true,maxlength:32}"/></td>	
			</tr>
			
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">备注：</td>
				<td align="left" class="l-table-edit-td-input">
					<textarea id="remark" name="remark" style="width: 200px; height: 100px; margin-top:5px;" >
					</textarea>
					</td>	
			</tr>
			
		</table>
			<input style="width:80px;" type="button" onclick="checkTell()" value="确定" /> 
			<input style="width:80px;"  type="button" onclick="reset()" value="重置" />
	</form >
	
</body>
</html>

