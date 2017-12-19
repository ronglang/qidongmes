<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String sysResource_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
	<head>
		

		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>sysResourceForm</title>
		<link rel="stylesheet" href="<%=basePath%>core/css/public.css" />
		<script type="text/javascript" src="<%=basePath%>core/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="<%=basePath%>core/js/cssBase.js"></script>

		<script>
			var basePath  = '<%=basePath%>';
    	</script>
	</head>

	<body>
		<form id="SysResource">
			<div></div>
			<a href="javascript:history.go(-1)" class="return">返&nbsp;回</a>
			<table>
				<tr><td>：</td><td><label id="id" ></label></td></tr><tr><td>：</td><td><label id="click" ></label></td></tr><tr><td>：</td><td><label id="desktop_url" ></label></td></tr><tr><td>：</td><td><label id="display" ></label></td></tr><tr><td>：</td><td><label id="icon_css" ></label></td></tr><tr><td>：</td><td><label id="image" ></label></td></tr><tr><td>：</td><td><label id="image128" ></label></td></tr><tr><td>：</td><td><label id="image256" ></label></td></tr><tr><td>：</td><td><label id="image32" ></label></td></tr><tr><td>：</td><td><label id="image48" ></label></td></tr><tr><td>：</td><td><label id="image64" ></label></td></tr><tr><td>：</td><td><label id="isref" ></label></td></tr><tr><td>：</td><td><label id="istext" ></label></td></tr><tr><td>：</td><td><label id="name" ></label></td></tr><tr><td>：</td><td><label id="pid" ></label></td></tr><tr><td>：</td><td><label id="r_code" ></label></td></tr><tr><td>：</td><td><label id="refid" ></label></td></tr><tr><td>：</td><td><label id="remark" ></label></td></tr><tr><td>：</td><td><label id="sort" ></label></td></tr><tr><td>：</td><td><label id="type" ></label></td></tr><tr><td>：</td><td><label id="url" ></label></td></tr><tr><td>：</td><td><label id="width" ></label></td></tr><tr><td>：</td><td><label id="win_wh" ></label></td></tr>
			</table>
		</form>
	</body>

	<script type="text/javascript">
	var routeName = "sysResourceManage"; 
	var row_id = "";
	row_id = <%=sysResource_id%>;
	
	$(document).ready(function() {
		if (row_id != null) {
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
		}
	});
  	</script>
</html>

