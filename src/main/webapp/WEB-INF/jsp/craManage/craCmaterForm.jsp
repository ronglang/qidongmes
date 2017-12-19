<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
    <script type="text/javascript">
    var groupicon = "";
    var form,proggxh;   
    var unit = [
                {id:'1',text:'千克' }
               ];
    $(function ()
        {
    		form();
        });
	
    function doValid(){
    	var va = form.valid();
    	return va;
    }
    
    function form(){
    	form = $("#form2").ligerForm({
            inputWidth: 150, labelWidth: 100, space:150,
            
            fields: [
            	{ display: "单位消耗材料量", name: "mcpu",  newline:true,comboboxName:'mcpu',type: "number",validate: { required: true, minlength: 1 }},
            	{ display: "单位", name: "unit", newline:false,comboboxName:'unit',type: "select", editor: {valueField:'text',data:unit},validate: { required: true, minlength: 1 }},
            	{ display: "材料规格", name: "materggxh", newline:true,comboboxName:'materGgxh',type: "select",validate: { required: true, minlength: 1 },editor: {url:'<%=basePath%>rest/craBomProductMaterManageAction/getMaterGgxh',valueField:'text'}},
            	{ display: "产品规格型号", name: "proggxh", newline:false,comboboxName:'proggxh',type: "select",validate: { required: true, minlength: 1 },editor: {url:'<%=basePath%>rest/craBomProductMaterManageAction/getProGgxhFromCraBomProductMater',valueField:'text',
            		onSelected: function (value)
	                 {
            			proggxh = $("[name=proggxh]").val();
                    $.post("<%=basePath%>rest/craBomProductMaterManageAction/getProColor",{
                    	proGgxh:proggxh
                    },function(data,textStatus){
                    	var combo = liger.get('procolor');
                        if (!combo) return;
                        combo.set('data', data);
                     },"json");
         	 }}},
            	{ display: "产品颜色", name: "procolor", newline:true,comboboxName:'procolor',type: "select",validate: { required: true, minlength: 1 },editor: {valueField:'text'}},
            	{ display: "所属工序", name: "seqname", newline:false,comboboxName:'seqname',type: "select",validate: { required: true, minlength: 1 },editor: {url:'<%=basePath%>rest/craBomProductMaterManageAction/getSeqName',valueField:'text'}},
            ],
            validate: true
        });
    }
    
		
    </script>
</head>
<body style="overflow-x:hidden; padding:2px;">
 <div class="l-clear"></div>

   <form id="form2"></form> 
   
  <div style="display:none;">
  
</div>
</body>
</html>


