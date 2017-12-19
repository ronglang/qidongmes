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
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script type="text/javascript">
    /* { display: '退料数量' ,render:function (backAcount){
		var h = "<a href='' onclick=aa("+backAcount.backAcount+")>参数</a> ";
		//debugger;
		return h;
	}} */
    var basePath ="<%=basePath%>";
    var grid,url,obj;
	$(function() {
		url = basePath + 'rest/craBomTheoryManageAction/getTheroyGrid';
		grids(url);
		form();
	});

	function grids(url) {
		grid = $("#maingrid").ligerGrid({
			url : url,
			height : '99%',
			title : 'BOM参数',
			checkbox: true,
			columns : [  {
				display : 'id',
				name : 'id',
				hide:true,
				width:1
			},{
				display : '参数类型',
				name : 'paramtype',
			},{
				display : '所属工序',
				name : 'thirdprop',
				render:function(row){
					var name = getSeqName(row) ;
					//debugger;
					return name;
					
				}
			},{
				display : '规格型号',
				name : 'proggxh'
			},{
				display : '颜色',
				name : 'procolor'
			},
			{
				display : '参数名称',
				name : 'paramname'
			},{
				display : '参数值',
				name : 'paramvalue',
				editor : {
					type : 'text',
					onBeforeEdit: function(){
						   if(obj){
							   obj.trigger('onChanged',true);
						   }
						   
					   },
					onChanged:function save(cell){
						obj = cell.record;
						debugger;
						var id = obj.id;
						var value = obj.paramvalue;
						if(getStrLength(value)>100){
							$.ligerDialog.warn("长度过长");
							return;
						}
						$.ajax({
							url:'<%=basePath %>rest/craBomTheoryManageAction/saveTheroy?id='+id+'&value='+value,
			        		type:'POST',
			        		dataType:'JSON',
			        		success:function(map){
			        			if(map.success){
			        				grid.reload();
				        			
			        			}else{
			        				$.ligerDialog.error("数据错误");
			        			}
			        		},
			        		error:function(){
			        			$.ligerDialog.error("发生了未知错误");
			        		}
							
							
						});
					}
						
				},
				
			},

			],
			validate: true,
			enabledEdit : true,
			rownumbers : true,
		});

		$("#pageloading").hide();

	}
	
	function getSeqName(row){
		var name = "";
		$.ajax({
			url:'<%=basePath %>rest/craBomTheoryManageAction/getSeqName?thirdprop='+row.thirdprop,
    		type:'POST',
    		dataType:'JSON',
    		async:false,
    		success:function(seqName){
    			if(seqName){
    				name=seqName;
    			}
    		}
		});
		return name;
	}
	
	
	function form(){
    	
      	 //创建表单结构 
          form = $("#form2").ligerForm({
              inputWidth: 200, labelWidth: 100, space: 50,
              fields: [
              	{ display: "参数类型", name: "paramType", newline: false, type: "select",editor: {url:'<%=basePath %>rest/craBomTheoryManageAction/getParamType' ,valueField:'text'}},
              	{ display: "所属工序", name: "thirdProp", newline: false, type: "select",editor: {url:'<%=basePath %>rest/craBomTheoryManageAction/getSEQName' ,valueField:'text'}},
              	{ display: "产品规格型号", name: "proGgxh", newline: false, type: "select",editor: {url:'<%=basePath %>rest/craBomTheoryManageAction/getProGgxh' ,valueField:'text',
              		onSelected: function (value)
	                 {
					var proGgxh = $("[name=proGgxh]").val();
					if(proGgxh !=""){
	                    $.post("<%=basePath %>rest/craBomTheoryManageAction/getProColor",{
	                    	proGgxh:proGgxh
	                    },function(data,textStatus){
	                           var combo = liger.get('proColor');
	                            if (!combo) return;
	                            combo.set('data', data);
	                     },"json");
					}
             	 }}},
              	{ display: "产品颜色", name: "proColor", newline: false, type: "select"},
              ],
              validate  : true
          });
      	
      }
	
	
	function search(){
		var paramType = $("[name=paramType]").val();
		var thirdProp = $("[name=thirdProp]").val();
		var proGgxh = $("[name=proGgxh]").val();
		var proColor = $("[name=proColor]").val();
		url = basePath + 'rest/craBomTheoryManageAction/getTheroyGrid?paramType='+paramType+'&thirdProp='+thirdProp+'&proGgxh='+proGgxh+'&proColor='+proColor;
		grids(url);
	}
	
	
	function getStrLength(str) {
		 var char = str.match(/[^\x00-\xff]/ig);
		 return str.length + (char == null ? 0 : char.length);  //先把中文替换成两个字节的英文，在计算长度
	}; 
	
</script>
</head>
<body style="overflow-x:hidden; padding:2px;">
<div class="l-loading" style="display:block" id="pageloading"></div>
 <div class="l-clear"></div>
	<div style="text-align: center;width: 100%;position: relative;;"  >
		<form id="form2" action="" >
		</form>
		<div class="liger-button" onclick="search()" style="float: right;position: absolute;right: 150px;top: 3px; ">查询</div>
	</div>
    <div id="maingrid"></div>
   
  <div style="display:none;">
  
</div>
</body>
</html>


