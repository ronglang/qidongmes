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
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    <script type="text/javascript">
        $(function ()
        {
            grid();
        });
		function grid(){
			window['g'] =
            $("#maingrid").ligerGrid({
                height: '400',
                title:'生产令',
                url: '<%=basePath %>rest/plaProductOrderManageAction/getOrderTable',
                checkbox: true,
                columns: [
                { display: '生产令编号', name: 'productOrderCode', minWidth: 120 },
                { display: '产品工艺编码', name: 'proCraftCode', minWidth: 120 },
                { display: '开单时间', name: 'billDate', align: 'left', minWidth: 60 },
                { display: '要求段长', name: 'demandPartLen', minWidth: 120 },
                { display: '生产段长', name: 'productPartLen', minWidth: 140 },
                { display: '产品规格', name: 'proGgxh' },
                { display: '产品颜色', name: 'proColor' },
                { display: '轴数(分盘后)', name: 'amount' },
                { display: '要求交期', name: 'demandDate',
                	render : function(row,i,val){
                		return new Date(val).format("yyyy-MM-dd");
                	}	
                },
                { hide:'id',name:'id',width:1}
                ],
                rownumbers: true
               // autoFilter: true
            });
             

            $("#pageloading").hide();
		}
		
		function decompose(){
			var manager = $("#maingrid").ligerGetGridManager();
        	var li = manager.getSelectedRows();
        	//alert(li);
        	var dataArray = [];
        	for(var i = 0; i<li.length;i++){
        		var bean = new Object();
        		var beans = li[i];
        		bean.proGgxh = beans.proGgxh;
        		bean.productPartLen = beans.productPartLen;
        		dataArray.push(bean);
        		debugger;
        	}
        	var date = JSON.stringify(dataArray);
        	//alert(date);
        	if(date.length>2){
        		//alert("ture");
        		$.ajax({ 
        			url: '<%=basePath %>rest/plaProductOrderManageAction/getDecompose?li='+date,
               		type:"post",
               		dataType:"json",
               		success: function(map){
               			if(map!=null)
               			debugger;
               			$.ligerDialog.success(map.msg);
               		 	location.reload();
               	    },
               	    error : function() {
               	    	$.ligerDialog.error("发生了未知错误,分解失败!");
        			}
        		});
        	}else{
        		$.ligerDialog.warn("请选择需要分解的行");
        	}
        	
		}
		
		
		
		Date.prototype.format = function(fmt) { 
		     var o = { 
		        "M+" : this.getMonth()+1,                 //月份 
		        "d+" : this.getDate(),                    //日 
		        "h+" : this.getHours(),                   //小时 
		        "m+" : this.getMinutes(),                 //分 
		        "s+" : this.getSeconds(),                 //秒 
		        "q+" : Math.floor((this.getMonth()+3)/3), //季度 
		        "S"  : this.getMilliseconds()             //毫秒 
		    }; 
		    if(/(y+)/.test(fmt)) {
		            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
		    }
		     for(var k in o) {
		        if(new RegExp("("+ k +")").test(fmt)){
		             fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
		         }
		     }
		    return fmt; 
		}   
	
    </script>
</head>
<body style="overflow-x:hidden; padding:2px;">
<div class="l-loading" style="display:block" id="pageloading"></div>
 <div class="l-clear"></div>

    <div id="maingrid"></div>
   
  <div style="display:none;">
  
</div>
</body>
</html>


