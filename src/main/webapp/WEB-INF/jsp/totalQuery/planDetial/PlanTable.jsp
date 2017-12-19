<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		 <base href="<%=basePath%>">
		 
	<title></title>
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
			$(function() {
				var url='<%=basePath%>rest/planDetialManageAction/getPageList';
				grid(url);
			});
			

			function grid(url) {
				window['g'] =
					$("#maingrid").ligerGrid({
						height: '90%',
						url:url,
						columns: [
						    { display: '生产令编号', name: 'ordercode',},
							{ display: '产品规格型号', name: 'proggxh',},
							{ display: '生产数量(轴)', name: 'axiscount',},
							{ display: '生产日期', name: 'workday',},
							{ display: '操作' ,render:function (rowdata,rowindex){
								var h = "";
								h += "<a href='javascript:todetail(" + rowindex + ")'>详情</a> ";
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
				var workday =rowdata.workday;
				var orderid = rowdata.orderid;
				var param = {};
				param.workday = workday;
				param.orderid = orderid;
				var json  = JSON.stringify(param);
			//	windows.open('<%=basePath%>rest/planDetialManageAction/getPageList?param='+json);
				window.location.href='<%=basePath%>rest/planDetialManageAction/toDetailPageList?param='+json;
				
			}
			
			function doSearch(){
				var ordercode = $("#ordercode").val();
				var ggxh = $("#ggxh").val();
				var workday = $("#workday").val();
				var param ={};
				if (ordercode != null && ordercode !="") {
					param.ordercode = ordercode;
				}
				if (ggxh != null && ggxh!="") {
					param.ggxh = ggxh;
				}
				if (workday != null && workday !="") {
					param.workDay = workday;
				}
				
				var json = JSON.stringify(param);
				var url = '<%=basePath%>rest/planDetialManageAction/getPageList?param='+json;
				grid(url);
				
			}
			
		</script>
		<style>
			#top{
				width:100%;
				margin:0 auto;
				height:60px;
				line-height: 60px;
			}
			#top ul{
				height:60px;
				line-height:60px;
				list-style-type: none;
				font-size: 14px;
				
			}
			#top ul li{
				margin-left:50px;
				float: left;
			}
			#top ul li span{
				
			}
			#top ul li input{
				height:25px;
			}
			#top ul li button{
				color:white;
				border-radius:5px;
				border:none;
				cursor: pointer;
				margin-right:20px;
				height:30px;
				width:80px;
				margin-top:15px;
			}
			#top ul li button.show{
				background-color: #0099CC;
			}
			#top ul li button.reset{
				background-color: #FCA590;
			}
			#top ul li button.show:hover{
				background-color: #48B4DB;
			}
			#top ul li button.reset:hover{
				background-color: #FAB9AA;
			}
		</style>
	</head>

	<body style="overflow-x:hidden; padding:2px;">
		<div class="l-loading" style="display:block" id="pageloading"></div>
		<div class="l-clear"></div>
		<div id="top">
			<ul>
				<li><span>生产令编号：</span><input type="text" id="ordercode" /></li>
				<li><span>产品规格型号：</span><input type="text" id="ggxh" /></li>
				<li><span>生产日期：</span><input type="text" id="workday" /></li>
				<li><button class="show" onclick="doSearch()">查  询</button><button class="reset">显示所有</button></li>
			</ul>
		</div>
		<div >
			<div id="maingrid"></div>
		</div>
		
		<div style="display:none;">
		</div>
	</body>

</html>
