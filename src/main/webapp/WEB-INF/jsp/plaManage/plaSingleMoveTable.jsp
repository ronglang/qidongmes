<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String plaSingleMove_id = request.getParameter("id");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title></title>
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerForm.js"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerComboBox.js"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerCheckBoxList.js"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerListBox.js"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script type="text/javascript">
	var id = <%=plaSingleMove_id%>;
	var manager,url;
	var list = [];
	var list1 = [];
	var batCode,mainId,proGgxh,color,partlen;
	var grids, grids1,form,form1;
	var CusData = {
		Rows : list
	};
	var CusData1 = {
		Rows : list1
	};
	$(function() {
		
		$.ajax({
			url:'<%=basePath%>rest/plaSingleMoveManageAction/getSellContractPlanBatchById?id='+id,
			type:'POST',
			dataType:'JSON',
			success:function(sell){
				debugger;
				proGgxh = sell.proGgxh;
				batCode = sell.batCode;
				mainId = sell.mainId;
				partlen = sell.reqPeriodLength;
				color = sell.proColor;
				url = '<%=basePath%>rest/plaSingleMoveManageAction/getplaSingleMove';
				grid(url, CusData);
				grid1();
				form();
				var bean = new Object();
				bean.proGgxh = proGgxh;
				bean.partlen = partlen;
				bean.color = color;
				bean.batCode = batCode;
				forms();
				form1.setData(bean);
				forms4();
			}
			
		});
		

	});
	

	function grid(url, CusData) {

		grids = $("#maingrid").ligerGrid({
			height : '85%',
			url : url,
			checkbox : true,
			columns : [ {
				display : '工作单号',
				name : 'wscode',
				width : '15%'
			}, {
				display : '规格型号',
				name : 'proggxh',
				width : '20%'
			}, {
				display : '颜色',
				name : 'color',
				width : '6%'
			}, {
				display : '批次号',
				name : 'batcode',
				width : '20%'
			}, {
				display : '生产段长',
				name : 'partlen',
				width : '24%'
			}, {
				display : '轴数',
				name : 'acount',
				width : '5%'
			}, {
				display : '*挪动轴数',
				name : 'amount',
				width : '8%',
				editor : {
					type : 'digits'
				}
			},{
				hide : '工作单ID',
				name : 'id',
				width : 1,
			} ],
			rownumbers : true,
			enabledEdit : true,
			data : CusData
		});

		$("#pageloading").hide();

	}

	function grid1() {

		grids1 = $("#maingrid1").ligerGrid({
			height : '90%',
			checkbox : true,
			columns : [ {
				display : '工作单号',
				name : 'wscode',
				width : '15%'
			}, {
				display : '规格型号',
				name : 'proggxh',
				width : '20%'
			}, {
				display : '颜色',
				name : 'color',
				width : '6%'
			}, {
				display : '批次号',
				name : 'batcode',
				width : '20%'
			}, {
				display : '生产段长',
				name : 'partlen',
				width : '24%'
			}, {
				display : '轴数',
				name : 'amount',
				width : '5%'
			}, {
				display : '操作',
				name : 'amount',
				width : '9%',
				render:function(row,idx){
            		return '<a href="" onClick=\"javascript:del(\''+row.__index+'\'); return false;\">删除'+'</a>';
            	}
			},{
				hide : '工作单ID',
				name : 'id',
				width : 1,
			}],
			rownumbers : true,
			usePager : false,
			data : CusData1
		});

		$("#pageloading").hide();

	}
	
	function grid2() {

		grids2 = $("#maingrid2").ligerGrid({
			height : '40%',
			checkbox : true,
			columns : [ {
				display : '工作单号',
				name : 'wscode',
				width : '15%'
			}, {
				display : '规格型号',
				name : 'proggxh',
				width : '20%'
			}, {
				display : '颜色',
				name : 'color',
				width : '6%'
			}, {
				display : '批次号',
				name : 'batcode',
				width : '20%'
			}, {
				display : '生产段长',
				name : 'partlen',
				width : '23%'
			}, {
				display : '交货日期',
				name : 'damandDate',
				width : '8%',
				editor : {
					type : 'date'
				}
			}, {
				display : '补单轴数',
				name : 'amount',
				width : '5%',
				editor : {
					type : 'digits'
				}
			},{
				hide : '工作单ID',
				name : 'id',
				width : 1,
			} ],
			rownumbers : true,
			usePager : false,
			enabledEdit : true,
			data : CusData1
		});

		$("#pageloading").hide();

	}

	function del(index){
    	grids1.remove(index);
	    var manager = $("#maingrid1").ligerGetGridManager(); 
	    manager.deleteRow($("tr.l-grid-row",manager.gridbody)[index]);
    	var aa =  grids1.getData();
    	list1 = aa;
    	CusData1 = {
    			Rows : list1
    		};
    	grid2(CusData1);	
	}
	
	function move() {
		manager = $("#maingrid").ligerGetGridManager();
		var li = manager.getSelectedRows();
		var lis = manager.getData();
		if (li.length != 1) {
			$.ligerDialog.warn("请选择一条需要挪单的工作单");
			return;
		}
		if (li[0].amount == "" || li[0].amount == null) {
			$.ligerDialog.warn("请输入挪动轴数");
			return;
		}
		if(li[0].proggxh != proGgxh || li[0].color != color || li[0].partlen != partlen ){
			$.ligerDialog.warn("挪单参数与目标批次参数不一致，不能挪单");
			return;
		}
		if (li[0].acount < li[0].amount) {
			$.ligerDialog.warn("挪动数量不能超过当前数量");
			return;
		} else {
			li[0].acount = li[0].acount - li[0].amount;
			for (var k = 0; k < lis.length; k++) {
				if (li[0].proggxh == lis[k].proggxh
						&& li[0].procolor == lis[k].procolor
						&& li[0].partlen == lis[k].partlen
						&& li[0].wscode == lis[k].wscode
						&& li[0].batcode == lis[k].batcode) {
					lis[k].acount = li[0].acount;
				}
			}
			if (list1.length < 1) {
				list1.push(li[0]);
			} else {
				var flag = true;
				for (var j = 0; j < list1.length; j++) {
					var aa = 0;
					if (li[0].proggxh == list1[j].proggxh
							&& li[0].procolor == list1[j].procolor
							&& li[0].partlen == list1[j].partlen
							&& li[0].wscode == list1[j].wscode
							&& li[0].batcode == list1[j].batcode) {
						aa = parseInt(list1[j].amount) + parseInt(li[0].amount);
						list1[j].amount = aa;
						flag = false;
						break;
					}
				}
				if (flag) {
					list1.push(li[0]);
				}

			}
		}

		CusData = {
			Rows : lis
		};
		grid("", CusData);
		CusData1 = {
			Rows : list1
		};
		grid1(CusData1);
		grid2(CusData1);
	}
	
	function form(){
    	
      	 //创建表单结构 
          form = $("#form2").ligerForm({
              inputWidth: 200, labelWidth: 65, space: 100,
              fields: [
              	{ display: "工作单号", name: "wscode", newline: false, type: "select",editor: {url:'<%=basePath%>rest/plaSingleMoveManageAction/getWsCode',valueField:'text'}},
              ],
              validate  : true
          });
      	
      }
	
	
	function forms(){
    	
     	 //创建表单结构 
         form1 = $("#form3").ligerForm({
             inputWidth: 150, labelWidth: 90, space: 100,
             fields: [
             	{ display: "目标批次", name: "batCode", newline: false, type: "text"},
             	{ display: "目标规格型号", name: "proGgxh", newline: false, type: "text"},
             	{ display: "目标颜色", name: "color", newline: true, type: "text"},
             	{ display: "目标段长", name: "partlen", newline: false, type: "text"}
             ],
             validate  : true
         });
         liger.get('batCode').setDisabled(); //设置只读
         liger.get('proGgxh').setDisabled(); //设置只读
         liger.get('color').setDisabled(); //设置只读
         liger.get('partlen').setDisabled(); //设置只读
     }
	
	
	function forms4(){
    	
    	 //创建表单结构 
        form2 = $("#form4").ligerForm({
            inputWidth: 200, labelWidth: 90, space: 150,
            fields: [
            	{ hide: "", name: "batCode", newline: false, type: "hide"},
            	{ display: "补单轴数", name: "headZzds", newline: false, type: "digits"},
            	{ display: "交货日期", name: "demandDate", newline: false, type: "date"},
            	{ hide: "", name: "partlen", newline: false, type: "hide"}
            ],
            validate  : true
        });
    }
	
	
	function search(){
		var wsCode = $("[name=wscode]").val();
		url = "<%=basePath%>rest/plaSingleMoveManageAction/getplaSingleMove?wsCode="+wsCode;
		grid(url);
	}
	
	
	function suerMove() {
		//alert("确认挪单");
		manager = $("#maingrid1").ligerGetGridManager();
		var li = manager.getData();
		var manager2 = $("#maingrid2").ligerGetGridManager();
		var li2 = manager2.getData();
		var datali = JSON.stringify(li); //目标挪单信息
		var datali2 = JSON.stringify(li2); //目标挪单信息
		/* alert(datali);
		alert(datali2);
		return; */
		if(li.length<1){
			$.ligerDialog.warn("请先进行挪单");
			return;
		}else{
			var flag = false;
			for(var i=0;i<li2.length;i++){
				if(!li2[i].damandDate){
					flag = true;
				}
			}
			if(flag){
				$.ligerDialog.confirm('不填写补单数据则不会补单,是否继续', function (yes) {
					if(yes){
						$.ajax({
							url:'<%=basePath%>rest/plaSingleMoveManageAction/singleMove?datali='
								+ encodeURIComponent(encodeURIComponent(datali))+ '&id=' + id,
								type : 'POST',
								dataType : 'JSON',
								success : function(map) {
									if (map.success) {
										$.ligerDialog.success(map.msg,function(yes){
											location.reload();
										});
									}
								},
								error : function(map) {
									$.ligerDialog.error("发生了未知错误!");
								}
						});
					}
				});
			}else{
				$.ajax({
					url:'<%=basePath%>rest/plaSingleMoveManageAction/singleMove?datali='
						+ encodeURIComponent(encodeURIComponent(datali))+ '&id=' + id +'&datali2='
						+ encodeURIComponent(encodeURIComponent(datali2)),
						type : 'POST',
						dataType : 'JSON',
						success : function(map) {
							if (map.success) {
								$.ligerDialog.success(map.msg,function(yes){
									location.reload();
								});
							}
						},
						error : function(map) {
							$.ligerDialog.error("发生了未知错误!");
						}
				});
			}
		}
	}
