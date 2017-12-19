<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>质量回溯--工序</title>
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
            	url:'<%=basePath %>rest/mauProcessSamplingManageAction/getBackAxisDate?endaxisnumber=${endaxisnumber}',
                height: '100%',
                columns: [
                { display: '工单号', name: 'wscode', align: 'left', width: 100, minWidth: 10 },
                { display: '工序名称', name: 'seqname', minWidth: 100 },
                { display: '工作开始时间', name: 'startdate', minWidth: 100 },
                { display: '工作结束时间', name: 'enddate', minWidth: 100  },
                { display: '材料序号',name:'materid',minWidth:100},
                { display: '生产数量',name:'manuamount',minWidth:100},
                { display: '单位',name:'unit', minWidth: 100 },
                { display: '当前轴号',name:'ownaxisnumber', minWidth: 100 },
                { display: '来源轴号',name:'sourceaxisnumber', minWidth: 100 },
                { display: '工艺编码',name:'ccode',minWidth: 100},
                { display: '产品序号',name:'productid',minWidth: 100},
                { display: '制程采样编码',name:'mpscode',minWidth: 100}
                ],
                onDblClickRow:function(rowdata, rowindex, rowDomElement){
            		debugger;
            		var seqname = rowdata.seqname;
            		var materid = rowdata.materid;
            		var ccode = rowdata.ccode;
            		var mpscode = rowdata.mpscode;
            		alert(seqname+","+materid+","+ccode+","+mpscode);
             		//window.open("<%=basePath%>rest/mauProcessSamplingManageAction/toBackDateDetailTable?endaxisnumber="+endaxisnumber);
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

