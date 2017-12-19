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
	<link rel="stylesheet" type="text/css" href="<%=basePath%>app/report/css/top.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>app/report/css/index.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>app/report/css/data.css"/>
	<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/echarts.min.js" type="text/javascript" charset="utf-8"></script>
	
	<script type="text/javascript">
		$(function(){
			alert(0);
			queryStore();
			
		});
		function outLog(){
			debugger;
			$.ajax({
				type:"POST",
				url:basePath +"/rest/sysUserManageAction/logOutWebUser",
				data:"",
				dataType:"json",
				success: function(data){
					if(data.success=true){
						window.location.href= basePath + "mylogin.jsp";
					}
				},
				error:function(){
					alert("服务器出错！");
				}
			});
		}
		
		function queryStore(){
			var url = basePath +"/rest/storeMrecordManageAction/getClassify";
			$.post(url,{},function(data){
				for(var i=0;i<data.length;i++){
					$("#"+data[i].key).html(data[i].value);
				}			
			},"json");
		}
		
		
	
	</script>

  </head>
  
  <body>
    
    <div id="index">
	<div id="wz">
		<ul class="dck">
			<li class="sy">首页</li>
			<!-- <li class="ck">出库单管理</li> -->
			<li class="outlogin"><a href="javascript:void(0);" onclick="outLog();">退出</a></li>
			<li class="yoers">当前用户：${session_user.name}！2</li>
		</ul>
		<img src="<%=basePath%>app/report/images/cha.png" class="cha" />
	</div>

	<div id="mbody">

		<div class="kc">
			<ul class="ylkc">
				<li class="jl">胶料库存：<samp class="samp1" id="胶料">10.234T</samp></li>
				<li class="jl1">铜线库存：<samp class="samp1" id="铜料">21.200KM</samp></li>
				<li class="jl1">铝线库存：<samp class="samp1" id="铝料">15.200KM</samp></li>
				<li class="jl1">钢带库存：<samp class="samp1" id="钢带">8.500KM</samp></li>
				<li class="jl1">铁丝库存：<samp class="samp1" id="铁料">10.234KM</samp></li>
				<li class="jl1">包带库存：<samp class="samp1" id="包带">10.230KM</samp></li>
			</ul>
		</div>

		<div class="tb">
			<div class="gd">&nbsp;&nbsp;&nbsp;&nbsp;今日工单完成情况</div>
			<iframe id="right_url" src="<%=basePath%>rest/mauIndexManageAction/workOrder" width="100%" height="100%" frameborder="no"></iframe>
		</div>

		<div class="tb1">
			<div class="jt">&nbsp;&nbsp;&nbsp;&nbsp;全部机台运行状态</div>
			
			<iframe id="right_url" src="<%=basePath%>rest/mauIndexManageAction/machineStatusNum" width="100%" height="100%" frameborder="no"></iframe>
		</div>

		<div class="bg">
			<div class="he">&nbsp;&nbsp;&nbsp;&nbsp;合同到期情况</div>
			<iframe id="right_url" src="<%=basePath%>rest/mauIndexManageAction/contractTime" width="100%" height="100%" frameborder="no"></iframe>
			<!-- 
			<div class="btable">
				<table class="table">
					<tr class="tr">
						<th class="th">序号</th>
						<th class="th">合同编号</th>
						<th class="th">生产令</th>
						<th class="th">批次号</th>
						<th class="th">完成情况</th>
						<th class="th">计划完工日期</th>
						<th class="th">交货日期</th>
					</tr>

					<tr class="tr1">
						<td class="td">01</td>
						<td class="td">sc-2017-06-27-06</td>
						<td class="td">L1704255</td>
						<td class="td">PVCPU-001</td>
						<td class="td">100%</td>
						<td class="td">2017/09/06</td>
						<td class="td">2017/09/06</td>
					</tr>

					<tr class="tr">
						<td class="td">02</td>
						<td class="td">sc-2017-06-27-06</td>
						<td class="td">L1704255</td>
						<td class="td">PVCPU-001</td>
						<td class="td">100%</td>
						<td class="td">2017/09/06</td>
						<td class="td">2017/09/06</td>
					</tr>

					<tr class="tr1">
						<td class="td">03</td>
						<td class="td">sc-2017-06-27-06</td>
						<td class="td">L1704255</td>
						<td class="td">PVCPU-001</td>
						<td class="td">100%</td>
						<td class="td">2017/09/06</td>
						<td class="td">2017/09/06</td>
					</tr>

					<tr class="tr">
						<td class="td">04</td>
						<td class="td">sc-2017-06-27-06</td>
						<td class="td">L1704255</td>
						<td class="td">PVCPU-001</td>
						<td class="td">100%</td>
						<td class="td">2017/09/06</td>
						<td class="td">2017/09/06</td>
					</tr>

					<tr class="tr1">
						<td class="td">05</td>
						<td class="td">sc-2017-06-27-06</td>
						<td class="td">L1704255</td>
						<td class="td">PVCPU-001</td>
						<td class="td">100%</td>
						<td class="td">2017/09/06</td>
						<td class="td">2017/09/06</td>
					</tr>

					<tr class="tr">
						<td class="td">06</td>
						<td class="td">sc-2017-06-27-06</td>
						<td class="td">L1704255</td>
						<td class="td">PVCPU-001</td>
						<td class="td">100%</td>
						<td class="td">2017/09/06</td>
						<td class="td">2017/09/06</td>
					</tr>

					<tr class="tr1">
						<td class="td">07</td>
						<td class="td">sc-2017-06-27-06</td>
						<td class="td">L1704255</td>
						<td class="td">PVCPU-001</td>
						<td class="td">100%</td>
						<td class="td">2017/09/06</td>
						<td class="td">2017/09/06</td>
					</tr>

					<tr class="tr">
						<td class="td">08</td>
						<td class="td">sc-2017-06-27-06</td>
						<td class="td">L1704255</td>
						<td class="td">PVCPU-001</td>
						<td class="td">100%</td>
						<td class="td">2017/09/06</td>
						<td class="td">2017/09/06</td>
					</tr>

					<tr class="tr1">
						<td class="td">09</td>
						<td class="td">sc-2017-06-27-06</td>
						<td class="td">L1704255</td>
						<td class="td">PVCPU-001</td>
						<td class="td">100%</td>
						<td class="td">2017/09/06</td>
						<td class="td">2017/09/06</td>
					</tr>

					<tr class="tr">
						<td class="td">10</td>
						<td class="td">sc-2017-06-27-06</td>
						<td class="td">L1704255</td>
						<td class="td">PVCPU-001</td>
						<td class="td">100%</td>
						<td class="td">2017/09/06</td>
						<td class="td">2017/09/06</td>
					</tr>

					<tr class="tr1">
						<td class="td">01</td>
						<td class="td">sc-2017-06-27-06</td>
						<td class="td">L1704255</td>
						<td class="td">PVCPU-001</td>
						<td class="td">100%</td>
						<td class="td">2017/09/06</td>
						<td class="td">2017/09/06</td>
					</tr>

				</table>
			</div>
			 -->
		</div>

	</div>

</div>
    
  </body>
</html>
