<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String id = request.getParameter("id");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
    <script type="text/javascript">
    var row_id = <%=id%>;
    var routeName = 'mauMachineManage';
    var form,grid,url;   
    var unit = [
                {id:'1',text:'m' },
                {id:'2',text:'kg'}
                ];
    $(function ()
        {
    	if(row_id){
 			form();
    		setDatas(row_id);	
    		debugger;
    	}else{
    		url ="";
	    	form();
	    	grid(url);
    	}
        });
		
    function form(){
    	form = $("#mauMachineManage").ligerForm({
            inputWidth: 150, labelWidth: 100, space:10,
            fields: [
            	{ display: "机台编码", name: "macCode", newline:true,type: "text" ,validate: { required: true, minlength: 1 }},
            	{ display: "机台名称", name: "macName", newline:false,type: "text" ,validate: { required: true, minlength: 1 }},
            	{ display: "机台所属工序", name: "seqCode", newline:false,type: "select",editor: {url:'<%=basePath %>rest/mauMachineManageAction/getSeqName' ,valueField:'text'},validate: { required: true, minlength: 1 }},
            	{ display: "最大直径(mm)", name: "diameterMax", newline:true,type: "number",validate: { required: true, minlength: 1 }},
            	{ display: "机台状态", name: "macState", newline:false,type: "select",editor: {url:'<%=basePath %>rest/mauMachineManageAction/getMachineState' ,valueField:'text'},validate: { required: true, minlength: 1 }},
            	{ display: "最小直径(mm)", name: "diameterMin", newline:false,type: "number",validate: { required: true, minlength: 1 }},
            	{ display: "出线数(根)", name: "outletCount", newline:true,type: "int"},
            	{ display: "备注", name: "remark", newline:false,type: "text"},
            ],
            validate: true
        });
    }
    
    function grid(){
    	grid = $("#maingrid").ligerGrid({
    		height: '95%',
            title:'机台系数',
            url: url,
            columns: [
            	{ display: "机台编码", name: "macCode",editor:{type:"text"}},
            	{ display: "机台速度系数", name: "quotiety",editor:{type:"number"}},
            	{ display: "机台速度(m/min)", name: "speed",editor:{type:"number"}},
            	{ display: "使用材料", name: "mater",editor:{type:"text"}},
            	{ display: "目标线径(mm)", name: "targetDiameter",editor:{type:"number"}},
            	{ hide: "id", name: "id",width:1},
            ],
            toolbar: {
                items: [
                { text: '增加', click: addQuatiety, icon: 'add' },
                { line: true }
                ]
            },
            enabledEdit:true,
            usePager:false
        });
    }
    
   function addQuatiety(){
	   grid.addRow({
		   macCode:$("[name=macCode]").val()
	   });
   }
   
   function getGrid(){
	  return grid.getData();
   }
    
    function setDatas(row_id){
  	   $.ajax({
     		url:'<%=basePath %>rest/mauMachineManageAction/getMachine?id='+row_id,
     		type:'POST',
     		dataType:'JSON',
     		success:function(mac){
     			form.setData(mac);
     			url = "<%=basePath %>rest/mauSpeedQuotietyManageAction/queryMauSpeedQuotiety?macCode="+mac.macCode;
     			grid(url);
     		},
     		error:function(){
     			$.ligerDialog.error("发生了未知错误");
     		}
     		
     	});
  	   
  	   
     }
    
    function valids(){
    	var va = form.valid();
    	return va;
    }
    
    function getDatas(){
    	var va = valids();
    	var quotiety = getGrid();
    	var datas = form.getData();
    	datas.id = row_id;
    	var data = JSON.stringify(datas);
    	var data1 = JSON.stringify(quotiety);
    	debugger;
    	if(!va){
    		$.ligerDialog.warn("请选择将数据填写完整");
    		return ;
    	}
    	$.ajax({
    		url:"<%=basePath %>rest/mauMachineManageAction/saveMachine?data="
    				+encodeURIComponent(encodeURIComponent(data))+"&quotiety="+data1,
    		type:'POST',
    		sync:false,
    		dataType:'JSON',
    		success:function(map){
    			debugger;
    			ajaxSuccess(map);
    			refreshDatagrid();
    			window.close();
    		},
    		error:function(map){
    			ajaxError(map);
    		}
    	});
    }
		
    </script>
</head>
<body style="overflow-x:hidden; padding:2px;">
 <div class="l-clear"></div>
   <form id="mauMachineManage"></form> 
   	<div style="width: 100%;border-top:1px solid #D4D4D4;">>>机台速度系数:</div>
   	
   <div id="maingrid"></div>
   <div style="margin:0px auto;width:250px; clear:both; overflow:hidden; margin-bottom:20px;">
		<input class="l-button" type="button" onclick="getDatas()" value="保存" /> 
	</div>
  <div style="display:none;">
  
</div>
</body>
</html>


