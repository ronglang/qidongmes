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
		<base href="<%=basePath%>">

		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>plaCourseList</title>
		<link href="<%=basePath%>app/css/newpage.css" rel="stylesheet" />
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
		<link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" />
		<link href="<%=basePath%>app/css/newpage.css" rel="stylesheet" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		
		<script src="<%=basePath %>app/js/plaManage/course/plaCourseList.js" type="text/javascript"></script>
		<script>
			var basePath  = '<%=basePath%>';
			var routeName = "plaCourseManage";
			var row_id = null;
			//debugger;
    	</script>
	</head>

	<body style="padding:6px; overflow:hidden;width:100%;height:100%;">
		<div style="width:100%;height:100%;">
			<div id="message" style="width:800px"></div>
			
			<!-- <div class="l-loading" style="display:block" id="pageloading"></div>  -->
			<form id="plaCourseManageListForm">
				<table cellpadding="0" cellspacing="0" class="l-table-edit" >
					<tr class="table-edit-tr">
						<td align="right" class="l-table-edit-td">工单是否已分解:</td>
						<td class="l-table-edit-td-input">
						    <input id="decomposeFlag" name="decomposeFlag" type="hidden" ztree="true" value="" />
							<div style="width: 177px; height: 22px; position: relative;">
									<input id="decomposeFlagShow" name="decomposeFlagShow" onfocus="this.blur()" type="text" ztree="true" 
										validate="{required:false}" placeholder="请选择" title="请选择"
										style="margin: 0px; padding-left: 5px; width: 170px; height: 20px; color: rgb(34, 34, 34);"/>
										<img
										src="<%=basePath%>core/img/btn_03.png"
										style="position: absolute; right: 0px; top: 2px;"
										id="decomposeFlagBtn" href="#"
										onclick="showMenu('decomposeFlag'); return false;" />
							</div>
						</td>
						<td align="left">&nbsp;</td>
						<td align="right" class="l-table-edit-td">计划是否已生成:</td>
						<td class="l-table-edit-td-input">
						    <input id="planFlag" name="planFlag" type="hidden" ztree="true" value="" />
							<div style="width: 177px; height: 22px; position: relative;">
									<input id="planFlagShow" name="planFlagShow" onfocus="this.blur()" type="text" ztree="true" 
										validate="{required:false}" placeholder="请选择" title="请选择"
										style="margin: 0px; padding-left: 5px; width: 170px; height: 20px; color: rgb(34, 34, 34);"/>
										<img
										src="<%=basePath%>core/img/btn_03.png"
										style="position: absolute; right: 0px; top: 2px;"
										id="planFlagBtn" href="#"
										onclick="showMenu('planFlag'); return false;" />
							</div>
						</td>
						<td align="left">&nbsp;</td>
						<td align="right" class="l-table-edit-td">工单类型:</td>
						<td class="l-table-edit-td-input">
						    <input id="wsType" name="wsType" type="hidden" ztree="true" value="" />
							<div style="width: 177px; height: 22px; position: relative;">
									<input id="wsTypeShow" name="wsTypeShow" onfocus="this.blur()" type="text" ztree="true" 
										validate="{required:false}" placeholder="请选择" title="请选择"
										style="margin: 0px; padding-left: 5px; width: 170px; height: 20px; color: rgb(34, 34, 34);"/>
										<img
										src="<%=basePath%>core/img/btn_03.png"
										style="position: absolute; right: 0px; top: 2px;"
										id="wsTypeBtn" href="#"
										onclick="showMenu('wsType'); return false;" />
							</div>
						</td>
						<td align="left">&nbsp;</td>
						<td align="right" class="l-table-edit-td">优先方式:</td>
						<td class="l-table-edit-td-input">
						    <input id="priorityWay" name="priorityWay" type="hidden" ztree="true" value="" />
							<div style="width: 177px; height: 22px; position: relative;">
									<input id="priorityWayShow" name="priorityWayShow" onfocus="this.blur()" type="text" ztree="true" 
										validate="{required:false}" placeholder="请选择" title="请选择"
										style="margin: 0px; padding-left: 5px; width: 170px; height: 20px; color: rgb(34, 34, 34);"/>
										<img
										src="<%=basePath%>core/img/btn_03.png"
										style="position: absolute; right: 0px; top: 2px;"
										id="priorityWayBtn" href="#"
										onclick="showMenu('priorityWay'); return false;" />
							</div>
						</td>
						<td align="left">&nbsp;</td>
						<td  style="align:right;text-align:right;">
							<button type="button" id="btn_query" class="btn-search" onclick="reloadData(['decomposeFlag','planFlag','wsType','priorityWay']); "></button>
							<button type="button" id="btn_query" class="btn-reset" onclick="reloadData(['decomposeFlag','planFlag','wsType','priorityWay'],true); "></button>
						</td>
					</tr>
				</table>
				<!-- <br/> -->
				<button type="button" onclick="pub_del(routeName)" class="btn-del" ></button>
				<!-- <button type="button" id="add" class="btn-add" ></button> -->
				<!-- 
				<input name="priorityWay_" type="radio" ltype="radio" radio=true checked value="产能优先"/>产能优先
      			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      			<input name="priorityWay_" type="radio" ltype="radio" radio=true value="节能优先" />节能优先
      			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      			<input name="priorityWay_" type="radio" ltype="radio" radio=true value="成本优先" />成本优先
      			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      			<input name="priorityWay_" type="radio" ltype="radio" radio=true value="自定义" />自定义
      		    -->
      			<!-- <button type="button"  id="btn_kd" onclick="genGD(routeName)" value="分解">分解</button>
      			<button type="button"  id="btn_kd" onclick="genGD_detail(routeName)" value="分解_明细">分解_明细</button>
      			<button type="button"  id="btn_kd_2" onclick="genGD_2(routeName)" value="生成计划">生成计划</button> -->
				<div id="plaCourseManageList" style="margin:0; padding:0;overflow:auto;"></div>
			</form>
		  	<div style="display:none;"></div>
		</div>
	</body>
</html>

