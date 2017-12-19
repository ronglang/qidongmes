<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String sysDictionary_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
	<head>
		

		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>sysDictionaryForm</title>
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="<%=basePath%>core/css/public.css" />

		<script type="text/javascript" src="<%=basePath%>core/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="<%=basePath%>core/js/cssBase.js"></script>
		<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

		<script>
			var basePath  = '<%=basePath%>';
    	</script>
	</head>

	<body >
		<form id="SysDictionary">
			<table >
			<tr class="l-table-edit-tr">
				    <td align="right" class="l-table-edit-td">父ID：</td>
			        <td align="left" class="l-table-edit-td-input">
			        	<div class="l-text-readonly"><input id="pid" name="pid" readonly="readonly"></div></input></td>
			        <td align="left"></td>
			        <td align="right" class="l-table-edit-td">是否系统字典：</td>
				    <td align="left" class="l-table-edit-td-input">
				    	<div class="l-text-readonly"><input id="isSystem" name="isSystem" readonly="readonly"></div></input></td>
				    <td align="left"></td>
				</tr>
				<!-- <tr class="l-table-edit-tr">
				    <td align="right" class="l-table-edit-td" style="width:100px;">编码：</td>
				    <td align="left" class="l-table-edit-td-input" style="width:180px;">
				    	<div class="l-text-readonly" ><input id="code" name="code" readonly="readonly"></div></input></td>
				    <td align="left" style="width:50px;"></td>
				    <td align="right" class="l-table-edit-td" style="width:80px;">编码值：</td>
				    <td align="left" class="l-table-edit-td-input" style="width:180px;">
				    	<div class="l-text-readonly"><input id="value" name="value" readonly="readonly"></div></input></td>
				    <td align="left"></td>
				</tr> 
				<tr class="l-table-edit-tr">
				    <td align="right" class="l-table-edit-td">父ID：</td>
			        <td align="left" class="l-table-edit-td-input">
			        	<div class="l-text-readonly"><input id="pid" name="pid" readonly="readonly"></div></input></td>
			        <td align="left"></td>
			        <td align="right" class="l-table-edit-td">是否系统字典：</td>
				    <td align="left" class="l-table-edit-td-input">
				    	<div class="l-text-readonly"><input id="isSystem" name="isSystem" readonly="readonly"></div></input></td>
				    <td align="left"></td>
				</tr>
			    <tr class="l-table-edit-tr">
				    <td align="right" class="l-table-edit-td">创建人：</td>
				    <td align="left" class="l-table-edit-td-input">
				    	<div class="l-text-readonly"><input id="createBy" name="createBy" readonly="readonly"></div></input></td>
				    <td align="left"></td>
				     <td align="right" class="l-table-edit-td">创建时间：</td>
				    <td align="left" class="l-table-edit-td-input">
				    	<div class="l-text-readonly"><input id="createDate" name="createDate" readonly="readonly"></div></input></td>
				    <td align="left"></td>
				</tr>
				<tr class="l-table-edit-tr">
				    <td align="right" class="l-table-edit-td">更新人：</td>
				    <td align="left" class="l-table-edit-td-input">
				    	<div class="l-text-readonly"><input id="updateBy" name="updateBy" readonly="readonly"></div></input></td>
				    <td align="left"></td>
				     <td align="right" class="l-table-edit-td">更新时间：</td>
				    <td align="left" class="l-table-edit-td-input">
				    	<div class="l-text-readonly"><input id="updateDate" name="updateDate" readonly="readonly"></div></input></td>
				    <td align="left"></td>
				</tr>
				<tr class="l-table-edit-tr">
			        <td align="right" class="l-table-edit-td">备注：</td>
			        <td align="left" class="l-table-edit-td-input" colspan="4" >
			        <textarea style="width:498px;height: 120px;margin-top: 10px;" rows="4" id="remark"
			        	name="remark" class="l-textarea" readonly="readonly">
			        </textarea></td>
			        <td align="left"></td>
			        -->
			     </tr>
			</table>
		</form>
	</body>

	<script type="text/javascript">
	var routeName = "sysDictionaryManage"; 
	var row_id = "";
	row_id = <%=sysDictionary_id%>;
	
	$(document).ready(function() {
		if (row_id != null) {
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
		}
	});
  	</script>
</html>

