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
		<title></title>
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
		<script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
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
			 
			 #sartch{
			 	width:100%;
			 	height:50px;
			 }
			 #sartch input{
			 	width:150px;
			 	height:20px;
			 }
			 #sartch .time{
			 	float: left;
			 	margin-left:40px;
			 	margin-top:13px;
			 }
			 #sartch .time .st{
			 	float:left;
			 }
			 #sartch .state{
			 	float: left;
			 	margin-left:40px;
			 	margin-top:13px;
			 }
			 #sartch .state .ss{
			 	float: left;
			 }
			 #sartch .mac{
			 	float: left;
			 	margin-left:40px;
			 	margin-top:13px;
			 }
			 #sartch .repar{
			 	float: left;
			 	margin-left:40px;
			 	margin-top:13px;
			 }
			 
		</style>
			<script>
			var basePath  = '<%=basePath%>';
			var routeName = "mauMachineFaultManage";    
    	</script>
		
		<script type="text/javascript">
		var grid;
		var form;
		$(function(){
			
			$("#start").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",labelWidth: 140,labelHeight:30});
			$("#end").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",labelWidth: 140,labelHeight:30});
			
			//createForm();
			var url=basePath + "rest/" + routeName + "Action/getFaultPage";
				getDataGrid(url);
				//myload();
		});
		/*
		function createForm(){
			debugger;
			 var matels=[{id:'',text:'--请选择--'},{id:'0',text:'未维修'},{id:'1',text:'维修中'},{id:'2',text:'已完成'}];
			 form= $("#myform").ligerForm({
	 			inputWidth: 170, labelWidth: 90, space: 10,
	            fields: [
	            { name: "id", type: "hidden" },
	            { display: "故障时间", name: "startTime",editor:{showTime:true}, newline: false, type: "date",width:180}, 
	            { display: "到", name: "endTime", editor:{showTime:true},newline: false, type: "date",width:180},
	            { display: "状态", name: "status", newline: false,type: "select",comboboxName: "status", options:{data: matels},width:120,
	            	
                    
	            }, 
	            { display: "故障机台", name: "macCode", newline: false,width:180},
	            { display: "维修人员", name: "repairBy", newline: false,width:180},
	            ]
			}); 
		};
		*/
		function getDataGrid(url){
			debugger;
			window['g'] =
				grid= $("#maingrid").ligerGrid({
		            url:url,
		            height:'70%',
		            checkbox:true,
		            columns: [
		              { hide: '序号', name: 'id'},
		              { display: '故障设备', name: 'macCode',},
		              { display: '设备类型', name: 'macType'},
		              { display: '维修人员', name: 'repairBy'},
                      { display: '故障类型', name: 'faultType'},
                      { display: '故障时间', name: 'faultDate'},
                      { display: '故障描述', name: 'remark'},
                      { display: '维修完成时间', name: 'repairDate'},
                      { display: '状态', name: 'status',
                    	  
                    	  render: function (row)
                          {
                              var data = row.status;
                              debugger;
                              var html = "";
                              if(data == "未维修"){
                              	  html += "<span style='color:red'>"+ data + "</span>";
                              }else if(data == "维修中"){
                            	  html += "<span style='color:blue'>"+ data + "</span>";
                            	  
                              }else{
                            	  html += "<span>"+ data + "</span>";
                              }
                              return html;
                          },
                      },
                      
                 	],
          
            rownumbers: true,
            toolbar : {
    			items : [
    			         //{ text : '修改', click : itemclick, icon : 'modify' },
    			         { text : '导出', click : exportClick, icon : 'print', },
    			         { text : '搜索', click : searchClick, icon : 'search', },
    			         { text : '重置', click : restartClick, icon : 'back', },
    			         ]
    		},
           // autoFilter: true
        });
        $("#pageloading").hide();
};
//导出excel
function exportClick(){
	var startTime=$("input[name=startTime]").val();
	var endTime=$("input[name=endTime]").val();
	var status=$("select[name=status]").val();
	var macCode=$("input[name=macCode]").val();
	var repairBy=$("input[name=repairBy]").val();
	var data = {};
	data.startTime = startTime;
	data.endTime = endTime;
	data.status = status;
	data.macCode = macCode;
	data.repairBy = repairBy;
	var param = JSON.stringify(data);
	var url=basePath + "rest/mauMachineFaultManageAction/exprotFaultExcel?param=" + param;
	window.open(url,"_self");
}

//搜索
function searchClick(){
	debugger;
	var startTime=$("input[name=startTime]").val();
	var endTime=$("input[name=endTime]").val();
	var status=$("select[name=status]").val();
	var macCode=$("input[name=macCode]").val();
	var repairBy=$("input[name=repairBy]").val();
	var data = {};
	data.startTime = startTime;
	data.endTime = endTime;
	data.status = status;
	data.macCode = macCode;
	data.repairBy = repairBy;
	var param = JSON.stringify(data);
	var url=basePath + "rest/mauMachineFaultManageAction/getFaultPage?param=" + param;
	getDataGrid(url);
}
//重置
function restartClick(){
	window.location.reload(true);
}
function itemclick(){

	var selected = grid.getSelecteds();
	if(selected.length==0){
		$.ligerDialog.warn('您还没有选择，请选择一条修改');
		return;
	}if(selected.length>1){
		$.ligerDialog.warn('您选了多条，请选择一条修改');
	    return;
	}else{
		var id = selected[0].id;
		var status= selected[0].status;
		if(status=="已完成"){
			$.ligerDialog.warn('不能选择状态为已完成的记录');
		    return;
		}else if(status=="未维修"){
			winEdit = $.ligerDialog.open({
				url : basePath + "rest/mauMachineFaultManageAction/toAddEditPage?id=" + id,
				height : 200,
				width : 300,
				modal : true,
				title : "添加维修人员"
				
			});
		}else if(status=="维修中"){
			winEdit = $.ligerDialog.open({
				url : basePath + "rest/mauMachineFaultManageAction/toAddEditPage1?id=" + id,
				height : 300,
				width : 400,
				modal : true,
				title : "添加维修结果"
				
			});
		}
		
	}

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
			
			
		</script>
	</head>

	<body style="overflow-x:hidden; padding:2px;">
		<div class="l-loading" style="display:block" id="pageloading"></div>
		<div class="l-clear"></div>
		<div id="sartch">
			<div class="time">
				<span style="float:left;display:inline-block;">故障时间：</span>
				<div class="st">
					<input type="text" name="startTime" id="start" />
					<span>到</span>
					<input type="text" name="endTime" id="end" />
				</div>
			</div>
			<div class="state">
				<span style="float:left;display:inline-block;">状态：</span>
				<div class="ss">
					<select name="status">
						<option value="">---请选择---</option>
						<option value="未维修">未维修</option>
						<option value="维修中">维修中</option>
						<option value="已完成">已完成</option>
					</select>
				</div>
			</div>
			<div class="mac">
				<span>故障机台：</span>
				<input type="text" name="macCode"/>
			</div>
			<div class="repar">
				<span>维修人员：</span>
				<input type="text" name="repairBy"/>
			</div>
			
		</div>
		<!-- 
		<div id="myform"></div>
		 -->
		<div id="maingrid"></div>
		<div style="display:none;">
		</div>
	</body>

</html>