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
		<title>维护保养计划管理</title>
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
			#overmain{
				font-size: 13px;
				color:green;
			}
			#overmain:hover{
				color:red;
			}
			#startmain{
				font-size: 13px;
				color:blue ;
			}
			#startmain:hover{
				color:red;
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
			//createForm();
			
			$("#add select[name='macType']").change(function(){
			    var macType = $(this).val();
			    if(macType == ""){
			    	$("#add select[name='macCode']").html("");
			    	var hql = "<option value = ''>---请选择---</option>";
	    			$("#add select[name='macCode']").append(hql);
			    	return ;
			    }
			    $.ajax({
			    	url:basePath + "rest/mauMachineFaultManageAction/getMachineInfo?macType="+macType,
			    	type: 'POST',
			    	dataType: 'json',
			    	success:function(data){
			    		$("#add select[name='macCode']").html("");
			    		for(var i=0;i<data.length;i++){
			    			var hql = "<option value = '"+data[i]+"'>"+data[i]+"</option>";
			    			$("#add select[name='macCode']").append(hql);
			    		}
			    		
			    	},
			    	error:function(){
			    		$.ligerDialog.error("服务器出错！");
			    	}
			    	
			    });
			    
			});
			
			
			$("input[name='startTimeOne']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",width: 171,height:24});
			$("input[name='startTimeTwo']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",width: 171,height:24});
			$("input[name='endTimeOne']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",width: 171,height:24});
			$("input[name='endTimeTwo']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",width: 171,height:24});
			$("input[name='startTime']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",width: 171,height:24});
			
			var url=basePath + "rest/mauMachineFaultManageAction/getMaintainPage?1=1";
			//	getDataGrid(url);
				//myload();
				
			
			//window['g'] =
			grid= $("#maingrid").ligerGrid({
			            url:url,
			            height:'80%',
			            checkbox:true,
			            enabledEdit: true,
			            title:'设备保养计划管理',
			            columns: [
			              { hide: '序号', name: 'id',  width: 1 },
			              { display: '保养设备', name: 'macCode'},
			              { display: '设备类型', name: 'macType'},
			                    { display: '计划开始保养时间', name: 'startMaintainDate'},
			                    { display: '实际开始保养时间', name: 'factMaintainDate'},
			                    { display: '结束保养时间', name: 'endMaintainDate'},
			                    { display: '保养人员', name: 'maintainBy'},
			                    { display: '状态', name: 'status',
			                  	  render: function (row)
			                        {
			                            var data = row.status;
			                            debugger;
			                            var html = "";
			                            if(data == "未开始"){
			                            	  html += "<span style='color:red'>"+ data + "</span>";
			                            }else if(data == "保养中"){
			                          	  html += "<span style='color:blue'>"+ data + "</span>";
			                          	  
			                            }else{
			                          	  html += "<span>"+ data + "</span>";
			                            }
			                            return html;
			                        },  
			                    },
			                    { display: '保养备注', name: 'remark'},
			                    { display: '操作',
			                  	  render: function (row)
			                         {
			                         var data = row.status;
			                         debugger;
			                         var html = "";
			                         if(data == "保养中"){
			                       	  html += "<a href='javascript:' id='overmain' onclick='overmaintain("+row.id+")' >点击完成保养</a>";
			                         }else if(data == "未开始"){
			                       	  html += "<a href='javascript:' id='startmain' onclick='startmaintain("+row.id+")' >点击开始保养</a>";
			                         }
			                         return html;
			                    	  },  
			                    }
			                    
			               	],
			        
			          rownumbers: true,
			          toolbar : {
			  			items : [{ text : '增加', click : addClick, icon : 'add'},
			  			{text : '修改',click : updateClick,icon : 'modify'},
			  			{text : '删除',click : deleteClick,icon : 'delete'},
			  			{ line: true },
			  			{text : '导出',click : exportClick,icon :  'print'},
			  			{ line: true },
			  			{text : '搜索',click : searchClick,icon : 'search'},
			  			{text : '重置',click : restartClick,icon : 'back'},
			  			]
			  		}
			         // autoFilter: true
			      });
			      $("#pageloading").hide();
		});

function getDataGrid(url,data){
	grid.set({'url':url});
	if(data){
		var p = '&param='+JSON.stringify(data);
		grid.loadServerData(decodeURIComponent(p,true));
		grid.setParm('startTimeOne',data.startTimeOne);
		grid.setParm('startTimeTwo',data.startTimeTwo);
		grid.setParm('endTimeOne',data.endTimeOne);
		grid.setParm('endTimeTwo',data.endTimeTwo);
		grid.setParm('startTimeOne',data.startTimeOne);
		grid.setParm('macname',data.macname);
		grid.setParm('state',data.state);
	}
	else{
		grid.loadServerData();
	}
	grid.changePage("first");
};		

