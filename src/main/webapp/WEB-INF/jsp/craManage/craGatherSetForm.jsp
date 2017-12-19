<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String p_code = request.getParameter("p_code");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>

</head>

<style>
<!--
#form2{text-align:center}
-->
</style>
  <script type="text/javascript">
    var p_code  = '<%=p_code%>';
    var basePath = '<%=basePath%>';
    var ggxhdata  = new Object();;
    var id;
    var colordata = new Object();
    $(function ()
        {
  			getGgxh();
  			subform();
        });
        
        
	function getGgxh(){
	$.ajax({
		url: basePath+"rest/craCraftProductManageAction/getGatherGGXH?p_code="+p_code,
		dataType: 'json',
		data: "id="+id,
		type: "post",
		async:false, 
		success:function(data){
	
			ggxhdata = data.data;
		}
	});
	}
    function subform(){
        $("#form2").ligerForm({
            inputWidth: 150, labelWidth: 100, 
            
            fields: [
            	{ display: "规格型号", name: "ggxh", newline:true,type: "select",id:"ggxh",comboboxName: "ggxh" ,
	    			options:{
	    				data: ggxhdata,
	    				onSelected :function(id,text){
	    					selectColor(id,text);
	    					
	    					}
	    				}
	    		},
	    
            	{ display: "颜色", name: "color", newline:true,type: "select",id:"color",
            	 comboboxName:"color", options:{data:colordata} },
            	{ display: "集绞数", name: "count", newline:true,type: "text"},
       
            ],
            validate: true
        });
    }
    
    function selectColor(id,text){
    var pro_code = text;
   
	$.ajax({
		url: basePath+"rest/craCraftProductManageAction/getGatherColor?&pro_code="+text,
		dataType: 'json',
		data: "id="+id,
		type: "post",
		async:false, 
		success:function(data){
			
			colordata = data.data;
			var combo = liger.get('color');
             if (!combo) return;
            combo.set('data', colordata);
		}
	});    
    }
    
    

		
 </script>
<body style="overflow-x:hidden; padding:2px;">
 <div class="l-clear"></div>
   <div id="form2"> 
</div>
</body>
</html>


