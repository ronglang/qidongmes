<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
						 {    
			    margin: 0;    
			    padding: 0;    
			}    
			#div2 {    
			    margin: auto;    
			    width: 100%;    
			    overflow: hidden;    
			    left: 20px;
			    height:200px;
			}    
			#div1 {    
			    left: 0px;    
			    width: 100%; 
			}    
			#div1 li {    
			    list-style-type: none;    
			    float: left;    
			    width:100%;    
			    height: 100px;
			}    
			   
			ul#ul1 {    
			    position: relative; 
			}    

		</style>
		<script src="<%=basePath %>core/js/jquery-2.1.4.min.js" type="text/javascript"></script>
	</head>

	<body>
	  
		 <!--   <div class="call" style="width: 99%;position: absolute; margin-top: 10px;">
				<marquee id="affiche" align="left" behavior="scroll" bgcolor="#DEDEDE" direction="left" height="50" width="100%" hspace="0" vspace="0 loop=" -1 " scrollamount="10 " scrolldelay="100 " onMouseOut="this.start() " onMouseOver="this.stop() ">
				
				</marquee>
		</div> -->
		  <div id="div2">    
        <div id="div1">    
            <ul id="ul1">    
               <c:forEach items="${list}" var="vo">
               <li>
               <marquee id="affiche" align="left" behavior="scroll" bgcolor="#DEDEDE" direction="left" height="50" width="100%" hspace="0" vspace="0 loop=" -1 " scrollamount="10 " scrolldelay="100 " onMouseOut="this.start() " onMouseOver="this.stop() ">
               <span id="sp" style="font-size: 26px;color: red;">维修机台:${vo.macCode},维修人员:${vo.repairBy},故障时间:${vo.faultDate}</span>
               </marquee>
               </li>
               </c:forEach> 
            </ul>    
        </div>    
    </div> 
	
	</body>
	<script type="text/javascript">
	 window.onload = function ()    
	   {    
	       var oUl = document.getElementById ('ul1');    
	       var t,o;    
	       var speed = 0;    
	       var funny = function ()    
	       {    
	           t && clearInterval(t);    
	           t = setInterval (function ()    
	           {    
	                speed -= 200/11;    
	                if(speed<-200){    
	                    speed=0;
	                    oUl.appendChild(oUl.childNodes[0]);
	                   // oUl.appendChild($(oUl).children[0]);    
	                    t && clearInterval(t);    
	                    t = null;    
	                    o && clearTimeout(o);    
	                    o=setTimeout(funny,1000);    
	                    }    
	               oUl.style.left = 10 + "px";    
	           }, 2000);    
	       };    
	        funny ();    
	   }  ;  
	
			
			setInterval(function(){
				setTime();
				},100000*6);
			$(function(){
				//$("#sp").text("暂无提示信息" );
			
				
				//myload();
				
			});
		
		function setTime(){
			
			window.location.reload(true);
		<%-- 	$.post("<%=basePath %>rest/mauMachineFaultManageAction/getListFault?requestType=mapuser",{},function(data){
				var data=data.fault;
				setData(data);
				
			},"json"); --%>
				}
		/* 
		 function setData(data){
			 var json =data;
			 for (var int = 0; int < json.length; int++) {
					 $("#sp").text("维修机台:"+json[int].macCode+",维修人员:"+json[int].repairBy+",故障时间:"+json[int].faultDate);
				}
			
		 } */
		function load(jsonBean){
			debugger;
			$("#sp").text("警告:"+jsonBean+"!");
		}
		 function myload(){
			  	var basePath = "<%=basePath%>";
					var urlPirfex = basePath.substring(7, basePath.length);
					var url = "ws://"+urlPirfex+"storeCallWebSocket";
					var webSocket = new WebSocket(url);
					webSocket.onerror = function(event) {
						onError(event);
					};
				
					webSocket.onopen = function(event) {
						onOpen(event);
					};
				
					webSocket.onmessage = function(event) {
						onMessage(event);
					};
				
					function onMessage(event) {
						var jsonBean = eval('('+event.data+')');
						load(jsonBean);
						
					}
					function onOpen(event) { 
						webSocket.send("true");
					}
				
					function onError(event) {
					}
			  }
	
	</script>
</html>