function searchClick(){
	var startTimeOne = $("input[name='startTimeOne']").val();
	var startTimeTwo = $("input[name='startTimeTwo']").val();
	var endTimeOne = $("input[name='endTimeOne']").val();
	var endTimeTwo = $("input[name='endTimeTwo']").val();
	var macname = $("input[name='macname']").val();
	var state = $("select[name='state']").val();
	var data = {};
	debugger;
	data.startTimeOne = startTimeOne;
	data.startTimeTwo = startTimeTwo;
	data.endTimeOne = endTimeOne;
	data.endTimeTwo = endTimeTwo;
	data.macname = macname;
	data.state = state;
	var param = JSON.stringify(data);
	var url=basePath + "rest/mauMachineFaultManageAction/getMaintainPage";
	getDataGrid(url,data);
}

function restartClick(){
	
	window.location.reload(true);
}

//点击添加
function addClick(){
	
	winEdit = $.ligerDialog.open({
		title : "添加保养计划",
		target:$("#add"),
		height : 250,
		width : 400,
		modal : true,
		buttons: [  { text: '添加', onclick: function (i, d) {saveData(d);}},
                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
                 ] 
	});
	
}

//保存添加的数据
function saveData(d){
	
	var macCode = $("#add select[name='macCode']").val();
	var startTime = $("#add input[name='startTime']").val();
	var macType = $("#add select[name='macType']").val();
	var nowTime = Date.parse(new Date());
	var startTimes = Date.parse(startTime);
	
	if(startTimes < nowTime){
		$.ligerDialog.warn("计划时间填写错误，不得小于当前时间！");
		return;
	}
	if(macCode == "" || startTime == "" || macType == ""){
		$.ligerDialog.warn("信息不能为空！");
		return;
	}
	var data = {};
	data.macCode = macCode;
	data.startTime = startTime;
	data.macType = macType;
	var param = JSON.stringify(data);
	$.ajax({
		type: 'POST',
		dataType: 'json',
		url: basePath + "rest/mauMachineFaultManageAction/addMaintainData?param="+param,
		success: function(info){
			if(info.success != "" && info.success!=null){
				$.ligerDialog.success(info.success);
				d.hide();
				var url=basePath + "rest/mauMachineFaultManageAction/getMaintainPage";
				getDataGrid(url);
			}else if(info.error != "" && info.error!=null){
				$.ligerDialog.error(info.error);
			}else{
				$.ligerDialog.error("服务器出错");
			}
		},
		errro:function(){
			$.ligerDialog.error("服务器出错！");
		}
		
	});
	
	
}

//点击修改
function updateClick(){
	
	var selected = grid.getSelecteds();
	if(selected.length != 1){
		$.ligerDialog.warn('请选择一条数据修改');
		return;
	}
	var status= selected[0].status;
	var id = selected[0].id;
	if(status != "未开始"){
		$.ligerDialog.warn('只能修改状态为未开始的保养记录！');
	    return;
	}
	
	$("input[name='macCode']").val(selected[0].macCode);
	$("input[name='startTime']").val(selected[0].startMaintainDate);
	$("select[name='macType']").val(selected[0].macType);
	
	$.ligerDialog.open({
		//url : basePath + "rest/mauMachineFaultManageAction/toAddEditPage3?id=" + id,
		title : "修改保养记录",
		target:$("#add"),
		height : 250,
		width : 400,
		modal : true,
		buttons: [  { text: '修改', onclick: function (i, d) { updateData(d,id); }},
                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
                 ]
		
	});
	
}

//保存修改后的数据
function updateData(d,id){
	var macCode = $("input[name='macCode']").val();
	var startTime = $("input[name='startTime']").val();
	var macType = $("select[name='macType']").val();
	if(macCode == "" || startTime == "" || macType==""){
		$.ligerDialog.warn("信息不能为空！");
	}
	var data = {};
	data.id = id;
	data.macCode = macCode;
	data.startTime = startTime;
	data.macType = macType;
	var param = JSON.stringify(data);
	$.ajax({
		url: basePath + "rest/mauMachineFaultManageAction/updateMachineMaintain?param=" + param,
		type: 'POST',
		dataType: 'json',
		success:function(info){
			if(info.success != "" && info.success!=null){
				$.ligerDialog.success(info.success);
				d.hide();
				var url=basePath + "rest/mauMachineFaultManageAction/getMaintainPage";
				getDataGrid(url);
			}else if(info.error != "" && info.error!=null){
				$.ligerDialog.error(info.error);
			}else{
				$.ligerDialog.error("服务器出错");
			}
		},
		error:function(){
			$.ligerDialog.error("服务器出错");
		}
		
	});
	
	
}



