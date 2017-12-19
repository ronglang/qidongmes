<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
</head>
<body style="overflow-x:hidden; padding:2px;">
<form id="PlaCourse">
		<table>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="createDate" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="createBy" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">描述信息：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="remark" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">工作单类型：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="wsType" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="wsCode" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">合同编号：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="scCode" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">批次号：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="batCode" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">型号规格：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="headGgxh" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">颜色：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="color" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">扎装段长：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="headZzdc" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">扎装段数：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="headZzds" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">总数量：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="totalAmount" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">生产通知单ID：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="manuNoticeId" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">客户id：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="cusId" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="billDate" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">是否完成：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="isFinish" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">工艺id：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="craftId" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">审核标志：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="auditFlag" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">审核时间：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="auditTime" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">过程名称：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="courseName" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">启用标志，是否启用：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="useFlag" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">生产原则：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="manuTenet" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">过期标志，是否已过期：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="pastFlag" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">计划启用日期：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="planEnableDate" type="text" ltype="text" readonly="readonly"></input></td>
				<td align="left"></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>


