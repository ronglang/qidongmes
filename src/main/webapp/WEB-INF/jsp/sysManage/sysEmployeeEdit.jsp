<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String sysEmployee_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>sysEmployeeEdit</title>
 <!-- <link rel="stylesheet" type="text/css" href="<%=basePath%>app/css/initStyle.css">-->
<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
<link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" />
<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<!--  <link href="<%=basePath%>app/css/newpage.css" rel="stylesheet" /> -->
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "sysEmployeeManage"; 
	var row_id = "";
	row_id =<%=sysEmployee_id%>;
</script>
<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
 <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
<!-- 默认引用1end -->
<script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
<script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
<!-- 默认引用end -->
<script src="<%=basePath%>core/plugin/json.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/swfupload/swfupload.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/swfupload/swfupload.queue.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/swfupload/fileprogress.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/swfupload/swfuploadProperties.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/swfupload/handlers.js" type="text/javascript"></script>
<!-- 默认引用 -->
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script src="<%=basePath%>app/js/sysManage/sysEmployeeEdit.js" type="text/javascript"></script>


<script type="text/javascript">
	$(document).ready(function() {
		$("#graduateTime,#hiredDate,#birthDate,#joinCommyTime").ligerDateEditor({
			format : "yyyy-MM-dd hh:mm:ss"
		});
	});
</script>
    	<style>
    		table{
    			margin:30px auto;
    		}
    		table tr {
    			height : 30px ;
    		}
    		textarea{
    		margin-top:15px ; 
    		margin-bottom:15px;
		resize:none;
    		}
    	</style>
</head>

