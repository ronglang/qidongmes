<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/json2.js" type="text/javascript"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    <script type="text/javascript">
   var url,grid1;
        $(function(){
        	url = '<%=basePath %>rest/mauMachineManageAction/getMachineGrid';
        	grid(url);
        	form();
        });
		function grid(url){
			grid1 =
            $("#maingrid").ligerGrid({
                height: '98%',
                title:'生产机台',
                url: url,
                columns: [
                { display: '机台编码', name: 'macCode' },
                { display: '机台名称', name: 'macName'},
                { display: '机台状态', name: 'macState', minWidth: 140,render:function(row){
                	var html = ""; //运行、关机、故障、保养
                	if(row.macState =="故障"){
                		html = "<span style='color:#EE2C2C'>"+row.macState+"</span>";
                		return html;
                	}else if(row.macState =="运行"){
                		html = "<span style='color:#00CD00'>"+row.macState+"</span>";
                		return html;
                	}else if(row.macState =="关闭"){
                		html = "<span style='color:#BABABA'>"+row.macState+"</span>";
                		return html;
                	}else if(row.macState =="保养"){
                		html = "<span style='color:#6959CD'>"+row.macState+"</span>";
                		return html;
                	}
                	
                	
                } },
                { display: '机台所属工序', name: 'seqCode' },
                { display: '出线最小直径', name: 'diameterMin' },
                { display: '出线最大直径', name: 'diameterMax' },
                { display: '出线数', name: 'outletCount' },
               /*  { display: '使用材料', name: 'usrMater' }, */
                { display: '备注', name: 'remark' },
                { hide:'id',name:'id',width:1}
                ],
                toolbar: {
                    items: [
                    { text: '修改', click: updateState, icon: 'modify' },
                    { line: true },
                    { text: '增加', click: addMachine, icon: 'add' },
                    { line: true },
                    { text: '删除', click: delMahcine, icon: 'delete' },
                    { line: true },
                    ]
                },
                rownumbers: true,
            });
            $("#pageloading").hide();
		}
		
		function addMachine(){
			$.ligerDialog.open({

		        height: 600,

		        width: 1000,

		        title: '增加机台信息',

		        url: "<%=basePath %>rest/mauMachineManageAction/toDetailPage",

		        showMax: false,

		        showMin: false,

		        isResize: true,

		        slide: false,

		        name:'repairAgain'


		    });
		}
		
		
		function updateState(){
			var manager = $("#maingrid").ligerGetGridManager();
        	var li = manager.getSelectedRow();
        	if(li==null){
        		$.ligerDialog.warn("请选择一行修改");
        		return;
        	}
        		var id = li.id;
        		$.ligerDialog.open({

    		        height: 600,

    		        width: 1000,

    		        title: '修改机台信息',

    		        url: "<%=basePath %>rest/mauMachineManageAction/toDetailPage?id="+id,

    		        showMax: false,

    		        showMin: false,

    		        isResize: true,

    		        slide: false,

    		        name:'repairAgain'
    		    });
        		
        		
			
		}
		
		function delMahcine(){
			var manager = $("#maingrid").ligerGetGridManager();
        	var li = manager.getSelectedRows();
        	if( li.length<1 ){
        		$.ligerDialog.warn("请选择删除行");
        		return;
        	}
        	var ids = [];
        	for(var i = 0; i<li.length;i++){
	        	var bean = new Object();
        		var beans = li[i];
        		bean.id = beans.id;
        		bean.macCode = beans.macCode;
        		ids.push(bean);
        	}
        	var idArray = JSON.stringify(ids);
        	$.ligerDialog.confirm('是否确定删除', function (yes) {
        		if(yes){
        			$.ajax({
	            		url:'<%=basePath %>rest/mauMachineManageAction/delMahcine?ids='+idArray,
	            		type:'POST',
	            		dataType:'JSON',
	            		success:function(map){
	            			debugger;
	            			if(map.success){
		            			$.ligerDialog.success('删除成功');
		            			url = '<%=basePath %>rest/mauMachineManageAction/getMachineGrid';
		            			grid(url);
	            			}else{
	            				$.ligerDialog.error('发生了未知错误');
	            			}
	            		},
	            		error:function(){
	            			$.ligerDialog.error('发生了未知错误');
	            			//grid();
	            		}
	            		
	            	}); 
        		}else
        			return;
        	});
		}
		
		function form(){
        	
	       	 //创建表单结构 
	           form = $("#form2").ligerForm({
	               inputWidth: 200, labelWidth: 65, space: 100,
	               fields: [
	               	{ display: "机台编码", name: "macCode", newline: false, type: "text"},
	               ],
	               validate  : true
	           });
	       	
	       }
		
		
		function search(){
		       	var macCode= $("[name=macCode]").val();
				url = '<%=basePath %>rest/mauMachineManageAction/getMachineGrid?macCode='+encodeURIComponent(encodeURIComponent(macCode));
				grid(url);
	    }
		
    </script>
</head>
<body style="overflow-x:hidden; padding:2px;">
<div class="l-loading" style="display:block" id="pageloading"></div>
 <div class="l-clear"></div>
	<div style="text-align: center;width: 100%;position: relative;;"  >
		<form id="form2" action="" >
		</form>
		<div class="liger-button" onclick="search()" style="float: right;position: absolute;left: 300px;top: 3px; ">查询</div>
	</div>
    <div id="maingrid"></div>
  <div style="display:none;">
  
</div>
</body>
</html>


