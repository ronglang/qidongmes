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
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
<script type="text/javascript">
		var time = new Date();
		var time1  = new Date(Date.parse(time)+1000*60*60*24);
		var time2  = new Date(Date.parse(time)+1000*60*60*24*2);
		var grid;
		var pstime=time1.getFullYear()+"-"+(time1.getMonth()+1)+"-"+(time1.getDate())+" 08:00:00"; 
        var pdtime=time2.getFullYear()+"-"+(time2.getMonth()+1)+"-"+(time2.getDate())+" 08:00:00"; 
        $(function ()
        {
        	var url = '<%=basePath%>rest/plaMachinePlanManageAction/getPlanGrid?pstime='
        			+pstime+'&pdtime='+pdtime+'&productstate=计划';
            grid(url);
           form();
        });
		function grid(url){
			grid =
            $("#maingrid").ligerGrid({
                height: '85%',
                title:'排产管理',
                url: url,
               // checkbox: true,
                columns: [
                { display: '工作单', name: 'workcode',width:100},
                { display: '机台号', name: 'maccode' },
                { display: '工序号', name: 'seqcode' },
                { display: '出轴数', name: 'axiscount' },
                { display: '交货日期', name: 'delidate',width:100},
                { display: '计划开始时间', name: 'pstime',width:130 },
                { display: '计划结束时间', name: 'pdtime',width:130 },
                { display: '实际开始时间', name: 'fstime',width:130  },
                { display: '实际结束时间', name: 'fdtime',width:130  },
                { display: '生产所需时间', name: 'useminute',width:90 },
                { display: '优先级', name: 'priority',width:50 },
                { display: '生产状态', name: 'productstate',width:80 },
                { display: '进度', name: 'schedule' },
                { display: '主操作', name: 'main_by' },
                { display: '副操作', name: 'vice_by' },
                { display: '放线轴', name: 'puttype' },
                { display: '收线轴', name: 'reaxistype' },
                { hide:'id',name:'id',width:1}
                ],
                toolbar: {
                    items: [
                    { text: '下发计划', click: issued, icon: 'add' },
                    { line: true },
                    { text: '重置时间', click: reSet, icon: 'modify' },
                    { line: true },
                    ]
                },
                rownumbers: true,
               // autoFilter: true
            });
            $("#pageloading").hide();
		}
		
		function reSet(){
			$.ajax({
				url:'<%=basePath%>rest/plaMachinePlanManageAction/reSetPlanTime',
				type:'POST',
				dataType:'JSON',
				success:function(map){
					if(map.success){
						$.ligerDialog.success(map.msg);
						location.reload();
					}else{
						$.ligerDialog.error(map.msg);
					}
				},
				errot:function(){
					$.ligerDialog.error("发生了未知错误");
				}
			});
		}
		
		
		function issued(){
			pstime = $("[name=pstime]").val();			
			pdtime = $("[name=pdtime]").val();
			if(pstime==""|| pstime==null || pdtime=="" || pdtime==null){
				pstime=time1.getFullYear()+"-"+(time1.getMonth()+1)+"-"+(time1.getDate())+" 08:00:00"; 
		        pdtime=time2.getFullYear()+"-"+(time2.getMonth()+1)+"-"+(time2.getDate())+" 08:00:00"; 
			}
			//alert(pdtime);
			var data = grid.getData();
			//alert(data);
			if(data=="" || data.length<1){
				$.ligerDialog.warn("请先生成计划");
				return;
			}
			$.ajax({
				url:'<%=basePath%>rest/plaMachinePlanManageAction/sendMacTask?pstime='
						+pstime+'&pdtime='+pdtime,
				type:'POST',
				dataType:'JSON',
				success:function(map){
					if(map.success){
						$.ligerDialog.success("下发成功");
						location.reload();
					}else{
						$.ligerDialog.error("下发失败");
					}
				},
				errot:function(){
					$.ligerDialog.error("发生了未知错误");
				}
				
			});
		}
		
		function form(){
	    	form = $("#form2").ligerForm({
	            inputWidth: 90, labelWidth: 90, space:10,
	            fields: [
	            	{ display: "计划开始时间", name: "pstime",  newline:false,type: "date",editor:{showTime:true,format:"yyyy-MM-dd hh:mm:ss"},width:130 },
	            	{ display: "计划结束时间", name: "pdtime",  newline:false,type: "date",editor:{showTime:true,format:"yyyy-MM-dd hh:mm:ss"},width:130},
	            ],	
	            validate: true
	        });
	    	//liger.get('scCode').setDisabled(); //设置只读
	    }
		
</script>
</head>
<body style="overflow-x:hidden; padding:2px;">
	<div id="fo" style="text-align: center;width: 100%;position: relative;">
		<form id="form2" action=""></form>
	</div>
	<div class="l-loading" style="display:block" id="pageloading"></div>
	<div class="l-clear"></div>

	<div id="maingrid"></div>

	<div style="display:none;"></div>

</body>
</html>


