<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发消息</title>
<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css"
	rel="stylesheet" />
<link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" />
<!-- 默认引用1 -->
 <link 
href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
rel="stylesheet" type="text/css" /> 
   <link href="<%=basePath%>core/plugin/swfupload/css/default.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<link href="<%=basePath%>app/css/newpage.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "bCityManage";
</script>
<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>

<script	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js"	type="text/javascript"></script>
<script	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"	type="text/javascript"></script>

<script	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"	type="text/javascript"></script>
<script	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js"	type="text/javascript"></script>
<script	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js"	type="text/javascript"></script>
<!-- 默认引用1end -->
<script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js"	type="text/javascript"></script>
<script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js"	type="text/javascript"></script>

<!-- 默认引用 -->
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script src="<%=basePath%>app/js/apache/activeMqWrite.js" type="text/javascript"></script>
<!-- 默认引用end -->
<script type="text/javascript">
$(function (){
	$('#111001_btn_save').click(function(){
		sendmsg();
	});
	$('#111001_btn_save2').click(function(){
		sendmsgSession();
	});
});
function sendmsg(){
	$.ajax({
		url:basePath + "rest/queueSendAction/sendQueueMethod1?areaCode=22342&aname=ttttest",
		type:"post",
		dataType:"json",
		async:true,
        success: function(json){
        	alert('success');
        },  
        error: function(json) {
        	alert('发生错误');
        }
	});
}		
function sendmsgSession(){
	$.ajax({
		url:basePath + "rest/queueSendAction/sendQueueMethodSession?areaCode=22342&aname=ttttest",
		type:"post",
		dataType:"json",
		async:true,
        success: function(json){
        	alert('success');
        },  
        error: function(json) {
        	alert('发生错误');
        }
	});
}	
</script>
</head>

<body>
	<h1 style="font-size:32px; font-weight:700; float: left;">www</h1>
    <img src="<%=basePath%>app/images/line.png" style="height:1px; width:84%;padding-top:13px; padding-left:10px;  background-repeat: repeat-x; float:left; "></img>
	<p style="clear:both;"></p>
	<div id="content" align="center">  
	<form id="bCityManage" method="post" >
		<table cellpadding="0" cellspacing="0" class=l-table-edit style="margin-top: 15px;">
			<tr class=table-edit-tr>
			 <td class=table-edit-td>&nbsp;&nbsp;&nbsp;基本信息</td>
			</tr>
		 </table>
				<div style="width:10%;text-align:center;align:center;">
			   		<input id="111001_btn_save" name="111001_btn_save" class="l-button l-button-submit" type="submit" value="保存"  style="color: #FFF;"/> 
			   		<input id="111001_btn_save2" name="111001_btn_save" class="l-button l-button-submit" type="submit" value="带回执消息"  style="color: #FFF;"/> 
				</div>
	</form>
</div>
</body>
</html>