<body>
	<form id="sysEmployeeManage" method="post">
		<div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit">
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">姓名：<span style="color:red">*</span></td>
				<td align="left" class="l-table-edit-td-input">
				<input id="name"	name="name" type="text" ltype="text" required="required"/>
				</td>
				
				<td align="right" class="l-table-edit-td">身份证号：<span style="color:red">*</span></td>
				<td align="left" class="l-table-edit-td-input">
				<input id="idNo"	name="idNo" type="text" ltype="text" validate="{number:true,minlength:15,maxlength:18}"/>
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">性别：</td>
				<td align="left" class="l-table-edit-td-input">
				<input	id="sex" name="sex" type="hidden" ztree="true" value="" />
				<div style="width:177px; height:22px;position:relative;">
						<input id="sexShow" name="sexShow" type="text" ztree="true" required="required"
							style="margin: 0px;padding-left:5px; width: 170px; height: 20px;  color: rgb(34, 34, 34);" /><img
							src="core/img/btn_03.png"
							style="position:absolute; right:0px; top:2px;" id="sexBtn"
							href="#" onclick="showMenu('sex'); return false;" />
					</div>
				</td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">人员类型：<span style="color:red">*</span></td>
				<td align="left" class="l-table-edit-td-input">
					<input id="userType" name="userType" type="hidden" ltype="text"/>
					<div style="width:177px; height:22px;position:relative;">
						<input id="userTypeShow" name="userTypeShow" type="text" ztree="true" required="required"
							style="margin: 0px;padding-left:5px; width: 170px; height: 20px;  color: rgb(34, 34, 34);" /><img
							src="core/img/btn_03.png"
							style="position:absolute; right:0px; top:2px;" id="userTypeBtn"
							href="#" onclick="showMenu('userType'); return false;" />
					</div>
				</td>
				<td align="right" class="l-table-edit-td">登陆帐户：</td>
				<td align="left" class="l-table-edit-td-input">
				<input id="account"	name="account" type="text" ltype="text" validate="{maxlength:32}"/>
				</td>
				<td align="left"></td>
			</tr>
		
		<tr class="l-table-edit-tr">		
			<td align="right"  class="l-table-edit-td">健康状况：</td>
				<td align="left" class="l-table-edit-td-input">
				<input	id="health" name="health" type="hidden" ztree="true" value="" />
				<div style="width:177px; height:22px;position:relative;">
						<input id="healthShow" name="healthShow" type="text" ztree="true" 
							style="margin: 0px;padding-left:5px; width: 170px; height: 20px;  color: rgb(34, 34, 34);" /><img
							src="core/img/btn_03.png"
							style="position:absolute; right:0px; top:2px;" id="healthBtn"
							href="#" onclick="showMenu('health'); return false;" />
					</div>
				</td>
				
				<td align="right" class="l-table-edit-td">民族：</td>
				<td align="left" class="l-table-edit-td-input">
				<input	id="nation" name="nation" type="hidden" ztree="true" value="" />
				<div style="width:177px; height:22px;position:relative;">
						<input id="nationShow" name="nationShow" type="text"
							ztree="true" 
							style="margin: 0px;padding-left:5px; width: 170px; height: 20px;  color: rgb(34, 34, 34);" /><img
							src="core/img/btn_03.png"
							style="position:absolute; right:0px; top:2px;" id="nationBtn"
							href="#" onclick="showMenu('nation'); return false;" />
					</div>
				</td>
			<td align="left"></td>				
				<td align="right" class="l-table-edit-td">政治面貌：</td>
				<td align="left" class="l-table-edit-td-input">
				<input	id="zzmm" name="zzmm" type="hidden" ztree="true" value="" />
				<div style="width:177px; height:22px;position:relative;">
						<input id="zzmmShow" name="zzmmShow" type="text" ztree="true"  
							style="margin: 0px;padding-left:5px; width: 170px; height: 20px;  color: rgb(34, 34, 34);" /><img
							src="core/img/btn_03.png"
							style="position:absolute; right:0px; top:2px;" id="zzmmBtn"
							href="#" onclick="showMenu('zzmm'); return false;" />
					</div>
				</td>
				
				<!--  
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">学历：<span style="color:red">*</span></td>
				<td align="left" class="l-table-edit-td-input"><input
					id="education" name="education" type="hidden" ztree="true" value="" />
				<div style="width:177px; height:22px;position:relative;">
						<input id="educationShow" name="educationShow" type="text"
							ztree="true" required="required"
							style="margin: 0px;padding-left:5px; width: 170px; height: 20px;  color: rgb(34, 34, 34);" /><img
							src="core/img/btn_03.png"
							style="position:absolute; right:0px; top:2px;" id="educationBtn"
							href="#" onclick="showMenu('education'); return false;" />
					</div>
				</td>
				<td align="left"></td>
				-->
			</tr>
			
			<!--  
			<tr class="l-table-edit-tr">

				<td align="right" class="l-table-edit-td">学位：<span style="color:red">*</span></td>
				<td align="left" class="l-table-edit-td-input"><input
					id="degree" name="degree" type="hidden" ztree="true" value="" />
				<div style="width:177px; height:22px;position:relative;">
						<input id="degreeShow" name="degreeShow" type="text" ztree="true" required="required"
							style="margin: 0px;padding-left:5px; width: 170px; height: 20px;  color: rgb(34, 34, 34);" /><img
							src="core/img/btn_03.png"
							style="position:absolute; right:0px; top:2px;" id="degreeBtn"
							href="#" onclick="showMenu('degree'); return false;" />
					</div>
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">学校名称：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="schoolName" name="schoolName" type="text" ltype="text" />
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">毕业时间：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="graduateTime" name="graduateTime" type="text" ltype="date" />
				</td>
				<td align="left"></td>
			</tr>
			-->
			

			
		<tr class="l-table-edit-tr">			
			 <td align="right" class="l-table-edit-td">学历：</td>
				<td align="left" class="l-table-edit-td-input">
				<input	id="education" name="education" type="hidden" ztree="true" value="" />
				<div style="width:177px; height:22px;position:relative;">
						<input id="educationShow" name="educationShow" type="text"
							ztree="true" 
							style="margin: 0px;padding-left:5px; width: 170px; height: 20px;  color: rgb(34, 34, 34);" /><img
							src="core/img/btn_03.png"
							style="position:absolute; right:0px; top:2px;" id="educationBtn"
							href="#" onclick="showMenu('education'); return false;" />
					</div>
				</td>
				
				<td align="right" class="l-table-edit-td">所在机构：</td>
				<td align="left" class="l-table-edit-td-input">
				<input	id="workUnit" name="workUnit" type="text" ltype="text"  />
				<!--  
				<div style="width:177px; height:22px;position:relative;">
						<input id="orgCodeShow" name="orgCodeShow" type="text"
							ztree="true" required="required"
							style="margin: 0px;padding-left:5px; width: 170px; height: 20px;  color: rgb(34, 34, 34);" /><img
							src="core/img/btn_03.png"
							style="position:absolute; right:0px; top:2px;" id="orgCodeBtn"
							href="#" onclick="showMenu('orgCode'); return false;" />
					</div>
					-->
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">职务：</td>
				<td align="left" class="l-table-edit-td-input">
				<input	id="position" name="position" type="text" ltype="text" ></input>
				</td>
			</tr>
			
			<tr class="l-table-edit-tr">
			<!--  
				<td align="right" class="l-table-edit-td">出生地：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="birthPlace" name="birthPlace" type="text" ltype="text" />
				</td>
				-->
				<td align="right" class="l-table-edit-td">单位性质：</td>
				<td align="left" class="l-table-edit-td-input">
				<input	id="unitType" name="unitType" type="text" ltype="text" ></input>
				</td>
				
				<td align="right" class="l-table-edit-td">籍贯：</td>
				<td align="left" class="l-table-edit-td-input">
				<input	id="nativePlace" name="nativePlace" type="text" ltype="text"  />
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">现居地：</td>
				<td align="left" class="l-table-edit-td-input">
				<input	id="currentPlace" name="currentPlace" type="text" ltype="text"  />
				</td>
				<!--  
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">工作时间：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="hiredDate" name="hiredDate" type="text" ltype="date" />
				</td>
				-->		
			</tr>
				<!--  
			<tr class="l-table-edit-tr">	
				<td align="right" class="l-table-edit-td">婚姻状况：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="maritalStatus" name="maritalStatus" type="hidden" ztree="true"
					value="" />
				<div style="width:177px; height:22px;position:relative;">
						<input id="maritalStatusShow" name="maritalStatusShow" type="text"
							ztree="true"
							style="margin: 0px;padding-left:5px; width: 170px; height: 20px;  color: rgb(34, 34, 34);" /><img
							src="core/img/btn_03.png"
							style="position:absolute; right:0px; top:2px;"
							id="maritalStatusBtn" href="#"
							onclick="showMenu('maritalStatus'); return false;" />
					</div>
				</td>	
				<td align="left"></td>
			</tr>
			-->
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">出生日期：</td>
				<td align="left" class="l-table-edit-td-input">
				<input	id="birthday" name="birthday" type="text" ltype="date" />
				</td>
				
			   <td align="right" class="l-table-edit-td">EMAIL：</td>
				<td align="left" class="l-table-edit-td-input">
				<input	id="email" name="email" type="text" ltype="text" />
				</td>				
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">手机：<span style="color:red">*</span></td>
				<td align="left" class="l-table-edit-td-input">
				<input	id="tel" name="tel" type="text" ltype="text" validate="{number:true,minlength:11,maxlength:11}" />
				</td>
				<!--
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">办公电话：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="officeTel" name="officeTel" type="text" ltype="text" validate="{number:true,maxlength:11}"/>
				</td>
				 <td align="left"></td>
				<td align="right" class="l-table-edit-td">职位：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="position" name="position" type="text" ltype="text" />
				</td> 
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">职务：</td>
				<td align="left" class="l-table-edit-td-input">
					<input id="position" name="position" type="text" ltype="text" value="" />
				</td>
				-->
			
			</tr>
			<!-- 
			<tr class="l-table-edit-tr">
			
				<td align="right" class="l-table-edit-td">入党时间：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="joinCommyTime" name="joinCommyTime" type="text" ltype="date" />
				</td>
				<td align="left"></td>
				
				<td align="right" class="l-table-edit-td">岗位级别：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="level" name="level" type="hidden" ztree="true" value="" />
				<div style="width:177px; height:22px;position:relative;">
						<input id="levelShow" name="levelShow" type="text" ztree="true"
							style="margin: 0px;padding-left:5px; width: 170px; height: 20px;  color: rgb(34, 34, 34);" /><img
							src="core/img/btn_03.png"
							style="position:absolute; right:0px; top:2px;" id="levelBtn"
							href="#" onclick="showMenu('level'); return false;" />
					</div>
				</td>
				
				<td align="left"></td>
			</tr>
			 -->
			 <!-- 
			<tr class="l-table-edit-tr">
				
					
				<td align="left"></td>
			 
				<td align="right" class="l-table-edit-td">毕业院校及专业：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="schoolName" name="schoolName" type="text" ltype="text" />
				</td>
				
				<td align="left"></td>
				<td align="right" class="l-table-edit-td"></td>
				<td align="left" class="l-table-edit-td-input"></td>
			</tr>
			-->
			<!--  
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">在职教育学历：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="oneducation" name="oneducation" type="hidden" ztree="true" value="" />
				<div style="width:177px; height:22px;position:relative;">
						<input id="oneducationShow" name="oneducationShow" type="text"
							ztree="true" 
							style="margin: 0px;padding-left:5px; width: 170px; height: 20px;  color: rgb(34, 34, 34);" /><img
							src="core/img/btn_03.png"
							style="position:absolute; right:0px; top:2px;" id="oneducationBtn"
							href="#" onclick="showMenu('oneducation'); return false;" />
					</div>
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">毕业院校及专业：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="onschoolName" name="onschoolName" type="text" ltype="text" />
				</td>
				
				<td align="left"></td>
				<td align="right" class="l-table-edit-td"></td>
				<td align="left" class="l-table-edit-td-input"></td>
			</tr>
			
			
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td" >是否参加过挂职锻炼：</td>
				<td align="left" class="l-table-edit-td-input" >
					<input
					id="training" name="training" type="hidden" ztree="true" value="" />
					<div style="width:177px; height:22px;position:relative;">
						<input id="trainingShow" name="trainingShow" type="text" ztree="true"
							style="margin: 0px;padding-left:5px; width: 170px; height: 20px;  color: rgb(34, 34, 34);" /><img
							src="core/img/btn_03.png"
							style="position:absolute; right:0px; top:2px;" id="trainingBtn"
							href="#" onclick="showMenu('training'); return false;" />
					</div>
					
				</td>
			
			</tr>
			-->
			<tr class="l-table-edit-tr">
			<td align="left" ></td>
			<!-- 照片上传 -->
				<td align="left" class="l-table-edit-td-input"  style="border:#cccccc solid 1px;">
					<input id="picture" name="picture" type="hidden"/>
					<img id="emphotoImg" src="<%=basePath%>app/images/noimg.jpg" style="width:184px;height:210px;text-align:center;border:none;">
					<div class="swfdiv">
						<div id="uploadurlShow" style="overflow: hidden;"></div>
						<div id="fsUploadProgress1" style="width:165px">
							<input type="hidden" id="saveDataId1" name="saveDataId1"/>
						</div>
						<div style="padding-left: 20px;">
						<span id="spanButtonPlaceholder1"></span>
						<input id="btnCancel1" type="button" value="取消上传"
						   onclick="cancelQueue(upload1);"	disabled="disabled"
						style="margin-left: 2px; height: 22px; font-size: 8pt;" />
						</div>
					</div></td>
					<!-- 照片上传  end -->
			<td align="left" colspan="5"></td>
			</tr>
		
			<tr class="l-table-edit-tr"> 
 				<td align="right" class="l-table-edit-td">备注：</td>
 				<td align="left" class="l-table-edit-td-input" colspan="6">
 				<textarea 	cols="120" rows="6" id="remark" name="remark" class="l-textarea"></textarea>
				</td> 
				<td align="left"></td> 
			</tr> 
		</table>
    <div style="margin:0px auto;width:250px; clear:both; overflow:hidden; margin-bottom:20px;">
		<input class="l-button l-button-test" type="reset" value="重置" />
		<input class="l-button l-button-submit" type="submit" value="提交" /> 
	</div>
</form>
	<script>
	
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
			 
		}
		
	</script>
</body>
</html>

