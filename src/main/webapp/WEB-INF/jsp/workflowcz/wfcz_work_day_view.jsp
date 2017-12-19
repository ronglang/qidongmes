<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
//http://blog.csdn.net/boss666666/article/details/10285975
//你的查询列表的路径
String contextPath = request.getContextPath();
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String getCurYear = null==request.getAttribute("getCurYear")?null:request.getAttribute("getCurYear").toString();//request.getParameter("getCurYear");
String workdays=null==request.getAttribute("workdays")?"":request.getAttribute("workdays").toString();
//request.setAttribute("getCurYear", getCurYear);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>工作日设置</title> 
<jsp:include page="../inc_right.jsp"></jsp:include>
<link rel="stylesheet" href="jquery-ui-1.10.2.custom.min.css" />
<script type="text/javascript" src="jquery-ui-1.10.2.custom.min.js"></script>
<style>
body {
	background-color: rgb(243, 243, 243);
	list-style: none; margin: 0px; padding: 0px; font-family: 宋体; font-size: 12px; text-decoration: none;
}

/*覆盖默认的datepicker样式*/
.ui-datepicker-inline{
	width: 288px;
}
.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default{
    font-weight: normal;
    height: 30px;
    line-height: 30px;
}
.ui-widget-content .ui-dialog-titlebar-close{
	height: 16px;
}
.calendar td a{
	font-size: 12px;
	text-align: center;
}
.calendar .hasData a{
	background: url(calendarTd.png) center center transparent no-repeat;
}
.calendar .hasNoData a{
	/*background: url(one_circle.gif) center center transparent no-repeat;*/
}
</style>
<script type="text/javascript">
var s = new Object();
s.type="";//设为空不刷新父页面
window.returnValue=s;


//关闭并刷新父窗口
function closeAndRefreshWindow(){
	var s=new Object();      
	s.type="ok";//设置返回值为ok就可以。//这里返回刷新父页面。
	window.returnValue=s;   
	window.close();    
} 
  
//关闭并刷新父窗口
function closeWindow(){      
	window.close();      
} 
 
 var curr_workdays='<%=workdays%>';
 var curr_workdaysArr=curr_workdays.split(',');
