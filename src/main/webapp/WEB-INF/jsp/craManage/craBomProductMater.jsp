<%@ page language="java" 
 import="com.css.business.web.sysManage.bean.SysConfig,com.css.common.util.Constant,com.css.business.web.sysManage.bean.SysUser,com.css.commcon.util.SessionUtils"
 pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	SysUser user = SessionUtils.getUser(request);
%>
<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">
		
		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>产品工艺的工序工艺关系管理</title>
		
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
		<link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" />
		<link href="<%=basePath%>app/css/newpage.css" rel="stylesheet" />
		
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
		
	   <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
		
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
		
		<script src="<%=basePath %>app/js/craManage/craBomProductMater.js" type="text/javascript"></script>
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<script>
			var basePath  = '<%=basePath%>';
			var routeName = "craProSeqRelationManage";    
			var row_id = null;
			var relationId = "${relationId}";
			alert(relationId);
    	</script>
	<style type="text/css">
		::-moz-placeholder{color:red;}              //ff
    ::-webkit-input-placeholder{color:red;}     //chrome,safari
    :-ms-input-placeholder{color:red;}
    
    	.cion50
		{
			width:80px;
			height:30px;
			float:left;
			margin-right:5px;
		}
    	.cion80
		{
			width:80px;
			height:18px;
			float:left;
			margin-right:5px;
		}
    	.cion100
		{
			width:100px;
			height:30px;
			float:left;
			margin-right:5px;
		}
    	.cion120
		{
			width:120px;
			height:30px;
			float:left;
			margin-right:5px;
		}
			table td{word-break: keep-all;white-space:nowrap;}
    		
			.bfpart1
			{
				border:1px solid #333333;
			}
			.bfpart1 td
			{
				border:1px solid #333333;
				font-size:13px;
				height:35px;
				background-color:white;
			}
			.l-table-edit {
			    border:1px solid #9a9a9a;
			    width:99%;
			  /*   table-layout:fixed; */
			}
			.l-table-edit-td{
				border-bottom: 1px solid #6b6b6b;
				text-overflow: ellipsis;
				word-break: keep-all;
				overflow: hidden;
				color: #6b6b6b;
			}
			.table-edi {
				width: 99%;
			}
			.tdnowrap{
				text-overflow:ellipsis; 
				word-break:keep-all; 
				overflow:hidden;
				display:inline;
			}
			.tdcenter{
				align:center;
				text-align:center;
			}
			.ta{width:100%;}
	</style>
	</head>
	<body style="padding-top:0px;padding-left:6px; overflow:hidden;">
		<div id="myGrid1"></div>
	</body>
</html>

