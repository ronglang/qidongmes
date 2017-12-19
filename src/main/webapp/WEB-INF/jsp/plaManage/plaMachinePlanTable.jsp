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
        	var url = '<%=basePath %>rest/plaMachinePlanManageAction/getPlaMachinePlanGrid';
            grid(url);
           // getSeqName();
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
                { display: '机台号', name: 'maccode' },
                { display: '工序号', name: 'seqcode' },
                { display: '出轴数', name: 'axiscount' },
                { display: '交货日期', name: 'delidate' },
                { display: '计划开始时间', name: 'pstime' },
                { display: '计划结束时间', name: 'pdtime' },
                { display: '实际开始时间', name: 'fstime' },
                { display: '实际结束时间', name: 'fdtime' },
                { display: '生产所需时间', name: 'useminute' },
                { display: '优先级', name: 'priority' },
                { display: '生产状态', name: 'productstate' },
                { display: '进度', name: 'schedule' },
                { display: '主操作人', name: 'main_by' },
                { display: '副操作人', name: 'vice_by' },
                { hide:'id',name:'id',width:1}
                ],
                isScroll: true, frozen:false,
                showTitle: false,width:'100%',
                rownumbers: true,
                detail: { onShowDetail: f_showOrder }
            });
            $("#pageloading").hide();
		}
		
		
		function f_showOrder(row, detailPanel,callback)
        {
			debugger;
            var grid = document.createElement('div'); 
            $(detailPanel).append(grid);
            $(grid).css('margin',10).ligerGrid({
            	url:'<%=basePath %>rest/plaMachinePlanManageAction/getChildPage?macCode='+row.maccode+'&workCode='+row.workcode,
                columns:
                            [
                            { display: '颜色', name: 'color',type:'text',width:200},
                            { display: '每轴长度', name: 'length',type:'text',width:200 },
                            { display: '出轴号', name: 'axiscode' ,width:200}
                            ], isScroll: false, showToggleColBtn: false, width: '90%',
                	showTitle: false , onAfterShowData: callback,frozen:false
            });  
        }
		
		
		function form(){
	    	form = $("#form2").ligerForm({
	            inputWidth: 90, labelWidth: 90, space:10,
	            fields: [
	            	{ display: "工单编号", name: "workcode", newline:true, type: "select",editor:{valueField:'text',url:"<%=basePath %>rest/plaMachinePlanManageAction/getWorkCode"}},
	            	{ display: "机台编码", name: "maccode",  newline:false,type: "select" ,editor:{valueField:'text',url:"<%=basePath %>rest/plaMachinePlanManageAction/getMacCode"}},
	            	{ display: "工序名称", name: "seqcode",  newline:false,type: "select" ,editor:{valueField:'text',url:"<%=basePath %>rest/craSeqManageAction/getSeqCode"}},
	            	{ display: "计划开始时间", name: "pstime",  newline:false,type: "date",editor:{showTime:true,format:"yyyy-MM-dd hh:mm:ss"},width:130 },
	            	{ display: "计划结束时间", name: "pdtime",  newline:false,type: "date",editor:{showTime:true,format:"yyyy-MM-dd hh:mm:ss"},width:130},
	            ],	
	            validate: true
	        });
	    	//liger.get('scCode').setDisabled(); //设置只读
	    }
		
		function search(){
			var workcode = $("[name=workcode]").val();
			var maccode = $("[name=maccode]").val();
			var seqcode = $("[name=seqcode]").val();
			var pstime = $("[name=pstime]").val();
			var pdtime = $("[name=pdtime]").val();
			url = "<%=basePath %>rest/plaMachinePlanManageAction/getPlaMachinePlanGrid?workcode="
					+workcode+"&maccode="+maccode+"&seqcode="+seqcode+"&pstime="+pstime+"&pdtime="+pdtime;
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


