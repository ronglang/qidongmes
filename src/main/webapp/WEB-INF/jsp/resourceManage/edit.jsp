<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />

<title>Insert title here</title>
</head>
<body>

<!--    <div id="form1" class="liger-form">
        <div class="fields">
        	<input data-type="text" data-label="父节点ID" data-name="pid"  id='pid'/> 
        	<input data-type="text" data-label="父节点名称" data-name="pName"  id='pName'/> 
            <input data-type="text" data-label="资源名称" data-name="name" validate="{required:true}"  />
            <input data-type="text" data-label="URL" data-name="url" validate="{required:true}" />
            <li data-label="类型" data-type="select" data-name="type" data-textField="CountryName" data-width="200">
                <input class="editor" data-data="getResouceTypeData()" data-textField="typeNmae" data-valueField="code" validate="{required:true}"  /> 
            </li>
            <input data-label="排序值"  data-type="spinner" name="sort" validate="{required: false minlength: 5}" >
        </div>  

    </div>   -->


  <form id="form1"></form> 


<script type="text/javascript" src="<%=basePath %>core/js/jquery-1.7.2.js"></script>
<script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
<script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/jquery-validation/messages_cn.js" type="text/javascript"></script>

<script type="text/javascript">
var form=false;

$(function(){
	init();
});

function init(){

 	
	form = $("#form1").ligerForm({
        inputWidth: 170, labelWidth: 90, space: 40,
        validate: true,
        fields: [
            { display: "类别", name: "type", newline: true, type: "select", 
            	editor: {
            		data:getResouceTypeData(),
            		textField:'typeName' ,
            		valueField:'code'
            		}
            }
           
        ]
	});
	 
	 /*  { label: "日期 ", name: "AddTime", newline: true, type: "date", validate: { required: true, minlength: 5 } },
      { label: "折扣", name: "QuantityPerUnit", newline: true, type: "number", validate: { required: true, minlength: 5 } },
      { label: "单价", name: "UnitPrice", newline: true, type: "number", validate: { required: true, minlength: 5 } } */
}

function ok(){
	
	 var form = liger.get("form1");
	 console.info(form);

	 if (form.valid()) {
         alert('验证通过');
     }
     else {
         form.showInvalid();
     }
     
}



function getResouceTypeData()
{
   return [
    	{typeName: '菜单', code: '1' }
    ];
}

function setData(data){
	
	var form = liger.get("form1");
	form.setData(data);
	
	 
}


</script>
</body>
</html>