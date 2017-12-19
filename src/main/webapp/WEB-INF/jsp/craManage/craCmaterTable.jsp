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
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
<script type="text/javascript">
    var grid;    
    $(function ()
        {
            grid();
            form();
        });
		function grid(){
			grid =
            $("#maingrid").ligerGrid({
                height: '100%',
                title:'BOM材料消耗',
                url: '<%=basePath%>rest/craBomProductMaterManageAction/getCraBomProductMater',
                columns: [
                { display: '单位消耗材料量', name: 'mcpu', minWidth: 60 ,editor:{
                	type:'number',
                	onBeforeEdit: function(){
						   if(obj){
							   obj.trigger('onChanged',true);
						   }
						   
					   },
					onChanged:function save(cell){
						obj = cell.record;
						var li = JSON.stringify(obj);
						/* alert(li);
						return; */
						var value = obj.mcpu;
						if(value.toString().length>32){
							$.ligerDialog.warn("长度过长");
							return;
						}
						$.ajax({
							url:'<%=basePath %>rest/craBomProductMaterManageAction/updateCraBomProductMater?li='
									+ encodeURIComponent(encodeURIComponent(li)),
			        		type:'POST',
			        		dataType:'JSON',
			        		success:function(map){
			        			if(map.success){
			        				grid.reload();
			        			}else{
			        				$.ligerDialog.error(map.msg);
			        			}
			        		},
			        		error:function(){
			        			$.ligerDialog.error("发生了未知错误");
			        		}
							
							
						});
					}
                }},
                { display: '单位', name: 'unit', minWidth: 120 },
                { display: '材料规格型号', name: 'materggxh', minWidth: 140 },
                { display: '产品规格型号', name: 'proggxh' },
                { display: '产品颜色', name: 'progcolor' },
                { display: '工序名称', name: 'seqname' },
                { hide:'id',name:'id',width:1}
                ],
                groupColumnName: 'proggxh', 
                groupRender: function (proggxh,groupdata)
                {
                    return '产品规格型号:' + proggxh + '(参数个数=' + groupdata.length + ')';
                },
                toolbar: {
                    items: [
                    { text: '增加', click: addRows, icon: 'add' },
                    { line: true },
                    ]
                },
                rownumbers: true,
                enabledEdit : true
            });
            $("#pageloading").hide();
		}
		
		function itemclick(item)
        {
            alert("对不起，你没有‘"+item.text+"’权限");
        }
		
		
 		
		function addRows(){
			$.ligerDialog.open({

		        height: 350,

		        width: 850,

		        title: '添加BOM材料消耗',

		        url: "<%=basePath%>rest/craBomProductMaterManageAction/toDetailPage",

		        showMax: false,

		        showMin: false,

		        isResize: true,

		        slide: false,

		        name:'repairAgain',

		        buttons: [{ text: '保存', onclick: function (item, dialog) {
		        	var mcpu = dialog.frame.$("[name=mcpu]").val();
		        	var unit = dialog.frame.$("[name=unit]").val();
		        	var materggxh = dialog.frame.$("[name=materggxh]").val();
		        	var proggxh = dialog.frame.$("[name=proggxh]").val();
		        	var seqname = dialog.frame.$("[name=seqname]").val();
		        	var flag = dialog.frame.doValid();
		        	if(!flag){
		        		$.ligerDialog.warn("请将数据填写完整");
		        		return;
		        	}
		        	var bean = new Object();
		        	bean.mcpu = mcpu;
		        	bean.unit = unit;
		        	bean.materggxh = materggxh;
		        	bean.proggxh = proggxh;
		        	bean.seqname = seqname;
		        	var li = JSON.stringify(bean);
		        	$.ajax({
		        		url:"<%=basePath%>rest/craBomProductMaterManageAction/saveCraBomProductMater?li="+li,
		        		type:'POST',
		        		dataType:'JSON',
		        		success:function(map){
		        			debugger;
		        			if(map.success){
			        			dialog.close();
			        			$.ligerDialog.success(map.msg);
		        			}else{
		        				$.ligerDialog.error(msp.msg);
		        			}
		        		},
		        		error:function(){
		        			$.ligerDialog.error("保存失败,发生了未知错误!");
		        		}
		        	});
		        	grid();
		        }

		        },
		            { text: '取消', onclick: function (item, dialog) { dialog.close(); } }]

		    });

			
		}
		
		function form(){
			form = $("#form2").ligerForm({
	            inputWidth: 200, labelWidth: 100, space: 50,
	            fields: [
	            	{ display: "规格型号", name: "proGgxh", newline: false, type: "select",editor: {
	            		url:'<%=basePath%>rest/craBomProductMaterManageAction/getProGgxhFromCraBomProductMater'
	            		,valueField:'text'}},
	            ],
	            validate  : true
	        });
			
		}
		
		function search(){
			var proGgxh = $("[name=proGgxh]").val();
			grid.set({url:"<%=basePath%>rest/craBomProductMaterManageAction/getCraBomProductMater?proGgxh="
							+ proGgxh
				});
	}
		
</script>
</head>
<body style="overflow-x:hidden; padding:2px;">
	<div class="l-loading" style="display:block" id="pageloading"></div>
	<div class="l-clear"></div>
	<div style="text-align: center;width: 100%;position: relative;;">
		<form id="form2" action=""></form>
		<div class="liger-button" onclick="search()"
			style="float: right;position: absolute;right: 150px;top: 3px; ">查询</div>
	</div>
	<div id="maingrid"></div>

	<div style="display:none;"></div>
</body>
</html>


