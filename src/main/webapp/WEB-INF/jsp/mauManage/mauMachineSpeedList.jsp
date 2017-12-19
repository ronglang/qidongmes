<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>机台支持的产品规格型号与对应的生产速度</title>
    
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
    
        $(function(){
        	grid();
        	
        	
        });
        
        function addForm(){
        	var basePath = '<%=basePath%>';
        	top.$.ligerDialog.open({ 
        		
        		url: basePath+"rest/mauMachineSpeedManageAction/toAddDataPage", 
        		height: 600,
        		width: 1080,
        		modal:true, 
        		title:"添加"
        		});
        	
        }
        
		function grid(){
			
			window['g'] =
            $("#maingrid").ligerGrid({
                height: '99%',
                title:'生产速度',
                url: '<%=basePath %>rest/mauMachineSpeedManageAction/getMauMachineSpeed',
               	checkbox: true,
                columns: [
                { display: '机台id', name: 'machineId',editor:{type:'text'}},
                { display: '产品规格型号', name: 'proGgxh',editor:{type:'text'} },
                { display: '*标准生产速度', name: 'speed',color:'red',
                	totalSummary:{
                    	type:'sum,count,max,min,avg'
                	},editor:{type:'text'}},
                { display: '单位', name: 'unit' },
                { display: '*准备时间(h)', name: 'readyTime',
                	render:function(rowData){
                		return "<span style='color:red'>"+rowData.readyTime+"</span>";
                	}
                	,editor:{type:'text'} },
                { display: '*放线上盘时间(h)', name: 'upTime' ,editor:{type:'text'}},
                { display: '*收线上盘时间(h)', name: 'downTime',editor:{type:'text'}},
                { hide:'id',name:'id',width:1}
                ],
                toolbar: {
                    items: [
                    { text: '保存', click: save, icon: 'save' },
                    { text: '增加', click: addRows, icon: 'add'},
                    { line: true }	
                    ]
                },
                rownumbers: true,
                enabledEdit: true
            });
            $("#pageloading").hide();
		}
		
		function addRows(){
			var manager = $("#maingrid").ligerGetGridManager();
			var row = manager.getSelectedRow();
			manager.addRow({
				machineId:'',
				proGgxh:'',
				speed:'',
				unit:'m/h',
				readyTime:'0.3',
				upTime:'0.3',
				downTime:'0.2',
			});
		}
		
		
		
		
		function save(){
			var manager = $("#maingrid").ligerGetGridManager();
        	var li = manager.getSelectedRows();
        	var dataArray = [];
        	if(li.length<1){
        		alert("请选择保存的行！");
        		return;
        	}
        	for(var i = 0; i<li.length;i++){
        		var bean = new Object();
        		var beans = li[i];
        		debugger;
        		bean.machineId = beans.machineId;
        		bean.proGgxh = beans.proGgxh;
        		bean.speed = beans.speed;
        		bean.unit = beans.unit;
        		bean.readyTime = beans.readyTime;
        		bean.upTime = beans.upTime;
        		bean.downTime = beans.downTime;
        		bean.id = beans.id;
        		dataArray.push(bean);
        	}
        	var data = JSON.stringify(dataArray);
        	//alert(data);
        	$.ajax({ 
        		url:'<%=basePath %>rest/mauMachineSpeedManageAction/saveMauMachineSpeed',
           		type:"post",
           		dataType:'json',
           		data:"li="+data,
           		success: function(map){
           			//debugger;
           			if(map.success){
           	    		alert(map.msg);
           		 		location.reload();
           			}else{
           				alert(map.msg+"保存失败");
           			}
           	    },
           	    error : function() {
    				alert("保存失败");
    			}
    		});
        	
		}
		
    </script>
  </head>
  
  <body style="overflow-x:hidden; padding:2px;">
   <div class="l-loading" style="display:block" id="pageloading"></div>
	 <div class="l-clear"></div>
	
	    <div id="maingrid"></div>
	   
	  <div style="display:none;">
	  
	</div>
    
	
  </body>
</html>