//点击删除
function deleteClick(){

	var selected = grid.getSelecteds();
	if(selected.length==0){
		$.ligerDialog.warn('请选择需要删除的数据');
		return;
	}
	
	var strIds = [];
	
	for (var i = 0; i < selected.length; i++) {
		var status= selected[i].status;
		if(status == "保养中"){
			$.ligerDialog.warn('不能删除状态为保养中的保养计划');
		    return;
		}
		var obj = new Object();
		obj.id = selected[i].id;
		strIds.push(obj);
	}
	debugger;
	var param = JSON.stringify(strIds);
	$.ligerDialog.confirm( "您确认要删掉这<font color=red>" + selected.length + "</font>条数据吗？",
		function(result) {
			if (result == true) {
				$.ajax({
						url : basePath + "rest/mauMachineFaultManageAction/toDelete?param="+param,
						type : "post",
						dataType : "json",
						success : function(info) {
							debugger;
							if(info.success != "" && info.success != null){
								$.ligerDialog.close(); //关闭弹出窗
								parent.$(".l-dialog,.l-window-mask").remove(); //关闭遮罩层 */
								var url=basePath + "rest/mauMachineFaultManageAction/getMaintainPage";
								getDataGrid(url);
								$.ligerDialog.success(info.success);
							}else if(info.error != "" && info.error != null){
								$.ligerDialog.error(info.error);
							}else{
								$.ligerDialog.error("服务器出错");
							}
						},
						error : function(map) {
							$.ligerDialog.error("服务器出错");
						}
					});
			} else {
				return;
			}
		});
}

//完成保养
function overmaintain(rowindex){
	
	$.ligerDialog.open({
		title : "添加完成保养信息",
		target:$("#over"),
		height : 250,
		width : 400,
		modal : true,
		buttons: [  { text: '提交', onclick: function (i, d) { saveOverData(d,rowindex); }},
                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
                 ]
		
	});
}

//保存完成保养得数据信息
function saveOverData(d,id){
	var maintainBy = $("#over input[name='maintainBy']").val();
	var remark = $("#over textarea").val();
	debugger;
	if(maintainBy == "" || remark == ""){
		$.ligerDialog.warn("保养人员和备注信息不能为空！");
		return;
	}
	var data = {};
	data.id = id;
	data.maintainBy = maintainBy;
	data.remark = remark;
	var param = JSON.stringify(data);
	$.ajax({
		url: basePath + "rest/mauMachineFaultManageAction/toSaveOverData?param="+param,
		type: "POST",
		dataType: "json",
		success: function(info){
			if(info.success != "" && info.success != null){
				d.hide();
				var url=basePath + "rest/mauMachineFaultManageAction/getMaintainPage";
				getDataGrid(url);
				$.ligerDialog.success(info.success);
			}else if(info.error != "" && info.error != null){
				$.ligerDialog.error(info.error);
			}else{
				$.ligerDialog.error("服务器出错!");
			}
		},
		error:function(){
			$.ligerDialog.error("服务器出错");
		}
	});
	
}

//点击开始保养
function startmaintain(id){
	
	var data = {};
	data.id = id;
	var param = JSON.stringify(data);
	$.ajax({
		url: basePath + "rest/mauMachineFaultManageAction/toSaveStartData?param="+param,
		type: "POST",
		dataType: "json",
		success: function(info){
			debugger;
			if(info.success != "" && info.success != null){
				var url=basePath + "rest/mauMachineFaultManageAction/getMaintainPage";
				getDataGrid(url);
				$.ligerDialog.success(info.success);
			}else if(info.error != "" && info.error != null){
				$.ligerDialog.error(info.error);
			}else{
				$.ligerDialog.error("服务器出错!");
			}
		},
		error:function(){
			$.ligerDialog.error("服务器出错");
		}
	});
	
}

