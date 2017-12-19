<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String plaCourse_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>plaCourseEdit</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />

<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<link href="<%=basePath%>app/css/newpage.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "plaCourseManage"; 
	var row_id = "";
	row_id = ${id};
	var proGgxh = '${proGgxh}';
	var proColor = '${proColor}';
	
</script>
	<script src="<%=basePath%>core/js/echarts.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

<%-- <script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script> --%>
<!-- 默认引用1end -->

<!-- 默认引用 -->
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script src="<%=basePath %>app/js/plaManage/course/plaCourseSelectMachine.js" type="text/javascript"></script>
<!-- 默认引用end -->
<script type="text/javascript">
	var ids = "${ids}";
	var gdh = "${gdh}";	
	var proCraftCode = "${proCraftCode}";	
</script>
<style type="text/css">
.l-clear{clear:both;}
</style>
</head>

<body style="align:center;text-align:center;padding-left:6px;width:99%;height:100%;">
	<br>
	<h3 style="font-size:28px; font-weight:700; float: left;">计划过程</h3>
	<img src="<%=basePath%>app/images/line.png" style="height:1px; width:84%;padding-top:13px; padding-left:10px;  background-repeat: repeat-x; float:left; "></img>
	<p style="clear:both;"></p> 
	<!-- <hr> -->
	<br>

    <div id="message" style="width:800px"></div>
    <div class="l-loading" style="display:none;" id="pageloading"></div> 
    
	  
	<div style="width:100%;height:100%;overflow:hidden;">
		
		<form id="plaCourseManage" method="post">
	 		<div></div> 
	 		<fieldset class="l-fieldset">
		 		<legend class="l-legend">
				</legend>
				<table cellpadding="1px" cellspacing="1px" class="l-table-edit" >
					<tr style="background-color:#F4F8FC;">
						<!-- color:gray; -->
						<td align="right" class="table-edit-label" >工单号：</td>
						<td align="left" class="table-edit-td"><span id="gdh" align="left">&nbsp;</span></td>
						<td align="right" class="table-edit-label" >&nbsp;</td>
					</tr>
				</table>
			</fieldset>
			
			<input id="ids" name="ids" type="hidden" value="${ids}"/>
			
			
	 		<!-- <table cellpadding="0" cellspacing="0" class="l-table-edit" >
	 			<tr class="l-table-edit-tr" >
	 				<td align="right" width="20%" class="l-table-edit-td">开单方式：</td>
	 				<td align="left" width="80%" class="l-table-edit-td-input">
	 					<input name="priorityWay_" type="radio" ltype="radio" radio=true checked value="产能优先"/>产能优先
		      			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		      			<input name="priorityWay_" type="radio" ltype="radio" radio=true value="节能优先" />节能优先
		      			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		      			<input name="priorityWay_" type="radio" ltype="radio" radio=true value="成本优先" />成本优先
		      			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		      			<input name="priorityWay_" type="radio" ltype="radio" radio=true value="自定义" />自定义
	 				</td>
	 			</tr>
	 		</table> -->
	<!-- 		<input class="l-button l-button-submit" type="submit" value="提交"/> -->
	<!-- 		<input class="l-button l-button-test" type="reset" value="重置"/> -->
		</form>
		<div id="divpar" class="top" style="width:100%;height:100%;">
			<!-- <span id="treediv" style="width:20%;height:100%;minheight:400px;"></span>
			<span style="width: 79%;height: 100%;text-align: center;margin: 0 auto;">
				<div id="container"	style="height: 80%;width: 76%;text-align: center;margin: 0 auto;">&nbsp;</div>
				<div style="height: 20%;width: 76%;text-align: center;margin: 0 auto;">
				 	asefraser
				</div>
			</span> -->
			<div position="left" id="treediv" style="overflow-y:auto;height:99%;" ></div>
			<div position="center" id="rightdiv" >
				<div id="container"	style="height: 80%;width: 98%%;text-align: center;margin: 0 auto;border: solid 1px black;">&nbsp;</div>
				<div style="height: 20%;width: 98%;text-align: center;margin: 0 auto;">
				 	<div style="margin:auto;align:right;text-align:center;">
				 		<br>
				 		<input id="save_btn" class="l-button" type="button" value="保存" onClick="save()"/>
				 	</div>
				</div>
			</div> 
			<div class="l-clear"></div>
		</div>
		<!-- <div class="top"
			style="width: 80%;height:400px;margin:0 auto; margin-top:0;border: 1px solid #EBEBEB;border-top-color: red">
			<div class="two-a"
				style="width:100%;height: 100%;margin:0 auto; margin-top:0;margin-left:1px;margin-right:1px;">
	 				<div class="l-clear"></div>
	 				机台负荷
	    			<div id="jcfhgrid"></div>
			</div>
		</div> -->
	</div>
</body>
</html>

