<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String blog_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>bolgEdit</title>
<!-- 树引用 -->
<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css"
	rel="stylesheet" />
<link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" />
<!-- 树引用  end-->

<!-- 默认引用1 -->
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Silvery/css/style.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/plugin/swfupload/css/default.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />

<script src="<%=basePath%>core/js/jquery-1.4.4.min.js"
	type="text/javascript"></script>

<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerForm.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerDateEditor.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerComboBox.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerCheckBoxList.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerRadioList.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerSpinner.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerTextBox.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>

<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<!-- 默认引用1end -->
<!-- 上传控件引用 -->
<script src="<%=basePath%>core/plugin/json.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/swfupload/swfupload.js"
	type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/swfupload/swfupload.queue.js"
	type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/swfupload/fileprogress.js"
	type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/swfupload/swfuploadProperties.js"
	type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/swfupload/handlers.js"
	type="text/javascript"></script>
<!-- 上传控件引用 end-->
<!-- 树引用 -->
<script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js"
	type="text/javascript"></script>
<script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js"
	type="text/javascript"></script>
<!-- 树引用 end-->
<!-- 富文本框 -->
<script src="<%=basePath%>core/js/ueditor/ueditor.config.js"
	type="text/javascript"></script>
<script src="<%=basePath%>core/js/ueditor/ueditor.all.min.js"
	type="text/javascript"></script>
<!-- 富文本框end -->
<!-- 默认引用 -->
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<!-- 默认引用end -->
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "blogManage";
	var row_id = "";
	row_id =<%=blog_id%>;
</script>
<style type="text/css">
* {
	padding: 0;
	margin: 0;
}

html,body {
	height: 100%;
}

#container {
	width: 100%;
	height:100%;
	overflow: hidden;
}

.sections,.section {
	height: 100%;
}

#container,.sections {
	position: relative;
}

.section {
	background-color: #000;
	background-size: cover;
	background-position: 50% 50%;
	text-align: center;
	color: white;
}

#section0 {
	background-image: url('<%=basePath%>core/img/安全1.jpg');
}

#section1 {
	background-image: url('<%=basePath%>core/img/创新1.jpg');
}

#section2 {
	background-image: url('<%=basePath%>core/img/共享1.jpg');
}

#section3 {
	background-image: url('<%=basePath%>core/img/责任1.jpg');
}
</style>
</head>

<body>
	<div id="container">
		<div class="sections">
			<div class="section" id="section0">
				
			</div>
			<div class="section" id="section1">
				
			</div>
			<div class="section" id="section2">
				
			</div>
			<div class="section" id="section3">
				
			</div>
		</div>
	</div>

	<script src="<%=basePath%>core/js/jquery-1.11.1.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/pageswitch.js"></script>
	<script>
		$("#container").PageSwitch({
			direction : 'horizontal',
			easing : 'ease-in',
			duration : 3000,
			autoPlay : true,
			loop : true
		});
	</script>
</body>
</html>

