<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>生产部铠装工序电子看板</title>
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
	<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<script src="<%=basePath%>core/js/echarts.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	
	<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerCheckBox.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerResizable.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerComboBox.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
	<script src="<%=basePath%>app/js/mauManage/mauArmoure.js" type="text/javascript"></script>
	<style>
			body{
				background-color: #0066FF;
			}
			#params{
				height: 240px;
				
			}
			#charts{
				margin-top:30px;
			}
			
			#PShow{
				width: 100%;
				height: 100%;
			}
			#HSTest{
				height: 300px;
				width: 45%;
				margin-left: 2%;
				background-color: #FFFFFF;
			}
			#MP{
				width: 47%;
				height: 300px;
				margin-left: 4%;
				background-color: #FFFFFF;
			}
			.ltext{
				font-size:20px;
				margin-left:30px;
			}
			#tt{
				margin-left:0px;
			}
			#ttt{
				margin-left:0px;
			}
		</style>
		<script>
			var basePath  = '<%=basePath%>';
    	</script>
  </head> 
  <body>
   		<div id="params">
			<iframe id="PShow" name="PShow" src="<%=basePath%>rest/testWireDrawingAction/toPShow"></iframe>
		</div>
		<div id="charts">
			<iframe id="HSTest" src="<%=basePath%>rest/testWireDrawingAction/toHostSpeedTest"></iframe>
			<iframe id="MP" src="<%=basePath%>rest/testWireDrawingAction/toMProgress"></iframe>
		</div>
  </body>
</html>
