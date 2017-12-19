<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String sellContract_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>sellContractEdit</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "sellContractManage"; 
	var row_id = "";
	row_id =<%=sellContract_id%>;
</script>
<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
 <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
<!-- 默认引用1end -->

<!-- 默认引用 -->
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script src="<%=basePath%>app/js/sellManage/sellContractEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->

</head>

<body>
	<form id="sellContractManage" method="post">
		<div></div>
		<div id="adddiv" style="display:none">
    		<div id="addNew1">
	    		 	<div id="headwsCodediv">
	    		 		合同录入
	    		 	</div>
	    		 	<label class="firstlable" for="scCode">合同编号:<input id="scCode" name="scCode" type="text" /></label>
	    			<label class="secondlable" for="scDate">签订日期:<input id="scDate" name="scDate" type="date" /></label><br/>
				    <label class="firstlable" for="scMoney">合同金额:<input id="scMoney" name="scMoney" type="text" /></label>
					<label class="secondlable" for="cusName">客户名称:<input id="cusName" name="cusName" type="text"/></label><br/>
				    <label class="firstlable" for="cusCode">客户编号:<input id="cusCode" name="cusCode" type="text"/></label>
			       	<label class="secondlable" for="agentBy">经办人员:<input  id="agentBy" name="agentBy" type="text"/></label><br/>
				    <label class="firstlable" for="createDate">录入时间:<input id="createDate" name="createDate" type="date"/></label> 
				    <label class="secondlable" for="createBy">录入人员:<input id="createBy" name="createBy" type="text"/></label> 
    		</div>
    		<div id="addNew2"></div>
    	</div>
		
		<input class="l-button l-button-submit" type="button" value="删除行"/>
		<input class="l-button l-button-submit" type="button" value="新增行"/>
		<input class="l-button l-button-submit" type="button" value="保存"/>
		<input class="l-button l-button-submit" type="button" value="关闭"/>
	</form>
</body>
</html>

