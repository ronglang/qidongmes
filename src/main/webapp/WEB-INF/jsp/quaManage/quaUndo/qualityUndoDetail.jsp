<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String workcode = request.getAttribute("wscode").toString();
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
	<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
	<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		
		<script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>

<script src="<%=basePath %>core/js/echarts-all-3.js"></script>

</head>
<body style="padding:6px; overflow:hidden;width:1200px;height:400px;">

	<div id="main" style="width: 1200px;height:300px;"></div>
    <div id="macinfo" style="position: fixed;height:100px;bottom: 0px" ></div>
	<script>
	var basePath = '<%=basePath%>';
    var wscode = '<%=workcode%>';
	
	  // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

     function getData(){
			var jsonstr = [];
			for (var i = 0; i < arr.length; i++) {
			var json = {};
			json.name = arr[i];
			json.x = (i+1)*100;
			json.y = 300;
			jsonstr.push(json);
			}
			return jsonstr;
	};
	
	function getChats(){
	var datas = new Object();
	$.ajax({
		url: basePath+"rest/qualityUndoDetailManageAction/getMacs?&wscode="+wscode,
		dataType: 'json',
		type: "post",
		async:false, 
		success:function(data){
			debugger;
			datas = data.data;
		}
	});
	return datas;
}

	function getLinks(){
	var datas = new Object();
	$.ajax({
		url: basePath+"rest/qualityUndoDetailManageAction/getLinks?&wscode="+wscode,
		dataType: 'json',
		type: "post",
		async:false, 
		success:function(data){
			debugger;
			datas = data.data;
		}
	});
	return datas;
}	
	 function getlink(){
		var linkstr = [];
		for (var i = 0; i < arr.length-1; i++) {
			var json = {};
			json.source = arr[i];
			json.target = arr[i+1];
			linkstr.push(json);
		}
		return linkstr;
	};

	var data=[] ;
	 data = getChats();

	var link=[];
	link = getLinks();



    option = {
	    title: {
	        text: '生产过程回溯'
	    },
	    tooltip: {},
	    animationDurationUpdate: 1500,
	    animationEasingUpdate: 'quinticInOut',
	    color:'#003366',
	    series : [
	        {
	            type: 'graph',
	            layout: 'none',
	            symbolSize: 80,
	              
	            roam: true,
	            label: {
	                normal: {
	                    show: true,
	                     fontSize:15,
	                }
	            },
	            edgeSymbol: ['circle', 'arrow'],
	            edgeSymbolSize: [8, 10],      
	            edgeLabel: {
	                normal: {
	                    textStyle: {
	                        fontSize: 30 
	                    }
	                   
	                }
	            },
	            data: data,
	          
	          links: link,
	            lineStyle: {
	                normal: {
	                    opacity: 0.9,
	                    width: 2,
	                    curveness: 0,
	                    color:'#003366'
	                }
	            }
	        }
	    ]
	};

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);	
        
        //鼠标事件
		myChart.on('click', function (params) {
        console.log(params.name);
        var maccode = params.name;
        var mac = maccode.substring(0,2);
       
        var step = maccode.slice(3,-1);
       
        var url=basePath + "rest/qualityUndoDetailManageAction/queryPage?&mac_code="+mac+"&workcode="+wscode+"&seqstep="+step;
        debugger
        getDataGrid(url);     
	});	
	function getDataGrid(url){
    
	  $("#macinfo").ligerGrid({ 
		url : url,
		checkbox : false,
		width : '100%',
		columns : [ 
				{
					display : '序号',name : 'indexid',hide:true
				},
					{
					display : '工单号',
					name : 'workcode',
					width:'15%'
				},
				 {
					display : '机台号',  
					name : 'maccode',
					width:'10%'
				}, 
				{
					display : '工序',
					name : 'seqcode',
					width:'13%'
					
				},  
				 {
					display : '第几步工序',
					name : 'step',
					width:'14%'
				},
				{
					display : '完成时间',
					name : 'fdtime',
					width:'13%'
				},
				{
					display : '生产速度',
					name : 'speed',
					width:'13%'
				},
				{
					display : '材料',
					name : 'materil',
					width:'10%'
				},
				{
					display:'操作人员',
					name:'main_by',
					width:'13%'
				}
			],
		 usePager:false
		
	});
	
	

}
$("#pageloading").hide();



    	</script>

</body>




</html>

