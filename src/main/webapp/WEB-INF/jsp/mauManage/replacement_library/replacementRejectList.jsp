<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>备件报废记录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/json2.js" type="text/javascript"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>

	<script type="text/javascript">
	var basePath = "<%=basePath%>";
		$(function(){
			
			var url = basePath+'rest/mauReplacementManageAction/getRejectList';
    		gridUi(url);
    		
    		//点击切换页面
    		replaceClick();
    		//显示所有
    		$("#top input.showall").click(gridTwo);
    		//查询
    		$("#top input.btn").click(sartch);
			
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
		
		//显示所有
    	function gridTwo(){
    		var url = basePath+'rest/mauReplacementManageAction/getRejectList';
    		gridUi(url);
    	}
		
    	//查询
    	function sartch(){
    		var data = {};
    		var code = $("#top input[name='code']").val();
    		data.code = code;
    		var param = JSON.stringify(data);
    		var url = basePath+'rest/mauReplacementManageAction/getRejectList?param='+param;
    		gridUi(url);
    		
    		
    	}
		
		var manage;
    	function gridUi(url){
    		manage = $("#maingrid").ligerGrid({
    			height: '80%',
    	        title:'备件报废记录',
    	        url: url,
    	       	checkbox: true,
    			columns:[
				    { hide:'id',name:'id',width:1},
    				{display: '备件编码',name:'code'},
    				{display: '备件规格',name:'ggxh'},
    				{display: '报废时间',name:'rejectTime'},
    				{display: '报废数量',name:'rejectNumber'},
    				{display: '报废原因',name:'rejectReason'},
    				/*
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
    				*/
    				
    			],
    			toolbar: {
    	            items: [
    	            //{ text: '增加', click: addRows, icon: 'add'},
    	            //{ text: '修改', click: updateRows, icon: 'modify'},
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
	
	</style>
	
  </head>
  
  <body>
    
    <ul id="head">
		<li></li>
		<li style="width:40%;">
			<span id="replace">备件列表</span>
			<span class="selected" id="reject">报废记录</span>
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
	 	</label>
 		<input type="button" name="btn" class="btn" value="查  询" />
 		<input type="button" name="showall" class="showall" value="显示所有"/>
	 	
	 </div>
     <div id="maingrid"></div>
	   
	 <div style="display:none;"></div>
    
  </body>
</html>
