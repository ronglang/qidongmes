<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>批次分解</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%= basePath%>app/js/sellManage/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />  
    <link rel="stylesheet" type="text/css" id="mylink"/>   
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>

	<script type="text/javascript">
		var basePath = '<%= basePath%>';
		var orderCode ='${orderCode}' ;
		var grid;
		var flag = true;
		$(function(){
			queryTable();
		});
		function queryTable(){
			grid = $("#myGrid").ligerGrid({
				title : "批次分解",
				url : basePath+"rest/sellSalesOrderDetailsManageAction/getListPageData?orderCode="+orderCode,
				checkbox : true,
				allowAdjustColWidth : true,
				delayLoad : false,
				alternatingRow : false,
				rownumbersColWidth : 60,
				height : 300,
				usePager : false,
				enabledEdit:true,
				columns : [
				{
					display : '订单编号',
					name : 'orderCode'
				}, {
					display : '交货日期',
					name : 'deliveryDate'
				}, {
					display : '产品规格型号',
					name : 'proGgxh'
				}, {
					display : '每轴长度',
					name : 'als'
				}, {
					display : '轴数',
					name : 'axisNumber'
				},{
					display : '已分解轴数',
					name : 'alreadyAxisNum'
				},{
					display : '本次分解轴数',
					name : 'nowAxisNum',
					editor:{
						 type: 'int',
						 onChanged:function(row){
							 debugger;
								var all = row.record.axisNumber;
								var already = row.record.alreadyAxisNum;
								var now = row.record.nowAxisNum;
								if(now < 0 ){
									$.ligerDialog.error("不能输入负数");
									flag = false;
								}
								var tol =  already + now;
								if(tol > all){
									$.ligerDialog.error("已分解加上现在分解不能大于总轴数");
									flag = false;
									//return now;
								}else{
									flag = true;
									//return now;
								}
								
							}
					},
					
				},{
					display : '颜色',
					name : 'proColor'
				}, {
					display : '总长度',
					name : 'totalLength'
				}, {
					hide : 'id',
					name : 'id',
					width:1
				} ],
			});
		}
		
		function batchDecompose(){
			if(!flag){
				$.ligerDialog.warn("有轴数填写不正确,请仔细检查");
				return;
			}
			var detailBeans = grid.getData();
			debugger;
			if(detailBeans.length == 0){
				$.ligerDialog.warn("无可分解的数据");
				return;
			}
			var beans = JSON.stringify(detailBeans);
			var url = basePath+"rest/sellSalesOrderManageAction/batchDecompose";
			$.post(url,{orderCode:orderCode,detailBeans:beans},function(data){
				if(data.success){
					$.ligerDialog.success(data.msg);
					grid.reload();
				}else{
					$.ligerDialog.error(data.msg);
				}
				
			},"json");
		}
		
	</script>
  </head>
  
  <body>
    <div id="myGrid" >
    
    </div>
  </body>
</html>
