<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String tel = request.getParameter("tel");
%>
<!DOCTYPE HTML>
<html>
<head>

<meta HTTP-EQUIV="pragma" CONTENT="no-cache"> 
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
<meta HTTP-EQUIV="expires" CONTENT="0">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="X-UA-Compatible" content="IE=10" />
<title>sysContactsCallTel</title>
<!-- 默认引用1 -->
<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css"
	rel="stylesheet" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/plugin/swfupload/css/default.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />

<style type="text/css">
.callnum {
	background: url("");
}

.phonenumFillin {
	display: inline-block;
	float: left;
	clear: none;
}

.phonenumBox {
	margin-left: 30px;
	display: inline-block;
	float: left;
	width: 159px;
	clear: none;
}

.l-form ul {
	clear: none;
}

.phonenumFillin li {
	float: left;
	list-style: none;
	cursor: pointer;
}

.phonenumBox li {
	float: left;
	padding: 0px;
	margin: 0px;
	margin-right: 10px;
	margin-bottom: 10px;
	width: 28px;
	height: 24px;
	line-height: 24px;
	list-style: none;
	color: #222;
	text-align: center;
	border: 1px #ccc solid;
	cursor: pointer;
}

.phonenumBox li:hover {
	background-color: #449fe8;
	color: #fff;
}

.phonenumBox li:active {
	background-color: #207cc9;
	line-height: 20px;
	color: #fff;
}
</style>
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "sysContactsManage"; 
	var tel = "<%=tel%>";
</script>
<script src="<%=basePath%>core/js/jquery-1.7.2.js"
	type="text/javascript"></script>

<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>
 	<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
 	<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerTimeEditor.js" type="text/javascript"></script>
 
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<%--   <!-- ztree -->
<script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
<script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
<!-- ztree --> --%>
<!-- 默认引用 -->
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script src="<%=basePath%>app/js/electronicFax/electronicFaxParam.js"type="text/javascript"></script>
<script src="<%=basePath%>app/js/electronicFax/ceventOld.js" type="text/javascript"></script>
<script src="<%=basePath%>app/js/electronicFax/connect.js" type="text/javascript"></script>

