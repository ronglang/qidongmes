<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">

		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>plaProductOrderList</title>
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	    
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		
		<script>
			var basePath  = '<%=basePath%>';
			var routeName = "plaProductOrderManage";    
    	</script>
	</head>

	<body style="padding:6px;overflow: scroll;">
		<input id="dec_btn" type="button" value="生产令分解" onclick="decompose()" style="width: 100px">
		<input id="gen_btn" type="button" value="生成计划" onclick="gen()" style="width: 100px">
		<iframe id="if1" style="height: 590px;width:100%;border:1px solid #ccc;" scrolling="no"
			src="<%=basePath%>rest/plaProductOrderManageAction/toOrderTable">
			</iframe>
	</body>
	<script type="text/javascript">
	function gen(){
// 		var manager2 = document.getElementById('if2').contentWindow.$("#maingrid").ligerGetGridManager();
	
		var manager = document.getElementById('if1').contentWindow.$("#maingrid").ligerGetGridManager();
		var li = manager.getSelectedRows();
		var ids = [];
		for(var i = 0;i < li.length;i++){
			ids.push(li[i].id);
		}
		if(ids.length == 0){
			$.ligerDialog.warn("请选则要生成计划的生产令");
			return;
		}
		$.ajax({
		  url: basePath+"rest/schedulingManageAction/scheduling",
		  dataType: 'json',
		  data: "orderIds="+ids,
		  success:function(msg1){
		  	manager.reload();
		  	$("#if2").hide();
		  	if(msg1.success){
		  		$.ligerDialog.success("生成成功");
		  	}else{
		  		$.ligerDialog.error("生成失败");
		  	}
		  }
		});
	}
	function decompose(){
		var manager = document.getElementById('if1').contentWindow.$("#maingrid").ligerGetGridManager();
		var li = manager.getSelectedRows();
		setIframValue(li);
    	//return; 
		var dataArray = [];
    	for(var i = 0; i<li.length;i++){
    		var bean = new Object();
    		var beans = li[i];
    		bean.proGgxh = beans.proGgxh;
    		bean.productPartLen = beans.productPartLen;
    		bean.amount = beans.amount;
    		bean.proColor = beans.proColor;
    		bean.id = beans.id;
    		bean.proCraftCode= beans.proCraftCode;
    		dataArray.push(bean);
    		debugger;
    	}
    	var date = JSON.stringify(dataArray);
    	
    	if(date.length>2){
    		//alert("ture");
    		$('#dec_btn').css('disabled','true');
    		$.ajax({ 
    			url: '<%=basePath %>rest/plaProductOrderManageAction/getDecompose?li='+date,
           		type:"post",
           		dataType:"json",
           		success: function(map){
           			$('#dec_btn').css('disabled','false');
           			if(map!=null)
           	    	if(map.success){
           	    		alert("分解成功");
           	    	}else{
           	    		//alert("分解失败");
           	    		$.ligerDialog.error(map.msg);
           	    		$("#if2").hide();
           	    		debugger;
           	    	}
           	    },
           	    error : function() {
           	    	$('#dec_btn').css('disabled','false');
           	    	$.ligerDialog.error("发生了未知错误,分解失败!");
    			}
    		});
    	}else{
    		$.ligerDialog.warn("请选择需要分解的行");
    	}
	}
	
	function setIframValue(li){
		var ur = basePath+"rest/schedulingManageAction/plaWeekSeqHours?";
		var ids = [];
		for(var i = 0;i < li.length;i++){
			ids.push(li[i].id);
		}
		ur += "li="+ids;
		$("#if2").css("display","block");
		$("#if2").attr("src",ur);
	}
	</script>
</html>

