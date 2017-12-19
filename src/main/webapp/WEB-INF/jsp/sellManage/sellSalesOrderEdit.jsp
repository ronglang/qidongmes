<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>订单管理</title>
    <link href="<%= basePath%>app/js/sellManage/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />  
    <link rel="stylesheet" type="text/css" id="mylink"/>   
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerForm.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerComboBox.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerCheckBoxList.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerListBox.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
	<script src="<%= basePath%>app/js/sellManage/sellSalesOrderEdit.js"></script>
    <script type="text/javascript">
 		var basePath = '<%= basePath%>';
    </script> 
<style type="text/css"> 
</style>
</head>
<body style="padding:0px;background:#EAEEF5;">
  
	<!-- 展示订单表头录入框 -->
	<div id="mainForm">
<!-- 		<div class="fields"> -->
<!-- 			<input data-type="hidden" data-label="id" data-name="id" /> -->
<!-- 			<input data-type="text" data-label="订单编号" data-name="orderCode"/> -->
<!-- 			<div data-type="select" data-label="合同编号" data-name="contractCode" id="contractCode" > -->
<!-- 				<input class="editor" data-data="getData()" data-onSelected="selectContractCode" data-textField="contractCode" data-valueField="contractCode"/ /> -->
<!-- 			</div> -->
<!-- 			<input data-type="date" data-label="交货日期" data-name="deliveryDate"/> -->
<!-- 			<input data-type="text" data-label="销售部经办人" data-name="salesManager"/> -->
<!-- 			<input data-type="text" data-label="订单录入人员" data-name="orderEntryClerk"/> -->
<!-- 			<input data-type="text" data-label="创建人" data-name="createBy"  /> -->
<!-- 		</div>   -->
	</div>
	<!-- 订单明细列表 -->
	<div id="myGrid"></div>
	<!-- 订单明细输入框 -->
	<div id="sonForm">
<!-- 		<div class="fields"> -->
<!-- 			<input type="hidden" data-label="id" data-name="id" /> -->
<!-- 			<input type="text" data-label="订单编号" data-name="orderCode" /> -->
<!-- 			<input type="date" data-label="交货日期" data-name="deliveryDate"/> -->
<!-- 			<input type="text" data-label="规格型号" data-name="proGgxh" /> -->
<!-- 			<input type="text" data-label="轴长度" data-name="als" /> -->
<!-- 			<input type="text" data-label="轴数" data-name="axisNumber"  /> -->
<!-- 			<input type="text" data-label="颜色" data-name="proColor" /> -->
<!-- 			<input type="text" data-label="创建人" data-name="createBy"  /> -->
<!-- 			<input type="text" data-label="总长度" data-name="totalLength"/> -->
<!-- 		</div> -->
	</div>
</body>
</html>

