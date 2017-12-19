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
		<title>sysContactsList</title>
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
		<link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" />
		
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	    <!-- ztree -->
		<script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
		<!-- ztree -->
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		
		<script src="<%=basePath%>app/js/electronicFax/cevent.js" type="text/javascript"></script>
		<script src="<%=basePath%>app/js/electronicFax/electronicFaxParam.js" type="text/javascript"></script>
		<script src="<%=basePath %>app/js/sysManage/sysContactsList.js" type="text/javascript"></script>
		
		<script>
			var basePath  = '<%=basePath%>';
			var routeName = "sysContactsManage";    
    	</script>
	</head>

	<body style="padding:6px; overflow:hidden;">
		<div id="message" style="width:800px"></div>
		<div class="l-loading" style="display:block" id="pageloading"></div> 
			<form id="sysContactsManageListForm">
				<fieldset class="l-fieldset">
			 		<legend class="l-legend">
						
					</legend>
				</fieldset>
				<br/>
				<button type="button" onclick="pub_del(routeName)" class="btn-del" style="margin-right: 10px;">删除</button>
				<button type="button" id="add" class="btn-add" style="margin-right: 10px;">新增</button>
				<button type="button" id="export" class="btn-add" onclick="javascript:exportExcel()">导出excel</button></br>
				<button id="all" class="btn-all" class="pinYin">全部</button>
				<label onclick="sorts('A')" class="pinYin">A</label>
				<label onclick="sorts('B')" class="pinYin">B</label>
				<label onclick="sorts('C')" class="pinYin">C</label>
				<label onclick="sorts('D')" class="pinYin">D</label>
				<label onclick="sorts('E')" class="pinYin">E</label>
				<label onclick="sorts('F')" class="pinYin">F</label>
				<label onclick="sorts('G')" class="pinYin">G</label>
				<label onclick="sorts('H')" class="pinYin">H</label>
				<label onclick="sorts('I')" class="pinYin">I</label>
				<label onclick="sorts('J')" class="pinYin">J</label>
				<label onclick="sorts('K')" class="pinYin">K</label>
				<label onclick="sorts('L')" class="pinYin">L</label>
				<label onclick="sorts('M')" class="pinYin">M</label>
				<label onclick="sorts('N')" class="pinYin">N</label>
				<label onclick="sorts('O')" class="pinYin">O</label>
				<label onclick="sorts('P')" class="pinYin">P</label>
				<label onclick="sorts('Q')" class="pinYin">Q</label>
				<label onclick="sorts('R')" class="pinYin">R</label>
				<label onclick="sorts('S')" class="pinYin">S</label>
				<label onclick="sorts('T')" class="pinYin">T</label>
				<label onclick="sorts('U')" class="pinYin">U</label>
				<label onclick="sorts('V')" class="pinYin">V</label>
				<label onclick="sorts('W')" class="pinYin">W</label>
				<label onclick="sorts('X')" class="pinYin">X</label>
				<label onclick="sorts('Y')" class="pinYin">Y</label>
				<label onclick="sorts('Z')" class="pinYin">Z</label><br>
				<label>姓名:</label>
				<input name="name" type="text" ltype="text">
				<label>所属机构:</label>
					<input id="orgId" name="orgId" type="hidden" ztree="true" value="" />
					<input id="orgIdShow" name="orgIdShow" type="text" ztree="true" style="width:168px;"/>
					<img src="<%=basePath%>core/img/btn_03.png" style="position: relative; right: 25px; top: 5px;" id="orgIdBtn"
					href="#" onclick="showMenu('orgId'); return false;" /> 
				<label>职位:</label>
					<input id="position" name="position" type="hidden" ztree="true" value="" />
					<input id="positionShow" name="positionShow" type="text" ztree="true" style="width:168px;"/>
					<img src="<%=basePath%>core/img/btn_03.png" style="position: relative; right: 25px; top: 5px;" id="positionBtn"
					href="#" onclick="showMenu('position'); return false;" /> </br>
				<label>手机:</label>
				<input name="mobileTel" type="text" ltype="text">
				<label>办公电话:</label>
				<input name="officeTel" type="text" ltype="text" style="margin-bottom: 10px;">
				<input type="button" class="btn-search" onclick="reloadData()" value="查询"/>
				<br>
				<div id="sysContactsGroupList" style="float:left"></div>
				<div style="float:left;width:87%;">
					<div id="sysContactsManageList" style="float: left;"></div>
				</div>
			</form>
		  	<div style="display:none;">
		</div>
	</body>
</html>
<script type="text/javascript">
function exportExcel(){
	window.location.href =  basePath + "rest/"+routeName+"Action/vwexportExcel";
}
</script>
