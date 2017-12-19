<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
  String sysCommdic_id = request.getParameter("id");
  String clas = request.getParameter("clas");
  String value = request.getParameter("value");
%>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML>
<html>
<head>


<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>sysDictionaryEdit</title>
<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css"
	rel="stylesheet" />
<link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" />
<!-- 默认引用1 -->
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/plugin/swfupload/css/default.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
  var basePath  = '<%=basePath%>';
  var routeName = "sysCommdicManage"; 
  var row_id = "";
  row_id =<%=sysCommdic_id%>;
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
<script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js"
	type="text/javascript"></script>
<script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js"
	type="text/javascript"></script>
<!-- 默认引用 -->
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script src="<%=basePath%>app/js/sysManage/sysCommdicEdit.js"
	type="text/javascript"></script>
<!-- 默认引用end -->

</head>

<body style="margin-left: -87px;">
	<form id="sysCommdicManage" method="post">
<%-- 		<input type="hidden" id="createBy" name="createBy"
			value="${createBy }" /> <input type="hidden" id="createDate"
			name="createDate" value="${createDate }" /> --%>
		<table cellpadding="0" cellspacing="0" class="l-table-edit"
			style="margin:20px auto;">
			<tr class="l-table-edit-tr">
			
				<td align="left" style="width:40px"></td>
				<td align="right" class="l-table-edit-td" style="width:80px">类别名称：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input" style="width:180px"><input
					id="clas" name="clas" type="text" ltype="text" value="${clas}" required="required"/>
				</td>
				
				<td align="right" class="l-table-edit-td" style="width:100px">值：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input" style="width:180px"><input
					id="value" name="value" type="text" ltype="text"  required="required" />
				</td>
				<td align="left"></td> 
			</tr>
<!-- 			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">类别名称：</td>
				<td align="left" class="l-table-edit-td-input"><input id="clas"
					name="pid" type="text" value="${clas}"
					style="height: 20px;background-color:rgb(218,218,218);"
					disabled="disabled" />
				</td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">备注：</td>
				<td align="left" class="l-table-edit-td-input" colspan="4"><textarea
						style="width:485px;height: 120px;" rows="4" id="remark"
						name="remark" class="l-textarea" >
       			 </textarea></td>
				<td align="left"></td>
			</tr> -->
		</table>
		<div>
		<input id="submit" name="submit" style="float: left;margin-right: 70px;margin-left: 272px;"
				class="l-button l-button-submit" type="submit" value="提交" /> 
	<!--  <input 	style="float: left; " class="l-button l-button-test" type="reset" value="重置" /> -->	
		</div>
	</form>
</body>
</html>

