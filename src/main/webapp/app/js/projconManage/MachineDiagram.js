$(function(){
	var processName = getUrlParam("processName");
	processName = decodeURI(processName);
	$("#proName").html(processName);
	showAllDiagram(processName);
	init_updateSelecState();
	changeSerch(processName);
	$("#time").html(getNowDate());
	timer2 = setInterval(function(){
		$("#time").html(getNowDate());
	},1000);
	$("#contentlist").hide();
})
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  // 匹配目标参数
    if (r != null) return r[2]; return null; // 返回参数值
}
//显示机台
function showContentList(){
	$("#contentlist").slideToggle(1000);
}

//日期格式转换 Wed Jul 19 2017 17:41:12 GMT+0800转换为yyyy-mm-dd
function getNowDate() {
	var num = new Date();
    num = num + ""; //给字符串后就一个空格
    var date = "";
    var month = new Array();
    month["Jan"] = 1; month["Feb"] = 2; month["Mar"] = 3; month["Apr"] = 4;
    month["May"] = 5; month["Jan"] = 6; month["Jul"] = 7; month["Aug"] = 8;
    month["Sep"] = 9; month["Oct"] = 10; month["Nov"] = 11; month["Dec"] = 12;
    var week = new Array();
    week["Mon"] = "星期一"; week["Tue"] = "星期二"; week["Wed"] = "星期三"; week["Thu"] = "星期四";
    week["Fri"] = "星期五"; week["Sat"] = "星期六"; week["Sun"] = "星期日";
    str = num.split(" "); //根据空格组成数组
    date = str[3] + "-" + month[str[1]] + "-" + str[2] + " " + str[4]+" "+week[str[0]]; 
    return date;
}
//根据工序名称展示所有的列表
function showAllDiagram(processName){
	$.ajax({
		url:basePath+"rest/projconMacManageAction/loadProjconMacManage",
		dataType: "json",   //返回格式为json
	    data:{processName:processName},
	    type: "post",
	    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
	    success: function(list) {
	    	init_param(list);
	    	createDiagram(list);
	    },
	    error: function() {
	       alert("根据工序名称展示所有的列表方法出错，请联系管理员");
	    }
	})
}
//根据不同的list设置不同的图标
function createDiagram(list){
	debugger;
	var ulist = document.getElementById("ulist");
	var str = "";
	while(ulist.firstChild){
		ulist.removeChild(ulist.firstChild);
    }
	for(var j = 0;j<list.length;j++){
		var macState = list[j]["macState"];
		switch(macState){
			case '未施工':
				str+= "<li name='"+list[j]["macCode"]+"' onclick='setSelected(this)'><h3>"+list[j]["macCode"]+"</h3><img src="+basePath+"app/images/projconManage/未施工.jpg></li>";
				break;
			case '已施工':
				str+= "<li name='"+list[j]["macCode"]+"' onclick='setSelected(this)'><h3>"+list[j]["macCode"]+"</h3><img  src="+basePath+"app/images/projconManage/已施工.jpg></li>";
				break;
			case '施工中':
				str+= "<li name='"+list[j]["macCode"]+"' onclick='setSelected(this)'><h3>"+list[j]["macCode"]+"</h3><img src="+basePath+"app/images/projconManage/施工中.jpg></li>";
				break;
			default:
				str+="";
				break;
		}
	}
	$("#ulist").append(str);
}
//总数：totalMacVal 已施工数： conMacVal 施工中数：comMacing
function init_param(list){
	var totalmacValNum = list.length;
	var conMacValNum = 0;
	var comMacingNum =0;
	for(var j = 0;j<list.length;j++){
		var macState = list[j]["macState"];
		switch(macState){
			case '已施工':
				conMacValNum+=1;
				break;
			case '施工中':
				comMacingNum+=1;
				break;
			default:
				break;
		}
	}
	$("#totalMacVal").html(totalmacValNum);
	$("#conMacVal").html(conMacValNum);
	$("#comMacing").html(comMacingNum);
}


//下拉列表框点击改变事件
function changeSerch(processName){
　　var construcSelec=document.getElementById("construcSelec");
　　construcSelec.onchange=function(){
　　　　var searchSate = construcSelec.options[construcSelec.selectedIndex].value;
	 if(searchSate.startsWith("--")){
		 showAllDiagram(processName);
	 }else{
		 $.ajax({
			url:basePath+"rest/projconMacManageAction/serchChangeProjconMacManage",
			dataType: "json",   //返回格式为json
		    data:{processName:processName,searchSate:searchSate},
		    type: "post",
		    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
		    success: function(list) {
		    	createDiagram(list);
		    },
		    error: function() {
		       alert("下拉列表框点击改变事件方法出错，请联系管理员");
		    }
		}) 
	 }
　　}　
}

