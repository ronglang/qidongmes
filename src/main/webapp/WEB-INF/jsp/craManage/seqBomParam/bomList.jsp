<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String seqName = request.getParameter("seqName");
String proGgxh = request.getParameter("proGgxh");
String flag = request.getParameter("flag");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    	<script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
    	<script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
   	 	<script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script> 
   	 	<script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
		<script type="text/javascript">
		var seqName = '<%=seqName%>';
		var proGgxh = '<%=proGgxh%>';
		var flag = '<%=flag%>';
		debugger;
        $(function (){
            form();
            if(seqName){
            	setSelect(seqName);
            }
        });
        
        function setSelect(seqName){
        	var bean = new Object();
        	bean.seqName = seqName;
        	form.setData(bean);
        }
        
        function form(){
       	 //创建表单结构 
           form = $("#form2").ligerForm({
               inputWidth: 200, labelWidth: 100, space: 100,
               fields: [
               	{ display: "工序 ", name: "seqName", newline: false, type: "select",editor: {valueField:'text',url:'<%=basePath%>rest/craSeqManageAction/getSeqCode',
               		onSelected:function(value){
               			var seqName = value;
               			var seqcode="";
                    	if(seqName=="拉丝"){seqcode = "<%=basePath%>rest/craLsBomParamManageAction/toListPage?proGgxh="+encodeURIComponent(encodeURIComponent(proGgxh))+"&flag="+flag+"&seqName="+seqName;
                    	}else if(seqName=="绞线"){seqcode = "<%=basePath%>rest/craJxBomParamManageAction/toListPage?proGgxh="+encodeURIComponent(encodeURIComponent(proGgxh))+"&flag="+flag+"&seqName="+seqName;
                    	}else if(seqName=="绝缘"){seqcode = "<%=basePath%>rest/craJyBomParamManageAction/toListPage?proGgxh="+encodeURIComponent(encodeURIComponent(proGgxh))+"&flag="+flag+"&seqName="+seqName;
                    	}else if(seqName=="包带"){seqcode = "<%=basePath%>rest/craBdBomParamManageAction/toListPage?proGgxh="+encodeURIComponent(encodeURIComponent(proGgxh))+"&flag="+flag+"&seqName="+seqName;
                    	}else if(seqName=="绞铁线"){seqcode = "<%=basePath%>rest/craJtxBomParamManageAction/toListPage?proGgxh="+encodeURIComponent(encodeURIComponent(proGgxh))+"&flag="+flag+"&seqName="+seqName;
                    	}else if(seqName=="印字"){seqcode = "<%=basePath%>rest/craYzBomParamManageAction/toListPage?proGgxh="+encodeURIComponent(encodeURIComponent(proGgxh))+"&flag="+flag+"&seqName="+seqName;
                    	}else if(seqName=="护套"){seqcode = "<%=basePath%>rest/craHtBomParamManageAction/toListPage?proGgxh="+encodeURIComponent(encodeURIComponent(proGgxh))+"&flag="+flag+"&seqName="+seqName;
                    	}else if(seqName=="集绞"){seqcode = "<%=basePath%>rest/craJjBomParamManageAction/toListPage?proGgxh="+encodeURIComponent(encodeURIComponent(proGgxh))+"&flag="+flag+"&seqName="+seqName;
                    	}else if(seqName=="复绕"){seqcode = "<%=basePath%>rest/craFrBomParamManageAction/toListPage?proGgxh="+encodeURIComponent(encodeURIComponent(proGgxh))+"&flag="+flag+"&seqName="+seqName;
                    	}else if(seqName=="对绞"){seqcode = "<%=basePath%>rest/craDjBomParamManageAction/toListPage?proGgxh="+encodeURIComponent(encodeURIComponent(proGgxh))+"&flag="+flag+"&seqName="+seqName;
                    	}
                   		$("#ifr").attr("src", seqcode);
               		}}},
               ],
               validate  : true
           });
       }
        
        function saveDatas(){
        	var bean = child.window.getDatas();
        	return bean;
        }
        
    </script>
	</head>
	<body>
		<div style="text-align: center;width: 100%;position: relative;;"  >
			<form id="form2" action="" >
			</form>
		</div>
		<iframe id="ifr" name="child" style="width: 99.9%;height: 550px;border: 0px;" src=""></iframe>

  <div style="display:none;">
</div>
		
	</body>
</html>
