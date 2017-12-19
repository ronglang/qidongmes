<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>OEE管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/css/public.css" rel="stylesheet" type="text/css" />
    
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/json2.js" type="text/javascript"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    <script src="<%=basePath %>core/js/cssBase.js"></script>
    <script type="text/javascript">
    	var basePath = "<%=basePath%>";
		var urlPath = basePath.substring(7, basePath.length);
		var grid;
		$(function(){
			$("#t_select .setMonth").ligerDateEditor({format: "yyyyMM",labelWidth: 100});
			$("#add .setMonth").ligerDateEditor({format: "yyyyMM",labelWidth: 100});
			initData();
			//查询时获取所有工序
			getAllCraSeqInfo();
			//查询时获取所有机台编码
			getAllMacCodeInfo();
			
			//点击查询
			$("#btnSartch").click(function(){
				clickSartch();
			});
			//显示所有
			$("#btnReset").click(function(){
				initData();
			});
			
			//获取所有工序
			getAllCraSeq();
			//改变工序显示机台
			showMacCode();
			
			
			
		});
		
		function clickSartch(){
			var seqName = $("#t_select select[name='seqName']").val();
			var macCode = $("#t_select select[name='macCode']").val();
			var setMonth = $("#t_select input[name='setMonth']").val();
			var map = new Map();
			if(seqName != ""){
				map.put("seqName",seqName);
			}
			if(macCode != ""){
				map.put("macCode",macCode);
			}
			if(setMonth != ""){
				map.put("setMonth",setMonth);
			}
			var param = map.toString();
			var url = basePath+'rest/mauOEEPreinstallManageAction/getListPage?param='+param;
			ligerGrid(url);
		}
		
		function initData(){
			var url = basePath+'rest/mauOEEPreinstallManageAction/getListPage';
			ligerGrid(url);
		}
		//查询时获取所有工序
		function getAllCraSeqInfo(){
			
			$.ajax({
				url: basePath + "rest/mauOEEPreinstallManageAction/getAllSeqName",
				type: "post",
				dataType: "JSON",
				success:function(list){
					showCraSeqInfo(list);
				}
				
			});
		}
		
		function showCraSeqInfo(list){
			$("#t_select select[name='seqName']").html("");
			var html = "";
			html += "<option value=''>--请选择--</option>";
			for(var i=0;i<list.length;i++){
				html += "<option value='"+list[i]+"' title='"+list[i]+"'>"+list[i]+"</option>";
			}
			$("#t_select select[name='seqName']").append(html);
			
			//$("#searchSeqName").append(html);
		}
		//查询时获取所有机台编码
		function getAllMacCodeInfo(){
			
			$.ajax({
				url: basePath + "rest/mauOEEPreinstallManageAction/getAllMacCode",
				type: "post",
				dataType: "JSON",
				success:function(list){
					showAllMacCode(list);
				}
				
			});
		}
		function showAllMacCode(list){
			$("#t_select select[name='macCode']").html("");
			var html = "";
			html += "<option value=''>--请选择--</option>";
			for(var i=0;i<list.length;i++){
				html += "<option value='"+list[i]+"' title='"+list[i]+"'>"+list[i]+"</option>";
			}
			$("#t_select select[name='macCode']").append(html);
		}
		
		//显示数据列表
		function ligerGrid(url){
			
		    grid = $("#maingrid").ligerGrid({
		        height: '75%',
		        width: '100%',
		        title:'OEE列表管理',
		        url: url,
		       	checkbox: true,
		        columns: [
				{ display: '工序名称', name: 'seqName'},
				{ display: '机台编码', name: 'macCode'},
		        { display: '月份', name: 'setMonth'},
		        { display: '月份OEE', name: 'setMonthOee'},
		        { display: '月份正品率(QR)', name: 'setMonthQR'},
		        { display: '月份开机率(OR)', name: 'setMonthOR'},
		        { display: '月份速度满载率(PR)', name: 'setMonthPR'},
		        { hide:'id',name:'id',width:1}
		        ],
		        toolbar: {
		            items: [
		            { text: '增加', click: addRows, icon: 'add'},
		            { text: '修改', click: updateRows, icon: 'modify'},
		            { text: '删除', click: deleteRows, icon: 'delete'},
		            { line: true },
		            ]
		        },
		        rownumbers: true,
		        enabledEdit: true,
		    });
		    $("#pageloading").hide();
		}
		//点击添加
		function addRows(){
			//获取所有工序
			getAllCraSeq();
			$("#add select[name='macCode']").html("<option value=''>--请选择--</option>");
			
			$("#add input[name='setMonth']").val("");
			$("#add input[name='setMonthOee']").val("");
			$("#add input[name='setMonthQR']").val("");
			$("#add input[name='setMonthOR']").val("");
			$("#add input[name='setMonthPR']").val("");
			//验证数据
			checkData();
			
			$.ligerDialog.open({
		        target:$("#add"),
		        title: '增加OEE数据',
		        width: 480,
		        height: 320,
		        isResize: true,
		        modal: true,
		        buttons: [  { text: '增加', onclick: function (i, d) { saveAddRows(d); }},
		                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
		                 ]                                  
		   });
		}
		//保存添加的数据
		function saveAddRows(d){
			var seqName = $("#add select[name='seqName']").val();
			var macCode = $("#add select[name='macCode']").val();
			var setMonth = $("#add input[name='setMonth']").val();
			var setMonthOee = $("#add input[name='setMonthOee']").val();
			var setMonthQR = $("#add input[name='setMonthQR']").val();
			var setMonthOR = $("#add input[name='setMonthOR']").val();
			var setMonthPR = $("#add input[name='setMonthPR']").val();
			if(seqName==""||macCode==""||setMonth==""||setMonthOee==""||setMonthQR==""||setMonthOR==""||setMonthPR==""){
				$.ligerDialog.warn("信息不得为空！");
				return;
			}
			var map = new Map();
			map.put("seqName",seqName);
			map.put("macCode",macCode);
			map.put("setMonth",setMonth);
			map.put("setMonthOee",setMonthOee);
			map.put("setMonthQR",setMonthQR);
			map.put("setMonthOR",setMonthOR);
			map.put("setMonthPR",setMonthPR);
			var bean = map.toString();
			saveDate(bean,d);
			
		}
		//点击修改
		function updateRows(){
			if(grid.selected.length != 1){
				$.ligerDialog.warn("请选择一行数据修改！");
				return ;
			}
			var row = grid.selected[0];
			var id = row.id;
			$("#add select[name='seqName']").val(row.seqName);
			$("#add select[name='macCode']").html("<option value='"+row.macCode+"'>"+row.macCode+"</option>");
			$("#add input[name='setMonth']").val(row.setMonth);
			$("#add input[name='setMonthOee']").val(row.setMonthOee);
			$("#add input[name='setMonthQR']").val(row.setMonthQR);
			$("#add input[name='setMonthOR']").val(row.setMonthOR);
			$("#add input[name='setMonthPR']").val(row.setMonthPR);
			//验证数据
			checkData();
			
			$.ligerDialog.open({
		        target:$("#add"),
		        title: '修改OEE数据',
		        width: 480,
		        height: 320,
		        isResize: true,
		        modal: true,
		        buttons: [  { text: '修改', onclick: function (i, d) { saveUpdateRows(d,id); }},
		                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
		                 ]                                  
		   });
			
		}
		//保存修改后的数据
		function saveUpdateRows(d,id){
			var seqName = $("#add select[name='seqName']").val();
			var macCode = $("#add select[name='macCode']").val();
			var setMonth = $("#add input[name='setMonth']").val();
			var setMonthOee = $("#add input[name='setMonthOee']").val();
			var setMonthQR = $("#add input[name='setMonthQR']").val();
			var setMonthOR = $("#add input[name='setMonthOR']").val();
			var setMonthPR = $("#add input[name='setMonthPR']").val();
			if(seqName==""||macCode==""||setMonth==""||setMonthOee==""||setMonthQR==""||setMonthOR==""||setMonthPR==""){
				$.ligerDialog.warn("信息不得为空！");
				return;
			}
			var map = new Map();
			map.put("id",id);
			map.put("seqName",seqName);
			map.put("macCode",macCode);
			map.put("setMonth",setMonth);
			map.put("setMonthOee",setMonthOee);
			map.put("setMonthQR",setMonthQR);
			map.put("setMonthOR",setMonthOR);
			map.put("setMonthPR",setMonthPR);
			var bean = map.toString();
			saveDate(bean,d);
		}
		//提交添加和修改的数据
		function saveDate(bean,d){
			$.ajax({
				url:basePath + "rest/mauOEEPreinstallManageAction/saveBean?bean="+bean,
				type:"POST",
				dataType:"JSON",
				success:function(map){
					if(map.success == true){
						$.ligerDialog.success(map.msg);
						d.hide();
						initData();
					}else{
						$.ligerDialog.error(map.msg);
					}
					
				},
				error:function(){
					$.ligerDialog.error("服务器异常！");
				}
			});
		}
		//验证数据信息
		function checkData(){
			$("#add input[name='setMonthOee']").blur(function(){
				var setMonthOee = $("#add input[name='setMonthOee']").val();
				if(parseInt(setMonthOee) != 0){
					$.ligerDialog.warn("值错误！");
					return;
				}
			});
			$("#add input[name='setMonthQR']").blur(function(){
				var setMonthQR = $("#add input[name='setMonthQR']").val();
				if(parseInt(setMonthQR) != 0){
					$.ligerDialog.warn("值错误！");
					return;
				}
			});
			$("#add input[name='setMonthOR']").blur(function(){
				var setMonthOR = $("#add input[name='setMonthOR']").val();
				if(parseInt(setMonthOR) != 0){
					$.ligerDialog.warn("值错误！");
					return;
				}
			});
			$("#add input[name='setMonthPR']").blur(function(){
				var setMonthPR = $("#add input[name='setMonthPR']").val();
				if(parseInt(setMonthPR) != 0){
					$.ligerDialog.warn("值错误！");
					return;
				}
			});
		}
		//点击删除
		function deleteRows(){
			if(grid.selected.length != 1){
				$.ligerDialog.warn("请选择一行数据删除！");
				return ;
			}
			var row = grid.selected[0];
			var id = row.id;
			$.ajax({
				url:basePath + "rest/mauOEEPreinstallManageAction/clearBean?id="+id,
				type:"POST",
				dataType:"JSON",
				success:function(map){
					if(map.success == true){
						$.ligerDialog.success(map.msg);
						initData();
					}else{
						$.ligerDialog.error(map.msg);
					}
					
				},
				error:function(){
					$.ligerDialog.error("服务器异常！");
				}
			});
			
		}
		
		
		//获取所有工序
		function getAllCraSeq(){
			
			$.ajax({
				url: basePath + "rest/mauNewDislayManageAction/getAllCraSeq",
				type: "post",
				dataType: "JSON",
				success:function(list){
					showCraSeq(list);
				}
				
			});
			
		}
		
		//将工序显示在页面上
		function showCraSeq(list){
			$("#add select[name='seqName']").html("");
			var html = "";
			html += "<option value=''>--请选择--</option>";
			for(var i=0;i<list.length;i++){
				html += "<option value='"+list[i].seqName+"' title='"+list[i].seqCode+"'>"+list[i].seqName+"</option>";
			}
			$("#add select[name='seqName']").append(html);
		}
		//改变工序，显示此工序下所有机台
		function showMacCode(){
			$("#add select[name='seqName']").change(function(){
				//var seqName = $(this).val();
				var seqCode = $(this).find("option:selected").attr("title");
				$.ajax({
					url:basePath + "rest/mauNewDislayManageAction/getSeqMacInfo?seqCode="+seqCode,
					type: "post",
					dataType: "JSON",
					success: function(list){
						showAllMachine(list);
					}
				});
			});
		}
		//显示此工序下的所有机台
		function showAllMachine(list){
			$("#add select[name='macCode']").html("");
			var html = "";
			html += "<option>---请选择---</option>";
			for(var i=0;i<list.length;i++){
				html += "<option value='"+list[i].macCode+"' title='"+list[i].macName+"'>"+list[i].macCode+"</option>";
			}
			$("#add select[name='macCode']").append(html);
			
		}
		
    </script>
	<style type="text/css">
		#t_select{
			height:60px;
			line-height:60px;
			width:95%;
			margin:0 auto;
		}
		#t_select span{
			margin-left:20px;
		}
		#t_select select{
			width:120px;
			height:23px;
		}
		#btnSartch{
			margin:0 20px;
		}
		#add .sele{
			width:120px;
			height:23px;
		}
		#add ul{
			
		}
		#add ul li{
			height:33px;
			line-height: 33px;
		}
		#add ul li span{
			display:inline-block;
			height:30px;
			line-height: 30px;
			width:140px;
			text-align: right;
			font-size:12px;
		}
		#add ul li .text{
			width:140px;
			height:24px;
		}
	</style>
  </head>
  
  <body>
    
    <div id="t_select">
    	<span>工序名称：</span>
    	<select name="seqName" id="searchSeqName">
    	</select>
    	<span>机台编码：</span>
    	<select name="macCode">
    	</select>
    	<span>月份：</span>
    	<input type="text" name="setMonth" class="setMonth" />
    	<div id="btnSartch">查  询</div>
    	<div id="btnReset">显示所有</div>
    </div>
    
    <div class="l-loading" style="display:block" id="pageloading"></div>
	<div class="l-clear"></div>
	
	<div id="maingrid"></div>
	   
	<div style="display:none;"></div>
    
    <div id="add" style="display:none">
    	<ul>
    		<li>
    			<span>工序名称：</span>
    			<label>
    				<select name="seqName" class="sele">
    					
    				</select>
    			</label>
    		</li>
    		<li>
    			<span>机台编码：</span>
    			<label>
    				<select name="macCode" class="sele">
    				</select>
    			</label>
    		</li>
    		<li>
    			<span>设置月份：</span>
    			<input type="text" name="setMonth" class="setMonth" />
    		</li>
    		<li>
    			<span>月份OEE：</span>
    			<input type="text" name="setMonthOee" class="text" />
    		</li>
    		<li>
    			<span>月份正平率(QR)：</span>
    			<input type="text" name="setMonthQR" class="text" />
    		</li>
    		<li>
    			<span>月份开机率(OR)：</span>
    			<input type="text" name="setMonthOR" class="text" />
    		</li>
    		<li>
    			<span>月份速度满载率(PR)：</span>
    			<input type="text" name="setMonthPR" class="text" />
    		</li>
    	</ul>
    </div>
    
    
    
    
  </body>
</html>
