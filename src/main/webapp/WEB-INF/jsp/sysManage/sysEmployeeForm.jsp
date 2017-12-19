<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String sysEmployee_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">

		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>sysEmployeeForm</title>
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="<%=basePath%>core/css/public.css" />
		<script type="text/javascript" src="<%=basePath%>core/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="<%=basePath%>core/js/cssBase.js"></script>
		<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

		<script>
			var basePath  = '<%=basePath%>';
    	</script>
    		<style>
    		table{
    			margin:30px auto;
    		}
    		textarea{
    		margin-top:15px ; 
    		margin-bottom:15px;
		resize:none;
    		}
    	</style>
	</head>

	<body>
		<form id="SysEmployee">
		<!--  		<input	id="picture" type="hidden" ltype="text" readonly="readonly"></input>
		 -->
		<table>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">姓名：</td>
				<td align="left" class="l-table-edit-td-input">
				<input id="name" type="text" ltype="text" readonly="readonly"></input>
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">性别：</td>
				<td align="left" class="l-table-edit-td-input">
				<input id="sex" type="text" ltype="text" readonly="readonly"></input>
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">民族：</td>
				<td align="left" class="l-table-edit-td-input">
				<input id="nation" type="text" ltype="text" readonly="readonly"></input>
				</td>
				
				<!-- 
				<td align="right" class="l-table-edit-td">健康状况：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="statusName" type="text" ltype="text" readonly="readonly"></input>
				</td>
								
				 
				<td rowspan="4" colspan="1" >
					<div style="height: 163px;width: 163px;border:1px solid gray" align="center">
						<img id="pictureShow" name="pictureShow" alt="" style="height: 160px;width: 160px"
							src="">
					</div></td>
				-->
			</tr>
			<tr class="l-table-edit-tr">
			    <td align="right" class="l-table-edit-td">身份证号：</td>
				<td align="left" class="l-table-edit-td-input">
				<input id="idNo" type="text" ltype="text" readonly="readonly"></input>
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">政治面貌：</td>
				<td align="left" class="l-table-edit-td-input">
				<input id="zzmm" type="text" ltype="text" readonly="readonly"></input>
				</td>
				<td align="left"></td>				
				<td align="right" class="l-table-edit-td">学历：</td>
				<td align="left" class="l-table-edit-td-input">
				<input id="education" type="text" ltype="text" readonly="readonly"></input>
				</td>
				
			</tr>
<!-- 			<tr class="l-table-edit-tr"> -->
<!-- 				<td align="right" class="l-table-edit-td">学位：</td> -->
<!-- 				<td align="left" class="l-table-edit-td-input"><input -->
<!-- 					id="degrees" type="text" ltype="text" readonly="readonly"></input> -->
<!-- 				</td> -->
<!-- 				<td align="left"></td> -->
<!-- 				<td align="right" class="l-table-edit-td">学校名称：</td> -->
<!-- 				<td align="left" class="l-table-edit-td-input"><input -->
<!-- 					id="schoolName" type="text" ltype="text" readonly="readonly"></input> -->
<!-- 				</td> -->
<!-- 				<td align="left"></td> -->
			
