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
            	debugger;
				grid();
	            form();
            }else if( flag==1){
            	url = "<%=basePath %>rest/craFrBomParamManageAction/getGridByProGgxh?proGgxh="+proGgxh;
            	grid(url);
            	form();
            	debugger;
            }else if(flag==2){
            	url = "<%=basePath %>rest/craFrBomParamManageAction/getGridByProGgxh?proGgxh="+proGgxh;
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
        		title:'复绕BOM参数',
                columns: [
               		    { display: "产品规格型号", name: "proGgxh",frozen:true},
						{ display: "工序步骤", name: "seqStep",frozen:true},
						{ display: "工序编码", name: "seqCode",frozen:true},
						{ display: "机台编号", name: "macCode"},
						{ display: "报表每小时平均产量(m)", name: "rhao"},
						{ display: "预计每小时产量 ", name: "estimatedOutputH"},
						{ display: "预计复绕所需时间", name: "estimatedFrHour", },
						{ display: "一层节距", name: "frLength"  },
						{ display: "复绕长度(m)  ", name: "bdLength", },
						{ display: "轴数", name: "axisNum" },
						{ display: "备注", name: "remark"},
						{ display: '操作', name: '',width:'5%',frozen:true,render : function(row) {
		                	var html = "";
		                	if(flag==1){
		    					html = '<a href="#" onclick="javascript:edit('
		    					+row.__index+ ');return false;">编辑</a>';
		                		
		                	}else if(flag==0){
		                		html = '<a href="#" onclick="javascript:del('
		        					+row.__index+ ');return false;">删除</a>';
		                	}else if(flag==2){
		                		html = '<a href="#" onclick="javascript:addColor('
		        					+row.__index+ ');return false;">查看颜色</a>';
		                	}
		    				return html;		
		               		}
		                },
		            	{ hide: "id", name: "id",width:1}
                ],
                height: '50%', 
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
						{ display: "产品规格型号", name: "proGgxh", newline:true,textField : 'proGgxh',type: "popup",
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
						{ display: "机台编号", name: "macCode", newline:true,comboboxName:'cusCode',type: "text" },
						{ display: "报表每小时平均产量(m)", name: "rhao", newline:false,comboboxName:'agentBy',type: "number" },
						{ display: "预计每小时产量 ", name: "estimatedOutputH", newline:false,comboboxName:'orderCode',type: "number" },
						{ display: "预计复绕所需时间", name: "estimatedFrHour", newline:true,comboboxName:'orderCode',type: "number" },
						{ display: "一层节距", name: "frLength", newline:false,comboboxName:'orderCode',type: "int"  },
						{ display: "复绕长度(m)  ", name: "bdLength", newline:false,comboboxName:'orderCode',type: "number" },
						{ display: "轴数", name: "axisNum", newline:true,comboboxName:'orderCode',type: "int" },
						{ display: "备注", name: "remark", newline:true,comboboxName:'orderCode',type: "text" ,width:490},
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
	        	grid(CustomersData);
        	}else{
        		return;
        	}
        }
        
        
        function getDatas(){
        	return grids.getData();
        }
        
        function addColor(rows){
        	var seqCode = "";
        	var ggxh = "";
        	var seqStep = 0;
        	if(seqName=="拉丝"){seqCode="ls";
            }else if(seqName=="绞线"){seqCode = "jx";
            }else if(seqName=="绝缘"){seqCode = "jy";
            }else if(seqName=="包带"){seqCode = "bd";
            }else if(seqName=="绞铁线"){seqCode = "jtx";
            }else if(seqName=="印字"){seqCode = "yz";
            }else if(seqName=="护套"){seqCode = "ht";
            }else if(seqName=="集绞"){seqCode = "jj";
            }else if(seqName=="复绕"){seqCode = "fr";
            }else if(seqName=="对绞"){seqCode = "dj";}
        	if(flag==2){
        		var li = grids.data.Rows[rows];
        		seqStep = li.seqStep;
        		ggxh = li.proGgxh;
        	}else{
        		 ggxh = $("[name=proGgxh]").val();
        		 seqStep = $("[name=seqStep]").val();
        	}
	        if(ggxh==null || ggxh==""){
	        	$.ligerDialog.warn("请填写规格型号后再添加颜色");
	        	return;
	        }
	        if(seqStep==0) {
	        	$.ligerDialog.warn("请填写工序步骤");
	        	return;
	        }
        	$.ligerDialog.open({

		        height: 500,

		        width: 800,

		        title: "增加颜色('<font style='color: red'>*</font>'号为必填)",

		        url: "<%=basePath%>rest/craColorBomManageAction/toTablePage?seqCode="+seqCode+"&proGgxh="+encodeURIComponent(encodeURIComponent(ggxh))+"&flag="+flag+"&seqStep="+seqStep,

		        showMax: false,

		        showMin: false,

		        isResize: true,

		        slide: false,

		        name:'repairAgain',

		        buttons: [
					{ text: '保存', onclick: function (item, dialog) { 
						var colors = dialog.frame.getDatas();
						if(colors.length==0){
							$.ligerDialog.warn("请先填写数据并提交");
							return;
						}
						var bean = JSON.stringify(colors);
						$.ajax({
		            		url:"<%=basePath%>rest/craColorBomManageAction/saveOrUpdateBeans?beans="+encodeURIComponent(encodeURIComponent(bean)),
		            		type:'POST',
		            		dataType:'JSON',
		            		success:function(map){
		            			if(map.success){
		            				$.ligerDialog.success("保存成功");
		            				dialog.close();
		            			}else{
		            				$.ligerDialog.error("保存失败");
		            			}
		            		},
		            		error:function(){
		            			$.ligerDialog.error("发生了未知错误");
		            		}
		            		
		            	});
						} 
					},
		            { text: '取消', onclick: function (item, dialog) { dialog.close(); } }]

		    });
        	
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
		<div class="liger-button" id = 'addColor' onclick="addColor()" style="float: right;position: absolute;right: 200px;margin-bottom: 50px; ">添加颜色</div>
		<div class="liger-button" id = 'tj' onclick="addGrid()" style="float: right;position: absolute;right: 100px;margin-bottom: 1px; ">提交</div>
	</div>
  <div style="display:none;">
  <!-- g data total ttt -->
</div>
		
	</body>
</html>
