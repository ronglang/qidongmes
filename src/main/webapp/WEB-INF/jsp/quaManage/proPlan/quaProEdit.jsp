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
    var form;   
    var unit = [
                {id:'1',text:'m' },
                {id:'2',text:'kg'}
                ];
    var materGgxh = "";
    var proCraftCode = "";
    var pcscRelaCode = "";
    var data = "";
    $(function ()
        {
    	getGgxh();
    	//getProCarftCode();
    	//getPcscCode();
    	//debugger;
        //setForm(materGgxh,proCraftCode,pcscRelaCode);
    	//form();
        });
		
    function form(){
    	form = $("#form2").ligerForm({
            inputWidth: 150, labelWidth: 100, 
            
            fields: [
            	{ display: "检测类型 ", name: "type", newline:true,comboboxName:'createDate', type: "date",align:'left'},
            	{ display: "轴名称", name: "axisName",  newline:false,comboboxName:'mcpu',type: "number" ,align:'right'},
            	{ display: "机器编号", name: "macCode", newline:true,comboboxName:'unit',type: "select", editor: {valueField:'text',data:unit},align:'left' },
            	{ display: "工序名称", name: "seqName", newline:false,comboboxName:'materGgxh',type: "select",editor: {valueField:'text',data:materGgxh},align:'right' },
            	{ display: "产品规格型号", name: "proGgxh", newline:true,comboboxName:'proCraftCode',type: "select",editor: {valueField:'text',data:proCraftCode},align:'left' },
            	{ display: "检测状态", name: "testState", newline:true,comboboxName:'proCraftCode',type: "select",editor: {valueField:'text',data:proCraftCode},align:'left' },
            	{ display: "产品规格型号", name: "testBy", newline:true,comboboxName:'proCraftCode',type: "select",editor: {valueField:'text',data:proCraftCode},align:'left' },
            	{ display: "主表编码", name: "pcscrelacode", newline:false,comboboxName:'pcscRelaCode',type: "select",editor: {valueField:'text',data:pcscRelaCode},align:'right' },
            ],
            validate: true
        });
    }
    
    function getGgxh(){
    	$.ajax({
    		url:'<%=basePath %>rest/craBomProductMaterManageAction/getMaterGgxh',
    		type:'POST',
    		dataType:'json',
    		success:function(str){
    			//debugger;
    			materGgxh = str;
    			//form(materGgxh);
    			//return materGgxh;
    			$.ajax({
    	    		url:'<%=basePath %>rest/craBomProductMaterManageAction/getProCraftCode',
    	    		type:'POST',
    	    		dataType:'json',
    	    		success:function(str){
    	    			//debugger;
    	    			proCraftCode = str;
    	    			//return  proCraftCode;
    	    			$.ajax({
    	    	    		url:'<%=basePath %>rest/craBomProductMaterManageAction/getPcscRelaCode',
    	    	    		type:'POST',
    	    	    		dataType:'json',
    	    	    		success:function(str){
    	    	    			//debugger;
    	    	    			pcscRelaCode = str;
    	    	    			//return pcscRelaCode;
    	    	    			form(materGgxh,proCraftCode,pcscRelaCode);
    	    	    			data = ${data};
    	    	    			if(data!=null && data!="false"){
	    	    	    			setDatas(data[0]);
    	    	    			}
    	    	    		}
    	    	    	});
    	    			
    	    		}
    	    	});
    			
    		}
    			
    	});
    }
    
    function getProCarftCode(){
    	$.ajax({
    		url:'<%=basePath %>rest/craBomProductMaterManageAction/getProCraftCode',
    		type:'POST',
    		dataType:'json',
    		success:function(str){
    			//debugger;
    			proCraftCode = str;
    			//return  proCraftCode;
    		}
    	});
    	
    }
    
    function getPcscCode(){
    	
    	$.ajax({
    		url:'<%=basePath %>rest/craBomProductMaterManageAction/getPcscRelaCode',
    		type:'POST',
    		dataType:'json',
    		success:function(str){
    			//debugger;
    			pcscRelaCode = str;
    			//return pcscRelaCode;
    		}
    	});
    }
    
    function setForm(materGgxh,proCraftCode,pcscRelaCode){
    	form(materGgxh,proCraftCode,pcscRelaCode);
    }
    
    function setDatas(d){
    	debugger;
    	form.setData(d);
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


