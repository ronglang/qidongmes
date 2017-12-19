<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String proGgxh = request.getParameter("proGgxh");
	String flag = request.getParameter("flag");
	String seqName = request.getParameter("seqName");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title></title>
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerGrid.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>
<script type="text/javascript">
		var list=[];
		var form,grids,j,length,id	;
		var i=0;
		var tempData1;
		var tempData2;
		var proGgxh = '<%=proGgxh%>';
		var flag = '<%=flag%>';
		var seqName = '<%=seqName%>';
		var CustomersData = {
				Rows: list
			};
		var url;
		$(function (){
			getProduct();
			getMateril();
            if(flag==0){
				grid();
	            form();
            }else if( flag==1){
            	url = "<%=basePath%>rest/craLsBomParamManageAction/getGridByProGgxh?proGgxh="+proGgxh;
            	grid(url);
            	form();
            }else if(flag==2){
            	url = "<%=basePath%>rest/craLsBomParamManageAction/getGridByProGgxh?proGgxh="+proGgxh;
            	grid(url);
            	form();
            	$("#fo").hide();
            }
            setForm();
        });
		
		function getProduct(){
			$.ajax({
				url:'<%=basePath%>rest/sellSalesOrderManageAction/selectProGgxh',
			type : 'POST',
			dataType : 'JSON',
			async : false,
			success : function(data) {
				tempData1 = data.data;
			}
		});
	}
        
		function getMateril(){
			$.ajax({
				url:'<%=basePath%>rest/craCraftManageAction/getSelectMateril',
			type : 'POST',
			dataType : 'JSON',
			async : false,
			success : function(data) {
				debugger;
				tempData2 = data.data;
			}
		});
	}

	function setForm() {
		var bean = new Object();
		if (seqName == "拉丝") {
			bean.seqCode = 'ls';
			form.setData(bean);
		} else if (seqName == "绞线") {
			bean.seqCode = 'jx';
			form.setData(bean);
		} else if (seqName == "绝缘") {
			bean.seqCode = 'jy';
			form.setData(bean);
		} else if (seqName == "包带") {
			bean.seqCode = 'bd';
			form.setData(bean);
		} else if (seqName == "绞铁线") {
			bean.seqCode = 'jtx';
			form.setData(bean);
		} else if (seqName == "印字") {
			bean.seqCode = 'yz';
			form.setData(bean);
		} else if (seqName == "护套") {
			bean.seqCode = 'ht';
			form.setData(bean);
		} else if (seqName == "集绞") {
			bean.seqCode = 'jj';
			form.setData(bean);
		} else if (seqName == "复绕") {
			bean.seqCode = 'fr';
			form.setData(bean);
		} else if (seqName == "对绞") {
			bean.seqCode = 'dj';
			form.setData(bean);
		}
	}

	function grid() {
		grids = $("#maingrid")
				.ligerGrid(
						{
							url : url,
							title : '拉丝BOM参数',
							columns : [
									{
										display : "产品规格型号",
										name : "proGgxh",
										frozen : true,
										minWidth : 90
									},
									{
										display : "工序步骤",
										name : "seqStep",
										frozen : true
									},
									{
										display : "工序编码",
										name : "seqCode",
										frozen : true
									},
									{
										display : "材料",
										name : "mater"
									},
									{
										display : "目标直径(mm)",
										name : "targetDiameter"
									},
									{
										display : "最小直径(mm)",
										name : "minDiameter"
									},
									{
										display : "最大直径(mm)",
										name : "maxDiameter"
									},
									{
										display : "轴数",
										name : "axisNum"
									},
									{
										display : "标准线径(mm)",
										name : "standDiameter"
									},
									{
										display : "延伸率%",
										name : "elongationRate"
									},
									{
										display : "报表每小时平均产量",
										name : "apph",
										minWidth : 120
									},
									{
										display : "预计每小时产量",
										name : "ehap",
										minWidth : 100
									},
									{
										display : "调试长度",
										name : "debugLength"
									},
									{
										display : "OEE:标准重量(KG/m)",
										name : "oeeStandWeight",
										minWidth : 120
									},
									{
										display : "OEE:每轴可出货长度",
										name : "oee_slps",
										minWidth : 120
									},
									{
										display : "工作单上检查项目",
										name : "inspectionItems",
										minWidth : 100
									},
									{
										display : "备注",
										name : "remark"
									},
									{
										display : '操作',
										name : '',
										width : '5%',
										frozen : true,
										render : function(row) {
											var html = "";
											if (flag == 1) {
												html = '<a href="#" onclick="javascript:edit('
														+ row.__index
														+ ');return false;">编辑</a>';

											} else if (flag == 0) {
												html = '<a href="#" onclick="javascript:del('
														+ row.__index
														+ ');return false;">删除</a>';
											}
											return html;
										}
									}, {
										hide : "id",
										name : "id",
										width : 1
									} ],
							height : '50%',
							data : CustomersData,
							rownumbers : true
						});

		$("#pageloading").hide();
	}

	function del(row) {
		list = ListRemove(list, row);
		CustomersData.Rows = list;
		grid(CustomersData);
	}

	function edit(rows) {
		j = rows;
		$("#tj").show();
		var manager = $("#maingrid").ligerGetGridManager();
		list = manager.getData();
		var li = manager.data.Rows[rows];
		id = li.id;
		debugger;
		if (i == 0) {
			length = list.length;
			i++;
		}
		var fo = new liger.get("form2");
		fo.setData(li);
	}


	function form() {
		form = $("#form2").ligerForm({
			inputWidth : 150,
			labelWidth : 150,
			fields : [ {
				display : "产品规格型号",
				name : "proGgxh",
				newline : true,
				textField : 'proGgxh',
				type : "popup",
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
						columns : [ {
							display : '产品规格型号',
							name : 'proGgxh',
							align : 'left',
							width : 300,
							minWidth : 33
						}, ],
						sortName : 'proGgxh',
						usePager : false,
						data : tempData1
					},
				},
				validate : {
					required : true,
					minlength : 2
				}
			}, {
				display : "工序步骤",
				name : "seqStep",
				newline : false,
				comboboxName : 'scDate',
				type : "int",
				validate : {
					required : true,
					minlength : 1
				}
			}, {
				display : "工序编码",
				name : "seqCode",
				newline : false,
				comboboxName : 'cusName',
				type : "text",
				validate : {
					required : true,
					minlength : 2
				}
			}, {
				display : "材料",
				name : "mater",
				newline : true,
				textField : 'mater',
				type : "popup",
				editor : {
					selectBoxWidth : 600,
					selectBoxHeight : 300,
					textField : 'mater',
					valeuField : 'mater',
					condition : {
						fields : [ {
							label : '材料',
							name : 'mater',
							type : 'text'
						} ]
					},
					grid : {
						columns : [ {
							display : '产品规格型号',
							name : 'mater',
							align : 'left',
							width : 300,
							minWidth : 33
						}, ],
						sortName : 'mater',
						data : tempData2
					},
				},
				validate : {
					required : true,
					minlength : 2
				}
			}, {
				display : "目标直径(mm)",
				name : "targetDiameter",
				newline : false,
				comboboxName : 'agentBy',
				type : "number",
				validate : {
					required : true,
					minlength : 2
				}
			}, {
				display : "最小直径(mm)",
				name : "minDiameter",
				newline : false,
				comboboxName : 'orderCode',
				type : "number"
			}, {
				display : "最大直径(mm)",
				name : "maxDiameter",
				newline : true,
				comboboxName : 'orderCode',
				type : "number"
			}, {
				display : "轴数",
				name : "axisNum",
				newline : false,
				comboboxName : 'orderCode',
				type : "int"
			}, {
				display : "标准线径(mm)",
				name : "standDiameter",
				newline : false,
				comboboxName : 'orderCode',
				type : "number"
			}, {
				display : "延伸率%",
				name : "elongationRate",
				newline : true,
				comboboxName : 'orderCode',
				type : "text"
			}, {
				display : "报表每小时平均产量",
				name : "apph",
				newline : false,
				comboboxName : 'orderCode',
				type : "text"
			}, {
				display : "预计每小时产量",
				name : "ehap",
				newline : false,
				comboboxName : 'orderCode',
				type : "text"
			}, {
				display : "调试长度",
				name : "debugLength",
				newline : true,
				comboboxName : 'orderCode',
				type : "int"
			}, {
				display : "每公里用量(kg)",
				name : "usePerKilometer",
				newline : false,
				comboboxName : 'orderCode',
				type : "int"
			}, {
				display : "OEE:标准重量(KG/m)",
				name : "oeeStandWeight",
				newline : false,
				comboboxName : 'orderCode',
				type : "number",
				validate : {
					required : true,
					minlength : 2
				}
			}, {
				display : "OEE:每轴可出货长度",
				name : "oeeSlps",
				newline : true,
				comboboxName : 'orderCode',
				type : "int"
			}, {
				display : "工作单上检查项目",
				name : "inspectionItems",
				newline : false,
				comboboxName : 'orderCode',
				type : "text",
				width : 490
			}, {
				display : "备注",
				name : "remark",
				newline : true,
				comboboxName : 'orderCode',
				type : "text",
				width : 490
			}, ],
			validate : true
		});

		liger.get('seqCode').setDisabled(); //设置只读
	}

	function addGrid() {
		var va = form.valid();
		url = '';
		if (va) {
			var bean = form.getData();
			if (flag == 0) {
				list.push(bean);
			} else if (flag == 1) {
				bean.id = id;
				list[j] = bean;
			}
			CustomersData = {
				Rows : list
			};
			debugger;
			grid(CustomersData);
		} else {
			return;
		}
	}

	function getDatas() {
		//alert(1);
		debugger;
		return grids.getData();
	}

	function ListRemove(list, index) {
		if (isNaN(index) || index > list.length) {
			return false;
		}
		for (var i = 0, n = 0; list < this.length; i++) {
			if (list[i] != list[dx]) {
				list[n++] = list[i];
			}
		}
		list.length -= 1;
		return list;
	}
</script>
</head>
<body>
	<div class="l-loading" style="display:block" id="pageloading"></div>
	<div id="maingrid" style="margin:0; padding:0"></div>
	<div id="des"
		style="border-bottom:1px solid #B0E2FF; margin-top: 10px;">
		带"<span style="color: red">*</span>"为必填
	</div>
	<div id="fo" style="text-align: center;width: 100%;position: relative;">
		<form id="form2" action=""></form>
		<div class="liger-button" id='tj' onclick="addGrid()"
			style="float: right;position: absolute;right: 100px;margin-bottom: 1px; ">提交</div>
	</div>
	<div style="display:none;">
		<!-- g data total ttt -->
	</div>

</body>
</html>
