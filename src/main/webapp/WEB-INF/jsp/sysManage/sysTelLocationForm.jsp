<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String sysTelLocation_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
	<head>
		

		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>sysTelLocationForm</title>
		<link rel="stylesheet" href="<%=basePath%>core/css/public.css" />
		<script type="text/javascript" src="<%=basePath%>core/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="<%=basePath%>core/js/cssBase.js"></script>

		<script>
			var basePath  = '<%=basePath%>';
    	</script>
	</head>

	<body>
		<form id="SysTelLocation">
			<div></div>
			<a href="javascript:history.go(-1)" class="return">返&nbsp;回</a>
			<table>
				<tr><td>：</td><td><label id="ID" ></label></td></tr><tr><td>编码：</td><td><label id="CODE" ></label></td></tr><tr><td>姓名：</td><td><label id="NAME" ></label></td></tr><tr><td>性别：</td><td><label id="GENDER" ></label></td></tr><tr><td>民族：</td><td><label id="NATION" ></label></td></tr><tr><td>身份证号：</td><td><label id="ID_NO" ></label></td></tr><tr><td>电话：</td><td><label id="TEL" ></label></td></tr><tr><td>地址：</td><td><label id="ADDRESS" ></label></td></tr><tr><td>经度：</td><td><label id="LONGITUDE" ></label></td></tr><tr><td>纬度：</td><td><label id="LATITUDE" ></label></td></tr><tr><td>创建人ID：</td><td><label id="CREATE_BY" ></label></td></tr><tr><td>记录时间：</td><td><label id="CREATE_DATE" ></label></td></tr><tr><td>更新人：</td><td><label id="UPDATE_BY" ></label></td></tr><tr><td>更新时间：</td><td><label id="UPDATE_DATE" ></label></td></tr>
			</table>
		</form>
	</body>

	<script type="text/javascript">
	var routeName = "sysTelLocationManage"; 
	var row_id = "";
	row_id = <%=sysTelLocation_id%>;
	
	$(document).ready(function() {
		if (row_id != null) {
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
		}
	});
  	</script>
</html>

