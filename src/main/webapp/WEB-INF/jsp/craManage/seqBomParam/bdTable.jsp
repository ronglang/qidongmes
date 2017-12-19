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
		var tempData2;
		var CustomersData = {
				Rows: list
			};
		var url;
		$(function (){
			getProduct();
			getMateril();
            if(flag==0){
            	debugger;
				grid();
	            form();
            }else if( flag==1){
            	url = "<%=basePath %>rest/craBdBomParamManageAction/getGridByProGgxh?proGgxh="+proGgxh;
            	grid(url);
            	form();
            	debugger;
            }else if(flag==2){
            	url = "<%=basePath %>rest/craBdBomParamManageAction/getGridByProGgxh?proGgxh="+proGgxh;
            	grid(url);
            	form();
            	$("#fo").hide();
            }
            setForm();
        });
        
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
		
		function getMateril(){
			$.ajax({
				url:'<%=basePath%>rest/craCraftManageAction/getSelectMateril',
			type : 'POST',
			dataType : 'JSON',
			async : false,
			success : function(data) {
				debugger;
				tempData2 = data.data;
			}
		});
	}
		
		function setForm(){
			var bean = new Object();
            if(seqName=="拉丝"){bean.seqCode = 'ls';form.setData(bean);
            }else if(seqName=="绞线"){bean.seqCode = 'jx';form.setData(bean);
            }else if(seqName=="绝缘"){bean.seqCode = 'jy';form.setData(bean);
            }else if(seqName=="包带"){bean.seqCode = 'bd';form.setData(bean);
            }else if(seqName=="绞铁线"){bean.seqCode ='jtx';form.setData(bean);
            }else if(seqName=="印字"){bean.seqCode = 'yz';form.setData(bean);
            }else if(seqName=="护套"){bean.seqCode = 'ht';form.setData(bean);
            }else if(seqName=="集绞"){bean.seqCode = 'jj';form.setData(bean);
            }else if(seqName=="复绕"){bean.seqCode = 'fr';form.setData(bean);
            }else if(seqName=="对绞"){bean.seqCode = 'dj';form.setData(bean);}
		}
		
        function grid(){
        	grids = $("#maingrid").ligerGrid({
        		url:url,
        		title:'包带BOM参数',
                columns: [
                { display: "产品规格型号", name:"proGgxh",frozen:true,minWidth:90},
                { display: "工序步骤", name:"seqStep",frozen:true,minWidth:60},
                { display: "工序编码", name: "seqCode" ,frozen:true,minWidth:60},
                { display: "包带类型", name: "bdType",minWidth:60},
                { display: "包带材料", name: "bdMater",minWidth:60},
                { display: "包带规格类型", name: "bdMaterGgxh",minWidth:90},
                { display: "包带方向", name: "bdDirection",minWidth:60},
                { display: "一层节距", name: "oncePitch",minWidth:60},
                { display: "包带长度  ", name: "bdLength",minWidth:60},
                { display: "覆盖率", name: "coverageRate",minWidth:60},
                { display: "最小覆盖率", name: "minCoverageRate",minWidth:90},
                { display: "每千米耗用(kg)", name: "expendKm",minWidth:100},
                { display: "完成外径(mm)", name: "overallDiameter",minWidth:110},
                { display: "最大外径(mm)", name: "maxDiameter",minWidth:110},
                { display: "材料总耗用量(kg)", name: "totalMaterExpend",minWidth:120},
            	{ display: "二层绞距", name: "twiceDistance",minWidth:60},
            	{ display: "带重(煅烧云母带专用kg)", name: "bdWeight",minWidth:140},
            	{ display: "带宽(麦拉带专用mm)", name: "bdWidth",minWidth:130},
            	{ display: "包带厚度(麦拉带专用mm)", name: "bdThick",minWidth:140},
            	{ display: "层数", name: "floorNum"},
            	{ display: "调试长度 ", name: "debugLength",minWidth:60},
            	{ display: "每公里用量(kg)", name: "usePerKilometer",minWidth:100},
            	{ display: "轴数", name: "axisNum"},
            	{ display: "OEE:标准重量(KG/m)", name: "oeeStandWeight",minWidth:120},
            	{ display: "OEE:每轴可出货长度", name: "oeeSlps" ,minWidth:120},
            	{ display: "工作单上检查项目", name: "inspectionItems",minWidth:100},
            	{ display: "备注", name: "remark"},
            	
            	{ display: '操作', name: '',width:'5%',frozen:true,render : function(row) {
                	var html = "";
                	if(flag){
    					html = '<a href="#" onclick="javascript:edit('
    					+row.__index+ ');return false;">编辑</a>';
                		
                	}else{
                		html = '<a href="#" onclick="javascript:del('
        					+row.__index+ ');return false;">删除</a>';
                	}
    				return html;		
               		}
                },
            	{ hide: "id", name: "id",width:1}
                ],
                height: '40%', 
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
						{ display: "产品规格型号", name: "proGgxh",textField : 'proGgxh', newline:true,type: "popup",
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
						{ display: "工序步骤", name: "seqStep",  newline:false,type: "int" ,validate: { required: true, minlength: 1 }},
						{ display: "工序编码", name: "seqCode", newline:false,type: "text" ,validate: { required: true, minlength: 2 }},
						{ display: "包带类型", name: "bdType", newline:true,type: "text" ,validate: { required: true, minlength: 2 }},
						{ display: "包带材料", name: "mater",textField : 'mater', newline:false,type : "popup",
							editor : {
								selectBoxWidth : 600,
								selectBoxHeight : 300,
								textField : 'mater',
								valeuField : 'mater',
								condition : {
									fields : [ {
										label : '材料',
										name : 'mater',
										type : 'text'
									} ]
								},
								grid : {
									columns : [ {
										display : '产品规格型号',
										name : 'mater',
										align : 'left',
										width : 300,
										minWidth : 33
									}, ],
									sortName : 'mater',
									usePager : false,
									data : tempData2
								},
							},validate: { required: true, minlength: 2 }},
						{ display: "包带规格类型", name: "bdMaterGgxh", newline:false,type: "text" },
						{ display: "包带方向", name: "bdDirection", newline:true,type: "select" ,editor: {valueField:'text',data:data}},
						{ display: "一层节距", name: "oncePitch", newline:false,type: "int"  },
						{ display: "包带长度  ", name: "bdLength", newline:false,type: "number" },
						{ display: "覆盖率", name: "coverageRate", newline:true,type: "number" },
						{ display: "最小覆盖率", name: "minCoverageRate", newline:false,type: "number" },
						{ display: "每千米耗用(kg)", name: "expendKm", newline:false,type: "number" },
						{ display: "完成外径(mm)", name: "overallDiameter", newline:true,type: "number" },
						{ display: "最大外径(mm)", name: "maxDiameter", newline:false,type: "number" },
						{ display: "材料总耗用量(kg)", name: "totalMaterExpend", newline:false,type: "number" },
						{ display: "二层绞距", name: "twiceDistance", newline:true,type: "number"},
						{ display: "带重(煅烧云母带专用kg)", name: "bdWeight", newline:false,type: "number" },
						{ display: "带宽(麦拉带专用mm)", name: "bdWidth", newline:false,type: "number" },
						{ display: "包带厚度(麦拉带专用mm)", name: "bdThick", newline:true,type: "number" },
						{ display: "层数", name: "floorNum", newline:false,type: "int" },
						{ display: "轴数", name: "axisNum", newline:false,type: "int" },
						{ display: "每公里用量(kg)", name: "usePerKilometer", newline:true,type: "int" },
						{ display: "OEE:标准重量(KG/m)", name: "oeeStandWeight", newline:false,type: "number" },
						{ display: "OEE:每轴可出货长度", name: "oeeSlps", newline:false,type: "int" },
						{ display: "工作单上检查项目", name: "inspectionItems", newline:true,type: "text" ,width:490},
						{ display: "备注", name: "remark", newline:true,type: "text" ,width:490},
					],
                validate: true
            });
        	
        	liger.get('seqCode').setDisabled(); //设置只读
        	
        }
        
        function addGrid(){
        	var va = form.valid();
        	url='';
        	if(va){
	        	var bean = form.getData();
	        	if(flag==0){
		        	list.push(bean);
	        	}else if(flag==1){
	        		bean.id = id;
	        		list[j] = bean;
	        	}
	        	CustomersData = { Rows: list };
	        	debugger;
	        	grid(CustomersData);
        	}else{
        		return;
        	}
        }
        
        
        function getDatas(){
        	//alert(1);
        	debugger;
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
    	<div id="des" style="border-bottom:1px solid #B0E2FF; margin-top: 10px;">
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
