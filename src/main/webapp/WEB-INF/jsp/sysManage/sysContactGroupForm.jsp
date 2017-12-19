<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String sysContactGroup_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>


<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>sysContactGroupForm</title>
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>core/css/public.css" />
<script type="text/javascript"
	src="<%=basePath%>core/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=basePath%>core/js/cssBase.js"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script>
            var basePath  = '<%=basePath%>';
        </script>
</head>

<body style="padding:0px; margin:0px;">
	<form id="SysContactGroup">
		<table style="margin:30px auto;">
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td" style="width:100px;">分组名：</td>
				<td align="left" class="l-table-edit-td-input" style="width:180px;"><div
						class="l-text-readonly">
						<input id="name" type="text" ltype="text" readonly="readonly"></input>
					</div>
				</td>
				<td align="left" style="width:40px;"></td>
				<td align="right" class="l-table-edit-td" style="width:100px;">维护人：</td>
				<td align="left" class="l-table-edit-td-input" style="width:180px;"><div
						class="l-text-readonly">
						<input id="createBys" type="text" ltype="text" readonly="readonly"></input>
					</div>
				</td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">维护时间：</td>
				<td align="left" class="l-table-edit-td-input"><div
						class="l-text-readonly">
						<input id="createDate" type="text" ltype="text"
							readonly="readonly"></input>
					</div>
				</td>
				<td align="left"></td>
				<!-- <td align="right" class="l-table-edit-td">更新人：</td>
				<td align="left" class="l-table-edit-td-input"><div
						class="l-text-readonly">
						<input id="updateBys" type="text" ltype="text" readonly="readonly"></input>
					</div>
				</td>
				<td align="left"></td> -->
			</tr>
			<!--  <tr class="l-table-edit-tr">
                    <td align="right" class="l-table-edit-td">更新时间：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"> <input id="updateDate" type="text" ltype="text" readonly="readonly"></input></div></td>
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left"></td>
                    <td align="left"></td>
                </tr> -->
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">备注：</td>
				<td align="left" class="l-table-edit-td-input" colspan="4"><textarea
						style="height: 120px;margin-top:10px;width: 507px;" id="remark"
						name="remark" class="l-textarea" readonly="readonly"></textarea>
				</td>
				<td align="left"></td>
			</tr>
		</table>
	</form>
</body>

<script type="text/javascript">
    var routeName = "sysContactGroupManage"; 
    var row_id = "";
    row_id = <%=sysContactGroup_id%>;
    
    $(document).ready(function() {
        if (row_id != null) {
            var parm = "id="+row_id;
            pub_initForm(routeName,parm);
        }
    });
    </script>
</html>

