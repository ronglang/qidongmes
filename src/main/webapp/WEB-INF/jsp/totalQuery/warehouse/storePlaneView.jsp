<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title></title>
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />

<script src="<%=basePath%>core/js/jquery-1.7.2.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
</head>
<style>
.areas {
	width: 150px;
	height:50px;
	position: relative;
}

.areas span {
	font-size: x-large;
	color: #BF3EFF;
}
</style>
<script type="text/javascript">
	$(function() {
		var areas = "<div id='C01-C15' class='areas' onclick='show(this)' style='left: 150px;top:40px;'><img id='C01' class='img' src='<%=basePath %>app/images/形状-1.png' /></div>";
		areas += "<div id='C16-C29' class='areas' onclick='show(this)' style='left: 350px;top:-5px;'><img id='C16' class='img' src='<%=basePath %>app/images/形状-1.png' /></div>";
		areas += "<div id='A22-A36' class='areas' onclick='show(this)' style='left: 630px;top:-50px;'><img id='A22' class='img' src='<%=basePath %>app/images/形状-1.png' /></div>";
		areas += "<div id='A01' class='areas' onclick='show(this)' style='left: 220px;top:15px;width:50px'><img id='A1' class='img' src='<%=basePath %>app/images/形状-1.png' /></div>";
		areas += "<div id='A02-A04' class='areas' onclick='show(this)' style='left: 310px;top:-35px;width:90px'><img id='A02' class='img' src='<%=basePath %>app/images/形状-1.png' /></div>";
		areas += "<div id='A05-A21' class='areas' onclick='show(this)' style='left: 420px;top:-20px;'><img class='img' id='A05' src='<%=basePath %>app/images/形状-1.png' /></div>";
		areas += "<div id='A37-A40' class='areas' onclick='show(this)' style='left: 650px;top:-140px;'><img class='img' id='A37' src='<%=basePath %>app/images/形状-1.png' /></div>";
		areas += "<div id='C101-C812' class='areas' onclick='show(this)' style='left: 130px;top:100px;'><img class='img' id='C101' src='<%=basePath %>app/images/形状-1.png' /></div>";
		areas += "<div id='C901-C908' class='areas' onclick='show(this)' style='left: 300px;top:-25px;'><img class='img' id='C901' src='<%=basePath %>app/images/形状-1.png' /></div>";
		areas += "<div id='S101-S508' class='areas' onclick='show(this)' style='left: 300px;top:10px;'><img class='img' id='S101' src='<%=basePath %>app/images/形状-1.png' /></div>";
		areas += "<div id='S601-S613' class='areas' onclick='show(this)' style='left: 460px;top:-80px;width:60px;height:100px'><img id='S601' class='img' src='<%=basePath %>app/images/形状-1.png' /></div>";
		areas += "<div id='C40-C60' class='areas' onclick='show(this)' style='left: 660px;top:-120px;'><img id='C40' class='img' src='<%=basePath %>app/images/形状-1.png' /></div>";
		$("#main").append(areas);
		$(".img").hide();
		form();
	});

	function form(){
    	
      	 //创建表单结构 
          form = $("#form2").ligerForm({
              inputWidth: 200, labelWidth: 65, space: 100,
              fields: [
              	{ display: "规格型号", name: "materGgxh", newline: false, type: "text"}
              ],
              validate  : true
          });
      	
      }
	
	function search(){
		var materGgxh = $("[name=materGgxh]").val();
		if(materGgxh==""||materGgxh==null){
			alert("请输入搜索内容!");
			return;
		}
		$.ajax({
			url:'<%=basePath%>rest/statStoreObjVoManageAction/getAreaType?materGgxh='
							+ materGgxh,
					type : 'POST',
					dataType : 'json',
					success : function(str) {
						//alert(str);
						$(".img").hide();
						//debugger;
						for(var i = 0;i<str.length;i++){
							if(str[i]>'C01'&&str[i]<'C15'){$('#C01').show();
							}else if(str[i]>='C16'&&str[i]<='C29'){$('#C16').show();
							}else if(str[i]>='A22'&&str[i]<='A36'){$('#A22').show();
							}else if(str[i]>='A01'&&str[i]<='A01'){$('#A1').show();
							}else if(str[i]>='A02'&&str[i]<='A04'){$('#A02').show();
							}else if(str[i]>='A05'&&str[i]<='A21'){$('#A05').show();
							}else if(str[i]>='A37'&&str[i]<='A40'){$('#A37').show();
							}else if(str[i]>='C101'&&str[i]<='C812'){$('#C101').show();
							}else if(str[i]>='C901'&&str[i]<='C908'){$('#C901').show();
							}else if(str[i]>='S101'&&str[i]<='S508'){$('#S101').show();
							}else if(str[i]>='S601'&&str[i]<='S613'){$('#S601').show();
							}else if(str[i]>='C40'&&str[i]<='C60'){$('#C40').show();
							}
						}
					}
				});

	}

	function show(ids) {
		var materGgxh = $("[name=materGgxh]").val();
		id = $(ids).attr("id");
		var width = document.body.clientWidth;
		var heigth = document.body.clientHeight
        window.open('<%=basePath %>rest/statStoreObjVoManageAction/toTablePage?materGgxh='+materGgxh+'&areaType='+id, "仓库位置详情",'height='+heigth+', width='+width);

	}
</script>
<body>
	<div id="main"
		style="width:890px;height:730px ;margin: 0 auto;background-image: url(<%=basePath%>app/images/仓库平面.jpg);background-repeat:no-repeat; background-size:100% 100%;-moz-background-size:100% 100%;">
		<div
			style="text-align: center;width: 100%;position: relative;margin-left: 200px;top:1px">
			<form id="form2" action=""></form>
			<div class="liger-button" onclick="search()"
				style="float: right;position: absolute;left: 300px;top: 3px; ">查询</div>
		</div>
	</div>
</body>
</html>