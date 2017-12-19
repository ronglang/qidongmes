<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String s = "";
	Object o = session.getAttribute("o");
	if(o == null){
		session.setAttribute("o","1");
		o = "1";
	}
	System.out.println("1111111111111111111111111111111:::"+o.toString());
%>

<!DOCTYPE html>
<html>
	<head>
		<title>集群1</title>
	</head>
	<body>
				集群1集群1集群1集群1集群1集群1,,<%=o.toString() %>
	</body>
</html>