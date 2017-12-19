<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>编辑的</title>
    
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
		var tempData1;
		var gridData ;
		var gridIndex;
		$(function(){
			//初始化产品规格型号
			initProGgxh();
			getForm();
			
			//getGrid();
			
			setForm();
			loadSonForm();
			setGrid();
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
					}  */
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
				}, {
					display : '订单类型',
					name : 'orderType',
					type : "text",
					newline : false
				}, {
					display : '优先级',
					name : 'priLevel',
					type : "text",
					newline : false
				}, {
					display : 'id',
					name : 'id',
					type : "hidden",
					newline : false
				}  ]
			});
			liger.get('orderCode').setDisabled();
			liger.get('orderType').setDisabled();
			liger.get('priLevel').setDisabled();
		}
		
		function setForm(){
			//debugger;
			var url = basePath+"rest/sellSalesOrderManageAction/getByOrderCode";
			$.post(url,{orderCode:orderCode},function(data){
				mainForm.setData(data);
			},"json");
		}
		function getGrid(){
			sonGrid = $("#myGrid").ligerGrid({
				title : "订单明细列表",
				//url : basePath+"rest/sellSalesOrderDetailsManageAction/getListPageData?orderCode="+orderCode,
				checkbox : true,
				allowAdjustColWidth : true,
				delayLoad : false,
				alternatingRow : false,
			   // rownumbers:true,
				rownumbersColWidth : 60,
				height : 300,
				usePager : false,
				columns : [
				// { dispaly:"id",name:"id",type:"hidden"},
				{
					display : '订单编号',
					name : 'orderCode'
				}, /* {
					display : '交货日期',
					name : 'deliveryDate'
				}, */ {
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
				}, {
					hide : 'id',
					name : 'id',
					width:1
				} ],
				data : {
					Rows : gridData
				},
				toolbar : {
					items : [ {
						text : "修改",
						click : modify,
						icon : "modify"
					}/* ,{
						text : "删除",
						click : delBean,
						icon : "add"
					} */ ]
				}
			});
		}
		
		function setGrid(){
			$.post(basePath+"rest/sellSalesOrderDetailsManageAction/getListPageData",{orderCode:orderCode},function(page){
				debugger;
				gridData = page.Rows;
				getGrid();
			},"json");
		}
		/**
		 * 点击增加明细，添加一行记录
		 */
		function addRowData() {
			var axis = $("[name=axisNumber]").val();
			var color = $("[name=proColor]").val();
			if (axis == null || axis == "" || color == null || color == "") {
				//debugger;
				$.ligerDialog.warn("请将数据填写完整");
				return;
			}
			var sonDataTemp = sonForm.getData();
			debugger;
			var oldData = sonGrid.get("data").Rows;
			// TODO 将日期类型转换成字符串类型
			sonDataTemp.deliveryDate = parseDateToString(sonDataTemp.deliveryDate);
			sonDataTemp.orderCode = mainForm.getData().orderCode;
			// 计算总长度
			sonDataTemp.totalLength = sonDataTemp.axisNumber * sonDataTemp.als;
			sonDataTemp.id = null;
			gridData.push(sonDataTemp);
			sonGrid.reload();
		}
		
		function getAllSaveDatas() {
 			//debugger;
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
		
		/**
		 * 获取表头数据集
		 */
		function getMainBean() {
			// mainData = liger.get("mainForm");
			var mainData = mainForm.getData();
			return mainData;
		}
		/**
		 * 获取明细数据集
		 */
		function getSonBeans() {
			// sonData = sonForm.getData();
			return sonGrid.getData();
		}
		
		/**
		 * 初始化订单从表页面
		 */
		function loadSonForm() {

			sonForm = $("#sonForm")
					.ligerForm(
							{
								inputWidth : 170,
								labelWidth : 90,
								space : 40,
								fields :[/* {
											display : '交货日期',
											name : 'deliveryDate',
											type : "date",
											validate : {
												required : true,
												minlength : 2
											},
											newline : false
										}, */
										{
											display : '产品规格型号',
											name : 'proGgxh',
											newline : false,
											textField : "proGgxh",
											type : "popup",
											validate : {
												required : true,
												minlength : 2
											},
											editor : {
												selectBoxWidth : 600,
												selectBoxHeight : 300,
												textField : 'proGgxh',
												valeuField : 'proGgxh',
												condition : {
													fields : [ {
														label : '产品规格型号',
														name : 'proGgxh',
														type : 'text'
													} ]
												},
												grid : {
													columns : [ 
													   {
														display : '产品规格型号',
														name : 'proGgxh',
														align : 'left',
														width : 300,
														minWidth : 33
													   	},
													],
													sortName:'proGgxh',
													usePager : false,
													data : tempData1
												},
											}

										},
										{
											display : '每轴长度',
											name : 'als',
											type : "text",
											validate : {
												required : true,
												minlength : 2
											},
											newline : false
										},
										{
											display : '轴数',
											name : 'axisNumber',
											type : "text",
											validate : {
												required : true,
												minlength : 2
											},
											newline : false

										},
										{
											display : '颜色',
											name : 'proColor',
											type : "select",
											validate : {
												required : true,
												minlength : 2
											},
											newline : false,
											comboboxName : 'proColor',
											editor : {
												url : basePath
														+ 'rest/sellSalesOrderManageAction/findProColor',
												valueField : 'text',
												//isMultiSelect : true,
											}

										},
										{
											display : '总长度',
											name : 'totalLength',
											type : "hidden",
											newline : false
										},
										{
											display : 'id',
											name : 'id',
											type : "hidden",
											newline : false
										}],
								validate : true
							});
		}
		
		/**
		 * 点击增加明细，添加明细记录
		 */
		function addSonData() {
			if (!sonGrid) {

				loadSonGrid();// 加载订单明细Grid
				// 初始化规格型号
				initProGgxh();
				loadSonForm();// 加载订单明细输入框

			} else {
				//debugger;
				addRowData();
			}
		}
		
		/**
		 * 初始化规格型号
		 */
		function initProGgxh() {

			$.ajax({
				url : basePath + "rest/sellSalesOrderManageAction/selectProGgxh",
				dataType : 'json',
				data : "",
				type : "post",
				async : false,
				// contentType:"application/json",
				success : function(data) {
					if (data.success) {
						tempData1 = data.data;
					} else {
					}
				}
			});
		}
		
		/**
		*  确认修改明细修改后保存
		*/
		function editSonData(){
			var axis = $("[name=axisNumber]").val();
			var color = $("[name=proColor]").val();
			if (axis == null || axis == "" || color == null || color == "") {
				//debugger;
				$.ligerDialog.warn("请将数据填写完整");
				return;
			}
			var sonDataTemp = sonForm.getData();
			/* debugger;
			var oldData = sonGrid.get("data").Rows; */
			// TODO 将日期类型转换成字符串类型
			sonDataTemp.deliveryDate = parseDateToString(sonDataTemp.deliveryDate);
			sonDataTemp.orderCode = mainForm.getData().orderCode;
			// 计算总长度
			sonDataTemp.totalLength = sonDataTemp.axisNumber * sonDataTemp.als;
			gridData[gridIndex] = sonDataTemp;
			sonGrid.reload();
		}
		
		/**
		*修改
		*/
		function modify(){
			var delRows = sonGrid.getSelectedRows();
			if(delRows.length !=1){
				$.ligerDialog.warn("请选择一行进行修改");
				return;
			}
			gridIndex = delRows[0].__index;
			//alert(gridIndex);
			sonForm.setData(delRows[0]);
			
		}
		
		
		
		
		/**
		*删除
		*/
		function delBean(){
			//删除分两种一是页面,而是数据库
			var delRows = sonGrid.getSelectedRows();
			if(delRows.length !=1){
				$.ligerDialog.warn("请选择一行进行删除");
				return;
			}
			var index  = delRows[0].__index;
			gridData = ListRemove(gridData,index);
			getGrid();
		}
		
		/**
		 * 将日期类型转换成字符串格式
		 * 
		 * @param date
		 */
		function parseDateToString(date) {
			//debugger;
			if (!date) {// 传入日期类型为空

			} else {// 不为空
				if (date instanceof Date) {// 匹配为日期类型
					var year = date.getFullYear();
					var month = date.getMonth() + 1;
					var day = date.getDate();
					return year + "-" + (month < 10 ? "0" + month : month) + "-"
							+ (day < 10 ? "0" + day : day);
				} else {
				}
			}
			return date;
		}
		
		//List 根据删除
		function ListRemove(list,index){
			 if(isNaN(index)||index>list.length){return false;} 
			 for(var i=0,n=0;list<this.length;i++){ 
	              if(list[i]!=list[dx]) { 
	            	  list[n++]=list[i];
	              } 
	            } 
	            list.length-=1 ;
	            return list;
		}
		
		 function checkOrderCode(value) {
			/* var boo = true;
			$.ajax({
				url : basePath + "rest/sellSalesOrderManageAction/checkOrderCode",
				dataType : 'json',
				data : "orderCode=" + value,
				type : "post",
				async : false,
				// contentType:"application/json",
				success : function(data) {
					if (data.success) {// 编号已经存在
						
					} else {// 编号不存在
						boo = false;
					}
				}
			});
			if (boo) {
				$.ligerDialog.alert("订单编号已经存在，请重新输入");
			}
			return boo; */
			
			return false;
		} 
		 
		  function getOrderCode(){
			 var orderCode = $("[name=orderCode]").val();
			 return orderCode;
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
