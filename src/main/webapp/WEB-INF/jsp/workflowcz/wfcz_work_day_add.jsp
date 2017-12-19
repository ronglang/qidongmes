<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat,com.xml.workflow.WorkflowService,com.xml.vo.WfWorkday" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
try {
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%
//http://blog.csdn.net/boss666666/article/details/10285975
//你的查询列表的路径
String contextPath = request.getContextPath();
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//String getCurYear = null==request.getAttribute("getCurYear")?null:request.getAttribute("getCurYear").toString();
String getCurYear = null==request.getParameter("getCurYear")?null:request.getParameter("getCurYear").toString();
WorkflowService service=null;
boolean haveCurYearData=false;
if("".equals(getCurYear) || null==getCurYear){
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
	getCurYear=sdf.format(new Date());
}
service=new WorkflowService();
haveCurYearData=service.checkWorkdayDataIsExist(getCurYear);

request.setAttribute("getCurYear", getCurYear);
%>
<head>
<base target='_self'/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>工作日设置</title> 
<jsp:include page="../inc_right.jsp"></jsp:include>
<link href="../images/style.css" rel="stylesheet" type="text/css" />
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

var haveCurYearData='<%=haveCurYearData%>';

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


//解决window.showModalDialog 模态窗口中打开新窗口
function go_to(url){
	document.getElementById('goLocation').href =url;
	document.getElementById('goLocation').click();
}

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
	 
	//##################得到今年最后一个工作日（1-5）#####################
	function get12lastFive(){
		var ofive="";
		var i=1;
		for(i=24;i<=31;i++){
			var dateText=getCurYear+'-12-'+getfillZero(i);
			var ogetdate=new Date(Date.parse(dateText.replace(/-/g, "/"))).getDay();
			if(ogetdate>=1){
				if(ogetdate<=5){
					ofive+=dateText+",";
				}
			}
		} 
		ofive=ofive.substring(0,ofive.lastIndexOf(","));
		ofive=ofive.substring(ofive.lastIndexOf(",")+1,ofive.length);
		return ofive; 
	}
	oget12lastFive=get12lastFive();////得到今年最后一个工作日（1-5）
	
	//##################################################
	//小于10的补零
	function getfillZero(text){
		if(text<10){
			return "0"+text;
		}else{ 
			return text;
		} 
	}
	
	/**
	*  获取日期数组
	*oinputObj是输入框对象（ID）
	*得到日期数组
	*/
	function getArrayDate(oinputObj){
		var arrayrest=oinputObj.value.substring(0,oinputObj.value.lastIndexOf(",")); 
		var getarrayarrayrest=arrayrest.split(",");
		return getarrayarrayrest;
	} 
	
	/**
	* 把日期dateText追加到oinputObj对象中，或者从对象oinputObj中移除dateText
	*oinputObj是输入框对象（ID）
	*dateText是日期文本
	*/
	function f_appendOrRemoveCurrSelectDate(oinputObj,dateText){
		var flag; 
		if (typeof(oinputObj)!=undefined) {
			flag=oinputObj.value.indexOf(dateText);
		}
		if(flag!=undefined){
			if(flag==-1){//oinputObj中没有dateText时，追加进去
			    var old_obj_val=oinputObj.value;
				if(old_obj_val.indexOf('end')<0){//无end
				   oinputObj.value = old_obj_val+dateText+",";
				}else{//有end时，加到end前面
				    oinputObj.value = old_obj_val.substr(0,old_obj_val.indexOf('end'))+dateText+old_obj_val.substr(old_obj_val.indexOf('end')-1,old_obj_val.length);// old_obj_val.+dateText+",";
				}
				
			}else{//已有时移除
				var clickText=dateText+",";
				//再次点击时去掉
				var text=oinputObj.value.replace(clickText, ""); 
				oinputObj.value=text;
			}
		} 
	}
	
	//#############################################
	//上一年
	$("#oyearup").click(function (){
		//showloading();
		$("#oyearup").attr("disabled",true); 
		var oyearup=$("#todayYear").html();
		oyearup--;
		$("#todayYear").html(oyearup);
		getCurYear=oyearup;
		//得到今年的最后一天
		var year_lastDay=getCurYear+'-12-'+calCountday(getCurYear,12);
		//得到今年的第一天
		var year_firstDay=getCurYear+'-01-01';
		var url="wfcz_work_day_add.jsp?planNo=${planNo}"
		//window.location.href=url+"&yearlastDay="+year_lastDay+"&yearfirstDay="+year_firstDay+"&getCurYear="+getCurYear;
		go_to(url+"&yearlastDay="+year_lastDay+"&yearfirstDay="+year_firstDay+"&getCurYear="+getCurYear);
		getCurYear='${getCurYear}';
	})
	
	//下一年
	$("#oyeardown").click(function (){
		//showloading();
		$("#oyeardown").attr("disabled",true); 
		var oyeardown=$("#todayYear").html();
		oyeardown++;
		$("#todayYear").html(oyeardown);
		getCurYear=oyeardown;
		//得到今年的最后一天
		var year_lastDay=getCurYear+'-12-'+calCountday(getCurYear,12);
		//得到今年的第一天
		var year_firstDay=getCurYear+'-01-01';
		var url="wfcz_work_day_add.jsp?planNo=${planNo}"
		//window.location.href=url+"&yearlastDay="+year_lastDay+"&yearfirstDay="+year_firstDay+"&getCurYear="+getCurYear;
		go_to(url+"&yearlastDay="+year_lastDay+"&yearfirstDay="+year_firstDay+"&getCurYear="+getCurYear);
		getCurYear='${getCurYear}';
	})
	
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
				//0和6是周日和周六
				var ogetdate=new Date(date).getDay();
				//休息日(周一至周五)
				var orestOneToFive=document.getElementById("restOneToFive");
				//工作日(周六和周日) 
				var oworkSixToSun=document.getElementById("workSixToSun");
				//工作日（周一至周五）
				var oworkday = document.getElementById("oworkday");
				if(ogetdate>=1 && ogetdate<=5){//是周一到周五
					//得到今年的最后一天
					var year_lastDay=new Date((getCurYear+'-12-'+calCountday(getCurYear,12)).replace(/-/g,"/"));
					//得到今年的第一天
					var year_firstDay=new Date((getCurYear+'-01-01').replace(/-/g,"/"));
					//得到全年
					if(year_firstDay<=date){
						if(date<=year_lastDay){ //第一天<= 当前天 <=最后一天
							var endFlag=oworkday.value.indexOf("end");
							var flagText=oworkday.value.indexOf(dateText);//工作日 中是否有该日期
							if(flagText==-1){//没有应该日期时，添加到工作日中，以逗号分隔
								if(endFlag==-1){//且没有到达一年最后的5个工作日
									oworkday.value = oworkday.value+dateText+",";
								} 
								if(dateText==oget12lastFive){//到达一年最后的5个工作日后 末尾加end
									oworkday.value = oworkday.value+"end";
								} 
							} 
						}
					}
					//调用函数后，必须返回一个数组：
					//[0]此日期是否可选 (true/false)，
					//[1]此日期的CSS样式名称(""表示默认)，
					//[2]当鼠标移至上面出现一段提示的内容
					/*oarray[0]='true';
					var flag=document.getElementById("restOneToFive").value.indexOf(dateText);//得到是否存在
					if(flag!=-1){//找到子字符串
						oarray[1]="ui-state-disabled";
					}else{
						oarray[1]="ui-state-default";
					}*/
					var flag=document.getElementById("oworkday").value.indexOf(dateText);//得到是否存在
					if(flag!=-1){//找到子字符串
						return [true, 'hasData', '工作日'];//oarray[1]="ui-state-disabled";
					}else{
						return [true, 'hasNoData', '非工作日'];//oarray[1]="ui-state-default";
					}
				}else{//是周六 或者  周日
					/*oarray[0]='false';
					var flag=document.getElementById("workSixToSun").value.indexOf(dateText);//得到是否存在
					if(flag!=-1){
						oarray[1]="ui-state-default";
					}else{ 
						oarray[1]="ui-state-disabled";
					}*/
					var flag=document.getElementById("oworkday").value.indexOf(dateText);//得到是否存在
					if(flag!=-1){//找到子字符串
						return [true, 'hasData', '工作日'];//oarray[1]="ui-state-disabled";
					}else{
						return [true, 'hasNoData', '非工作日'];//oarray[1]="ui-state-default";
					}
				}
				//oarray[2]=dateText;
				return oarray;
			},
			beforeShow : function(input){//在日期控件显示面板之前，触发此事件，并返回当前触发事件的控件的实例对象
			},
			onSelect:function (dateText, inst){//当在日期面板选中一个日期后触发此事件，参数为选择的日期和当前日期插件的实例
			    //计算周几
				var ogetdate=new Date(Date.parse(dateText.replace(/-/g, "/"))).getDay();
				var orestOneToFive = document.getElementById("restOneToFive");
				var oworkSixToSun = document.getElementById("workSixToSun");
				var my_workday=document.getElementById("oworkday");
				//周一至周五
				if(ogetdate>=1 && ogetdate<=5){
					//添加--> 此项是工作日(周一至周五)
					f_appendOrRemoveCurrSelectDate(orestOneToFive,dateText);
				}else{//添加--> 此项是休息日 周六和周日
					f_appendOrRemoveCurrSelectDate(oworkSixToSun,dateText);
				} 
				//添加或者移除到工作日
				f_appendOrRemoveCurrSelectDate(my_workday,dateText);
			}
		});
	} 
	
	//工作日添加
	$("#olookup").click(function (){
	    var tipMsg='确实要保存'+global_now_year+'的日期设置吗？';
	    if(haveCurYearData=="true"){
	       tipMsg=global_now_year+'的工作日已经设置过，确实要更新';
	       tipMsg=tipMsg+global_now_year;
	       tipMsg=tipMsg+'的日期设置吗？';
		}
		if(confirm(tipMsg)){
			//showloading();
			var osubrestOneToFive=document.getElementById("restOneToFive");
			var osubworkSixToSun=document.getElementById("workSixToSun");
			var osuboworkday=document.getElementById("oworkday");
			//得到今年的最后一天
			var year_lastDay=getCurYear+'-12-'+calCountday(getCurYear,12);
			//得到今年的第一天
			var year_firstDay=getCurYear+'-01-01';
			//alert(osuboworkday.value);
			$('#new_workday').val(osuboworkday.value);
		  $.ajax({
			url:"wfcz_workdaySave.action?curr_year="+global_now_year+"&yearfirstDay="+year_firstDay+"&yearlastDay="+year_lastDay+"&new_workday="+$('#new_workday').val()+"&restOneToFive="+osubrestOneToFive.value+"&workSixToSun="+osubworkSixToSun.value,
			type:'post',
			async:true,
			cache:false,
			timeout: 100000,
			data:null,
			error:function(){
				alert("服务器繁忙，请稍后再试！");
			},
			success: function(result){
		        if(result=="1"){
		            alert("工作日设置成功！");
		            closeAndRefreshWindow()
		         }else{
		             alert("工作日设置失败！");
		         }
		     }
		  });
		}
	}); 
	
	//$(".MnsTbsEdit").css("height","718px");
	//$(".MnsTbsEdit").css("width","785px");
});
</script>
</head>
<body>
<a href="" id="goLocation" style="display:none">不会打开新窗口哦</a>
<div id="Mns">
	<div class="MnsTbsEdit">
		<div>
		</div>
		<div class="MnsTbsEdit3">
			
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
			
<a href="#" id="oyearup" name="lookup" class="easyui-linkbutton">&lt;&lt;上一年</a>
<span id="todayYear" style="color: #FF0000; font-weight: bold;"></span>
<a href="#" id="oyeardown" name="lookup" class="easyui-linkbutton">下一年&gt;&gt;</a>
<a href="#" id="olookup" name="lookup" class="easyui-linkbutton">保存工作日设置</a>
&nbsp;&nbsp;&nbsp;&nbsp;
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
	</div>
</div>
</body>
</html>
<%
} catch (Exception e) {
e.printStackTrace();
}
%>