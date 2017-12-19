<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link
	href="<%= basePath%>app/js/sellManage/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%= basePath%>app/js/sellManage/lib/ligerUI/skins/ligerui-icons.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%= basePath%>app/js/sellManage/lib/ligerUI/skins/Gray/css/all.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%= basePath%>app/js/sellManage/lib/jquery/jquery-1.9.0.min.js"
	type="text/javascript"></script>
<script
	src="<%= basePath%>app/js/sellManage/lib/ligerUI/js/core/base.js"></script>
<script
	src="<%= basePath%>app/js/sellManage/lib/ligerUI/js/ligerui.all.js"></script>
<script type="text/javascript">
    var CustomersData = 
    {Rows:[{"scCode":"SC78984",
    "cusShort":"启动",
    "scDate":"2017-4-20",
    "batCode":"1",
    "deliveDate":"2017-8-30"},
    ]};
    
    
    var scCode;
   
    
    
        function itemclick(item)
        {
            if(item.text=="增加"){
            	$.ligerDialog.success("执行增加");
            }
            if(item.text=="修改"){
            	$.ligerDialog.success("修改了");
            }
            if(item.text=="删除"){
            	var selected = g.getSelected();
            	if(!selected){
            		$.ligerDialog.warn("请选择需要删除的行");
            		return ;
            	}
            	var scCode = selected.scCode;
            	window.location.href="<%=basePath%>/rest/sellContractManageAction/delSellContract?scCode="+scCode;
            	alert(scCode);
            }
            if(item.text=="查询"){
            	$.ligerDialog.open({url: '<%=basePath%>/rest/sellContractManageAction/toSearch', height: 400,width:400 ,isResize: true }).show();
            }   
        }
         
        
        $(function ()
        {
            window['g'] =
            $("#maingrid").ligerGrid({
            	url:"<%=basePath%>/rest/sellContractManageAction/querySellContract",
            	width : '99.8%',
                height: '100%',
                columns: [
                { display: 'id', name: 'id', align: 'left', width: '3%', minWidth: 10 },
                { display: 'createDate', name: 'createDate' },
                { display: 'createBy', name: 'createBy' },
                { display: 'scCode', name: 'scCode' },
                { display: 'scContent',name:'scContent'},
                { display: 'remark',name:'remark'},
                { display: 'scDate',name:'scDate'},
                { display: 'scMoney',name:'scMoney'},
                { display: 'firstParty',name:'firstParty'},
                { display: 'secondParty',name:'secondParty'},
                { display: 'agentBy',name:'agentBy'},
                { display: 'cusCode',name:'cusCode'},
                { display: 'deliveDate',name:'deliveDate'}
                ], 
                pageSize: 10, 
                rownumbers: true, 	
                toolbar: {
                    items: [
                    
                    { text: '增加', click: itemclick, icon: 'add' },
                    { line: true },
                    { text: '修改', click: itemclick, icon: 'modify' },
                    { line: true },
                    { text: '删除', click: itemclick, img: '<%= basePath%>app/js/sellManage/lib/ligerUI/skins/icons/delete.gif' },
                    { line: true },
                    { text: '查询', click: itemclick, img: '<%= basePath%>app/js/sellManage/lib/ligerUI/skins/icons/search2.gif' }
                    ]
                },
                autoFilter: false
                
            });
             

            $("#pageloading").hide();
        });

        function deleteRow()
        {
        	
        }

        function getList(){
        	$.ajax({
        	url: "<%=basePath%>/rest/sellContractManageAction/querySellContract",
        	type: "post",
        	dataType:"json",
    		success:function(list){
        			if(list!=null){
        				alert(list);
        				CustomersDate = {Rows:list};
        				$.ligerDialog.success(CustomersDate);
        				return ;
        			}
        			$.ligerDialog.warn("没有对应的数据！");
        		}
    	
        	});
        }
        
    </script>
</head>
<body style="overflow-x:hidden; padding:2px;">
	<div class="l-loading" style="display:block" id="pageloading"></div>
	<a class="l-button"
		style="width:120px;float:left; margin-left:10px; display:none;"
		onclick="deleteRow(scCode)">删除选择的行</a>


	<div class="l-clear"></div>

	<div id="maingrid" style="overflow: hidden;"></div>

	<div style="display:none;"></div>
</body>
</html>