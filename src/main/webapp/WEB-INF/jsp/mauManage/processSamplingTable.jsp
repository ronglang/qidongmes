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
            	url:'<%=basePath %>rest/mauProcessSamplingManageAction/getBackDate?endaxisnumber=${endaxisnumber}',
                height: '100%',
                columns: [
                { display: '产品名称', name: 'productname', align: 'left', width: 100, minWidth: 10 },
                { display: '产品类型', name: 'producttype', minWidth: 150 },
                { display: '产品长度', name: 'productlength', minWidth: 150 },
                { display: '单位', name: 'unit', minWidth: 150  },
                { display: '包装类型',name:'packingtype',minWidth:150},
                { display: '颜色',name:'color',minWidth:150},
                { display: '备注',name:'remark', minWidth: 150 },
                { display: '生产批次',name:'productbatch', minWidth: 150 },
                { display: '产品轴号',name:'endaxisnumber', minWidth: 150 }
                ],  
            	onDblClickRow:function(rowdata, rowindex, rowDomElement){
            		debugger;
            		var endaxisnumber = rowdata.endaxisnumber;
            		//alert(endaxisnumber);
             		window.open("<%=basePath%>rest/mauProcessSamplingManageAction/toBackDateDetailTable?endaxisnumber="+endaxisnumber);
            	},
                pageSize: 15, rownumbers: true,
                autoFilter: true
            });
            $("#pageloading").hide();
        });        
        function check(){
        	testRegExp = /^[A-Za-z0-9]+$/; 
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
 <div class="l-clear"></div>
	<div style="text-align: center;">
		<form id="form" name="form" method="post" action="<%=basePath %>rest/mauProcessSamplingManageAction/toBackDateTable">
			<input type="text" placeholder="请输入产品编号批次号" value="" name="productid" id="productid" onmouseout="check()" style="width: 20%;height: 25px;font-size: 16px;"/>
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