//增加机台 
function addMac(){
	var adddiv=$('#adddiv');
    $.ligerDialog.open({
        height:300,
        width:300,
        title : '新增机台',
        showMax: true,
        showToggle: true,
        showMin: true,
        isResize: true,
        slide: false,
        target:adddiv,
        buttons: [{ 
        			text: '保存', 
        			onclick: function (i, d) { 
        				var macCode = $("#macCode").val();
    				  	debugger;
        				var macState = $("#macState").val(); 
    				  	debugger;
    				  	var processName = $("#processName").val();
    				  	debugger;
    				  	 $.ajax({
    							url:basePath+"rest/projconMacManageAction/saveProjconMacManage",
    							dataType: "json",   //返回格式为json
    						    data:{macCode:macCode,macState:macState,processName:processName},
    						    type: "post",
    						    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
    						    success: function() {
    						    	showAllDiagram(processName);
    						    },
    						    error: function() {
    						       alert("增加机台方法出错 ，请联系管理员");
    						    }
    						}) 
    						d.hide();
        			 }
        		   },
                   { 
        			  text: '取消', 
        			  onclick: function (i, d) {
        				  	 d.hide();
        			  }
        		   }], 
    });
}

//修改机台
function updateMac(){
	debugger;
	 $.ajax({
			url:basePath+"rest/projconMacManageAction/serchSelecStateNum",
			dataType: "json",   //返回格式为json
		    type: "post",
		    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
		    success: function(list) {
		    	debugger;
		    	if(list.length==1){
		    		var obj = list[0];
		    		update(obj);
		    	}else{
		    		alert("请选择一条数据");
		    	}
		    },
		    error: function() {
		    	alert("修改机台方法出错，请联系管理员");
		    }
		})
}
//修改机台2
function update(obj){
	var modifydiv=$('#modifydiv');
	var processName = $("#proName").html();
	$("#id1").val(obj["id"]);
	$("#macCode1").val(obj["macCode"]);
	$("#macState1").val(obj["macState"]); 
  	$("#processName1").val(obj["processName"]);
    $.ligerDialog.open({
        height:300,
        width:300,
        title : '修改机台',
        showMax: true,
        showToggle: true,
        showMin: true,
        isResize: true,
        slide: false,
        target:modifydiv,
        buttons: [{ 
        			text: '保存', 
        			onclick: function (i, d) { 
        				obj["id"] = $("#id1").val();
        				obj["macCode"] = $("#macCode1").val();
        				obj["macState"] = $("#macState1").val(); 
        				obj["processName"] = $("#processName1").val();
    				  	debugger;
    				  	 $.ajax({
    							url:basePath+"rest/projconMacManageAction/updateProjconMacManage",
    							dataType: "json",   //返回格式为json
    						    data:obj,
    						    type: "post",
    						    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
    						    success: function(msg) {
    						    	showAllDiagram(processName);
    						    },
    						    error: function() {
    						       alert("修改机台2方法中的保存出错，请联系管理员");
    						    }
    						}) 
    						d.hide();
        			 }
        		   },
                   { 
        			  text: '取消', 
        			  onclick: function (i, d) {
        				  	 d.hide();
        			  }
        		   }], 
    });
}
//删除机台
function deleteMac(){
	var proName = $("#proName").html();
	 $.ajax({
			url:basePath+"rest/projconMacManageAction/deleteProjconMacManageBySelecState",
			dataType: "json",   //返回格式为json
		    type: "post",
		    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
		    success: function(msg) {
		    	showAllDiagram(proName);
		    	debugger;
		    },
		    error: function() {
		    	alert("删除机台方法出错，请联系管理员");
		    }
		})
}

//设置选中样式
function setSelected(li){
	var $li = $(li);
	var macCode = $li.attr("name");
	debugger;
	 $.ajax({
			url:basePath+"rest/projconMacManageAction/serchProjconMacManageForSelecState",
			dataType: "json",   //返回格式为json
		    data:{macCode:macCode},
		    type: "post",
		    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
		    success: function(selecState) {
		    	if(selecState!="true"){
		    		$li.addClass("imgbackground");
		    		var sstate ="true";
		    		updateSelecState(sstate,macCode);
		    	}else{
		    		debugger;
		    		$li.removeClass("imgbackground");
		    		var sstate = "";
		    		updateSelecState(sstate,macCode);
		    	}
		    },
		    error: function() {
		    	alert("设置选中样式方法出错，请联系管理员");
		    }
		})
}

//跟新标记值
function updateSelecState(selecState,macCode){
	 $.ajax({
			url:basePath+"rest/projconMacManageAction/updateProjconMacManageForSelecState",
			dataType: "json",   //返回格式为json
		    data:{selecState:selecState,macCode:macCode},
		    type: "post",
		    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
		    success: function(msg) {
		    },
		    error: function() {
		    	alert("跟新标记值方法出错，请联系管理员");
		    }
		})
}
//初始化跟新标记值
function init_updateSelecState(){
	$.ajax({
		url:basePath+"rest/projconMacManageAction/initUpdateSelecState",
		dataType: "json",   //返回格式为json
	    type: "post",
	    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
	    success: function(msg) {
	    },
	    error: function() {
	    	alert("初始化跟新标记值方法出错，请联系管理员");
	    }
	})
}
//机台详情页
function detailMac(){
	var processName = $("#proName").html();
	 $.ajax({
			url:basePath+"rest/projconMacManageAction/serchSelecStateNum",
			dataType: "json",   //返回格式为json
		    type: "post",
		    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
		    success: function(list) {
		    	if(list.length==1){
		    		var obj = list[0];
		    		alert(obj["id"]);
		    		init_updateSelecState();
		    		showAllDiagram(processName);
		    	}else{
		    		alert("请选择一条数据");
		    	}
		    },
		    error: function() {
		    	alert("机台详情页方法出错，请联系管理员");
		    }
		})
}





