<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>已分解的</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
 	<script type="text/javascript">
 		var basePath = '<%= basePath%>';
 		var orderCode ='${orderCode}';
 		var sonGrid;
 		var mainForm;
 		$(function(){
 			getForm();
 			
 			getGrid();
 			
 			setForm();
 		});
 		
 		function getForm(){
 			mainForm = $("#mainForm").ligerForm({
 				inputWidth : 170,
 				labelWidth : 90,
 				space : 40,
 				fields : [ {
 					display : "订单编号",
 					name : "orderCode",
 					type : "text",
 					newline : false,
 					/* editor : {
 						onChangeValue : function(value) {
 							checkOrderCode(value);
 						}
 					} */
 				}, {
 					display : '交货日期',
 					name : 'deliveryDate',
 					type : "date",
 					newline : false
 				}, {
 					display : '销售部经办人',
 					name : 'salesManager',
 					type : "text",
 					newline : false
 				}, {
 					display : '订单录入人员',
 					name : 'orderEntryClerk',
 					type : "text",
 					newline : false
 				}, {
 					display : '合同编号',
 					name : 'contractCode',
 					type : "text",
 					newline : false
 				} ]
 			});
 			
 			liger.get('orderCode').setDisabled(); //设置只读
    		liger.get('deliveryDate').setDisabled(); //设置只读
    		liger.get('contractCode').setDisabled(); //设置只读
    		liger.get('orderEntryClerk').setDisabled(); //设置只读
    		liger.get('salesManager').setDisabled(); //设置只读
 		}
 		
 		function setForm(){
 			debugger;
 			var url = basePath+"rest/sellSalesOrderManageAction/getByOrderCode";
 			$.post(url,{orderCode:orderCode},function(data){
 				mainForm.setData(data);
 			},"json");
 		}
 		function getGrid(){
 			sonGrid = $("#myGrid").ligerGrid({
 				title : "订单明细列表",
 				url : basePath+"rest/sellSalesOrderDetailsManageAction/getListPageData?orderCode="+orderCode,
 				//checkbox : true,
 				allowAdjustColWidth : true,
 				delayLoad : false,
 				alternatingRow : false,
 			    rownumbers:true,
 				rownumbersColWidth : 60,
 				height : 300,
 				usePager : false,
 				columns : [
 				// { dispaly:"id",name:"id",type:"hidden"},
 				{
 					display : '订单编号',
 					name : 'orderCode'
 				}/* , {
 					display : '交货日期',
 					name : 'deliveryDate'
 				} */, {
 					display : '产品规格型号',
 					name : 'proGgxh'
 				}, {
 					display : '每轴长度',
 					name : 'als'
 				}, {
 					display : '轴数',
 					name : 'axisNumber'
 				}, {
 					display : '颜色',
 					name : 'proColor'
 				}, {
 					display : '总长度',
 					name : 'totalLength'
 				} ,{
 					hide:'id',
 					name:'id',
 					width:1
 				}],
 				data : {
 					Rows : []
 				}
 			});
 		}
 	
 		
 		
 		function getAllSaveDatas() {
 			debugger;
 			var data = [];
 			var mainData = getMainBean();
 			var sonDatas = getSonBeans();
 			// 转化日期格式
 			mainData.deliveryDate = parseDateToString(mainData.deliveryDate);

 			for (var i = 0; i < sonDatas.length; i++) {
 				// alert("转换前的从表日期"+sonDatas[i].deliveryDate);
 				sonDatas[i].deliveryDate = parseDateToString(sonDatas[i].deliveryDate);
 				// alert("转换后的从表日期"+sonDatas[i].deliveryDate);
 			}

 			data.push(mainData);
 			data.push(sonDatas);
 			return data;
 		}
    </script> 
  </head>
  
  <body style="padding:0px;background:#EAEEF5;">
 	<div id="mainForm"></div>
 	<!-- 订单明细列表 -->
	<div id="myGrid"></div>
	<!-- 订单明细输入框 -->
	<div id="sonForm"></div>
  </body>
</html>