<!-- 			</tr> -->

                <tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">所在机构：</td>
				<td align="left" class="l-table-edit-td-input">
				<input id="workUnit" type="text" ltype="text" readonly="readonly"></input>
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">职务：</td>
				<td align="left" class="l-table-edit-td-input">
				<input	id="position" type="text" ltype="text" readonly="readonly"></input>
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">单位性质：</td>
				<td align="left" class="l-table-edit-td-input">
				<input	id="unitType" type="text" ltype="text" readonly="readonly"></input>
				</td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">籍贯：</td>
				<td align="left" class="l-table-edit-td-input">
				<input	id="nativePlace" type="text" ltype="text" readonly="readonly"></input>
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">现居地：</td>
				<td align="left" class="l-table-edit-td-input">
				<input	id="currentPlace" type="text" ltype="text" readonly="readonly"></input>
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">出生日期：</td>
				<td align="left" class="l-table-edit-td-input">
				<input	id="birthday" type="text" ltype="text" readonly="readonly"></input>
				</td>
			</tr>
			
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">EMAIL：</td>
				<td align="left" class="l-table-edit-td-input">
				<input	id="email" type="text" ltype="text" readonly="readonly"></input>
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">手机：</td>
				<td align="left" class="l-table-edit-td-input">
				<input id="tel" type="text" ltype="text" readonly="readonly"></input>
				</td>
				<td align="left"></td>
				
			</tr>
			<!-- 
			<tr class="l-table-edit-tr">
				
				<td align="left"></td>
				  
				<td align="right" class="l-table-edit-td">婚姻状况：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="maritalStatusName" type="text" ltype="text" readonly="readonly"></input>
				</td>
				
				
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				
				<td align="left"></td>
				<!--  
				<td align="right" class="l-table-edit-td">办公电话：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="officeTel" type="text" ltype="text" readonly="readonly"></input>
				</td>
				
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">籍贯：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="nativePlace" type="text" ltype="text" readonly="readonly"></input>
				</td>
				
				<td align="left"></td>
			</tr>
			 
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">入党时间：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="joinCommyTime" type="text" ltype="text" readonly="readonly"></input>
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">岗位级别：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="levels" type="text" ltype="text" readonly="readonly"></input>
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">职务：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="position" type="text" ltype="text" readonly="readonly"></input>
				</td>
				<td align="left"></td>
			</tr>
			
			<tr class="l-table-edit-tr">
				
				<td align="left"></td>
				
				<td align="right" class="l-table-edit-td">工作时间：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="hiredDate" type="text" ltype="text" readonly="readonly"></input>
				</td>
				<td align="left"></td>
				
				<td align="right" class="l-table-edit-td"></td>
				<td align="left" class="l-table-edit-td-input"></input>
				</td>
				<td align="left"></td>

			</tr>
			-->
			<!-- 
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">全日制教育学历：</td>
				<td align="left" class="l-table-edit-td-input"><input id="educations"
					type="text" ltype="text" readonly="readonly"></input>
				</td>
				<td align="left"></td>
					<td align="right" class="l-table-edit-td">毕业院校及专业：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="schoolName" type="text" ltype="text" readonly="readonly"></input>
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td"></td>
				<td align="left" class="l-table-edit-td-input">
				<td align="left"></td>
			</tr>
			
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">在职教育学历：</td>
				<td align="left" class="l-table-edit-td-input"><input id="oneducations"
					type="text" ltype="text" readonly="readonly"></input>
				</td>
				<td align="left"></td>
					<td align="right" class="l-table-edit-td">毕业院校及专业：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="onschoolName" type="text" ltype="text" readonly="readonly"></input>
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td"></td>
				<td align="left" class="l-table-edit-td-input">
				<td align="left"></td>
			</tr>
			 -->
	
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td ">备注：</td> 
				<td align="left" class="l-table-edit-td-input" colspan="7">
				<textarea id="remark"  cols="120" rows="8" readonly></textarea>
				</td>
			</tr>
		</table>
	</form>
	</body>

	<script type="text/javascript">
	
	if(document.all){
		 var textArea=$("textarea");
		 for(var i=0;i<textArea.length;i++){
		 textArea[i].style.width="778px";  
		 }
		 $("#title").attr("ligerui",'{width:778}');
		 
	}else{
		 var textArea=$("textarea");
		 for(var i=0;i<textArea.length;i++){
		 textArea[i].style.width="720px";  
		 }
		 $("#title").attr("ligerui",'{width:720}');
	}
	
	
	var routeName = "sysEmployeeManage"; 
	var row_id = "";
	row_id = <%=sysEmployee_id%>;
	
	$(document).ready(function() {
		if (row_id != null) {
			$("#SysEmployee").ligerForm();
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
		}
	});
	
	//pub_initForm加载数据成功回调函数，picture字段存的是服务器保存照片的url
	function initSuccessCallback(json){
		showPicture(json.data.picture);
	}
	
	function showPicture(url){
		var url=$("#picture").val();
		if(url==""||url==null){
			url=basePath+"app/images/noimg.jpg";
		}else{
			url=basePath+url;
		}
		$("#pictureShow").attr("src", url);
	}
  	</script>
</html>

