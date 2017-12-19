<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>设置工序对应机台参数</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	
	<script type="text/javascript">
	var basePath = "<%=basePath%>";
		$(function(){
			//查询此工序的所有机台信息
			//showSeqMacInfo();
			//选中后查询显示对应机台所有参数信息
			showMacAllParam();
			
		
			
		});
		
		//查询此工序的所有机台信息
		function showSeqMacInfo(){
			var seqCode = '${seqCode}';
			$.ajax({
				url:basePath + "rest/mauNewDislayManageAction/getSeqMacInfo?seqCode="+seqCode,
				type: "post",
				dataType: "JSON",
				success: function(list){
					var data = eval("("+list+")");
					showAllMachine(data);
				}
				
			});
			
		}
		//显示此工序下的所有机台
		function showAllMachine(list){
			$("#main .top select").html("");
			var html = "";
			html += "<option>---请选择---</option>";
			for(var i=0;i<list.length;i++){
				html += "<option value='"+list[i].macCode+"' title='"+list[i].macName+"'>"+list[i].macName+"-"+list[i].macCode+"</option>";
			}
			$("#main .top select").append(html);
			
		}
		
		//选中后查询显示对应机台所有参数信息
		/*
		function showMacAllParam(){
			$("#main .top select").change(function(){
				var macCode = $("#main .top select").val();
				$.ajax({
					url: basePath + "rest/mauNewDislayManageAction/getMacParam?macCode="+macCode,
					type: "post",
					dataType: "JSON",
					success:function(list){
						var data = eval("("+list+")");
						showMacParam(data);
					},
					
					
				});
				
			});
			
		}
		*/
		function showMacAllParam(){
				var macCode = '${macCode}';
				$.ajax({
					url: basePath + "rest/mauNewDislayManageAction/getMacParam?macCode="+macCode,
					type: "post",
					dataType: "JSON",
					success:function(list){
						var data = eval("("+list+")");
						showMacParam(data);
					},
					
					
				});
				
		}
		
		//将所有参数显示在页面是绗棉供选择
		function showMacParam(list){
			$("#param").html("");
			var html = "";
			if(list.length > 0){
				html += "<ul>";
				for(var i =0;i<list.length;i++){
					html += "<li>";
					if(list[i].isShow == '1'){
						html += "<input type='checkbox' checked='checked' name='param' value='"+list[i].value+"' /> <span>"+list[i].value+"</span>";
					}else{
						html += "<input type='checkbox'  name='param' value='"+list[i].value+"' /> <span>"+list[i].value+"</span>";
					}
					
					html += "</li>";
				}
				html += "</ul>";
				html += "<div class='btn'><span class='res' onclick='clickReturn()'>返回</span><span class='sub' onclick='clickGetParam()'>提交</span></div>";
			}else{
				html += "<p style='text-align:center;color:red'>暂无参数</p>";
			}
			
			$("#param").append(html);
			
		}
		//点击提交
		function clickGetParam(){
			var params = $("#param ul input:checkbox[name=param]:checked");
			debugger;
			var list = new Array();
			for(var i=0;i<params.length;i++){
				list[i] = params[i].value;
			}
			
			var map = {};
			map.seqName = $("#seqName").html();
			map.params = JSON.stringify(list);
			//map.macCode = $("#main .top select").val();
			//var macName = $("#main .top select option:selected").attr("title");
			map.macCode = $("#macCode").html();
			var macName = '${macName}';
			debugger;
			macName = macName.replace(/#/g,"");
			map.macName = JSON.stringify(macName);
			var str = JSON.stringify(map);
			//把%转16进制 发送
			str=str.replace(/\%/g,"%25");
			str=str.replace(/\#/g,"%23");//#
			str=str.replace(/\#/g,"%26");//&
			
			window.open(basePath + "rest/mauNewDislayManageAction/toNewKanBan?map="+str);
			
		}
		
		function clickReturn(){
			window.history.back();
		}
		
	</script>
	<style type="text/css">
		body{
			margin: 0;
			padding: 0;
			font-size: 14px;
		}
		ul{
			list-style-type: none;
		}
		ul li{
			float: left;
		}
		#main{
			margin: 0 auto;
			width: 100%;
		}
		#main .head{
			height:50px;
			line-height:50px;
			width: 100%;
			text-align: center;
			font-size:20px;
		}
		#main .top{
			width:100%;
			margin:0 auto;
			text-align: center;
		}
		#main .top select{
			width:150px;
			height:25px;
		}
		#param ul{
			width:90%;
			margin: 10px auto;
		}
		#param ul li{
			width:300px;
			height:40px;
			line-height:40px;
			display:inline-block;
		}
		#param .btn{
			width:100%;
			margin: 20px auto;
			padding-top:30px;
			text-align: center;
			clear: both;
		}
		#param .btn span.res{
			margin:0 10px;
			display:inline-block;
			width:100px;
			text-align:center;
			height:27px;
			line-height: 27px;
			border-radius:5px;
			background-color: #D68226;
			color:white;
			cursor: pointer;
		}
		#param .btn span.sub{
			margin:0 10px;
			display:inline-block;
			width:100px;
			text-align:center;
			height:27px;
			line-height: 27px;
			border-radius:5px;
			background-color: #1F62A0;
			color:white;
			cursor: pointer;
		}
		#param .btn span.sub:hover{
			background-color: #6489AF;
		}
	</style>
  </head>
  
  <body>
    
    <div id="main">
    	<div class="head">设置显示<font color="#F04D7E" size="4" id="seqName">${seqName }</font><font color="#F04D7A" size="5" id="macCode">${macCode }</font>机台参数</div>
    	<!-- 
    	<div class="top">
    		<span>请选择：</span><select name="machine"></select>
    	</div>
    	 -->
    	<div id="param">	
    	</div>
    </div>
    
    
  </body>
</html>