//点击导出excel
function  exportClick(){
	//导出excel表
	
	var startTimeOne = $("input[name='startTimeOne']").val();
	var startTimeTwo = $("input[name='startTimeTwo']").val();
	var endTimeOne = $("input[name='endTimeOne']").val();
	var endTimeTwo = $("input[name='endTimeTwo']").val();
	var macname = $("input[name='macname']").val();
	var state = $("select[name='state']").val();
	var data = {};
	data.startTimeOne = startTimeOne;
	data.startTimeTwo = startTimeTwo;
	data.endTimeOne = endTimeOne;
	data.endTimeTwo = endTimeTwo;
	data.macname = macname;
	data.state = state;
	var param = JSON.stringify(data);
	
	var url=basePath + "rest/mauMachineFaultManageAction/exprotMaintainExcel?param=" + param;
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
			
			
		</script>
		
		<style type="text/css">
			#sort{
				width:100%;
				height:80px;
				font-size: 14px;
			}
			#sort span{
				display: inline-block;
				width:100px;
				text-align: right;
			}
			#sort input{
				width:170px;
				height:20px;
			}
			#sort .starttime{
				height:40px;
				float: left;
			}
			#sort .endtime{
				height:40px;
				float: left;
				margin-left:50px;
			}
			#sort .other{
				height:40px;
				clear: both;
			}
			
			/*添加保养计划的样式  */
			#add{
				display: none;
				width:100%;
				margin:20px auto;
			}
			#add table{
				width:100%;
			}
			#add table tr{
				height:35px;
			}
			#add table tr td span{
				display: inline-block;
				width:120px;
				height:35px;
				line-height: 35px;
				text-align: right;
			}
			#add table tr td input{
				height:22px;
				width:170px;
			}
			/*完成保养 */
			#over{
				margin:5px auto;
				width:100%;
				font-size: 13px;
				display: none;
			}
			#over label{
				display: block;
				margin-top:10px;
			}
			#over label textarea{
				height:80px;
				width:170px;
			}
			#over input{
				height:22px;
				width:170px;
			}
			#over span{
				display: inline-block;
				width:100px;
				height:25px;
				line-height: 25px;
				text-align: right;
			}
		</style>
	</head>

	<body style="overflow-x:hidden; padding:2px;">
	
		<div class="l-loading" style="display:block" id="pageloading"></div>
		<div class="l-clear"  style="margin-top: 0px;"></div>
		<!-- 
		<div id="myform" style="margin:5px auto;width:90%"></div>
		 -->
		 <div id="sort">
		 	<div class="starttime">
		 		<span>开始保养时间：</span>
		 		<input type="text" name="startTimeOne" />
		 		<span style="width:20px;text-align: center;">到</span>
		 		<input type="text" name="startTimeTwo"/>
		 	</div>
		 	<div class="endtime">
		 		<span>结束保养时间：</span>
		 		<input type="text" name="endTimeOne" />
		 		<span style="width:20px;text-align: center;">到</span>
		 		<input type="text" name="endTimeTwo"/>
		 	</div>
		 	<div class="other">
		 		<label>
		 			<span>保养设备：</span>
		 			<input type="text" name="macname" />
		 		</label>
		 		<label>
		 			<span>保养状态：</span>
		 			<select name="state">
		 				<option value = "">---请选择---</option>
		 				<option value = "未开始">未开始</option>
		 				<option value = "保养中">保养中</option>
		 				<option value = "已完成">已完成</option>
		 			</select>
		 		</label>
		 	</div>
		 </div>
		 
		 
		<div id="maingrid" ></div>
		<div style="display:none;">
		</div>
		
		<!-- 添加保养 -->
		
		<div id="add">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<span>设备类型：</span>
						<select name="macType">
			 				<option value = "">---请选择---</option>
			 				<option value = "叉车">叉车</option>
			 				<option value = "机台">机台</option>
			 			</select>
					</td>
				</tr>
				<tr>
					<td>
						<span>保养设备：</span>
						<select name="macCode">
							
						</select>	
					</td>
				</tr>
				<tr>
					<td>
						<span>保养计划开始时间：</span>
						<input type="text" name="startTime" />	
					</td>
				</tr>
				
				<tr>
					<td style="text-align: center;color:red;">这里需要验证信息(机台是否存在，保养开始时间是否和机台排产计划冲突，保养开始时间验证不能小于当前时间，修改也一样不能小于)</td>
				</tr>
			</table>
		</div>
		
		<!-- 完成保养 -->
		<div id="over">
			<label>
				<span>保养人员：</span>
				<input type="text" name="maintainBy" />
			</label>
			<label>
				<span>保养备注：</span>
				<textarea class="status" rows="1" cols="3" name="remark"></textarea>
			</label>
			
		</div>
		
		
		
		
	</body>

</html>