<script type="text/javascript">
	var isConnectSuc;
	var isLoginSuc;
	var grid;
	var audio ;
	$(function (){
		if(tel != 'undefined'){
			$("#telphoneNumber").val(tel);
		}
		$("#sysContactsManage").ligerForm();
		$("#sysContactsManageQuyerCondition").ligerForm();
		createGrid();//加载好友分组列表
		initCallRecordGridlist();
		doInitActiveX();
		if(isConnectSuc){
			$("#version").val(GetVersion());
		}
	});

	function checkMobile(str) {
		var re = /^1\d{10}$/
		if (re.test(str)) {
			return true;
		} else {
			return false;
		}
	}

	function checkPhone(str) {
		var re = /^0\d{2,3}-?\d{7,8}$/;
		if (re.test(str)) {
			return true;
		} else {
			return false;
		}
	}

	function checkDatetime(str) {
		var re = /^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\d):[0-5]?\d:[0-5]?\d$/;
		if (re.test(str)) {
			return true;
		} else {
			return false;
		}
	}

	//请求查询电话录音文件
	function queryPhoneRecord() {
		if (!isConnectSuc) {
			top.$.ligerDialog.warn("电话录音服务器未连接！");
			return;
		}
		var StartID = 0;//起始序号
		var RecordNum = 32; //查询多少条数据，最多32
		var CallerNum = ""; // 主叫号码
		var CalledNum = ""; //被叫号码
		var StartTime = ""; // 开始时间  yyyy-mm-dd hh:mm:ss
		var EndTime = "";// 结束时间 yyyy-mm-dd hh:mm:ss

		if ($("#CallerNum").val()) {
			var maintel = $("#CallerNum").val();
			if (checkMobile(maintel) || checkPhone(maintel)) {
				CallerNum = maintel;
			} else {
				top.$.ligerDialog.warn("请输入正确的手机号或电话号码！");
			}
		}

		if ($("#CalledNum").val()) {
			var stel = $("#CalledNum").val();
			if (checkMobile(stel) || checkPhone(stel)) {
				CalledNum = stel;
			} else {
				top.$.ligerDialog.warn("请输入正确的手机号或电话号码！");
			}
		}

		if ($("#StartTime").val()) {
			var sTime = $("#StartTime").val();
			if (checkDatetime(sTime)) {
				StartTime = sTime;
			} else {
				top.$.ligerDialog.warn("请输入正确的日期！");
			}
		}

		if ($("#EndTime").val()) {
			var eTime = $("#EndTime").val();
			if (checkDatetime(eTime)) {
				EndTime = eTime;
			} else {
				top.$.ligerDialog.warn("请输入正确的日期！");
			}
		}
		var ocxobj = document.getElementById("ServerOcx1");
		var re = ocxobj.QueryRecordFileListV1(StartID, RecordNum, CallerNum, CalledNum,StartTime, EndTime);
		if (re != 0) {
			top.$.ligerDialog.tip({title : '系统提醒信息',content : "请求查询电话录音失败！",height : '260'});
		}else{
			top.$.ligerDialog.tip({title : '系统提醒信息',content : "请求查询电话录音成功！",height : '260'});
		}
	}

	function initCallRecordGridlist() {
		grid = $("#callRecordGridListDiv")
				.ligerGrid(
						{
							checkbox : false,
							columns : [
									{
										display : '文件ID',
										name : 'FileID',
										width : 10,
										hide : true
									},
									{
										display : '录音文件名',
										name : 'FileName',
										width : 130
									},
									{
										display : '主叫号码',
										name : 'CallerNum',
										width : 120,
										render : function(row, index, val) {
											return getcallTelHtml(val);
										}
									},
									{
										display : '被叫号码',
										name : 'CalledNum',
										width : 120,
										render : function(row, index, val) {
											return getcallTelHtml(val);
										}
									},
									{
										display : '开始时间',
										name : 'StartTime',
										width : 160
									},
									{
										display : '结束时间',
										name : 'EndTime',
										width : 160
									},
									
									{
										display : '文件录音',
										name : 'FilePath',
										width : 1,
										hide : true
									},
									{
										display : '操作',
										isAllowHide : false,
										width : 80,
										render : function(row) {
											var html = '<a id="audioPlayerlink" style="font-size: 14px;color: red;" title="点击播放录音" href="javascript:playAudio(\''
													+ row.FilePath
													+ '\');">播放</a>';
											return html;
										}
									} ],
							usePager : true,
							pageSize : 32,
							pageSizeOptions : [ 32 ],
							isChecked : function(rowdata, rowid, rowobj) {

							},
							onCheckRow : function(rowdata, rowid, rowobj) {

							},
							onCheckAllRow : function(rowdata, rowid, rowobj) {

							},
							onSelectRow : function(rowdata, rowid, rowobj) {

							},
							onToFirst : function(element) {

							},
							onToPrev : function(element) {

							},
							onToNext : function(element) {

							},
							onToLast : function(element) {

							},
							width : '790',
							height : '300',
							onSuccess : function(json, grid) {
								$("#pageloading").hide();
							},
							onError : function(json, grid) {
								$("#pageloading").hide();
							}
						});

		grid.set({
			data : CustomersData
		});
		//grid.loadData(function(){},false,CustomersData);
	}

	function getcallTelHtml(val) {
		var html = '<a style="font-size: 14px;color: red;"  href="javascript:setCallTel('
				+ val + ');">' + val + '</a>';
		return html;
	}

	function setCallTel(tel) {
		$("#telphoneNumber").val(tel);
	}

	function inputPhoneNum(num) {
		$("#telphoneNumber").val($("#telphoneNumber").val() + num);
	}

	function clearTelphone() {
		$("#telphoneNumber").val("");
	}

	function telPhoneCancel() {
		var tel = $("#telphoneNumber").val();
		if (tel && tel.length > 1) {
			$("#telphoneNumber").val(tel.substring(0, tel.length - 1));
		} else {
			$("#telphoneNumber").val("");
		}
	}

	var isplay = 0;
	function playAudio(filepath) {
		//$("#audioObj").attr("src",filepath);
		$("#embedObj").attr("src", filepath);
		var embedObject = $("#embedObj")[0];
		if (isplay == 0) {
			isplay = 1;
			embedObject.Play();//Stop(); onpause();
		} else {
			isplay = 0;
			embedObject.onpause();//Stop(); onpause();
		}

		/*audio = document.getElementById('audioObj');
		if(isplay == 0){
			isplay = 1;
			$("#audioPlayerlink").text("暂停");
			$("#audioPlayerlink").attr("title","点击暂停播放录音");
			audio.play();
		}else{
			isplay = 0;
			$("#audioPlayerlink").text("播放");
			$("#audioPlayerlink").attr("title","点击播放录音");
			audio.pause();
		}*/

		/*if(audio.paused){
			audio.play();
			return;
		}
		audio.pause();*/
	}

	var CustomersData = {
		Rows : [ /*{
			"FileID" : "1",
			"FileName" : "AlfredsFutterkiste",
			"CallerNum" : "18140162826",
			"CalledNum" : "13348862826",
			"StartTime" : "2015-12-12 13:12:12",
			"EndTime" : "2015-12-12 13:15:12",
			"FileSize" : 56,
			"FilePath" : basePath + "audio/赵雷-南方姑娘.wav"
		}, {
			"FileID" : "1",
			"FileName" : "AlfredsFutterkiste",
			"CallerNum" : "18140162826",
			"CalledNum" : "13348862826",
			"StartTime" : "2015-12-12 13:12:12",
			"EndTime" : "2015-12-12 13:15:12",
			"FileSize" : 56,
			"FilePath" : basePath + "audio/我的好兄弟.mp3"
		} */],
		Total : 91
	};


 function createGrid(){
        	groupgrid=$("#sysContactsGroupList").ligerGrid({
    			url : basePath + "rest/sysContactGroupManageAction/dataGridPage",
    	        columns: [
    	            { display: '组名', name: 'name',  width: 120}
    	        ],
    	        pageSize:10,
    	        onSelectRow:function(rowdata, rowid, rowobj){
    	        	if(rowdata.id==1){
		        		grids.set({url:basePath + "rest/sysContactsManageAction/dataGridPage"});
		    			grids.reload();
		        	}else{
		        		grids.set({url:basePath + "rest/sysContactsManageAction/dataGridPage?contactGroupId="+rowdata.id});
		    			grids.reload();
		        	}
    	        },
    	        width:160,
    	        height:300
    	    });
    	    
        	grids=$("#sysContactsManageList").ligerGrid({
    			url : basePath + "rest/sysContactsManageAction/dataGridPage",
    	        checkbox: true,
    	        columns: [
    	            { display: '序号', name: 'id',  width: 1 ,hide:true},
    	            { display: '姓名', name: 'name',  width: 90 },
    	            { display: '手机', name: 'mobileTel',  width: 120 ,
						render : function(row, index, val) {
							return getcallTelHtml(val);
						}
                    },
    	           
    	            { display: '办公电话', name: 'officeTel',  width: 120,
						render : function(row, index, val) {
							return getcallTelHtml(val);
						}
                    }
    	        ], 
    	        pageSize:10,
    	        pageSizeOptions:[10, 20, 30, 40, 50,100,200,400,500,1000,2000] ,
    	        isChecked: function(){}, 
    	        onSelectRow:function(rowdata, rowid, rowobj){
    	        	
    	        },
    	        width: 420,height:300,
    	        onSuccess:function (json, grids){
    	        	$("#pageloading").hide(); 
    	        },
    			onError:function (json, grids){
    	        	$("#pageloading").hide(); 
    	        }
    	    });
        }
