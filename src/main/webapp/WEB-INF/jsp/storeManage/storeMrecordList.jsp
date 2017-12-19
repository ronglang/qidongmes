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
    var grid;
        $(function ()
        {
        	var url = '<%=basePath %>rest/storeMrecordManageAction/getStoreMrecordByPage';
            getTable(url);
            form();
            //getGgxh();
        });
        
        function getTable(url){
        	grid =
                $("#maingrid").ligerGrid({
                	url:url,
                    height: '93%',
                    title:'盘点管理',
                    checkbox:true,
                    columns: [
                    { display: '物料名称', name: 'materName'},
                    { display: '物料规格型号', name: 'materGgxh'},
                    { display: '物料类型', name: 'materType'},
                    { display: '批次号', name: 'batchCode', align: 'left'},
                    { display: '实际库存',name:'actStock'},
                    { display: '单位',name:'unit'},
                    { display: '位置',name:'address'},
                    { display: '盘点库存',name:'checkStock'},
                    ],
                    toolbar: {
                        items: [
                        { text: '确认盘点', click: sureCheck, icon: 'add' },
                        { line: true },
                        ]
                    },
                    rownumbers: true
                });
                $("#pageloading").hide();
        }
        
        function sureCheck(){
        	var manager = $("#maingrid").ligerGetGridManager();
        	var lis = manager.getSelectedRows();
        	if(lis.length < 1){
        		$.ligerDialog.warn("请选择确认盘点项");
        		return;
        	}
        	var ids = JSON.stringify(lis);
        	$.ajax({
        		url:'<%=basePath %>rest/storeMrecordManageAction/sureCheck?ids='+ids,
        		type:'post',
        		dataType:'JSON',
        		success:function(map){
        			if(map.success){
        				$.ligerDialog.success("确认成功");
	        			var url = '<%=basePath %>rest/storeMrecordManageAction/getStoreMrecordByPage';
	                    getTable(url);
        			}
        		},
        		error:function(map){
        			ajaxError(map);
        		}
        	});
        	
        }
        
        function form(){
        	
        	 //创建表单结构 
            form = $("#form2").ligerForm({
                inputWidth: 120, labelWidth: 90, space: 10,
                fields: [
              	{ display: "批次号", name: "batchCode", newline: false,type: "select",editor: {url:'<%=basePath %>rest/storeMrecordManageAction/getSmBatchCode',valueField:'text'}},
              	{ display: "物料规格型号", name: "materGgxh", newline: false,type: "select",editor: {url:'<%=basePath %>rest/storeMrecordManageAction/getSmMaterGgxh',valueField:'text'}},
                ],
            });
        	
        }
        
        function search(){
			var batchCode=$("[name=batchCode]").val();
			var materGgxh=$("[name=materGgxh]").val();
			url = '<%=basePath %>rest/storeMrecordManageAction/getStoreMrecordByPage?batchCode='+batchCode+'&materGgxh='+materGgxh;
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

