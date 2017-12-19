<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>大拉丝</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=basePath %>core/css/board/strepsinema.css" />
	<script type="text/javascript">
		var basePath = "<%=basePath%>";
		var urlPath = basePath.substring(7, basePath.length);
	</script>
	<style type="text/css">
		#tbs1{
			margin:10px auto;
			width:100%;
		}
		
	
	</style>
	
  </head>
  <body>
		<div id="head">
			<table border="0" cellpadding="0" cellspacing="0" align="center">
				<tr>
					<td class="tbh" id="">机台：</td>
					<td class="tbb">【<span id="machineName"></span>】</td>
					<td class="tbh" id="jx_type">规格型号：</td>
					<td class="tbb">【<span  id="machineType"></span>】</td>
					<td class="tbh" id="jx_task">机台任务完成情况：</td>
					<td class="tbb">【<span  id="taskPercent"></span>%】</td>
				</tr>
			</table>
		</div>
		
		<div id="content">
			<div class="con_head">
				<div>工单编号：<span id="courseCode"></span></div>
				<div>操作人员：<span id="operPerson"></span></div>
				<div class="h1" id="showtime">&nbsp;</div>
			</div>
			<div class="con_info">
				<div>材料：<span id="material"></span></div>
				<div>型号：<span id="material_type"></span></div>
				<div>数量：<span id="material_num"></span>m</div>
				<div>剩余米数：<span id="semiLen"></span>m</div>
			</div>
			<table border="0" cellpadding="0" cellspacing="0" id="tbs1">
			
			<tr>
  				<td>
					<span>实际拉线径值：</span>
					<label id="4000"></label>
				</td>
				<td>
					<span>实际拉线径标准差：</span>
					<label id="4001"></label>
				</td>
				<td>
					<span>出口模直径：</span>
					<label id="6002"></label>
				</td>
				<td>
					<span>前模直径：</span>
					<label id="6003"></label>
				</td>
			</tr>
			<tr>
				<td>
					<span>前模模数：</span>
					<label id="6004"></label>
				</td>
				<td>
					<span>拉线速度：</span>
					<label id="4002"></label>
				</td>
				<td>
					<span>拉线平均速度：</span>
					<label id="4003"></label>
				</td>
				<td>
					<span>拉线最大速度：</span>
					<label id="4004"></label>
				</td>
			<tr>
				
				<td>
					<span>退火电压设定(%)：</span>
					<label id="4005"></label>
				</td>
				<td>
					<span>退火电压：</span>
					<label id="4006"></label>
				</td>
				<td>
					<span>退火电流：</span>
					<label id="4007"></label>
				</td>
				<td>
					<span>主马达电流：</span>
					<label id="4008"></label>
				</td>
			</tr>
			<tr>
				<td>
					<span>拉丝长度：</span>
					<label id="4009"></label>
				</td>
				<td>
					<span>拉丝条数：</span>
					<label id="4010"></label>
				</td>
				<td>
					<span>收线部分选择：</span>
					<label id="6013"></label>
				</td>
				<td>
					<span>总电能：</span>
					<label id="2000"></label>
				</td>
			</tr>
				<tr>
					<td>
						<span>A相电压：</span>
						<label id="2001"></label>
					</td>
					<td>
						<span>B相电压：</span>
						<label id="2002"></label>
					</td>
					<td>
						<span>C相电压：</span>
						<label id="2003"></label>
					</td>
					<td>
						<span>A相电流：</span>
						<label id="2004"></label>
					</td>
				</tr>
				<tr>
					<td>
						<span>B相电流：</span>
						<label id="2005"></label>
					</td>
					<td>
						<span>C相电流：</span>
						<label id="2006"></label>
					</td>
					<td>
						<span>总有功率：</span>
						<label id="2007"></label>
					</td>
					<td>
						<span>A相有功率：</span>
						<label id="2008"></label>
					</td>
				</tr>
				<tr>
					
					<td>
						<span>B相有功率：</span>
						<label id="2009"></label>
					</td>
					<td>
						<span>C相有功率：</span>
						<label id="2010"></label>
					</td>
					<td>
						<span>总无功率：</span>
						<label id="2011"></label>
					</td>
					<td>
						<span>A相无功率：</span>
						<label id="2012"></label>
					</td>
				</tr>
				<tr>
					
					<td>
						<span>B相无功率：</span>
						<label id="2013"></label>
					</td>
					<td>
						<span>C相无功率：</span>
						<label id="2014"></label>
					</td>
					<td>
						<span>总功率因数：</span>
						<label id="2015"></label>
					</td>
					<td>
						<span>A相功率因数：</span>
						<label id="2016"></label>
					</td>
				</tr>
				<tr>
					
					<td>
						<span>B相功率因数：</span>
						<label id="2017"></label>
					</td>
					<td>
						<span>C相功率因数：</span>
						<label id="2018"></label>
					</td>
					
				</tr>
				
				
			</table>
		</div>
		
		
		
		<script type="text/javascript" src="<%=basePath%>core/js/jquery-2.1.4.min.js" ></script>
		<script type="text/javascript" src="<%=basePath%>core/js/board/strepsinema.js" ></script>
		
	</body>
	
</html>
