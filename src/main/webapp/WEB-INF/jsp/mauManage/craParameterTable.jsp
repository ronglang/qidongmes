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
      
        $(function ()
        {
            window['g'] =
            $("#maingrid").ligerGrid({
            	url:'<%=basePath %>rest/mauProcessSamplingManageAction/getCraParameter?productid=${productid}',
                height: '100%',
                columns: [
                { display: '机器名称', name: 'macname', align: 'left', width: 100, minWidth: 10 },
                { display: '机器类型', name: 'mactype', minWidth: 150 },
                { display: '工序名称', name: 'seqname', minWidth: 150 },
                { display: '参数名称', name: 'paramname', minWidth: 150  },
                { display: '参数值', name:'vaule',minWidth:150},
                { display: '成品id',name:'productid',minWidth:150}
                ],  
            	onDblClickRow:function(rowdata, rowindex, rowDomElement){
            		var productid = rowdata.productid;
            		//alert(productid);
            		window.open("<%=basePath %>rest/mauProcessMonitoringManageAction/toListDetailPage?productid="+productid);
            	},
                pageSize: 15, rownumbers: true,
                autoFilter: true
            });
            $("#pageloading").hide();
        });        
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
		<form id="form" name="form" method="post" action="<%=basePath %>rest/mauProcessSamplingManageAction/toCraParameter">
			<input type="text" placeholder="请输入3-6位数产品编号" value="" name="productid" id="productid" onmouseout="check()" style="width: 20%;height: 25px;font-size: 16px;"/>
			<input type="submit" id="search" style="cursor: pointer;padding-left: 26px;width: 73px;height:27px;
			background-color: transparent;background-image: url(<%=basePath %>app/js/lib/images/ListSearch.png);background-repeat: no-repeat;border: 0px;color: #0e6fbd;" />
		</form>
	</div>
    <div id="maingrid" style="overflow: hidden;">
    </div>
  <div style="display:none;">
</div>
</body>
</html>

