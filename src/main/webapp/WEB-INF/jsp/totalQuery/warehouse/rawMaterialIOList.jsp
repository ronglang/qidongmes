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
    		background-color: #50C1E8;
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
    		background-color: #F7C6B9;
    	}
    </style>
    <script type="text/javascript">
    var basePath = "<%=basePath%>";
    var data = {};  //列表的全局参数
    var arr = {};   //详情的全局参数
	$(document).ready(function(){
		
		$("#start").val(time.getPreDate(7));
		$("#end").val(time.getPreDate(0));
		
		$("#start").ligerDateEditor(
				{format:'yyyy-MM-dd hh:mm:ss',showTime:true,width:180,height:25}
				);
		$("#end").ligerDateEditor({format:'yyyy-MM-dd hh:mm:ss',showTime:true,width:180,height:25});
		
		
		grid();
		
		$("#over_product").click(toOverProduct);
		$("#raw_material").click(toRawMaterial);
		$("#semi_over_product").click(toSemiOverProduct);
		var ggxh = "";
		var color = "";
		var name = "";
		$("#sort").click(function(){
			//debugger;
			data = {};
			ggxh = $("#ggxh").val();
			name = $("#name").val();
			color = $("#color").val();
			
			if(name != ""){
				data.name = name;
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
			data.name = null;
			grid();
		});
		
		$("#export").on("click",function(){
			var ggxh = $("#ggxh").val();
			var color = $("#color").val();
			var start= $("#start").val();
			var end =$("#end").val();
			var matName=$("#name").val();
			data.type = '原材料';
			data.focus = '出入库';
			var param = JSON.stringify(data);
		var jsonQuery="ggxh="+ggxh+"&color="+color+"&start="+start+"&end="+end+"&matName="+matName;
		var	url = basePath+"rest/statStoreObjVoManageAction/exportExecl"+ "?" + jsonQuery+"&param="+param;
		window.open(url,"_self");
	});
	});
	
	function grid(){
		debugger;
		data.start = $("#start").val();
		data.end = $("#end").val();
		data.type = '原材料';
		data.focus = '出入库';
		var param = JSON.stringify(data);
		window['g'] =
	    $("#maingrid").ligerGrid({
	        height: '90%',
	        title:'仓库查询  &rarr; 出入库查询  &rarr; 原材料列表(一段时间出入库统计，默认7天)',
	        url: basePath+'rest/statStoreObjVoManageAction/getAllStore?param='+param,
	       	checkbox: true,
	        columns: [
    			{ display: '原材料规格', name: 'ggxh'},
    	        { display: '原材料名称', name: 'name2'},
    	        { display: '原材料颜色', name: 'color'},
    	        { display: '出库数量', name: 'outcount'},
    	        { display: '入库数量', name: 'incount'},
    	        { display: '单位', name: 'unit'},
    	        { display: '详情',
    	        	render: function (rowdata, rowindex, value)
                    {
    	        		var h = "";
                    	h += "<a href='javascript:outStatDetail(" + rowindex + ")'>出库</a>&nbsp;&nbsp;&nbsp;&nbsp;";
                        h += "<a href='javascript:inStatDetail(" + rowindex + ")'>入库</a>";
                    	return h;
                    }	
    	        },
    	        
    	        { hide:'id',name:'id',width:1}
	        ],
	        rownumbers: true,
	        enabledEdit: true
	    });
	    $("#pageloading").hide();
	}
	
	function outStatDetail(rowindex){
		var manager = $("#maingrid").ligerGetGridManager();
		var li = manager.data.Rows[rowindex];
		arr.type = '原材料';
		arr.inout = '出库';
		arr.ggxh = li.ggxh;
		arr.color = li.color;
		
		start = $("#start").val();
		end = $("#end").val();
		if(start != "" && end != ""){
			arr.start = start;
			arr.end = end;
		}else{
			$.ligerDialog.warn("时间段不能为空！");
		}
		var param2 = JSON.stringify(arr);
		//debugger;
		$.ligerDialog.open({
	        height: 500,
	        width: 1000,
	        title: '原材料出入库详情',
	        url: basePath+"rest/statStoreObjVoManageAction/toStatDetail?param="+param2,
	        showMax: false,
	        showMin: false,
	        isResize: true,
	        slide: false,
	        name:'repairAgain',
	        buttons: [
	            { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
	            
	            ]

	    });
		
	}
	
	function inStatDetail(rowindex){
		var manager = $("#maingrid").ligerGetGridManager();
		var li = manager.data.Rows[rowindex];
		arr.type = '原材料';
		arr.inout = '入库';
		arr.ggxh = li.ggxh;
		arr.color = li.color;
		
		start = $("#start").val();
		end = $("#end").val();
		if(start != "" && end != ""){
			arr.start = start;
			arr.end = end;
		}else{
			alert("时间段不能为空！");
		}
		var param = JSON.stringify(arr);
		//debugger;
		$.ligerDialog.open({
	        height: 500,
	        width: 1000,
	        title: '成品入库详情',
	        url: basePath+"rest/statStoreObjVoManageAction/toStatDetail?param="+param,
	        showMax: false,
	        showMin: false,
	        isResize: true,
	        slide: false,
	        name:'repairAgain',
	        buttons: [
	            { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
	            
	            ]

	    });
	}
	
	
	function toOverProduct(){
		window.location.href = basePath+"rest/statStoreObjVoManageAction/overProductIO";
	}
		
	function toRawMaterial(){
		window.location.href = basePath+"rest/statStoreObjVoManageAction/rarMaterialIO";
	}
	
	function toSemiOverProduct(){
		window.location.href = basePath+"rest/statStoreObjVoManageAction/semiOverProductIO";
	}
	
	
	
	var time={  
          getPreDate:function(pre){  
              var self=this;  
              var c = new Date();  
              c.setDate(c.getDate() - pre);  
              return self.formatDate(c);  
          },  
          formatDate:function(d){  
        	  var n = new Date();
              var self=this;
              return d.getFullYear() + "-" + self.getMonth1(d.getMonth()) + "-" + 
              d.getDate()+ " " + n.getHours() + ":" + 
              n.getMinutes() + ":" + n.getSeconds();  
          },
          getMonth1:function(m){
              m++;  
              if(m<10)  
                  return "0" + m.toString();  
              return m.toString();  
          }  
      };
	
	
	</script>
	
  </head>
  
  <body>
  
    <div class="l-loading" style="display:block" id="pageloading"></div>
	<div class="l-clear"></div>
	
	<div id="t_select" style="margin-top:0px;">
		
		<ul class="nav">
	        <li class="drop-down">
	        	 <a><span id="raw_material">原材料查询</span><span class="arrow-down"></span></a>
	             <ul class="drop-down-content">
	                 <li><a id="over_product">成品查询</a></li>
	                 <li><a id="semi_over_product">半成品查询</a></li>
	             </ul>
	        </li>
     	</ul>
		<div class="con" style="height:100px;">
			<div class="con_div">
				<span>原材料规格：</span><input type="text" name="" style="margin-right:20px" id="ggxh">
				<span>原材料名称：</span><input type="text" name="" style="margin-right:20px" id="name"/>
				<span>原材料颜色：</span><input type="text" name="" id="color"/><br/>
				<span>时间段：</span><input type="text" name="" id="start" value="">
				<span>—</span>
				<input type="text" name="" id="end"/>
				<input type="button" name="" id="sort" value="查  询" />
				<input type="button" name="" id="showall" value="显示所有" />
			</div>
			<div class="con_ex">
				<button id="export"></button>
			</div>
			
		</div>
		
	</div>
    <div id="liger" style="margin-top:40px;">
    	<div id="maingrid"></div>
    </div>
    
    
	<div style="display:none;"></div>
	
  </body>
</html>
