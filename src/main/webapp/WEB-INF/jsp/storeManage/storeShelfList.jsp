<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>storeShelfList</title>
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	    <link
	     href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css"
	     rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		
		<script src="<%=basePath %>app/js/storeManage/storeShelfList.js" type="text/javascript"></script>
		<script>
			var basePath  = '<%=basePath%>';
			var routeName = "storeShelfManage";    
    	</script>
    	<style type="text/css">
    		#sort{
		  		border:none;
	    		margin-left:20px;
	    		background-color: #0099CC;
	    		color:white;
	    		width:80px;
	    		height:25px;
	    		border-radius:5px;
	    		cursor: pointer;
	    		font-size: 13px;
		  	}
		  #sort:hover{
    			background-color: #50C1E8;
	    	}
	    	#showall{
	    		border:none;
	    		margin-left:20px;
	    		background-color: #FFAB95;
	    		color:white;
	    		width:80px;
	    		height:25px;
	    		border-radius:5px;
	    		cursor: pointer;
	    		font-size: 13px;
	    	}
	    	#showall:hover{
	    		background-color: #F7C6B9;
	    	}
    	</style>
	</head>
	<body style="padding:6px; overflow:hidden;">
        <div style="width:100%;height:35px">
          <div id="queryForm" class="liger-form" style="float:left;"></div>
          <div style="margin-left:30px">
          <input type="button" name="" id="sort" value="查  询"  onclick="searchForm()"/>
		  <input type="button" name="" id="showall" value="重  置" onclick="resetForm()"/>
          <!-- 
           <button type="button" onclick="searchForm()">搜索</button>
              &nbsp&nbsp&nbsp&nbsp&nbsp
            <button type="button" onclick="resetForm()">重置</button>
          -->
          </div>
        </div>
		<div id="storeShelfManageList" style="margin:0; padding:0"></div>  
</body> 
</html>



