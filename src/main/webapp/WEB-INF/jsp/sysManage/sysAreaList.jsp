<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
	<head>
		

		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>sysAreaList</title>
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
	    
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		
		<script src="<%=basePath %>app/js/sysManage/sysAreaList.js" type="text/javascript"></script>
		<script>
			var basePath  = '<%=basePath%>';
			var routeName = "sysAreaManage";    
    	</script>
    	<style type="text/css">
    	.l-table-edit {
		    border:1px solid #9a9a9a;
		    width:99%;
		    table-layout:fixed;
			}
    	</style>
	</head>

	<body style="padding:6px; overflow:hidden;">
		<div id="message" style="width:800px"></div>
			<form id="sysAreaManageListForm">
				<fieldset class="l-fieldset">
			 		<legend class="l-legend">
						
					</legend>
					<div class="sysArea_serch">
  				<table cellpadding="0" cellspacing="0" class="l-table-edit">
  					<tr class=table-edit-tr >
  						<%-- <td style="width: 50px;"><img class="right" src="<%=basePath%>app/images/basicDataManage/village/u46.png" /></td> --%>
  						<td align="right" class="l-table-edit-td">区划名称:</td> 
  						<td style="width:13%" align="left" class="l-table-edit-td-input">
							<input id="aname" name="aname" style= "text-align:center;"   type="text" ltype="text" />
							<!--
							<input id="areaCode" name="areaCode" type="hidden" ztree="true" />
							<div style="width: 177px; height: 22px; position: relative;">
						  placeholder="行政区域" 
									<input id="areaCodeShow" name="areaCodeShow" onfocus="this.blur()" type="text" ztree="true" validate="{required:true}"
										style="margin: 0px; padding-left: 5px; width: 170px; height: 20px; color: rgb(34, 34, 34);" /><img
										src="<%=basePath%>core/img/btn_03.png"
										style="position: absolute; right: 0px; top: 2px;"
										id="areaCodeBtn" href="#"
										onclick="showMenu('areaCode'); return false;" />
							</div>
							-->
						</td>
						<td align="right" class="l-table-edit-td">区划代码:</td> 
  						<td style="width:13%" align="left" class="l-table-edit-td-input">
							<input id="areaCode" name="areaCode" style= "text-align:center;"   type="text" ltype="text" />
						</td>
						<%-- <td style="width: 50px;"><img class="right" src="<%=basePath%>app/images/basicDataManage/village/u152.png" /></td> --%>
					<!--	<td align="right" class="l-table-edit-td">乡镇名称:</td> 
						 placeholder="请输入乡镇名" 
						<td style="width: 150px"><input id="areaName" name="areaName" style="border:solid 1px gray;height:20px;" class="vil_inp" type="text"  /></td>
						-->
						<%-- <td style="width: 50px;"><img class="right" src="<%=basePath%>app/images/hManage/u80.png" /></td> --%>
					<!--	<td align="right" class="l-table-edit-td">数据年度:</td> 
						<td style="width:13%" align="left" class="l-table-edit-td-input">
						 	<%-- <input id="dataYear" name="dataYear" type="text" value="<%=dataYear %>" readOnly="readonly"/> --%>
							<input id="dataYear" name="dataYear" type="hidden" ztree="true"  />
							<div style="width: 177px; height: 22px; position: relative;">
									 placeholder="数据年度" 
									<input id="dataYearShow" name="dataYearShow" onfocus="this.blur()" type="text" ztree="true" validate="{required:true}"
										style="margin: 0px; padding-left: 5px; width: 170px; height: 20px; color: rgb(34, 34, 34);"/><img
										src="<%=basePath%>core/img/btn_03.png"
										style="position: absolute; right: 0px; top: 2px;"
										id="dataYearBtn" href="#"
										onclick="showMenu('dataYear'); return false;" />
							</div>
						</td>
						-->
  						<td style="text-align: left;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" id="btn_query" class="btn-search" onclick="reloadData1(['areaCode','aname'])"></button>
							<button type="button" class="btn-reset" onclick="reloadData1(['areaCode','aname'],true);"></button>
						</td>
  					</tr>
  				</table>
  			</div>
  			<!-- 
				<label>区划名称:</label><input id="aname" name = "aname" type="text" ltype="text" >
				<label>区划代码:</label><input id="areaCode" name = "areaCode" type="text" ltype="text" >
				<input type="button" class="btn-search" onclick="reloadData(['aname','areaCode'])" />
				<input type="button" class="btn-reset" onclick="reloadData(['aname','areaCode'],true)"/>
			 -->
				</fieldset>
				<br/>
				<button type="button" id="add" class="btn-add" ></button>
				<button type="button" onclick="pub_del(routeName)" class="btn-del" ></button>
				<div id="sysAreaManageList" style="margin:0; padding:0"></div>
			</form>
		  	<div style="display:none;">
		</div>
	</body>
</html>

