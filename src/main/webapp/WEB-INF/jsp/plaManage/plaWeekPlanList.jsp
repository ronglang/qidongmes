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
<title>plaProductOrderList</title>
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />

<script src="<%=basePath%>core/js/jquery-1.7.2.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>

<script>
	var basePath  = '<%=basePath%>';
	var routeName = "plaProductOrderManage";
</script>
</head>

<body style="padding:6px;overflow: scroll;">
	<!-- <input type="button" value="生成机台计划" onclick="gen()"
		style="width: 100px"> -->
	<iframe id="if1"
		style="height: 600px;width:100%;border:1px solid #ccc;" scrolling="no"
		src="<%=basePath%>rest/plaWeekPlanManageAction/toPlaWeekPlanTable">
	</iframe>
	<!-- <iframe id="if2"
		style="height: 400px;width:100%;border:1px solid #ccc;display: none;"
		scrolling="no" src=""> </iframe> -->
</body>
<script type="text/javascript">
	function gen() { //得到周计划的Id，为生成机台计划准备
		var manager = document.getElementById('if1').contentWindow.$(
				"#maingrid").ligerGetGridManager();
		var li = manager.getSelectedRows();
		var ids = [];
		for (var i = 0; i < li.length; i++) {
			ids.push(li[i].id);
		}
		$.ajax({
			url : basePath+ "rest/plaMachinePlanManageAction/getPlaMachinePlan",
			dataType : 'json',
			data : "weekIds=" + ids,
			success : function(map) {
				debugger;
				if(map.success){
					manager.reload();
					setIframValue(li);
				}else{
					alert(map.msg);
				}
			}
		});

	}
	
	//生成机台计划，现在默认在生成周计划的时候直接生成。这里不再生成机台计划
	function setIframValue(li) {
		var ur = basePath + "rest/plaMachinePlanManageAction/toPlaMachineTable?";
		var ids = [];
		for (var i = 0; i < li.length; i++) {
			ids.push(li[i].id);
		}
		ur += "ids=" + ids;
		$("#if2").css("display", "block");
		$("#if2").attr("src", ur);
	}
</script>
</html>