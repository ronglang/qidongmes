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
    var groupicon = "";
    var form,seqcode,proggxh,maccode,employee,axisname;   
    var isFinish = [
                {id:'1',text:'是' },
                {id:'2',text:'否'},
                ];
    var proCraftCode,wsCode,url;
    
        $(function ()
        {
        	url = '<%=basePath%>rest/plaCourseManageAction/getPlaCourseGrid';
            grid(url);
            getGgxh();
            //form();
        });
		function grid(){
			window['g'] =
            $("#maingrid").ligerGrid({
                height: '80%',
                title:'工单统计查询',
                url: url,
                //checkbox: true,
                columns: [
                { display: '工单编号', name: 'wsCode', width:'8%'},
				{ display: '产品规格型号', name: 'headGgxh',   width:'9%'},
				{ display: '颜色', name: 'color',  width:'4%'},
				{ display: '扎装段长', name: 'headZzdc',  width:'9%'},
				{ display: '扎装段数', name: 'headZzds',  width:'9%'},
				{ display: '生产总数', name: 'totalAmount',  width:'4%'},
				{ display: '开单日期', name: 'billDate',  width:'9%'},
				{ display: '完成情况', name: 'isFinish',  width:'4%'},
				{ display: '是否启用', name: 'useFlag',  width:'4%'},
				{ display: '计划启用日期', name: 'planEnableDate',  width:'9%'},
				{ display: '工艺路线', name: 'routeCode',  width:'8%'},
				{ display: '产品工艺', name: 'proCraftCode',  width:'9%'},
				{ display: '生产令编号', name: 'productOrderCode',  width:'9%'},
				{ display: '操作' ,render : function(rowData) {
				var html = '<a href="#" onclick="javascript:show('+ rowData.id+ ');return false;">查看详情</a>';
				return html;
				}, width:'5%'}
                ],
                rownumbers: true
            });
             

            $("#pageloading").hide();
		}
		
		function show(id) {
			top.$.ligerDialog.open({
				url : "<%=basePath%>rest/plaCourseManageAction/toDetailPage?id=" + id,
				height : 600,
				width : 1080,
				modal : true,
				title : "详细信息"
			});
		}
		
		function form(){
	    	form = $("#form2").ligerForm({
	            inputWidth: 150, labelWidth: 100, space:10,
	            
	            fields: [
	            	{ display: "工单编码", name: "wsCode", newline:false,comboboxName:'wsCode',type: "select",editor: {valueField:'text',data:wsCode},align:'left' },
	            	{ display: "启用日期 ", name: "planEnableDate",newline:false,comboboxName:'planEnableDate', type: "date",editor:{showTime:true,format:"yyyy-MM-dd hh:mm:ss"},align:'left'},
	            	{ display: "完成情况", name: "isFinish",showTime:true, newline:false,comboboxName:'isFinish',editor: {valueField:'text',data:isFinish}, type: "select",align:'left'},
	            	{ display: "产品工艺", name: "proCraftCode", newline:false,comboboxName:'proCraftCode',type: "select",editor: {valueField:'text',data:proCraftCode},align:'left' },
	            ],
	            validate: true
	        });
	    }
		
		function search(){
			var wc = $("[name=wsCode]").val();
			var ped = $("[name=planEnableDate]").val();
			var isf = $("[name=isFinish]").val();
			var pcc = $("[name=proCraftCode]").val();
			url = "<%=basePath%>rest/plaCourseManageAction/getPlaCourseGrid?wsCode="+wc+"&planEnableDate="+ped+"&isFinish="+isf+"&proCraftCode="+pcc;
			grid(url);
		}
		
		function getGgxh(){
	    	$.ajax({
	    		url:'<%=basePath%>rest/plaCourseManageAction/getWsCode',
	    		type:'POST',
	    		dataType:'json',
	    		success:function(str){
	    			wsCode = str;
	    			$.ajax({
	    	    		url:'<%=basePath%>rest/craBomProductMaterManageAction/getProCraftCode',
	    	    		type:'POST',
	    	    		dataType:'json',
	    	    		success:function(str){
	    	    			proCraftCode = str;
	    	    			form(wsCode,proCraftCode);
	    	    		}
	    	    	});
	    			
	    		}
	    			
	    	});
	    }
		
		
    </script>
</head>
<body style="overflow-x:hidden; padding:2px;">
	<div class="l-loading" style="display:block" id="pageloading"></div>
	<div class="l-clear"></div>
	<div style="text-align: center;width: 80%;margin:0 auto;position: relative;border-bottom-color: red">
		<form id="form2" action=""></form>
		<div class="liger-button" onclick="search()" style="float: right;position: absolute;right: 130px;top: 3px; ">搜索</div>
	</div>
	<div id="maingrid"></div>

	<div style="display:none;"></div>
</body>
</html>


