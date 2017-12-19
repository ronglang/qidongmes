<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>生产质检打印</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="<%=basePath%>core/img/logos.png">
	
	<script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
	
	<style type="text/css">
			*{
				margin: 0;
				padding: 0;
			}
			#main{
				margin: 30px auto;
				width: 65%;
			}
			#main h3{
				text-align: center;
				margin: 20px auto;
				letter-spacing: 3px;
			}
			#main .top{
				width: 100%;
			}
			#main .top table{
				margin: 0 auto;
				width: 100%;
			}
			#main .top table tr{
				height: 40px;
			}
			#main .top table tr td{
				font-size: 15px;
			}
			#main .top table tr td span{
				display: inline-block;
				width: 130px;
				height: 30px;
				line-height:30px;
				text-align: right;
				background-color: #CECED1;
			}
			#main .top table tr td label{
				font-size: 13px;
				margin-left: 10px;
			}
			#main .bottom table{
				margin: 20px auto;
				width: 100%;
			}
			#main .bottom table tr{
				height: 40px;
			}
			#main .bottom table tr th{
				background-color: #DEDEDE;
				border: 1px solid #D6D6D6;
			}
			#main .bottom table tr td{
				font-size: 15px;
				text-align: center;
				border: 1px solid #DFDFDF;
			}
			#main .bottom table tr td span{
				display: inline-block;
				width: 130px;
				text-align: right;
			}
		</style>
		
		<script type="text/javascript">
		var basePath = '<%=basePath %>';
		
			$(function(){
				var axisName = '${axisName}';
				$.ajax({
					url:basePath+ "rest/qualityMauReportManageAction/getPrintMauReport?axisName="+axisName,
					dataType:'json',
					type:'post',
					success:function(data){
						//var data = eval("("+datas+")");
						if(data.success){
							//debugger;
							$("#testType").html(data.data.testType);
							$("#reportCode").html(data.data.reportCode);
							$("#axisName").html(data.data.axisName);
							$("#seqName").html(data.data.seqName);
							$("#macCode").html(data.data.macCode);
							$("#courseCode").html(data.data.courseCode);
							$("#proGgxh").html(data.data.proGgxh);
							$("#testBy").html(data.data.testBy);
							$("#testDate").html(data.data.testDate);
							$("#pageCode").html(data.data.pageCode);
							$("#remark").html(data.data.remark);
							$("#testState").html(data.data.testState);
							$("#testResult").html(data.data.testResult);
							$("#typeName").html(data.data.typeName);
							$("#testAccording").html(data.data.testAccording);
							$("#isCome").html(data.data.isCome);
							var html = "";
							$("#params").html("");
							html += "<tr>";
							html += "<th>参数名称</th>";
							html += "<th>参数值</th>";
							html += "<th>第一次</th>";
							html += "<th>第二次</th>";
							html += "</tr>";
							var list = data.data.vaList;
							for(var i=0;i<list.length;i++){
								html += "<tr>";
								html += "<td>"+list[i].paramName+"</td>";
								html += "<td>"+list[i].referValue+"</td>";
								html += "<td>"+list[i].paramValue+"</td>";
								html += "<td>"+list[i].secondValue+"</td>";
								html += "</tr>";
							}
							$("#params").append(html);
							
						}else{
							$.ligerDialog.error(data.msg);
						}
					},
				error:function(data){
					alert(data.msg)
				}
					
				});
				
			});
		
		
		</script>

  </head>
  
  <body>
    
    <div id="main">
			<h3>生产质检打印</h3>
			<div class="top">
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td><span>测试类型：</span><label id="testType"></label></td>
						<td><span>系统质检编号：</span><label id="reportCode"></label></td>
						<td><span>轴名称：</span><label id="axisName"></label></td>
						<td><span>工序名称：</span><label id="seqName"></label></td>
					</tr>
					<tr>
						<td><span>机器编码：</span><label id="macCode"></label></td>
						<td><span>工单编号：</span><label id="courseCode"></label></td>
						<td><span>产品规格型号：</span><label id="proGgxh"></label></td>
						<td><span>检测人：</span><label id="testBy"></label></td>
					</tr>
					<tr>
						<td><span>检测时间：</span><label id="testDate"></label></td>
						<td><span>纸质报告编码：</span><label id="pageCode"></label></td>
						<td><span>备注：</span><label id="remark"></label></td>
						<td><span>检测状态：</span><label id="testState"></label></td>
					</tr>
					<tr>
						<td><span>检测结果：</span><label id="testResult"></label></td>
						<td><span>是否入库：</span><label id="isCome"></label></td>
						<td><span>检测类型名称：</span><label id="typeName"></label></td>
						<td><span>检测依据：</span><label id="testAccording"></label></td>
						
					</tr>
				</table>
			</div>
			<div class="bottom">
				<table border="0" cellspacing="0" cellpadding="0" id="params">
				
				</table>
			</div>
			
		</div>
    
    
  </body>
</html>
