<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>原材料 form</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
	<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
	<script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
	<script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
	<script src="<%=basePath %>core/js/pictureHandle.js"></script>
	<script src="<%=basePath %>core/js/tools.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <style type="text/css">
			*{
				margin: 0px;
				padding: 0px;
			}
			body,div,table{
				margin: 0px;
				padding: 0px;
				font-size: 14px;
			}
			h3{
				text-align: center;
				margin: 0 auto;
			}
			form{
			}
			ul{
				list-style-type: none;
			}
			#b_add{
				width:80%;
				margin: 0 auto;
				border-top: 1px solid gray;
			}
			#b_add tr{
				height: 50px;
			}
			#b_add tr td{
				width: 33%;
				font-size: 13px;
				position: relative;
			}
			#b_add tr td span{
				display: inline-block;
				width: 100px;
				text-align: right;
			}
			#b_add tr td select{
				width: 80px;
			}
			#b_add tr td input{
				
			}
			#end{
				width: 200px;
				height: 50px;
				margin: 10px auto;
				
			}
			#end input.btn1{
				background-color: #0A90F0;
				border-radius:5px;
				display: inline-block;
				width: 80px;
				height: 25px;
				line-height: 25px;
				color: white;
				cursor: pointer;
				border: none;
				margin: 12px;
			}
			#end input.btn1:hover{
				background-color: #62B4ED;
			}
			#end input.btn2{
				background-color: #AACE1F;
				border-radius:5px;
				display: inline-block;
				width: 80px;
				height: 25px;
				line-height: 25px;
				color: white;
				cursor: pointer;
				border: none;
			}
			#end input.btn2:hover{
				background-color: #CEE574;
			}
			#b_add .error{
				position: absolute;
				top: 10px;
				left: 250px;
				width: 170px;
				height: 30px;
				text-indent: 15px;
				line-height: 30px;
				background: url(<%=basePath %>app/images/errror_bg.png);
				display: none;
			}
			#top{
				width:100%;height:30px;line-height: 30px;
			}
			#top .t_ret{
				width:30%;float: left;text-align: center;text-decoration: underline;
			}
			#top .t_ret a{
				color: #038BDE;
			}
			#top .t_ret a:hover{
				color: red;
			}
			#top .t_text{
				width:40%;text-align: center;display: inline-block;float: left;
			}
			/*智能搜索样式*/
			#searchresult{
		    	width:150px; 
		    	position:absolute;
		    	z-index:1; 
		    	overflow:hidden;
		    	border:1px solid gray;
		    	border-top:none;
		    	background-color: #DBDBDB;
		    }
		    .line{
		    	font-size:12px;
		    	width:150px;
		    	height:20px;
		    	line-height:20px;
		    	text-indent: 2px;
		    }
		    .line:hover{
		    	background-color:gray;
		    	color:white;
		    }
		    .hover{
		    	background-color:gray; 
		    	width:150px;
		    	
		    	color:#767676;
		    }
		    .std{ 
		    	display:inline-block;
		    	text-align:left;
		    	width:100%;
		    	height:20px;
		    	line-height:20px;
		    }
		</style>
		<script type="text/javascript">
			
			var numFlag = false;
			var checkFlag = false;
			var checks = true;
			$(function(){
				$("#surveyDate").ligerDateEditor({format:'yyyy-MM-dd hh:mm:ss',/* label: '检测期', */ showTime:true, labelWidth: 105,labelAlign: 'right' });
				$("#expiryDate").ligerDateEditor({format:'yyyy-MM-dd',/* label: '保质期', */ showTime:true, labelWidth: 105, labelAlign: 'right' });
				$("#releaseDate").ligerDateEditor({format:'yyyy-MM-dd',/* label: '有效期', */ showTime:true, labelWidth: 105, labelAlign: 'right' });
				/*  var surveyDate =${report.surveyDate};
				if (surveyDate!=null) {
					$("#surveyDate").setValue(${report.surveyDate});
				}  */
		
				//---
				
				
				//初始化错误信息
				//$("#b_add .error").html("该字段不能为空");
				
				//执行为空方法
				//$("#sub_btn").click(fun_empty);
				//执行事件方法
				//fun_div();
				
				//返回
				$("#top .t_ret a").click(function(){
					window.location.href = "<%=basePath %>/rest/qualitySurveyReportManageAction/toMaterialPageList";
				});
				
				//智能搜索供应商
				intelSartch();
				
			});
			
			//执行为空方法
			function fun_empty(){
				isEmpty($("#obj_ggxh"));
				isEmpty($("#obj_color"));
				isEmpty($("#supply_agent"));
				isEmpty($("#batchCode"));
				isEmpty($("#rfidCode"));
				isEmpty($("#acount"));
				isEmpty($("#surveyDate"));
				isEmpty($("#expiryDate"));
				isEmpty($("#releaseDate"));
				isEmpty($("#surveyReportCode"));
				isEmpty($("#beizhu"));
			}
			
			//执行事件方法
			function fun_div(){
				overDiv($("#obj_ggxh"));
				overDiv($("#obj_color"));
				overDiv($("#supply_agent"));
				overDiv($("#batchCode"));
				overDiv($("#rfidCode"));
				overDivNum($("#acount"));
				dateOverDiv($("#surveyDate"));
				dateOverDiv($("#expiryDate"));
				dateOverDiv($("#releaseDate"));
				overDiv($("#surveyReportCode"));
				overDiv($("#beizhu"));
			}
			
			//判断input中value是否为空
			function isEmpty(div){
				var divVal = div.val();
				debugger;
//				alert(divVal);
				if("" == divVal || divVal == null){
					div.parents("td").children(".error").css("display","inline-block");
					numFlag = false;
				}
				
			}
			//三个时间判断事件
			function dateOverDiv(div){
				div.mouseover(function(){
					div.parents("td").children(".error").css("display","none");
					numFlag = true;
				});
				div.mouseout(function(){
					if(div.val() == "" || div.val() == null){
						div.parents("td").children(".error").css("display","inline-block");
						numFlag = false;
					}
				});
			}
			//事件判断是否有值存在
			function overDiv(div){
				div.focus(function(){
					div.parents("td").children(".error").css("display","none");
					numFlag = true;
				});
				div.blur(function(){
					if(div.val() == "" || div.val() == null){
						div.parents("td").children(".error").css("display","inline-block");
						numFlag = false;
					}
				});
			}
			//^[0-9]+([.]{1}[0-9]+){0,1}$
			//判断输入的是否为数字或者小数
			function overDivNum(div){
				var attr = /^[1-9]+([.]{1}[0-9]+){0,1}$/;
				
				div.focus(function(){
					div.parent().children(".error").css("display","none");
					numFlag = true;
				});
				div.blur(function(){
					debugger;
					if(div.val() == "" || div.val() == null){
						div.parent().children(".error").css("display","inline-block");
						numFlag = false;
					}else{
						if(!attr.test(div.val())){
							div.parent().children(".error").css("display","inline-block");
							div.parent().children(".error").html("值只能是数字或小数");
							numFlag = false;
						}else{
							div.parent().children(".error").css("display","none");
							numFlag = true;
						}
					}
				});
			}
			
			function formSubmit(){
				checkFlag = checkData();
				if(checkFlag == false){
					if(checks == false){
						$.ligerDialog.error("检验状态为已检测，检验报告单和检测结果不能为空！");
					}else{
						$.ligerDialog.error("有星号的字段不能为空！");
					}
					return;
				}
				
				if(confirm("确认提交吗?")){
					
					$("#form1").attr('action','<%=basePath%>rest/qualitySurveyReportManageAction/saveSurveyReport');
					$("#form1").submit();
				}else{
					return;
				}
			}	
			
			//数据验证
			function checkData(){
				debugger;
				var obj_sort = $("#obj_sort").val();
				var obj_ggxh = $("#obj_ggxh").val();
				var supply_agent = $("#supply_agent").val();
				var batchCode = $("#batchCode").val();
				var isSupplySurey = $("#isSupplySurey").val();
				var acount = $("#acount").val();
				var unit = $("#unit").val();
				var rfidCode = $("#rfidCode").val();
				var surveyDate = $("#surveyDate").val();
				var expiryDate = $("#expiryDate").val();
				var releaseDate = $("#releaseDate").val();
				var reportState = $("#reportState").val();
				var surveyReportCode = $("#surveyReportCode").val();
				var reportResult = $("#reportResult").val();
				
				if(obj_sort == "" || obj_ggxh=="" || supply_agent == "" 
						|| batchCode == "" ||isSupplySurey == "" || acount == "" || unit == "" 
						|| rfidCode=="" || surveyDate==""||expiryDate==""||releaseDate==""||reportState==""){
					checkFlag = false;
				}else{
					if(reportState == "已检测"){
						if(surveyReportCode == "" || reportResult == ""){
							checkFlag = false;
							checks = false;
						}else{
							checks = true;
							checkFlag = true;
						}
					}else{
						checkFlag = true;
					}
					
				}
				return checkFlag;
				
			}
			
			//智能搜索供应商
			function intelSartch(){
				//var datas = ["first", "second", "third"];
				$("#supply_agent").keyup(function (evt) {
					//debugger;
					ChangeCoords(); //控制查询结果div坐标
					var k = window.event ? evt.keyCode : evt.which;
					var info = $("#supply_agent").val();
					if (info != "" && k != 38 && k != 40 && k != 13) {
						
						$.ajax({
							url:'<%=basePath%>rest/storeMaterialBasicInfoManageAction/getAllDelivery',
							type:'POST',
							dataType:'json',
							data:'info='+info,
							success:function(datas){
								debugger;
								if (datas != "" && datas != null) {
									layer = "";
				                    layer = "<ul id='aa'>";
				                    for (var i = 0; i < datas.length; i++) {
				                        layer += "<li class='line'>" + datas[i] + "</li>";
				                    }
				                    layer += "</ul>";
				                    //将结果添加到div中    
				                    $("#searchresult").empty();
				                    $("#searchresult").append(layer);
				                    //$(".line:first").addClass("hover");
				                    $("#searchresult").css("display", "inline-block");
				                    
				                    //鼠标移动事件
				                    $(".line").hover(function () {
				                        $(".line").removeClass("hover");
				                        $(this).addClass("hover");
				                    }, function () {
				                        $(this).removeClass("hover");
				                        //$("#searchresult").css("display", "none");
				                    });
				                    //鼠标点击事件
				                    $(".line").click(function () {
				                        $("#supply_agent").val($(this).text());
				                        $("#searchresult").css("display", "none");
				                    });
								
								} else {
				                        $("#searchresult").empty();
				                        $("#searchresult").css("display", "none");
				                }
							},
							error:function(){
								$.ligerDialog.error("未知错误！");
							},
							
						});
						
						
						
					
					} else if (k == 38) {//上箭头
			            $('#aa tr.hover').prev().addClass("hover");
			            $('#aa tr.hover').next().removeClass("hover");
			            //$('#txt_search').val($('#aa tr.hover').text());
			        } else if (k == 40) {//下箭头
			            $('#aa tr.hover').next().addClass("hover");
			            $('#aa tr.hover').prev().removeClass("hover");
			            //$('#txt_search').val($('#aa tr.hover').text());
			        }
			        else if (k == 13) {//回车
			            $('#txt_search').val($('#aa tr.hover').text());
			            $("#searchresult").empty();
			            $("#searchresult").css("display", "none");
			        }
			        else {
			            $("#searchresult").empty();
			            $("#searchresult").css("display", "none");
			        }
			    });
				
			}
			//设置查询结果div坐标
			function ChangeCoords() {
			 //    var left = $("#txt_search")[0].offsetLeft; //获取距离最左端的距离，像素，整型
			    //    var top = $("#txt_search")[0].offsetTop + 26; //获取距离最顶端的距离，像素，整型（20为搜索输入框的高度）
			    var left = $("#supply_agent").position().left; //获取距离最左端的距离，像素，整型
			    var top = $("#supply_agent").position().top+20; ; //获取距离最顶端的距离，像素，整型（20为搜索输入框的高度）
			    $("#searchresult").css("left", left + "px"); //重新定义CSS属性
			    $("#searchresult").css("top", top + "px"); //同上
			}
			
		</script>
		
	</head>
  
  <body style="padding:10px">
  		 <div id="top">
  		 	<div class="t_ret"><a href="javascript:">返回</a></div>
  		 	<h3 class="t_text">原材料质检${info}</h3>
  		 	<div style="width:30%;float: left;">&nbsp;</div>
  		 </div>
		 
		 <form id="form1" action="" method="post"  enctype="multipart/form-data">
		 	<div style="display: none"><input name="id" value="${report.id}"></div>
		 	<table border="0" cellspacing="" cellpadding="" width="100%" id="b_add">
		 		<tr class="l-table-edit-tr">
		 			<td>
		 				<span>*物料类型:</span>
		 				<select name="objSort" id="obj_sort">
		 						<option <c:if test="${report.objSort=='胶料' }">selected</c:if>>胶料</option>	
		 						<option <c:if test="${report.objSort=='铜杆' }">selected</c:if>>铜杆</option>
		 						<option <c:if test="${report.objSort=='铝杆' }">selected</c:if>>铝杆</option>
		 						<option <c:if test="${report.objSort=='其他' }">selected</c:if>>其他</option>	
		 				</select>
		 			</td>
		 			<td >
		 				<span>*物料规格型号:</span>
		 				<input id="obj_ggxh" type="text" name="objGgxh" value="${report.objGgxh}" />
		 				<div class="error"></div>
		 			</td>
		 			<td >
		 				<span>*物料名称:</span>
		 				<input id="objName" type="text" name="objName" value="${report.objName}" />
		 				<div class="error"></div>
		 			</td>
		 			
		 		</tr>
		 		<tr>
		 			<td>
		 				<span>*供货商:</span>
		 				<input id="supply_agent" type="text" name="supplyAgent" value="${report.supplyAgent}" />
		 				<div id="searchresult" style=" display:none;" ></div>
		 				<div class="error"></div>
		 			</td>
		 			<td>
		 				<span>*批次号:</span>
		 				<input id="batchCode"type="text" name="batchCode" value="${report.batchCode}"/>
		 				<div class="error"></div>
		 			</td>
		 			<td>
		 				<span>*出厂报告:</span>
		 				<select name="isSupplySurey" id="isSupplySurey">
		 					<option value="有"<c:if test="${report.isSupplySurey=='有' }">selected</c:if>>有</option>	
		 					<option value="无"<c:if test="${report.isSupplySurey=='无' }">selected</c:if> >无</option>
		 				</select>
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				<span>*数量:</span>
		 				<input id="acount" type="text" name="acount" value="${report.acount}"/>
		 				<div class="error"></div>
		 			</td>
		 			<td>
		 				<span>*单位:</span>
		 				<select name="unit" id="unit">
		 						<option <c:if test="${report.unit=='kg' }">selected</c:if>>kg</option>	
		 						<option <c:if test="${report.unit=='m' }">selected</c:if>>m</option>
		 				</select>
		 			</td>
	 				<td>
	 					<span>*RFID卡:</span>
	 					<input type="text" name="rfidCode" id="rfidCode" value="${report.rfidCode}" />
	 					<div class="error"></div>
	 				</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				<span>*检测日期：</span>
		 				<input type="text" name="surveyDate" id="surveyDate" value="${report.surveyDate}"/>
		 				<div class="error"></div>
		 			</td>
		 			<td>
		 				<span>*有效期：</span>
		 				<input type="text" name="expiryDate" id="expiryDate"  value="${report.expiryDate}" />
		 				<div class="error"></div>
		 			</td>
		 			<td>
		 				<span>*保质期</span>
		 				<input type="text" name="releaseDate" id="releaseDate"  value="${report.releaseDate}" />
		 				<div class="error"></div>
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				<span>*检验状态:</span>
		 				<select name="reportState" id="reportState">
		 						<option <c:if test="${report.reportState=='未检测' }">selected</c:if>>未检测</option>	
		 						<option <c:if test="${report.reportState=='检测中' }">selected</c:if>>检测中</option>
		 						<option <c:if test="${report.reportState=='已检测' }">selected</c:if>>已检测</option>
		 				</select>
		 			</td>
		 			<td>
		 				<span>检验报告单号:</span>
		 				<input type="text" name="surveyReportCode" id="surveyReportCode"  value="${report.surveyReportCode}"/>
		 				<div class="error"></div>
		 			</td>
		 			<td>
		 				<span>检测结果:</span>
		 				<select name="reportResult" id="reportResult">
		 						<option <c:if test="${report.reportResult=='' }">selected</c:if>></option>	
		 						<option <c:if test="${report.reportResult=='不合格' }">selected</c:if>>不合格</option>
		 						<option <c:if test="${report.reportResult=='合格' }">selected</c:if>>合格</option>
		 				</select>
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				<span>报告单上传:</span>
		 				<input type="file" name="myfiles" style="width: 150px;" id="upFile" />
		 				<div class="error"></div>
		 			</td>
		 			
		 			<%-- <td colspan="1">
		 				<span>备注:</span>
		 				<input type="text" name="remark" id="beizhu" value="" style="width: 300px;height: 25px;" value="${report.remark}"/>
		 				<div class="error" style="left: 400px;"></div>
		 			</td> --%>
		 			<td>
		 				<span>备注:</span>
		 				<input type="text" name="remark" id="remark"  value="${report.remark}"/>
		 				<div class="error"></div>
		 			</td>
		 			<td>
		 				<span>系统生成单号:</span>
		 				<input type="text" name="surveyCode" id="surveyCode" readonly="readonly" value="${report.surveyCode}"/>
		 			</td>
		 		</tr>
		 		<%-- <tr>
		 			<td>
		 				<span>系统生成单号:</span>
		 				<input type="text" name="surveyCode" id="surveyCode" readonly="readonly" value="${report.surveyCode}"/>
		 			</td>
		 		</tr> --%>
		 	</table>
 			<div id="end">
 				<input type="button" name="" id="sub_btn" value="提交" class="btn1" onclick="formSubmit()"/>
 				<input type="reset" name="" id="sub_res" value="重置" class="btn2"/>
 					<!--<button class="btn1" onclick="formSubmit()">提交</button>
 					<button type="btn2">重置</button>-->
 					
 			</div>
		 				
		 </form>
		 	<%-- <c:if test="${report.reportUrl == null} ">
			 <div id="preview1"  style="width: 60%;margin: 0 auto;height:600px;display: none;" ><!-- class="download_img" -->
				 <img src="<%=basePath %>${report.reportUrl}" style="width: 100%;height:100%;" id="preview"/>
		 	<!--此处为长传的图片-->
			 </div>
		 </c:if>
		 <c:if test="${report.reportUrl  != null }">
		 	<div id="preview1" style="width: 60%;margin: 0 auto;height:600px;" ><!-- class="download_img" -->
				 <img src="<%=basePath %>${report.reportUrl}" style="width: 100%;height:100%;" id="preview"/>
		 	<!--此处为长传的图片-->
			 </div>
		 </c:if> --%>
		 
		 <c:choose>
		 	<c:when test="${report.reportUrl == null}">  
			 	<div id="preview1"  style="width: 60%;margin: 0 auto;height:600px;display: none;" ><!-- class="download_img" -->
					 <img src="<%=basePath %>${report.reportUrl}" style="width: 100%;height:100%;" id="preview"/>
			 	<!--此处为长传的图片-->
			 	</div>
   			</c:when>
   			<c:otherwise>
   				<div id="preview1" style="width: 60%;margin: 0 auto;height:600px;" ><!-- class="download_img" -->
				 <img src="<%=basePath %>${report.reportUrl}" style="width: 100%;height:100%;" id="preview"/>
		 	<!--此处为长传的图片-->
				 </div>
   			</c:otherwise>
		 </c:choose>
	</body>
	<style>
		
	</style>
</html>
