<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>模具维修管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
	<script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
	<script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
	<script type="text/javascript">
	var basePath  = '<%=basePath%>';
		$(function(){
			//获取模具维修状态列表
			sartchStatus();
			//添加维修计划时联动操作方法
			changeMouName();
			
			$(".time input[name='planStartTime']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",width: 171,height:22});
			$(".time input[name='planStartTimes']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",width: 171,height:22});
			$(".time input[name='factStartTime']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",width: 171,height:22});
			$(".time input[name='factStartTimes']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",width: 171,height:22});
			$("#over input[name='overTime']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",width: 171,height:22});
			$("#add input[name='planStartTime']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",width: 171,height:22});
			
			$("#update input[name='planMaintainTime']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",width: 171,height:22});
			$("#update input[name='factMaintainTime']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",width: 171,height:22});
			$("#update input[name='endMaintainTime']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",width: 171,height:22});
			
			
			var url = basePath + "rest/mauMouldManageAction/getMouldMaintainList";
			getDataGrid(url);
			
		});
		function getDataGrid(url){
			debugger;
			window['g'] =
				grid= $("#maingrid").ligerGrid({
		            url:url,
		            height:'70%',
		            enabledEdit: true,
		            title: "模具维修记录信息",
		            checkbox:true,
		            columns: [
		              { hide: '序号', name: 'id'},
		              { display: '模具编码', name: 'mouCode'},
		              { display: '维修厂家', name: 'maintainVender'},
                      { display: '维修结果', name: 'maintainResult'},
                      { display: '变化记录', name: 'changeRecord'},
                      { display: '维修时间', name: 'maintainTime',width:'120'},
                      { display: '返厂时间', name: 'resultTime',width:'120'},
                      { display: '状态',
                    	  render: function (row)
                          {
                    		  debugger;
                              var data = row.resultTime;
                              var html = "";
                              if(data == "" || data == null){
                              	  html += "<span style='color:red'>维修中</span>";
                              }else{
                            	  html += "<span>已完成</span>";
                              }
                              return html;
                          },
                      },/*
                      { display: '操作',
                    	  render: function (row,rowIndex)
                          {
                              var data = row.status;
                              var html = "";
                              if(data == "未开始"){
                            	  html += "<a href='javascript:' onclick='startMaintain("+row.id+")' class='starts'>点击开始维修</a>";
                              }else if(data == "维修中"){
                            	  html += "<a href='javascript:' onclick='endMaintain("+rowIndex+")' class='ends'>点击完成维修</a>";
                              }else{
                            	  html += "";
                              }
                              return html;
                          },
                      },*/
                      
                 	],
          
		            rownumbers: true,
		            toolbar : {
		    			items : [
								 //{ text : '添加', click : addClick, icon : 'add' },
		    			         //{ text : '修改', click : updateClick, icon : 'modify' },
		    			         { text : '删除', click : deleteClick, icon : 'delete' },
		    			         ]
		    		},
		        });
		        $("#pageloading").hide();
		};
		//刷新
		function restartClick(){
			var url = basePath + "rest/mauMouldManageAction/getMouldMaintainList";
			getDataGrid(url);
		}
		//查询
		function searchClick(){
			var mouName = $("#sartch input[name='mouName']").val();
			var mouCode = $("#sartch input[name='mouCode']").val();
			var status = $("#sartch select[name='status']").val();
			var maintainBy = $("#sartch input[name='maintainBy']").val();
			var planStartTime = $("#sartch input[name='planStartTime']").val();
			var planStartTimes = $("#sartch input[name='planStartTimes']").val();
			var factStartTime = $("#sartch input[name='factStartTime']").val();
			var factStartTimes = $("#sartch input[name='factStartTimes']").val();
			var data = {};
			data.mouName = mouName;
			data.mouCode = mouCode;
			data.status = status;
			data.maintainBy = maintainBy;
			data.planStartTime = planStartTime;
			data.planStartTimes = planStartTimes;
			data.factStartTime = factStartTime;
			data.factStartTimes = factStartTimes;
			var param = JSON.stringify(data);
			var url = basePath + "rest/mauMouldManageAction/getMouldMaintainList?param="+param;
			getDataGrid(url);
			
		}
		
		function addClick(){
			//查询所有模具编码基础信息
			$.ajax({
				url: basePath + "rest/mauMouldManageAction/showAllData",
				type: 'POST',
				dataType: 'JSON',
				success:function(list){
					var div = $("#add select[name='mouCode']");
					div.html("");
					div.append("<option value=''>---请选择---</option>");
					var html = "";
					for(var i=0;i<list.length;i++){
						html += "<option value='"+list[i].mouCode+"' >"+list[i].mouCode+"</option>";
					}
					div.append(html);
				},
				error:function(){
					$.ligerDialog.error("服务器出错！");
				}
				
			});
			
			//展示添加模态框
			$.ligerDialog.open({
				title : "添加维修计划",
				target: $("#add"),
				height : 250,
				width : 400,
				modal : true,
				buttons: [  { text: '添加	', onclick: function (i, d) {saveAddMaintainData(d);}},
		                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
		                 ] 
			});
			
		}
		
		//改变模具编码时改变模具名称
		function changeMouName(){
			debugger;
			var mouCode = $("#add select[name='mouCode']");
			mouCode.change(function(){
				var param = mouCode.val();
				$.ajax({
					url: basePath + "rest/mauMouldManageAction/showAllMauNameData?param="+param,
					type: 'POST',
					dataType: 'JSON',
					success:function(list){
						var div = $("#add select[name='mouName']");
						div.html("");
						var html = "";
						for(var i=0;i<list.length;i++){
							html += "<option value='"+list[i].mouName+"' >"+list[i].mouName+"</option>";
						}
						div.append(html);
					},
					error:function(){
						$.ligerDialog.error("服务器出错！");
					}
					
				});
				
			});
			
			
			
		}
		
		//保存添加维修计划的数据
		function saveAddMaintainData(d){
			var data = {};
			var mouName = $("#add select[name='mouName']").val();
			var mouCode = $("#add select[name='mouCode']").val();
			var startTime = $("#add input[name='planStartTime']").val();
			var Stime = new Date(startTime).getTime();
			var Ntime = new Date().getTime();
			if(Stime < Ntime){
				$.ligerDialog.error("计划时间错误，不得小于当前时间！");
				return ;
			}
			
			//data.id = id;
			data.mouName = mouName;
			data.mouCode = mouCode;
			data.startTime = startTime;
			var param = JSON.stringify(data);
			$.ajax({
				url: basePath + "rest/mauMouldManageAction/saveMaintainData?param="+param,
				type: 'post',
				dataType: 'JSON',
				success:function(map){
					if(map.success != "" && map.success != null){
						$.ligerDialog.success(map.success);
						d.hide();
						restartClick();
					}else if(map.error != "" && map.error != null){
						$.ligerDialog.error(map.error);
					}else{
						$.ligerDialog.error("服务器出错！");
					}
				},
				error:function(){
					$.ligerDialog.error("服务器出错！");
				}
				
			});
		}
		
		//修改维修计划信息
		function updateClick(){
			
			var rows = grid.selected;
			if(rows.length != 1){
				$.ligerDialog.warn("请选择一行数据进行修改！");
				return ;
			}
			if(rows[0].status == "维修中"){
				$.ligerDialog.warn("处于维修中状态的模具禁止修改！");
				return ;
			}
			if(rows[0].status == "未开始"){
				var mouName = $("#add select[name='mouName']");
				var mouCode = $("#add select[name='mouCode']");
				mouName.html("");
				mouCode.html("");
				var html1 = "<option value='"+rows[0].mouName+"'>"+rows[0].mouName+"</option>";
				var html2 = "<option value='"+rows[0].mouCode+"'>"+rows[0].mouCode+"</option>";
				mouName.append(html1);
				mouCode.append(html2);
				mouName.css("cursor","not-allowed");
				mouCode.css("cursor","not-allowed");
				
				$("#add input[name='planStartTime']").val(rows[0].planMaintainTime);
				
				$.ligerDialog.open({
					title : "修改维修计划",
					target:$("#add"),
					height : 250,
					width : 400,
					modal : true,
					buttons: [  { text: '修改', onclick: function (i, d) {updateSaveMaintainData(d,rows[0].id);}},
			                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
			                 ] 
				});
			}
			
			if(rows[0].status == "已完成"){
				var mouName = $("#update input[name='mouName']");
				var mouCode =$("#update input[name='mouCode']");
				mouName.val(rows[0].mouName);
				mouCode.val(rows[0].mouCode);
				mouName.css("cursor","not-allowed");
				mouName.attr("readonly","readonly");
				mouCode.css("cursor","not-allowed");
				mouCode.attr("readonly","readonly");
				
				$("#update input[name='planMaintainTime']").val(rows[0].planMaintainTime);
				$("#update input[name='factMaintainTime']").val(rows[0].factMaintainTime);
				$("#update input[name='endMaintainTime']").val(rows[0].endMaintainTime);
				$("#update input[name='maintainBy']").val(rows[0].maintainBy);
				$("#update textarea[name='remark']").val(rows[0].remark);
				
				$.ligerDialog.open({
					title : "修改维修计划",
					target:$("#update"),
					height : 350,
					width : 400,
					modal : true,
					buttons: [  { text: '修改', onclick: function (i, d) {updateSaveMaintainData2(d,rows[0].id);}},
			                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
			                 ] 
				});
				
			}
			
		}
		
		//保存更新后的数据1
		function updateSaveMaintainData(d,id){
			var planMaintainTime = $("#add input[name='planStartTime']").val();
			var Stime = new Date(planMaintainTime).getTime();
			var Ntime = new Date().getTime();
			if(Stime < Ntime){
				$.ligerDialog.error("计划时间错误，不得小于当前时间！");
				return ;
			}
			var data = {};
			data.id = id;
			data.planMaintainTime = planMaintainTime;
			var param = JSON.stringify(data);
			
			updateSaveAll(d,param);
		}
		
		//保存更新后的数据2
		function updateSaveMaintainData2(d,id){
			
			var planMaintainTime = $("#update input[name='planStartTime']").val();
			var factMaintainTime = $("#update input[name='factMaintainTime']").val();
			var endMaintainTime = $("#update input[name='endMaintainTime']").val();
			var remark = $("#update textarea[name='remark']").val();
			var maintainBy = $("#update input[name='maintainBy']").val();
			
			var ptime = new Date(planMaintainTime).getTime();
			var ftime = new Date(factMaintainTime).getTime();
			var etime = new Date(endMaintainTime).getTime();
			var Ntime = new Date().getTime();
			if(ptime < Ntime){
				$.ligerDialog.error("计划时间错误，不得小于当前时间！");
				return ;
			}
			if(ftime < ptime){
				$.ligerDialog.error("实际开始时间错误，不得小于计划开始时间！");
				return ;
			}
			if(etime < ftime){
				$.ligerDialog.error("完成时间错误，不得小于实际开始时间！");
				return ;
			}
			
			var data = {};
			data.id = id;
			data.planMaintainTime = planMaintainTime;
			data.factMaintainTime = factMaintainTime;
			data.endMaintainTime = endMaintainTime;
			data.remark = remark;
			data.maintainBy = maintainBy;
			var param = JSON.stringify(data);
			
			updateSaveAll(d,param);
		}
		//保存修改后的数据公用方法
		function updateSaveAll(d,param){
			$.ajax({
				url: basePath + "rest/mauMouldManageAction/updateSaveMaintainData?param="+param,
				type: 'post',
				dataType: 'JSON',
				success:function(map){
					if(map.success != "" && map.success != null){
						$.ligerDialog.success(map.success);
						d.hide();
						restartClick();
					}else if(map.error != "" && map.error != null){
						$.ligerDialog.error(map.error);
					}else{
						$.ligerDialog.error("服务器出错！");
					}
				},
				error:function(){
					$.ligerDialog.error("服务器出错！");
				}
				
			});
		}
		
		
		//点击删除数据
		function deleteClick(){
			var rows = grid.selected;
			if(rows.length < 1 ){
				$.ligerDialog.warn("请选择需要删除的数据行！");
				return;
			}
			var data = [];
			for(var i=0;i<rows.length;i++){
				if(rows[i].status == "维修中"){
					$.ligerDialog.warn("禁止删除维修中的数据行！");
					return;
				}
				var obj = new Object();
				obj.id = rows[i].id;
				data.push(obj);
			}
			var param = JSON.stringify(data);
			$.ligerDialog.confirm( "您确认要删掉这<font color=red>" + rows.length + "</font>条数据吗？",
					function(result) {
						if (result == true) {
							$.ajax({
								url: basePath + "rest/mauMouldManageAction/clearMaintainData?param="+param,
								type: 'POST',
								dataType: 'JSON',
								success:function(map){
									if(map.success != null && map.success!=""){
										$.ligerDialog.success(map.success);
										restartClick();
									}else if(map.error != null && map.error != ""){
										$ligerDialog.error(map.error);
									}else{
										$.ligerDialog.error("服务器出错！");
									}
								},
								error:function(){
									$.ligerDialog.error("服务器出错！");
								}
								
							});
						} else {
							return;
						}
				});
			
			
		}
		
		//点击开始维修
		function startMaintain(id){
			$.ligerDialog.confirm( "是否确认开始维修？",
				function(result) {
					if (result == true) {
						$.ajax({
							url: basePath + "rest/mauMouldManageAction/startMaintain?id="+id,
							type: 'POST',
							dataType: 'JSON',
							success:function(map){
								if(map.success != null && map.success!=""){
									$.ligerDialog.success(map.success);
									restartClick();
								}else if(map.error != null && map.error != ""){
									$ligerDialog.error(map.error);
								}else{
									$.ligerDialog.error("服务器出错！");
								}
							},
							error:function(){
								$.ligerDialog.error("服务器出错！");
							}
							
						});
					} else {
						return;
					}
			});
			
			
		}
		
		//点击完成维修
		function endMaintain(rowIndex){
			var row = grid.rows[rowIndex];
			$("#over input[name='mouName']").val(row.mouName);
			$("#over input[name='mouCode']").val(row.mouCode);
			$.ligerDialog.open({
				title : "完成维修计划信息",
				target: $("#over"),
				height : 320,
				width : 400,
				modal : true,
				buttons: [  { text: '提交', onclick: function (i, d) {overMaintainData(d,row);}},
		                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
		                 ] 
			});
			
		}
		//保存完成维修数据
		function overMaintainData(d,datas){
			var endMaintainTime = $("#over input[name='overTime']").val();
			var maintainBy = $("#over input[name='maintainBy']").val();
			var remark = $("#over textarea[name='remark']").val();
			var data = {};
			data.id = datas.id;
			data.mouName = datas.mouName;
			data.mouCode = datas.mouCode;
			data.endMaintainTime = endMaintainTime;
			data.maintainBy = maintainBy;
			data.remark = remark;
			var param = JSON.stringify(data);
			$.ajax({
				url: basePath + "rest/mauMouldManageAction/overMaintainData?param="+param,
				type: 'POST',
				dataType: 'JSON',
				success:function(map){
					if(map.success != null && map.success!=""){
						$.ligerDialog.success(map.success);
						d.hide();
						restartClick();
					}else if(map.error != null && map.error != ""){
						$ligerDialog.error(map.error);
					}else{
						$.ligerDialog.error("服务器出错！");
					}
				},
				error:function(){
					$.ligerDialog.error("服务器出错！");
				}
				
			});
			
		}
		
		
		//获取所有模具状态显示下拉框
		function sartchStatus(){
			$.ajax({
				url: basePath + "rest/mauMouldManageAction/getMouldMaintainStatus",
				type: 'POST',
				dataType: 'JSON',
				success:function(list){
					debugger;
					var div = $("#sartch select[name='status']");
					div.append("<option value=''>---请选择---</option>");
					var html = "";
					for(var i=0;i<list.length;i++){
						html += "<option value='"+list[i]+"'>"+list[i]+"</option>";
					}
					div.append(html);
				},
				error:function(){
					$.ligerDialog.error("服务器出错！");
				}
				
			});
			
			
		}
		
	</script>
	<style type="text/css">
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
		 #sartch .click{
		 	float: left;
		 	margin-left:40px;
		 	margin-top:10px;
		 }
		 #sartch .btn,#sartch .res{
		 	border:none;
		 	border-radius:5px;
		 	color:white;
		 	width:80px;
		 	height:27px;
		 	cursor: pointer;
		 	margin-left:20px;
		 }
		 #sartch .btn{
		 	background-color: #0389B7;
		 }
		 #sartch .btn:hover{
		 	background-color: #62B2CE;
		 }
		 #sartch .res{
		 	background-color: #F29883;
		 }
		 #sartch .res:hover{
		 	background-color: #E3BCB2;
		 }
		 .maintain{
		 	color:blue;
		 }
		 .maintain:hover{
		 	color:red;
		 }
		 .starts:hover{
		 	color: green;
		 }
		 .ends{
		 	color:green;
		 }
		 .ends:hover{
		 	color:black;
		 }
		 
		 #over{
		 	display:none;
		 }
		 #over table{
		 	width:100%;
		 }
		 #over table tr{
		 	height:30px;
		 }
		 #over table tr td span{
		    display:inline-block;
		  	width:100px;
		  	text-align: right;
		  	
		 }
		 #over table tr td input{
		  	width:170px;
		  	height:25px;
		 }
		 #over table tr td textarea {
		  	width:170px;
		  	height:70px;
		 }
		 /*添加维修计划  */
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
				width:150px;
			}
			/*更新维修计划  */
			 #update {
				display: none;
				width:100%;
				margin:20px auto;
			}
			#update table{
				width:100%;
			}
			#update table tr{
				height:35px;
			}
			#update table tr td span{
				display: inline-block;
				width:120px;
				height:35px;
				line-height: 35px;
				text-align: right;
			}
			#update table tr td input{
				height:22px;
				width:150px;
			}
	</style>
  </head>
  
  <body>
  		
  		<div class="l-loading" style="display:block" id="pageloading"></div>
		<div class="l-clear"></div>
		<form>
		<div id="sartch">
			<!-- 
			<div class="time">
				<span style="float:left;display:inline-block;">故障时间：</span>
				<div class="st">
					<input type="text" name="startTime" id="start" />
					<span>到</span>
					<input type="text" name="endTime" id="end" />
				</div>
			</div>
			
			
			<div class="mac">
				<span>模具名称：</span>
				<input type="text" name="mouName"/>
			</div> -->
			<div class="repar">
				<span>模具编码：</span>
				<input type="text" name="mouCode"/>
			</div>
			<!-- 
			<div class="state">
				<span style="float:left;display:inline-block;">状态：</span>
				<div class="ss">
					<select name="status">
					</select>
				</div>
			</div> 
			<div class="repar">
				<span>维修人：</span>
				<input type="text" name="maintainBy"/>
			</div>
			<div class="time">
				<span>计划开始时间：</span>
				<input type="text" name="planStartTime"/>&nbsp;&nbsp;到
				<input type="text" name="planStartTimes"/>
			</div>
			<div class="time">
				<span>实际开始时间：</span>
				<input type="text" name="factStartTime"/>&nbsp;&nbsp;到
				<input type="text" name="factStartTimes"/>
			</div>-->
			<div class="click">
				<input type="button" class="btn" value="查  询" onclick="searchClick()">
				<input type="reset" class="res" value="重  置" onclick="restartClick()" />
			</div>
			
		</div>
		</form>
		
		<div id="maingrid"></div>
		<div style="display:none;"></div>
		
		
		<div id="over">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<span>模具名称：</span>
						<input type="text" name="mouName" readonly="readonly" style="cursor: not-allowed;background-color: #D6D6D8;border:none;"/>
					</td>
				</tr>
				<tr>
					<td>
						<span>模具编码：</span>
						<input type="text" name="mouCode" readonly="readonly" style="cursor: not-allowed;background-color: #D6D6D8;border:none;" />
					</td>
				</tr>
				<tr>
					<td>
						<span>维修完成时间：</span>
						<input type="text" name="overTime" />
					</td>
				</tr>
				<tr>
					<td>
						<span>维修人：</span>
						<input type="text" name="maintainBy" />
					</td>
				</tr>
				<tr>
					<td>
						<span>备注：</span>
						<textarea name="remark"></textarea>
					</td>
				</tr>
			</table>
		
		</div>
		
		<div id="add">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<span>模具编码：</span>
						<select name="mouCode">
						</select>	
					</td>
				</tr>
				<tr>
					<td>
						<span>模具名称：</span>
						<select name="mouName">
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<span>计划维修开始时间：</span>
						<input type="text" name="planStartTime" />	
					</td>
				</tr>
				
			</table>
		</div>
		
		<div id="update">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<span>模具编码：</span>
						<input type="text" name="mouCode"/>	
					</td>
				</tr>
				<tr>
					<td>
						<span>模具名称：</span>
						<input type="text" name="mouName"/>	
					</td>
				</tr>
				<tr>
					<td>
						<span>计划开始时间：</span>
						<input type="text" name="planMaintainTime" />	
					</td>
				</tr>
				<tr>
					<td>
						<span>实际开始时间：</span>
						<input type="text" name="factMaintainTime" />	
					</td>
				</tr>
				<tr>
					<td>
						<span>维修完成时间：</span>
						<input type="text" name="endMaintainTime" />	
					</td>
				</tr>
				<tr>
					<td>
						<span>维修人：</span>
						<input type="text" name="maintainBy" />	
					</td>
				</tr>
				<tr>
					<td>
						<span>备注：</span>
						<textarea name="remark"></textarea>
					</td>
				</tr>
			</table>
		</div>
		
		
		
  </body>
</html>
