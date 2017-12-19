<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>craftRouteList</title>
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />

<script src="<%=basePath%>core/js/jquery-1.7.2.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>

<script>
			var basePath  = '<%=basePath%>';
	var routeName = "craftRouteManage";
	
	var url;
	var state = [
                 {id:'1',text:'ls' },
                 {id:'2',text:'jx'}
                ];
        $(function (){
        	url = basePath+'rest/craRouteManageAction/getListPage';
            grid(url);
            form();
        });
        
        function grid(url){
        	$("#maingrid4").ligerGrid({
        		url:url,
        		title:'工艺路线',
                columns: [
                { display: '产品规格型号', name: 'proGgxh'},
                { display: '工序名称', name: 'seqName'},
                { display: '工序步骤', name: 'seqStep' },
                { hide: 'id', name: 'id',width:1 },
                { display: '操作', name: '',width:'5%',render : function(row) {
                	var html = "";
    				html = '<a href="javascript void(0);" onclick="javascript:detial('
    				+row.id+ ');return false;">详情</a>';
    				return html;		
               		}
                }
                ], 
                height: '99%', 
                toolbar: {
                        items: [
                        { text: '增加BOM', click: addContract, icon: 'add' },
                        { line: true },
                        { text: '修改BOM', click: updata, icon: 'modify' },
                        { line: true },
                        { text: '删除BOM', click: del, icon:'delete' },
                        { line: true },
                        ]
                    },
                rownumbers: true,
                groupColumnName: 'proGgxh', 
                pageSize:20,
                groupRender: function (proGgxh,groupdata){
                    return '产品规格型号 ' + proGgxh + '(参数个数=' + groupdata.length + ')';
                }
            });
            $("#pageloading").hide();
        }
        
        function del(){
        	$.ligerDialog.confirm('提示内容', function (yes) { 
        		if(yes){
        			var manager = $("#maingrid4").ligerGetGridManager();
                	var li = manager.getSelectedRow();
                	var id = li.id;
                	var url = basePath+'rest/craRouteManageAction/clearBean';
                	$.post(url,{id:id},function(data){
                		debugger;
                		if(data.success){
                			$.ligerDialog.success(data.msg);
                			location.reload();
                		}else{
                			$.ligerDialog.error(data.msg);
                		}
                		
                	},"json");
        		}else{
        			return;
        		}
        	});
        }
        
        function updata(){
        	var manager = $("#maingrid4").ligerGetGridManager();
        	var li = manager.getSelectedRow();
        	var id = li.id;
        	var url = basePath+'rest/craRouteManageAction/detail';
        	$.post(url,{id:id},function(data){
        		if(data.success){
        			var bean = data.data;
        			var seqName = bean.seqName;
        			var proGgxh = bean.proGgxh;
        			//打开详情二级页面
        			var width = document.body.clientWidth * 0.9;
        			var heigth = document.body.clientHeight * 0.95;
        			$.ligerDialog.open({

        		        height: heigth,

        		        width: width,

        		        title: "修改计划('<font style='color: red'>*</font>'号为必填)",

        		        url: "<%=basePath%>rest/craLsBomParamManageAction/toListBom?seqName="+seqName+"&proGgxh="+encodeURIComponent(encodeURIComponent(proGgxh))+"&flag=1",

        		        showMax: false,

        		        showMin: false,

        		        isResize: true,

        		        slide: false,

        		        name:'repairAgain',

        		        buttons: [
							{ text: '保存', onclick: function (item, dialog) { 
								var seqName = dialog.frame.$("[name=seqName]").val();
	    		            	var seqbean=dialog.frame.saveDatas();
	    		            	if(seqbean.length==0){
	    		            		$.ligerDialog.warn("请填写数据并提交");
	    		            		return;
	    		            	}
	    		            	var data =JSON.stringify(seqbean);
	    		            	var url = "";
	    		            	if(seqName=="拉丝"){url = "<%=basePath%>rest/craLsBomParamManageAction/saveOrUpdateBean?bean="+encodeURIComponent(encodeURIComponent(data));
	                        	}else if(seqName=="绞线"){url = "<%=basePath%>rest/craJxBomParamManageAction/saveOrUpdateBean?bean="+encodeURIComponent(encodeURIComponent(data));
	                        	}else if(seqName=="绝缘"){url = "<%=basePath%>rest/craJyBomParamManageAction/saveOrUpdateBean?bean="+encodeURIComponent(encodeURIComponent(data));
	                        	}else if(seqName=="包带"){url = "<%=basePath%>rest/craBdBomParamManageAction/saveOrUpdateBean?bean="+encodeURIComponent(encodeURIComponent(data));
	                        	}else if(seqName=="绞铁线"){url = "<%=basePath%>rest/craJtxBomParamManageAction/saveOrUpdateBean?bean="+encodeURIComponent(encodeURIComponent(data));
	                        	}else if(seqName=="印字"){url = "<%=basePath%>rest/craYzBomParamManageAction/saveOrUpdateBean?bean="+encodeURIComponent(encodeURIComponent(data));
	                        	}else if(seqName=="护套"){url = "<%=basePath%>rest/craHtBomParamManageAction/saveOrUpdateBean?bean="+encodeURIComponent(encodeURIComponent(data));
	                        	}else if(seqName=="集绞"){url = "<%=basePath%>rest/craJjBomParamManageAction/saveOrUpdateBean?bean="+encodeURIComponent(encodeURIComponent(data));
	                        	}else if(seqName=="复绕"){url = "<%=basePath%>rest/craFrBomParamManageAction/saveOrUpdateBean?bean="+encodeURIComponent(encodeURIComponent(data));
	                        	}else if(seqName=="对绞"){url = "<%=basePath%>rest/craDjBomParamManageAction/saveOrUpdateBean?bean="+encodeURIComponent(encodeURIComponent(data));
	                        	}
	    		            	$.ajax({
	    		            		url:url,
	    		            		type:'POST',
	    		            		dataType:'JSON',
	    		            		success:function(map){
	    		            			if(map.success){
	    		            				$.ligerDialog.success("保存成功");
	    		            				dialog.close();
	    		            				location.reload();
	    		            			}else{
	    		            				$.ligerDialog.error("保存失败");
	    		            			}
	    		            		},
	    		            		error:function(){
	    		            			$.ligerDialog.error("发生了未知错误");
	    		            		}
	    		            		
	    		            	});
								
						 	} },
        		            { text: '取消', onclick: function (item, dialog) { dialog.close(); } }]

        		    });
            		
        			
        		}
        		
        	},"json");
        }
        
        
        function detial(id){
        	//alert(id);
        	var url = basePath+'rest/craRouteManageAction/detail';
        	$.post(url,{id:id},function(data){
        		if(data.success){
        			var bean = data.data;
        			var seqName = bean.seqName;
        			var proGgxh = bean.proGgxh;
        			//打开详情二级页面
        			var width = document.body.clientWidth * 0.9;
        			var heigth = document.body.clientHeight * 0.95;
        			$.ligerDialog.open({

        		        height: heigth,

        		        width: width,

        		        title: "增加计划('<font style='color: red'>*</font>'号为必填)",

        		        url: "<%=basePath%>rest/craLsBomParamManageAction/toListBom?seqName="+seqName+"&proGgxh="+encodeURIComponent(encodeURIComponent(proGgxh))+"&flag=2",

        		        showMax: false,

        		        showMin: false,

        		        isResize: true,

        		        slide: false,

        		        name:'repairAgain',

        		        buttons: [
        		            { text: '取消', onclick: function (item, dialog) { dialog.close(); } }]

        		    });
            		
        			
        		}
        		
        	},"json");
        	
        }
        
        function form(){
        	
       	 //创建表单结构 
           form = $("#form2").ligerForm({
               inputWidth: 200, labelWidth: 100, space: 100,
               fields: [
               	{ display: "工序名称", name: "seqName", newline: false, type: "select",editor: {valueField:'text',url:'<%=basePath%>rest/craSeqManageAction/getSeqCode'}},
               	{ display: "产品规格型号", name: "proGgxh", newline: false, type: "text"},
               ],
               validate  : true
           });
       	
       }
        
        function search(){
        	var seqName = $("[name=seqName]").val();
        	var proGgxh = $("[name=proGgxh]").val();
        	url = basePath+"rest/craRouteManageAction/getListPage?seqName="+seqName+"&proGgxh="+encodeURIComponent(encodeURIComponent(proGgxh));
        	grid(url);
        }
        
        
        function addContract(){
			var width = document.body.clientWidth * 0.9;
			var heigth = document.body.clientHeight * 0.95;
        		$.ligerDialog.open({

    		        height: heigth,

    		        width: width,

    		        title: "增加计划('<font style='color: red'>*</font>'号为必填)",

    		        url: "<%=basePath%>rest/craLsBomParamManageAction/toListBom?flag=0",

    		        showMax: false,

    		        showMin: false,

    		        isResize: true,

    		        slide: false,

    		        name:'repairAgain',

    		        buttons: [
    		            { text: '保存', onclick: function (item, dialog) {
    		            	var seqName = dialog.frame.$("[name=seqName]").val();
    		            	var seqbean=dialog.frame.saveDatas();
    		            	if(seqbean.length==0){
    		            		$.ligerDialog.warn("请填写数据并提交");
    		            		return;
    		            	}
    		            	var data =JSON.stringify(seqbean);
    		            	var url = "";
    		            	if(seqName=="拉丝"){url = "<%=basePath%>rest/craLsBomParamManageAction/saveOrUpdateBean?bean="+encodeURIComponent(encodeURIComponent(data));
                        	}else if(seqName=="绞线"){url = "<%=basePath%>rest/craJxBomParamManageAction/saveOrUpdateBean?bean="+encodeURIComponent(encodeURIComponent(data));
                        	}else if(seqName=="绝缘"){url = "<%=basePath%>rest/craJyBomParamManageAction/saveOrUpdateBean?bean="+encodeURIComponent(encodeURIComponent(data));
                        	}else if(seqName=="包带"){url = "<%=basePath%>rest/craBdBomParamManageAction/saveOrUpdateBean?bean="+encodeURIComponent(encodeURIComponent(data));
                        	}else if(seqName=="绞铁线"){url = "<%=basePath%>rest/craJtxBomParamManageAction/saveOrUpdateBean?bean="+encodeURIComponent(encodeURIComponent(data));
                        	}else if(seqName=="印字"){url = "<%=basePath%>rest/craYzBomParamManageAction/saveOrUpdateBean?bean="+encodeURIComponent(encodeURIComponent(data));
                        	}else if(seqName=="护套"){url = "<%=basePath%>rest/craHtBomParamManageAction/saveOrUpdateBean?bean="+encodeURIComponent(encodeURIComponent(data));
                        	}else if(seqName=="集绞"){url = "<%=basePath%>rest/craJjBomParamManageAction/saveOrUpdateBean?bean="+encodeURIComponent(encodeURIComponent(data));
                        	}else if(seqName=="复绕"){url = "<%=basePath%>rest/craFrBomParamManageAction/saveOrUpdateBean?bean="+encodeURIComponent(encodeURIComponent(data));
                        	}else if(seqName=="对绞"){url = "<%=basePath%>rest/craDjBomParamManageAction/saveOrUpdateBean?bean="+encodeURIComponent(encodeURIComponent(data));
                        	}
    		            	$.ajax({
    		            		url:url,
    		            		type:'POST',
    		            		dataType:'JSON',
    		            		success:function(map){
    		            			if(map.success){
    		            				$.ligerDialog.success("保存成功");
    		            				dialog.close();
    		            				location.reload();
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
        
	
</script>
</head>

<body>
	<div class="l-loading" style="display:block" id="pageloading"></div>
	<div style="text-align: center;width: 100%;position: relative;;">
		<form id="form2" action=""></form>
		<div class="liger-button" onclick="search()"
			style="float: right;position: absolute;left: 780px;top: 2px; ">查询</div>
	</div>
	<div id="maingrid4" style="margin:0; padding:0"></div>


	<div style="display:none;">
		<!-- g data total ttt -->
	</div>

</body>
</html>

