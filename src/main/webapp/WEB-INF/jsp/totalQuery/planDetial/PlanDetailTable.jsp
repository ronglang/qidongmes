<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'PalnDetailTable.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    <script type="text/javascript">
			/* function itemclick(item) {
				alert(item.text);
			} */
			var data  = ${data};
			$(function() {
				grid();
			});
			

			function grid() {
				//var json = eval('(' + data+ ')'); 
				var json = JSON.stringify(data); 
				window['g'] =
					$("#maingrid").ligerGrid({
						height: '90%',
						url:'<%=basePath%>rest/planDetialManageAction/getPageList?param='+json,
						columns: [
							{ display: '工单编号', name: 'coursecode'},
							{ display: '产品规格型号', name: 'proggxh'},
							{ display: '轴名称', name: 'axisname' },
							{ display: '机台编号', name: 'maccode' },
							{ display: '工序名称', name: 'seqname' },
							{ display: '主操作手', name: 'mainby' },
							{ display: '副操作手', name: 'viceby'},
							{ display: '计划长度', name: 'partlen'},
							{ display: '生产长度', name: 'semilen'},
							{ display: '生产状态', name: 'productstate',},
							{ hide: '机台计划id', name:'planid',  width:1},
							{ display: '操作' ,render:function (rowdata,rowindex){
								var h = "";
								h += "<a href='javascript:todetail(" + rowindex + ")'>原料详情</a> ";
								return h;
							},  width:'9%'},
							{ hide: '生产令id', name: 'orderid',  width:1}
						],
						/* toolbar: {
                    		items: [
                    				{ text: '增加', click: itemclick, icon: 'add' },
                    				{ line: true },
                    				{ text: '修改', click: itemclick, icon: 'modify' },
                    				{ line: true },
                   	 				{ text: '删除', click: itemclick, icon:'delete' }
                    			]
                			}, */
						//data: CustomersData,
						rownumbers: true,
						// autoFilter: true
					});

				$("#pageloading").hide();
			}
			
			function todetail(rowindex){
				var manager = $("#maingrid").ligerGetGridManager();
				var rowdata = manager.data.Rows[rowindex];

				debugger;
				/* if(null == rowdata.planid ){
					alert("未查询到相关信息");
					return;
				} */
				var planid =rowdata.planid;
				var param = {};
				param.planid = planid;
				var json  = JSON.stringify(param);
			//	windows.open('<%=basePath%>rest/planDetialManageAction/getPageList?param='+json);
				//window.location.href='<%=basePath%>rest/planDetialManageAction/toDetailPageList?param='+json;
				
				$.ligerDialog.open({

			        height: 450,

			        width: 850,

			        title: '原材料详情页面',

			        url: '<%=basePath%>rest/planDetialManageAction/toMaterDetailPageList?param='+json,

			        showMax: false,

			        showMin: false,

			        isResize: true,

			        slide: false,

			        name:'repairAgain',

			        buttons: [
			        	
			            { text: '确定', onclick: function (item, dialog) { dialog.close(); } }]

			    });

			}
			
		</script>
  </head>
  
  <body style="overflow-x:hidden; padding:2px;">
		<div class="l-loading" style="display:block" id="pageloading"></div>
		<div class="l-clear"></div>
		<div style="width:85%;margin:0 auto;">
			<div id="maingrid"></div>
		</div>
		
		<div style="display:none;">
		</div>
	</body>
</html>
