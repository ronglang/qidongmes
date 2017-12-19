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
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    <script type="text/javascript">
        $(function ()
        {
        	var url = '<%=basePath %>rest/plaMacTaskMaterilManageAction/getPlaMacTaskMateril';
            grid(url);
           form();
        });
		function grid(url){
			window['g'] =
            $("#maingrid").ligerGrid({
                height: '95%',
                title:'机台计划',
                url: url,
               // checkbox: true,
                columns: [
                { display: '工作单', name: 'workcode'},
                { display: '工序号', name: 'seqcode' },
                { display: '机台编码', name: 'maccode' },
                { display: '计划领料时间', name: 'ptime' },
                { display: '材料类型', name: 'materil' },
                { display: '材料用量', name: 'matecount',type: 'float',
                    totalSummary:
                    {
                        type: 'sum,max'
                    } },
                { hide:'id',name:'id',width:1}
                ],
                rownumbers: true,
                isScroll: false,
                groupColumnName:'materil', 
                groupColumnDisplay:'材料类型'
               // autoFilter: true
            });
            $("#pageloading").hide();
		}
		
		
		
		function form(){
	    	form = $("#form2").ligerForm({
	            inputWidth: 90, labelWidth: 90, space:10,
	            fields: [
	            	{ display: "从", name: "stime",  newline:false,type: "date",editor:{showTime:true,format:"yyyy-MM-dd hh:mm:ss"},width:130 },
	            	{ display: "到", name: "dtime",  newline:false,type: "date",editor:{showTime:true,format:"yyyy-MM-dd hh:mm:ss"},width:130},
	            ],	
	            validate: true
	        });
	    	//liger.get('scCode').setDisabled(); //设置只读
	    }
		
		function search(){
			var stime = $("[name=stime]").val();
			var dtime = $("[name=dtime]").val();
			url = "<%=basePath %>rest/plaMacTaskMaterilManageAction/getPlaMacTaskMateril?stime="+stime+"&dtime="+dtime;
			grid(url);
		}
		
		
		
    </script>
</head>
<body style="overflow-x:hidden; padding:2px;">

	<div id="fo" style="text-align: center;width: 100%;position: relative;"  >
		<form id="form2" action="" >
		</form>
		<div class="liger-button" id = 'tj' onclick="search()" style="float: right;position: absolute;right: 200px;top: 1px; ">提交</div>
	</div>
	<div class="l-loading" style="display:block" id="pageloading"></div>
    <div class="l-clear"></div>

    <div id="maingrid"></div>
   
    <div style="display:none;">
    </div>
    
</body>
</html>


