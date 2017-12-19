<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=basePath %>core/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=basePath %>app/js/userManage/userManage.js"></script>
</head>
<body>
<form id = "fileUploadForm" name="fileUploadForm" action="<%=basePath %>rest/fileUploadAction/uploadfile" enctype="multipart/form-data" method="post"">
 		<div id="imgesContainer">
			<input type="file" name="file">
		</div>
		<input type="button" id="btn_add2" value="增加一行" >
		<input type="submit" value="上传" >
 </form>
<input type="button" title="save" value="save" onclick="userManage.saveBlog()"/>
<input type="button" title="save" value="locationUrl" onclick="userManage.locationUrl()"/>
<img id="uploadimg" height="200" width="300" src="http://localhost:8080/yaecp/uploadfiles\\attachment\\2015\\04\\17\\201516171429249248538ps33b.jpg">
</body>
</html>