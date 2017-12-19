<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工序列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
	
  		<style>
			body {
				margin: 0;
				padding: 0;
				font-size: 14px;
			}
			#content ul{
				list-style-type: none;
			}
			#content ul li{
				width:90%;
				margin:30px 50px;
				border:2px solid #7B7B7B;
				border-radius:20px;
			}
			#content ul li span{
				display:inline-block;
				width:100px;
				font-size:18px;
				color:#F04D7E;
				font-weight:bold;
				text-align:center;
			}
			#content ul li a{
				text-decoration: none;
				font-size: 25px;
				display:inline-block;
				margin:20px 5px;
				width:50px;
				height:50px;
				background-color:gray;
				line-height:50px;
				text-align: center;
				color: white;
				font-weight: bold;
			}
			#content ul li a.start{
				background-color:green;
			}
			#content ul li a.end{
				background-color:gray;
			}
			#content ul li a.fault{
				background-color:red;
			}
			#content ul li a:hover{
				border-radius:10px;
				filter:alpha(opacity=80);  
		      	-moz-opacity:0.8;  
		        -khtml-opacity: 0.8;  
		        opacity: 0.8;
			}
			/*
			#content ul li a{
				text-decoration: none;
				font-size: 25px;
				display:inline-block;
				width:150px;
				height:100px;
				line-height:100px;
				text-align: center;
				color: #F04D7E;
				font-weight: bold;
			}
			#content ul li a:hover{
				color: #7B7B7B;
			}
			#content ul li:hover{
				border:2px solid #F04D7E;
				background-color: #C4FFFF;
			}
			*/
		</style>
		
		<script type="text/javascript">
		var basePath = "<%=basePath%>";
			$(function(){
				//获取所有工序列表，显示在页面上
				getAllCraSeq();
			});
			
			//获取所有工序
			/*
			function getAllCraSeq(){
				
				$.ajax({
					url: basePath + "rest/mauNewDislayManageAction/getAllCraSeq",
					type: "post",
					dataType: "JSON",
					success:function(list){
						var data = eval("("+list+")");
						showCraSeq(data);
					}
					
				});
				
			}
			*/
			function getAllCraSeq(){
				
				$.ajax({
					url: basePath + "rest/mauNewDislayManageAction/getAllCraSeq",
					type: "post",
					dataType: "JSON",
					success:function(data){
						var resultMap = eval("("+data+")");
						showCraSeq(resultMap);
					}
					
				});
				
			}
			//将工序显示在页面上
			/*
			function showCraSeq(list){
				$("#ulist").html("");
				var html = "";
				for(var i=0;i<list.length;i++){
					html += "<li>";
					html += "<a href='"+basePath+"rest/mauNewDislayManageAction/newProcessListEdit?seqCode="+list[i].seqCode+"&seqName="+list[i].seqName+"'>"+list[i].seqName+"</a>";
					html += "</li>";
				}
				$("#ulist").append(html);
			}
			*/
			
			
			function showCraSeq(resultMap){
				//查询缓存中的机台
				debugger;
				//工序list
				var seqs = resultMap.seqs;
				var macMap = resultMap.macMap;
				$("#ulist").html("");
				var html = "";
				for(var i = 0;i<seqs.length;i++){
					html += "<li>";
					var macList = macMap[seqs[i].seqCode];
					for(var j=0;j<macList.length;j++){
						if(j == 0){
							html += "<span>"+macList[j].macType+"</span>";
						}
						html += "<a href='"+basePath+"rest/mauNewDislayManageAction/newProcessListEdit?macCode="+macList[j].macCode+"&seqName="+macList[j].macType+"&macName="+macList[j].macName+"' title='"+macList[j].macName+"' class='end' id='"+macList[j].macCode+"'>"+macList[j].macCode+"</a>";
					}
					html += "</li>";
				}
				$("#ulist").append(html);
				
				/* $("#ulist").html("");
				var html = "";
				for(var key in map){
					html += "<li>";
					for(var i=0;i<map[key].length;i++){
						if(i == 0){
							html += "<span>"+map[key][i].macType+"</span>";
						}
						html += "<a href='"+basePath+"rest/mauNewDislayManageAction/newProcessListEdit?macCode="+map[key][i].macCode+"&seqName="+map[key][i].macType+"&macName="+map[key][i].macName+"' title='"+map[key][i].macName+"' class='end' id='"+map[key][i].macCode+"'>"+map[key][i].macCode+"</a>";
					}
					html += "</li>";
				}
				$("#ulist").append(html); */
				
				$.ajax({
					url: basePath + "rest/mauNewDislayManageAction/getMacCodeCache",
					type: "post",
					dataType: "JSON",
					success: function(data){
						debugger;
						var datas = eval("("+data+")");
						if(datas.success){
							datas = datas.data;
							for(var macCode in datas){
								//如果缓存中存在此机台
								$("#"+macCode+"").attr('href',basePath+'rest/mauNewDislayManageAction/newProcessListEdit?macCode='+macCode);
								$("#"+macCode+"").attr("class","start");
								//html += "<a href='"+basePath+"rest/mauNewDislayManageAction/newProcessListEdit?macCode="+map[key][i].macCode+"&seqName="+map[key][i].macType+"&macName="+map[key][i].macName+"' title='"+map[key][i].macName+"' class='start'>"+map[key][i].macCode+"</a>";
							}
						}
						
						
						
					}
					
				});
				
				
			}
		</script>
  </head>
 
<body>
	<div id="content">
		<ul id="ulist">
			
		</ul>
	</div>
			
</body>
</html>