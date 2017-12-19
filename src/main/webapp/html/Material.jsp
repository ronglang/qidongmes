
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html>
  <head>
    <title>Material.html</title>
	
    <meta name="keywords" content="keyword1,keyword2,keyword3">
    <meta name="description" content="this is my page">
    <meta name="content-type" content="text/html; charset=UTF-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
	<link rel="stylesheet" href="../core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" />
	<link rel="stylesheet" href="../app/css/storeBasicInfo/storeMaterialInput.css" />
	<script type="text/javascript" src="../core/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="../core/plugin/LigerUI/lib/ligerUI/js/core/base.js"></script>
	<script type="text/javascript" src="../core/plugin/LigerUI/lib/ligerUI/js/ligerui.min.js"></script>
  	<script type="text/javascript" src="../core/js/jquery-validation/jquery.validate.min.js"></script>
  	<script type="text/javascript" src="../app/js/storeManage/storeBasicInfo/storeInfoInput.js"></script>
  	  <script type="text/javascript">
   		var basePath = '<%=basePath%>';
   		var routeName = "storeMaterialBasicInfoManage";
   		var row_id = null;
   </script>
  </head>
  <body>
 	
 	<form id="inputform"></form>
    <div class="btnarea">
		<input id="save" class="btn" type="submit" class="btn" value="提交" />
		<input id="cancel" class="btn" type="reset" class="btn" value="取消" />
	</div>
 	
    
  </body>
</html>