$(function() {
	//每次进入界面重新选择
	document.getElementById("restOneToFive").value='';
	document.getElementById("workSixToSun").value='';
	document.getElementById("oworkday").value='';
	//工作日（周一至周五）
	var oworkday = document.getElementById("oworkday");
	var oget12lastFive='';//得到今年最后一个工作日（1-5）
	var getCurYear='';
	var oarray=new Array();
	var dateTime=new Date();
	<%
	if(getCurYear==null){//如果参数中当前年为空，就取JS的当前年
	%> 
		getCurYear=dateTime.getFullYear();//得到年
	<%
	}else{ //取参数中传递过来的当前年 getCurYear
	%>
		getCurYear=parseInt('${getCurYear}');
	<%
	} 
	%> 
	var global_now_year=getCurYear;
	$("#todayYear").html(getCurYear);
	
	//得到当前年月
	function F_getYear(){
		return getCurYear;
	} 
	$("#onowyear").html(F_getYear()); 
	 
	//计算某年的月的天数
	function calCountday(curYear,curMonth){
		var dayCount=30;
		switch(curMonth){
		 case 1://大月31天 
		 dayCount = 31;
		 break;
		 case 3:
		dayCount = 31;
		 break;
		 case 5:
		dayCount = 31;
		 break;
		 case 7:
		dayCount = 31;
		 break;
		 case 8:
		dayCount = 31;
		 break;
		 case 10:
		dayCount = 31;
		 break;
		 case 12:
		dayCount = 31;
		 break;
		 case 4://小月30天 
		dayCount = 30;
		 break;
		 case 6:
		dayCount = 30;
		 break;
		 case 9:
		dayCount = 30;
		 break;
		 case 11:
		dayCount = 30;
		 break;
		 case 2://2月特殊判断处理 
		 //判断闰年：能被400整除，或者能被4整除而不能被100整除。
		if(0==curYear%4){
			if(0!=curYear%100){//闰年2月29天 
				dayCount=29;
			}else{
				dayCount=28;//平年2月28天
			}
		 }else if(0==curYear%400){ 
		 	dayCount=29;
		 }else{
		dayCount=28;
		 }
		 break; 
		}
		return dayCount;
	}

	
	//##################################################
	//小于10的补零
	function getfillZero(text){
		if(text<10){
			return "0"+text;
		}else{ 
			return text;
		} 
	}
	
	
	/*############################初始化一年12个月的工作日#################################*/
	for(var i=1;i<=12;i++){
	    //最小日期
		var onewfrom=getCurYear+'-'+getfillZero(i)+'-01';
		//最大日期
		var onewTo=getCurYear+'-'+getfillZero(i)+'-'+calCountday(getCurYear,i);
		$( "#datepicker"+i ).datepicker({
			showOn: "button",
			dateFormat:'yy-mm-dd',
			minDate:onewfrom,
			maxDate:onewTo,
			monthNames:['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
			dayNamesMin:['日', '一', '二', '三', '四', '五', '六'], 
			beforeShowDay:function(date){//在日期控件显示面板之前，每个面板上的日期绑定时都触发此事件，参数为触发事件的日期。用函数后，必须返回一个数组：[0]此日期是否可选(true/false)，[1]此日期的CSS样式名称(""表示默认)，[2]当鼠标移至上面出现一段提示的内容。
				var dateText=date.getFullYear()+"-"+getfillZero((date.getMonth()+1))+"-"+getfillZero(date.getDate());
				for(var j in curr_workdaysArr){
					var sysDate =curr_workdaysArr[j];
					if(sysDate== dateText){//如果当前单元格的日期与课程的开课日期是同一天，就返回一个带有三个元素的数组变量:
					                     //分别代表日期是否可选，要在日期容器里额外添加的class属性及单元格的hover事件触发时显示的内容，相当于a的title。
						return [true, 'hasData', '工作日'];
					}
				}
				oarray[2]=dateText;
				return oarray;
			}
		});
	} 
	
});
</script>
</head>
<body onunload="closeAndRefreshWindow()">
<div id="Mns" style="margin:0 auto;text-align:center;">
		
<!--========重要参数定义======-->			
<div style="display: none">
	<textarea id="new_workday"></textarea>
	<!--此项是工作日(周一至周五) -->
	<input type="text" id="restOneToFive" size="180"style="display: none" />
	<!-- 此项是休息日(周六和周日) -->
	<input type="text" id="workSixToSun" size="180" style="display: none"/>
	<!--周一至周五全年工作日-->
	<textarea rows="10" cols="180" id="oworkday" style="display: none"></textarea>
</div>
			
<a href="#" class="easyui-linkbutton" onclick="closeWindow()">关闭返回</a>

<table class="datepicker_table" style="padding:0 0 0 0" border="0" >
 <tr>
	 <td><span class="calendar" id="datepicker1" ></span></td>
	 <td><span class="calendar" id="datepicker2" ></span></td>
	 <td><span class="calendar" id="datepicker3" ></span></td>
	 <td><span class="calendar" id="datepicker4" ></span></td>
 </tr>
 <tr>
	 <td><span class="calendar" id="datepicker5" ></span></td>
	 <td><span class="calendar" id="datepicker6" ></span></td>
	 <td><span class="calendar" id="datepicker7" ></span></td>
	 <td><span class="calendar" id="datepicker8" ></span></td>
 </tr>
 <tr>
	 <td><span class="calendar" id="datepicker9" ></span></td>
	 <td><span class="calendar" id="datepicker10" ></span></td>
	 <td><span class="calendar" id="datepicker11" ></span></td>
	 <td><span class="calendar" id="datepicker12" ></span></td>
 </tr>
</table> 
</div>
</body>
</html>
