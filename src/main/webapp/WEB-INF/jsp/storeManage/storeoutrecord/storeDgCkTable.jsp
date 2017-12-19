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
		<title>出库单详情</title>
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
		<script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
		<%-- <link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script> --%>
		<style type="text/css">
		#nowTime{
			font-size: 20px;
			color:white;
			}
			.nav-a{
				width: 33%;
				height: 60px;
				text-align: center;
				float: left;
				
			}
			.span-a{
				font-size:20px;
				font-family: "微软雅黑";
				font-weight: 300;
				color:white;
			}
	
	
	
			.tb{
				width: 30%;
				height: 80px;
				float: right;
				text-align: right;
				line-height: 70px;
			}
			 .newSpan{
			 	text-align: center;
			 	float: left;
			 	margin-top: 20px;
			 	margin-left: 40%;
			 }
		#nowTime{
			font-size: 20px;
			color:white;
			}
			.nav-a{
				width: 33%;
				height: 60px;
				text-align: center;
				float: left;
				
			}
			.span-a{
				font-size:20px;
				font-family: "微软雅黑";
				font-weight: 300;
				color:white;
			}
	
	
	
			.tb{
				width: 30%;
				height: 80px;
				float: right;
				text-align: right;
				line-height: 150px;
			}
			 .newSpan{
			 	text-align: center;
			 	float: left;
			 	margin-top: 20px;
			 	margin-left: 40%;
			 }
		</style>
			<script>
			var basePath  = '<%=basePath%>';
			var routeName = "storeDgCkManage"; 
			
    	</script>
		
		<script type="text/javascript">
		var grid;
		var form;
		$(function(){
			var outCode=$("#hideCode").val();
			var url=basePath + "rest/" + routeName + "Action/getDetail?outCode="+outCode;
				getDataGrid(url);
				//myload();
		});
		
		
		function getDataGrid(url){
			window['g'] =
				grid= $("#maingrid").ligerGrid({
		            url:url,
		            height:'99%',
		            checkbox:true,
		            columns: [
		              { hide: '序号', name: 'id',  width: 1 },
		              { display: '出库单号', name: 'ordercode',  width:  '20%' },
		              { display: '领料单号', name: 'mmrcode',  width:  '20%' },
                      { display: '物料名称', name: 'matername',  width: '15%',editor:{type:'text'}},
                      { display: '规格型号', name: 'ggxh',  width: '10%',editor:{type:'text'}},
                      { display: '数量', name: 'amount', width: '10%',editor:{type:'text'} },
                      { display: '实际出库数量', name: 'factamount', width: '10%',editor:{type:'text'} },
                      { display: '单位', name: 'unit',  width:  '10%', editor:{type:'text'} 
                      },
          	        
                 		 ],
                 		
            rownumbers: true,
            enabledEdit:true,
            toolbar : {
    			 items : [{
    				text : '保存',
    				click : saveClick,
    				icon : 'save'
    			},{
    				text : '导出',
    				click : exportClick,
    				icon :  'print',
    			},] 
    		},
           // autoFilter: true
        });
        $("#pageloading").hide();
};		



function saveClick(){
	var manager = $("#maingrid").ligerGetGridManager();
	var li = manager.getSelectedRows();
	var dataArray = [];
	if(li.length<1){
		$.ligerDialog.warn("请选择保存的行！");
		return;
	}
	
	for(var i = 0; i<li.length;i++){
		var bean = new Object();
		var beans = li[i];
		bean.ordercode = beans.ordercode;
		bean.mmrcode = beans.mmrcode;
		bean.matername = beans.matername;
		bean.unit = beans.unit;
		bean.ggxh = beans.ggxh;
		bean.amount = beans.amount;
		bean.factamount = beans.factamount;
		bean.id = beans.id;
		dataArray.push(bean);
	}
	var data = JSON.stringify(dataArray);
	//alert(data);
	$.ajax({ 
		url:  basePath+"rest/storeDgCkManageAction/saveProductData",
   		type:"post",
   		dataType:'json',
   		data:"li="+data,
   		success: function(map){
   			debugger;
   			if(map.success){
   				$.ligerDialog.success(map.msg);
   		 		location.reload();
   			}else{
   				$.ligerDialog.error(map.msg+"保存失败");
   			}
   	    },
   	    error : function() {
   	    	$.ligerDialog.error("保存失败");
		}
	});
	
}

/* function saveClick(){

	var selected = grid.getSelecteds();
	if(selected.length==0){
		$.ligerDialog.warn('您还没有选择，请选择一条修改');
		return;
	}if(selected.length>1){
		$.ligerDialog.warn('您选了多条，请选择一条修改');
	    return;
	}
		var id = selected[0].id;
		var status= selected[0].status;
		if(status=="已完成"){
			$.ligerDialog.warn('不能选择状态为已完成的记录');
		    return;
		}else if(status=="未开始"){
			$.ligerDialog.warn('不允许修改,请删除该记录添加新计划!');
			return;
		}else if(status=="进行中"){
			winEdit = top.$.ligerDialog.open({
				url : basePath
						+ "rest/storeOutOfStorageManageAction/toAddEditPage3?id="
						+ id,
				height :300,
				width : 400,
				modal : true,
				title : "编辑维护记录"
			});
		}
		
	

} */


function  exportClick(){
	//导出excel表
	
	var order=$("[name=order]").val();
	var jsonQuery="order="+order;
	var url=basePath + "rest/" + routeName + "Action/exprotDetailExcel"+"?" + jsonQuery;
	window.open(url,"_self");
	
}
function myload(){
  	var basePath = "<%=basePath%>";
		var urlPirfex = basePath.substring(7, basePath.length);
		var url = "ws://"+urlPirfex+"storeCrewWebS";
	
		var webSocket = new WebSocket(url);
		webSocket.onerror = function(event) {
			onError(event);
		};
	
		webSocket.onopen = function(event) {
			onOpen(event);
		};
	
		webSocket.onmessage = function(event) {
			onMessage(event);
		};
	
		function onMessage(event) {
			var jsonBean = eval('('+event.data+')');
			if(jsonBean){
				$("#maingrid").ligerGrid({}).reload();
			}
			
		}
		function onOpen(event) { 
			webSocket.send("true");
		}
	
		function onError(event) {
		}
  }
setInterval(function(){
	setTime();
},1000);

function setTime(){
	var date = new Date();
	var dates=date.pattern("yyyy-MM-dd EEE hh:mm:ss");
	$("#nowTime").html(dates);
}
	
			
		</script>
	</head>

	<body style="overflow-x:hidden; padding:2px;">
	<div  style="width: 100%;height: 70px;background: rgba(33, 138, 217, 1);float:left;">
			<div class="newSpan">
				<span style="font-size: 30px;color: white;font-family: 'arial, helvetica, sans-serif';font-weight: 300;">
					出&nbsp;&nbsp;库&nbsp;&nbsp;单&nbsp;&nbsp;明&nbsp;&nbsp;细&nbsp;&nbsp;管&nbsp;&nbsp;理
				</span>
				
			</div>
		</div>
		<div class="l-loading" style="display:block" id="pageloading"></div>
		<div class="l-clear"  style="margin-top: 60px;"></div>
		<div id="myform" ></div>
		<input type="hidden"  value="${outCode}"  id="hideCode" />
		<div id="maingrid" ></div>
		<div style="display:none;">
		</div>
	</body>

</html>