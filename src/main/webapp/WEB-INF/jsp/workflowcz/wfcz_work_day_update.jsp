<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
try {
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%
//http://blog.csdn.net/boss666666/article/details/10285975
//��Ĳ�ѯ�б��·��
String contextPath = request.getContextPath();
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String getCurYear = null==request.getAttribute("getCurYear")?null:request.getAttribute("getCurYear").toString();//request.getParameter("getCurYear");
String workdays=null==request.getAttribute("workdays")?"":request.getAttribute("workdays").toString();
//request.setAttribute("getCurYear", getCurYear);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>����������</title> 
<jsp:include page="../inc_right.jsp"></jsp:include>
<link rel="stylesheet" href="jquery-ui-1.10.2.custom.min.css" />
<script type="text/javascript" src="jquery-ui-1.10.2.custom.min.js"></script>
<style>
body {
	background-color: rgb(243, 243, 243);
	list-style: none; margin: 0px; padding: 0px; font-family: ����; font-size: 12px; text-decoration: none;
}

/*����Ĭ�ϵ�datepicker��ʽ*/
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
s.type="";//��Ϊ�ղ�ˢ�¸�ҳ��
window.returnValue=s;


//�رղ�ˢ�¸�����
function closeAndRefreshWindow(){
	var s=new Object();      
	s.type="ok";//���÷���ֵΪok�Ϳ��ԡ�//���ﷵ��ˢ�¸�ҳ�档
	window.returnValue=s;   
	window.close();    
} 
  
//�رղ�ˢ�¸�����
function closeWindow(){      
	window.close();      
} 
 
 var curr_workdays='<%=workdays%>';
 var curr_workdaysArr=curr_workdays.split(',');
 
$(function() {
	//ÿ�ν����������ѡ��
	document.getElementById("restOneToFive").value='';
	document.getElementById("workSixToSun").value='';
	document.getElementById("oworkday").value='';
	//�����գ���һ�����壩
	var oworkday = document.getElementById("oworkday");
	var oget12lastFive='';//�õ��������һ�������գ�1-5��
	var getCurYear='';
	var oarray=new Array();
	var dateTime=new Date();
	<%
	if(getCurYear==null){//��������е�ǰ��Ϊ�գ���ȡJS�ĵ�ǰ��
	%> 
		getCurYear=dateTime.getFullYear();//�õ���
	<%
	}else{ //ȡ�����д��ݹ����ĵ�ǰ�� getCurYear
	%>
		getCurYear=parseInt('${getCurYear}');
	<%
	} 
	%> 
	var global_now_year=getCurYear;
	$("#todayYear").html(getCurYear);
	
	//�õ���ǰ����
	function F_getYear(){
		return getCurYear;
	} 
	$("#onowyear").html(F_getYear()); 
	 
	//����ĳ����µ�����
	function calCountday(curYear,curMonth){
		var dayCount=30;
		switch(curMonth){
		 case 1://����31�� 
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
		 case 4://С��30�� 
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
		 case 2://2�������жϴ��� 
		 //�ж����꣺�ܱ�400�����������ܱ�4���������ܱ�100������
		if(0==curYear%4){
			if(0!=curYear%100){//����2��29�� 
				dayCount=29;
			}else{
				dayCount=28;//ƽ��2��28��
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
	 
	//##################�õ��������һ�������գ�1-5��#####################
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
	oget12lastFive=get12lastFive();////�õ��������һ�������գ�1-5��
	
	//##################################################
	//С��10�Ĳ���
	function getfillZero(text){
		if(text<10){
			return "0"+text;
		}else{ 
			return text;
		} 
	}
	
	/**
	*  ��ȡ��������
	*oinputObj����������ID��
	*�õ���������
	*/
	function getArrayDate(oinputObj){
		var arrayrest=oinputObj.value.substring(0,oinputObj.value.lastIndexOf(",")); 
		var getarrayarrayrest=arrayrest.split(",");
		return getarrayarrayrest;
	} 
	
	/**
	* ������dateText׷�ӵ�oinputObj�����У����ߴӶ���oinputObj���Ƴ�dateText
	*oinputObj����������ID��
	*dateText�������ı�
	*/
	function f_appendOrRemoveCurrSelectDate(oinputObj,dateText){
		var flag; 
		if (typeof(oinputObj)!=undefined) {
			flag=oinputObj.value.indexOf(dateText);
		}
		if(flag!=undefined){
			if(flag==-1){//oinputObj��û��dateTextʱ��׷�ӽ�ȥ
			    var old_obj_val=oinputObj.value;
				if(old_obj_val.indexOf('end')<0){//��end
				   oinputObj.value = old_obj_val+dateText+",";
				}else{//��endʱ���ӵ�endǰ��
				    oinputObj.value = old_obj_val.substr(0,old_obj_val.indexOf('end'))+dateText+old_obj_val.substr(old_obj_val.indexOf('end')-1,old_obj_val.length);// old_obj_val.+dateText+",";
				}
				
			}else{//����ʱ�Ƴ�
				var clickText=dateText+",";
				//�ٴε��ʱȥ��
				var text=oinputObj.value.replace(clickText, ""); 
				oinputObj.value=text;
			}
		} 
	}
	
	//#############################################
	//��һ��
	$("#oyearup").click(function (){
		//showloading();
		$("#oyearup").attr("disabled",true); 
		var oyearup=$("#todayYear").html();
		oyearup--;
		$("#todayYear").html(oyearup);
		getCurYear=oyearup;
		//�õ���������һ��
		var year_lastDay=getCurYear+'-12-'+calCountday(getCurYear,12);
		//�õ�����ĵ�һ��
		var year_firstDay=getCurYear+'-01-01';
		var url="wfcz_work_day.action?planNo=${planNo}"
		window.location.href=url+"&yearlastDay="+year_lastDay+"&yearfirstDay="+year_firstDay+"&getCurYear="+getCurYear;
		getCurYear='${getCurYear}';
	})
	
	//��һ��
	$("#oyeardown").click(function (){
		//showloading();
		$("#oyeardown").attr("disabled",true); 
		var oyeardown=$("#todayYear").html();
		oyeardown++;
		$("#todayYear").html(oyeardown);
		getCurYear=oyeardown;
		//�õ���������һ��
		var year_lastDay=getCurYear+'-12-'+calCountday(getCurYear,12);
		//�õ�����ĵ�һ��
		var year_firstDay=getCurYear+'-01-01';
		var url="wfcz_work_day.action?planNo=${planNo}"
		window.location.href=url+"&yearlastDay="+year_lastDay+"&yearfirstDay="+year_firstDay+"&getCurYear="+getCurYear;
		getCurYear='${getCurYear}';
	})
	
	/*############################��ʼ��һ��12���µĹ�����#################################*/
	for(var i=1;i<=12;i++){
	    //��С����
		var onewfrom=getCurYear+'-'+getfillZero(i)+'-01';
		//�������
		var onewTo=getCurYear+'-'+getfillZero(i)+'-'+calCountday(getCurYear,i);
		$( "#datepicker"+i ).datepicker({
			showOn: "button",
			dateFormat:'yy-mm-dd',
			minDate:onewfrom,
			maxDate:onewTo,
			monthNames:['һ��', '����', '����', '����', '����', '����', '����', '����', '����', 'ʮ��', 'ʮһ��', 'ʮ����'],
			dayNamesMin:['��', 'һ', '��', '��', '��', '��', '��'], 
			beforeShowDay:function(date){//�����ڿؼ���ʾ���֮ǰ��ÿ������ϵ����ڰ�ʱ���������¼�������Ϊ�����¼������ڡ��ú����󣬱��뷵��һ�����飺[0]�������Ƿ��ѡ(true/false)��[1]�����ڵ�CSS��ʽ����(""��ʾĬ��)��[2]����������������һ����ʾ�����ݡ�
				var dateText=date.getFullYear()+"-"+getfillZero((date.getMonth()+1))+"-"+getfillZero(date.getDate());
				//0��6�����պ�����
				var ogetdate=new Date(date).getDay();
				//��Ϣ��(��һ������)
				var orestOneToFive=document.getElementById("restOneToFive");
				//������(����������) 
				var oworkSixToSun=document.getElementById("workSixToSun");
				//�����գ���һ�����壩
				var oworkday = document.getElementById("oworkday");
				if(ogetdate>=1 && ogetdate<=5){//����һ������
					//�õ���������һ��
					var year_lastDay=new Date((getCurYear+'-12-'+calCountday(getCurYear,12)).replace(/-/g,"/"));
					//�õ�����ĵ�һ��
					var year_firstDay=new Date((getCurYear+'-01-01').replace(/-/g,"/"));
					//�õ�ȫ��
					if(year_firstDay<=date){
						if(date<=year_lastDay){ //��һ��<= ��ǰ�� <=���һ��
							var endFlag=oworkday.value.indexOf("end");
							var flagText=oworkday.value.indexOf(dateText);//������ ���Ƿ��и�����
							if(flagText==-1){//û��Ӧ������ʱ����ӵ��������У��Զ��ŷָ�
								if(endFlag==-1){//��û�е���һ������5��������
									oworkday.value = oworkday.value+dateText+",";
								} 
								if(dateText==oget12lastFive){//����һ������5�������պ� ĩβ��end
									oworkday.value = oworkday.value+"end";
								} 
							} 
						}
					}
					//����ʱ��ԭ�����ݿ��еĹ����� �Ƚ�
					for(var j in curr_workdaysArr){
						var sysDate =curr_workdaysArr[j];
						if(sysDate== dateText){//�����ǰ��Ԫ���������γ̵Ŀ���������ͬһ�죬�ͷ���һ����������Ԫ�ص��������:
						                     //�ֱ���������Ƿ��ѡ��Ҫ�����������������ӵ�class���Լ���Ԫ���hover�¼�����ʱ��ʾ�����ݣ��൱��a��title��
							return [true, 'hasData', '������'];
						}
					}
					var flag=document.getElementById("oworkday").value.indexOf(dateText);//�õ��Ƿ����
					if(flag!=-1){//�ҵ����ַ���
						return [true, 'hasData', '������'];//oarray[1]="ui-state-disabled";
					}else{
						return [true, 'hasNoData', '�ǹ�����'];//oarray[1]="ui-state-default";
					}
				}else{//������ ����  ����
				    //����ʱ��ԭ�����ݿ��еĹ����� �Ƚ�
					for(var j in curr_workdaysArr){
						var sysDate =curr_workdaysArr[j];
						if(sysDate== dateText){//�����ǰ��Ԫ���������γ̵Ŀ���������ͬһ�죬�ͷ���һ����������Ԫ�ص��������:
						                     //�ֱ���������Ƿ��ѡ��Ҫ�����������������ӵ�class���Լ���Ԫ���hover�¼�����ʱ��ʾ�����ݣ��൱��a��title��
							return [true, 'hasData', '������'];
						}
					}
				}
				/*
				var flag=document.getElementById("oworkday").value.indexOf(dateText);//�õ��Ƿ����
				if(flag!=-1){//�ҵ����ַ���
					return [true, 'hasData', '������'];//oarray[1]="ui-state-disabled";
				}else{
					return [true, 'hasNoData', '�ǹ�����'];//oarray[1]="ui-state-default";
				}*/
				oarray[2]=dateText;
				return oarray;
			},
			beforeShow : function(input){//�����ڿؼ���ʾ���֮ǰ���������¼��������ص�ǰ�����¼��Ŀؼ���ʵ������
			},
			onSelect:function (dateText, inst){//�����������ѡ��һ�����ں󴥷����¼�������Ϊѡ������ں͵�ǰ���ڲ����ʵ��
			    //�����ܼ�
				var ogetdate=new Date(Date.parse(dateText.replace(/-/g, "/"))).getDay();
				var orestOneToFive = document.getElementById("restOneToFive");
				var oworkSixToSun = document.getElementById("workSixToSun");
				var my_workday=document.getElementById("oworkday");
				//��һ������
				if(ogetdate>=1 && ogetdate<=5){
					//���--> �����ǹ�����(��һ������)
					f_appendOrRemoveCurrSelectDate(orestOneToFive,dateText);
				}else{//���--> ��������Ϣ�� ����������
					f_appendOrRemoveCurrSelectDate(oworkSixToSun,dateText);
				} 
				//��ӻ����Ƴ���������
				f_appendOrRemoveCurrSelectDate(my_workday,dateText);
			}
		});
	} 
	
	//���������
	$("#olookup").click(function (){
		if(confirm('ȷʵҪ����'+global_now_year+'������������')){
			//showloading();
			var osubrestOneToFive=document.getElementById("restOneToFive");
			var osubworkSixToSun=document.getElementById("workSixToSun");
			var osuboworkday=document.getElementById("oworkday");
			//�õ���������һ��
			var year_lastDay=getCurYear+'-12-'+calCountday(getCurYear,12);
			//�õ�����ĵ�һ��
			var year_firstDay=getCurYear+'-01-01';
			//alert(osuboworkday.value);
			$('#new_workday').val(osuboworkday.value);
		  $.ajax({
			url:"wfcz_workdaySet.action?curr_year="+global_now_year+"&yearfirstDay="+year_firstDay+"&yearlastDay="+year_lastDay+"&new_workday="+$('#new_workday').val()+"&restOneToFive="+osubrestOneToFive.value+"&workSixToSun="+osubworkSixToSun.value,
			type:'post',
			async:true,
			cache:false,
			timeout: 100000,
			data:null,
			error:function(){
				alert("��������æ�����Ժ����ԣ�");
			},
			success: function(result){
		        if(result=="1"){
		            alert("���������óɹ���");
		         }else{
		             alert("����������ʧ�ܣ�");
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
<body onunload="closeAndRefreshWindow()">
<div id="Mns" style="margin:0 auto;text-align:center;">
	<div class="MnsTbsEdit">
		<div>
		</div>
		<div class="MnsTbsEdit3">
<!--========��Ҫ��������======-->			
<div style="display:block">
<textarea id="new_workday"></textarea>
	<!--�����ǹ�����(��һ������) -->
	<input type="text" id="restOneToFive" size="180"style="display: none" />
	<!-- ��������Ϣ��(����������) -->
	<input type="text" id="workSixToSun" size="180" style="display: none"/>
	<!--��һ������ȫ�깤����-->
	<textarea rows="10" cols="180" id="oworkday" style="display: none"><%=workdays%></textarea>
</div>
<!-- 
<a href="#" id="oyearup" name="lookup" class="easyui-linkbutton">&lt;&lt;��һ��</a>
<span id="todayYear" style="color: #FF0000; font-weight: bold;"></span>
<a href="#" id="oyeardown" name="lookup" class="easyui-linkbutton">��һ��&gt;&gt;</a>-->
<a href="#" id="olookup" name="lookup" class="easyui-linkbutton">���湤��������</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="#" class="easyui-linkbutton" onclick="closeWindow()">�رշ���</a>
		
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