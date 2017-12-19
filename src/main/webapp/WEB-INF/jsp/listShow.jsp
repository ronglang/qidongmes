<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="X-UA-Compatible" content="IE=9" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath%>app/css/initStyle.css" rel="stylesheet" />
	<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	  <script>
			var basePath  = '<%=basePath%>';
			var ctx = '<%=basePath%>';
	</script>
	<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	<script type="text/javascript" language="javascript" src="<%=basePath%>app/js/jquery.dropdown.js"></script>
	<!-- 菜单样式  start-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/app/css/index.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/app/css/style.css">
	<!-- 菜单样式  end-->
	<style>
	
	h1{ font-size:22px;font-family:"微软雅黑 ";font-weight:bold;color: #000000; }
	td{border:1px solid #363636;text-align:center;vertical-align:middle;font-weight:bold; }
	.first-td{
	color:#6399d9;
	font-family:微软雅黑;
	font-size:16px;
	font-weight:bold;
	text-align:left;
	width:150px;
	}
	input{
	 text-align:right;
	  width:50px;
	  color:#ea3f38;
	  border: none;
	}
	.bordernone{
	border-top:1px;
	border-left:0px;
	border-right:0px;
	text-align:center;
	
	}
	.bordertop{
	border-top:0px;
	border-left:0px;
	border-right:0px;
	text-align:center;
	}
	.firstInput{
	  color:#6399d9;
	}
	table{border:1px solid #363636;
       /* 边框合并*/
    border-collapse:collapse; 
    width:98%;
   
    }
	</style>
	
  </head>
  <body >
  	<div id="list" style="width:98%;margin-left:10px;position:absolute; height:100%; overflow:auto;top:0px;">
  	        <div style="width:100%;margin-left:10px;position:relative; top:0px;">
  	           <h1>一、总体情况</h1>
  	           <table>
  	               <tr height="30px">
  	                 <td bgcolor="#85d4f3" style="color:#ffffff" ><input id="cou" style="background-color:#85d4f3;color:#ffffff"></input>个县（区）</td>
  	                 <td>绿灯<input id="cou_green"></input>个</td>
  	                 <td>黄灯<input id="cou_yellow" ></input>个</td>
  	                 <td>红灯<input id="cou_red" class="redInput"></input>个</td>
  	                 <td bgcolor="#ea3f38" style="color:#ffffff"><input id="town" style="background-color:#ea3f38;color:#ffffff"></input>个乡镇</td>
  	                 <td>绿灯<input id="town_green"></input>个</td>
  	                 <td>黄灯<input id="town_yellow" ></input>个</td>
  	                 <td>红灯<input id="town_red" class="redInput"></input>个</td>
  	                 <td bgcolor="#238acc" ><input id="vil" style="background-color:#238acc;color:#ffffff"></input>个村</td>
  	                 <td>绿灯<input id="vil_green"></input>个</td>
  	                 <td>黄灯<input id="vil_yellow" ></input>个</td>
  	                 <td>红灯<input id="vil_red" class="redInput"></input>个</td>
  	               </tr>
  	           </table>
  	        </div>
  	        <div style="width:100%;margin-left:10px;position:relative; top:20px;">
  	           <h1>二、减贫目标任务完成情况</h1>
  	           <table >
  	               <tr height="35px" >
  	                 <td class="first-td" style="border-style:none;"><input style="width: 10px;height:35px;background-color:#6399d9 " />贫困人口减贫</td>
  	                 <td class="bordernone" width="160px";>全年计划减贫<input id="pop_task" class="firstInput"></input>人;</td>
  	                 <td class="bordernone" width="120px";>已达标<input id="pop_result" class="firstInput"></input>人;</td>
  	                 <td class="bordernone" width="180px";>占比<input id="pop_rate" class="firstInput">%</input>;</td>
  	                 <td class="bordernone" width="140px";>状态<input id="pop_state" class="firstInput"></input></td>
  	                 <td width="60px" class="bordernone"></td>
  	               </tr>
  	               <tr height="30px">
  	                 <td>人均纯收入</td>
  	                 <td class="bordernone">已实现人均纯收入<input id="pop_incapt"></input>元</td>
  	                 <td class="bordernone" >占贫困标准线<input id="pop_incapt_rate">%</input></td>
  	                 <td class="bordernone">已达标人均纯收入<input id="pop_upbase"></input>人</td>
  	                 <td class="bordernone">占比<input id="pop_upbase_rate">%</input></td>
  	                 <td  class="bordernone"></td>
  	               </tr>
  	               <tr height="30px">
  	                 <td rowspan="4">户“七有”</td>
  	                 <td class="bordernone">有吃穿保障:已达标<input id="pop_cc"></input>人</td>
  	                 <td class="bordernone">占比<input id="pop_cc_rate"></input>%</td>
  	                 <td style="border-right:0px;">有安全住房:已达标<input id="pop_sh"></input>人</td>
  	                 <td class="bordernone">占比<input id="pop_sh_rate"></input>%</td>
  	                 <td class="bordernone"></td>
  	               </tr>
  	               <tr height="30px">
  	                 <td class="bordernone">有基本医疗:已达标<input id="pop_medi"></input>人</td>
  	                 <td class="bordernone">占比<input id="pop_medi_rate"></input>%</td>
  	                 <td style="border-right:0px;">有基本教育:已达标<input id="pop_edu"></input>人</td>
  	                 <td class="bordernone">占比<input id="pop_edu_rate"></input>%</td>
  	                 <td class="bordernone"></td>
  	               </tr>
  	               <tr height="30px">
  	                 <td class="bordernone">有安全饮水:已达标<input id="pop_sw"></input>人</td>
  	                 <td class="bordernone">占比<input id="pop_sw_rate"></input>%</td>
  	                 <td style="border-right:0px;">有生活用电:已达标<input id="pop_elec"></input>人</td>
  	                 <td class="bordernone">占比<input id="pop_elec_rate"></input>%</td>
  	                 <td class="bordernone"></td>
  	               </tr>
  	               <tr height="30px">
  	                 <td class="bordernone">有电视:已达标<input id="pop_tv"></input>人</td>
  	                 <td class="bordernone">占比<input id="pop_tv_rate">%</input></td>
  	                 <td style="border-right:0px;"></td>
  	                 <td class="bordernone"></td>
  	                 <td class="bordernone"></td>
  	               </tr>
  	                <tr height="35px" >
  	                 <td class="first-td" style="border-style:none"><input style="width:10px;height:35px;border:0px;background-color:#6399d9 " />贫困村销号</td>
  	                 <td class="bordernone" >全年计划销号贫困村<input id="vil_task" class="firstInput"></input>个;</td>
  	                 <td class="bordernone" >已达标<input id="vil_result" class="firstInput"></input>个;</td>
  	                 <td class="bordernone" >占比<input id="vil_rate" class="firstInput">%</input>;</td>
  	                 <td class="bordernone">状态<input id="vil_state" class="firstInput"></input></td>
  	                 <td  class="bordernone"></td>
  	               </tr>
  	               <tr height="30px">
  	                 <td>贫困村销号</td>
  	                 <td class="bordernone" width="160px" >贫困发生率低于3%摘帽村数<input id="vil_pov"></input>个</td>
  	                 <td class="bordernone">占比<input id="vil_pov_rate">%</input></td>
  	                 <td class="bordernone"></td>
  	                 <td class="bordernone"></td>
  	                 <td class="bordernone"></td>
  	               </tr>
  	               <tr height="30px">
  	                 <td rowspan="4">村“七有”</td>
  	                 <td class="bordernone">有集体经济收入:已达标<input id="vil_incom"></input>个</td>
  	                 <td class="bordernone">占比<input id="vil_incom_rate">%</input></td>
  	                 <td style="border-right:0px;">有安全饮水:已达标<input id="vil_sw"></input>个</td>
  	                 <td class="bordernone">占比<input id="vil_sw_rate">%</input></td>
  	                 <td class="bordernone"></td>
  	               </tr>
  	               <tr height="30px">
  	                 <td class="bordernone">有通村硬化公里:已达标<input id="vil_road"></input>个</td>
  	                 <td class="bordernone">占比<input id="vil_road_rate">%</input></td>
  	                 <td style="border-right:0px;">有生活用电:已达标<input id="vil_elec"></input>个</td>
  	                 <td class="bordernone">占比<input id="vil_elec_rate">%</input></td>
  	                 <td class="bordernone"></td>
  	               </tr>
  	               <tr height="30px">
  	                 <td class="bordernone">有广播电视和网络:已达标<input id="vil_tv"></input>个</td>
  	                 <td class="bordernone">占比<input id="vil_tv_rate">%</input></td>
  	                 <td style="border-right:0px;">有村文化室:已达标<input id="vil_culture"></input>个</td>
  	                 <td class="bordernone">占比<input id="vil_culture_rate">%</input></td>
  	                 <td class="bordernone"></td>
  	               </tr>
  	               <tr height="30px">
  	                 <td class="bordernone">有村卫生室:已达标<input id="vil_clinic"></input>人</td>
  	                 <td class="bordernone">占比<input id="vil_clinic_rate">%</input></td>
  	                 <td style="border-right:0px;"></td>
  	                 <td class="bordernone"></td>
  	                 <td class="bordernone"></td>
  	               </tr>
  	                <tr height="35px" >
  	                 <td class="first-td" style="border-style:none"><input style="width:10px;height:35px;border:0px;background-color:#6399d9 " />贫困县摘帽</td>
  	                 <td class="bordernone" width="260px" >全年计划摘帽贫困县<input id="cou_task" class="firstInput"></input>个（乡镇<input id="cou_towns" class="firstInput"></input>个）;</td>
  	                 <td class="bordernone" >已达标<input id="cou_result" class="firstInput"></input>个;</td>
  	                 <td class="bordernone" >占比<input id="cou_rate" class="firstInput">%</input>;</td>
  	                 <td class="bordernone" >状态<input id="cou_state" class="firstInput"></input></td>
  	                 <td class="bordernone"></td>
  	               </tr>
  	               <tr height="30px">
  	                 <td>资金整合</td>
  	                 <td class="bordernone">贫困发生率低于3%摘帽县<input id="cou_pov"></input>个</td>
  	                 <td class="bordernone">占比<input id="cou_pov_rate">%</input></td>
  	                 <td class="bordernone"></td>
  	                 <td class="bordernone"></td>
  	                 <td class="bordernone"></td>
  	               </tr>
  	               <tr height="30px">
  	                 <td rowspan="2">县“三有”</td>
  	                 <td class="bordernone">乡有标准中心校:已达标<input id="cou_school"></input>个</td>
  	                 <td class="bordernone">占比<input id="cou_school_rate">%</input></td>
  	                 <td style="border-right:0px;">乡有达标卫生院:已达标<input id="cou_hosp"></input>个</td>
  	                 <td class="bordernone">占比<input id="cou_hosp_rate">%</input></td>
  	                 <td class="bordernone"></td>
  	               </tr>
  	               <tr height="30px">
  	                 <td class="bordernone">乡有便民服务中心:已达标<input id="cou_ser"></input>个</td>
  	                 <td class="bordernone">占比<input id="cou_ser_rate">%</input></td>
  	                 <td style="border-right:0px;"></td>
  	                 <td class="bordernone"></td>
  	                 <td class="bordernone"></td>
  	               </tr>
  	           </table>
  	        </div>
  	        <div style="width:100%;margin-left:10px;position:relative; top:35px;">
  	           <h1>三、主要工作和政策措施落实兑现情况</h1>
  	           <table >
  	           <tr height="30px">
  	                 <td rowspan="2">资金整合</td>
  	                 <td class="bordernone">全年整合资金<input id="zjzh_plan" class="firstInput"></input>亿元</td>
  	                 <td class="bordernone">已到位资金<input id="zjzh_result" class="firstInput"></input>亿元</td>
  	                 <td class="bordernone">占比<input id="zjzh_rate" class="firstInput">%</input></td>
  	                 <td class="bordernone">状态<input id="zjzh_state" class="firstInput"></input></td>
  	                 <td class="bordernone" width="120px"></td>
  	                 <td class="bordernone" width="120px"></td>
  	               </tr>
  	               <tr height="30px">
  	                 <td class="bordernone">计划总投资<input id="zjzh_xm_plan" class="firstInput"></input>亿元</td>
  	                 <td class="bordernone">已执行<input id="zjzh_xm_result" class="firstInput"></input>亿元</td>
  	                 <td class="bordernone">占比<input id="zjzh_xm_rate" class="firstInput">%</input></td>
  	                 <td class="bordernone">状态<input id="zjzh_xm_state" class="firstInput"></input></td>
  	                 <td class="bordernone"></td>
  	                 <td class="bordernone"></td>
  	               </tr>
  	               <tr height="30px">
  	                 <td rowspan="4">五个一批</td>
  	                 <td class="bordernone">全年计划实施“五个一批”<input id="wgyp_plan" class="firstInput"></input>人次</td>
  	                 <td class="bordernone">已完成<input id="wgyp_result" class="firstInput"></input>人次</td>
  	                 <td class="bordernone">占比<input id="wgyp_rate" class="firstInput">%</input></td>
  	                 <td class="bordernone">状态<input id="wgyp_state" class="firstInput"></input></td>
  	                 <td class="bordernone" ></td>
  	                 <td class="bordernone"></td>
  	               </tr>
  	               <tr height="30px">
  	                 <td class="bordernone">计划实施生产就业发展一批<input id="jysc_plan"></input>人</td>
  	                 <td class="bordernone">已完成<input id="jysc_result"></input>人</td>
  	                 <td class="bordernone">占比<input id="jysc_rate">%</input></td>
  	                 <td style="border-right:0px;">计划实施移民搬迁安置一批<input id="ymbq_plan"></input>人</td>
  	                 <td class="bordernone">已完成<input id="ymbq_result"></input>人</td>
  	                 <td class="bordernone">占比<input id="ymbq_rate">%</input></td>
  	               </tr>
  	               <tr height="30px">
  	                 <td class="bordernone">计划实施低保政策兜底一批<input id="dbdd_plan"></input>人</td>
  	                 <td class="bordernone">已完成<input id="dbdd_result"></input>人</td>
  	                 <td class="bordernone">占比<input id="dbdd_rate">%</input></td>
  	                 <td style="border-right:0px;">计划实施医疗救助扶持一批<input id="yljz_plan"></input>人</td>
  	                 <td class="bordernone">已完成<input id="yljz_result"></input>人</td>
  	                 <td class="bordernone">占比<input id="yljz_rate">%</input></td>
  	               </tr>
  	               <tr height="30px">
  	                 <td class="bordernone">计划实施灾后重建一批<input id="zhcj_plan"></input>人</td>
  	                 <td class="bordernone">已完成<input id="zhcj_result"></input>人</td>
  	                 <td class="bordernone">占比<input id="zhcj_rate">%</input></td>
  	                 <td style="border-right:0px;"></td>
  	                 <td class="bordernone"></td>
  	                 <td class="bordernone"></td>
  	               </tr>
  
  	               <tr height="30px">
  	                 <td width="120px">村规划</td>
  	                 <td  height="80px" style="text-align: left;" colspan="6" class="bordernone">
  	                   <p><input style="width: 30px;border:0px;" />十七个专项年度计划总投资<input id="bmzj_plan" class="firstInput"></input>亿元；已完成投资<input id="bmzj_result" class="firstInput"></input>亿元；占比<input id="bmzj_rate" class="firstInput">%</input>；状态<input id="bmzj_state" class="firstInput"></p>
  	                   <p><input style="width: 30px;border:0px;" />其中<input type="text" id="bmzj_green"  style="width:100px;text-align:left;color:#555555;"></input>专项完成占比均在<input id="bmzj_green_rate" style="width:30px;" >%</input>以上，状态：<span style="color:#ea3f38;">绿灯</span></p>
  	                   <p><input style="width: 30px;border:0px;" />其中<input type="text" id="bmzj_yellow" style="width:100px;text-align:left;color:#555555;"></input>专项完成占比低于<input id="bmzj_yellow_rate" style="width:30px;">%</input>，状态：<span style="color:#ea3f38;">黄灯</span></p>
  	                   <p><input style="width: 30px;border:0px;" />其中<input type="text" id="bmzj_red" style="width:100px;text-align:left;color:#555555;"></input>专项完成占比低于<input id="bmzj_red_rate" style="width:30px;">%</input>，状态：<span style="color:#ea3f38;">红灯</span></p>
  	                 </td>
  	               </tr>
  	               <tr height="30px">
  	                 <td width="120px">连片扶贫开发</td>
  	                 <td  height="80px" colspan="6" class="bordernone" style="text-align: left;">
  	                   <p><input style="width: 30px;border:0px;" />计划实施<input id="lpfp_pian" class="firstInput" ></input>片；计划总投资<input id="lpfp_task" class="firstInput"></input>亿元；已启动建设村<input id="lpfp_qd" class="firstInput"></input>个；占比<input id="lpfp_qd_rate" class="firstInput">%</input>；已完成投资<input id="lpfp_result" class="firstInput"></input> 亿元；占比<input id="lpfp_rate" class="firstInput">%</input>；状态<input id="lpfp_state" class="firstInput"></input></p>
  	                   <p><input style="width: 30px;border:0px;" />其中完成交通、水利、电力、通讯等基础设施投资<input id="lpfp_basic"></input>万元；其中产业发展投资<input id="lpfp_cyfz"></input>万元</p>
  	                    <p><input style="width: 30px;border:0px;" />其中公共服务建设投资<input id="lpfp_ggfw"></input>万元；其中生态环境建设投资<input id="lpfp_sthj"></input>万元</p>
  	                 </td>
  	               </tr>
  	           </table>
  	        </div>
  	        <div style="width:100%;margin-left:10px;position:relative; top:45px;">
  	           <h1>四、一本台账建立情况</h1>
  	            <table >
  	             <tr>
  	               <td width="120px">台账建立</td>
  	               <td height="80px" style="text-align:left;">
  	                 <p><input style="width: 30px;border:0px;" />全市现有未脱贫贫困户<input id="tz_plan" class="firstInput"></input>户；已建立台账<input id="tz_result" class="firstInput"></input>户；占比<input id="tz_rate" class="firstInput">%</input></p>
  	                 <p><input style="width: 30px;border:0px;" />其中人均收入高于扶贫标准<input id="tz_upbase"></input>户；占比<input id="tz_upbase_rate">%</input> </p>
  	                 <p><input style="width: 30px;border:0px;" />其中贫困户人均现金收入<input id="tz_rjxjsr"></input>元，人均现金支出<input id="tz_rjxjzc"></input>元，实现人均纯收入<input id="tz_incapt"></input>元</p>
  	               </td>
  	             </tr>
  	           </table>
  	        </div>
  	     </div>
  </body>
  <script>
   $("input").attr("readOnly",true);  
   
   $(document).ready(function(){
	   
	  var areaCode=${session_user.areaCode};
	  var urlf=basePath+"rest/sysUserManageAction/getReportData?areaCode="+areaCode;
	   $.ajax({
		   type:"post",
		   url:urlf,
		   data:"",
		   dataType:"json",
		   success:function(data){
			   var info=data.data;//请求返回数据结果集
			   var zx=info.bmzj;//十七个专项结果集
			   var tz=info.tz;//台账结果集
			   var lp=info.lpfp;//连片扶贫结果集
			   var gh=info.viltp;//村规划结果集
			   var wgyp=info.wgyp;//五个一批结果集
			   var zj=info.zjzh;//资金整合结果集
			   var cou=info.cou;//贫困县摘帽结果集
			   var vil=info.vil;//贫困村摘帽结果集
			   var pop=info.pop;//贫困人口减贫结果集
			   
			   $("#cou").val(info.jc_cou==null?0:info.jc_cou);
			   $("#cou_green").val(info.jc_cou_green==null?0:info.jc_cou_green);
			   $("#cou_yellow").val(info.jc_cou_yellow==null?0:info.jc_cou_yellow);
			   $("#cou_red").val(info.jc_cou_red==null?0:info.jc_cou_red);
			   $("#town").val(info.jc_town==null?0:info.jc_town);
			   $("#town_green").val(info.jc_town_green==null?0:info.jc_town_green);
			   $("#town_yellow").val(info.jc_town_yellow==null?0:info.jc_town_yellow);
			   $("#town_red").val(info.jc_town_red==null?0:info.jc_town_red);
			   $("#vil").val(info.jc_vil==null?0:info.jc_vil);
			   $("#vil_green").val(info.jc_vil_green==null?0:info.jc_vil_green);
			   $("#vil_yellow").val(info.jc_vil_yellow==null?0:info.jc_vil_yellow);
			   $("#vil_red").val(info.jc_vil_red==null?0:info.jc_vil_red);
			   //贫困人口
			   $("#pop_task").val(pop.pop_task==null?0:pop.pop_task);
			   $("#pop_result").val(pop.pop_result==null?0:pop.pop_result);
			   $("#pop_rate").val(pop.pop_rate==null?0:pop.pop_rate);
			   $("#pop_state").val(pop.pop_state==null?0:pop.pop_state);
			   $("#pop_incapt").val(pop.pop_incapt==null?0:pop.pop_incapt);
			   $("#pop_incapt_rate").val(pop.pop_incapt_rate==null?0:pop.pop_incapt_rate);
			   $("#pop_upbase ").val(pop.pop_upbase==null?0:pop.pop_upbase );
			   $("#pop_upbase_rate").val(pop.pop_upbase_rate==null?0:pop.pop_upbase_rate);
			   $("#pop_cc").val(pop.pop_cc==null?0:pop.pop_cc);
			   $("#pop_cc_rate").val(pop.pop_cc_rate==null?0:pop.pop_cc_rate);
			   $("#pop_sh").val(pop.pop_sh==null?0:pop.pop_sh);
			   $("#pop_sh_rate").val(pop.pop_sh_rate==null?0:pop.pop_sh_rate);
			   $("#pop_medi").val(pop.pop_medi==null?0:pop.pop_medi);
			   $("#pop_medi_rate").val(pop.pop_medi_rate==null?0:pop.pop_medi_rate);
			   $("#pop_edu ").val(pop.pop_edu==null?0:pop.pop_edu );
			   $("#pop_edu_rate").val(pop.pop_edu_rate==null?0:pop.pop_edu_rate);
			   $("#pop_sw").val(pop.pop_sw==null?0:pop.pop_sw);
			   $("#pop_sw_rate ").val(pop.pop_sw_rate==null?0:pop.pop_sw_rate );
			   $("#pop_elec").val(pop.pop_elec==null?0:pop.pop_elec);
			   $("#pop_elec_rate").val(pop.pop_elec_rate==null?0:pop.pop_elec_rate);
			   $("#pop_tv").val(pop.pop_tv==null?0:pop.pop_tv);
			   $("#pop_tv_rate").val(pop.pop_tv_rate==null?0:pop.pop_tv_rate);
			   //贫困村摘帽
			   $("#vil_task").val(vil.vil_task==null?0:vil.vil_task);
			   $("#vil_result").val(vil.vil_result==null?0:vil.vil_result);
			   $("#vil_rate").val(vil.vil_rate==null?0:vil.vil_rate);
			   $("#vil_state").val(vil.vil_state==null?0:vil.vil_state);
			   $("#vil_pov").val(vil.vil_pov==null?0:vil.vil_pov );
			   $("#vil_pov_rate").val(vil.vil_pov_rate==null?0:vil.vil_pov_rate);
			   $("#vil_incom").val(vil.vil_incom==null?0:vil.vil_incom);
			   $("#vil_incom_rate").val(vil.vil_incom_rate==null?0:vil.vil_incom_rate);
			   $("#vil_sw").val(vil.vil_sw==null?0:vil.vil_sw);
			   $("#vil_sw_rate").val(vil.vil_sw_rate==null?0:vil.vil_sw_rate);
			   $("#vil_elec").val(vil.vil_elec==null?0:vil.vil_elec);
			   $("#vil_elec_rate").val(vil.vil_elec_rate==null?0:vil.vil_elec_rate);
			   $("#vil_road").val(vil.vil_road==null?0:vil.vil_road);
			   $("#vil_road_rate").val(vil.vil_road_rate==null?0:vil.vil_road_rate);
			   $("#vil_clinic").val(vil.vil_clinic==null?0:vil.vil_clinic);
			   $("#vil_clinic_rate").val(vil.vil_clinic_rate==null?0:vil.vil_clinic_rate);
			   $("#vil_culture").val(vil.vil_culture==null?0:vil.vil_culture);
			   $("#vil_culture_rate").val(vil.vil_culture_rate==null?0:vil.vil_culture_rate);
			   $("#vil_tv").val(vil.vil_tv==null?0:vil.vil_tv);
			   $("#vil_tv_rate").val(vil.vil_tv_rate==null?0:vil.vil_tv_rate);
			   //贫困县摘帽
			   $("#cou_task").val(cou.cou_task==null?0:cou.cou_task);
			   $("#cou_towns").val(cou.cou_towns==null?0:cou.cou_towns);
			   $("#cou_result").val(cou.cou_result==null?0:cou.cou_result);
			   $("#cou_rate").val(cou.cou_rate==null?0:cou.cou_rate);
			   $("#cou_state").val(cou.cou_state==null?0:cou.cou_state );
			   $("#cou_pov").val(cou.cou_pov==null?0:cou.cou_pov);
			   $("#cou_pov_rate").val(cou.cou_pov_rate==null?0:cou.cou_pov_rate);
			   $("#cou_school").val(cou.cou_school==null?0:cou.cou_school);
			   $("#cou_school_rate").val(cou.cou_school_rate==null?0:cou.cou_school_rate);
			   $("#cou_hosp").val(cou.cou_hosp==null?0:cou.cou_hosp);
			   $("#cou_hosp_rate").val(cou.cou_hosp_rate==null?0:cou.cou_hosp_rate);
			   $("#cou_ser").val(cou.cou_ser==null?0:cou.cou_ser);
			   $("#cou_ser_rate").val(cou.cou_ser_rate==null?0:cou.cou_ser_rate);
			  //资金整合zj
			   $("#zjzh_plan").val(zj.zjzh_plan==null?0:zj.zjzh_plan);
			   $("#zjzh_result").val(zj.zjzh_result==null?0:zj.zjzh_result);
			   $("#zjzh_rate").val(zj.zjzh_rate==null?0:zj.zjzh_rate);
			   $("#zjzh_state").val(zj.zjzh_state==null?0:zj.zjzh_state);
			   $("#zjzh_xm_plan").val(zj.zjzh_xm_plan==null?0:zj.zjzh_xm_plan);
			   $("#zjzh_xm_result").val(zj.zjzh_xm_result==null?0:zj.zjzh_xm_result);
			   $("#zjzh_xm_rate").val(zj.zjzh_xm_rate==null?0:zj.zjzh_xm_rate);
			   $("#zjzh_xm_state").val(zj.zjzh_xm_state==null?0:zj.zjzh_xm_state);
			   //五个一批wgyp
			   $("#wgyp_plan").val(wgyp.wgyp_plan==null?0:wgyp.wgyp_plan);
			   $("#wgyp_result").val(wgyp.wgyp_result==null?0:wgyp.wgyp_result);
			   $("#wgyp_rate").val(wgyp.wgyp_rate==null?0:wgyp.wgyp_rate);
			   $("#wgyp_state").val(wgyp.wgyp_state==null?0:wgyp.wgyp_state);
			   $("#jysc_plan").val(wgyp.jysc_plan==null?0:wgyp.jysc_plan );
			   $("#jysc_result").val(wgyp.jysc_result==null?0:wgyp.jysc_result);
			   $("#jysc_rate").val(wgyp.jysc_rate==null?0:wgyp.jysc_rate);
			   $("#ymbq_plan").val(wgyp.ymbq_plan==null?0:wgyp.ymbq_plan);
			   $("#ymbq_result").val(wgyp.ymbq_result==null?0:wgyp.ymbq_result);
			   $("#ymbq_rate").val(wgyp.ymbq_rate==null?0:wgyp.ymbq_rate);
			   $("#dbdd_plan").val(wgyp.dbdd_plan==null?0:wgyp.dbdd_plan);
			   $("#dbdd_result").val(wgyp.dbdd_result==null?0:wgyp.dbdd_result);
			   $("#dbdd_rate").val(wgyp.dbdd_rate==null?0:wgyp.dbdd_rate);
			   $("#yljz_plan").val(wgyp.yljz_plan==null?0:wgyp.yljz_plan);
			   $("#yljz_result").val(wgyp.yljz_result==null?0:wgyp.yljz_result);
			   $("#yljz_rate").val(wgyp.yljz_rate==null?0:wgyp.yljz_rate);
			   $("#zhcj_plan").val(wgyp.zhcj_plan==null?0:wgyp.zhcj_plan);
			   $("#zhcj_result").val(wgyp.zhcj_result==null?0:wgyp.zhcj_result);
			   $("#zhcj_rate").val(wgyp.zhcj_rate==null?0:wgyp.zhcj_rate);
			   //十七个专项zx
			   $("#bmzj_plan").val(zx.bmzj_plan==null?0:zx.bmzj_plan);
			   $("#bmzj_result").val(zx.bmzj_result==null?0:zx.bmzj_result);
			   $("#bmzj_rate").val(zx.bmzj_rate==null?0:zx.bmzj_rate);
			   $("#bmzj_state").val(zx.bmzj_state==null?0:zx.bmzj_state);
			   $("#bmzj_green").val(zx.bmzj_green==null?0:zx.bmzj_green);
			   $("#bmzj_green_rate").val(zx.bmzj_green_rate==null?0:zx.bmzj_green_rate);
			   $("#bmzj_yellow").val(zx.bmzj_yellow==null?0:zx.bmzj_yellow);
			   $("#bmzj_yellow_rate").val(zx.bmzj_yellow_rate==null?0:zx.bmzj_yellow_rate);
			   $("#bmzj_red").val(zx.bmzj_red==null?0:zx.bmzj_red);
			   $("#bmzj_red_rate").val(zx.bmzj_red_rate==null?0:zx.bmzj_red_rate);
			   //连片开发lp
			   $("#lpfp_pian").val(lp.lpfp_pian==null?0:lp.lpfp_pian);
			   $("#lpfp_qd").val(lp.lpfp_qd==null?0:lp.lpfp_qd);
			   $("#lpfp_qd_rate").val(lp.lpfp_qd_rate==null?0:lp.lpfp_qd_rate);
			   $("#lpfp_task").val(lp.lpfp_task==null?0:lp.lpfp_task);
			   $("#lpfp_result").val(lp.lpfp_result==null?0:lp.lpfp_result);
			   $("#lpfp_rate").val(lp.lpfp_rate==null?0:lp.lpfp_rate);
			   $("#lpfp_state").val(lp.lpfp_state==null?0:lp.lpfp_state);
			   $("#lpfp_basic").val(lp.lpfp_basic==null?0:lp.lpfp_basic);
			   $("#lpfp_cyfz").val(lp.lpfp_cyfz==null?0:lp.lpfp_cyfz);
			   $("#lpfp_ggfw").val(lp.lpfp_ggfw==null?0:lp.lpfp_ggfw);
			   $("#lpfp_sthj").val(lp.lpfp_sthj==null?0:lp.lpfp_sthj);
			   //台账tz
			   $("#tz_plan").val(tz.tz_plan==null?0:tz.tz_plan);
			   $("#tz_result").val(tz.tz_result==null?0:tz.tz_result);
			   $("#tz_rate").val(tz.tz_rate==null?0:tz.tz_rate);
			   $("#tz_upbase").val(tz.tz_upbase==null?0:tz.tz_upbase);
			   $("#tz_upbase_rate").val(tz.tz_upbase_rate==null?0:tz.tz_upbase_rate);
			   $("#tz_rjxjsr").val(tz.tz_rjxjsr==null?0:tz.tz_rjxjsr);
			   $("#tz_rjxjzc").val(tz.tz_rjxjzc==null?0:tz.tz_rjxjzc);
			   $("#tz_incapt").val(tz.tz_incapt==null?0:tz.tz_incapt);
		       
			   var lpfp_sthj=lp.lpfp_sthj.length;
			   var lpfp_ggfw=lp.lpfp_ggfw.length;
			   var lpfp_basic=lp.lpfp_basic.length;
			   var lpfp_cyfz=lp.lpfp_cyfz.length;
			   var green= zx.bmzj_green.length;
			   var yellow=zx.bmzj_yellow.length;
			   var red=zx.bmzj_red.length;
			   var wgyp_plan=wgyp.wgyp_plan.length;
			   var wgyp_result=wgyp.wgyp_result.length;
			    var pop_upbase=pop.pop_upbase.length;
			   var pop_cc=pop.pop_cc.length;
			   var pop_sh=pop.pop_sh.length;
			   var pop_medi=pop.pop_medi.length;
			   var pop_edu=pop.pop_edu.length;
			   var pop_sw=pop.pop_sw.length;
			   var pop_elec=pop.pop_elec.length;
			   var pop_tv=pop.pop_tv.length;
			   
			   var cou_task=cou.cou_task.length;
			   var cou_towns=cou.cou_towns.length;
	//汉字input
			   $("#bmzj_green").css('width', green*15 + 'px');
			   $("#bmzj_yellow").css('width', yellow*15 + 'px');
			   $("#bmzj_red").css('width', red*15 + 'px');
	//数字input
	           $("#cou_task").css('width', cou_task*8 + 'px');
			   $("#cou_towns").css('width', cou_towns*8 + 'px');
			   $("#lpfp_cyfz").css('width', lpfp_cyfz*8 + 'px');
			   $("#lpfp_basic").css('width', lpfp_basic*8 + 'px');
			   $("#lpfp_ggfw").css('width', lpfp_ggfw*8 + 'px');
			   $("#lpfp_sthj").css('width', lpfp_sthj*8 + 'px');
			   $("#wgyp_plan").css('width', wgyp_plan*8 + 'px');
			   $("#wgyp_result").css('width', wgyp_result*8 + 'px');
			   $("#pop_cc").css('width', pop_cc*8 + 'px');
			   $("#pop_sh").css('width', pop_sh*8 + 'px');
			   $("#pop_medi").css('width', pop_medi*8 + 'px');
			   $("#pop_edu").css('width', pop_edu*8 + 'px');
			   $("#pop_sw").css('width', pop_sw*8 + 'px');
			   $("#pop_elec").css('width', pop_elec*8 + 'px');
			   $("#pop_tv").css('width', pop_tv*8 + 'px');
			   $("#pop_upbase").css('width', pop_upbase*8 + 'px');
	       },
		   error:function(){
		   }
	   });
 });

  </script>
</html>
