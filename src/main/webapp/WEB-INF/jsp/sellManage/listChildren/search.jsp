<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link href="lib/ligerUI/skins/Aqua/css/ligerui-form.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div style="width: 100%;height:300px;overflow: hidden;">
			<form>
				公司简称:<select style="margin-right: 1px;">
					<option>
					321323123412431234
					</option>
				</select>
			</form>
			<input type="submit" value="搜索" />
			
		</div>
	</body>
</html>
