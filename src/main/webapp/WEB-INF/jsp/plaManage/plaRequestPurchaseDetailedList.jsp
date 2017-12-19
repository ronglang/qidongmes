<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link href="<%=basePath %>app/js/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>app/js/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>app/js/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>app/js/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>app/js/lib/ligerUI/js/ligerui.all.js"></script>
    <script type="text/javascript">
    
    var CustomersData = 
    {Rows:[{"productId":"78984",
    "isQualified":"合格",
    "samplDate":"2017-4-20",
    "ownAxisNumber":"1",
    "sourceAxisNumber":"0",
    "wsCode":"WS2017820",
    "seqName":"拉丝"},
    {"productId":"78984",
    "isQualified":"合格",
    "samplDate":"2017-4-20",
    "ownAxisNumber":"2",
    "sourceAxisNumber":"1",
    "wsCode":"WS2017830",
    "seqName":"挤绝缘"},
    ]};
    
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
            window['g'] =
            $("#maingrid").ligerGrid({
            	url:'<%=basePath %>rest/mauProcessMonitoringManageAction/getMauVoData?productid=${productid}',
                height: '100%',
                columns: [
                { display: '轴名称', name: 'sampldate', minWidth: 30 },
                { display: '机台编号', name: 'productid', align: 'left', width: 100, minWidth: 10 },
                { display: '人员编号', name: 'isqualified', minWidth: 30 },
                { display: '产品工艺', name: 'ownaxisnumber' },
                { display: '物料规格', name: 'sourceaxisnumber' },
                { display: '当前工序编码',name:'wscode',minWidth:30},
                { display: '工序半制品轴号',name:'seqname',minWidth:30},
                { display: '上工序编号',name:'maccode',minWidth:30},
                { display: '下工序编号',name:'maccode',minWidth:30},
                { display: '下工序机台编号',name:'maccode',minWidth:30},
                { display: '(半)制品长度',name:'maccode',minWidth:30},
                { display: '准备时间(m)',name:'maccode',minWidth:30},
                { display: '生产时间(m)',name:'maccode',minWidth:30},
                { display: '生产速度(m/s)',name:'maccode',minWidth:30}
                ],  
            	onDblClickRow:function(rowdata, rowindex, rowDomElement){
            		var productid = rowdata.productid;
            		//alert(productid);
            		window.open("<%=basePath %>rest/mauProcessMonitoringManageAction/toListDetailPage?productid="+productid);
            	},
                pageSize: 10, rownumbers: true,
                autoFilter: true
            });
            $("#pageloading").hide();
        });

        function deleteRow()
        {
            g.deleteSelectedRow();
        }
        
        function check(){
        	testRegExp = /^\d{3,6}$/; 
        	strzip = $('#productid').val();
        	if(testRegExp.test(strzip)) {
        	return;
        	} else {
        	$('#productid').val("");
        	}
        	
        }
        
    </script>
</head>
<body style="overflow-x:hidden; padding:2px;">
<div class="l-loading" style="display:block" id="pageloading"></div>
 <a class="l-button" style="width:120px;float:left; margin-left:10px; display:none;" onclick="deleteRow()">删除选择的行</a>

 
 <div class="l-clear"></div>
	<div style="text-align: center;">
		<form id="form" name="form" method="post" action="<%=basePath %>rest/mauProcessMonitoringManageAction/toMonitoringTable">
			<input type="text" placeholder="请输入3-6位数产品编号" value="" name="productid" id="productid" onmouseout="check()" style="width: 20%;height: 25px;font-size: 16px;"/>
			<input type="submit" id="search" value=" " style="cursor: pointer;padding-left: 26px;width: 73px;height:27px;
			background-color: transparent;background-image: url(<%=basePath %>app/js/lib/images/ListSearch.png);background-repeat: no-repeat;border: 0px;color: #0e6fbd;" />
		</form>
	</div>
    <div id="maingrid" style="overflow: hidden;">
    	
    </div>
   
  <div style="display:none;">
  
</div>
</body>
</html>

