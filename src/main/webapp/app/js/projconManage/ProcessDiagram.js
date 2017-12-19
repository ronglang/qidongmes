$(function(){
	
	var timetest = getNowDate();
	$("#time").html(getNowDate());
	// 每秒刷新当前时间
	timer2 = setInterval(function(){
		$("#time").html(getNowDate());
	},1000);
})

function JumpToMac(processName){
	window.location.href = basePath+"rest/projconMacManageAction/toMachineDiagram?processName="+encodeURI(processName);
}
// 日期格式转换 Wed Jul 19 2017 17:41:12 GMT+0800转换为yyyy-mm-dd
function getNowDate() {
	var num = new Date();
    num = num + ""; // 给字符串后就一个空格
    var date = "";
    var month = new Array();
    month["Jan"] = 1; month["Feb"] = 2; month["Mar"] = 3; month["Apr"] = 4;
    month["May"] = 5; month["Jan"] = 6; month["Jul"] = 7; month["Aug"] = 8;
    month["Sep"] = 9; month["Oct"] = 10; month["Nov"] = 11; month["Dec"] = 12;
    var week = new Array();
    week["Mon"] = "星期一"; week["Tue"] = "星期二"; week["Wed"] = "星期三"; week["Thu"] = "星期四";
    week["Fri"] = "星期五"; week["Sat"] = "星期六"; week["Sun"] = "星期日";
    str = num.split(" "); // 根据空格组成数组
    date = str[3] + "-" + month[str[1]] + "-" + str[2] + " " + str[4]+" "+week[str[0]]; 
    return date;
}