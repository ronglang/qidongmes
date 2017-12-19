<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <script type="text/javascript">
    
    var batchCode;
    var objSort;
    var objGgxh;
    var address;
        function itemclick(item)
        {
            if(item.text=="增加"){
            	alert("执行增加");
            }
            if(item.text=="修改"){
            	alert("修改了");
            }
            if(item.text=="删除"){
            	alert("删除掉了");
            }
            if(item.text=="查询"){
            	$.ligerDialog.open({url: 'search.html', height: 400,width:400 ,isResize: true }).show();
            }
        }
        $(function ()
        {
        	var url = '<%=basePath %>rest/storeObjManageAction/getStoreObjDate';
            getTable(url);
            form();
            //getGgxh();
        });
        
        function getTable(url){
        	window['g'] =
                $("#maingrid").ligerGrid({
                	url:url,
                    height: '93%',
                    columns: [
                    { display: '批次号', name: 'batchCode', align: 'left'},
                    { display: 'RFID编号', name: 'rfidCode'},
                    { display: '物料名称', name: 'objName'},
                    { display: '型号', name: 'objGgxh' },
                    { display: '数量',name:'acount'},
                    { display: '单位',name:'unit',width:'5%'},
                    { display: '位置',name:'address'},
                    { display: '入库时间',name:'inDate'},
                    { display: '供货商',name:'supplyAgent'}
                    ],  
                    rownumbers: true
                });
                $("#pageloading").hide();
        }

        function deleteRow()
        {
            g.deleteSelectedRow();
        }
        
        var groupicon = "";
        var form;
       
        
        function form(){
        	
        	 //创建表单结构 
            form = $("#form2").ligerForm({
                inputWidth: 120, labelWidth: 65, space: 10,
                fields: [
                { display: "入库日期 ", name: "time1", newline: false, type: "date", validate: {required: true,minlength: 5 }},
                { display: "到 ", name: "time2", newline: false, type: "date", validate: {required: true,minlength: 5 } },
              	{ display: "批次号", name: "batchCode", newline: false,type: "select",editor: {url:'<%=basePath %>rest/storeObjManageAction/getBatchCode',valueField:'text',data:batchCode}},
              	{ display: "物料名称", name: "objName", newline: false,type: "select",editor: {url:'<%=basePath %>rest/storeObjManageAction/getObjSort',valueField:'text',data:objSort}},
              	{ display: "型号", name: "objGgxh", newline: false, type: "select",editor: {url:'<%=basePath %>rest/storeObjManageAction/getObjGgxh',valueField:'text',data:objGgxh}},
              	{ display: "位置信息", name: "address", newline: false,type: "select",editor: {url:'<%=basePath %>rest/storeObjManageAction/getAddress',valueField:'text',data:address}},
                ],
                validate  : true
            });
        	
        }
        
        function search(){
        	//form.valid();
        	var time1= $("[name=time1]").val();
        	var time2= $("[name=time2]").val();
			var batchCode=$("[name=batchCode]").val();
			var objSort=$("[name=objName]").val();
			var objGgxh=$("[name=objGgxh]").val();
			var address=$("[name=address]").val();
			url = '<%=basePath %>rest/storeObjManageAction/getStoreObjDate?time1='+time1+'&time2='+time2+'&batchCode='+batchCode+'&objSort='+objSort+'&objGgxh='+objGgxh+'&address='+address;
			getTable(url);
         }
        
        
        
        
    </script>
</head>
<body style="overflow-x:hidden; padding:2px;">
<div class="l-loading" style="display:block" id="pageloading"></div>
 <div class="l-clear"></div>
	<div style="text-align: center;width: 100%;position: relative;;"  >
		<form id="form2" action="" >
		</form>
		<div class="liger-button" onclick="search()" style="float: right;position: absolute;right: 20px;top: 3px; ">搜索</div>
	</div>
    <div id="maingrid" style="overflow: hidden;">
    	
    </div>
   
  <div style="display:none;">
  
</div>
</body>
</html>