</script>
</head>

<body style="padding:4px">
	<div
		style="width: 47%;border: 1px solid cornflowerblue;float: left;height: 700px;">
		<div
			style="width: 100%;height: 50px;background-color: #63B8FF;text-align: center; ">
			<div
				style="padding-top: 5px;font-size: xx-large;color: white;font-family: monospace;">
				准备挪用工单选项</div>
		</div>
		<div style="text-align: center;width: 100%;position: relative;;">
			<form id="form2" action=""></form>
			<div class="liger-button" onclick="search()"
				style="float: right;position: absolute;right: 200px;top: 5px; ">查询</div>
		</div>
		<div id="maingrid"></div>
	</div>
	<div style="float: right;position: absolute;right: 48%;top: 200px; ">
		<span style="color: red;">提示：</br>请选择需要</br>挪单的工单</br>后点击确认</br>挪单
		</span>
	</div>
	<div class="liger-button" onclick="move()"
		style="float: right;position: absolute;right: 48%;top: 300px; ">挪单>></div>
	<div class="liger-button" onclick="suerMove()"
		style="float: right;position: absolute;right: 48%;top: 350px; ">确认挪单</div>
	<div
		style="width: 47%;border: 1px solid cornflowerblue;float: right;height: 700px;">
		<div
			style="width: 100%;height: 50px;background-color: #63B8FF;text-align: center;">
			<div
				style="padding-top: 5px;font-size: xx-large;color: white;font-family: monospace;">
				目标挪单数据</div>
		</div>
		<div style="text-align: center;width: 100%;position: relative;;">
			<form id="form3" action=""></form>
		</div>
		<div id="maingrid1" style="margin-top: 5px"></div>
	</div>
	<div
		style="width: 99.9%;border: 1px solid cornflowerblue;float: right;height: 400px;margin-top: 10px">
		<div
			style="width: 100%;height: 20px;background-color: #63B8FF;text-align: center;">
			<div
				style="padding-top: 2px;font-size: large;color: white;font-family: monospace;">
				补单数据(不填写则默认不补单)</div>
		</div>
		<div id="maingrid2" style="margin-top: 5px;width: 99%"></div>
	</div>
</body>

</html>

