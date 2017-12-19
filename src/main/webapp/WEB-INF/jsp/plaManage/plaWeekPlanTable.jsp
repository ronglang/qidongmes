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
    var basePath = '<%=basePath %>';
        $(function (){	
        	
        	var url= basePath+"rest/plaWeekPlanManageAction/getPlaWeekPlan";
            grid(url);
            //模糊查询
            $("#btn").click(function(){
				var workDay=$("#workDay").val();
				var state=$("#condition select[name='status']").val();
				var data={};
				
				data.workDay=workDay;
				data.state=state;
				var param=JSON.stringify(data);
				
				var url="<%=basePath %>rest/plaWeekPlanManageAction/getPlaWeekPlan?param="+param;
				 grid(url);
			})
            //获取所有状态
			statusList();
			
        });
		function grid(url){
			window['g'] =
            $("#maingrid").ligerGrid({
                height: '70%',
                title:'生产日计划',
                url: url,
                checkbox: true,
                columns: [
                { display: '工作日', name: 'workDay'},
                { display: '开始工作日', name: 'workStartDate'},
                { display: '结束工作日', name: 'workEndDate'},
                { display: '生产令id', name: 'productOrderId'},
                { display: '物料规格', name: 'materType' },
                { display: '轴数', name: 'axisAmount' },
                { display: '段长', name: 'partLen' },
                { display: '拉丝总工时', name: 'drawbenchTime'},
                { display: '绞线总工时', name: 'strandTime'},
                { display: '绝缘总工时', name: 'insulationTime'},
                { display: '铠装总工时', name: 'armoredTime'},
                { display: '成缆总工时', name: 'cableTime'},
                { display: '护套总工时', name: 'sheathTime'},
                { display: '状态', name: 'state'},
                { hide:'id',name:'id',width:1}
                ],
                /* toolbar: {
                    items: [
                    { text: '增加', click: itemclick, icon: 'add' },
                    { line: true },
                    { text: '修改', click: itemclick, icon: 'modify' },
                    { line: true },
                    { text: '删除', click: itemclick, icon:'delete' }
                    ]
                }, */
                rownumbers: true,
               // autoFilter: true
            });
            $("#pageloading").hide();
		}
		
		function itemclick(item)
        {
            alert("对不起，你没有‘"+item.text+"’权限");
        }
		
		function statusList(){
			$.ajax({
				url: basePath+"rest/plaWeekPlanManageAction/getStatusList",
				type: "POST",
				dataType: "Json",
				success:function(list){
					$("#condition select[name='status']").html("");
					var html = "";
					html += "<option value=''>--请选择--</option>";
					for(var i=0;i<list.length;i++){
						html += "<option value='"+list[i]+"'>"+list[i]+"</option>";
					}
					$("#condition select[name='status']").append(html);
				}
				
			});
			
		}
		
		
		
    </script>
    <style type="text/css">
  	  #btn {
			width:80px;
			height:25px;
			border: none;
			background-color: #03A2DB;
			border-radius:5px;
			color:white;
			cursor: pointer;
			margin-left:20px;
		}
    	#condition{
    		margin-left:40px;
    		padding-top: 11px;
    	}
    	#condition label{
    	}
    	#condition select{
    		width:100px;
    		height:22px;
    	}
    </style>
</head>
<body style="overflow-x:hidden; padding:2px;">
<div class="l-loading" style="display:block" id="pageloading"></div>
 <div class="l-clear"></div>
	<div id="condition">
	<label>
		<span>工作日:</span>
		<input type="text" name="workDay" id="workDay"/>
	</label>
	<lable style="margin-left:20px;">
		<span>状态：</span>
		<select name="status">
			
		</select>
	</lable>
	
	<input type="button" id="btn" value="查询"/>
	</div>&nbsp;
    <div id="maingrid"></div>
  
  <div style="display:none;">
  
</div>
</body>
</html>


