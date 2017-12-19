<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>成品查询</title>
    
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
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <link href="<%=basePath %>app/css/totalQuery/query.css" rel="stylesheet" type="text/css" />
    
    <style type="text/css">
    	#sort:hover{
    		background-color: #48B9E3;
    	}
    	#showall{
    		border:none;
    		margin-left:20px;
    		background-color: #FFAB95;
    		color:white;
    		width:80px;
    		height:25px;
    		border-radius:5px;
    		cursor: pointer;
    	}
    	
    	#showall:hover{
    		background-color: #F5BFB2;
    	}
    </style>
    <script type="text/javascript">
    var basePath = "<%=basePath%>";
    var data = {};
	$(document).ready(function(){
		
		grid();
		
		$("#over_product").click(toOverProduct);
		$("#raw_material").click(toRawMaterial);
		$("#semi_over_product").click(toSemiOverProduct);
		var ggxh = "";
		var color = "";
		$("#sort").click(function(){
			//debugger;
			ggxh = $("#ggxh").val();
			color = $("#color").val();
			if(ggxh == "" && color == ""){
				return;
			}
			if(ggxh != ""){
				data.ggxh = ggxh;
			}
			if(color != ""){
				data.color = color;
			}
			grid();
		});
		
		//显示所有
		$("#showall").click(function(){
			data.ggxh = null;
			data.color = null;
			
			grid();
		});
		
		$("#export").on("click",function(){
				var ggxh = $("#ggxh").val();
				var color = $("#color").val();
				data.type = '成品';
				data.focus = '库存';
				var param = JSON.stringify(data);
			var jsonQuery="ggxh="+ggxh+"&color="+color;
			var	url = basePath+"rest/statStoreObjVoManageAction/exportExecl"+ "?" + jsonQuery+"&param="+param;
			window.open(url,"_self");
		});
	});
	
	function grid(){
		debugger;
		data.type = '成品';
		data.focus = '库存';
		var param = JSON.stringify(data);
		window['g'] =
	    $("#maingrid").ligerGrid({
	        height: '90%',
	        title:'仓库查询  &rarr; 库存查询  &rarr; 成品列表(当前库存)',
	        url: basePath+'rest/statStoreObjVoManageAction/getAllStore?param='+param,
	       	checkbox: true,
	        columns: [
    			{ display: '成品规格', name: 'ggxh'},
    	        { display: '成品名称', name: 'name2'},
    	        { display: '成品颜色', name: 'color'},
    	        { display: '库存数量', name: 'allcount'},
    	        { display: '单位', name: 'unit'},
    	        /*{ display: '详情',
    	        	render: function (rowdata, rowindex, value)
                    {
                        var h = "";
                            h += "<a href='javascript:beginEdit(" + rowindex + ")'>出库</a>&nbsp;&nbsp;";
                            h += "<a href='javascript:deleteRow(" + rowindex + ")'>入库</a> ";
                        return h;
                    }	
    	        },*/
    	        
    	        { hide:'id',name:'id',width:1}
	        ],
	        rownumbers: true,
	        enabledEdit: true
	    });
	    $("#pageloading").hide();
	}
	

	
	function toOverProduct(){
		window.location.href = basePath+"rest/statStoreObjVoManageAction/overProduct";
	}
		
	function toRawMaterial(){
		window.location.href = basePath+"rest/statStoreObjVoManageAction/rarMaterial";
		
	}
	
	function toSemiOverProduct(){
		window.location.href = basePath+"rest/statStoreObjVoManageAction/semiOverProduct";
	}
	
	function exportExcel(){
		var params = {};
		/* var ggxh = ; */
		$.post('<%=basePath %>rest/statStoreObjVoManageAction/exportE',{param:'{}'},function(data){
			
		},"json");
	}
	
	</script>
	
  </head>
  
  <body>
  
    <div class="l-loading" style="display:block" id="pageloading"></div>
	<div class="l-clear"></div>
	
	<div id="t_select" style="margin-top:0px;">
		
		<ul class="nav">
	        <li class="drop-down">
	        	 <a><span id="over_product">成品查询</span><span class="arrow-down"></span></a>
	             <ul class="drop-down-content">
	                 <li><a id="raw_material">原材料查询</a></li>
	                 <li><a id="semi_over_product">半成品查询</a></li>
	             </ul>
	        </li>
     	</ul>
		<div class="con">
			<div class="con_div">
				<span>成品规格：</span><input type="text" name="" style="margin-right:20px" id="ggxh">
				<span>成品颜色：</span><input type="text" name="" id="color"/>
				<input type="button" name="" id="sort" value="查  询" />
				<input type="button" name="" id="showall" value="显示所有"/>
			</div>
			<div class="con_ex">
				<button id="export" onclick="exportExcel()"></button>
			</div>
			
		</div>
		
	</div>
    <div id="liger">
    	<div id="maingrid"></div>
    </div>
    
    
	<div style="display:none;"></div>
	
  </body>
</html>
