<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />

<title>Insert title here</title>
</head>
<body>
	<iframe style="height: 600px;width: 1250px;" src="http://124.161.245.148:8081/MapProbzfp/map/jsp/layermanage.jsp?showOper=false"></iframe>
<!-- <iframe style="height: 600px;width: 1250px;" src="http://localhost:8080/MapPro/map/jsp/layermanage.jsp?showOper=false"></iframe> -->
</body>
</html>