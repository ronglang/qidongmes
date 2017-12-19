<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String craWireDisc_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>craWireDiscEdit</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "craWireDiscManage"; 
	var row_id = "";
	row_id =<%=craWireDisc_id%>;
</script>
<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
 <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
<!-- 默认引用1end -->

<!-- 默认引用 -->
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script src="<%=basePath%>app/js/craManage/craWireDiscEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->
<style type="text/css">

#craWireDiscManage{
padding-left: 40px;
padding-top: 20px;

}
</style>
<script type="text/javascript">
var formed;
$(function(){
	
	getFormSelect();
});

function getFormSelect(){
	var form =$("#craWireDiscManage");
	var url =basePath + "rest/" + routeName + "Action/getSelectOption";
	$.ajax({
		type: "GET",
		url:url,
		dataType:"json",
		success:function(json){
			var amatels=[];
			var bmatels=[];
			var cmatels=[];
			var dmatels=[];
        	var ma1=json.线盘规格型号;
        	var ma2=json.线盘质地;
        	var ma3=json.线盘状态;
        	var ma4=json.线盘使用状态;
        	for (var int = 0; int <ma1.length; int++) {
        		var matel = new Object();
        		matel.id=ma1[int].value;
        		matel.text=ma1[int].value;
        		amatels.push(matel);
        		}
        	
        	for (var int = 0; int < ma3.length; int++) {
        		var matel = new Object();
        		matel.id=ma3[int].value;
        		matel.text=ma3[int].value;
        		bmatels.push(matel);
        		}	
        	
        	for (var int = 0; int < ma4.length; int++) {
            		var matel = new Object();
            		matel.id=ma4[int].value;
            		matel.text=ma4[int].value;
            		cmatels.push(matel);
            		}
        	
        	for (var int = 0; int < ma2.length; int++) {
        		var matel = new Object();
        		matel.id=ma2[int].value;
        		matel.text=ma2[int].value;
        		dmatels.push(matel);
        		}
        	formed=form.ligerForm({
        		inputWidth: 170, labelWidth: 90, space: 40,
                fields: [
                { display: "rfid编号", name: "rfidNumber", newline: false,Rtype: "text",},
                { display: "型号", name: "wireDiscPgxh", newline: false, type: "select", comboboxName: "wireDiscPgxh", options: {data: amatels } },
                { display: "内径", name: "boreDiameter", newline: false, type: "text" },
              
                { display: "材质", name: "materialTexture", newline: false, type: "select", comboboxName: "materialTexture", options: {data: dmatels } },
                { display: "外径", name: "externalDiameter", newline: false, type: "text"},
               
                { display: "状态", name: "status", newline: false, type: "select", comboboxName: "status", options:{data: bmatels } },
                { display: "容量", name: "capacity", newline: false, type: "text" },
                { display: "使用状态", name: "useStatus", newline: false, type: "select", comboboxName: "useStatus", options: {data: cmatels } },
                ] 
     	}); 
        	 	var data = ${dataMessage};
	           	 if(data!=null){
	           		 setDatas(data);
	           	 } 
        	
        	 	
		},
	error:function(){
		
	}
		
		});
	};
	
	  function setDatas(d){
	    	formed.setData(d);
	    }
	

</script>
</head>
<body> 	
	<form id="craWireDiscManage" method="post">
	</form> 
	<input class="l-button l-button-submit" id="mysubmit" type="button" value="提交" onclick="saveFormData()" style="margin-right:55px"/>
	<input class="l-button l-button-test" type="button" onclick="resetMine()" value="重置" />
</body>
</html>