</script>

<!-- 默认引用end -->

</head>
<OBJECT ID="ServerOcx1" WIDTH=1 HEIGHT=1
	CLASSID="CLSID:59660F8F-517C-48B4-9277-E6C65016A414">
	<PARAM NAME="_Version" VALUE="65536">
	<PARAM NAME="_ExtentX" VALUE="127">
	<PARAM NAME="_ExtentY" VALUE="35">
	<PARAM NAME="_StockProps" VALUE="0">
</OBJECT>

<body style="padding:0px; margin:10px;">
	<audio id="audioObj"></audio>
	<bgsound id="embedObj" />
	<form id="sysContactsManage" method="post">
		<div style="margin:0px auto; width:555px;">
			<ul class="phonenumFillin">
				<li>电话号码：</li>
				<li><input id="telphoneNumber" ligerui="height:24px;"
					type="text" ltype="text" placeholder="请输入电话号码" value="18283596187"
					style=" width:150px; height:18px; border:1px #479fe9 solid; outline-style:none;" />
				</li>
				<li style=" margin-right:40px;"><img id="telphoneNumberBtn"
					title="点击拨打电话" onclick="callTelNum();"
					src="<%=basePath%>app/images/call_10.png" />
				</li>
				<li style=" margin-right:20px;"><img id="telphoneRecord"
					title="点击录音" onclick="StartRecord();"
					src="<%=basePath%>app/images/call_07.png" />
				</li>
				<li><input type="button" value="login" onclick="login();"/></li>
				<li><span id="version" style="text-align:12p"></span></li>
			</ul>
			
		</div>
	</form>

	<div style="width:100%;margin-top: 10px">
		<div id="sysContactsGroupList" style="float:left"></div>
		<div id="sysContactsManageList" style="float:left"></div>
		<div style="float:left">
			<ul class="phonenumBox">
				<li onclick="inputPhoneNum(1);">1</li>
				<li onclick="inputPhoneNum(2);">2</li>
				<li onclick="inputPhoneNum(3);">3</li>
				<li onclick="inputPhoneNum(4);">4</li>
				<li onclick="inputPhoneNum(5);">5</li>
				<li onclick="inputPhoneNum(6);">6</li>
				<li onclick="inputPhoneNum(7);">7</li>
				<li onclick="inputPhoneNum(8);">8</li>
				<li onclick="inputPhoneNum(9);">9</li>
				<li onclick="inputPhoneNum(0);">0</li>
				<li style="width:40px; background-color:#479fe9;"><img
					onclick="telPhoneCancel();"
					src="<%=basePath%>app/images/call_15.png" /></li>
				<li style="width:40px; background-color:#479fe9;"><img
					onclick="clearTelphone();"
					src="<%=basePath%>app/images/vedio_close2.png" /></li>
					
			</ul>
		</div>
	</div>

	</br>
	<form id="sysContactsManageQuyerCondition"
		style="padding:0px; margin:0px;float: left;">
		<table cellpadding="0" cellspacing="0" class="l-table-edit">
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td" style="width:100px;">主叫号码：</td>
				<td align="left" class="l-table-edit-td-input" style="width:180px;">
					<input id="CallerNum" name="CallerNum" type="text" ltype="text" />
				</td>
				<td align="left" style="width:100px;"></td>

				<td align="right" class="l-table-edit-td" style="width:100px;">被叫号码：</td>
				<td align="left" class="l-table-edit-td-input" style="width: 180px;">
					<input id="CalledNum" name="CalledNum" type="text" ltype="text" />
				</td>
				<td align="left" style="width: 60px;"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">开始时间：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="StartTime" name="StartTime" type="text" ltype="time" />
				</td>
				<td align="left"></td>

				<td align="right" class="l-table-edit-td">结束时间：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="EndTime" name="EndTime" type="text" ltype="time" /></td>
				<td align="left" style="width: 60px;"><input type="button"
					class="btn-search" onclick="queryPhoneRecord();" value="查询" />
				</td>
			</tr>
		</table>
	</form>
	<div id="callRecordGridListDiv" style="float: left;"></div>
</body>
</html>

