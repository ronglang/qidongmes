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
		<title>出库单详情</title>
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
		<script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
		<%-- <link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script> --%>
		<style type="text/css">
		#nowTime{
			font-size: 20px;
			color:white;
			}
			.nav-a{
				width: 33%;
				height: 60px;
				text-align: center;
				float: left;
				
			}
			.span-a{
				font-size:20px;
				font-family: "微软雅黑";
				font-weight: 300;
				color:white;
			}
	
	
	
			.tb{
				width: 30%;
				height: 80px;
				float: right;
				text-align: right;
				line-height: 70px;
			}
			 .newSpan{
			 	text-align: center;
			 	float: left;
			 	margin-top: 20px;
			 	margin-left: 40%;
			 }
		#nowTime{
			font-size: 20px;
			color:white;
			}
			.nav-a{
				width: 33%;
				height: 60px;
				text-align: center;
				float: left;
				
			}
			.span-a{
				font-size:20px;
				font-family: "微软雅黑";
				font-weight: 300;
				color:white;
			}
	
	
	
			.tb{
				width: 30%;
				height: 80px;
				float: right;
				text-align: right;
				line-height: 150px;
			}
			 .newSpan{
			 	text-align: center;
			 	float: left;
			 	margin-top: 20px;
			 	margin-left: 40%;
			 }
		</style>
			<script>
			var basePath  = '<%=basePath%>';
			var routeName = "storeDgCkManage"; 
			
    	</script>
		
		<script type="text/javascript">
		var grid;
		var form;
		$(function(){
			var outCode=$("#hideCode").val();
			var url=basePath + "rest/" + routeName + "Action/getDetail?outCode="+outCode;
				getDataGrid(url);
				//myload();
		});
		
		
		function getDataGrid(url){
			window['g'] =
				grid= $("#maingrid").ligerGrid({
		            url:url,
		            height:'99%',
		            columns: [
		              { hide: '序号', name: 'id',  width: 1 },
		              { display: '出库单号', name: 'ordercode',  width:  '20%' },
		              { display: '领料单号', name: 'mmrcode',  width:  '20%' },
                      { display: '物料名称', name: 'matername',  width: '15%',editor:{type:'text'}},
                      { display: '规格型号', name: 'ggxh',  width: '15%',editor:{type:'text'}},
                      { display: '数量', name: 'amount', width: '10%',editor:{type:'text'} },
                      { display: '实际出库数量', name: 'factamount', width: '10%',editor:{type:'text'} },
                      { display: '单位', name: 'unit',  width:  '10%', editor:{type:'text'} 
                      },
          	        
                 		 ],
          
            rownumbers: true,
           
           // autoFilter: true
        });
        $("#pageloading").hide();
};		




		</script>
	</head>

	<body style="overflow-x:hidden; padding:2px;">
	<div  style="width: 100%;height: 70px;background: rgba(33, 138, 217, 1);float:left;">
			<div class="newSpan">
				<span style="font-size: 30px;color: white;font-family: 'arial, helvetica, sans-serif';font-weight: 300;">
					出&nbsp;&nbsp;库&nbsp;&nbsp;物&nbsp;&nbsp;料&nbsp;&nbsp;详&nbsp;&nbsp;情
				</span>
				
			</div>
		</div>
		<div class="l-loading" style="display:block" id="pageloading"></div>
		<div class="l-clear"  style="margin-top: 60px;"></div>
		<div id="myform" ></div>
		<input type="hidden"  value="${outCode}"  id="hideCode" />
		<div id="maingrid" ></div>
		<div style="display:none;">
		</div>
	</body>

</html>