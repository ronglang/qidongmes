<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>模具管理</title>
    
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
	var grid;
		$(function(){
			
			$("#add input[name='startTime']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",labelWidth: 160,labelHeight:30});
			$("#adds input[name='inStoreTime']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",labelWidth: 180,labelHeight:30});
			$("#add input[name='rejectTime']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",labelWidth: 180,labelHeight:30});
			$("#maintain input[name='maintainTime']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd",labelWidth: 180,labelHeight:30});
			$("#maintainover input[name='resultTime']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd",labelWidth: 180,labelHeight:30});
			
			
			//获取模具状态列表
			sartchStatus();
			
			var url = basePath + "rest/mauMouldManageAction/getMouldList";
			getDataGrid(url);
			
			
		});
		
		function getDataGrid(url){
			debugger;
			window['g'] =
				grid= $("#maingrid").ligerGrid({
		            url:url,
		            height:'70%',
		            enabledEdit: true,
		            checkbox:true,
		            columns: [
		              { hide: '序号', name: 'id'},
		              { display: '模具编码', name: 'mouCode'},
		              { display: '模具规格', name: 'mouGgxh',},
		              { display: '模具类型', name: 'mouType',},
		              { display: '模具尺寸', name: 'mouSize',},
		              { display: '供应商', name: 'mouSupply',},
		              //{ display: '可用数量', name: 'number'},
		              //{ display: '单位', name: 'unit'},
		              { display: '入库时间', name: 'inStoreTime',width:'100'},
		              { display: '机器用途', name: 'mouUse'},
		              { display: '电线用途', name: 'mouLineUse'},
		              { display: '是否报废', name: 'isReject'},
		              { display: '报废时间', name: 'rejectTime',width: '180'},
		              { display: '报废理由', name: 'rejectReason'},
		              //{ display: '报废数量', name: 'rejectNumber'},
		              
                      { display: '状态', name: 'status',
                    	  render: function (row)
                          {
                              var data = row.status;
                              var html = "";
                              if(data == "维修中"){
                              	  html += "<span style='color:red'>"+ data + "</span>";
                              }else if(data == "正常"){
                            	  html += "<span style='color:green'>"+ data + "</span>";
                              }else if(data == "报废"){
                            	  html += "<span style='color:gray'>"+ data + "</span>";
                              }else{
                            	  html += "<span>"+ data + "</span>";
                              }
                              return html;
                          },
                      },
                      { display: '操作',
                    	  render: function (row,rowIndex)
                          {
                              var data = row.isReject;
                              
                              var html = "";
                              if(data == "是"){
                            	  html += "<span></span>";
                              }else{
                            	  if(row.status == "维修中"){
                            		  html += "<span><a href='javascript:' onclick='rejectClick("+rowIndex+")' class='maintain'>报废</a>&nbsp;&nbsp;<a href='javascript:' onclick='maintainOverClick("+rowIndex+")' class='maintain'>完成维修</a></span>";
                            	  }else{
                            		  html += "<span><a href='javascript:' onclick='rejectClick("+rowIndex+")' class='maintain'>报废</a>&nbsp;&nbsp;<a href='javascript:' onclick='maintainClick("+rowIndex+")' class='maintain'>点击维修</a></span>";
                            	  }
                            	  
                              }
                              return html;
                          },width:'120',
                      },
                      
                 	],
          
		            rownumbers: true,
		            toolbar : {
		    			items : [
								 { text : '添加', click : addClick, icon : 'add' },
		    			         { text : '修改', click : updateClick, icon : 'modify' },
		    			         { text : '删除', click : deleteClick, icon : 'delete' },
		    			         ]
		    		},
		        });
		        $("#pageloading").hide();
		};
		
		//添加基础数据
		function addClick(){
			
			$.ligerDialog.open({
				title : "添加维修计划",
				target:$("#adds"),
				height : 300,
				width : 560,
				modal : true,
				buttons: [  { text: '添加', onclick: function (i, d) {saveData(d);}},
		                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
		                 ] 
			});
			
		}
		//保存基础数据
		function saveData(d){
			var mouGgxh = $("#adds input[name='mouGgxh']").val();
			var mouCode = $("#adds input[name='mouCode']").val();
			var mouType = $("#adds input[name='mouType']").val();
			var mouSize = $("#adds input[name='mouSize']").val();
			var mouSupply = $("#adds input[name='mouSupply']").val();
			//var number = $("#adds input[name='number']").val();
			//var unit = $("#adds input[name='unit']").val();
			var inStoreTime = $("#adds input[name='inStoreTime']").val();
			var mouUse = $("#adds input[name='mouUse']").val();
			var mouLineUse = $("#adds input[name='mouLineUse']").val();
			
			var data = {};
			data.mouGgxh = mouGgxh;
			data.mouCode = mouCode;
			data.mouType = mouType;
			data.mouSize = mouSize;
			data.mouSupply = mouSupply;
			//data.number = number;
			//data.unit = unit;
			data.inStoreTime = inStoreTime;
			data.mouUse = mouUse;
			data.mouLineUse = mouLineUse;
			var param = JSON.stringify(data);
			
			$.ajax({
				url: basePath + "rest/mauMouldManageAction/saveData?param="+param,
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
		//修改模具基础数据
		function updateClick(){
			var rows = grid.selected;
			if(rows.length != 1){
				$.ligerDialog.warn("请选择一行数据进行修改！");
				return ;
			}
			/*
			if(rows[0].status != "正常"){
				$.ligerDialog.warn("处于非正常状态的模具禁止修改！");
				return ;
			}
			*/
			
			$("#adds input[name='mouGgxh']").val(rows[0].mouGgxh);
			$("#adds input[name='mouCode']").val(rows[0].mouCode);
			$("#adds input[name='mouType']").val(rows[0].mouType);
			$("#adds input[name='mouSize']").val(rows[0].mouSize);
			$("#adds input[name='mouSupply']").val(rows[0].mouSupply);
			$("#adds input[name='inStoreTime']").val(rows[0].inStoreTime);
			$("#adds input[name='mouUse']").val(rows[0].mouUse);
			$("#adds input[name='mouLineUse']").val(rows[0].mouLineUse);
			//$("#adds input[name='number']").val(rows[0].number);
			//$("#adds input[name='unit']").val(rows[0].unit);
			$.ligerDialog.open({
				title : "修改维修计划",
				target:$("#adds"),
				height : 300,
				width : 560,
				modal : true,
				buttons: [  { text: '修改', onclick: function (i, d) {updateSaveData(d,rows[0].id);}},
		                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
		                 ] 
			});
			
		}
		//保存修改后的模具基础数据
		function updateSaveData(d,id){
			var mouGgxh = $("#adds input[name='mouGgxh']").val();
			var mouCode = $("#adds input[name='mouCode']").val();
			var mouType = $("#adds input[name='mouType']").val();
			var mouSize = $("#adds input[name='mouSize']").val();
			var mouSupply = $("#adds input[name='mouSupply']").val();
			//var number = $("#adds input[name='number']").val();
			//var unit = $("#adds input[name='unit']").val();
			var inStoreTime = $("#adds input[name='inStoreTime']").val();
			var mouUse = $("#adds input[name='mouUse']").val();
			var mouLineUse = $("#adds input[name='mouLineUse']").val();
			var data = {};
			data.id = id;
			data.mouGgxh = mouGgxh;
			data.mouCode = mouCode;
			data.mouType = mouType;
			data.mouSize = mouSize;
			data.mouSupply = mouSupply;
			//data.number = number;
			//data.unit = unit;
			data.inStoreTime = inStoreTime;
			data.mouUse = mouUse;
			data.mouLineUse = mouLineUse;
			var param = JSON.stringify(data);
			$.ajax({
				url: basePath + "rest/mauMouldManageAction/updateSaveData?param="+param,
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
		
		function deleteClick(){
			var rows = grid.selected;
			if(rows.length < 1){
				$.ligerDialog.warn("请选择数据删除！");
				return;
			}
			var data = [];
			for(var i=0;i<rows.length;i++){
				
				if(rows[i].status == "维修中"){
					$.ligerDialog.warn("不得删除维修中的模具信息！");
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
								url: basePath + "rest/mauMouldManageAction/clearData?param="+param,
								type: 'post',
								dataType: 'JSON',
								success:function(map){
									if(map.success != "" && map.success != null){
										$.ligerDialog.success(map.success);
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
						} else {
							return;
						}
					});
			
			
		}
		
		//报废
		function rejectClick(rowIndex){
			var row = grid.rows[rowIndex];
			$("#add input[name='mouCode']").val(row.mouCode);
			
			$.ligerDialog.open({
				title : "报废",
				target:$("#add"),
				height : 250,
				width : 400,
				modal : true,
				buttons: [  { text: '添加', onclick: function (i, d) {saveRejectData(d,rowIndex);}},
		                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
		                 ] 
			});
			
		}
		//保存报废数据
		function saveRejectData(d,rowIndex){
			var row = grid.rows[rowIndex];
			var id = row.id;
			var rejectTime = $("#add input[name='rejectTime']").val();
			var rejectReason = $("#add textarea[name='rejectReason']").val();
			var data = {};
			data.id = id;
			data.rejectTime = rejectTime;
			data.rejectReason = rejectReason;
			var param = JSON.stringify(data);
			$.ajax({
				url: basePath+"rest/mauMouldManageAction/saveRejectData?param=" +param,
				type: "POST",
				dataType: "JSON",
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
		
		//添加维修计划,点击开始维修
		function maintainClick(rowIndex){
			
			$.ligerDialog.open({
				title : "开始维修",
				target:$("#maintain"),
				height : 150,
				width : 300,
				modal : true,
				buttons: [  { text: '开始维修', onclick: function (i, d) {saveMaintainData(d,rowIndex);}},
		                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
		                 ] 
			});
			
		}
		//保存维修计划信息
		function saveMaintainData(d,rowIndex){
			var maintainTime = $("#maintain input[name='maintainTime']").val();
			if(maintainTime == ""){
				$.ligerDialog.error("开始维修时间不能为空！");
				return;
			}
			var rows = grid.rows[rowIndex];
			var data = {};
			data.id = rows.id;
			data.mouGgxh = rows.mouGgxh;
			data.mouCode = rows.mouCode;
			data.mouType = rows.mouType;
			data.mouSize = rows.mouSize;
			data.mouSupply = rows.mouSupply;
			data.inStoreTime = rows.inStoreTime;
			data.mouUse = rows.mouUse;
			data.mouLineUse = rows.mouLineUse;
			data.maintainTime = $("#maintain input[name='maintainTime']").val();
			
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
				},
				
			});
			
		}
		
		//完成维修
		function maintainOverClick(rowIndex){
			var row = grid.rows[rowIndex];
			$("#maintainover input[name='mouCode']").val(row.mouCode);
			$.ligerDialog.open({
				title : "完成维修",
				target:$("#maintainover"),
				height : 350,
				width : 400,
				modal : true,
				buttons: [  { text: '完成维修', onclick: function (i, d) {saveMaintainOverData(d,row.id);}},
		                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
		                 ] 
			});
			
		}
		//保存完成维修数据
		function saveMaintainOverData(d,id){
			var mouCode = $("#maintainover input[name='mouCode']").val();
			var maintainVender = $("#maintainover input[name='maintainVender']").val();
			var maintainResult = $("#maintainover input[name='maintainResult']").val();
			var resultTime = $("#maintainover input[name='resultTime']").val();
			var changeRecord = $("#maintainover textarea[name='changeRecord']").val();
			var data = {};
			data.id = id;
			data.mouCode = mouCode;
			data.maintainVender = maintainVender;
			data.maintainResult = maintainResult;
			data.resultTime = resultTime;
			data.changeRecord = changeRecord;
			var param = JSON.stringify(data);
			$.ajax({
				url: basePath + "rest/mauMouldManageAction/saveMaintainOverData?param="+param,
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
				},
				
			});
			
		}
		
		
		//查询
		function searchClick(){
			var mouGgxh = $("#sartch input[name='mouGgxh']").val();
			var mouCode = $("#sartch input[name='mouCode']").val();
			var status = $("#sartch select[name='status']").val();
			var data = {};
			data.mouGgxh = mouGgxh;
			data.mouCode = mouCode;
			data.status = status;
			var param = JSON.stringify(data);
			var url = basePath + "rest/mauMouldManageAction/getMouldList?param="+param;
			getDataGrid(url);
		}
		//重置
		function restartClick(){
			var url = basePath + "rest/mauMouldManageAction/getMouldList";
			getDataGrid(url);
		}
		//获取所有基础状态显示下拉框
		function sartchStatus(){
			$.ajax({
				url: basePath + "rest/mauMouldManageAction/getMouldStatus",
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
			 
			 #add,#maintainover{
				display: none;
				width:100%;
				margin:20px auto;
			}
			#add table,#maintainover table{
				width:100%;
			}
			#add table tr,#maintainover table tr{
				height:35px;
			}
			#add table tr td span,#maintainover table tr td span{
				display: inline-block;
				width:120px;
				height:35px;
				line-height: 35px;
				text-align: right;
			}
			#add table tr td input,#maintainover table tr td input{
				height:22px;
				width:150px;
			}
			
			#maintain{
				display: none;
				width:100%;
				margin:20px auto;
			}
			#maintain table{
				width:100%;
			}
			#maintain table tr{
				height:35px;
			}
			#maintain table tr td span{
				display: inline-block;
				width:120px;
				height:35px;
				line-height: 35px;
				text-align: right;
			}
			#maintain table tr td input{
				height:22px;
				width:150px;
			}
			
			/*添加基础信息   */
			#adds{
				display: none;
				width:100%;
				margin:20px auto;
			}
			#adds table{
				width:100%;
			}
			#adds table tr{
				height:35px;
			}
			#adds table tr td span{
				display: inline-block;
				width:90px;
				height:35px;
				line-height: 35px;
				text-align: right;
			}
			#adds table tr td input{
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
			 -->
			
			<div class="mac">
				<span>模具规格：</span>
				<input type="text" name="mouGgxh"/>
			</div>
			<div class="repar">
				<span>模具编码：</span>
				<input type="text" name="mouCode"/>
			</div>
			<div class="state">
				<span style="float:left;display:inline-block;">状态：</span>
				<div class="ss">
					<select name="status">
					</select>
				</div>
			</div>
			<div class="click">
				<input type="button" class="btn" value="查  询" onclick="searchClick()">
				<input type="reset" class="res" value="重  置" onclick="restartClick()" />
			</div>
			
		</div>
		</form>
		<!-- 
		<div id="myform"></div>
		 -->
		<div id="maingrid"></div>
		<div style="display:none;">
		</div>
    	
    	<div id="adds">
    		<table border="0" cellpadding="0" cellspacing="0">
    			<tr>
					<td>
						<span>模具编码：</span>
						<input type="text" name="mouCode"/>	
					</td>
					<td>
						<span>模具规格：</span>
						<input type="text" name="mouGgxh"/>
					</td>
				</tr>
				<tr>
					<td>
						<span>模具类型：</span>
						<input type="text" name="mouType"/>
					</td>
					<td>
						<span>供应商：</span>
						<input type="text" name="mouSupply"/>
					</td>
				</tr><!-- 
				<tr>
					<td>
						<span>数量：</span>
						<input type="text" name="number" />	
					</td>
					<td>
						<span>单位：</span>
						<input type="text" name="unit" />	
					</td>
				</tr> -->
				<tr>
					<td>
						<span>模具尺寸：</span>
						<input type="text" name="mouSize"/>
					</td>
					<td>
						<span>入库时间：</span>
						<input type="text" name="inStoreTime" />	
					</td>
				</tr>
				<tr>
					<td>
						<span>机器用途：</span>
						<input type="text" name="mouUse"/>
					</td>
					<td>
						<span>电线用途：</span>
						<input type="text" name="mouLineUse" />	
					</td>
				</tr>
				
			</table>
    	
    	</div>
    	
    	<div id="add">
    		<table border="0" cellpadding="0" cellspacing="0">
    			<tr>
    				<td>
						<span>模具编码：</span>
						<input type="text" name="mouCode" readonly="readonly" title="只读" style="background-color: #DBDBDB;"/>	
					</td>
				</tr>
				<tr>
					<td>
						<span>报废时间：</span>
						<input type="text" name="rejectTime" />	
					</td>
				</tr>
				<tr>
					<td>
						<span>报废理由：</span>
						<textarea name="rejectReason" style="width:150px;height:60px;"></textarea>	
					</td>
				</tr>
				
			</table>
    	</div>
    	
    	<div id="maintain">
    		<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<span>维修时间：</span>
						<input type="text" name="maintainTime" />	
					</td>
				</tr>
				
			</table>
    	</div>
    	
    	
    	<div id="maintainover">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<span>模具编码：</span>
						<input type="text" readonly="readonly" name="mouCode" style="background-color: #DBDBDB;" title="只读" />	
					</td>
				</tr>
				<tr>
					<td>
						<span>维修厂家：</span>
						<input type="text" name="maintainVender" />	
					</td>
				</tr>
				<tr>
					<td>
						<span>维修结果：</span>
						<input type="text" name="maintainResult" />	
					</td>
				</tr>
				<tr>
					<td>
						<span>反厂时间：</span>
						<input type="text" name="resultTime" />	
					</td>
				</tr>
				<tr>
					<td>
						<span>变化记录：</span>
						<textarea name="changeRecord" style="width:150px;height:60px;"></textarea>	
					</td>
				</tr>
			</table>
		</div>
    	<!-- 
    	<div id="add">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<span>模具名称：</span>
						<input type="text" readonly="readonly" name="mouName" style="background-color: #DBDBDB;" title="只读" />
					</td>
				</tr>
				<tr>
					<td>
						<span>模具编码：</span>
						<input type="text" readonly="readonly" name="mouCode" style="background-color: #DBDBDB;" title="只读" />	
					</td>
				</tr>
				<tr>
					<td>
						<span>计划维修开始时间：</span>
						<input type="text" name="startTime" />	
					</td>
				</tr>
				
			</table>
		</div>
    	 -->
  </body>
</html>
