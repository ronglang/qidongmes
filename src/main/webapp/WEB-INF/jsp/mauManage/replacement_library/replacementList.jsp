<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>备件管理列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/json2.js" type="text/javascript"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    
    <script type="text/javascript">
        var basePath = "<%=basePath%>";
		
    	$(function(){
    		
    		$("#add input[name='inStoreTime']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",width: 171,height:22});
    		$("#rejects input[name='rejectTime']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",width: 160,height:22});
    		$("#materials input[name='materialTime']").ligerDateEditor({ showTime: true, format: "yyyy-MM-dd hh:mm:ss",width: 160,height:22});
			
    		//addrTopAdd();
    		//显示所有
    		$("#top input.showall").click(gridTwo);
    		//查询
    		$("#top input.btn").click(sartch);
    		
    		var url = basePath+'rest/mauReplacementManageAction/getDataListAction';
    		gridUi(url);
    		selectType();
    		//点击切换页面
    		replaceClick();
    	});
    	
    	function replaceClick(){
    		
    		$("#replace").click(function(){
    			window.location.href = basePath + 'rest/mauReplacementManageAction/toReplacementLibrary';
    		});
			$("#reject").click(function(){
				window.location.href = basePath + 'rest/mauReplacementManageAction/toRejectList';
    		});
			$("#material").click(function(){
				window.location.href = basePath + 'rest/mauReplacementManageAction/toMaterialList';
			});
    		
    	}
    	
    	var manage;
    	function gridUi(url){
    		manage = $("#maingrid").ligerGrid({
    			height: '80%',
    	        title:'备件列表管理',
    	        url: url,
    	       	checkbox: true,
    			columns:[
				    { hide:'id',name:'id',width:1},
    				{display: '备件编码',name:'code'},
    				{display: '规格型号',name:'ggxh'},
    				{display: '备件类型',name:'type'},
    				{display: '库存数量',name:'number'},
    				{display: '公用商',name:'supply'},
    				{display: '机器用途',name:'replaceUse'},
    				{display: '电线用途',name:'replaceLineUse'},
    				{display: '入库时间',name:'inStoreTime'},
    				{ display: '操作',
                  	  render: function (row,rowIndex)
                        {
                            var data = row.number;
                            var html = "";
                            if(data == "0"){
                            	  html += "<span></span>";
                            }else{
                          	  html += "<span><a href='javascript:' onclick='rejectClick("+rowIndex+")' class='replace'>报废</a>&nbsp;&nbsp;<a href='javascript:' onclick='materialClick("+rowIndex+")' class='replace'>领料</a></span>";
                            }
                            return html;
                        },
                    },
    				
    				
    			],
    			toolbar: {
    	            items: [
    	            { text: '增加', click: addRows, icon: 'add'},
    	            { text: '修改', click: updateRows, icon: 'modify'},
    	            { text: '删除', click: deleteRows, icon: 'delete'},
    	            { line: true },
    	            //{ text: '打印', click: printExcel, icon: 'print'},
    	            ]
    	        },
    	        rownumbers: true,
    	        enabledEdit: true,
    	        pageSize: 10,
    		});
    		$("#pageloading").hide();
    	}
    	//查询
    	function sartch(){
    		var data = {};
    		var code = $("#top input[name='code']").val();
    		//var name = $("#top input[name='name']").val();
    		var ggxh = $("#top input[name='ggxh']").val();
    		var type = $("#sType").val();
    		data.code = code;
    		//data.name = name;
    		data.ggxh = ggxh;
    		data.type=type;
    		var param = JSON.stringify(data);
    		var url = basePath+'rest/mauReplacementManageAction/getDataListAction?param='+param;
    		gridUi(url);
    		
    		
    	}
    	//显示所有
    	function gridTwo(){
    		var url = basePath+'rest/mauReplacementManageAction/getDataListAction';
    		gridUi(url);
    	}
    	//添加数据
    	function addRows(){
    		
    		$.ligerDialog.open({
    	        target:$("#add"),
    	        //url : basePath+"rest/mauReplacementManageAction/toAddDataPage",
    	        title: '增加备件信息',
    	        width: 630,
    	        height: 300,
    	        isResize: true,
    	        modal: true,
    	        buttons: [  { text: '添加', onclick: function (i, d) { saveReplacement(d); }},
    	                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
    	                 ]                                  
    	   });
    		
    	}
    	//保存添加的数据
    	function saveReplacement(d){
    		//var name = $("#add input[name='name']").val();
    		var code = $("#add input[name='code']").val();
    		var ggxh = $("#add input[name='ggxh']").val();
    		var type = $("#add input[name='type']").val();
    		var number = $("#add input[name='number']").val();
    		var replaceUse = $("#add input[name='replaceUse']").val();
    		var replaceLineUse= $("#add input[name='replaceLineUse']").val();
    		var supply= $("#add input[name='supply']").val();
    		var inStoreTime= $("#add input[name='inStoreTime']").val();
    		if(code == "" || ggxh == "" || number == "" || replaceUse == ""||type==""||replaceLineUse==""||supply==""||inStoreTime==""){
    			$.ligerDialog.warn("添加失败，字段不能为空！");
    			return;
    		}
    		var data = {};
    		data.code = code;
    		data.ggxh = ggxh;
    		data.number = number;
    		data.type=type;
    		data.replaceUse = replaceUse;
    		data.replaceLineUse = replaceLineUse;
    		data.supply = supply;
    		data.inStoreTime=inStoreTime;
    		var param = JSON.stringify(data);
    		$.ajax({
    			url:basePath+"rest/mauReplacementManageAction/addReplacementData?param="+param,
    			type: "POST",
    			success:function(map){
    				debugger;
    				var info = eval("("+map+")");
    				if(info.success != "" && info.success!=null){
    					$.ligerDialog.success(info.success);
    					d.hide();
    					gridTwo();
    				}else if(info.error != "" && info.error!=null){
    					$.ligerDialog.error(info.error);
    				}else{
    					$.ligerDialog.error("服务器出错");
    				}
    			},
    			error:function(){
    				$.ligerDialog.error("服务器错误，添加失败！");
    			}
    		});
    		
    		
    	}
    	
    	
    	//修改数据
    	function updateRows(){
    		
    		var manager = $("#maingrid").ligerGetGridManager();
    		var li = manager.getSelectedRows();
    		if(li.length != 1){
    			$.ligerDialog.warn("请选择一条数据修改！");
    			return ;
    		}
    		$("#add input[name='code']").val(li[0].code);
    		$("#add input[name='ggxh']").val(li[0].ggxh);
    		$("#add input[name='type']").val(li[0].type);
    		$("#add input[name='number']").val(li[0].number);
    		$("#add input[name='replaceUse']").val(li[0].replaceUse);
    		$("#add input[name='replaceLineUse']").val(li[0].replaceLineUse);
    		$("#add input[name='supply']").val(li[0].supply);
    		$("#add input[name='inStoreTime']").val(li[0].inStoreTime);
    		$.ligerDialog.open({
    	        target:$("#add"),
    	        title: '增加备件信息',
    	        width: 630,
    	        height: 400,
    	        isResize: true,
    	        modal: true,
    	        buttons: [  { text: '修改', onclick: function (i, d) { updateReplacement(d,li[0].id); }},
    	                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
    	                 ]                                  
    	   });
    	}
    	
    	//保存修改后的数据
    	function updateReplacement(d,id){
    		
    		var code = $("#add input[name='code']").val();
    		var ggxh = $("#add input[name='ggxh']").val();
    		var type = $("#add input[name='type']").val();
    		var number = $("#add input[name='number']").val();
    		var replaceUse = $("#add input[name='replaceUse']").val();
    		var replaceLineUse= $("#add input[name='replaceLineUse']").val();
    		var supply= $("#add input[name='supply']").val();
    		var inStoreTime= $("#add input[name='inStoreTime']").val();
    		if(code == "" || ggxh == "" || number == "" || replaceUse == ""||type==""||replaceLineUse==""||supply==""||inStoreTime==""){
    			$.ligerDialog.warn("修改失败，字段不能为空！");
    			return;
    		}
    		debugger;
    		var data = {};
    		data.id = id;
    		data.code = code;
    		data.ggxh = ggxh;
    		data.number = number;
    		data.type=type;
    		data.replaceUse = replaceUse;
    		data.replaceLineUse = replaceLineUse;
    		data.supply = supply;
    		data.inStoreTime=inStoreTime;
    		var param = JSON.stringify(data);
    		$.ajax({
    			url:basePath+"rest/mauReplacementManageAction/updateReplacementData?param="+param,
    			type: "POST",
    			success:function(map){
    				var info = eval("("+map+")");
    				if(info.success != "" && info.success!=null){
    					$.ligerDialog.success(info.success);
    					d.hide();
    					gridTwo();
    				}else if(info.error != "" && info.error!=null){
    					$.ligerDialog.error(info.error);
    				}else{
    					$.ligerDialog.error("服务器出错");
    				}
    			},
    			error:function(map){
    				$.ligerDialog.error("服务器出错，修改失败！");
    			}
    			
    			
    		});
    		
    		
    	}
    	
    	//删除数据
    	function deleteRows(){
    		var manager = $("#maingrid").ligerGetGridManager();
    		var li = manager.getSelectedRows();
    		var dataArray = [];
    		if(li.length<1){
    			$.ligerDialog.warn("请选择删除的行！");
    			return;
    		}
    		for(var i = 0; i<li.length;i++){
    			var bean = new Object();
    			var beans = li[i];
    			bean.id = beans.id;
    			dataArray.push(bean);
    		}
    		var data = JSON.stringify(dataArray);
    		$.ligerDialog.confirm("温馨提示：数据不易，且行且珍惜！是否确定删除？",function(yes){
    			if(yes){
    				$.ajax({ 
    					url: basePath+'rest/mauReplacementManageAction/clearReplacementData',
    			   		type:"post",
    			   		dataType:'json',
    			   		data:"ids="+data,
    			   		success: function(info){
    			   			debugger;
    			   			if(info.success != "" && info.success != null){
    			   	    		$.ligerDialog.success(info.success);
    			   	    		gridTwo();
    			   			}else if(info.error != "" && info.error != null){
    			   				$.ligerDialog.error(info.error);
    			   			}else{
    			   				$.ligerDialog.error("服务器错误！");
    			   			}
    			   	    },
    			   	    error : function() {
    			   	    	$.ligerDialog.error("删除失败，发生未知错误！");
    					}
    				});
    			}else{
    				return;
    			}
    			
    		});
    		
    		
    	}
    	
    	//点击报废
    	function rejectClick(rowIndex){
    		
    		var row = manage.rows[rowIndex];
			$("#rejects input[name='code']").val(row.code);
			
			$.ligerDialog.open({
				title : "报废",
				target:$("#rejects"),
				height : 280,
				width : 400,
				modal : true,
				buttons: [  { text: '报废', onclick: function (i, d) {saveRejectData(d,rowIndex);}},
		                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
		                 ] 
			});
    		
    	}
    	
    	function saveRejectData(d,rowIndex){
    		
    		var row = manage.rows[rowIndex];
			var id = row.id;
			var rejectTime = $("#rejects input[name='rejectTime']").val();
			var rejectNumber = $("#rejects input[name='rejectNumber']").val();
			var rejectReason = $("#rejects textarea[name='rejectReason']").val();
			
			if(parseInt(row.number) < parseInt(rejectNumber)){
				$.ligerDialog.warn("报废数量不正确，报废数量不得多于库存数量！");
				return;
			}
			
			var data = {};
			data.id = id;
			data.code = row.code;
			data.ggxh = row.ggxh;
    		data.number = row.number;
    		data.type= row.type;
    		data.replaceUse = row.replaceUse;
    		data.replaceLineUse = row.replaceLineUse;
    		data.supply = row.supply;
    		data.inStoreTime= row.inStoreTime;
			data.rejectTime = rejectTime;
			data.rejectNumber = rejectNumber;
			data.rejectReason = rejectReason;
			data.changeNumber = parseInt(row.number) - parseInt(rejectNumber);
			var param = JSON.stringify(data);
			$.ajax({
				url: basePath+"rest/mauReplacementManageAction/saveRejectData?param=" +param,
				type: "POST",
				dataType: "JSON",
				success:function(map){
					if(map.success != "" && map.success != null){
						$.ligerDialog.success(map.success);
						d.hide();
						gridTwo();
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
    	
    	//点击领料
    	function materialClick(rowIndex){
    		var row = manage.rows[rowIndex];
			$("#materials input[name='code']").val(row.code);
			
			$.ligerDialog.open({
				title : "领料",
				target:$("#materials"),
				height : 320,
				width : 400,
				modal : true,
				buttons: [  { text: '领料', onclick: function (i, d) {saveMaterialData(d,rowIndex);}},
		                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
		                 ] 
			});
    	}
    	
    	function saveMaterialData(d,rowIndex){
    		var row = manage.rows[rowIndex];
			var id = row.id;
			var materialTime = $("#materials input[name='materialTime']").val();
			var materialNumber = $("#materials input[name='materialNumber']").val();
			var materialBy = $("#materials input[name='materialBy']").val();
			var materialUse = $("#materials textarea[name='materialUse']").val();
			
			if(parseInt(row.number) < parseInt(materialNumber)){
				$.ligerDialog.warn("领料数量不正确，数量不得多于库存数量！");
				return;
			}
			
			var data = {};
			data.id = id;
			data.code = row.code;
			data.ggxh = row.ggxh;
    		data.number = row.number;
    		data.type= row.type;
    		data.replaceUse = row.replaceUse;
    		data.replaceLineUse = row.replaceLineUse;
    		data.supply = row.supply;
    		data.inStoreTime= row.inStoreTime;
			data.materialTime = materialTime;
			data.materialNumber = materialNumber;
			data.materialBy = materialBy;
			data.materialUse = materialUse;
			data.changeNumber = parseInt(row.number) - parseInt(materialNumber);
			var param = JSON.stringify(data);
			$.ajax({
				url: basePath+"rest/mauReplacementManageAction/saveMaterialData?param=" +param,
				type: "POST",
				dataType: "JSON",
				success:function(map){
					if(map.success != "" && map.success != null){
						$.ligerDialog.success(map.success);
						d.hide();
						gridTwo();
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
    	
    	//打印excel
    	function printExcel(){
    		
    		
    	}
    	
    	/*暂时没用*/
    	function addrTopAdd(){
    		debugger;
    		var bigHeight = $(window).height();
    		var bigWidth = $(window).width();
    		var top = parseInt(bigHeight) - parseInt($("#add").css("height"))/2;
    		var left = parseInt(bigWidth) - parseInt($("#add").css("width"))/2;
    		$("#add").css("position","fixed");
    		$("#add").css("top",top);
    		$("#add").css("left",left);
    		
    	}
    	
    	function selectType(){
    		$.ajax({
    			type:"post",
    			dataType:"json",
    			url:basePath+'rest/mauReplacementManageAction/getReplacementType',
    			success:function(data){
    				$("#sType").html("");
    				var str="";
    				debugger;	
    				str+="<option value=''>--请选择--</option>";
    				for(var i=0;i<data.length;i++){
    					str+="<option value='"+data[i]+"'>"+data[i]+"</option>";
    				}
    				$("#sType").html(str);
    			}
    			
    			
    		});
    	}
    </script>
    
    <style type="text/css">
    	#top{
    		height:50px;
    		width:100%;
    	}
    	#top label{
    		height:50px;
    		line-height:50px;
    		margin-left:30px;
    	}
    	#top label span{
    		text-size:13px;
    	}
    	#top label input.text{
    		width:180px;
    		height:27px;
    	}
    	#top input.btn{
    		width:80px;
    		height:25px;
    		border:none;
    		background-color: #0099CC;
    		border-radius:5px;
    		cursor: pointer;
    		color: white;
    		margin-left:30px;
    	}
    	#top input.btn:hover{
    		background-color: #6AC6E8;
    	}
    	#top input.showall{
    		width:80px;
    		height:25px;
    		border:none;
    		background-color: #FAA590;
    		border-radius:5px;
    		cursor: pointer;
    		margin-left:30px;
    		color: white;
    	}
    	#top input.showall:hover{
    		background-color: #F5BCAF;
    	}
    	/* 添加 */
    	#add{
			width:100%;
			display: none;
		}
		#add table{
			width:100%;
			margin: 10px auto;
		}
		#add table tr{
			height:50px;
		}
		#add table tr td{
		}
		#add table tr td span{
			display: inline-block;
			width:90px;
			height:40px;
			line-height: 40px;
			text-align: right;
			font-size: 14px;
		}
		#add table tr td input{
			width:180px;
			height:30px;
		}
		
		#head{
			height:70px;
			line-height:50px;
			width:100%;	
			border-bottom: 1px solid black;
		}
		#head li{
			display:inline-block;
			width:30%;
		}
		#head li span{
			display:inline-block;
			width:30%;
			margin:5px;
			text-align:center;
			border-bottom: 1px solid gray; 
			cursor: pointer;
			font-size:15px;
		}
		#head li span:hover{
			background-color: gray;
			color: white;
		}
		#head li span.selected{
			background-color: gray;
			color: white;
		}
		.replace:hover{
			color:red;
			text-decoration: none;
		}
		
		#rejects,#materials{
			display: none;
			width:100%;
			margin:20px auto;
		}
		#rejects table,#materials table{
			width:100%;
		}
		#rejects table tr,#materials table tr{
			height:35px;
		}
		#rejects table tr td span,#materials table tr td span{
			display: inline-block;
			width:120px;
			height:35px;
			line-height: 35px;
			text-align: right;
		}
		#rejects table tr td input,#materials table tr td input{
			height:22px;
			width:150px;
		}
		
		
    </style>
  </head>
  
  <body>
  
	<ul id="head">
		<li></li>
		<li style="width:40%;">
			<span class="selected" id="replace">备件列表</span>
			<span id="reject">报废记录</span>
			<span id="material">领料记录</span>
		</li>
		<li></li>
	</ul>
  
     <div class="l-loading" style="display:block" id="pageloading"></div>
	 <div class="l-clear"></div>
	 <div id="top">
	 	<label>
	 		<span>备件编码：</span>
	 		<input type="text" name="code" class="text" />
	 	</label><!-- 
	 	<label>
	 		<span>备件名称：</span>
	 		<input type="text" name="name" class="text" />
	 	</label> -->
	 	<label>
	 		<span>规格型号：</span>
	 		<input type="text" name="ggxh" class="text" />
	 	</label>
	 	<label>
	 		<span>备件类型：</span>
	 		<select id="sType"></select>
	 	</label>
 		<input type="button" name="btn" class="btn" value="查  询" />
 		<input type="button" name="showall" class="showall" value="显示所有"/>
	 	
	 </div>
     <div id="maingrid"></div>
	   
	 <div style="display:none;"></div>
     
     <div id="add">
  			<table border="0" cellpadding="0" cellspacing="0">
  				<tr>
  					<td>
  						<span>备件编码：</span>
  						<input type="text" name="code"  />
  					</td>
  					<td>
  						<span>规格型号：</span>
  						<input type="text" name="ggxh"  />
  					</td>
  				</tr>
  				<tr>
  					<td>
  						<span>备件类型：</span>
  						<input type="text" name="type"  />
  					</td>
  					<td>
  						<span>数量：</span>
  						<input type="text" name="number"  />
  					</td>
  				</tr>
  				<tr>
  					<td>
  						<span>机器用途：</span>
  						<input type="text" name="replaceUse"  />
  					</td>
  					<td>
  						<span>电线用途：</span>
  						<input type="text" name="replaceLineUse"  />
  					</td>
  				</tr>
  				<tr>
  					<td>
  						<span>供应商：</span>
  						<input type="text" name="supply"  />
  					</td>
  					<td>
  						<span>入库时间：</span>
  						<input type="text" name="inStoreTime"  />
  					</td>
  				</tr>
  			</table>
  			
  			
  		</div>
    	
    	<div id="rejects">
    		<table border="0" cellpadding="0" cellspacing="0">
    			<tr>
    				<td>
						<span>备件编码：</span>
						<input type="text" name="code" readonly="readonly" title="只读" style="background-color: #DBDBDB;"/>	
					</td>
				</tr>
				<tr>
					<td>
						<span>报废数量：</span>
						<input type="text" name="rejectNumber" />	
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
						<span>报废原因：</span>
						<textarea name="rejectReason" style="width:150px;height:60px;"></textarea>	
					</td>
				</tr>
				
			</table>
    	</div>
    	
    	<div id="materials">
    		<table border="0" cellpadding="0" cellspacing="0">
    			<tr>
    				<td>
						<span>备件编码：</span>
						<input type="text" name="code" readonly="readonly" title="只读" style="background-color: #DBDBDB;"/>	
					</td>
				</tr>
				<tr>
					<td>
						<span>领料数量：</span>
						<input type="text" name="materialNumber" />	
					</td>
				</tr>
				<tr>
					<td>
						<span>领料时间：</span>
						<input type="text" name="materialTime" />	
					</td>
				</tr>
				<tr>
					<td>
						<span>领料人：</span>
						<input type="text" name="materialBy" />	
					</td>
				</tr>
				<tr>
					<td>
						<span>领料用途：</span>
						<textarea name="materialUse" style="width:150px;height:60px;"></textarea>	
					</td>
				</tr>
				
			</table>
    	</div>
    	
    	
  </body>
</html>
