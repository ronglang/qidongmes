<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String proGgxh = request.getParameter("proGgxh");
	/* String decode = URLDecoder.decode(proGgxh,"UTF-8"); */
	String seqCode = request.getParameter("seqCode");
	String flag = request.getParameter("flag");
	String seqStep = request.getParameter("seqStep");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script>
<script type="text/javascript">
var list=[];
var form,grids,j,length,id	;
var i=0;
var proGgxh = "${proGgxh}";
var flag = '<%=flag%>';
var seqCode = '<%=seqCode%>';
var seqStep = '<%=seqStep%>';
var CustomersData = {
		Rows: list
	};
var url;
		$(function (){
			if(flag==0){
				grid();
				form();
			}else if(flag==1){
				url = "<%=basePath %>rest/craColorBomManageAction/getListPage?proGgxh="+encodeURIComponent(encodeURIComponent(proGgxh))
						+"&seqCode="+seqCode+"&seqStep="+seqStep;
				grid(url);
				form();
			} else if(flag==2){
				url = "<%=basePath %>rest/craColorBomManageAction/getListPage?proGgxh="+encodeURIComponent(encodeURIComponent(proGgxh))
				+"&seqCode="+seqCode+"&seqStep="+seqStep;
				grid(url);
				form();
			}
			setForm();
        });
        
		function setForm(){
			var bean = new Object();
			bean.proGgxh = proGgxh;
			bean.seqCode = seqCode;
			bean.seqStep = seqStep;
			form.setData(bean);
		}
		
        function grid(){
        	grids = $("#maingrid").ligerGrid({
        		url:url,
        		title:'颜色参数BOM参数',
                columns: [
            	{ hide: "id", name: "id",width:1},
                { display: "产品规格型号", name:"proGgxh"},
                { display: "工序编码", name: "seqCode" },
                { display: "每色重量", name: "weightPerColor",editor:{type:"text"}},
                { display: "颜色", name: "color"},
                { display: "轴数", name: "axisNum"},
                { display: "工序步骤", name: "seqStep"},
                { display: "备注", name: "remark" },
            	{ display: '操作', name: '',width:'5%',render : function(row) {
                	var html = "";
                	if(flag==0){
	                	html = '<a href="#" onclick="javascript:del('
	        					+row.__index+ ');return false;">删除</a>';
                	}else if(flag==1){
                		html = '<a href="#" onclick="javascript:edit('
        					+row.__index+ ');return false;">编辑</a>';
                	}
    				return html;		
               		}
                },
                { hide: "id", name: "id",width:1}
                ],
                height: '60%', 
                enabledEdit: true,
                data: CustomersData,
                /* toolbar: {
                    items: [
                    { text: '增加', click: addRowWithData, icon: 'add' },
                    { line: true },
                    ]
                }, */
                rownumbers: true
            });


            $("#pageloading").hide();
        }
        
        function form(){
        	form = $("#form2").ligerForm({
                inputWidth: 150, labelWidth: 150, 
                fields: [
                	{ display: "产品规格型号", name: "proGgxh", newline:true,comboboxName:'scCode', type: "text",validate: { required: true, minlength: 2 }},
                	{ display: "工序编码", name: "seqCode", newline:false,comboboxName:'cusName',type: "text" ,validate: { required: true, minlength: 2 }},
                	{ display: "轴数", name: "axisNum", newline:true,comboboxName:'cusName',type: "int" ,validate: { required: true, minlength: 1 }},
                	{ display: "工序步骤", name: "seqStep", newline:false,comboboxName:'cusName',type: "int" ,validate: { required: true, minlength: 1 }},
                	{ display: "颜色", name: "color", newline:true,comboboxName:'cusName',type: "text" ,validate: { required: true, minlength: 1 }},
                	{ display: "每色重量", name: "weightPerColor", newline:false,comboboxName:'cusName',type: "number" },
                	{ display: "备注", name: "remark", newline:true,comboboxName:'orderCode',type: "text" },
                ],
                validate: true
            });
        	
        	liger.get('seqCode').setDisabled(); //设置只读
        	liger.get('proGgxh').setDisabled(); //设置只读
        	liger.get('seqStep').setDisabled(); //设置只读
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
        
        function del(row) { 
        	list.remove(row);
        	var manager = $("#maingrid").ligerGetGridManager(); 
        	manager.deleteRow($("tr.l-grid-row",manager.gridbody)[0]); 
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
	        	//debugger;
	        	grid(CustomersData);
        	}else{
        		return;
        	}
        }
        
        function getDatas(){
        	return grids.getData();
        } 
        
        
        Array.prototype.remove=function(dx){ 
            if(isNaN(dx)||dx>this.length){return false;} 
            for(var i=0,n=0;i<this.length;i++) 
            { 
              if(this[i]!=this[dx]) 
              { 
                this[n++]=this[i];
              } 
            } 
            this.length-=1 ;
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
