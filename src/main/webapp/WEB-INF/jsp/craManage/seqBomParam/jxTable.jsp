<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String proGgxh = request.getParameter("proGgxh");
String flag = request.getParameter("flag");
String seqName = request.getParameter("seqName");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    	<script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
    	<script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    	<script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"></script>
   	 	<script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script> 
   	 	<script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
		<script type="text/javascript">
		var list=[];
		var data=[{id:1,text:'左'},{id:1,text:'右'}];
		var form,grids,j,length,id	;
		var i=0;
		var proGgxh = '<%=proGgxh%>';
		var flag = '<%=flag%>';
		var seqName = '<%=seqName%>';
		var tempData1;
		var CustomersData = {
				Rows: list
			};
		var url;
		$(function (){
			getProduct();
            if(flag==0){
				grid();
	            form();
            }else if( flag==1){
            	url = "<%=basePath %>rest/craJxBomParamManageAction/getGridByProGgxh?proGgxh="+proGgxh;
            	grid(url);
            	form();
            }else if(flag==2){
            	url = "<%=basePath %>rest/craJxBomParamManageAction/getGridByProGgxh?proGgxh="+proGgxh;
            	grid(url);
            	form();
            	$("#fo").hide();
            }
            setForm();
        });
        
		function setForm(){
			var bean = new Object();
            if(seqName=="拉丝"){bean.seqCode = 'ls';form.setData(bean);
            }else if(seqName=="绞线"){bean.seqCode = 'jx';form.setData(bean);
            }else if(seqName=="绝缘"){bean.seqCode = 'jy';form.setData(bean);
            }else if(seqName=="包带"){bean.seqCode = 'bd';form.setData(bean);
            }else if(seqName=="绞铁线"){bean.seqCode = 'jtx';form.setData(bean);
            }else if(seqName=="印字"){bean.seqCode = 'yz';form.setData(bean);
            }else if(seqName=="护套"){bean.seqCode = 'ht';form.setData(bean);
            }else if(seqName=="集绞"){bean.seqCode = 'jj';form.setData(bean);
            }else if(seqName=="复绕"){bean.seqCode = 'fr';form.setData(bean);
            }else if(seqName=="对绞"){bean.seqCode = 'dj';form.setData(bean);}
		}
		
		function getProduct(){
			$.ajax({
				url:'<%=basePath%>rest/sellSalesOrderManageAction/selectProGgxh',
			type : 'POST',
			dataType : 'JSON',
			async : false,
			success : function(data) {
				tempData1 = data.data;
			}
		});
	}
		
        function grid(){
        	grids = $("#maingrid").ligerGrid({
        		url:url,
        		title:'绞线BOM参数',
                columns: [
               			{ display: "产品规格型号", name: "proGgxh",frozen:true,minWidth:90},
						{ display: "工序步骤", name: "seqStep",frozen:true,minWidth:60},
						{ display: "工序编码", name: "seqCode",frozen:true,minWidth:60},
						{ display: "一层绞线方向", name: "onceDirection",minWidth:90},
						{ display: "二层绞线方向", name: "twiceDirection" ,minWidth:90},
						{ display: "三层绞线方向", name: "thirdDirection",minWidth:90},
						{ display: "四层绞线方向", name: "fourthDirection" ,minWidth:90},
						{ display: "一层绞线线数", name: "onceLineNum" ,minWidth:90},
						{ display: "二层绞线线数", name: "twiceLineNum" ,minWidth:90},
						{ display: "三层绞线线数", name: "thridLineNum" ,minWidth:90},
						{ display: "四层绞线线数", name: "fourthLineNum" ,minWidth:90},
						{ display: "一层绞距", name: "onceLay" ,minWidth:60},
						{ display: "一层完成直径(mm)", name: "onceFdiameter" ,minWidth:110},
						{ display: "一层最大线径(mm)", name: "onceMaxDiameter",minWidth:110 },
						{ display: "二层绞距", name: "twiceLay" ,minWidth:60},
						{ display: "二层完成直径(mm)", name: "twiceFdiameter",minWidth:110},
						{ display: "二层最大线径(mm)", name: "twiceaxDiameter" ,minWidth:110},
						{ display: "三层绞距", name: "thirdLay" ,minWidth:60},
						{ display: "三层完成直径(mm)", name: "thirdFdiameter" ,minWidth:110},
						{ display: "三层最大线径(mm)", name: "thirdMaxDiameter" ,minWidth:110},
						{ display: "四层绞距", name: "fourthLay",minWidth:60},
						{ display: "四层完成直径(mm)", name: "fourthFdiameter" ,minWidth:110},
						{ display: "四层最大线径(mm)", name: "fourthMaxDiameter",minWidth:110},
						{ display: "一层压缩率%", name: "oncecompressionRatio" ,minWidth:110},
						{ display: "二层压缩率%", name: "twicecompressionRatio" ,minWidth:110},
						{ display: "三层压缩率%", name: "thridcompressionRatio" ,minWidth:110},
						{ display: "四层压缩率%", name: "fourthcompressionRatio" ,minWidth:110},
						{ display: "预绞距", name: "layDistance" ,minWidth:80},
						{ display: "每公里用量(kg)", name: "usePerKilometer" ,minWidth:100},
						{ display: "轴数", name: "axisNum"},
						{ display: "调试长度", name: "debugLength" ,minWidth:60},
						{ display: "OEE:标准重量(KG/m)", name: "oeeStandWeight" ,minWidth:120},
						{ display: "OEE:每轴可出货长度", name: "oeeSlps" ,minWidth:120},
						{ display: "工作单上检查项目", name: "inspectionItems",minWidth:120},
						{ display: "备注", name: "remark"},
            	
            	{ display: '操作', name: '',width:'5%',frozen:true,render : function(row) {
                	var html = "";
                	if(flag==1){
    					html = '<a href="#" onclick="javascript:edit('
    					+row.__index+ ');return false;">编辑</a>';
                		
                	}else if(flag==0){
                		html = '<a href="#" onclick="javascript:del('
        					+row.__index+ ');return false;">删除</a>';
                	}
    				return html;		
               		}
                },
            	{ hide: "id", name: "id",width:1}
                ],
                height: '30%', 
                data: CustomersData,
                rownumbers: true
            });


            $("#pageloading").hide();
        }
        
        function del(row) { 
        	list = ListRemove(list,row);
    		CustomersData.Rows = list;
    		grid(CustomersData);
        }
        
        function edit(rows){
        	j = rows;
        	$("#tj").show();
        	var manager = $("#maingrid").ligerGetGridManager();
        	list = manager.getData();
        	var li = manager.data.Rows[rows];
        	id = li.id;
        	//list = lis;
        	debugger;
        	if(i==0){
    	    	length = list.length;
    	    	i++;
        	}
        	var fo = new liger.get("form2");    
        	fo.setData(li);
        }
        
        
        function form(){
        	form = $("#form2").ligerForm({
                inputWidth: 150, labelWidth: 150, 
                fields: [
						{ display: "产品规格型号", name: "proGgxh", newline:true,textField : 'proGgxh', type: "popup",
							editor : {
	        					selectBoxWidth : 600,
	        					selectBoxHeight : 300,
	        					textField : 'proGgxh',
	        					valeuField : 'proGgxh',
	        					condition : {
	        						fields : [ {
	        							label : '产品规格型号',
	        							name : 'proGgxh',
	        							type : 'text'
	        						} ]
	        					},
	        					grid : {
	        						columns : [ 
	        						   {
	        							display : '产品规格型号',
	        							name : 'proGgxh',
	        							align : 'left',
	        							width : 300,
	        							minWidth : 33
	        						   	},
	        						],
	        						sortName:'proGgxh',
	        						usePager : false,
	        						data : tempData1
	        					},
	        				},validate: { required: true, minlength: 2 }},
						{ display: "工序步骤", name: "seqStep",  newline:false,comboboxName:'scDate',type: "int" ,validate: { required: true, minlength: 1 }},
						{ display: "工序编码", name: "seqCode", newline:false,comboboxName:'cusName',type: "text" ,validate: { required: true, minlength: 2 }},
						{ display: "一层绞线方向", name: "onceDirection", newline:true,comboboxName:'cusCode',type: "select" ,editor: {valueField:'text',data:data},validate: { required: true, minlength: 1 }},
						{ display: "二层绞线方向", name: "twiceDirection", newline:false,comboboxName:'orderCode',type: "select" ,editor: {valueField:'text',data:data} },
						{ display: "三层绞线方向", name: "thirdDirection", newline:false,comboboxName:'agentBy',type: "select",editor: {valueField:'text',data:data} },
						{ display: "四层绞线方向", name: "fourthDirection", newline:true,comboboxName:'orderCode',type: "select" ,editor: {valueField:'text',data:data}},
						{ display: "一层绞线线数", name: "onceLineNum", newline:false,comboboxName:'orderCode',type: "text" },
						{ display: "二层绞线线数", name: "twiceLineNum", newline:false,comboboxName:'orderCode',type: "text" },
						{ display: "三层绞线线数", name: "thridLineNum", newline:true,comboboxName:'orderCode',type: "text" },
						{ display: "四层绞线线数", name: "fourthLineNum", newline:false,comboboxName:'orderCode',type: "text" },
						{ display: "一层绞距", name: "onceLay", newline:false,comboboxName:'orderCode',type: "number" },
						{ display: "一层完成直径(mm)", name: "onceFdiameter", newline:true,comboboxName:'orderCode',type: "text" ,validate: { required: true, minlength: 1 }},
						{ display: "一层最大线径(mm)", name: "onceMaxDiameter", newline:false,comboboxName:'orderCode',type: "text" ,validate: { required: true, minlength: 1 }},
						{ display: "二层绞距", name: "twiceLay", newline:false,comboboxName:'orderCode',type: "number" },
						{ display: "二层完成直径(mm)", name: "twiceFdiameter", newline:true,comboboxName:'orderCode',type: "text" },
						{ display: "二层最大线径(mm)", name: "twiceMaxDiameter", newline:false,comboboxName:'orderCode',type: "text" },
						{ display: "三层绞距", name: "thirdLay", newline:false,comboboxName:'orderCode',type: "number" },
						{ display: "三层完成直径(mm)", name: "thirdFdiameter", newline:true,comboboxName:'orderCode',type: "text" },
						{ display: "三层最大线径(mm)", name: "thirdMaxDiameter", newline:false,comboboxName:'orderCode',type: "text" },
						{ display: "四层绞距", name: "fourthLay", newline:false,comboboxName:'orderCode',type: "number" },
						{ display: "四层完成直径(mm)", name: "fourthFdiameter", newline:true,comboboxName:'orderCode',type: "text" },
						{ display: "四层最大线径(mm)", name: "fourthMaxDiameter", newline:false,comboboxName:'orderCode',type: "text" },
						{ display: "预绞距", name: "layDistance", newline:false,comboboxName:'orderCode',type: "number" },
						{ display: "一层压缩率%", name: "oncecompressionRatio" , newline:true,comboboxName:'orderCode',type: "number"},
						{ display: "二层压缩率%", name: "twicecompressionRatio" , newline:false,comboboxName:'orderCode',type: "number"},
						{ display: "三层压缩率%", name: "thridcompressionRatio", newline:false,comboboxName:'orderCode',type: "number" },
						{ display: "四层压缩率%", name: "fourthcompressionRatio" , newline:true,comboboxName:'orderCode',type: "number"},
						{ display: "每公里用量(kg)", name: "usePerKilometer", newline:false,comboboxName:'orderCode',type: "number" },
						{ display: "轴数", name: "axisNum", newline:false,comboboxName:'orderCode',type: "int" },
						{ display: "调试长度", name: "debugLength", newline:true,comboboxName:'orderCode',type: "int" },
						{ display: "OEE:标准重量(KG/m)", name: "oeeStandWeight", newline:false,comboboxName:'orderCode',type: "number" },
						{ display: "OEE:每轴可出货长度", name: "oeeSlps", newline:false,comboboxName:'orderCode',type: "int" },
						{ display: "工作单上检查项目", name: "inspectionItems", newline:true,comboboxName:'orderCode',type: "text" ,width:490},
						{ display: "备注", name: "remark", newline:true,comboboxName:'orderCode',type: "text" ,width:490},
					],
                validate: true
            });
        	liger.get('seqCode').setDisabled(); //设置只读
        }
        
        function addGrid(){
        	var va = form.valid();
        	var repat = /^([0-9]{1,}[.][0-9]*)$/;
        	url='';
        	if(va){
	        	var bean = form.getData();
	        	var fmeter = bean.onceFdiameter.split("x");
	        	var mmater = bean.onceMaxDiameter.split("x");
	        	for(var i = 0;i<fmeter.length;i++){
	        		if(!repat.exec(fmeter[i])||!repat.exec(mmater[i])){
		        		$.ligerDialog.warn("请给一层直径填写真确的表达式或小数如：26.8x26.6");
		        		return;
		        	}
	        	}
	        	if(bean.twiceFdiameter!=null && bean.twiceFdiameter!=""){
	        		var fmeter = bean.twiceFdiameter.split("x");
		        	for(var i = 0;i<fmeter.length;i++){
		        		if(!repat.exec(fmeter[i])){
			        		$.ligerDialog.warn("请给二层完成直径填写真确的表达式或小数如：26.8x26.6");
			        		return;
			        	}
		        	}
	        	}
	        	if(bean.twiceMaxDiameter!=null&&bean.twiceMaxDiameter!=""){
	        		var mmater = bean.twiceMaxDiameter.split("x");
		        	for(var i = 0;i<mmater.length;i++){
		        		if(!repat.exec(mmater[i])){
			        		$.ligerDialog.warn("请给二层最大直径填写真确的表达式或小数如：26.8x26.6");
			        		return;
			        	}
		        	}
	        	}
	        	if(bean.thirdFdiameter!=null && bean.thirdFdiameter!=""){
	        		var fmeter = bean.thirdFdiameter.split("x");
		        	for(var i = 0;i<fmeter.length;i++){
		        		if(!repat.exec(fmeter[i])){
			        		$.ligerDialog.warn("请给三层完成直径填写真确的表达式或小数如：26.8x26.6");
			        		return;
			        	}
		        	}
	        	}
	        	if(bean.thirdMaxDiameter!=null&&bean.thirdMaxDiameter!=""){
	        		var mmater = bean.thirdMaxDiameter.split("x");
		        	for(var i = 0;i<mmater.length;i++){
		        		if(!repat.exec(mmater[i])){
			        		$.ligerDialog.warn("请给三层最大直径填写真确的表达式或小数如：26.8x26.6");
			        		return;
			        	}
		        	}
	        	}
	        	if(bean.fourthFdiameter!=null && bean.fourthFdiameter!=""){
	        		var fmeter = bean.fourthFdiameter.split("x");
		        	for(var i = 0;i<fmeter.length;i++){
		        		if(!repat.exec(fmeter[i])){
			        		$.ligerDialog.warn("请给四层完成直径填写真确的表达式或小数如：26.8x26.6");
			        		return;
			        	}
		        	}
	        	}
	        	if(bean.fourthMaxDiameter!=null&&bean.fourthMaxDiameter!=""){
	        		var mmater = bean.fourthMaxDiameter.split("x");
		        	for(var i = 0;i<mmater.length;i++){
		        		if(!repat.exec(mmater[i])){
			        		$.ligerDialog.warn("请给四层最大直径填写真确的表达式或小数如：26.8x26.6");
			        		return;
			        	}
		        	}
	        	}
	        	
	        	if(flag==0){
		        	list.push(bean);
	        	}else if(flag==1){
	        		bean.id = id;
	        		list[j] = bean;
	        	}
	        	CustomersData = { Rows: list };
	        	grid(CustomersData);
        	}else{
        		return;
        	}
        }
        
        
        function getDatas(){
        	return grids.getData();
        }
        
        
        function ListRemove(list,index){
      		 if(isNaN(index)||index>list.length){return false;} 
      		 for(var i=0,n=0;list<this.length;i++){ 
                   if(list[i]!=list[dx]) { 
                 	  list[n++]=list[i];
                   } 
                 } 
                 list.length-=1 ;
                 return list;
      	}
    </script>
	</head>
	<body>
		<div class="l-loading" style="display:block" id="pageloading"></div> 
    	<div id="maingrid" style="margin:0; padding:0"></div>
    	<div id="des" style="border-bottom:1px solid #B0E2FF; margin-top: 2px;">
		 带"<span style="color: red">*</span>"为必填
		</div>
		<div id="fo" style="text-align: center;width: 100%;position: relative;"  >
		<form id="form2" action="" >
		</form>
		<div class="liger-button" id = 'tj' onclick="addGrid()" style="float: right;position: absolute;right: 100px;margin-bottom: 1px; ">提交</div>
	</div>
  <div style="display:none;">
  <!-- g data total ttt -->
</div>
		
	</body>
</html>
