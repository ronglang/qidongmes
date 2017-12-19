<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>轴</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%= basePath%>app/js/sellManage/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />  
    <link rel="stylesheet" type="text/css" id="mylink"/>   
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%= basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>    
    <script src="<%= basePath%>app/js/sellManage/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>  

	<script type="text/javascript">
		var grid;
		
		$(function(){
			setForm();
			queryTable();
		});
	
		function queryTable(){
			var param = initParam();
			debugger;
			grid = $("#myGrid").ligerGrid({
				title : "订单明细列表",
				url : "<%= basePath%>rest/mauAxisManageAction/getPageList?param="+param,
				checkbox : true,
				rownumbersColWidth : 60,
				height : 600,
				usePager : true,
				columns : [
				{
					display : '线盘名称',
					name : 'axisName'
				}, {
					display : '轴颜色',
					name : 'axisColor'
				}, {
					display : '外盘径',
					name : 'externalDiameter'
				}, {
					display : '内盘径',
					name : 'internalDiameter'
				}, {
					display : '轴内宽',
					name : 'axisInWidth'
				}, {
					display : '轴外宽',
					name : 'axisOutWidth'
				}, {
					display : '中心孔直径',
					name : 'centerDiameter'
				},
				 {
					display : '是否绑定rfid',
					name : 'rfid',
					render:function(row){
						var html = "";
						if (row.rfid =="" || row.rfid == null) {
							return "未绑定";
						}else{
							return "已绑定";
						}
					}
				},{
					hide : 'id',
					name : 'id',
					width:1
				} ],
				 groupColumnName: 'axisName', 
	                groupRender: function (axisName,groupdata)
	                {
	                    return '线盘名称 ' + axisName + '(: 数目=' + groupdata.length + ')';
	                },
				toolbar : {
					items : [ {
						text : "添加",
						click : insert,
						icon : "add"
					} ,{
						text : "修改",
						click : update,
						icon : "add"
					} ,{
						text : "删除",
						click : delBean,
						icon : "add"
					},{
						text : "绑定rfid",
						click : scanRfid,
						icon : "add"
					}  ]
				}
			});
		}
		var ligerDialogOpen ;
		function insert(){
			 ligerDialogOpen = $.ligerDialog.open({
	 				title:"添加",
	 				url:"<%=basePath %>rest/mauAxisManageAction/toAddOrEdit",
	 				type:'question' ,
	 				width :1000,
	 				height:400,
	 				buttons :[
	 				          {
	 					        	 text:"保存",onclick:function(i,d){
	 					        		d.frame.save();
	 					        		grid.reload() ;
	 					        	 }
	 					          },
	 					         
	 					         { 
	 					        	 text: '取消', onclick: function(i,d){ ligerDialogOpen.close();;}
	 					         }
	 					        
	 				]
	 			});
		}
		
		function update(){
			
		}
		
		function delBean(){
			var data = grid.getData();
			if (data.length == 0) {
				$.ligerDialog.warn("请选择一行进行删除");
				return;
			}
			
			var rowId = data[0].id;
			var url = "<%=basePath %>rest/mauAxisManageAction/clearBean";
			$.post(url,{id:rowId},function(data){
				if(data.success){
					$.ligerDialog.success(data.msg);
					grid.reload();
				}else{
					$.ligerDialog.error(data.msg);
				}
			},"json");
		}
		
		function initParam(){
			var bean = {};
			var axisName = $("[name=axisName]").val();
			if (axisName != null || axisName != "") {
				bean.axisName = axisName;
			}
			var se = JSON.stringify(bean);
			return se;
		}
		
		function setForm(){
			seForm = $("#myForm").ligerForm({
 		        inputWidth: 120, labelWidth: 80, space:10,
 		        fields: [
 		        //	{ display: "是否已分解", name: "genFlag",  newline:true,type: "select" ,options: { data: flagData}},
 		        //	{ display: "交货日期:从", name: "start",  newline:false,type: "date",editor:{showTime:true,format:"yyyy-MM-dd"} },
 		        	{ display: "轴名称", name: "axisName", newline:false, type: "text"},
 		        //	{ display: "订单编号", name: "orderCode", newline:false, type: "text"},
 		        ],	
 		    });	
		}
		
		var flag = false;
		var rfidCode;
		//为绑定rfid 做准备
		setInterval(function(){
			if (flag) {
				var url = "<%=basePath %>rest/mauAxisManageAction/getRfidCode";
				$.post(url,{},function(data){
					if(data.success){
						rfidCode = data.data;
						flag = false;
					}
				},"json");				
			}
			
		}, 1000);
		
		//扫描绑定
		function scanRfid(){
			flag = true;
			var data = grid.getData();
			if (data.length != 0) {
				$.ligerDialog.warn("请选择一行进行删除");
				return;
			}
			
			var rowId = data[0].id;
			var url = "<%=basePath %>rest/mauAxisManageAction/bingdRfid";
			$.post(url,{id:rowId,rfid:rfidCode},function(data){
				if(data.success){
					$.ligerDialog.success(data.msg);
					grid.reload();
					flag = false;
				}else{
					$.ligerDialog.error(data.msg);
				}
			},"json");
			
		}
	</script>
  </head>
  
  <body>
  	<!-- 查询条件,此处暂时不写代码 -->
	<div id="fo" style="text-align: center;width: 100%;position: relative;"  >
		<form id="myForm" action="" >
		</form>
		<div class="liger-button" id = 'tj' onclick="queryTable()" style="float: right;position: absolute;left: 200px;top: 1px; ">提交</div>
	</div>
    <div id="myGrid"></div>
  </body>
</html>
