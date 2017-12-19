<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>质检类型添加</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 	<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerForm.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerComboBox.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerCheckBoxList.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerListBox.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script type="text/javascript">
    	var id=${id};
    	var paramList = [];
    	var paramData = {	//grid的值
    			Rows: paramList
    		};
    	var typeForm; //type的form 对象
    	var paramForm; //param的form 对象
    	var typeName;
    	var typeId;
    	var testAccording; 
    	//var paramData;		//grid的值
    	var changeData; //修改或者添加的数据  list
    	var deleteData; //删除或者删除的数据 list
    	var dataIndex; //paramData的索引,用于修改过后找到data
    	
    	
    	$(function(){
    		//生产默认form
    		basicTypeForm();
    		//给默认form设值
    		getBasicType(id);
    		//生成默认grid
    		var params = {};
    		params.basicTypeId = id;
    		var param = JSON.stringify(params);
    		var gUrl = "<%=basePath %>rest/qualityBasicParamManageAction/getPageList?param="+param;
    		basicParamGrid(/* gUrl */);
    		//给默认grid赋值
    		setbasicParam(/* gUrl */);
    		//生成默认参数form
    		//basicParamPorm();
    		
    	});
    	
    	
    	//默认grid
    	function basicParamGrid(/* gUrl */){
    		window['g'] =
    	        $("#maingrid").ligerGrid({
    	            height: '60%',
    	            columns: [
    	            { display: '参数名称', name: 'paramName'},
    	            { display: '参考值', name: 'referValue'},
    	            { display: '所属检测类型', name: 'basicTypeName' },
    	            { display: '操作', name: '',render : function(row) {
    	            	var html = "";
    	            	
    						html += '<a href="#" onclick="javascript:edit('+row.__index+ ');return false;">编辑</a> || ';
    	            		//html += '<a href="#" onclick="javascript:delByIndex('+row.__index+ ');return false;">删除</a>'; //通过rowIndex去删除,删除表单,并没有删除数据库
    	            		html += '<a href="#" onclick="javascript:delById('+row.id+ ');return false;">删除</a>';  //调用后台方法 直接删除数据库
    	            	
    					return html;		
    	           		}
    	            },
    	            { hide: 'id', name: 'id',width:1},
    	            ],
    	            
    	            data: paramData,
    	            rownumbers: true,
    	        });
    	        $("#pageloading").hide();
    	}
    	//给默认grid赋值
    	function setbasicParam(){
    		var params = {};
    		params.basicTypeId = id;
    		var param = JSON.stringify(params);
    		var gUrl = "<%=basePath %>rest/qualityBasicParamManageAction/getPageList?param="+param;
    		$.post(gUrl,{},function(data){
    			paramData = data;
    			
    			basicParamGrid(paramData);
    		},"json");
    	}
    	
    	//grid 修改方法
    	
    	//基础TypeForm
   		 function basicTypeForm(){
    		 typeForm = $("#form2").ligerForm({
                inputWidth: 150, labelWidth: 100, 
                fields: [
    			         { display: "质检类型", name: "type", newline:true,comboboxName:'type', type: "text"},
    		            	{ display: "质检类型名称", name: "typeName",  newline:false,comboboxName:'typeName',type: "text" },
    		            	{ display: "质检依据", name: "testAccording", newline:true,comboboxName:'testAccording',type: "text" },
    		            	{ display: "备注", name: "remark", newline:false,comboboxName:'remark',type: "text" },
                ],
               // validate: true
            }); 
           
    		liger.get('type').setDisabled(); //设置只读
    		liger.get('typeName').setDisabled(); //设置只读
    		liger.get('testAccording').setDisabled(); //设置只读
    		liger.get('remark').setDisabled(); //设置只读
    		
    	//	typeName = basicType.typeName;
    	//	testAccording = basicType.testAccording;
    	}
   	//查询基础数据
     	function getBasicType(id){
     		var url ="<%=basePath %>rest/qualityBasicTypeManageAction/getById";
     		//alert(id);
     		$.post(url,{id:id},function(data){
     			typeForm.setData(data.data);
     			typeName = data.data.typeName;
     			testAccording = data.data.testAccording;
     		},"json");
     		//通过id去查询该类型关联的所有参数明细
     	}
    	
    	
    	
    	//生成参数form
    	function basicParamPorm(){
    		paramForm = $("#form3").ligerForm({
                inputWidth: 150, labelWidth: 100, 
                fields: [
		{ display: "参数名称", name: "paramName", newline:true,comboboxName:'paramName',type: "text",validate: { required: true, minlength: 1 }},
		{ display: "参考值", name: "referValue", newline:false,comboboxName:'referValue',type: "text" ,validate: { required: true, minlength: 1 } },
		/* { display: "质检类型名称", name: "basicTypeName", newline:false,comboboxName:'basicTypeName',type: "text"  }, */
                ],
                validate: true
            }); 
           
    		/* liger.get('type').setDisabled(); //设置只读
    		liger.get('typeName').setDisabled(); //设置只读
    		liger.get('testAccording').setDisabled(); //设置只读 */
    		//liger.get('basicTypeName').setDisabled(); //设置只读
    	}
    	//修改,将grid数据向form下面提
    	function edit(index){
    		debugger;
    		dataIndex = index;
        	$("#tj").show();
        	var manager = $("#maingrid").ligerGetGridManager();
        	paramList = manager.getData();
        	var row = manager.rows[index];
        	//grid参数全局变量
        	//paramData = gridData;
        	//参数向下提
        	//paramForm.setData(row);
        	
        	$("#paramName").val(row.paramName) ;
        	$("#referValue").val(row.referValue);
    	}
    	
    	//修改确认 修改保存
    	function update(){
    		debugger;
    		var data=new Object();
    		data.paramName = $("#paramName").val();
    		if($("#paramName").val() == "" || $("#paramName").val() ==null){
    			$.ligerDialog.confirm("参数名不能为空");
    			return;
    		}
    		data.referValue = $("#referValue").val();
    	//	data.basicTypeName = $("[name=basicTypeName]").val();
    		data.basicTypeName = typeName;
    		var reData = paramData.Rows[dataIndex] ;
    		data.id = reData.id;
    		data.basicTypeId = id;//全局变量 
    		//paramData[dataIndex] = data;
    		
    		//数据库修改方法
    		var bean  = JSON.stringify(data);
    		var url ="<%=basePath %>rest/qualityBasicParamManageAction/saveBean";
    		$.post(url,{bean:bean},function(data){
    			if (data.success) {
    				$.ligerDialog.success(data.msg);
    				setbasicParam();
				} else {
					$.ligerDialog.error(data.msg);
				}
    		},"json");
    		
    		//ligeruigrid的修改方法
    		/* paramList[dataIndex] =data;
    		paramData = { Rows: paramList };
    		basicParamGrid(paramData); */
    		//让form为空
    	}
    	
    	//新增操作
    	function add(){
    		var data=new Object();
    		if($("#paramName").val() == "" || $("#paramName").val() ==null){
    			$.ligerDialog.confirm("参数名不能为空");
    			return;
    		}
    		data.paramName = $("#paramName").val();
    		data.referValue = $("#referValue").val();
    		data.testAccording = testAccording;
    		data.basicTypeName = typeName;
    		data.basicTypeId = id;
    		
    		var bean  = JSON.stringify(data);
    		var url ="<%=basePath %>rest/qualityBasicParamManageAction/saveBean";
    		$.post(url,{bean:bean},function(data){
    			if (data.success) {
    				$.ligerDialog.success(data.msg);
    				setbasicParam();
				} else {
					$.ligerDialog.error(data.msg);
				}
    		},"json");
    		
    		
    		//ligeruigrid的添加方法
    		/* var manager = $("#maingrid").ligerGetGridManager();
        	paramList = manager.getData();
    		var length = paramList.length;
    		paramList[length] =data ;
    		paramData = { Rows: paramList };
    		basicParamGrid(paramData); */
    		
    	}
    	//删除操作
    	function delById(basicPatamId){
    		$.ligerDialog.confirm("确认删除",function(flag){
    			if(flag){
    				var url = "<%=basePath %>rest/qualityBasicParamManageAction/clearBean";
    				$.post(url,{id:basicPatamId},function(data){
    					if(data.success){
    						$.ligerDialog.success(data.msg);
    					} else{
    						$.ligerDialog.error(data.msg);
    					}
    				},"json");
    			}
    		});
    	}
    	
    	//查重
    	function checkName(){
    		var paramName = $("#paramName").val();
    		//查重操作
    		var url ="<%=basePath %>rest/qualityBasicParamManageAction/checkName";
    		$.post(url,{basicTypeId:id,paramName:paramName},function(data){
    			if (data.success) {
					$("#error").empty();
					$("#error").append("可以使用");
					$("#xz").show();
					$("#tj").show();
				} else{
					$("#error").empty();
					$("#error").append(data.msg);
					$("#xz").hide();
					$("#tj").hide();
				}
    		},"json");
    	}
    	
    </script>
    <style type="text/css">
    	#all{
    		width:100%;
    		margin: 10px auto;
    	}
    	#all .one{
    		height:40px;
    		line-height: 40px;
    		width:400px;
    		margin: 0 auto;
    		text-align: left;
    	}
    	#all input{
    		height:25px;
    		width:150px;
    	}
    	#all .one .ospan{
    		display: inline-block;
    		width:80px;
    		text-align: right;
    	}
    	#error{
    		color:red;
    	}
    	#all .two{
    		height:40px;
    		line-height: 40px;
    		width:400px;
    		margin: 0 auto;
    		text-align: left;
    	}
    	#all .two span{
    		display: inline-block;
    		width:80px;
    		text-align: right;
    	}
    	#xz{
    		cursor: pointer;
    		margin-right:20px;
    	}
    	#tj{
    		cursor: pointer;
    		margin-right:20px;
    	}
    </style>
  </head>
  
  
  <body style="overflow-x:hidden; padding:2px;">
 <div class="l-clear"></div>

   <form id="form2"></form> 
   <div id="maingrid" ></div>
   <div id="fo" style="text-align: center;width: 100%;position: relative;"  >
		<!-- <form id="form3" action="" >
		</form> -->
		<!-- <div class="liger-button" id = 'tj' onclick="update()" style="float: left;position: absolute;right: 10px;top: 35px; ">修改保存</div>
		<div class="liger-button" id = 'xz' onclick="add()" style="float: right;position: absolute;right: 10px;top: 35px; ">新增保存</div> -->
		<div id="all">
			<div class="one">
				<span class="ospan">参数名称：</span>
				<input id="paramName" onblur="checkName()">
				<span id="error"></span>
			</div>
			<div class="two">
				<span>参考值：</span>
				<input id="referValue" type="text">
			</div>
			
		</div>
		<button  id="xz" onclick="add()" value="新增保存">新增保存</button>
		<button  id="tj" onclick="update()" value="提交保存">修改保存</button>
		<input  id="cz" type="reset" class="l-button l-button-reset" >
		
	</div>
  <div style="display:none;">
  
</div>
</body>
</html>
